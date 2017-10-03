package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class pushejsonservice extends  android.app.Service{
	public static class pushejsonservice_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, pushejsonservice.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static pushejsonservice mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return pushejsonservice.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "hpksoftware.setak", "hpksoftware.setak.pushejsonservice");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.pushejsonservice", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("** Service (pushejsonservice) Create **");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
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
                    BA.LogInfo("** Service (pushejsonservice) Create **");
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
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (pushejsonservice) Start **");
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
        BA.LogInfo("** Service (pushejsonservice) Destroy **");
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
public static co.ronash.pushe.wrapper.PusheWrapper.PusheB4AUtil _pusheutil = null;
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
public static String  _messagearrived(anywheresoftware.b4a.objects.IntentWrapper _pintent) throws Exception{
String _jsonmsg = "";
anywheresoftware.b4a.objects.collections.JSONParser _parser = null;
anywheresoftware.b4a.objects.collections.Map _root = null;
String _action = "";
int _uid = 0;
String _name_lname = "";
barxdroid.NotificationBuilder.NotificationBuilder _nb = null;
String _price = "";
int _id_post = 0;
String _title_post = "";
 //BA.debugLineNum = 22;BA.debugLine="Sub MessageArrived (pIntent As Intent)";
 //BA.debugLineNum = 24;BA.debugLine="Dim JsonMsg As String";
_jsonmsg = "";
 //BA.debugLineNum = 25;BA.debugLine="JsonMsg = PusheUtil.getPusheJsonMsg(pIntent)";
_jsonmsg = _pusheutil.getPusheJsonMsg((android.content.Intent)(_pintent.getObject()));
 //BA.debugLineNum = 26;BA.debugLine="If JsonMsg <> \"\" Then";
if ((_jsonmsg).equals("") == false) { 
 //BA.debugLineNum = 27;BA.debugLine="Dim parser As JSONParser";
_parser = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 28;BA.debugLine="parser.Initialize(JsonMsg)";
_parser.Initialize(_jsonmsg);
 //BA.debugLineNum = 29;BA.debugLine="Dim root As Map = parser.NextObject";
_root = new anywheresoftware.b4a.objects.collections.Map();
_root = _parser.NextObject();
 //BA.debugLineNum = 30;BA.debugLine="Dim action As String=root.Get(\"action\")";
_action = BA.ObjectToString(_root.Get((Object)("action")));
 //BA.debugLineNum = 33;BA.debugLine="If function.is_admin=True Then";
if (mostCurrent._function._is_admin(processBA)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 35;BA.debugLine="If action=\"reg_user\" Then";
if ((_action).equals("reg_user")) { 
 //BA.debugLineNum = 36;BA.debugLine="Dim uid As Int = root.Get(\"uid\")";
_uid = (int)(BA.ObjectToNumber(_root.Get((Object)("uid"))));
 //BA.debugLineNum = 37;BA.debugLine="Dim name_lname As String = root.Get(\"name_lnam";
_name_lname = BA.ObjectToString(_root.Get((Object)("name_lname")));
 //BA.debugLineNum = 38;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 39;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 40;BA.debugLine="nb.setCustomLight(Colors.red,50,50)";
_nb.setCustomLight(anywheresoftware.b4a.keywords.Common.Colors.Red,(int) (50),(int) (50));
 //BA.debugLineNum = 41;BA.debugLine="nb.DefaultVibrate=True";
_nb.setDefaultVibrate(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 42;BA.debugLine="nb.DefaultSound=True";
_nb.setDefaultSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 43;BA.debugLine="nb.Autocancel=False";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 44;BA.debugLine="nb.smallIcon=\"icon\"";
_nb.setSmallIcon("icon");
 //BA.debugLineNum = 45;BA.debugLine="nb.Ticker=\"یک ثبت نام جدید\"";
_nb.setTicker("یک ثبت نام جدید");
 //BA.debugLineNum = 46;BA.debugLine="nb.ContentTitle= $\"ثبت نام جدید با اشتراک  ${u";
_nb.setContentTitle(("ثبت نام جدید با اشتراک  "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_uid))+""));
 //BA.debugLineNum = 47;BA.debugLine="nb.contentInfo=\"(جدید)\"";
_nb.setContentInfo("(جدید)");
 //BA.debugLineNum = 48;BA.debugLine="nb.contentText= $\"کاربر: ${name_lname}\"$";
_nb.setContentText(("کاربر: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_name_lname))+""));
 //BA.debugLineNum = 49;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 50;BA.debugLine="nb.Tag=\"u\" & (uid)";
_nb.setTag("u"+BA.NumberToString((_uid)));
 //BA.debugLineNum = 51;BA.debugLine="nb.setActivity(shared_admin)";
_nb.setActivity(processBA,(Object)(mostCurrent._shared_admin.getObject()));
 //BA.debugLineNum = 52;BA.debugLine="nb.Notify(	Rnd(1,100) )";
_nb.Notify(processBA,anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (100)));
 };
 //BA.debugLineNum = 55;BA.debugLine="If action=\"buy_user\" Then";
if ((_action).equals("buy_user")) { 
 //BA.debugLineNum = 56;BA.debugLine="Dim uid As Int = root.Get(\"uid\")";
_uid = (int)(BA.ObjectToNumber(_root.Get((Object)("uid"))));
 //BA.debugLineNum = 57;BA.debugLine="Dim price As String = root.Get(\"price\")";
_price = BA.ObjectToString(_root.Get((Object)("price")));
 //BA.debugLineNum = 58;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 59;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 60;BA.debugLine="nb.setCustomLight(Colors.red,50,50)";
_nb.setCustomLight(anywheresoftware.b4a.keywords.Common.Colors.Red,(int) (50),(int) (50));
 //BA.debugLineNum = 61;BA.debugLine="nb.DefaultVibrate=True";
_nb.setDefaultVibrate(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 62;BA.debugLine="nb.DefaultSound=True";
_nb.setDefaultSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 63;BA.debugLine="nb.Autocancel=False";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 64;BA.debugLine="nb.smallIcon=\"icon\"";
_nb.setSmallIcon("icon");
 //BA.debugLineNum = 65;BA.debugLine="nb.Ticker=\"یک سفارش جدید\"";
_nb.setTicker("یک سفارش جدید");
 //BA.debugLineNum = 66;BA.debugLine="nb.ContentTitle= $\"سفارش جدید اشتراک   ${uid}\"";
_nb.setContentTitle(("سفارش جدید اشتراک   "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_uid))+""));
 //BA.debugLineNum = 67;BA.debugLine="nb.contentInfo=\"(سفارش)\"";
_nb.setContentInfo("(سفارش)");
 //BA.debugLineNum = 68;BA.debugLine="nb.contentText= $\"مبلغ: ${price}\"$";
_nb.setContentText(("مبلغ: "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_price))+""));
 //BA.debugLineNum = 69;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 70;BA.debugLine="nb.Tag=\"u\" & (uid)";
_nb.setTag("u"+BA.NumberToString((_uid)));
 //BA.debugLineNum = 72;BA.debugLine="nb.setActivity(shared_admin)";
_nb.setActivity(processBA,(Object)(mostCurrent._shared_admin.getObject()));
 //BA.debugLineNum = 73;BA.debugLine="nb.Notify(	Rnd(1,100) )";
_nb.Notify(processBA,anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (100)));
 };
 };
 //BA.debugLineNum = 78;BA.debugLine="If action=\"new_post\" Then";
if ((_action).equals("new_post")) { 
 //BA.debugLineNum = 79;BA.debugLine="Dim id_post As Int = root.Get(\"id_post\")";
_id_post = (int)(BA.ObjectToNumber(_root.Get((Object)("id_post"))));
 //BA.debugLineNum = 80;BA.debugLine="Dim title_post As String = root.Get(\"title_post";
_title_post = BA.ObjectToString(_root.Get((Object)("title_post")));
 //BA.debugLineNum = 81;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 82;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 83;BA.debugLine="nb.DefaultVibrate=True";
_nb.setDefaultVibrate(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 84;BA.debugLine="nb.DefaultSound=True";
_nb.setDefaultSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 85;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 86;BA.debugLine="nb.smallIcon=\"icon\"";
_nb.setSmallIcon("icon");
 //BA.debugLineNum = 87;BA.debugLine="nb.Ticker=\"معرفی محصول \"";
_nb.setTicker("معرفی محصول ");
 //BA.debugLineNum = 88;BA.debugLine="nb.ContentTitle=\"معرفی محصول \"";
_nb.setContentTitle("معرفی محصول ");
 //BA.debugLineNum = 89;BA.debugLine="nb.contentText=title_post";
_nb.setContentText(_title_post);
 //BA.debugLineNum = 90;BA.debugLine="nb.contentInfo=\"( \" & Application.LabelName & \"";
_nb.setContentInfo("( "+anywheresoftware.b4a.keywords.Common.Application.getLabelName()+" )");
 //BA.debugLineNum = 91;BA.debugLine="nb.Tag=id_post";
_nb.setTag(BA.NumberToString(_id_post));
 //BA.debugLineNum = 93;BA.debugLine="nb.setActivity(Main)";
_nb.setActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 94;BA.debugLine="nb.Notify(	Rnd(1,100) )";
_nb.Notify(processBA,anywheresoftware.b4a.keywords.Common.Rnd((int) (1),(int) (100)));
 };
 };
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim PusheUtil As PusheB4AUtil";
_pusheutil = new co.ronash.pushe.wrapper.PusheWrapper.PusheB4AUtil();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 16;BA.debugLine="Select StartingIntent.Action";
switch (BA.switchObjectToInt(_startingintent.getAction(),"com.google.android.c2dm.intent.RECEIVE")) {
case 0: {
 //BA.debugLineNum = 18;BA.debugLine="MessageArrived(StartingIntent)";
_messagearrived(_startingintent);
 break; }
}
;
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
}
