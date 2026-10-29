package local.ziweixingyu.rebuild;
import org.json.*;

/** Manual lunar-day/hour overlays from REA zwpview.g(Canvas).
 * This does not convert a civil date, validate a particular month's length,
 * or choose a new transformation source. */
public final class DayHourState {
 public static final String[] DAYS={"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十"};
 public static int parseDay(String value)throws JSONException{
  String text=value==null?"":value.trim();
  if(!text.matches("[0-9]{1,2}"))throw new JSONException("请输入1至30的农历日");
  int day=Integer.parseInt(text);if(day<1||day>30)throw new JSONException("请输入1至30的农历日");return day;
 }
 private static JSONObject labels(int anchor)throws JSONException{
  JSONObject result=new JSONObject();for(int i=0;i<12;i++)result.put(FlowState.BRANCH.substring(i,i+1),FlowState.NAMES[Math.floorMod(i-anchor,12)]);return result;
 }
 public static JSONObject describe(JSONObject monthly,int day,String hour)throws JSONException{
  if(monthly==null||!"monthly".equals(monthly.optString("mode")))throw new JSONException("请先选择流月");
  String branch=monthly.optString("anchor");int monthIndex=FlowState.BRANCH.indexOf(branch);
  if(branch.length()!=1||monthIndex<0)throw new JSONException("流月命宫无效");
  if(day<1||day>30)throw new JSONException("请输入1至30的农历日");
  if(hour==null||hour.length()!=1||FlowState.BRANCH.indexOf(hour)<0)throw new JSONException("请选择子至亥的时辰");
  int dayIndex=(monthIndex+day-1)%12,hourIndex=(dayIndex+FlowState.BRANCH.indexOf(hour))%12;
  return new JSONObject().put("day",day).put("dayName",DAYS[day-1]).put("hour",hour)
   .put("dayAnchor",FlowState.BRANCH.substring(dayIndex,dayIndex+1)).put("hourAnchor",FlowState.BRANCH.substring(hourIndex,hourIndex+1))
   .put("dayPalaces",labels(dayIndex)).put("hourPalaces",labels(hourIndex));
 }
}
