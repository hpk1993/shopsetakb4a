package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class alarm_service extends  android.app.Service{
	public static class alarm_service_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, alarm_service.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static alarm_service mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return alarm_service.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "hpksoftware.setak", "hpksoftware.setak.alarm_service");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.alarm_service", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("** Service (alarm_service) Create **");
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
                    BA.LogInfo("** Service (alarm_service) Create **");
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
    	BA.LogInfo("** Service (alarm_service) Start **");
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
        BA.LogInfo("** Service (alarm_service) Destroy **");
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
public static anywheresoftware.b4a.student.PersianDate _daye_per = null;
public static anywheresoftware.b4a.objects.Timer _timer1 = null;
public static int _index = 0;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public b4a.example.frgfg.connector _connector = null;
public b4a.example.frgfg.db_mysql _db_mysql = null;
public at.ahadev.b4a.ahashare.ahacontentchooser _ahacontentchooser = null;
public hpksoftware.setak.main _main = null;
public hpksoftware.setak.starter _starter = null;
public hpksoftware.setak.insert_noskhe _insert_noskhe = null;
public hpksoftware.setak.show_noskhe _show_noskhe = null;
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
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim daye_per As PersianDate";
_daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 8;BA.debugLine="Dim timer1 As Timer";
_timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 9;BA.debugLine="Dim index As Int";
_index = 0;
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 13;BA.debugLine="Log(\"Service_Create\")";
anywheresoftware.b4a.keywords.Common.Log("Service_Create");
 //BA.debugLineNum = 14;BA.debugLine="timer1.Initialize(\"timer1\",2000)";
_timer1.Initialize(processBA,"timer1",(long) (2000));
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
boolean _enabled_alaram_daroo = false;
anywheresoftware.b4a.objects.collections.List _list_clock = null;
int _i = 0;
String _time1 = "";
long _timeset = 0L;
barxdroid.NotificationBuilder.NotificationBuilder _nb = null;
boolean _enabled_alaram_visit = false;
String _date_selected = "";
 //BA.debugLineNum = 19;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 21;BA.debugLine="timer1.Enabled=True";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 22;BA.debugLine="If File.Exists(File.DirDefaultExternal,\"clock_dar";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"clock_daroo")) { 
 //BA.debugLineNum = 23;BA.debugLine="Dim enabled_alaram_daroo As Boolean=File.Exists(";
_enabled_alaram_daroo = anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_daroo");
 //BA.debugLineNum = 24;BA.debugLine="DateTime.TimeFormat=\"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 27;BA.debugLine="If enabled_alaram_daroo=True Then";
if (_enabled_alaram_daroo==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 28;BA.debugLine="Dim list_clock As List=File.ReadList(File.DirDe";
_list_clock = new anywheresoftware.b4a.objects.collections.List();
_list_clock = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"clock_daroo");
 //BA.debugLineNum = 30;BA.debugLine="For i=0 To list_clock.Size-1";
{
final int step7 = 1;
final int limit7 = (int) (_list_clock.getSize()-1);
for (_i = (int) (0) ; (step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7); _i = ((int)(0 + _i + step7)) ) {
 //BA.debugLineNum = 31;BA.debugLine="Dim time1 As String=list_clock.Get(i)";
_time1 = BA.ObjectToString(_list_clock.Get(_i));
 //BA.debugLineNum = 32;BA.debugLine="DateTime.TimeFormat=\"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 33;BA.debugLine="If time1.Length=1 Then";
if (_time1.length()==1) { 
 //BA.debugLineNum = 34;BA.debugLine="DateTime.TimeFormat=\"H\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("H");
 }else if(_time1.length()==2) { 
 //BA.debugLineNum = 36;BA.debugLine="DateTime.TimeFormat=\"HH\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH");
 };
 //BA.debugLineNum = 40;BA.debugLine="Dim timeset As Long=DateTime.TimeParse(	time1";
_timeset = anywheresoftware.b4a.keywords.Common.DateTime.TimeParse(_time1);
 //BA.debugLineNum = 42;BA.debugLine="If (DateTime.Now >= timeset) And (DateTime.Now";
if ((anywheresoftware.b4a.keywords.Common.DateTime.getNow()>=_timeset) && (anywheresoftware.b4a.keywords.Common.DateTime.getNow()<=(_timeset+10000))) { 
 //BA.debugLineNum = 43;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 44;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 45;BA.debugLine="nb.setCustomLight(Colors.Magenta,500,500)";
_nb.setCustomLight(anywheresoftware.b4a.keywords.Common.Colors.Magenta,(int) (500),(int) (500));
 //BA.debugLineNum = 46;BA.debugLine="nb.DefaultVibrate=True";
_nb.setDefaultVibrate(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 47;BA.debugLine="nb.DefaultSound=True";
_nb.setDefaultSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 48;BA.debugLine="nb.smallIcon=\"icon\"";
_nb.setSmallIcon("icon");
 //BA.debugLineNum = 49;BA.debugLine="nb.Ticker=\"زمان مصرف دارو رسیده\"";
_nb.setTicker("زمان مصرف دارو رسیده");
 //BA.debugLineNum = 50;BA.debugLine="nb.ContentTitle= \"مصرف دارو\"";
_nb.setContentTitle("مصرف دارو");
 //BA.debugLineNum = 51;BA.debugLine="nb.contentInfo=\"(ستاک دارو)\"";
_nb.setContentInfo("(ستاک دارو)");
 //BA.debugLineNum = 52;BA.debugLine="nb.contentText= \"لطفا دارو مورد نظر را مصرف ک";
_nb.setContentText("لطفا دارو مورد نظر را مصرف کنید");
 //BA.debugLineNum = 53;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 55;BA.debugLine="nb.setActivity(show_noskhe)";
_nb.setActivity(processBA,(Object)(mostCurrent._show_noskhe.getObject()));
 //BA.debugLineNum = 56;BA.debugLine="nb.Notify(	i )";
_nb.Notify(processBA,_i);
 };
 }
};
 };
 //BA.debugLineNum = 63;BA.debugLine="Dim enabled_alaram_visit As Boolean=File.Exists(";
_enabled_alaram_visit = anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"enabled_alarm_visit");
 //BA.debugLineNum = 64;BA.debugLine="If File.Exists(File.DirDefaultExternal,\"date_vis";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"date_visit")==anywheresoftware.b4a.keywords.Common.True && _enabled_alaram_visit==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 65;BA.debugLine="Dim date_selected As String=File.ReadString(Fil";
_date_selected = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),"date_visit");
 //BA.debugLineNum = 68;BA.debugLine="If date_selected.CompareTo(daye_per.getDate(0,0";
if (_date_selected.compareTo(_daye_per.getDate((int) (0),(int) (0),(int) (0),"/"))==0) { 
 //BA.debugLineNum = 69;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 70;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 71;BA.debugLine="nb.setCustomLight(Colors.Magenta,500,500)";
_nb.setCustomLight(anywheresoftware.b4a.keywords.Common.Colors.Magenta,(int) (500),(int) (500));
 //BA.debugLineNum = 72;BA.debugLine="nb.DefaultVibrate=False";
_nb.setDefaultVibrate(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 73;BA.debugLine="nb.DefaultSound=False";
_nb.setDefaultSound(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 74;BA.debugLine="nb.smallIcon=\"icon\"";
_nb.setSmallIcon("icon");
 //BA.debugLineNum = 75;BA.debugLine="nb.Ticker=\"با پزشک خود قرار ملاقات دارید\"";
_nb.setTicker("با پزشک خود قرار ملاقات دارید");
 //BA.debugLineNum = 76;BA.debugLine="nb.ContentTitle= \"ملاقات پزشک \"";
_nb.setContentTitle("ملاقات پزشک ");
 //BA.debugLineNum = 77;BA.debugLine="nb.contentInfo=\"(ستاک)\"";
_nb.setContentInfo("(ستاک)");
 //BA.debugLineNum = 78;BA.debugLine="nb.contentText= \"امروز قراره ملاقات با پزشک ست";
_nb.setContentText("امروز قراره ملاقات با پزشک ستاک دارید");
 //BA.debugLineNum = 79;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 80;BA.debugLine="nb.setActivity(show_noskhe)";
_nb.setActivity(processBA,(Object)(mostCurrent._show_noskhe.getObject()));
 //BA.debugLineNum = 81;BA.debugLine="nb.Notify(	1000 )";
_nb.Notify(processBA,(int) (1000));
 };
 };
 };
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub timer1_Tick";
 //BA.debugLineNum = 99;BA.debugLine="timer1.Enabled=False";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 100;BA.debugLine="index=index+1";
_index = (int) (_index+1);
 //BA.debugLineNum = 101;BA.debugLine="StartServiceAt(Me,DateTime.Now,True)";
anywheresoftware.b4a.keywords.Common.StartServiceAt(processBA,alarm_service.getObject(),anywheresoftware.b4a.keywords.Common.DateTime.getNow(),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
}
