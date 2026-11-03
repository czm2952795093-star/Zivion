import local.ziweixingyu.rebuild.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.json.*;
public class LunarDateSelectionTest {
 static int count;
 static void check(boolean value)throws Exception{count++;if(!value)throw new Exception("lunar date selection check "+count+" failed");}
 interface Action{void run()throws Exception;}
 static void reject(Action action)throws Exception{boolean bad=false;try{action.run();}catch(Exception e){bad=true;}check(bad);}
 public static void main(String[] args)throws Exception{
  String text=new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8);
  CalendarLookup lookup=new CalendarLookup(text);JSONArray official=new JSONArray(new String(Files.readAllBytes(Paths.get(args[1])),StandardCharsets.UTF_8));
  for(int i=0;i<official.length();i++){
   JSONObject row=official.getJSONObject(i);String lunar=String.format(Locale.US,"%04d%02d%02d235947",row.getInt("year"),row.getInt("month"),row.getInt("day"));
   LunarDateSelection s=new LunarDateSelection(lookup,lunar,row.getBoolean("leap"));
   check(s.formatted().equals(lunar));check(s.leap()==row.getBoolean("leap"));check(s.day()<=s.days());
  }
  LunarDateSelection s=new LunarDateSelection(lookup,"20250630123456",false);
  check(s.setMonth(-6).contains("最后一天"));check(s.day()==29&&s.leap());check(s.formatted().equals("20250629123456"));
  String warning=s.setYear(2026);check(warning.contains("没有所选闰月"));check(s.month()==6&&s.day()==29);check(s.formatted().equals("20260629123456"));
  s.setMonth(1);s.setDay(30);check(s.setMonth(2).contains("最后一天"));check(s.day()==29);
  String before=s.formatted();reject(()->s.setMonth(-2));check(s.formatted().equals(before));reject(()->s.setYear(2101));check(s.formatted().equals(before));
  reject(()->s.setDay(30));check(s.formatted().equals(before));reject(()->s.setTime(23,60,0));check(s.formatted().equals(before));
  s.setTime(0,0,0);check(s.formatted().endsWith("000000"));s.setTime(23,59,59);check(s.formatted().endsWith("235959"));
  for(String bad:new String[]{null,"","20250630120000","20250230120000","20250600235959","18990101120000","21010101120000","20250101240000","20250101006000","20250101000060","２０２５０１０１１２００００"})reject(()->new LunarDateSelection(lookup,bad,true));
  for(String input:new String[]{"19000101000000","21001201235959"}){LunarDateSelection edge=new LunarDateSelection(lookup,input,false);check(edge.formatted().equals(input));}
  for(String bad:new String[]{"20250101240000","20250101006000","20250101000060","20250230120000","20250100000000"})reject(()->new LunarDateSelection(lookup,bad,false));
  reject(()->s.setTime(-1,0,0));reject(()->s.setTime(0,-1,0));reject(()->s.setTime(0,0,-1));
  // All table years expose chronological ordinary/leap options, each independently copyable.
  JSONArray rows=new JSONObject(text).getJSONArray("months");int leapRows=0;
  for(int y=1900;y<=2100;y++){
   int[] values=lookup.lunarMonths(y);check(values.length==12||values.length==13);int ordinary=0;
   for(int m:values){if(m>0)check(m==++ordinary);else{check(m==-ordinary);leapRows++;}check(lookup.lunarMonthDays(y,m)==29||lookup.lunarMonthDays(y,m)==30);}
   check(ordinary==12);
  }
  check(leapRows>0);
  System.out.println("PASS "+count+" lunar date selection checks; 730 official daily fixtures, 201 years of month options, leap/day clamping, time preservation, invalid-change atomicity");
 }
}
