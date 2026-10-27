const fs=require('fs'),path=require('path');
const root=path.resolve(__dirname,'../rea-evidence');
const pairs={},cases=[];
for(const folder of ['improvements','v03','v04']){
 const dir=path.join(root,folder,'samples');
 for(const file of fs.readdirSync(dir).filter(f=>f.endsWith('-input.json')).sort()){
  const key=file.replace('-input.json',''),raw=fs.readFileSync(path.join(dir,key+'.txt'),'utf8').replace(/\r/g,'');cases.push(folder+'/'+key);
  for(const branch of '子丑寅卯辰巳午未申酉戌亥'){
   const block=raw.split('\n'+branch+'宫\n')[1].split('宫内星：\n')[1].split('\n'+branch+'宫产生的四化')[0].trim();
   for(const line of block.split('\n')){const [star,value='']=line.split(':');const pair=star+'|'+branch;(pairs[pair]??=[]).push({value,sample:folder+'/'+key});}
  }
 }
}
const candidates={},conflicts={},singletons={};
for(const [key,rows] of Object.entries(pairs)){
 const values=[...new Set(rows.map(r=>r.value))];
 if(values.length>1)conflicts[key]=rows;
 else if(rows.length>=2)candidates[key]={value:values[0],samples:rows.map(r=>r.sample)};
 else singletons[key]=rows;
}
const out=path.join(root,'v05');fs.mkdirSync(out,{recursive:true});
fs.writeFileSync(path.join(out,'brightness-analysis.json'),JSON.stringify({source:'36 existing original-app charts become development data for v05; do not describe them as held-out validation for this table.',cases,candidates,conflicts,singletons},null,2));
console.log(JSON.stringify({cases:cases.length,pairs:Object.keys(pairs).length,candidates:Object.keys(candidates).length,conflicts,singletons:Object.keys(singletons).length},null,2));
