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

public class insert_noskhe extends Activity implements B4AActivity{
	public static insert_noskhe mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "hpksoftware.setak", "hpksoftware.setak.insert_noskhe");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (insert_noskhe).");
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
		activityBA = new BA(this, layout, processBA, "hpksoftware.setak", "hpksoftware.setak.insert_noskhe");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.insert_noskhe", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (insert_noskhe) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (insert_noskhe) Resume **");
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
		return insert_noskhe.class;
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
        BA.LogInfo("** Activity (insert_noskhe) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (insert_noskhe) Resume **");
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
public anywheresoftware.b4a.student.PersianDate _daye_per = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spiner_daroo = null;
public anywheresoftware.b4a.objects.LabelWrapper _txt_info = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbl_clock = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrol_main = null;
public anywheresoftware.b4a.objects.LabelWrapper _footer = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_history = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public hpk.software.label.line_height _line_height = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog _input_dialog = null;
public anywheresoftware.b4a.sql.SQL _sql1 = null;
public anywheresoftware.b4a.sql.SQL.CursorWrapper _cr1 = null;
public static int _hiden_id_daroo = 0;
public anywheresoftware.b4a.objects.EditTextWrapper _txt_search = null;
public anywheresoftware.b4a.objects.IME _ime1 = null;
public de.amberhome.objects.appcompat.ACSpinnerWrapper _spiner_date = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel_date = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
public hpksoftware.setak.main _main = null;
public hpksoftware.setak.starter _starter = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.drawable.ColorDrawable _color_header = null;
int _i = 0;
boolean _is_now_date = false;
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 43;BA.debugLine="input_dialog.Hint=\"Password\"";
mostCurrent._input_dialog.setHint("Password");
 //BA.debugLineNum = 44;BA.debugLine="input_dialog.HintColor=Colors.Gray";
mostCurrent._input_dialog.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 45;BA.debugLine="input_dialog.InputType=input_dialog.INPUT_TYPE_DE";
mostCurrent._input_dialog.setInputType(mostCurrent._input_dialog.INPUT_TYPE_DECIMAL_NUMBERS);
 //BA.debugLineNum = 46;BA.debugLine="input_dialog.PasswordMode=True";
mostCurrent._input_dialog.setPasswordMode(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 48;BA.debugLine="If input_dialog.Show(\"لطفا گذرواژه خود را وارد کن";
if (mostCurrent._input_dialog.Show("لطفا گذرواژه خود را وارد کنید","ورود پزشک","ورود","بستن","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null))==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 49;BA.debugLine="If input_dialog.Input=123456 Then";
if ((mostCurrent._input_dialog.getInput()).equals(BA.NumberToString(123456))) { 
 //BA.debugLineNum = 51;BA.debugLine="If File.Exists(Starter.rute_file_app,\"db.db\") =";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._starter._rute_file_app,"db.db")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 52;BA.debugLine="If sql1.IsInitialized=False Then sql1.Initiali";
if (mostCurrent._sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._sql1.Initialize(mostCurrent._starter._rute_file_app,"db.db",anywheresoftware.b4a.keywords.Common.True);};
 };
 //BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"scrol_layout\")";
mostCurrent._activity.LoadLayout("scrol_layout",mostCurrent.activityBA);
 //BA.debugLineNum = 56;BA.debugLine="Dim color_header As ColorDrawable";
_color_header = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 57;BA.debugLine="color_header.Initialize(Starter.color_base,0)";
_color_header.Initialize(mostCurrent._starter._color_base,(int) (0));
 //BA.debugLineNum = 58;BA.debugLine="Panel1.Background=color_header";
mostCurrent._panel1.setBackground((android.graphics.drawable.Drawable)(_color_header.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="Label3.Text=\"درج نسخه توسط پزشک\"";
mostCurrent._label3.setText(BA.ObjectToCharSequence("درج نسخه توسط پزشک"));
 //BA.debugLineNum = 60;BA.debugLine="scrol_main.Panel.LoadLayout(\"insert_noskhe\")";
mostCurrent._scrol_main.getPanel().LoadLayout("insert_noskhe",mostCurrent.activityBA);
 //BA.debugLineNum = 65;BA.debugLine="Log(	\"height= \" & 	line_height.Initialize(txt_i";
anywheresoftware.b4a.keywords.Common.Log("height= "+mostCurrent._line_height._initialize(processBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._txt_info.getObject())),(float) (1.5)));
 //BA.debugLineNum = 67;BA.debugLine="footer.Visible=False";
mostCurrent._footer.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 68;BA.debugLine="scrol_main.Height=scrol_main.Height+footer.Heig";
mostCurrent._scrol_main.setHeight((int) (mostCurrent._scrol_main.getHeight()+mostCurrent._footer.getHeight()));
 //BA.debugLineNum = 73;BA.debugLine="Try";
try { //BA.debugLineNum = 74;BA.debugLine="spiner_daroo.Clear";
mostCurrent._spiner_daroo.Clear();
 //BA.debugLineNum = 75;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From daroo\"$)";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From daroo"))));
 //BA.debugLineNum = 76;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step22 = 1;
final int limit22 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step22 > 0 && _i <= limit22) || (step22 < 0 && _i >= limit22); _i = ((int)(0 + _i + step22)) ) {
 //BA.debugLineNum = 77;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 78;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 79;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id")));
 //BA.debugLineNum = 80;BA.debugLine="lbl_clock.Text=cr1.GetString(\"clock\")";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("clock")));
 //BA.debugLineNum = 81;BA.debugLine="txt_info.Text=cr1.GetString(\"info\")";
mostCurrent._txt_info.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("info")));
 //BA.debugLineNum = 83;BA.debugLine="txt_info.Height=library.get_height_label(txt";
mostCurrent._txt_info.setHeight((int) (mostCurrent._library._get_height_label(mostCurrent.activityBA,mostCurrent._txt_info,mostCurrent._txt_info.getText())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))));
 //BA.debugLineNum = 84;BA.debugLine="If txt_info.Height< 35%y Then txt_info.Heigh";
if (mostCurrent._txt_info.getHeight()<anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA)) { 
mostCurrent._txt_info.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA));};
 //BA.debugLineNum = 86;BA.debugLine="Label4.Top=txt_info.Top + txt_info.Height +";
mostCurrent._label4.setTop((int) (mostCurrent._txt_info.getTop()+mostCurrent._txt_info.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 87;BA.debugLine="ListView1.Top=Label4.Top + Label4.Height";
mostCurrent._listview1.setTop((int) (mostCurrent._label4.getTop()+mostCurrent._label4.getHeight()));
 //BA.debugLineNum = 88;BA.debugLine="scrol_main.Panel.Height=ListView1.Top + List";
mostCurrent._scrol_main.getPanel().setHeight((int) (mostCurrent._listview1.getTop()+mostCurrent._listview1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 };
 //BA.debugLineNum = 90;BA.debugLine="spiner_daroo.Add(cr1.GetString(\"name\")	)";
mostCurrent._spiner_daroo.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")));
 }
};
 } 
       catch (Exception e37) {
			processBA.setLastException(e37); //BA.debugLineNum = 93;BA.debugLine="Msgbox(LastException.Message,\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 98;BA.debugLine="Try";
try { //BA.debugLineNum = 99;BA.debugLine="Dim is_now_date As Boolean=False";
_is_now_date = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 100;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe gr";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe group by date_noskhe"))));
 //BA.debugLineNum = 101;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step42 = 1;
final int limit42 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step42 > 0 && _i <= limit42) || (step42 < 0 && _i >= limit42); _i = ((int)(0 + _i + step42)) ) {
 //BA.debugLineNum = 102;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 103;BA.debugLine="spiner_date.Add(cr1.GetString(\"date_noskhe\")";
mostCurrent._spiner_date.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("date_noskhe")));
 //BA.debugLineNum = 104;BA.debugLine="spiner_date.SelectedIndex=i";
mostCurrent._spiner_date.setSelectedIndex(_i);
 //BA.debugLineNum = 105;BA.debugLine="If spiner_date.SelectedItem=daye_per.getDate(";
if ((mostCurrent._spiner_date.getSelectedItem()).equals(mostCurrent._daye_per.getDate((int) (0),(int) (0),(int) (0),"/"))) { 
_is_now_date = anywheresoftware.b4a.keywords.Common.True;};
 }
};
 //BA.debugLineNum = 107;BA.debugLine="spiner_date.SelectedIndex=spiner_date.Size-1";
mostCurrent._spiner_date.setSelectedIndex((int) (mostCurrent._spiner_date.getSize()-1));
 //BA.debugLineNum = 109;BA.debugLine="If is_now_date=False Then spiner_date.Add(	day";
if (_is_now_date==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._spiner_date.Add(BA.ObjectToCharSequence(mostCurrent._daye_per.getDate((int) (0),(int) (0),(int) (0),"/")));};
 } 
       catch (Exception e51) {
			processBA.setLastException(e51); //BA.debugLineNum = 111;BA.debugLine="Msgbox(LastException.Message,\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 116;BA.debugLine="ListView1.SingleLineLayout.ItemHeight=40dip";
mostCurrent._listview1.getSingleLineLayout().setItemHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 117;BA.debugLine="ListView1.SingleLineLayout.Label.Height=ListVie";
mostCurrent._listview1.getSingleLineLayout().Label.setHeight(mostCurrent._listview1.getSingleLineLayout().getItemHeight());
 //BA.debugLineNum = 119;BA.debugLine="ListView1.SingleLineLayout.Label.TextColor=Colo";
mostCurrent._listview1.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 120;BA.debugLine="ListView1.SingleLineLayout.Label.TextSize=14";
mostCurrent._listview1.getSingleLineLayout().Label.setTextSize((float) (14));
 //BA.debugLineNum = 121;BA.debugLine="ListView1.SingleLineLayout.Label.Gravity=Gravit";
mostCurrent._listview1.getSingleLineLayout().Label.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.CENTER_HORIZONTAL+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 122;BA.debugLine="ListView1.SingleLineLayout.Label.Typeface=Typef";
mostCurrent._listview1.getSingleLineLayout().Label.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iransans Bold.ttf"));
 //BA.debugLineNum = 126;BA.debugLine="Try";
try { //BA.debugLineNum = 127;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 128;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe Le";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"'"))));
 //BA.debugLineNum = 129;BA.debugLine="For i=0 To cr1.RowCount-1";
{
final int step62 = 1;
final int limit62 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step62 > 0 && _i <= limit62) || (step62 < 0 && _i >= limit62); _i = ((int)(0 + _i + step62)) ) {
 //BA.debugLineNum = 130;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 131;BA.debugLine="ListView1.AddSingleLine2(	cr1.GetString(\"name";
mostCurrent._listview1.AddSingleLine2(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")),(Object)(mostCurrent._cr1.GetString("id_daroo")));
 }
};
 //BA.debugLineNum = 134;BA.debugLine="ListView1.Height=(cr1.RowCount * 40dip)";
mostCurrent._listview1.setHeight((int) ((mostCurrent._cr1.getRowCount()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)))));
 //BA.debugLineNum = 135;BA.debugLine="scrol_main.Panel.Height=ListView1.Top + ListVi";
mostCurrent._scrol_main.getPanel().setHeight((int) (mostCurrent._listview1.getTop()+mostCurrent._listview1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 } 
       catch (Exception e69) {
			processBA.setLastException(e69); //BA.debugLineNum = 138;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 };
 //BA.debugLineNum = 145;BA.debugLine="ime1.Initialize(\"ime1\")";
mostCurrent._ime1.Initialize("ime1");
 //BA.debugLineNum = 147;BA.debugLine="ime1.HideKeyboard";
mostCurrent._ime1.HideKeyboard(mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 156;BA.debugLine="ToastMessageShow(\"گذرواژه پزشک اشتباه است\",True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("گذرواژه پزشک اشتباه است"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 157;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else {
 //BA.debugLineNum = 160;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 170;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 166;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _btn_add_click() throws Exception{
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _i = 0;
 //BA.debugLineNum = 208;BA.debugLine="Sub btn_add_Click";
 //BA.debugLineNum = 209;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 210;BA.debugLine="Pd=function.style_msgbox(\"پیام سیستم\",\"آیا میخواه";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"پیام سیستم","آیا میخواهید به نسخه بیمار اضافه کنید؟","بلی","خیر","");
 //BA.debugLineNum = 211;BA.debugLine="Dim i As Int = Pd.Create";
_i = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 212;BA.debugLine="If i=DialogResponse.POSITIVE Then";
if (_i==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 215;BA.debugLine="Try";
try { //BA.debugLineNum = 216;BA.debugLine="sql1.ExecNonQuery($\"INSERT INTO noskhe(id_daroo";
mostCurrent._sql1.ExecNonQuery(("INSERT INTO noskhe(id_daroo,clock,date_noskhe) VALUES("+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_hiden_id_daroo))+",'"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._lbl_clock.getText()))+"','"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"')"));
 //BA.debugLineNum = 218;BA.debugLine="Try";
try { //BA.debugLineNum = 219;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 220;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe Le";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"'"))));
 //BA.debugLineNum = 221;BA.debugLine="For i=0 To cr1.RowCount-1";
{
final int step10 = 1;
final int limit10 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 222;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 223;BA.debugLine="ListView1.AddSingleLine2(	cr1.GetString(\"name";
mostCurrent._listview1.AddSingleLine2(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")),(Object)(mostCurrent._cr1.GetString("id_daroo")));
 }
};
 //BA.debugLineNum = 226;BA.debugLine="ListView1.Height=(cr1.RowCount * 40dip)";
mostCurrent._listview1.setHeight((int) ((mostCurrent._cr1.getRowCount()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)))));
 //BA.debugLineNum = 227;BA.debugLine="scrol_main.Panel.Height=ListView1.Top + ListVi";
mostCurrent._scrol_main.getPanel().setHeight((int) (mostCurrent._listview1.getTop()+mostCurrent._listview1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 } 
       catch (Exception e17) {
			processBA.setLastException(e17); //BA.debugLineNum = 230;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage());
 //BA.debugLineNum = 231;BA.debugLine="Dim Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 232;BA.debugLine="Pd=function.style_msgbox(\"پیام سیستم\",\"این دار";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"پیام سیستم","این دارو از قبل در نسخه موجود است","","بسیار خب","");
 //BA.debugLineNum = 233;BA.debugLine="Pd.Create";
_pd.Create(mostCurrent.activityBA);
 };
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); };
 };
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}
public static String  _btn_back_click() throws Exception{
 //BA.debugLineNum = 247;BA.debugLine="Sub btn_back_Click";
 //BA.debugLineNum = 248;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim daye_per As PersianDate";
mostCurrent._daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 13;BA.debugLine="Private spiner_daroo As ACSpinner";
mostCurrent._spiner_daroo = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private txt_info As Label";
mostCurrent._txt_info = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lbl_clock As Label";
mostCurrent._lbl_clock = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private scrol_main As ScrollView";
mostCurrent._scrol_main = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private footer As Label";
mostCurrent._footer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private panel_history As Panel";
mostCurrent._panel_history = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim line_height As line_height";
mostCurrent._line_height = new hpk.software.label.line_height();
 //BA.debugLineNum = 28;BA.debugLine="Dim input_dialog As InputDialog";
mostCurrent._input_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 30;BA.debugLine="Dim sql1 As SQL";
mostCurrent._sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 31;BA.debugLine="Dim cr1 As Cursor";
mostCurrent._cr1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim hiden_id_daroo As Int";
_hiden_id_daroo = 0;
 //BA.debugLineNum = 33;BA.debugLine="Private txt_search As EditText";
mostCurrent._txt_search = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim ime1 As IME";
mostCurrent._ime1 = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 37;BA.debugLine="Private spiner_date As ACSpinner";
mostCurrent._spiner_date = new de.amberhome.objects.appcompat.ACSpinnerWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private Panel_date As Panel";
mostCurrent._panel_date = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _ime1_heightchanged(int _newheight,int _oldheight) throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Sub ime1_HeightChanged (NewHeight As Int, OldHeigh";
 //BA.debugLineNum = 175;BA.debugLine="scrol_main.Panel.Height=NewHeight";
mostCurrent._scrol_main.getPanel().setHeight(_newheight);
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public static String  _lbl_edit_click() throws Exception{
 //BA.debugLineNum = 199;BA.debugLine="Sub lbl_edit_Click";
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemlongclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.ParsMSGBOX _pd = null;
int _i = 0;
 //BA.debugLineNum = 269;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
 //BA.debugLineNum = 270;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 271;BA.debugLine="Pd=function.style_msgbox(\"پیام سیستم\",\"آیا میخواه";
_pd = mostCurrent._function._style_msgbox(mostCurrent.activityBA,"پیام سیستم","آیا میخواهید این دارو را از نسخه بیمار حذف کنید؟","بلی","خیر","");
 //BA.debugLineNum = 272;BA.debugLine="Dim i As Int = Pd.Create";
_i = _pd.Create(mostCurrent.activityBA);
 //BA.debugLineNum = 273;BA.debugLine="If i=DialogResponse.POSITIVE Then";
if (_i==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 274;BA.debugLine="Try";
try { //BA.debugLineNum = 275;BA.debugLine="sql1.ExecNonQuery2($\"Delete From noskhe Where i";
mostCurrent._sql1.ExecNonQuery2(("Delete From noskhe Where id_daroo=? AND date_noskhe='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"'"),anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{_value}));
 //BA.debugLineNum = 276;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 277;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe Lef";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"'"))));
 //BA.debugLineNum = 278;BA.debugLine="For i=0 To cr1.RowCount-1";
{
final int step9 = 1;
final int limit9 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9); _i = ((int)(0 + _i + step9)) ) {
 //BA.debugLineNum = 279;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 280;BA.debugLine="ListView1.AddSingleLine2(	cr1.GetString(\"name\"";
mostCurrent._listview1.AddSingleLine2(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")),(Object)(mostCurrent._cr1.GetString("id_daroo")));
 }
};
 //BA.debugLineNum = 282;BA.debugLine="ListView1.Height=(cr1.RowCount * 40dip)";
mostCurrent._listview1.setHeight((int) ((mostCurrent._cr1.getRowCount()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)))));
 //BA.debugLineNum = 283;BA.debugLine="scrol_main.Panel.Height=ListView1.Top + ListVie";
mostCurrent._scrol_main.getPanel().setHeight((int) (mostCurrent._listview1.getTop()+mostCurrent._listview1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 } 
       catch (Exception e16) {
			processBA.setLastException(e16); //BA.debugLineNum = 285;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 };
 //BA.debugLineNum = 288;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _spiner_daroo_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub spiner_daroo_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 180;BA.debugLine="Try";
try { //BA.debugLineNum = 181;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From daroo where";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From daroo where name='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",_value)+"'"))));
 } 
       catch (Exception e4) {
			processBA.setLastException(e4); //BA.debugLineNum = 183;BA.debugLine="Msgbox(LastException.Message,\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 186;BA.debugLine="cr1.Position = 0";
mostCurrent._cr1.setPosition((int) (0));
 //BA.debugLineNum = 187;BA.debugLine="lbl_clock.Text=cr1.GetString(\"clock\")";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("clock")));
 //BA.debugLineNum = 188;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id")));
 //BA.debugLineNum = 189;BA.debugLine="txt_info.Text=cr1.GetString(\"info\")";
mostCurrent._txt_info.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("info")));
 //BA.debugLineNum = 191;BA.debugLine="txt_info.Height=library.get_height_label(txt_info";
mostCurrent._txt_info.setHeight((int) (mostCurrent._library._get_height_label(mostCurrent.activityBA,mostCurrent._txt_info,mostCurrent._txt_info.getText())+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6))));
 //BA.debugLineNum = 192;BA.debugLine="If txt_info.Height< 35%y Then txt_info.Height=35%";
if (mostCurrent._txt_info.getHeight()<anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA)) { 
mostCurrent._txt_info.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA));};
 //BA.debugLineNum = 194;BA.debugLine="Label4.Top=txt_info.Top + txt_info.Height + 5dip";
mostCurrent._label4.setTop((int) (mostCurrent._txt_info.getTop()+mostCurrent._txt_info.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 195;BA.debugLine="ListView1.Top=Label4.Top + Label4.Height";
mostCurrent._listview1.setTop((int) (mostCurrent._label4.getTop()+mostCurrent._label4.getHeight()));
 //BA.debugLineNum = 196;BA.debugLine="scrol_main.Panel.Height=ListView1.Top + ListView1";
mostCurrent._scrol_main.getPanel().setHeight((int) (mostCurrent._listview1.getTop()+mostCurrent._listview1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 197;BA.debugLine="End Sub";
return "";
}
public static String  _spiner_date_itemclick(int _position,Object _value) throws Exception{
int _i = 0;
 //BA.debugLineNum = 290;BA.debugLine="Sub spiner_date_ItemClick (Position As Int, Value";
 //BA.debugLineNum = 291;BA.debugLine="Try";
try { //BA.debugLineNum = 292;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 293;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From noskhe Left";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._spiner_date.getSelectedItem()))+"'"))));
 //BA.debugLineNum = 294;BA.debugLine="For i=0 To cr1.RowCount-1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 295;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 296;BA.debugLine="ListView1.AddSingleLine2(	cr1.GetString(\"name\")";
mostCurrent._listview1.AddSingleLine2(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")),(Object)(mostCurrent._cr1.GetString("id_daroo")));
 }
};
 //BA.debugLineNum = 298;BA.debugLine="ListView1.Height=(cr1.RowCount * 40dip)";
mostCurrent._listview1.setHeight((int) ((mostCurrent._cr1.getRowCount()*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)))));
 //BA.debugLineNum = 299;BA.debugLine="scrol_main.Panel.Height=ListView1.Top + ListView";
mostCurrent._scrol_main.getPanel().setHeight((int) (mostCurrent._listview1.getTop()+mostCurrent._listview1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 301;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 303;BA.debugLine="End Sub";
return "";
}
public static String  _txt_search_textchanged(String _old,String _new) throws Exception{
int _i = 0;
 //BA.debugLineNum = 251;BA.debugLine="Sub txt_search_TextChanged (Old As String, New As";
 //BA.debugLineNum = 253;BA.debugLine="Try";
try { //BA.debugLineNum = 254;BA.debugLine="cr1 = sql1.ExecQuery($\"Select * From daroo where";
mostCurrent._cr1.setObject((android.database.Cursor)(mostCurrent._sql1.ExecQuery(("Select * From daroo where name like '%"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_new))+"%'"))));
 //BA.debugLineNum = 255;BA.debugLine="spiner_daroo.Clear";
mostCurrent._spiner_daroo.Clear();
 //BA.debugLineNum = 256;BA.debugLine="For i = 0 To cr1.RowCount - 1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._cr1.getRowCount()-1);
for (_i = (int) (0) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 257;BA.debugLine="cr1.Position = i";
mostCurrent._cr1.setPosition(_i);
 //BA.debugLineNum = 258;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 259;BA.debugLine="hiden_id_daroo=cr1.GetString(\"id\")";
_hiden_id_daroo = (int)(Double.parseDouble(mostCurrent._cr1.GetString("id")));
 //BA.debugLineNum = 260;BA.debugLine="lbl_clock.Text=cr1.GetString(\"clock\")";
mostCurrent._lbl_clock.setText(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("clock")));
 };
 //BA.debugLineNum = 262;BA.debugLine="spiner_daroo.Add(cr1.GetString(\"name\")	)";
mostCurrent._spiner_daroo.Add(BA.ObjectToCharSequence(mostCurrent._cr1.GetString("name")));
 }
};
 } 
       catch (Exception e13) {
			processBA.setLastException(e13); //BA.debugLineNum = 265;BA.debugLine="Msgbox(LastException.Message,\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return "";
}
}
