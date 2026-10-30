import local.ziweixingyu.rebuild.*;
import org.json.*;
public class BackupRequestTest {
 static int count;
 static void check(boolean b)throws Exception{if(!b)throw new Exception("roundtrip mismatch");count++;}
 static JSONObject record(String request)throws Exception{return new JSONObject().put("title","经度边界").put("request",request).put("clock","").put("id","fixture");}
 static String request(String lon,int mode,boolean legacy){return "柝袛袛祈祈拆#"+mode+"|1990|1|1|12|0|0|"+lon+"|-8|1|0"+(legacy?"":"|1");}
 public static void main(String[] args)throws Exception{
  for(double lon:new double[]{0,-0.0,0.00001,-0.00001,Double.MIN_VALUE,120,-180,180})for(int mode=1;mode<=4;mode++)for(boolean legacy:new boolean[]{false,true}){
   String input=request(Double.toString(lon),mode,legacy);JSONArray records=new JSONArray().put(record(input));String before=records.toString();
   JSONObject parsed=BackupCodec.parse(BackupCodec.encode(records,records,new JSONArray()));
   check(parsed.getJSONArray("saved").getJSONObject(0).getString("request").equals(input));
   check(parsed.getJSONArray("history").getJSONObject(0).getString("request").equals(input));
   check(BackupCodec.merge(records,parsed.getJSONArray("saved"),"saved").length()==1);check(records.toString().equals(before));
  }
  for(String bad:new String[]{"NaN","Infinity","1e309","181","-181","0x1.0p0","1d"," 120","1e","1|0"}){
   String input=request(bad,1,false);boolean rejected=false;try{BackupCodec.encode(new JSONArray().put(record(input)),new JSONArray(),new JSONArray());}catch(JSONException e){rejected=true;}check(rejected);
   JSONObject raw=new JSONObject().put("format",BackupCodec.FORMAT).put("version",1).put("saved",new JSONArray().put(record(input))).put("history",new JSONArray()).put("notes",new JSONArray());
   rejected=false;try{BackupCodec.parse(raw.toString());}catch(JSONException e){rejected=true;}check(rejected);
  }
  for(String input:new String[]{request("120",1,false).replace("|1990|","|1899|"),request("120",1,false).replace("|12|0|0|","|24|0|0|"),request("120",1,false).replace("|-8|","|0|")}){
   boolean rejected=false;try{ChartRequest.validate(input);}catch(JSONException e){rejected=true;}check(rejected);
  }
  System.out.println("PASS "+count+" backup request checks; scientific longitude, legacy requests, merge idempotence, invalid inputs and source immutability");
 }
}
