package local.ziweixingyu.rebuild;
import org.json.JSONException;

/** Shared validation of persisted input; calendar validity remains the engine responsibility. */
public final class ChartRequest {
 public static void validate(String input)throws JSONException{
  if(input==null||input.length()>500||!input.startsWith("柝袛袛祈祈拆#"))throw new JSONException("命盘格式无效");
  String[] p=input.substring("柝袛袛祈祈拆#".length()).split("\\|",-1);
  if(p.length!=11&&p.length!=12)throw new JSONException("命盘字段缺失");
  int[] min={1,1900,1,1,0,0,0},max={4,2100,12,31,23,59,59};
  try{for(int i=0;i<7;i++){if(!p[i].matches("[0-9]{1,4}"))throw new NumberFormatException();int n=Integer.parseInt(p[i]);if(n<min[i]||n>max[i])throw new NumberFormatException();}
   if(!p[7].matches("-?[0-9]+(?:\\.[0-9]+)?(?:[eE][+-]?[0-9]+)?"))throw new NumberFormatException();
   double lon=Double.parseDouble(p[7]);if(!Double.isFinite(lon)||Math.abs(lon)>180||!p[8].equals("-8")||!p[9].matches("[12]")||!p[10].equals("0")||(p.length==12&&!p[11].matches("[01]")))throw new NumberFormatException();
  }catch(NumberFormatException e){throw new JSONException("命盘输入无效");}
 }
}
