import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class RegularMonthTest {
 static int count;
 static void check(boolean value)throws Exception{count++;if(!value)throw new Exception("regular month check "+count+" failed");}
 static boolean accepts(CalendarLookup c,int y,int m,int d){try{c.requireRegularDay(y,m,d);return true;}catch(Exception e){return false;}}
 public static void main(String[] args)throws Exception{
  CalendarLookup c=new CalendarLookup(new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8));
  JSONArray official=new JSONArray(new String(Files.readAllBytes(Paths.get(args[1])),StandardCharsets.UTF_8));int boundaries=0;
  for(int i=0;i<official.length()-1;i++){
   JSONObject a=official.getJSONObject(i),b=official.getJSONObject(i+1);
   if(!a.getBoolean("leap")&&b.getInt("day")==1){
    int y=a.getInt("year"),m=a.getInt("month"),last=a.getInt("day");boundaries++;
    check(c.regularMonthDays(y,m)==last);check(accepts(c,y,m,1));check(accepts(c,y,m,last));check(!accepts(c,y,m,last+1));check(!accepts(c,y,m,0));
   }
  }
  check(boundaries==23);
  check(c.regularMonthDays(2025,6)==30);check(c.convert("20250725120000").getInt("daysInMonth")==29);
  check(accepts(c,2025,6,30));check(!accepts(c,2026,2,30));
  check(accepts(c,1900,1,1));check(accepts(c,2100,12,1));
  for(int[] bad:new int[][]{{1899,12,1},{2101,1,1},{2026,0,1},{2026,13,1},{2026,-6,1},{2026,1,-1},{2026,1,31}})check(!accepts(c,bad[0],bad[1],bad[2]));
  System.out.println("PASS "+count+" regular month checks; "+boundaries+" official month boundaries, ordinary/leap distinction and range limits");
 }
}
