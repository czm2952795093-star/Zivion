/* Original-app observations separate the birth-year rollover at 23:00
 * on lunar New Year's Eve from the lunar month/day rollover at midnight.
 * Scope a synchronous adapter around natal construction only, restoring the
 * pinned CommonJS function even on error. No vendor files or app binaries edited.
 */
const ganzhi=require('lunar-lite/lib/ganzhi');
function withBirthYear(pair,calculate){
 const original=ganzhi.getHeavenlyStemAndEarthlyBranchBySolarDate;
 ganzhi.getHeavenlyStemAndEarthlyBranchBySolarDate=function(...args){const result=original(...args);return Object.assign({},result,{yearly:pair.slice()});};
 try{return calculate();}finally{ganzhi.getHeavenlyStemAndEarthlyBranchBySolarDate=original;}
}
module.exports={withBirthYear};
