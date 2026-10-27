package local.ziweixingyu.rebuild;

import org.json.*;
import java.util.*;

/** Validate the entire backup before any preference write; merge without deleting existing data. */
public final class BackupCodec {
 public static final String FORMAT="ziweixingyu-reconstruction";
 private static String string(JSONObject o,String k,int max)throws JSONException{
  Object value=o.opt(k);if(!(value instanceof String)||((String)value).length()>max)throw new JSONException("字段无效："+k);return (String)value;
 }
 private static JSONArray records(JSONArray rows,String kind)throws JSONException{
  if(rows==null||rows.length()>5000)throw new JSONException("记录列表缺失或过大");JSONArray clean=new JSONArray();
  for(int i=0;i<rows.length();i++){
   JSONObject r=rows.getJSONObject(i),n=new JSONObject();n.put("title",string(r,"title",500));
   if(kind.equals("notes")){n.put("body",string(r,"body",100000));}
   else {String request=string(r,"request",500);
    if(!request.matches("柝袛袛祈祈拆#[1-4]\\|[0-9]{4}\\|[0-9]{1,2}\\|[0-9]{1,2}\\|[0-9]{1,2}\\|[0-9]{1,2}\\|[0-9]{1,2}\\|-?[0-9]+(?:\\.[0-9]+)?\\|-8\\|[12]\\|0(?:\\|[01])?"))throw new JSONException("命例时间格式无效");
    n.put("request",request);n.put("clock",r.optString("clock",""));n.put("time",r.optLong("time",0));n.put("id",r.optString("id",UUID.randomUUID().toString()));
   }clean.put(n);
  }return clean;
 }
 public static JSONObject parse(String json)throws JSONException{
  if(json==null||json.length()>8000000)throw new JSONException("备份文件过大");JSONObject o=new JSONObject(json);
  if(!FORMAT.equals(o.optString("format"))||o.optInt("version")!=1)throw new JSONException("不是受支持的紫微星语复刻版备份");
  JSONObject clean=new JSONObject();for(String k:new String[]{"saved","history","notes"})clean.put(k,records(o.optJSONArray(k),k));return clean;
 }
 public static String encode(JSONArray saved,JSONArray history,JSONArray notes)throws JSONException{
  JSONObject o=new JSONObject();o.put("format",FORMAT);o.put("version",1);o.put("createdAt",System.currentTimeMillis());o.put("saved",records(saved,"saved"));o.put("history",records(history,"history"));o.put("notes",records(notes,"notes"));return o.toString(2);
 }
 public static JSONArray merge(JSONArray current,JSONArray incoming,String kind)throws JSONException{
  JSONArray out=new JSONArray();Set<String> seen=new HashSet<>();
  for(JSONArray rows:new JSONArray[]{current,incoming})for(int i=0;i<rows.length();i++){
   JSONObject r=rows.getJSONObject(i);String key=new JSONArray().put(r.optString("title")).put(r.optString(kind.equals("notes")?"body":"request")).toString();
   boolean fresh=seen.add(key);if(rows==current||fresh)out.put(r);
  }
  if(out.length()>5000)throw new JSONException("合并后记录过多，请先整理记录");return out;
 }
}
