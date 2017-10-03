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

public class info_kala extends Activity implements B4AActivity{
	public static info_kala mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.info_kala");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (info_kala).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.info_kala");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.info_kala", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (info_kala) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (info_kala) Resume **");
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
		return info_kala.class;
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
        BA.LogInfo("** Activity (info_kala) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (info_kala) Resume **");
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
public static int _code_kala = 0;
public static String _kala_txt_info = "";
public static String _server_mysql = "";
public de.amberhome.objects.appcompat.AppCompatBase _apc = null;
public static String _dir_root_image_file_detailes = "";
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public dmax.dialog.Spotsd _progress_spot = null;
public b4a.example.money _toman = null;
public wir.hitex.recycler.Hitex_Picasso _piccaso = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol = null;
public anywheresoftware.b4a.objects.PanelWrapper _header = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_sabad = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_search = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_share = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_back = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_badge = null;
public anywheresoftware.b4a.objects.collections.List _list_kala = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _color_header = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _color_footer = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public static boolean _this_visue_bool = false;
public static int _height = 0;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _error_image = null;
public anywheresoftware.b4a.objects.drawable.BitmapDrawable _progress_image = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font_ico = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font_txt = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_nazar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_fani = null;
public anywheresoftware.b4a.objects.PanelWrapper _slide = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_mark = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public static String _price_kala = "";
public b4a.example.rate_star _rate = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _rate3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_count_nazar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_reg_nazar = null;
public static boolean _bool_login = false;
public static String _name_dat = "";
public anywheresoftware.b4a.objects.ButtonWrapper _btn_share_kala = null;
public static int _val_rate1 = 0;
public static int _val_rate2 = 0;
public static int _val_rate3 = 0;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public static boolean _exist_kala = false;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_exist = null;
public com.porya.pagerbullet.PagerBulletAdapter _pa = null;
public com.porya.pagerbullet.PagerBulletWrapper _pb = null;
public anywheresoftware.b4a.objects.collections.List _mapslider = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _progress_slider = null;
public de.donmanfred.htmltv _html_info = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_info = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_info_inside = null;
public anywheresoftware.b4a.objects.WebViewWrapper _webview_info = null;
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
int _height_ico = 0;
int _center_ver = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _btn_header_color = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _badge_bg = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _bg_panel_info = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _bg_panel_info_inside = null;
 //BA.debugLineNum = 87;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 88;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,info_kala.getObject());
 //BA.debugLineNum = 89;BA.debugLine="mapslider.Initialize";
mostCurrent._mapslider.Initialize();
 //BA.debugLineNum = 90;BA.debugLine="Log(\"code\" & code_kala)";
anywheresoftware.b4a.keywords.Common.Log("code"+BA.NumberToString(_code_kala));
 //BA.debugLineNum = 91;BA.debugLine="Try";
try { //BA.debugLineNum = 92;BA.debugLine="name_dat =Starter.filename_user";
mostCurrent._name_dat = mostCurrent._starter._filename_user;
 //BA.debugLineNum = 93;BA.debugLine="If File.Exists(myLibrary.rute,name_dat)=True The";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._name_dat)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 94;BA.debugLine="bool_login=True";
_bool_login = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 96;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 99;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 102;BA.debugLine="error_image.Initialize(LoadBitmap(File.DirAssets";
mostCurrent._error_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.png").getObject()));
 //BA.debugLineNum = 103;BA.debugLine="progress_image.Initialize(LoadBitmap(File.DirAss";
mostCurrent._progress_image.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prog_img.png").getObject()));
 //BA.debugLineNum = 107;BA.debugLine="font_ico=Typeface.LoadFromAssets(\"pack icon1.ttf";
mostCurrent._font_ico.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("pack icon1.ttf")));
 //BA.debugLineNum = 108;BA.debugLine="font_txt=Starter.font_body";
mostCurrent._font_txt = mostCurrent._starter._font_body;
 //BA.debugLineNum = 111;BA.debugLine="scrol.Initialize2(1000,\"scrol\")";
mostCurrent._scrol.Initialize2(mostCurrent.activityBA,(int) (1000),"scrol");
 //BA.debugLineNum = 112;BA.debugLine="header.Initialize(\"\")";
mostCurrent._header.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 113;BA.debugLine="footer.Initialize(\"add_to_sabad\")";
mostCurrent._footer.Initialize(mostCurrent.activityBA,"add_to_sabad");
 //BA.debugLineNum = 114;BA.debugLine="btn_sabad.Initialize(\"btn_sabad\")";
mostCurrent._btn_sabad.Initialize(mostCurrent.activityBA,"btn_sabad");
 //BA.debugLineNum = 115;BA.debugLine="btn_search.Initialize(\"btn_search\")";
mostCurrent._btn_search.Initialize(mostCurrent.activityBA,"btn_search");
 //BA.debugLineNum = 116;BA.debugLine="btn_share.Initialize(\"btn_share\")";
mostCurrent._btn_share.Initialize(mostCurrent.activityBA,"btn_share");
 //BA.debugLineNum = 117;BA.debugLine="btn_back.Initialize(\"btn_back\")";
mostCurrent._btn_back.Initialize(mostCurrent.activityBA,"btn_back");
 //BA.debugLineNum = 119;BA.debugLine="panel_info.Initialize(\"panel_info\")";
mostCurrent._panel_info.Initialize(mostCurrent.activityBA,"panel_info");
 //BA.debugLineNum = 120;BA.debugLine="panel_info_inside.Initialize(\"panel_info_inside\"";
mostCurrent._panel_info_inside.Initialize(mostCurrent.activityBA,"panel_info_inside");
 //BA.debugLineNum = 121;BA.debugLine="webview_info.Initialize(\"webview_info\")";
mostCurrent._webview_info.Initialize(mostCurrent.activityBA,"webview_info");
 //BA.debugLineNum = 123;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
mostCurrent._color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 124;BA.debugLine="color_footer.Initialize(0xff66BB6A,0)'Colors.ARG";
mostCurrent._color_footer.Initialize((int) (0xff66bb6a),(int) (0));
 //BA.debugLineNum = 127;BA.debugLine="Activity.AddView(header,0,0,100%x,apc.GetMateria";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._header.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._apc.GetMaterialActionBarHeight(mostCurrent.activityBA));
 //BA.debugLineNum = 128;BA.debugLine="Dim height_ico As Int=header.Height";
_height_ico = mostCurrent._header.getHeight();
 //BA.debugLineNum = 129;BA.debugLine="Dim center_ver As Int=(header.Height/2)-(height_";
_center_ver = (int) ((mostCurrent._header.getHeight()/(double)2)-(_height_ico/(double)2));
 //BA.debugLineNum = 130;BA.debugLine="apc.SetElevation(header,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._header.getObject()),(float) (7));
 //BA.debugLineNum = 132;BA.debugLine="Dim btn_header_color As ColorDrawable";
_btn_header_color = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 133;BA.debugLine="btn_header_color.Initialize(Colors.ARGB(0,255,25";
_btn_header_color.Initialize(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (0),(int) (255),(int) (255),(int) (255)),(int) (0));
 //BA.debugLineNum = 135;BA.debugLine="btn_sabad.Background=btn_header_color";
mostCurrent._btn_sabad.setBackground((android.graphics.drawable.Drawable)(_btn_header_color.getObject()));
 //BA.debugLineNum = 136;BA.debugLine="btn_sabad.Typeface=font_ico";
mostCurrent._btn_sabad.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 137;BA.debugLine="btn_sabad.Text=\"*\"";
mostCurrent._btn_sabad.setText(BA.ObjectToCharSequence("*"));
 //BA.debugLineNum = 138;BA.debugLine="btn_sabad.TextSize=15";
mostCurrent._btn_sabad.setTextSize((float) (15));
 //BA.debugLineNum = 139;BA.debugLine="btn_sabad.TextColor=Colors.White";
mostCurrent._btn_sabad.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 140;BA.debugLine="btn_sabad.Gravity=Gravity.CENTER";
mostCurrent._btn_sabad.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 142;BA.debugLine="btn_sabad.SendToBack";
mostCurrent._btn_sabad.SendToBack();
 //BA.debugLineNum = 144;BA.debugLine="Dim badge_bg As ColorDrawable";
_badge_bg = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 145;BA.debugLine="badge_bg.Initialize(Colors.Red,50)";
_badge_bg.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Red,(int) (50));
 //BA.debugLineNum = 147;BA.debugLine="lbl_badge.Initialize(\"lbl_badge\")";
mostCurrent._lbl_badge.Initialize(mostCurrent.activityBA,"lbl_badge");
 //BA.debugLineNum = 148;BA.debugLine="lbl_badge.TextColor=Colors.White";
mostCurrent._lbl_badge.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 149;BA.debugLine="lbl_badge.Background=badge_bg";
mostCurrent._lbl_badge.setBackground((android.graphics.drawable.Drawable)(_badge_bg.getObject()));
 //BA.debugLineNum = 150;BA.debugLine="lbl_badge.TextSize=13";
mostCurrent._lbl_badge.setTextSize((float) (13));
 //BA.debugLineNum = 151;BA.debugLine="lbl_badge.Text=\"\"";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 152;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_s";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 153;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 154;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 155;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 };
 //BA.debugLineNum = 158;BA.debugLine="lbl_badge.Typeface=font_txt";
mostCurrent._lbl_badge.setTypeface((android.graphics.Typeface)(mostCurrent._font_txt.getObject()));
 //BA.debugLineNum = 159;BA.debugLine="lbl_badge.Gravity=Gravity.CENTER";
mostCurrent._lbl_badge.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 160;BA.debugLine="header.AddView(lbl_badge,height_ico/2+4dip,1dip,";
mostCurrent._header.AddView((android.view.View)(mostCurrent._lbl_badge.getObject()),(int) (_height_ico/(double)2+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),(int) (_height_ico/(double)2),(int) (_height_ico/(double)2));
 //BA.debugLineNum = 161;BA.debugLine="header.AddView(btn_sabad,1%x,center_ver,height_i";
mostCurrent._header.AddView((android.view.View)(mostCurrent._btn_sabad.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),_center_ver,_height_ico,_height_ico);
 //BA.debugLineNum = 162;BA.debugLine="btn_sabad.SendToBack";
mostCurrent._btn_sabad.SendToBack();
 //BA.debugLineNum = 163;BA.debugLine="lbl_badge.BringToFront";
mostCurrent._lbl_badge.BringToFront();
 //BA.debugLineNum = 178;BA.debugLine="btn_search.Background=btn_header_color";
mostCurrent._btn_search.setBackground((android.graphics.drawable.Drawable)(_btn_header_color.getObject()));
 //BA.debugLineNum = 179;BA.debugLine="btn_search.Typeface=font_ico";
mostCurrent._btn_search.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 180;BA.debugLine="btn_search.Text=\"-\"";
mostCurrent._btn_search.setText(BA.ObjectToCharSequence("-"));
 //BA.debugLineNum = 181;BA.debugLine="btn_search.TextSize=13";
mostCurrent._btn_search.setTextSize((float) (13));
 //BA.debugLineNum = 182;BA.debugLine="btn_search.TextColor=Colors.White";
mostCurrent._btn_search.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 183;BA.debugLine="btn_search.Gravity=Gravity.CENTER";
mostCurrent._btn_search.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 185;BA.debugLine="btn_search.Visible=False";
mostCurrent._btn_search.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="btn_back.Background=btn_header_color";
mostCurrent._btn_back.setBackground((android.graphics.drawable.Drawable)(_btn_header_color.getObject()));
 //BA.debugLineNum = 189;BA.debugLine="btn_back.Typeface=font_ico";
mostCurrent._btn_back.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 190;BA.debugLine="btn_back.Text=\"/\"";
mostCurrent._btn_back.setText(BA.ObjectToCharSequence("/"));
 //BA.debugLineNum = 191;BA.debugLine="btn_back.TextSize=15";
mostCurrent._btn_back.setTextSize((float) (15));
 //BA.debugLineNum = 192;BA.debugLine="btn_back.TextColor=Colors.White";
mostCurrent._btn_back.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 193;BA.debugLine="btn_back.Gravity=Gravity.CENTER";
mostCurrent._btn_back.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 194;BA.debugLine="header.AddView(btn_back,(header.Width - height_i";
mostCurrent._header.AddView((android.view.View)(mostCurrent._btn_back.getObject()),(int) ((mostCurrent._header.getWidth()-_height_ico)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),_center_ver,_height_ico,_height_ico);
 //BA.debugLineNum = 198;BA.debugLine="Activity.AddView(footer,0,100%y-50dip,100%x,50di";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._footer.getObject()),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 199;BA.debugLine="Activity.AddView(scrol,0,header.Height,100%x,100";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._scrol.getObject()),(int) (0),mostCurrent._header.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 202;BA.debugLine="Activity.AddView(panel_info,0,header.Height,100%";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._panel_info.getObject()),(int) (0),mostCurrent._header.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._header.getHeight()));
 //BA.debugLineNum = 203;BA.debugLine="Dim bg_panel_info As ColorDrawable";
_bg_panel_info = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 204;BA.debugLine="bg_panel_info.Initialize(0x6E000000,0)";
_bg_panel_info.Initialize((int) (0x6e000000),(int) (0));
 //BA.debugLineNum = 205;BA.debugLine="panel_info.Background=bg_panel_info";
mostCurrent._panel_info.setBackground((android.graphics.drawable.Drawable)(_bg_panel_info.getObject()));
 //BA.debugLineNum = 206;BA.debugLine="panel_info.Visible=False";
mostCurrent._panel_info.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 208;BA.debugLine="panel_info.AddView(panel_info_inside,3%x,3%y,pan";
mostCurrent._panel_info.AddView((android.view.View)(mostCurrent._panel_info_inside.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (3),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (3),mostCurrent.activityBA),(int) (mostCurrent._panel_info.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (6),mostCurrent.activityBA)),(int) (mostCurrent._panel_info.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (6),mostCurrent.activityBA)));
 //BA.debugLineNum = 209;BA.debugLine="Dim bg_panel_info_inside As ColorDrawable";
_bg_panel_info_inside = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 210;BA.debugLine="bg_panel_info_inside.Initialize(Colors.White,20)";
_bg_panel_info_inside.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,(int) (20));
 //BA.debugLineNum = 211;BA.debugLine="panel_info_inside.Background=bg_panel_info_insid";
mostCurrent._panel_info_inside.setBackground((android.graphics.drawable.Drawable)(_bg_panel_info_inside.getObject()));
 //BA.debugLineNum = 212;BA.debugLine="apc.SetElevation(panel_info_inside,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._panel_info_inside.getObject()),(float) (7));
 //BA.debugLineNum = 213;BA.debugLine="panel_info_inside.AddView(webview_info,1%x,1%y,p";
mostCurrent._panel_info_inside.AddView((android.view.View)(mostCurrent._webview_info.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (1),mostCurrent.activityBA),(int) (mostCurrent._panel_info_inside.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (2),mostCurrent.activityBA)),(int) (mostCurrent._panel_info_inside.getHeight()-anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (2),mostCurrent.activityBA)));
 //BA.debugLineNum = 214;BA.debugLine="webview_info.ZoomEnabled=False";
mostCurrent._webview_info.setZoomEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 218;BA.debugLine="header.Background=color_header";
mostCurrent._header.setBackground((android.graphics.drawable.Drawable)(mostCurrent._color_header.getObject()));
 //BA.debugLineNum = 219;BA.debugLine="footer.Background=color_footer";
mostCurrent._footer.setBackground((android.graphics.drawable.Drawable)(mostCurrent._color_footer.getObject()));
 //BA.debugLineNum = 220;BA.debugLine="header.BringToFront";
mostCurrent._header.BringToFront();
 //BA.debugLineNum = 221;BA.debugLine="btn_sabad.BringToFront";
mostCurrent._btn_sabad.BringToFront();
 //BA.debugLineNum = 222;BA.debugLine="footer.BringToFront";
mostCurrent._footer.BringToFront();
 //BA.debugLineNum = 223;BA.debugLine="scrol.Panel.LoadLayout(\"info_kala\")";
mostCurrent._scrol.getPanel().LoadLayout("info_kala",mostCurrent.activityBA);
 //BA.debugLineNum = 224;BA.debugLine="lbl_name_kala.Typeface=Starter.font_body";
mostCurrent._lbl_name_kala.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 226;BA.debugLine="panel_info.BringToFront";
mostCurrent._panel_info.BringToFront();
 //BA.debugLineNum = 228;BA.debugLine="Dim apc As AppCompat";
mostCurrent._apc = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 229;BA.debugLine="apc.SetElevation(Panel2,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._panel2.getObject()),(float) (7));
 //BA.debugLineNum = 230;BA.debugLine="apc.SetElevation(Panel3,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._panel3.getObject()),(float) (7));
 //BA.debugLineNum = 231;BA.debugLine="apc.SetElevation(Button1,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._button1.getObject()),(float) (7));
 //BA.debugLineNum = 232;BA.debugLine="apc.SetElevation(btn_fani,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._btn_fani.getObject()),(float) (7));
 //BA.debugLineNum = 233;BA.debugLine="apc.SetElevation(btn_nazar,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._btn_nazar.getObject()),(float) (7));
 //BA.debugLineNum = 234;BA.debugLine="apc.SetElevation(btn_reg_nazar,7)";
mostCurrent._apc.SetElevation((android.view.View)(mostCurrent._btn_reg_nazar.getObject()),(float) (7));
 //BA.debugLineNum = 237;BA.debugLine="If bool_login=True Then btn_reg_nazar.Visible=Tr";
if (_bool_login==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._btn_reg_nazar.setVisible(anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 238;BA.debugLine="btn_share_kala.Typeface=font_ico";
mostCurrent._btn_share_kala.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 240;BA.debugLine="scrol.Panel.Height=Panel3.Top + Panel3.Height +";
mostCurrent._scrol.getPanel().setHeight((int) (mostCurrent._panel3.getTop()+mostCurrent._panel3.getHeight()+mostCurrent._footer.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 244;BA.debugLine="footer.Text=\"(  |  \" & \"اضافه به سبد خرید\"";
mostCurrent._footer.setText(BA.ObjectToCharSequence("(  |  "+"اضافه به سبد خرید"));
 //BA.debugLineNum = 245;BA.debugLine="footer.Gravity=Gravity.CENTER";
mostCurrent._footer.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 246;BA.debugLine="footer.TextColor=Colors.White";
mostCurrent._footer.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 247;BA.debugLine="footer.TextSize=17";
mostCurrent._footer.setTextSize((float) (17));
 //BA.debugLineNum = 248;BA.debugLine="footer.Typeface=font_ico";
mostCurrent._footer.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 251;BA.debugLine="btn_nazar.Typeface=font_ico";
mostCurrent._btn_nazar.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 252;BA.debugLine="btn_fani.Typeface=font_ico";
mostCurrent._btn_fani.setTypeface((android.graphics.Typeface)(mostCurrent._font_ico.getObject()));
 //BA.debugLineNum = 253;BA.debugLine="Label4.Typeface=font_txt";
mostCurrent._label4.setTypeface((android.graphics.Typeface)(mostCurrent._font_txt.getObject()));
 //BA.debugLineNum = 254;BA.debugLine="Button1.Typeface=font_txt";
mostCurrent._button1.setTypeface((android.graphics.Typeface)(mostCurrent._font_txt.getObject()));
 //BA.debugLineNum = 259;BA.debugLine="Label1.Typeface=Starter.font_body";
mostCurrent._label1.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 260;BA.debugLine="Label5.Typeface=Starter.font_body";
mostCurrent._label5.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 261;BA.debugLine="Label6.Typeface=Starter.font_body";
mostCurrent._label6.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 262;BA.debugLine="lbl_count_nazar.Typeface=Starter.font_body";
mostCurrent._lbl_count_nazar.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 //BA.debugLineNum = 265;BA.debugLine="get_kala(code_kala)";
_get_kala(_code_kala);
 } 
       catch (Exception e119) {
			processBA.setLastException(e119); //BA.debugLineNum = 269;BA.debugLine="toast.Initialize(LastException.Message,toast.LEN";
mostCurrent._toast.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.ERROR);
 };
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 623;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 624;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 625;BA.debugLine="If panel_info.Visible=True Then";
if (mostCurrent._panel_info.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 626;BA.debugLine="panel_info.Visible=False";
mostCurrent._panel_info.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 628;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 629;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 };
 //BA.debugLineNum = 632;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 634;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 286;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 288;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 290;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 291;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,info_kala.getObject());
 //BA.debugLineNum = 292;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_sa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 293;BA.debugLine="list_kala.Initialize";
mostCurrent._list_kala.Initialize();
 //BA.debugLineNum = 294;BA.debugLine="list_kala=File.ReadList(myLibrary.rute,Starter.f";
mostCurrent._list_kala = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 295;BA.debugLine="lbl_badge.Text=list_kala.Size";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()));
 //BA.debugLineNum = 296;BA.debugLine="If list_kala.Size<=0 Then lbl_badge.Visible=Fals";
if (mostCurrent._list_kala.getSize()<=0) { 
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);};
 }else {
 //BA.debugLineNum = 298;BA.debugLine="lbl_badge.Visible=False";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return "";
}
public static String  _add_to_sabad_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _list_kala2 = null;
boolean _bol_duplicate_kala = false;
int _i = 0;
 //BA.debugLineNum = 512;BA.debugLine="Sub add_to_sabad_Click()";
 //BA.debugLineNum = 513;BA.debugLine="If exist_kala=True Then";
if (_exist_kala==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 514;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,info_kala.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff0ba41b),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 515;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_s";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 516;BA.debugLine="Dim list_kala2 As List";
_list_kala2 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 517;BA.debugLine="Dim bol_duplicate_kala As Boolean=False";
_bol_duplicate_kala = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 518;BA.debugLine="list_kala2.Initialize";
_list_kala2.Initialize();
 //BA.debugLineNum = 519;BA.debugLine="list_kala2=File.ReadList(myLibrary.rute,Starter";
_list_kala2 = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad);
 //BA.debugLineNum = 520;BA.debugLine="For i=0 To list_kala2.Size-1";
{
final int step8 = 1;
final int limit8 = (int) (_list_kala2.getSize()-1);
for (_i = (int) (0) ; (step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8); _i = ((int)(0 + _i + step8)) ) {
 //BA.debugLineNum = 521;BA.debugLine="If code_kala=list_kala2.Get(i) Then";
if (_code_kala==(double)(BA.ObjectToNumber(_list_kala2.Get(_i)))) { 
 //BA.debugLineNum = 522;BA.debugLine="bol_duplicate_kala=True";
_bol_duplicate_kala = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 523;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 526;BA.debugLine="If bol_duplicate_kala=False Then";
if (_bol_duplicate_kala==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 527;BA.debugLine="list_kala2.Add(code_kala)";
_list_kala2.Add((Object)(_code_kala));
 //BA.debugLineNum = 528;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"کالای مورد";
mostCurrent._mytoastmessage._showtoastmessageshow("کالای مورد نظر به سبد خرید اضاف شد",(long) (4),anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 529;BA.debugLine="lbl_badge.Text=list_kala.Size +1";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence(mostCurrent._list_kala.getSize()+1));
 }else {
 //BA.debugLineNum = 532;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"این کالا د";
mostCurrent._mytoastmessage._showtoastmessageshow("این کالا در سبد خرید شما موجود است",(long) (4),anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 534;BA.debugLine="File.WriteList(myLibrary.rute,Starter.filename_";
anywheresoftware.b4a.keywords.Common.File.WriteList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad,_list_kala2);
 }else {
 //BA.debugLineNum = 537;BA.debugLine="Dim list_kala2 As List";
_list_kala2 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 538;BA.debugLine="list_kala2.Initialize";
_list_kala2.Initialize();
 //BA.debugLineNum = 539;BA.debugLine="list_kala2.Add(code_kala)";
_list_kala2.Add((Object)(_code_kala));
 //BA.debugLineNum = 540;BA.debugLine="File.WriteList(myLibrary.rute,Starter.filename_";
anywheresoftware.b4a.keywords.Common.File.WriteList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._starter._filename_sabad,_list_kala2);
 //BA.debugLineNum = 541;BA.debugLine="lbl_badge.Visible=True";
mostCurrent._lbl_badge.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 542;BA.debugLine="lbl_badge.Text=\"1\"";
mostCurrent._lbl_badge.setText(BA.ObjectToCharSequence("1"));
 //BA.debugLineNum = 544;BA.debugLine="mytoastMessage.ShowToastMessageShow(\"کالای مورد";
mostCurrent._mytoastmessage._showtoastmessageshow("کالای مورد نظر به سبد خرید اضاف شد",(long) (4),anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
 //BA.debugLineNum = 547;BA.debugLine="toast.Initialize(\"متاسفانه کالای مورد نظر به اتم";
mostCurrent._toast.Initialize(mostCurrent.activityBA,"متاسفانه کالای مورد نظر به اتمام رسیده",mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.ERROR);
 };
 //BA.debugLineNum = 550;BA.debugLine="Log(\"kala: \" &  list_kala2)";
anywheresoftware.b4a.keywords.Common.Log("kala: "+BA.ObjectToString(_list_kala2));
 //BA.debugLineNum = 551;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 579;BA.debugLine="Sub btn_back_Click()";
 //BA.debugLineNum = 580;BA.debugLine="If panel_info.Visible=True Then";
if (mostCurrent._panel_info.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 581;BA.debugLine="panel_info.Visible=False";
mostCurrent._panel_info.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 583;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 584;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 };
 //BA.debugLineNum = 586;BA.debugLine="End Sub";
return "";
}
public static String  _btn_fani_click() throws Exception{
 //BA.debugLineNum = 641;BA.debugLine="Sub btn_fani_Click";
 //BA.debugLineNum = 642;BA.debugLine="show_nazar.bool_propertis=True";
mostCurrent._show_nazar._bool_propertis = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 643;BA.debugLine="show_nazar.code_kala=code_kala";
mostCurrent._show_nazar._code_kala = _code_kala;
 //BA.debugLineNum = 644;BA.debugLine="StartActivity(show_nazar)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_nazar.getObject()));
 //BA.debugLineNum = 645;BA.debugLine="End Sub";
return "";
}
public static String  _btn_nazar_click() throws Exception{
 //BA.debugLineNum = 636;BA.debugLine="Sub btn_nazar_Click";
 //BA.debugLineNum = 637;BA.debugLine="show_nazar.code_kala=code_kala";
mostCurrent._show_nazar._code_kala = _code_kala;
 //BA.debugLineNum = 638;BA.debugLine="StartActivity(show_nazar)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._show_nazar.getObject()));
 //BA.debugLineNum = 639;BA.debugLine="End Sub";
return "";
}
public static String  _btn_reg_nazar_click() throws Exception{
 //BA.debugLineNum = 607;BA.debugLine="Sub btn_reg_nazar_Click";
 //BA.debugLineNum = 608;BA.debugLine="nazar.code_kala=code_kala";
mostCurrent._nazar._code_kala = _code_kala;
 //BA.debugLineNum = 609;BA.debugLine="StartActivity(nazar)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._nazar.getObject()));
 //BA.debugLineNum = 610;BA.debugLine="End Sub";
return "";
}
public static String  _btn_sabad_click() throws Exception{
 //BA.debugLineNum = 553;BA.debugLine="Sub btn_sabad_Click()";
 //BA.debugLineNum = 554;BA.debugLine="StartActivity(sabad)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._sabad.getObject()));
 //BA.debugLineNum = 555;BA.debugLine="End Sub";
return "";
}
public static String  _btn_search_click() throws Exception{
 //BA.debugLineNum = 568;BA.debugLine="Sub btn_search_Click";
 //BA.debugLineNum = 569;BA.debugLine="RequestSearchBar";
_requestsearchbar();
 //BA.debugLineNum = 570;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_click() throws Exception{
anywheresoftware.b4a.ariagplib.ARIAlib _share = null;
 //BA.debugLineNum = 557;BA.debugLine="Sub btn_share_Click()";
 //BA.debugLineNum = 558;BA.debugLine="Dim share As AriaLib";
_share = new anywheresoftware.b4a.ariagplib.ARIAlib();
 //BA.debugLineNum = 559;BA.debugLine="StartActivity(share.ShareApplication(Application.";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share.ShareApplication(anywheresoftware.b4a.keywords.Common.Application.getPackageName(),anywheresoftware.b4a.keywords.Common.Application.getLabelName())));
 //BA.debugLineNum = 560;BA.debugLine="End Sub";
return "";
}
public static String  _btn_share_kala_click() throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _share = null;
String _mytext = "";
 //BA.debugLineNum = 612;BA.debugLine="Sub btn_share_kala_Click";
 //BA.debugLineNum = 613;BA.debugLine="Dim share As Intent";
_share = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 614;BA.debugLine="If this_visue_bool=True Then price_kala=toman.num";
if (_this_visue_bool==anywheresoftware.b4a.keywords.Common.True) { 
mostCurrent._price_kala = mostCurrent._toman._number(mostCurrent._price_kala)+" تومان"+anywheresoftware.b4a.keywords.Common.CRLF+"🏃🏻"+"پیشنهاد ویژه به مدت محدود"+"🎯";};
 //BA.debugLineNum = 615;BA.debugLine="Dim mytext As String=lbl_name_kala.Text &  CRLF &";
_mytext = mostCurrent._lbl_name_kala.getText()+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"💶 "+mostCurrent._price_kala+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"برای ثبت سفارش به لینک زیر مراجعه کنید 👇🏻."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"🆔 "+mostCurrent._starter._root_site+"/product/detail/"+BA.NumberToString(_code_kala)+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"لینک دانلود فروشگاه : "+anywheresoftware.b4a.keywords.Common.CRLF+mostCurrent._starter._root_site+"/android/"+mostCurrent._starter._apk_name_for_dowanload;
 //BA.debugLineNum = 616;BA.debugLine="share.Initialize(share.ACTION_SEND,\"\")";
_share.Initialize(_share.ACTION_SEND,"");
 //BA.debugLineNum = 617;BA.debugLine="share.SetType(\"text/plain\")";
_share.SetType("text/plain");
 //BA.debugLineNum = 618;BA.debugLine="share.PutExtra(\"android.intent.extra.TEXT\", mytex";
_share.PutExtra("android.intent.extra.TEXT",(Object)(_mytext));
 //BA.debugLineNum = 619;BA.debugLine="share.WrapAsIntentChooser(\"اشتراک با\")";
_share.WrapAsIntentChooser("اشتراک با");
 //BA.debugLineNum = 620;BA.debugLine="StartActivity(share)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(_share.getObject()));
 //BA.debugLineNum = 621;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
String _txt_info = "";
 //BA.debugLineNum = 481;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 499;BA.debugLine="panel_info.Visible=True";
mostCurrent._panel_info.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 500;BA.debugLine="Dim txt_info As String";
_txt_info = "";
 //BA.debugLineNum = 501;BA.debugLine="txt_info=kala_txt_info.Replace(\"width:auto\",\"widt";
_txt_info = _kala_txt_info.replace("width:auto","width:100%");
 //BA.debugLineNum = 502;BA.debugLine="txt_info= txt_info.Replace(\"/>\",$\"class=\"img-resp";
_txt_info = _txt_info.replace("/>",("class=\"img-responsive\" />"));
 //BA.debugLineNum = 503;BA.debugLine="webview_info.LoadHtml(File.ReadString(File.DirAss";
mostCurrent._webview_info.LoadHtml(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"index1.html")+_txt_info+("</div></div></div><script src=\"js/bootstrap.min.js\"></script></body></html>"));
 //BA.debugLineNum = 505;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
String _pic = "";
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
int _number_kala = 0;
String _url_pic = "";
anywheresoftware.b4a.objects.StringUtils _s = null;
hpk.software.label.line_height _line = null;
int _stus_viesue = 0;
String _price_base = "";
int _temp_1 = 0;
int _temp_2 = 0;
int _temp_3 = 0;
int _w = 0;
 //BA.debugLineNum = 303;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 304;BA.debugLine="Try";
try { //BA.debugLineNum = 305;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_kala"),(Object)("get_viesue"),(Object)("get_image_kala"),(Object)("get_rate"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 307;BA.debugLine="Dim pic As String";
_pic = "";
 //BA.debugLineNum = 308;BA.debugLine="Log(\"****\" & records)";
anywheresoftware.b4a.keywords.Common.Log("****"+BA.ObjectToString(_records));
 //BA.debugLineNum = 309;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 311;BA.debugLine="For i=0 To records.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 312;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 314;BA.debugLine="pic=map1.Get(\"pic\")";
_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 316;BA.debugLine="Dim number_kala As Int=map1.Get(\"number\")";
_number_kala = (int)(BA.ObjectToNumber(_map1.Get((Object)("number"))));
 //BA.debugLineNum = 317;BA.debugLine="If number_kala > 0 Then";
if (_number_kala>0) { 
 //BA.debugLineNum = 318;BA.debugLine="exist_kala=True";
_exist_kala = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 319;BA.debugLine="img_exist.Bitmap=LoadBitmap(File.DirAssets,";
mostCurrent._img_exist.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"available.png").getObject()));
 }else {
 //BA.debugLineNum = 321;BA.debugLine="exist_kala=False";
_exist_kala = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 322;BA.debugLine="img_exist.Bitmap=LoadBitmap(File.DirAssets,";
mostCurrent._img_exist.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"unavailabl.png").getObject()));
 };
 //BA.debugLineNum = 326;BA.debugLine="Dim url_pic As String=pic";
_url_pic = _pic;
 //BA.debugLineNum = 327;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Th";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 328;BA.debugLine="url_pic=Starter.dir_root_image_file_thumnai";
_url_pic = mostCurrent._starter._dir_root_image_file_thumnail+_pic;
 };
 //BA.debugLineNum = 331;BA.debugLine="mapslider.Add(url_pic)";
mostCurrent._mapslider.Add((Object)(_url_pic));
 }
};
 //BA.debugLineNum = 335;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 339;BA.debugLine="lbl_name_kala.Text=map1.Get(\"name_kala\")";
mostCurrent._lbl_name_kala.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name_kala"))));
 //BA.debugLineNum = 340;BA.debugLine="label3.Text=map1.Get(\"garanti\")";
mostCurrent._label3.setText(BA.ObjectToCharSequence(_map1.Get((Object)("garanti"))));
 //BA.debugLineNum = 341;BA.debugLine="kala_txt_info=map1.Get(\"info\")";
_kala_txt_info = BA.ObjectToString(_map1.Get((Object)("info")));
 //BA.debugLineNum = 342;BA.debugLine="html_info.setHtmlFromString(kala_txt_info,Fal";
mostCurrent._html_info.setHtmlFromString(_kala_txt_info,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 344;BA.debugLine="Dim s As StringUtils";
_s = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 345;BA.debugLine="Dim line As line_height";
_line = new hpk.software.label.line_height();
 //BA.debugLineNum = 346;BA.debugLine="If kala_txt_info.Length < 180 Then";
if (_kala_txt_info.length()<180) { 
 //BA.debugLineNum = 347;BA.debugLine="Button1.Visible=False";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 348;BA.debugLine="Label4.Text=kala_txt_info";
mostCurrent._label4.setText(BA.ObjectToCharSequence(_kala_txt_info));
 //BA.debugLineNum = 351;BA.debugLine="Label4.Height=myLibrary.art(Label4.Text,Labe";
mostCurrent._label4.setHeight((int) (mostCurrent._mylibrary._art(mostCurrent.activityBA,mostCurrent._label4.getText(),mostCurrent._label4,mostCurrent._font_txt,(int) (14),(int) (1))+mostCurrent._button1.getHeight()));
 }else {
 //BA.debugLineNum = 353;BA.debugLine="Button1.Visible=True";
mostCurrent._button1.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 354;BA.debugLine="Label4.Text=kala_txt_info.SubString2(0,180)";
mostCurrent._label4.setText(BA.ObjectToCharSequence(_kala_txt_info.substring((int) (0),(int) (180))+"..."));
 //BA.debugLineNum = 355;BA.debugLine="Label4.Height=myLibrary.art(Label4.Text,Labe";
mostCurrent._label4.setHeight((int) (mostCurrent._mylibrary._art(mostCurrent.activityBA,mostCurrent._label4.getText(),mostCurrent._label4,mostCurrent._font_txt,(int) (12),(int) (1.5))));
 };
 //BA.debugLineNum = 360;BA.debugLine="height=myLibrary.art(kala_txt_info,Label4,fon";
_height = (int) (mostCurrent._mylibrary._art(mostCurrent.activityBA,_kala_txt_info,mostCurrent._label4,mostCurrent._font_txt,(int) (12),(int) (2))+mostCurrent._button1.getHeight()/(double)2);
 //BA.debugLineNum = 364;BA.debugLine="Dim stus_viesue As Int=map1.Get(\"stut_viesue\"";
_stus_viesue = (int)(BA.ObjectToNumber(_map1.Get((Object)("stut_viesue"))));
 //BA.debugLineNum = 366;BA.debugLine="If stus_viesue=1 Then";
if (_stus_viesue==1) { 
 //BA.debugLineNum = 367;BA.debugLine="img_mark.Visible=True";
mostCurrent._img_mark.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 368;BA.debugLine="price_kala=toman.number(map1.Get(\"price\")) &";
mostCurrent._price_kala = mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان";
 //BA.debugLineNum = 369;BA.debugLine="get_viesue(code_kala)";
_get_viesue(_code_kala);
 }else {
 //BA.debugLineNum = 371;BA.debugLine="footer.Text=footer.Text & \"  \" & toman.numbe";
mostCurrent._footer.setText(BA.ObjectToCharSequence(mostCurrent._footer.getText()+"  "+mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان"));
 //BA.debugLineNum = 372;BA.debugLine="price_kala=toman.number(map1.Get(\"price\")) &";
mostCurrent._price_kala = mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان";
 //BA.debugLineNum = 374;BA.debugLine="get_image_kala(code_kala)";
_get_image_kala(_code_kala);
 };
 };
 break; }
case 1: {
 //BA.debugLineNum = 381;BA.debugLine="Dim price_base As String=price_kala";
_price_base = mostCurrent._price_kala;
 //BA.debugLineNum = 382;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 383;BA.debugLine="For i=0 To records.Size-1";
{
final int step55 = 1;
final int limit55 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step55 > 0 && _i <= limit55) || (step55 < 0 && _i >= limit55); _i = ((int)(0 + _i + step55)) ) {
 //BA.debugLineNum = 384;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 385;BA.debugLine="price_kala=map1.Get(\"price_off\")";
mostCurrent._price_kala = BA.ObjectToString(_map1.Get((Object)("price_off")));
 }
};
 //BA.debugLineNum = 387;BA.debugLine="this_visue_bool=True";
_this_visue_bool = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 388;BA.debugLine="footer.Text=\"(  |  \" & \"  \" & price_base  & \"";
mostCurrent._footer.setText(BA.ObjectToCharSequence("(  |  "+"  "+_price_base+" با تخفیف "+mostCurrent._toman._number(mostCurrent._price_kala)+" تومان"));
 };
 //BA.debugLineNum = 391;BA.debugLine="get_image_kala(code_kala)";
_get_image_kala(_code_kala);
 break; }
case 2: {
 //BA.debugLineNum = 396;BA.debugLine="Log(\"*****\" & records)";
anywheresoftware.b4a.keywords.Common.Log("*****"+BA.ObjectToString(_records));
 //BA.debugLineNum = 397;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 398;BA.debugLine="For i=0 To records.Size-1";
{
final int step66 = 1;
final int limit66 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step66 > 0 && _i <= limit66) || (step66 < 0 && _i >= limit66); _i = ((int)(0 + _i + step66)) ) {
 //BA.debugLineNum = 399;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 400;BA.debugLine="pic=map1.Get(\"pic_kala\")";
_pic = BA.ObjectToString(_map1.Get((Object)("pic_kala")));
 //BA.debugLineNum = 402;BA.debugLine="Dim url_pic As String=pic";
_url_pic = _pic;
 //BA.debugLineNum = 403;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False T";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 404;BA.debugLine="url_pic=Starter.dir_root_image_file_detail";
_url_pic = mostCurrent._starter._dir_root_image_file_detailes_main+_pic;
 };
 //BA.debugLineNum = 407;BA.debugLine="mapslider.Add(	url_pic	)";
mostCurrent._mapslider.Add((Object)(_url_pic));
 }
};
 };
 //BA.debugLineNum = 411;BA.debugLine="initialize_slideshow(mapslider)";
_initialize_slideshow(mostCurrent._mapslider);
 //BA.debugLineNum = 414;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 3: {
 //BA.debugLineNum = 416;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 417;BA.debugLine="For i=0 To records.Size-1";
{
final int step80 = 1;
final int limit80 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step80 > 0 && _i <= limit80) || (step80 < 0 && _i >= limit80); _i = ((int)(0 + _i + step80)) ) {
 //BA.debugLineNum = 418;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 420;BA.debugLine="Dim temp_1 As Int=map1.Get(\"rate1\")";
_temp_1 = (int)(BA.ObjectToNumber(_map1.Get((Object)("rate1"))));
 //BA.debugLineNum = 421;BA.debugLine="val_rate1=val_rate1 + temp_1";
_val_rate1 = (int) (_val_rate1+_temp_1);
 //BA.debugLineNum = 422;BA.debugLine="Dim temp_2 As Int=map1.Get(\"rate2\")";
_temp_2 = (int)(BA.ObjectToNumber(_map1.Get((Object)("rate2"))));
 //BA.debugLineNum = 423;BA.debugLine="val_rate2=val_rate2 + temp_2";
_val_rate2 = (int) (_val_rate2+_temp_2);
 //BA.debugLineNum = 424;BA.debugLine="Dim temp_3 As Int=map1.Get(\"rate3\")";
_temp_3 = (int)(BA.ObjectToNumber(_map1.Get((Object)("rate3"))));
 //BA.debugLineNum = 425;BA.debugLine="val_rate3=val_rate3 + temp_3";
_val_rate3 = (int) (_val_rate3+_temp_3);
 }
};
 };
 //BA.debugLineNum = 429;BA.debugLine="Dim w As Int=(rate1.Width/5)-2dip";
_w = (int) ((mostCurrent._rate1.getWidth()/(double)5)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)));
 //BA.debugLineNum = 431;BA.debugLine="rate.Initialize(rate1,Me,\"\",w,w,LoadBitmap(Fil";
mostCurrent._rate._initialize(mostCurrent.activityBA,mostCurrent._rate1,info_kala.getObject(),"",_w,_w,anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star1.png"),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star0.png"));
 //BA.debugLineNum = 432;BA.debugLine="rate.show_rate(Round(val_rate1/records.Size))";
mostCurrent._rate._show_rate((int) (anywheresoftware.b4a.keywords.Common.Round(_val_rate1/(double)_records.getSize())));
 //BA.debugLineNum = 434;BA.debugLine="rate.Initialize(rate2,Me,\"\",w,w,LoadBitmap(Fil";
mostCurrent._rate._initialize(mostCurrent.activityBA,mostCurrent._rate2,info_kala.getObject(),"",_w,_w,anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star1.png"),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star0.png"));
 //BA.debugLineNum = 435;BA.debugLine="rate.show_rate(Round(val_rate2/records.Size))";
mostCurrent._rate._show_rate((int) (anywheresoftware.b4a.keywords.Common.Round(_val_rate2/(double)_records.getSize())));
 //BA.debugLineNum = 437;BA.debugLine="rate.Initialize(rate3,Me,\"\",w,w,LoadBitmap(Fil";
mostCurrent._rate._initialize(mostCurrent.activityBA,mostCurrent._rate3,info_kala.getObject(),"",_w,_w,anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star1.png"),anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"star0.png"));
 //BA.debugLineNum = 438;BA.debugLine="rate.show_rate(Round(val_rate3/records.Size))";
mostCurrent._rate._show_rate((int) (anywheresoftware.b4a.keywords.Common.Round(_val_rate3/(double)_records.getSize())));
 break; }
case 4: {
 //BA.debugLineNum = 442;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 443;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 5: {
 //BA.debugLineNum = 445;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 446;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 447;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 449;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 450;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e108) {
			processBA.setLastException(e108); //BA.debugLineNum = 452;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 453;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 455;BA.debugLine="End Sub";
return "";
}
public static String  _font_label() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _view1 = null;
anywheresoftware.b4a.objects.LabelWrapper _label_font = null;
 //BA.debugLineNum = 274;BA.debugLine="Sub font_label";
 //BA.debugLineNum = 275;BA.debugLine="For Each view1 As View In Activity.GetAllViewsRec";
_view1 = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group1 = mostCurrent._activity.GetAllViewsRecursive();
final int groupLen1 = group1.getSize();
for (int index1 = 0;index1 < groupLen1 ;index1++){
_view1.setObject((android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 276;BA.debugLine="If view1 Is Label Then";
if (_view1.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 277;BA.debugLine="Dim label_font As Label";
_label_font = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 278;BA.debugLine="label_font=view1";
_label_font.setObject((android.widget.TextView)(_view1.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="label_font.Typeface=Starter.font_body";
_label_font.setTypeface((android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()));
 };
 }
;
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _get_image_kala(int _code) throws Exception{
 //BA.debugLineNum = 476;BA.debugLine="Sub get_image_kala(code As Int)";
 //BA.debugLineNum = 477;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 478;BA.debugLine="connector.send_query($\"SELECT * FROM `image_kala`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * FROM `image_kala` WHERE `code_kala`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+""),"get_image_kala",(Object)(""));
 //BA.debugLineNum = 479;BA.debugLine="End Sub";
return "";
}
public static String  _get_kala(int _code) throws Exception{
 //BA.debugLineNum = 470;BA.debugLine="Sub get_kala(code As Int)";
 //BA.debugLineNum = 471;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 473;BA.debugLine="connector.send_query($\"SELECT DISTINCT * FROM `ka";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT DISTINCT * FROM `kala` where `id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+""),"get_kala",(Object)(""));
 //BA.debugLineNum = 474;BA.debugLine="End Sub";
return "";
}
public static String  _get_rate(int _code) throws Exception{
 //BA.debugLineNum = 464;BA.debugLine="Sub get_rate(code As Int)";
 //BA.debugLineNum = 465;BA.debugLine="Log(\"rate \")";
anywheresoftware.b4a.keywords.Common.Log("rate ");
 //BA.debugLineNum = 466;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 467;BA.debugLine="connector.send_query($\"SELECT * from `nazar` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * from `nazar` where `code_kala`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+""),"get_rate",(Object)(""));
 //BA.debugLineNum = 468;BA.debugLine="End Sub";
return "";
}
public static String  _get_viesue(int _code) throws Exception{
 //BA.debugLineNum = 459;BA.debugLine="Sub get_viesue(code As Int)";
 //BA.debugLineNum = 460;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 461;BA.debugLine="connector.send_query($\"SELECT * from `viesue` whe";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * from `viesue` where `code_kala`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_code))+""),"get_viesue",(Object)(""));
 //BA.debugLineNum = 462;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim apc As AppCompat";
mostCurrent._apc = new de.amberhome.objects.appcompat.AppCompatBase();
 //BA.debugLineNum = 17;BA.debugLine="Dim dir_root_image_file_detailes As String=Starte";
mostCurrent._dir_root_image_file_detailes = mostCurrent._starter._dir_root_image_file_detailes_tumnail;
 //BA.debugLineNum = 18;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 19;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 20;BA.debugLine="Dim toman As money";
mostCurrent._toman = new b4a.example.money();
 //BA.debugLineNum = 21;BA.debugLine="Dim piccaso As Hitex_Picasso";
mostCurrent._piccaso = new wir.hitex.recycler.Hitex_Picasso();
 //BA.debugLineNum = 23;BA.debugLine="Private scrol As ScrollView";
mostCurrent._scrol = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private header As Panel";
mostCurrent._header = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim btn_sabad,btn_search,btn_share,btn_back As Bu";
mostCurrent._btn_sabad = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btn_search = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btn_share = new anywheresoftware.b4a.objects.ButtonWrapper();
mostCurrent._btn_back = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim lbl_badge As Label";
mostCurrent._lbl_badge = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Dim list_kala As List";
mostCurrent._list_kala = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 33;BA.debugLine="Dim color_header As ColorDrawable";
mostCurrent._color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 34;BA.debugLine="Dim color_footer As ColorDrawable";
mostCurrent._color_footer = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 36;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Dim this_visue_bool As Boolean=False";
_this_visue_bool = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 42;BA.debugLine="Dim height As Int";
_height = 0;
 //BA.debugLineNum = 43;BA.debugLine="Private Button1 As Button'//////////ادامه مطلب";
mostCurrent._button1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim error_image,progress_image As BitmapDrawable";
mostCurrent._error_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
mostCurrent._progress_image = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 45;BA.debugLine="Dim font_ico,font_txt As Typeface";
mostCurrent._font_ico = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
mostCurrent._font_txt = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private btn_nazar As Button";
mostCurrent._btn_nazar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private btn_fani As Button";
mostCurrent._btn_fani = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private slide As Panel";
mostCurrent._slide = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private img_mark As ImageView";
mostCurrent._img_mark = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private lbl_name_kala,label3 As Label";
mostCurrent._lbl_name_kala = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private price_kala As String";
mostCurrent._price_kala = "";
 //BA.debugLineNum = 54;BA.debugLine="Dim rate As rate_star";
mostCurrent._rate = new b4a.example.rate_star();
 //BA.debugLineNum = 55;BA.debugLine="Private rate1 As Panel";
mostCurrent._rate1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private rate2 As Panel";
mostCurrent._rate2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private rate3 As Panel";
mostCurrent._rate3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private lbl_count_nazar As Label";
mostCurrent._lbl_count_nazar = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private btn_reg_nazar As Button";
mostCurrent._btn_reg_nazar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Dim bool_login As Boolean=False";
_bool_login = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 65;BA.debugLine="Dim name_dat As String";
mostCurrent._name_dat = "";
 //BA.debugLineNum = 67;BA.debugLine="Private btn_share_kala As Button";
mostCurrent._btn_share_kala = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Dim val_rate1,val_rate2,val_rate3 As Int";
_val_rate1 = 0;
_val_rate2 = 0;
_val_rate3 = 0;
 //BA.debugLineNum = 70;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 72;BA.debugLine="Dim exist_kala As Boolean=True";
_exist_kala = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 73;BA.debugLine="Private img_exist As ImageView";
mostCurrent._img_exist = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Dim pa As PagerBulletAdapter";
mostCurrent._pa = new com.porya.pagerbullet.PagerBulletAdapter();
 //BA.debugLineNum = 76;BA.debugLine="Dim pb As PagerBullet";
mostCurrent._pb = new com.porya.pagerbullet.PagerBulletWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Dim mapslider As List";
mostCurrent._mapslider = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 78;BA.debugLine="Private Progress_slider As ProgressBar";
mostCurrent._progress_slider = new anywheresoftware.b4a.objects.ProgressBarWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Private html_info As htmltv";
mostCurrent._html_info = new de.donmanfred.htmltv();
 //BA.debugLineNum = 80;BA.debugLine="Dim panel_info As Panel";
mostCurrent._panel_info = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Dim panel_info_inside As Panel";
mostCurrent._panel_info_inside = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Dim webview_info As WebView";
mostCurrent._webview_info = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_slideshow(anywheresoftware.b4a.objects.collections.List _list_url) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper[] _p_img = null;
int _i = 0;
 //BA.debugLineNum = 648;BA.debugLine="Sub initialize_slideshow(list_url As List)";
 //BA.debugLineNum = 649;BA.debugLine="Dim p_img(list_url.Size) As ImageView'///////////";
_p_img = new anywheresoftware.b4a.objects.ImageViewWrapper[_list_url.getSize()];
{
int d0 = _p_img.length;
for (int i0 = 0;i0 < d0;i0++) {
_p_img[i0] = new anywheresoftware.b4a.objects.ImageViewWrapper();
}
}
;
 //BA.debugLineNum = 650;BA.debugLine="pa.Initialize";
mostCurrent._pa.Initialize(mostCurrent.activityBA);
 //BA.debugLineNum = 651;BA.debugLine="For i = 0 To list_url.Size-1";
{
final int step3 = 1;
final int limit3 = (int) (_list_url.getSize()-1);
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 652;BA.debugLine="p_img(i).Initialize(\"\")";
_p_img[_i].Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 653;BA.debugLine="Target1(list_url.Get(i),p_img(i))";
_target1(BA.ObjectToString(_list_url.Get(_i)),(Object)(_p_img[_i].getObject()));
 //BA.debugLineNum = 654;BA.debugLine="pa.AddPage(p_img(i)) 'map_info_baner.GetkeyAt(i)";
mostCurrent._pa.AddPage((android.view.View)(_p_img[_i].getObject()));
 }
};
 //BA.debugLineNum = 657;BA.debugLine="Progress_slider.Visible=False";
mostCurrent._progress_slider.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 659;BA.debugLine="pb.Initialize(pa,\"pb1\")";
mostCurrent._pb.Initialize(mostCurrent.activityBA,mostCurrent._pa,"pb1");
 //BA.debugLineNum = 661;BA.debugLine="pb.Transition = pb.ZOOM_OUT_SIDE";
mostCurrent._pb.setTransition(mostCurrent._pb.ZOOM_OUT_SIDE);
 //BA.debugLineNum = 662;BA.debugLine="slide.AddView(pb,0,0,100%x,slide.Height)";
mostCurrent._slide.AddView((android.view.View)(mostCurrent._pb.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._slide.getHeight());
 //BA.debugLineNum = 668;BA.debugLine="get_rate(code_kala)";
_get_rate(_code_kala);
 //BA.debugLineNum = 670;BA.debugLine="End Sub";
return "";
}
public static String  _panel_info_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 507;BA.debugLine="Sub panel_info_Touch (Action As Int, X As Float, Y";
 //BA.debugLineNum = 508;BA.debugLine="panel_info.Visible=False";
mostCurrent._panel_info.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 509;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public code_kala As Int':code_kala=116";
_code_kala = 0;
 //BA.debugLineNum = 8;BA.debugLine="Public kala_txt_info As String";
_kala_txt_info = "";
 //BA.debugLineNum = 9;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _requestsearchbar() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _ref = null;
 //BA.debugLineNum = 572;BA.debugLine="Sub RequestSearchBar";
 //BA.debugLineNum = 573;BA.debugLine="Dim ref As Reflector";
_ref = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 574;BA.debugLine="ref.Target = ref.GetActivity";
_ref.Target = (Object)(_ref.GetActivity(processBA));
 //BA.debugLineNum = 575;BA.debugLine="ref.RunPublicmethod(\"onSearchRequested\", Null, N";
_ref.RunPublicmethod("onSearchRequested",(Object[])(anywheresoftware.b4a.keywords.Common.Null),(String[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 576;BA.debugLine="End Sub";
return "";
}
public static String  _scrol_scrollchanged(int _position) throws Exception{
int _h = 0;
 //BA.debugLineNum = 588;BA.debugLine="Sub scrol_ScrollChanged(Position As Int)";
 //BA.debugLineNum = 589;BA.debugLine="Dim h As Int=255-Position";
_h = (int) (255-_position);
 //BA.debugLineNum = 601;BA.debugLine="If h <= 255 And h >= 0 Then";
if (_h<=255 && _h>=0) { 
 };
 //BA.debugLineNum = 605;BA.debugLine="End Sub";
return "";
}
public static String  _target1(String _img,Object _tag) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _image_view = null;
 //BA.debugLineNum = 684;BA.debugLine="Sub Target1(img As String, Tag As Object)";
 //BA.debugLineNum = 685;BA.debugLine="Dim image_view As ImageView=Tag";
_image_view = new anywheresoftware.b4a.objects.ImageViewWrapper();
_image_view.setObject((android.widget.ImageView)(_tag));
 //BA.debugLineNum = 687;BA.debugLine="Log(\"url: \" & img)";
anywheresoftware.b4a.keywords.Common.Log("url: "+_img);
 //BA.debugLineNum = 688;BA.debugLine="piccaso.Load(\"http\",img).SkipMemoryCache.Into(ima";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_img).SkipMemoryCache().Into(_image_view);
 //BA.debugLineNum = 690;BA.debugLine="End Sub";
return "";
}
public static String  _timer_slide_tick() throws Exception{
 //BA.debugLineNum = 672;BA.debugLine="Sub timer_slide_Tick";
 //BA.debugLineNum = 682;BA.debugLine="End Sub";
return "";
}
}
