import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class CalendarLookupTest {
 static int count;static void check(boolean b)throws Exception{count++;if(!b)throw new Exception("calendar check "+count+" failed");}
 static String read(String p)throws Exception{return new String(Files.readAllBytes(Paths.get(p)),StandardCharsets.UTF_8);}
 public static void main(String[] args)throws Exception{
  CalendarLookup c=new CalendarLookup(read(args[0]));JSONArray rows=new JSONArray(read(args[1]));
  for(int i=0;i<rows.length();i++){JSONObject expected=rows.getJSONObject(i);for(String time:new String[]{"000000","120000","235959"}){
   JSONObject got=c.convert(expected.getString("input").substring(0,8)+time);
   for(String k:new String[]{"year","month","day","leap"})check(expected.get(k).equals(got.get(k)));
   check(got.getInt("day")<=got.getInt("daysInMonth"));boolean rejected=false;try{CalendarLookup.requireOverlay(got);}catch(Exception e){rejected=true;}check(rejected==(expected.getBoolean("leap")||time.equals("235959")));
  }}
  for(int h=0;h<24;h++){JSONObject r=c.convert("20261030"+String.format(Locale.US,"%02d5959",h));check(r.getInt("hour")==((h+1)/2)%12);}
  for(String bad:new String[]{null,"","20260230000000","20250229000000","20260001000000","20261301000000","20260100235959","20260101240000","20260101006000","20260101000060","19000101000000","21010101000000","202601010000000","２０２６０１０１００００００"}){boolean rejected=false;try{c.convert(bad);}catch(Exception e){rejected=true;}check(rejected);}
  check(c.convert("19010101000000").getInt("year")==1900);check(c.convert("21001231235959").getInt("day")>0);check(c.convert("20240229120000").getInt("day")>0);
  String baseline=c.convert("20261030000000").toString();TimeZone previous=TimeZone.getDefault();try{for(String zone:new String[]{"America/New_York","Pacific/Auckland","UTC"}){TimeZone.setDefault(TimeZone.getTimeZone(zone));check(c.convert("20261030000000").toString().equals(baseline));}}finally{TimeZone.setDefault(previous);}
  JSONObject table=new JSONObject(read(args[0]));table.getJSONArray("months").getJSONArray(1).put(3,31);boolean rejected=false;try{new CalendarLookup(table.toString());}catch(Exception e){rejected=true;}check(rejected);
  System.out.println("PASS "+count+" calendar checks; 730 official daily dates at 3 times; leap/late-Zi gating, input bounds and timezone independence");
 }
}
