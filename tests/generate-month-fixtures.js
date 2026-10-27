const fs=require('fs'),{calculate}=require('../calculation-engine/engine');
const inputs={male1990:'1|1990|1|1|12|0|0|120|-8|1|0',female1968:'1|1968|4|23|4|30|0|120|-8|2|0',male2001:'1|2001|11|4|14|30|0|120|-8|1|0',male2000:'1|2000|2|29|23|30|0|120|-8|1|0',female2012:'1|2012|6|12|18|0|0|120|-8|2|0'};
fs.writeFileSync(__dirname+'/month-fixtures.json',JSON.stringify(Object.fromEntries(Object.entries(inputs).map(([k,v])=>[k,calculate(v)])),null,2));
