package local.ziweixingyu.rebuild;
import org.json.*;

/** Derived from REA r(Canvas): B2 = annual branch + birth hour - birth lunar month.
 * g(Canvas) places month m at o(B2,m). Month labels advance independently of sex. */
public final class MonthState {
 public static final String[] MONTHS={"正月","二月","三月","四月","五月","六月","七月","八月","九月","十月","冬月","腊月"};
 static int birthMonth(JSONObject info)throws JSONException{
  String name=info.getString("农历月").replace("闰","").replace("月","");
  if(name.equals("十一"))return 11;if(name.equals("十二"))return 12;if(name.equals("一"))return 1;
  for(int i=0;i<12;i++)if(MONTHS[i].equals(name+"月"))return i+1;
  try{int m=Integer.parseInt(name);if(m>=1&&m<=12)return m;}catch(NumberFormatException ignored){}
  throw new JSONException("出生农历月份无效");
 }
 public static JSONObject describe(JSONObject chart,int age,int month)throws JSONException{
  if(month<1||month>12)throw new JSONException("月份须为正月至腊月");
  JSONObject annual=FlowState.describe(chart,true,age),info=chart.getJSONObject("其他信息");
  int hour=FlowState.BRANCH.indexOf(info.getString("时支"));if(hour<0||info.getString("时支").length()!=1)throw new JSONException("出生时辰无效");
  int start=Math.floorMod(FlowState.BRANCH.indexOf(annual.getString("anchor"))+hour-birthMonth(info)+1,12);
  JSONObject map=new JSONObject(),labels=new JSONObject();int yearStem=FlowState.STEM.indexOf(annual.getString("yearStem"));
  for(int m=1;m<=12;m++){String b=FlowState.BRANCH.substring((start+m-1)%12,(start+m-1)%12+1);int stem=(2*(yearStem%5)+m+1)%10;map.put(b,new JSONObject().put("month",m).put("name",MONTHS[m-1]).put("stem",FlowState.STEM.substring(stem,stem+1)));}
  int index=(start+month-1)%12;String anchor=FlowState.BRANCH.substring(index,index+1);
  for(int i=0;i<12;i++)labels.put(FlowState.BRANCH.substring(i,i+1),FlowState.NAMES[Math.floorMod(i-index,12)]);
  JSONObject result=new JSONObject(annual.toString()).put("mode","monthly").put("anchor",anchor).put("palaces",labels).put("month",month).put("monthMap",map).put("monthStem",map.getJSONObject(anchor).getString("stem"));
  result=FlowState.selectSource(chart,result,anchor);
  result.put("title","流月 · "+annual.getInt("year")+"年 "+MONTHS[month-1]+"（月干"+result.getString("monthStem")+"）");
  return result;
 }
 public static int step(int month,int direction)throws JSONException{if(month<1||month>12||(direction!=1&&direction!=-1))throw new JSONException("月份切换无效");return Math.max(1,Math.min(12,month+direction));}
}
