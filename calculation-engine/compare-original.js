const fs=require('node:fs');const {calculate}=require('./engine');
const raw=fs.readFileSync('../rea-evidence/runtime/original-1990-male.txt','utf8').replace(/\r/g,'');
const result=calculate('1|1990|1|1|12|0|0|120|-8|1|0');
const palaces=result['十二宫信息'];const branches='子丑寅卯辰巳午未申酉戌亥';
const match={palaceNames:0,stems:0,decadals:0,majorPositions:0,transformations:0};
const differences=[];let brightnessCompared=0,brightnessDifferent=0;
for(const branch of branches){
 const start=raw.indexOf('\n'+branch+'宫\n');if(start<0)throw Error('Missing original palace '+branch);
 const suffix=raw.slice(start+1);const next=suffix.slice(4).search(/\n[子丑寅卯辰巳午未申酉戌亥]宫\n/);const block=next>=0?suffix.slice(0,next+4):suffix;
 const field=name=>block.match(new RegExp(name+'：([^\\n]+)'))?.[1]||'';const p=palaces[branch];
 const name=p['宫']==='仆役'?'交友':p['宫'];if(field('宫名')===name+'宫')match.palaceNames++;
 if(field('宫干')===p['宫干'])match.stems++;if(field('大限')===p['大限'])match.decadals++;
 const starsText=block.split('宫内星：\n')[1].split('\n'+branch+'宫产生的四化')[0];const originalStars={};for(const line of starsText.trim().split('\n')){const [star,b='']=line.split(':');originalStars[star]=b;}
 for(const [star,type] of Object.entries(p['星列表'])){if(type==='major'&&star in originalStars)match.majorPositions++;if(star in originalStars){brightnessCompared++;if(originalStars[star]!==p['星庙旺'][star]['庙旺']){brightnessDifferent++;differences.push({branch,star,originalBrightness:originalStars[star],rebuiltBrightness:p['星庙旺'][star]['庙旺']});}}}
 for(const [star,t] of Object.entries(p['四化'])){const targetName=t['宫']==='仆役'?'交友':t['宫'];if(block.includes('使在'+t['支']+'的'+targetName+'宫里的'+star+'化'+t['化']))match.transformations++;}
 const missing=Object.keys(originalStars).filter(s=>!(s in p['星列表'])),extra=Object.keys(p['星列表']).filter(s=>!(s in originalStars));if(missing.length||extra.length)differences.push({branch,missing,extra});
}
const output={sample:'1990-01-01 12:00:00 UTC+8, longitude 120E, male',provenance:'Original app public copy-chart action, local emulator; supplemental ADB clipboard extraction; original screenshot captured by REA',matches:match,expected:{palaceNames:12,stems:12,decadals:12,majorPositions:14,transformations:48},brightnessCompared,brightnessDifferent,solarTime:{original:'11:56:38',rebuilt:result['其他信息']['真太阳时'],differenceSeconds:1},differences,limitations:'One sample only. Match does not prove all dates, edge cases, brightness, auxiliary stars or proprietary strength percentages are equivalent.'};
fs.writeFileSync('../rea-evidence/runtime/comparison-1990-male.json',JSON.stringify(output,null,2));console.log(JSON.stringify({...output,differences:undefined}));
