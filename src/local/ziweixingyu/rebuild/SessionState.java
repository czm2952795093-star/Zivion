package local.ziweixingyu.rebuild;
import org.json.*;

/** Versioned local resume parameters. Calculated chart data is regenerated, never trusted from disk. */
public final class SessionState {
 private static int number(JSONObject o,String key,int min,int max)throws JSONException{
  Object value=o.opt(key);if(!(value instanceof Integer)&&!(value instanceof Long))throw new JSONException("恢复参数无效："+key);
  long n=((Number)value).longValue();if(n<min||n>max)throw new JSONException("恢复参数超出范围："+key);return (int)n;
 }
 private static String string(JSONObject o,String key,int max)throws JSONException{
  Object value=o.opt(key);if(!(value instanceof String)||((String)value).length()>max)throw new JSONException("恢复文字无效："+key);return (String)value;
 }
 private static boolean flag(JSONObject o,String key)throws JSONException{Object value=o.opt(key);if(!(value instanceof Boolean))throw new JSONException("恢复开关无效："+key);return (Boolean)value;}
 private static String branch(JSONObject o,String key)throws JSONException{String value=string(o,key,1);if(!value.isEmpty()&&FlowState.BRANCH.indexOf(value)<0)throw new JSONException("恢复宫位无效："+key);return value;}
 private static void request(String input)throws JSONException{
  if(!input.startsWith("柝袛袛祈祈拆#"))throw new JSONException("恢复命盘格式无效");
  String[] p=input.substring("柝袛袛祈祈拆#".length()).split("\\|",-1);
  if(p.length!=11&&p.length!=12)throw new JSONException("恢复命盘字段缺失");
  int[] min={1,1900,1,1,0,0,0},max={4,2100,12,31,23,59,59};
  try{for(int i=0;i<7;i++){if(!p[i].matches("[0-9]{1,4}"))throw new NumberFormatException();int n=Integer.parseInt(p[i]);if(n<min[i]||n>max[i])throw new NumberFormatException();}
   double lon=Double.parseDouble(p[7]);if(!Double.isFinite(lon)||Math.abs(lon)>180||!p[8].equals("-8")||!p[9].matches("[12]")||!p[10].equals("0")||(p.length==12&&!p[11].matches("[01]")))throw new NumberFormatException();
  }catch(NumberFormatException e){throw new JSONException("恢复命盘输入无效");}
 }
 public static JSONObject parse(String json)throws JSONException{
  if(json==null||json.length()>8192)throw new JSONException("恢复数据过大或缺失");JSONObject raw=new JSONObject(json);
  number(raw,"version",1,1);String input=string(raw,"request",500),title=string(raw,"title",RecordPolicy.TITLE_LIMIT);request(input);
  JSONObject v=raw.getJSONObject("view"),clean=new JSONObject();
  for(String key:new String[]{"mode","age","month","day","hour"})clean.put(key,number(v,key,key.equals("mode")||key.equals("hour")?0:1,key.equals("mode")?3:key.equals("age")?120:key.equals("month")?12:key.equals("day")?30:11));
  clean.put("source",branch(v,"source")).put("selected",branch(v,"selected")).put("lines",flag(v,"lines")).put("dayHour",flag(v,"dayHour"));
  if(clean.getInt("mode")!=3&&clean.getBoolean("dayHour"))throw new JSONException("日时叠宫需要流月模式");
  if(clean.getInt("mode")==0&&!clean.getString("source").isEmpty())throw new JSONException("本命模式不能使用流宫来源");
  return new JSONObject().put("version",1).put("request",input).put("title",title).put("view",clean);
 }
 public static String encode(String request,String title,JSONObject view)throws JSONException{return parse(new JSONObject().put("version",1).put("request",request).put("title",title).put("view",view).toString()).toString();}
 /** Validate the whole query against a newly computed chart before applying any restored UI state. */
 public static void validateForChart(JSONObject chart,JSONObject v)throws JSONException{
  int mode=v.getInt("mode"),age=v.getInt("age");JSONObject layer=mode==0?null:mode==3?MonthState.describe(chart,age,v.getInt("month")):FlowState.describe(chart,mode==2,age);
  if(layer!=null&&!v.getString("source").isEmpty())FlowState.selectSource(chart,layer,v.getString("source"));
  if(v.getBoolean("dayHour"))DayHourState.describe(layer,v.getInt("day"),FlowState.BRANCH.substring(v.getInt("hour"),v.getInt("hour")+1));
  String selected=v.getString("selected");if(!selected.isEmpty()&&!chart.getJSONObject("十二宫信息").has(selected))throw new JSONException("原命盘中没有所选宫位");
 }
}
