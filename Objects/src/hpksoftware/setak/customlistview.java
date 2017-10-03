package hpksoftware.setak;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class customlistview extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "hpksoftware.setak.customlistview");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", hpksoftware.setak.customlistview.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
public anywheresoftware.b4a.objects.collections.List _items = null;
public anywheresoftware.b4a.objects.collections.List _panels = null;
public float _dividerheight = 0f;
public int _div_color = 0;
public Object _presseddrawable = null;
public String _eventname = "";
public Object _callback = null;
public anywheresoftware.b4a.objects.StringUtils _su = null;
public int _defaulttextsize = 0;
public int _defaulttextcolor = 0;
public int _defaulttextbackgroundcolor = 0;
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
public hpksoftware.setak.changefontbylabelsize _changefontbylabelsize = null;
public String  _add(anywheresoftware.b4a.objects.PanelWrapper _pnl,int _itemheight,Object _value) throws Exception{
 //BA.debugLineNum = 167;BA.debugLine="Public Sub Add(Pnl As Panel, ItemHeight As Int, Va";
 //BA.debugLineNum = 168;BA.debugLine="InsertAt(items.Size, Pnl, ItemHeight, Value)";
_insertat(_items.getSize(),_pnl,_itemheight,_value);
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public String  _addtextitem(String _text,Object _value) throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Public Sub AddTextItem(Text As String, Value As Ob";
 //BA.debugLineNum = 97;BA.debugLine="InsertAtTextItem(items.Size, Text, Value)";
_insertattextitem(_items.getSize(),_text,_value);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.ConcreteViewWrapper  _asview() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Public Sub AsView As View";
 //BA.debugLineNum = 60;BA.debugLine="Return sv";
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_sv.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return null;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private sv As ScrollView";
_sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 4;BA.debugLine="Private items As List";
_items = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 5;BA.debugLine="Private panels As List";
_panels = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 6;BA.debugLine="Public dividerHeight As Float";
_dividerheight = 0f;
 //BA.debugLineNum = 8;BA.debugLine="Public div_color As Int'=Colors.White";
_div_color = 0;
 //BA.debugLineNum = 10;BA.debugLine="Private pressedDrawable As Object";
_presseddrawable = new Object();
 //BA.debugLineNum = 11;BA.debugLine="Private EventName As String";
_eventname = "";
 //BA.debugLineNum = 12;BA.debugLine="Private CallBack As Object";
_callback = new Object();
 //BA.debugLineNum = 13;BA.debugLine="Private su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 14;BA.debugLine="Public DefaultTextSize As Int";
_defaulttextsize = 0;
 //BA.debugLineNum = 15;BA.debugLine="Public DefaultTextColor As Int";
_defaulttextcolor = 0;
 //BA.debugLineNum = 16;BA.debugLine="Public DefaultTextBackgroundColor As Int";
_defaulttextbackgroundcolor = 0;
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public String  _clear() throws Exception{
int _i = 0;
 //BA.debugLineNum = 49;BA.debugLine="Public Sub Clear";
 //BA.debugLineNum = 50;BA.debugLine="items.Clear";
_items.Clear();
 //BA.debugLineNum = 51;BA.debugLine="panels.Clear";
_panels.Clear();
 //BA.debugLineNum = 52;BA.debugLine="sv.Panel.Height = 0";
_sv.getPanel().setHeight((int) (0));
 //BA.debugLineNum = 53;BA.debugLine="For i = sv.Panel.NumberOfViews - 1 To 0 Step -1";
{
final int step4 = (int) (-1);
final int limit4 = (int) (0);
for (_i = (int) (_sv.getPanel().getNumberOfViews()-1) ; (step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4); _i = ((int)(0 + _i + step4)) ) {
 //BA.debugLineNum = 54;BA.debugLine="sv.Panel.RemoveViewAt(i)";
_sv.getPanel().RemoveViewAt(_i);
 }
};
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public int  _getheight() throws Exception{
 //BA.debugLineNum = 197;BA.debugLine="Public Sub getHeight As Int";
 //BA.debugLineNum = 198;BA.debugLine="Return sv.Height";
if (true) return _sv.getHeight();
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return 0;
}
public String  _getitemfromview(anywheresoftware.b4a.objects.ConcreteViewWrapper _v) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _parent = null;
Object _current = null;
 //BA.debugLineNum = 210;BA.debugLine="Public Sub GetItemFromView(v As View)";
 //BA.debugLineNum = 211;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 212;BA.debugLine="Dim parent, current As Object";
_parent = new Object();
_current = new Object();
 //BA.debugLineNum = 213;BA.debugLine="parent = v";
_parent = (Object)(_v.getObject());
 //BA.debugLineNum = 214;BA.debugLine="Do While (parent Is Panel) = False Or sv.Panel <>";
while ((_parent instanceof android.view.ViewGroup)==__c.False || (_sv.getPanel()).equals((android.view.ViewGroup)(_parent)) == false) {
 //BA.debugLineNum = 215;BA.debugLine="current = parent";
_current = _parent;
 //BA.debugLineNum = 216;BA.debugLine="r.Target = current";
_r.Target = _current;
 //BA.debugLineNum = 217;BA.debugLine="parent = r.RunMethod(\"getParent\")";
_parent = _r.RunMethod("getParent");
 }
;
 //BA.debugLineNum = 219;BA.debugLine="v = current";
_v.setObject((android.view.View)(_current));
 //BA.debugLineNum = 220;BA.debugLine="Return v.Tag";
if (true) return BA.ObjectToString(_v.getTag());
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.PanelWrapper  _getpanel(int _index) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 69;BA.debugLine="Public Sub GetPanel(Index As Int) As Panel";
 //BA.debugLineNum = 70;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="p = panels.get(Index) 'this is the parent panel";
_p.setObject((android.view.ViewGroup)(_panels.Get(_index)));
 //BA.debugLineNum = 72;BA.debugLine="Return p.GetView(0)";
if (true) return (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_p.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return null;
}
public String  _getpanelheight() throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Public Sub getPanelHeight";
 //BA.debugLineNum = 194;BA.debugLine="Return sv.Panel.Height";
if (true) return BA.NumberToString(_sv.getPanel().getHeight());
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public int  _getsize() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Public Sub GetSize As Int";
 //BA.debugLineNum = 65;BA.debugLine="Return items.Size";
if (true) return _items.getSize();
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return 0;
}
public Object  _getvalue(int _index) throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Public Sub GetValue(Index As Int) As Object";
 //BA.debugLineNum = 77;BA.debugLine="Return items.get(Index)";
if (true) return _items.Get(_index);
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _vcallback,String _veventname,int _divcolor,int _div_height) throws Exception{
innerInitialize(_ba);
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _idpressed = 0;
 //BA.debugLineNum = 22;BA.debugLine="Public Sub Initialize (vCallback As Object, vEvent";
 //BA.debugLineNum = 23;BA.debugLine="sv.Initialize2(0, \"sv\")";
_sv.Initialize2(ba,(int) (0),"sv");
 //BA.debugLineNum = 24;BA.debugLine="items.Initialize";
_items.Initialize();
 //BA.debugLineNum = 25;BA.debugLine="panels.Initialize";
_panels.Initialize();
 //BA.debugLineNum = 27;BA.debugLine="If divcolor=0 Then";
if (_divcolor==0) { 
 //BA.debugLineNum = 28;BA.debugLine="div_color=Colors.White";
_div_color = __c.Colors.White;
 }else {
 //BA.debugLineNum = 30;BA.debugLine="div_color=divcolor";
_div_color = _divcolor;
 };
 //BA.debugLineNum = 33;BA.debugLine="dividerHeight = div_Height";
_dividerheight = (float) (_div_height);
 //BA.debugLineNum = 34;BA.debugLine="EventName = vEventName";
_eventname = _veventname;
 //BA.debugLineNum = 35;BA.debugLine="CallBack = vCallback";
_callback = _vcallback;
 //BA.debugLineNum = 36;BA.debugLine="sv.Color = div_color'0xFFD9D7DE 'this sets the di";
_sv.setColor(_div_color);
 //BA.debugLineNum = 37;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 38;BA.debugLine="Dim idPressed As Int";
_idpressed = 0;
 //BA.debugLineNum = 39;BA.debugLine="idPressed = r.GetStaticField(\"android.R$drawab";
_idpressed = (int)(BA.ObjectToNumber(_r.GetStaticField("android.R$drawable","list_selector_background")));
 //BA.debugLineNum = 40;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(ba));
 //BA.debugLineNum = 41;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
 //BA.debugLineNum = 42;BA.debugLine="pressedDrawable = r.RunMethod2(\"getDrawable\", idP";
_presseddrawable = _r.RunMethod2("getDrawable",BA.NumberToString(_idpressed),"java.lang.int");
 //BA.debugLineNum = 43;BA.debugLine="DefaultTextColor = Colors.White";
_defaulttextcolor = __c.Colors.White;
 //BA.debugLineNum = 44;BA.debugLine="DefaultTextSize = 16";
_defaulttextsize = (int) (16);
 //BA.debugLineNum = 45;BA.debugLine="DefaultTextBackgroundColor = Colors.Black";
_defaulttextbackgroundcolor = __c.Colors.Black;
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public String  _insertat(int _index,anywheresoftware.b4a.objects.PanelWrapper _pnl,int _itemheight,Object _value) throws Exception{
anywheresoftware.b4a.objects.drawable.StateListDrawable _sd = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
int _top = 0;
anywheresoftware.b4a.objects.PanelWrapper _previouspanel = null;
anywheresoftware.b4a.objects.PanelWrapper _p2 = null;
int _i = 0;
 //BA.debugLineNum = 119;BA.debugLine="Public Sub InsertAt(Index As Int, Pnl As Panel, It";
 //BA.debugLineNum = 121;BA.debugLine="Dim sd As StateListDrawable";
_sd = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 122;BA.debugLine="sd.Initialize";
_sd.Initialize();
 //BA.debugLineNum = 123;BA.debugLine="sd.AddState(sd.State_Pressed, pressedDrawable)";
_sd.AddState(_sd.State_Pressed,(android.graphics.drawable.Drawable)(_presseddrawable));
 //BA.debugLineNum = 124;BA.debugLine="sd.AddCatchAllState(Pnl.Background)";
_sd.AddCatchAllState(_pnl.getBackground());
 //BA.debugLineNum = 127;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 128;BA.debugLine="p.Initialize(\"panel\")";
_p.Initialize(ba,"panel");
 //BA.debugLineNum = 129;BA.debugLine="p.Background = sd";
_p.setBackground((android.graphics.drawable.Drawable)(_sd.getObject()));
 //BA.debugLineNum = 130;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 131;BA.debugLine="cd.Initialize(Colors.Transparent, 0)";
_cd.Initialize(__c.Colors.Transparent,(int) (0));
 //BA.debugLineNum = 132;BA.debugLine="Pnl.Background = cd";
_pnl.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="p.AddView(Pnl, 0, 0, sv.Width, ItemHeight)";
_p.AddView((android.view.View)(_pnl.getObject()),(int) (0),(int) (0),_sv.getWidth(),_itemheight);
 //BA.debugLineNum = 134;BA.debugLine="p.Tag = Index";
_p.setTag((Object)(_index));
 //BA.debugLineNum = 136;BA.debugLine="If Index = items.Size Then";
if (_index==_items.getSize()) { 
 //BA.debugLineNum = 137;BA.debugLine="items.Add(Value)";
_items.Add(_value);
 //BA.debugLineNum = 138;BA.debugLine="panels.Add(p)";
_panels.Add((Object)(_p.getObject()));
 //BA.debugLineNum = 139;BA.debugLine="Dim top As Int";
_top = 0;
 //BA.debugLineNum = 140;BA.debugLine="If Index = 0 Then top = dividerHeight Else top =";
if (_index==0) { 
_top = (int) (_dividerheight);}
else {
_top = _sv.getPanel().getHeight();};
 //BA.debugLineNum = 141;BA.debugLine="sv.Panel.AddView(p, 0, top, sv.Width, ItemHeight";
_sv.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),_top,_sv.getWidth(),_itemheight);
 }else {
 //BA.debugLineNum = 143;BA.debugLine="Dim top As Int";
_top = 0;
 //BA.debugLineNum = 144;BA.debugLine="If Index = 0 Then";
if (_index==0) { 
 //BA.debugLineNum = 145;BA.debugLine="top = dividerHeight";
_top = (int) (_dividerheight);
 }else {
 //BA.debugLineNum = 147;BA.debugLine="Dim previousPanel As Panel";
_previouspanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 148;BA.debugLine="previousPanel = panels.get(Index - 1)";
_previouspanel.setObject((android.view.ViewGroup)(_panels.Get((int) (_index-1))));
 //BA.debugLineNum = 149;BA.debugLine="top = previousPanel.top + previousPanel.Height";
_top = (int) (_previouspanel.getTop()+_previouspanel.getHeight()+_dividerheight);
 };
 //BA.debugLineNum = 152;BA.debugLine="Dim p2 As Panel";
_p2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 153;BA.debugLine="For i = Index To panels.Size - 1";
{
final int step29 = 1;
final int limit29 = (int) (_panels.getSize()-1);
for (_i = _index ; (step29 > 0 && _i <= limit29) || (step29 < 0 && _i >= limit29); _i = ((int)(0 + _i + step29)) ) {
 //BA.debugLineNum = 154;BA.debugLine="p2 = panels.get(i)";
_p2.setObject((android.view.ViewGroup)(_panels.Get(_i)));
 //BA.debugLineNum = 155;BA.debugLine="p2.top = p2.top + ItemHeight + dividerHeight";
_p2.setTop((int) (_p2.getTop()+_itemheight+_dividerheight));
 //BA.debugLineNum = 156;BA.debugLine="p2.Tag = i + 1";
_p2.setTag((Object)(_i+1));
 }
};
 //BA.debugLineNum = 158;BA.debugLine="items.InsertAt(Index, Value)";
_items.InsertAt(_index,_value);
 //BA.debugLineNum = 159;BA.debugLine="panels.InsertAt(Index, p)";
_panels.InsertAt(_index,(Object)(_p.getObject()));
 //BA.debugLineNum = 160;BA.debugLine="sv.Panel.AddView(p, 0, top, sv.Width, ItemHeight";
_sv.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),_top,_sv.getWidth(),_itemheight);
 };
 //BA.debugLineNum = 162;BA.debugLine="sv.Panel.Height = sv.Panel.Height + ItemHeight +";
_sv.getPanel().setHeight((int) (_sv.getPanel().getHeight()+_itemheight+_dividerheight));
 //BA.debugLineNum = 163;BA.debugLine="If items.Size = 1 Then sv.Panel.Height = sv.Panel";
if (_items.getSize()==1) { 
_sv.getPanel().setHeight((int) (_sv.getPanel().getHeight()+_dividerheight));};
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public String  _insertattextitem(int _index,String _text,Object _value) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
int _minheight = 0;
 //BA.debugLineNum = 101;BA.debugLine="Public Sub InsertAtTextItem(Index As Int, Text As";
 //BA.debugLineNum = 102;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 103;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(ba,"");
 //BA.debugLineNum = 104;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 105;BA.debugLine="lbl.Initialize(\"\")";
_lbl.Initialize(ba,"");
 //BA.debugLineNum = 106;BA.debugLine="lbl.Gravity = Bit.Or(Gravity.CENTER_VERTICAL, Gra";
_lbl.setGravity(__c.Bit.Or(__c.Gravity.CENTER_VERTICAL,__c.Gravity.LEFT));
 //BA.debugLineNum = 107;BA.debugLine="pnl.AddView(lbl, 5dip, 2dip, sv.Width - 5dip, 20d";
_pnl.AddView((android.view.View)(_lbl.getObject()),__c.DipToCurrent((int) (5)),__c.DipToCurrent((int) (2)),(int) (_sv.getWidth()-__c.DipToCurrent((int) (5))),__c.DipToCurrent((int) (20)));
 //BA.debugLineNum = 108;BA.debugLine="lbl.Text = Text";
_lbl.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 109;BA.debugLine="lbl.TextSize = DefaultTextSize";
_lbl.setTextSize((float) (_defaulttextsize));
 //BA.debugLineNum = 110;BA.debugLine="lbl.TextColor = DefaultTextColor";
_lbl.setTextColor(_defaulttextcolor);
 //BA.debugLineNum = 111;BA.debugLine="pnl.Color = DefaultTextBackgroundColor";
_pnl.setColor(_defaulttextbackgroundcolor);
 //BA.debugLineNum = 112;BA.debugLine="Dim minHeight As Int";
_minheight = 0;
 //BA.debugLineNum = 113;BA.debugLine="minHeight = su.MeasureMultilineTextHeight(lbl, Te";
_minheight = _su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 114;BA.debugLine="lbl.Height = Max(50dip, minHeight)";
_lbl.setHeight((int) (__c.Max(__c.DipToCurrent((int) (50)),_minheight)));
 //BA.debugLineNum = 115;BA.debugLine="InsertAt(Index, pnl, lbl.Height + 2dip, Value)";
_insertat(_index,_pnl,(int) (_lbl.getHeight()+__c.DipToCurrent((int) (2))),_value);
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public String  _jumptoitem(int _index) throws Exception{
int _top = 0;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
int _i = 0;
 //BA.debugLineNum = 172;BA.debugLine="Public Sub JumpToItem(Index As Int)";
 //BA.debugLineNum = 173;BA.debugLine="Dim top As Int";
_top = 0;
 //BA.debugLineNum = 174;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 175;BA.debugLine="For i = 0 To Min(Index - 1, items.Size - 1)";
{
final int step3 = 1;
final int limit3 = (int) (__c.Min(_index-1,_items.getSize()-1));
for (_i = (int) (0) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 176;BA.debugLine="p = panels.get(i)";
_p.setObject((android.view.ViewGroup)(_panels.Get(_i)));
 //BA.debugLineNum = 177;BA.debugLine="top = top + p.Height + dividerHeight";
_top = (int) (_top+_p.getHeight()+_dividerheight);
 }
};
 //BA.debugLineNum = 179;BA.debugLine="sv.ScrollPosition = top";
_sv.setScrollPosition(_top);
 //BA.debugLineNum = 181;BA.debugLine="DoEvents";
__c.DoEvents();
 //BA.debugLineNum = 182;BA.debugLine="sv.ScrollPosition = top";
_sv.setScrollPosition(_top);
 //BA.debugLineNum = 183;BA.debugLine="DoEvents";
__c.DoEvents();
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public String  _panel_click() throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 201;BA.debugLine="Private Sub Panel_Click";
 //BA.debugLineNum = 202;BA.debugLine="If SubExists(CallBack, EventName & \"_ItemClick\")";
if (__c.SubExists(ba,_callback,_eventname+"_ItemClick")) { 
 //BA.debugLineNum = 203;BA.debugLine="Dim v As View";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 204;BA.debugLine="v = Sender";
_v.setObject((android.view.View)(__c.Sender(ba)));
 //BA.debugLineNum = 205;BA.debugLine="CallSub3(CallBack, EventName & \"_ItemClick\", v.T";
__c.CallSubNew3(ba,_callback,_eventname+"_ItemClick",_v.getTag(),_items.Get((int)(BA.ObjectToNumber(_v.getTag()))));
 };
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public String  _removeat(int _index) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _removepanel = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
int _i = 0;
 //BA.debugLineNum = 81;BA.debugLine="Public Sub RemoveAt(Index As Int)";
 //BA.debugLineNum = 82;BA.debugLine="Dim removePanel, p As Panel";
_removepanel = new anywheresoftware.b4a.objects.PanelWrapper();
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 83;BA.debugLine="removePanel = panels.get(Index)";
_removepanel.setObject((android.view.ViewGroup)(_panels.Get(_index)));
 //BA.debugLineNum = 84;BA.debugLine="For i = Index + 1 To items.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (_items.getSize()-1);
for (_i = (int) (_index+1) ; (step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3); _i = ((int)(0 + _i + step3)) ) {
 //BA.debugLineNum = 85;BA.debugLine="p = panels.get(i)";
_p.setObject((android.view.ViewGroup)(_panels.Get(_i)));
 //BA.debugLineNum = 86;BA.debugLine="p.Tag = i - 1";
_p.setTag((Object)(_i-1));
 //BA.debugLineNum = 87;BA.debugLine="p.Top = p.Top - removePanel.Height - dividerHeig";
_p.setTop((int) (_p.getTop()-_removepanel.getHeight()-_dividerheight));
 }
};
 //BA.debugLineNum = 89;BA.debugLine="sv.Panel.Height = sv.Panel.Height - removePanel.H";
_sv.getPanel().setHeight((int) (_sv.getPanel().getHeight()-_removepanel.getHeight()-_dividerheight));
 //BA.debugLineNum = 90;BA.debugLine="items.RemoveAt(Index)";
_items.RemoveAt(_index);
 //BA.debugLineNum = 91;BA.debugLine="panels.RemoveAt(Index)";
_panels.RemoveAt(_index);
 //BA.debugLineNum = 92;BA.debugLine="removePanel.RemoveView";
_removepanel.RemoveView();
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public String  _sv_scrollchanged(int _position) throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Private Sub sv_ScrollChanged(Position As Int)";
 //BA.debugLineNum = 187;BA.debugLine="If SubExists(CallBack, EventName & \"_Position\") T";
if (__c.SubExists(ba,_callback,_eventname+"_Position")) { 
 //BA.debugLineNum = 189;BA.debugLine="CallSub2(CallBack, EventName & \"_Position\",Posit";
__c.CallSubNew2(ba,_callback,_eventname+"_Position",(Object)(_position));
 };
 //BA.debugLineNum = 191;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
