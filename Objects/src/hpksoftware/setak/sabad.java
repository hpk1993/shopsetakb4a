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

public class sabad extends Activity implements B4AActivity{
	public static sabad mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.sabad");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (sabad).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.sabad");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.sabad", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (sabad) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (sabad) Resume **");
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
		return sabad.class;
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
        BA.LogInfo("** Activity (sabad) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (sabad) Resume **");
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
public static String _dir_root_image_file = "";
public b4a.example.money _toman = null;
public hpksoftware.setak.customlistview _lsv_sabad = null;
public dmax.dialog.Spotsd _progress_spot = null;
public wir.hitex.recycler.Hitex_Picasso _piccaso = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _error_image = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _progress_image = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_main = null;
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public static long _time = 0L;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_price = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_tedad = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.IME _keyboard = null;
public static int _pos_panel = 0;
public anywheresoftware.b4a.objects.collections.List _list_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _lbl_fi = null;
public anywheresoftware.b4a.objects.Timer _timer1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_setting = null;
public static int _code_kala = 0;
public de.amberhome.objects.appcompat.AppCompatBase _apc = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_sabad = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_share = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_search = null;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public static int _mlastscrolly = 0;
public anywheresoftware.b4a.objects.SpinnerWrapper _spin_tedad = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_price_kol = null;
public static int _count_item_sabad = 0;
public anywheresoftware.b4a.objects.LabelWrapper _titlebar_price_kol = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_badge = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_buy = null;
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
anywheresoftware.b4a.objects.drawable.ColorDrawable _bg_titlebar_price_kol = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _btn_bitmap = null;
int _i = 0;
 //BA.debugLineNum = 68;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 69;BA.debugLine="Activity.LoadLayout(\"category\")";
mostCurrent._activity.LoadLayout("category",mostCurrent.activityBA);
 //BA.debugLineNum = 70;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 71;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 72;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 74;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,sabad.getObject());
 //BA.debugLineNum = 75;BA.debugLine="btn_buy.Visible=True";
mostCurrent._btn_buy.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 76;BA.debugLine="titlebar_price_kol.Visible=True";
mostCurrent._titlebar_price_kol.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 78;BA.debugLine="Dim bg_titlebar_price_kol As ColorDrawable";
_bg_titlebar_price_kol = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 79;BA.debugLine="bg_titlebar_price_kol.Initialize(Starter.color_ba";
_bg_titlebar_price_kol.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 80;BA.debugLine="titlebar_price_kol.Background=bg_titlebar_price_k";
mostCurrent._titlebar_price_kol.setBackground((android.graphics.drawable.Drawable)(_bg_titlebar_price_kol.getObject()));
 //BA.debugLineNum = 81;BA.debugLine="apc.SetElevation(titlebar_price_kol,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._titlebar_price_kol.getObject()),(float) (7));
 //BA.debugLineNum = 82;BA.debugLine="apc.SetElevation(panel_setting,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._panel_setting.getObject()),(float) (7));
 //BA.debugLineNum = 83;BA.debugLine="panel_main.SetLayout(0,titlebar_price_kol.Top + t";
mostCurrent._panel_main.SetLayout((int) (0),(int) (mostCurrent._titlebar_price_kol.getTop()+mostCurrent._titlebar_price_kol.getHeight()),mostCurrent._panel_main.getWidth(),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-(mostCurrent._panel1.getHeight()+mostCurrent._titlebar_price_kol.getHeight())));
 //BA.debugLineNum = 90;BA.debugLine="error_image.Initialize(LoadBitmap(File.DirAssets,";
mostCurrent._error_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.png").getObject()));
 //BA.debugLineNum = 91;BA.debugLine="progress_image.Initialize(LoadBitmap(File.DirAsse";
mostCurrent._progress_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prog_img.png").getObject()));
 //BA.debugLineNum = 94;BA.debugLine="server_get_time=Starter.server_get_time";
_server_get_time = mostCurrent._starter._server_get_time;
 //BA.debugLineNum = 95;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 96;BA.debugLine="dir_root_image_file=Starter.dir_root_image_file_t";
_dir_root_image_file = mostCurrent._starter._dir_root_image_file_thumnail;
 //BA.debugLineNum = 98;BA.debugLine="keyboard.Initialize(\"keyboard_t\")";
mostCurrent._keyboard.Initialize("keyboard_t");
 //BA.debugLineNum = 99;BA.debugLine="keyboard.AddHeightChangedEvent";
mostCurrent._keyboard.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 103;BA.debugLine="Label3.Text=\"\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 104;BA.debugLine="Dim btn_bitmap As BitmapDrawable";
_btn_bitmap = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 105;BA.debugLine="btn_bitmap.Initialize(LoadBitmap(File.DirAssets,\"";
_btn_bitmap.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"delete.png").getObject()));
 //BA.debugLineNum = 106;BA.debugLine="btn_bitmap.Gravity=Gravity.FILL";
_btn_bitmap.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 107;BA.debugLine="btn_sabad.Background=btn_bitmap";
mostCurrent._btn_sabad.setBackground((android.graphics.drawable.Drawable)(_btn_bitmap.getObject()));
 //BA.debugLineNum = 108;BA.debugLine="btn_bitmap.Initialize(LoadBitmap(File.DirAssets,\"";
_btn_bitmap.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"history.png").getObject()));
 //BA.debugLineNum = 109;BA.debugLine="btn_share.Background=btn_bitmap";
mostCurrent._btn_share.setBackground((android.graphics.drawable.Drawable)(_btn_bitmap.getObject()));
 //BA.debugLineNum = 110;BA.debugLine="btn_search.Visible=False";
mostCurrent._btn_search.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 112;BA.debugLine="timer1.Initialize(\"timer1\",2500)";
mostCurrent._timer1.Initialize(processBA,"timer1",(long) (2500));
 //BA.debugLineNum = 113;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 114;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,sabad.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff13879a),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 115;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 116;BA.debugLine="lsv_sabad.Initialize(Me,\"sv_sabad_me\",0xFFF6F1F1,";
mostCurrent._lsv_sabad._initialize(mostCurrent.activityBA,sabad.getObject(),"sv_sabad_me",(int) (0xfff6f1f1),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
 //BA.debugLineNum = 117;BA.debugLine="panel_main.AddView(lsv_sabad.AsView,0,0,panel_mai";
mostCurrent._panel_main.AddView((android.view.View)(mostCurrent._lsv_sabad._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_main.getWidth(),(int) (mostCurrent._panel_main.getHeight()-mostCurrent._btn_buy.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 118;BA.debugLine="panel_main.Color=0xFFB6B3B6";
mostCurrent._panel_main.setColor((int) (0xffb6b3b6));
 //BA.debugLineNum = 119;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_sa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 120;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 121;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.f";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 122;BA.debugLine="Log(\"sabad: \" & list_kala)";
anywheresoftware.b4a.keywords.Common.Log("sabad: "+BA.ObjectToString(mostCurrent._list_kala));
 //BA.debugLineNum = 123;BA.debugLine="If list_kala.Size > 0 Then";
if (mostCurrent._list_kala.getSize()>0) { 
 //BA.debugLineNum = 124;BA.debugLine="For i=0 To list_kala.Size-1";
{
final int step41 = 1;
final int limit41 = (int) (mostCurrent._list_kala.getSize()-1);
for (_i = (int) (0) ; (step41 > 0 && _i <= limit41) || (step41 < 0 && _i >= limit41); _i = ((int)(0 + _i + step41)) ) {
 //BA.debugLineNum = 125;BA.debugLine="Log(\"kala= \" & list_kala.Get(i))";
anywheresoftware.b4a.keywords.Common.Log("kala= "+BA.ObjectToString(mostCurrent._list_kala.Get(_i)));
 }
};
 //BA.debugLineNum = 129;BA.debugLine="get_sabad";
_get_sabad();
 };
 }else {
 //BA.debugLineNum = 132;BA.debugLine="titlebar_price_kol.Text=\"سبد خرید شما خالی است\"";
mostCurrent._titlebar_price_kol.setText(BA.ObjectToCharSequence("سبد خرید شما خالی است"));
 //BA.debugLineNum = 133;BA.debugLine="btn_buy.Enabled=False";
mostCurrent._btn_buy.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 429;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 430;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 431;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 432;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 433;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 435;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 157;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 140;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 141;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,sabad.getObject());
 //BA.debugLineNum = 142;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_sa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 143;BA.debugLine="lsv_sabad.Clear";
mostCurrent._lsv_sabad._clear();
 //BA.debugLineNum = 145;BA.debugLine="titlebar_price_kol.Text=\"سبد خرید شما خالی است\"";
mostCurrent._titlebar_price_kol.setText(BA.ObjectToCharSequence("سبد خرید شما خالی است"));
 //BA.debugLineNum = 146;BA.debugLine="btn_buy.Enabled=False";
mostCurrent._btn_buy.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 147;BA.debugLine="lbl_badge.Visible=False";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 149;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_sa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 150;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 151;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.f";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 152;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 //BA.debugLineNum = 153;BA.debugLine="If list_kala.Size<=0 Then lbl_badge.Visible=Fals";
if (mostCurrent._list_kala.getSize()<=0) { 
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);};
 };
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 424;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 425;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 426;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 427;BA.debugLine="End Sub";
return "";
}
public static String  _btn_buy_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _r = 0;
 //BA.debugLineNum = 334;BA.debugLine="Sub btn_buy_Click";
 //BA.debugLineNum = 336;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_us";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user)) { 
 //BA.debugLineNum = 337;BA.debugLine="Dim lst As List=File.ReadList(myLibrary.rute,Sta";
_lst = new anywheresoftware.b4a.objects.collections.List();
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 342;BA.debugLine="order.id_user=lst.Get(3)";
mostCurrent._order._id_user = (int)(BA.ObjectToNumber(_lst.Get((int) (3))));
 //BA.debugLineNum = 343;BA.debugLine="order.order_detiles=get_factor";
mostCurrent._order._order_detiles = _get_factor();
 //BA.debugLineNum = 344;BA.debugLine="StartActivity(order)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._order.getObject()));
 }else {
 //BA.debugLineNum = 348;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 349;BA.debugLine="Pd=function.style_msgbox(\"توجه\",\"شما وارد حساب ک";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"توجه","شما وارد حساب کاربری خود نشدید.اگر عضو نیستید میتوانید رایگان ثبت نام کنید","ورد یا ثبت نام","","انصراف");
 //BA.debugLineNum = 350;BA.debugLine="Dim r  As Int = Pd.Create";
_r = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 351;BA.debugLine="If r=DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 352;BA.debugLine="StartActivity(reg_login)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._reg_login.getObject()));
 };
 };
 //BA.debugLineNum = 358;BA.debugLine="End Sub";
return "";
}
public static String  _btn_buy_longclick() throws Exception{
 //BA.debugLineNum = 330;BA.debugLine="Sub btn_buy_LongClick";
 //BA.debugLineNum = 331;BA.debugLine="Log(\"Long click\")";
anywheresoftware.b4a.keywords.Common.Log("Long click");
 //BA.debugLineNum = 332;BA.debugLine="End Sub";
return "";
}
public static String  _btn_del_kala_sabad_click() throws Exception{
String _tmp = "";
int _i = 0;
String _select_kala = "";
 //BA.debugLineNum = 363;BA.debugLine="Sub btn_del_kala_sabad_Click";
 //BA.debugLineNum = 364;BA.debugLine="Dim tmp As String=code_kala";
_tmp = BA.NumberToString(_code_kala);
 //BA.debugLineNum = 365;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 366;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.fi";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 367;BA.debugLine="For i=0 To list_kala.Size-1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._list_kala.getSize()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 368;BA.debugLine="Dim select_kala As String=list_kala.Get(i)";
_select_kala = BA.ObjectToString(mostCurrent._list_kala.Get(_i));
 //BA.debugLineNum = 369;BA.debugLine="If tmp.CompareTo(select_kala)=0 Then";
if (_tmp.compareTo(_select_kala)==0) { 
 //BA.debugLineNum = 370;BA.debugLine="list_kala.RemoveAt(i)";
mostCurrent._list_kala.RemoveAt(_i);
 //BA.debugLineNum = 371;BA.debugLine="lsv_sabad.RemoveAt(pos_panel)";
mostCurrent._lsv_sabad._removeat(_pos_panel);
 //BA.debugLineNum = 372;BA.debugLine="File.WriteList(myLibrary.rute,Starter.filename_";
anywheresoftware.b4a.keywords.Common.File.WriteList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad,mostCurrent._list_kala);
 //BA.debugLineNum = 373;BA.debugLine="list_kala.Clear";
mostCurrent._list_kala.Clear();
 //BA.debugLineNum = 374;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 375;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 //BA.debugLineNum = 376;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 379;BA.debugLine="panel_setting.SetLayoutAnimated(1000,100%x+panel_";
mostCurrent._panel_setting.SetLayoutAnimated((int) (1000),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)+mostCurrent._panel_setting.getWidth()),mostCurrent._panel_setting.getTop(),mostCurrent._panel_setting.getWidth(),mostCurrent._panel_setting.getHeight());
 //BA.debugLineNum = 380;BA.debugLine="End Sub";
return "";
}
public static String  _btn_sabad_click() throws Exception{
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _r = 0;
 //BA.debugLineNum = 406;BA.debugLine="Sub btn_sabad_Click";
 //BA.debugLineNum = 407;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 408;BA.debugLine="Pd=function.style_msgbox(\"\",\"آیا میخواهید لیست خر";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"","آیا میخواهید لیست خرید را خالی کنید؟","بلی","خیر","");
 //BA.debugLineNum = 409;BA.debugLine="Dim r As Int = Pd.Create";
_r = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 410;BA.debugLine="If DialogResponse.POSITIVE=r Then";
if (anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE==_r) { 
 //BA.debugLineNum = 411;BA.debugLine="delete_sabad";
_delete_sabad();
 };
 //BA.debugLineNum = 413;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst_user = null;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _r = 0;
 //BA.debugLineNum = 389;BA.debugLine="Sub btn_share_Click";
 //BA.debugLineNum = 390;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_us";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user)) { 
 //BA.debugLineNum = 391;BA.debugLine="Dim lst_user As List=File.ReadList(myLibrary.rut";
_lst_user = new anywheresoftware.b4a.objects.collections.List();
_lst_user = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 392;BA.debugLine="history_order.id_user=lst_user.Get(3)";
mostCurrent._history_order._id_user = (int)(BA.ObjectToNumber(_lst_user.Get((int) (3))));
 //BA.debugLineNum = 393;BA.debugLine="Log(\"id_user\" & lst_user.Get(3))";
anywheresoftware.b4a.keywords.Common.Log("id_user"+BA.ObjectToString(_lst_user.Get((int) (3))));
 //BA.debugLineNum = 394;BA.debugLine="StartActivity(history_order)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._history_order.getObject()));
 }else {
 //BA.debugLineNum = 396;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 397;BA.debugLine="Pd=function.style_msgbox(\"توجه\",\"شما وارد حساب ک";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"توجه","شما وارد حساب کاربری خود نشدید.اگر عضو نیستید میتوانید رایگان ثبتنام کنید","ورد یا ثبت نام","","انصراف");
 //BA.debugLineNum = 398;BA.debugLine="Dim r  As Int = Pd.Create";
_r = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 399;BA.debugLine="If r=DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 400;BA.debugLine="StartActivity(reg_login)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._reg_login.getObject()));
 };
 };
 //BA.debugLineNum = 404;BA.debugLine="End Sub";
return "";
}
public static String  _btn_show_kala_click() throws Exception{
 //BA.debugLineNum = 382;BA.debugLine="Sub btn_show_kala_Click";
 //BA.debugLineNum = 383;BA.debugLine="info_kala.code_kala=code_kala";
mostCurrent._info_kala._code_kala = _code_kala;
 //BA.debugLineNum = 384;BA.debugLine="StartActivity(info_kala)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._info_kala.getObject()));
 //BA.debugLineNum = 385;BA.debugLine="panel_setting.SetLayoutAnimated(1000,100%x+panel_";
mostCurrent._panel_setting.SetLayoutAnimated((int) (1000),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)+mostCurrent._panel_setting.getWidth()),mostCurrent._panel_setting.getTop(),mostCurrent._panel_setting.getWidth(),mostCurrent._panel_setting.getHeight());
 //BA.debugLineNum = 386;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper[] _btn_fi = null;
de.amberhome.objects.appcompat.ACSpinnerWrapper[] _spin_tedad2 = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
int _price_of = 0;
int _is_visue = 0;
int _number_of = 0;
int _j = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_fi = null;
String _url_pic = "";
 //BA.debugLineNum = 161;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 162;BA.debugLine="Try";
try { //BA.debugLineNum = 163;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_sabad"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 166;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 168;BA.debugLine="Dim btn_fi(records.Size) As Button";
_btn_fi = new anywheresoftware.b4a.objects.ButtonWrapper[_records.getSize()];
{
int d0 = _btn_fi.length;
for (int i0 = 0;i0 < d0;i0++) {
_btn_fi[i0] = new anywheresoftware.b4a.objects.ButtonWrapper();
}
}
;
 //BA.debugLineNum = 169;BA.debugLine="Dim spin_tedad2(records.Size) As ACSpinner";
_spin_tedad2 = new de.amberhome.objects.appcompat.ACSpinnerWrapper[_records.getSize()];
{
int d0 = _spin_tedad2.length;
for (int i0 = 0;i0 < d0;i0++) {
_spin_tedad2[i0] = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
}
}
;
 //BA.debugLineNum = 171;BA.debugLine="For i=0 To records.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 174;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 175;BA.debugLine="Dim p1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 176;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 177;BA.debugLine="p1.LoadLayout(\"sv_sabad\")";
_p1.LoadLayout("sv_sabad",mostCurrent.activityBA);
 //BA.debugLineNum = 178;BA.debugLine="apc.SetElevation(Panel1,8)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._panel1.getObject()),(float) (8));
 //BA.debugLineNum = 179;BA.debugLine="lsv_sabad.Add(p1,Panel1.Height,map1.Get(\"id\"";
mostCurrent._lsv_sabad._add(_p1,mostCurrent._panel1.getHeight(),_map1.Get((Object)("id")));
 //BA.debugLineNum = 180;BA.debugLine="lbl_name.Text=map1.Get(\"name_kala\")";
mostCurrent._lbl_name.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name_kala"))));
 //BA.debugLineNum = 182;BA.debugLine="Dim price_of As Int";
_price_of = 0;
 //BA.debugLineNum = 183;BA.debugLine="Dim is_visue As Int=map1.Get(\"stut_viesue\")";
_is_visue = (int)(BA.ObjectToNumber(_map1.Get((Object)("stut_viesue"))));
 //BA.debugLineNum = 184;BA.debugLine="If is_visue=1 Then";
if (_is_visue==1) { 
 //BA.debugLineNum = 185;BA.debugLine="price_of=map1.Get(\"price_off\")";
_price_of = (int)(BA.ObjectToNumber(_map1.Get((Object)("price_off"))));
 }else {
 //BA.debugLineNum = 187;BA.debugLine="price_of=map1.Get(\"price\")";
_price_of = (int)(BA.ObjectToNumber(_map1.Get((Object)("price"))));
 };
 //BA.debugLineNum = 190;BA.debugLine="txt_tedad.Tag=map1.Get(\"id\") '& \";\" & price_";
mostCurrent._txt_tedad.setTag(_map1.Get((Object)("id")));
 //BA.debugLineNum = 191;BA.debugLine="lbl_price.Text=\"\" & toman.number(price_of) &";
mostCurrent._lbl_price.setText(BA.ObjectToCharSequence(""+mostCurrent._toman._number(BA.NumberToString(_price_of))+" تومان"));
 //BA.debugLineNum = 192;BA.debugLine="lbl_price.Tag=price_of";
mostCurrent._lbl_price.setTag((Object)(_price_of));
 //BA.debugLineNum = 193;BA.debugLine="lbl_price.Typeface=Starter.font_body";
mostCurrent._lbl_price.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 195;BA.debugLine="spin_tedad2(i).Initialize(\"spin_tedad2\")";
_spin_tedad2[_i].Initialize(mostCurrent.activityBA,"spin_tedad2");
 //BA.debugLineNum = 197;BA.debugLine="Dim number_of As Int=map1.Get(\"number\")";
_number_of = (int)(BA.ObjectToNumber(_map1.Get((Object)("number"))));
 //BA.debugLineNum = 198;BA.debugLine="For j=1 To number_of";
{
final int step28 = 1;
final int limit28 = _number_of;
for (_j = (int) (1) ; (step28 > 0 && _j <= limit28) || (step28 < 0 && _j >= limit28); _j = ((int)(0 + _j + step28)) ) {
 //BA.debugLineNum = 199;BA.debugLine="spin_tedad2(i).Add(myLibrary.CovertEnglish2";
_spin_tedad2[_i].Add(BA.ObjectToCharSequence(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,BA.NumberToString(_j))));
 }
};
 //BA.debugLineNum = 201;BA.debugLine="If number_of<=0 Then spin_tedad2(i).Add(myLi";
if (_number_of<=0) { 
_spin_tedad2[_i].Add(BA.ObjectToCharSequence(mostCurrent._mylibrary._covertenglish2persian(mostCurrent.activityBA,BA.NumberToString(0))));};
 //BA.debugLineNum = 203;BA.debugLine="spin_tedad2(i).TextColor=Colors.Black";
_spin_tedad2[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 204;BA.debugLine="spin_tedad2(i).SelectedIndex=0";
_spin_tedad2[_i].setSelectedIndex((int) (0));
 //BA.debugLineNum = 205;BA.debugLine="spin_tedad2(i).DropdownBackgroundColor=Color";
_spin_tedad2[_i].setDropdownBackgroundColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 206;BA.debugLine="spin_tedad2(i).DropdownTextColor=Colors.Dark";
_spin_tedad2[_i].setDropdownTextColor(anywheresoftware.b4a.keywords.Common.Colors.DarkGray);
 //BA.debugLineNum = 207;BA.debugLine="spin_tedad2(i).SetVisibleAnimated(500,True)";
_spin_tedad2[_i].SetVisibleAnimated((int) (500),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 208;BA.debugLine="spin_tedad2(i).TextSize=16";
_spin_tedad2[_i].setTextSize((float) (16));
 //BA.debugLineNum = 210;BA.debugLine="spin_tedad2(i).Tag=count_item_sabad";
_spin_tedad2[_i].setTag((Object)(_count_item_sabad));
 //BA.debugLineNum = 212;BA.debugLine="Panel1.AddView(spin_tedad2(i),spin_tedad.Lef";
mostCurrent._panel1.AddView((android.view.View)(_spin_tedad2[_i].getObject()),mostCurrent._spin_tedad.getLeft(),mostCurrent._spin_tedad.getTop(),mostCurrent._spin_tedad.getWidth(),mostCurrent._spin_tedad.getHeight());
 //BA.debugLineNum = 214;BA.debugLine="Dim color_fi As ColorDrawable";
_color_fi = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 215;BA.debugLine="color_fi.Initialize2(Colors.Transparent,50,2";
_color_fi.Initialize2(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (50),(int) (2),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 216;BA.debugLine="btn_fi(i).Initialize(\"btn_fi\")";
_btn_fi[_i].Initialize(mostCurrent.activityBA,"btn_fi");
 //BA.debugLineNum = 217;BA.debugLine="btn_fi(i).Text=\"!\"";
_btn_fi[_i].setText(BA.ObjectToCharSequence("!"));
 //BA.debugLineNum = 218;BA.debugLine="btn_fi(i).Background=color_fi";
_btn_fi[_i].setBackground((android.graphics.drawable.Drawable)(_color_fi.getObject()));
 //BA.debugLineNum = 219;BA.debugLine="btn_fi(i).TextSize=13";
_btn_fi[_i].setTextSize((float) (13));
 //BA.debugLineNum = 220;BA.debugLine="btn_fi(i).TextColor=Colors.Blue";
_btn_fi[_i].setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 221;BA.debugLine="Try";
try { //BA.debugLineNum = 222;BA.debugLine="btn_fi(i).Tag=myLibrary.CovertPersian2Engli";
_btn_fi[_i].setTag((Object)((double)(Double.parseDouble(mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,_spin_tedad2[_i].getSelectedItem())))*_price_of));
 } 
       catch (Exception e50) {
			processBA.setLastException(e50); //BA.debugLineNum = 224;BA.debugLine="toast.Initialize(LastException.Message,toas";
mostCurrent._toast.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.WARNING);
 };
 //BA.debugLineNum = 227;BA.debugLine="Panel1.AddView(btn_fi(i),1,Panel1.Height-lbl";
mostCurrent._panel1.AddView((android.view.View)(_btn_fi[_i].getObject()),(int) (1),(int) (mostCurrent._panel1.getHeight()-mostCurrent._lbl_fi.getHeight()),mostCurrent._lbl_fi.getWidth(),mostCurrent._lbl_fi.getHeight());
 //BA.debugLineNum = 228;BA.debugLine="btn_fi(i).Visible=False";
_btn_fi[_i].setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 229;BA.debugLine="lbl_price_kol.Tag=myLibrary.CovertPersian2En";
mostCurrent._lbl_price_kol.setTag((Object)((double)(Double.parseDouble(mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,_spin_tedad2[_i].getSelectedItem())))*_price_of));
 //BA.debugLineNum = 230;BA.debugLine="lbl_price_kol.Text=\"قیمت این محصول : \" & tom";
mostCurrent._lbl_price_kol.setText(BA.ObjectToCharSequence("قیمت این محصول : "+mostCurrent._toman._number(BA.NumberToString((double)(Double.parseDouble(mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,_spin_tedad2[_i].getSelectedItem())))*_price_of))+" تومان"));
 //BA.debugLineNum = 231;BA.debugLine="lbl_price_kol.Typeface=Starter.font_body";
mostCurrent._lbl_price_kol.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 233;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 237;BA.debugLine="Dim url_pic As String=map1.Get(\"pic\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 238;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Th";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 239;BA.debugLine="url_pic=dir_root_image_file & map1.Get(\"pic";
_url_pic = _dir_root_image_file+BA.ObjectToString(_map1.Get((Object)("pic")));
 };
 //BA.debugLineNum = 242;BA.debugLine="piccaso.Load(\"http\",url_pic).Resize(img_kala";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).Resize(mostCurrent._img_kala.getWidth(),mostCurrent._img_kala.getHeight()).SkipMemoryCache().Into(mostCurrent._img_kala);
 //BA.debugLineNum = 243;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 244;BA.debugLine="count_item_sabad=count_item_sabad+1";
_count_item_sabad = (int) (_count_item_sabad+1);
 }
};
 //BA.debugLineNum = 247;BA.debugLine="get_kol_price";
_get_kol_price();
 };
 break; }
case 1: {
 //BA.debugLineNum = 255;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 256;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 2: {
 //BA.debugLineNum = 258;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 259;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 260;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 262;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 263;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e79) {
			processBA.setLastException(e79); //BA.debugLineNum = 265;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 266;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 268;BA.debugLine="End Sub";
return "";
}
public static String  _delete_sabad() throws Exception{
 //BA.debugLineNum = 415;BA.debugLine="Sub delete_sabad";
 //BA.debugLineNum = 416;BA.debugLine="File.Delete(myLibrary.rute,Starter.filename_saba";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 418;BA.debugLine="lsv_sabad.Clear";
mostCurrent._lsv_sabad._clear();
 //BA.debugLineNum = 419;BA.debugLine="titlebar_price_kol.Text=\"لیست خرید شما خالی است\"";
mostCurrent._titlebar_price_kol.setText(BA.ObjectToCharSequence("لیست خرید شما خالی است"));
 //BA.debugLineNum = 420;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"لیست خرید ش";
mostCurrent._mytoastmessage._showtoastmessageshow("لیست خرید شما خالی شد",(long) (2),anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 421;BA.debugLine="lbl_badge.Text=\"0\"";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence("0"));
 //BA.debugLineNum = 422;BA.debugLine="btn_buy.Enabled=False";
mostCurrent._btn_buy.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 423;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _get_factor() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst_fact = null;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.EditTextWrapper _id = null;
anywheresoftware.b4a.objects.LabelWrapper _price_of = null;
de.amberhome.objects.appcompat.ACSpinnerWrapper _number_of = null;
 //BA.debugLineNum = 455;BA.debugLine="Sub get_factor() As List";
 //BA.debugLineNum = 456;BA.debugLine="Dim lst_fact As List";
_lst_fact = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 457;BA.debugLine="lst_fact.Initialize";
_lst_fact.Initialize();
 //BA.debugLineNum = 458;BA.debugLine="For i=0 To lsv_sabad.GetSize-1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._lsv_sabad._getsize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 459;BA.debugLine="Dim pnl As Panel=lsv_sabad.GetPanel(i).GetView(0";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl.setObject((android.view.ViewGroup)(mostCurrent._lsv_sabad._getpanel(_i).GetView((int) (0)).getObject()));
 //BA.debugLineNum = 460;BA.debugLine="Dim id As EditText=pnl.GetView(2)";
_id = new anywheresoftware.b4a.objects.EditTextWrapper();
_id.setObject((android.widget.EditText)(_pnl.GetView((int) (2)).getObject()));
 //BA.debugLineNum = 461;BA.debugLine="Dim price_of As Label=pnl.GetView(4)";
_price_of = new anywheresoftware.b4a.objects.LabelWrapper();
_price_of.setObject((android.widget.TextView)(_pnl.GetView((int) (4)).getObject()));
 //BA.debugLineNum = 462;BA.debugLine="Dim number_of As ACSpinner=pnl.GetView(9)";
_number_of = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
_number_of.setObject((de.amberhome.objects.appcompat.ACSpinnerWrapper.ACB4ASpinner)(_pnl.GetView((int) (9)).getObject()));
 //BA.debugLineNum = 463;BA.debugLine="lst_fact.Add(id.Tag & \",\" & price_of.Tag & \",\"";
_lst_fact.Add((Object)(BA.ObjectToString(_id.getTag())+","+BA.ObjectToString(_price_of.getTag())+","+mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,_number_of.getSelectedItem())));
 }
};
 //BA.debugLineNum = 465;BA.debugLine="Return lst_fact";
if (true) return _lst_fact;
 //BA.debugLineNum = 466;BA.debugLine="End Sub";
return null;
}
public static String  _get_kol_price() throws Exception{
int _kol_price = 0;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
int _pr = 0;
 //BA.debugLineNum = 274;BA.debugLine="Sub get_kol_price";
 //BA.debugLineNum = 275;BA.debugLine="Dim kol_price As Int=0";
_kol_price = (int) (0);
 //BA.debugLineNum = 276;BA.debugLine="Try";
try { //BA.debugLineNum = 277;BA.debugLine="For i=0 To lsv_sabad.GetSize-1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._lsv_sabad._getsize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 279;BA.debugLine="Dim pnl As Panel=lsv_sabad.GetPanel(i).GetView(0";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl.setObject((android.view.ViewGroup)(mostCurrent._lsv_sabad._getpanel(_i).GetView((int) (0)).getObject()));
 //BA.debugLineNum = 280;BA.debugLine="Dim btn As Button= pnl.GetView(10)";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn.setObject((android.widget.Button)(_pnl.GetView((int) (10)).getObject()));
 //BA.debugLineNum = 281;BA.debugLine="Dim pr As Int=btn.Tag";
_pr = (int)(BA.ObjectToNumber(_btn.getTag()));
 //BA.debugLineNum = 282;BA.debugLine="kol_price=kol_price + pr";
_kol_price = (int) (_kol_price+_pr);
 }
};
 //BA.debugLineNum = 284;BA.debugLine="titlebar_price_kol.Text=\"مجموع سبد خرید: \" & toma";
mostCurrent._titlebar_price_kol.setText(BA.ObjectToCharSequence("مجموع سبد خرید: "+mostCurrent._toman._number(BA.NumberToString(_kol_price))+" تومان"));
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 288;BA.debugLine="toast.Initialize(LastException.Message,toast.LEN";
mostCurrent._toast.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.ERROR);
 };
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return "";
}
public static String  _get_sabad() throws Exception{
String _values_id = "";
int _z = 0;
 //BA.debugLineNum = 310;BA.debugLine="Sub get_sabad'(code As Int)";
 //BA.debugLineNum = 311;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 312;BA.debugLine="Dim values_id As String";
_values_id = "";
 //BA.debugLineNum = 313;BA.debugLine="For z=0 To list_kala.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._list_kala.getSize()-1);
for (_z = (int) (0) ; (step3 > 0 && _z <= limit3) || (step3 < 0 && _z >= limit3); _z = ((int)(0 + _z + step3)) ) {
 //BA.debugLineNum = 314;BA.debugLine="If z=0 Then  values_id=list_kala.Get(z)";
if (_z==0) { 
_values_id = BA.ObjectToString(mostCurrent._list_kala.Get(_z));};
 //BA.debugLineNum = 315;BA.debugLine="values_id=values_id & \",\" & list_kala.Get(z)";
_values_id = _values_id+","+BA.ObjectToString(mostCurrent._list_kala.Get(_z));
 }
};
 //BA.debugLineNum = 317;BA.debugLine="connector.send_query($\"SELECT `kala` . * ,  `vies";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT `kala` . * ,  `viesue`.`price_off` ,  `viesue`.`code_kala`\n"+"FROM `kala`\n"+"LEFT JOIN `viesue`\n"+"ON `kala`.`id`=`viesue`.`code_kala`\n"+"where `kala`.`id` in ("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_values_id))+")"),"get_sabad",(Object)(""));
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public static String  _get_time() throws Exception{
 //BA.debugLineNum = 324;BA.debugLine="Sub get_time";
 //BA.debugLineNum = 325;BA.debugLine="connector.send_query($\"time\"$,\"get_time\",\"\")";
mostCurrent._connector._send_query(mostCurrent.activityBA,("time"),"get_time",(Object)(""));
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim toman As money";
mostCurrent._toman = new b4a.example.money();
 //BA.debugLineNum = 18;BA.debugLine="Dim lsv_sabad As CustomListView";
mostCurrent._lsv_sabad = new hpksoftware.setak.customlistview();
 //BA.debugLineNum = 19;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 20;BA.debugLine="Dim piccaso As Hitex_Picasso";
mostCurrent._piccaso = new wir.hitex.recycler.Hitex_Picasso();
 //BA.debugLineNum = 21;BA.debugLine="Dim error_image,progress_image As BitmapDrawable";
mostCurrent._error_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
mostCurrent._progress_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 23;BA.debugLine="Private panel_main As Panel";
mostCurrent._panel_main = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 26;BA.debugLine="Dim time As Long";
_time = 0L;
 //BA.debugLineNum = 27;BA.debugLine="Private img_kala As ImageView";
mostCurrent._img_kala = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private lbl_name As Label";
mostCurrent._lbl_name = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lbl_price As Label";
mostCurrent._lbl_price = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private txt_tedad As EditText";
mostCurrent._txt_tedad = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim keyboard As IME";
mostCurrent._keyboard = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 34;BA.debugLine="Dim pos_panel As Int";
_pos_panel = 0;
 //BA.debugLineNum = 36;BA.debugLine="Dim list_kala As List";
mostCurrent._list_kala = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 39;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private lbl_fi As Button";
mostCurrent._lbl_fi = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim timer1 As Timer";
mostCurrent._timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 45;BA.debugLine="Private panel_setting As Panel";
mostCurrent._panel_setting = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Dim code_kala As Int";
_code_kala = 0;
 //BA.debugLineNum = 47;BA.debugLine="Dim apc As AppCompat";
mostCurrent._apc = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 49;BA.debugLine="Private btn_sabad As Button";
mostCurrent._btn_sabad = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private btn_share As Button";
mostCurrent._btn_share = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private btn_search As Button";
mostCurrent._btn_search = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 56;BA.debugLine="Private mLastScrollY As Int = 0";
_mlastscrolly = (int) (0);
 //BA.debugLineNum = 58;BA.debugLine="Private spin_tedad As Spinner";
mostCurrent._spin_tedad = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private lbl_price_kol As Label";
mostCurrent._lbl_price_kol = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Dim count_item_sabad As Int=0";
_count_item_sabad = (int) (0);
 //BA.debugLineNum = 62;BA.debugLine="Private titlebar_price_kol As Label";
mostCurrent._titlebar_price_kol = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Private lbl_badge As Label";
mostCurrent._lbl_badge = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private btn_buy As Button";
mostCurrent._btn_buy = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 10;BA.debugLine="Public server_get_time As String";
_server_get_time = "";
 //BA.debugLineNum = 11;BA.debugLine="Public dir_root_image_file As String";
_dir_root_image_file = "";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _spin_tedad2_itemclick(int _position,Object _value) throws Exception{
de.amberhome.objects.appcompat.ACSpinnerWrapper _spin = null;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl_kol = null;
anywheresoftware.b4a.objects.LabelWrapper _price_of = null;
int _sum = 0;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
 //BA.debugLineNum = 437;BA.debugLine="Sub spin_tedad2_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 438;BA.debugLine="Dim spin As ACSpinner";
_spin = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 439;BA.debugLine="spin=Sender";
_spin.setObject((de.amberhome.objects.appcompat.ACSpinnerWrapper.ACB4ASpinner)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 440;BA.debugLine="Log(\"id=\" & spin.Tag)";
anywheresoftware.b4a.keywords.Common.Log("id="+BA.ObjectToString(_spin.getTag()));
 //BA.debugLineNum = 441;BA.debugLine="Dim pnl As Panel=lsv_sabad.GetPanel(spin.Tag).Get";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
_pnl.setObject((android.view.ViewGroup)(mostCurrent._lsv_sabad._getpanel((int)(BA.ObjectToNumber(_spin.getTag()))).GetView((int) (0)).getObject()));
 //BA.debugLineNum = 443;BA.debugLine="Dim lbl_kol As Label=pnl.GetView(8)";
_lbl_kol = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl_kol.setObject((android.widget.TextView)(_pnl.GetView((int) (8)).getObject()));
 //BA.debugLineNum = 444;BA.debugLine="Dim price_of As Label=pnl.GetView(4)";
_price_of = new anywheresoftware.b4a.objects.LabelWrapper();
_price_of.setObject((android.widget.TextView)(_pnl.GetView((int) (4)).getObject()));
 //BA.debugLineNum = 446;BA.debugLine="Dim sum As Int=myLibrary.CovertPersian2English(sp";
_sum = (int) ((double)(Double.parseDouble(mostCurrent._mylibrary._covertpersian2english(mostCurrent.activityBA,_spin.getSelectedItem())))*(double)(BA.ObjectToNumber(_price_of.getTag())));
 //BA.debugLineNum = 447;BA.debugLine="lbl_kol.Text=\"قیمت کل : \" & toman.number(sum) & \"";
_lbl_kol.setText(BA.ObjectToCharSequence("قیمت کل : "+mostCurrent._toman._number(BA.NumberToString(_sum))+" تومان"));
 //BA.debugLineNum = 448;BA.debugLine="Log(pnl.GetView(2))";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_pnl.GetView((int) (2))));
 //BA.debugLineNum = 450;BA.debugLine="Dim btn As Button=pnl.GetView(10)";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
_btn.setObject((android.widget.Button)(_pnl.GetView((int) (10)).getObject()));
 //BA.debugLineNum = 451;BA.debugLine="btn.Tag=sum";
_btn.setTag((Object)(_sum));
 //BA.debugLineNum = 452;BA.debugLine="get_kol_price";
_get_kol_price();
 //BA.debugLineNum = 453;BA.debugLine="End Sub";
return "";
}
public static String  _sv_sabad_me_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 292;BA.debugLine="Sub sv_sabad_me_ItemClick(Position As Int, Value A";
 //BA.debugLineNum = 293;BA.debugLine="pos_panel=Position";
_pos_panel = _position;
 //BA.debugLineNum = 294;BA.debugLine="code_kala=Value";
_code_kala = (int)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 295;BA.debugLine="panel_setting.Visible=True";
mostCurrent._panel_setting.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 296;BA.debugLine="panel_setting.SetLayoutAnimated(1000,100%x-panel_";
mostCurrent._panel_setting.SetLayoutAnimated((int) (1000),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._panel_setting.getWidth()),mostCurrent._panel_setting.getTop(),mostCurrent._panel_setting.getWidth(),mostCurrent._panel_setting.getHeight());
 //BA.debugLineNum = 297;BA.debugLine="panel_setting.BringToFront";
mostCurrent._panel_setting.BringToFront();
 //BA.debugLineNum = 298;BA.debugLine="timer1.Enabled=True";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 302;BA.debugLine="Sub timer1_Tick";
 //BA.debugLineNum = 303;BA.debugLine="timer1.Enabled=False";
mostCurrent._timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 304;BA.debugLine="panel_setting.SetLayoutAnimated(1000,100%x+panel_";
mostCurrent._panel_setting.SetLayoutAnimated((int) (1000),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)+mostCurrent._panel_setting.getWidth()),mostCurrent._panel_setting.getTop(),mostCurrent._panel_setting.getWidth(),mostCurrent._panel_setting.getHeight());
 //BA.debugLineNum = 305;BA.debugLine="End Sub";
return "";
}
}
