import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class MonthStateTest {
 static int checks=0;
 static void eq(Object a,Object b,String name){checks++;if(!a.equals(b))throw new AssertionError(name+": expected "+a+", got "+b);}
 static void observed(JSONObject chart,int age,String branches,String stems){
  String before=chart.toString();
  for(int m=1;m<=12;m++){
   JSONObject f=MonthState.describe(chart,age,m);String branch=branches.substring(m-1,m);
   eq(branch,f.getString("anchor"),"original month position");eq(stems.substring(m-1,m),f.getString("monthStem"),"original month stem");eq(MonthState.MONTHS[m-1],f.getJSONObject("monthMap").getJSONObject(branch).getString("name"),"original month name");
   eq(12,f.getJSONObject("palaces").length(),"twelve month palaces");eq("命",f.getJSONObject("palaces").getString(branch),"selected month anchor");
   JSONObject nativePalace=chart.getJSONObject("十二宫信息").getJSONObject(branch);eq(nativePalace.getString("宫干"),f.getString("stem"),"palace transformation stem kept separate from month stem");
   for(String star:nativePalace.getJSONObject("四化").keySet()){
    JSONObject a=nativePalace.getJSONObject("四化").getJSONObject(star),b=f.getJSONObject("transforms").getJSONObject(star);eq(a.getString("支"),b.getString("支"),"source targets preserved");eq(a.getString("化"),b.getString("化"),"source mark preserved");
   }
  }
  eq(before,chart.toString(),"birth chart unchanged");
 }
 public static void main(String[] args)throws Exception{
  JSONObject c=new JSONObject(new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8));
  // Month locations read from original annual-page screenshots; palace rotation is static REA evidence.
  observed(c.getJSONObject("male1990"),39,"寅卯辰巳午未申酉戌亥子丑","壬癸甲乙丙丁戊己庚辛壬癸");
  observed(c.getJSONObject("female1968"),60,"未申酉戌亥子丑寅卯辰巳午","壬癸甲乙丙丁戊己庚辛壬癸");
  observed(c.getJSONObject("male2001"),27,"午未申酉戌亥子丑寅卯辰巳","壬癸甲乙丙丁戊己庚辛壬癸");
  observed(c.getJSONObject("male2000"),28,"未申酉戌亥子丑寅卯辰巳午","壬癸甲乙丙丁戊己庚辛壬癸");
  observed(c.getJSONObject("male2000"),29,"申酉戌亥子丑寅卯辰巳午未","甲乙丙丁戊己庚辛壬癸甲乙");
  observed(c.getJSONObject("female2012"),16,"丑寅卯辰巳午未申酉戌亥子","壬癸甲乙丙丁戊己庚辛壬癸");
  observed(c.getJSONObject("female2012"),17,"寅卯辰巳午未申酉戌亥子丑","甲乙丙丁戊己庚辛壬癸甲乙");
  eq("1",c.getJSONObject("female2012").getJSONObject("其他信息").getString("农历是否闰月"),"leap birth case included");
  for(String key:c.keySet())for(int age:new int[]{1,2,30,60,120}){
   JSONObject f=MonthState.describe(c.getJSONObject(key),age,1);Set<String> months=new HashSet<>();for(String b:f.getJSONObject("monthMap").keySet())months.add(f.getJSONObject("monthMap").getJSONObject(b).getString("name"));eq(12,months.size(),"each month occurs once");
  }
  for(int m=1;m<=12;m++){eq(Math.min(12,m+1),MonthState.step(m,1),"next month bounded");eq(Math.max(1,m-1),MonthState.step(m,-1),"previous month bounded");}
  for(int m:new int[]{0,13}){boolean bad=false;try{MonthState.describe(c.getJSONObject("male1990"),39,m);}catch(JSONException e){bad=true;}eq(true,bad,"invalid month rejected");}
  for(int age:new int[]{0,121}){boolean bad=false;try{MonthState.describe(c.getJSONObject("male1990"),age,1);}catch(JSONException e){bad=true;}eq(true,bad,"invalid age rejected");}
  JSONObject changed=new JSONObject(c.getJSONObject("male1990").toString());changed.getJSONObject("其他信息").put("农历月","不明");boolean bad=false;try{MonthState.describe(changed,39,1);}catch(JSONException e){bad=true;}eq(true,bad,"unknown birth month does not silently default");
  System.out.println("PASS "+checks+" checks; 84 observed month placements/stems across 7 annual states; boundaries and source isolation");
 }
}
