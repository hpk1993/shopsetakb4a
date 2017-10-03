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

public class shared_admin extends Activity implements B4AActivity{
	public static shared_admin mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.shared_admin");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (shared_admin).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.shared_admin");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.shared_admin", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (shared_admin) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (shared_admin) Resume **");
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
		return shared_admin.class;
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
        BA.LogInfo("** Activity (shared_admin) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (shared_admin) Resume **");
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
public hpksoftware.setak.customlistview _sv = null;
public hpksoftware.setak.customlistview _sv2 = null;
public b4a.example.money _mony = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_search = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_search = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_list = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_job = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_tell = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_address = null;
public anywheresoftware.b4a.objects.PanelWrapper _sv_user_panel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_id = null;
public anywheresoftware.b4a.objects.PanelWrapper _sv_panel_list = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_price_order = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_tels = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_status = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_date_last = null;
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
anywheresoftware.b4a.objects.IntentWrapper _intent1 = null;
String _notification_tag = "";
int _uid = 0;
 //BA.debugLineNum = 33;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"search_shared\")";
mostCurrent._activity.LoadLayout("search_shared",mostCurrent.activityBA);
 //BA.debugLineNum = 36;BA.debugLine="connector.progress_circle_initialize(Activity)";
mostCurrent._connector._progress_circle_initialize(mostCurrent.activityBA,mostCurrent._activity);
 //BA.debugLineNum = 37;BA.debugLine="connector.progress_circle_visible(False)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 38;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,shared_admin.getObject());
 //BA.debugLineNum = 40;BA.debugLine="sv.Initialize(Me,\"sv1\",0xFFF5F2F5,4dip)";
mostCurrent._sv._initialize(mostCurrent.activityBA,shared_admin.getObject(),"sv1",(int) (0xfff5f2f5),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)));
 //BA.debugLineNum = 41;BA.debugLine="panel_list.AddView(sv.AsView,0,0,panel_list.Width";
mostCurrent._panel_list.AddView((android.view.View)(mostCurrent._sv._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_list.getWidth(),mostCurrent._panel_list.getHeight());
 //BA.debugLineNum = 46;BA.debugLine="Dim Intent1 As Intent";
_intent1 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Intent1 = Activity.GetStartingIntent";
_intent1 = mostCurrent._activity.GetStartingIntent();
 //BA.debugLineNum = 48;BA.debugLine="If Intent1.HasExtra(\"Notification_Tag\") Then";
if (_intent1.HasExtra("Notification_Tag")) { 
 //BA.debugLineNum = 49;BA.debugLine="Try";
try { //BA.debugLineNum = 50;BA.debugLine="Dim Notification_Tag As String= Intent1.GetExtr";
_notification_tag = BA.ObjectToString(_intent1.GetExtra("Notification_Tag"));
 //BA.debugLineNum = 51;BA.debugLine="If Notification_Tag.SubString2(0,1).IndexOf(\"u\"";
if (_notification_tag.substring((int) (0),(int) (1)).indexOf("u")!=-1) { 
 //BA.debugLineNum = 52;BA.debugLine="connector.Initialize(Me,\"db\",Starter.server_my";
mostCurrent._connector._initialize(mostCurrent.activityBA,shared_admin.getObject(),"db",mostCurrent._starter._server_mysql,mostCurrent._starter._dn,mostCurrent._starter._du,mostCurrent._starter._dp);
 //BA.debugLineNum = 53;BA.debugLine="If function.is_admin=True Then";
if (mostCurrent._function._is_admin(mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 55;BA.debugLine="Dim uid As Int=Notification_Tag.SubString(1)";
_uid = (int)(Double.parseDouble(_notification_tag.substring((int) (1))));
 //BA.debugLineNum = 56;BA.debugLine="Log(\"is_admin: \" & uid )";
anywheresoftware.b4a.keywords.Common.Log("is_admin: "+BA.NumberToString(_uid));
 //BA.debugLineNum = 57;BA.debugLine="connector. progress_circle_visible(True)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 58;BA.debugLine="connector.send_query($\"select * from `users`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("select * from `users` where id="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_uid-100))+""),"get_user",(Object)(""));
 };
 };
 } 
       catch (Exception e22) {
			processBA.setLastException(e22); };
 };
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 69;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,shared_admin.getObject());
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _btn_list_click() throws Exception{
 //BA.debugLineNum = 198;BA.debugLine="Sub btn_list_Click";
 //BA.debugLineNum = 199;BA.debugLine="connector.progress_circle_visible(True)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 200;BA.debugLine="connector.send_query($\"SELECT  `order`.`id` AS  '";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT  `order`.`id` AS  'id_order',  `order_payment`.`amount_price` ,  `order_payment`.`status` ,  `order`.`customer_name` , `order`.`mobile` ,  `order`.`tel` ,  `order`.`user_id` ,  `order_detail`.`quality` ,`order`.`updated_at`\n"+"FROM  `order` \n"+"LEFT JOIN  `order_detail` ON  `order_detail`.`order_id` =  `order`.`id` \n"+"LEFT JOIN  `order_payment` ON  `order_payment`.`order_id` =  `order`.`id` \n"+"WHERE `order_payment`.`status`>1 \n"+"GROUP BY  `order`.`id` \n"+"ORDER BY  `order`.`id` DESC \n"+"LIMIT 0 , 15"),"get_all",(Object)(""));
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return "";
}
public static String  _btn_search_click() throws Exception{
String _q = "";
int _id = 0;
String _name = "";
 //BA.debugLineNum = 173;BA.debugLine="Sub btn_search_Click";
 //BA.debugLineNum = 174;BA.debugLine="Dim q As String=txt_search.Text.Trim";
_q = mostCurrent._txt_search.getText().trim();
 //BA.debugLineNum = 175;BA.debugLine="Log(q)";
anywheresoftware.b4a.keywords.Common.Log(_q);
 //BA.debugLineNum = 176;BA.debugLine="If IsNumber(q)=True  Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_q)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 177;BA.debugLine="Dim id As Int=q";
_id = (int)(Double.parseDouble(_q));
 //BA.debugLineNum = 178;BA.debugLine="Log(id)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_id));
 //BA.debugLineNum = 179;BA.debugLine="If id > 100 Then";
if (_id>100) { 
 //BA.debugLineNum = 180;BA.debugLine="Log(id)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_id));
 //BA.debugLineNum = 181;BA.debugLine="connector.progress_circle_visible(True)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 182;BA.debugLine="connector.send_query($\"select * from `users` wh";
mostCurrent._connector._send_query(mostCurrent.activityBA,("select * from `users` where id="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id-100))+""),"get_user",(Object)(""));
 };
 };
 //BA.debugLineNum = 186;BA.debugLine="If IsNumber(q)=False  Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_q)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 187;BA.debugLine="Dim name As String=q";
_name = _q;
 //BA.debugLineNum = 188;BA.debugLine="Log(name)";
anywheresoftware.b4a.keywords.Common.Log(_name);
 //BA.debugLineNum = 189;BA.debugLine="If name.Length>0 Then";
if (_name.length()>0) { 
 //BA.debugLineNum = 190;BA.debugLine="Log(name)";
anywheresoftware.b4a.keywords.Common.Log(_name);
 //BA.debugLineNum = 191;BA.debugLine="connector.progress_circle_visible(True)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 192;BA.debugLine="connector.send_query($\"select * from `users` wh";
mostCurrent._connector._send_query(mostCurrent.activityBA,("select * from `users` where `lname`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_name.trim()))+"' "),"get_user",(Object)(""));
 };
 };
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _list_records,Object _tag) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
int _idu = 0;
int _vip = 0;
b4a.example.money _toman = null;
String _stat_str = "";
anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _rich1 = null;
int _stat_color = 0;
int _stat = 0;
String[] _time_reg = null;
String[] _date = null;
String _clock = "";
anywheresoftware.b4a.student.PersianDate _daye_per = null;
 //BA.debugLineNum = 76;BA.debugLine="Sub db_connector(list_records As List,tag As Objec";
 //BA.debugLineNum = 77;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 78;BA.debugLine="Try";
try { //BA.debugLineNum = 79;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_user"),(Object)("get_all"),(Object)("disconnect"))) {
case 0: {
 //BA.debugLineNum = 81;BA.debugLine="If list_records.Size>0 Then";
if (_list_records.getSize()>0) { 
 //BA.debugLineNum = 82;BA.debugLine="sv.Clear";
mostCurrent._sv._clear();
 //BA.debugLineNum = 83;BA.debugLine="Log(\"# \" & list_records)";
anywheresoftware.b4a.keywords.Common.Log("# "+BA.ObjectToString(_list_records));
 //BA.debugLineNum = 84;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step8 = 1;
final int limit8 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 85;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 86;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 87;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 88;BA.debugLine="pnl.LoadLayout(\"sv_users\")";
_pnl.LoadLayout("sv_users",mostCurrent.activityBA);
 //BA.debugLineNum = 89;BA.debugLine="sv.Add(pnl,sv_user_panel.Height,map1.Get(\"id";
mostCurrent._sv._add(_pnl,mostCurrent._sv_user_panel.getHeight(),_map1.Get((Object)("id")));
 //BA.debugLineNum = 90;BA.debugLine="Dim idu As Int=map1.Get(\"id\")";
_idu = (int)(BA.ObjectToNumber(_map1.Get((Object)("id"))));
 //BA.debugLineNum = 91;BA.debugLine="lbl_name.Text=map1.Get(\"name\") & \" \" & map1.";
mostCurrent._lbl_name.setText(BA.ObjectToCharSequence(BA.ObjectToString(_map1.Get((Object)("name")))+" "+BA.ObjectToString(_map1.Get((Object)("lname")))+"  با کد اشتراک "+BA.NumberToString((_idu+100))));
 //BA.debugLineNum = 92;BA.debugLine="lbl_job.Text=\"شغل: \" & map1.Get(\"job\")";
mostCurrent._lbl_job.setText(BA.ObjectToCharSequence("شغل: "+BA.ObjectToString(_map1.Get((Object)("job")))));
 //BA.debugLineNum = 93;BA.debugLine="lbl_tell.Text=\"موبایل: \" & map1.Get(\"mobile\"";
mostCurrent._lbl_tell.setText(BA.ObjectToCharSequence("موبایل: "+BA.ObjectToString(_map1.Get((Object)("mobile")))));
 //BA.debugLineNum = 94;BA.debugLine="lbl_address.Text=map1.Get(\"address\")";
mostCurrent._lbl_address.setText(BA.ObjectToCharSequence(_map1.Get((Object)("address"))));
 }
};
 };
 //BA.debugLineNum = 99;BA.debugLine="connector. progress_circle_visible(False)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 break; }
case 1: {
 //BA.debugLineNum = 102;BA.debugLine="If list_records.Size>0 Then";
if (_list_records.getSize()>0) { 
 //BA.debugLineNum = 103;BA.debugLine="sv.Clear";
mostCurrent._sv._clear();
 //BA.debugLineNum = 104;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step25 = 1;
final int limit25 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step25 > 0 && _i <= limit25) || (step25 < 0 && _i >= limit25); _i = ((int)(0 + _i + step25)) ) {
 //BA.debugLineNum = 105;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 106;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 107;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 108;BA.debugLine="pnl.LoadLayout(\"sv_list_lat_order\")";
_pnl.LoadLayout("sv_list_lat_order",mostCurrent.activityBA);
 //BA.debugLineNum = 109;BA.debugLine="Dim vip As Int=map1.Get(\"user_id\")";
_vip = (int)(BA.ObjectToNumber(_map1.Get((Object)("user_id"))));
 //BA.debugLineNum = 110;BA.debugLine="sv.Add(pnl,sv_Panel_list.Height,vip	)	'map1.";
mostCurrent._sv._add(_pnl,mostCurrent._sv_panel_list.getHeight(),(Object)(_vip));
 //BA.debugLineNum = 111;BA.debugLine="Dim toman As money";
_toman = new b4a.example.money();
 //BA.debugLineNum = 112;BA.debugLine="lbl_id.Text= map1.Get(\"customer_name\") & \" ب";
mostCurrent._lbl_id.setText(BA.ObjectToCharSequence(BA.ObjectToString(_map1.Get((Object)("customer_name")))+" با اشتراک  "+BA.NumberToString((_vip+100))));
 //BA.debugLineNum = 113;BA.debugLine="lbl_price_order.Text=\"مبلغ سفارش \" &  toman.";
mostCurrent._lbl_price_order.setText(BA.ObjectToCharSequence("مبلغ سفارش "+_toman._number(BA.ObjectToString(_map1.Get((Object)("amount_price"))))+" تومان "+" با تعداد "+BA.ObjectToString(_map1.Get((Object)("quality")))+" عدد"));
 //BA.debugLineNum = 114;BA.debugLine="lbl_tels.Text=\"تلفن : \" & map1.Get(\"mobile\")";
mostCurrent._lbl_tels.setText(BA.ObjectToCharSequence("تلفن : "+BA.ObjectToString(_map1.Get((Object)("mobile")))+"     "+BA.ObjectToString(_map1.Get((Object)("tel")))));
 //BA.debugLineNum = 115;BA.debugLine="Dim stat_str As String";
_stat_str = "";
 //BA.debugLineNum = 116;BA.debugLine="Dim rich1 As RichString";
_rich1 = new anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString();
 //BA.debugLineNum = 117;BA.debugLine="Dim stat_color As Int";
_stat_color = 0;
 //BA.debugLineNum = 118;BA.debugLine="Dim stat As Int=map1.Get(\"status\")";
_stat = (int)(BA.ObjectToNumber(_map1.Get((Object)("status"))));
 //BA.debugLineNum = 119;BA.debugLine="Select stat";
switch (_stat) {
case 2: {
 //BA.debugLineNum = 121;BA.debugLine="stat_str=\"کد سفارش \" &  map1.Get(\"id_order";
_stat_str = "کد سفارش "+BA.ObjectToString(_map1.Get((Object)("id_order")))+"  |  "+"{C}"+"پرداخت شده و در حال بررسی"+"{C}";
 //BA.debugLineNum = 122;BA.debugLine="stat_color=0xFF09477F";
_stat_color = (int) (0xff09477f);
 break; }
case 3: {
 //BA.debugLineNum = 124;BA.debugLine="stat_str=\"کد سفارش \" &  map1.Get(\"id_order";
_stat_str = "کد سفارش "+BA.ObjectToString(_map1.Get((Object)("id_order")))+"  |  "+"{C}"+"ارسال شد"+"{C}";
 //BA.debugLineNum = 125;BA.debugLine="stat_color=0xFF135504";
_stat_color = (int) (0xff135504);
 break; }
case 4: {
 //BA.debugLineNum = 127;BA.debugLine="stat_str=\"کد سفارش \" &  map1.Get(\"id_order";
_stat_str = "کد سفارش "+BA.ObjectToString(_map1.Get((Object)("id_order")))+"  |  "+"وضعیت سفارش: "+"{C}"+"پرداخت در محل"+"{C}";
 //BA.debugLineNum = 128;BA.debugLine="stat_color=0xFFF89F0D";
_stat_color = (int) (0xfff89f0d);
 break; }
}
;
 //BA.debugLineNum = 130;BA.debugLine="rich1.Initialize(stat_str)";
_rich1.Initialize(BA.ObjectToCharSequence(_stat_str));
 //BA.debugLineNum = 131;BA.debugLine="rich1.Color2(stat_color,\"{C}\")";
_rich1.Color2(_stat_color,"{C}");
 //BA.debugLineNum = 132;BA.debugLine="lbl_status.Text= rich1";
mostCurrent._lbl_status.setText(BA.ObjectToCharSequence(_rich1.getObject()));
 //BA.debugLineNum = 135;BA.debugLine="Dim time_reg() As String";
_time_reg = new String[(int) (0)];
java.util.Arrays.fill(_time_reg,"");
 //BA.debugLineNum = 136;BA.debugLine="time_reg=Regex.Split(\" \",map1.Get(\"updated_a";
_time_reg = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",BA.ObjectToString(_map1.Get((Object)("updated_at"))));
 //BA.debugLineNum = 138;BA.debugLine="Dim date() As String=Regex.Split(\"-\",time_re";
_date = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_time_reg[(int) (0)]);
 //BA.debugLineNum = 140;BA.debugLine="Dim clock As String=time_reg(1)";
_clock = _time_reg[(int) (1)];
 //BA.debugLineNum = 142;BA.debugLine="Dim daye_per As PersianDate";
_daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 143;BA.debugLine="lbl_date_last.Text=\"تاریخ سفارش: \" & daye_pe";
mostCurrent._lbl_date_last.setText(BA.ObjectToCharSequence("تاریخ سفارش: "+_daye_per.getDate((int)(Double.parseDouble(_date[(int) (0)])),(int)(Double.parseDouble(_date[(int) (1)])),(int)(Double.parseDouble(_date[(int) (2)])),"/")));
 //BA.debugLineNum = 144;BA.debugLine="If map1.Get(\"updated_at\")=\"0000-00-00 00:00:";
if ((_map1.Get((Object)("updated_at"))).equals((Object)("0000-00-00 00:00:00"))) { 
mostCurrent._lbl_date_last.setText(BA.ObjectToCharSequence("تاریخ ثبت نشده"));};
 }
};
 };
 //BA.debugLineNum = 149;BA.debugLine="connector. progress_circle_visible(False)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 break; }
case 2: {
 //BA.debugLineNum = 152;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 153;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 154;BA.debugLine="connector. progress_circle_visible(False)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 break; }
}
;
 } 
       catch (Exception e70) {
			processBA.setLastException(e70); };
 //BA.debugLineNum = 160;BA.debugLine="connector. progress_circle_visible(False)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim sv ,sv2 As CustomListView";
mostCurrent._sv = new hpksoftware.setak.customlistview();
mostCurrent._sv2 = new hpksoftware.setak.customlistview();
 //BA.debugLineNum = 12;BA.debugLine="Dim mony As money";
mostCurrent._mony = new b4a.example.money();
 //BA.debugLineNum = 15;BA.debugLine="Private btn_search As Button";
mostCurrent._btn_search = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private txt_search As EditText";
mostCurrent._txt_search = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private panel_list As Panel";
mostCurrent._panel_list = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private lbl_name As Label";
mostCurrent._lbl_name = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lbl_job As Label";
mostCurrent._lbl_job = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private lbl_tell As Label";
mostCurrent._lbl_tell = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private lbl_address As Label";
mostCurrent._lbl_address = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private sv_user_panel As Panel";
mostCurrent._sv_user_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private lbl_id As Label";
mostCurrent._lbl_id = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private sv_Panel_list As Panel";
mostCurrent._sv_panel_list = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lbl_price_order As Label";
mostCurrent._lbl_price_order = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lbl_tels As Label";
mostCurrent._lbl_tels = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lbl_status As Label";
mostCurrent._lbl_status = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lbl_date_last As Label";
mostCurrent._lbl_date_last = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _sv1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 163;BA.debugLine="Sub sv1_ItemClick (Position As Int, Value As Objec";
 //BA.debugLineNum = 164;BA.debugLine="history_order.id_user=Value";
mostCurrent._history_order._id_user = (int)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 165;BA.debugLine="history_order.is_admin=True";
mostCurrent._history_order._is_admin = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 166;BA.debugLine="Log(\"id_user\" & Value)";
anywheresoftware.b4a.keywords.Common.Log("id_user"+BA.ObjectToString(_value));
 //BA.debugLineNum = 167;BA.debugLine="StartActivity(history_order)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._history_order.getObject()));
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
}
