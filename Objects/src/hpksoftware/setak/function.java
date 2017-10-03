package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class function {
private static function mostCurrent = new function();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static int _set_color_title = 0;
public static int _set_color_msg = 0;
public static int _set_color_positive = 0;
public static int _set_color_negetive = 0;
public static int _set_color_cancel = 0;
public static int _set_size_text = 0;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _set_font_text = null;
public static String _set_gravity_text = "";
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
public hpksoftware.setak.pushservice _pushservice = null;
public hpksoftware.setak.show_nazar _show_nazar = null;
public hpksoftware.setak.nazar _nazar = null;
public hpksoftware.setak.push_active _push_active = null;
public hpksoftware.setak.result _result = null;
public hpksoftware.setak.disconnect _disconnect = null;
public hpksoftware.setak.changefontbylabelsize _changefontbylabelsize = null;
public static String  _hide_scrollbar_horizontal(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _v) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 22;BA.debugLine="Sub hide_scrollbar_horizontal(v As HorizontalScrol";
 //BA.debugLineNum = 23;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 24;BA.debugLine="r.Target = v";
_r.Target = (Object)(_v.getObject());
 //BA.debugLineNum = 25;BA.debugLine="r.RunMethod2(\"setHorizontalScrollBarEnabled\", Fal";
_r.RunMethod2("setHorizontalScrollBarEnabled",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.False),"java.lang.boolean");
 //BA.debugLineNum = 26;BA.debugLine="r.RunMethod2(\"setOverScrollMode\", 2, \"java.lang.i";
_r.RunMethod2("setOverScrollMode",BA.NumberToString(2),"java.lang.int");
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _initialize_spotdialog(anywheresoftware.b4a.BA _ba,dmax.dialog.Spotsd _progress_spot) throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub initialize_spotdialog(progress_spot As SpotsDi";
 //BA.debugLineNum = 18;BA.debugLine="progress_spot.Initialize2(\"در حال بارگذاری اطلاعا";
_progress_spot.Initialize2(_ba,BA.ObjectToCharSequence("در حال بارگذاری اطلاعات لطفا شکیبا باشید.."),_progress_spot.Theme_Custom,anywheresoftware.b4a.keywords.Common.Colors.White,(float) (12),(android.graphics.Typeface)(mostCurrent._starter._font_body.getObject()),(int) (6),(int) (0xff0be721),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static boolean  _is_admin(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _user_type = 0;
 //BA.debugLineNum = 62;BA.debugLine="Sub is_admin() As Boolean";
 //BA.debugLineNum = 63;BA.debugLine="If File.Exists(myLibrary.rute,Starter.filename_us";
if (anywheresoftware.b4a.keywords.Common.File.Exists(mostCurrent._mylibrary._rute(_ba),mostCurrent._starter._filename_user)==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 64;BA.debugLine="Dim lst As List=File.ReadList(myLibrary.rute,Sta";
_lst = new anywheresoftware.b4a.objects.collections.List();
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(mostCurrent._mylibrary._rute(_ba),mostCurrent._starter._filename_user);
 //BA.debugLineNum = 65;BA.debugLine="Dim user_type As Int= lst.Get(4)";
_user_type = (int)(BA.ObjectToNumber(_lst.Get((int) (4))));
 //BA.debugLineNum = 66;BA.debugLine="If user_type=1 Then";
if (_user_type==1) { 
 //BA.debugLineNum = 67;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 69;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 };
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return false;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Public set_color_title As Int=0xFF1C1B1C";
_set_color_title = (int) (0xff1c1b1c);
 //BA.debugLineNum = 6;BA.debugLine="Public set_color_msg As Int=0xFF020202";
_set_color_msg = (int) (0xff020202);
 //BA.debugLineNum = 7;BA.debugLine="Public set_color_positive As Int=0xFF033055";
_set_color_positive = (int) (0xff033055);
 //BA.debugLineNum = 8;BA.debugLine="Public set_color_negetive As Int=0xFF033055";
_set_color_negetive = (int) (0xff033055);
 //BA.debugLineNum = 9;BA.debugLine="Public set_color_cancel As Int=0xFF033055";
_set_color_cancel = (int) (0xff033055);
 //BA.debugLineNum = 10;BA.debugLine="Public set_size_text As Int=15";
_set_size_text = (int) (15);
 //BA.debugLineNum = 11;BA.debugLine="Public set_font_text As Typeface=Starter.font_bod";
_set_font_text = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_set_font_text = mostCurrent._starter._font_body;
 //BA.debugLineNum = 12;BA.debugLine="Public set_Gravity_text As String=Gravity.RIGHT";
_set_gravity_text = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.ParsMSGBOX  _style_msgbox(anywheresoftware.b4a.BA _ba,String _title,String _msg,String _positive,String _negetive,String _cancel) throws Exception{
anywheresoftware.b4a.ParsMSGBOX _pd = null;
 //BA.debugLineNum = 34;BA.debugLine="Sub style_msgbox(title As String,msg As String,pos";
 //BA.debugLineNum = 35;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 36;BA.debugLine="Pd.Initialize(\"pd\")";
_pd.Initialize(_ba,"pd");
 //BA.debugLineNum = 37;BA.debugLine="Pd.setTitle(title,set_Gravity_text,set_color_titl";
_pd.setTitle(_ba,_title,(int)(Double.parseDouble(_set_gravity_text)),_set_color_title,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 38;BA.debugLine="Pd.setMessage(msg,set_Gravity_text,set_color_msg,";
_pd.setMessage(_ba,_msg,(int)(Double.parseDouble(_set_gravity_text)),_set_color_msg,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 39;BA.debugLine="Pd.setNegative(negetive,set_color_negetive,set_si";
_pd.setNegative(_ba,_negetive,_set_color_negetive,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="Pd.setNeutral(cancel,set_color_cancel,set_size_te";
_pd.setNeutral(_ba,_cancel,_set_color_cancel,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 41;BA.debugLine="Pd.setPositive(positive,set_color_positive,set_si";
_pd.setPositive(_ba,_positive,_set_color_positive,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 42;BA.debugLine="Return Pd";
if (true) return _pd;
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.ParsMSGBOX  _style_msgbox2(anywheresoftware.b4a.BA _ba,String _title,String _msg) throws Exception{
String _cancel = "";
anywheresoftware.b4a.ParsMSGBOX _pd = null;
 //BA.debugLineNum = 50;BA.debugLine="Sub style_msgbox2(title As String,msg As String) A";
 //BA.debugLineNum = 51;BA.debugLine="Dim cancel As String=\"بسیار خب\"";
_cancel = "بسیار خب";
 //BA.debugLineNum = 52;BA.debugLine="Dim  Pd As ParsMSGBOX";
_pd = new anywheresoftware.b4a.ParsMSGBOX();
 //BA.debugLineNum = 53;BA.debugLine="Pd.Initialize(\"pd\")";
_pd.Initialize(_ba,"pd");
 //BA.debugLineNum = 54;BA.debugLine="Pd.setTitle(title,set_Gravity_text,set_color_titl";
_pd.setTitle(_ba,_title,(int)(Double.parseDouble(_set_gravity_text)),_set_color_title,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 55;BA.debugLine="Pd.setMessage(msg,set_Gravity_text,set_color_msg,";
_pd.setMessage(_ba,_msg,(int)(Double.parseDouble(_set_gravity_text)),_set_color_msg,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 57;BA.debugLine="Pd.setNeutral(cancel,set_color_cancel,set_size_te";
_pd.setNeutral(_ba,_cancel,_set_color_cancel,(float) (_set_size_text),(android.graphics.Typeface)(_set_font_text.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="Return Pd";
if (true) return _pd;
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return null;
}
}
