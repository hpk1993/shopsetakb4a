package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_disconnect{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("btn_check_net").vw.setLeft((int)((20d / 100 * width)));
views.get("btn_check_net").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
views.get("imageview1").vw.setLeft((int)((20d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((80d / 100 * width) - ((20d / 100 * width))));
views.get("imageview1").vw.setTop((int)((20d / 100 * height)));
views.get("imageview1").vw.setHeight((int)((80d / 100 * height) - ((20d / 100 * height))));
views.get("imageview1").vw.setHeight((int)((views.get("imageview1").vw.getWidth())));
views.get("imageview1").vw.setTop((int)((12d / 100 * height)));
views.get("btn_check_net").vw.setTop((int)((60d / 100 * height)));

}
}