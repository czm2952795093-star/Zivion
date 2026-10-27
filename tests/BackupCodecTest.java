import local.ziweixingyu.rebuild.BackupCodec;
import org.json.*;

public class BackupCodecTest {
 static int count=0;
 static void check(boolean v,String message){if(!v)throw new AssertionError(message);count++;}
 interface Action{void run()throws Exception;}
 static void rejects(Action a)throws Exception{try{a.run();throw new AssertionError("invalid backup accepted");}catch(JSONException expected){count++;}}
 public static void main(String[] args)throws Exception{
  JSONObject record=new JSONObject().put("title","命例甲").put("request","柝袛袛祈祈拆#1|1990|1|1|12|0|0|120.0|-8|1|0|1").put("id","old-id").put("clock","1990-01-01 12:00:00");
  JSONObject note=new JSONObject().put("title","笔记").put("body","内容\n中文 🙂");
  String json=BackupCodec.encode(new JSONArray().put(record),new JSONArray().put(record),new JSONArray().put(note));
  JSONObject parsed=BackupCodec.parse(json);
  check(parsed.getJSONArray("notes").getJSONObject(0).getString("body").equals("内容\n中文 🙂"),"unicode roundtrip");
  check(parsed.getJSONArray("saved").getJSONObject(0).getString("request").equals(record.getString("request")),"request roundtrip");
  JSONArray duplicate=BackupCodec.merge(new JSONArray().put(record),parsed.getJSONArray("saved"),"saved");
  check(duplicate.length()==1,"duplicate restore is idempotent");
  check(duplicate.getJSONObject(0).getString("id").equals("old-id"),"existing record retained");
  check(BackupCodec.merge(new JSONArray().put(record).put(record),new JSONArray().put(record),"history").length()==2,"existing repeated history entries preserved");
  JSONObject changed=new JSONObject(record.toString()).put("title","命例乙");
  check(BackupCodec.merge(new JSONArray().put(record),new JSONArray().put(changed),"saved").length()==2,"distinct titles retained");
  check(BackupCodec.merge(new JSONArray().put(note),new JSONArray().put(new JSONObject(note.toString()).put("body","新内容")),"notes").length()==2,"same-title different notes retained");
  rejects(()->BackupCodec.parse("{}"));
  rejects(()->BackupCodec.parse("{"));
  rejects(()->BackupCodec.parse(new JSONObject(json).put("version",2).toString()));
  rejects(()->BackupCodec.parse(new JSONObject(json).put("notes",new JSONArray().put(new JSONObject().put("title","x").put("body",7))).toString()));
  rejects(()->BackupCodec.parse(new JSONObject(json).put("saved",new JSONArray().put(new JSONObject(record.toString()).put("request","untrusted payload"))).toString()));
  JSONObject missing=new JSONObject(json);missing.remove("history");rejects(()->BackupCodec.parse(missing.toString()));
  JSONArray many=new JSONArray();for(int i=0;i<5001;i++)many.put(new JSONObject().put("title",String.valueOf(i)).put("body","x"));
  rejects(()->BackupCodec.merge(new JSONArray(),many,"notes"));
  rejects(()->BackupCodec.parse(new JSONObject(json).put("notes",many).toString()));
  check(BackupCodec.parse(BackupCodec.encode(new JSONArray(),new JSONArray(),new JSONArray())).getJSONArray("saved").length()==0,"empty backup");
  System.out.println("PASS "+count+" backup codec checks");
 }
}
