import local.ziweixingyu.rebuild.*;
import org.json.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class FlowStarStateTest {
 static int checks;
 static void check(boolean ok) { if (!ok) throw new AssertionError("Flow stars check " + checks); checks++; }
 static String read(String path) throws Exception { return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8); }
 static void cells(JSONObject layer, JSONArray expected) throws Exception {
  JSONObject palaces = layer.getJSONObject("palaces");
  check(palaces.length() == 12);
  for (int i = 0; i < 12; i++) check(expected.getString(i).equals(palaces.getString(FlowState.BRANCH.substring(i,i+1))));
 }
 public static void main(String[] args) throws Exception {
  JSONObject chart = new JSONObject(read(args[0])).getJSONObject("male");
  String original = chart.toString();
  JSONObject annual = FlowStarState.forChart(chart,2,39);
  check(annual.getJSONObject("annual").getString("stem").equals("丁"));
  check(FlowState.describe(chart,true,39).getString("stem").equals("辛"));
  cells(annual.getJSONObject("annual"),new JSONArray("['咸耗','','喜','火','铃','陀马','存曲','羊昌','鸾','魁','','钺']"));
  cells(annual.getJSONObject("limit"),new JSONArray("['','','魁马','','陀铃','存喜','羊曲钺','','昌火','咸','','鸾耗']"));
  cells(FlowStarState.describe("癸","未","午"),new JSONArray("['存咸耗','羊','喜','魁火','铃','钺昌马','','','鸾','','','陀魁曲']"));
  check(FlowStarState.forChart(chart,3,39).getJSONObject("annual").toString().equals(annual.getJSONObject("annual").toString()));
  check(!FlowStarState.forChart(chart,1,1).has("limit"));
  check(FlowStarState.forChart(chart,2,1).has("annual"));
  check(!FlowStarState.forChart(chart,0,39).has("annual"));
  check(FlowStarState.summary(annual).contains("巳宫\n限：禄存、天喜\n年：陀罗、天马"));
  check(chart.toString().equals(original));
  try { FlowStarState.describe("丁","未",""); throw new AssertionError("Invalid hour accepted"); } catch (JSONException expected) { checks++; }
  System.out.println("PASS " + checks + " flow star checks");
  if (args.length > 1) {
   JSONArray reference = new JSONArray(read(args[1])); int before = checks;
   for (int i = 0; i < reference.length(); i++) {
    JSONObject row = reference.getJSONObject(i);
    cells(FlowStarState.describe(row.getString("stem"),row.getString("branch"),row.getString("hour")),row.getJSONArray("palaces"));
   }
   System.out.println("PASS " + reference.length() + " reference combinations, " + (checks-before) + " checks");
  }
 }
}
