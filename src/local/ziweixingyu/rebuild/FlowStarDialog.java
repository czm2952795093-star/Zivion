package local.ziweixingyu.rebuild;
import android.app.AlertDialog;
import android.content.*;
import android.widget.*;

final class FlowStarDialog {
 static void show(FlowController flow){
  MainActivity host=flow.host;try{
   final String report=FlowStarState.summary(flow.stars);LinearLayout box=host.column();box.setPadding(host.dp(20),host.dp(8),host.dp(20),host.dp(8));
   box.addView(host.text("按当前虚岁查看大限与流年。流月沿用当年流曜；月、日、时流曜尚未提供。",14,MainActivity.INK));
   TextView result=host.text(report,15,MainActivity.INK);result.setTextIsSelectable(true);result.setPadding(0,host.dp(10),0,0);box.addView(result);ScrollView scroll=new ScrollView(host);scroll.addView(box);
   new AlertDialog.Builder(host).setTitle("限年流曜分布").setView(scroll).setPositiveButton("关闭",null).setNeutralButton("复制流曜",(d,w)->{((ClipboardManager)host.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("限年流曜",report));host.toast("流曜分布已复制");}).show();
  }catch(Exception e){host.message("流曜未显示",e.getMessage());}
 }
}
