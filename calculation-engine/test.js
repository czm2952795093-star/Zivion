const assert=require('node:assert/strict');
const {calculate,safely}=require('./engine');
const make=(tail='1|1990|1|1|12|0|0|120|-8|1|0')=>calculate(tail);
const a=make(),b=make('3|1989|12|5|12|0|0|120|-8|1|0');
assert.deepEqual(a['十二宫信息'],b['十二宫信息'],'Gregorian and lunar equivalents');
assert.equal(a['其他信息']['命宫'],'未');assert.equal(a['其他信息']['五行局'],'土五局');
assert.equal(a['其他信息']['月干']+a['其他信息']['月支'],'丙子');
assert.equal(a['其他信息']['真太阳时'],'1990-01-01 11:56:37');
let tested=0;
for(const year of [1900,1989,1990,2000,2023,2024,2100])for(const sex of [1,2])for(const hour of [0,1,11,12,22,23]){
 const d=make(`1|${year}|1|1|${hour}|0|0|120|-8|${sex}|0`),p=d['十二宫信息'];assert.equal(Object.keys(p).length,12);
 const majors=[];for(const palace of Object.values(p)){for(const [name,type] of Object.entries(palace['星列表']))if(type==='major')majors.push(name);assert.match(palace['大限'],/^\d+~\d+$/);for(const transform of Object.values(palace['四化']))assert.ok(p[transform['支']]);}
 assert.equal(new Set(majors).size,14);assert.equal(majors.length,14);tested++;
}
assert.notEqual(make()['十二宫信息']['巳']['大限'],make('1|1990|1|1|12|0|0|120|-8|2|0')['十二宫信息']['巳']['大限']);
for(const bad of ['1|2023|2|29|12|0|0|120|-8|1|0','1|2000|13|1|12|0|0|120|-8|1|0','1|2000|1|1|24|0|0|120|-8|1|0','1|2000|1|1|12|0|0|181|-8|1|0'])assert.equal(safely(bad).ok,false,bad);
const leap=make('4|2023|2|10|12|0|0|120|-8|1|0');assert.equal(leap['其他信息']['农历是否闰月'],'1');
const solar=make('2|1990|1|1|11|56|37|120|-8|1|0');assert.equal(solar['其他信息']['真太阳时'],'1990-01-01 11:56:37');
// Original REA input onCreate restores 换月 from the final original field (default 0).
// Original public copy on 2012-06-12, leap fourth month day 23: 命宫申 / 身宫寅.
const leapOriginal=make('1|2012|6|12|18|0|0|120|-8|2|0');
assert.equal(leapOriginal['其他信息']['命宫'],'申');assert.equal(leapOriginal['其他信息']['身宫'],'寅');
assert.equal(make('1|2012|6|12|18|0|0|120|-8|2|0|1')['其他信息']['命宫'],'酉');
console.log(JSON.stringify({status:'passed',boundaryCharts:tested,checks:['14 unique major stars','12 palaces','all transformation targets exist','lunar/solar equivalence','leap month','gender changes decadal direction','invalid inputs rejected','true-solar input retained']}));
