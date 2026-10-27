const fs=require('node:fs'),path=require('node:path');
const {calculate}=require('./engine');
const dir=process.argv[2]?path.resolve(process.argv[2]):path.resolve(__dirname,'../rea-evidence/improvements/samples');
const branches='子丑寅卯辰巳午未申酉戌亥';
function parse(raw){
 const palaces={};
 for(const branch of branches){
  const start=raw.indexOf('\n'+branch+'宫\n');if(start<0)throw Error('Missing palace '+branch);
  const suffix=raw.slice(start+1),next=suffix.slice(4).search(/\n[子丑寅卯辰巳午未申酉戌亥]宫\n/);
  const block=next>=0?suffix.slice(0,next+4):suffix;
  const field=name=>block.match(new RegExp(name+'：([^\\n]+)'))?.[1]||'';
  const stars={};for(const line of block.split('宫内星：\n')[1].split('\n'+branch+'宫产生的四化')[0].trim().split('\n')){const [star,b='']=line.split(':');stars[star]=b;}
  palaces[branch]={name:field('宫名'),stem:field('宫干'),decadal:field('大限'),stars,block};
 }return palaces;
}
const pairs={},samples=[];
for(const file of fs.readdirSync(dir).filter(f=>f.endsWith('-input.json')).sort()){
 const meta=JSON.parse(fs.readFileSync(path.join(dir,file),'utf8')),key=file.replace('-input.json','');
 const raw=fs.readFileSync(path.join(dir,key+'.txt'),'utf8').replace(/\r/g,'');
 const d=meta.input,input=`1|${+d.slice(0,4)}|${+d.slice(4,6)}|${+d.slice(6,8)}|${+d.slice(8,10)}|${+d.slice(10,12)}|${+d.slice(12,14)}|120|-8|${meta.sex}|0|0`;
 const expected=parse(raw),actual=calculate(input)['十二宫信息'],baseline=calculate(input,{observedBrightness:false})['十二宫信息'];
 const matches={palaceNames:0,stems:0,decadals:0,majorPositions:0,transformations:0,brightness:0},differences=[];let brightnessTotal=0,baselineBrightness=0;
 for(const b of branches){
  const e=expected[b],a=actual[b],alias=n=>n==='仆役'?'交友':n;
  if(e.name===alias(a['宫'])+'宫')matches.palaceNames++;
  if(e.stem===a['宫干'])matches.stems++;
  if(e.decadal===a['大限'])matches.decadals++;
  for(const [star,type] of Object.entries(a['星列表'])){
   if(type==='major'&&star in e.stars)matches.majorPositions++;
   if(star in e.stars){brightnessTotal++;if(baseline[b]['星庙旺'][star]['庙旺']===e.stars[star])baselineBrightness++;const val=a['星庙旺'][star]['庙旺'];if(val===e.stars[star])matches.brightness++;else differences.push({branch:b,star,expected:e.stars[star],actual:val});}
  }
  for(const [star,t] of Object.entries(a['四化']))if(e.block.includes('使在'+t['支']+'的'+alias(t['宫'])+'宫里的'+star+'化'+t['化']))matches.transformations++;
  if(meta.sample_role==='calibration')for(const [star,val] of Object.entries(e.stars)){const k=star+'|'+b;(pairs[k]??=[]).push({value:val,sample:key});}
 }
 samples.push({key,role:meta.sample_role,matches,baselineBrightness,expected:{palaceNames:12,stems:12,decadals:12,majorPositions:14,transformations:48,brightness:brightnessTotal},differences});
}
const candidates={},conflicts={};for(const [k,values] of Object.entries(pairs)){
 const unique=[...new Set(values.map(x=>x.value))];
 if(unique.length>1)conflicts[k]=values;
 else if(values.length>=2)candidates[k]={value:unique[0],observations:values.length,samples:values.map(x=>x.sample)};
}
const report={source:'Original unchanged APK public copy-chart output; REA screenshots for each input. Calibration and held-out roles fixed before collection.',samples,candidateBrightness:candidates,conflictingBrightness:conflicts,limitations:'Limited dates, longitude 120E, Gregorian inputs. Agreement here does not establish full algorithm or visual parity.'};
fs.writeFileSync(path.join(dir,'comparison.json'),JSON.stringify(report,null,2));
console.log(JSON.stringify({samples:samples.map(({key,role,matches,expected})=>({key,role,matches,expected})),candidateCount:Object.keys(candidates).length,conflictCount:Object.keys(conflicts).length},null,2));
