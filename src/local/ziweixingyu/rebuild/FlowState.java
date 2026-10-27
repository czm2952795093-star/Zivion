package local.ziweixingyu.rebuild;
import org.json.*;
import java.util.*;

/** Age-selected palace overlays. Original REA g(Canvas) establishes the rotation order. */
public final class FlowState {
 public static final String BRANCH="子丑寅卯辰巳午未申酉戌亥",STEM="甲乙丙丁戊己庚辛壬癸";
 static final String[] NAMES={"命","父母","福德","田宅","官禄","交友","迁移","疾厄","财帛","子女","夫妻","兄弟"};
 public static JSONObject describe(JSONObject chart,boolean annual,int age)throws JSONException{
  if(age<1||age>120)throw new JSONException("虚岁请输入1至120");
  JSONObject natal=chart.getJSONObject("十二宫信息"),info=chart.getJSONObject("其他信息");
  int year=Integer.parseInt(info.getString("农历年"))+age-1;String anchor="",stem="",range="";
  String yearStem=STEM.substring(Math.floorMod(year-4,10),Math.floorMod(year-4,10)+1);
  if(annual){anchor=BRANCH.substring(Math.floorMod(year-4,12),Math.floorMod(year-4,12)+1);stem=natal.getJSONObject(anchor).getString("宫干");}
  else for(int i=0;i<12;i++){String b=BRANCH.substring(i,i+1);JSONObject p=natal.getJSONObject(b);String[] bounds=p.getString("大限").split("~");if(age>=Integer.parseInt(bounds[0])&&age<=Integer.parseInt(bounds[1])){anchor=b;stem=p.getString("宫干");range=p.getString("大限");break;}}
  JSONObject result=new JSONObject().put("mode",annual?"annual":"decadal").put("age",age).put("year",year).put("yearStem",yearStem).put("anchor",anchor).put("stem",stem).put("range",range);
  JSONObject labels=new JSONObject(),transforms=new JSONObject();result.put("palaces",labels).put("transforms",transforms);
  if(anchor.isEmpty()){result.put("title","大限 · "+age+"虚岁 · 尚未进入首限");return result;}
  for(int i=0;i<12;i++)labels.put(BRANCH.substring(i,i+1),NAMES[Math.floorMod(i-BRANCH.indexOf(anchor),12)]);
  // REA g(Canvas) selects the flow anchor; a(Canvas) reads that palace's 四化.
  JSONObject base=natal.getJSONObject(anchor).getJSONObject("四化");
  for(Iterator<String> it=base.keys();it.hasNext();){String star=it.next();JSONObject t=new JSONObject(base.getJSONObject(star).toString());t.put("本命宫",t.getString("宫"));t.put("流宫",labels.getString(t.getString("支")));transforms.put(star,t);}
  result.put("title",annual?"流年 · "+year+"年 "+yearStem+anchor+" · "+age+"虚岁":"大限 · "+range+"虚岁 · "+stem+anchor+"限");return result;
 }
 public static int step(JSONObject chart,boolean annual,int age,int direction)throws JSONException{
  if(direction!=1&&direction!=-1)throw new JSONException("方向无效");
  if(annual)return Math.max(1,Math.min(120,age+direction));
  JSONObject natal=chart.getJSONObject("十二宫信息");ArrayList<Integer> starts=new ArrayList<>();
  for(int i=0;i<12;i++)starts.add(Integer.parseInt(natal.getJSONObject(BRANCH.substring(i,i+1)).getString("大限").split("~")[0]));Collections.sort(starts);
  int index=-1;for(int i=0;i<starts.size();i++)if(starts.get(i)<=age)index=i;
  int target=index+direction;if(target<0)return 1;if(target>=starts.size())return age;return Math.max(1,Math.min(120,starts.get(target)));
 }
}
