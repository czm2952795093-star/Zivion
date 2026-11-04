package local.ziweixingyu.rebuild;
import org.json.JSONObject;

/** Shared birth-date labels for input, rendered charts and copied information. */
public final class BirthDateText {
 public static String input(String value,boolean lunar,boolean leap,boolean seconds){
  if(value==null||!value.matches("[0-9]{14}"))return "时间信息（年月日时分秒）";
  return (lunar?"阴历":"公历")+value.substring(0,4)+"年"+(lunar&&leap?"闰":"")
   +Integer.parseInt(value.substring(4,6))+"月"+Integer.parseInt(value.substring(6,8))+"日"
   +Integer.parseInt(value.substring(8,10))+"时"+Integer.parseInt(value.substring(10,12))+"分"
   +(seconds?Integer.parseInt(value.substring(12,14))+"秒":"");
 }
 public static String lunar(JSONObject info){
  if(info==null)return "";String month=info.optString("农历月");
  boolean leap=month.contains("闰")||"1".equals(info.optString("农历是否闰月"))||info.optBoolean("农历是否闰月",false);
  month=month.replace("闰","").replace("月","");
  return info.optString("农历年")+"年"+(leap?"闰":"")+month+"月"+info.optString("农历日")+info.optString("时支")+"时";
 }
}
