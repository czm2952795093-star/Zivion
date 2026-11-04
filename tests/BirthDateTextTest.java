import local.ziweixingyu.rebuild.BirthDateText;
import org.json.*;
public class BirthDateTextTest {
 static int count;
 static void check(boolean value){count++;if(!value)throw new AssertionError("birth date label check "+count);}
 public static void main(String[] args)throws Exception{
  JSONObject info=new JSONObject().put("农历年","2025").put("农历月","六").put("农历日","廿九").put("时支","午").put("农历是否闰月","1");
  String before=info.toString();check(BirthDateText.lunar(info).equals("2025年闰六月廿九午时"));check(info.toString().equals(before));
  for(Object flag:new Object[]{"1",1,true,"true"}){info.put("农历是否闰月",flag);check(BirthDateText.lunar(info).equals("2025年闰六月廿九午时"));}
  for(String month:new String[]{"闰六","闰六月","六月"}){info.put("农历月",month);check(BirthDateText.lunar(info).equals("2025年闰六月廿九午时"));}
  info.put("农历月","闰六月").put("农历是否闰月","0");check(BirthDateText.lunar(info).equals("2025年闰六月廿九午时"));
  info.put("农历月","六");check(BirthDateText.lunar(info).equals("2025年六月廿九午时"));
  check(BirthDateText.input("20250629123456",true,true,false).equals("阴历2025年闰6月29日12时34分"));
  check(BirthDateText.input("20250629123456",true,true,true).equals("阴历2025年闰6月29日12时34分56秒"));
  check(BirthDateText.input("20250629123456",false,true,true).equals("公历2025年6月29日12时34分56秒"));
  check(!BirthDateText.input("20250629123456",true,false,false).contains("闰"));
  for(String input:new String[]{null,"","2025","２０２５０６２９１２３４５６","2025062912345a"})check(BirthDateText.input(input,true,true,true).equals("时间信息（年月日时分秒）"));
  check(BirthDateText.lunar(null).isEmpty());
  System.out.println("PASS "+count+" birth date label checks; leap metadata/prefix normalization, lunar/solar labels, seconds, partial input and no mutation");
 }
}
