package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class pushservice extends  android.app.Service{
	public static class pushservice_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, pushservice.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static pushservice mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return pushservice.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "hpksoftware.setak", "hpksoftware.setak.pushservice");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "hpksoftware.setak.pushservice", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("** Service (pushservice) Create **");
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
                    BA.LogInfo("** Service (pushservice) Create **");
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
    	BA.LogInfo("** Service (pushservice) Start **");
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
        BA.LogInfo("** Service (pushservice) Destroy **");
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
public static int _num_notify = 0;
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
public hpksoftware.setak.info_kala _info_kala = null;
public hpksoftware.setak.update _update = null;
public hpksoftware.setak.mylibrary _mylibrary = null;
public hpksoftware.setak.function _function = null;
public hpksoftware.setak.show_nazar _show_nazar = null;
public hpksoftware.setak.nazar _nazar = null;
public hpksoftware.setak.push_active _push_active = null;
public hpksoftware.setak.result _result = null;
public hpksoftware.setak.disconnect _disconnect = null;
public hpksoftware.setak.changefontbylabelsize _changefontbylabelsize = null;
public static String  _handleregistrationresult(anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _j = null;
String _rid = "";
String _phone_id = "";
anywheresoftware.b4a.phone.Phone.PhoneId _phone1 = null;
anywheresoftware.b4a.phone.Phone _phone_model = null;
 //BA.debugLineNum = 163;BA.debugLine="Sub HandleRegistrationResult(Intent As Intent)";
 //BA.debugLineNum = 164;BA.debugLine="If Intent.HasExtra(\"error\") Then";
if (_intent.HasExtra("error")) { 
 //BA.debugLineNum = 165;BA.debugLine="Log(\"Error: \" & Intent.GetExtra(\"error\"))";
anywheresoftware.b4a.keywords.Common.Log("Error: "+BA.ObjectToString(_intent.GetExtra("error")));
 }else if(_intent.HasExtra("unregistered")) { 
 //BA.debugLineNum = 169;BA.debugLine="ToastMessageShow(Application.LabelName,False)' l";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Application.getLabelName()),anywheresoftware.b4a.keywords.Common.False);
 }else if(_intent.HasExtra("registration_id")) { 
 //BA.debugLineNum = 173;BA.debugLine="Dim j As HttpJob";
_j = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 174;BA.debugLine="Dim rid,phone_id As String";
_rid = "";
_phone_id = "";
 //BA.debugLineNum = 175;BA.debugLine="Dim phone1 As PhoneId";
_phone1 = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 176;BA.debugLine="Dim phone_model As Phone";
_phone_model = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 177;BA.debugLine="rid = Intent.GetExtra(\"registration_id\")";
_rid = BA.ObjectToString(_intent.GetExtra("registration_id"));
 //BA.debugLineNum = 178;BA.debugLine="j.Initialize(\"RegisterTask\", Me)";
_j._initialize(processBA,"RegisterTask",pushservice.getObject());
 //BA.debugLineNum = 179;BA.debugLine="j.Download2(Starter.BoardUrl, Array As String(";
_j._download2(mostCurrent._starter._boardurl,new String[]{"tell",_phone1.GetLine1Number(),"name",_phone_model.getModel(),"regId",_rid,"phone_id",_phone1.GetDeviceId(),"project",mostCurrent._starter._senderid});
 };
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job1) throws Exception{
 //BA.debugLineNum = 185;BA.debugLine="Sub JobDone(Job1 As HttpJob)";
 //BA.debugLineNum = 186;BA.debugLine="If Job1.Success Then";
if (_job1._success) { 
 //BA.debugLineNum = 187;BA.debugLine="Select Job1.JobName";
switch (BA.switchObjectToInt(_job1._jobname,"RegisterTask","UnregisterTask")) {
case 0: {
 //BA.debugLineNum = 192;BA.debugLine="File.WriteString(myLibrary.rute,\"reg_push.txt\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(mostCurrent._mylibrary._rute(processBA),"reg_push.txt","True");
 break; }
case 1: {
 break; }
}
;
 }else {
 //BA.debugLineNum = 198;BA.debugLine="Log(Job1.ErrorMessage)";
anywheresoftware.b4a.keywords.Common.Log(_job1._errormessage);
 };
 //BA.debugLineNum = 200;BA.debugLine="Job1.Release";
_job1._release();
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _messagearrived(anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
String _from = "";
String _collapsekey = "";
String _data = "";
String _msg = "";
anywheresoftware.b4a.objects.NotificationWrapper _n = null;
barxdroid.NotificationBuilder.NotificationBuilder _nb = null;
String[] _arry_push_data = null;
int _codep = 0;
String[] _arry_push_msg = null;
String[] _arry_update_push = null;
int _version = 0;
 //BA.debugLineNum = 27;BA.debugLine="Sub MessageArrived (Intent As Intent)";
 //BA.debugLineNum = 28;BA.debugLine="Dim From, CollapseKey, Data, Msg As String 'ignor";
_from = "";
_collapsekey = "";
_data = "";
_msg = "";
 //BA.debugLineNum = 29;BA.debugLine="If Intent.HasExtra(\"from\") Then From = Intent.Get";
if (_intent.HasExtra("from")) { 
_from = BA.ObjectToString(_intent.GetExtra("from"));};
 //BA.debugLineNum = 30;BA.debugLine="If Intent.HasExtra(\"data\") Then Data = Intent.Get";
if (_intent.HasExtra("data")) { 
_data = BA.ObjectToString(_intent.GetExtra("data"));};
 //BA.debugLineNum = 32;BA.debugLine="Log(Data & \"//\" & From)";
anywheresoftware.b4a.keywords.Common.Log(_data+"//"+_from);
 //BA.debugLineNum = 33;BA.debugLine="If Intent.HasExtra(\"collapse_key\") Then CollapseK";
if (_intent.HasExtra("collapse_key")) { 
_collapsekey = BA.ObjectToString(_intent.GetExtra("collapse_key"));};
 //BA.debugLineNum = 35;BA.debugLine="If Intent.HasExtra(\"msg_reg\") Then";
if (_intent.HasExtra("msg_reg")) { 
 //BA.debugLineNum = 36;BA.debugLine="Log(\"msg_reg\")";
anywheresoftware.b4a.keywords.Common.Log("msg_reg");
 //BA.debugLineNum = 37;BA.debugLine="Dim n As Notification";
_n = new anywheresoftware.b4a.objects.NotificationWrapper();
 //BA.debugLineNum = 38;BA.debugLine="n.Initialize";
_n.Initialize();
 //BA.debugLineNum = 39;BA.debugLine="n.Light = True";
_n.setLight(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 40;BA.debugLine="n.Vibrate = True";
_n.setVibrate(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 41;BA.debugLine="n.Sound = True";
_n.setSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 42;BA.debugLine="n.AutoCancel=True";
_n.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 43;BA.debugLine="Data = Intent.GetExtra(\"msg_reg\")";
_data = BA.ObjectToString(_intent.GetExtra("msg_reg"));
 //BA.debugLineNum = 44;BA.debugLine="n.Icon = \"push1\"";
_n.setIcon("push1");
 //BA.debugLineNum = 45;BA.debugLine="n.SetInfo(Application.LabelName, Data, Main) 'Ch";
_n.SetInfo(processBA,anywheresoftware.b4a.keywords.Common.Application.getLabelName(),_data,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 46;BA.debugLine="n.notify(Num_notify)";
_n.Notify(_num_notify);
 //BA.debugLineNum = 47;BA.debugLine="Num_notify=Num_notify+1";
_num_notify = (int) (_num_notify+1);
 }else {
 //BA.debugLineNum = 51;BA.debugLine="Log(\"get data: \" & Data)";
anywheresoftware.b4a.keywords.Common.Log("get data: "+_data);
 //BA.debugLineNum = 52;BA.debugLine="Dim nb As NotificationBuilder";
_nb = new barxdroid.NotificationBuilder.NotificationBuilder();
 //BA.debugLineNum = 55;BA.debugLine="nb.Initialize";
_nb.Initialize(processBA);
 //BA.debugLineNum = 56;BA.debugLine="nb.setCustomLight(Colors.red,50,50)";
_nb.setCustomLight(anywheresoftware.b4a.keywords.Common.Colors.Red,(int) (50),(int) (50));
 //BA.debugLineNum = 57;BA.debugLine="nb.DefaultVibrate=True";
_nb.setDefaultVibrate(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 58;BA.debugLine="nb.DefaultSound=True";
_nb.setDefaultSound(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 59;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 60;BA.debugLine="If Data.IndexOf(\";\") <> -1 Then";
if (_data.indexOf(";")!=-1) { 
 //BA.debugLineNum = 61;BA.debugLine="Log(\";\")";
anywheresoftware.b4a.keywords.Common.Log(";");
 //BA.debugLineNum = 62;BA.debugLine="Dim arry_push_data() As String";
_arry_push_data = new String[(int) (0)];
java.util.Arrays.fill(_arry_push_data,"");
 //BA.debugLineNum = 63;BA.debugLine="arry_push_data=Regex.Split(\";\",Data)";
_arry_push_data = anywheresoftware.b4a.keywords.Common.Regex.Split(";",_data);
 //BA.debugLineNum = 65;BA.debugLine="nb.smallIcon=\"push1\"";
_nb.setSmallIcon("push1");
 //BA.debugLineNum = 66;BA.debugLine="nb.Ticker=\"محصول جدید \"";
_nb.setTicker("محصول جدید ");
 //BA.debugLineNum = 67;BA.debugLine="nb.ContentTitle=Application.LabelName";
_nb.setContentTitle(anywheresoftware.b4a.keywords.Common.Application.getLabelName());
 //BA.debugLineNum = 68;BA.debugLine="nb.contentText=arry_push_data(0)'title project";
_nb.setContentText(_arry_push_data[(int) (0)]);
 //BA.debugLineNum = 69;BA.debugLine="nb.contentInfo=\"(جدید)\"";
_nb.setContentInfo("(جدید)");
 //BA.debugLineNum = 83;BA.debugLine="Dim codep As Int=arry_push_data(1).Trim";
_codep = (int)(Double.parseDouble(_arry_push_data[(int) (1)].trim()));
 //BA.debugLineNum = 84;BA.debugLine="If codep > 0  Then";
if (_codep>0) { 
 //BA.debugLineNum = 85;BA.debugLine="nb.Tag=codep'code project";
_nb.setTag(BA.NumberToString(_codep));
 //BA.debugLineNum = 86;BA.debugLine="nb.setParentActivity(push_active)";
_nb.setParentActivity(processBA,(Object)(mostCurrent._push_active.getObject()));
 //BA.debugLineNum = 87;BA.debugLine="nb.setActivity(Main)";
_nb.setActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 92;BA.debugLine="nb.Notify(Num_notify)";
_nb.Notify(processBA,_num_notify);
 //BA.debugLineNum = 94;BA.debugLine="Return";
if (true) return "";
 }else if(_data.indexOf("#")!=-1) { 
 //BA.debugLineNum = 97;BA.debugLine="Log(\"#\")";
anywheresoftware.b4a.keywords.Common.Log("#");
 //BA.debugLineNum = 98;BA.debugLine="Dim arry_push_msg() As String";
_arry_push_msg = new String[(int) (0)];
java.util.Arrays.fill(_arry_push_msg,"");
 //BA.debugLineNum = 99;BA.debugLine="arry_push_msg=Regex.Split(\"#\",Data)";
_arry_push_msg = anywheresoftware.b4a.keywords.Common.Regex.Split("#",_data);
 //BA.debugLineNum = 101;BA.debugLine="nb.smallIcon=\"push1\"";
_nb.setSmallIcon("push1");
 //BA.debugLineNum = 102;BA.debugLine="nb.Ticker=\"پیام جدید از سین سین\"";
_nb.setTicker("پیام جدید از سین سین");
 //BA.debugLineNum = 103;BA.debugLine="nb.ContentTitle=arry_push_msg(0)";
_nb.setContentTitle(_arry_push_msg[(int) (0)]);
 //BA.debugLineNum = 104;BA.debugLine="nb.contentInfo=arry_push_msg(1)";
_nb.setContentInfo(_arry_push_msg[(int) (1)]);
 //BA.debugLineNum = 105;BA.debugLine="nb.contentText=arry_push_msg(2)	'title project";
_nb.setContentText(_arry_push_msg[(int) (2)]);
 //BA.debugLineNum = 106;BA.debugLine="nb.Autocancel=True";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 107;BA.debugLine="Log(arry_push_msg(3))";
anywheresoftware.b4a.keywords.Common.Log(_arry_push_msg[(int) (3)]);
 //BA.debugLineNum = 108;BA.debugLine="nb.Tag=arry_push_msg(3)'.SubString2(0,arry_push";
_nb.setTag(_arry_push_msg[(int) (3)]);
 //BA.debugLineNum = 110;BA.debugLine="nb.setParentActivity(push_active)";
_nb.setParentActivity(processBA,(Object)(mostCurrent._push_active.getObject()));
 //BA.debugLineNum = 111;BA.debugLine="nb.setActivity(push_active)";
_nb.setActivity(processBA,(Object)(mostCurrent._push_active.getObject()));
 //BA.debugLineNum = 113;BA.debugLine="nb.Notify(Num_notify)";
_nb.Notify(processBA,_num_notify);
 //BA.debugLineNum = 115;BA.debugLine="Return";
if (true) return "";
 }else if(_data.indexOf("Update")!=-1 || _data.indexOf("update")!=-1) { 
 //BA.debugLineNum = 117;BA.debugLine="Dim arry_update_push() As String";
_arry_update_push = new String[(int) (0)];
java.util.Arrays.fill(_arry_update_push,"");
 //BA.debugLineNum = 118;BA.debugLine="arry_update_push=Regex.Split(\":\",Data)";
_arry_update_push = anywheresoftware.b4a.keywords.Common.Regex.Split(":",_data);
 //BA.debugLineNum = 119;BA.debugLine="Dim version As Int=arry_update_push(1)";
_version = (int)(Double.parseDouble(_arry_update_push[(int) (1)]));
 //BA.debugLineNum = 120;BA.debugLine="If Application.VersionCode < version Then";
if (anywheresoftware.b4a.keywords.Common.Application.getVersionCode()<_version) { 
 //BA.debugLineNum = 121;BA.debugLine="nb.smallIcon=\"push1\"";
_nb.setSmallIcon("push1");
 //BA.debugLineNum = 122;BA.debugLine="nb.Ticker=$\"پیام جدید از ${Application.LabelNa";
_nb.setTicker(("پیام جدید از "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.Application.getLabelName()))+""));
 //BA.debugLineNum = 123;BA.debugLine="nb.ContentTitle=$\"نسخه جدید ${Application.Labe";
_nb.setContentTitle(("نسخه جدید "+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(anywheresoftware.b4a.keywords.Common.Application.getLabelName()))+""));
 //BA.debugLineNum = 124;BA.debugLine="nb.contentInfo=\"(جدید)\"";
_nb.setContentInfo("(جدید)");
 //BA.debugLineNum = 125;BA.debugLine="nb.contentText=\"کلیک کنید\"";
_nb.setContentText("کلیک کنید");
 //BA.debugLineNum = 126;BA.debugLine="nb.Autocancel=False";
_nb.setAutoCancel(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 127;BA.debugLine="nb.Tag=$\"${Starter.root_site}/android/${Starte";
_nb.setTag((""+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._starter._root_site))+"/android/"+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(mostCurrent._starter._apk_name_for_dowanload))+""));
 //BA.debugLineNum = 128;BA.debugLine="nb.setParentActivity(push_active)";
_nb.setParentActivity(processBA,(Object)(mostCurrent._push_active.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="nb.setActivity(push_active)	'push_active";
_nb.setActivity(processBA,(Object)(mostCurrent._push_active.getObject()));
 //BA.debugLineNum = 131;BA.debugLine="nb.Notify(Num_notify)";
_nb.Notify(processBA,_num_notify);
 };
 }else {
 };
 };
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private Num_notify As Int: Num_notify=0";
_num_notify = 0;
 //BA.debugLineNum = 8;BA.debugLine="Private Num_notify As Int: Num_notify=0";
_num_notify = (int) (0);
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _registerdevice(boolean _unregister) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.IntentWrapper _i2 = null;
Object _pi = null;
 //BA.debugLineNum = 143;BA.debugLine="Sub RegisterDevice (Unregister As Boolean)";
 //BA.debugLineNum = 144;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 145;BA.debugLine="If Unregister Then";
if (_unregister) { 
 //BA.debugLineNum = 146;BA.debugLine="i.Initialize(\"com.google.android.c2dm.intent.UNR";
_i.Initialize("com.google.android.c2dm.intent.UNREGISTER","");
 }else {
 //BA.debugLineNum = 148;BA.debugLine="i.Initialize(\"com.google.android.c2dm.intent.REG";
_i.Initialize("com.google.android.c2dm.intent.REGISTER","");
 //BA.debugLineNum = 149;BA.debugLine="i.PutExtra(\"sender\", Starter.SenderId)";
_i.PutExtra("sender",(Object)(mostCurrent._starter._senderid));
 };
 //BA.debugLineNum = 152;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 153;BA.debugLine="Dim i2 As Intent";
_i2 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 154;BA.debugLine="i2 = r.CreateObject(\"android.content.Intent\")";
_i2.setObject((android.content.Intent)(_r.CreateObject("android.content.Intent")));
 //BA.debugLineNum = 155;BA.debugLine="Dim pi As Object";
_pi = new Object();
 //BA.debugLineNum = 156;BA.debugLine="pi = r.RunStaticMethod(\"android.app.PendingIntent";
_pi = _r.RunStaticMethod("android.app.PendingIntent","getBroadcast",new Object[]{(Object)(_r.GetContext(processBA)),(Object)(0),(Object)(_i2.getObject()),(Object)(0)},new String[]{"android.content.Context","java.lang.int","android.content.Intent","java.lang.int"});
 //BA.debugLineNum = 159;BA.debugLine="i.PutExtra(\"app\", pi)";
_i.PutExtra("app",_pi);
 //BA.debugLineNum = 160;BA.debugLine="StartService(i)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(_i.getObject()));
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 17;BA.debugLine="Select StartingIntent.Action";
switch (BA.switchObjectToInt(_startingintent.getAction(),"com.google.android.c2dm.intent.REGISTRATION","com.google.android.c2dm.intent.RECEIVE")) {
case 0: {
 //BA.debugLineNum = 19;BA.debugLine="Log(\"REGISTRATION\")";
anywheresoftware.b4a.keywords.Common.Log("REGISTRATION");
 //BA.debugLineNum = 20;BA.debugLine="HandleRegistrationResult(StartingIntent)";
_handleregistrationresult(_startingintent);
 break; }
case 1: {
 //BA.debugLineNum = 22;BA.debugLine="Log(\"recived\")";
anywheresoftware.b4a.keywords.Common.Log("recived");
 //BA.debugLineNum = 23;BA.debugLine="MessageArrived(StartingIntent)";
_messagearrived(_startingintent);
 break; }
}
;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
}
