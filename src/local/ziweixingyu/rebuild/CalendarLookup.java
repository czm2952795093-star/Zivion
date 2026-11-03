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
 public int[] lunarMonths(int year)throws Exception{
  ArrayList<Integer> result=new ArrayList<>();for(int[] row:months)if(row[0]==year)result.add(row[1]);
  if(result.isEmpty())throw new Exception("阴历日期选择支持1900至2100年");
  int[] values=new int[result.size()];for(int i=0;i<values.length;i++)values[i]=result.get(i);return values;
 }
 public int lunarMonthDays(int year,int signedMonth)throws Exception{
  for(int[] row:months)if(row[0]==year&&row[1]==signedMonth)return row[2];
  throw new Exception("所选年份不存在该阴历月份");
 }
 /** Selected flow months denote ordinary months, never their leap counterparts. */
 public int regularMonthDays(int year,int month)throws Exception{
  if(month<1||month>12)throw new Exception("农历月份须为1至12月");
  for(int[] row:months)if(row[0]==year&&row[1]==month)return row[2];
  throw new Exception("日时叠宫日期校验支持农历1900至2100年，请调整年份");
 }
 public void requireRegularDay(int year,int month,int day)throws Exception{
  int length=regularMonthDays(year,month);
  if(day<1||day>length)throw new Exception(year+"年农历"+month+"月（非闰月）只有"+length+"天，请输入1至"+length+"日");
 }
 public static void requireOverlay(JSONObject result)throws Exception{
  if(result.getBoolean("leap"))throw new Exception("闰月叠宫规则尚未确认，本次只显示换算结果");
  if(result.getBoolean("lateZi"))throw new Exception("23点子时换日规则尚未确认，本次只显示换算结果");
 }
}
