package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_show_propertis{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("sv_pro_panel").vw.setLeft((int)(0d));
views.get("sv_pro_panel").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("sv_pro_value").vw.setLeft((int)((1d / 100 * width)));
views.get("sv_pro_value").vw.setWidth((int)((55d / 100 * width) - ((1d / 100 * width))));
views.get("sv_pro_key").vw.setLeft((int)((55d / 100 * width)));
views.get("sv_pro_key").vw.setWidth((int)((99d / 100 * width) - ((55d / 100 * width))));

}
}