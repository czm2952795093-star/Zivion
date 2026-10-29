import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class DayHourStateTest {
 static int checks=0;
 static void eq(Object expected,Object actual,String label){checks++;if(!Objects.equals(expected,actual))throw new AssertionError(label+": expected "+expected+", got "+actual);}
 interface Checked{void run()throws Exception;}
 static void bad(Checked task)throws Exception{boolean rejected=false;try{task.run();}catch(JSONException e){rejected=true;}eq(true,rejected,"invalid input rejected");}
 public static void main(String[] args)throws Exception{
  JSONObject fixtures=new JSONObject(new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8));
  // Original 2026-10-29 capture, displayed annual year 2027 (cached age 39).
  // Read month/day/hour anchor labels independently from original-annual-current/screen.png.
  JSONObject chart=fixtures.getJSONObject("male1990"),month=MonthState.describe(chart,39,9);
  JSONObject observed=DayHourState.describe(month,20,"巳");
  eq("戌",month.getString("anchor"),"original month anchor");eq("巳",observed.getString("dayAnchor"),"original day anchor");eq("戌",observed.getString("hourAnchor"),"original hour anchor");
  JSONObject secondMonth=MonthState.describe(fixtures.getJSONObject("male2001"),27,9),second=DayHourState.describe(secondMonth,20,"巳");
  eq("寅",secondMonth.getString("anchor"),"second original month anchor");eq("酉",second.getString("dayAnchor"),"second original day anchor");eq("寅",second.getString("hourAnchor"),"second original hour anchor");
  // The twelve anchor-relative labels are established by retained static REA g(Canvas),
  // not counted as twelve independent runtime observations.
  for(String key:fixtures.keySet()){
   JSONObject c=fixtures.getJSONObject(key);String chartBefore=c.toString();
   for(int m=1;m<=12;m++){
    JSONObject f=MonthState.describe(c,39,m);String before=f.toString();
    for(int day=1;day<=30;day++)for(int h=0;h<12;h++){
     JSONObject d=DayHourState.describe(f,day,FlowState.BRANCH.substring(h,h+1));
     for(String layer:new String[]{"day","hour"}){
      JSONObject labels=d.getJSONObject(layer+"Palaces");eq(12,labels.length(),"twelve branches");
      Set<String> names=new HashSet<>();for(String b:labels.keySet())names.add(labels.getString(b));eq(12,names.size(),"each palace once");
      eq("命",labels.getString(d.getString(layer+"Anchor")),"anchor carries life palace");
     }
     if(h==0)eq(d.getString("dayAnchor"),d.getString("hourAnchor"),"zi hour starts at day anchor");
     if(day==1)eq(f.getString("anchor"),d.getString("dayAnchor"),"first day starts at month anchor");
     if(day>12)eq(DayHourState.describe(f,day-12,FlowState.BRANCH.substring(h,h+1)).getString("dayAnchor"),d.getString("dayAnchor"),"twelve-day cycle");
    }
    eq(before,f.toString(),"month and transformation source unmodified");
    JSONObject d=DayHourState.describe(f,1,"子");d.getJSONObject("dayPalaces").put("子","changed");eq(before,f.toString(),"output isolated from monthly input");
   }
   eq(chartBefore,c.toString(),"birth chart unchanged");
  }
  for(String value:new String[]{null,"","0","31","-1","1.0","1e1","100","一","１２","999999999999"})bad(()->DayHourState.parseDay(value));
  for(int day=1;day<=30;day++)eq(day,DayHourState.parseDay(" "+day+" "),"trimmed day");
  for(int day:new int[]{0,31,Integer.MIN_VALUE,Integer.MAX_VALUE})bad(()->DayHourState.describe(month,day,"子"));
  for(String hour:new String[]{null,"","子时","x"," 子","子子"})bad(()->DayHourState.describe(month,1,hour));
  bad(()->DayHourState.describe(null,1,"子"));bad(()->DayHourState.describe(FlowState.describe(chart,true,39),1,"子"));
  for(String branch:new String[]{"","invalid","子子"})bad(()->DayHourState.describe(new JSONObject(month.toString()).put("anchor",branch),1,"子"));
  System.out.println("PASS "+checks+" checks; 2 original runtime anchor sets; static rotation, exhaustive manual day/hour combinations, bounds and immutability");
 }
}
