import local.ziweixingyu.rebuild.*;
import org.json.*;
public class RecordPolicyTest {
 static int checks=0;
 static void check(boolean value){if(!value)throw new AssertionError();checks++;}
 interface Action{void run()throws Exception;}
 static void rejects(Action a)throws Exception{try{a.run();throw new AssertionError("Expected rejection");}catch(JSONException expected){checks++;}}
 static JSONArray rows(int n)throws Exception{JSONArray a=new JSONArray();for(int i=0;i<n;i++)a.put(new JSONObject().put("title","record-"+i).put("body","text"));return a;}
 public static void main(String[] args)throws Exception{
  JSONObject next=new JSONObject().put("title","new").put("body","new note");
  for(int n:new int[]{60,1000,4999}){JSONArray old=rows(n),result=RecordPolicy.insert(old,next,true);check(result.length()==n+1);check(old.length()==n);check(result.getJSONObject(n).getString("title").equals("record-"+(n-1)));check(result.getJSONObject(0)==next);}
  JSONArray full=rows(5000);String before=full.toString();rejects(()->RecordPolicy.insert(full,next,true));rejects(()->RecordPolicy.insert(full,next,false));check(before.equals(full.toString()));
  JSONArray append=RecordPolicy.insert(rows(2),next,false);check(append.getJSONObject(0).getString("title").equals("record-0"));check(append.getJSONObject(2)==next);
  rejects(()->RecordPolicy.title(new String(new char[501])));rejects(()->RecordPolicy.note("n",new String(new char[100001])));
  RecordPolicy.note(new String(new char[500]),new String(new char[100000]));checks++;
  String body=new String(new char[100000]).replace('\0','中');JSONArray small=new JSONArray(),large=new JSONArray();
  for(int i=0;i<28;i++){JSONObject note=new JSONObject().put("title","note-"+i).put("body",body);large.put(note);if(i<26)small.put(note);}
  String ok=BackupCodec.encode(new JSONArray(),new JSONArray(),small);check(BackupCodec.parse(ok).getJSONArray("notes").length()==26);
  rejects(()->BackupCodec.encode(new JSONArray(),new JSONArray(),large));
  JSONObject over=new JSONObject().put("format",BackupCodec.FORMAT).put("version",1).put("saved",new JSONArray()).put("history",new JSONArray()).put("notes",large);
  check(over.toString().length()<BackupCodec.MAX_BYTES);rejects(()->BackupCodec.parse(over.toString()));
  System.out.println("PASS "+checks+" retention, capacity and UTF-8 backup checks");
 }
}
