package local.ziweixingyu.rebuild;
import org.json.*;

/** Limit/year star distributions; separate from palace overlays and transformations. */
public final class FlowStarState {
 private static final String[] STEM_ROWS={
  "|陀魁|存|羊||昌||钺||曲||", "魁||陀|存|羊||昌||钺曲|||", "||||陀|存|羊曲||昌|钺||魁",
  "|||||陀|存曲|羊昌||魁||钺", "||魁||陀|存|羊曲钺||昌|||", "钺|||||陀曲|存|羊|魁|昌||",
  "|钺||曲||||陀魁|存|羊||昌", "昌||钺曲||||魁||陀|存|羊|", "羊曲||昌|钺||魁|||||陀|存",
  "存|羊||魁||钺昌||||||陀魁曲"
 };
 private static final int[] HORSE={2,11,8,5},FIRE={2,3,1,9},BELL={10,10,3,10},SALT={9,6,3,0};
 private static final String SYMBOLS="存羊陀魁钺昌曲马火铃鸾喜咸耗";
 private static final String[] NAMES={"禄存","擎羊","陀罗","天魁","天钺","文昌","文曲","天马","火星","铃星","红鸾","天喜","咸池","耗"};
 private static int index(String choices,String value)throws JSONException{if(value==null||value.length()!=1||choices.indexOf(value)<0)throw new JSONException("流曜干支或出生时支无效");return choices.indexOf(value);}
 public static JSONObject describe(String stem,String branch,String birthHour)throws JSONException{
  int s=index(FlowState.STEM,stem),b=index(FlowState.BRANCH,branch),h=index(FlowState.BRANCH,birthHour);String[] cells=STEM_ROWS[s].split("\\|",-1);
  if(cells.length!=12)throw new JSONException("流曜配置无效");
  cells[HORSE[b%4]]+="马";cells[(FIRE[b%4]+h)%12]+="火";cells[(BELL[b%4]+h)%12]+="铃";
  cells[Math.floorMod(3-b,12)]+="鸾";cells[Math.floorMod(9-b,12)]+="喜";cells[SALT[b%4]]+="咸";cells[(b+(s%2==0?7:5))%12]+="耗";
  JSONObject palaces=new JSONObject();for(int i=0;i<12;i++)palaces.put(FlowState.BRANCH.substring(i,i+1),cells[i]);
  return new JSONObject().put("stem",stem).put("branch",branch).put("palaces",palaces);
 }
 public static JSONObject forChart(JSONObject chart,int mode,int age)throws JSONException{
  if(mode<0||mode>3||age<1||age>120)throw new JSONException("流曜查询范围无效");
  JSONObject result=new JSONObject().put("mode",mode);if(mode==0)return result;
  JSONObject info=chart.getJSONObject("其他信息");String hour=info.getString("时支");JSONObject limit=FlowState.describe(chart,false,age);
  if(!limit.getString("anchor").isEmpty())result.put("limit",describe(limit.getString("stem"),limit.getString("anchor"),hour).put("title",limit.getString("title")));
  if(mode>=2){JSONObject annual=FlowState.describe(chart,true,age);String branch=FlowState.BRANCH.substring(Math.floorMod(annual.getInt("year")-4,12),Math.floorMod(annual.getInt("year")-4,12)+1);
   result.put("annual",describe(annual.getString("yearStem"),branch,hour).put("title",annual.getString("title")));}
  return result;
 }
 public static String names(String symbols){StringBuilder text=new StringBuilder();for(int i=0;i<symbols.length();i++){int at=SYMBOLS.indexOf(symbols.charAt(i));if(at<0)continue;if(text.length()>0)text.append("、");text.append(NAMES[at]);}return text.length()==0?"—":text.toString();}
 public static String palaceDetail(JSONObject state,String branch){
  if(state==null||branch==null||branch.length()!=1||FlowState.BRANCH.indexOf(branch)<0)return "";
  JSONObject limit=state.optJSONObject("limit");
  JSONObject annual=state.optJSONObject("annual");
  StringBuilder detail=new StringBuilder();
  if(limit!=null)detail.append("\n限曜：").append(names(limit.optJSONObject("palaces").optString(branch)));
  if(annual!=null)detail.append("\n年曜：").append(names(annual.optJSONObject("palaces").optString(branch)));
  return detail.toString();
 }
 public static String summary(JSONObject state)throws JSONException{
  if(state.optInt("mode")==0)return "本命模式未启用限年流曜。";
  JSONObject limit=state.optJSONObject("limit"),annual=state.optJSONObject("annual");StringBuilder out=new StringBuilder("限年流曜分布\n");
  out.append(limit==null?"大限：尚未进入首限。":limit.getString("title")).append('\n');if(annual!=null)out.append(annual.getString("title")).append('\n');
  if(limit==null&&annual==null)return out.toString().trim();
  out.append("仅显示大限／流年流曜，不改变四化来源。\n");
  for(int i=0;i<12;i++){String b=FlowState.BRANCH.substring(i,i+1);out.append('\n').append(b).append("宫");
   if(limit!=null)out.append("\n限：").append(names(limit.getJSONObject("palaces").getString(b)));
   if(annual!=null)out.append("\n年：").append(names(annual.getJSONObject("palaces").getString(b)));out.append('\n');}
  return out.toString().trim();
 }
}
