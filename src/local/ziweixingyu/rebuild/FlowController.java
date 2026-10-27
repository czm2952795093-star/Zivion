package local.ziweixingyu.rebuild;
import android.widget.*;
import android.text.InputType;
import java.util.*;
import org.json.*;

final class FlowController {
 final MainActivity host;int mode=0,age=1;JSONObject data,decadal;String source="";boolean lines=true;EditText ageInput;TextView heading,details,period;MainActivity.ChartView canvas;final Button[] modeButtons=new Button[3];
 FlowController(MainActivity host){this.host=host;}
 void reset(JSONObject chart){mode=0;data=null;decadal=null;source="";try{age=Math.max(1,Math.min(120,Calendar.getInstance(TimeZone.getTimeZone("GMT+8")).get(Calendar.YEAR)-Integer.parseInt(chart.getJSONObject("其他信息").getString("农历年"))+1));}catch(Exception e){age=1;}}
 void attach(LinearLayout content,MainActivity.ChartView view){canvas=view;LinearLayout box=host.column();box.setPadding(host.dp(8),host.dp(4),host.dp(8),host.dp(8));content.addView(box);
  LinearLayout modes=host.row();String[] names={"本命","大限","流年"};for(int i=0;i<3;i++){final int chosen=i;modeButtons[i]=host.button(names[i],()->select(chosen));modes.addView(modeButtons[i],new LinearLayout.LayoutParams(0,host.dp(44),1));}box.addView(modes);
  LinearLayout years=host.row();years.addView(host.text("虚岁",14,MainActivity.INK),new LinearLayout.LayoutParams(host.dp(38),host.dp(44)));years.addView(host.button("−",()->step(-1)),new LinearLayout.LayoutParams(host.dp(46),host.dp(44)));
  ageInput=host.edit(String.valueOf(age),"1—120",InputType.TYPE_CLASS_NUMBER);years.addView(ageInput,new LinearLayout.LayoutParams(0,host.dp(44),1));years.addView(host.button("+",()->step(1)),new LinearLayout.LayoutParams(host.dp(46),host.dp(44)));years.addView(host.button("应用",()->apply()),new LinearLayout.LayoutParams(host.dp(68),host.dp(44)));box.addView(years);
  LinearLayout options=host.row();CheckBox showLines=new CheckBox(host);showLines.setText("四化连线");showLines.setTextSize(14);showLines.setChecked(lines);showLines.setOnCheckedChangeListener((b,on)->{lines=on;canvas.invalidate();});options.addView(showLines,new LinearLayout.LayoutParams(0,host.dp(44),1));options.addView(host.button("回到命宫",()->{clearSource();refresh();}),new LinearLayout.LayoutParams(0,host.dp(44),1));box.addView(options);
  heading=host.text("",15,MainActivity.TEAL);box.addView(heading);period=host.text("",13,MainActivity.TEAL);box.addView(period);details=host.text("",14,MainActivity.INK);details.setTextIsSelectable(true);box.addView(details);refresh();
 }
 void clearSource(){source="";if(canvas!=null)canvas.selected=-1;}
 void select(int selected){mode=selected;clearSource();refresh();}
 void chooseSource(String branch){if(data==null||data.optJSONObject("palaces").length()==0)return;source=branch;refresh();}
 void cycle(){select((mode+1)%3);}
 void apply(){try{int next=Integer.parseInt(ageInput.getText().toString());if(next<1||next>120)throw new Exception();age=next;clearSource();ageInput.setError(null);((android.view.inputmethod.InputMethodManager)host.getSystemService(android.content.Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ageInput.getWindowToken(),0);refresh();}catch(Exception e){ageInput.setError("请输入1至120的虚岁");}}
 void step(int direction){try{age=FlowState.step(host.chart,mode!=1,age,direction);clearSource();ageInput.setText(String.valueOf(age));ageInput.setError(null);refresh();}catch(Exception e){host.message("暂时无法切换",e.getMessage());}}
 void refresh(){for(int i=0;i<3;i++){modeButtons[i].setSelected(i==mode);modeButtons[i].setTextColor(i==mode?android.graphics.Color.WHITE:MainActivity.INK);modeButtons[i].setBackgroundTintList(android.content.res.ColorStateList.valueOf(i==mode?MainActivity.TEAL:0xffeeeeee));}try{data=mode==0?null:FlowState.describe(host.chart,mode==2,age);decadal=mode==2?FlowState.describe(host.chart,false,age):null;if(data!=null&&!source.isEmpty())data=FlowState.selectSource(host.chart,data,source);heading.setText(data==null?"本命命盘":data.getString("title"));period.setText(decadal==null?"":decadal.getString("title"));period.setVisibility(decadal==null?android.view.View.GONE:android.view.View.VISIBLE);details.setText(data==null?"点击圆盘可切换本命、大限、流年。":summaryBody());canvas.setContentDescription("紫微十二宫命盘 · "+heading.getText()+(decadal==null?"":" · 同时叠加"+decadal.optString("title"))+(data==null?"":" · 四化来源"+data.optString("source",data.optString("anchor"))));canvas.invalidate();}catch(Exception e){data=null;decadal=null;heading.setText("流运未生成");period.setVisibility(android.view.View.GONE);details.setText(e.getMessage());canvas.invalidate();}}
 String summaryBody(){if(data==null)return "";StringBuilder out=new StringBuilder(prefix()+data.optString("sourceLabel","命")+"在"+data.optString("source",data.optString("anchor"))+" · "+data.optString("stem")+"干四化\n");JSONObject t=data.optJSONObject("transforms");if(t.length()==0)return "尚未进入首限，可调整虚岁或查看流年。";
  for(String mark:new String[]{"禄","权","科","忌"})for(Iterator<String> it=t.keys();it.hasNext();){String star=it.next();JSONObject v=t.optJSONObject(star);if(mark.equals(v.optString("化")))out.append(star).append("化").append(mark).append(" → ").append(v.optString("支")).append(v.optString("流宫")).append("宫").append(v.has("力度")?"（"+v.optInt("力度")+"%）":"（力度未确认）").append('\n');}return out.toString().trim();
 }
 String summary(){return data==null?"":"\n\n"+data.optString("title")+"\n"+(decadal==null?"":"同时叠加："+decadal.optString("title")+"\n")+summaryBody();}
 String overlay(String branch){return data==null?"":data.optJSONObject("palaces").optString(branch,"");}
 String prefix(){return mode==1?"限":"年";}
}
