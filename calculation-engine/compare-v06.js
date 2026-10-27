const fs=require('fs'),path=require('path'),assert=require('node:assert/strict'),crypto=require('crypto');
const {calculate}=require('./engine'),{strengthFor}=require('./transformation-strength');
const root=path.resolve(__dirname,'../rea-evidence'),table=require('./observed-strength.json').values,samples=[];
for(const folder of ['improvements','v03','v04','v05','v06']){const dir=path.join(root,folder,'samples');
 for(const file of fs.readdirSync(dir).filter(f=>f.endsWith('-input.json')).sort()){
  const meta=JSON.parse(fs.readFileSync(path.join(dir,file),'utf8')),key=file.replace('-input.json',''),d=meta.input;
  const input=`1|${+d.slice(0,4)}|${+d.slice(4,6)}|${+d.slice(6,8)}|${+d.slice(8,10)}|${+d.slice(10,12)}|${+d.slice(12,14)}|120|-8|${meta.sex}|0|0`;
  const actual=calculate(input)['十二宫信息'],raw=fs.readFileSync(path.join(dir,key+'.txt'),'utf8').replace(/\r/g,'');
  const scores={stars:0,positions:0,extra:0,brightness:0,palaces:0,stems:0,decadals:0,major:0,transforms:0,strengthTotal:0,strengthKnown:0,strengthMatch:0,strengthUnknown:0},differences=[];
  for(const b of '子丑寅卯辰巳午未申酉戌亥'){
   const suffix=raw.split('\n'+b+'宫\n')[1],next=suffix.search(/\n[子丑寅卯辰巳午未申酉戌亥]宫\n/),block=next<0?suffix:suffix.slice(0,next),a=actual[b],alias=n=>(n==='仆役'?'交友':n)+'宫';
   const field=name=>block.match(new RegExp(name+'：([^\\n]+)'))?.[1]||'';
   if(alias(a['宫'])===field('宫名'))scores.palaces++;if(a['宫干']===field('宫干'))scores.stems++;if(a['大限']===field('大限'))scores.decadals++;
   const stars={};for(const line of block.split('宫内星：\n')[1].split('\n'+b+'宫产生的四化')[0].trim().split('\n')){const [star,v='']=line.split(':');stars[star]=v;}
   for(const [star,v] of Object.entries(stars)){scores.stars++;if(star in a['星列表']){scores.positions++;if(a['星列表'][star]==='major')scores.major++;if(a['星庙旺'][star]['庙旺']===v)scores.brightness++;}}
   scores.extra+=Object.keys(a['星列表']).filter(s=>!(s in stars)).length;
   for(const match of block.matchAll(/使在(.)的(.*?)里的(.*?)化([禄权科忌])(\d+)%/g)){
    const [,target,palace,star,hua,value]=match,t=a['四化'][star];scores.strengthTotal++;
    assert.equal(!!t&&Object.hasOwn(t,'力度'),Object.hasOwn(table,star+'|'+hua+'|'+target),key+' strength availability '+star);
    if(t&&t['支']===target&&alias(t['宫'])===palace&&t['化']===hua)scores.transforms++;
    if(t&&Object.hasOwn(t,'力度')){scores.strengthKnown++;if(t['力度']===+value)scores.strengthMatch++;else differences.push({source:b,star,hua,target,expected:+value,actual:t['力度']});}
    else scores.strengthUnknown++;
   }
  }
  samples.push({key,role:folder==='v06'?'holdout':'development',scores,differences});
 }
}
const totals={};for(const role of ['development','holdout']){const subset=samples.filter(s=>s.role===role),sum={samples:subset.length};for(const s of subset)for(const [k,v] of Object.entries(s.scores))sum[k]=(sum[k]||0)+v;totals[role]=sum;}
fs.writeFileSync(path.join(root,'v06/comparison.json'),JSON.stringify({source:'42 prior original charts reused for development;359-entry table frozen before6 new holdouts. Unknown strengths are explicitly omitted, never fabricated.',totals,samples,limits:'Observed display compatibility only. Gregorian,120E,UTC+8,fixLeap off; not original algorithm recovery or universal parity.'},null,2));console.log(JSON.stringify(totals,null,2));
if(process.argv.includes('--verify')){
 const analysis=JSON.parse(fs.readFileSync(path.join(root,'v06/strength-analysis.json'),'utf8')),plan=JSON.parse(fs.readFileSync(path.join(root,'v06/holdout-plan.json'),'utf8'));
 assert.equal(crypto.createHash('sha256').update(fs.readFileSync(path.join(__dirname,'observed-strength.json'))).digest('hex'),plan.strengthTableSha256);
 assert.equal(Object.keys(table).length,359);assert.equal(Object.keys(analysis.conflicts).length,0);assert.deepEqual(Object.keys(table).sort(),Object.keys(analysis.candidates).sort());
 for(const [key,rows] of Object.entries(analysis.candidates)){assert.ok(new Set(rows.map(r=>r.sample)).size>=2);assert.ok(rows.every(r=>r.value===table[key]&&!r.sample.startsWith('v06/')));}
 assert.equal(strengthFor('未知','禄','子'),null);assert.equal(totals.development.samples,42);assert.equal(totals.holdout.samples,6);assert.ok(totals.holdout.strengthKnown>0);assert.equal(totals.holdout.strengthKnown,totals.holdout.strengthMatch);
 for(const s of samples){for(const [key,n] of [['positions',70],['extra',0],['palaces',12],['stems',12],['decadals',12],['major',14],['transforms',48],['strengthTotal',48]])assert.equal(s.scores[key],n,s.key+' '+key);assert.equal(s.scores.strengthKnown,s.scores.strengthMatch,s.key);}
 console.log('PASS48 charts structural regression; observed strengths match; table frozen before6 holdouts; unknown is not zero');
}
