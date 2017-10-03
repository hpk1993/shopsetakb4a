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

public class nazar extends Activity implements B4AActivity{
	public static nazar mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.nazar");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (nazar).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.nazar");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.nazar", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (nazar) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (nazar) Resume **");
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
		return nazar.class;
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
        BA.LogInfo("** Activity (nazar) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (nazar) Resume **");
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
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public b4a.example.rate_star _rate1 = null;
public b4a.example.rate_star _rate2 = null;
public b4a.example.rate_star _rate3 = null;
public static int _val_rate1 = 0;
public static int _val_rate2 = 0;
public static int _val_rate3 = 0;
public anywheresoftware.b4a.objects.PanelWrapper _rate_panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate_panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate_panel3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate1_panel = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate2_panel = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate3_panel = null;
public dmax.dialog.Spotsd _progress_spot = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_nazar = null;
public static String _user = "";
public anywheresoftware.b4a.phone.Phone.PhoneId _phone_id = null;
public anywheresoftware.b4a.objects.ButtonWrapper _reg = null;
public anywheresoftware.b4a.objects.collections.List _lst_dat = null;
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
public hpksoftware.setak.show_nazar _show_nazar = null;
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
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,nazar.getObject());
 //BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"reg_nazar\")";
mostCurrent._activity.LoadLayout("reg_nazar",mostCurrent.activityBA);
 //BA.debugLineNum = 39;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 40;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 41;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 43;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,nazar.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff13879a),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 45;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 46;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 48;BA.debugLine="lst_dat =File.ReadList(myLibrary.rute,Starter.fil";
mostCurrent._lst_dat = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 49;BA.debugLine="user=lst_dat.Get(2)";
mostCurrent._user = BA.ObjectToString(mostCurrent._lst_dat.Get((int) (2)));
 //BA.debugLineNum = 51;BA.debugLine="val_rate1=0";
_val_rate1 = (int) (0);
 //BA.debugLineNum = 52;BA.debugLine="val_rate2=0";
_val_rate2 = (int) (0);
 //BA.debugLineNum = 53;BA.debugLine="val_rate3=0";
_val_rate3 = (int) (0);
 //BA.debugLineNum = 55;BA.debugLine="rate1.Initialize(rate1_panel,Me,\"get_rate1\",rate";
mostCurrent._rate1._initialize(mostCurrent.activityBA,mostCurrent._rate1_panel,nazar.getObject(),"get_rate1",(int) (mostCurrent._rate1_panel.getWidth()/(double)5-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (mostCurrent._rate1_panel.getWidth()/(double)5-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star1.png"),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star0.png"));
 //BA.debugLineNum = 56;BA.debugLine="rate1.set_rate";
mostCurrent._rate1._set_rate();
 //BA.debugLineNum = 58;BA.debugLine="rate2.Initialize(rate2_panel,Me,\"get_rate2\",rate";
mostCurrent._rate2._initialize(mostCurrent.activityBA,mostCurrent._rate2_panel,nazar.getObject(),"get_rate2",(int) (mostCurrent._rate2_panel.getWidth()/(double)5-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (mostCurrent._rate2_panel.getWidth()/(double)5-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star1.png"),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star0.png"));
 //BA.debugLineNum = 59;BA.debugLine="rate2.set_rate";
mostCurrent._rate2._set_rate();
 //BA.debugLineNum = 61;BA.debugLine="rate3.Initialize(rate3_panel,Me,\"get_rate3\",rate";
mostCurrent._rate3._initialize(mostCurrent.activityBA,mostCurrent._rate3_panel,nazar.getObject(),"get_rate3",(int) (mostCurrent._rate3_panel.getWidth()/(double)5-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (mostCurrent._rate3_panel.getWidth()/(double)5-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star1.png"),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star0.png"));
 //BA.debugLineNum = 62;BA.debugLine="rate3.set_rate";
mostCurrent._rate3._set_rate();
 //BA.debugLineNum = 65;BA.debugLine="ckeck_nazar";
_ckeck_nazar();
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 107;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 108;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 109;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 110;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 93;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,nazar.getObject());
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _btn_close_click() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Sub btn_close_Click";
 //BA.debugLineNum = 102;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 103;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _ckeck_nazar() throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Sub ckeck_nazar";
 //BA.debugLineNum = 128;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 129;BA.debugLine="connector.send_query($\"SELECT * From `nazar` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * From `nazar` where `phone`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._phone_id.GetDeviceId()))+"' And `code_kala`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code_kala))+""),"ckeck_nazar",(Object)(""));
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 134;BA.debugLine="Try";
try { //BA.debugLineNum = 135;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("insert_nazar"),(Object)("ckeck_nazar"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 137;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 138;BA.debugLine="ToastMessageShow(\"نظر شما ثبت شد\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("نظر شما ثبت شد"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 139;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 1: {
 //BA.debugLineNum = 142;BA.debugLine="If records.Size >0  Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 143;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 144;BA.debugLine="ToastMessageShow(\"نظر شما قبلا ثیت شده است\",T";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("نظر شما قبلا ثیت شده است"),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 147;BA.debugLine="reg.Enabled=True";
mostCurrent._reg.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 149;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 2: {
 //BA.debugLineNum = 153;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 154;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 156;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 157;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 158;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 160;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 161;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 163;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 164;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _font_label() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _view1 = null;
anywheresoftware.b4a.objects.LabelWrapper _label_font = null;
 //BA.debugLineNum = 69;BA.debugLine="Sub font_label";
 //BA.debugLineNum = 70;BA.debugLine="For Each view1 As View In Activity.GetAllViewsRec";
_view1 = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group1 = mostCurrent._activity.GetAllViewsRecursive();
final int groupLen1 = group1.getSize();
for (int index1 = 0;index1 < groupLen1 ;index1++){
_view1.setObject((android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 71;BA.debugLine="If view1 Is Label Then";
if (_view1.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 72;BA.debugLine="Dim label_font As Label";
_label_font = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="label_font=view1";
_label_font.setObject((android.widget.TextView)(_view1.getObject()));
 //BA.debugLineNum = 74;BA.debugLine="label_font.Typeface=Starter.font_body";
_label_font.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 };
 }
;
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _get_rate1(String _rate_number) throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Sub get_rate1(rate_number)";
 //BA.debugLineNum = 81;BA.debugLine="val_rate1=rate_number";
_val_rate1 = (int)(Double.parseDouble(_rate_number));
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _get_rate2(String _rate_number) throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub get_rate2(rate_number)";
 //BA.debugLineNum = 85;BA.debugLine="val_rate2=rate_number";
_val_rate2 = (int)(Double.parseDouble(_rate_number));
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static String  _get_rate3(String _rate_number) throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub get_rate3(rate_number)";
 //BA.debugLineNum = 89;BA.debugLine="val_rate3=rate_number";
_val_rate3 = (int)(Double.parseDouble(_rate_number));
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 14;BA.debugLine="Dim rate1,rate2,rate3 As rate_star";
mostCurrent._rate1 = new b4a.example.rate_star();
mostCurrent._rate2 = new b4a.example.rate_star();
mostCurrent._rate3 = new b4a.example.rate_star();
 //BA.debugLineNum = 15;BA.debugLine="Dim val_rate1,val_rate2,val_rate3 As Int";
_val_rate1 = 0;
_val_rate2 = 0;
_val_rate3 = 0;
 //BA.debugLineNum = 17;BA.debugLine="Private rate_panel1 As Panel";
mostCurrent._rate_panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private rate_panel2 As Panel";
mostCurrent._rate_panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private rate_panel3 As Panel";
mostCurrent._rate_panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private rate1_panel As Panel";
mostCurrent._rate1_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private rate2_panel As Panel";
mostCurrent._rate2_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private rate3_panel As Panel";
mostCurrent._rate3_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 27;BA.debugLine="Private txt_nazar As EditText";
mostCurrent._txt_nazar = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim user As String";
mostCurrent._user = "";
 //BA.debugLineNum = 30;BA.debugLine="Dim phone_id As PhoneId";
mostCurrent._phone_id = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 31;BA.debugLine="Private reg As Button";
mostCurrent._reg = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim lst_dat As List";
mostCurrent._lst_dat = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 33;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _insert_nazar() throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Sub insert_nazar";
 //BA.debugLineNum = 123;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 124;BA.debugLine="connector.send_query($\"insert into `nazar`(`code_";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `nazar`(`code_kala`,`rate1`,`rate2`,`rate3`,`phone`,`sender`,`user_id`,`msg`,`stuts`) values("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code_kala))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_val_rate1))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_val_rate2))+","+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_val_rate3))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._phone_id.GetDeviceId()))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._user))+"',"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._lst_dat.Get((int) (3)))+",N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_nazar.getText().trim()))+"',0)"),"insert_nazar",(Object)(""));
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="Public code_kala As Int";
_code_kala = 0;
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _reg_click() throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Sub reg_Click";
 //BA.debugLineNum = 115;BA.debugLine="If val_rate1 > 0 And val_rate2>0 And val_rate3>0";
if (_val_rate1>0 && _val_rate2>0 && _val_rate3>0) { 
 //BA.debugLineNum = 116;BA.debugLine="insert_nazar";
_insert_nazar();
 }else {
 //BA.debugLineNum = 118;BA.debugLine="ToastMessageShow(\"لطفا به ما ستاره بدید\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا به ما ستاره بدید"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
}
