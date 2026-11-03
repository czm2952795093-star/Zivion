package local.ziweixingyu.rebuild;
import android.app.AlertDialog;
import android.view.View;
import android.widget.*;

final class LunarDateDialog {
 final MainActivity host;final LunarDateSelection selection;
 final NumberPicker year,month,day,hour,minute,second;final TextView status;
 boolean updating;String notice="";
 static void show(MainActivity host){
  try{
   CalendarLookup lookup=CalendarDialog.load(host);LunarDateSelection initial;String warning="";
   try{initial=new LunarDateSelection(lookup,host.draftDate,host.draftLeap);}catch(Exception e){initial=new LunarDateSelection(lookup,"19900101120000",false);warning="当前输入不是有效阴历日期，先显示1990年正月初一；点击填入后才会替换。";}
   new LunarDateDialog(host,initial,warning);
  }catch(Exception e){host.message("阴历日期未打开",e.getMessage());}
 }
 NumberPicker picker(int min,int max,int value,String label){NumberPicker p=new NumberPicker(host);p.setMinValue(min);p.setMaxValue(max);p.setValue(value);p.setWrapSelectorWheel(false);p.setContentDescription(label);for(int i=0;i<p.getChildCount();i++)if(p.getChildAt(i) instanceof EditText)p.getChildAt(i).setContentDescription(label);return p;}
 void add(LinearLayout row,String label,NumberPicker picker){LinearLayout field=host.column();TextView text=host.text(label,14,MainActivity.INK);text.setGravity(android.view.Gravity.CENTER);field.addView(text,new LinearLayout.LayoutParams(-1,host.dp(24)));field.addView(picker,new LinearLayout.LayoutParams(-1,host.dp(136)));row.addView(field,new LinearLayout.LayoutParams(0,-2,1));}
 LunarDateDialog(MainActivity host,LunarDateSelection selection,String warning)throws Exception{
  this.host=host;this.selection=selection;notice=warning;
  LinearLayout box=host.column();box.setPadding(host.dp(12),host.dp(4),host.dp(12),0);
  box.addView(host.text("选择阴历出生日期与时分秒。月份随年份显示实际闰月；仅填入草稿，点击起盘后计算。",14,MainActivity.INK));
  year=picker(1900,2100,selection.year(),"阴历年份");month=picker(0,11,0,"阴历月份");day=picker(1,selection.days(),selection.day(),"阴历日期");
  hour=picker(0,23,selection.hour(),"阴历小时");minute=picker(0,59,selection.minute(),"阴历分钟");second=picker(0,59,selection.second(),"阴历秒钟");
  LinearLayout dates=host.row();add(dates,"年",year);add(dates,"月",month);add(dates,"日",day);box.addView(dates);
  LinearLayout times=host.row();add(times,"时",hour);add(times,"分",minute);add(times,"秒",second);box.addView(times);
  status=host.text("",14,MainActivity.TEAL);status.setPadding(host.dp(6),host.dp(4),host.dp(6),host.dp(4));status.setAccessibilityLiveRegion(View.ACCESSIBILITY_LIVE_REGION_POLITE);box.addView(status);
  refresh();
  year.setOnValueChangedListener((p,old,value)->{if(updating)return;try{notice=selection.setYear(value);refresh();}catch(Exception e){notice=e.getMessage();refresh();}});
  month.setOnValueChangedListener((p,old,value)->{if(updating)return;try{notice=selection.setMonth(selection.months()[value]);refresh();}catch(Exception e){notice=e.getMessage();refresh();}});
  day.setOnValueChangedListener((p,old,value)->{if(updating)return;try{selection.setDay(value);notice="";showStatus();}catch(Exception e){status.setText(e.getMessage());}});
  NumberPicker.OnValueChangeListener time=(p,old,value)->{if(updating)return;try{selection.setTime(hour.getValue(),minute.getValue(),second.getValue());notice="";showStatus();}catch(Exception e){status.setText(e.getMessage());}};
  hour.setOnValueChangedListener(time);minute.setOnValueChangedListener(time);second.setOnValueChangedListener(time);
  ScrollView scroll=new ScrollView(host);scroll.addView(box);
  AlertDialog dialog=new AlertDialog.Builder(host).setTitle("调整阴历时间").setView(scroll).setNegativeButton("取消",null).setPositiveButton("填入",null).create();
  dialog.setOnShowListener(d->dialog.getButton(-1).setOnClickListener(v->{box.clearFocus();try{
   // Flush keyboard edits before reading picker values; listeners update dependent fields.
   year.clearFocus();month.clearFocus();day.clearFocus();hour.clearFocus();minute.clearFocus();second.clearFocus();
   selection.setTime(hour.getValue(),minute.getValue(),second.getValue());selection.setDay(day.getValue());
   host.date.setText(selection.formatted());host.leap.setChecked(selection.leap());host.rememberDraft();dialog.dismiss();
  }catch(Exception e){status.setText(e.getMessage());}}));dialog.show();
 }
 void refresh(){updating=true;try{
  int[] values=selection.months();String[] labels=new String[values.length];int index=0;
  for(int i=0;i<values.length;i++){int value=values[i];labels[i]=(value<0?"闰":"")+MonthState.MONTHS[Math.abs(value)-1];if(value==selection.month())index=i;}
  month.setDisplayedValues(null);month.setMinValue(0);month.setMaxValue(values.length-1);month.setDisplayedValues(labels);month.setValue(index);
  day.setMaxValue(selection.days());day.setValue(selection.day());year.setValue(selection.year());showStatus();
 }catch(Exception e){status.setText(e.getMessage());}finally{updating=false;}}
 void showStatus(){try{status.setText(selection.year()+"年 "+(selection.leap()?"闰":"")+MonthState.MONTHS[Math.abs(selection.month())-1]+" "+selection.day()+"日，本月"+selection.days()+"天。\n"+notice);}catch(Exception e){status.setText(e.getMessage());}}
}
