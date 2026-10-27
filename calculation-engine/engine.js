/* Independent calculation adapter. Original APK native code is not used.
 * Time correction: NOAA solar equation-of-time model (approximate).
 * The output schema follows REA's observed UI contract; it is not a parity claim.
 */
const {astro,util}=require('iztro');
const {Solar,Lunar}=require('lunar-typescript');
const observedBrightness=require('./observed-brightness.json').values;
const BRANCH='子丑寅卯辰巳午未申酉戌亥';
const STEM='甲乙丙丁戊己庚辛壬癸';
function equationMinutes(date){
 const t=((date.getTime()/86400000+2440587.5)-2451545)/36525;
 const rad=Math.PI/180;
 const l=(280.46646+t*(36000.76983+t*.0003032))*rad;
 const m=(357.52911+t*(35999.05029-.0001537*t))*rad;
 const e=.016708634-t*(.000042037+.0000001267*t);
 const eps=(23+(26+(21.448-t*(46.815+t*(.00059-.001813*t)))/60)/60+.00256*Math.cos((125.04-1934.136*t)*rad))*rad;
 const y=Math.tan(eps/2)**2;
 return 4/rad*(y*Math.sin(2*l)-2*e*Math.sin(m)+4*e*y*Math.sin(m)*Math.cos(2*l)-.5*y*y*Math.sin(4*l)-1.25*e*e*Math.sin(2*m));
}
function pad(n){return String(n).padStart(2,'0');}
function stamp(d){return d.getUTCFullYear()+'-'+pad(d.getUTCMonth()+1)+'-'+pad(d.getUTCDate())+' '+pad(d.getUTCHours())+':'+pad(d.getUTCMinutes())+':'+pad(d.getUTCSeconds());}
function calculate(input,options={}){
 const p=input.replace(/^柝袛袛祈祈拆#/,'').split('|');
 const mode=+p[0],year=+p[1],month=+p[2],day=+p[3],hour=+p[4],minute=+p[5],second=+p[6],lon=+p[7],sex=+p[9];
 if(![1,2,3,4].includes(mode)||p.length<11||year<1900||year>2100||month<1||month>12||day<1||day>31||hour<0||hour>23||minute<0||minute>59||second<0||second>59||!Number.isFinite(lon)||Math.abs(lon)>180||![1,2].includes(sex))throw Error('出生信息格式无效');
 let clock=new Date(Date.UTC(year,month-1,day,hour,minute,second));
 if(mode===3||mode===4){const solar=Lunar.fromYmdHms(year,mode===4?-month:month,day,hour,minute,second).getSolar();clock=new Date(Date.UTC(solar.getYear(),solar.getMonth()-1,solar.getDay(),hour,minute,second));}
 else if(clock.getUTCMonth()!==month-1||clock.getUTCDate()!==day)throw Error('日期不存在');
 let effective;
 const correction=d=>4*(lon-120)+equationMinutes(new Date(d.getTime()-8*3600000));
 if(mode===2){effective=clock;for(let i=0;i<3;i++)clock=new Date(effective.getTime()-correction(clock)*60000);}
 else effective=new Date(clock.getTime()+correction(clock)*60000);
 const dateString=effective.getUTCFullYear()+'-'+(effective.getUTCMonth()+1)+'-'+effective.getUTCDate();
 const timeIndex=Math.floor((effective.getUTCHours()+1)/2);
 const a=astro.withOptions({type:'solar',dateStr:dateString,timeIndex,gender:sex===1?'男':'女',fixLeap:p[11]==='1',language:'zh-CN',config:{yearDivide:'normal',dayDivide:'forward',algorithm:'default'}});
 const lunar=Solar.fromYmdHms(effective.getUTCFullYear(),effective.getUTCMonth()+1,effective.getUTCDate(),effective.getUTCHours(),effective.getUTCMinutes(),effective.getUTCSeconds()).getLunar();
 const raw=a.rawDates.lunarDate, pillars=[lunar.getYearInGanZhiExact(),lunar.getMonthInGanZhiExact(),lunar.getDayInGanZhiExact(),lunar.getTimeInGanZhi()];
 const original=a.palaces.find(x=>x.isOriginalPalace);
 const info={'性别':(STEM.indexOf(a.rawDates.chineseDate.yearly[0])%2?'阴':'阳')+(sex===1?'男':'女'),'五行局':a.fiveElementsClass,'命主':a.soul,'身主':a.body,'命宫':a.earthlyBranchOfSoulPalace,'身宫':a.earthlyBranchOfBodyPalace,'经度':lon.toFixed(6),'钟表时间':stamp(clock),'真太阳时':stamp(effective),'农历年':String(raw.lunarYear),'农历月':lunar.getMonthInChinese().replace('闰',''),'农历日':lunar.getDayInChinese(),'农历是否闰月':raw.isLeap?'1':'0','立春年干':pillars[0][0],'立春年支':pillars[0][1],'月干':pillars[1][0],'月支':pillars[1][1],'日干':pillars[2][0],'日支':pillars[2][1],'时干':pillars[3][0],'时支':pillars[3][1],'层1.1':original?original.earthlyBranch:'','算法来源':'iztro 2.6.2 / 独立实现','太阳时说明':'NOAA近似模型，与原版秒值可能不同'};
 const palaces={};
 for(const p of a.palaces){
  const stars=[...p.majorStars,...p.minorStars,...p.adjectiveStars];const list={},brightness={},birth={};
  for(const s of stars){const key=s.name+'|'+p.earthlyBranch;const observed=options.observedBrightness!==false&&Object.prototype.hasOwnProperty.call(observedBrightness,key);list[s.name]=s.type;brightness[s.name]={'庙旺':observed?observedBrightness[key]:(s.brightness||'')};if(s.mutagen)birth[s.name]=s.mutagen;}
  const transformations={};const transformed=util.getMutagensByHeavenlyStem(p.heavenlyStem);
  transformed.forEach((name,i)=>{const target=a.palaces.find(q=>[...q.majorStars,...q.minorStars].some(s=>s.name===name));if(target)transformations[name]={'支':target.earthlyBranch,'宫':target.name,'化':'禄权科忌'[i]};});
  palaces[p.earthlyBranch]={'宫干':p.heavenlyStem,'宫':p.name,'大限':p.decadal.range.join('~'),'小限':p.ages.join(','),'星列表':list,'星庙旺':brightness,'生年四化':birth,'四化':transformations,'生年博士':p.boshi12,'生年将前':p.jiangqian12,'生年岁前':p.suiqian12,'长生':p.changsheng12};
 }
 return {'其他信息':info,'十二宫信息':palaces};
}
function safely(input){try{return {ok:true,data:calculate(input)};}catch(e){return {ok:false,error:String(e.message||e)};}}
module.exports={calculate,safely,equationMinutes};
