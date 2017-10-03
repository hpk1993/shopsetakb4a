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

public class show_list_kala extends Activity implements B4AActivity{
	public static show_list_kala mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.show_list_kala");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (show_list_kala).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.show_list_kala");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.show_list_kala", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (show_list_kala) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (show_list_kala) Resume **");
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
		return show_list_kala.class;
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
        BA.LogInfo("** Activity (show_list_kala) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (show_list_kala) Resume **");
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
public static int _code_cat = 0;
public static int _code_sub_cat = 0;
public static int _code_brand = 0;
public static String _brand_name = "";
public static String _cat_name = "";
public static boolean _bool_brand_layout = false;
public static boolean _bool_cat = false;
public static boolean _bool_porfroush = false;
public static boolean _bool_news_list = false;
public static String _dir_root_image_file = "";
public static boolean _bool_search = false;
public static String _query_search = "";
public dmax.dialog.Spotsd _progress_spot = null;
public hpksoftware.setak.customlistview _sv_list_cat = null;
public hpksoftware.setak.customlistview _sv_list_brand = null;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_main = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_info = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name_kala = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_price_off = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_price = null;
public b4a.example.money _toman = null;
public wir.hitex.recycler.Hitex_Picasso _piccaso = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _error_image = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _progress_image = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public static int _count_rows = 0;
public static boolean _bo_load_rows = false;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_mark_viesue = null;
public anywheresoftware.b4a.objects.collections.List _list_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_badge = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_exist = null;
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
public hpksoftware.setak.catgory _catgory = null;
public hpksoftware.setak.library _library = null;
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
 //BA.debugLineNum = 49;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 50;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,show_list_kala.getObject());
 //BA.debugLineNum = 51;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 54;BA.debugLine="error_image.Initialize(LoadBitmap(File.DirAssets";
mostCurrent._error_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.png").getObject()));
 //BA.debugLineNum = 55;BA.debugLine="progress_image.Initialize(LoadBitmap(File.DirAss";
mostCurrent._progress_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prog_img.png").getObject()));
 //BA.debugLineNum = 57;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 58;BA.debugLine="dir_root_image_file=Starter.dir_root_image_file_m";
_dir_root_image_file = mostCurrent._starter._dir_root_image_file_main;
 //BA.debugLineNum = 60;BA.debugLine="Activity.LoadLayout(\"category\")";
mostCurrent._activity.LoadLayout("category",mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 62;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 63;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="sv_list_cat.Initialize(Me,\"lsv_list_cat\",0xFFEFEB";
mostCurrent._sv_list_cat._initialize(mostCurrent.activityBA,show_list_kala.getObject(),"lsv_list_cat",(int) (0xffefebeb),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)));
 //BA.debugLineNum = 66;BA.debugLine="panel_main.AddView(sv_list_cat.AsView,0,0,panel_m";
mostCurrent._panel_main.AddView((android.view.View)(mostCurrent._sv_list_cat._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_main.getWidth(),mostCurrent._panel_main.getHeight());
 //BA.debugLineNum = 71;BA.debugLine="If bool_search =True Then";
if (_bool_search==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 72;BA.debugLine="If query_search.Length > 4 Then";
if (_query_search.length()>4) { 
 //BA.debugLineNum = 73;BA.debugLine="result_query_search(query_search)";
_result_query_search(_query_search);
 }else {
 //BA.debugLineNum = 75;BA.debugLine="ToastMessageShow(\"عبارت مورد جستجو کوتاه است\",T";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("عبارت مورد جستجو کوتاه است"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 76;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else if(_bool_porfroush==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 79;BA.debugLine="get_porfroush";
_get_porfroush();
 }else if(_bool_news_list==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 81;BA.debugLine="get_news";
_get_news();
 }else if(_bool_cat==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 83;BA.debugLine="get_count(code_cat)";
_get_count(_code_cat);
 }else if(_bool_brand_layout==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 85;BA.debugLine="get_count_brand(code_brand)";
_get_count_brand(_code_brand);
 };
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 342;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 343;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 344;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 345;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 346;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 348;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 97;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,show_list_kala.getObject());
 //BA.debugLineNum = 98;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_sa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 99;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 100;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.f";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 101;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 //BA.debugLineNum = 102;BA.debugLine="If list_kala.Size<=0 Then lbl_badge.Visible=Fals";
if (mostCurrent._list_kala.getSize()<=0) { 
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);};
 }else {
 //BA.debugLineNum = 104;BA.debugLine="lbl_badge.Visible=False";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 310;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 311;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 312;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _btn_close_click() throws Exception{
 //BA.debugLineNum = 338;BA.debugLine="Sub btn_close_Click";
 //BA.debugLineNum = 339;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 340;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 341;BA.debugLine="End Sub";
return "";
}
public static String  _btn_sabad_click() throws Exception{
 //BA.debugLineNum = 315;BA.debugLine="Sub btn_sabad_Click()";
 //BA.debugLineNum = 316;BA.debugLine="StartActivity(sabad)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._sabad.getObject()));
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return "";
}
public static String  _btn_search_click() throws Exception{
 //BA.debugLineNum = 324;BA.debugLine="Sub btn_search_Click";
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_click() throws Exception{
anywheresoftware.b4a.ariagplib.ARIAlib _share = null;
 //BA.debugLineNum = 319;BA.debugLine="Sub btn_share_Click";
 //BA.debugLineNum = 320;BA.debugLine="Dim share As AriaLib";
_share = new anywheresoftware.b4a.ariagplib.ARIAlib();
 //BA.debugLineNum = 321;BA.debugLine="StartActivity(share.ShareApplication(Application.";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share.ShareApplication(anywheresoftware.b4a.keywords.Common.Application.getPackageName(),anywheresoftware.b4a.keywords.Common.Application.getLabelName())));
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
de.amberhome.objects.appcompat.AppCompatBase _apc = null;
String _info = "";
hpk.software.label.line_height _line_h = null;
int _number_kala = 0;
int _stut_viesue = 0;
anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _strick = null;
String _price_off_strick = "";
String _url_pic = "";
 //BA.debugLineNum = 113;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 114;BA.debugLine="Try";
try { //BA.debugLineNum = 115;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_list_cat"),(Object)("get_porfroush"),(Object)("get_count"),(Object)("get_count_brand"),(Object)("disconnect"),(Object)("error"))) {
case 0: 
case 1: {
 //BA.debugLineNum = 118;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 119;BA.debugLine="For i=0 To records.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 120;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 122;BA.debugLine="Dim p1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 123;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 124;BA.debugLine="p1.LoadLayout(\"sv_list_catgory\")";
_p1.LoadLayout("sv_list_catgory",mostCurrent.activityBA);
 //BA.debugLineNum = 125;BA.debugLine="sv_list_cat.Add(p1,Panel1.Height,map1.Get(\"i";
mostCurrent._sv_list_cat._add(_p1,mostCurrent._panel1.getHeight(),_map1.Get((Object)("id")));
 //BA.debugLineNum = 126;BA.debugLine="Dim apc As AppCompat";
_apc = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 127;BA.debugLine="apc.SetElevation( Panel1,6)";
_apc.SetElevation((android.view.View)(mostCurrent._panel1.getObject()),(float) (6));
 //BA.debugLineNum = 128;BA.debugLine="lbl_name_kala.Text=map1.Get(\"name_kala\")";
mostCurrent._lbl_name_kala.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name_kala"))));
 //BA.debugLineNum = 129;BA.debugLine="lbl_name_kala.TextSize=13";
mostCurrent._lbl_name_kala.setTextSize((float) (13));
 //BA.debugLineNum = 130;BA.debugLine="Dim info As String=map1.Get(\"info\")";
_info = BA.ObjectToString(_map1.Get((Object)("info")));
 //BA.debugLineNum = 131;BA.debugLine="If info.Length > 45 Then";
if (_info.length()>45) { 
 //BA.debugLineNum = 132;BA.debugLine="lbl_info.Text=info.SubString2(0,45) & \"...\"";
mostCurrent._lbl_info.setText(BA.ObjectToCharSequence(_info.substring((int) (0),(int) (45))+"..."));
 }else {
 //BA.debugLineNum = 134;BA.debugLine="lbl_info.Text=info";
mostCurrent._lbl_info.setText(BA.ObjectToCharSequence(_info));
 };
 //BA.debugLineNum = 138;BA.debugLine="Dim line_h As line_height";
_line_h = new hpk.software.label.line_height();
 //BA.debugLineNum = 139;BA.debugLine="lbl_info.TextSize=10";
mostCurrent._lbl_info.setTextSize((float) (10));
 //BA.debugLineNum = 140;BA.debugLine="line_h.Initialize(lbl_info,.75)";
_line_h._initialize(processBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._lbl_info.getObject())),(float) (.75));
 //BA.debugLineNum = 142;BA.debugLine="Dim number_kala As Int=map1.Get(\"number\")";
_number_kala = (int)(BA.ObjectToNumber(_map1.Get((Object)("number"))));
 //BA.debugLineNum = 143;BA.debugLine="If number_kala <=0 Then img_exist.Visible=Tr";
if (_number_kala<=0) { 
mostCurrent._img_exist.setVisible(anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 145;BA.debugLine="Dim stut_viesue As Int=map1.Get(\"stut_viesue";
_stut_viesue = (int)(BA.ObjectToNumber(_map1.Get((Object)("stut_viesue"))));
 //BA.debugLineNum = 146;BA.debugLine="If stut_viesue=1 Then";
if (_stut_viesue==1) { 
 //BA.debugLineNum = 147;BA.debugLine="img_mark_viesue.Visible=True";
mostCurrent._img_mark_viesue.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 148;BA.debugLine="Dim strick As RichString";
_strick = new anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString();
 //BA.debugLineNum = 149;BA.debugLine="Dim price_off_strick As String=toman.number";
_price_off_strick = mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان";
 //BA.debugLineNum = 150;BA.debugLine="strick.Initialize(price_off_strick)";
_strick.Initialize(BA.ObjectToCharSequence(_price_off_strick));
 //BA.debugLineNum = 151;BA.debugLine="strick.Strikethrough(0,price_off_strick.Len";
_strick.Strikethrough((int) (0),_price_off_strick.length());
 //BA.debugLineNum = 152;BA.debugLine="lbl_price_off.Text=strick";
mostCurrent._lbl_price_off.setText(BA.ObjectToCharSequence(_strick.getObject()));
 //BA.debugLineNum = 153;BA.debugLine="lbl_price.Text=toman.number(map1.Get(\"price";
mostCurrent._lbl_price.setText(BA.ObjectToCharSequence(mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price_off"))))+" تومان"));
 }else {
 //BA.debugLineNum = 155;BA.debugLine="img_mark_viesue.Visible=False";
mostCurrent._img_mark_viesue.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 156;BA.debugLine="lbl_price_off.Text=\"\"";
mostCurrent._lbl_price_off.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 157;BA.debugLine="lbl_price.Text=toman.number(map1.Get(\"price";
mostCurrent._lbl_price.setText(BA.ObjectToCharSequence(mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان"));
 };
 //BA.debugLineNum = 160;BA.debugLine="lbl_price_off.TextSize=10";
mostCurrent._lbl_price_off.setTextSize((float) (10));
 //BA.debugLineNum = 161;BA.debugLine="lbl_price.TextSize=10";
mostCurrent._lbl_price.setTextSize((float) (10));
 //BA.debugLineNum = 164;BA.debugLine="Dim url_pic As String=map1.Get(\"pic\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 165;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Th";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 166;BA.debugLine="url_pic=dir_root_image_file & map1.Get(\"pic";
_url_pic = _dir_root_image_file+BA.ObjectToString(_map1.Get((Object)("pic")));
 };
 //BA.debugLineNum = 171;BA.debugLine="piccaso.Load(\"http\",url_pic).Resize(img_kala";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).Resize(mostCurrent._img_kala.getWidth(),mostCurrent._img_kala.getHeight()).SkipMemoryCache().Into(mostCurrent._img_kala);
 //BA.debugLineNum = 172;BA.debugLine="If bool_brand_layout=True Then";
if (_bool_brand_layout==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 173;BA.debugLine="Label3.Text=brand_name";
mostCurrent._label3.setText(BA.ObjectToCharSequence(_brand_name));
 }else {
 //BA.debugLineNum = 175;BA.debugLine="Label3.Text=cat_name";
mostCurrent._label3.setText(BA.ObjectToCharSequence(_cat_name));
 };
 }
};
 //BA.debugLineNum = 178;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 179;BA.debugLine="font_label";
_font_label();
 //BA.debugLineNum = 180;BA.debugLine="bo_load_rows=True";
_bo_load_rows = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 182;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 184;BA.debugLine="toast.Initialize(\"محصولی درج نشده است\",toast.";
mostCurrent._toast.Initialize(mostCurrent.activityBA,"محصولی درج نشده است",mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.ERROR);
 //BA.debugLineNum = 185;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 break; }
case 2: {
 //BA.debugLineNum = 189;BA.debugLine="count_rows=records.Size";
_count_rows = _records.getSize();
 //BA.debugLineNum = 190;BA.debugLine="get_list_cat(code_cat,0,10)";
_get_list_cat(_code_cat,(int) (0),(int) (10));
 break; }
case 3: {
 //BA.debugLineNum = 194;BA.debugLine="count_rows=records.Size";
_count_rows = _records.getSize();
 //BA.debugLineNum = 195;BA.debugLine="get_list_brand(code_brand,0,10)";
_get_list_brand(_code_brand,(int) (0),(int) (10));
 break; }
case 4: {
 //BA.debugLineNum = 200;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 201;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 5: {
 //BA.debugLineNum = 203;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 204;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 205;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 207;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 208;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e78) {
			processBA.setLastException(e78); //BA.debugLineNum = 210;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 211;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static String  _font_label() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _view1 = null;
anywheresoftware.b4a.objects.LabelWrapper _label_font = null;
 //BA.debugLineNum = 328;BA.debugLine="Sub font_label";
 //BA.debugLineNum = 329;BA.debugLine="For Each view1 As View In Activity.GetAllViewsRec";
_view1 = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group1 = mostCurrent._activity.GetAllViewsRecursive();
final int groupLen1 = group1.getSize();
for (int index1 = 0;index1 < groupLen1 ;index1++){
_view1.setObject((android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 330;BA.debugLine="If view1 Is Label Then";
if (_view1.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 331;BA.debugLine="Dim label_font As Label";
_label_font = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 332;BA.debugLine="label_font=view1";
_label_font.setObject((android.widget.TextView)(_view1.getObject()));
 //BA.debugLineNum = 333;BA.debugLine="label_font.Typeface=Starter.font_body";
_label_font.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 };
 }
;
 //BA.debugLineNum = 336;BA.debugLine="End Sub";
return "";
}
public static String  _get_count(int _code) throws Exception{
 //BA.debugLineNum = 276;BA.debugLine="Sub get_count(code As Int)";
 //BA.debugLineNum = 277;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 278;BA.debugLine="connector.send_query($\"SELECT * FROM `kala` where";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `kala` where `code_cat`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+" and `show_kala`=1 ORDER BY `id`"),"get_count",(Object)(""));
 //BA.debugLineNum = 279;BA.debugLine="End Sub";
return "";
}
public static String  _get_count_brand(int _code) throws Exception{
 //BA.debugLineNum = 293;BA.debugLine="Sub get_count_brand(code As Int)";
 //BA.debugLineNum = 294;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 295;BA.debugLine="connector.send_query($\"SELECT * FROM `kala` where";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `kala` where `code_brand`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+" and `show_kala`=1 ORDER BY `code_brand`"),"get_count_brand",(Object)(""));
 //BA.debugLineNum = 296;BA.debugLine="End Sub";
return "";
}
public static String  _get_list_brand(int _code,int _begin,int _ends) throws Exception{
 //BA.debugLineNum = 264;BA.debugLine="Sub get_list_brand(code As Int,begin As Int,ends A";
 //BA.debugLineNum = 265;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 266;BA.debugLine="connector.send_query($\" SELECT `kala` . * ,  `vie";
mostCurrent._connector._send_query(mostCurrent.activityBA,("\n"+"SELECT `kala` . * ,  `viesue`.`price_off` ,  `viesue`.`code_kala`\n"+"FROM `kala`\n"+"LEFT JOIN `viesue`\n"+"ON `kala`.`id`=`viesue`.`code_kala`\n"+"where `code_brand`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+" and `show_kala`=1  ORDER BY `kala`.`number` desc LIMIT "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_begin))+" , "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ends))+" "),"get_list_cat",(Object)(""));
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static String  _get_list_cat(int _code,int _begin,int _ends) throws Exception{
 //BA.debugLineNum = 240;BA.debugLine="Sub get_list_cat(code As Int,begin As Int,ends As";
 //BA.debugLineNum = 241;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 242;BA.debugLine="connector.send_query($\" SELECT `kala` . * ,  `vie";
mostCurrent._connector._send_query(mostCurrent.activityBA,("\n"+"SELECT `kala` . * ,  `viesue`.`price_off` ,  `viesue`.`code_kala` \n"+"FROM  `kala` \n"+"LEFT JOIN `viesue`\n"+"ON `kala`.`id`=`viesue`.`code_kala`\n"+"WHERE  `code_cat` \n"+"IN (\n"+"\n"+"SELECT  `category`.`id` \n"+"FROM  `category` \n"+"WHERE (\n"+"`category`.`id` ="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+"\n"+"AND  `category`.`parent_id` =0\n"+")\n"+"OR (\n"+"`category`.`parent_id` ="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+"\n"+")\n"+")\n"+"and `show_kala`=1 ORDER BY  `kala`.`number` DESC LIMIT "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_begin))+" , "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_ends))+" "),"get_list_cat",(Object)(""));
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
return "";
}
public static String  _get_news() throws Exception{
 //BA.debugLineNum = 298;BA.debugLine="Sub get_news";
 //BA.debugLineNum = 299;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 300;BA.debugLine="connector.send_query($\"Select `kala`.*,`viesue`.`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("Select `kala`.*,`viesue`.`price_off`,`viesue`.`code_kala` FROM `kala` LEFT JOIN `viesue` ON `kala`.`id`=`viesue`.`code_kala` where `show_kala`=1 order BY `kala`.`id` desc LIMIT 0 , 12 "),"get_list_cat",(Object)(""));
 //BA.debugLineNum = 301;BA.debugLine="End Sub";
return "";
}
public static String  _get_porfroush() throws Exception{
 //BA.debugLineNum = 281;BA.debugLine="Sub get_porfroush";
 //BA.debugLineNum = 282;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 283;BA.debugLine="connector.send_query($\" SELECT DISTINCT `kala`.*,";
mostCurrent._connector._send_query(mostCurrent.activityBA,("\n"+"SELECT DISTINCT `kala`.*,`por_froush`.`code_kala` as پرفروش,`viesue`.`code_kala`,`viesue`.`price_off`\n"+"FROM  `kala` \n"+"INNER JOIN  `por_froush` ON  `kala`.`id` =  `por_froush`.`code_kala` \n"+"LEFT JOIN  `viesue` ON  `kala`.`id` =  `viesue`.`code_kala`\n"+"where `kala`.`id` =  `por_froush`.`code_kala` and `show_kala`=1\n"+"ORDER BY  `kala`.`id` DESC \n"+"LIMIT 0 , 15"),("get_porfroush"),(Object)(""));
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 23;BA.debugLine="Dim sv_list_cat,sv_list_brand As CustomListView";
mostCurrent._sv_list_cat = new hpksoftware.setak.customlistview();
mostCurrent._sv_list_brand = new hpksoftware.setak.customlistview();
 //BA.debugLineNum = 24;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 25;BA.debugLine="Private panel_main As Panel";
mostCurrent._panel_main = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private lbl_info As Label";
mostCurrent._lbl_info = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lbl_name_kala As Label";
mostCurrent._lbl_name_kala = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private img_kala As ImageView";
mostCurrent._img_kala = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lbl_price_off As Label";
mostCurrent._lbl_price_off = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lbl_price As Label";
mostCurrent._lbl_price = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim toman As money";
mostCurrent._toman = new b4a.example.money();
 //BA.debugLineNum = 33;BA.debugLine="Dim piccaso As Hitex_Picasso";
mostCurrent._piccaso = new wir.hitex.recycler.Hitex_Picasso();
 //BA.debugLineNum = 34;BA.debugLine="Dim error_image,progress_image As BitmapDrawable";
mostCurrent._error_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
mostCurrent._progress_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 35;BA.debugLine="Private Label3 As Label ' titlebar";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim count_rows As Int";
_count_rows = 0;
 //BA.debugLineNum = 38;BA.debugLine="Dim bo_load_rows As Boolean=True";
_bo_load_rows = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 39;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private img_mark_viesue As ImageView";
mostCurrent._img_mark_viesue = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim list_kala As List";
mostCurrent._list_kala = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 43;BA.debugLine="Private lbl_badge As Label";
mostCurrent._lbl_badge = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private img_exist As ImageView";
mostCurrent._img_exist = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _lsv_list_cat_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Sub lsv_list_cat_ItemClick(Position As Int, Value";
 //BA.debugLineNum = 222;BA.debugLine="info_kala.code_kala=Value";
mostCurrent._info_kala._code_kala = (int)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 223;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _lsv_list_cat_position(int _position) throws Exception{
 //BA.debugLineNum = 226;BA.debugLine="Sub lsv_list_cat_Position(Position As Int)";
 //BA.debugLineNum = 227;BA.debugLine="If Position + sv_list_cat.Height >= sv_list_cat.";
if (_position+mostCurrent._sv_list_cat._getheight()>=(double)(Double.parseDouble(mostCurrent._sv_list_cat._getpanelheight()))) { 
 //BA.debugLineNum = 229;BA.debugLine="If sv_list_cat.GetSize < count_rows And bo_load";
if (mostCurrent._sv_list_cat._getsize()<_count_rows && _bo_load_rows==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 230;BA.debugLine="bo_load_rows=False";
_bo_load_rows = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 231;BA.debugLine="get_list_cat(code_cat,sv_list_cat.GetSize,sv_";
_get_list_cat(_code_cat,mostCurrent._sv_list_cat._getsize(),(int) (mostCurrent._sv_list_cat._getsize()+3));
 };
 };
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="Public code_cat,code_sub_cat,code_brand As Int";
_code_cat = 0;
_code_sub_cat = 0;
_code_brand = 0;
 //BA.debugLineNum = 9;BA.debugLine="Public brand_name,cat_name As String";
_brand_name = "";
_cat_name = "";
 //BA.debugLineNum = 10;BA.debugLine="Public bool_brand_layout,bool_cat As Boolean=Fals";
_bool_brand_layout = false;
_bool_cat = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 11;BA.debugLine="Public bool_porfroush,bool_news_list As Boolean=F";
_bool_porfroush = false;
_bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 12;BA.debugLine="Public dir_root_image_file As String";
_dir_root_image_file = "";
 //BA.debugLineNum = 14;BA.debugLine="Public bool_search As Boolean=False";
_bool_search = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 15;BA.debugLine="Public query_search As String";
_query_search = "";
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _reg_click() throws Exception{
 //BA.debugLineNum = 350;BA.debugLine="Sub reg_Click";
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return "";
}
public static String  _result_query_search(String _q) throws Exception{
 //BA.debugLineNum = 303;BA.debugLine="Sub result_query_search(q As String)";
 //BA.debugLineNum = 304;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 305;BA.debugLine="connector.send_query($\"SELECT * FROM `kala` where";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `kala` where (`name_kala` like '% "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_q))+" %' and `kala`.`number` > 0) OR (`info` like '% "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_q))+" %' and `kala`.`number` > 0) and (`show_kala`=1) ORDER BY `id` DESC"),"get_list_cat",(Object)(""));
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return "";
}
}
