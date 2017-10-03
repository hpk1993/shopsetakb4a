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

public class rezerv extends android.support.v7.app.AppCompatActivity implements B4AActivity{
	public static rezerv mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.rezerv");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (rezerv).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.rezerv");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.rezerv", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (rezerv) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (rezerv) Resume **");
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
		return rezerv.class;
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
        BA.LogInfo("** Activity (rezerv) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (rezerv) Resume **");
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
public anywheresoftware.b4a.objects.collections.Map _map_day = null;
public static String _date_per = "";
public dmax.dialog.Spotsd _progress_spot = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.student.PersianDate _daye_per = null;
public static String _val_day = "";
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol_main = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_history = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public ir.hamsaa.persiandatepicker.PersianPicker_wrap _dp = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spin_clock = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spin_city = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spin_day = null;
public anywheresoftware.b4a.objects.collections.List _list_clocks = null;
public anywheresoftware.b4a.objects.collections.List _list_visited = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_back = null;
public ir.anamsoftware.persiandateultimate.ManamPDUltimate _persian = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spin_month = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
public hpksoftware.setak.main _main = null;
public hpksoftware.setak.starter _starter = null;
public hpksoftware.setak.insert_noskhe _insert_noskhe = null;
public hpksoftware.setak.show_noskhe _show_noskhe = null;
public hpksoftware.setak.alarm_service _alarm_service = null;
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
public static class _adapter_clock_reserv{
public boolean IsInitialized;
public int id;
public String city;
public String clocks;
public String day;
public void Initialize() {
IsInitialized = true;
id = 0;
city = "";
clocks = "";
day = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _adapter_visited{
public boolean IsInitialized;
public int id;
public int user_id;
public String city;
public String date_visit;
public String clock_visit;
public String day_visit;
public void Initialize() {
IsInitialized = true;
id = 0;
user_id = 0;
city = "";
date_visit = "";
clock_visit = "";
day_visit = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_header = null;
 //BA.debugLineNum = 43;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 44;BA.debugLine="Activity.LoadLayout(\"scrol_layout\")";
mostCurrent._activity.LoadLayout("scrol_layout",mostCurrent.activityBA);
 //BA.debugLineNum = 45;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 46;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 47;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="Label3.Text=\"رزرو آنلاین نوبت\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("رزرو آنلاین نوبت"));
 //BA.debugLineNum = 49;BA.debugLine="Label3.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._label3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 50;BA.debugLine="Label3.SetLayout(10%x,Label3.Top,80%x,Label3.Heig";
mostCurrent._label3.SetLayout(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA),mostCurrent._label3.getTop(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (80),mostCurrent.activityBA),mostCurrent._label3.getHeight());
 //BA.debugLineNum = 51;BA.debugLine="scrol_main.Panel.LoadLayout(\"rezerv\")";
mostCurrent._scrol_main.getPanel().LoadLayout("rezerv",mostCurrent.activityBA);
 //BA.debugLineNum = 53;BA.debugLine="panel_history.Visible=False";
mostCurrent._panel_history.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 54;BA.debugLine="footer.Visible=False";
mostCurrent._footer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 55;BA.debugLine="scrol_main.Height=scrol_main.Height+footer.Height";
mostCurrent._scrol_main.setHeight((int) (mostCurrent._scrol_main.getHeight()+mostCurrent._footer.getHeight()));
 //BA.debugLineNum = 56;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 59;BA.debugLine="connector.Initialize(Me,\"db\",Starter.server_mysql";
mostCurrent._connector._initialize(mostCurrent.activityBA,rezerv.getObject(),"db",mostCurrent._starter._server_mysql,mostCurrent._starter._dn,mostCurrent._starter._du,mostCurrent._starter._dp);
 //BA.debugLineNum = 60;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 61;BA.debugLine="connector.send_query(\"select * from `clock_visit`";
mostCurrent._connector._send_query(mostCurrent.activityBA,"select * from `clock_visit` order by `id`","get_rezerv",(Object)(""));
 //BA.debugLineNum = 64;BA.debugLine="scrol_main.Panel.Height=100%y";
mostCurrent._scrol_main.getPanel().setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 339;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 341;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _btn_adddate_click() throws Exception{
 //BA.debugLineNum = 348;BA.debugLine="Sub btn_adddate_Click";
 //BA.debugLineNum = 349;BA.debugLine="dp.Initialize(\"dp\")";
mostCurrent._dp.Initialize(mostCurrent.activityBA,"dp");
 //BA.debugLineNum = 350;BA.debugLine="dp.SetMaxYear(1400)";
mostCurrent._dp.SetMaxYear((int) (1400));
 //BA.debugLineNum = 351;BA.debugLine="dp.SetMinYear(1372)";
mostCurrent._dp.SetMinYear((int) (1372));
 //BA.debugLineNum = 352;BA.debugLine="dp.SetPositiveButtonString(\"انتخاب\")";
mostCurrent._dp.SetPositiveButtonString("انتخاب");
 //BA.debugLineNum = 353;BA.debugLine="dp.SetNegativeButton(\"بستن\")";
mostCurrent._dp.SetNegativeButton("بستن");
 //BA.debugLineNum = 354;BA.debugLine="dp.SetTodayButtonVisible(True)";
mostCurrent._dp.SetTodayButtonVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 355;BA.debugLine="dp.SetTypeFace(Typeface.LoadFromAssets(\"iransans";
mostCurrent._dp.SetTypeFace(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iransans Bold.ttf"));
 //BA.debugLineNum = 356;BA.debugLine="dp.SetActionTextColor(Colors.Red)";
mostCurrent._dp.SetActionTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 357;BA.debugLine="dp.setListener";
mostCurrent._dp.setListener();
 //BA.debugLineNum = 358;BA.debugLine="dp.show()";
mostCurrent._dp.Show();
 //BA.debugLineNum = 359;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 515;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 516;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 517;BA.debugLine="End Sub";
return "";
}
public static String  _btn_reg_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _user_info = null;
String[] _array_day = null;
 //BA.debugLineNum = 361;BA.debugLine="Sub btn_reg_Click";
 //BA.debugLineNum = 362;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_us";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 364;BA.debugLine="Dim user_info As List";
_user_info = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 365;BA.debugLine="user_info.Initialize";
_user_info.Initialize();
 //BA.debugLineNum = 366;BA.debugLine="user_info=File.ReadList(myLibrary.rute,Starter.";
_user_info = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 367;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 368;BA.debugLine="Dim array_day() As String=Regex.Split(\" \",val_da";
_array_day = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",mostCurrent._val_day);
 //BA.debugLineNum = 369;BA.debugLine="connector.send_query($\"insert into `visit`(`user";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `visit`(`user_id`,`date_visit`,`day_visit`,`clock_visit`,`city`) Values("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",_user_info.Get((int) (3)))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._date_per))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_array_day[(int) (0)]))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spin_clock.getSelectedItem()))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spin_city.getSelectedItem()))+"')"),"insert_visit",(Object)(""));
 }else {
 //BA.debugLineNum = 377;BA.debugLine="ToastMessageShow(\"لطفا ابتدا وارد حساب کاربری خو";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا ابتدا وارد حساب کاربری خود شوید"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 381;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
anywheresoftware.b4a.objects.collections.Map _map_city = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _col = null;
String _city = "";
String _clocks = "";
String _day = "";
hpksoftware.setak.rezerv._adapter_clock_reserv _adapter_clock_reserv1 = null;
int _y = 0;
hpksoftware.setak.rezerv._adapter_visited _adapter_visited1 = null;
String _value = "";
String _day_select = "";
String[] _array_day = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
String _colroot = "";
boolean _reserved_clock = false;
int _z = 0;
 //BA.debugLineNum = 119;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 121;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_rezerv"),(Object)("get_visit"),(Object)("insert_visit"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 123;BA.debugLine="If records.Size>0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 124;BA.debugLine="spin_city.Clear";
mostCurrent._spin_city.Clear();
 //BA.debugLineNum = 125;BA.debugLine="List_Clocks.Initialize";
mostCurrent._list_clocks.Initialize();
 //BA.debugLineNum = 126;BA.debugLine="Dim map_city As Map";
_map_city = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 128;BA.debugLine="map_city.Initialize";
_map_city.Initialize();
 //BA.debugLineNum = 130;BA.debugLine="For i=0 To records.Size-1";
{
final int step8 = 1;
final int limit8 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 131;BA.debugLine="Dim col As Map=records.Get(i)";
_col = new anywheresoftware.b4a.objects.collections.Map();
_col.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 132;BA.debugLine="Dim city As String=col.Get(\"city\")";
_city = BA.ObjectToString(_col.Get((Object)("city")));
 //BA.debugLineNum = 133;BA.debugLine="Dim clocks As String=col.Get(\"clocks\")";
_clocks = BA.ObjectToString(_col.Get((Object)("clocks")));
 //BA.debugLineNum = 134;BA.debugLine="Dim day As String=col.Get(\"day\")";
_day = BA.ObjectToString(_col.Get((Object)("day")));
 //BA.debugLineNum = 136;BA.debugLine="map_city.Put(city,i)";
_map_city.Put((Object)(_city),(Object)(_i));
 //BA.debugLineNum = 138;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Re";
_adapter_clock_reserv1 = new hpksoftware.setak.rezerv._adapter_clock_reserv();
 //BA.debugLineNum = 139;BA.debugLine="Adapter_Clock_Reserv1.Initialize";
_adapter_clock_reserv1.Initialize();
 //BA.debugLineNum = 140;BA.debugLine="Adapter_Clock_Reserv1.city=city";
_adapter_clock_reserv1.city = _city;
 //BA.debugLineNum = 141;BA.debugLine="Adapter_Clock_Reserv1.clocks=clocks";
_adapter_clock_reserv1.clocks = _clocks;
 //BA.debugLineNum = 142;BA.debugLine="Adapter_Clock_Reserv1.day=day";
_adapter_clock_reserv1.day = _day;
 //BA.debugLineNum = 143;BA.debugLine="List_Clocks.Add(Adapter_Clock_Reserv1)";
mostCurrent._list_clocks.Add((Object)(_adapter_clock_reserv1));
 }
};
 //BA.debugLineNum = 147;BA.debugLine="For y=0 To map_city.Size-1";
{
final int step21 = 1;
final int limit21 = (int) (_map_city.getSize()-1);
for (_y = (int) (0) ; (step21 > 0 && _y <= limit21) || (step21 < 0 && _y >= limit21); _y = ((int)(0 + _y + step21)) ) {
 //BA.debugLineNum = 148;BA.debugLine="spin_city.Add(map_city.GetKeyAt(y) )";
mostCurrent._spin_city.Add(BA.ObjectToCharSequence(_map_city.GetKeyAt(_y)));
 }
};
 //BA.debugLineNum = 151;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 152;BA.debugLine="connector.send_query($\"SELECT * FROM `visit`\"$";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `visit`"),"get_visit",(Object)(""));
 };
 break; }
case 1: {
 //BA.debugLineNum = 156;BA.debugLine="List_visited.Initialize";
mostCurrent._list_visited.Initialize();
 //BA.debugLineNum = 157;BA.debugLine="If records.Size>0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 158;BA.debugLine="For i=0 To records.Size-1";
{
final int step30 = 1;
final int limit30 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step30 > 0 && _i <= limit30) || (step30 < 0 && _i >= limit30); _i = ((int)(0 + _i + step30)) ) {
 //BA.debugLineNum = 159;BA.debugLine="Dim col As Map=records.Get(i)";
_col = new anywheresoftware.b4a.objects.collections.Map();
_col.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 160;BA.debugLine="Dim Adapter_visited1 As Adapter_visited";
_adapter_visited1 = new hpksoftware.setak.rezerv._adapter_visited();
 //BA.debugLineNum = 161;BA.debugLine="Adapter_visited1.date_visit=col.Get(\"date_vis";
_adapter_visited1.date_visit = BA.ObjectToString(_col.Get((Object)("date_visit")));
 //BA.debugLineNum = 162;BA.debugLine="Adapter_visited1.clock_visit=col.Get(\"clock_v";
_adapter_visited1.clock_visit = BA.ObjectToString(_col.Get((Object)("clock_visit")));
 //BA.debugLineNum = 163;BA.debugLine="Adapter_visited1.day_visit=col.Get(\"day_visit";
_adapter_visited1.day_visit = BA.ObjectToString(_col.Get((Object)("day_visit")));
 //BA.debugLineNum = 164;BA.debugLine="Adapter_visited1.user_id=col.Get(\"user_id\")";
_adapter_visited1.user_id = (int)(BA.ObjectToNumber(_col.Get((Object)("user_id"))));
 //BA.debugLineNum = 165;BA.debugLine="Adapter_visited1.city=col.Get(\"city\")";
_adapter_visited1.city = BA.ObjectToString(_col.Get((Object)("city")));
 //BA.debugLineNum = 166;BA.debugLine="List_visited.Add(Adapter_visited1)";
mostCurrent._list_visited.Add((Object)(_adapter_visited1));
 //BA.debugLineNum = 167;BA.debugLine="Log(\"add visited\")";
anywheresoftware.b4a.keywords.Common.Log("add visited");
 }
};
 };
 //BA.debugLineNum = 173;BA.debugLine="map_day.Initialize";
mostCurrent._map_day.Initialize();
 //BA.debugLineNum = 175;BA.debugLine="spin_city.SelectedIndex=0";
mostCurrent._spin_city.setSelectedIndex((int) (0));
 //BA.debugLineNum = 176;BA.debugLine="Dim value As String=spin_city.SelectedItem";
_value = mostCurrent._spin_city.getSelectedItem();
 //BA.debugLineNum = 177;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step45 = 1;
final int limit45 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step45 > 0 && _i <= limit45) || (step45 < 0 && _i >= limit45); _i = ((int)(0 + _i + step45)) ) {
 //BA.debugLineNum = 178;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Res";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 180;BA.debugLine="If Adapter_Clock_Reserv1.city=value Then";
if ((_adapter_clock_reserv1.city).equals(_value)) { 
 //BA.debugLineNum = 182;BA.debugLine="map_day.Put(Adapter_Clock_Reserv1.day,i)";
mostCurrent._map_day.Put((Object)(_adapter_clock_reserv1.day),(Object)(_i));
 };
 }
};
 //BA.debugLineNum = 186;BA.debugLine="spin_day.SelectedIndex=0";
mostCurrent._spin_day.setSelectedIndex((int) (0));
 //BA.debugLineNum = 189;BA.debugLine="spin_month.Add(get_nameofmonth_string(persian.P";
mostCurrent._spin_month.Add(BA.ObjectToCharSequence(_get_nameofmonth_string(mostCurrent._persian.getPersianMonth())));
 //BA.debugLineNum = 190;BA.debugLine="spin_month.Add(get_nameofmonth_string(persian.P";
mostCurrent._spin_month.Add(BA.ObjectToCharSequence(_get_nameofmonth_string((int) (mostCurrent._persian.getPersianMonth()+1))));
 //BA.debugLineNum = 192;BA.debugLine="For i=1 To get_dayofmonth(get_numberofmonth_int";
{
final int step54 = 1;
final int limit54 = _get_dayofmonth(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()));
for (_i = (int) (1) ; (step54 > 0 && _i <= limit54) || (step54 < 0 && _i >= limit54); _i = ((int)(0 + _i + step54)) ) {
 //BA.debugLineNum = 193;BA.debugLine="Dim day_select As String= persian.getDayOfWeek";
_day_select = mostCurrent._persian.getDayOfWeekByPerianDate(BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+BA.NumberToString(_i));
 //BA.debugLineNum = 194;BA.debugLine="If map_day.ContainsKey(day_select)=True Then";
if (mostCurrent._map_day.ContainsKey((Object)(_day_select))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 195;BA.debugLine="spin_day.Add( day_select & \" \" & i & \" \" & ge";
mostCurrent._spin_day.Add(BA.ObjectToCharSequence(_day_select+" "+BA.NumberToString(_i)+" "+_get_nameofmonth_string(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))));
 };
 }
};
 //BA.debugLineNum = 199;BA.debugLine="Dim array_day() As String=Regex.Split(\" \",spin_";
_array_day = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",mostCurrent._spin_day.getSelectedItem());
 //BA.debugLineNum = 200;BA.debugLine="date_per=1396 & \"/\" & get_numberofmonth_int(sp";
mostCurrent._date_per = BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+_array_day[(int) (1)];
 //BA.debugLineNum = 203;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step62 = 1;
final int limit62 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step62 > 0 && _i <= limit62) || (step62 < 0 && _i >= limit62); _i = ((int)(0 + _i + step62)) ) {
 //BA.debugLineNum = 204;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Res";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 205;BA.debugLine="If Adapter_Clock_Reserv1.city=value And Adapte";
if ((_adapter_clock_reserv1.city).equals(_value) && (_adapter_clock_reserv1.day).equals(_array_day[(int) (0)])) { 
 //BA.debugLineNum = 207;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 208;BA.debugLine="parser.Initialize(Adapter_Clock_Reserv1.clock";
_parser.Initialize(_adapter_clock_reserv1.clocks);
 //BA.debugLineNum = 211;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 213;BA.debugLine="For Each colroot As String In root";
final anywheresoftware.b4a.BA.IterableList group68 = _root;
final int groupLen68 = group68.getSize();
for (int index68 = 0;index68 < groupLen68 ;index68++){
_colroot = BA.ObjectToString(group68.Get(index68));
 //BA.debugLineNum = 214;BA.debugLine="Dim reserved_clock As Boolean=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 215;BA.debugLine="For z=0 To List_visited.Size-1";
{
final int step70 = 1;
final int limit70 = (int) (mostCurrent._list_visited.getSize()-1);
for (_z = (int) (0) ; (step70 > 0 && _z <= limit70) || (step70 < 0 && _z >= limit70); _z = ((int)(0 + _z + step70)) ) {
 //BA.debugLineNum = 216;BA.debugLine="Dim Adapter_visited1 As Adapter_visited=Lis";
_adapter_visited1 = (hpksoftware.setak.rezerv._adapter_visited)(mostCurrent._list_visited.Get(_z));
 //BA.debugLineNum = 218;BA.debugLine="If Adapter_visited1.city=value And Adapter_";
if ((_adapter_visited1.city).equals(_value) && (_adapter_visited1.clock_visit).equals(_colroot) && (_adapter_visited1.day_visit).equals(mostCurrent._spin_day.getSelectedItem()) && (mostCurrent._date_per).equals(_adapter_visited1.date_visit)) { 
 //BA.debugLineNum = 219;BA.debugLine="Log(\"exist visit clock this time\")";
anywheresoftware.b4a.keywords.Common.Log("exist visit clock this time");
 //BA.debugLineNum = 220;BA.debugLine="reserved_clock=True";
_reserved_clock = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 222;BA.debugLine="Exit";
if (true) break;
 }else {
 //BA.debugLineNum = 224;BA.debugLine="reserved_clock=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 };
 }
};
 //BA.debugLineNum = 229;BA.debugLine="If reserved_clock=False Then spin_clock.Add(";
if (_reserved_clock==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._spin_clock.Add(BA.ObjectToCharSequence(_colroot));};
 }
;
 };
 }
};
 break; }
case 2: {
 //BA.debugLineNum = 239;BA.debugLine="Log(records)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_records));
 //BA.debugLineNum = 240;BA.debugLine="ToastMessageShow(\"نوبت شما با موفقیت ثبت شد\" &";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("نوبت شما با موفقیت ثبت شد"+anywheresoftware.b4a.keywords.Common.CRLF+"وضعیت:"+anywheresoftware.b4a.keywords.Common.CRLF+mostCurrent._spin_city.getSelectedItem()+anywheresoftware.b4a.keywords.Common.CRLF+mostCurrent._val_day+" "+mostCurrent._date_per+anywheresoftware.b4a.keywords.Common.CRLF+mostCurrent._spin_clock.getSelectedItem()),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 242;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 243;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 245;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 251;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 252;BA.debugLine="Log(\"no internet\")";
anywheresoftware.b4a.keywords.Common.Log("no internet");
 break; }
case 4: {
 //BA.debugLineNum = 254;BA.debugLine="Log(\"eror\")";
anywheresoftware.b4a.keywords.Common.Log("eror");
 //BA.debugLineNum = 255;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
}
;
 //BA.debugLineNum = 257;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 258;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static int  _get_dayofmonth(int _m) throws Exception{
 //BA.debugLineNum = 262;BA.debugLine="Sub get_dayofmonth(m As Int) As Int";
 //BA.debugLineNum = 264;BA.debugLine="Select m";
switch (_m) {
case 1: 
case 2: 
case 3: 
case 4: 
case 5: 
case 6: {
 //BA.debugLineNum = 266;BA.debugLine="Return 31";
if (true) return (int) (31);
 break; }
case 12: {
 //BA.debugLineNum = 268;BA.debugLine="Return 20";
if (true) return (int) (20);
 break; }
default: {
 //BA.debugLineNum = 270;BA.debugLine="Return 30";
if (true) return (int) (30);
 break; }
}
;
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return 0;
}
public static String  _get_nameofmonth_string(int _m) throws Exception{
 //BA.debugLineNum = 275;BA.debugLine="Sub get_nameofmonth_string(m As Int) As String";
 //BA.debugLineNum = 277;BA.debugLine="Select m";
switch (_m) {
case 1: {
 //BA.debugLineNum = 279;BA.debugLine="Return \"فروردین\"";
if (true) return "فروردین";
 break; }
case 2: {
 //BA.debugLineNum = 281;BA.debugLine="Return \"اردیبهشت\"";
if (true) return "اردیبهشت";
 break; }
case 3: {
 //BA.debugLineNum = 283;BA.debugLine="Return \"خرداد\"";
if (true) return "خرداد";
 break; }
case 4: {
 //BA.debugLineNum = 285;BA.debugLine="Return \"تیر\"";
if (true) return "تیر";
 break; }
case 5: {
 //BA.debugLineNum = 287;BA.debugLine="Return \"مرداد\"";
if (true) return "مرداد";
 break; }
case 6: {
 //BA.debugLineNum = 289;BA.debugLine="Return \"شهریور\"";
if (true) return "شهریور";
 break; }
case 7: {
 //BA.debugLineNum = 291;BA.debugLine="Return \"مهر\"";
if (true) return "مهر";
 break; }
case 8: {
 //BA.debugLineNum = 293;BA.debugLine="Return \"آبان\"";
if (true) return "آبان";
 break; }
case 9: {
 //BA.debugLineNum = 295;BA.debugLine="Return \"آذر\"";
if (true) return "آذر";
 break; }
case 10: {
 //BA.debugLineNum = 297;BA.debugLine="Return \"دی\"";
if (true) return "دی";
 break; }
case 11: {
 //BA.debugLineNum = 299;BA.debugLine="Return \"بهمن\"";
if (true) return "بهمن";
 break; }
case 12: {
 //BA.debugLineNum = 301;BA.debugLine="Return \"اسفند\"";
if (true) return "اسفند";
 break; }
}
;
 //BA.debugLineNum = 305;BA.debugLine="End Sub";
return "";
}
public static int  _get_numberofmonth_int(String _m) throws Exception{
 //BA.debugLineNum = 307;BA.debugLine="Sub get_numberofmonth_int(m As String) As Int";
 //BA.debugLineNum = 309;BA.debugLine="Select m";
switch (BA.switchObjectToInt(_m,"فروردین","اردیبهشت","خرداد","تیر","مرداد","شهریور","مهر","آبان","آذر","دی","بهمن","اسفند")) {
case 0: {
 //BA.debugLineNum = 311;BA.debugLine="Return 1";
if (true) return (int) (1);
 break; }
case 1: {
 //BA.debugLineNum = 313;BA.debugLine="Return 2";
if (true) return (int) (2);
 break; }
case 2: {
 //BA.debugLineNum = 315;BA.debugLine="Return 3";
if (true) return (int) (3);
 break; }
case 3: {
 //BA.debugLineNum = 317;BA.debugLine="Return 4";
if (true) return (int) (4);
 break; }
case 4: {
 //BA.debugLineNum = 319;BA.debugLine="Return 5";
if (true) return (int) (5);
 break; }
case 5: {
 //BA.debugLineNum = 321;BA.debugLine="Return 6";
if (true) return (int) (6);
 break; }
case 6: {
 //BA.debugLineNum = 323;BA.debugLine="Return 7";
if (true) return (int) (7);
 break; }
case 7: {
 //BA.debugLineNum = 325;BA.debugLine="Return 8";
if (true) return (int) (8);
 break; }
case 8: {
 //BA.debugLineNum = 327;BA.debugLine="Return 9";
if (true) return (int) (9);
 break; }
case 9: {
 //BA.debugLineNum = 329;BA.debugLine="Return 10";
if (true) return (int) (10);
 break; }
case 10: {
 //BA.debugLineNum = 331;BA.debugLine="Return 11";
if (true) return (int) (11);
 break; }
case 11: {
 //BA.debugLineNum = 333;BA.debugLine="Return 12";
if (true) return (int) (12);
 break; }
}
;
 //BA.debugLineNum = 337;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim map_day As Map";
mostCurrent._map_day = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 14;BA.debugLine="Dim date_per As String";
mostCurrent._date_per = "";
 //BA.debugLineNum = 15;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 17;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim daye_per As PersianDate";
mostCurrent._daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 19;BA.debugLine="Dim val_day As String";
mostCurrent._val_day = "";
 //BA.debugLineNum = 21;BA.debugLine="Private scrol_main As ScrollView";
mostCurrent._scrol_main = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private panel_history As Panel";
mostCurrent._panel_history = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim dp As DatePicker_persian_hpk";
mostCurrent._dp = new ir.hamsaa.persiandatepicker.PersianPicker_wrap();
 //BA.debugLineNum = 29;BA.debugLine="Private spin_clock As ACSpinner";
mostCurrent._spin_clock = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private spin_city As ACSpinner";
mostCurrent._spin_city = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private spin_day As ACSpinner";
mostCurrent._spin_day = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Type Adapter_Clock_Reserv(id As Int,city As Strin";
;
 //BA.debugLineNum = 35;BA.debugLine="Dim List_Clocks As List";
mostCurrent._list_clocks = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 36;BA.debugLine="Type Adapter_visited(id As Int,user_id As Int,cit";
;
 //BA.debugLineNum = 37;BA.debugLine="Dim List_visited As List";
mostCurrent._list_visited = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 38;BA.debugLine="Private btn_back As Button";
mostCurrent._btn_back = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim persian As ManamPerianDateUltimate";
mostCurrent._persian = new ir.anamsoftware.persiandateultimate.ManamPDUltimate();
 //BA.debugLineNum = 40;BA.debugLine="Private spin_month As ACSpinner";
mostCurrent._spin_month = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _spin_city_itemclick(int _position,Object _value) throws Exception{
String _value_city = "";
int _i = 0;
hpksoftware.setak.rezerv._adapter_clock_reserv _adapter_clock_reserv1 = null;
String _day_select = "";
String[] _array_day = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
String _colroot = "";
boolean _reserved_clock = false;
int _z = 0;
hpksoftware.setak.rezerv._adapter_visited _adapter_visited1 = null;
 //BA.debugLineNum = 383;BA.debugLine="Sub spin_city_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 384;BA.debugLine="spin_clock.Clear";
mostCurrent._spin_clock.Clear();
 //BA.debugLineNum = 385;BA.debugLine="spin_day.Clear";
mostCurrent._spin_day.Clear();
 //BA.debugLineNum = 386;BA.debugLine="map_day.Clear";
mostCurrent._map_day.Clear();
 //BA.debugLineNum = 387;BA.debugLine="Dim Value_city As String=spin_city.SelectedItem";
_value_city = mostCurrent._spin_city.getSelectedItem();
 //BA.debugLineNum = 388;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 389;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reser";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 391;BA.debugLine="If Adapter_Clock_Reserv1.city=Value_city Then";
if ((_adapter_clock_reserv1.city).equals(_value_city)) { 
 //BA.debugLineNum = 393;BA.debugLine="map_day.Put(Adapter_Clock_Reserv1.day,i)";
mostCurrent._map_day.Put((Object)(_adapter_clock_reserv1.day),(Object)(_i));
 };
 }
};
 //BA.debugLineNum = 397;BA.debugLine="For i=1 To get_dayofmonth(get_numberofmonth_int(s";
{
final int step11 = 1;
final int limit11 = _get_dayofmonth(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()));
for (_i = (int) (1) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 398;BA.debugLine="Dim day_select As String= persian.getDayOfWeekBy";
_day_select = mostCurrent._persian.getDayOfWeekByPerianDate(BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+BA.NumberToString(_i));
 //BA.debugLineNum = 399;BA.debugLine="If map_day.ContainsKey(day_select)=True Then";
if (mostCurrent._map_day.ContainsKey((Object)(_day_select))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 400;BA.debugLine="spin_day.Add( day_select & \" \" & i & \" \" & get_";
mostCurrent._spin_day.Add(BA.ObjectToCharSequence(_day_select+" "+BA.NumberToString(_i)+" "+_get_nameofmonth_string(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))));
 };
 }
};
 //BA.debugLineNum = 404;BA.debugLine="Dim array_day() As String=Regex.Split(\" \",val_day";
_array_day = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",mostCurrent._val_day);
 //BA.debugLineNum = 405;BA.debugLine="date_per=1396 & \"/\" & get_numberofmonth_int(spin_";
mostCurrent._date_per = BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+_array_day[(int) (1)];
 //BA.debugLineNum = 409;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step19 = 1;
final int limit19 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step19 > 0 && _i <= limit19) || (step19 < 0 && _i >= limit19); _i = ((int)(0 + _i + step19)) ) {
 //BA.debugLineNum = 410;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reser";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 411;BA.debugLine="If Adapter_Clock_Reserv1.city=spin_city.Selected";
if ((_adapter_clock_reserv1.city).equals(mostCurrent._spin_city.getSelectedItem())) { 
 //BA.debugLineNum = 413;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 414;BA.debugLine="parser.Initialize(Adapter_Clock_Reserv1.clocks)";
_parser.Initialize(_adapter_clock_reserv1.clocks);
 //BA.debugLineNum = 417;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 419;BA.debugLine="For Each colroot As String In root";
final anywheresoftware.b4a.BA.IterableList group25 = _root;
final int groupLen25 = group25.getSize();
for (int index25 = 0;index25 < groupLen25 ;index25++){
_colroot = BA.ObjectToString(group25.Get(index25));
 //BA.debugLineNum = 420;BA.debugLine="LogColor(colroot  , Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogColor(_colroot,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 421;BA.debugLine="Dim reserved_clock As Boolean=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 422;BA.debugLine="For z=0 To List_visited.Size-1";
{
final int step28 = 1;
final int limit28 = (int) (mostCurrent._list_visited.getSize()-1);
for (_z = (int) (0) ; (step28 > 0 && _z <= limit28) || (step28 < 0 && _z >= limit28); _z = ((int)(0 + _z + step28)) ) {
 //BA.debugLineNum = 423;BA.debugLine="Dim Adapter_visited1 As Adapter_visited=List_";
_adapter_visited1 = (hpksoftware.setak.rezerv._adapter_visited)(mostCurrent._list_visited.Get(_z));
 //BA.debugLineNum = 424;BA.debugLine="LogColor(Adapter_visited1.city & \"|\" & Value";
anywheresoftware.b4a.keywords.Common.LogColor(_adapter_visited1.city+"|"+BA.ObjectToString(_value),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 425;BA.debugLine="LogColor(Adapter_visited1.clock_visit & \"|\" &";
anywheresoftware.b4a.keywords.Common.LogColor(_adapter_visited1.clock_visit+"|"+_colroot,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 426;BA.debugLine="LogColor(Adapter_visited1.day_visit & \"|\" & a";
anywheresoftware.b4a.keywords.Common.LogColor(_adapter_visited1.day_visit+"|"+_array_day[(int) (0)],anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 427;BA.debugLine="LogColor(date_per & \"|\" & Adapter_visited1.da";
anywheresoftware.b4a.keywords.Common.LogColor(mostCurrent._date_per+"|"+_adapter_visited1.date_visit,anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 428;BA.debugLine="If Adapter_visited1.city=Value And Adapter_vi";
if ((_adapter_visited1.city).equals(BA.ObjectToString(_value)) && (_adapter_visited1.clock_visit).equals(_colroot) && (_adapter_visited1.day_visit).equals(_array_day[(int) (0)]) && (mostCurrent._date_per).equals(_adapter_visited1.date_visit)) { 
 //BA.debugLineNum = 429;BA.debugLine="Log(\"exist visit clock this time\")";
anywheresoftware.b4a.keywords.Common.Log("exist visit clock this time");
 //BA.debugLineNum = 430;BA.debugLine="reserved_clock=True";
_reserved_clock = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 432;BA.debugLine="Exit";
if (true) break;
 }else {
 //BA.debugLineNum = 434;BA.debugLine="reserved_clock=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 };
 }
};
 //BA.debugLineNum = 439;BA.debugLine="If reserved_clock=False Then spin_clock.Add(co";
if (_reserved_clock==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._spin_clock.Add(BA.ObjectToCharSequence(_colroot));};
 }
;
 };
 }
};
 //BA.debugLineNum = 447;BA.debugLine="End Sub";
return "";
}
public static String  _spin_clock_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 344;BA.debugLine="Sub spin_clock_ItemClick (Position As Int, Value A";
 //BA.debugLineNum = 346;BA.debugLine="End Sub";
return "";
}
public static String  _spin_day_itemclick(int _position,Object _value) throws Exception{
String _value_city = "";
int _i = 0;
hpksoftware.setak.rezerv._adapter_clock_reserv _adapter_clock_reserv1 = null;
String _day_select = "";
String[] _array_day = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
String _colroot = "";
boolean _reserved_clock = false;
int _z = 0;
hpksoftware.setak.rezerv._adapter_visited _adapter_visited1 = null;
 //BA.debugLineNum = 449;BA.debugLine="Sub spin_day_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 450;BA.debugLine="spin_day.Clear";
mostCurrent._spin_day.Clear();
 //BA.debugLineNum = 451;BA.debugLine="spin_clock.Clear";
mostCurrent._spin_clock.Clear();
 //BA.debugLineNum = 452;BA.debugLine="map_day.Clear";
mostCurrent._map_day.Clear();
 //BA.debugLineNum = 453;BA.debugLine="Dim Value_city As String=spin_city.SelectedItem";
_value_city = mostCurrent._spin_city.getSelectedItem();
 //BA.debugLineNum = 454;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 455;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reser";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 457;BA.debugLine="If Adapter_Clock_Reserv1.city=Value_city Then";
if ((_adapter_clock_reserv1.city).equals(_value_city)) { 
 //BA.debugLineNum = 459;BA.debugLine="If map_day.ContainsKey(Adapter_Clock_Reserv1.da";
if (mostCurrent._map_day.ContainsKey((Object)(_adapter_clock_reserv1.day))==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._map_day.Put((Object)(_adapter_clock_reserv1.day),(Object)(_i));};
 };
 }
};
 //BA.debugLineNum = 463;BA.debugLine="For i=1 To get_dayofmonth(get_numberofmonth_int(s";
{
final int step11 = 1;
final int limit11 = _get_dayofmonth(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()));
for (_i = (int) (1) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 464;BA.debugLine="Dim day_select As String= persian.getDayOfWeekBy";
_day_select = mostCurrent._persian.getDayOfWeekByPerianDate(BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+BA.NumberToString(_i));
 //BA.debugLineNum = 465;BA.debugLine="If map_day.ContainsKey(day_select)=True Then";
if (mostCurrent._map_day.ContainsKey((Object)(_day_select))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 466;BA.debugLine="spin_day.Add( day_select & \" \" & i & \" \" & get_";
mostCurrent._spin_day.Add(BA.ObjectToCharSequence(_day_select+" "+BA.NumberToString(_i)+" "+_get_nameofmonth_string(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))));
 };
 }
};
 //BA.debugLineNum = 470;BA.debugLine="Dim array_day() As String=Regex.Split(\" \",Value)";
_array_day = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",BA.ObjectToString(_value));
 //BA.debugLineNum = 472;BA.debugLine="date_per =1396 & \"/\" & get_numberofmonth_int(spi";
mostCurrent._date_per = BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+_array_day[(int) (1)];
 //BA.debugLineNum = 474;BA.debugLine="LogColor(List_visited,Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogColor(BA.ObjectToString(mostCurrent._list_visited),anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 475;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step20 = 1;
final int limit20 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step20 > 0 && _i <= limit20) || (step20 < 0 && _i >= limit20); _i = ((int)(0 + _i + step20)) ) {
 //BA.debugLineNum = 476;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reser";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 477;BA.debugLine="If Adapter_Clock_Reserv1.city=spin_city.Selected";
if ((_adapter_clock_reserv1.city).equals(mostCurrent._spin_city.getSelectedItem())) { 
 //BA.debugLineNum = 479;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 480;BA.debugLine="parser.Initialize(Adapter_Clock_Reserv1.clocks)";
_parser.Initialize(_adapter_clock_reserv1.clocks);
 //BA.debugLineNum = 481;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 483;BA.debugLine="For Each colroot As String In root";
final anywheresoftware.b4a.BA.IterableList group26 = _root;
final int groupLen26 = group26.getSize();
for (int index26 = 0;index26 < groupLen26 ;index26++){
_colroot = BA.ObjectToString(group26.Get(index26));
 //BA.debugLineNum = 485;BA.debugLine="Dim reserved_clock As Boolean=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 487;BA.debugLine="For z=0 To List_visited.Size-1";
{
final int step28 = 1;
final int limit28 = (int) (mostCurrent._list_visited.getSize()-1);
for (_z = (int) (0) ; (step28 > 0 && _z <= limit28) || (step28 < 0 && _z >= limit28); _z = ((int)(0 + _z + step28)) ) {
 //BA.debugLineNum = 488;BA.debugLine="Dim Adapter_visited1 As Adapter_visited=List_";
_adapter_visited1 = (hpksoftware.setak.rezerv._adapter_visited)(mostCurrent._list_visited.Get(_z));
 //BA.debugLineNum = 489;BA.debugLine="If Adapter_visited1.city=spin_city.SelectedIt";
if ((_adapter_visited1.city).equals(mostCurrent._spin_city.getSelectedItem()) && (_adapter_visited1.clock_visit).equals(_colroot) && (_adapter_visited1.day_visit).equals(_array_day[(int) (0)]) && (mostCurrent._date_per).equals(_adapter_visited1.date_visit)) { 
 //BA.debugLineNum = 490;BA.debugLine="Log(\"exist visit clock this time\")";
anywheresoftware.b4a.keywords.Common.Log("exist visit clock this time");
 //BA.debugLineNum = 491;BA.debugLine="reserved_clock=True";
_reserved_clock = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 493;BA.debugLine="Exit";
if (true) break;
 }else {
 //BA.debugLineNum = 495;BA.debugLine="reserved_clock=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 };
 }
};
 //BA.debugLineNum = 500;BA.debugLine="If reserved_clock=False Then";
if (_reserved_clock==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 501;BA.debugLine="LogColor(colroot  , Colors.Blue)";
anywheresoftware.b4a.keywords.Common.LogColor(_colroot,anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 502;BA.debugLine="spin_clock.Add(colroot)";
mostCurrent._spin_clock.Add(BA.ObjectToCharSequence(_colroot));
 };
 }
;
 };
 }
};
 //BA.debugLineNum = 510;BA.debugLine="val_day=Value";
mostCurrent._val_day = BA.ObjectToString(_value);
 //BA.debugLineNum = 511;BA.debugLine="Log(\"****** \" & Value)";
anywheresoftware.b4a.keywords.Common.Log("****** "+BA.ObjectToString(_value));
 //BA.debugLineNum = 512;BA.debugLine="End Sub";
return "";
}
public static String  _spin_month_itemclick(int _position,Object _value) throws Exception{
String _value_city = "";
int _i = 0;
hpksoftware.setak.rezerv._adapter_clock_reserv _adapter_clock_reserv1 = null;
String _day_select = "";
String[] _array_day = null;
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.List _root = null;
String _colroot = "";
boolean _reserved_clock = false;
int _z = 0;
hpksoftware.setak.rezerv._adapter_visited _adapter_visited1 = null;
 //BA.debugLineNum = 519;BA.debugLine="Sub spin_month_ItemClick (Position As Int, Value A";
 //BA.debugLineNum = 520;BA.debugLine="spin_day.Clear";
mostCurrent._spin_day.Clear();
 //BA.debugLineNum = 521;BA.debugLine="spin_clock.Clear";
mostCurrent._spin_clock.Clear();
 //BA.debugLineNum = 522;BA.debugLine="map_day.Clear";
mostCurrent._map_day.Clear();
 //BA.debugLineNum = 523;BA.debugLine="Dim Value_city As String=spin_city.SelectedItem";
_value_city = mostCurrent._spin_city.getSelectedItem();
 //BA.debugLineNum = 524;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 525;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reser";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 527;BA.debugLine="If Adapter_Clock_Reserv1.city=Value_city Then";
if ((_adapter_clock_reserv1.city).equals(_value_city)) { 
 //BA.debugLineNum = 529;BA.debugLine="map_day.Put(Adapter_Clock_Reserv1.day,i)";
mostCurrent._map_day.Put((Object)(_adapter_clock_reserv1.day),(Object)(_i));
 };
 }
};
 //BA.debugLineNum = 533;BA.debugLine="For i=1 To get_dayofmonth(get_numberofmonth_int(s";
{
final int step11 = 1;
final int limit11 = _get_dayofmonth(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()));
for (_i = (int) (1) ; (step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11); _i = ((int)(0 + _i + step11)) ) {
 //BA.debugLineNum = 534;BA.debugLine="Dim day_select As String= persian.getDayOfWeekBy";
_day_select = mostCurrent._persian.getDayOfWeekByPerianDate(BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+BA.NumberToString(_i));
 //BA.debugLineNum = 535;BA.debugLine="If map_day.ContainsKey(day_select)=True Then";
if (mostCurrent._map_day.ContainsKey((Object)(_day_select))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 536;BA.debugLine="spin_day.Add( day_select & \" \" & i & \" \" & get_";
mostCurrent._spin_day.Add(BA.ObjectToCharSequence(_day_select+" "+BA.NumberToString(_i)+" "+_get_nameofmonth_string(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))));
 };
 }
};
 //BA.debugLineNum = 540;BA.debugLine="Dim array_day() As String=Regex.Split(\" \",val_day";
_array_day = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",mostCurrent._val_day);
 //BA.debugLineNum = 541;BA.debugLine="date_per=1396 & \"/\" & get_numberofmonth_int(spin_";
mostCurrent._date_per = BA.NumberToString(1396)+"/"+BA.NumberToString(_get_numberofmonth_int(mostCurrent._spin_month.getSelectedItem()))+"/"+_array_day[(int) (1)];
 //BA.debugLineNum = 544;BA.debugLine="For i=0 To List_Clocks.Size-1";
{
final int step19 = 1;
final int limit19 = (int) (mostCurrent._list_clocks.getSize()-1);
for (_i = (int) (0) ; (step19 > 0 && _i <= limit19) || (step19 < 0 && _i >= limit19); _i = ((int)(0 + _i + step19)) ) {
 //BA.debugLineNum = 545;BA.debugLine="Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reser";
_adapter_clock_reserv1 = (hpksoftware.setak.rezerv._adapter_clock_reserv)(mostCurrent._list_clocks.Get(_i));
 //BA.debugLineNum = 546;BA.debugLine="If Adapter_Clock_Reserv1.city=spin_city.Selected";
if ((_adapter_clock_reserv1.city).equals(mostCurrent._spin_city.getSelectedItem())) { 
 //BA.debugLineNum = 548;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 549;BA.debugLine="parser.Initialize(Adapter_Clock_Reserv1.clocks)";
_parser.Initialize(_adapter_clock_reserv1.clocks);
 //BA.debugLineNum = 552;BA.debugLine="Dim root As List = parser.NextArray";
_root = new anywheresoftware.b4a.objects.collections.List();
_root = _parser.NextArray();
 //BA.debugLineNum = 554;BA.debugLine="For Each colroot As String In root";
final anywheresoftware.b4a.BA.IterableList group25 = _root;
final int groupLen25 = group25.getSize();
for (int index25 = 0;index25 < groupLen25 ;index25++){
_colroot = BA.ObjectToString(group25.Get(index25));
 //BA.debugLineNum = 555;BA.debugLine="Dim reserved_clock As Boolean=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 556;BA.debugLine="For z=0 To List_visited.Size-1";
{
final int step27 = 1;
final int limit27 = (int) (mostCurrent._list_visited.getSize()-1);
for (_z = (int) (0) ; (step27 > 0 && _z <= limit27) || (step27 < 0 && _z >= limit27); _z = ((int)(0 + _z + step27)) ) {
 //BA.debugLineNum = 557;BA.debugLine="Dim Adapter_visited1 As Adapter_visited=List_";
_adapter_visited1 = (hpksoftware.setak.rezerv._adapter_visited)(mostCurrent._list_visited.Get(_z));
 //BA.debugLineNum = 559;BA.debugLine="If Adapter_visited1.city=spin_city.SelectedIt";
if ((_adapter_visited1.city).equals(mostCurrent._spin_city.getSelectedItem()) && (_adapter_visited1.clock_visit).equals(_colroot) && (_adapter_visited1.day_visit).equals(mostCurrent._spin_day.getSelectedItem()) && (mostCurrent._date_per).equals(_adapter_visited1.date_visit)) { 
 //BA.debugLineNum = 560;BA.debugLine="Log(\"exist visit clock this time\")";
anywheresoftware.b4a.keywords.Common.Log("exist visit clock this time");
 //BA.debugLineNum = 561;BA.debugLine="reserved_clock=True";
_reserved_clock = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 563;BA.debugLine="Exit";
if (true) break;
 }else {
 //BA.debugLineNum = 565;BA.debugLine="reserved_clock=False";
_reserved_clock = anywheresoftware.b4a.keywords.Common.False;
 };
 }
};
 //BA.debugLineNum = 570;BA.debugLine="If reserved_clock=False Then spin_clock.Add(co";
if (_reserved_clock==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._spin_clock.Add(BA.ObjectToCharSequence(_colroot));};
 }
;
 };
 }
};
 //BA.debugLineNum = 576;BA.debugLine="End Sub";
return "";
}
}
