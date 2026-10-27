/* Partial compatibility rules inferred from unchanged original APK chart exports.
 * Development evidence: the 12 v0.2 samples plus original-1990-male.txt.
 * The ten independently selected v0.3 dates are validation, not lookup keys.
 * Positions depend on lunar birth-year stem/branch, never on a stored birth date.
 */
const BRANCH='子丑寅卯辰巳午未申酉戌亥',STEM='甲乙丙丁戊己庚辛壬癸';
function applyAuxiliaryRules(palaces,yearStem,yearBranch,brightnessFor){
 const stem=STEM.indexOf(yearStem),branch=BRANCH.indexOf(yearBranch);
 if(stem<0||branch<0)throw Error('生年干支无效');
 function remove(name){let type='adjective';for(const p of Object.values(palaces)){if(name in p['星列表'])type=p['星列表'][name];delete p['星列表'][name];delete p['星庙旺'][name];delete p['生年四化'][name];}return type;}
 function place(name,index,type='adjective'){const b=BRANCH[(index+12)%12],p=palaces[b];p['星列表'][name]=type;p['星庙旺'][name]={'庙旺':brightnessFor(name,b)};}
 // Original distinguishes the principal/secondary empty stars by year parity.
 remove('截路');remove('空亡');
 const emptyBase=8-2*(stem%5);
 place('截空',emptyBase+stem%2);place('副截',emptyBase+1-stem%2);
 const voidBase=(branch-stem+22)%12;
 place('副旬',voidBase+1-stem%2);
 place('龙德',branch+7);place('大耗',branch+(branch%2?5:7));
 place('劫煞',[5,2,11,8][branch%4]);
 // 丙、辛 coincide with the library's mapping; other entries were observed
 // directly, including 己 in the earlier 1990-01-01 original-app sample.
 const kui='丑子亥酉寅申未午巳卯',yue='未申酉亥午子丑寅卯巳';
 for(const [name,target] of [['天魁',kui[stem]],['天钺',yue[stem]]]){const type=remove(name);place(name,BRANCH.indexOf(target),type);}
 for(const p of Object.values(palaces)){
  const priority=t=>t==='major'?0:['soft','tough'].includes(t)?1:2;
  const ordered={};for(const [name,type] of Object.entries(p['星列表']).sort((a,b)=>priority(a[1])-priority(b[1])))ordered[name]=type;p['星列表']=ordered;
 }
}
module.exports={applyAuxiliaryRules};
