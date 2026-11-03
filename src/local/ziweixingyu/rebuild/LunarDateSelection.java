package local.ziweixingyu.rebuild;
import java.util.Locale;

/** Editable lunar birth date; changing a parent field keeps the date valid. */
public final class LunarDateSelection {
 private final CalendarLookup lookup;
 private int year,month,day,hour,minute,second;
 public LunarDateSelection(CalendarLookup lookup,String input,boolean leap)throws Exception{
  this.lookup=lookup;
  if(input==null||!input.matches("[0-9]{14}"))throw new Exception("阴历时间须为14位年月日时分秒");
  year=Integer.parseInt(input.substring(0,4));month=Integer.parseInt(input.substring(4,6))*(leap?-1:1);day=Integer.parseInt(input.substring(6,8));
  hour=Integer.parseInt(input.substring(8,10));minute=Integer.parseInt(input.substring(10,12));second=Integer.parseInt(input.substring(12,14));
  lookup.lunarMonths(year);int days=lookup.lunarMonthDays(year,month);
  if(day<1||day>days||hour>23||minute>59||second>59)throw new Exception("阴历日期或时间不存在");
 }
 public int year(){return year;}public int month(){return month;}public int day(){return day;}
 public int hour(){return hour;}public int minute(){return minute;}public int second(){return second;}
 public int[] months()throws Exception{return lookup.lunarMonths(year);}
 public int days()throws Exception{return lookup.lunarMonthDays(year,month);}
 public String setYear(int value)throws Exception{
  int[] choices=lookup.lunarMonths(value);int next=month;boolean found=false;for(int choice:choices)if(choice==next)found=true;
  String notice="";if(!found){next=Math.abs(month);notice="该年没有所选闰月，已改为同名普通月。";}
  int limit=lookup.lunarMonthDays(value,next);if(day>limit){notice+="日期已调整为本月最后一天。";day=limit;}
  year=value;month=next;return notice;
 }
 public String setMonth(int value)throws Exception{
  int limit=lookup.lunarMonthDays(year,value);String notice="";if(day>limit){day=limit;notice="日期已调整为本月最后一天。";}month=value;return notice;
 }
 public void setDay(int value)throws Exception{if(value<1||value>days())throw new Exception("阴历日无效");day=value;}
 public void setTime(int h,int m,int s)throws Exception{if(h<0||h>23||m<0||m>59||s<0||s>59)throw new Exception("时分秒无效");hour=h;minute=m;second=s;}
 public boolean leap(){return month<0;}
 public String formatted(){return String.format(Locale.US,"%04d%02d%02d%02d%02d%02d",year,Math.abs(month),day,hour,minute,second);}
}
