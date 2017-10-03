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

public class order extends Activity implements B4AActivity{
	public static order mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.order");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (order).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.order");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.order", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (order) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (order) Resume **");
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
		return order.class;
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
        BA.LogInfo("** Activity (order) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (order) Resume **");
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
public static String _server_get_time = "";
public static int _id_user = 0;
public static anywheresoftware.b4a.objects.collections.List _order_detiles = null;
public dmax.dialog.Spotsd _progress_spot = null;
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public anywheresoftware.b4a.object.RippleViewWrapper _effect_btn1 = null;
public static int _time = 0;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol_main = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_gift = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_get = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_tell = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_phone = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_address = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_post_code = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public static int _order_id = 0;
public static int _code_kala = 0;
public static int _number = 0;
public static String _price = "";
public Object _date_reg = null;
public b4a.example.money _toman = null;
public static int _price_kol_sabad = 0;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public static boolean _run_payment = false;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_offline = null;
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
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"scrol_layout2\")";
mostCurrent._activity.LoadLayout("scrol_layout2",mostCurrent.activityBA);
 //BA.debugLineNum = 44;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 45;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 46;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="scrol_main.Panel.LoadLayout(\"reg_order\")";
mostCurrent._scrol_main.getPanel().LoadLayout("reg_order",mostCurrent.activityBA);
 //BA.debugLineNum = 49;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,order.getObject());
 //BA.debugLineNum = 50;BA.debugLine="server_get_time=Starter.server_get_time";
_server_get_time = mostCurrent._starter._server_get_time;
 //BA.debugLineNum = 51;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 54;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 55;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,order.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff13879a),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 56;BA.debugLine="ime.Initialize(\"IME\")";
mostCurrent._ime.Initialize("IME");
 //BA.debugLineNum = 57;BA.debugLine="ime.AddHeightChangedEvent";
mostCurrent._ime.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 58;BA.debugLine="effect_btn1.Initialize(footer,Colors.White,400,Fa";
mostCurrent._effect_btn1.Initialize(mostCurrent.activityBA,(android.view.View)(mostCurrent._footer.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,(int) (400),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 59;BA.debugLine="scrol_main.Panel.Height=100%y'txt_address.Top + t";
mostCurrent._scrol_main.getPanel().setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 61;BA.debugLine="get_user";
_get_user();
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 288;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 289;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 290;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 291;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 292;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 294;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 202;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 199;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,order.getObject());
 //BA.debugLineNum = 200;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 220;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 221;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 222;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 223;BA.debugLine="Return True";
if (true) return BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _btn_sabad_click() throws Exception{
 //BA.debugLineNum = 226;BA.debugLine="Sub btn_sabad_Click";
 //BA.debugLineNum = 228;BA.debugLine="End Sub";
return "";
}
public static String  _btn_search_click() throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Sub btn_search_Click";
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_click() throws Exception{
anywheresoftware.b4a.ariagplib.ARIAlib _share = null;
 //BA.debugLineNum = 230;BA.debugLine="Sub btn_share_Click";
 //BA.debugLineNum = 231;BA.debugLine="Dim share As AriaLib";
_share = new anywheresoftware.b4a.ariagplib.ARIAlib();
 //BA.debugLineNum = 232;BA.debugLine="StartActivity(share.ShareApplication(Application.";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share.ShareApplication(anywheresoftware.b4a.keywords.Common.Application.getPackageName(),anywheresoftware.b4a.keywords.Common.Application.getLabelName())));
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
int _i = 0;
String[] _tmp_arry = null;
anywheresoftware.b4a.phone.Phone.PhoneIntents _brow = null;
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.samples.httputils2.httpjob _http_sms = null;
String _msgsms = "";
 //BA.debugLineNum = 68;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 69;BA.debugLine="Try";
try { //BA.debugLineNum = 70;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_time"),(Object)("get_user"),(Object)("insert_order"),(Object)("insert_order2"),(Object)("get_id_order"),(Object)("get_id_order2"),(Object)("insert_order_detiles"),(Object)("insert_order_detiles2"),(Object)("insert_order_payment"),(Object)("insert_order_payment2"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 72;BA.debugLine="time=records.Get(0)";
_time = (int)(BA.ObjectToNumber(_records.Get((int) (0))));
 break; }
case 1: {
 //BA.debugLineNum = 75;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 76;BA.debugLine="Dim map1 As Map=records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get((int) (0))));
 //BA.debugLineNum = 78;BA.debugLine="txt_phone.Text=map1.Get(\"mobile\")";
mostCurrent._txt_phone.setText(BA.ObjectToCharSequence(_map1.Get((Object)("mobile"))));
 //BA.debugLineNum = 79;BA.debugLine="txt_tell.Text=map1.Get(\"tel\")";
mostCurrent._txt_tell.setText(BA.ObjectToCharSequence(_map1.Get((Object)("tel"))));
 //BA.debugLineNum = 80;BA.debugLine="txt_post_code.Text=map1.Get(\"post_code\")";
mostCurrent._txt_post_code.setText(BA.ObjectToCharSequence(_map1.Get((Object)("post_code"))));
 //BA.debugLineNum = 81;BA.debugLine="txt_address.Text=map1.Get(\"address\")";
mostCurrent._txt_address.setText(BA.ObjectToCharSequence(_map1.Get((Object)("address"))));
 //BA.debugLineNum = 82;BA.debugLine="txt_get.Text=map1.Get(\"name\") & \" \" & map1.Ge";
mostCurrent._txt_get.setText(BA.ObjectToCharSequence(BA.ObjectToString(_map1.Get((Object)("name")))+" "+BA.ObjectToString(_map1.Get((Object)("lname")))));
 //BA.debugLineNum = 83;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 }else {
 //BA.debugLineNum = 85;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 break; }
case 2: {
 //BA.debugLineNum = 89;BA.debugLine="get_id_order'@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
_get_id_order();
 break; }
case 3: {
 //BA.debugLineNum = 92;BA.debugLine="get_id_order2'@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
_get_id_order2();
 break; }
case 4: {
 //BA.debugLineNum = 97;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 98;BA.debugLine="Dim map1 As Map=records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get((int) (0))));
 //BA.debugLineNum = 99;BA.debugLine="order_id=map1.Get(\"id\")";
_order_id = (int)(BA.ObjectToNumber(_map1.Get((Object)("id"))));
 };
 //BA.debugLineNum = 102;BA.debugLine="For i=0 To order_detiles.Size-1";
{
final int step26 = 1;
final int limit26 = (int) (_order_detiles.getSize()-1);
for (_i = (int) (0) ; (step26 > 0 && _i <= limit26) || (step26 < 0 && _i >= limit26); _i = ((int)(0 + _i + step26)) ) {
 //BA.debugLineNum = 103;BA.debugLine="Dim tmp_arry() As String";
_tmp_arry = new String[(int) (0)];
java.util.Arrays.fill(_tmp_arry,"");
 //BA.debugLineNum = 104;BA.debugLine="tmp_arry=Regex.Split(\",\",order_detiles.Get(i)";
_tmp_arry = anywheresoftware.b4a.keywords.Common.Regex.Split(",",BA.ObjectToString(_order_detiles.Get(_i)));
 //BA.debugLineNum = 105;BA.debugLine="code_kala =tmp_arry(0)";
_code_kala = (int)(Double.parseDouble(_tmp_arry[(int) (0)]));
 //BA.debugLineNum = 106;BA.debugLine="number=tmp_arry(2)";
_number = (int)(Double.parseDouble(_tmp_arry[(int) (2)]));
 //BA.debugLineNum = 107;BA.debugLine="price=tmp_arry(1)";
mostCurrent._price = _tmp_arry[(int) (1)];
 //BA.debugLineNum = 108;BA.debugLine="price_kol_sabad=price_kol_sabad + (price * nu";
_price_kol_sabad = (int) (_price_kol_sabad+((double)(Double.parseDouble(mostCurrent._price))*_number));
 //BA.debugLineNum = 109;BA.debugLine="insert_order_detiles(order_detiles.Size,i+1)";
_insert_order_detiles(_order_detiles.getSize(),(int) (_i+1));
 }
};
 //BA.debugLineNum = 111;BA.debugLine="run_payment=True";
_run_payment = anywheresoftware.b4a.keywords.Common.True;
 break; }
case 5: {
 //BA.debugLineNum = 115;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 116;BA.debugLine="Dim map1 As Map=records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get((int) (0))));
 //BA.debugLineNum = 117;BA.debugLine="order_id=map1.Get(\"id\")";
_order_id = (int)(BA.ObjectToNumber(_map1.Get((Object)("id"))));
 };
 //BA.debugLineNum = 120;BA.debugLine="For i=0 To order_detiles.Size-1";
{
final int step41 = 1;
final int limit41 = (int) (_order_detiles.getSize()-1);
for (_i = (int) (0) ; (step41 > 0 && _i <= limit41) || (step41 < 0 && _i >= limit41); _i = ((int)(0 + _i + step41)) ) {
 //BA.debugLineNum = 121;BA.debugLine="Dim tmp_arry() As String";
_tmp_arry = new String[(int) (0)];
java.util.Arrays.fill(_tmp_arry,"");
 //BA.debugLineNum = 122;BA.debugLine="tmp_arry=Regex.Split(\",\",order_detiles.Get(i)";
_tmp_arry = anywheresoftware.b4a.keywords.Common.Regex.Split(",",BA.ObjectToString(_order_detiles.Get(_i)));
 //BA.debugLineNum = 123;BA.debugLine="code_kala =tmp_arry(0)";
_code_kala = (int)(Double.parseDouble(_tmp_arry[(int) (0)]));
 //BA.debugLineNum = 124;BA.debugLine="number=tmp_arry(2)";
_number = (int)(Double.parseDouble(_tmp_arry[(int) (2)]));
 //BA.debugLineNum = 125;BA.debugLine="price=tmp_arry(1)";
mostCurrent._price = _tmp_arry[(int) (1)];
 //BA.debugLineNum = 126;BA.debugLine="price_kol_sabad=price_kol_sabad + (price * nu";
_price_kol_sabad = (int) (_price_kol_sabad+((double)(Double.parseDouble(mostCurrent._price))*_number));
 //BA.debugLineNum = 127;BA.debugLine="insert_order_detiles2(order_detiles.Size,i+1)";
_insert_order_detiles2(_order_detiles.getSize(),(int) (_i+1));
 }
};
 //BA.debugLineNum = 130;BA.debugLine="run_payment=True";
_run_payment = anywheresoftware.b4a.keywords.Common.True;
 break; }
case 6: {
 //BA.debugLineNum = 135;BA.debugLine="If run_payment=True Then";
if (_run_payment==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 136;BA.debugLine="insert_order_payment";
_insert_order_payment();
 //BA.debugLineNum = 137;BA.debugLine="run_payment=False";
_run_payment = anywheresoftware.b4a.keywords.Common.False;
 };
 break; }
case 7: {
 //BA.debugLineNum = 144;BA.debugLine="If run_payment=True Then";
if (_run_payment==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 145;BA.debugLine="insert_order_payment2";
_insert_order_payment2();
 //BA.debugLineNum = 146;BA.debugLine="run_payment=False";
_run_payment = anywheresoftware.b4a.keywords.Common.False;
 };
 break; }
case 8: {
 //BA.debugLineNum = 152;BA.debugLine="File.Delete(myLibrary.rute,Starter.filename_sa";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 154;BA.debugLine="Dim brow As PhoneIntents";
_brow = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 155;BA.debugLine="StartActivity(brow.OpenBrowser($\"${Starter.Add";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_brow.OpenBrowser((""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._starter._address_payment))+"?a="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._mylibrary._base64(mostCurrent.activityBA,BA.NumberToString(_price_kol_sabad*10))))+"&o="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._mylibrary._base64(mostCurrent.activityBA,BA.NumberToString(_order_id))))+""))));
 //BA.debugLineNum = 156;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 157;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 9: {
 //BA.debugLineNum = 160;BA.debugLine="File.Delete(myLibrary.rute,Starter.filename_sa";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 161;BA.debugLine="toast.Initialize(\"سفارش شما ثبت شد\",toast.LENG";
mostCurrent._toast.Initialize(mostCurrent.activityBA,"سفارش شما ثبت شد",mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.SUCCESS);
 //BA.debugLineNum = 162;BA.debugLine="send_push";
_send_push();
 //BA.debugLineNum = 164;BA.debugLine="Dim lst As List=File.ReadList(myLibrary.rute,S";
_lst = new anywheresoftware.b4a.objects.collections.List();
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 165;BA.debugLine="Dim http_sms As HttpJob";
_http_sms = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 166;BA.debugLine="http_sms.Initialize(\"sms_send\",Me)";
_http_sms._initialize(processBA,"sms_send",order.getObject());
 //BA.debugLineNum = 167;BA.debugLine="Dim msgsms As String=$\"سفارش شما به ثبت رسید.ا";
_msgsms = ("سفارش شما به ثبت رسید.از شما متشکریم.کد سفارش شما :  "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_order_id))+"");
 //BA.debugLineNum = 168;BA.debugLine="http_sms.PostString(Starter.address_send_sms,$";
_http_sms._poststring(mostCurrent._starter._address_send_sms,("offline_buy=true&orderid="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_order_id))+"&userid="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user+100))+"&msg="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_msgsms))+"&to="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",_lst.Get((int) (0)))+""));
 //BA.debugLineNum = 169;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 10: {
 //BA.debugLineNum = 174;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 175;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 11: {
 //BA.debugLineNum = 177;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 178;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 179;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 181;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 182;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e88) {
			processBA.setLastException(e88); //BA.debugLineNum = 184;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 185;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return "";
}
public static String  _footer_click() throws Exception{
 //BA.debugLineNum = 296;BA.debugLine="Sub footer_Click";
 //BA.debugLineNum = 297;BA.debugLine="txt_post_code.Text=\"\"";
mostCurrent._txt_post_code.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 298;BA.debugLine="If txt_get.Text.Length > 3 Then ' And txt_gift.Te";
if (mostCurrent._txt_get.getText().length()>3) { 
 //BA.debugLineNum = 299;BA.debugLine="If  txt_address.Text.Length > 8 And txt_phone.T";
if (mostCurrent._txt_address.getText().length()>8 && mostCurrent._txt_phone.getText().length()==11) { 
 //BA.debugLineNum = 302;BA.debugLine="DateTime.TimeFormat=\"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 303;BA.debugLine="DateTime.DateFormat=\"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 304;BA.debugLine="date_reg=DateTime.Date(DateTime.Now) & \" \" &";
mostCurrent._date_reg = (Object)(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+" "+anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 307;BA.debugLine="insert_order(txt_address.Text.Trim,id_user,txt";
_insert_order(mostCurrent._txt_address.getText().trim(),_id_user,mostCurrent._txt_get.getText().trim(),mostCurrent._txt_phone.getText().trim(),mostCurrent._txt_tell.getText().trim(),mostCurrent._txt_gift.getText().trim(),"example@server.com",_time,BA.ObjectToString(mostCurrent._date_reg));
 }else {
 //BA.debugLineNum = 309;BA.debugLine="If txt_phone.Text.Length < 11 Then mytoastMessa";
if (mostCurrent._txt_phone.getText().length()<11) { 
mostCurrent._mytoastmessage._showtoastmessageshow("فیلد های خواسته شده را بررسی کنید,تلفن همراه 11 رقم است",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 310;BA.debugLine="If txt_address.Text.Length >=8  Then mytoastMes";
if (mostCurrent._txt_address.getText().length()>=8) { 
mostCurrent._mytoastmessage._showtoastmessageshow("فیلد های خواسته شده را بررسی کنید,آدرس حداقل 8 حرف",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);};
 };
 }else {
 //BA.debugLineNum = 314;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"نام دلخواه";
mostCurrent._mytoastmessage._showtoastmessageshow("نام دلخواه کالا و نام گیرنده را وارد کنید.حداقل 4 کاراکتر",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public static String  _get_id_order() throws Exception{
 //BA.debugLineNum = 245;BA.debugLine="Sub get_id_order";
 //BA.debugLineNum = 246;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 247;BA.debugLine="connector.send_query($\"SELECT * FROM `order` WHER";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `order` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user))+" order by `id` desc limit 1"),"get_id_order",(Object)(""));
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public static String  _get_id_order2() throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Sub get_id_order2";
 //BA.debugLineNum = 250;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 251;BA.debugLine="connector.send_query($\"SELECT * FROM `order` WHER";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `order` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user))+" order by `id` desc limit 1"),"get_id_order2",(Object)(""));
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _get_time() throws Exception{
 //BA.debugLineNum = 284;BA.debugLine="Sub get_time";
 //BA.debugLineNum = 285;BA.debugLine="connector.send_query($\"time\"$,\"get_time\",\"\")";
mostCurrent._connector._send_query(mostCurrent.activityBA,("time"),"get_time",(Object)(""));
 //BA.debugLineNum = 286;BA.debugLine="End Sub";
return "";
}
public static String  _get_user() throws Exception{
 //BA.debugLineNum = 240;BA.debugLine="Sub get_user";
 //BA.debugLineNum = 241;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 242;BA.debugLine="connector.send_query($\"SELECT * FROM `users` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `users` where `id`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user))+"'"),"get_user",(Object)(""));
 //BA.debugLineNum = 243;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 15;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 16;BA.debugLine="Dim effect_btn1 As RippleView";
mostCurrent._effect_btn1 = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private time As Int";
_time = 0;
 //BA.debugLineNum = 19;BA.debugLine="Private scrol_main As ScrollView";
mostCurrent._scrol_main = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private ime As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 21;BA.debugLine="Private txt_gift As EditText";
mostCurrent._txt_gift = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private txt_get As EditText";
mostCurrent._txt_get = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private txt_tell As EditText";
mostCurrent._txt_tell = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private txt_phone As EditText";
mostCurrent._txt_phone = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private txt_address As EditText";
mostCurrent._txt_address = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private txt_post_code As EditText";
mostCurrent._txt_post_code = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim order_id As Int";
_order_id = 0;
 //BA.debugLineNum = 30;BA.debugLine="Dim code_kala As Int";
_code_kala = 0;
 //BA.debugLineNum = 31;BA.debugLine="Dim number As Int";
_number = 0;
 //BA.debugLineNum = 32;BA.debugLine="Dim price As String";
mostCurrent._price = "";
 //BA.debugLineNum = 33;BA.debugLine="Dim date_reg As Object";
mostCurrent._date_reg = new Object();
 //BA.debugLineNum = 34;BA.debugLine="Dim toman As money";
mostCurrent._toman = new b4a.example.money();
 //BA.debugLineNum = 35;BA.debugLine="Dim price_kol_sabad As Int";
_price_kol_sabad = 0;
 //BA.debugLineNum = 36;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 37;BA.debugLine="Dim run_payment As Boolean=False";
_run_payment = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 38;BA.debugLine="Private lbl_offline As Label";
mostCurrent._lbl_offline = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _ime_heightchanged(int _newheight,int _oldheight) throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub IME_HeightChanged(NewHeight As Int, OldHeight";
 //BA.debugLineNum = 65;BA.debugLine="scrol_main.Height=NewHeight - footer.Height";
mostCurrent._scrol_main.setHeight((int) (_newheight-mostCurrent._footer.getHeight()));
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _insert_order(String _address,int _user_id,String _customer_name,String _phone,String _tel,String _gift_name,String _email,int _timee,String _date) throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub insert_order(address As String,user_id As Int,";
 //BA.debugLineNum = 255;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 256;BA.debugLine="connector.send_query($\"insert into `order`(`addre";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `order`(`address`,`customer_name`,`gift_name`,`tel`,`mobile`,`email`,`user_id`,`created_at`,`updated_at`) values(N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_address))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_customer_name))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_gift_name))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_tel))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_phone))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_email))+"',"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_date))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_date))+"')"),"insert_order",(Object)(""));
 //BA.debugLineNum = 257;BA.debugLine="End Sub";
return "";
}
public static String  _insert_order_detiles(int _total,int _curent) throws Exception{
 //BA.debugLineNum = 264;BA.debugLine="Sub insert_order_detiles(total As Int,curent As In";
 //BA.debugLineNum = 265;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 266;BA.debugLine="connector.send_query($\"insert into `order_detail`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `order_detail`(`order_id`,`code_kala`,`quality`,`price`,`created_at`,`updated_at`) values("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_order_id))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code_kala))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_number))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._price))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"')"),"insert_order_detiles",(Object)(""));
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return "";
}
public static String  _insert_order_detiles2(int _total,int _curent) throws Exception{
 //BA.debugLineNum = 269;BA.debugLine="Sub insert_order_detiles2(total As Int,curent As I";
 //BA.debugLineNum = 270;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 271;BA.debugLine="connector.send_query($\"insert into `order_detail`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `order_detail`(`order_id`,`code_kala`,`quality`,`price`,`created_at`,`updated_at`) values("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_order_id))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code_kala))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_number))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._price))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"')"),"insert_order_detiles2",(Object)(""));
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static String  _insert_order_payment() throws Exception{
 //BA.debugLineNum = 274;BA.debugLine="Sub insert_order_payment";
 //BA.debugLineNum = 275;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 276;BA.debugLine="connector.send_query($\"insert into `order_payment";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `order_payment`(`order_id`,`date`,`amount_price`,`code_rahgiri`,`refnum`,`status`,`created_at`,`updated_at`) values("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_order_id))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(0))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_price_kol_sabad))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(" "))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(" "))+"',1,'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"')"),"insert_order_payment",(Object)(""));
 //BA.debugLineNum = 277;BA.debugLine="End Sub";
return "";
}
public static String  _insert_order_payment2() throws Exception{
 //BA.debugLineNum = 279;BA.debugLine="Sub insert_order_payment2";
 //BA.debugLineNum = 280;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 281;BA.debugLine="connector.send_query($\"insert into `order_payment";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `order_payment`(`order_id`,`date`,`amount_price`,`code_rahgiri`,`refnum`,`status`,`created_at`,`updated_at`) values("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_order_id))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(0))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_price_kol_sabad))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(" "))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(" "))+"',4,'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._date_reg)+"')"),"insert_order_payment2",(Object)(""));
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _insert_order2(String _address,int _user_id,String _customer_name,String _phone,String _tel,String _gift_name,String _email,int _timee,String _date) throws Exception{
 //BA.debugLineNum = 259;BA.debugLine="Sub insert_order2(address As String,user_id As Int";
 //BA.debugLineNum = 260;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 261;BA.debugLine="connector.send_query($\"insert into `order`(`addre";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `order`(`address`,`customer_name`,`gift_name`,`tel`,`mobile`,`email`,`user_id`,`created_at`,`updated_at`) values(N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_address))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_customer_name))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_gift_name))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_tel))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_phone))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_email))+"',"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_date))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_date))+"')"),"insert_order2",(Object)(""));
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _res) throws Exception{
 //BA.debugLineNum = 207;BA.debugLine="Sub JobDone(res As HttpJob)";
 //BA.debugLineNum = 208;BA.debugLine="If res.Success=True Then";
if (_res._success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 209;BA.debugLine="Select res.JobName";
switch (BA.switchObjectToInt(_res._jobname,"sms_send","send_push")) {
case 0: {
 //BA.debugLineNum = 211;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 1: {
 //BA.debugLineNum = 214;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
}
;
 };
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_offline_click() throws Exception{
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _i = 0;
 //BA.debugLineNum = 318;BA.debugLine="Sub lbl_offline_Click";
 //BA.debugLineNum = 319;BA.debugLine="Log(\"lbl_offline_Click\")";
anywheresoftware.b4a.keywords.Common.Log("lbl_offline_Click");
 //BA.debugLineNum = 320;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 321;BA.debugLine="Pd=function.style_msgbox(\"توجه\",\"آیا مطمعن به ثبت";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"توجه","آیا مطمعن به ثبت سفارش خود هستید؟","بلی","خیر","");
 //BA.debugLineNum = 322;BA.debugLine="Dim i As Int = Pd.Create";
_i = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 323;BA.debugLine="If i=DialogResponse.POSITIVE Then";
if (_i==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 324;BA.debugLine="Log(\"lbl_offline_Click\")";
anywheresoftware.b4a.keywords.Common.Log("lbl_offline_Click");
 //BA.debugLineNum = 325;BA.debugLine="txt_post_code.Text=\"\"";
mostCurrent._txt_post_code.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 326;BA.debugLine="If txt_get.Text.Length > 3  Then ' And txt_gift.";
if (mostCurrent._txt_get.getText().length()>3) { 
 //BA.debugLineNum = 327;BA.debugLine="If  txt_address.Text.Length >=8 And txt_phone.T";
if (mostCurrent._txt_address.getText().length()>=8 && mostCurrent._txt_phone.getText().length()==11) { 
 //BA.debugLineNum = 330;BA.debugLine="DateTime.TimeFormat=\"HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm:ss");
 //BA.debugLineNum = 331;BA.debugLine="DateTime.DateFormat=\"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 332;BA.debugLine="date_reg=DateTime.Date(DateTime.Now) & \" \" & D";
mostCurrent._date_reg = (Object)(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+" "+anywheresoftware.b4a.keywords.Common.DateTime.Time(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 335;BA.debugLine="insert_order2(txt_address.Text.Trim,id_user,tx";
_insert_order2(mostCurrent._txt_address.getText().trim(),_id_user,mostCurrent._txt_get.getText().trim(),mostCurrent._txt_phone.getText().trim(),mostCurrent._txt_tell.getText().trim(),mostCurrent._txt_gift.getText().trim(),"example@server.com",_time,BA.ObjectToString(mostCurrent._date_reg));
 }else {
 //BA.debugLineNum = 338;BA.debugLine="If txt_phone.Text.Length < 11 Then mytoastMess";
if (mostCurrent._txt_phone.getText().length()<11) { 
mostCurrent._mytoastmessage._showtoastmessageshow("فیلد های خواسته شده را بررسی کنید,تلفن همراه 11 رقم است",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 339;BA.debugLine="If txt_address.Text.Length >=8 Then mytoastMes";
if (mostCurrent._txt_address.getText().length()>=8) { 
mostCurrent._mytoastmessage._showtoastmessageshow("فیلد های خواسته شده را بررسی کنید,آدرس حداقل 8 حرف",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);};
 };
 }else {
 //BA.debugLineNum = 344;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"نام دلخواه";
mostCurrent._mytoastmessage._showtoastmessageshow("نام دلخواه کالا و نام گیرنده را وارد کنید.حداقل 4 کاراکتر",(long) (2),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 };
 };
 //BA.debugLineNum = 347;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="Public server_get_time As String";
_server_get_time = "";
 //BA.debugLineNum = 9;BA.debugLine="Public id_user As Int";
_id_user = 0;
 //BA.debugLineNum = 10;BA.debugLine="Public order_detiles As List";
_order_detiles = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _send_push() throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _http_push = null;
 //BA.debugLineNum = 190;BA.debugLine="Sub send_push";
 //BA.debugLineNum = 192;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 193;BA.debugLine="Dim http_push As HttpJob";
_http_push = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 194;BA.debugLine="http_push.Initialize(\"send_push\",Me)";
_http_push._initialize(processBA,"send_push",order.getObject());
 //BA.debugLineNum = 195;BA.debugLine="http_push.PostString(Starter.Addres_send_pushe,$\"";
_http_push._poststring(mostCurrent._starter._addres_send_pushe,("buy_user=true&price="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._toman._number(BA.NumberToString(_price_kol_sabad))+" تومان"))+"&uid="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(100+_id_user))+""));
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
}
