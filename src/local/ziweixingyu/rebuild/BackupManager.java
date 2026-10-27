package local.ziweixingyu.rebuild;

import android.app.*;
import android.content.*;
import android.net.Uri;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.json.*;

final class BackupManager {
 static final int EXPORT=8101,IMPORT=8102;private final MainActivity host;private String pendingExport;
 BackupManager(MainActivity activity){host=activity;}
 void menu(){host.retainDraft();new AlertDialog.Builder(host).setTitle("备份与恢复").setItems(new String[]{"导出命例、历史和笔记","从备份文件合并恢复"},(d,w)->{
  try{if(w==0)pendingExport=BackupCodec.encode(host.array("saved"),host.array("history"),host.array("notes"));Intent i=new Intent(w==0?Intent.ACTION_CREATE_DOCUMENT:Intent.ACTION_OPEN_DOCUMENT);i.addCategory(Intent.CATEGORY_OPENABLE);i.setType(w==0?"application/json":"*/*");if(w==0)i.putExtra(Intent.EXTRA_TITLE,"紫微星语备份-"+new java.text.SimpleDateFormat("yyyyMMdd-HHmmss",java.util.Locale.US).format(new java.util.Date())+".json");host.startActivityForResult(i,w==0?EXPORT:IMPORT);}catch(ActivityNotFoundException e){host.message("无法打开文件选择器","此设备缺少系统文件管理器。");}catch(Exception e){host.message("无法导出备份",e.getMessage());}
 }).setNegativeButton("返回",null).show();}
 void result(int request,int result,Intent data){if(result!=Activity.RESULT_OK||data==null||data.getData()==null){pendingExport=null;return;}Uri uri=data.getData();
  try{
   if(request==EXPORT){String json=pendingExport!=null?pendingExport:BackupCodec.encode(host.array("saved"),host.array("history"),host.array("notes"));pendingExport=null;try(OutputStream out=host.getContentResolver().openOutputStream(uri,"wt")){if(out==null)throw new IOException("无法写入文件");out.write(json.getBytes(StandardCharsets.UTF_8));}host.toast("备份已保存");return;}
   if(request!=IMPORT)return;String json;
   try(InputStream in=host.getContentResolver().openInputStream(uri);ByteArrayOutputStream out=new ByteArrayOutputStream()){if(in==null)throw new IOException("无法读取文件");byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1){if(out.size()+n>BackupCodec.MAX_BYTES)throw new IOException("文件超过8MB");out.write(b,0,n);}json=new String(out.toByteArray(),StandardCharsets.UTF_8);}
   JSONObject parsed=BackupCodec.parse(json);String summary="备份包含 "+parsed.getJSONArray("saved").length()+" 条命例、"+parsed.getJSONArray("history").length()+" 条历史、"+parsed.getJSONArray("notes").length()+" 条笔记。\n\n合并时保留现有记录，并跳过相同内容。";
   new AlertDialog.Builder(host).setTitle("合并恢复").setMessage(summary).setNegativeButton("取消",null).setPositiveButton("合并",(d,w)->{
    try{JSONObject merged=new JSONObject();for(String key:new String[]{"saved","history","notes"})merged.put(key,BackupCodec.merge(host.array(key),parsed.getJSONArray(key),key));android.content.SharedPreferences.Editor edit=host.prefs.edit();for(String key:new String[]{"saved","history","notes"})edit.putString(key,merged.getJSONArray(key).toString());if(!edit.commit())throw new IOException("写入失败");host.toast("备份已合并，现有记录已保留");host.showInput();}catch(Exception e){host.message("恢复未完成",e.getMessage());}
   }).show();
  }catch(Exception e){host.message("备份操作未完成",e.getMessage());}
 }
}
