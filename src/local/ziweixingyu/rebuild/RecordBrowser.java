package local.ziweixingyu.rebuild;

import android.app.AlertDialog;
import android.text.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.EditorInfo;
import android.content.Context;
import android.widget.*;
import org.json.*;
import java.util.*;

/** Search operates on original row indices; filtered deletion never uses the visible position. */
final class RecordBrowser {
 final MainActivity host;final String key,title;final boolean notes;
 final JSONArray rows;final ArrayList<Integer> visible=new ArrayList<>();
 EditText query;TextView count,empty;BaseAdapter adapter;
 RecordBrowser(MainActivity host,String key,String title){this.host=host;this.key=key;this.title=title;notes=key.equals("notes");rows=host.array(key);}
 void show(){
  host.screen=notes?"notes":"records";host.base(title,notes?"新增":"返回",()->{if(notes)host.editNote(-1);else host.showInput();});
  LinearLayout search=host.row();search.setPadding(host.dp(10),host.dp(4),host.dp(10),0);
  query=host.edit(host.recordFilters.getOrDefault(key,""),notes?"搜索标题或笔记内容":"搜索名称或出生日期",android.text.InputType.TYPE_CLASS_TEXT);
  query.setContentDescription(notes?"搜索笔记":"搜索命例或历史");query.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
  query.setOnEditorActionListener((v,id,event)->{if(id==EditorInfo.IME_ACTION_SEARCH){hideKeyboard();return true;}return false;});
  search.addView(query,new LinearLayout.LayoutParams(0,host.dp(48),1));search.addView(host.button("清空",()->query.setText("")),new LinearLayout.LayoutParams(host.dp(64),host.dp(48)));host.root.addView(search);
  count=host.text("",13,0xff777777);count.setPadding(host.dp(16),host.dp(4),host.dp(16),host.dp(4));host.root.addView(count);
  FrameLayout frame=new FrameLayout(host);host.root.addView(frame,new LinearLayout.LayoutParams(-1,0,1));
  ListView list=new ListView(host);list.setDividerHeight(host.dp(1));frame.addView(list,new FrameLayout.LayoutParams(-1,-1));
  empty=host.text("",16,MainActivity.INK);empty.setGravity(Gravity.CENTER);empty.setPadding(host.dp(20),host.dp(30),host.dp(20),host.dp(30));frame.addView(empty,new FrameLayout.LayoutParams(-1,-1));list.setEmptyView(empty);
  adapter=new BaseAdapter(){public int getCount(){return visible.size();}public Object getItem(int position){return rows.optJSONObject(visible.get(position));}public long getItemId(int position){return visible.get(position);}
   public View getView(int position,View reuse,ViewGroup parent){
    final int originalIndex=visible.get(position);final JSONObject r=rows.optJSONObject(originalIndex);
    if(notes){Button b=host.button(r.optString("title")+"\n"+r.optString("body"),()->{hideKeyboard();host.editNote(originalIndex);});b.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);b.setMaxLines(3);b.setEllipsize(TextUtils.TruncateAt.END);b.setLayoutParams(new AbsListView.LayoutParams(-1,host.dp(90)));return b;}
    LinearLayout item=host.row();item.setLayoutParams(new AbsListView.LayoutParams(-1,host.dp(76)));
    LinearLayout open=host.column();open.setGravity(Gravity.CENTER_VERTICAL);open.setPadding(host.dp(14),host.dp(8),host.dp(8),host.dp(8));
    TextView name=host.text(r.optString("title"),16,MainActivity.TEAL);name.setSingleLine(true);name.setEllipsize(TextUtils.TruncateAt.END);name.setTypeface(null,android.graphics.Typeface.BOLD);open.addView(name);
    TextView date=host.text(r.optString("clock"),14,MainActivity.INK);date.setSingleLine(true);date.setEllipsize(TextUtils.TruncateAt.END);open.addView(date);
    open.setContentDescription(r.optString("title")+"，"+r.optString("clock"));open.setClickable(true);open.setOnClickListener(v->{hideKeyboard();host.runChart(r.optString("request"),r.optString("title"),false,false);});item.addView(open,new LinearLayout.LayoutParams(0,-1,1));
    Button delete=host.button("删除",()->new AlertDialog.Builder(host).setTitle("删除这条记录？").setMessage(r.optString("title")).setNegativeButton("取消",null).setPositiveButton("删除",(d,w)->{rows.remove(originalIndex);host.prefs.edit().putString(key,rows.toString()).apply();host.showRecords(key,title);}).show());delete.setContentDescription("删除："+r.optString("title"));item.addView(delete,new LinearLayout.LayoutParams(host.dp(68),host.dp(52)));return item;
   }};
  list.setAdapter(adapter);query.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int start,int count,int after){}public void onTextChanged(CharSequence s,int start,int before,int count){filter();}public void afterTextChanged(Editable e){}});filter();
 }
 void hideKeyboard(){((InputMethodManager)host.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(query.getWindowToken(),0);}
 void filter(){String q=query.getText().toString().trim().toLowerCase(Locale.ROOT);host.recordFilters.put(key,query.getText().toString());visible.clear();
  for(int i=0;i<rows.length();i++){JSONObject r=rows.optJSONObject(i);if(r==null)continue;String value=r.optString("title")+"\n"+r.optString(notes?"body":"clock");boolean match=value.toLowerCase(Locale.ROOT).contains(q);if(!notes&&!match&&q.matches("[0-9]+"))match=r.optString("clock").replaceAll("[^0-9]","").contains(q);if(match)visible.add(i);}
  count.setText("显示 "+visible.size()+" / "+rows.length()+" 条");empty.setText(rows.length()==0?(notes?"暂无笔记，点击“新增”开始记录。":"暂无记录"):(notes?"未找到笔记，请更换关键词或清空搜索。":"未找到记录，请更换关键词或清空搜索。"));adapter.notifyDataSetChanged();
 }
}
