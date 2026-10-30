package local.ziweixingyu.rebuild;
import org.json.*;
import java.text.*;
import java.util.*;

/** UTC+8 civil calendar. No longitude correction or Zi-hour day rollover. */
public final class CalendarLookup {
 private final long[] starts;private final int[][] months;
 private static long parse(String value)throws Exception{
  if(value==null||!value.matches("[0-9]{14}"))throw new Exception("请输入14位公历年月日时分秒");
  int year=Integer.parseInt(value.substring(0,4));if(year<1900||year>2101)throw new Exception("日期超出历表范围");
  SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss",Locale.US);f.setTimeZone(TimeZone.getTimeZone("GMT+8"));f.setLenient(false);
  try{return f.parse(value).getTime();}catch(ParseException e){throw new Exception("公历日期或时间不存在");}
 }
 public CalendarLookup(String json)throws Exception{
  JSONObject root=new JSONObject(json);if(root.getInt("version")!=1)throw new Exception("历表版本不支持");JSONArray rows=root.getJSONArray("months");starts=new long[rows.length()];months=new int[rows.length()][3];
  if(rows.length()==0)throw new Exception("历表为空");
  for(int i=0;i<rows.length();i++){JSONArray r=rows.getJSONArray(i);starts[i]=parse(r.getString(0)+"000000");months[i]=new int[]{r.getInt(1),r.getInt(2),r.getInt(3)};
   if(Math.abs(months[i][1])<1||Math.abs(months[i][1])>12||months[i][2]<29||months[i][2]>30)throw new Exception("历表月份无效");
   if(i>0&&starts[i]!=starts[i-1]+months[i-1][2]*86400000L)throw new Exception("历表日期不连续");
  }
 }
 public JSONObject convert(String input)throws Exception{
  long value=parse(input);int year=Integer.parseInt(input.substring(0,4));if(year<1901||year>2100)throw new Exception("公历换算支持1901至2100年");
  int at=Arrays.binarySearch(starts,value);if(at<0)at=-at-2;
  if(at<0)throw new Exception("日期不在历表内");int day=(int)((value-starts[at])/86400000L)+1;if(day>months[at][2])throw new Exception("日期不在历表内");
  int hour=Integer.parseInt(input.substring(8,10)),branch=((hour+1)/2)%12;
  return new JSONObject().put("year",months[at][0]).put("month",Math.abs(months[at][1])).put("leap",months[at][1]<0).put("day",day).put("daysInMonth",months[at][2]).put("hour",branch).put("lateZi",hour==23).put("civil",input);
 }
 public static void requireOverlay(JSONObject result)throws Exception{
  if(result.getBoolean("leap"))throw new Exception("闰月叠宫规则尚未确认，本次只显示换算结果");
  if(result.getBoolean("lateZi"))throw new Exception("23点子时换日规则尚未确认，本次只显示换算结果");
 }
}
