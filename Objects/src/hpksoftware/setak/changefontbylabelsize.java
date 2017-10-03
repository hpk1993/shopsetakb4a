package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class changefontbylabelsize {
private static changefontbylabelsize mostCurrent = new changefontbylabelsize();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
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
public hpksoftware.setak.function _function = null;
public hpksoftware.setak.show_nazar _show_nazar = null;
public hpksoftware.setak.nazar _nazar = null;
public hpksoftware.setak.push_active _push_active = null;
public hpksoftware.setak.result _result = null;
public hpksoftware.setak.disconnect _disconnect = null;
public static String  _fit_view_textsize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _i = 0;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
 //BA.debugLineNum = 35;BA.debugLine="Public Sub fit_view_textsize(v As View)";
 //BA.debugLineNum = 36;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 37;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 39;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="lbl=v";
_lbl.setObject((android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 41;BA.debugLine="If lbl.Text <> \"\" Then";
if ((_lbl.getText()).equals("") == false) { 
 //BA.debugLineNum = 42;BA.debugLine="For i = 1 To 1000";
{
final int step6 = 1;
final int limit6 = (int) (1000);
for (_i = (int) (1) ; (step6 > 0 && _i <= limit6) || (step6 < 0 && _i >= limit6); _i = ((int)(0 + _i + step6)) ) {
 //BA.debugLineNum = 43;BA.debugLine="lbl.TextSize = i";
_lbl.setTextSize((float) (_i));
 //BA.debugLineNum = 44;BA.debugLine="If su.MeasureMultilineTextHeight(lbl, lbl.Tex";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),BA.ObjectToCharSequence(_lbl.getText()))==_lbl.getHeight()) { 
 //BA.debugLineNum = 45;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),BA.ObjectToCharSequence(_lbl.getText()))>_lbl.getHeight()) { 
 //BA.debugLineNum = 47;BA.debugLine="lbl.TextSize = i-1";
_lbl.setTextSize((float) (_i-1));
 //BA.debugLineNum = 48;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 }else if(_v.getObjectOrNull() instanceof android.widget.Button) { 
 //BA.debugLineNum = 57;BA.debugLine="Dim btn As Button";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 58;BA.debugLine="btn=v";
_btn.setObject((android.widget.Button)(_v.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="If btn.Text <> \"\" Then";
if ((_btn.getText()).equals("") == false) { 
 //BA.debugLineNum = 60;BA.debugLine="For i = 1 To 1000";
{
final int step20 = 1;
final int limit20 = (int) (1000);
for (_i = (int) (1) ; (step20 > 0 && _i <= limit20) || (step20 < 0 && _i >= limit20); _i = ((int)(0 + _i + step20)) ) {
 //BA.debugLineNum = 61;BA.debugLine="btn.TextSize = i";
_btn.setTextSize((float) (_i));
 //BA.debugLineNum = 62;BA.debugLine="If su.MeasureMultilineTextHeight(btn, btn.Tex";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_btn.getObject()),BA.ObjectToCharSequence(_btn.getText()))==_btn.getHeight()) { 
 //BA.debugLineNum = 63;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_btn.getObject()),BA.ObjectToCharSequence(_btn.getText()))>_btn.getHeight()) { 
 //BA.debugLineNum = 65;BA.debugLine="btn.TextSize = i-1";
_btn.setTextSize((float) (_i-1));
 //BA.debugLineNum = 66;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 };
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _fittobuttonsize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ButtonWrapper _l) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
 //BA.debugLineNum = 5;BA.debugLine="Sub FitTobuttonSize(L As Button)";
 //BA.debugLineNum = 6;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 7;BA.debugLine="If L.Text <> \"\" Then";
if ((_l.getText()).equals("") == false) { 
 //BA.debugLineNum = 8;BA.debugLine="For i = 1 To 1000";
{
final int step3 = 1;
final int limit3 = (int) (1000);
for (_i = (int) (1) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 9;BA.debugLine="L.TextSize = i";
_l.setTextSize((float) (_i));
 //BA.debugLineNum = 10;BA.debugLine="If su.MeasureMultilineTextHeight(L, L.Text) = L.";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))==_l.getHeight()) { 
 //BA.debugLineNum = 11;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))>_l.getHeight()) { 
 //BA.debugLineNum = 13;BA.debugLine="L.TextSize = i-1";
_l.setTextSize((float) (_i-1));
 //BA.debugLineNum = 14;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _fittolabelsize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _l) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
 //BA.debugLineNum = 20;BA.debugLine="Sub FitToLabelSize(L As Label)";
 //BA.debugLineNum = 21;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 22;BA.debugLine="If L.Text <> \"\" Then";
if ((_l.getText()).equals("") == false) { 
 //BA.debugLineNum = 23;BA.debugLine="For i = 1 To 1000";
{
final int step3 = 1;
final int limit3 = (int) (1000);
for (_i = (int) (1) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 24;BA.debugLine="L.TextSize = i";
_l.setTextSize((float) (_i));
 //BA.debugLineNum = 25;BA.debugLine="If su.MeasureMultilineTextHeight(L, L.Text) = L.";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))==_l.getHeight()) { 
 //BA.debugLineNum = 26;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))>_l.getHeight()) { 
 //BA.debugLineNum = 28;BA.debugLine="L.TextSize = i-1";
_l.setTextSize((float) (_i-1));
 //BA.debugLineNum = 29;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _minimomfontlabel(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _l,int _font) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
int _i = 0;
 //BA.debugLineNum = 74;BA.debugLine="Sub MinimomFontLabel(L As Label, Font As Int)";
 //BA.debugLineNum = 75;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 76;BA.debugLine="If L.Text <> \"\" Then";
if ((_l.getText()).equals("") == false) { 
 //BA.debugLineNum = 77;BA.debugLine="For i = 1 To Font";
{
final int step3 = 1;
final int limit3 = _font;
for (_i = (int) (1) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 78;BA.debugLine="L.TextSize = i";
_l.setTextSize((float) (_i));
 //BA.debugLineNum = 79;BA.debugLine="If su.MeasureMultilineTextHeight(L, L.Text) = L.";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))==_l.getHeight()) { 
 //BA.debugLineNum = 80;BA.debugLine="Exit";
if (true) break;
 }else if(_su.MeasureMultilineTextHeight((android.widget.TextView)(_l.getObject()),BA.ObjectToCharSequence(_l.getText()))>_l.getHeight()) { 
 //BA.debugLineNum = 82;BA.debugLine="L.TextSize = i-1";
_l.setTextSize((float) (_i-1));
 //BA.debugLineNum = 83;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 };
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return "";
}
}
