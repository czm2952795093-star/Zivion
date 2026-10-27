const fs=require('fs'),{calculate}=require('../calculation-engine/engine');
fs.writeFileSync(__dirname+'/flow-v09.json',JSON.stringify({female1968:calculate('1|1968|4|23|4|30|0|120|-8|2|0'),male2001:calculate('1|2001|11|4|14|30|0|120|-8|1|0')},null,2));
