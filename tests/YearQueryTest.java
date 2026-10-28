import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
public class YearQueryTest {
 static int checks=0;
 static void eq(Object a,Object b){checks++;if(!a.equals(b))throw new AssertionError(a+" != "+b);}
 public static void main(String[] args)throws Exception{
  JSONObject fixtures=new JSONObject(new String(Files.readAllBytes(Paths.get(args[0])),"UTF-8"));
  for(String key:fixtures.keySet()){
   JSONObject chart=fixtures.getJSONObject(key);String before=chart.toString();int birth=Integer.parseInt(chart.getJSONObject("其他信息").getString("农历年"));
   for(int age=1;age<=120;age++){
    String year=String.valueOf(birth+age-1);eq(age,FlowState.ageForYear(chart,year));eq(birth+age-1,FlowState.describe(chart,true,FlowState.ageForYear(chart,year)).getInt("year"));
   }
   eq(1,FlowState.ageForYear(chart," "+birth+" "));
   for(String bad:new String[]{null,"","-1","0","2027.0","2e3","２０２７","999999999999",String.valueOf(birth-1),String.valueOf(birth+120)}){
    boolean failed=false;try{FlowState.ageForYear(chart,bad);}catch(JSONException e){failed=true;}eq(true,failed);
   }
   eq(before,chart.toString());
  }
  System.out.println("PASS "+checks+" year-query checks; all 120 ages per fixture, limits, malformed input, chart isolation");
 }
}
