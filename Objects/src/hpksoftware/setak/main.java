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

public class main extends android.support.v7.app.AppCompatActivity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public static String _name_dat = "";
public static co.ronash.pushe.wrapper.PusheWrapper _pushe = null;
public anywheresoftware.b4a.objects.collections.List _list1_baner = null;
public de.amberhome.objects.appcompat.AppCompatBase _apc = null;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public dmax.dialog.Spotsd _progress_spot = null;
public static long _time = 0L;
public static long _time_viesue = 0L;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_account = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_menu = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_slider = null;
public wir.hitex.recycler.Hitex_Picasso _piccaso = null;
public b4a.example.money _toman = null;
public anywheresoftware.b4a.objects.collections.List _list_kala = null;
public static boolean _bool_load_porfroush = false;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _porfroush_panel = null;
public anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _viesue_panel = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_timer = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_vip = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_title_news = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_badge = null;
public static boolean _bool_click_link = false;
public static boolean _checked_update = false;
public com.porya.pagerbullet.PagerBulletAdapter _pa = null;
public com.porya.pagerbullet.PagerBulletWrapper _pb = null;
public anywheresoftware.b4a.objects.Timer _timer_slide = null;
public static int _index_slider = 0;
public com.maximussoft.msmaterialdrawer.MSMaterialDrawerBuilder _mdb = null;
public com.maximussoft.msmaterialdrawer.MSMaterialDrawer _md = null;
public anywheresoftware.b4a.objects.collections.List _user_info = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_sabad = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_share = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_search = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_v1 = null;
public wir.hitex.recycler.Hitex_LayoutView _layoutview1 = null;
public wir.hitex.recycler.Hitex_LayoutView _layoutview2 = null;
public wir.hitex.recycler.Hitex_DividerItemDecoration _divider_layoutview1 = null;
public wir.hitex.recycler.Hitex_RecyclerCardView _cardview1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_base = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _progress_porfroosh = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_baner_1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_baner_2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_obj0 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_obj1 = null;
public anywheresoftware.b4a.objects.collections.List _list_brand = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_brand = null;
public com.github.javiersantos.materialstyleddialogs.StyledDialog _point_dialog = null;
public anywheresoftware.b4a.objects.PanelWrapper _splash = null;
public anywheresoftware.b4a.objects.Timer _timer_splash = null;
public anywheresoftware.b4a.ariagplib.ARIAlib _arialib1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _btn_list_porfroush = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
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
public hpksoftware.setak.nazar _nazar = null;
public hpksoftware.setak.push_active _push_active = null;
public hpksoftware.setak.result _result = null;
public hpksoftware.setak.disconnect _disconnect = null;
public hpksoftware.setak.changefontbylabelsize _changefontbylabelsize = null;
public static class _baner_list_1{
public boolean IsInitialized;
public String src_pic;
public String desc;
public void Initialize() {
IsInitialized = true;
src_pic = "";
desc = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _brands{
public boolean IsInitialized;
public String pic;
public void Initialize() {
IsInitialized = true;
pic = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (insert_noskhe.mostCurrent != null);
vis = vis | (show_noskhe.mostCurrent != null);
vis = vis | (rezerv.mostCurrent != null);
vis = vis | (order.mostCurrent != null);
vis = vis | (catgory.mostCurrent != null);
vis = vis | (show_list_kala.mostCurrent != null);
vis = vis | (project_help.mostCurrent != null);
vis = vis | (reg_login.mostCurrent != null);
vis = vis | (sabad.mostCurrent != null);
vis = vis | (history_order.mostCurrent != null);
vis = vis | (shared_admin.mostCurrent != null);
vis = vis | (update_users.mostCurrent != null);
vis = vis | (info_kala.mostCurrent != null);
vis = vis | (update.mostCurrent != null);
vis = vis | (show_nazar.mostCurrent != null);
vis = vis | (nazar.mostCurrent != null);
vis = vis | (push_active.mostCurrent != null);
vis = vis | (result.mostCurrent != null);
vis = vis | (disconnect.mostCurrent != null);
vis = vis | (at.ahadev.b4a.ahashare.ahacontentchooser.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _intent1 = null;
String _data_link = "";
String _str_link = "";
String _notification_tag = "";
anywheresoftware.b4a.sql.SQL _sql1 = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cr1 = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_header = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_header2 = null;
 //BA.debugLineNum = 126;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 127;BA.debugLine="Activity.Color=Colors.White";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 128;BA.debugLine="name_dat=Starter.filename_user";
_name_dat = mostCurrent._starter._filename_user;
 //BA.debugLineNum = 129;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 130;BA.debugLine="StartService(alarm_service)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._alarm_service.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="Try";
try { //BA.debugLineNum = 134;BA.debugLine="Dim Intent1 As Intent";
_intent1 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 135;BA.debugLine="Dim data_link,str_link As String";
_data_link = "";
_str_link = "";
 //BA.debugLineNum = 136;BA.debugLine="Intent1 = Activity.GetStartingIntent";
_intent1 = mostCurrent._activity.GetStartingIntent();
 //BA.debugLineNum = 137;BA.debugLine="If Intent1.HasExtra(\"Notification_Tag\") Then";
if (_intent1.HasExtra("Notification_Tag")) { 
 //BA.debugLineNum = 138;BA.debugLine="Try";
try { //BA.debugLineNum = 139;BA.debugLine="Dim Notification_Tag As String= Intent1.GetExt";
_notification_tag = BA.ObjectToString(_intent1.GetExtra("Notification_Tag"));
 //BA.debugLineNum = 140;BA.debugLine="connector.Initialize(Me,\"db\",Starter.server_my";
mostCurrent._connector._initialize(mostCurrent.activityBA,main.getObject(),"db",mostCurrent._starter._server_mysql,mostCurrent._starter._dn,mostCurrent._starter._du,mostCurrent._starter._dp);
 //BA.debugLineNum = 141;BA.debugLine="info_kala.code_kala=Notification_Tag";
mostCurrent._info_kala._code_kala = (int)(Double.parseDouble(_notification_tag));
 //BA.debugLineNum = 142;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 //BA.debugLineNum = 143;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 } 
       catch (Exception e17) {
			processBA.setLastException(e17); };
 };
 //BA.debugLineNum = 149;BA.debugLine="If Intent1.ExtrasToString.Contains(\"com.android.";
if (_intent1.ExtrasToString().contains("com.android.browser.application_id")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 151;BA.debugLine="Log(\"com.android.browser.application_id\")";
anywheresoftware.b4a.keywords.Common.Log("com.android.browser.application_id");
 //BA.debugLineNum = 152;BA.debugLine="bool_click_link=True";
_bool_click_link = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 153;BA.debugLine="Log(\"bool_click_link: \" & bool_click_link)";
anywheresoftware.b4a.keywords.Common.Log("bool_click_link: "+BA.ObjectToString(_bool_click_link));
 //BA.debugLineNum = 154;BA.debugLine="str_link=Intent1.GetData";
_str_link = _intent1.GetData();
 //BA.debugLineNum = 155;BA.debugLine="data_link=str_link.SubString((str_link.LastInde";
_data_link = _str_link.substring((int) ((_str_link.lastIndexOf("/"))+1));
 //BA.debugLineNum = 156;BA.debugLine="info_kala.code_kala=data_link";
mostCurrent._info_kala._code_kala = (int)(Double.parseDouble(_data_link));
 //BA.debugLineNum = 157;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 //BA.debugLineNum = 158;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 159;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 160;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 164;BA.debugLine="Dim sql1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 165;BA.debugLine="Dim cr1 As Cursor";
_cr1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 166;BA.debugLine="If File.Exists(Starter.rute_file_app,\"db.db\") =";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._starter._rute_file_app,"db.db")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 167;BA.debugLine="File.Copy(File.DirAssets,\"db.db\",Starter.rute_";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"db.db",mostCurrent._starter._rute_file_app,"db.db");
 };
 //BA.debugLineNum = 169;BA.debugLine="If sql1.IsInitialized=False Then sql1.Initializ";
if (_sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_sql1.Initialize(mostCurrent._starter._rute_file_app,"db.db",anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 171;BA.debugLine="If AriaLib1.TestInterntConnection=True Then";
if (mostCurrent._arialib1.TestInterntConnection()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 173;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 //BA.debugLineNum = 174;BA.debugLine="Panel1.Visible=False";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 175;BA.debugLine="Dim color_header,color_header2 As ColorDrawabl";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_color_header2 = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 176;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 177;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 178;BA.debugLine="color_header2.Initialize(0xFF1EBF6F,0)";
_color_header2.Initialize((int) (0xff1ebf6f),(int) (0));
 //BA.debugLineNum = 179;BA.debugLine="splash.Background=color_header2";
mostCurrent._splash.setBackground((android.graphics.drawable.Drawable)(_color_header2.getObject()));
 //BA.debugLineNum = 180;BA.debugLine="splash.BringToFront";
mostCurrent._splash.BringToFront();
 //BA.debugLineNum = 181;BA.debugLine="initialize_awsome";
_initialize_awsome();
 //BA.debugLineNum = 182;BA.debugLine="Log(\"coneccted\")";
anywheresoftware.b4a.keywords.Common.Log("coneccted");
 //BA.debugLineNum = 183;BA.debugLine="connector.Initialize(Me,\"db\",Starter.server_my";
mostCurrent._connector._initialize(mostCurrent.activityBA,main.getObject(),"db",mostCurrent._starter._server_mysql,mostCurrent._starter._dn,mostCurrent._starter._du,mostCurrent._starter._dp);
 //BA.debugLineNum = 184;BA.debugLine="timer_slide.Initialize(\"timer_slide\",Starter.t";
mostCurrent._timer_slide.Initialize(processBA,"timer_slide",(long) (mostCurrent._starter._timer_slideshow));
 //BA.debugLineNum = 186;BA.debugLine="connector.send_query($\"select * from `check`\"$";
mostCurrent._connector._send_query(mostCurrent.activityBA,("select * from `check`"),"check",(Object)(""));
 //BA.debugLineNum = 187;BA.debugLine="If Starter.enable_push=True Then Pushe.initial";
if (mostCurrent._starter._enable_push==anywheresoftware.b4a.keywords.Common.True) { 
_pushe.initialize(processBA);};
 }else {
 //BA.debugLineNum = 189;BA.debugLine="Log(\"disconeccted\")";
anywheresoftware.b4a.keywords.Common.Log("disconeccted");
 //BA.debugLineNum = 193;BA.debugLine="Activity.LoadLayout(\"page_1\")";
mostCurrent._activity.LoadLayout("page_1",mostCurrent.activityBA);
 };
 };
 } 
       catch (Exception e58) {
			processBA.setLastException(e58); };
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 653;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 654;BA.debugLine="timer_slide.Enabled=False";
mostCurrent._timer_slide.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 655;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 1121;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 1122;BA.debugLine="Log(\"bool_click_link: \" & bool_click_link)";
anywheresoftware.b4a.keywords.Common.Log("bool_click_link: "+BA.ObjectToString(_bool_click_link));
 //BA.debugLineNum = 1123;BA.debugLine="If AriaLib1.TestInterntConnection=True Then";
if (mostCurrent._arialib1.TestInterntConnection()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1126;BA.debugLine="If bool_click_link=False Then  'direct az telegr";
if (_bool_click_link==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1131;BA.debugLine="If checked_update=True Then";
if (_checked_update==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1133;BA.debugLine="If porfroush_panel.Panel.NumberOfViews < 1 The";
if (mostCurrent._porfroush_panel.getPanel().getNumberOfViews()<1) { 
 //BA.debugLineNum = 1134;BA.debugLine="porfroush_panel.Panel.RemoveAllViews";
mostCurrent._porfroush_panel.getPanel().RemoveAllViews();
 //BA.debugLineNum = 1135;BA.debugLine="get_porfroush";
_get_porfroush();
 };
 };
 //BA.debugLineNum = 1146;BA.debugLine="If Result.SearchState=Result.SS_RESULT_SUCCESS";
if (mostCurrent._result._searchstate==mostCurrent._result._ss_result_success) { 
 //BA.debugLineNum = 1148;BA.debugLine="show_list_kala.bool_search=True";
mostCurrent._show_list_kala._bool_search = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1149;BA.debugLine="show_list_kala.query_search=Result.SearchResul";
mostCurrent._show_list_kala._query_search = mostCurrent._result._searchresult;
 //BA.debugLineNum = 1150;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1151;BA.debugLine="show_list_kala.bool_search=False";
mostCurrent._show_list_kala._bool_search = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1152;BA.debugLine="Result.SearchState=Result.SS_NONE";
mostCurrent._result._searchstate = mostCurrent._result._ss_none;
 };
 //BA.debugLineNum = 1154;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=True An";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat)==anywheresoftware.b4a.keywords.Common.True && _checked_update==anywheresoftware.b4a.keywords.Common.True) { 
_check_account();};
 //BA.debugLineNum = 1156;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1157;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 1158;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 1159;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 //BA.debugLineNum = 1160;BA.debugLine="lbl_badge.Visible=True";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1161;BA.debugLine="If list_kala.Size<=0 Then lbl_badge.Visible=Fa";
if (mostCurrent._list_kala.getSize()<=0) { 
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);};
 }else {
 //BA.debugLineNum = 1163;BA.debugLine="lbl_badge.Visible=False";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 };
 };
 //BA.debugLineNum = 1169;BA.debugLine="End Sub";
return "";
}
public static String  _baner_toch(String _response) throws Exception{
int _code_kala = 0;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
 //BA.debugLineNum = 1013;BA.debugLine="Sub baner_toch(response As String)";
 //BA.debugLineNum = 1014;BA.debugLine="If response.Length > 0 Then";
if (_response.length()>0) { 
 //BA.debugLineNum = 1015;BA.debugLine="If IsNumber(response)=True Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_response)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1016;BA.debugLine="Dim code_kala As Int=response";
_code_kala = (int)(Double.parseDouble(_response));
 //BA.debugLineNum = 1017;BA.debugLine="info_kala.code_kala=code_kala";
mostCurrent._info_kala._code_kala = _code_kala;
 //BA.debugLineNum = 1018;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 }else {
 //BA.debugLineNum = 1020;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 1021;BA.debugLine="Pd=function.style_msgbox2(\"اطلاعیه\",\"response\")";
_pd = mostCurrent._function._style_msgbox2(mostCurrent.activityBA,"اطلاعیه","response");
 //BA.debugLineNum = 1022;BA.debugLine="Pd.Create";
_pd.Create(mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 1026;BA.debugLine="End Sub";
return "";
}
public static String  _btn_list_category_click() throws Exception{
 //BA.debugLineNum = 674;BA.debugLine="Sub btn_list_category_Click";
 //BA.debugLineNum = 675;BA.debugLine="catgory.bool_brand_layout=False";
mostCurrent._catgory._bool_brand_layout = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 676;BA.debugLine="StartActivity(catgory)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._catgory.getObject()));
 //BA.debugLineNum = 678;BA.debugLine="End Sub";
return "";
}
public static String  _btn_list_category1_click() throws Exception{
 //BA.debugLineNum = 679;BA.debugLine="Sub btn_list_category1_Click";
 //BA.debugLineNum = 680;BA.debugLine="catgory.bool_brand_layout=False";
mostCurrent._catgory._bool_brand_layout = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 681;BA.debugLine="StartActivity(catgory)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._catgory.getObject()));
 //BA.debugLineNum = 682;BA.debugLine="End Sub";
return "";
}
public static String  _btn_list_porfroush_click() throws Exception{
 //BA.debugLineNum = 1184;BA.debugLine="Sub btn_list_porfroush_Click";
 //BA.debugLineNum = 1188;BA.debugLine="catgory.bool_brand_layout=False";
mostCurrent._catgory._bool_brand_layout = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1189;BA.debugLine="StartActivity(catgory)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._catgory.getObject()));
 //BA.debugLineNum = 1190;BA.debugLine="End Sub";
return "";
}
public static String  _btn_menu_click() throws Exception{
 //BA.debugLineNum = 660;BA.debugLine="Sub btn_menu_Click";
 //BA.debugLineNum = 661;BA.debugLine="Initialize_menu";
_initialize_menu();
 //BA.debugLineNum = 662;BA.debugLine="MD.OpenDrawer";
mostCurrent._md.OpenDrawer();
 //BA.debugLineNum = 663;BA.debugLine="End Sub";
return "";
}
public static String  _btn_p1_click() throws Exception{
 //BA.debugLineNum = 1467;BA.debugLine="Sub btn_p1_Click";
 //BA.debugLineNum = 1468;BA.debugLine="StartActivity(show_noskhe)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_noskhe.getObject()));
 //BA.debugLineNum = 1469;BA.debugLine="End Sub";
return "";
}
public static String  _btn_p2_click() throws Exception{
 //BA.debugLineNum = 1464;BA.debugLine="Sub btn_p2_Click";
 //BA.debugLineNum = 1465;BA.debugLine="StartActivity(rezerv)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._rezerv.getObject()));
 //BA.debugLineNum = 1466;BA.debugLine="End Sub";
return "";
}
public static String  _btn_p3_click() throws Exception{
 //BA.debugLineNum = 1461;BA.debugLine="Sub btn_p3_Click";
 //BA.debugLineNum = 1462;BA.debugLine="StartActivity(insert_noskhe)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._insert_noskhe.getObject()));
 //BA.debugLineNum = 1463;BA.debugLine="End Sub";
return "";
}
public static String  _btn_sabad_click() throws Exception{
 //BA.debugLineNum = 666;BA.debugLine="Sub btn_sabad_Click()";
 //BA.debugLineNum = 667;BA.debugLine="StartActivity(sabad)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._sabad.getObject()));
 //BA.debugLineNum = 668;BA.debugLine="End Sub";
return "";
}
public static String  _btn_search_click() throws Exception{
 //BA.debugLineNum = 1171;BA.debugLine="Sub btn_search_Click";
 //BA.debugLineNum = 1172;BA.debugLine="RequestSearchBar";
_requestsearchbar();
 //BA.debugLineNum = 1173;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_click() throws Exception{
anywheresoftware.b4a.ariagplib.ARIAlib _share = null;
 //BA.debugLineNum = 1115;BA.debugLine="Sub btn_share_Click";
 //BA.debugLineNum = 1116;BA.debugLine="Dim share As AriaLib";
_share = new anywheresoftware.b4a.ariagplib.ARIAlib();
 //BA.debugLineNum = 1117;BA.debugLine="StartActivity(share.ShareApplication(Application.";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share.ShareApplication(anywheresoftware.b4a.keywords.Common.Application.getPackageName(),anywheresoftware.b4a.keywords.Common.Application.getLabelName())));
 //BA.debugLineNum = 1118;BA.debugLine="End Sub";
return "";
}
public static String  _check_account() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 368;BA.debugLine="Sub check_account";
 //BA.debugLineNum = 369;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=True Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 370;BA.debugLine="Dim lst As List=File.ReadList(myLibrary.rute,nam";
_lst = new anywheresoftware.b4a.objects.collections.List();
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat);
 //BA.debugLineNum = 372;BA.debugLine="Log(lst)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_lst));
 //BA.debugLineNum = 374;BA.debugLine="If lst.Get(2)=\"\" Or lst.Get(1)=\"\" Or lst.Get(0";
if ((_lst.Get((int) (2))).equals((Object)("")) || (_lst.Get((int) (1))).equals((Object)("")) || (_lst.Get((int) (0))).equals((Object)(""))) { 
 //BA.debugLineNum = 375;BA.debugLine="ToastMessageShow(\"برای راحتی عملیات خرید از بخش";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("برای راحتی عملیات خرید از بخش پروفایل، حساب کاربری خود را تکمیل کنید"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 };
 };
 //BA.debugLineNum = 380;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _list_records,Object _tag) throws Exception{
anywheresoftware.b4a.objects.collections.Map _map_type = null;
String _type_baner = "";
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
hpksoftware.setak.main._baner_list_1 _baner_list1 = null;
anywheresoftware.b4a.objects.ImageViewWrapper[] _img_brand = null;
anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _hor_panel = null;
hpksoftware.setak.main._brands _brand1 = null;
int _status = 0;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _ver = 0;
 //BA.debugLineNum = 414;BA.debugLine="Sub db_connector(list_records As List,tag As Objec";
 //BA.debugLineNum = 415;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 416;BA.debugLine="Try";
try { //BA.debugLineNum = 417;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_viesue"),(Object)("get_category"),(Object)("get_porfroush"),(Object)("get_slideshow"),(Object)("time_life_viesue"),(Object)("baners1"),(Object)("baners2"),(Object)("get_brand"),(Object)("check"),(Object)("disconnect"))) {
case 0: {
 //BA.debugLineNum = 419;BA.debugLine="func_viesue(list_records)";
_func_viesue(_list_records);
 break; }
case 1: {
 //BA.debugLineNum = 421;BA.debugLine="func_get_category(list_records)";
_func_get_category(_list_records);
 break; }
case 2: {
 //BA.debugLineNum = 423;BA.debugLine="func_get_porfroush(list_records)";
_func_get_porfroush(_list_records);
 break; }
case 3: {
 //BA.debugLineNum = 425;BA.debugLine="func_get_slideshow(list_records)";
_func_get_slideshow(_list_records);
 break; }
case 4: {
 //BA.debugLineNum = 427;BA.debugLine="func_time_life_viesue(list_records)";
_func_time_life_viesue(_list_records);
 break; }
case 5: {
 //BA.debugLineNum = 433;BA.debugLine="If list_records.Size>0 Then";
if (_list_records.getSize()>0) { 
 //BA.debugLineNum = 434;BA.debugLine="Dim map_type As Map=list_records.Get(0)";
_map_type = new anywheresoftware.b4a.objects.collections.Map();
_map_type.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get((int) (0))));
 //BA.debugLineNum = 435;BA.debugLine="Dim type_baner As String=map_type.Get(\"meta_t";
_type_baner = BA.ObjectToString(_map_type.Get((Object)("meta_type")));
 //BA.debugLineNum = 436;BA.debugLine="list1_baner.Initialize";
mostCurrent._list1_baner.Initialize();
 //BA.debugLineNum = 437;BA.debugLine="Select type_baner";
switch (BA.switchObjectToInt(_type_baner,"list-single","list-two")) {
case 0: {
 //BA.debugLineNum = 439;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step21 = 1;
final int limit21 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step21 > 0 && _i <= limit21) || (step21 < 0 && _i >= limit21); _i = ((int)(0 + _i + step21)) ) {
 //BA.debugLineNum = 440;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 441;BA.debugLine="Dim baner_list1 As baner_list_1";
_baner_list1 = new hpksoftware.setak.main._baner_list_1();
 //BA.debugLineNum = 442;BA.debugLine="baner_list1.Initialize";
_baner_list1.Initialize();
 //BA.debugLineNum = 443;BA.debugLine="baner_list1.src_pic=map1.Get(\"meta_value\")";
_baner_list1.src_pic = BA.ObjectToString(_map1.Get((Object)("meta_value")));
 //BA.debugLineNum = 444;BA.debugLine="baner_list1.desc=map1.Get(\"meta_descriptio";
_baner_list1.desc = BA.ObjectToString(_map1.Get((Object)("meta_description")));
 //BA.debugLineNum = 445;BA.debugLine="list1_baner.Add(	baner_list1	)";
mostCurrent._list1_baner.Add((Object)(_baner_list1));
 }
};
 //BA.debugLineNum = 447;BA.debugLine="panel_baner_1.Height=list1_baner.Size * 130";
mostCurrent._panel_baner_1.setHeight((int) (mostCurrent._list1_baner.getSize()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (130))));
 //BA.debugLineNum = 448;BA.debugLine="panel_obj1.Top= panel_baner_1.Top + panel_b";
mostCurrent._panel_obj1.setTop((int) (mostCurrent._panel_baner_1.getTop()+mostCurrent._panel_baner_1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 449;BA.debugLine="panel_brand.Top=panel_obj1.Top + panel_obj1";
mostCurrent._panel_brand.setTop((int) (mostCurrent._panel_obj1.getTop()+mostCurrent._panel_obj1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 450;BA.debugLine="ScrollView1.Panel.Height=panel_obj1.Top + p";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._panel_obj1.getTop()+mostCurrent._panel_obj1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 451;BA.debugLine="LayoutView1.Initialize(\"LayoutView1\",False,";
mostCurrent._layoutview1.Initialize(mostCurrent.activityBA,"LayoutView1",anywheresoftware.b4a.keywords.Common.False,"ListView","Vertical",anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,(int) (0));
 //BA.debugLineNum = 452;BA.debugLine="panel_baner_1.AddView(LayoutView1,0,	0	,	pa";
mostCurrent._panel_baner_1.AddView((android.view.View)(mostCurrent._layoutview1.getObject()),(int) (0),(int) (0),mostCurrent._panel_baner_1.getWidth(),mostCurrent._panel_baner_1.getHeight());
 //BA.debugLineNum = 453;BA.debugLine="divider_LayoutView1.Initialize(LayoutView1)";
mostCurrent._divider_layoutview1.Initialize(mostCurrent.activityBA,mostCurrent._layoutview1);
 //BA.debugLineNum = 454;BA.debugLine="divider_LayoutView1.AddItemDecoration1(Colo";
mostCurrent._divider_layoutview1.AddItemDecoration1(anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
 //BA.debugLineNum = 461;BA.debugLine="LayoutView1.Show(list1_baner.Size,LayoutVie";
mostCurrent._layoutview1.Show(mostCurrent._list1_baner.getSize(),mostCurrent._layoutview1.ANIMATION_ScaleIn,(int) (500),anywheresoftware.b4a.keywords.Common.False,mostCurrent._panel_baner_1.getWidth(),mostCurrent._panel_baner_1.getHeight());
 break; }
case 1: {
 //BA.debugLineNum = 464;BA.debugLine="Log(\"list-two\")";
anywheresoftware.b4a.keywords.Common.Log("list-two");
 //BA.debugLineNum = 465;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step40 = 1;
final int limit40 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step40 > 0 && _i <= limit40) || (step40 < 0 && _i >= limit40); _i = ((int)(0 + _i + step40)) ) {
 //BA.debugLineNum = 466;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 467;BA.debugLine="Dim baner_list1 As baner_list_1";
_baner_list1 = new hpksoftware.setak.main._baner_list_1();
 //BA.debugLineNum = 468;BA.debugLine="baner_list1.Initialize";
_baner_list1.Initialize();
 //BA.debugLineNum = 469;BA.debugLine="baner_list1.src_pic=map1.Get(\"meta_value\")";
_baner_list1.src_pic = BA.ObjectToString(_map1.Get((Object)("meta_value")));
 //BA.debugLineNum = 470;BA.debugLine="baner_list1.desc=map1.Get(\"meta_descriptio";
_baner_list1.desc = BA.ObjectToString(_map1.Get((Object)("meta_description")));
 //BA.debugLineNum = 471;BA.debugLine="list1_baner.Add(	baner_list1	)";
mostCurrent._list1_baner.Add((Object)(_baner_list1));
 //BA.debugLineNum = 472;BA.debugLine="Log(\"list1_baner: \" & list1_baner.Get(i) )";
anywheresoftware.b4a.keywords.Common.Log("list1_baner: "+BA.ObjectToString(mostCurrent._list1_baner.Get(_i)));
 }
};
 //BA.debugLineNum = 474;BA.debugLine="panel_baner_1.Height=Round(list1_baner.Size";
mostCurrent._panel_baner_1.setHeight((int) (anywheresoftware.b4a.keywords.Common.Round(mostCurrent._list1_baner.getSize()/(double)2)*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150))));
 //BA.debugLineNum = 475;BA.debugLine="panel_obj1.Top= panel_baner_1.Top + panel_b";
mostCurrent._panel_obj1.setTop((int) (mostCurrent._panel_baner_1.getTop()+mostCurrent._panel_baner_1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 476;BA.debugLine="panel_brand.Top=panel_obj1.Top + panel_obj1";
mostCurrent._panel_brand.setTop((int) (mostCurrent._panel_obj1.getTop()+mostCurrent._panel_obj1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 477;BA.debugLine="ScrollView1.Panel.Height=panel_brand.Top +";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._panel_brand.getTop()+mostCurrent._panel_brand.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 481;BA.debugLine="LayoutView1.Initialize(\"LayoutView2\",False,";
mostCurrent._layoutview1.Initialize(mostCurrent.activityBA,"LayoutView2",anywheresoftware.b4a.keywords.Common.False,"GridView","Vertical",anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,(int) (2));
 //BA.debugLineNum = 482;BA.debugLine="panel_baner_1.AddView(LayoutView1,0,	0	,	pa";
mostCurrent._panel_baner_1.AddView((android.view.View)(mostCurrent._layoutview1.getObject()),(int) (0),(int) (0),mostCurrent._panel_baner_1.getWidth(),mostCurrent._panel_baner_1.getHeight());
 //BA.debugLineNum = 483;BA.debugLine="divider_LayoutView1.Initialize(LayoutView1)";
mostCurrent._divider_layoutview1.Initialize(mostCurrent.activityBA,mostCurrent._layoutview1);
 //BA.debugLineNum = 484;BA.debugLine="divider_LayoutView1.AddItemDecoration1(Colo";
mostCurrent._divider_layoutview1.AddItemDecoration1(anywheresoftware.b4a.keywords.Common.Colors.Transparent,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)));
 //BA.debugLineNum = 485;BA.debugLine="LayoutView1.Show(list1_baner.Size,LayoutVie";
mostCurrent._layoutview1.Show(mostCurrent._list1_baner.getSize(),mostCurrent._layoutview1.ANIMATION_ScaleIn,(int) (500),anywheresoftware.b4a.keywords.Common.False,mostCurrent._panel_baner_1.getWidth(),mostCurrent._panel_baner_1.getHeight());
 break; }
}
;
 };
 //BA.debugLineNum = 490;BA.debugLine="get_slideshow";
_get_slideshow();
 break; }
case 6: {
 //BA.debugLineNum = 493;BA.debugLine="Log(\"baners2\")";
anywheresoftware.b4a.keywords.Common.Log("baners2");
 //BA.debugLineNum = 495;BA.debugLine="If list_records.Size>0 Then";
if (_list_records.getSize()>0) { 
 //BA.debugLineNum = 496;BA.debugLine="Log(\"??????\" & list_records)";
anywheresoftware.b4a.keywords.Common.Log("??????"+BA.ObjectToString(_list_records));
 //BA.debugLineNum = 497;BA.debugLine="Dim map_type As Map=list_records.Get(0)";
_map_type = new anywheresoftware.b4a.objects.collections.Map();
_map_type.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get((int) (0))));
 //BA.debugLineNum = 498;BA.debugLine="Dim type_baner As String=map_type.Get(\"meta_t";
_type_baner = BA.ObjectToString(_map_type.Get((Object)("meta_type")));
 //BA.debugLineNum = 499;BA.debugLine="list1_baner.Initialize";
mostCurrent._list1_baner.Initialize();
 //BA.debugLineNum = 500;BA.debugLine="Log(\"type_baner: \" & type_baner )";
anywheresoftware.b4a.keywords.Common.Log("type_baner: "+_type_baner);
 //BA.debugLineNum = 501;BA.debugLine="Select type_baner.Trim";
switch (BA.switchObjectToInt(_type_baner.trim(),"list-single","list-two")) {
case 0: {
 //BA.debugLineNum = 503;BA.debugLine="Log(\"list-single\")";
anywheresoftware.b4a.keywords.Common.Log("list-single");
 //BA.debugLineNum = 504;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step72 = 1;
final int limit72 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step72 > 0 && _i <= limit72) || (step72 < 0 && _i >= limit72); _i = ((int)(0 + _i + step72)) ) {
 //BA.debugLineNum = 505;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 506;BA.debugLine="Dim baner_list1 As baner_list_1";
_baner_list1 = new hpksoftware.setak.main._baner_list_1();
 //BA.debugLineNum = 507;BA.debugLine="baner_list1.Initialize";
_baner_list1.Initialize();
 //BA.debugLineNum = 508;BA.debugLine="baner_list1.src_pic=map1.Get(\"meta_value\")";
_baner_list1.src_pic = BA.ObjectToString(_map1.Get((Object)("meta_value")));
 //BA.debugLineNum = 509;BA.debugLine="baner_list1.desc=map1.Get(\"meta_descriptio";
_baner_list1.desc = BA.ObjectToString(_map1.Get((Object)("meta_description")));
 //BA.debugLineNum = 510;BA.debugLine="list1_baner.Add(	baner_list1	)";
mostCurrent._list1_baner.Add((Object)(_baner_list1));
 }
};
 //BA.debugLineNum = 513;BA.debugLine="panel_baner_2.Height=(list1_baner.Size * 13";
mostCurrent._panel_baner_2.setHeight((int) ((mostCurrent._list1_baner.getSize()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (130)))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 514;BA.debugLine="panel_baner_2.Top= panel_obj1.Top + panel_o";
mostCurrent._panel_baner_2.setTop((int) (mostCurrent._panel_obj1.getTop()+mostCurrent._panel_obj1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 515;BA.debugLine="ScrollView1.Panel.Height=panel_baner_2.Top";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._panel_baner_2.getTop()+mostCurrent._panel_baner_2.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 516;BA.debugLine="Try";
try { //BA.debugLineNum = 517;BA.debugLine="LayoutView2.Initialize(\"LayoutView1_baners";
mostCurrent._layoutview2.Initialize(mostCurrent.activityBA,"LayoutView1_baners2",anywheresoftware.b4a.keywords.Common.False,"ListView","Vertical",anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,(int) (0));
 } 
       catch (Exception e86) {
			processBA.setLastException(e86); //BA.debugLineNum = 519;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 523;BA.debugLine="panel_baner_2.AddView(LayoutView2,0,	0	,	pa";
mostCurrent._panel_baner_2.AddView((android.view.View)(mostCurrent._layoutview2.getObject()),(int) (0),(int) (0),mostCurrent._panel_baner_2.getWidth(),mostCurrent._panel_baner_2.getHeight());
 //BA.debugLineNum = 524;BA.debugLine="divider_LayoutView1.Initialize(LayoutView2)";
mostCurrent._divider_layoutview1.Initialize(mostCurrent.activityBA,mostCurrent._layoutview2);
 //BA.debugLineNum = 525;BA.debugLine="divider_LayoutView1.AddItemDecoration1(Colo";
mostCurrent._divider_layoutview1.AddItemDecoration1(anywheresoftware.b4a.keywords.Common.Colors.Transparent,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
 //BA.debugLineNum = 527;BA.debugLine="LayoutView2.Show(list1_baner.Size,LayoutVie";
mostCurrent._layoutview2.Show(mostCurrent._list1_baner.getSize(),mostCurrent._layoutview2.ANIMATION_ScaleIn,(int) (500),anywheresoftware.b4a.keywords.Common.False,mostCurrent._panel_baner_2.getWidth(),mostCurrent._panel_baner_2.getHeight());
 break; }
case 1: {
 //BA.debugLineNum = 530;BA.debugLine="Log(\"list-two\")";
anywheresoftware.b4a.keywords.Common.Log("list-two");
 //BA.debugLineNum = 531;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step94 = 1;
final int limit94 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step94 > 0 && _i <= limit94) || (step94 < 0 && _i >= limit94); _i = ((int)(0 + _i + step94)) ) {
 //BA.debugLineNum = 532;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 533;BA.debugLine="Dim baner_list1 As baner_list_1";
_baner_list1 = new hpksoftware.setak.main._baner_list_1();
 //BA.debugLineNum = 534;BA.debugLine="baner_list1.Initialize";
_baner_list1.Initialize();
 //BA.debugLineNum = 535;BA.debugLine="baner_list1.src_pic=map1.Get(\"meta_value\")";
_baner_list1.src_pic = BA.ObjectToString(_map1.Get((Object)("meta_value")));
 //BA.debugLineNum = 536;BA.debugLine="baner_list1.desc=map1.Get(\"meta_descriptio";
_baner_list1.desc = BA.ObjectToString(_map1.Get((Object)("meta_description")));
 //BA.debugLineNum = 537;BA.debugLine="list1_baner.Add(	baner_list1	)";
mostCurrent._list1_baner.Add((Object)(_baner_list1));
 //BA.debugLineNum = 538;BA.debugLine="Log(\"list1_baner: \" & list1_baner.Get(i) )";
anywheresoftware.b4a.keywords.Common.Log("list1_baner: "+BA.ObjectToString(mostCurrent._list1_baner.Get(_i)));
 }
};
 //BA.debugLineNum = 540;BA.debugLine="panel_baner_2.Height=Round(list1_baner.Size";
mostCurrent._panel_baner_2.setHeight((int) (anywheresoftware.b4a.keywords.Common.Round(mostCurrent._list1_baner.getSize()/(double)2)*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))));
 //BA.debugLineNum = 541;BA.debugLine="panel_baner_2.Top= panel_obj1.Top + panel_o";
mostCurrent._panel_baner_2.setTop((int) (mostCurrent._panel_obj1.getTop()+mostCurrent._panel_obj1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 542;BA.debugLine="ScrollView1.Panel.Height=panel_baner_2.Top";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._panel_baner_2.getTop()+mostCurrent._panel_baner_2.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 543;BA.debugLine="LayoutView1.Initialize(\"LayoutView2_baners2";
mostCurrent._layoutview1.Initialize(mostCurrent.activityBA,"LayoutView2_baners2",anywheresoftware.b4a.keywords.Common.False,"GridView","Vertical",anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,(int) (2));
 //BA.debugLineNum = 544;BA.debugLine="panel_baner_2.AddView(LayoutView1,0,	0	,	pa";
mostCurrent._panel_baner_2.AddView((android.view.View)(mostCurrent._layoutview1.getObject()),(int) (0),(int) (0),mostCurrent._panel_baner_2.getWidth(),mostCurrent._panel_baner_2.getHeight());
 //BA.debugLineNum = 545;BA.debugLine="divider_LayoutView1.Initialize(LayoutView1)";
mostCurrent._divider_layoutview1.Initialize(mostCurrent.activityBA,mostCurrent._layoutview1);
 //BA.debugLineNum = 546;BA.debugLine="divider_LayoutView1.AddItemDecoration1(Colo";
mostCurrent._divider_layoutview1.AddItemDecoration1(anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
 //BA.debugLineNum = 547;BA.debugLine="LayoutView1.Show(list1_baner.Size,LayoutVie";
mostCurrent._layoutview1.Show(mostCurrent._list1_baner.getSize(),mostCurrent._layoutview1.ANIMATION_ScaleIn,(int) (500),anywheresoftware.b4a.keywords.Common.False,mostCurrent._panel_baner_2.getWidth(),mostCurrent._panel_baner_2.getHeight());
 break; }
}
;
 };
 break; }
case 7: {
 //BA.debugLineNum = 559;BA.debugLine="Log( \"get_brand\")";
anywheresoftware.b4a.keywords.Common.Log("get_brand");
 //BA.debugLineNum = 560;BA.debugLine="list_brand.Initialize";
mostCurrent._list_brand.Initialize();
 //BA.debugLineNum = 561;BA.debugLine="Dim img_brand(list_records.Size) As ImageView";
_img_brand = new anywheresoftware.b4a.objects.ImageViewWrapper[_list_records.getSize()];
{
int d0 = _img_brand.length;
for (int i0 = 0;i0 < d0;i0++) {
_img_brand[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 563;BA.debugLine="Dim hor_panel As HorizontalScrollView";
_hor_panel = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 564;BA.debugLine="hor_panel.Initialize(panel_brand.Width,\"\")";
_hor_panel.Initialize(mostCurrent.activityBA,mostCurrent._panel_brand.getWidth(),"");
 //BA.debugLineNum = 565;BA.debugLine="panel_brand.AddView(hor_panel,0,0,panel_brand.";
mostCurrent._panel_brand.AddView((android.view.View)(_hor_panel.getObject()),(int) (0),(int) (0),mostCurrent._panel_brand.getWidth(),mostCurrent._panel_brand.getHeight());
 //BA.debugLineNum = 566;BA.debugLine="function.hide_scrollbar_horizontal(hor_panel)";
mostCurrent._function._hide_scrollbar_horizontal(mostCurrent.activityBA,_hor_panel);
 //BA.debugLineNum = 567;BA.debugLine="For i=0 To list_records.Size-1";
{
final int step121 = 1;
final int limit121 = (int) (_list_records.getSize()-1);
for (_i = (int) (0) ; (step121 > 0 && _i <= limit121) || (step121 < 0 && _i >= limit121); _i = ((int)(0 + _i + step121)) ) {
 //BA.debugLineNum = 568;BA.debugLine="Dim map1 As Map=list_records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get(_i)));
 //BA.debugLineNum = 569;BA.debugLine="Dim brand1 As brands";
_brand1 = new hpksoftware.setak.main._brands();
 //BA.debugLineNum = 570;BA.debugLine="brand1.Initialize";
_brand1.Initialize();
 //BA.debugLineNum = 571;BA.debugLine="brand1.pic=map1.Get(\"logo_brand\")";
_brand1.pic = BA.ObjectToString(_map1.Get((Object)("logo_brand")));
 //BA.debugLineNum = 572;BA.debugLine="list_brand.Add(	brand1	)";
mostCurrent._list_brand.Add((Object)(_brand1));
 //BA.debugLineNum = 574;BA.debugLine="img_brand(i).Initialize(\"img_brand\")";
_img_brand[_i].Initialize(mostCurrent.activityBA,"img_brand");
 //BA.debugLineNum = 576;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 577;BA.debugLine="hor_panel.Panel.AddView(img_brand(i),2dip,0,";
_hor_panel.getPanel().AddView((android.view.View)(_img_brand[_i].getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),(int) (0),(int) (mostCurrent._panel_brand.getWidth()/(double)5),mostCurrent._panel_brand.getHeight());
 }else {
 //BA.debugLineNum = 579;BA.debugLine="hor_panel.Panel.AddView(img_brand(i),img_bra";
_hor_panel.getPanel().AddView((android.view.View)(_img_brand[_i].getObject()),(int) (_img_brand[(int) (_i-1)].getLeft()+_img_brand[(int) (_i-1)].getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))),(int) (0),(int) (mostCurrent._panel_brand.getWidth()/(double)5),mostCurrent._panel_brand.getHeight());
 };
 //BA.debugLineNum = 581;BA.debugLine="piccaso.Load(\"http\", Starter.dir_root_image_f";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",mostCurrent._starter._dir_root_image_file_brand+_brand1.pic).SkipMemoryCache().Into(_img_brand[_i]);
 }
};
 //BA.debugLineNum = 583;BA.debugLine="hor_panel.Panel.Width=img_brand(list_records.S";
_hor_panel.getPanel().setWidth((int) (_img_brand[(int) (_list_records.getSize()-1)].getLeft()+_img_brand[(int) (_list_records.getSize()-1)].getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 584;BA.debugLine="apc.SetElevation(panel_brand,7dip)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._panel_brand.getObject()),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 break; }
case 8: {
 //BA.debugLineNum = 590;BA.debugLine="If list_records.Size>0 Then";
if (_list_records.getSize()>0) { 
 //BA.debugLineNum = 591;BA.debugLine="Dim map1 As Map=list_records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_list_records.Get((int) (0))));
 //BA.debugLineNum = 592;BA.debugLine="Dim status As Int";
_status = 0;
 //BA.debugLineNum = 593;BA.debugLine="If IsNumber(map1.Get(\"status\") )=True Then st";
if (anywheresoftware.b4a.keywords.Common.IsNumber(BA.ObjectToString(_map1.Get((Object)("status"))))==anywheresoftware.b4a.keywords.Common.True) { 
_status = (int)(BA.ObjectToNumber(_map1.Get((Object)("status"))));};
 //BA.debugLineNum = 595;BA.debugLine="If Starter.ckeck_app_access=True Then";
if (mostCurrent._starter._ckeck_app_access==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 597;BA.debugLine="Select status";
switch (_status) {
case 0: {
 //BA.debugLineNum = 599;BA.debugLine="Log(0)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(0));
 //BA.debugLineNum = 600;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 601;BA.debugLine="Pd=function.style_msgbox2(\"پیام سیستم\",map";
_pd = mostCurrent._function._style_msgbox2(mostCurrent.activityBA,"پیام سیستم",BA.ObjectToString(_map1.Get((Object)("msg"))));
 //BA.debugLineNum = 602;BA.debugLine="Pd.Create";
_pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 603;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 //BA.debugLineNum = 605;BA.debugLine="Dim ver As Int";
_ver = 0;
 //BA.debugLineNum = 606;BA.debugLine="If IsNumber(map1.Get(\"ver\") )=True Then ve";
if (anywheresoftware.b4a.keywords.Common.IsNumber(BA.ObjectToString(_map1.Get((Object)("ver"))))==anywheresoftware.b4a.keywords.Common.True) { 
_ver = (int)(BA.ObjectToNumber(_map1.Get((Object)("ver"))));};
 //BA.debugLineNum = 607;BA.debugLine="If ver > Application.VersionCode Then";
if (_ver>anywheresoftware.b4a.keywords.Common.Application.getVersionCode()) { 
 //BA.debugLineNum = 608;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 609;BA.debugLine="Pd=function.style_msgbox(\"پیام سیستم\",\"بر";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"پیام سیستم","برنامه شما باید به ورژن جدیدتر آپدیت شود.آیا میخواهید دانلود ورژن جدید صورت بگیرد؟","بلی","خیر","");
 }else {
 //BA.debugLineNum = 621;BA.debugLine="initialize_layout";
_initialize_layout();
 };
 break; }
}
;
 }else {
 //BA.debugLineNum = 625;BA.debugLine="initialize_layout";
_initialize_layout();
 };
 };
 //BA.debugLineNum = 629;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 9: {
 //BA.debugLineNum = 632;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 633;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
}
;
 } 
       catch (Exception e170) {
			processBA.setLastException(e170); //BA.debugLineNum = 639;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 //BA.debugLineNum = 640;BA.debugLine="toast.Initialize(\"برنامه را دوباره اجرا کنید\",to";
mostCurrent._toast.Initialize(mostCurrent.activityBA,"برنامه را دوباره اجرا کنید",mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.INFO);
 //BA.debugLineNum = 641;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 643;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 644;BA.debugLine="End Sub";
return "";
}
public static String  _font_label() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _view1 = null;
anywheresoftware.b4a.objects.LabelWrapper _label_font = null;
 //BA.debugLineNum = 1031;BA.debugLine="Sub font_label";
 //BA.debugLineNum = 1034;BA.debugLine="For Each view1 As View In Activity.GetAllViewsRec";
_view1 = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group1 = mostCurrent._activity.GetAllViewsRecursive();
final int groupLen1 = group1.getSize();
for (int index1 = 0;index1 < groupLen1 ;index1++){
_view1.setObject((android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 1035;BA.debugLine="If view1 Is Label Then";
if (_view1.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 1037;BA.debugLine="Dim label_font As Label";
_label_font = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 1038;BA.debugLine="label_font=view1";
_label_font.setObject((android.widget.TextView)(_view1.getObject()));
 //BA.debugLineNum = 1039;BA.debugLine="Try";
try { } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 1059;BA.debugLine="label_font.Typeface=Starter.font_body";
_label_font.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 };
 };
 }
;
 //BA.debugLineNum = 1065;BA.debugLine="initialize_awsome";
_initialize_awsome();
 //BA.debugLineNum = 1067;BA.debugLine="End Sub";
return "";
}
public static String  _func_get_category(anywheresoftware.b4a.objects.collections.List _lists) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper[] _panel_kala = null;
anywheresoftware.b4a.objects.ImageViewWrapper[] _img_porfroush = null;
anywheresoftware.b4a.objects.ImageViewWrapper[] _im_mark_viesue = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_name_porfroush = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_price_porfroush = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_panel_porfroush = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
String _temp_name_kala = "";
String _url_pic = "";
 //BA.debugLineNum = 780;BA.debugLine="Sub func_get_category(lists As List)";
 //BA.debugLineNum = 781;BA.debugLine="If lists.Size > 0 Then";
if (_lists.getSize()>0) { 
 //BA.debugLineNum = 784;BA.debugLine="Dim panel_kala(lists.Size) As Panel";
_panel_kala = new anywheresoftware.b4a.objects.PanelWrapper[_lists.getSize()];
{
int d0 = _panel_kala.length;
for (int i0 = 0;i0 < d0;i0++) {
_panel_kala[i0] = new anywheresoftware.b4a.objects.PanelWrapper();
}
}
;
 //BA.debugLineNum = 785;BA.debugLine="Dim img_porfroush(lists.Size),im_mark_viesue(lis";
_img_porfroush = new anywheresoftware.b4a.objects.ImageViewWrapper[_lists.getSize()];
{
int d0 = _img_porfroush.length;
for (int i0 = 0;i0 < d0;i0++) {
_img_porfroush[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
_im_mark_viesue = new anywheresoftware.b4a.objects.ImageViewWrapper[_lists.getSize()];
{
int d0 = _im_mark_viesue.length;
for (int i0 = 0;i0 < d0;i0++) {
_im_mark_viesue[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 786;BA.debugLine="Dim lbl_name_porfroush(lists.Size),lbl_price_por";
_lbl_name_porfroush = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_name_porfroush.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_name_porfroush[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
_lbl_price_porfroush = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_price_porfroush.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_price_porfroush[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
 //BA.debugLineNum = 787;BA.debugLine="Dim color_panel_porfroush As ColorDrawable";
_color_panel_porfroush = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 788;BA.debugLine="color_panel_porfroush.Initialize2(Colors.White,7";
_color_panel_porfroush.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (0xff0fa2ec));
 //BA.debugLineNum = 790;BA.debugLine="For i=0 To lists.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 791;BA.debugLine="Dim map1 As Map=lists.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lists.Get(_i)));
 //BA.debugLineNum = 792;BA.debugLine="panel_kala(i).Initialize(\"panel_viesue\")";
_panel_kala[_i].Initialize(mostCurrent.activityBA,"panel_viesue");
 //BA.debugLineNum = 793;BA.debugLine="panel_kala(i).Tag=map1.Get(\"id\") & \";\" & map1.G";
_panel_kala[_i].setTag((Object)(BA.ObjectToString(_map1.Get((Object)("id")))+";"+BA.ObjectToString(_map1.Get((Object)("parent_id")))+";"+BA.ObjectToString(_map1.Get((Object)("name_cat")))));
 //BA.debugLineNum = 794;BA.debugLine="img_porfroush(i).Initialize(\"img_porfroush\")";
_img_porfroush[_i].Initialize(mostCurrent.activityBA,"img_porfroush");
 //BA.debugLineNum = 795;BA.debugLine="im_mark_viesue(i).Initialize(\"im_mark_viesue\")";
_im_mark_viesue[_i].Initialize(mostCurrent.activityBA,"im_mark_viesue");
 //BA.debugLineNum = 796;BA.debugLine="lbl_name_porfroush(i).Initialize(\"lbl_name_porf";
_lbl_name_porfroush[_i].Initialize(mostCurrent.activityBA,"lbl_name_porfroush");
 //BA.debugLineNum = 797;BA.debugLine="lbl_price_porfroush(i).Initialize(\"lbl_price_po";
_lbl_price_porfroush[_i].Initialize(mostCurrent.activityBA,"lbl_price_porfroush");
 //BA.debugLineNum = 799;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 800;BA.debugLine="viesue_panel.Panel.AddView(panel_kala(i),4dip,";
mostCurrent._viesue_panel.getPanel().AddView((android.view.View)(_panel_kala[_i].getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) ((mostCurrent._viesue_panel.getWidth()/(double)2.5)),(int) (mostCurrent._viesue_panel.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 }else {
 //BA.debugLineNum = 802;BA.debugLine="viesue_panel.Panel.AddView(panel_kala(i),panel";
mostCurrent._viesue_panel.getPanel().AddView((android.view.View)(_panel_kala[_i].getObject()),(int) (_panel_kala[(int) (_i-1)].getLeft()+_panel_kala[(int) (_i-1)].getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) ((mostCurrent._viesue_panel.getWidth()/(double)2.5)),(int) (mostCurrent._viesue_panel.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 };
 //BA.debugLineNum = 804;BA.debugLine="panel_kala(i).AddView(img_porfroush(i),5dip,2di";
_panel_kala[_i].AddView((android.view.View)(_img_porfroush[_i].getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),(int) (_panel_kala[_i].getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_panel_kala[_i].getHeight()/(double)1.5));
 //BA.debugLineNum = 805;BA.debugLine="panel_kala(i).AddView(lbl_name_porfroush(i),img";
_panel_kala[_i].AddView((android.view.View)(_lbl_name_porfroush[_i].getObject()),_img_porfroush[_i].getLeft(),(int) (_img_porfroush[_i].getTop()+_img_porfroush[_i].getHeight()),_img_porfroush[_i].getWidth(),(int) ((_panel_kala[_i].getHeight())-(_img_porfroush[_i].getHeight())));
 //BA.debugLineNum = 806;BA.debugLine="Dim temp_name_kala As String=map1.Get(\"name_cat";
_temp_name_kala = BA.ObjectToString(_map1.Get((Object)("name_cat")));
 //BA.debugLineNum = 807;BA.debugLine="If temp_name_kala.Length > 35 Then";
if (_temp_name_kala.length()>35) { 
 //BA.debugLineNum = 808;BA.debugLine="lbl_name_porfroush(i).Text=temp_name_kala.SubS";
_lbl_name_porfroush[_i].setText(BA.ObjectToCharSequence(_temp_name_kala.substring((int) (0),(int) (35))+"..."));
 }else {
 //BA.debugLineNum = 810;BA.debugLine="lbl_name_porfroush(i).Text=temp_name_kala";
_lbl_name_porfroush[_i].setText(BA.ObjectToCharSequence(_temp_name_kala));
 };
 //BA.debugLineNum = 812;BA.debugLine="lbl_name_porfroush(i).TextColor=Colors.Black";
_lbl_name_porfroush[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 813;BA.debugLine="lbl_name_porfroush(i).Gravity=Gravity.CENTER";
_lbl_name_porfroush[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 815;BA.debugLine="lbl_name_porfroush(i).TextSize=11";
_lbl_name_porfroush[_i].setTextSize((float) (11));
 //BA.debugLineNum = 817;BA.debugLine="lbl_name_porfroush(i).BringToFront";
_lbl_name_porfroush[_i].BringToFront();
 //BA.debugLineNum = 820;BA.debugLine="Dim url_pic As String=map1.Get(\"pic_cat\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic_cat")));
 //BA.debugLineNum = 821;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Then";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 822;BA.debugLine="url_pic=Starter.dir_root_image_file_cat & map1";
_url_pic = mostCurrent._starter._dir_root_image_file_cat+BA.ObjectToString(_map1.Get((Object)("pic_cat")));
 };
 //BA.debugLineNum = 825;BA.debugLine="piccaso.Load(\"http\",url_pic).SkipMemoryCache.In";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).SkipMemoryCache().Into(_img_porfroush[_i]);
 //BA.debugLineNum = 826;BA.debugLine="panel_kala(i).Background=color_panel_porfroush";
_panel_kala[_i].setBackground((android.graphics.drawable.Drawable)(_color_panel_porfroush.getObject()));
 //BA.debugLineNum = 827;BA.debugLine="apc.SetElevation(panel_kala(i),9)";
mostCurrent._apc.SetElevation((android.view.View)(_panel_kala[_i].getObject()),(float) (9));
 }
};
 //BA.debugLineNum = 829;BA.debugLine="viesue_panel.Panel.Width=panel_kala(panel_kala.L";
mostCurrent._viesue_panel.getPanel().setWidth((int) (_panel_kala[(int) (_panel_kala.length-1)].getLeft()+_panel_kala[(int) (_panel_kala.length-1)].getWidth()));
 };
 //BA.debugLineNum = 832;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 834;BA.debugLine="font_label";
_font_label();
 //BA.debugLineNum = 836;BA.debugLine="ChangeFontByLabelSize.MinimomFontLabel(btn_list_p";
mostCurrent._changefontbylabelsize._minimomfontlabel(mostCurrent.activityBA,mostCurrent._btn_list_porfroush,(int) (13));
 //BA.debugLineNum = 837;BA.debugLine="get_slideshow";
_get_slideshow();
 //BA.debugLineNum = 840;BA.debugLine="End Sub";
return "";
}
public static String  _func_get_porfroush(anywheresoftware.b4a.objects.collections.List _lists) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper[] _panel_kala = null;
anywheresoftware.b4a.objects.ImageViewWrapper[] _img_porfroush = null;
anywheresoftware.b4a.objects.ImageViewWrapper[] _im_mark_viesue = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_name_porfroush = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_price_porfroush = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_panel_porfroush = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
int _stut_viesue = 0;
String _temp_name_kala = "";
String _url_pic = "";
 //BA.debugLineNum = 842;BA.debugLine="Sub func_get_porfroush(lists As List)";
 //BA.debugLineNum = 843;BA.debugLine="Try";
try { //BA.debugLineNum = 844;BA.debugLine="If lists.Size > 0 Then";
if (_lists.getSize()>0) { 
 //BA.debugLineNum = 847;BA.debugLine="Dim panel_kala(lists.Size) As Panel";
_panel_kala = new anywheresoftware.b4a.objects.PanelWrapper[_lists.getSize()];
{
int d0 = _panel_kala.length;
for (int i0 = 0;i0 < d0;i0++) {
_panel_kala[i0] = new anywheresoftware.b4a.objects.PanelWrapper();
}
}
;
 //BA.debugLineNum = 848;BA.debugLine="Dim img_porfroush(lists.Size),im_mark_viesue(li";
_img_porfroush = new anywheresoftware.b4a.objects.ImageViewWrapper[_lists.getSize()];
{
int d0 = _img_porfroush.length;
for (int i0 = 0;i0 < d0;i0++) {
_img_porfroush[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
_im_mark_viesue = new anywheresoftware.b4a.objects.ImageViewWrapper[_lists.getSize()];
{
int d0 = _im_mark_viesue.length;
for (int i0 = 0;i0 < d0;i0++) {
_im_mark_viesue[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 849;BA.debugLine="Dim lbl_name_porfroush(lists.Size),lbl_price_po";
_lbl_name_porfroush = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_name_porfroush.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_name_porfroush[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
_lbl_price_porfroush = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_price_porfroush.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_price_porfroush[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
 //BA.debugLineNum = 850;BA.debugLine="Dim color_panel_porfroush As ColorDrawable";
_color_panel_porfroush = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 851;BA.debugLine="color_panel_porfroush.Initialize2(Colors.White,";
_color_panel_porfroush.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (0xff0fa2ec));
 //BA.debugLineNum = 853;BA.debugLine="For i=0 To lists.Size-1";
{
final int step8 = 1;
final int limit8 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 854;BA.debugLine="Dim map1 As Map=lists.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lists.Get(_i)));
 //BA.debugLineNum = 855;BA.debugLine="panel_kala(i).Initialize(\"panel_porfroush\")";
_panel_kala[_i].Initialize(mostCurrent.activityBA,"panel_porfroush");
 //BA.debugLineNum = 856;BA.debugLine="panel_kala(i).Tag=map1.Get(\"code_kala\")";
_panel_kala[_i].setTag(_map1.Get((Object)("code_kala")));
 //BA.debugLineNum = 857;BA.debugLine="img_porfroush(i).Initialize(\"img_porfroush\")";
_img_porfroush[_i].Initialize(mostCurrent.activityBA,"img_porfroush");
 //BA.debugLineNum = 858;BA.debugLine="im_mark_viesue(i).Initialize(\"im_mark_viesue\")";
_im_mark_viesue[_i].Initialize(mostCurrent.activityBA,"im_mark_viesue");
 //BA.debugLineNum = 859;BA.debugLine="lbl_name_porfroush(i).Initialize(\"lbl_name_por";
_lbl_name_porfroush[_i].Initialize(mostCurrent.activityBA,"lbl_name_porfroush");
 //BA.debugLineNum = 860;BA.debugLine="lbl_price_porfroush(i).Initialize(\"lbl_price_p";
_lbl_price_porfroush[_i].Initialize(mostCurrent.activityBA,"lbl_price_porfroush");
 //BA.debugLineNum = 862;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 863;BA.debugLine="porfroush_panel.Panel.AddView(panel_kala(i),4";
mostCurrent._porfroush_panel.getPanel().AddView((android.view.View)(_panel_kala[_i].getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) ((mostCurrent._porfroush_panel.getWidth()/(double)2.5)),(int) (mostCurrent._porfroush_panel.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 }else {
 //BA.debugLineNum = 865;BA.debugLine="porfroush_panel.Panel.AddView(panel_kala(i),p";
mostCurrent._porfroush_panel.getPanel().AddView((android.view.View)(_panel_kala[_i].getObject()),(int) (_panel_kala[(int) (_i-1)].getLeft()+_panel_kala[(int) (_i-1)].getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) ((mostCurrent._porfroush_panel.getWidth()/(double)2.5)),(int) (mostCurrent._porfroush_panel.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 };
 //BA.debugLineNum = 867;BA.debugLine="panel_kala(i).AddView(img_porfroush(i),5dip,2d";
_panel_kala[_i].AddView((android.view.View)(_img_porfroush[_i].getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),(int) (_panel_kala[_i].getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_panel_kala[_i].getHeight()/(double)2));
 //BA.debugLineNum = 868;BA.debugLine="panel_kala(i).AddView(lbl_name_porfroush(i),im";
_panel_kala[_i].AddView((android.view.View)(_lbl_name_porfroush[_i].getObject()),_img_porfroush[_i].getLeft(),(int) (_img_porfroush[_i].getTop()+_img_porfroush[_i].getHeight()),_img_porfroush[_i].getWidth(),(int) ((_img_porfroush[_i].getHeight()/(double)2)/(double)1.1));
 //BA.debugLineNum = 869;BA.debugLine="panel_kala(i).AddView(lbl_price_porfroush(i),0";
_panel_kala[_i].AddView((android.view.View)(_lbl_price_porfroush[_i].getObject()),(int) (0),(int) (_lbl_name_porfroush[_i].getTop()+_lbl_name_porfroush[_i].getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),_panel_kala[_i].getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 870;BA.debugLine="panel_kala(i).AddView(im_mark_viesue(i),panel_";
_panel_kala[_i].AddView((android.view.View)(_im_mark_viesue[_i].getObject()),(int) (_panel_kala[_i].getWidth()/(double)2.5),(int) (0),(int) (_panel_kala[_i].getWidth()-(_panel_kala[_i].getWidth()/(double)2.5)),(int) (_panel_kala[_i].getHeight()/(double)2));
 //BA.debugLineNum = 873;BA.debugLine="Dim stut_viesue As Int=	map1.Get(\"stut_viesue\"";
_stut_viesue = (int)(BA.ObjectToNumber(_map1.Get((Object)("stut_viesue"))));
 //BA.debugLineNum = 874;BA.debugLine="If stut_viesue=1 Then";
if (_stut_viesue==1) { 
 //BA.debugLineNum = 875;BA.debugLine="im_mark_viesue(i).Visible=True";
_im_mark_viesue[_i].setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 876;BA.debugLine="lbl_price_porfroush(i).Visible=False";
_lbl_price_porfroush[_i].setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 877;BA.debugLine="im_mark_viesue(i).Bitmap=LoadBitmap(File.DirA";
_im_mark_viesue[_i].setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"badge_list2.png").getObject()));
 //BA.debugLineNum = 878;BA.debugLine="im_mark_viesue(i).Gravity=Gravity.FILL";
_im_mark_viesue[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 }else {
 //BA.debugLineNum = 880;BA.debugLine="im_mark_viesue(i).Visible=False";
_im_mark_viesue[_i].setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 883;BA.debugLine="lbl_price_porfroush(i).Text=toman.number(map1.";
_lbl_price_porfroush[_i].setText(BA.ObjectToCharSequence(mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان"));
 //BA.debugLineNum = 884;BA.debugLine="lbl_price_porfroush(i).TextColor=Colors.RGB(48";
_lbl_price_porfroush[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (48),(int) (146),(int) (10)));
 //BA.debugLineNum = 885;BA.debugLine="lbl_price_porfroush(i).Gravity=Gravity.CENTER";
_lbl_price_porfroush[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 886;BA.debugLine="lbl_price_porfroush(i).BringToFront";
_lbl_price_porfroush[_i].BringToFront();
 //BA.debugLineNum = 887;BA.debugLine="ChangeFontByLabelSize.MinimomFontLabel(lbl_pri";
mostCurrent._changefontbylabelsize._minimomfontlabel(mostCurrent.activityBA,_lbl_price_porfroush[_i],(int) (14));
 //BA.debugLineNum = 888;BA.debugLine="Dim temp_name_kala As String=map1.Get(\"name_ka";
_temp_name_kala = BA.ObjectToString(_map1.Get((Object)("name_kala")));
 //BA.debugLineNum = 889;BA.debugLine="If temp_name_kala.Length > 35 Then";
if (_temp_name_kala.length()>35) { 
 //BA.debugLineNum = 890;BA.debugLine="lbl_name_porfroush(i).Text=temp_name_kala.Sub";
_lbl_name_porfroush[_i].setText(BA.ObjectToCharSequence(_temp_name_kala.substring((int) (0),(int) (35))+"..."));
 }else {
 //BA.debugLineNum = 892;BA.debugLine="lbl_name_porfroush(i).Text=temp_name_kala";
_lbl_name_porfroush[_i].setText(BA.ObjectToCharSequence(_temp_name_kala));
 };
 //BA.debugLineNum = 894;BA.debugLine="lbl_name_porfroush(i).TextColor=Colors.Black";
_lbl_name_porfroush[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 895;BA.debugLine="lbl_name_porfroush(i).Gravity=Gravity.CENTER";
_lbl_name_porfroush[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 897;BA.debugLine="lbl_name_porfroush(i).TextSize=11";
_lbl_name_porfroush[_i].setTextSize((float) (11));
 //BA.debugLineNum = 899;BA.debugLine="lbl_name_porfroush(i).BringToFront";
_lbl_name_porfroush[_i].BringToFront();
 //BA.debugLineNum = 902;BA.debugLine="Dim url_pic As String=map1.Get(\"pic\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 903;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Then";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 904;BA.debugLine="url_pic=Starter.dir_root_image_file_thumnail";
_url_pic = mostCurrent._starter._dir_root_image_file_thumnail+BA.ObjectToString(_map1.Get((Object)("pic")));
 };
 //BA.debugLineNum = 907;BA.debugLine="piccaso.Load(\"http\",url_pic).SkipMemoryCache.I";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).SkipMemoryCache().Into(_img_porfroush[_i]);
 //BA.debugLineNum = 908;BA.debugLine="panel_kala(i).Background=color_panel_porfroush";
_panel_kala[_i].setBackground((android.graphics.drawable.Drawable)(_color_panel_porfroush.getObject()));
 //BA.debugLineNum = 909;BA.debugLine="apc.SetElevation(panel_kala(i),6)";
mostCurrent._apc.SetElevation((android.view.View)(_panel_kala[_i].getObject()),(float) (6));
 }
};
 //BA.debugLineNum = 911;BA.debugLine="porfroush_panel.Panel.Width=panel_kala(panel_ka";
mostCurrent._porfroush_panel.getPanel().setWidth((int) (_panel_kala[(int) (_panel_kala.length-1)].getLeft()+_panel_kala[(int) (_panel_kala.length-1)].getWidth()));
 //BA.debugLineNum = 912;BA.debugLine="progress_porfroosh.Visible=False";
mostCurrent._progress_porfroosh.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 914;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 916;BA.debugLine="font_label";
_font_label();
 //BA.debugLineNum = 917;BA.debugLine="splash.Visible=False";
mostCurrent._splash.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 918;BA.debugLine="Panel1.Visible=True";
mostCurrent._panel1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 920;BA.debugLine="ChangeFontByLabelSize.MinimomFontLabel(btn_list_";
mostCurrent._changefontbylabelsize._minimomfontlabel(mostCurrent.activityBA,mostCurrent._btn_list_porfroush,(int) (13));
 } 
       catch (Exception e66) {
			processBA.setLastException(e66); //BA.debugLineNum = 923;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 };
 //BA.debugLineNum = 925;BA.debugLine="End Sub";
return "";
}
public static String  _func_get_slideshow(anywheresoftware.b4a.objects.collections.List _lists) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst1 = null;
anywheresoftware.b4a.objects.collections.List _lst2 = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
String _url_pic = "";
 //BA.debugLineNum = 927;BA.debugLine="Sub func_get_slideshow(lists As List)";
 //BA.debugLineNum = 928;BA.debugLine="Dim lst1,lst2 As List";
_lst1 = new anywheresoftware.b4a.objects.collections.List();
_lst2 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 929;BA.debugLine="lst1.Initialize";
_lst1.Initialize();
 //BA.debugLineNum = 930;BA.debugLine="lst2.Initialize";
_lst2.Initialize();
 //BA.debugLineNum = 931;BA.debugLine="If lists.Size > 0 Then";
if (_lists.getSize()>0) { 
 //BA.debugLineNum = 932;BA.debugLine="For i=0 To lists.Size-1";
{
final int step5 = 1;
final int limit5 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step5 > 0 && _i <= limit5) || (step5 < 0 && _i >= limit5); _i = ((int)(0 + _i + step5)) ) {
 //BA.debugLineNum = 933;BA.debugLine="Dim map1 As Map=lists.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lists.Get(_i)));
 //BA.debugLineNum = 937;BA.debugLine="Dim url_pic As String=map1.Get(\"pic\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 938;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False The";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 939;BA.debugLine="url_pic=Starter.dir_root_image_file_slideshow";
_url_pic = mostCurrent._starter._dir_root_image_file_slideshow+BA.ObjectToString(_map1.Get((Object)("pic")));
 };
 //BA.debugLineNum = 942;BA.debugLine="lst1.Add(url_pic)";
_lst1.Add((Object)(_url_pic));
 //BA.debugLineNum = 943;BA.debugLine="lst2.Add(map1.Get(\"info\"))";
_lst2.Add(_map1.Get((Object)("info")));
 }
};
 //BA.debugLineNum = 946;BA.debugLine="initialize_slideshow(	lst1 	)";
_initialize_slideshow(_lst1);
 };
 //BA.debugLineNum = 948;BA.debugLine="bool_load_porfroush=False";
_bool_load_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 949;BA.debugLine="get_porfroush";
_get_porfroush();
 //BA.debugLineNum = 950;BA.debugLine="End Sub";
return "";
}
public static String  _func_time_life_viesue(anywheresoftware.b4a.objects.collections.List _lists) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
 //BA.debugLineNum = 952;BA.debugLine="Sub func_time_life_viesue(lists As List)";
 //BA.debugLineNum = 953;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 954;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 955;BA.debugLine="If lists.Size > 0 Then";
if (_lists.getSize()>0) { 
 //BA.debugLineNum = 956;BA.debugLine="For i=0 To lists.Size-1";
{
final int step4 = 1;
final int limit4 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 957;BA.debugLine="Dim map1 As Map=lists.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lists.Get(_i)));
 }
};
 //BA.debugLineNum = 959;BA.debugLine="time_viesue=map1.Get(\"time_viesue\")";
_time_viesue = BA.ObjectToLongNumber(_map1.Get((Object)("time_viesue")));
 //BA.debugLineNum = 960;BA.debugLine="get_time";
_get_time();
 };
 //BA.debugLineNum = 962;BA.debugLine="End Sub";
return "";
}
public static String  _func_viesue(anywheresoftware.b4a.objects.collections.List _lists) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper[] _panel_kala2 = null;
anywheresoftware.b4a.objects.ImageViewWrapper[] _img_viesue = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_name_viesue = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_price_viesue = null;
anywheresoftware.b4a.objects.LabelWrapper[] _lbl_price_off_viesue = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_panel_viesue = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
String _temp_name_kala = "";
anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _strick = null;
String _price_off_strick = "";
String _url_pic = "";
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 686;BA.debugLine="Sub func_viesue(lists As List)";
 //BA.debugLineNum = 687;BA.debugLine="If lists.Size > 0 Then";
if (_lists.getSize()>0) { 
 //BA.debugLineNum = 689;BA.debugLine="Dim panel_kala2(lists.Size) As Panel";
_panel_kala2 = new anywheresoftware.b4a.objects.PanelWrapper[_lists.getSize()];
{
int d0 = _panel_kala2.length;
for (int i0 = 0;i0 < d0;i0++) {
_panel_kala2[i0] = new anywheresoftware.b4a.objects.PanelWrapper();
}
}
;
 //BA.debugLineNum = 690;BA.debugLine="Dim img_viesue(lists.Size) As ImageView";
_img_viesue = new anywheresoftware.b4a.objects.ImageViewWrapper[_lists.getSize()];
{
int d0 = _img_viesue.length;
for (int i0 = 0;i0 < d0;i0++) {
_img_viesue[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 691;BA.debugLine="Dim lbl_name_viesue(lists.Size),lbl_price_viesue";
_lbl_name_viesue = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_name_viesue.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_name_viesue[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
_lbl_price_viesue = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_price_viesue.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_price_viesue[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
_lbl_price_off_viesue = new anywheresoftware.b4a.objects.LabelWrapper[_lists.getSize()];
{
int d0 = _lbl_price_off_viesue.length;
for (int i0 = 0;i0 < d0;i0++) {
_lbl_price_off_viesue[i0] = new anywheresoftware.b4a.objects.LabelWrapper();
}
}
;
 //BA.debugLineNum = 692;BA.debugLine="Dim color_panel_viesue As ColorDrawable";
_color_panel_viesue = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 693;BA.debugLine="color_panel_viesue.Initialize2(Colors.White,15,0";
_color_panel_viesue.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (15),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),(int) (0xff0fa2ec));
 //BA.debugLineNum = 695;BA.debugLine="For i=0 To lists.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_lists.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 696;BA.debugLine="Dim map1 As Map=lists.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_lists.Get(_i)));
 //BA.debugLineNum = 697;BA.debugLine="panel_kala2(i).Initialize(\"panel_viesue\")";
_panel_kala2[_i].Initialize(mostCurrent.activityBA,"panel_viesue");
 //BA.debugLineNum = 698;BA.debugLine="panel_kala2(i).Tag=map1.Get(\"code_kala\")";
_panel_kala2[_i].setTag(_map1.Get((Object)("code_kala")));
 //BA.debugLineNum = 699;BA.debugLine="img_viesue(i).Initialize(\"img_viesue\")";
_img_viesue[_i].Initialize(mostCurrent.activityBA,"img_viesue");
 //BA.debugLineNum = 700;BA.debugLine="lbl_name_viesue(i).Initialize(\"lbl_name_viesue\"";
_lbl_name_viesue[_i].Initialize(mostCurrent.activityBA,"lbl_name_viesue");
 //BA.debugLineNum = 701;BA.debugLine="lbl_price_viesue(i).Initialize(\"lbl_price_viesu";
_lbl_price_viesue[_i].Initialize(mostCurrent.activityBA,"lbl_price_viesue");
 //BA.debugLineNum = 702;BA.debugLine="lbl_price_off_viesue(i).Initialize(\"lbl_price_o";
_lbl_price_off_viesue[_i].Initialize(mostCurrent.activityBA,"lbl_price_off_viesue");
 //BA.debugLineNum = 703;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 704;BA.debugLine="Log(\"pnl\")";
anywheresoftware.b4a.keywords.Common.Log("pnl");
 //BA.debugLineNum = 705;BA.debugLine="viesue_panel.Panel.AddView(panel_kala2(i),0,3d";
mostCurrent._viesue_panel.getPanel().AddView((android.view.View)(_panel_kala2[_i].getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) ((mostCurrent._viesue_panel.getWidth()/(double)3.5)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (mostCurrent._viesue_panel.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 }else {
 //BA.debugLineNum = 707;BA.debugLine="viesue_panel.Panel.AddView(panel_kala2(i),pane";
mostCurrent._viesue_panel.getPanel().AddView((android.view.View)(_panel_kala2[_i].getObject()),(int) (_panel_kala2[(int) (_i-1)].getLeft()+_panel_kala2[(int) (_i-1)].getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3)),(int) ((mostCurrent._viesue_panel.getWidth()/(double)3.5)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (mostCurrent._viesue_panel.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 };
 //BA.debugLineNum = 712;BA.debugLine="panel_kala2(i).AddView(img_viesue(i),5dip,2dip,";
_panel_kala2[_i].AddView((android.view.View)(_img_viesue[_i].getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),(int) (_panel_kala2[_i].getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_panel_kala2[_i].getHeight()/(double)2));
 //BA.debugLineNum = 713;BA.debugLine="panel_kala2(i).AddView(lbl_name_viesue(i),img_v";
_panel_kala2[_i].AddView((android.view.View)(_lbl_name_viesue[_i].getObject()),_img_viesue[_i].getLeft(),(int) (_img_viesue[_i].getTop()+_img_viesue[_i].getHeight()),_img_viesue[_i].getWidth(),(int) ((_img_viesue[_i].getHeight()/(double)2)/(double)1.2));
 //BA.debugLineNum = 714;BA.debugLine="panel_kala2(i).AddView(lbl_price_off_viesue(i),";
_panel_kala2[_i].AddView((android.view.View)(_lbl_price_off_viesue[_i].getObject()),(int) (0),(int) (_lbl_name_viesue[_i].getTop()+_lbl_name_viesue[_i].getHeight()),_panel_kala2[_i].getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 715;BA.debugLine="panel_kala2(i).AddView(lbl_price_viesue(i),0,lb";
_panel_kala2[_i].AddView((android.view.View)(_lbl_price_viesue[_i].getObject()),(int) (0),(int) (_lbl_price_off_viesue[_i].getTop()+_lbl_price_off_viesue[_i].getHeight()),_panel_kala2[_i].getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 718;BA.debugLine="lbl_price_viesue(i).Text=toman.number(map1.Get(";
_lbl_price_viesue[_i].setText(BA.ObjectToCharSequence(mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price_off"))))+" تومان"));
 //BA.debugLineNum = 719;BA.debugLine="lbl_price_viesue(i).TextSize=lbl_price_viesue(i";
_lbl_price_viesue[_i].setTextSize((float) (_lbl_price_viesue[_i].getTextSize()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 720;BA.debugLine="lbl_price_viesue(i).TextColor=Colors.RGB(48,146";
_lbl_price_viesue[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (48),(int) (146),(int) (10)));
 //BA.debugLineNum = 721;BA.debugLine="lbl_price_viesue(i).Gravity=Gravity.CENTER";
_lbl_price_viesue[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 722;BA.debugLine="lbl_price_viesue(i).BringToFront";
_lbl_price_viesue[_i].BringToFront();
 //BA.debugLineNum = 724;BA.debugLine="Dim temp_name_kala As String=map1.Get(\"name_kal";
_temp_name_kala = BA.ObjectToString(_map1.Get((Object)("name_kala")));
 //BA.debugLineNum = 726;BA.debugLine="If temp_name_kala.Length > 35 Then";
if (_temp_name_kala.length()>35) { 
 //BA.debugLineNum = 727;BA.debugLine="lbl_name_viesue(i).Text=temp_name_kala.SubStri";
_lbl_name_viesue[_i].setText(BA.ObjectToCharSequence(_temp_name_kala.substring((int) (0),(int) (35))+"..."));
 }else {
 //BA.debugLineNum = 729;BA.debugLine="lbl_name_viesue(i).Text=temp_name_kala";
_lbl_name_viesue[_i].setText(BA.ObjectToCharSequence(_temp_name_kala));
 };
 //BA.debugLineNum = 732;BA.debugLine="lbl_name_viesue(i).TextColor=Colors.Black";
_lbl_name_viesue[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 733;BA.debugLine="lbl_name_viesue(i).Gravity=Gravity.CENTER";
_lbl_name_viesue[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 735;BA.debugLine="lbl_name_viesue(i).TextSize=13";
_lbl_name_viesue[_i].setTextSize((float) (13));
 //BA.debugLineNum = 737;BA.debugLine="lbl_name_viesue(i).BringToFront";
_lbl_name_viesue[_i].BringToFront();
 //BA.debugLineNum = 739;BA.debugLine="Dim strick As RichString";
_strick = new anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString();
 //BA.debugLineNum = 740;BA.debugLine="Dim price_off_strick As String=toman.number(map";
_price_off_strick = mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان";
 //BA.debugLineNum = 741;BA.debugLine="strick.Initialize(price_off_strick)";
_strick.Initialize(BA.ObjectToCharSequence(_price_off_strick));
 //BA.debugLineNum = 742;BA.debugLine="strick.Strikethrough(0,price_off_strick.Length)";
_strick.Strikethrough((int) (0),_price_off_strick.length());
 //BA.debugLineNum = 743;BA.debugLine="lbl_price_off_viesue(i).Text=strick";
_lbl_price_off_viesue[_i].setText(BA.ObjectToCharSequence(_strick.getObject()));
 //BA.debugLineNum = 744;BA.debugLine="lbl_price_off_viesue(i).TextSize=lbl_price_off_";
_lbl_price_off_viesue[_i].setTextSize((float) (_lbl_price_off_viesue[_i].getTextSize()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 745;BA.debugLine="lbl_price_off_viesue(i).TextColor=Colors.Red";
_lbl_price_off_viesue[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 746;BA.debugLine="lbl_price_off_viesue(i).Gravity=Gravity.CENTER";
_lbl_price_off_viesue[_i].setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 747;BA.debugLine="lbl_price_off_viesue(i).BringToFront";
_lbl_price_off_viesue[_i].BringToFront();
 //BA.debugLineNum = 749;BA.debugLine="If Activity.Height>1800 Then";
if (mostCurrent._activity.getHeight()>1800) { 
 //BA.debugLineNum = 750;BA.debugLine="lbl_price_viesue(i).TextSize=13";
_lbl_price_viesue[_i].setTextSize((float) (13));
 //BA.debugLineNum = 751;BA.debugLine="lbl_price_off_viesue(i).TextSize=12";
_lbl_price_off_viesue[_i].setTextSize((float) (12));
 };
 //BA.debugLineNum = 755;BA.debugLine="Dim url_pic As String=map1.Get(\"pic\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 756;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Then";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 757;BA.debugLine="url_pic=Starter.dir_root_image_file_thumnail &";
_url_pic = mostCurrent._starter._dir_root_image_file_thumnail+BA.ObjectToString(_map1.Get((Object)("pic")));
 };
 //BA.debugLineNum = 760;BA.debugLine="piccaso.Load(\"http\",url_pic).Resize(img_viesue(";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).Resize(_img_viesue[_i].getWidth(),_img_viesue[_i].getHeight()).Error(mostCurrent._starter._error_image).Placeholder(mostCurrent._starter._progress_image).SkipMemoryCache().Into(_img_viesue[_i]);
 //BA.debugLineNum = 761;BA.debugLine="panel_kala2(i).Background=color_panel_viesue";
_panel_kala2[_i].setBackground((android.graphics.drawable.Drawable)(_color_panel_viesue.getObject()));
 //BA.debugLineNum = 762;BA.debugLine="apc.SetElevation(panel_kala2(i),6)";
mostCurrent._apc.SetElevation((android.view.View)(_panel_kala2[_i].getObject()),(float) (6));
 }
};
 //BA.debugLineNum = 765;BA.debugLine="viesue_panel.Panel.Width=panel_kala2(panel_kala2";
mostCurrent._viesue_panel.getPanel().setWidth((int) (_panel_kala2[(int) (_panel_kala2.length-1)].getLeft()+_panel_kala2[(int) (_panel_kala2.length-1)].getWidth()));
 //BA.debugLineNum = 768;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 769;BA.debugLine="r.Target = viesue_panel";
_r.Target = (Object)(mostCurrent._viesue_panel.getObject());
 //BA.debugLineNum = 770;BA.debugLine="r.RunMethod2(\"setHorizontalScrollBarEnabled\", Fa";
_r.RunMethod2("setHorizontalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 773;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 774;BA.debugLine="font_label";
_font_label();
 //BA.debugLineNum = 776;BA.debugLine="get_slideshow";
_get_slideshow();
 };
 //BA.debugLineNum = 778;BA.debugLine="End Sub";
return "";
}
public static String  _get_baners(int _group) throws Exception{
 //BA.debugLineNum = 403;BA.debugLine="Sub get_baners(group As Int)";
 //BA.debugLineNum = 405;BA.debugLine="Select group";
switch (_group) {
case 1: {
 //BA.debugLineNum = 407;BA.debugLine="connector.send_query($\"select * from `baners` w";
mostCurrent._connector._send_query(mostCurrent.activityBA,("select * from `baners` where `group`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_group))+" ORDER BY  `order` ASC"),"baners1",(Object)(""));
 break; }
case 2: {
 //BA.debugLineNum = 409;BA.debugLine="connector.send_query($\"select * from `baners` w";
mostCurrent._connector._send_query(mostCurrent.activityBA,("select * from `baners` where `group`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_group))+" ORDER BY  `order` ASC"),"baners2",(Object)(""));
 break; }
}
;
 //BA.debugLineNum = 412;BA.debugLine="End Sub";
return "";
}
public static String  _get_category() throws Exception{
 //BA.debugLineNum = 972;BA.debugLine="Sub get_category";
 //BA.debugLineNum = 973;BA.debugLine="Log(\"get_category\")";
anywheresoftware.b4a.keywords.Common.Log("get_category");
 //BA.debugLineNum = 974;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 975;BA.debugLine="connector.send_query($\"SELECT * FROM  `category`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM  `category` where `parent_id`=0"),"get_category",(Object)(""));
 //BA.debugLineNum = 976;BA.debugLine="End Sub";
return "";
}
public static String  _get_info_user(int _index) throws Exception{
 //BA.debugLineNum = 1442;BA.debugLine="Public Sub get_info_user(index As Int) As String";
 //BA.debugLineNum = 1443;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=True Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1444;BA.debugLine="Dim user_info As List";
mostCurrent._user_info = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1445;BA.debugLine="user_info.Initialize";
mostCurrent._user_info.Initialize();
 //BA.debugLineNum = 1446;BA.debugLine="user_info=File.ReadList(myLibrary.rute,name_dat)";
mostCurrent._user_info = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat);
 //BA.debugLineNum = 1447;BA.debugLine="Return user_info.Get(index)";
if (true) return BA.ObjectToString(mostCurrent._user_info.Get(_index));
 }else {
 //BA.debugLineNum = 1449;BA.debugLine="Log(\"NOT FILE USER\")";
anywheresoftware.b4a.keywords.Common.Log("NOT FILE USER");
 };
 //BA.debugLineNum = 1451;BA.debugLine="End Sub";
return "";
}
public static String  _get_porfroush() throws Exception{
 //BA.debugLineNum = 979;BA.debugLine="Sub get_porfroush";
 //BA.debugLineNum = 980;BA.debugLine="Log(\"get_porfroush\")";
anywheresoftware.b4a.keywords.Common.Log("get_porfroush");
 //BA.debugLineNum = 981;BA.debugLine="connector.send_query($\"SELECT DISTINCT * FROM `ka";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT DISTINCT * FROM `kala` INNER JOIN `por_froush` ON `kala`.`id`= `por_froush`.`code_kala` where `kala`.`number` > 0 AND `show_kala`=1 ORDER BY `code_kala` Desc LIMIT 0 , 8"),"get_porfroush",(Object)(""));
 //BA.debugLineNum = 982;BA.debugLine="End Sub";
return "";
}
public static String  _get_slideshow() throws Exception{
 //BA.debugLineNum = 984;BA.debugLine="Sub get_slideshow";
 //BA.debugLineNum = 985;BA.debugLine="Log(\"get_slideshow\")";
anywheresoftware.b4a.keywords.Common.Log("get_slideshow");
 //BA.debugLineNum = 987;BA.debugLine="connector.send_query($\"SELECT * FROM `slide_show`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `slide_show` where `device`='1'"),"get_slideshow",(Object)(""));
 //BA.debugLineNum = 988;BA.debugLine="End Sub";
return "";
}
public static String  _get_time() throws Exception{
 //BA.debugLineNum = 996;BA.debugLine="Sub get_time";
 //BA.debugLineNum = 997;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 998;BA.debugLine="connector.send_query($\"time\"$,\"get_time\",\"\")";
mostCurrent._connector._send_query(mostCurrent.activityBA,("time"),"get_time",(Object)(""));
 //BA.debugLineNum = 999;BA.debugLine="End Sub";
return "";
}
public static String  _get_viesue() throws Exception{
 //BA.debugLineNum = 966;BA.debugLine="Sub get_viesue";
 //BA.debugLineNum = 967;BA.debugLine="Log(\"get_viesue\")";
anywheresoftware.b4a.keywords.Common.Log("get_viesue");
 //BA.debugLineNum = 968;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 969;BA.debugLine="connector.send_query($\"SELECT DISTINCT * FROM  `k";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT DISTINCT * FROM  `kala` INNER JOIN  `viesue` ON  `kala`.`id` =  `viesue`.`code_kala` where `kala`.`number` >0 AND `show_kala`=1 ORDER BY  `code_kala` DESC LIMIT 0 , 8"),"get_viesue",(Object)(""));
 //BA.debugLineNum = 970;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 44;BA.debugLine="Type baner_list_1(src_pic As String,desc As Strin";
;
 //BA.debugLineNum = 45;BA.debugLine="Dim list1_baner As List";
mostCurrent._list1_baner = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 46;BA.debugLine="Dim apc As AppCompat";
mostCurrent._apc = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 47;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 48;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 49;BA.debugLine="Dim time,time_viesue As Long";
_time = 0L;
_time_viesue = 0L;
 //BA.debugLineNum = 53;BA.debugLine="Private lbl_account As Label";
mostCurrent._lbl_account = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Public name_dat As String";
_name_dat = "";
 //BA.debugLineNum = 58;BA.debugLine="Private lbl_account As Label";
mostCurrent._lbl_account = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private btn_menu As Button";
mostCurrent._btn_menu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 64;BA.debugLine="Private Panel_slider As Panel";
mostCurrent._panel_slider = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Dim piccaso As Hitex_Picasso";
mostCurrent._piccaso = new wir.hitex.recycler.Hitex_Picasso();
 //BA.debugLineNum = 67;BA.debugLine="Dim toman As money";
mostCurrent._toman = new b4a.example.money();
 //BA.debugLineNum = 69;BA.debugLine="Dim list_kala As List";
mostCurrent._list_kala = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 71;BA.debugLine="Dim bool_load_porfroush As Boolean=True";
_bool_load_porfroush = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 73;BA.debugLine="Private porfroush_panel,viesue_panel As Horizonta";
mostCurrent._porfroush_panel = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
mostCurrent._viesue_panel = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Private panel_timer As Panel";
mostCurrent._panel_timer = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Private img_vip As ImageView";
mostCurrent._img_vip = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 83;BA.debugLine="Private lbl_title_news As Label";
mostCurrent._lbl_title_news = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 85;BA.debugLine="Private lbl_badge As Label";
mostCurrent._lbl_badge = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 86;BA.debugLine="Dim bool_click_link As Boolean=False";
_bool_click_link = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 87;BA.debugLine="Dim checked_update As Boolean=False";
_checked_update = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 89;BA.debugLine="Dim pa As PagerBulletAdapter";
mostCurrent._pa = new com.porya.pagerbullet.PagerBulletAdapter();
 //BA.debugLineNum = 90;BA.debugLine="Dim pb As PagerBullet";
mostCurrent._pb = new com.porya.pagerbullet.PagerBulletWrapper();
 //BA.debugLineNum = 91;BA.debugLine="Dim timer_slide As Timer";
mostCurrent._timer_slide = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 92;BA.debugLine="Dim index_slider As Int=0";
_index_slider = (int) (0);
 //BA.debugLineNum = 95;BA.debugLine="Public MDB As MSMaterialDrawerBuilder";
mostCurrent._mdb = new com.maximussoft.msmaterialdrawer.MSMaterialDrawerBuilder();
 //BA.debugLineNum = 96;BA.debugLine="Public MD As MSMaterialDrawer";
mostCurrent._md = new com.maximussoft.msmaterialdrawer.MSMaterialDrawer();
 //BA.debugLineNum = 97;BA.debugLine="Dim user_info As List";
mostCurrent._user_info = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 99;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 100;BA.debugLine="Private btn_sabad As Button";
mostCurrent._btn_sabad = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 101;BA.debugLine="Private btn_share As Button";
mostCurrent._btn_share = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 102;BA.debugLine="Private btn_search As Button";
mostCurrent._btn_search = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 104;BA.debugLine="Private panel_v1 As Panel";
mostCurrent._panel_v1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 105;BA.debugLine="Private LayoutView1,LayoutView2 As Hitex_LayoutVi";
mostCurrent._layoutview1 = new wir.hitex.recycler.Hitex_LayoutView();
mostCurrent._layoutview2 = new wir.hitex.recycler.Hitex_LayoutView();
 //BA.debugLineNum = 106;BA.debugLine="Private divider_LayoutView1 As Hitex_DividerItemD";
mostCurrent._divider_layoutview1 = new wir.hitex.recycler.Hitex_DividerItemDecoration();
 //BA.debugLineNum = 107;BA.debugLine="Private cardview1 As Hitex_RecyclerCardView";
mostCurrent._cardview1 = new wir.hitex.recycler.Hitex_RecyclerCardView();
 //BA.debugLineNum = 108;BA.debugLine="Private panel_base As Panel";
mostCurrent._panel_base = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 109;BA.debugLine="Private progress_porfroosh As ProgressBar";
mostCurrent._progress_porfroosh = new anywheresoftware.b4a.objects.ProgressBarWrapper();
 //BA.debugLineNum = 110;BA.debugLine="Private panel_baner_1,panel_baner_2 As Panel";
mostCurrent._panel_baner_1 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panel_baner_2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 111;BA.debugLine="Private panel_obj0,panel_obj1 As Panel";
mostCurrent._panel_obj0 = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._panel_obj1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 113;BA.debugLine="Type brands(pic As String)";
;
 //BA.debugLineNum = 114;BA.debugLine="Dim list_brand As List";
mostCurrent._list_brand = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 115;BA.debugLine="Private panel_brand As Panel";
mostCurrent._panel_brand = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 118;BA.debugLine="Dim point_dialog As MatrialDialog_hpk";
mostCurrent._point_dialog = new com.github.javiersantos.materialstyleddialogs.StyledDialog();
 //BA.debugLineNum = 120;BA.debugLine="Private splash As Panel";
mostCurrent._splash = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 121;BA.debugLine="Dim timer_splash As Timer";
mostCurrent._timer_splash = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 122;BA.debugLine="Dim AriaLib1 As AriaLib";
mostCurrent._arialib1 = new anywheresoftware.b4a.ariagplib.ARIAlib();
 //BA.debugLineNum = 123;BA.debugLine="Private btn_list_porfroush As Label";
mostCurrent._btn_list_porfroush = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static String  _img_cat1_click() throws Exception{
 //BA.debugLineNum = 1474;BA.debugLine="Sub img_cat1_Click";
 //BA.debugLineNum = 1475;BA.debugLine="show_list_kala.cat_name=\"پمادها\"	'نام گروه";
mostCurrent._show_list_kala._cat_name = "پمادها";
 //BA.debugLineNum = 1476;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1477;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1478;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1479;BA.debugLine="show_list_kala.code_cat=4'کد گروه";
mostCurrent._show_list_kala._code_cat = (int) (4);
 //BA.debugLineNum = 1480;BA.debugLine="show_list_kala.code_sub_cat=0'کد زیر گروه";
mostCurrent._show_list_kala._code_sub_cat = (int) (0);
 //BA.debugLineNum = 1481;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1482;BA.debugLine="End Sub";
return "";
}
public static String  _img_cat2_click() throws Exception{
 //BA.debugLineNum = 1483;BA.debugLine="Sub img_cat2_Click";
 //BA.debugLineNum = 1484;BA.debugLine="show_list_kala.cat_name=\"روغن ها\"	'نام گروه";
mostCurrent._show_list_kala._cat_name = "روغن ها";
 //BA.debugLineNum = 1485;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1486;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1487;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1488;BA.debugLine="show_list_kala.code_cat=5'کد گروه";
mostCurrent._show_list_kala._code_cat = (int) (5);
 //BA.debugLineNum = 1489;BA.debugLine="show_list_kala.code_sub_cat=0'کد زیر گروه";
mostCurrent._show_list_kala._code_sub_cat = (int) (0);
 //BA.debugLineNum = 1490;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1491;BA.debugLine="End Sub";
return "";
}
public static String  _img_cat3_click() throws Exception{
 //BA.debugLineNum = 1493;BA.debugLine="Sub img_cat3_Click";
 //BA.debugLineNum = 1494;BA.debugLine="show_list_kala.cat_name=\" عرقیات\"	'نام گروه";
mostCurrent._show_list_kala._cat_name = " عرقیات";
 //BA.debugLineNum = 1495;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1496;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1497;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1498;BA.debugLine="show_list_kala.code_cat=1'کد گروه";
mostCurrent._show_list_kala._code_cat = (int) (1);
 //BA.debugLineNum = 1499;BA.debugLine="show_list_kala.code_sub_cat=0'کد زیر گروه";
mostCurrent._show_list_kala._code_sub_cat = (int) (0);
 //BA.debugLineNum = 1500;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1501;BA.debugLine="End Sub";
return "";
}
public static String  _img_cat4_click() throws Exception{
 //BA.debugLineNum = 1502;BA.debugLine="Sub img_cat4_Click";
 //BA.debugLineNum = 1503;BA.debugLine="show_list_kala.cat_name=\" آموزش ها\"	'نام گروه";
mostCurrent._show_list_kala._cat_name = " آموزش ها";
 //BA.debugLineNum = 1504;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1505;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1506;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1507;BA.debugLine="show_list_kala.code_cat=6'کد گروه";
mostCurrent._show_list_kala._code_cat = (int) (6);
 //BA.debugLineNum = 1508;BA.debugLine="show_list_kala.code_sub_cat=0'کد زیر گروه";
mostCurrent._show_list_kala._code_sub_cat = (int) (0);
 //BA.debugLineNum = 1509;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1510;BA.debugLine="End Sub";
return "";
}
public static String  _img_cat5_click() throws Exception{
 //BA.debugLineNum = 1511;BA.debugLine="Sub img_cat5_Click";
 //BA.debugLineNum = 1512;BA.debugLine="show_list_kala.cat_name=\"دمنوش\"	'نام گروه";
mostCurrent._show_list_kala._cat_name = "دمنوش";
 //BA.debugLineNum = 1513;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1514;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1515;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1516;BA.debugLine="show_list_kala.code_cat=3'کد گروه";
mostCurrent._show_list_kala._code_cat = (int) (3);
 //BA.debugLineNum = 1517;BA.debugLine="show_list_kala.code_sub_cat=0'کد زیر گروه";
mostCurrent._show_list_kala._code_sub_cat = (int) (0);
 //BA.debugLineNum = 1518;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1519;BA.debugLine="End Sub";
return "";
}
public static String  _img_cat6_click() throws Exception{
 //BA.debugLineNum = 1520;BA.debugLine="Sub img_cat6_Click";
 //BA.debugLineNum = 1521;BA.debugLine="show_list_kala.cat_name=\"داروها\"	'نام گروه";
mostCurrent._show_list_kala._cat_name = "داروها";
 //BA.debugLineNum = 1522;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1523;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1524;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1525;BA.debugLine="show_list_kala.code_cat=2'کد گروه";
mostCurrent._show_list_kala._code_cat = (int) (2);
 //BA.debugLineNum = 1526;BA.debugLine="show_list_kala.code_sub_cat=0'کد زیر گروه";
mostCurrent._show_list_kala._code_sub_cat = (int) (0);
 //BA.debugLineNum = 1527;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 //BA.debugLineNum = 1528;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_awsome() throws Exception{
 //BA.debugLineNum = 1069;BA.debugLine="Sub initialize_awsome";
 //BA.debugLineNum = 1070;BA.debugLine="btn_menu.Typeface=Starter.Font_Awsome";
mostCurrent._btn_menu.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_awsome.getObject()));
 //BA.debugLineNum = 1071;BA.debugLine="btn_sabad.Typeface=Starter.Font_Awsome";
mostCurrent._btn_sabad.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_awsome.getObject()));
 //BA.debugLineNum = 1072;BA.debugLine="btn_share.Typeface=Starter.Font_Awsome";
mostCurrent._btn_share.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_awsome.getObject()));
 //BA.debugLineNum = 1073;BA.debugLine="btn_search.Typeface=Starter.Font_Awsome";
mostCurrent._btn_search.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_awsome.getObject()));
 //BA.debugLineNum = 1074;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_layout() throws Exception{
 //BA.debugLineNum = 383;BA.debugLine="Sub	initialize_layout";
 //BA.debugLineNum = 384;BA.debugLine="ScrollView1.Panel.LoadLayout(\"page_main\")";
mostCurrent._scrollview1.getPanel().LoadLayout("page_main",mostCurrent.activityBA);
 //BA.debugLineNum = 386;BA.debugLine="ScrollView1.Panel.Height=panel_obj1.Top + panel_o";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._panel_obj1.getTop()+mostCurrent._panel_obj1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 //BA.debugLineNum = 387;BA.debugLine="function.hide_scrollbar_horizontal(porfroush_pane";
mostCurrent._function._hide_scrollbar_horizontal(mostCurrent.activityBA,mostCurrent._porfroush_panel);
 //BA.debugLineNum = 388;BA.debugLine="function.hide_scrollbar_horizontal(viesue_panel)";
mostCurrent._function._hide_scrollbar_horizontal(mostCurrent.activityBA,mostCurrent._viesue_panel);
 //BA.debugLineNum = 389;BA.debugLine="timer1.Initialize(\"timer1\",3500)";
mostCurrent._timer1.Initialize(processBA,"timer1",(long) (3500));
 //BA.debugLineNum = 391;BA.debugLine="checked_update=True";
_checked_update = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 393;BA.debugLine="font_label";
_font_label();
 //BA.debugLineNum = 398;BA.debugLine="get_slideshow";
_get_slideshow();
 //BA.debugLineNum = 399;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_menu() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnlhdr = null;
com.maximussoft.msmaterialdrawer.MSProfile _msp1 = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _pdb1 = null;
String _id_shared = "";
com.maximussoft.msmaterialdrawer.MSProfile[] _profiles = null;
com.maximussoft.msmaterialdrawer.MSAccountHeaderBuilder _msa = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _headerbackground = null;
Object _materialheaderresult = null;
int _color_object_menu = 0;
com.maximussoft.msmaterialdrawer.IconicDrawable _p1 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p2 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p3 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p4 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p5 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p6 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p7 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p8 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p9 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p10 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p11 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _p12 = null;
com.maximussoft.msmaterialdrawer.IconicDrawable _s2 = null;
 //BA.debugLineNum = 1265;BA.debugLine="Public Sub Initialize_menu";
 //BA.debugLineNum = 1266;BA.debugLine="If MD.IsInitialized=False Then";
if (mostCurrent._md.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1267;BA.debugLine="LogColor(\"initialize MD\",Colors.Red)";
anywheresoftware.b4a.keywords.Common.LogColor("initialize MD",anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 1268;BA.debugLine="Dim pnlHdr As Panel";
_pnlhdr = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 1269;BA.debugLine="pnlHdr.Initialize(\"\")";
_pnlhdr.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1270;BA.debugLine="pnlHdr.Color = Colors.DarkGray";
_pnlhdr.setColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 1271;BA.debugLine="pnlHdr.Tag = \"Header\"";
_pnlhdr.setTag((Object)("Header"));
 //BA.debugLineNum = 1273;BA.debugLine="Dim MSP1 As MSProfile";
_msp1 = new com.maximussoft.msmaterialdrawer.MSProfile();
 //BA.debugLineNum = 1274;BA.debugLine="Dim PDB1 As BitmapDrawable : PDB1.Initialize(Loa";
_pdb1 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 1274;BA.debugLine="Dim PDB1 As BitmapDrawable : PDB1.Initialize(Loa";
_pdb1.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"profile3.jpg").getObject()));
 //BA.debugLineNum = 1276;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=True The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1278;BA.debugLine="user_info.Initialize";
mostCurrent._user_info.Initialize();
 //BA.debugLineNum = 1279;BA.debugLine="user_info=File.ReadList(myLibrary.rute,name_dat";
mostCurrent._user_info = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat);
 //BA.debugLineNum = 1281;BA.debugLine="Dim id_shared As String=\"اشتراک \"  & (user_info";
_id_shared = "اشتراک "+BA.NumberToString(((double)(BA.ObjectToNumber(mostCurrent._user_info.Get((int) (3))))+100));
 //BA.debugLineNum = 1282;BA.debugLine="MSP1.withName(user_info.Get(1) & \" \" & user_inf";
_msp1.withName(BA.ObjectToString(mostCurrent._user_info.Get((int) (1)))+" "+BA.ObjectToString(mostCurrent._user_info.Get((int) (2)))).withEmail(_id_shared).withIcon((android.graphics.drawable.Drawable)(_pdb1.getObject()));
 }else {
 //BA.debugLineNum = 1284;BA.debugLine="MSP1.withName(\"کاربر مهمان\").withEmail(\"\").with";
_msp1.withName("کاربر مهمان").withEmail("").withIcon((android.graphics.drawable.Drawable)(_pdb1.getObject()));
 };
 //BA.debugLineNum = 1292;BA.debugLine="Dim Profiles(1) As MSProfile";
_profiles = new com.maximussoft.msmaterialdrawer.MSProfile[(int) (1)];
{
int d0 = _profiles.length;
for (int i0 = 0;i0 < d0;i0++) {
_profiles[i0] = new com.maximussoft.msmaterialdrawer.MSProfile();
}
}
;
 //BA.debugLineNum = 1293;BA.debugLine="Profiles(0) = MSP1";
_profiles[(int) (0)] = _msp1;
 //BA.debugLineNum = 1297;BA.debugLine="Dim msa As MSAccountHeaderBuilder";
_msa = new com.maximussoft.msmaterialdrawer.MSAccountHeaderBuilder();
 //BA.debugLineNum = 1298;BA.debugLine="Dim HeaderBackground As BitmapDrawable";
_headerbackground = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 1301;BA.debugLine="HeaderBackground.Initialize(LoadBitmap(File.DirA";
_headerbackground.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"header.jpg").getObject()));
 //BA.debugLineNum = 1303;BA.debugLine="msa.Initialize(\"MSA\")";
_msa.Initialize(mostCurrent.activityBA,"MSA");
 //BA.debugLineNum = 1304;BA.debugLine="msa.addProfiles(Profiles)";
_msa.addProfiles((com.mikepenz.materialdrawer.model.interfaces.IProfile[])(_profiles));
 //BA.debugLineNum = 1305;BA.debugLine="msa.withHeaderBackground(HeaderBackground)";
_msa.withHeaderBackground((android.graphics.drawable.Drawable)(_headerbackground.getObject()));
 //BA.debugLineNum = 1306;BA.debugLine="Dim MaterialHeaderResult As Object = msa.build";
_materialheaderresult = (Object)(_msa.build());
 //BA.debugLineNum = 1308;BA.debugLine="Dim color_object_menu As Int=Starter.color_base";
_color_object_menu = mostCurrent._starter._color_base;
 //BA.debugLineNum = 1309;BA.debugLine="Dim p1 As MSIconicDrawable : p1.Initialize(\"gmd_";
_p1 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1309;BA.debugLine="Dim p1 As MSIconicDrawable : p1.Initialize(\"gmd_";
_p1.Initialize(processBA,"gmd_home");
 //BA.debugLineNum = 1309;BA.debugLine="Dim p1 As MSIconicDrawable : p1.Initialize(\"gmd_";
_p1.setColor(_color_object_menu);
 //BA.debugLineNum = 1310;BA.debugLine="Dim p2 As MSIconicDrawable : p2.Initialize(\"gmd_";
_p2 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1310;BA.debugLine="Dim p2 As MSIconicDrawable : p2.Initialize(\"gmd_";
_p2.Initialize(processBA,"gmd_drafts");
 //BA.debugLineNum = 1310;BA.debugLine="Dim p2 As MSIconicDrawable : p2.Initialize(\"gmd_";
_p2.setColor(_color_object_menu);
 //BA.debugLineNum = 1311;BA.debugLine="Dim p3 As MSIconicDrawable : p3.Initialize(\"faw_";
_p3 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1311;BA.debugLine="Dim p3 As MSIconicDrawable : p3.Initialize(\"faw_";
_p3.Initialize(processBA,"faw_fire");
 //BA.debugLineNum = 1311;BA.debugLine="Dim p3 As MSIconicDrawable : p3.Initialize(\"faw_";
_p3.setColor(_color_object_menu);
 //BA.debugLineNum = 1312;BA.debugLine="Dim p4 As MSIconicDrawable : p4.Initialize(\"faw_";
_p4 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1312;BA.debugLine="Dim p4 As MSIconicDrawable : p4.Initialize(\"faw_";
_p4.Initialize(processBA,"faw_dot_circle_o");
 //BA.debugLineNum = 1312;BA.debugLine="Dim p4 As MSIconicDrawable : p4.Initialize(\"faw_";
_p4.setColor(_color_object_menu);
 //BA.debugLineNum = 1313;BA.debugLine="Dim p5 As MSIconicDrawable : p5.Initialize(\"faw_";
_p5 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1313;BA.debugLine="Dim p5 As MSIconicDrawable : p5.Initialize(\"faw_";
_p5.Initialize(processBA,"faw_clock_o");
 //BA.debugLineNum = 1313;BA.debugLine="Dim p5 As MSIconicDrawable : p5.Initialize(\"faw_";
_p5.setColor(_color_object_menu);
 //BA.debugLineNum = 1314;BA.debugLine="Dim p6 As MSIconicDrawable : p6.Initialize(\"faw_";
_p6 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1314;BA.debugLine="Dim p6 As MSIconicDrawable : p6.Initialize(\"faw_";
_p6.Initialize(processBA,"faw_phone");
 //BA.debugLineNum = 1314;BA.debugLine="Dim p6 As MSIconicDrawable : p6.Initialize(\"faw_";
_p6.setColor(_color_object_menu);
 //BA.debugLineNum = 1315;BA.debugLine="Dim p7 As MSIconicDrawable : p7.Initialize(\"faw_";
_p7 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1315;BA.debugLine="Dim p7 As MSIconicDrawable : p7.Initialize(\"faw_";
_p7.Initialize(processBA,"faw_anchor");
 //BA.debugLineNum = 1315;BA.debugLine="Dim p7 As MSIconicDrawable : p7.Initialize(\"faw_";
_p7.setColor(_color_object_menu);
 //BA.debugLineNum = 1316;BA.debugLine="Dim p8 As MSIconicDrawable : p8.Initialize(\"faw_";
_p8 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1316;BA.debugLine="Dim p8 As MSIconicDrawable : p8.Initialize(\"faw_";
_p8.Initialize(processBA,"faw_barcode");
 //BA.debugLineNum = 1316;BA.debugLine="Dim p8 As MSIconicDrawable : p8.Initialize(\"faw_";
_p8.setColor(_color_object_menu);
 //BA.debugLineNum = 1317;BA.debugLine="Dim p9 As MSIconicDrawable : p9.Initialize(\"faw_";
_p9 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1317;BA.debugLine="Dim p9 As MSIconicDrawable : p9.Initialize(\"faw_";
_p9.Initialize(processBA,"faw_hand_o_left");
 //BA.debugLineNum = 1317;BA.debugLine="Dim p9 As MSIconicDrawable : p9.Initialize(\"faw_";
_p9.setColor(_color_object_menu);
 //BA.debugLineNum = 1318;BA.debugLine="Dim p10 As MSIconicDrawable : p10.Initialize(\"fa";
_p10 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1318;BA.debugLine="Dim p10 As MSIconicDrawable : p10.Initialize(\"fa";
_p10.Initialize(processBA,"faw_close");
 //BA.debugLineNum = 1318;BA.debugLine="Dim p10 As MSIconicDrawable : p10.Initialize(\"fa";
_p10.setColor(_color_object_menu);
 //BA.debugLineNum = 1319;BA.debugLine="Dim p11 As MSIconicDrawable : p11.Initialize(\"fa";
_p11 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1319;BA.debugLine="Dim p11 As MSIconicDrawable : p11.Initialize(\"fa";
_p11.Initialize(processBA,"faw_group");
 //BA.debugLineNum = 1319;BA.debugLine="Dim p11 As MSIconicDrawable : p11.Initialize(\"fa";
_p11.setColor(_color_object_menu);
 //BA.debugLineNum = 1320;BA.debugLine="Dim p12 As MSIconicDrawable : p12.Initialize(\"fa";
_p12 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1320;BA.debugLine="Dim p12 As MSIconicDrawable : p12.Initialize(\"fa";
_p12.Initialize(processBA,"faw_flag");
 //BA.debugLineNum = 1320;BA.debugLine="Dim p12 As MSIconicDrawable : p12.Initialize(\"fa";
_p12.setColor(_color_object_menu);
 //BA.debugLineNum = 1323;BA.debugLine="Dim s2 As MSIconicDrawable : s2.Initialize(\"gmd_";
_s2 = new com.maximussoft.msmaterialdrawer.IconicDrawable();
 //BA.debugLineNum = 1323;BA.debugLine="Dim s2 As MSIconicDrawable : s2.Initialize(\"gmd_";
_s2.Initialize(processBA,"gmd_help");
 //BA.debugLineNum = 1323;BA.debugLine="Dim s2 As MSIconicDrawable : s2.Initialize(\"gmd_";
_s2.setColor(_color_object_menu);
 //BA.debugLineNum = 1325;BA.debugLine="MDB.Initialize(\"MD\")";
mostCurrent._mdb.Initialize(mostCurrent.activityBA,"MD");
 //BA.debugLineNum = 1326;BA.debugLine="MDB.withAccountHeader(MaterialHeaderResult)";
mostCurrent._mdb.withAccountHeader((com.mikepenz.materialdrawer.accountswitcher.AccountHeader.Result)(_materialheaderresult));
 //BA.debugLineNum = 1331;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=False Th";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1332;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"ورود یا ثبت نام\" ,";
mostCurrent._mdb.AddPrimaryDrawerItem("ورود یا ثبت نام",_p9.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (1),"");
 }else {
 //BA.debugLineNum = 1335;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"پروفایل\" , p11.Draw";
mostCurrent._mdb.AddPrimaryDrawerItem("پروفایل",_p11.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (2),"");
 //BA.debugLineNum = 1336;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"تاریخچه خرید\"  , p5";
mostCurrent._mdb.AddPrimaryDrawerItem("تاریخچه خرید",_p5.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (3),"");
 //BA.debugLineNum = 1337;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"خروج از حساب کاربری";
mostCurrent._mdb.AddPrimaryDrawerItem("خروج از حساب کاربری",_p10.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (4),"");
 };
 //BA.debugLineNum = 1339;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"شرکت های تولید کننده";
mostCurrent._mdb.AddPrimaryDrawerItem("شرکت های تولید کننده",_p3.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (5),"");
 //BA.debugLineNum = 1340;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"پرفروش ترین ها\" , p1";
mostCurrent._mdb.AddPrimaryDrawerItem("پرفروش ترین ها",_p12.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (6),"");
 //BA.debugLineNum = 1341;BA.debugLine="MDB.AddPrimaryDrawerItem  (\"جدیدترین ها\" , p4.Dr";
mostCurrent._mdb.AddPrimaryDrawerItem("جدیدترین ها",_p4.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (7),"");
 //BA.debugLineNum = 1342;BA.debugLine="MDB.AddSectionDrawerItem  (Starter.root_site  ,T";
mostCurrent._mdb.AddSectionDrawerItem(mostCurrent._starter._root_site,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1343;BA.debugLine="MDB.AddSecondaryDrawerItem(\"درباره سازنده\" ,p7.D";
mostCurrent._mdb.AddSecondaryDrawerItem("درباره سازنده",_p7.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (8));
 //BA.debugLineNum = 1344;BA.debugLine="MDB.AddSecondaryDrawerItem(\"ارتباط با فروشنده در";
mostCurrent._mdb.AddSecondaryDrawerItem("ارتباط با فروشنده در تلگرام",_p6.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.True,(int) (9));
 //BA.debugLineNum = 1345;BA.debugLine="MDB.AddSecondaryDrawerItem(\"نسخه \" & Application";
mostCurrent._mdb.AddSecondaryDrawerItem("نسخه "+anywheresoftware.b4a.keywords.Common.Application.getVersionName(),_p8.getDrawable(),(android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null),"",anywheresoftware.b4a.keywords.Common.False,(int) (0));
 //BA.debugLineNum = 1346;BA.debugLine="MDB.withDrawerGravity(Gravity.RIGHT)";
mostCurrent._mdb.withDrawerGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 1348;BA.debugLine="MD = MDB.Build";
mostCurrent._md = mostCurrent._mdb.Build();
 };
 //BA.debugLineNum = 1351;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_slideshow(anywheresoftware.b4a.objects.collections.List _list_url) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper[] _p_img = null;
int _i = 0;
 //BA.debugLineNum = 1204;BA.debugLine="Sub initialize_slideshow(list_url As List)";
 //BA.debugLineNum = 1205;BA.debugLine="Dim p_img(list_url.Size) As ImageView'///////////";
_p_img = new anywheresoftware.b4a.objects.ImageViewWrapper[_list_url.getSize()];
{
int d0 = _p_img.length;
for (int i0 = 0;i0 < d0;i0++) {
_p_img[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 1206;BA.debugLine="pa.Initialize";
mostCurrent._pa.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 1207;BA.debugLine="For i = 0 To list_url.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (_list_url.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 1208;BA.debugLine="p_img(i).Initialize(\"\")";
_p_img[_i].Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1209;BA.debugLine="Target1(list_url.Get(i),p_img(i))";
_target1(BA.ObjectToString(_list_url.Get(_i)),(Object)(_p_img[_i].getObject()));
 //BA.debugLineNum = 1210;BA.debugLine="pa.AddPage(p_img(i)) 'map_info_baner.GetkeyAt(i)";
mostCurrent._pa.AddPage((android.view.View)(_p_img[_i].getObject()));
 }
};
 //BA.debugLineNum = 1212;BA.debugLine="pb.Initialize(pa,\"pb1\")";
mostCurrent._pb.Initialize(mostCurrent.activityBA,mostCurrent._pa,"pb1");
 //BA.debugLineNum = 1214;BA.debugLine="pb.Transition = pb.ZOOM_OUT_SIDE";
mostCurrent._pb.setTransition(mostCurrent._pb.ZOOM_OUT_SIDE);
 //BA.debugLineNum = 1215;BA.debugLine="Panel_slider.AddView(pb,0,0,100%x,Panel_slider.He";
mostCurrent._panel_slider.AddView((android.view.View)(mostCurrent._pb.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._panel_slider.getHeight());
 //BA.debugLineNum = 1217;BA.debugLine="timer_slide.Initialize(\"timer_slide\",Starter.time";
mostCurrent._timer_slide.Initialize(processBA,"timer_slide",(long) (mostCurrent._starter._timer_slideshow));
 //BA.debugLineNum = 1218;BA.debugLine="timer_slide.Enabled=True";
mostCurrent._timer_slide.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1219;BA.debugLine="End Sub";
return "";
}
public static boolean  _is_login() throws Exception{
 //BA.debugLineNum = 1434;BA.debugLine="Public Sub is_login() As Boolean";
 //BA.debugLineNum = 1435;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=True Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1436;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 1438;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 1440;BA.debugLine="End Sub";
return false;
}
public static String  _layoutview1_baners2_itemclick(anywheresoftware.b4a.objects.PanelWrapper _clickeditem,int _position) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
int _val_id = 0;
String _val_info = "";
anywheresoftware.b4a.object.XmlLayoutBuilder _xml = null;
 //BA.debugLineNum = 317;BA.debugLine="Sub LayoutView1_baners2_ItemClick (ClickedItem As";
 //BA.debugLineNum = 318;BA.debugLine="Log(ClickedItem.Tag)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_clickeditem.getTag()));
 //BA.debugLineNum = 319;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 320;BA.debugLine="parser.Initialize(	ClickedItem.Tag	)";
_parser.Initialize(BA.ObjectToString(_clickeditem.getTag()));
 //BA.debugLineNum = 321;BA.debugLine="Dim root As Map = parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 322;BA.debugLine="Dim val_id As Int=root.Get(\"value\")";
_val_id = (int)(BA.ObjectToNumber(_root.Get((Object)("value"))));
 //BA.debugLineNum = 323;BA.debugLine="Dim val_info As String=root.Get(\"info\")";
_val_info = BA.ObjectToString(_root.Get((Object)("info")));
 //BA.debugLineNum = 324;BA.debugLine="If root.Get(\"type\")=\"category\" Then";
if ((_root.Get((Object)("type"))).equals((Object)("category"))) { 
 //BA.debugLineNum = 325;BA.debugLine="show_list_kala.cat_name=val_info'نام گروه";
mostCurrent._show_list_kala._cat_name = _val_info;
 //BA.debugLineNum = 326;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 327;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 328;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 329;BA.debugLine="show_list_kala.code_cat=val_id'کد گروه";
mostCurrent._show_list_kala._code_cat = _val_id;
 //BA.debugLineNum = 331;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 };
 //BA.debugLineNum = 333;BA.debugLine="If root.Get(\"type\")=\"product\" Then";
if ((_root.Get((Object)("type"))).equals((Object)("product"))) { 
 //BA.debugLineNum = 334;BA.debugLine="info_kala.code_kala=val_id";
mostCurrent._info_kala._code_kala = _val_id;
 //BA.debugLineNum = 335;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 };
 //BA.debugLineNum = 338;BA.debugLine="If root.Get(\"type\")=\"project\" Then";
if ((_root.Get((Object)("type"))).equals((Object)("project"))) { 
 //BA.debugLineNum = 339;BA.debugLine="If val_id=0 Then";
if (_val_id==0) { 
 //BA.debugLineNum = 340;BA.debugLine="StartActivity(project_help)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._project_help.getObject()));
 };
 };
 //BA.debugLineNum = 344;BA.debugLine="If root.Get(\"type\")=\"point\" Then";
if ((_root.Get((Object)("type"))).equals((Object)("point"))) { 
 //BA.debugLineNum = 345;BA.debugLine="If val_id=0 Then";
if (_val_id==0) { 
 //BA.debugLineNum = 346;BA.debugLine="point_dialog.Initialize(\"dialog\")";
mostCurrent._point_dialog.Initialize(mostCurrent.activityBA,"dialog");
 //BA.debugLineNum = 347;BA.debugLine="point_dialog.SetTitle(\" اطلاعیه\")";
mostCurrent._point_dialog.SetTitle(BA.ObjectToCharSequence(" اطلاعیه"));
 //BA.debugLineNum = 348;BA.debugLine="point_dialog.SetDescription(val_info)";
mostCurrent._point_dialog.SetDescription(BA.ObjectToCharSequence(_val_info));
 //BA.debugLineNum = 349;BA.debugLine="point_dialog.SetHeaderColorInt(Starter.color_ba";
mostCurrent._point_dialog.SetHeaderColorInt(mostCurrent._starter._color_base);
 //BA.debugLineNum = 350;BA.debugLine="Dim xml As XmlLayoutBuilder";
_xml = new anywheresoftware.b4a.object.XmlLayoutBuilder();
 //BA.debugLineNum = 351;BA.debugLine="point_dialog.SetIcon(	xml.GetDrawable(\"icon1\")";
mostCurrent._point_dialog.SetIcon(_xml.GetDrawable("icon1"));
 //BA.debugLineNum = 352;BA.debugLine="point_dialog.SetCancelable(True)";
mostCurrent._point_dialog.SetCancelable(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 355;BA.debugLine="point_dialog.SetNeutralText(\"بستن\")";
mostCurrent._point_dialog.SetNeutralText(BA.ObjectToCharSequence("بستن"));
 //BA.debugLineNum = 356;BA.debugLine="point_dialog.SetStyle(point_dialog.EADER_WITH_I";
mostCurrent._point_dialog.SetStyle(mostCurrent._point_dialog.EADER_WITH_ICON);
 //BA.debugLineNum = 357;BA.debugLine="point_dialog.WithDialogAnimation2(True,point_di";
mostCurrent._point_dialog.WithDialogAnimation2(anywheresoftware.b4a.keywords.Common.True,mostCurrent._point_dialog.Duration_SLOW);
 //BA.debugLineNum = 358;BA.debugLine="point_dialog.build";
mostCurrent._point_dialog.build();
 //BA.debugLineNum = 359;BA.debugLine="point_dialog.show";
mostCurrent._point_dialog.show();
 };
 };
 //BA.debugLineNum = 362;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview1_baners2_onbindviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent,int _position) throws Exception{
hpksoftware.setak.main._baner_list_1 _baner_list1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl1 = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 288;BA.debugLine="Sub LayoutView1_baners2_onBindViewHolder (Parent A";
 //BA.debugLineNum = 289;BA.debugLine="Dim baner_list1=list1_baner.Get(Position) As bane";
_baner_list1 = (hpksoftware.setak.main._baner_list_1)(mostCurrent._list1_baner.Get(_position));
 //BA.debugLineNum = 290;BA.debugLine="Dim pnl1 As Panel=Parent";
_pnl1 = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl1 = _parent;
 //BA.debugLineNum = 291;BA.debugLine="Dim img1  As ImageView=pnl1.GetView(0)";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
_img1.setObject((android.widget.ImageView)(_pnl1.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 292;BA.debugLine="Parent.Tag=baner_list1.desc";
_parent.setTag((Object)(_baner_list1.desc));
 //BA.debugLineNum = 293;BA.debugLine="piccaso.Load(\"http\",baner_list1.src_pic).Resize(i";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_baner_list1.src_pic).Resize(_img1.getWidth(),_img1.getHeight()).CenterCrop().SkipMemoryCache().Into(_img1);
 //BA.debugLineNum = 294;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview1_baners2_oncreateviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 279;BA.debugLine="Sub LayoutView1_baners2_onCreateViewHolder (Parent";
 //BA.debugLineNum = 280;BA.debugLine="Log(\"LayoutView2_onCreateViewHolder\")";
anywheresoftware.b4a.keywords.Common.Log("LayoutView2_onCreateViewHolder");
 //BA.debugLineNum = 281;BA.debugLine="Parent.Height=(panel_baner_2.Height / list1_baner";
_parent.setHeight((int) ((mostCurrent._panel_baner_2.getHeight()/(double)mostCurrent._list1_baner.getSize())-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 282;BA.debugLine="Parent.Width=100%x";
_parent.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 283;BA.debugLine="Dim img1  As ImageView";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 284;BA.debugLine="img1.Initialize(\"\")";
_img1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 285;BA.debugLine="Parent.AddView(img1,2%x,0,	Parent.Width-(4%x)	,Pa";
_parent.AddView((android.view.View)(_img1.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA),(int) (0),(int) (_parent.getWidth()-(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (4),mostCurrent.activityBA))),_parent.getHeight());
 //BA.debugLineNum = 286;BA.debugLine="apc.SetElevation(img1,30dip)";
mostCurrent._apc.SetElevation((android.view.View)(_img1.getObject()),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))));
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview1_onbindviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent,int _position) throws Exception{
hpksoftware.setak.main._baner_list_1 _baner_list1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl1 = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 220;BA.debugLine="Sub LayoutView1_onBindViewHolder (Parent As Panel,";
 //BA.debugLineNum = 221;BA.debugLine="Dim baner_list1=list1_baner.Get(Position) As bane";
_baner_list1 = (hpksoftware.setak.main._baner_list_1)(mostCurrent._list1_baner.Get(_position));
 //BA.debugLineNum = 222;BA.debugLine="Dim pnl1 As Panel=Parent";
_pnl1 = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl1 = _parent;
 //BA.debugLineNum = 223;BA.debugLine="Dim img1  As ImageView=pnl1.GetView(0)";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
_img1.setObject((android.widget.ImageView)(_pnl1.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 224;BA.debugLine="piccaso.Load(\"http\",baner_list1.src_pic).Resize(i";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_baner_list1.src_pic).Resize(_img1.getWidth(),_img1.getHeight()).CenterCrop().SkipMemoryCache().Into(_img1);
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview1_oncreateviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 209;BA.debugLine="Sub LayoutView1_onCreateViewHolder (Parent As Pane";
 //BA.debugLineNum = 210;BA.debugLine="Log(\"LayoutView1_onCreateViewHolder\")";
anywheresoftware.b4a.keywords.Common.Log("LayoutView1_onCreateViewHolder");
 //BA.debugLineNum = 211;BA.debugLine="Parent.Height=(panel_baner_1.Height / list1_baner";
_parent.setHeight((int) ((mostCurrent._panel_baner_1.getHeight()/(double)mostCurrent._list1_baner.getSize())));
 //BA.debugLineNum = 212;BA.debugLine="Parent.Width=100%x";
_parent.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 215;BA.debugLine="Dim img1  As ImageView";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 216;BA.debugLine="img1.Initialize(\"\")";
_img1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 217;BA.debugLine="Parent.AddView(img1,2%x,0,Parent.Width-4%x,Parent";
_parent.AddView((android.view.View)(_img1.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA),(int) (0),(int) (_parent.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (4),mostCurrent.activityBA)),_parent.getHeight());
 //BA.debugLineNum = 218;BA.debugLine="apc.SetElevation(img1,30dip)";
mostCurrent._apc.SetElevation((android.view.View)(_img1.getObject()),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))));
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview2_baners2_onbindviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent,int _position) throws Exception{
hpksoftware.setak.main._baner_list_1 _baner_list1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl2 = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 307;BA.debugLine="Sub LayoutView2_baners2_onBindViewHolder (Parent A";
 //BA.debugLineNum = 308;BA.debugLine="Dim baner_list1=list1_baner.Get(Position) As bane";
_baner_list1 = (hpksoftware.setak.main._baner_list_1)(mostCurrent._list1_baner.Get(_position));
 //BA.debugLineNum = 309;BA.debugLine="Log(\"GridView: \" & baner_list1.src_pic)";
anywheresoftware.b4a.keywords.Common.Log("GridView: "+_baner_list1.src_pic);
 //BA.debugLineNum = 310;BA.debugLine="Dim pnl1 As Panel=Parent";
_pnl1 = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl1 = _parent;
 //BA.debugLineNum = 311;BA.debugLine="Dim pnl2 As Panel=pnl1.GetView(0)";
_pnl2 = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl2.setObject((android.view.ViewGroup)(_pnl1.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 312;BA.debugLine="Dim img1  As ImageView=pnl2.GetView(0)";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
_img1.setObject((android.widget.ImageView)(_pnl2.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 314;BA.debugLine="piccaso.Load(\"http\",baner_list1.src_pic).Resize(i";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_baner_list1.src_pic).Resize(_img1.getWidth(),_img1.getHeight()).SkipMemoryCache().Into(_img1);
 //BA.debugLineNum = 315;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview2_baners2_oncreateviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 296;BA.debugLine="Sub LayoutView2_baners2_onCreateViewHolder (Parent";
 //BA.debugLineNum = 297;BA.debugLine="Parent.Height=(panel_baner_2.Height / Round(list1";
_parent.setHeight((int) ((mostCurrent._panel_baner_2.getHeight()/(double)anywheresoftware.b4a.keywords.Common.Round(mostCurrent._list1_baner.getSize()/(double)2))));
 //BA.debugLineNum = 298;BA.debugLine="Parent.Width=50%x";
_parent.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA));
 //BA.debugLineNum = 299;BA.debugLine="Dim pnl  As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 300;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 301;BA.debugLine="Parent.AddView(pnl,0,0,Parent.Width,Parent.Height";
_parent.AddView((android.view.View)(_pnl.getObject()),(int) (0),(int) (0),_parent.getWidth(),_parent.getHeight());
 //BA.debugLineNum = 302;BA.debugLine="Dim img1  As ImageView";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 303;BA.debugLine="img1.Initialize(\"\")";
_img1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 304;BA.debugLine="pnl.AddView(img1,0,0,pnl.Width,pnl.Height)";
_pnl.AddView((android.view.View)(_img1.getObject()),(int) (0),(int) (0),_pnl.getWidth(),_pnl.getHeight());
 //BA.debugLineNum = 305;BA.debugLine="pnl.Padding=Array As Int (5dip, 0dip, 5dip, 5dip)";
_pnl.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview2_itemclick(anywheresoftware.b4a.objects.PanelWrapper _clickeditem,int _position) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
int _val_id = 0;
String _val_info = "";
 //BA.debugLineNum = 252;BA.debugLine="Sub LayoutView2_ItemClick (ClickedItem As Panel,Po";
 //BA.debugLineNum = 253;BA.debugLine="Log(ClickedItem.Tag)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_clickeditem.getTag()));
 //BA.debugLineNum = 254;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 255;BA.debugLine="parser.Initialize(	ClickedItem.Tag	)";
_parser.Initialize(BA.ObjectToString(_clickeditem.getTag()));
 //BA.debugLineNum = 256;BA.debugLine="Dim root As Map = parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 257;BA.debugLine="If root.Get(\"type\")=\"category\" Then";
if ((_root.Get((Object)("type"))).equals((Object)("category"))) { 
 //BA.debugLineNum = 258;BA.debugLine="Dim val_id As Int=root.Get(\"value\")";
_val_id = (int)(BA.ObjectToNumber(_root.Get((Object)("value"))));
 //BA.debugLineNum = 259;BA.debugLine="Dim val_info As String=root.Get(\"info\")";
_val_info = BA.ObjectToString(_root.Get((Object)("info")));
 //BA.debugLineNum = 260;BA.debugLine="show_list_kala.cat_name=val_info'نام گروه";
mostCurrent._show_list_kala._cat_name = _val_info;
 //BA.debugLineNum = 261;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 262;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 263;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 264;BA.debugLine="show_list_kala.code_cat=val_id'کد گروه";
mostCurrent._show_list_kala._code_cat = _val_id;
 //BA.debugLineNum = 266;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 };
 //BA.debugLineNum = 268;BA.debugLine="If root.Get(\"type\")=\"product\" Then";
if ((_root.Get((Object)("type"))).equals((Object)("product"))) { 
 //BA.debugLineNum = 269;BA.debugLine="info_kala.code_kala=val_id";
mostCurrent._info_kala._code_kala = _val_id;
 //BA.debugLineNum = 270;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 };
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview2_onbindviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent,int _position) throws Exception{
hpksoftware.setak.main._baner_list_1 _baner_list1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl1 = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl2 = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 242;BA.debugLine="Sub LayoutView2_onBindViewHolder (Parent As Panel,";
 //BA.debugLineNum = 243;BA.debugLine="Dim baner_list1=list1_baner.Get(Position) As bane";
_baner_list1 = (hpksoftware.setak.main._baner_list_1)(mostCurrent._list1_baner.Get(_position));
 //BA.debugLineNum = 244;BA.debugLine="Log(\"GridView: \" & baner_list1.src_pic)";
anywheresoftware.b4a.keywords.Common.Log("GridView: "+_baner_list1.src_pic);
 //BA.debugLineNum = 245;BA.debugLine="Dim pnl1 As Panel=Parent";
_pnl1 = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl1 = _parent;
 //BA.debugLineNum = 246;BA.debugLine="Dim pnl2 As Panel=pnl1.GetView(0)";
_pnl2 = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl2.setObject((android.view.ViewGroup)(_pnl1.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 247;BA.debugLine="Dim img1  As ImageView=pnl2.GetView(0)";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
_img1.setObject((android.widget.ImageView)(_pnl2.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 248;BA.debugLine="Parent.Tag=baner_list1.desc";
_parent.setTag((Object)(_baner_list1.desc));
 //BA.debugLineNum = 249;BA.debugLine="piccaso.Load(\"http\",baner_list1.src_pic).Resize(i";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_baner_list1.src_pic).Resize(_img1.getWidth(),_img1.getHeight()).SkipMemoryCache().Into(_img1);
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public static String  _layoutview2_oncreateviewholder(anywheresoftware.b4a.objects.PanelWrapper _parent) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
 //BA.debugLineNum = 228;BA.debugLine="Sub LayoutView2_onCreateViewHolder (Parent As Pane";
 //BA.debugLineNum = 229;BA.debugLine="Log(\"Round(list1_baner.Size/2) : \" & Round(list1_";
anywheresoftware.b4a.keywords.Common.Log("Round(list1_baner.Size/2) : "+BA.NumberToString(anywheresoftware.b4a.keywords.Common.Round(mostCurrent._list1_baner.getSize()/(double)2)));
 //BA.debugLineNum = 230;BA.debugLine="Parent.Height=(panel_baner_1.Height / Round(list1";
_parent.setHeight((int) ((mostCurrent._panel_baner_1.getHeight()/(double)anywheresoftware.b4a.keywords.Common.Round(mostCurrent._list1_baner.getSize()/(double)2))));
 //BA.debugLineNum = 231;BA.debugLine="Parent.Width=50%x";
_parent.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA));
 //BA.debugLineNum = 233;BA.debugLine="Dim pnl  As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 234;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 235;BA.debugLine="Parent.AddView(pnl,0,0,Parent.Width,Parent.Height";
_parent.AddView((android.view.View)(_pnl.getObject()),(int) (0),(int) (0),_parent.getWidth(),_parent.getHeight());
 //BA.debugLineNum = 236;BA.debugLine="Dim img1  As ImageView";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 237;BA.debugLine="img1.Initialize(\"\")";
_img1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 238;BA.debugLine="pnl.AddView(img1,0,0,pnl.Width,pnl.Height)";
_pnl.AddView((android.view.View)(_img1.getObject()),(int) (0),(int) (0),_pnl.getWidth(),_pnl.getHeight());
 //BA.debugLineNum = 239;BA.debugLine="pnl.Padding=Array As Int (5dip, 0dip, 5dip, 5dip)";
_pnl.setPadding(new int[]{anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (0)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))});
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return "";
}
public static String  _md_itemclick(int _position,int _identifier) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst_user = null;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _r = 0;
int _res = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 1367;BA.debugLine="Sub MD_ItemClick(Position As Int, Identifier As In";
 //BA.debugLineNum = 1368;BA.debugLine="Log(Position & \"\\\" & Identifier)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_position)+"\\"+BA.NumberToString(_identifier));
 //BA.debugLineNum = 1369;BA.debugLine="Select Identifier";
switch (_identifier) {
case 1: {
 //BA.debugLineNum = 1372;BA.debugLine="myLibrary.SetAnimation(\"file2\",\"file1\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file2","file1");
 //BA.debugLineNum = 1373;BA.debugLine="StartActivity(reg_login)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._reg_login.getObject()));
 //BA.debugLineNum = 1374;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 2: {
 //BA.debugLineNum = 1377;BA.debugLine="StartActivity(update_users)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._update_users.getObject()));
 break; }
case 3: {
 //BA.debugLineNum = 1380;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user)) { 
 //BA.debugLineNum = 1381;BA.debugLine="Dim lst_user As List=File.ReadList(myLibrary.r";
_lst_user = new anywheresoftware.b4a.objects.collections.List();
_lst_user = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 1382;BA.debugLine="history_order.id_user=lst_user.Get(3)";
mostCurrent._history_order._id_user = (int)(BA.ObjectToNumber(_lst_user.Get((int) (3))));
 //BA.debugLineNum = 1383;BA.debugLine="Log(\"id_user\" & lst_user.Get(3))";
anywheresoftware.b4a.keywords.Common.Log("id_user"+BA.ObjectToString(_lst_user.Get((int) (3))));
 //BA.debugLineNum = 1384;BA.debugLine="StartActivity(history_order)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._history_order.getObject()));
 }else {
 //BA.debugLineNum = 1386;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 1387;BA.debugLine="Pd=function.style_msgbox(\"توجه\",\"شما وارد حساب";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"توجه","شما وارد حساب کاربری خود نشدید.اگر عضو نیستید میتوانید رایگان ثبتنام کنید","ورد یا ثبت نام","","انصراف");
 //BA.debugLineNum = 1388;BA.debugLine="Dim r  As Int = Pd.Create";
_r = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 1389;BA.debugLine="If r=DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 1390;BA.debugLine="StartActivity(reg_login)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._reg_login.getObject()));
 };
 };
 break; }
case 4: {
 //BA.debugLineNum = 1395;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 1396;BA.debugLine="Pd=function.style_msgbox(\"\",\"آیا میخواهید از حس";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"","آیا میخواهید از حساب کاربری خود خارج شوید؟","بله","خیر","");
 //BA.debugLineNum = 1397;BA.debugLine="Dim res As Int = Pd.Create";
_res = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 1398;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 1399;BA.debugLine="File.Delete(myLibrary.rute,name_dat)";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._mylibrary._rute(mostCurrent.activityBA),_name_dat);
 //BA.debugLineNum = 1402;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 break; }
case 5: {
 //BA.debugLineNum = 1406;BA.debugLine="catgory.bool_brand_layout=True";
mostCurrent._catgory._bool_brand_layout = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1407;BA.debugLine="StartActivity(catgory)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._catgory.getObject()));
 break; }
case 6: {
 //BA.debugLineNum = 1410;BA.debugLine="show_list_kala.bool_porfroush=True";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1411;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 break; }
case 7: {
 //BA.debugLineNum = 1414;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1415;BA.debugLine="show_list_kala.bool_brand_layout=False";
mostCurrent._show_list_kala._bool_brand_layout = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1416;BA.debugLine="show_list_kala.bool_news_list=True";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1417;BA.debugLine="show_list_kala.cat_name=\"جدیدترین ها\"";
mostCurrent._show_list_kala._cat_name = "جدیدترین ها";
 //BA.debugLineNum = 1418;BA.debugLine="StartActivity(show_list_kala)'جدیدترین ها";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 break; }
case 8: {
 //BA.debugLineNum = 1421;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 1422;BA.debugLine="i.Initialize(i.ACTION_VIEW,\"https://t.me/padide";
_i.Initialize(_i.ACTION_VIEW,"https://t.me/padideh_sazan");
 //BA.debugLineNum = 1423;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 break; }
case 9: {
 //BA.debugLineNum = 1426;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 1427;BA.debugLine="i.Initialize(i.ACTION_VIEW,\"https://t.me/info_h";
_i.Initialize(_i.ACTION_VIEW,"https://t.me/info_hpk");
 //BA.debugLineNum = 1428;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_i.getObject()));
 break; }
}
;
 //BA.debugLineNum = 1432;BA.debugLine="End Sub";
return "";
}
public static String  _msa_profilechanged(com.maximussoft.msmaterialdrawer.MSProfile _profile) throws Exception{
 //BA.debugLineNum = 1356;BA.debugLine="Sub MSA_ProfileChanged(profile As MSProfile)";
 //BA.debugLineNum = 1357;BA.debugLine="Log(\"MSA_ProfileChanged\")";
anywheresoftware.b4a.keywords.Common.Log("MSA_ProfileChanged");
 //BA.debugLineNum = 1358;BA.debugLine="If function.is_admin=True Then";
if (mostCurrent._function._is_admin(mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1359;BA.debugLine="StartActivity(shared_admin)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._shared_admin.getObject()));
 };
 //BA.debugLineNum = 1361;BA.debugLine="End Sub";
return "";
}
public static String  _msa_profileclicked(com.maximussoft.msmaterialdrawer.MSProfile _profile) throws Exception{
 //BA.debugLineNum = 1363;BA.debugLine="Sub  MSA_ProfileClicked(profile As MSProfile)";
 //BA.debugLineNum = 1364;BA.debugLine="Log(\"MSA_ProfileClicked\")";
anywheresoftware.b4a.keywords.Common.Log("MSA_ProfileClicked");
 //BA.debugLineNum = 1365;BA.debugLine="End Sub";
return "";
}
public static String  _panel_porfroush_touch(int _action,float _x,float _y) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
 //BA.debugLineNum = 1079;BA.debugLine="Sub panel_porfroush_Touch (Action As Int, X As Flo";
 //BA.debugLineNum = 1080;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 1081;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1082;BA.debugLine="If Action=1 Then";
if (_action==1) { 
 //BA.debugLineNum = 1083;BA.debugLine="pnl=Sender";
_pnl.setObject((android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 1084;BA.debugLine="info_kala.code_kala=pnl.Tag";
mostCurrent._info_kala._code_kala = (int)(BA.ObjectToNumber(_pnl.getTag()));
 //BA.debugLineNum = 1085;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 };
 //BA.debugLineNum = 1087;BA.debugLine="End Sub";
return "";
}
public static String  _panel_slider_scrollchanged(int _position) throws Exception{
 //BA.debugLineNum = 1111;BA.debugLine="Sub Panel_slider_ScrollChanged(Position As Int)";
 //BA.debugLineNum = 1112;BA.debugLine="End Sub";
return "";
}
public static String  _panel_viesue_touch(int _action,float _x,float _y) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
Object _value = null;
String[] _arr_temp = null;
 //BA.debugLineNum = 1089;BA.debugLine="Sub panel_viesue_Touch (Action As Int, X As Float,";
 //BA.debugLineNum = 1090;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 1091;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1092;BA.debugLine="If Action=1 Then";
if (_action==1) { 
 //BA.debugLineNum = 1093;BA.debugLine="pnl=Sender";
_pnl.setObject((android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 1096;BA.debugLine="Dim value As Object=pnl.Tag";
_value = _pnl.getTag();
 //BA.debugLineNum = 1097;BA.debugLine="Dim arr_temp() As String";
_arr_temp = new String[(int) (0)];
java.util.Arrays.fill(_arr_temp,"");
 //BA.debugLineNum = 1098;BA.debugLine="arr_temp=Regex.Split(\";\",value)";
_arr_temp = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_value));
 //BA.debugLineNum = 1099;BA.debugLine="show_list_kala.cat_name=arr_temp(2)'نام گروه";
mostCurrent._show_list_kala._cat_name = _arr_temp[(int) (2)];
 //BA.debugLineNum = 1100;BA.debugLine="show_list_kala.bool_cat=True";
mostCurrent._show_list_kala._bool_cat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1101;BA.debugLine="show_list_kala.bool_news_list=False";
mostCurrent._show_list_kala._bool_news_list = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1102;BA.debugLine="show_list_kala.bool_porfroush=False";
mostCurrent._show_list_kala._bool_porfroush = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 1103;BA.debugLine="show_list_kala.code_cat=arr_temp(0)'کد گروه";
mostCurrent._show_list_kala._code_cat = (int)(Double.parseDouble(_arr_temp[(int) (0)]));
 //BA.debugLineNum = 1104;BA.debugLine="show_list_kala.code_sub_cat=arr_temp(1)'کد زیر گ";
mostCurrent._show_list_kala._code_sub_cat = (int)(Double.parseDouble(_arr_temp[(int) (1)]));
 //BA.debugLineNum = 1105;BA.debugLine="StartActivity(show_list_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_list_kala.getObject()));
 };
 //BA.debugLineNum = 1107;BA.debugLine="End Sub";
return "";
}
public static String  _panelmenu_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 646;BA.debugLine="Sub Panelmenu_Touch (Action As Int, X As Float, Y";
 //BA.debugLineNum = 648;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
b4a.example.frgfg.connector._process_globals();
b4a.example.frgfg.db_mysql._process_globals();
at.ahadev.b4a.ahashare.ahacontentchooser._process_globals();
main._process_globals();
starter._process_globals();
insert_noskhe._process_globals();
show_noskhe._process_globals();
alarm_service._process_globals();
rezerv._process_globals();
order._process_globals();
catgory._process_globals();
library._process_globals();
show_list_kala._process_globals();
project_help._process_globals();
reg_login._process_globals();
sabad._process_globals();
history_order._process_globals();
pushejsonservice._process_globals();
shared_admin._process_globals();
update_users._process_globals();
info_kala._process_globals();
update._process_globals();
mylibrary._process_globals();
pushservice._process_globals();
function._process_globals();
show_nazar._process_globals();
nazar._process_globals();
push_active._process_globals();
result._process_globals();
disconnect._process_globals();
changefontbylabelsize._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 35;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 36;BA.debugLine="Public name_dat As String";
_name_dat = "";
 //BA.debugLineNum = 37;BA.debugLine="Dim Pushe As Pushe";
_pushe = new co.ronash.pushe.wrapper.PusheWrapper();
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static String  _register_push() throws Exception{
 //BA.debugLineNum = 1193;BA.debugLine="Sub Register_push";
 //BA.debugLineNum = 1194;BA.debugLine="CallSubDelayed2(PushService, \"RegisterDevice\", Fa";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(mostCurrent.activityBA,(Object)(mostCurrent._pushservice.getObject()),"RegisterDevice",(Object)(anywheresoftware.b4a.keywords.Common.False));
 //BA.debugLineNum = 1195;BA.debugLine="End Sub";
return "";
}
public static String  _requestsearchbar() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _ref = null;
 //BA.debugLineNum = 1175;BA.debugLine="Sub RequestSearchBar";
 //BA.debugLineNum = 1176;BA.debugLine="Dim ref As Reflector";
_ref = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 1177;BA.debugLine="ref.Target = ref.GetActivity";
_ref.Target = (Object)(_ref.GetActivity(processBA));
 //BA.debugLineNum = 1178;BA.debugLine="ref.RunPublicmethod(\"onSearchRequested\", Null, N";
_ref.RunPublicmethod("onSearchRequested",(Object[])(anywheresoftware.b4a.keywords.Common.Null),(String[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 1179;BA.debugLine="End Sub";
return "";
}
public static String  _splash_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 1530;BA.debugLine="Sub splash_Touch (Action As Int, X As Float, Y As";
 //BA.debugLineNum = 1532;BA.debugLine="End Sub";
return "";
}
public static String  _target1(String _img,Object _tag) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _image_view = null;
 //BA.debugLineNum = 1238;BA.debugLine="Sub Target1(img As String, Tag As Object)";
 //BA.debugLineNum = 1239;BA.debugLine="Dim image_view As ImageView=Tag";
_image_view = new anywheresoftware.b4a.objects.ImageViewWrapper();
_image_view.setObject((android.widget.ImageView)(_tag));
 //BA.debugLineNum = 1241;BA.debugLine="piccaso.Load(\"http\",img).Into(image_view)";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_img).Into(_image_view);
 //BA.debugLineNum = 1243;BA.debugLine="image_view.Gravity=Gravity.FILL";
_image_view.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 1244;BA.debugLine="End Sub";
return "";
}
public static String  _time_life_viesue() throws Exception{
 //BA.debugLineNum = 990;BA.debugLine="Sub time_life_viesue";
 //BA.debugLineNum = 991;BA.debugLine="Log(\"time_life_viesue\")";
anywheresoftware.b4a.keywords.Common.Log("time_life_viesue");
 //BA.debugLineNum = 992;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 993;BA.debugLine="connector.send_query($\"SELECT * FROM `time_viesue";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `time_viesue`"),"time_life_viesue",(Object)(""));
 //BA.debugLineNum = 994;BA.debugLine="End Sub";
return "";
}
public static String  _timer_slide_tick() throws Exception{
int _count = 0;
 //BA.debugLineNum = 1221;BA.debugLine="Sub timer_slide_Tick";
 //BA.debugLineNum = 1222;BA.debugLine="If timer_slide.IsInitialized=True Then";
if (mostCurrent._timer_slide.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 1223;BA.debugLine="Dim count As Int=pa.Count-1";
_count = (int) (mostCurrent._pa.getCount()-1);
 //BA.debugLineNum = 1224;BA.debugLine="pb.GotoPage(index_slider)";
mostCurrent._pb.GotoPage(_index_slider);
 //BA.debugLineNum = 1225;BA.debugLine="If index_slider >=count Then";
if (_index_slider>=_count) { 
 //BA.debugLineNum = 1226;BA.debugLine="index_slider=0";
_index_slider = (int) (0);
 }else {
 //BA.debugLineNum = 1229;BA.debugLine="index_slider=index_slider+1";
_index_slider = (int) (_index_slider+1);
 };
 }else {
 //BA.debugLineNum = 1232;BA.debugLine="timer_slide.Initialize(\"timer_slide\",Starter.tim";
mostCurrent._timer_slide.Initialize(processBA,"timer_slide",(long) (mostCurrent._starter._timer_slideshow));
 //BA.debugLineNum = 1233;BA.debugLine="timer_slide.Enabled=True";
mostCurrent._timer_slide.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 1236;BA.debugLine="End Sub";
return "";
}
public static String  _timer_splash_tick() throws Exception{
 //BA.debugLineNum = 1250;BA.debugLine="Sub  timer_splash_Tick";
 //BA.debugLineNum = 1251;BA.debugLine="timer_splash.Enabled=False";
mostCurrent._timer_splash.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1252;BA.debugLine="splash.Visible=False";
mostCurrent._splash.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1253;BA.debugLine="End Sub";
return "";
}
}
