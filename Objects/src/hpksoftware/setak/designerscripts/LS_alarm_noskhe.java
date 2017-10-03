package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_alarm_noskhe{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[alarm_noskhe/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="lbl_date.SetLeftAndRight(lbl_date.Left,(Panel4.Width/2)-2DIP)"[alarm_noskhe/General script]
views.get("lbl_date").vw.setLeft((int)((views.get("lbl_date").vw.getLeft())));
views.get("lbl_date").vw.setWidth((int)(((views.get("panel4").vw.getWidth())/2d)-(2d * scale) - ((views.get("lbl_date").vw.getLeft()))));
//BA.debugLineNum = 5;BA.debugLine="btn_adddate.SetLeftAndRight(lbl_date.Right+2dip,(Panel4.Width)-4DIP)"[alarm_noskhe/General script]
views.get("btn_adddate").vw.setLeft((int)((views.get("lbl_date").vw.getLeft() + views.get("lbl_date").vw.getWidth())+(2d * scale)));
views.get("btn_adddate").vw.setWidth((int)(((views.get("panel4").vw.getWidth()))-(4d * scale) - ((views.get("lbl_date").vw.getLeft() + views.get("lbl_date").vw.getWidth())+(2d * scale))));
//BA.debugLineNum = 8;BA.debugLine="Label2.SetLeftAndRight(ACSwitch1.Right+2dip,Label4.Left-4dip)"[alarm_noskhe/General script]
views.get("label2").vw.setLeft((int)((views.get("acswitch1").vw.getLeft() + views.get("acswitch1").vw.getWidth())+(2d * scale)));
views.get("label2").vw.setWidth((int)((views.get("label4").vw.getLeft())-(4d * scale) - ((views.get("acswitch1").vw.getLeft() + views.get("acswitch1").vw.getWidth())+(2d * scale))));

}
}