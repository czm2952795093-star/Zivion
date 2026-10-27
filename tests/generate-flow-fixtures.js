const fs=require('fs');
const {calculate}=require('../calculation-engine/engine');
const cases={};
for(const sex of [1,2])cases[sex===1?'male':'female']=calculate(`1|1990|1|1|12|0|0|120|-8|${sex}|0`);
fs.writeFileSync(__dirname+'/flow-fixtures.json',JSON.stringify(cases,null,2));
