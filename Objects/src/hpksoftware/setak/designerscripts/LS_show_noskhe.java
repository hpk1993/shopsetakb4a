package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_show_noskhe{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel3").vw.setTop((int)((99d / 100 * height) - (views.get("panel3").vw.getHeight())));
views.get("txt_info").vw.setTop((int)((views.get("lbl_clock").vw.getTop() + views.get("lbl_clock").vw.getHeight())+(2d / 100 * height)));
views.get("txt_info").vw.setHeight((int)((views.get("panel3").vw.getTop())-(2d / 100 * height) - ((views.get("lbl_clock").vw.getTop() + views.get("lbl_clock").vw.getHeight())+(2d / 100 * height))));
views.get("spiner_date").vw.setLeft((int)(0d));
views.get("spiner_date").vw.setWidth((int)((58d / 100 * width) - (0d)));
views.get("label5").vw.setLeft((int)((views.get("spiner_date").vw.getLeft() + views.get("spiner_date").vw.getWidth())));
views.get("label5").vw.setWidth((int)((views.get("panel_date").vw.getWidth())-(2d / 100 * width) - ((views.get("spiner_date").vw.getLeft() + views.get("spiner_date").vw.getWidth()))));
views.get("label2").vw.setLeft((int)((views.get("acswitch2").vw.getLeft() + views.get("acswitch2").vw.getWidth())));
views.get("label2").vw.setWidth((int)((views.get("label4").vw.getLeft()) - ((views.get("acswitch2").vw.getLeft() + views.get("acswitch2").vw.getWidth()))));

}
}