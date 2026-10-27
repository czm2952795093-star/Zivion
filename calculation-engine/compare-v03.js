const fs=require('node:fs'),path=require('node:path'),assert=require('node:assert/strict');
const {calculate}=require('./engine');
const root=path.resolve(__dirname,'../rea-evidence');
const dirs=[['development',path.join(root,'improvements/samples')],['validation',path.join(root,'v03/samples')]];
const branches='子丑寅卯辰巳午未申酉戌亥';
function parse(raw){const out={};for(const b of branches){const s=raw.indexOf('\n'+b+'宫\n');if(s<0)throw Error('Missing '+b);const suffix=raw.slice(s+1),n=suffix.slice(4).search(/\n[子丑寅卯辰巳午未申酉戌亥]宫\n/);const block=n>=0?suffix.slice(0,n+4):suffix;
 const stars={};for(const line of block.split('宫内星：\n')[1].split('\n'+b+'宫产生的四化')[0].trim().split('\n')){const [star,brightness='']=line.split(':');stars[star]=brightness;}
 const field=name=>block.match(new RegExp(name+'：([^\\n]+)'))?.[1]||'';out[b]={block,stars,name:field('宫名'),stem:field('宫干'),decadal:field('大限')};}return out;}
const samples=[];
for(const [role,dir] of dirs)for(const file of fs.readdirSync(dir).filter(f=>f.endsWith('-input.json')).sort()){
 const meta=JSON.parse(fs.readFileSync(path.join(dir,file),'utf8')),key=file.replace('-input.json',''),d=meta.input;
 const request=`1|${+d.slice(0,4)}|${+d.slice(4,6)}|${+d.slice(6,8)}|${+d.slice(8,10)}|${+d.slice(10,12)}|${+d.slice(12,14)}|120|-8|${meta.sex}|0|0`;
 const expected=parse(fs.readFileSync(path.join(dir,key+'.txt'),'utf8').replace(/\r/g,''));const result=calculate(request),actual=result['十二宫信息'],before=calculate(request,{auxiliaryRules:false})['十二宫信息'];
 const scores={originalStars:0,oldPositions:0,newPositions:0,oldExtra:0,newExtra:0,brightness:0,palaces:0,stems:0,decadals:0,major:0,transforms:0},differences=[];
 for(const b of branches){const e=expected[b],a=actual[b],old=before[b],alias=n=>(n==='仆役'?'交友':n)+'宫';
  if(alias(a['宫'])===e.name)scores.palaces++;if(a['宫干']===e.stem)scores.stems++;if(a['大限']===e.decadal)scores.decadals++;
  for(const [star,value] of Object.entries(e.stars)){scores.originalStars++;if(star in old['星列表'])scores.oldPositions++;if(star in a['星列表']){scores.newPositions++;if(a['星列表'][star]==='major')scores.major++;if(a['星庙旺'][star]['庙旺']===value)scores.brightness++;}else differences.push({branch:b,missing:star});}
  for(const s of Object.keys(a['星列表']))if(!(s in e.stars)){scores.newExtra++;differences.push({branch:b,extra:s});}
  scores.oldExtra+=Object.keys(old['星列表']).filter(s=>!(s in e.stars)).length;
  for(const [star,t] of Object.entries(a['四化']))if(e.block.includes('使在'+t['支']+'的'+alias(t['宫'])+'里的'+star+'化'+t['化']))scores.transforms++;
 }
 samples.push({key,role,year:result['其他信息']['农历年'],scores,differences});
}
const totals={};for(const role of ['development','validation']){const subset=samples.filter(s=>s.role===role),sum={samples:subset.length};for(const s of subset)for(const [k,v] of Object.entries(s.scores))sum[k]=(sum[k]||0)+v;totals[role]=sum;}
const report={provenance:'Original unmodified APK public-copy text, REA native screen captures. Inferred year-based auxiliary rules; v0.2 dates used for development, ten new dates for validation.',totals,samples,limits:'These checks do not establish all-time parity, brightness equivalence, transformation strength or flow-period features.'};
fs.mkdirSync(path.join(root,'v03'),{recursive:true});fs.writeFileSync(path.join(root,'v03/comparison.json'),JSON.stringify(report,null,2));console.log(JSON.stringify(totals,null,2));
if(process.argv.includes('--verify')){assert.equal(totals.validation.samples,10);for(const s of samples){assert.equal(s.scores.newPositions,s.scores.originalStars,s.key+' missing');assert.equal(s.scores.newExtra,0,s.key+' extra');for(const [k,n] of [['palaces',12],['stems',12],['decadals',12],['major',14],['transforms',48]])assert.equal(s.scores[k],n,s.key+' '+k);}console.log('PASS 22 original charts: positions, membership and structural regression');}
