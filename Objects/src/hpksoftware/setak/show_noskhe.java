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

public class show_noskhe extends Activity implements B4AActivity{
	public static show_noskhe mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.show_noskhe");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (show_noskhe).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.show_noskhe");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.show_noskhe", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (show_noskhe) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (show_noskhe) Resume **");
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
		return show_noskhe.class;
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
        BA.LogInfo("** Activity (show_noskhe) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (show_noskhe) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.student.PersianDate _daye_per = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_history = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spiner_daroo = null;
public anywheresoftware.b4a.objects.LabelWrapper _txt_info = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_clock = null;
public anywheresoftware.b4a.sql.SQL _sql1 = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cr1 = null;
public static int _hiden_id_daroo = 0;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_search = null;
public ir.hamsaa.persiandatepicker.PersianPicker_wrap _dp = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_date = null;
public de.amberhome.objects.appcompat.ACSwitchCompatWrapper _acswitch1 = null;
public anywheresoftware.b4a.objects.TabHostWrapper _tabhost1 = null;
public anywheresoftware.b4a.objects.collections.List _list_clock = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spiner_date = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_reged_date = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_date_reg = null;
public static boolean _reged_date_next_visit_bool = false;
public de.amberhome.objects.appcompat.ACSwitchCompatWrapper _acswitch2 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
public hpksoftware.setak.main _main = null;
public hpksoftware.setak.starter _starter = null;
public hpksoftware.setak.insert_noskhe _insert_noskhe = null;
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
public static String  _acswitch1_checkedchange(boolean _checked) throws Exception{
int _enabled_switch_next_visit = 0;
 //BA.debugLineNum = 118;BA.debugLine="Sub ACSwitch1_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 120;BA.debugLine="Dim enabled_switch_next_visit As Int";
_enabled_switch_next_visit = 0;
 //BA.debugLineNum = 121;BA.debugLine="If Checked=True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 122;BA.debugLine="File.WriteString(File.DirDefaultExternal,\"enable";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_visit",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True));
 //BA.debugLineNum = 123;BA.debugLine="enabled_switch_next_visit=1";
_enabled_switch_next_visit = (int) (1);
 }else {
 //BA.debugLineNum = 125;BA.debugLine="If File.Exists(File.DirDefaultExternal,\"enabled_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_visit")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 126;BA.debugLine="File.Delete(File.DirDefaultExternal,\"enabled_al";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_visit");
 };
 //BA.debugLineNum = 129;BA.debugLine="enabled_switch_next_visit=0";
_enabled_switch_next_visit = (int) (0);
 };
 //BA.debugLineNum = 131;BA.debugLine="sql1.ExecNonQuery($\"update alarm set enabled=${en";
mostCurrent._sql1.ExecNonQuery(("update alarm set enabled="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_enabled_switch_next_visit))+" where type='next_visit'"));
 //BA.debugLineNum = 132;BA.debugLine="Log(\"Checked: \" & Checked)";
anywheresoftware.b4a.keywords.Common.Log("Checked: "+BA.ObjectToString(_checked));
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public static String  _acswitch2_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 343;BA.debugLine="Sub ACSwitch2_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 344;BA.debugLine="If Checked=True Then";
if (_checked==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 345;BA.debugLine="File.WriteString(File.DirDefaultExternal,\"enable";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_daroo",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True));
 }else {
 //BA.debugLineNum = 347;BA.debugLine="If File.Exists(File.DirDefaultExternal,\"enabled_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_daroo")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 348;BA.debugLine="File.Delete(File.DirDefaultExternal,\"enabled_al";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_daroo");
 };
 };
 //BA.debugLineNum = 351;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _i = 0;
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"tab_noskhe\")";
mostCurrent._activity.LoadLayout("tab_noskhe",mostCurrent.activityBA);
 //BA.debugLineNum = 45;BA.debugLine="TabHost1.AddTab(\"ویزیت بعدی\", \"alarm_noskhe.bal\")";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"ویزیت بعدی","alarm_noskhe.bal");
 //BA.debugLineNum = 46;BA.debugLine="TabHost1.AddTab(\"نسخه پزشک\",\"show_noskhe.bal\")";
mostCurrent._tabhost1.AddTab(mostCurrent.activityBA,"نسخه پزشک","show_noskhe.bal");
 //BA.debugLineNum = 48;BA.debugLine="If File.Exists(File.DirDefaultExternal,\"enabled_a";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_daroo")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 49;BA.debugLine="ACSwitch2.Checked=True";
mostCurrent._acswitch2.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 51;BA.debugLine="ACSwitch2.Checked=False";
mostCurrent._acswitch2.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 56;BA.debugLine="Panel3.Top=txt_info.Top + txt_info.Height + 5dip";
mostCurrent._panel3.setTop((int) (mostCurrent._txt_info.getTop()+mostCurrent._txt_info.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 60;BA.debugLine="TabHost1.CurrentTab =1 '(TabHost1.CurrentTab + 1)";
mostCurrent._tabhost1.setCurrentTab((int) (1));
 //BA.debugLineNum = 63;BA.debugLine="If File.Exists(Starter.rute_file_app,\"db.db\") = T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._starter._rute_file_app,"db.db")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 64;BA.debugLine="If sql1.IsInitialized=False Then sql1.Initialize";
if (mostCurrent._sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._sql1.Initialize(mostCurrent._starter._rute_file_app,"db.db",anywheresoftware.b4a.keywords.Common.True);};
 };
 //BA.debugLineNum = 67;BA.debugLine="Try";
try { //BA.debugLineNum = 69;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe grou";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe group by date_noskhe"))));
 //BA.debugLineNum = 70;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step16 = 1;
final int limit16 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step16 > 0 && _i <= limit16) || (step16 < 0 && _i >= limit16); _i = ((int)(0 + _i + step16)) ) {
 //BA.debugLineNum = 71;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 72;BA.debugLine="spiner_date.Add(cr1.GetString(\"date_noskhe\")	)";
mostCurrent._spiner_date.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("date_noskhe")));
 //BA.debugLineNum = 73;BA.debugLine="spiner_date.SelectedIndex=i";
mostCurrent._spiner_date.setSelectedIndex(_i);
 }
};
 } 
       catch (Exception e22) {
			processBA.setLastException(e22); //BA.debugLineNum = 78;BA.debugLine="Msgbox(LastException.Message,\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 84;BA.debugLine="get_of_date";
_get_of_date();
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _btn_adddate_click() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub btn_adddate_Click";
 //BA.debugLineNum = 100;BA.debugLine="dp.Initialize(\"dp\")";
mostCurrent._dp.Initialize(mostCurrent.activityBA,"dp");
 //BA.debugLineNum = 101;BA.debugLine="dp.SetMaxYear(1400)";
mostCurrent._dp.SetMaxYear((int) (1400));
 //BA.debugLineNum = 102;BA.debugLine="dp.SetMinYear(1372)";
mostCurrent._dp.SetMinYear((int) (1372));
 //BA.debugLineNum = 103;BA.debugLine="dp.SetPositiveButtonString(\"انتخاب\")";
mostCurrent._dp.SetPositiveButtonString("انتخاب");
 //BA.debugLineNum = 104;BA.debugLine="dp.SetNegativeButton(\"بستن\")";
mostCurrent._dp.SetNegativeButton("بستن");
 //BA.debugLineNum = 105;BA.debugLine="dp.SetTodayButtonVisible(True)";
mostCurrent._dp.SetTodayButtonVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 106;BA.debugLine="dp.SetTypeFace(Typeface.LoadFromAssets(\"iransans";
mostCurrent._dp.SetTypeFace(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iransans Bold.ttf"));
 //BA.debugLineNum = 107;BA.debugLine="dp.SetActionTextColor(Colors.Red)";
mostCurrent._dp.SetActionTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 108;BA.debugLine="dp.setListener";
mostCurrent._dp.setListener();
 //BA.debugLineNum = 109;BA.debugLine="dp.show()";
mostCurrent._dp.Show();
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 135;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 136;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _btn_reg_click() throws Exception{
String _title_visit_alarm = "";
 //BA.debugLineNum = 315;BA.debugLine="Sub btn_reg_Click";
 //BA.debugLineNum = 316;BA.debugLine="If lbl_date.Text.Length>0 Then";
if (mostCurrent._lbl_date.getText().length()>0) { 
 //BA.debugLineNum = 317;BA.debugLine="If reged_date_next_visit_bool=True Then";
if (_reged_date_next_visit_bool==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 318;BA.debugLine="LogColor($\"update alarm set clock='${lbl_date.T";
anywheresoftware.b4a.keywords.Common.LogColor(("update alarm set clock='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._lbl_date.getTag())+"' where type='next_visit'"),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 319;BA.debugLine="sql1.ExecNonQuery($\"update alarm set clock='${l";
mostCurrent._sql1.ExecNonQuery(("update alarm set clock='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._lbl_date.getTag())+"' where type='next_visit'"));
 //BA.debugLineNum = 320;BA.debugLine="lbl_date_reg.Text=$\"تاریخ ملاقات بعدی شما با پز";
mostCurrent._lbl_date_reg.setText(BA.ObjectToCharSequence(("تاریخ ملاقات بعدی شما با پزشک در "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._lbl_date.getTag())+" است")));
 //BA.debugLineNum = 321;BA.debugLine="File.WriteString(File.DirDefaultExternal,\"date_";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"date_visit",BA.ObjectToString(mostCurrent._lbl_date.getTag()));
 //BA.debugLineNum = 322;BA.debugLine="ToastMessageShow(\"تاریخ ویرایش شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("تاریخ ویرایش شد"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 324;BA.debugLine="If DialogResponse.POSITIVE=Msgbox2(\"آیا میخواهی";
if (anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE==anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("آیا میخواهید تاریخ ویزیت بعدی را درج کنید؟"),BA.ObjectToCharSequence(""),"بله","انصراف","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)) { 
 //BA.debugLineNum = 325;BA.debugLine="Dim title_visit_alarm As String=\"یادآور ملاقات";
_title_visit_alarm = "یادآور ملاقات شما با پزشک ستاک";
 //BA.debugLineNum = 326;BA.debugLine="Try";
try { //BA.debugLineNum = 327;BA.debugLine="sql1.ExecNonQuery($\"INSERT INTO alarm(enabled";
mostCurrent._sql1.ExecNonQuery(("INSERT INTO alarm(enabled,title,clock,type) VALUES(1,'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_title_visit_alarm))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._lbl_date.getText()))+"','next_visit')"));
 //BA.debugLineNum = 328;BA.debugLine="panel_reged_date.Visible=True";
mostCurrent._panel_reged_date.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 329;BA.debugLine="ACSwitch1.Checked=True";
mostCurrent._acswitch1.setChecked(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 330;BA.debugLine="lbl_date_reg.Text=$\"تاریخ ملاقات بعدی شما با";
mostCurrent._lbl_date_reg.setText(BA.ObjectToCharSequence(("تاریخ ملاقات بعدی شما با پزشک در "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",mostCurrent._lbl_date.getTag())+" است")));
 //BA.debugLineNum = 331;BA.debugLine="File.WriteString(File.DirDefaultExternal,\"dat";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"date_visit",BA.ObjectToString(mostCurrent._lbl_date.getTag()));
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 333;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 };
 };
 }else {
 //BA.debugLineNum = 339;BA.debugLine="ToastMessageShow(\"لطفا تاریخ را درج کنید\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا تاریخ را درج کنید"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 341;BA.debugLine="End Sub";
return "";
}
public static String  _clock_split(String _clock1) throws Exception{
String[] _array1 = null;
String _str = "";
int _i = 0;
 //BA.debugLineNum = 162;BA.debugLine="Sub clock_split(clock1 As String) As String";
 //BA.debugLineNum = 163;BA.debugLine="Dim array1() As String=Regex.Split(\"،\" , clock1)";
_array1 = anywheresoftware.b4a.keywords.Common.Regex.Split("،",_clock1);
 //BA.debugLineNum = 164;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 165;BA.debugLine="For i=0 To array1.Length-1";
{
final int step3 = 1;
final int limit3 = (int) (_array1.length-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 166;BA.debugLine="If i=array1.Length-1 Then";
if (_i==_array1.length-1) { 
 //BA.debugLineNum = 167;BA.debugLine="str=str & array1(i)";
_str = _str+_array1[_i];
 }else {
 //BA.debugLineNum = 169;BA.debugLine="str=str & array1(i) & \" | \"";
_str = _str+_array1[_i]+" | ";
 };
 }
};
 //BA.debugLineNum = 173;BA.debugLine="Return str";
if (true) return _str;
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return "";
}
public static String  _dp_dateclick(String _text) throws Exception{
 //BA.debugLineNum = 113;BA.debugLine="Sub dp_dateClick(Text As String)";
 //BA.debugLineNum = 114;BA.debugLine="lbl_date.Text=Text";
mostCurrent._lbl_date.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 115;BA.debugLine="lbl_date.tag=Text";
mostCurrent._lbl_date.setTag((Object)(_text));
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public static String  _get_of_date() throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
int _i = 0;
String _clock1 = "";
String[] _array1 = null;
int _y = 0;
 //BA.debugLineNum = 258;BA.debugLine="Sub get_of_date";
 //BA.debugLineNum = 259;BA.debugLine="Try";
try { //BA.debugLineNum = 260;BA.debugLine="spiner_daroo.Clear";
mostCurrent._spiner_daroo.Clear();
 //BA.debugLineNum = 261;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe Left";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"'"))));
 //BA.debugLineNum = 264;BA.debugLine="If list_clock.IsInitialized=False  Then";
if (mostCurrent._list_clock.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 265;BA.debugLine="list_clock.Initialize";
mostCurrent._list_clock.Initialize();
 //BA.debugLineNum = 266;BA.debugLine="Dim map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 267;BA.debugLine="map1.Initialize";
_map1.Initialize();
 //BA.debugLineNum = 269;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step8 = 1;
final int limit8 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 270;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 272;BA.debugLine="Dim clock1 As String=cr1.GetString(\"clock\")";
_clock1 = mostCurrent._cr1.GetString("clock");
 //BA.debugLineNum = 273;BA.debugLine="If  clock1.Contains(\"،\")=True Then";
if (_clock1.contains("،")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 274;BA.debugLine="Dim array1() As String=Regex.Split(\"،\" , cloc";
_array1 = anywheresoftware.b4a.keywords.Common.Regex.Split("،",_clock1);
 //BA.debugLineNum = 275;BA.debugLine="For y=0 To array1.Length-1";
{
final int step13 = 1;
final int limit13 = (int) (_array1.length-1);
for (_y = (int) (0) ; (step13 > 0 && _y <= limit13) || (step13 < 0 && _y >= limit13); _y = ((int)(0 + _y + step13)) ) {
 //BA.debugLineNum = 276;BA.debugLine="If map1.ContainsKey(array1(y).Trim)=False Th";
if (_map1.ContainsKey((Object)(_array1[_y].trim()))==anywheresoftware.b4a.keywords.Common.False) { 
_map1.Put((Object)(_array1[_y].trim()),(Object)(_y));};
 }
};
 }else {
 //BA.debugLineNum = 279;BA.debugLine="If map1.ContainsKey(clock1.Trim)=False Then m";
if (_map1.ContainsKey((Object)(_clock1.trim()))==anywheresoftware.b4a.keywords.Common.False) { 
_map1.Put((Object)(_clock1.trim()),(Object)(_y));};
 };
 }
};
 //BA.debugLineNum = 283;BA.debugLine="For i=0 To map1.Size-1";
{
final int step20 = 1;
final int limit20 = (int) (_map1.getSize()-1);
for (_i = (int) (0) ; (step20 > 0 && _i <= limit20) || (step20 < 0 && _i >= limit20); _i = ((int)(0 + _i + step20)) ) {
 //BA.debugLineNum = 284;BA.debugLine="list_clock.Add(	map1.GetKeyAt(i)		)";
mostCurrent._list_clock.Add(_map1.GetKeyAt(_i));
 }
};
 //BA.debugLineNum = 286;BA.debugLine="File.WriteList(File.DirDefaultExternal,\"clock_d";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"clock_daroo",mostCurrent._list_clock);
 //BA.debugLineNum = 287;BA.debugLine="LogColor(File.ReadList(File.DirDefaultExternal,";
anywheresoftware.b4a.keywords.Common.LogColor(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"clock_daroo")),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 };
 //BA.debugLineNum = 291;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step26 = 1;
final int limit26 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step26 > 0 && _i <= limit26) || (step26 < 0 && _i >= limit26); _i = ((int)(0 + _i + step26)) ) {
 //BA.debugLineNum = 292;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 293;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 294;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id_daroo\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id_daroo")));
 //BA.debugLineNum = 295;BA.debugLine="lbl_clock.Text=clock_split( cr1.GetString(\"clo";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(_clock_split(mostCurrent._cr1.GetString("clock"))));
 //BA.debugLineNum = 296;BA.debugLine="txt_info.Text=cr1.GetString(\"info\")";
mostCurrent._txt_info.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("info")));
 //BA.debugLineNum = 298;BA.debugLine="txt_info.Height=library.get_height_label(txt_i";
mostCurrent._txt_info.setHeight((int) (mostCurrent._library._get_height_label(mostCurrent.activityBA,mostCurrent._txt_info,mostCurrent._txt_info.getText())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))));
 //BA.debugLineNum = 299;BA.debugLine="If txt_info.Height< 35%y Then txt_info.Height=";
if (mostCurrent._txt_info.getHeight()<anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA)) { 
mostCurrent._txt_info.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA));};
 //BA.debugLineNum = 300;BA.debugLine="Panel3.Top=txt_info.Top + txt_info.Height + 10";
mostCurrent._panel3.setTop((int) (mostCurrent._txt_info.getTop()+mostCurrent._txt_info.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 };
 //BA.debugLineNum = 303;BA.debugLine="spiner_daroo.Add(cr1.GetString(\"name\")	)";
mostCurrent._spiner_daroo.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")));
 }
};
 } 
       catch (Exception e39) {
			processBA.setLastException(e39); //BA.debugLineNum = 311;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 };
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Dim daye_per As PersianDate";
mostCurrent._daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 13;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private panel_history As Panel";
mostCurrent._panel_history = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private spiner_daroo As ACSpinner";
mostCurrent._spiner_daroo = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private txt_info As Label";
mostCurrent._txt_info = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private lbl_clock As Label";
mostCurrent._lbl_clock = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim sql1 As SQL";
mostCurrent._sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 22;BA.debugLine="Dim cr1 As Cursor";
mostCurrent._cr1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim hiden_id_daroo As Int";
_hiden_id_daroo = 0;
 //BA.debugLineNum = 24;BA.debugLine="Private txt_search As EditText";
mostCurrent._txt_search = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim dp As DatePicker_persian_hpk";
mostCurrent._dp = new ir.hamsaa.persiandatepicker.PersianPicker_wrap();
 //BA.debugLineNum = 27;BA.debugLine="Private lbl_date As Label";
mostCurrent._lbl_date = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private ACSwitch1 As ACSwitch";
mostCurrent._acswitch1 = new de.amberhome.objects.appcompat.ACSwitchCompatWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private TabHost1 As TabHost";
mostCurrent._tabhost1 = new anywheresoftware.b4a.objects.TabHostWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim list_clock As List";
mostCurrent._list_clock = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 32;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private spiner_date As ACSpinner";
mostCurrent._spiner_date = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private panel_reged_date As Panel";
mostCurrent._panel_reged_date = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private lbl_date_reg As Label";
mostCurrent._lbl_date_reg = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim reged_date_next_visit_bool As Boolean=False";
_reged_date_next_visit_bool = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 38;BA.debugLine="Private ACSwitch2 As ACSwitch";
mostCurrent._acswitch2 = new de.amberhome.objects.appcompat.ACSwitchCompatWrapper();
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _spiner_daroo_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Sub spiner_daroo_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 177;BA.debugLine="Try";
try { //BA.debugLineNum = 178;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From daroo where";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From daroo where name='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",_value)+"'"))));
 } 
       catch (Exception e4) {
			processBA.setLastException(e4); //BA.debugLineNum = 180;BA.debugLine="Msgbox(LastException.Message,\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 183;BA.debugLine="cr1.Position = 0";
mostCurrent._cr1.setPosition((int) (0));
 //BA.debugLineNum = 184;BA.debugLine="spiner_daroo.SelectedIndex=0";
mostCurrent._spiner_daroo.setSelectedIndex((int) (0));
 //BA.debugLineNum = 185;BA.debugLine="lbl_clock.Text= clock_split( cr1.GetString(\"clock";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(_clock_split(mostCurrent._cr1.GetString("clock"))));
 //BA.debugLineNum = 186;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id")));
 //BA.debugLineNum = 187;BA.debugLine="txt_info.Text=cr1.GetString(\"info\")";
mostCurrent._txt_info.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("info")));
 //BA.debugLineNum = 189;BA.debugLine="txt_info.Height=library.get_height_label(txt_info";
mostCurrent._txt_info.setHeight((int) (mostCurrent._library._get_height_label(mostCurrent.activityBA,mostCurrent._txt_info,mostCurrent._txt_info.getText())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))));
 //BA.debugLineNum = 190;BA.debugLine="If txt_info.Height< 35%y Then txt_info.Height=35%";
if (mostCurrent._txt_info.getHeight()<anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA)) { 
mostCurrent._txt_info.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA));};
 //BA.debugLineNum = 191;BA.debugLine="Panel3.Top=txt_info.Top + txt_info.Height + 10dip";
mostCurrent._panel3.setTop((int) (mostCurrent._txt_info.getTop()+mostCurrent._txt_info.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static String  _spiner_date_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub spiner_date_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 255;BA.debugLine="get_of_date";
_get_of_date();
 //BA.debugLineNum = 256;BA.debugLine="End Sub";
return "";
}
public static String  _tabhost1_tabchanged() throws Exception{
int _enabled_switch_next_visit = 0;
int _i = 0;
 //BA.debugLineNum = 195;BA.debugLine="Sub TabHost1_TabChanged";
 //BA.debugLineNum = 196;BA.debugLine="Select TabHost1.CurrentTab";
switch (BA.switchObjectToInt(mostCurrent._tabhost1.getCurrentTab(),(int) (0),(int) (1))) {
case 0: {
 //BA.debugLineNum = 199;BA.debugLine="Try";
try { //BA.debugLineNum = 201;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From alarm whe";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From alarm where type='next_visit'"))));
 //BA.debugLineNum = 202;BA.debugLine="If cr1.RowCount > 0 Then";
if (mostCurrent._cr1.getRowCount()>0) { 
 //BA.debugLineNum = 203;BA.debugLine="panel_reged_date.Visible=True";
mostCurrent._panel_reged_date.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 204;BA.debugLine="cr1.Position = 0";
mostCurrent._cr1.setPosition((int) (0));
 //BA.debugLineNum = 205;BA.debugLine="LogColor(cr1.GetInt(\"enabled\"),Colors.red)";
anywheresoftware.b4a.keywords.Common.LogColor(BA.NumberToString(mostCurrent._cr1.GetInt("enabled")),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 206;BA.debugLine="Dim enabled_switch_next_visit As Int=cr1.GetI";
_enabled_switch_next_visit = mostCurrent._cr1.GetInt("enabled");
 //BA.debugLineNum = 207;BA.debugLine="If enabled_switch_next_visit=1 Then";
if (_enabled_switch_next_visit==1) { 
 //BA.debugLineNum = 208;BA.debugLine="ACSwitch1.Checked=True";
mostCurrent._acswitch1.setChecked(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 210;BA.debugLine="ACSwitch1.Checked=False";
mostCurrent._acswitch1.setChecked(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 212;BA.debugLine="reged_date_next_visit_bool=True";
_reged_date_next_visit_bool = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 213;BA.debugLine="lbl_date_reg.Text=$\"تاریخ ملاقات بعدی شما با";
mostCurrent._lbl_date_reg.setText(BA.ObjectToCharSequence(("تاریخ ملاقات بعدی شما با پزشک در "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._cr1.GetString("clock")))+" است")));
 };
 } 
       catch (Exception e19) {
			processBA.setLastException(e19); //BA.debugLineNum = 217;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 };
 break; }
case 1: {
 //BA.debugLineNum = 222;BA.debugLine="footer.Visible=False";
mostCurrent._footer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 223;BA.debugLine="If File.Exists(Starter.rute_file_app,\"db.db\") =";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._starter._rute_file_app,"db.db")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 224;BA.debugLine="If sql1.IsInitialized=False Then sql1.Initiali";
if (mostCurrent._sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._sql1.Initialize(mostCurrent._starter._rute_file_app,"db.db",anywheresoftware.b4a.keywords.Common.True);};
 };
 //BA.debugLineNum = 228;BA.debugLine="Try";
try { //BA.debugLineNum = 229;BA.debugLine="spiner_daroo.Clear";
mostCurrent._spiner_daroo.Clear();
 //BA.debugLineNum = 230;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe Le";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo"))));
 //BA.debugLineNum = 231;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step29 = 1;
final int limit29 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step29 > 0 && _i <= limit29) || (step29 < 0 && _i >= limit29); _i = ((int)(0 + _i + step29)) ) {
 //BA.debugLineNum = 232;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 233;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 234;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id_daroo\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id_daroo")));
 //BA.debugLineNum = 235;BA.debugLine="Log(lbl_clock.IsInitialized)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(mostCurrent._lbl_clock.IsInitialized()));
 //BA.debugLineNum = 236;BA.debugLine="lbl_clock.Text= clock_split( cr1.GetString(\"";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(_clock_split(mostCurrent._cr1.GetString("clock"))));
 //BA.debugLineNum = 237;BA.debugLine="txt_info.Text=cr1.GetString(\"info\")";
mostCurrent._txt_info.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("info")));
 //BA.debugLineNum = 239;BA.debugLine="txt_info.Height=library.get_height_label(txt";
mostCurrent._txt_info.setHeight((int) (mostCurrent._library._get_height_label(mostCurrent.activityBA,mostCurrent._txt_info,mostCurrent._txt_info.getText())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))));
 //BA.debugLineNum = 240;BA.debugLine="If txt_info.Height< 35%y Then txt_info.Heigh";
if (mostCurrent._txt_info.getHeight()<anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA)) { 
mostCurrent._txt_info.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA));};
 //BA.debugLineNum = 241;BA.debugLine="Panel3.Top=txt_info.Top + txt_info.Height +";
mostCurrent._panel3.setTop((int) (mostCurrent._txt_info.getTop()+mostCurrent._txt_info.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 };
 //BA.debugLineNum = 244;BA.debugLine="spiner_daroo.Add(cr1.GetString(\"name\")	)";
mostCurrent._spiner_daroo.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")));
 }
};
 } 
       catch (Exception e43) {
			processBA.setLastException(e43); //BA.debugLineNum = 247;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 };
 break; }
}
;
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public static String  _txt_search_textchanged(String _old,String _new) throws Exception{
int _i = 0;
 //BA.debugLineNum = 140;BA.debugLine="Sub txt_search_TextChanged (Old As String, New As";
 //BA.debugLineNum = 142;BA.debugLine="Try";
try { //BA.debugLineNum = 143;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From daroo where";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From daroo where name like '%"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_new))+"%'"))));
 //BA.debugLineNum = 144;BA.debugLine="spiner_daroo.Clear";
mostCurrent._spiner_daroo.Clear();
 //BA.debugLineNum = 145;BA.debugLine="If cr1.RowCount>0 Then";
if (mostCurrent._cr1.getRowCount()>0) { 
 //BA.debugLineNum = 146;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step5 = 1;
final int limit5 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 147;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 148;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 149;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id")));
 //BA.debugLineNum = 150;BA.debugLine="lbl_clock.Text= clock_split( cr1.GetString(\"c";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(_clock_split(mostCurrent._cr1.GetString("clock"))));
 };
 //BA.debugLineNum = 152;BA.debugLine="spiner_daroo.Add(cr1.GetString(\"name\")	)";
mostCurrent._spiner_daroo.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")));
 }
};
 };
 } 
       catch (Exception e15) {
			processBA.setLastException(e15); };
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
}
