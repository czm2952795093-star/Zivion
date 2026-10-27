const fs=require('node:fs'),path=require('node:path'),assert=require('node:assert/strict');
const {calculate}=require('./engine');
const oldTable=require('./observed-brightness-v04.json').values,newTable=require('./observed-brightness.json').values;
const root=path.resolve(__dirname,'../rea-evidence'),branches='子丑寅卯辰巳午未申酉戌亥';
function parse(raw){const out={};for(const b of branches){const suffix=raw.split('\n'+b+'宫\n')[1];assert.ok(suffix,'Missing palace '+b);const next=suffix.search(/\n[子丑寅卯辰巳午未申酉戌亥]宫\n/),block=next<0?suffix:suffix.slice(0,next);
 const stars={};for(const line of block.split('宫内星：\n')[1].split('\n'+b+'宫产生的四化')[0].trim().split('\n')){const [star,brightness='']=line.split(':');stars[star]=brightness;}
 const field=name=>block.match(new RegExp(name+'：([^\\n]+)'))?.[1]||'';out[b]={block,stars,name:field('宫名'),stem:field('宫干'),decadal:field('大限')};}return out;}
const samples=[];
for(const folder of ['improvements','v03','v04','v05']){const dir=path.join(root,folder,'samples');
 for(const file of fs.readdirSync(dir).filter(f=>f.endsWith('-input.json')).sort()){
  const meta=JSON.parse(fs.readFileSync(path.join(dir,file),'utf8')),key=file.replace('-input.json',''),d=meta.input;
  const request=`1|${+d.slice(0,4)}|${+d.slice(4,6)}|${+d.slice(6,8)}|${+d.slice(8,10)}|${+d.slice(10,12)}|${+d.slice(12,14)}|120|-8|${meta.sex}|0|0`;
  const expected=parse(fs.readFileSync(path.join(dir,key+'.txt'),'utf8').replace(/\r/g,''));const actual=calculate(request)['十二宫信息'],fallback=calculate(request,{observedBrightness:false})['十二宫信息'];
  const scores={stars:0,positions:0,extra:0,brightnessBefore:0,brightnessAfter:0,mapped:0,mappedConflicts:0,palaces:0,stems:0,decadals:0,major:0,transforms:0},differences=[];
  for(const b of branches){const e=expected[b],a=actual[b],alias=n=>(n==='仆役'?'交友':n)+'宫';
   if(alias(a['宫'])===e.name)scores.palaces++;if(a['宫干']===e.stem)scores.stems++;if(a['大限']===e.decadal)scores.decadals++;
   for(const [star,value] of Object.entries(e.stars)){scores.stars++;const pair=star+'|'+b;if(!(star in a['星列表'])){differences.push({branch:b,missing:star});continue;}scores.positions++;if(a['星列表'][star]==='major')scores.major++;
    const before=Object.hasOwn(oldTable,pair)?oldTable[pair]:fallback[b]['星庙旺'][star]['庙旺'],after=a['星庙旺'][star]['庙旺'];
    if(before===value)scores.brightnessBefore++;if(after===value)scores.brightnessAfter++;else differences.push({branch:b,star,expected:value,actual:after});
    if(Object.hasOwn(newTable,pair)){scores.mapped++;if(after!==value)scores.mappedConflicts++;}
   }
   scores.extra+=Object.keys(a['星列表']).filter(s=>!(s in e.stars)).length;
   for(const [star,t] of Object.entries(a['四化']))if(e.block.includes('使在'+t['支']+'的'+alias(t['宫'])+'里的'+star+'化'+t['化']))scores.transforms++;
  }
  samples.push({key,role:folder==='v05'?'holdout':'development',scores,differences});
 }
}
const totals={};for(const role of ['development','holdout']){const subset=samples.filter(s=>s.role===role),sum={samples:subset.length};for(const s of subset)for(const [k,v] of Object.entries(s.scores))sum[k]=(sum[k]||0)+v;totals[role]=sum;}
const report={source:'36 previous original charts reused for development;6 new dates declared before collection as held-out verification;552-pair table frozen before holdout collection.',totals,samples,limits:'Counts include blank brightness labels. Limited Gregorian /120E /UTC+8 /fixLeap off; not all-date or full-app parity.'};
fs.writeFileSync(path.join(root,'v05/comparison.json'),JSON.stringify(report,null,2));console.log(JSON.stringify(totals,null,2));
if(process.argv.includes('--verify')){
 const analysis=JSON.parse(fs.readFileSync(path.join(root,'v05/brightness-analysis.json'),'utf8'));
 assert.equal(Object.keys(analysis.conflicts).length,0);
 assert.deepEqual(Object.keys(newTable).sort(),Object.keys(analysis.candidates).sort());
 for(const [pair,entry] of Object.entries(analysis.candidates)){assert.equal(newTable[pair],entry.value);assert.ok(new Set(entry.samples).size>=2);assert.ok(entry.samples.every(s=>!s.startsWith('v05/')));}
 for(const [pair,value] of Object.entries(oldTable))assert.equal(newTable[pair],value,'previous supported value preserved '+pair);
 assert.equal(totals.development.samples,36);assert.equal(totals.holdout.samples,6);assert.equal(Object.keys(newTable).length,552);assert.equal(totals.holdout.mappedConflicts,0);assert.ok(totals.holdout.brightnessAfter>totals.holdout.brightnessBefore);for(const s of samples){assert.equal(s.scores.positions,70,s.key);assert.equal(s.scores.extra,0,s.key);for(const [k,n] of [['palaces',12],['stems',12],['decadals',12],['major',14],['transforms',48]])assert.equal(s.scores[k],n,s.key+' '+k);}console.log('PASS42 charts structural regression, evidence-backed table and6 held-out brightness improvements');}
