package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_rezerv{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("label1").vw.setLeft((int)((views.get("spin_city").vw.getLeft() + views.get("spin_city").vw.getWidth())));
views.get("label1").vw.setWidth((int)((100d / 100 * width)-(views.get("spin_city").vw.getLeft()) - ((views.get("spin_city").vw.getLeft() + views.get("spin_city").vw.getWidth()))));
views.get("label2").vw.setLeft((int)((views.get("label1").vw.getLeft())));
views.get("label2").vw.setWidth((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth()) - ((views.get("label1").vw.getLeft()))));
views.get("label3").vw.setLeft((int)((views.get("label1").vw.getLeft())));
views.get("label3").vw.setWidth((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth()) - ((views.get("label1").vw.getLeft()))));
views.get("label4").vw.setLeft((int)((views.get("label1").vw.getLeft())));
views.get("label4").vw.setWidth((int)((views.get("label1").vw.getLeft() + views.get("label1").vw.getWidth()) - ((views.get("label1").vw.getLeft()))));

}
}