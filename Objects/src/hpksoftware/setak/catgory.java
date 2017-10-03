package hpksoftware.setak;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class catgory extends Activity implements B4AActivity{
	public static catgory mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.catgory");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (catgory).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.catgory");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.catgory", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (catgory) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (catgory) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return catgory.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (catgory) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (catgory) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _server_mysql = "";
public static boolean _bool_brand_layout = false;
public static String _dir_root_image_file_brand = "";
public static String _dir_root_image_file_cat = "";
public dmax.dialog.Spotsd _progress_spot = null;
public wir.hitex.recycler.Hitex_Picasso _piccaso = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _error_image = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _progress_image = null;
public hpksoftware.setak.customlistview _sv_cat = null;
public hpksoftware.setak.customlistview _sv_brand = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_main = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_cat = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_cat = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_brand = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_brand = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.collections.List _list_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_badge = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
public hpksoftware.setak.main _main = null;
public hpksoftware.setak.starter _starter = null;
public hpksoftware.setak.insert_noskhe _insert_noskhe = null;
public hpksoftware.setak.show_noskhe _show_noskhe = null;
public hpksoftware.setak.alarm_service _alarm_service = null;
public hpksoftware.setak.rezerv _rezerv = null;
public hpksoftware.setak.order _order = null;
public hpksoftware.setak.library _library = null;
public hpksoftware.setak.show_list_kala _show_list_kala = null;
public hpksoftware.setak.project_help _project_help = null;
public hpksoftware.setak.reg_login _reg_login = null;
public hpksoftware.setak.sabad _sabad = null;
public hpksoftware.setak.history_order _history_order = null;
public hpksoftware.setak.pushejsonservice _pushejsonservice = null;
public hpksoftware.setak.shared_admin _shared_admin = null;
public hpksoftware.setak.update_users _update_users = null;
public hpksoftware.setak.info_kala _info_kala = null;
public hpksoftware.setak.update _update = null;
public hpksoftware.setak.mylibrary _mylibrary = null;
public hpksoftware.setak.pushservice _pushservice = null;
public hpksoftware.setak.function _function = null;
public hpksoftware.setak.show_nazar _show_nazar = null;
public hpksoftware.setak.nazar _nazar = null;
public hpksoftware.setak.push_active _push_active = null;
public hpksoftware.setak.result _result = null;
public hpksoftware.setak.disconnect _disconnect = null;
public hpksoftware.setak.changefontbylabelsize _changefontbylabelsize = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_header = null;
 //BA.debugLineNum = 38;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 41;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,catgory.getObject());
 //BA.debugLineNum = 43;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 46;BA.debugLine="error_image.Initialize(LoadBitmap(File.DirAssets";
mostCurrent._error_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.png").getObject()));
 //BA.debugLineNum = 47;BA.debugLine="progress_image.Initialize(LoadBitmap(File.DirAss";
mostCurrent._progress_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prog_img.png").getObject()));
 //BA.debugLineNum = 49;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 50;BA.debugLine="Activity.LoadLayout(\"category\")";
mostCurrent._activity.LoadLayout("category",mostCurrent.activityBA);
 //BA.debugLineNum = 51;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 52;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 53;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 55;BA.debugLine="If bool_brand_layout=False Then";
if (_bool_brand_layout==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 56;BA.debugLine="sv_cat.Initialize(Me,\"lsv_cat\",0,5dip)";
mostCurrent._sv_cat._initialize(mostCurrent.activityBA,catgory.getObject(),"lsv_cat",(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 57;BA.debugLine="panel_main.AddView(sv_cat.AsView,0,0,panel_main.";
mostCurrent._panel_main.AddView((android.view.View)(mostCurrent._sv_cat._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_main.getWidth(),mostCurrent._panel_main.getHeight());
 //BA.debugLineNum = 58;BA.debugLine="get_cat";
_get_cat();
 }else if(_bool_brand_layout==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 60;BA.debugLine="sv_brand.Initialize(Me,\"lsv_brand\",0,2dip)";
mostCurrent._sv_brand._initialize(mostCurrent.activityBA,catgory.getObject(),"lsv_brand",(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)));
 //BA.debugLineNum = 61;BA.debugLine="panel_main.AddView(sv_brand.AsView,0,0,panel_mai";
mostCurrent._panel_main.AddView((android.view.View)(mostCurrent._sv_brand._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_main.getWidth(),mostCurrent._panel_main.getHeight());
 };
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 207;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 208;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 209;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 210;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 211;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 67;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,catgory.getObject());
 //BA.debugLineNum = 68;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_sa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 69;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 70;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.f";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 71;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 //BA.debugLineNum = 72;BA.debugLine="If list_kala.Size<=0 Then lbl_badge.Visible=Fals";
if (mostCurrent._list_kala.getSize()<=0) { 
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);};
 }else {
 //BA.debugLineNum = 74;BA.debugLine="lbl_badge.Visible=False";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 204;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 205;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _btn_sabad_click() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub btn_sabad_Click()";
 //BA.debugLineNum = 191;BA.debugLine="StartActivity(sabad)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._sabad.getObject()));
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _btn_search_click() throws Exception{
 //BA.debugLineNum = 199;BA.debugLine="Sub btn_search_Click";
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_click() throws Exception{
anywheresoftware.b4a.ariagplib.ARIAlib _share = null;
 //BA.debugLineNum = 194;BA.debugLine="Sub btn_share_Click";
 //BA.debugLineNum = 195;BA.debugLine="Dim share As AriaLib";
_share = new anywheresoftware.b4a.ariagplib.ARIAlib();
 //BA.debugLineNum = 196;BA.debugLine="StartActivity(share.ShareApplication(Application.";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share.ShareApplication(anywheresoftware.b4a.keywords.Common.Application.getPackageName(),anywheresoftware.b4a.keywords.Common.Application.getLabelName())));
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
String _url_pic = "";
 //BA.debugLineNum = 83;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 84;BA.debugLine="Try";
try { //BA.debugLineNum = 85;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_cat"),(Object)("get_brand"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 88;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 89;BA.debugLine="For i=0 To records.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 90;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 91;BA.debugLine="Dim p1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 92;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 93;BA.debugLine="p1.LoadLayout(\"sv_catgory\")";
_p1.LoadLayout("sv_catgory",mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="sv_cat.Add(p1,Panel1.Height,map1.Get(\"id\") &";
mostCurrent._sv_cat._add(_p1,mostCurrent._panel1.getHeight(),(Object)(BA.ObjectToString(_map1.Get((Object)("id")))+";"+BA.ObjectToString(_map1.Get((Object)("parent_id")))+";"+BA.ObjectToString(_map1.Get((Object)("name_cat")))));
 //BA.debugLineNum = 96;BA.debugLine="lbl_cat.Text=map1.Get(\"name_cat\")";
mostCurrent._lbl_cat.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name_cat"))));
 //BA.debugLineNum = 97;BA.debugLine="lbl_cat.Typeface=Starter.font_body";
mostCurrent._lbl_cat.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 100;BA.debugLine="Dim url_pic As String=map1.Get(\"pic_cat\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic_cat")));
 //BA.debugLineNum = 101;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Th";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 102;BA.debugLine="url_pic=dir_root_image_file_cat & map1.Get(";
_url_pic = mostCurrent._dir_root_image_file_cat+BA.ObjectToString(_map1.Get((Object)("pic_cat")));
 };
 //BA.debugLineNum = 106;BA.debugLine="piccaso.Load(\"http\",url_pic).Resize(img_cat.";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).Resize(mostCurrent._img_cat.getWidth(),mostCurrent._img_cat.getHeight()).SkipMemoryCache().Into(mostCurrent._img_cat);
 }
};
 };
 //BA.debugLineNum = 109;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 1: {
 //BA.debugLineNum = 113;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 114;BA.debugLine="For i=0 To records.Size-1";
{
final int step23 = 1;
final int limit23 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23); _i = ((int)(0 + _i + step23)) ) {
 //BA.debugLineNum = 115;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 116;BA.debugLine="Dim p1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 117;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 118;BA.debugLine="p1.LoadLayout(\"sv_brand\")";
_p1.LoadLayout("sv_brand",mostCurrent.activityBA);
 //BA.debugLineNum = 119;BA.debugLine="sv_brand.Add(p1,Panel2.Height,map1.Get(\"id\")";
mostCurrent._sv_brand._add(_p1,mostCurrent._panel2.getHeight(),(Object)(BA.ObjectToString(_map1.Get((Object)("id")))+";"+BA.ObjectToString(_map1.Get((Object)("name_brand")))));
 //BA.debugLineNum = 121;BA.debugLine="lbl_brand.Text=map1.Get(\"name_brand\")";
mostCurrent._lbl_brand.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name_brand"))));
 //BA.debugLineNum = 122;BA.debugLine="lbl_brand.Typeface=Starter.font_body";
mostCurrent._lbl_brand.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 125;BA.debugLine="Dim url_pic As String=map1.Get(\"logo_brand\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("logo_brand")));
 //BA.debugLineNum = 126;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Th";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 127;BA.debugLine="url_pic=dir_root_image_file_brand & map1.Ge";
_url_pic = mostCurrent._dir_root_image_file_brand+BA.ObjectToString(_map1.Get((Object)("logo_brand")));
 };
 //BA.debugLineNum = 130;BA.debugLine="piccaso.Load(\"http\",url_pic).Resize(img_bran";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).Resize(mostCurrent._img_brand.getWidth(),mostCurrent._img_brand.getHeight()).SkipMemoryCache().Into(mostCurrent._img_brand);
 }
};
 };
 //BA.debugLineNum = 133;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 2: {
 //BA.debugLineNum = 136;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 137;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 139;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 140;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 141;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 143;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 144;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e50) {
			processBA.setLastException(e50); //BA.debugLineNum = 146;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 147;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 149;BA.debugLine="End Sub";
return "";
}
public static String  _get_brand() throws Exception{
 //BA.debugLineNum = 183;BA.debugLine="Sub get_brand";
 //BA.debugLineNum = 184;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 185;BA.debugLine="connector.send_query($\"SELECT * FROM `brand` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `brand` where `show_brand`=1 ORDER BY `id` ASC"),"",(Object)(""));
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return "";
}
public static String  _get_cat() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub get_cat";
 //BA.debugLineNum = 180;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 181;BA.debugLine="connector.send_query($\"SELECT * FROM `category` w";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `category` where `parent_id`=0 and `show_cat`=1 ORDER BY `id` ASC"),"get_cat",(Object)(""));
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Dim dir_root_image_file_brand As String=Starter.d";
mostCurrent._dir_root_image_file_brand = mostCurrent._starter._dir_root_image_file_brand;
 //BA.debugLineNum = 15;BA.debugLine="Dim dir_root_image_file_cat As String=Starter.dir";
mostCurrent._dir_root_image_file_cat = mostCurrent._starter._dir_root_image_file_cat;
 //BA.debugLineNum = 17;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 18;BA.debugLine="Dim piccaso As Hitex_Picasso";
mostCurrent._piccaso = new wir.hitex.recycler.Hitex_Picasso();
 //BA.debugLineNum = 19;BA.debugLine="Dim error_image,progress_image As BitmapDrawable";
mostCurrent._error_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
mostCurrent._progress_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 21;BA.debugLine="Dim sv_cat,sv_brand As CustomListView";
mostCurrent._sv_cat = new hpksoftware.setak.customlistview();
mostCurrent._sv_brand = new hpksoftware.setak.customlistview();
 //BA.debugLineNum = 23;BA.debugLine="Private Panel1 As Panel 'header page";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private panel_main As Panel";
mostCurrent._panel_main = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private lbl_cat As Label";
mostCurrent._lbl_cat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private img_cat As ImageView";
mostCurrent._img_cat = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lbl_brand As Label";
mostCurrent._lbl_brand = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private img_brand As ImageView";
mostCurrent._img_brand = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim list_kala As List";
mostCurrent._list_kala = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 34;BA.debugLine="Private lbl_badge As Label";
mostCurrent._lbl_badge = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _lsv_brand_itemclick(int _position,Object _value) throws Exception{
String[] _arr_temp = null;
 //BA.debugLineNum = 165;BA.debugLine="Sub lsv_brand_ItemClick(Position As Int, Value As";
 //BA.debugLineNum = 166;BA.debugLine="Dim arr_temp() As String";
_arr_temp = new String[(int) (0)];
java.util.Arrays.fill(_arr_temp,"");
 //BA.debugLineNum = 167;BA.debugLine="arr_temp=Regex.Split(\";\",Value)";
_arr_temp = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_value));
 //BA.debugLineNum = 168;BA.debugLine="show_list_kala.brand_name=arr_temp(1)'نام برند";
mostCurrent._show_list_kala._brand_name = _arr_temp[(int) (1)];
 //BA.debugLineNum = 169;BA.debugLine="show_list_kala.bool_brand_layout=True";
mostCurrent._show_list_kala._bool_brand_layout = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 170;BA.debugLine="show_list_kala.bool_cat=False";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 171;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 172;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 173;BA.debugLine="show_list_kala.code_brand=arr_temp(0)'کد برند";
mostCurrent._show_list_kala._code_brand = (int)(Double.parseDouble(_arr_temp[(int) (0)]));
 //BA.debugLineNum = 174;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _lsv_cat_itemclick(int _position,Object _value) throws Exception{
String[] _arr_temp = null;
 //BA.debugLineNum = 153;BA.debugLine="Sub lsv_cat_ItemClick(Position As Int, Value As Ob";
 //BA.debugLineNum = 154;BA.debugLine="Dim arr_temp() As String";
_arr_temp = new String[(int) (0)];
java.util.Arrays.fill(_arr_temp,"");
 //BA.debugLineNum = 155;BA.debugLine="arr_temp=Regex.Split(\";\",Value)";
_arr_temp = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_value));
 //BA.debugLineNum = 156;BA.debugLine="show_list_kala.cat_name=arr_temp(2)'نام گروه";
mostCurrent._show_list_kala._cat_name = _arr_temp[(int) (2)];
 //BA.debugLineNum = 157;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 158;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 159;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 160;BA.debugLine="show_list_kala.code_cat=arr_temp(0)'کد گروه";
mostCurrent._show_list_kala._code_cat = (int)(Double.parseDouble(_arr_temp[(int) (0)]));
 //BA.debugLineNum = 161;BA.debugLine="show_list_kala.code_sub_cat=arr_temp(1)'کد زیر گر";
mostCurrent._show_list_kala._code_sub_cat = (int)(Double.parseDouble(_arr_temp[(int) (1)]));
 //BA.debugLineNum = 162;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 163;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="Public bool_brand_layout As Boolean=False";
_bool_brand_layout = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
}
