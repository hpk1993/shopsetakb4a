package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_update_app{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("progressbar1").vw.setLeft((int)((50d / 100 * width) - (views.get("progressbar1").vw.getWidth() / 2)));
views.get("progressbar1").vw.setTop((int)((50d / 100 * height) - (views.get("progressbar1").vw.getHeight() / 2)));
views.get("lbl_progress").vw.setTop((int)((views.get("progressbar1").vw.getTop() + views.get("progressbar1").vw.getHeight())+(2d / 100 * height)));

}
}