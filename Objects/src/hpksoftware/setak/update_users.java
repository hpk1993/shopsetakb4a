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

public class update_users extends Activity implements B4AActivity{
	public static update_users mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.update_users");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (update_users).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.update_users");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.update_users", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (update_users) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (update_users) Resume **");
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
		return update_users.class;
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
        BA.LogInfo("** Activity (update_users) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (update_users) Resume **");
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
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public anywheresoftware.b4a.object.RippleViewWrapper _effect_btn1 = null;
public dmax.dialog.Spotsd _progress_spot = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public static int _id_user = 0;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_back = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol_main = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_search = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_share = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_sabad = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_name = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_lname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_meli = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_mobile = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_tel = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spin_sex = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spin_year = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spin_month = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spin_day = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_pass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_address = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_email = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_job = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_post = null;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public anywheresoftware.b4a.objects.StringUtils _su = null;
public anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bc = null;
public anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p5 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p8 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p9 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p10 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p11 = null;
public anywheresoftware.b4a.objects.PanelWrapper _p12 = null;
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
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlbg = null;
anywheresoftware.b4a.objects.collections.List _lst_user = null;
int _top_old = 0;
int _i = 0;
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"scrol_layout\")";
mostCurrent._activity.LoadLayout("scrol_layout",mostCurrent.activityBA);
 //BA.debugLineNum = 49;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 50;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 51;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 52;BA.debugLine="scrol_main.Panel.LoadLayout(\"update_users\")";
mostCurrent._scrol_main.getPanel().LoadLayout("update_users",mostCurrent.activityBA);
 //BA.debugLineNum = 53;BA.debugLine="For Each v As View In Panel1.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group6 = mostCurrent._panel1.GetAllViewsRecursive();
final int groupLen6 = group6.getSize();
for (int index6 = 0;index6 < groupLen6 ;index6++){
_v.setObject((android.view.View)(group6.Get(index6)));
 //BA.debugLineNum = 54;BA.debugLine="If v Is Panel Then";
if (_v.getObjectOrNull() instanceof android.view.ViewGroup) { 
 //BA.debugLineNum = 55;BA.debugLine="Dim pnlbg As Panel=v";
_pnlbg = new anywheresoftware.b4a.objects.PanelWrapper();
_pnlbg.setObject((android.view.ViewGroup)(_v.getObject()));
 //BA.debugLineNum = 56;BA.debugLine="If pnlbg.Tag=\"bg_p\" Then";
if ((_pnlbg.getTag()).equals((Object)("bg_p"))) { 
 //BA.debugLineNum = 57;BA.debugLine="pnlbg.Background=color_header";
_pnlbg.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 };
 };
 }
;
 //BA.debugLineNum = 61;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,update_users.getObject());
 //BA.debugLineNum = 62;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 65;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 66;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,update_users.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff13879a),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 67;BA.debugLine="ime.Initialize(\"IME\")";
mostCurrent._ime.Initialize("IME");
 //BA.debugLineNum = 68;BA.debugLine="ime.AddHeightChangedEvent";
mostCurrent._ime.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 69;BA.debugLine="effect_btn1.Initialize(footer,Colors.White,400,Fa";
mostCurrent._effect_btn1.Initialize(mostCurrent.activityBA,(android.view.View)(mostCurrent._footer.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,(int) (400),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 70;BA.debugLine="scrol_main.Panel.Height=900dip'txt_address.Top +";
mostCurrent._scrol_main.getPanel().setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (900)));
 //BA.debugLineNum = 73;BA.debugLine="Label3.Text=\"ویرایش مشخصات\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("ویرایش مشخصات"));
 //BA.debugLineNum = 74;BA.debugLine="footer.Text=\"ذخیره تغییرات\"";
mostCurrent._footer.setText(BA.ObjectToCharSequence("ذخیره تغییرات"));
 //BA.debugLineNum = 76;BA.debugLine="Dim lst_user As List=File.ReadList(myLibrary.rute";
_lst_user = new anywheresoftware.b4a.objects.collections.List();
_lst_user = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 77;BA.debugLine="id_user=lst_user.Get(3)";
_id_user = (int)(BA.ObjectToNumber(_lst_user.Get((int) (3))));
 //BA.debugLineNum = 79;BA.debugLine="spin_sex.Add(\"زن\")";
mostCurrent._spin_sex.Add("زن");
 //BA.debugLineNum = 80;BA.debugLine="spin_sex.Add(\"مرد\")";
mostCurrent._spin_sex.Add("مرد");
 //BA.debugLineNum = 81;BA.debugLine="spin_sex.SelectedIndex=0";
mostCurrent._spin_sex.setSelectedIndex((int) (0));
 //BA.debugLineNum = 82;BA.debugLine="spin_sex.DropdownBackgroundColor=Colors.White";
mostCurrent._spin_sex.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 83;BA.debugLine="spin_sex.DropdownTextColor=Colors.Black";
mostCurrent._spin_sex.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 85;BA.debugLine="spin_year.Add(\"سال تولد\")";
mostCurrent._spin_year.Add("سال تولد");
 //BA.debugLineNum = 86;BA.debugLine="spin_month.Add(\"ماه\")";
mostCurrent._spin_month.Add("ماه");
 //BA.debugLineNum = 87;BA.debugLine="spin_day.Add(\"روز\")";
mostCurrent._spin_day.Add("روز");
 //BA.debugLineNum = 89;BA.debugLine="Dim top_old As Int=myLibrary.persion_date(\"y\")";
_top_old = (int)(BA.ObjectToNumber(mostCurrent._mylibrary._persion_date(mostCurrent.activityBA,"y")));
 //BA.debugLineNum = 90;BA.debugLine="For i=1300 To top_old-10";
{
final int step35 = 1;
final int limit35 = (int) (_top_old-10);
for (_i = (int) (1300) ; (step35 > 0 && _i <= limit35) || (step35 < 0 && _i >= limit35); _i = ((int)(0 + _i + step35)) ) {
 //BA.debugLineNum = 91;BA.debugLine="spin_year.Add(myLibrary.CovertEnglish2Persian(i";
mostCurrent._spin_year.Add(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,BA.NumberToString(_i)));
 }
};
 //BA.debugLineNum = 93;BA.debugLine="For i=1 To 12";
{
final int step38 = 1;
final int limit38 = (int) (12);
for (_i = (int) (1) ; (step38 > 0 && _i <= limit38) || (step38 < 0 && _i >= limit38); _i = ((int)(0 + _i + step38)) ) {
 //BA.debugLineNum = 94;BA.debugLine="spin_month.Add(myLibrary.CovertEnglish2Persian(";
mostCurrent._spin_month.Add(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,BA.NumberToString(_i)));
 }
};
 //BA.debugLineNum = 96;BA.debugLine="For i=1 To 31";
{
final int step41 = 1;
final int limit41 = (int) (31);
for (_i = (int) (1) ; (step41 > 0 && _i <= limit41) || (step41 < 0 && _i >= limit41); _i = ((int)(0 + _i + step41)) ) {
 //BA.debugLineNum = 97;BA.debugLine="spin_day.Add(myLibrary.CovertEnglish2Persian(i)";
mostCurrent._spin_day.Add(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,BA.NumberToString(_i)));
 }
};
 //BA.debugLineNum = 99;BA.debugLine="spin_day.SelectedIndex=0";
mostCurrent._spin_day.setSelectedIndex((int) (0));
 //BA.debugLineNum = 100;BA.debugLine="spin_day.DropdownBackgroundColor=Colors.White";
mostCurrent._spin_day.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 101;BA.debugLine="spin_day.DropdownTextColor=Colors.Black";
mostCurrent._spin_day.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 103;BA.debugLine="spin_month.SelectedIndex=0";
mostCurrent._spin_month.setSelectedIndex((int) (0));
 //BA.debugLineNum = 104;BA.debugLine="spin_month.DropdownBackgroundColor=Colors.White";
mostCurrent._spin_month.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 105;BA.debugLine="spin_month.DropdownTextColor=Colors.Black";
mostCurrent._spin_month.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 107;BA.debugLine="spin_year.SelectedIndex=0";
mostCurrent._spin_year.setSelectedIndex((int) (0));
 //BA.debugLineNum = 108;BA.debugLine="spin_year.DropdownBackgroundColor=Colors.White";
mostCurrent._spin_year.setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 109;BA.debugLine="spin_year.DropdownTextColor=Colors.Black";
mostCurrent._spin_year.setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 112;BA.debugLine="get_user(id_user)";
_get_user(_id_user);
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 232;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 233;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 234;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 235;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 236;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 238;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 117;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,update_users.getObject());
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 125;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 126;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 127;BA.debugLine="Return True";
if (true) return BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
int _sex = 0;
String[] _date_born = null;
int _i = 0;
byte[] _byte1 = null;
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 130;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 131;BA.debugLine="Try";
try { //BA.debugLineNum = 132;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_user"),(Object)("update_user"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 134;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 135;BA.debugLine="Dim map1 As Map=records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get((int) (0))));
 //BA.debugLineNum = 137;BA.debugLine="txt_name.Text=map1.Get(\"name\")";
mostCurrent._txt_name.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name"))));
 //BA.debugLineNum = 138;BA.debugLine="If txt_name.Text=\"null\" Then txt_name.Text=\"\"";
if ((mostCurrent._txt_name.getText()).equals("null")) { 
mostCurrent._txt_name.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 140;BA.debugLine="txt_lname.Text=map1.Get(\"lname\")";
mostCurrent._txt_lname.setText(BA.ObjectToCharSequence(_map1.Get((Object)("lname"))));
 //BA.debugLineNum = 141;BA.debugLine="If txt_lname.Text=\"null\" Then txt_lname.Text=";
if ((mostCurrent._txt_lname.getText()).equals("null")) { 
mostCurrent._txt_lname.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 143;BA.debugLine="txt_meli.Text=map1.Get(\"code_meli\")";
mostCurrent._txt_meli.setText(BA.ObjectToCharSequence(_map1.Get((Object)("code_meli"))));
 //BA.debugLineNum = 144;BA.debugLine="If txt_meli.Text=\"null\" Then txt_meli.Text=\"\"";
if ((mostCurrent._txt_meli.getText()).equals("null")) { 
mostCurrent._txt_meli.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 146;BA.debugLine="txt_mobile.Text=map1.Get(\"mobile\")";
mostCurrent._txt_mobile.setText(BA.ObjectToCharSequence(_map1.Get((Object)("mobile"))));
 //BA.debugLineNum = 147;BA.debugLine="If txt_mobile.Text=\"null\" Then txt_mobile.Tex";
if ((mostCurrent._txt_mobile.getText()).equals("null")) { 
mostCurrent._txt_mobile.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 149;BA.debugLine="txt_tel.Text=map1.Get(\"tel\")";
mostCurrent._txt_tel.setText(BA.ObjectToCharSequence(_map1.Get((Object)("tel"))));
 //BA.debugLineNum = 150;BA.debugLine="If txt_tel.Text=\"null\" Then txt_tel.Text=\"\"";
if ((mostCurrent._txt_tel.getText()).equals("null")) { 
mostCurrent._txt_tel.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 152;BA.debugLine="Dim sex As Int=map1.Get(\"sex\")";
_sex = (int)(BA.ObjectToNumber(_map1.Get((Object)("sex"))));
 //BA.debugLineNum = 153;BA.debugLine="spin_sex.SelectedIndex=sex";
mostCurrent._spin_sex.setSelectedIndex(_sex);
 //BA.debugLineNum = 155;BA.debugLine="txt_post.Text=map1.Get(\"post_code\")";
mostCurrent._txt_post.setText(BA.ObjectToCharSequence(_map1.Get((Object)("post_code"))));
 //BA.debugLineNum = 156;BA.debugLine="If txt_post.Text=\"null\" Then txt_post.Text=\"\"";
if ((mostCurrent._txt_post.getText()).equals("null")) { 
mostCurrent._txt_post.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 158;BA.debugLine="txt_job.Text=map1.Get(\"job\")";
mostCurrent._txt_job.setText(BA.ObjectToCharSequence(_map1.Get((Object)("job"))));
 //BA.debugLineNum = 159;BA.debugLine="If txt_job.Text=\"null\" Then txt_job.Text=\"\"";
if ((mostCurrent._txt_job.getText()).equals("null")) { 
mostCurrent._txt_job.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 160;BA.debugLine="Try";
try { //BA.debugLineNum = 161;BA.debugLine="Dim date_born() As String=Regex.Split(\"/\",ma";
_date_born = anywheresoftware.b4a.keywords.Common.Regex.Split("/",BA.ObjectToString(_map1.Get((Object)("born"))));
 //BA.debugLineNum = 162;BA.debugLine="For i=0 To spin_year.Size-1";
{
final int step24 = 1;
final int limit24 = (int) (mostCurrent._spin_year.getSize()-1);
for (_i = (int) (0) ; (step24 > 0 && _i <= limit24) || (step24 < 0 && _i >= limit24); _i = ((int)(0 + _i + step24)) ) {
 //BA.debugLineNum = 163;BA.debugLine="If spin_year.GetItem(i).CompareTo(myLibrary";
if (mostCurrent._spin_year.GetItem(_i).compareTo(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,_date_born[(int) (0)]))==0) { 
 //BA.debugLineNum = 164;BA.debugLine="spin_year.SelectedIndex=i";
mostCurrent._spin_year.setSelectedIndex(_i);
 //BA.debugLineNum = 165;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 168;BA.debugLine="For i=0 To spin_month.Size-1";
{
final int step30 = 1;
final int limit30 = (int) (mostCurrent._spin_month.getSize()-1);
for (_i = (int) (0) ; (step30 > 0 && _i <= limit30) || (step30 < 0 && _i >= limit30); _i = ((int)(0 + _i + step30)) ) {
 //BA.debugLineNum = 169;BA.debugLine="If spin_month.GetItem(i).CompareTo(myLibrar";
if (mostCurrent._spin_month.GetItem(_i).compareTo(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,_date_born[(int) (1)]))==0) { 
 //BA.debugLineNum = 170;BA.debugLine="spin_month.SelectedIndex=i";
mostCurrent._spin_month.setSelectedIndex(_i);
 //BA.debugLineNum = 171;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 174;BA.debugLine="For i=0 To spin_day.Size-1";
{
final int step36 = 1;
final int limit36 = (int) (mostCurrent._spin_day.getSize()-1);
for (_i = (int) (0) ; (step36 > 0 && _i <= limit36) || (step36 < 0 && _i >= limit36); _i = ((int)(0 + _i + step36)) ) {
 //BA.debugLineNum = 175;BA.debugLine="If spin_day.GetItem(i).CompareTo(myLibrary.";
if (mostCurrent._spin_day.GetItem(_i).compareTo(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,_date_born[(int) (2)]))==0) { 
 //BA.debugLineNum = 176;BA.debugLine="spin_day.SelectedIndex=i";
mostCurrent._spin_day.setSelectedIndex(_i);
 //BA.debugLineNum = 177;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 } 
       catch (Exception e43) {
			processBA.setLastException(e43); };
 //BA.debugLineNum = 184;BA.debugLine="txt_email.Text=map1.Get(\"email\")";
mostCurrent._txt_email.setText(BA.ObjectToCharSequence(_map1.Get((Object)("email"))));
 //BA.debugLineNum = 185;BA.debugLine="If txt_email.Text=\"null\" Then txt_email.Text=";
if ((mostCurrent._txt_email.getText()).equals("null")) { 
mostCurrent._txt_email.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 187;BA.debugLine="txt_address.Text=map1.Get(\"address\")";
mostCurrent._txt_address.setText(BA.ObjectToCharSequence(_map1.Get((Object)("address"))));
 //BA.debugLineNum = 188;BA.debugLine="If txt_address.Text=\"null\" Then txt_address.T";
if ((mostCurrent._txt_address.getText()).equals("null")) { 
mostCurrent._txt_address.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 191;BA.debugLine="If txt_pass.Text=\"null\" Then txt_pass.Text=\"\"";
if ((mostCurrent._txt_pass.getText()).equals("null")) { 
mostCurrent._txt_pass.setText(BA.ObjectToCharSequence(""));};
 //BA.debugLineNum = 192;BA.debugLine="Dim byte1() As Byte";
_byte1 = new byte[(int) (0)];
;
 //BA.debugLineNum = 193;BA.debugLine="byte1=su.DecodeBase64(map1.Get(\"password\"))";
_byte1 = mostCurrent._su.DecodeBase64(BA.ObjectToString(_map1.Get((Object)("password"))));
 //BA.debugLineNum = 194;BA.debugLine="txt_pass.Text=bc.StringFromBytes(byte1,\"utf-8";
mostCurrent._txt_pass.setText(BA.ObjectToCharSequence(mostCurrent._bc.StringFromBytes(_byte1,"utf-8")));
 };
 //BA.debugLineNum = 197;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 1: {
 //BA.debugLineNum = 200;BA.debugLine="Dim lst As List=File.ReadList(myLibrary.rute,S";
_lst = new anywheresoftware.b4a.objects.collections.List();
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 201;BA.debugLine="lst.Set(0,txt_mobile.Text.Trim)";
_lst.Set((int) (0),(Object)(mostCurrent._txt_mobile.getText().trim()));
 //BA.debugLineNum = 202;BA.debugLine="lst.Set(1,txt_name.Text.Trim)";
_lst.Set((int) (1),(Object)(mostCurrent._txt_name.getText().trim()));
 //BA.debugLineNum = 203;BA.debugLine="lst.Set(2,txt_lname.Text.Trim)";
_lst.Set((int) (2),(Object)(mostCurrent._txt_lname.getText().trim()));
 //BA.debugLineNum = 204;BA.debugLine="File.WriteList(myLibrary.rute,Starter.filename";
anywheresoftware.b4a.keywords.Common.File.WriteList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user,_lst);
 //BA.debugLineNum = 205;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 207;BA.debugLine="ToastMessageShow(\"تغییرات ذخیره شد\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("تغییرات ذخیره شد"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 208;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 2: {
 //BA.debugLineNum = 213;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 214;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 216;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 217;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 218;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 220;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 221;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e74) {
			processBA.setLastException(e74); //BA.debugLineNum = 223;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 224;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return "";
}
public static String  _footer_click() throws Exception{
 //BA.debugLineNum = 255;BA.debugLine="Sub footer_Click";
 //BA.debugLineNum = 256;BA.debugLine="If txt_pass.Text.Length >=6 Then";
if (mostCurrent._txt_pass.getText().length()>=6) { 
 //BA.debugLineNum = 257;BA.debugLine="If txt_name.Text.Length >= 3 And txt_lname.Text.";
if (mostCurrent._txt_name.getText().length()>=3 && mostCurrent._txt_lname.getText().length()>=3) { 
 //BA.debugLineNum = 258;BA.debugLine="If txt_mobile.Text.Length =11 And txt_address.T";
if (mostCurrent._txt_mobile.getText().length()==11 && mostCurrent._txt_address.getText().length()>5 && mostCurrent._txt_post.getText().length()==10) { 
 //BA.debugLineNum = 259;BA.debugLine="update_user";
_update_user();
 }else {
 //BA.debugLineNum = 261;BA.debugLine="ToastMessageShow(\"فیلد های شماره همراه و آدرس";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("فیلد های شماره همراه و آدرس و کد پستی را بررسی کنید"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 264;BA.debugLine="ToastMessageShow(\"فیلد های نام و نام خانوادگی ر";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("فیلد های نام و نام خانوادگی را پر کنید"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 267;BA.debugLine="ToastMessageShow(\"حداقل طول گذرواژه 6 کاراکتر اس";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("حداقل طول گذرواژه 6 کاراکتر است"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 269;BA.debugLine="End Sub";
return "";
}
public static String  _get_user(int _id) throws Exception{
 //BA.debugLineNum = 242;BA.debugLine="Sub get_user(id As Int)";
 //BA.debugLineNum = 243;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 244;BA.debugLine="connector.send_query($\"SELECT * FROM `users` WHER";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `users` WHERE `id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+""),"get_user",(Object)(""));
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 12;BA.debugLine="Dim effect_btn1 As RippleView";
mostCurrent._effect_btn1 = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 14;BA.debugLine="Private ime As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 16;BA.debugLine="Dim id_user As Int";
_id_user = 0;
 //BA.debugLineNum = 18;BA.debugLine="Private btn_back As Button";
mostCurrent._btn_back = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private scrol_main As ScrollView";
mostCurrent._scrol_main = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private btn_search As Button";
mostCurrent._btn_search = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private btn_share As Button";
mostCurrent._btn_share = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private btn_sabad As Button";
mostCurrent._btn_sabad = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private txt_name As EditText";
mostCurrent._txt_name = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private txt_lname As EditText";
mostCurrent._txt_lname = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private txt_meli As EditText";
mostCurrent._txt_meli = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private txt_mobile As EditText";
mostCurrent._txt_mobile = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private txt_tel As EditText";
mostCurrent._txt_tel = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private spin_sex As Spinner";
mostCurrent._spin_sex = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private spin_year As Spinner";
mostCurrent._spin_year = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private spin_month As Spinner";
mostCurrent._spin_month = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private spin_day As Spinner";
mostCurrent._spin_day = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private txt_pass As EditText";
mostCurrent._txt_pass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private txt_address As EditText";
mostCurrent._txt_address = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private txt_email As EditText";
mostCurrent._txt_email = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private txt_job As EditText";
mostCurrent._txt_job = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private txt_post As EditText";
mostCurrent._txt_post = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 42;BA.debugLine="Dim su As StringUtils";
mostCurrent._su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 43;BA.debugLine="Dim bc As ByteConverter";
mostCurrent._bc = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 44;BA.debugLine="Private p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12 As";
mostCurrent._p1 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p2 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p3 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p4 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p5 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p6 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p7 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p8 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p9 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p10 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p11 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._p12 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _update_user() throws Exception{
String _pass = "";
String _b = "";
 //BA.debugLineNum = 247;BA.debugLine="Sub update_user()";
 //BA.debugLineNum = 248;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 249;BA.debugLine="Dim pass As String";
_pass = "";
 //BA.debugLineNum = 250;BA.debugLine="pass=(su.EncodeBase64(bc.StringToBytes(txt_pa";
_pass = (mostCurrent._su.EncodeBase64(mostCurrent._bc.StringToBytes(mostCurrent._txt_pass.getText().trim(),"utf-8")));
 //BA.debugLineNum = 251;BA.debugLine="Dim b As String=myLibrary.CovertPersian2English(";
_b = mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,mostCurrent._spin_year.getSelectedItem())+"/"+mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,mostCurrent._spin_month.getSelectedItem())+"/"+mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,mostCurrent._spin_day.getSelectedItem());
 //BA.debugLineNum = 252;BA.debugLine="connector.send_query($\"update `users` set `sex`=$";
mostCurrent._connector._send_query(mostCurrent.activityBA,("update `users` set `sex`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spin_sex.getSelectedIndex()))+",`name`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_name.getText().trim()))+"',`lname`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_lname.getText().trim()))+"',`code_meli`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_meli.getText().trim()))+"',`born`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_b))+"',`job`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_job.getText().trim()))+"',`mobile`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_mobile.getText().trim()))+"',`tel`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_tel.getText().trim()))+"',`address`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_address.getText().trim()))+"',`post_code`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_post.getText().trim()))+"',`email`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_email.getText().trim()))+"',`password`=N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_pass))+"' WHERE `id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id_user))+""),"update_user",(Object)(""));
 //BA.debugLineNum = 253;BA.debugLine="End Sub";
return "";
}
}
