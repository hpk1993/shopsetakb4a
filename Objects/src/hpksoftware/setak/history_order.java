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

public class history_order extends Activity implements B4AActivity{
	public static history_order mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.history_order");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (history_order).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.history_order");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.history_order", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (history_order) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (history_order) Resume **");
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
		return history_order.class;
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
        BA.LogInfo("** Activity (history_order) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (history_order) Resume **");
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
public static int _id_user = 0;
public static boolean _is_admin = false;
public wir.hitex.recycler.Hitex_Picasso _piccaso = null;
public b4a.example.mytoastmessageshow _mytoastmessage = null;
public anywheresoftware.b4a.object.RippleViewWrapper _effect_btn1 = null;
public dmax.dialog.Spotsd _progress_spot = null;
public hpksoftware.setak.customlistview _sv = null;
public hpksoftware.setak.customlistview _sv2 = null;
public b4a.example.money _mony = null;
public njdude.animated.sliding.menu.animatedslidingmenu _njmenu = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol_main = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_back = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_search = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_share = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btn_sabad = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _sv_panel = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_code = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_date = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_price = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_stat = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_history = null;
public b4a.example.money _toman = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_name = null;
public com.sdsmdg.tastytoast.Tasty _toast = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _img_kala = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
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
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bt = null;
 //BA.debugLineNum = 42;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout(\"scrol_layout\")";
mostCurrent._activity.LoadLayout("scrol_layout",mostCurrent.activityBA);
 //BA.debugLineNum = 44;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 45;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 46;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,history_order.getObject());
 //BA.debugLineNum = 49;BA.debugLine="server_mysql=Starter.server_mysql";
_server_mysql = mostCurrent._starter._server_mysql;
 //BA.debugLineNum = 52;BA.debugLine="function. initialize_spotdialog(progress_spot)";
mostCurrent._function._initialize_spotdialog(mostCurrent.activityBA,mostCurrent._progress_spot);
 //BA.debugLineNum = 53;BA.debugLine="mytoastMessage.Initialize(Me,\"DoAction_End\",Activ";
mostCurrent._mytoastmessage._initialize(mostCurrent.activityBA,history_order.getObject(),"DoAction_End",(anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),(int) (0xff13879a),anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 54;BA.debugLine="mony.Initialize";
mostCurrent._mony._initialize(processBA);
 //BA.debugLineNum = 55;BA.debugLine="effect_btn1.Initialize(footer,Colors.White,400,Fa";
mostCurrent._effect_btn1.Initialize(mostCurrent.activityBA,(android.view.View)(mostCurrent._footer.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,(int) (400),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 57;BA.debugLine="Dim bt As BitmapDrawable";
_bt = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 58;BA.debugLine="bt.Initialize(LoadBitmap(File.DirAssets,\"filter2.";
_bt.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"filter2.png").getObject()));
 //BA.debugLineNum = 59;BA.debugLine="btn_back.Background=bt";
mostCurrent._btn_back.setBackground((android.graphics.drawable.Drawable)(_bt.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="Label3.Text=\"تاریخچه همه سفارشات\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("تاریخچه همه سفارشات"));
 //BA.debugLineNum = 62;BA.debugLine="footer.Visible=False";
mostCurrent._footer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 63;BA.debugLine="scrol_main.SetLayout(scrol_main.Left,scrol_main.T";
mostCurrent._scrol_main.SetLayout(mostCurrent._scrol_main.getLeft(),mostCurrent._scrol_main.getTop(),mostCurrent._scrol_main.getWidth(),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-(mostCurrent._panel1.getHeight())));
 //BA.debugLineNum = 65;BA.debugLine="initialize_menu";
_initialize_menu();
 //BA.debugLineNum = 67;BA.debugLine="sv.Initialize(Me,\"sv1\",0xffE0DDDD,6dip)";
mostCurrent._sv._initialize(mostCurrent.activityBA,history_order.getObject(),"sv1",(int) (0xffe0dddd),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
 //BA.debugLineNum = 68;BA.debugLine="sv2.Initialize(Me,\"sv2\",0xffE0DDDD,6dip)";
mostCurrent._sv2._initialize(mostCurrent.activityBA,history_order.getObject(),"sv2",(int) (0xffe0dddd),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)));
 //BA.debugLineNum = 71;BA.debugLine="scrol_main.Visible=False";
mostCurrent._scrol_main.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 72;BA.debugLine="panel_history.Visible=True";
mostCurrent._panel_history.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 73;BA.debugLine="panel_history.AddView(sv.AsView,0,0,panel_history";
mostCurrent._panel_history.AddView((android.view.View)(mostCurrent._sv._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_history.getWidth(),mostCurrent._panel_history.getHeight());
 //BA.debugLineNum = 81;BA.debugLine="connector.progress_circle_initialize(Activity)";
mostCurrent._connector._progress_circle_initialize(mostCurrent.activityBA,mostCurrent._activity);
 //BA.debugLineNum = 82;BA.debugLine="connector.progress_circle_visible(False)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 83;BA.debugLine="Log(\"id_user\" & id_user)";
anywheresoftware.b4a.keywords.Common.Log("id_user"+BA.NumberToString(_id_user));
 //BA.debugLineNum = 84;BA.debugLine="get_all_order(id_user)";
_get_all_order(_id_user);
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 323;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 324;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 325;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 326;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 327;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 329;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 89;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,history_order.getObject());
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _animatedmenu_click(Object _selecteditem) throws Exception{
 //BA.debugLineNum = 262;BA.debugLine="Sub AnimatedMenu_Click(SelectedItem As Object)";
 //BA.debugLineNum = 263;BA.debugLine="Select SelectedItem";
switch (BA.switchObjectToInt(_selecteditem,(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5))) {
case 0: {
 //BA.debugLineNum = 266;BA.debugLine="get_all_order(id_user)";
_get_all_order(_id_user);
 //BA.debugLineNum = 267;BA.debugLine="Label3.Text=\"تاریخچه همه سفارشات\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("تاریخچه همه سفارشات"));
 break; }
case 1: {
 //BA.debugLineNum = 269;BA.debugLine="get_pardakhti_order(id_user)";
_get_pardakhti_order(_id_user);
 //BA.debugLineNum = 270;BA.debugLine="Label3.Text=\"سفارشات در حال بررسی\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("سفارشات در حال بررسی"));
 break; }
case 2: {
 //BA.debugLineNum = 273;BA.debugLine="get_not_pardakht_order(id_user)";
_get_not_pardakht_order(_id_user);
 //BA.debugLineNum = 274;BA.debugLine="Label3.Text=\"سفارشات پرداخت نشده\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("سفارشات پرداخت نشده"));
 break; }
case 3: {
 //BA.debugLineNum = 276;BA.debugLine="get_send_order(id_user)";
_get_send_order(_id_user);
 //BA.debugLineNum = 277;BA.debugLine="Label3.Text=\"سفارشات ارسال شده\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("سفارشات ارسال شده"));
 break; }
case 4: {
 //BA.debugLineNum = 279;BA.debugLine="get_pay_location(id_user)";
_get_pay_location(_id_user);
 //BA.debugLineNum = 280;BA.debugLine="Label3.Text=\"سفارشات پرداخت در محل\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("سفارشات پرداخت در محل"));
 break; }
}
;
 //BA.debugLineNum = 283;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 255;BA.debugLine="njMenu.AddItem(Null, \"همه سفارشات\", Colors.B";
mostCurrent._njmenu._additem((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),"همه سفارشات",anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.Colors.White,(int) (1));
 //BA.debugLineNum = 256;BA.debugLine="njMenu.AddItem(Null, \"سفارشات در حال بررسی\", Co";
mostCurrent._njmenu._additem((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),"سفارشات در حال بررسی",anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.Colors.White,(int) (2));
 //BA.debugLineNum = 257;BA.debugLine="njMenu.AddItem(Null, \"سفارشات پرداخت نشده\", Co";
mostCurrent._njmenu._additem((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),"سفارشات پرداخت نشده",anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.Colors.White,(int) (3));
 //BA.debugLineNum = 258;BA.debugLine="njMenu.AddItem(Null, \"سفارشات ارسال شده\", Color";
mostCurrent._njmenu._additem((anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),"سفارشات ارسال شده",anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.Colors.White,(int) (4));
 //BA.debugLineNum = 259;BA.debugLine="njMenu.OpenMenu(\"Flip\")	'	Cascade";
mostCurrent._njmenu._openmenu("Flip");
 //BA.debugLineNum = 260;BA.debugLine="End Sub";
return "";
}
public static String  _db_connector(anywheresoftware.b4a.objects.collections.List _records,Object _tag) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.PanelWrapper _p1 = null;
String _stat_str = "";
anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _rich1 = null;
int _stat_color = 0;
int _stat = 0;
String _code_rah = "";
String[] _time_reg = null;
String[] _date = null;
String _clock = "";
anywheresoftware.b4a.student.PersianDate _daye_per = null;
String _url_pic = "";
 //BA.debugLineNum = 107;BA.debugLine="Sub db_connector(records As List,tag As Object)";
 //BA.debugLineNum = 108;BA.debugLine="Try";
try { //BA.debugLineNum = 109;BA.debugLine="Select tag";
switch (BA.switchObjectToInt(_tag,(Object)("get_time"),(Object)("get_all_order"),(Object)("update_order_pay"),(Object)("get_order_detils"),(Object)("disconnect"),(Object)("error"))) {
case 0: {
 break; }
case 1: {
 //BA.debugLineNum = 113;BA.debugLine="sv.Clear";
mostCurrent._sv._clear();
 //BA.debugLineNum = 114;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 116;BA.debugLine="For i=0 To records.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 117;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 118;BA.debugLine="Dim p1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 119;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 120;BA.debugLine="p1.LoadLayout(\"sv_history_order\")";
_p1.LoadLayout("sv_history_order",mostCurrent.activityBA);
 //BA.debugLineNum = 121;BA.debugLine="sv.Add(p1,sv_panel.Height,map1.Get(\"order_id";
mostCurrent._sv._add(_p1,mostCurrent._sv_panel.getHeight(),_map1.Get((Object)("order_id")));
 //BA.debugLineNum = 123;BA.debugLine="Dim stat_str As String";
_stat_str = "";
 //BA.debugLineNum = 124;BA.debugLine="Dim rich1 As RichString";
_rich1 = new anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString();
 //BA.debugLineNum = 125;BA.debugLine="Dim stat_color As Int";
_stat_color = 0;
 //BA.debugLineNum = 126;BA.debugLine="Dim stat As Int=map1.Get(\"status\")";
_stat = (int)(BA.ObjectToNumber(_map1.Get((Object)("status"))));
 //BA.debugLineNum = 127;BA.debugLine="Select stat";
switch (_stat) {
case 1: {
 //BA.debugLineNum = 129;BA.debugLine="stat_str=\"وضعیت سفارش: \" &  \"{C}\" & \"پرداخ";
_stat_str = "وضعیت سفارش: "+"{C}"+"پرداخت نشده"+"{C}";
 //BA.debugLineNum = 130;BA.debugLine="stat_color=0xFFEB1045";
_stat_color = (int) (0xffeb1045);
 break; }
case 2: {
 //BA.debugLineNum = 132;BA.debugLine="stat_str=\"وضعیت سفارش: \" &  \"{C}\" & \"پرداخ";
_stat_str = "وضعیت سفارش: "+"{C}"+"پرداخت شده و در حال بررسی"+"{C}";
 //BA.debugLineNum = 133;BA.debugLine="stat_color=0xFF09477F";
_stat_color = (int) (0xff09477f);
 break; }
case 3: {
 //BA.debugLineNum = 135;BA.debugLine="stat_str=\"وضعیت سفارش: \" &  \"{C}\" & \"ارسال";
_stat_str = "وضعیت سفارش: "+"{C}"+"ارسال شد"+"{C}";
 //BA.debugLineNum = 136;BA.debugLine="stat_color=0xFF135504";
_stat_color = (int) (0xff135504);
 break; }
case 4: {
 //BA.debugLineNum = 138;BA.debugLine="stat_str=\"وضعیت سفارش: \" &  \"{C}\" & \"پرداخ";
_stat_str = "وضعیت سفارش: "+"{C}"+"پرداخت در محل"+"{C}";
 //BA.debugLineNum = 139;BA.debugLine="stat_color=0xFFF89F0D";
_stat_color = (int) (0xfff89f0d);
 break; }
}
;
 //BA.debugLineNum = 141;BA.debugLine="rich1.Initialize(stat_str)";
_rich1.Initialize(BA.ObjectToCharSequence(_stat_str));
 //BA.debugLineNum = 142;BA.debugLine="rich1.Color2(stat_color,\"{C}\")";
_rich1.Color2(_stat_color,"{C}");
 //BA.debugLineNum = 143;BA.debugLine="lbl_stat.Text=rich1";
mostCurrent._lbl_stat.setText(BA.ObjectToCharSequence(_rich1.getObject()));
 //BA.debugLineNum = 145;BA.debugLine="Dim code_rah As String=map1.Get(\"code_rahgir";
_code_rah = BA.ObjectToString(_map1.Get((Object)("code_rahgiri")));
 //BA.debugLineNum = 146;BA.debugLine="If code_rah.Length > 2 Then	lbl_code.Text=\"پ";
if (_code_rah.length()>2) { 
mostCurrent._lbl_code.setText(BA.ObjectToCharSequence("پیگیری: "+_code_rah));};
 //BA.debugLineNum = 148;BA.debugLine="lbl_price.Text=\"مبلغ کل: \" & mony.number(map";
mostCurrent._lbl_price.setText(BA.ObjectToCharSequence("مبلغ کل: "+mostCurrent._mony._number(BA.ObjectToString(_map1.Get((Object)("amount_price"))))+" تومان"));
 //BA.debugLineNum = 150;BA.debugLine="Dim time_reg() As String";
_time_reg = new String[(int) (0)];
java.util.Arrays.fill(_time_reg,"");
 //BA.debugLineNum = 151;BA.debugLine="time_reg=Regex.Split(\" \",map1.Get(\"updated_a";
_time_reg = anywheresoftware.b4a.keywords.Common.Regex.Split(" ",BA.ObjectToString(_map1.Get((Object)("updated_at"))));
 //BA.debugLineNum = 153;BA.debugLine="Dim date() As String=Regex.Split(\"-\",time_re";
_date = anywheresoftware.b4a.keywords.Common.Regex.Split("-",_time_reg[(int) (0)]);
 //BA.debugLineNum = 155;BA.debugLine="Dim clock As String=time_reg(1)";
_clock = _time_reg[(int) (1)];
 //BA.debugLineNum = 156;BA.debugLine="Log(\"date:\" & time_reg(0) )";
anywheresoftware.b4a.keywords.Common.Log("date:"+_time_reg[(int) (0)]);
 //BA.debugLineNum = 157;BA.debugLine="Log(\"clock:\" & time_reg(1) )";
anywheresoftware.b4a.keywords.Common.Log("clock:"+_time_reg[(int) (1)]);
 //BA.debugLineNum = 159;BA.debugLine="Dim daye_per As PersianDate";
_daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 160;BA.debugLine="lbl_date.Text=\"تاریخ سفارش: \" & daye_per.get";
mostCurrent._lbl_date.setText(BA.ObjectToCharSequence("تاریخ سفارش: "+_daye_per.getDate((int)(Double.parseDouble(_date[(int) (0)])),(int)(Double.parseDouble(_date[(int) (1)])),(int)(Double.parseDouble(_date[(int) (2)])),"/")));
 }
};
 };
 //BA.debugLineNum = 165;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 break; }
case 2: {
 //BA.debugLineNum = 168;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 169;BA.debugLine="ToastMessageShow(\"با موفقیت انجام شد\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("با موفقیت انجام شد"),anywheresoftware.b4a.keywords.Common.True);
 break; }
case 3: {
 //BA.debugLineNum = 172;BA.debugLine="panel_history.RemoveAllViews";
mostCurrent._panel_history.RemoveAllViews();
 //BA.debugLineNum = 173;BA.debugLine="panel_history.AddView(sv2.AsView,0,0,panel_his";
mostCurrent._panel_history.AddView((android.view.View)(mostCurrent._sv2._asview().getObject()),(int) (0),(int) (0),mostCurrent._panel_history.getWidth(),mostCurrent._panel_history.getHeight());
 //BA.debugLineNum = 174;BA.debugLine="If records.Size > 0 Then";
if (_records.getSize()>0) { 
 //BA.debugLineNum = 175;BA.debugLine="For i=0 To records.Size-1";
{
final int step55 = 1;
final int limit55 = (int) (_records.getSize()-1);
for (_i = (int) (0) ; (step55 > 0 && _i <= limit55) || (step55 < 0 && _i >= limit55); _i = ((int)(0 + _i + step55)) ) {
 //BA.debugLineNum = 176;BA.debugLine="Dim map1 As Map=records.Get(i)";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
_map1.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_records.Get(_i)));
 //BA.debugLineNum = 177;BA.debugLine="Dim p1 As Panel";
_p1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 178;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 179;BA.debugLine="p1.LoadLayout(\"sv_sabad\")";
_p1.LoadLayout("sv_sabad",mostCurrent.activityBA);
 //BA.debugLineNum = 180;BA.debugLine="sv2.Add(p1,140dip,map1.Get(\"id\"))";
mostCurrent._sv2._add(_p1,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (140)),_map1.Get((Object)("id")));
 //BA.debugLineNum = 181;BA.debugLine="lbl_name.Text=map1.Get(\"name_kala\")";
mostCurrent._lbl_name.setText(BA.ObjectToCharSequence(_map1.Get((Object)("name_kala"))));
 //BA.debugLineNum = 183;BA.debugLine="Dim url_pic As String=map1.Get(\"pic\")";
_url_pic = BA.ObjectToString(_map1.Get((Object)("pic")));
 //BA.debugLineNum = 185;BA.debugLine="If url_pic.Trim.Contains(\"http://\")=False Th";
if (_url_pic.trim().contains("http://")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 186;BA.debugLine="url_pic=Starter.dir_root_image_file_thumnai";
_url_pic = mostCurrent._starter._dir_root_image_file_thumnail+_url_pic;
 }else {
 //BA.debugLineNum = 188;BA.debugLine="piccaso.Load(\"http\",url_pic).SkipMemoryCach";
mostCurrent._piccaso.Load(mostCurrent.activityBA,"http",_url_pic).SkipMemoryCache().Into(mostCurrent._img_kala);
 };
 //BA.debugLineNum = 191;BA.debugLine="lbl_price.Text=toman.number( map1.Get(\"price";
mostCurrent._lbl_price.setText(BA.ObjectToCharSequence(mostCurrent._toman._number(BA.ObjectToString(_map1.Get((Object)("price"))))+" تومان"));
 //BA.debugLineNum = 192;BA.debugLine="Label2.Text=map1.Get(\"quality\")";
mostCurrent._label2.setText(BA.ObjectToCharSequence(_map1.Get((Object)("quality"))));
 }
};
 };
 break; }
case 4: {
 //BA.debugLineNum = 197;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 198;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 5: {
 //BA.debugLineNum = 200;BA.debugLine="StartActivity(disconnect)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._disconnect.getObject()));
 //BA.debugLineNum = 201;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 202;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 break; }
}
;
 //BA.debugLineNum = 204;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 //BA.debugLineNum = 205;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e83) {
			processBA.setLastException(e83); //BA.debugLineNum = 207;BA.debugLine="ToastMessageShow(\"متاسفانه بارگذاری نشد.دوباره ت";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("متاسفانه بارگذاری نشد.دوباره تلاش کنید"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 208;BA.debugLine="progress_spot.DisMissDialog";
mostCurrent._progress_spot.DisMissDialog();
 };
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return "";
}
public static String  _footer_click() throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub footer_Click";
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _get_all_order(int _id) throws Exception{
 //BA.debugLineNum = 217;BA.debugLine="Sub get_all_order(id As Int)";
 //BA.debugLineNum = 218;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 219;BA.debugLine="connector.send_query($\"SELECT `order_payment`.*,`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+" ORDER BY  `id` DESC "),"get_all_order",(Object)(""));
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _get_not_pardakht_order(int _id) throws Exception{
 //BA.debugLineNum = 227;BA.debugLine="Sub get_not_pardakht_order(id As Int)";
 //BA.debugLineNum = 228;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 229;BA.debugLine="connector.send_query($\"SELECT `order_payment`.*,`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+" AND `order_payment`.`status` =1 ORDER BY  `id` DESC "),"get_all_order",(Object)(""));
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return "";
}
public static String  _get_order_detils(int _id) throws Exception{
 //BA.debugLineNum = 242;BA.debugLine="Sub get_order_detils(id As Int)";
 //BA.debugLineNum = 243;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 244;BA.debugLine="connector.send_query($\"SELECT DISTINCT  `kala`.`i";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT DISTINCT  `kala`.`id` ,  `kala`.`name_kala`,`kala`.`pic` ,  `order_detail`. * ,  `order`.`user_id`\n"+"FROM  `kala` \n"+"LEFT JOIN  `order_detail` ON  `kala`.`id` =  `order_detail`.`code_kala` \n"+"LEFT JOIN  `order` ON  `order_detail`.`order_id` =  `order`.`id` \n"+"WHERE  `order_detail`.`order_id` ="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+"\n"+"ORDER BY  `order`.`id` DESC \n"+"LIMIT 0 , 30\n"+" "),"get_order_detils",(Object)(""));
 //BA.debugLineNum = 252;BA.debugLine="End Sub";
return "";
}
public static String  _get_pardakhti_order(int _id) throws Exception{
 //BA.debugLineNum = 222;BA.debugLine="Sub get_pardakhti_order(id As Int)";
 //BA.debugLineNum = 223;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 224;BA.debugLine="connector.send_query($\"SELECT `order_payment`.*,`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+" AND `order_payment`.`status` =2 ORDER BY  `id` DESC "),"get_all_order",(Object)(""));
 //BA.debugLineNum = 225;BA.debugLine="End Sub";
return "";
}
public static String  _get_pay_location(int _id) throws Exception{
 //BA.debugLineNum = 237;BA.debugLine="Sub get_pay_location(id As Int)";
 //BA.debugLineNum = 238;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 239;BA.debugLine="connector.send_query($\"SELECT `order_payment`.*,`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+" AND `order_payment`.`status` =4 ORDER BY  `id` DESC "),"get_all_order",(Object)(""));
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return "";
}
public static String  _get_send_order(int _id) throws Exception{
 //BA.debugLineNum = 232;BA.debugLine="Sub get_send_order(id As Int)";
 //BA.debugLineNum = 233;BA.debugLine="progress_spot.ShowDialog";
mostCurrent._progress_spot.ShowDialog();
 //BA.debugLineNum = 234;BA.debugLine="connector.send_query($\"SELECT `order_payment`.*,`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_id))+" AND `order_payment`.`status` =3 ORDER BY  `id` DESC "),"get_all_order",(Object)(""));
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Dim piccaso As Hitex_Picasso";
mostCurrent._piccaso = new wir.hitex.recycler.Hitex_Picasso();
 //BA.debugLineNum = 14;BA.debugLine="Dim mytoastMessage As MyToastMessageShow";
mostCurrent._mytoastmessage = new b4a.example.mytoastmessageshow();
 //BA.debugLineNum = 15;BA.debugLine="Dim effect_btn1 As RippleView";
mostCurrent._effect_btn1 = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private progress_spot As SpotsDialog";
mostCurrent._progress_spot = new dmax.dialog.Spotsd();
 //BA.debugLineNum = 17;BA.debugLine="Dim sv ,sv2 As CustomListView";
mostCurrent._sv = new hpksoftware.setak.customlistview();
mostCurrent._sv2 = new hpksoftware.setak.customlistview();
 //BA.debugLineNum = 18;BA.debugLine="Dim mony As money";
mostCurrent._mony = new b4a.example.money();
 //BA.debugLineNum = 19;BA.debugLine="Dim njMenu As AnimatedSlidingMenu 'menu top";
mostCurrent._njmenu = new njdude.animated.sliding.menu.animatedslidingmenu();
 //BA.debugLineNum = 20;BA.debugLine="Private scrol_main As ScrollView";
mostCurrent._scrol_main = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private btn_back As Button";
mostCurrent._btn_back = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private btn_search As Button";
mostCurrent._btn_search = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private btn_share As Button";
mostCurrent._btn_share = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private btn_sabad As Button";
mostCurrent._btn_sabad = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private sv_panel As Panel";
mostCurrent._sv_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private lbl_code As Label";
mostCurrent._lbl_code = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private lbl_date As Label";
mostCurrent._lbl_date = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private lbl_price As Label";
mostCurrent._lbl_price = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lbl_stat As Label";
mostCurrent._lbl_stat = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private panel_history As Panel";
mostCurrent._panel_history = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim toman As money";
mostCurrent._toman = new b4a.example.money();
 //BA.debugLineNum = 36;BA.debugLine="Private lbl_name As Label";
mostCurrent._lbl_name = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim toast As TatyToast";
mostCurrent._toast = new com.sdsmdg.tastytoast.Tasty();
 //BA.debugLineNum = 38;BA.debugLine="Private img_kala As ImageView";
mostCurrent._img_kala = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_menu() throws Exception{
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _myfont = null;
 //BA.debugLineNum = 101;BA.debugLine="Sub initialize_menu";
 //BA.debugLineNum = 103;BA.debugLine="Dim myfont As Typeface=Starter.font_body";
_myfont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_myfont = mostCurrent._starter._font_body;
 //BA.debugLineNum = 104;BA.debugLine="njMenu.Initialize(Activity, Me, \"\", \"AnimatedMenu";
mostCurrent._njmenu._initialize(mostCurrent.activityBA,mostCurrent._activity,history_order.getObject(),"","AnimatedMenu","T",mostCurrent._panel1.getHeight(),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.Colors.White,_myfont,(int) (14),"right",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 105;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public server_mysql As String";
_server_mysql = "";
 //BA.debugLineNum = 8;BA.debugLine="Public id_user As Int";
_id_user = 0;
 //BA.debugLineNum = 9;BA.debugLine="Public is_admin As Boolean=False";
_is_admin = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _sv1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Sub sv1_ItemClick(Position As Int, Value As Object";
 //BA.debugLineNum = 315;BA.debugLine="Log(Value)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_value));
 //BA.debugLineNum = 316;BA.debugLine="get_order_detils(Value)";
_get_order_detils((int)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 319;BA.debugLine="End Sub";
return "";
}
public static String  _sv1_itemlongclick(int _position,Object _value) throws Exception{
String[] _stu = null;
int _st = 0;
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _d = 0;
int _d2 = 0;
 //BA.debugLineNum = 289;BA.debugLine="Sub sv1_ItemLongClick (Position As Int, Value As O";
 //BA.debugLineNum = 290;BA.debugLine="connector.Initialize2(Me)";
mostCurrent._connector._initialize2(mostCurrent.activityBA,history_order.getObject());
 //BA.debugLineNum = 291;BA.debugLine="Dim stu() As String=Regex.Split(\";\",Value)";
_stu = anywheresoftware.b4a.keywords.Common.Regex.Split(";",BA.ObjectToString(_value));
 //BA.debugLineNum = 292;BA.debugLine="Dim st As Int=stu(1)";
_st = (int)(Double.parseDouble(_stu[(int) (1)]));
 //BA.debugLineNum = 293;BA.debugLine="If is_admin=True Then";
if (_is_admin==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 294;BA.debugLine="If st<>1 And st<>3 Then";
if (_st!=1 && _st!=3) { 
 //BA.debugLineNum = 295;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 296;BA.debugLine="Pd=function.style_msgbox(\"سوال\",\"آیا این محصول";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"سوال","آیا این محصول را ارسال کردید؟","آره","نه","");
 //BA.debugLineNum = 297;BA.debugLine="Dim d As Int = Pd.Create";
_d = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 298;BA.debugLine="If d=DialogResponse.POSITIVE Then";
if (_d==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 300;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 301;BA.debugLine="Pd=function.style_msgbox(\"\",\"مطمعنی؟\",\"اره باب";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"","مطمعنی؟","اره بابا","نه شرمنده","");
 //BA.debugLineNum = 302;BA.debugLine="Dim d2 As Int = Pd.Create";
_d2 = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 303;BA.debugLine="If d2=DialogResponse.POSITIVE Then";
if (_d2==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 304;BA.debugLine="connector.progress_circle_visible(True)";
mostCurrent._connector._progress_circle_visible(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 305;BA.debugLine="connector.send_query($\"UPDATE `order_payment`";
mostCurrent._connector._send_query(mostCurrent.activityBA,("UPDATE `order_payment` SET `status`=3 WHERE `order_id`="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_stu[(int) (0)]))+""),"update_order_pay",(Object)(""));
 };
 };
 };
 };
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static String  _sv2_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 285;BA.debugLine="Sub sv2_ItemClick(Position As Int, Value As Object";
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
return "";
}
}
