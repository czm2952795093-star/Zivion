from pathlib import Path
import re,json,collections
root=Path(__file__).resolve().parent;out=root/'rea-evidence/v06';out.mkdir(exist_ok=True)
rows=[]
for folder in ['improvements','v03','v04','v05']:
 for p in sorted((root/'rea-evidence'/folder/'samples').glob('*-input.json')):
  key=p.name.replace('-input.json','');raw=p.with_name(key+'.txt').read_text('utf8')
  for branch,block in re.findall(r'\n([子丑寅卯辰巳午未申酉戌亥])宫\n(.*?)(?=\n[子丑寅卯辰巳午未申酉戌亥]宫\n|\Z)',raw,re.S):
   stem=re.search(r'宫干：([^\n]+)',block)[1]
   for target,palace,star,hua,value in re.findall(r'使在(.)的(.*?)里的(.*?)化([禄权科忌])(\d+)%',block):
    rows.append(dict(sample=folder+'/'+key,source=branch,stem=stem,target=target,palace=palace,star=star,hua=hua,value=int(value)))
reports={}
for fields in [('star',),('star','hua'),('star','target'),('star','hua','target'),('source','star','hua','target')]:
 pairs=collections.defaultdict(list)
 for r in rows:pairs['|'.join(r[k] for k in fields)].append(r)
 conflicts={k:v for k,v in pairs.items() if len({r['value'] for r in v})>1}
 repeat={k:v for k,v in pairs.items() if len({r['sample'] for r in v})>=2 and k not in conflicts}
 reports['+'.join(fields)]={'keys':len(pairs),'conflicts':len(conflicts),'repeatedKeys':len(repeat),'repeatedRows':sum(len(v) for v in repeat.values())}
 if fields==('star','hua','target'):
  (out/'strength-analysis.json').write_text(json.dumps({'source':'42 prior original public-copy charts, reused as development data for v06','rows':rows,'candidates':repeat,'conflicts':conflicts},ensure_ascii=False,indent=2),'utf8')
print(json.dumps({'rows':len(rows),'models':reports},ensure_ascii=False,indent=2))
