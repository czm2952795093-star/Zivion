const assert=require('node:assert/strict'),fs=require('node:fs'),path=require('node:path');
const dir=process.argv[2]?path.resolve(process.argv[2]):path.resolve(__dirname,'../rea-evidence/improvements/samples');
const report=JSON.parse(fs.readFileSync(path.join(dir,'comparison.json'),'utf8'));
const values=require('./observed-brightness.json').values;
assert.equal(report.samples.length,12);assert.equal(report.samples.filter(s=>s.role==='holdout').length,3);
for(const [key,value] of Object.entries(values)){
 const provenance=report.candidateBrightness[key];assert.ok(provenance,key+' has repeated calibration evidence');
 assert.ok(new Set(provenance.samples).size>=2);assert.equal(provenance.value,value);
}
let before=0,after=0,total=0;
for(const s of report.samples){
 for(const k of ['palaceNames','stems','decadals','majorPositions','transformations'])assert.equal(s.matches[k],s.expected[k],s.key+' '+k);
 if(s.role==='holdout'){
  assert.ok(s.matches.brightness>=s.baselineBrightness,s.key+' no brightness regression');
  assert.equal(s.differences.filter(d=>Object.hasOwn(values,d.star+'|'+d.branch)).length,0,s.key+' observed pairs agree');
  before+=s.baselineBrightness;after+=s.matches.brightness;total+=s.expected.brightness;
 }
}
console.log(JSON.stringify({status:'passed',originalSamples:12,calibration:9,holdout:3,repeatedPairs:Object.keys(values).length,holdoutBrightness:{before,after,total},limits:'Brightness is partial; original auxiliary-star membership is not reproduced by these checks.'}));
