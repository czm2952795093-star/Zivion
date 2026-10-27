package local.ziweixingyu.rebuild;
import android.app.Activity;
import android.webkit.*;
import org.json.*;

/** Local assets only; calculations never leave the device. */
final class LocalEngine {
 interface Callback {void done(JSONObject result,String error);}
 private final WebView view;private boolean ready;private Runnable pending;
 LocalEngine(Activity activity){view=new WebView(activity);view.getSettings().setJavaScriptEnabled(true);view.getSettings().setBlockNetworkLoads(true);view.getSettings().setAllowFileAccessFromFileURLs(false);view.getSettings().setAllowUniversalAccessFromFileURLs(false);
  view.setWebViewClient(new WebViewClient(){@Override public void onPageFinished(WebView v,String url){ready=true;if(pending!=null){Runnable r=pending;pending=null;r.run();}}@Override public boolean shouldOverrideUrlLoading(WebView v,String url){return true;}});
  view.loadUrl("file:///android_asset/engine.html");}
 void calculate(String input,Callback callback){Runnable task=()->view.evaluateJavascript("JSON.stringify(ZiweiEngine.safely("+JSONObject.quote(input)+"))",value->{try{Object decoded=new JSONTokener(value).nextValue();if(!(decoded instanceof String))throw new Exception("排盘模块未能启动");JSONObject r=new JSONObject((String)decoded);callback.done(r.optJSONObject("data"),r.optBoolean("ok")?null:r.optString("error","排盘失败"));}catch(Exception e){callback.done(null,"排盘模块未能返回有效结果："+e.getMessage());}});if(ready)task.run();else pending=task;}
 void close(){pending=null;view.destroy();}
}
