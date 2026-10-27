import local.ziweixingyu.rebuild.FlowState;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class FlowStateTest {
 static int checks=0;
 static void eq(Object expected,Object actual,String name){checks++;if(!expected.equals(actual))throw new AssertionError(name+": expected "+expected+", got "+actual);}
 static void state(JSONObject chart,boolean annual,int age,String anchor,String stem,String labels,String[] transforms)throws Exception{
  JSONObject f=FlowState.describe(chart,annual,age);eq(anchor,f.getString("anchor"),"native anchor");eq(stem,f.getString("stem"),"anchor stem, not year stem");
  String[] names=labels.split(",");for(int i=0;i<12;i++)eq(names[i],f.getJSONObject("palaces").getString(FlowState.BRANCH.substring(i,i+1)),"native overlay");
  eq(4,f.getJSONObject("transforms").length(),"four transformations");
  for(String item:transforms){String[] s=item.split(",");JSONObject t=f.getJSONObject("transforms").getJSONObject(s[0]);eq(s[1],t.getString("化"),"native transformation");eq(s[2],t.getString("支"),"native destination");eq(Integer.parseInt(s[3]),t.getInt("力度"),"native strength");}
 }
 public static void main(String[] args)throws Exception{
  JSONObject fixtures=new JSONObject(new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8));JSONObject male=fixtures.getJSONObject("male"),female=fixtures.getJSONObject("female");
  // Explicitly transcribed REA original screens listed in flow-observations.json.
  state(male,false,39,"辰","戊","财帛,子女,夫妻,兄弟,命,父母,福德,田宅,官禄,交友,迁移,疾厄",new String[]{"贪狼,禄,辰,50","太阴,权,卯,90","右弼,科,亥,80","天机,忌,丑,95"});
  state(male,false,26,"巳","己","疾厄,财帛,子女,夫妻,兄弟,命,父母,福德,田宅,官禄,交友,迁移",new String[]{"武曲,禄,戌,90","贪狼,权,辰,90","天梁,科,未,15","文曲,忌,戌,50"});
  state(female,false,39,"戌","甲","福德,田宅,官禄,交友,迁移,疾厄,财帛,子女,夫妻,兄弟,命,父母",new String[]{"廉贞,禄,午,10","破军,权,子,10","武曲,科,戌,30","太阳,忌,亥,80"});
  String annualLabels="交友,迁移,疾厄,财帛,子女,夫妻,兄弟,命,父母,福德,田宅,官禄";
  String[] annualTransforms={"巨门,禄,巳,20","太阳,权,亥,20","文曲,科,戌,30","文昌,忌,辰,30"};
  state(male,true,39,"未","辛",annualLabels,annualTransforms);state(female,true,39,"未","辛",annualLabels,annualTransforms);
  state(male,true,26,"午","庚","迁移,疾厄,财帛,子女,夫妻,兄弟,命,父母,福德,田宅,官禄,交友",new String[]{"太阳,禄,亥,60","武曲,权,戌,50","太阴,科,卯,95","天同,忌,酉,90"});
  eq(2027,FlowState.describe(male,true,39).getInt("year"),"lunar birth year used");eq("丁",FlowState.describe(male,true,39).getString("yearStem"),"display year stem separate from palace");
  eq(2014,FlowState.describe(male,true,26).getInt("year"),"second observed year");
  for(JSONObject chart:new JSONObject[]{male,female}){
   String before=chart.toString();
   for(int age=1;age<=120;age++){
    JSONObject annual=FlowState.describe(chart,true,age),decadal=FlowState.describe(chart,false,age);
    eq(12,annual.getJSONObject("palaces").length(),"annual covers twelve palaces");eq(4,annual.getJSONObject("transforms").length(),"annual four transformations");
    eq(age<5?0:12,decadal.getJSONObject("palaces").length(),"pre-first period has no fabricated anchor");
    eq(Math.min(120,age+1),FlowState.step(chart,true,age,1),"next year bound");eq(Math.max(1,age-1),FlowState.step(chart,true,age,-1),"previous year bound");
   }
   eq(before,chart.toString(),"flow never mutates natal chart");
   eq(45,FlowState.step(chart,false,39,1),"next decadal period by age");eq(25,FlowState.step(chart,false,39,-1),"previous decadal period by age");eq(5,FlowState.step(chart,false,1,1),"enter first period");
   eq(1,FlowState.step(chart,false,5,-1),"return pre-first period");eq(120,FlowState.step(chart,false,120,1),"last period bound");
   for(int age:new int[]{0,121}){boolean rejected=false;try{FlowState.describe(chart,true,age);}catch(JSONException e){rejected=true;}eq(true,rejected,"invalid age rejected");}
  }
  eq("卯",FlowState.describe(male,false,45).getString("anchor"),"male reverse direction");eq("亥",FlowState.describe(female,false,45).getString("anchor"),"female forward direction");
  // Unknown strength stays absent rather than becoming zero, and copied data stays isolated.
  JSONObject isolated=new JSONObject(male.toString());isolated.getJSONObject("十二宫信息").getJSONObject("未").getJSONObject("四化").getJSONObject("巨门").remove("力度");
  JSONObject flow=FlowState.describe(isolated,true,39);eq(false,flow.getJSONObject("transforms").getJSONObject("巨门").has("力度"),"unknown strength preserved");
  flow.getJSONObject("transforms").getJSONObject("巨门").put("支","子");eq("巳",isolated.getJSONObject("十二宫信息").getJSONObject("未").getJSONObject("四化").getJSONObject("巨门").getString("支"),"deep copy");
  System.out.println("PASS "+checks+" checks; 6 observed flow states, 2 sexes x 120 ages; natal data unchanged");
 }
}
