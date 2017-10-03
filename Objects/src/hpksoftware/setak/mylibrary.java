package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mylibrary {
private static mylibrary mostCurrent = new mylibrary();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean _first = false;
public static anywheresoftware.b4a.objects.MediaPlayerWrapper _me1 = null;
public static anywheresoftware.b4a.student.PersianDate _daye_per = null;
public static anywheresoftware.b4a.objects.StringUtils _utils_str = null;
public static float _ht = 0f;
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
public hpksoftware.setak.pushservice _pushservice = null;
public hpksoftware.setak.function _function = null;
public hpksoftware.setak.show_nazar _show_nazar = null;
public hpksoftware.setak.nazar _nazar = null;
public hpksoftware.setak.push_active _push_active = null;
public hpksoftware.setak.result _result = null;
public hpksoftware.setak.disconnect _disconnect = null;
public hpksoftware.setak.changefontbylabelsize _changefontbylabelsize = null;
public static String  _animationview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _view1,int _fromx,String _fromy,int _tox,int _toy) throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub AnimationView(View1 As View,fromx As Int,fromy";
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static float  _art(anywheresoftware.b4a.BA _ba,String _txt,anywheresoftware.b4a.objects.LabelWrapper _label,anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font,int _size,int _line_space) throws Exception{
float _before = 0f;
float _after = 0f;
anywheresoftware.b4a.agraham.reflection.Reflection _obj1 = null;
anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString _r1 = null;
 //BA.debugLineNum = 285;BA.debugLine="Sub art(txt As String,label As Label,font As Typef";
 //BA.debugLineNum = 286;BA.debugLine="Dim before,after As Float";
_before = 0f;
_after = 0f;
 //BA.debugLineNum = 287;BA.debugLine="Dim obj1 As Reflector";
_obj1 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 290;BA.debugLine="Dim r1 As RichString";
_r1 = new anywheresoftware.b4a.agraham.richstring.RichStringBuilder.RichString();
 //BA.debugLineNum = 291;BA.debugLine="r1.Initialize(txt)";
_r1.Initialize(BA.ObjectToCharSequence(_txt));
 //BA.debugLineNum = 292;BA.debugLine="r1.Style2(r1.STYLE_BOLD,\"{B}\")";
_r1.Style2(_r1.STYLE_BOLD,"{B}");
 //BA.debugLineNum = 293;BA.debugLine="r1.Style2(r1.STYLE_ITALIC,\"{I}\")";
_r1.Style2(_r1.STYLE_ITALIC,"{I}");
 //BA.debugLineNum = 294;BA.debugLine="r1.Style2(r1.STYLE_BOLD_ITALIC,\"{B_I}\")";
_r1.Style2(_r1.STYLE_BOLD_ITALIC,"{B_I}");
 //BA.debugLineNum = 295;BA.debugLine="r1.Subscript2(\"{SUBSCRPT}\")";
_r1.Subscript2("{SUBSCRPT}");
 //BA.debugLineNum = 296;BA.debugLine="r1.Strikethrough2(\"{STRIK}\")";
_r1.Strikethrough2("{STRIK}");
 //BA.debugLineNum = 298;BA.debugLine="r1.Underscore2(\"{UNDER}\")";
_r1.Underscore2("{UNDER}");
 //BA.debugLineNum = 299;BA.debugLine="r1.Color2(0xFF096DC6,\"{BLUE}\")";
_r1.Color2((int) (0xff096dc6),"{BLUE}");
 //BA.debugLineNum = 300;BA.debugLine="r1.Color2(0xFFDC0C4C,\"{RED}\")";
_r1.Color2((int) (0xffdc0c4c),"{RED}");
 //BA.debugLineNum = 301;BA.debugLine="r1.Color2(0xFF035E03,\"{GREEN}\")";
_r1.Color2((int) (0xff035e03),"{GREEN}");
 //BA.debugLineNum = 302;BA.debugLine="r1.Color2(0xFFEDF40E,\"{YELLOW}\")";
_r1.Color2((int) (0xffedf40e),"{YELLOW}");
 //BA.debugLineNum = 303;BA.debugLine="r1.Color2(0xFFBB09A0,\"{MAGENTA}\")";
_r1.Color2((int) (0xffbb09a0),"{MAGENTA}");
 //BA.debugLineNum = 305;BA.debugLine="r1.BackColor2(0xFF68B2F4,\"{BG_BLUE}\")";
_r1.BackColor2((int) (0xff68b2f4),"{BG_BLUE}");
 //BA.debugLineNum = 306;BA.debugLine="r1.BackColor2(0xFFED1054,\"{BG_RED}\")";
_r1.BackColor2((int) (0xffed1054),"{BG_RED}");
 //BA.debugLineNum = 307;BA.debugLine="r1.BackColor2(0xFF46F958,\"{BG_GREEN}\")";
_r1.BackColor2((int) (0xff46f958),"{BG_GREEN}");
 //BA.debugLineNum = 308;BA.debugLine="r1.BackColor2(0xFFE1F52E,\"{BG_YELLOW}\")";
_r1.BackColor2((int) (0xffe1f52e),"{BG_YELLOW}");
 //BA.debugLineNum = 309;BA.debugLine="r1.BackColor2(0xFFEC32D0,\"{BG_MAGENTA}\")";
_r1.BackColor2((int) (0xffec32d0),"{BG_MAGENTA}");
 //BA.debugLineNum = 311;BA.debugLine="r1.RelativeSize2(1,\"{BIG1}\")";
_r1.RelativeSize2((float) (1),"{BIG1}");
 //BA.debugLineNum = 312;BA.debugLine="r1.RelativeSize2(2,\"{BIG2}\")";
_r1.RelativeSize2((float) (2),"{BIG2}");
 //BA.debugLineNum = 313;BA.debugLine="r1.RelativeSize2(3,\"{BIG3}\")";
_r1.RelativeSize2((float) (3),"{BIG3}");
 //BA.debugLineNum = 314;BA.debugLine="r1.RelativeSize2(4,\"{BIG4}\")";
_r1.RelativeSize2((float) (4),"{BIG4}");
 //BA.debugLineNum = 316;BA.debugLine="label.Text=r1";
_label.setText(BA.ObjectToCharSequence(_r1.getObject()));
 //BA.debugLineNum = 317;BA.debugLine="label.TextSize=size";
_label.setTextSize((float) (_size));
 //BA.debugLineNum = 318;BA.debugLine="label.Gravity=Gravity.RIGHT";
_label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 319;BA.debugLine="label.Left=label.Left";
_label.setLeft(_label.getLeft());
 //BA.debugLineNum = 320;BA.debugLine="label.Typeface=font";
_label.setTypeface((android.graphics.Typeface)(_font.getObject()));
 //BA.debugLineNum = 322;BA.debugLine="ht=utils_str.MeasureMultilineTextHeight(label,txt";
_ht = (float) (_utils_str.MeasureMultilineTextHeight((android.widget.TextView)(_label.getObject()),BA.ObjectToCharSequence(_txt)));
 //BA.debugLineNum = 323;BA.debugLine="obj1.Target=label";
_obj1.Target = (Object)(_label.getObject());
 //BA.debugLineNum = 324;BA.debugLine="before=obj1.RunMethod(\"getLineHeight\")";
_before = (float)(BA.ObjectToNumber(_obj1.RunMethod("getLineHeight")));
 //BA.debugLineNum = 325;BA.debugLine="obj1.Target=label";
_obj1.Target = (Object)(_label.getObject());
 //BA.debugLineNum = 326;BA.debugLine="obj1.RunMethod3(\"setLineSpacing\", .10,\"java.lang.";
_obj1.RunMethod3("setLineSpacing",BA.NumberToString(.10),"java.lang.float",BA.NumberToString(_line_space),"java.lang.float");
 //BA.debugLineNum = 327;BA.debugLine="obj1.Target=label";
_obj1.Target = (Object)(_label.getObject());
 //BA.debugLineNum = 328;BA.debugLine="after=obj1.RunMethod(\"getLineHeight\")";
_after = (float)(BA.ObjectToNumber(_obj1.RunMethod("getLineHeight")));
 //BA.debugLineNum = 329;BA.debugLine="ht=((after * ht)/before)";
_ht = (float) (((_after*_ht)/(double)_before));
 //BA.debugLineNum = 332;BA.debugLine="Return ht";
if (true) return _ht;
 //BA.debugLineNum = 333;BA.debugLine="End Sub";
return 0f;
}
public static String  _base64(anywheresoftware.b4a.BA _ba,String _txt) throws Exception{
anywheresoftware.b4a.agraham.byteconverter.ByteConverter _converter = null;
byte[] _byte1 = null;
anywheresoftware.b4a.objects.StringUtils _st = null;
 //BA.debugLineNum = 145;BA.debugLine="public Sub base64(txt As String)as String";
 //BA.debugLineNum = 146;BA.debugLine="Dim converter As ByteConverter";
_converter = new anywheresoftware.b4a.agraham.byteconverter.ByteConverter();
 //BA.debugLineNum = 147;BA.debugLine="Dim byte1() As Byte";
_byte1 = new byte[(int) (0)];
;
 //BA.debugLineNum = 148;BA.debugLine="byte1=converter.StringToBytes(txt,\"UTF-8\")";
_byte1 = _converter.StringToBytes(_txt,"UTF-8");
 //BA.debugLineNum = 149;BA.debugLine="Dim st As StringUtils";
_st = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 150;BA.debugLine="Return st.EncodeBase64	(byte1)";
if (true) return _st.EncodeBase64(_byte1);
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static boolean  _bookmark(anywheresoftware.b4a.BA _ba,String _sid) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 111;BA.debugLine="Sub bookmark(sID As String) As Boolean";
 //BA.debugLineNum = 112;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 113;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 114;BA.debugLine="If File.Exists(File.DirInternal,\"bookmark\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark")) { 
 //BA.debugLineNum = 115;BA.debugLine="m1 = File.ReadMap(File.DirInternal,\"bookmark\")";
_m1 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark");
 };
 //BA.debugLineNum = 117;BA.debugLine="If m1.ContainsKey(sID) = True Then";
if (_m1.ContainsKey((Object)(_sid))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 118;BA.debugLine="m1.Remove(sID)";
_m1.Remove((Object)(_sid));
 //BA.debugLineNum = 119;BA.debugLine="File.WriteMap(File.DirInternal,\"bookmark\",m1)";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark",_m1);
 //BA.debugLineNum = 120;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 122;BA.debugLine="m1.Put(sID,sID)";
_m1.Put((Object)(_sid),(Object)(_sid));
 //BA.debugLineNum = 123;BA.debugLine="File.WriteMap(File.DirInternal,\"bookmark\",m1)";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark",_m1);
 //BA.debugLineNum = 124;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return false;
}
public static String  _change_font_view(anywheresoftware.b4a.BA _ba,boolean _label_view,boolean _button_view,boolean _edittext_view,boolean _spiner_view,boolean _check_view,anywheresoftware.b4a.objects.ActivityWrapper _activity,String _font) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _obj = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl1 = null;
anywheresoftware.b4a.objects.EditTextWrapper _txt1 = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn1 = null;
anywheresoftware.b4a.objects.SpinnerWrapper _spin1 = null;
anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _check1 = null;
 //BA.debugLineNum = 239;BA.debugLine="Public Sub change_font_view(label_view As Boolean,";
 //BA.debugLineNum = 240;BA.debugLine="For Each obj As View In activity.GetAllViewsRecur";
_obj = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
final anywheresoftware.b4a.BA.IterableList group1 = _activity.GetAllViewsRecursive();
final int groupLen1 = group1.getSize();
for (int index1 = 0;index1 < groupLen1 ;index1++){
_obj.setObject((android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 241;BA.debugLine="If label_view=True Then";
if (_label_view==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 242;BA.debugLine="If obj Is Label Then";
if (_obj.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 243;BA.debugLine="Dim lbl1 As Label";
_lbl1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 244;BA.debugLine="lbl1=obj";
_lbl1.setObject((android.widget.TextView)(_obj.getObject()));
 //BA.debugLineNum = 245;BA.debugLine="lbl1.Typeface=Typeface.LoadFromAssets(font)";
_lbl1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets(_font));
 };
 }else if(_edittext_view==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 248;BA.debugLine="If obj Is EditText Then";
if (_obj.getObjectOrNull() instanceof android.widget.EditText) { 
 //BA.debugLineNum = 249;BA.debugLine="Dim txt1 As EditText";
_txt1 = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 250;BA.debugLine="txt1=obj";
_txt1.setObject((android.widget.EditText)(_obj.getObject()));
 //BA.debugLineNum = 251;BA.debugLine="txt1.Typeface=Typeface.LoadFromAssets(font)";
_txt1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets(_font));
 };
 }else if(_button_view==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 254;BA.debugLine="If obj Is Button Then";
if (_obj.getObjectOrNull() instanceof android.widget.Button) { 
 //BA.debugLineNum = 255;BA.debugLine="Dim btn1 As Button";
_btn1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 256;BA.debugLine="btn1=obj";
_btn1.setObject((android.widget.Button)(_obj.getObject()));
 //BA.debugLineNum = 257;BA.debugLine="btn1.Typeface=Typeface.LoadFromAssets(font)";
_btn1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets(_font));
 };
 }else if(_spiner_view==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 260;BA.debugLine="If obj Is Spinner Then";
if (_obj.getObjectOrNull() instanceof anywheresoftware.b4a.objects.SpinnerWrapper.B4ASpinner) { 
 //BA.debugLineNum = 261;BA.debugLine="Dim spin1 As Spinner";
_spin1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 262;BA.debugLine="spin1=obj";
_spin1.setObject((anywheresoftware.b4a.objects.SpinnerWrapper.B4ASpinner)(_obj.getObject()));
 };
 }else if(_check_view==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 266;BA.debugLine="If obj Is CheckBox Then";
if (_obj.getObjectOrNull() instanceof android.widget.CheckBox) { 
 //BA.debugLineNum = 267;BA.debugLine="Dim check1 As CheckBox";
_check1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 268;BA.debugLine="check1=obj";
_check1.setObject((android.widget.CheckBox)(_obj.getObject()));
 //BA.debugLineNum = 269;BA.debugLine="check1.Typeface=Typeface.LoadFromAssets(font)";
_check1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets(_font));
 };
 };
 }
;
 //BA.debugLineNum = 273;BA.debugLine="End Sub";
return "";
}
public static boolean  _checkbookmark(anywheresoftware.b4a.BA _ba,String _sid) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 128;BA.debugLine="Sub checkBookmark(sID As String) As Boolean";
 //BA.debugLineNum = 129;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 130;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 131;BA.debugLine="If File.Exists(File.DirInternal,\"bookmark\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark")) { 
 //BA.debugLineNum = 132;BA.debugLine="m1 = File.ReadMap(File.DirInternal,\"bookmark\")";
_m1 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark");
 //BA.debugLineNum = 133;BA.debugLine="If m1.ContainsKey(sID) = True Then";
if (_m1.ContainsKey((Object)(_sid))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 134;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 136;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 139;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return false;
}
public static boolean  _checkinternet(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.phone.Phone _ph = null;
anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper _mylan = null;
 //BA.debugLineNum = 39;BA.debugLine="Sub CheckInternet As Boolean";
 //BA.debugLineNum = 54;BA.debugLine="Dim ph As Phone";
_ph = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 55;BA.debugLine="Dim mylan As ServerSocket";
_mylan = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 56;BA.debugLine="If (ph.GetDataState <> \"CONNECTED\" And mylan.GetM";
if (((_ph.GetDataState()).equals("CONNECTED") == false && (_mylan.GetMyIP()).equals("127.0.0.1")) || (_mylan.GetMyIP()).equals("127.0.0.1")) { 
 //BA.debugLineNum = 57;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 59;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return false;
}
public static String  _covertenglish2persian(anywheresoftware.b4a.BA _ba,String _str) throws Exception{
String _te = "";
 //BA.debugLineNum = 423;BA.debugLine="Sub CovertEnglish2Persian(Str As String) As String";
 //BA.debugLineNum = 424;BA.debugLine="Dim te As String";
_te = "";
 //BA.debugLineNum = 425;BA.debugLine="te = Str";
_te = _str;
 //BA.debugLineNum = 426;BA.debugLine="te = te.Replace(\"1\",\"۱\")";
_te = _te.replace("1","۱");
 //BA.debugLineNum = 427;BA.debugLine="te = te.Replace(\"2\",\"۲\")";
_te = _te.replace("2","۲");
 //BA.debugLineNum = 428;BA.debugLine="te = te.Replace(\"3\",\"۳\")";
_te = _te.replace("3","۳");
 //BA.debugLineNum = 429;BA.debugLine="te = te.Replace(\"4\",\"۴\")";
_te = _te.replace("4","۴");
 //BA.debugLineNum = 430;BA.debugLine="te = te.Replace(\"5\",\"۵\")";
_te = _te.replace("5","۵");
 //BA.debugLineNum = 431;BA.debugLine="te = te.Replace(\"6\",\"۶\")";
_te = _te.replace("6","۶");
 //BA.debugLineNum = 432;BA.debugLine="te = te.Replace(\"7\",\"۷\")";
_te = _te.replace("7","۷");
 //BA.debugLineNum = 433;BA.debugLine="te = te.Replace(\"8\",\"۸\")";
_te = _te.replace("8","۸");
 //BA.debugLineNum = 434;BA.debugLine="te = te.Replace(\"9\",\"۹\")";
_te = _te.replace("9","۹");
 //BA.debugLineNum = 435;BA.debugLine="te = te.Replace(\"0\",\"۰\")";
_te = _te.replace("0","۰");
 //BA.debugLineNum = 436;BA.debugLine="Return te";
if (true) return _te;
 //BA.debugLineNum = 437;BA.debugLine="End Sub";
return "";
}
public static String  _covertpersian2english(anywheresoftware.b4a.BA _ba,String _str) throws Exception{
String _te = "";
 //BA.debugLineNum = 408;BA.debugLine="Sub CovertPersian2English(Str As String) As String";
 //BA.debugLineNum = 409;BA.debugLine="Dim te As String";
_te = "";
 //BA.debugLineNum = 410;BA.debugLine="te = Str";
_te = _str;
 //BA.debugLineNum = 411;BA.debugLine="te = te.Replace(\"۱\",\"1\")";
_te = _te.replace("۱","1");
 //BA.debugLineNum = 412;BA.debugLine="te = te.Replace(\"۲\",\"2\")";
_te = _te.replace("۲","2");
 //BA.debugLineNum = 413;BA.debugLine="te = te.Replace(\"۳\",\"3\")";
_te = _te.replace("۳","3");
 //BA.debugLineNum = 414;BA.debugLine="te = te.Replace(\"۴\",\"4\")";
_te = _te.replace("۴","4");
 //BA.debugLineNum = 415;BA.debugLine="te = te.Replace(\"۵\",\"5\")";
_te = _te.replace("۵","5");
 //BA.debugLineNum = 416;BA.debugLine="te = te.Replace(\"۶\",\"6\")";
_te = _te.replace("۶","6");
 //BA.debugLineNum = 417;BA.debugLine="te = te.Replace(\"۷\",\"7\")";
_te = _te.replace("۷","7");
 //BA.debugLineNum = 418;BA.debugLine="te = te.Replace(\"۸\",\"8\")";
_te = _te.replace("۸","8");
 //BA.debugLineNum = 419;BA.debugLine="te = te.Replace(\"۹\",\"9\")";
_te = _te.replace("۹","9");
 //BA.debugLineNum = 420;BA.debugLine="te = te.Replace(\"۰\",\"0\")";
_te = _te.replace("۰","0");
 //BA.debugLineNum = 421;BA.debugLine="Return te";
if (true) return _te;
 //BA.debugLineNum = 422;BA.debugLine="End Sub";
return "";
}
public static String  _fit_view_textsize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
 //BA.debugLineNum = 340;BA.debugLine="Public Sub fit_view_textsize(v As View)";
 //BA.debugLineNum = 341;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 342;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 344;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 345;BA.debugLine="lbl=v";
_lbl.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 346;BA.debugLine="If lbl.Text <> \"\" Then";
if ((_lbl.getText()).equals("") == false) { 
 //BA.debugLineNum = 347;BA.debugLine="For i = 1 To 1000";
{
final int step6 = 1;
final int limit6 = (int) (1000);
for (_i = (int) (1) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 348;BA.debugLine="lbl.TextSize = i";
_lbl.setTextSize((float) (_i));
 //BA.debugLineNum = 349;BA.debugLine="If su.MeasureMultilineTextHeight(lbl, lbl.Tex";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),BA.ObjectToCharSequence(_lbl.getText()))==_lbl.getHeight()) { 
 //BA.debugLineNum = 350;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),BA.ObjectToCharSequence(_lbl.getText()))>_lbl.getHeight()) { 
 //BA.debugLineNum = 352;BA.debugLine="lbl.TextSize = i-1";
_lbl.setTextSize((float) (_i-1));
 //BA.debugLineNum = 353;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 };
 //BA.debugLineNum = 377;BA.debugLine="End Sub";
return "";
}
public static float  _get_height_label(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lbl,String _str) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl1 = null;
 //BA.debugLineNum = 232;BA.debugLine="Public Sub get_height_label(lbl As Label,str As St";
 //BA.debugLineNum = 233;BA.debugLine="Dim lbl1 As Label";
_lbl1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 234;BA.debugLine="lbl1.Initialize(\"\")";
_lbl1.Initialize(_ba,"");
 //BA.debugLineNum = 235;BA.debugLine="lbl1=lbl";
_lbl1 = _lbl;
 //BA.debugLineNum = 236;BA.debugLine="Return utils_str.MeasureMultilineTextHeight(lbl1,";
if (true) return (float) (_utils_str.MeasureMultilineTextHeight((android.widget.TextView)(_lbl1.getObject()),BA.ObjectToCharSequence(_str)));
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return 0f;
}
public static anywheresoftware.b4a.objects.collections.Map  _getbookmarklist(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m1 = null;
 //BA.debugLineNum = 154;BA.debugLine="Sub getbookmarklist As Map";
 //BA.debugLineNum = 155;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 156;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 158;BA.debugLine="If File.Exists(File.DirInternal,\"bookmark\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark")) { 
 //BA.debugLineNum = 159;BA.debugLine="m1 = File.ReadMap(File.DirInternal,\"bookmark\")";
_m1 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"bookmark");
 //BA.debugLineNum = 160;BA.debugLine="Return m1";
if (true) return _m1;
 }else {
 //BA.debugLineNum = 162;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 };
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return null;
}
public static int  _getcolor(anywheresoftware.b4a.BA _ba,String _color) throws Exception{
 //BA.debugLineNum = 178;BA.debugLine="Sub getColor(color As String) As Int";
 //BA.debugLineNum = 179;BA.debugLine="Select color";
switch (BA.switchObjectToInt(_color,"Blue","Red","Green","Yellow","Black")) {
case 0: {
 //BA.debugLineNum = 181;BA.debugLine="Return Colors.RGB(36,92,154)";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (36),(int) (92),(int) (154));
 break; }
case 1: {
 //BA.debugLineNum = 183;BA.debugLine="Return Colors.RGB(202,40,40)";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (202),(int) (40),(int) (40));
 break; }
case 2: {
 //BA.debugLineNum = 185;BA.debugLine="Return Colors.RGB(26,159,16)";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (26),(int) (159),(int) (16));
 break; }
case 3: {
 //BA.debugLineNum = 187;BA.debugLine="Return Colors.RGB(212,203,53)";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (212),(int) (203),(int) (53));
 break; }
case 4: {
 //BA.debugLineNum = 189;BA.debugLine="Return Colors.Black";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Black;
 break; }
}
;
 //BA.debugLineNum = 191;BA.debugLine="Return Colors.RGB(36,92,154)";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (36),(int) (92),(int) (154));
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return 0;
}
public static float  _getdevicephysicalsize(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.keywords.LayoutValues _lv = null;
 //BA.debugLineNum = 439;BA.debugLine="Sub GetDevicePhysicalSize As Float";
 //BA.debugLineNum = 440;BA.debugLine="Dim lv As LayoutValues";
_lv = new anywheresoftware.b4a.keywords.LayoutValues();
 //BA.debugLineNum = 441;BA.debugLine="lv = GetDeviceLayoutValues";
_lv = anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(_ba);
 //BA.debugLineNum = 442;BA.debugLine="Return Sqrt(Power(lv.Height / lv.Scale / 160,";
if (true) return (float) (anywheresoftware.b4a.keywords.Common.Sqrt(anywheresoftware.b4a.keywords.Common.Power(_lv.Height/(double)_lv.Scale/(double)160,2)+anywheresoftware.b4a.keywords.Common.Power(_lv.Width/(double)_lv.Scale/(double)160,2)));
 //BA.debugLineNum = 443;BA.debugLine="End Sub";
return 0f;
}
public static String  _list_to_arry(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _lst) throws Exception{
String _str = "";
int _i = 0;
 //BA.debugLineNum = 195;BA.debugLine="Public Sub list_to_arry(lst As List) As String";
 //BA.debugLineNum = 196;BA.debugLine="Dim str As String";
_str = "";
 //BA.debugLineNum = 197;BA.debugLine="For i=0 To lst.Size-1";
{
final int step2 = 1;
final int limit2 = (int) (_lst.getSize()-1);
for (_i = (int) (0) ; (step2 > 0 && _i <= limit2) || (step2 < 0 && _i >= limit2); _i = ((int)(0 + _i + step2)) ) {
 //BA.debugLineNum = 198;BA.debugLine="If i=0 Then";
if (_i==0) { 
 //BA.debugLineNum = 199;BA.debugLine="str=lst.Get(i) & \";\"";
_str = BA.ObjectToString(_lst.Get(_i))+";";
 }else if(_i>0 && _i<_lst.getSize()-1) { 
 //BA.debugLineNum = 201;BA.debugLine="str=str & lst.Get(i) & \";\"";
_str = _str+BA.ObjectToString(_lst.Get(_i))+";";
 }else if(_i==_lst.getSize()-1) { 
 //BA.debugLineNum = 203;BA.debugLine="str=str & lst.Get(i)";
_str = _str+BA.ObjectToString(_lst.Get(_i));
 };
 }
};
 //BA.debugLineNum = 206;BA.debugLine="Return str";
if (true) return _str;
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public static String  _minimomfontbutton(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ButtonWrapper _l,int _font) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
 //BA.debugLineNum = 393;BA.debugLine="public Sub MinimomFontbutton(L As Button, Font As";
 //BA.debugLineNum = 394;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 395;BA.debugLine="If L.Text <> \"\" Then";
if ((_l.getText()).equals("") == false) { 
 //BA.debugLineNum = 396;BA.debugLine="For i = 1 To Font";
{
final int step3 = 1;
final int limit3 = _font;
for (_i = (int) (1) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 397;BA.debugLine="L.TextSize = i";
_l.setTextSize((float) (_i));
 //BA.debugLineNum = 398;BA.debugLine="If su.MeasureMultilineTextHeight(L, L.Text) = L.";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))==_l.getHeight()) { 
 //BA.debugLineNum = 399;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))>_l.getHeight()) { 
 //BA.debugLineNum = 401;BA.debugLine="L.TextSize = i-1";
_l.setTextSize((float) (_i-1));
 //BA.debugLineNum = 402;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 //BA.debugLineNum = 406;BA.debugLine="End Sub";
return "";
}
public static String  _minimomfontlabel(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _l,int _font) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
 //BA.debugLineNum = 379;BA.debugLine="public Sub MinimomFontLabel(L As Label, Font As In";
 //BA.debugLineNum = 380;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 381;BA.debugLine="If L.Text <> \"\" Then";
if ((_l.getText()).equals("") == false) { 
 //BA.debugLineNum = 382;BA.debugLine="For i = 1 To Font";
{
final int step3 = 1;
final int limit3 = _font;
for (_i = (int) (1) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 383;BA.debugLine="L.TextSize = i";
_l.setTextSize((float) (_i));
 //BA.debugLineNum = 384;BA.debugLine="If su.MeasureMultilineTextHeight(L, L.Text) = L.";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))==_l.getHeight()) { 
 //BA.debugLineNum = 385;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))>_l.getHeight()) { 
 //BA.debugLineNum = 387;BA.debugLine="L.TextSize = i-1";
_l.setTextSize((float) (_i-1));
 //BA.debugLineNum = 388;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 //BA.debugLineNum = 392;BA.debugLine="End Sub";
return "";
}
public static String  _openbazaarapp(anywheresoftware.b4a.BA _ba,String _spackagename,boolean _blnopinion) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _market = null;
String _uri = "";
 //BA.debugLineNum = 85;BA.debugLine="Sub OpenBazaarApp(sPackageName As String,blnOpinio";
 //BA.debugLineNum = 86;BA.debugLine="Dim market As Intent, uri As String";
_market = new anywheresoftware.b4a.objects.IntentWrapper();
_uri = "";
 //BA.debugLineNum = 87;BA.debugLine="uri=\"bazaar://details?id=\" & sPackageName";
_uri = "bazaar://details?id="+_spackagename;
 //BA.debugLineNum = 88;BA.debugLine="If blnOpinion = False Then";
if (_blnopinion==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 89;BA.debugLine="market.Initialize(market.ACTION_EDIT,uri)";
_market.Initialize(_market.ACTION_EDIT,_uri);
 }else {
 //BA.debugLineNum = 91;BA.debugLine="market.Initialize(market.ACTION_VIEW,uri)";
_market.Initialize(_market.ACTION_VIEW,_uri);
 };
 //BA.debugLineNum = 93;BA.debugLine="StartActivity(market)";
anywheresoftware.b4a.keywords.Common.StartActivity(_ba,(Object)(_market.getObject()));
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static Object  _persion_date(anywheresoftware.b4a.BA _ba,String _p) throws Exception{
String _date = "";
String _y = "";
String _m = "";
String _d = "";
String[] _arrtemp = null;
 //BA.debugLineNum = 209;BA.debugLine="Sub persion_date(p As String)As Object";
 //BA.debugLineNum = 210;BA.debugLine="Dim date,y,m,d As String";
_date = "";
_y = "";
_m = "";
_d = "";
 //BA.debugLineNum = 211;BA.debugLine="date=daye_per.getDate(0,0,0,\"/\")";
_date = _daye_per.getDate((int) (0),(int) (0),(int) (0),"/");
 //BA.debugLineNum = 212;BA.debugLine="Log(\"//////////\" & date)";
anywheresoftware.b4a.keywords.Common.Log("//////////"+_date);
 //BA.debugLineNum = 213;BA.debugLine="Dim arrtemp() As String";
_arrtemp = new String[(int) (0)];
java.util.Arrays.fill(_arrtemp,"");
 //BA.debugLineNum = 214;BA.debugLine="arrtemp=Regex.Split(\"/\",date)";
_arrtemp = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_date);
 //BA.debugLineNum = 215;BA.debugLine="Log(arrtemp(0))";
anywheresoftware.b4a.keywords.Common.Log(_arrtemp[(int) (0)]);
 //BA.debugLineNum = 216;BA.debugLine="Log(arrtemp(1))";
anywheresoftware.b4a.keywords.Common.Log(_arrtemp[(int) (1)]);
 //BA.debugLineNum = 217;BA.debugLine="Log(arrtemp(2))";
anywheresoftware.b4a.keywords.Common.Log(_arrtemp[(int) (2)]);
 //BA.debugLineNum = 218;BA.debugLine="Select p";
switch (BA.switchObjectToInt(_p,"y","m","d","y/m/d")) {
case 0: {
 //BA.debugLineNum = 220;BA.debugLine="Return arrtemp(0)";
if (true) return (Object)(_arrtemp[(int) (0)]);
 break; }
case 1: {
 //BA.debugLineNum = 222;BA.debugLine="Return arrtemp(1)";
if (true) return (Object)(_arrtemp[(int) (1)]);
 break; }
case 2: {
 //BA.debugLineNum = 224;BA.debugLine="Return arrtemp(2)";
if (true) return (Object)(_arrtemp[(int) (2)]);
 break; }
case 3: {
 //BA.debugLineNum = 227;BA.debugLine="Return (arrtemp(0) & \"/\" & arrtemp(1) & \"/\" & a";
if (true) return (Object)((_arrtemp[(int) (0)]+"/"+_arrtemp[(int) (1)]+"/"+_arrtemp[(int) (2)]));
 break; }
}
;
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim first As Boolean";
_first = false;
 //BA.debugLineNum = 16;BA.debugLine="Dim me1 As MediaPlayer";
_me1 = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private daye_per As PersianDate";
_daye_per = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 19;BA.debugLine="Private utils_str As StringUtils";
_utils_str = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 20;BA.debugLine="Dim ht As Float";
_ht = 0f;
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _rute(anywheresoftware.b4a.BA _ba) throws Exception{
String _path = "";
 //BA.debugLineNum = 493;BA.debugLine="Sub rute() As String";
 //BA.debugLineNum = 494;BA.debugLine="Dim path As String";
_path = "";
 //BA.debugLineNum = 496;BA.debugLine="If File.ExternalWritable=True Then";
if (anywheresoftware.b4a.keywords.Common.File.getExternalWritable()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 497;BA.debugLine="path=File.DirDefaultExternal";
_path = anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal();
 }else {
 //BA.debugLineNum = 499;BA.debugLine="path=File.DirInternal";
_path = anywheresoftware.b4a.keywords.Common.File.getDirInternal();
 };
 //BA.debugLineNum = 501;BA.debugLine="Return path";
if (true) return _path;
 //BA.debugLineNum = 502;BA.debugLine="End Sub";
return "";
}
public static String  _sendmail(anywheresoftware.b4a.BA _ba,String _sto,String _sbody,String _ssubject) throws Exception{
anywheresoftware.b4a.phone.Phone.Email _p1 = null;
 //BA.debugLineNum = 96;BA.debugLine="Sub SendMail(sTo As String,sBody As String,sSubjec";
 //BA.debugLineNum = 97;BA.debugLine="Try";
try { //BA.debugLineNum = 98;BA.debugLine="Dim p1 As Email";
_p1 = new anywheresoftware.b4a.phone.Phone.Email();
 //BA.debugLineNum = 99;BA.debugLine="p1.To.add(sTo)";
_p1.To.Add((Object)(_sto));
 //BA.debugLineNum = 100;BA.debugLine="p1.Subject = sSubject";
_p1.Subject = _ssubject;
 //BA.debugLineNum = 101;BA.debugLine="p1.Body    = sBody";
_p1.Body = _sbody;
 //BA.debugLineNum = 102;BA.debugLine="ProgressDialogShow2(\"در حال ارسال\",True)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(_ba,BA.ObjectToCharSequence("در حال ارسال"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 103;BA.debugLine="StartActivity(p1.GetIntent)";
anywheresoftware.b4a.keywords.Common.StartActivity(_ba,(Object)(_p1.GetIntent()));
 //BA.debugLineNum = 104;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e10) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e10); //BA.debugLineNum = 106;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 107;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(_ba).getMessage());
 };
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _sendsms(anywheresoftware.b4a.BA _ba,String _phonenumber,String _text) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _in = null;
 //BA.debugLineNum = 32;BA.debugLine="Sub SendSms(PhoneNumber As String, Text As String)";
 //BA.debugLineNum = 33;BA.debugLine="Dim In As Intent";
_in = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 34;BA.debugLine="In.Initialize(In.ACTION_VIEW, \"sms:\" & PhoneNu";
_in.Initialize(_in.ACTION_VIEW,"sms:"+_phonenumber);
 //BA.debugLineNum = 35;BA.debugLine="In.PutExtra(\"sms_body\", Text)";
_in.PutExtra("sms_body",(Object)(_text));
 //BA.debugLineNum = 36;BA.debugLine="StartActivity(In)";
anywheresoftware.b4a.keywords.Common.StartActivity(_ba,(Object)(_in.getObject()));
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static String  _set_title_background(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b) throws Exception{
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 470;BA.debugLine="Sub set_title_background(v As View,b As Bitmap)";
 //BA.debugLineNum = 471;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 472;BA.debugLine="bd.Initialize(b)";
_bd.Initialize((android.graphics.Bitmap)(_b.getObject()));
 //BA.debugLineNum = 474;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 475;BA.debugLine="r.Target=bd";
_r.Target = (Object)(_bd.getObject());
 //BA.debugLineNum = 476;BA.debugLine="r.RunMethod3(\"setTileModeXY\",\"REPEAT\",\"android.gr";
_r.RunMethod3("setTileModeXY","REPEAT","android.graphics.Shader$TileMode","REPEAT","android.graphics.Shader$TileMode");
 //BA.debugLineNum = 477;BA.debugLine="v.Background=bd";
_v.setBackground((android.graphics.drawable.Drawable)(_bd.getObject()));
 //BA.debugLineNum = 478;BA.debugLine="End Sub";
return "";
}
public static String  _setanimation(anywheresoftware.b4a.BA _ba,String _inanimation,String _outanimation) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
String _package = "";
int _in = 0;
int _out = 0;
 //BA.debugLineNum = 167;BA.debugLine="Sub SetAnimation(InAnimation As String, OutAnimati";
 //BA.debugLineNum = 168;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 169;BA.debugLine="Dim package As String";
_package = "";
 //BA.debugLineNum = 170;BA.debugLine="Dim In, out As Int";
_in = 0;
_out = 0;
 //BA.debugLineNum = 171;BA.debugLine="package = r.GetStaticField(\"anywheresoftware.b";
_package = BA.ObjectToString(_r.GetStaticField("anywheresoftware.b4a.BA","packageName"));
 //BA.debugLineNum = 172;BA.debugLine="In = r.GetStaticField(package & \".R$anim\", InA";
_in = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$anim",_inanimation)));
 //BA.debugLineNum = 173;BA.debugLine="out = r.GetStaticField(package & \".R$anim\", Ou";
_out = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$anim",_outanimation)));
 //BA.debugLineNum = 174;BA.debugLine="r.Target = r.GetActivity";
_r.Target = (Object)(_r.GetActivity((_ba.processBA == null ? _ba : _ba.processBA)));
 //BA.debugLineNum = 175;BA.debugLine="r.RunMethod4(\"overridePendingTransition\", Arra";
_r.RunMethod4("overridePendingTransition",new Object[]{(Object)(_in),(Object)(_out)},new String[]{"java.lang.int","java.lang.int"});
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public static String  _setcursorvisible(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _edt,boolean _visible) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 481;BA.debugLine="Sub setCursorVisible(edt As EditText, Visible As B";
 //BA.debugLineNum = 482;BA.debugLine="Dim jo = edt As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo.setObject((java.lang.Object)(_edt.getObject()));
 //BA.debugLineNum = 483;BA.debugLine="jo.RunMethod(\"setCursorVisible\", Array As Obje";
_jo.RunMethod("setCursorVisible",new Object[]{(Object)(_visible)});
 //BA.debugLineNum = 484;BA.debugLine="End Sub";
return "";
}
public static String  _setdivider(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ListViewWrapper _lv,int _color,int _height) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 276;BA.debugLine="Sub SetDivider(lv As ListView, Color As Int, Heigh";
 //BA.debugLineNum = 277;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 278;BA.debugLine="r.Target = lv";
_r.Target = (Object)(_lv.getObject());
 //BA.debugLineNum = 279;BA.debugLine="Dim CD As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 280;BA.debugLine="CD.Initialize(Color, 0)";
_cd.Initialize(_color,(int) (0));
 //BA.debugLineNum = 281;BA.debugLine="r.RunMethod4(\"setDivider\", Array As Object(CD),";
_r.RunMethod4("setDivider",new Object[]{(Object)(_cd.getObject())},new String[]{"android.graphics.drawable.Drawable"});
 //BA.debugLineNum = 282;BA.debugLine="r.RunMethod2(\"setDividerHeight\", Height, \"java.l";
_r.RunMethod2("setDividerHeight",BA.NumberToString(_height),"java.lang.int");
 //BA.debugLineNum = 283;BA.debugLine="End Sub";
return "";
}
public static String  _setstatusbarcolor(anywheresoftware.b4a.BA _ba,int _clr) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _window = null;
 //BA.debugLineNum = 72;BA.debugLine="Sub SetStatusBarColor(clr As Int)";
 //BA.debugLineNum = 73;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 74;BA.debugLine="If p.SdkVersion >= 21 Then";
if (_p.getSdkVersion()>=21) { 
 //BA.debugLineNum = 75;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 76;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 77;BA.debugLine="Dim window As JavaObject = jo.RunMethodJO(\"ge";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = _jo.RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 78;BA.debugLine="window.RunMethod(\"addFlags\", Array (0x8000000";
_window.RunMethod("addFlags",new Object[]{(Object)(0x80000000)});
 //BA.debugLineNum = 79;BA.debugLine="window.RunMethod(\"clearFlags\", Array (0x04000";
_window.RunMethod("clearFlags",new Object[]{(Object)(0x04000000)});
 //BA.debugLineNum = 80;BA.debugLine="window.RunMethod(\"setStatusBarColor\", Array(c";
_window.RunMethod("setStatusBarColor",new Object[]{(Object)(_clr)});
 };
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _settextisselectable(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.EditTextWrapper _edt,boolean _selectable) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 488;BA.debugLine="Sub setTextIsSelectable(edt As EditText, Selectabl";
 //BA.debugLineNum = 489;BA.debugLine="Dim jo = edt As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo.setObject((java.lang.Object)(_edt.getObject()));
 //BA.debugLineNum = 490;BA.debugLine="jo.RunMethod(\"setTextIsSelectable\", Array As O";
_jo.RunMethod("setTextIsSelectable",new Object[]{(Object)(_selectable)});
 //BA.debugLineNum = 491;BA.debugLine="End Sub";
return "";
}
public static String  _sharecontent(anywheresoftware.b4a.BA _ba,String _scontent,String _stitle) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _share = null;
 //BA.debugLineNum = 23;BA.debugLine="Sub ShareContent(sContent As String,sTitle As Stri";
 //BA.debugLineNum = 24;BA.debugLine="Dim share As Intent";
_share = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 25;BA.debugLine="share.Initialize(share.ACTION_SEND,\"\")";
_share.Initialize(_share.ACTION_SEND,"");
 //BA.debugLineNum = 26;BA.debugLine="share.SetType(\"text/plain\")";
_share.SetType("text/plain");
 //BA.debugLineNum = 27;BA.debugLine="share.PutExtra(\"android.intent.extra.TEXT\", sConte";
_share.PutExtra("android.intent.extra.TEXT",(Object)(_scontent));
 //BA.debugLineNum = 28;BA.debugLine="share.WrapAsIntentChooser(sTitle)";
_share.WrapAsIntentChooser(_stitle);
 //BA.debugLineNum = 29;BA.debugLine="StartActivity(share)";
anywheresoftware.b4a.keywords.Common.StartActivity(_ba,(Object)(_share.getObject()));
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
public static String  _time_ago(anywheresoftware.b4a.BA _ba,int _time_now,int _t) throws Exception{
int _result1 = 0;
int _result2 = 0;
String _ago = "";
 //BA.debugLineNum = 446;BA.debugLine="Sub time_ago(time_now As Int,t As Int)As String";
 //BA.debugLineNum = 447;BA.debugLine="Dim result1,Result2 As Int";
_result1 = 0;
_result2 = 0;
 //BA.debugLineNum = 448;BA.debugLine="Dim ago As String";
_ago = "";
 //BA.debugLineNum = 450;BA.debugLine="result1=time_now-t";
_result1 = (int) (_time_now-_t);
 //BA.debugLineNum = 451;BA.debugLine="result1=result1/60";
_result1 = (int) (_result1/(double)60);
 //BA.debugLineNum = 453;BA.debugLine="If(result1=0 ) Then";
if ((_result1==0)) { 
 //BA.debugLineNum = 454;BA.debugLine="Return \"لحظاتی پیش\"";
if (true) return "لحظاتی پیش";
 }else if((_result1>=1 && _result1<=59)) { 
 //BA.debugLineNum = 456;BA.debugLine="Result2=Round(result1)";
_result2 = (int) (anywheresoftware.b4a.keywords.Common.Round(_result1));
 //BA.debugLineNum = 457;BA.debugLine="ago=\"دقیقه پیش\"";
_ago = "دقیقه پیش";
 //BA.debugLineNum = 458;BA.debugLine="Return Result2 & \" \" & ago";
if (true) return BA.NumberToString(_result2)+" "+_ago;
 }else if((_result1>=60 && _result1<=1440)) { 
 //BA.debugLineNum = 460;BA.debugLine="Result2=Round(result1/60)";
_result2 = (int) (anywheresoftware.b4a.keywords.Common.Round(_result1/(double)60));
 //BA.debugLineNum = 461;BA.debugLine="ago=\"ساعت پیش\"";
_ago = "ساعت پیش";
 //BA.debugLineNum = 462;BA.debugLine="Return Result2 & \" \" & ago";
if (true) return BA.NumberToString(_result2)+" "+_ago;
 }else if((_result1>=1441)) { 
 //BA.debugLineNum = 464;BA.debugLine="Result2=Round(result1/60/24)";
_result2 = (int) (anywheresoftware.b4a.keywords.Common.Round(_result1/(double)60/(double)24));
 //BA.debugLineNum = 465;BA.debugLine="ago=\"روز پیش\"";
_ago = "روز پیش";
 //BA.debugLineNum = 466;BA.debugLine="Return Result2 & \" \" & ago";
if (true) return BA.NumberToString(_result2)+" "+_ago;
 };
 //BA.debugLineNum = 468;BA.debugLine="End Sub";
return "";
}
}
