package local.ziweixingyu.rebuild;
import android.app.AlertDialog;
import android.widget.*;
import android.view.View;
import org.json.*;
import java.io.*;
import java.util.*;

/** Explicit civil calendar helper; uncertain native leap/late-Zi policies are not applied. */
final class CalendarDialog {
 static void show(FlowController flow){
  MainActivity host=flow.host;final CalendarLookup lookup;
  try(InputStream in=host.getAssets().open("calendar-months.json");ByteArrayOutputStream out=new ByteArrayOutputStream()){
   byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);lookup=new CalendarLookup(out.toString("UTF-8"));
  }catch(Exception e){host.message("换算未打开",e.getMessage());return;}
  LinearLayout box=host.column();box.setPadding(host.dp(20),host.dp(8),host.dp(20),0);
  box.addView(host.text("按东八区钟表时间换算，午夜换日，不作真太阳时修正。支持1901—2100年；闰月和23点可查看结果，暂不能填入叠宫。",14,MainActivity.INK));
  java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyyMMddHHmmss",Locale.US);f.setTimeZone(TimeZone.getTimeZone("GMT+8"));
  EditText input=host.edit(f.format(new Date()),"公历年月日时分秒",android.text.InputType.TYPE_CLASS_NUMBER);input.setContentDescription("换算公历时间");box.addView(input);
  TextView result=host.text("输入14位年月日时分秒，点击换算。",15,MainActivity.INK);result.setPadding(0,host.dp(8),0,host.dp(8));result.setAccessibilityLiveRegion(View.ACCESSIBILITY_LIVE_REGION_POLITE);box.addView(result);
  TextView error=host.text("",14,MainActivity.RED);error.setAccessibilityLiveRegion(View.ACCESSIBILITY_LIVE_REGION_POLITE);error.setVisibility(View.GONE);box.addView(error);
  input.addTextChangedListener(new android.text.TextWatcher(){public void beforeTextChanged(CharSequence s,int start,int count,int after){}public void onTextChanged(CharSequence s,int start,int before,int count){result.setText("输入已修改，请重新换算。");error.setText("");error.setVisibility(View.GONE);}public void afterTextChanged(android.text.Editable s){}});
  ScrollView scroll=new ScrollView(host);scroll.addView(box);
  AlertDialog dialog=new AlertDialog.Builder(host).setTitle("公历换算为农历").setView(scroll).setNegativeButton("取消",null).setNeutralButton("换算",null).setPositiveButton("填入叠宫",null).create();
  dialog.setOnShowListener(d->{
   View.OnClickListener action=v->{error.setText("");error.setVisibility(View.GONE);result.setText("");try{
    JSONObject r=lookup.convert(input.getText().toString());result.setTextColor(MainActivity.INK);
    result.setText(r.getInt("year")+"年 "+(r.getBoolean("leap")?"闰":"")+MonthState.MONTHS[r.getInt("month")-1]+" "+r.getInt("day")+"日 · "+FlowState.BRANCH.substring(r.getInt("hour"),r.getInt("hour")+1)+"时\n本月"+r.getInt("daysInMonth")+"天。填入后按手选日时叠宫显示，不生成日时四化或流曜。");
    if(v==dialog.getButton(-1)){
     CalendarLookup.requireOverlay(r);int next=FlowState.ageForYear(host.chart,String.valueOf(r.getInt("year")));
     JSONObject layer=MonthState.describe(host.chart,next,r.getInt("month"));DayHourState.describe(layer,r.getInt("day"),FlowState.BRANCH.substring(r.getInt("hour"),r.getInt("hour")+1));
     flow.mode=3;flow.age=next;flow.month=r.getInt("month");flow.day=r.getInt("day");flow.hour=r.getInt("hour");flow.showDayHour=true;flow.clearSource();host.showChart();dialog.dismiss();
    }
   }catch(Exception e){error.setText(e.getMessage());error.setVisibility(View.VISIBLE);}};
   dialog.getButton(-3).setOnClickListener(action);dialog.getButton(-1).setOnClickListener(action);
  });dialog.show();
 }
}
