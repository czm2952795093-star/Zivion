import local.ziweixingyu.rebuild.FlowState;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class FlowSelectionTest {
 static int checks;
 static void eq(Object a,Object b,String note){checks++;if(!a.equals(b))throw new AssertionError(note+": "+a+" != "+b);}
 static void observed(JSONObject f,String anchor,String stem,String... rows){eq(anchor,f.getString("anchor"),"native anchor");eq(stem,f.getString("stem"),"native source stem");eq(4,f.getJSONObject("transforms").length(),"four marks");for(String row:rows){String[] r=row.split(",");JSONObject t=f.getJSONObject("transforms").getJSONObject(r[0]);eq(r[1],t.getString("化"),r[0]+" mark");eq(r[2],t.getString("支"),r[0]+" target");eq(Integer.parseInt(r[3]),t.getInt("力度"),r[0]+" strength");}}
 public static void main(String[] args)throws Exception{
  JSONObject samples=new JSONObject(new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8)),f=samples.getJSONObject("female1968"),m=samples.getJSONObject("male2001");
  JSONObject fa=FlowState.describe(f,true,60),ma=FlowState.describe(m,true,27);
  observed(fa,"未","己","武曲,禄,戌,90","贪狼,权,辰,90","天梁,科,未,15","文曲,忌,午,10");
  observed(ma,"未","乙","天机,禄,酉,99","天梁,权,亥,20","紫微,科,戌,30","太阴,忌,未,30");
  observed(FlowState.describe(f,false,60),"酉","辛","巨门,禄,巳,20","太阳,权,亥,20","文曲,科,午,10","文昌,忌,申,80");
  observed(FlowState.describe(m,false,27),"丑","辛","巨门,禄,酉,99","太阳,权,未,90","文曲,科,亥,80","文昌,忌,卯,20");
  observed(FlowState.selectSource(f,fa,"卯"),"未","乙","天机,禄,丑,50","天梁,权,未,90","紫微,科,寅,90","太阴,忌,卯,20");
  observed(FlowState.selectSource(m,ma,"卯"),"未","辛","巨门,禄,酉,99","太阳,权,未,90","文曲,科,亥,80","文昌,忌,卯,20");
  eq("52~61",FlowState.describe(f,false,60).getString("range"),"native female simultaneous decade");eq("23~32",FlowState.describe(m,false,27).getString("range"),"native male simultaneous decade");
  for(int k=0;k<2;k++){JSONObject chart=k==0?f:m;int age=k==0?60:27;String original=chart.toString();
   for(boolean annual:new boolean[]{false,true}){JSONObject layer=FlowState.describe(chart,annual,age);String originalLayer=layer.toString();
    for(int i=0;i<12;i++){String branch=FlowState.BRANCH.substring(i,i+1);JSONObject result=FlowState.selectSource(chart,layer,branch);
     eq(layer.getString("anchor"),result.getString("anchor"),"selection does not rotate overlay");eq(layer.getJSONObject("palaces").toString(),result.getJSONObject("palaces").toString(),"all labels remain");eq(layer.getJSONObject("palaces").getString(branch),result.getString("sourceLabel"),"selected flow palace name");eq(branch,result.getString("source"),"selected source");
     for(String star:result.getJSONObject("transforms").keySet()){JSONObject t=result.getJSONObject("transforms").getJSONObject(star);eq(layer.getJSONObject("palaces").getString(t.getString("支")),t.getString("流宫"),"destination label uses flow layer");}
    }
    eq(originalLayer,layer.toString(),"selection does not mutate default state");
   }
   eq(original,chart.toString(),"selection does not mutate birth data");
  }
  for(String invalid:new String[]{"","子丑","无"}){boolean rejected=false;try{FlowState.selectSource(m,ma,invalid);}catch(JSONException e){rejected=true;}eq(true,rejected,"invalid branch rejected");}
  boolean rejected=false;try{FlowState.selectSource(m,FlowState.describe(m,false,1),"子");}catch(JSONException e){rejected=true;}eq(true,rejected,"pre-first decade cannot select fake layer");
  System.out.println("PASS "+checks+" checks; 6 newly observed flow states; 48 source selections; overlays and birth data preserved");
 }
}
