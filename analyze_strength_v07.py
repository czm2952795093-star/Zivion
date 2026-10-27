from pathlib import Path
import re,json,collections
root=Path(__file__).resolve().parent;out=root/'rea-evidence/v07';out.mkdir(exist_ok=True)
rows=[];samples=[]
for folder in ['improvements','v03','v04','v05','v06','v07-targeted']:
 for p in sorted((root/'rea-evidence'/folder/'samples').glob('*-input.json')):
  key=p.name.replace('-input.json','');sample=folder+'/'+key;samples.append(sample);raw=p.with_name(key+'.txt').read_text('utf8')
  for branch,block in re.findall(r'\n([子丑寅卯辰巳午未申酉戌亥])宫\n(.*?)(?=\n[子丑寅卯辰巳午未申酉戌亥]宫\n|\Z)',raw,re.S):
   stem=re.search(r'宫干：([^\n]+)',block)[1]
   for target,palace,star,hua,value in re.findall(r'使在(.)的(.*?)里的(.*?)化([禄权科忌])(\d+)%',block):
    rows.append(dict(sample=sample,source=branch,stem=stem,target=target,palace=palace,star=star,hua=hua,value=int(value)))
pairs=collections.defaultdict(list)
for r in rows:pairs[r['star']+'|'+r['hua']+'|'+r['target']].append(r)
conflicts={k:v for k,v in pairs.items() if len({r['value'] for r in v})>1}
candidates={k:v for k,v in pairs.items() if k not in conflicts and len({r['sample'] for r in v})>=2}
(out/'strength-analysis.json').write_text(json.dumps({'source':'Prior original charts plus specifically targeted development charts; excludes v07 independent holdouts','samples':samples,'rows':rows,'candidates':candidates,'conflicts':conflicts,'counts':{k:len({r['sample'] for r in v}) for k,v in pairs.items()}},ensure_ascii=False,indent=2),'utf8')
print(json.dumps({'samples':len(samples),'rows':len(rows),'keys':len(pairs),'supported':len(candidates),'conflicts':len(conflicts)}))
