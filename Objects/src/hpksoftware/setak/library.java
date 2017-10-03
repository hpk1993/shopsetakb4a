package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class library {
private static library mostCurrent = new library();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
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
public static float  _art(anywheresoftware.b4a.BA _ba,String _txt,anywheresoftware.b4a.objects.LabelWrapper _label,anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font,int _size,int _line_space) throws Exception{
float _before = 0f;
float _after = 0f;
anywheresoftware.b4a.agraham.reflection.Reflection _obj1 = null;
 //BA.debugLineNum = 9;BA.debugLine="Sub art(txt As String,label As Label,font As Typef";
 //BA.debugLineNum = 10;BA.debugLine="Dim before,after As Float";
_before = 0f;
_after = 0f;
 //BA.debugLineNum = 11;BA.debugLine="Dim obj1 As Reflector";
_obj1 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 12;BA.debugLine="label.Text=txt";
_label.setText(BA.ObjectToCharSequence(_txt));
 //BA.debugLineNum = 13;BA.debugLine="label.TextSize=size";
_label.setTextSize((float) (_size));
 //BA.debugLineNum = 14;BA.debugLine="label.Gravity=Gravity.CENTER";
_label.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 15;BA.debugLine="label.Left=label.Left";
_label.setLeft(_label.getLeft());
 //BA.debugLineNum = 16;BA.debugLine="label.Typeface=font";
_label.setTypeface((android.graphics.Typeface)(_font.getObject()));
 //BA.debugLineNum = 18;BA.debugLine="ht=utils_str.MeasureMultilineTextHeight(label,txt";
_ht = (float) (_utils_str.MeasureMultilineTextHeight((android.widget.TextView)(_label.getObject()),BA.ObjectToCharSequence(_txt)));
 //BA.debugLineNum = 19;BA.debugLine="obj1.Target=label";
_obj1.Target = (Object)(_label.getObject());
 //BA.debugLineNum = 20;BA.debugLine="before=obj1.RunMethod(\"getLineHeight\")";
_before = (float)(BA.ObjectToNumber(_obj1.RunMethod("getLineHeight")));
 //BA.debugLineNum = 21;BA.debugLine="obj1.Target=label";
_obj1.Target = (Object)(_label.getObject());
 //BA.debugLineNum = 22;BA.debugLine="obj1.RunMethod3(\"setLineSpacing\", .10,\"java.lang.";
_obj1.RunMethod3("setLineSpacing",BA.NumberToString(.10),"java.lang.float",BA.NumberToString(_line_space),"java.lang.float");
 //BA.debugLineNum = 23;BA.debugLine="obj1.Target=label";
_obj1.Target = (Object)(_label.getObject());
 //BA.debugLineNum = 24;BA.debugLine="after=obj1.RunMethod(\"getLineHeight\")";
_after = (float)(BA.ObjectToNumber(_obj1.RunMethod("getLineHeight")));
 //BA.debugLineNum = 25;BA.debugLine="ht=((after * ht)/before)";
_ht = (float) (((_after*_ht)/(double)_before));
 //BA.debugLineNum = 28;BA.debugLine="Return ht";
if (true) return _ht;
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return 0f;
}
public static float  _get_height_label(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lbl,String _str) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl1 = null;
 //BA.debugLineNum = 31;BA.debugLine="Public Sub get_height_label(lbl As Label,str As St";
 //BA.debugLineNum = 32;BA.debugLine="Dim lbl1 As Label";
_lbl1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="lbl1.Initialize(\"\")";
_lbl1.Initialize(_ba,"");
 //BA.debugLineNum = 34;BA.debugLine="lbl1=lbl";
_lbl1 = _lbl;
 //BA.debugLineNum = 35;BA.debugLine="Return utils_str.MeasureMultilineTextHeight(lbl1,";
if (true) return (float) (_utils_str.MeasureMultilineTextHeight((android.widget.TextView)(_lbl1.getObject()),BA.ObjectToCharSequence(_str)));
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return 0f;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Private utils_str As StringUtils";
_utils_str = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 5;BA.debugLine="Dim ht As Float";
_ht = 0f;
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
}
