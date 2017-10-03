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

public class show_nazar extends Activity implements B4AActivity{
	public static show_nazar mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.show_nazar");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (show_nazar).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.show_nazar");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.show_nazar", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (show_nazar) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (show_nazar) Resume **");
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
		return show_nazar.class;
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
        BA.LogInfo("** Activity (show_nazar) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (show_nazar) Resume **");
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
public static int _code_kala = 0;
public static boolean _bool_propertis = false;
public hpksoftware.setak.customlistview _sv = null;
public dmax.dialog.Spotsd _progress_spot = null;
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_main = null;
public anywheresoftware.b4a.objects.LabelWrapper _sv_lbl_msg = null;
public anywheresoftware.b4a.objects.PanelWrapper _sv_panel_text = null;
public anywheresoftware.b4a.objects.PanelWrapper _sv_panel = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _myfont = null;
public anywheresoftware.b4a.objects.LabelWrapper _sv_pro_value = null;
public anywheresoftware.b4a.objects.PanelWrapper _sv_pro_panel = null;
public anywheresoftware.b4a.objects.LabelWrapper _sv_pro_key = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
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
 //BA.debugLineNum = 29;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"show_nazar\")";
mostCurrent._activity.LoadLayout("show_nazar",mostCurrent.activityBA);
 //BA.debugLineNum = 31;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 32;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 33;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 35;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,show_nazar.getObject());
 //BA.debugLineNum = 37;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 38;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,show_nazar.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff13879a),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 40;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 41;BA.debugLine="sv.Initialize(Me,\"sv1\",0xffE0DDDD,8dip)";
mostCurrent._sv._initialize(mostCurrent.activityBA,show_nazar.getObject(),"sv1",(int) (0xffe0dddd),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)));
 //BA.debugLineNum = 42;BA.debugLine="panel_main.AddView(sv.AsView,0,0,panel_main.Width";
mostCurrent._panel_main.AddView((android.view.View)(mostCurrent._sv._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_main.getWidth(),mostCurrent._panel_main.getHeight());
 //BA.debugLineNum = 45;BA.debugLine="myfont=Starter.font_body";
mostCurrent._myfont = mostCurrent._starter._font_body;
 //BA.debugLineNum = 47;BA.debugLine="If bool_propertis=True Then";
if (_bool_propertis==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 48;BA.debugLine="get_propertis";
_get_propertis();
 }else {
 //BA.debugLineNum = 50;BA.debugLine="get_nazar";
_get_nazar();
 };
 //BA.debugLineNum = 53;BA.debugLine="bool_propertis=False";
_bool_propertis = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 151;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 152;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 153;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 154;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 57;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,show_nazar.getObject());
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _btn_close_click() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub btn_close_Click";
 //BA.debugLineNum = 146;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 147;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
String _msg = "";
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
String _txt = "";
String _namepro = "";
String _valuepro = "";
 //BA.debugLineNum = 74;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 75;BA.debugLine="Try";
try { //BA.debugLineNum = 76;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_nazar"),(Object)("get_propertis"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 78;BA.debugLine="For i=0 To records.Size-1";
{
final int step4 = 1;
final int limit4 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 79;BA.debugLine="Dim map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 80;BA.debugLine="map1=records.Get(i)";
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 81;BA.debugLine="Dim msg As String=map1.Get(\"msg\")";
_msg = BA.ObjectToString(_map1.Get((Object)("msg")));
 //BA.debugLineNum = 82;BA.debugLine="If msg.Length > 1 Then";
if (_msg.length()>1) { 
 //BA.debugLineNum = 83;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 84;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 85;BA.debugLine="pnl.LoadLayout(\"sv_show_nazar\")";
_pnl.LoadLayout("sv_show_nazar",mostCurrent.activityBA);
 //BA.debugLineNum = 86;BA.debugLine="Dim txt As String=\"{BLUE}\" & map1.Get(\"sende";
_txt = "{BLUE}"+BA.ObjectToString(_map1.Get((Object)("sender")))+":{BLUE}"+anywheresoftware.b4a.keywords.Common.CRLF+BA.ObjectToString(_map1.Get((Object)("msg")));
 //BA.debugLineNum = 88;BA.debugLine="sv_panel.Height=myLibrary.art(txt,sv_lbl_msg";
mostCurrent._sv_panel.setHeight((int) (mostCurrent._mylibrary._art(mostCurrent.activityBA,_txt,mostCurrent._sv_lbl_msg,mostCurrent._myfont,(int) (14),(int) (2))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8))));
 //BA.debugLineNum = 89;BA.debugLine="sv_panel_text.SetLayout(3%x,0,sv_panel.Width";
mostCurrent._sv_panel_text.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA),(int) (0),(int) (mostCurrent._sv_panel.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (6),mostCurrent.activityBA)),mostCurrent._sv_panel.getHeight());
 //BA.debugLineNum = 90;BA.debugLine="sv_lbl_msg.SetLayout(1%x,1%y,sv_panel_text.W";
mostCurrent._sv_lbl_msg.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA),(int) (mostCurrent._sv_panel_text.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA)),(int) (mostCurrent._sv_panel_text.getWidth()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA)));
 //BA.debugLineNum = 92;BA.debugLine="sv.Add(pnl,sv_panel.Height,i)";
mostCurrent._sv._add(_pnl,mostCurrent._sv_panel.getHeight(),(Object)(_i));
 };
 }
};
 break; }
case 1: {
 //BA.debugLineNum = 98;BA.debugLine="Try";
try { //BA.debugLineNum = 99;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 100;BA.debugLine="For i=0 To records.Size-1";
{
final int step22 = 1;
final int limit22 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step22 > 0 && _i <= limit22) || (step22 < 0 && _i >= limit22); _i = ((int)(0 + _i + step22)) ) {
 //BA.debugLineNum = 101;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 102;BA.debugLine="Dim namepro As String=map1.Get(\"key_name\")";
_namepro = BA.ObjectToString(_map1.Get((Object)("key_name")));
 //BA.debugLineNum = 103;BA.debugLine="Dim valuepro As String=map1.Get(\"key_value\"";
_valuepro = BA.ObjectToString(_map1.Get((Object)("key_value")));
 //BA.debugLineNum = 104;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 105;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 106;BA.debugLine="pnl.LoadLayout(\"sv_show_propertis\")";
_pnl.LoadLayout("sv_show_propertis",mostCurrent.activityBA);
 //BA.debugLineNum = 107;BA.debugLine="sv.Add(pnl,sv_pro_panel.Height,i)";
mostCurrent._sv._add(_pnl,mostCurrent._sv_pro_panel.getHeight(),(Object)(_i));
 //BA.debugLineNum = 108;BA.debugLine="sv_pro_key.Text=namepro";
mostCurrent._sv_pro_key.setText(BA.ObjectToCharSequence(_namepro));
 //BA.debugLineNum = 109;BA.debugLine="sv_pro_value.Text=valuepro";
mostCurrent._sv_pro_value.setText(BA.ObjectToCharSequence(_valuepro));
 }
};
 //BA.debugLineNum = 113;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 }else {
 //BA.debugLineNum = 117;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 118;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"مشخصات";
mostCurrent._mytoastmessage._showtoastmessageshow("مشخصات فنی برای این محصول درج نشده است",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 };
 } 
       catch (Exception e39) {
			processBA.setLastException(e39); //BA.debugLineNum = 122;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 123;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"مشخصات ف";
mostCurrent._mytoastmessage._showtoastmessageshow("مشخصات فنی برای این محصول درج نشده است",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 125;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 2: {
 //BA.debugLineNum = 127;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 128;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 130;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 132;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"متاسفانه";
mostCurrent._mytoastmessage._showtoastmessageshow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 134;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 135;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e54) {
			processBA.setLastException(e54); //BA.debugLineNum = 137;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"متاسفانه با";
mostCurrent._mytoastmessage._showtoastmessageshow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 138;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _get_nazar() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub get_nazar";
 //BA.debugLineNum = 65;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 66;BA.debugLine="connector.send_query($\"SELECT * From `nazar` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * From `nazar` where `code_kala`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code_kala))+" and `stuts`=1"),"get_nazar",(Object)(""));
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _get_propertis() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub get_propertis";
 //BA.debugLineNum = 70;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 71;BA.debugLine="connector.send_query($\"SELECT * From `propertis_k";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * From `propertis_kala` where `code_kala`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code_kala))+""),"get_propertis",(Object)(""));
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Dim sv As CustomListView";
mostCurrent._sv = new hpksoftware.setak.customlistview();
 //BA.debugLineNum = 15;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 16;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 17;BA.debugLine="Private panel_main As Panel";
mostCurrent._panel_main = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private sv_lbl_msg As Label";
mostCurrent._sv_lbl_msg = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private sv_panel_text As Panel";
mostCurrent._sv_panel_text = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private sv_panel As Panel";
mostCurrent._sv_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim myfont As Typeface";
mostCurrent._myfont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private sv_pro_value As Label";
mostCurrent._sv_pro_value = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private sv_pro_panel As Panel";
mostCurrent._sv_pro_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private sv_pro_key As Label";
mostCurrent._sv_pro_key = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="Public code_kala As Int";
_code_kala = 0;
 //BA.debugLineNum = 10;BA.debugLine="Public bool_propertis As Boolean=False";
_bool_propertis = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
}
