// Observed original display only; unknown values stay null rather than becoming0%.
const values=require('./observed-strength.json').values;
function strengthFor(star,transformation,branch){
 const key=star+'|'+transformation+'|'+branch;
 return Object.prototype.hasOwnProperty.call(values,key)?values[key]:null;
}
module.exports={strengthFor};
