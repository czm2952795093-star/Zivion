// Calendar month boundaries only. Does not modify the natal engine.
const fs=require('fs');
const {LunarYear,Solar}=require('lunar-typescript');
const months=[];
for(let year=1900;year<=2100;year++)for(const m of LunarYear.fromYear(year).getMonthsInYear()){
 const s=Solar.fromJulianDay(m.getFirstJulianDay());
 months.push([s.toYmd().replace(/-/g,''),year,m.getMonth(),m.getDayCount()]);
}
const version=JSON.parse(fs.readFileSync(require('path').join(require.resolve('lunar-typescript'),'../../package.json'),'utf8')).version;
fs.writeFileSync(process.argv[2],JSON.stringify({version:1,source:'lunar-typescript '+version,months}));
