package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends  android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "hpksoftware.setak", "hpksoftware.setak.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("** Service (starter) Create **");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			if (ServiceHelper.StarterHelper.waitForLayout != null)
				BA.handler.post(ServiceHelper.StarterHelper.waitForLayout);
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA))
			handleStart(intent);
		else {
			ServiceHelper.StarterHelper.waitForLayout = new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
				}
			};
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        BA.LogInfo("** Service (starter) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
        processBA.runHook("ondestroy", this, null);
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static String _dn = "";
public static String _du = "";
public static String _dp = "";
public static String _root_site = "";
public static String _dir_root_image_file_thumnail = "";
public static String _dir_root_image_file_main = "";
public static String _dir_root_image_file_slideshow = "";
public static String _server_mysql = "";
public static String _dir_root_image_file_detailes_tumnail = "";
public static String _dir_root_image_file_detailes_main = "";
public static String _dir_root_image_file_brand = "";
public static String _dir_root_image_file_cat = "";
public static String _server_address_send_forgetpassword = "";
public static String _server_get_time = "";
public static String _filename_user = "";
public static String _filename_sabad = "";
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _error_image = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _progress_image = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font_awsome = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font_body = null;
public static String _address_payment = "";
public static String _address_send_sms = "";
public static String _username_sms = "";
public static String _password_sms = "";
public static String _api_key_sms = "";
public static String[] _sender_sms = null;
public static String _apk_name_for_dowanload = "";
public static int _color_base = 0;
public static int _timer_slideshow = 0;
public static String _addres_send_pushe = "";
public static boolean _ckeck_app_access = false;
public static boolean _enable_push = false;
public static String _boardurl = "";
public static String _senderid = "";
public static String _rute_file_app = "";
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
public hpksoftware.setak.main _main = null;
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
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 100;BA.debugLine="Log(Error )";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(_error));
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return false;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Public dn As String=\"cp28393_setak\"";
_dn = "cp28393_setak";
 //BA.debugLineNum = 9;BA.debugLine="Public du As String=\"cp28393_hpk\"";
_du = "cp28393_hpk";
 //BA.debugLineNum = 10;BA.debugLine="Public dp As String=\"147963\"'+_Q$b6#rEQHR";
_dp = "147963";
 //BA.debugLineNum = 14;BA.debugLine="Public root_site As String=\"http://setakteb.ir\"";
_root_site = "http://setakteb.ir";
 //BA.debugLineNum = 16;BA.debugLine="Public dir_root_image_file_thumnail As String=roo";
_dir_root_image_file_thumnail = _root_site+"/images/shop/product/thumnail/";
 //BA.debugLineNum = 17;BA.debugLine="Public dir_root_image_file_main As String=root_si";
_dir_root_image_file_main = _root_site+"/images/shop/product/main/";
 //BA.debugLineNum = 19;BA.debugLine="Public dir_root_image_file_slideshow As String=ro";
_dir_root_image_file_slideshow = _root_site+"/images/shop/slideshow-image/";
 //BA.debugLineNum = 21;BA.debugLine="Public server_mysql As String = root_site  & \"/se";
_server_mysql = _root_site+"/server/database/connector.php";
 //BA.debugLineNum = 23;BA.debugLine="Public dir_root_image_file_detailes_tumnail As St";
_dir_root_image_file_detailes_tumnail = _root_site+"/images/shop/product-details/tumnail/";
 //BA.debugLineNum = 24;BA.debugLine="Public dir_root_image_file_detailes_main As Strin";
_dir_root_image_file_detailes_main = _root_site+"/images/shop/product-details/main/";
 //BA.debugLineNum = 26;BA.debugLine="Public dir_root_image_file_brand As String=root_s";
_dir_root_image_file_brand = _root_site+"/images/shop/brand-image/";
 //BA.debugLineNum = 27;BA.debugLine="Public dir_root_image_file_cat As String=root_sit";
_dir_root_image_file_cat = _root_site+"/images/shop/category-image/";
 //BA.debugLineNum = 29;BA.debugLine="Public server_address_send_ForgetPassword As Stri";
_server_address_send_forgetpassword = _root_site+"/server/forget_password/index.php";
 //BA.debugLineNum = 30;BA.debugLine="Public server_get_time As String=root_site  & \"/s";
_server_get_time = _root_site+"/server/get_time.php";
 //BA.debugLineNum = 34;BA.debugLine="Public filename_user As String=\"setak\"";
_filename_user = "setak";
 //BA.debugLineNum = 35;BA.debugLine="Public filename_sabad As String=\"setak_sabad\"";
_filename_sabad = "setak_sabad";
 //BA.debugLineNum = 40;BA.debugLine="Dim error_image As Bitmap=LoadBitmap(File.DirAsse";
_error_image = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_error_image = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.png");
 //BA.debugLineNum = 41;BA.debugLine="Dim progress_image As Bitmap=LoadBitmap(File.DirA";
_progress_image = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_progress_image = anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prog_img.png");
 //BA.debugLineNum = 48;BA.debugLine="Public Font_Awsome As Typeface=Typeface.LoadFromA";
_font_awsome = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_font_awsome.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("FontAwesome.otf")));
 //BA.debugLineNum = 49;BA.debugLine="Public font_body As Typeface=Typeface.LoadFromAss";
_font_body = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_font_body.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("iransans Bold.ttf")));
 //BA.debugLineNum = 54;BA.debugLine="Public Address_payment As String=\"http://padidehs";
_address_payment = "http://padidehsms.ir/payment_setak/index.php";
 //BA.debugLineNum = 55;BA.debugLine="Public address_send_sms As String=\"http://www.pad";
_address_send_sms = "http://www.padidehsms.ir/payment_setak/sms.php";
 //BA.debugLineNum = 57;BA.debugLine="Public username_sms As String=\"hpk\"";
_username_sms = "hpk";
 //BA.debugLineNum = 58;BA.debugLine="Public password_sms As String=\"147963\"";
_password_sms = "147963";
 //BA.debugLineNum = 59;BA.debugLine="Public Api_key_sms As String=\"hpksoftware\"";
_api_key_sms = "hpksoftware";
 //BA.debugLineNum = 60;BA.debugLine="Public sender_sms() As String=Array As String(\"+9";
_sender_sms = new String[]{"+9810009","+98sim","+98100020400"};
 //BA.debugLineNum = 63;BA.debugLine="Public apk_name_for_dowanload=\"setak.apk\"";
_apk_name_for_dowanload = "setak.apk";
 //BA.debugLineNum = 65;BA.debugLine="Public color_base As Int=0xFF26A69A";
_color_base = (int) (0xff26a69a);
 //BA.debugLineNum = 67;BA.debugLine="Public timer_slideshow As Int=10              *10";
_timer_slideshow = (int) (10*1000);
 //BA.debugLineNum = 70;BA.debugLine="Public Addres_send_pushe As String=root_site & \"/";
_addres_send_pushe = _root_site+"/server/pushe/index.php";
 //BA.debugLineNum = 71;BA.debugLine="Public ckeck_app_access As Boolean=True	'برای این";
_ckeck_app_access = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 75;BA.debugLine="Public enable_push As Boolean=True";
_enable_push = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 80;BA.debugLine="Public BoardUrl As String";
_boardurl = "";
 //BA.debugLineNum = 81;BA.debugLine="Public SenderId As String";
_senderid = "";
 //BA.debugLineNum = 85;BA.debugLine="Dim rute_file_app As String=File.DirDefaultExtern";
_rute_file_app = anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal();
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
}
