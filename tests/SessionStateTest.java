import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class SessionStateTest {
 static int checks;static void eq(Object a,Object b,String name){checks++;if(!Objects.equals(a,b))throw new AssertionError(name+": "+a+" != "+b);}
 interface Task{void run()throws Exception;}static void bad(Task t)throws Exception{boolean rejected=false;try{t.run();}catch(JSONException e){rejected=true;}eq(true,rejected,"reject malformed resume state");}
 static String request="柝袛袛祈祈拆#1|1990|1|1|12|0|0|120.0|-8|1|0|0";
 static JSONObject view()throws Exception{return new JSONObject().put("mode",3).put("age",39).put("month",9).put("day",20).put("hour",5).put("dayHour",true).put("lines",false).put("source","卯").put("selected","卯");}
 public static void main(String[] args)throws Exception{
  JSONObject chart=new JSONObject(new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8)).getJSONObject("male1990");String birth=chart.toString();JSONObject v=view();String before=v.toString();
  String encoded=SessionState.encode(request,"原盘\"标题\"\n与草稿分离",v);JSONObject parsed=SessionState.parse(encoded);SessionState.validateForChart(chart,parsed.getJSONObject("view"));
  eq(request,parsed.getString("request"),"exact request");eq("原盘\"标题\"\n与草稿分离",parsed.getString("title"),"title preserved");eq(before,parsed.getJSONObject("view").toString(),"all query fields preserved");eq(before,v.toString(),"encoding does not mutate source");
  parsed.getJSONObject("view").put("month",1);eq(9,v.getInt("month"),"decoded state independent");eq(birth,chart.toString(),"restore validation does not mutate chart");
  for(int mode=0;mode<4;mode++)for(int age:new int[]{1,5,39,120})for(int month:new int[]{1,12}){
   JSONObject state=view().put("mode",mode).put("age",age).put("month",month).put("dayHour",mode==3).put("source","");JSONObject result=SessionState.parse(SessionState.encode(request,"",state));
   SessionState.validateForChart(chart,result.getJSONObject("view"));eq(state.toString(),result.getJSONObject("view").toString(),"boundary view round trip");
  }
  for(String key:new String[]{"mode","age","month","day","hour"}){
   for(Object value:new Object[]{-1,121,1.5,"1",JSONObject.NULL}){JSONObject raw=new JSONObject(encoded);raw.getJSONObject("view").put(key,value);bad(()->SessionState.parse(raw.toString()));}
   JSONObject raw=new JSONObject(encoded);raw.getJSONObject("view").remove(key);bad(()->SessionState.parse(raw.toString()));
  }
  for(String key:new String[]{"dayHour","lines"})for(Object value:new Object[]{0,"true",JSONObject.NULL}){JSONObject raw=new JSONObject(encoded);raw.getJSONObject("view").put(key,value);bad(()->SessionState.parse(raw.toString()));}
  for(String key:new String[]{"source","selected"})for(String value:new String[]{"子丑","?"}){JSONObject raw=new JSONObject(encoded);raw.getJSONObject("view").put(key,value);bad(()->SessionState.parse(raw.toString()));}
  for(Object version:new Object[]{0,2,"1",1.5,JSONObject.NULL})bad(()->SessionState.parse(new JSONObject(encoded).put("version",version).toString()));
  for(String text:new String[]{null,"","{}","[]","not-json",String.join("",Collections.nCopies(8193,"x"))})bad(()->SessionState.parse(text));
  bad(()->SessionState.encode(request,String.join("",Collections.nCopies(501,"x")),view()));
  bad(()->SessionState.encode(request,"",view().put("mode",2)));bad(()->SessionState.encode(request,"",view().put("mode",0).put("dayHour",false)));
  bad(()->SessionState.validateForChart(chart,view().put("mode",1).put("age",1).put("dayHour",false)));
  for(String input:new String[]{"wrong",request+"|1",request.replace("|1990|","|1899|"),request.replace("|12|0|0|","|24|0|0|"),request.replace("120.0","NaN"),request.replace("120.0","181"),request.replace("|-8|","|0|"),request.replace("#1|","#5|"),request.replace("|1|0|0","|9|0|0")})bad(()->SessionState.encode(input,"",view()));
  for(int mode=1;mode<=4;mode++)for(String lon:new String[]{"120","-180.0","1.0E-5"}){String input=request.replace("#1|","#"+mode+"|").replace("120.0",lon);eq(input,SessionState.parse(SessionState.encode(input,"",view())).getString("request"),"valid input mode and longitude retained");}
  eq(birth,chart.toString(),"all validation leaves chart unchanged");System.out.println("PASS "+checks+" session checks; round trips, boundaries, malformed snapshots, chart validation and immutability");
 }
}
