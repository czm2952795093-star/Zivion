package local.ziweixingyu.rebuild;
import org.json.*;

/** A full collection rejects additions; saving never removes existing records. */
public final class RecordPolicy {
 public static final int LIMIT=5000,TITLE_LIMIT=500,NOTE_LIMIT=100000;
 public static JSONArray insert(JSONArray current,JSONObject record,boolean newestFirst)throws JSONException{
  if(current.length()>=LIMIT)throw new JSONException("记录已达5000条，请先备份并手动整理；已有记录未删除。");
  JSONArray result=new JSONArray();if(newestFirst)result.put(record);
  for(int i=0;i<current.length();i++)result.put(current.get(i));
  if(!newestFirst)result.put(record);return result;
 }
 public static void title(String value)throws JSONException{if(value.length()>TITLE_LIMIT)throw new JSONException("标题最多500字，请缩短后再保存。");}
 public static void note(String title,String body)throws JSONException{title(title);if(body.length()>NOTE_LIMIT)throw new JSONException("笔记内容最多100000字，请分篇保存。");}
}
