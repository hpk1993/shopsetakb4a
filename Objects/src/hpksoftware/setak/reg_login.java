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

public class reg_login extends Activity implements B4AActivity{
	public static reg_login mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.reg_login");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (reg_login).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.reg_login");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.reg_login", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (reg_login) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (reg_login) Resume **");
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
		return reg_login.class;
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
        BA.LogInfo("** Activity (reg_login) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (reg_login) Resume **");
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
public static String _username = "";
public static String _password = "";
public static String _key_software = "";
public static String _sender_sms = "";
public static String _name_dat = "";
public static String _server_adress_forget = "";
public anywheresoftware.b4a.object.RippleViewWrapper _effect_btn1 = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public dmax.dialog.Spotsd _progress_spot = null;
public static int _uid = 0;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_down = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol_reg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_pass = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_name = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_lname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_job = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_phone = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_adress = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_reg_pass1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_reg_pass2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_reg = null;
public anywheresoftware.b4a.objects.StringUtils _su = null;
public anywheresoftware.b4a.agraham.byteconverter.ByteConverter _bc = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_user = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_forget = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_forget = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_forget = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_login = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_login = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_reg = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_login = null;
public com.sdsmdg.tastytoast.Tasty _toast = null;
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
anywheresoftware.b4a.objects.drawable.ColorDrawable _bg_btn = null;
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 48;BA.debugLine="Activity.LoadLayout(\"reg_login\")";
mostCurrent._activity.LoadLayout("reg_login",mostCurrent.activityBA);
 //BA.debugLineNum = 49;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 50;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 51;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 53;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 54;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 55;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,reg_login.getObject());
 //BA.debugLineNum = 56;BA.debugLine="name_dat=Starter.filename_user";
mostCurrent._name_dat = mostCurrent._starter._filename_user;
 //BA.debugLineNum = 57;BA.debugLine="IME.Initialize(\"IME\")";
mostCurrent._ime.Initialize("IME");
 //BA.debugLineNum = 58;BA.debugLine="IME.AddHeightChangedEvent";
mostCurrent._ime.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 66;BA.debugLine="btn_reg.Initialize(\"btn_reg\")";
mostCurrent._btn_reg.Initialize(mostCurrent.activityBA,"btn_reg");
 //BA.debugLineNum = 67;BA.debugLine="txt_name.Initialize(\"txt_name\")";
mostCurrent._txt_name.Initialize(mostCurrent.activityBA,"txt_name");
 //BA.debugLineNum = 68;BA.debugLine="txt_lname.Initialize(\"txt_lname\")";
mostCurrent._txt_lname.Initialize(mostCurrent.activityBA,"txt_lname");
 //BA.debugLineNum = 69;BA.debugLine="txt_job.Initialize(\"txt_job\")";
mostCurrent._txt_job.Initialize(mostCurrent.activityBA,"txt_job");
 //BA.debugLineNum = 70;BA.debugLine="txt_phone.Initialize(\"txt_phone\")";
mostCurrent._txt_phone.Initialize(mostCurrent.activityBA,"txt_phone");
 //BA.debugLineNum = 71;BA.debugLine="txt_adress.Initialize(\"txt_adress\")";
mostCurrent._txt_adress.Initialize(mostCurrent.activityBA,"txt_adress");
 //BA.debugLineNum = 72;BA.debugLine="txt_reg_pass1.Initialize(\"txt_reg_pass1\")";
mostCurrent._txt_reg_pass1.Initialize(mostCurrent.activityBA,"txt_reg_pass1");
 //BA.debugLineNum = 73;BA.debugLine="txt_reg_pass2.Initialize(\"txt_reg_pass2\")";
mostCurrent._txt_reg_pass2.Initialize(mostCurrent.activityBA,"txt_reg_pass2");
 //BA.debugLineNum = 75;BA.debugLine="btn_reg.Text=\"ثبت نام\"";
mostCurrent._btn_reg.setText(BA.ObjectToCharSequence("ثبت نام"));
 //BA.debugLineNum = 76;BA.debugLine="btn_reg.TextSize=13";
mostCurrent._btn_reg.setTextSize((float) (13));
 //BA.debugLineNum = 77;BA.debugLine="btn_reg.TextColor=Colors.White";
mostCurrent._btn_reg.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 78;BA.debugLine="Dim bg_btn As ColorDrawable";
_bg_btn = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 79;BA.debugLine="bg_btn.Initialize2(0xff66BB6A,0,0,0)";
_bg_btn.Initialize2((int) (0xff66bb6a),(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 80;BA.debugLine="btn_reg.Background=bg_btn";
mostCurrent._btn_reg.setBackground((android.graphics.drawable.Drawable)(_bg_btn.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="scrol_reg.Panel.AddView(txt_name,5%x,6%y,scrol_re";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_name.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (6),mostCurrent.activityBA),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 83;BA.debugLine="scrol_reg.Panel.AddView(txt_lname,5%x,(txt_name.T";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_lname.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) ((mostCurrent._txt_name.getTop()+mostCurrent._txt_name.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 84;BA.debugLine="scrol_reg.Panel.AddView(txt_job,5%x,(txt_lname.To";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_job.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) ((mostCurrent._txt_lname.getTop()+mostCurrent._txt_lname.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 85;BA.debugLine="scrol_reg.Panel.AddView(txt_phone,5%x,(txt_job.To";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_phone.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) ((mostCurrent._txt_job.getTop()+mostCurrent._txt_job.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 86;BA.debugLine="scrol_reg.Panel.AddView(txt_adress,5%x,(txt_phone";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_adress.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) ((mostCurrent._txt_phone.getTop()+mostCurrent._txt_phone.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 88;BA.debugLine="scrol_reg.Panel.AddView(txt_reg_pass1,5%x,(txt_ad";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_reg_pass1.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) ((mostCurrent._txt_adress.getTop()+mostCurrent._txt_adress.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 89;BA.debugLine="scrol_reg.Panel.AddView(txt_reg_pass2,5%x,(txt_re";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._txt_reg_pass2.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),(int) ((mostCurrent._txt_reg_pass1.getTop()+mostCurrent._txt_reg_pass1.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (mostCurrent._scrol_reg.getWidth()-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 90;BA.debugLine="scrol_reg.Panel.AddView(btn_reg,0,(txt_reg_pass2.";
mostCurrent._scrol_reg.getPanel().AddView((android.view.View)(mostCurrent._btn_reg.getObject()),(int) (0),(int) ((mostCurrent._txt_reg_pass2.getTop()+mostCurrent._txt_reg_pass2.getHeight())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),mostCurrent._scrol_reg.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 93;BA.debugLine="txt_name.Hint=\"نام\"";
mostCurrent._txt_name.setHint("نام");
 //BA.debugLineNum = 94;BA.debugLine="txt_name.SingleLine=True";
mostCurrent._txt_name.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 95;BA.debugLine="txt_lname.Hint=\"نام خانوادگی\"";
mostCurrent._txt_lname.setHint("نام خانوادگی");
 //BA.debugLineNum = 96;BA.debugLine="txt_lname.SingleLine=True";
mostCurrent._txt_lname.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 97;BA.debugLine="txt_job.Hint=\"شغل\"";
mostCurrent._txt_job.setHint("شغل");
 //BA.debugLineNum = 98;BA.debugLine="txt_job.SingleLine=True";
mostCurrent._txt_job.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 99;BA.debugLine="txt_phone.Hint=\"تلفن همراه مثال :09123456789\"";
mostCurrent._txt_phone.setHint("تلفن همراه مثال :09123456789");
 //BA.debugLineNum = 100;BA.debugLine="txt_phone.SingleLine=True";
mostCurrent._txt_phone.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 101;BA.debugLine="txt_phone.InputType=txt_phone.INPUT_TYPE_PHONE";
mostCurrent._txt_phone.setInputType(mostCurrent._txt_phone.INPUT_TYPE_PHONE);
 //BA.debugLineNum = 103;BA.debugLine="txt_adress.Hint=\"آدرس\"";
mostCurrent._txt_adress.setHint("آدرس");
 //BA.debugLineNum = 104;BA.debugLine="txt_reg_pass1.Hint=\"گذرواژه\"";
mostCurrent._txt_reg_pass1.setHint("گذرواژه");
 //BA.debugLineNum = 105;BA.debugLine="txt_reg_pass1.SingleLine=True";
mostCurrent._txt_reg_pass1.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 106;BA.debugLine="txt_reg_pass2.SingleLine=True";
mostCurrent._txt_reg_pass2.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 107;BA.debugLine="txt_reg_pass2.Hint=\"تکرار گذرواژه\"";
mostCurrent._txt_reg_pass2.setHint("تکرار گذرواژه");
 //BA.debugLineNum = 109;BA.debugLine="txt_name.TextColor=0xFF0C0249";
mostCurrent._txt_name.setTextColor((int) (0xff0c0249));
 //BA.debugLineNum = 110;BA.debugLine="txt_lname.TextColor=txt_name.TextColor";
mostCurrent._txt_lname.setTextColor(mostCurrent._txt_name.getTextColor());
 //BA.debugLineNum = 111;BA.debugLine="txt_job.TextColor=txt_name.TextColor";
mostCurrent._txt_job.setTextColor(mostCurrent._txt_name.getTextColor());
 //BA.debugLineNum = 112;BA.debugLine="txt_phone.TextColor=txt_name.TextColor";
mostCurrent._txt_phone.setTextColor(mostCurrent._txt_name.getTextColor());
 //BA.debugLineNum = 113;BA.debugLine="txt_adress.TextColor=txt_name.TextColor";
mostCurrent._txt_adress.setTextColor(mostCurrent._txt_name.getTextColor());
 //BA.debugLineNum = 114;BA.debugLine="txt_reg_pass1.TextColor=txt_name.TextColor";
mostCurrent._txt_reg_pass1.setTextColor(mostCurrent._txt_name.getTextColor());
 //BA.debugLineNum = 115;BA.debugLine="txt_reg_pass2.TextColor=txt_name.TextColor";
mostCurrent._txt_reg_pass2.setTextColor(mostCurrent._txt_name.getTextColor());
 //BA.debugLineNum = 117;BA.debugLine="txt_name.HintColor=0xFF9B949B";
mostCurrent._txt_name.setHintColor((int) (0xff9b949b));
 //BA.debugLineNum = 118;BA.debugLine="txt_lname.HintColor=txt_name.HintColor";
mostCurrent._txt_lname.setHintColor(mostCurrent._txt_name.getHintColor());
 //BA.debugLineNum = 119;BA.debugLine="txt_job.HintColor=txt_name.HintColor";
mostCurrent._txt_job.setHintColor(mostCurrent._txt_name.getHintColor());
 //BA.debugLineNum = 120;BA.debugLine="txt_phone.HintColor=txt_name.HintColor";
mostCurrent._txt_phone.setHintColor(mostCurrent._txt_name.getHintColor());
 //BA.debugLineNum = 121;BA.debugLine="txt_adress.HintColor=txt_name.HintColor";
mostCurrent._txt_adress.setHintColor(mostCurrent._txt_name.getHintColor());
 //BA.debugLineNum = 122;BA.debugLine="txt_reg_pass1.HintColor=txt_name.HintColor";
mostCurrent._txt_reg_pass1.setHintColor(mostCurrent._txt_name.getHintColor());
 //BA.debugLineNum = 123;BA.debugLine="txt_reg_pass2.HintColor=txt_name.HintColor";
mostCurrent._txt_reg_pass2.setHintColor(mostCurrent._txt_name.getHintColor());
 //BA.debugLineNum = 125;BA.debugLine="txt_name.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._txt_name.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 126;BA.debugLine="txt_lname.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._txt_lname.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 127;BA.debugLine="txt_job.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._txt_job.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 128;BA.debugLine="txt_phone.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._txt_phone.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 129;BA.debugLine="txt_adress.Gravity=Gravity.RIGHT + Gravity.TOP";
mostCurrent._txt_adress.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.RIGHT+anywheresoftware.b4a.keywords.Common.Gravity.TOP));
 //BA.debugLineNum = 130;BA.debugLine="txt_reg_pass1.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._txt_reg_pass1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 131;BA.debugLine="txt_reg_pass2.Gravity=Gravity.CENTER_HORIZONTAL";
mostCurrent._txt_reg_pass2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL);
 //BA.debugLineNum = 133;BA.debugLine="txt_name.TextSize=13";
mostCurrent._txt_name.setTextSize((float) (13));
 //BA.debugLineNum = 134;BA.debugLine="txt_lname.TextSize=txt_name.TextSize";
mostCurrent._txt_lname.setTextSize(mostCurrent._txt_name.getTextSize());
 //BA.debugLineNum = 135;BA.debugLine="txt_job.TextSize=txt_name.TextSize";
mostCurrent._txt_job.setTextSize(mostCurrent._txt_name.getTextSize());
 //BA.debugLineNum = 136;BA.debugLine="txt_phone.TextSize=txt_name.TextSize";
mostCurrent._txt_phone.setTextSize(mostCurrent._txt_name.getTextSize());
 //BA.debugLineNum = 137;BA.debugLine="txt_adress.TextSize=txt_name.TextSize";
mostCurrent._txt_adress.setTextSize(mostCurrent._txt_name.getTextSize());
 //BA.debugLineNum = 138;BA.debugLine="txt_reg_pass1.TextSize=txt_name.TextSize";
mostCurrent._txt_reg_pass1.setTextSize(mostCurrent._txt_name.getTextSize());
 //BA.debugLineNum = 139;BA.debugLine="txt_reg_pass2.TextSize=txt_name.TextSize";
mostCurrent._txt_reg_pass2.setTextSize(mostCurrent._txt_name.getTextSize());
 //BA.debugLineNum = 142;BA.debugLine="scrol_reg.Panel.Height=btn_reg.Top + btn_reg.Heig";
mostCurrent._scrol_reg.getPanel().setHeight((int) (mostCurrent._btn_reg.getTop()+mostCurrent._btn_reg.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 382;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 383;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 384;BA.debugLine="If panel_reg.Visible=True Then";
if (mostCurrent._panel_reg.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 385;BA.debugLine="panel_reg.Visible=False";
mostCurrent._panel_reg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 387;BA.debugLine="panel_login.Visible=True";
mostCurrent._panel_login.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 388;BA.debugLine="panel_forget.Visible=True";
mostCurrent._panel_forget.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 389;BA.debugLine="btn_down.Visible=True";
mostCurrent._btn_down.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 390;BA.debugLine="img_login.Visible=True";
mostCurrent._img_login.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 392;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 395;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 396;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 397;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 398;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 };
 //BA.debugLineNum = 401;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 162;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 159;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,reg_login.getObject());
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 178;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 179;BA.debugLine="If panel_reg.Visible=True Then";
if (mostCurrent._panel_reg.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 180;BA.debugLine="panel_reg.Visible=False";
mostCurrent._panel_reg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="panel_login.Visible=True";
mostCurrent._panel_login.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 182;BA.debugLine="panel_forget.Visible=True";
mostCurrent._panel_forget.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 183;BA.debugLine="btn_down.Visible=True";
mostCurrent._btn_down.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 184;BA.debugLine="img_login.Visible=True";
mostCurrent._img_login.setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 186;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 187;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 188;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 };
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _btn_down_click() throws Exception{
 //BA.debugLineNum = 167;BA.debugLine="Sub btn_down_Click";
 //BA.debugLineNum = 169;BA.debugLine="panel_login.Visible=False";
mostCurrent._panel_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 170;BA.debugLine="panel_forget.Visible=False";
mostCurrent._panel_forget.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 171;BA.debugLine="btn_forget.Visible=False";
mostCurrent._btn_forget.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 172;BA.debugLine="btn_down.Visible=False";
mostCurrent._btn_down.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 173;BA.debugLine="img_login.Visible=False";
mostCurrent._img_login.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 174;BA.debugLine="panel_reg.Visible=True";
mostCurrent._panel_reg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _btn_forget_click() throws Exception{
 //BA.debugLineNum = 417;BA.debugLine="Sub btn_forget_Click";
 //BA.debugLineNum = 418;BA.debugLine="If panel_forget.Top= btn_forget.Top Then";
if (mostCurrent._panel_forget.getTop()==mostCurrent._btn_forget.getTop()) { 
 //BA.debugLineNum = 419;BA.debugLine="panel_forget.SetLayoutAnimated(500,panel_forget.";
mostCurrent._panel_forget.SetLayoutAnimated((int) (500),mostCurrent._panel_forget.getLeft(),(int) (mostCurrent._btn_forget.getTop()+mostCurrent._btn_forget.getHeight()),mostCurrent._panel_forget.getWidth(),mostCurrent._panel_forget.getHeight());
 }else {
 //BA.debugLineNum = 421;BA.debugLine="panel_forget.SetLayoutAnimated(500,panel_forget.";
mostCurrent._panel_forget.SetLayoutAnimated((int) (500),mostCurrent._panel_forget.getLeft(),mostCurrent._btn_forget.getTop(),mostCurrent._panel_forget.getWidth(),mostCurrent._panel_forget.getHeight());
 };
 //BA.debugLineNum = 424;BA.debugLine="End Sub";
return "";
}
public static String  _btn_login_click() throws Exception{
 //BA.debugLineNum = 403;BA.debugLine="Sub btn_login_Click";
 //BA.debugLineNum = 404;BA.debugLine="If txt_user.Text.Length > 0 And txt_pass.Text.Len";
if (mostCurrent._txt_user.getText().length()>0 && mostCurrent._txt_pass.getText().length()>0) { 
 //BA.debugLineNum = 405;BA.debugLine="If txt_user.Text.Contains(\"09\")=True And (txt_us";
if (mostCurrent._txt_user.getText().contains("09")==anywheresoftware.b4a.keywords.Common.True && (mostCurrent._txt_user.getText().length()==11)) { 
 //BA.debugLineNum = 406;BA.debugLine="check_login";
_check_login();
 }else if(mostCurrent._txt_user.getText().contains("@")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 408;BA.debugLine="check_login";
_check_login();
 }else {
 //BA.debugLineNum = 410;BA.debugLine="ToastMessageShow(\"نام کاریری مورد تایید نیست\" &";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("نام کاریری مورد تایید نیست"+anywheresoftware.b4a.keywords.Common.CRLF+"مثال : 09123456789"+anywheresoftware.b4a.keywords.Common.CRLF+"یا مثال : example@domain.com"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 413;BA.debugLine="ToastMessageShow(\"لطفا فیلد ها را پر کنید\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا فیلد ها را پر کنید"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 415;BA.debugLine="End Sub";
return "";
}
public static String  _btn_reg_click() throws Exception{
 //BA.debugLineNum = 192;BA.debugLine="Sub btn_reg_Click";
 //BA.debugLineNum = 193;BA.debugLine="If txt_reg_pass1.Text.CompareTo(txt_reg_pass2.Tex";
if (mostCurrent._txt_reg_pass1.getText().compareTo(mostCurrent._txt_reg_pass2.getText())==0 && mostCurrent._txt_reg_pass1.getText().length()>=5) { 
 //BA.debugLineNum = 194;BA.debugLine="If txt_name.Text.Trim.Length >= 3 And txt_lname.";
if (mostCurrent._txt_name.getText().trim().length()>=3 && mostCurrent._txt_lname.getText().trim().length()>=3 && mostCurrent._txt_phone.getText().trim().length()==11 && mostCurrent._txt_phone.getText().trim().contains("09")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 195;BA.debugLine="If txt_job.Text.Trim.Length > 3 Then";
if (mostCurrent._txt_job.getText().trim().length()>3) { 
 //BA.debugLineNum = 196;BA.debugLine="check_users_reg";
_check_users_reg();
 }else {
 //BA.debugLineNum = 198;BA.debugLine="ToastMessageShow(\"لطفا شغل را به درستی وارد کن";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا شغل را به درستی وارد کنید"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 201;BA.debugLine="ToastMessageShow(\"اطلاعات خود را کامل وارد کنید";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("اطلاعات خود را کامل وارد کنید(حداقل 3 کاراکتر)"+anywheresoftware.b4a.keywords.Common.CRLF+"شماره تلفن همراه را مانند : 09123456789 وارد کنید"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 204;BA.debugLine="ToastMessageShow(\"نکته : گذرواژه باید حداقل 5 کا";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("نکته : گذرواژه باید حداقل 5 کاراکتر باشد"+anywheresoftware.b4a.keywords.Common.CRLF+"دو فیلد گذرواژه باید مشابه هم باشند"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _btn_send_forgt_click() throws Exception{
int _count_sms = 0;
String _phone_number = "";
 //BA.debugLineNum = 434;BA.debugLine="Sub btn_send_forgt_Click";
 //BA.debugLineNum = 435;BA.debugLine="DateTime.DateFormat=\"yyyy_MM_dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy_MM_dd");
 //BA.debugLineNum = 436;BA.debugLine="If File.Exists(myLibrary.rute,DateTime.Date(DateT";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 437;BA.debugLine="File.WriteString(myLibrary.rute,DateTime.Date(Da";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._mylibrary._rute(mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),BA.NumberToString(1));
 };
 //BA.debugLineNum = 440;BA.debugLine="Dim count_sms As Int=File.ReadString(myLibrary.ru";
_count_sms = (int)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._mylibrary._rute(mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))));
 //BA.debugLineNum = 441;BA.debugLine="Log(\"count_sms:\" & count_sms)";
anywheresoftware.b4a.keywords.Common.Log("count_sms:"+BA.NumberToString(_count_sms));
 //BA.debugLineNum = 442;BA.debugLine="If count_sms <= 3 Then";
if (_count_sms<=3) { 
 //BA.debugLineNum = 446;BA.debugLine="Dim phone_number As String=txt_forget.Text.T";
_phone_number = mostCurrent._txt_forget.getText().trim();
 //BA.debugLineNum = 447;BA.debugLine="If phone_number.SubString2(0,3).Contains(\"09";
if (_phone_number.substring((int) (0),(int) (3)).contains("09")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 448;BA.debugLine="If phone_number.SubString2(0,3).CompareTo(\"";
if (_phone_number.substring((int) (0),(int) (3)).compareTo("091")==0 || _phone_number.substring((int) (0),(int) (3)).compareTo("090")==0) { 
 //BA.debugLineNum = 449;BA.debugLine="sender_sms=Starter.sender_sms(2)";
mostCurrent._sender_sms = mostCurrent._starter._sender_sms[(int) (2)];
 }else {
 //BA.debugLineNum = 451;BA.debugLine="sender_sms=Starter.sender_sms(1)";
mostCurrent._sender_sms = mostCurrent._starter._sender_sms[(int) (1)];
 };
 //BA.debugLineNum = 454;BA.debugLine="send_function_sms(Array As String(\"user\",us";
_send_function_sms(new String[]{"user",mostCurrent._username,"pass",mostCurrent._password,"op","send","from",mostCurrent._sender_sms,"to",_phone_number.trim(),"function_panel",mostCurrent._key_software});
 }else if(_phone_number.contains("@")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 459;BA.debugLine="send_function_sms(Array As String(\"user\",us";
_send_function_sms(new String[]{"user",mostCurrent._username,"pass",mostCurrent._password,"op","send","from",mostCurrent._sender_sms,"email",_phone_number.trim(),"function_panel",mostCurrent._key_software});
 }else {
 //BA.debugLineNum = 462;BA.debugLine="ToastMessageShow(\"ایمیل یا شماره تماس را به";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("ایمیل یا شماره تماس را به صورت صحیح وارد کنید"),anywheresoftware.b4a.keywords.Common.True);
 };
 }else {
 //BA.debugLineNum = 466;BA.debugLine="ToastMessageShow(\"محدودیت در ارسال روزانه.متاس";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("محدودیت در ارسال روزانه.متاسفانه شما امروز بیشتر از 3 بار سعی در ارسال کد کردید"),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 471;BA.debugLine="End Sub";
return "";
}
public static String  _check_login() throws Exception{
String _pass = "";
 //BA.debugLineNum = 361;BA.debugLine="Sub check_login";
 //BA.debugLineNum = 362;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 363;BA.debugLine="Dim pass As String";
_pass = "";
 //BA.debugLineNum = 364;BA.debugLine="pass=(su.EncodeBase64(bc.StringToBytes(txt_pass.T";
_pass = (mostCurrent._su.EncodeBase64(mostCurrent._bc.StringToBytes(mostCurrent._txt_pass.getText().trim(),"utf-8")));
 //BA.debugLineNum = 365;BA.debugLine="connector.send_query($\"SELECT * From `users` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * From `users` where (`mobile`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_user.getText().trim()))+"' OR `email`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_user.getText().trim()))+"') AND `password`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_pass))+"'"),"check_login",(Object)(""));
 //BA.debugLineNum = 366;BA.debugLine="End Sub";
return "";
}
public static String  _check_users_reg() throws Exception{
 //BA.debugLineNum = 356;BA.debugLine="Sub check_users_reg";
 //BA.debugLineNum = 357;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 358;BA.debugLine="connector.send_query($\"SELECT * From `users` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * From `users` where `mobile`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_phone.getText().trim()))+"' "),"check_users_reg",(Object)(""));
 //BA.debugLineNum = 359;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
int _id_user = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
int _user_type = 0;
anywheresoftware.b4a.objects.collections.List _lst1 = null;
anywheresoftware.b4a.samples.httputils2.httpjob _http_sms = null;
String _msgsms = "";
anywheresoftware.b4a.objects.collections.List _lst_user = null;
String _mobile = "";
 //BA.debugLineNum = 208;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 209;BA.debugLine="Try";
try { //BA.debugLineNum = 210;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("check_users_reg"),(Object)("insert_users"),(Object)("get_id"),(Object)("check_login"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 //BA.debugLineNum = 212;BA.debugLine="If records.Size <=0 Then";
if (_records.getSize()<=0) { 
 //BA.debugLineNum = 213;BA.debugLine="insert_users";
_insert_users();
 }else {
 //BA.debugLineNum = 216;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 217;BA.debugLine="txt_name.Text=\"\"";
mostCurrent._txt_name.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 218;BA.debugLine="txt_lname.Text=\"\"";
mostCurrent._txt_lname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 219;BA.debugLine="txt_job.Text=\"\"";
mostCurrent._txt_job.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 220;BA.debugLine="txt_phone.Text=\"\"";
mostCurrent._txt_phone.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 221;BA.debugLine="txt_reg_pass1.Text=\"\"";
mostCurrent._txt_reg_pass1.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 222;BA.debugLine="txt_reg_pass2.Text=\"\"";
mostCurrent._txt_reg_pass2.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 223;BA.debugLine="toast.Initialize(\"محصولی درج نشده است\",toast.";
mostCurrent._toast.Initialize(mostCurrent.activityBA,"محصولی درج نشده است",mostCurrent._toast.LENGTH_SHORT,mostCurrent._toast.ERROR);
 };
 break; }
case 1: {
 //BA.debugLineNum = 228;BA.debugLine="get_id";
_get_id();
 break; }
case 2: {
 //BA.debugLineNum = 233;BA.debugLine="Dim id_user As Int";
_id_user = 0;
 //BA.debugLineNum = 235;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 236;BA.debugLine="Dim map1 As Map=records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get((int) (0))));
 //BA.debugLineNum = 237;BA.debugLine="id_user=map1.Get(\"id\")";
_id_user = (int)(BA.ObjectToNumber(_map1.Get((Object)("id"))));
 //BA.debugLineNum = 238;BA.debugLine="Dim user_type As Int=map1.Get(\"user_type\")";
_user_type = (int)(BA.ObjectToNumber(_map1.Get((Object)("user_type"))));
 //BA.debugLineNum = 239;BA.debugLine="Dim lst1 As List";
_lst1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 240;BA.debugLine="lst1.Initialize";
_lst1.Initialize();
 //BA.debugLineNum = 241;BA.debugLine="lst1.Add(txt_phone.Text.Trim) 'phone";
_lst1.Add((Object)(mostCurrent._txt_phone.getText().trim()));
 //BA.debugLineNum = 242;BA.debugLine="lst1.Add(txt_name.Text.Trim) ' نام";
_lst1.Add((Object)(mostCurrent._txt_name.getText().trim()));
 //BA.debugLineNum = 243;BA.debugLine="lst1.Add(txt_lname.Text.Trim) 'فامیلی";
_lst1.Add((Object)(mostCurrent._txt_lname.getText().trim()));
 //BA.debugLineNum = 244;BA.debugLine="lst1.Add(id_user) 'id user";
_lst1.Add((Object)(_id_user));
 //BA.debugLineNum = 245;BA.debugLine="lst1.Add(user_type)";
_lst1.Add((Object)(_user_type));
 //BA.debugLineNum = 246;BA.debugLine="uid=id_user";
_uid = _id_user;
 //BA.debugLineNum = 247;BA.debugLine="File.WriteList(myLibrary.rute,name_dat,lst1)";
anywheresoftware.b4a.keywords.Common.File.WriteList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._name_dat,_lst1);
 //BA.debugLineNum = 249;BA.debugLine="Dim http_sms As HttpJob";
_http_sms = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 250;BA.debugLine="http_sms.Initialize(\"sms_send\",Me)";
_http_sms._initialize(processBA,"sms_send",reg_login.getObject());
 //BA.debugLineNum = 251;BA.debugLine="Dim msgsms As String=$\"از عضویت در فروشگاه ${";
_msgsms = ("از عضویت در فروشگاه "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.Application.getLabelName()))+" سپاسگذاریم.کد اشتراک شما "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(100+_id_user))+"");
 //BA.debugLineNum = 252;BA.debugLine="http_sms.PostString(Starter.address_send_sms,";
_http_sms._poststring(mostCurrent._starter._address_send_sms,("reg_user=true&name="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_name.getText().trim()))+"&lname="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_lname.getText().trim()))+"&code="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(100+_id_user))+"&msg="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_msgsms))+"&to="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_phone.getText().trim()))+""));
 }else {
 //BA.debugLineNum = 256;BA.debugLine="ToastMessageShow(\"مشکل در ثبت اطلاعات.لطفا دو";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("مشکل در ثبت اطلاعات.لطفا دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 257;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 break; }
case 3: {
 //BA.debugLineNum = 262;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 263;BA.debugLine="Dim map1 As Map=records.Get(0)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get((int) (0))));
 //BA.debugLineNum = 264;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 265;BA.debugLine="Dim lst_user As List";
_lst_user = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 266;BA.debugLine="lst_user.Initialize";
_lst_user.Initialize();
 //BA.debugLineNum = 267;BA.debugLine="Dim mobile As String=map1.Get(\"mobile\")";
_mobile = BA.ObjectToString(_map1.Get((Object)("mobile")));
 //BA.debugLineNum = 268;BA.debugLine="If mobile.Length <=0 Then";
if (_mobile.length()<=0) { 
 //BA.debugLineNum = 269;BA.debugLine="lst_user.Add(map1.Get(\"email\"))";
_lst_user.Add(_map1.Get((Object)("email")));
 }else {
 //BA.debugLineNum = 271;BA.debugLine="lst_user.Add(map1.Get(\"mobile\"))";
_lst_user.Add(_map1.Get((Object)("mobile")));
 };
 //BA.debugLineNum = 273;BA.debugLine="lst_user.Add(map1.Get(\"name\"))";
_lst_user.Add(_map1.Get((Object)("name")));
 //BA.debugLineNum = 274;BA.debugLine="lst_user.Add(map1.Get(\"lname\"))";
_lst_user.Add(_map1.Get((Object)("lname")));
 //BA.debugLineNum = 275;BA.debugLine="lst_user.Add(map1.Get(\"id\"))";
_lst_user.Add(_map1.Get((Object)("id")));
 //BA.debugLineNum = 276;BA.debugLine="lst_user.Add(map1.Get(\"user_type\")	)";
_lst_user.Add(_map1.Get((Object)("user_type")));
 //BA.debugLineNum = 277;BA.debugLine="File.WriteList(myLibrary.rute,name_dat,lst_us";
anywheresoftware.b4a.keywords.Common.File.WriteList(mostCurrent._mylibrary._rute(mostCurrent.activityBA),mostCurrent._name_dat,_lst_user);
 //BA.debugLineNum = 278;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 279;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 281;BA.debugLine="txt_user.Text=\"\"";
mostCurrent._txt_user.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 282;BA.debugLine="txt_pass.Text=\"\"";
mostCurrent._txt_pass.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 283;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 284;BA.debugLine="toast.Initialize(\"نام کاربری یا گذرواژه شما د";
mostCurrent._toast.Initialize(mostCurrent.activityBA,"نام کاربری یا گذرواژه شما در سیستم موجود نمی باشد",mostCurrent._toast.LENGTH_LONG,mostCurrent._toast.ERROR);
 };
 break; }
case 4: {
 //BA.debugLineNum = 287;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 288;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 5: {
 //BA.debugLineNum = 290;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 291;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 292;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 295;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e76) {
			processBA.setLastException(e76); //BA.debugLineNum = 297;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 298;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return "";
}
public static String  _get_id() throws Exception{
 //BA.debugLineNum = 368;BA.debugLine="Sub get_id";
 //BA.debugLineNum = 369;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 370;BA.debugLine="connector.send_query($\"SELECT * From `users` wher";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT * From `users` where `mobile`='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_phone.getText().trim()))+"'"),"get_id",(Object)(""));
 //BA.debugLineNum = 371;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim username As String=Starter.username_sms";
mostCurrent._username = mostCurrent._starter._username_sms;
 //BA.debugLineNum = 14;BA.debugLine="Dim password As String=Starter.password_sms";
mostCurrent._password = mostCurrent._starter._password_sms;
 //BA.debugLineNum = 15;BA.debugLine="Dim key_software As String=Starter.Api_key_sms";
mostCurrent._key_software = mostCurrent._starter._api_key_sms;
 //BA.debugLineNum = 16;BA.debugLine="Dim sender_sms As String=Starter.sender_sms(0)";
mostCurrent._sender_sms = mostCurrent._starter._sender_sms[(int) (0)];
 //BA.debugLineNum = 17;BA.debugLine="Dim name_dat As String";
mostCurrent._name_dat = "";
 //BA.debugLineNum = 19;BA.debugLine="Dim server_adress_forget As String=Starter.server";
mostCurrent._server_adress_forget = mostCurrent._starter._server_address_send_forgetpassword;
 //BA.debugLineNum = 20;BA.debugLine="Dim effect_btn1 As RippleView ',effect_btn2,effec";
mostCurrent._effect_btn1 = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim IME As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 22;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 23;BA.debugLine="Dim uid As Int";
_uid = 0;
 //BA.debugLineNum = 24;BA.debugLine="Private btn_down As Button";
mostCurrent._btn_down = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private scrol_reg As ScrollView";
mostCurrent._scrol_reg = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private txt_pass As EditText";
mostCurrent._txt_pass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private txt_name,txt_lname,txt_job,txt_phone,txt_";
mostCurrent._txt_name = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._txt_lname = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._txt_job = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._txt_phone = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._txt_adress = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private txt_reg_pass1,txt_reg_pass2 As EditText";
mostCurrent._txt_reg_pass1 = new anywheresoftware.b4a.objects.EditTextWrapper();
mostCurrent._txt_reg_pass2 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private btn_reg As Button";
mostCurrent._btn_reg = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim su As StringUtils";
mostCurrent._su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 34;BA.debugLine="Dim bc As ByteConverter";
mostCurrent._bc = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 35;BA.debugLine="Private txt_user As EditText";
mostCurrent._txt_user = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private btn_forget As Button";
mostCurrent._btn_forget = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private txt_forget As EditText";
mostCurrent._txt_forget = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private panel_forget As Panel";
mostCurrent._panel_forget = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private btn_login As Button";
mostCurrent._btn_login = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private panel_login As Panel";
mostCurrent._panel_login = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private panel_reg As Panel";
mostCurrent._panel_reg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private img_login As ImageView";
mostCurrent._img_login = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _ime_heightchanged(int _newheight,int _oldheight) throws Exception{
 //BA.debugLineNum = 146;BA.debugLine="Sub IME_HeightChanged(NewHeight As Int, OldHeight";
 //BA.debugLineNum = 147;BA.debugLine="If scrol_reg.Height > 100dip  Then";
if (mostCurrent._scrol_reg.getHeight()>anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))) { 
 //BA.debugLineNum = 148;BA.debugLine="scrol_reg.Height=NewHeight - scrol_reg.Top";
mostCurrent._scrol_reg.setHeight((int) (_newheight-mostCurrent._scrol_reg.getTop()));
 };
 //BA.debugLineNum = 151;BA.debugLine="If panel_forget.Top=btn_forget.Top + btn_forget.H";
if (mostCurrent._panel_forget.getTop()==mostCurrent._btn_forget.getTop()+mostCurrent._btn_forget.getHeight() && mostCurrent._txt_forget.RequestFocus()) { 
 //BA.debugLineNum = 152;BA.debugLine="panel_forget.Top=(NewHeight-NewHeight)+Panel1.He";
mostCurrent._panel_forget.setTop((int) ((_newheight-_newheight)+mostCurrent._panel1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))));
 };
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public static String  _insert_users() throws Exception{
String _pass = "";
 //BA.debugLineNum = 350;BA.debugLine="Sub insert_users";
 //BA.debugLineNum = 351;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 352;BA.debugLine="Dim pass As String";
_pass = "";
 //BA.debugLineNum = 353;BA.debugLine="pass=(su.EncodeBase64(bc.StringToBytes(txt_reg_pa";
_pass = (mostCurrent._su.EncodeBase64(mostCurrent._bc.StringToBytes(mostCurrent._txt_reg_pass1.getText().trim(),"utf-8")));
 //BA.debugLineNum = 354;BA.debugLine="connector.send_query($\"insert into `users`(`name`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("insert into `users`(`name`,`lname`,`mobile`,`job`,`password`,`user_type`,`address`) values(N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_name.getText().trim()))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_lname.getText().trim()))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_phone.getText().trim()))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_job.getText().trim()))+"',N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_pass))+"',2,N'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_adress.getText().trim()))+"')"),"insert_users",(Object)(""));
 //BA.debugLineNum = 355;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _res = "";
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
int _count_sms = 0;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
 //BA.debugLineNum = 303;BA.debugLine="Sub JobDone(Job As HttpJob)";
 //BA.debugLineNum = 304;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 305;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 306;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 307;BA.debugLine="res = Job.GetString";
_res = _job._getstring();
 //BA.debugLineNum = 308;BA.debugLine="Log(\"Response from server: \" & res)";
anywheresoftware.b4a.keywords.Common.Log("Response from server: "+_res);
 //BA.debugLineNum = 309;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 310;BA.debugLine="json.Initialize(res)";
_json.Initialize(_res);
 //BA.debugLineNum = 311;BA.debugLine="Select Job.JobName";
switch (BA.switchObjectToInt(_job._jobname,"send_param","sms_send","send_push")) {
case 0: {
 //BA.debugLineNum = 314;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 315;BA.debugLine="DateTime.DateFormat=\"yyyy_MM_dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy_MM_dd");
 //BA.debugLineNum = 316;BA.debugLine="Dim count_sms As Int=File.ReadString(myLibrary";
_count_sms = (int)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.File.ReadString(mostCurrent._mylibrary._rute(mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))));
 //BA.debugLineNum = 317;BA.debugLine="File.WriteString(myLibrary.rute,DateTime.Date(";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._mylibrary._rute(mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()),BA.NumberToString(_count_sms+1));
 //BA.debugLineNum = 318;BA.debugLine="Log(DateTime.Date(DateTime.Now))";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 319;BA.debugLine="Log(count_sms)";
anywheresoftware.b4a.keywords.Common.Log(BA.NumberToString(_count_sms));
 //BA.debugLineNum = 320;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 321;BA.debugLine="Pd=function.style_msgbox2(\"پیام\",Job.Getstring";
_pd = mostCurrent._function._style_msgbox2(mostCurrent.activityBA,"پیام",_job._getstring());
 //BA.debugLineNum = 322;BA.debugLine="Pd.Create";
_pd.Create(mostCurrent.activityBA);
 break; }
case 1: {
 //BA.debugLineNum = 325;BA.debugLine="send_push";
_send_push();
 break; }
case 2: {
 //BA.debugLineNum = 329;BA.debugLine="ToastMessageShow(\"خوش آمدید\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("خوش آمدید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 330;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 331;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 332;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
}
;
 }else {
 //BA.debugLineNum = 336;BA.debugLine="Log(\"\")";
anywheresoftware.b4a.keywords.Common.Log("");
 };
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _scrol_reg_scrollchanged(int _position) throws Exception{
 //BA.debugLineNum = 426;BA.debugLine="Sub scrol_reg_ScrollChanged(Position As Int)";
 //BA.debugLineNum = 428;BA.debugLine="End Sub";
return "";
}
public static String  _send_function_sms(String[] _a) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
 //BA.debugLineNum = 373;BA.debugLine="Sub send_function_sms(a() As String)";
 //BA.debugLineNum = 374;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 375;BA.debugLine="Dim J As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 376;BA.debugLine="J.Initialize(\"send_param\",Me)";
_j._initialize(processBA,"send_param",reg_login.getObject());
 //BA.debugLineNum = 378;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 379;BA.debugLine="J.Download2(server_adress_forget,a)";
_j._download2(mostCurrent._server_adress_forget,_a);
 //BA.debugLineNum = 380;BA.debugLine="End Sub";
return "";
}
public static String  _send_push() throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _http_push = null;
 //BA.debugLineNum = 341;BA.debugLine="Sub send_push";
 //BA.debugLineNum = 342;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 343;BA.debugLine="Dim http_push As HttpJob";
_http_push = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 344;BA.debugLine="http_push.Initialize(\"send_push\",Me)";
_http_push._initialize(processBA,"send_push",reg_login.getObject());
 //BA.debugLineNum = 345;BA.debugLine="http_push.PostString(Starter.Addres_send_pushe,$\"";
_http_push._poststring(mostCurrent._starter._addres_send_pushe,("reg_user=true&name_lname="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._txt_name.getText().trim()+" "+mostCurrent._txt_lname.getText().trim()))+"&uid="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(100+_uid))+""));
 //BA.debugLineNum = 346;BA.debugLine="End Sub";
return "";
}
public static String  _txt_forget_focuschanged(boolean _hasfocus) throws Exception{
 //BA.debugLineNum = 430;BA.debugLine="Sub txt_forget_FocusChanged (HasFocus As Boolean)";
 //BA.debugLineNum = 431;BA.debugLine="If HasFocus=False Then panel_forget.Top=btn_forge";
if (_hasfocus==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._panel_forget.setTop(mostCurrent._btn_forget.getTop());};
 //BA.debugLineNum = 432;BA.debugLine="End Sub";
return "";
}
}
