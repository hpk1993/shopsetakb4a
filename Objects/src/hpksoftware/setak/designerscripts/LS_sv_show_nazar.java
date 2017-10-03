package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_show_nazar{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("sv_panel").vw.setLeft((int)(0d));
views.get("sv_panel").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("sv_panel_text").vw.setLeft((int)((3d / 100 * width)));
views.get("sv_panel_text").vw.setWidth((int)((views.get("sv_panel").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("sv_panel_text").vw.setTop((int)(0d));
views.get("sv_panel_text").vw.setHeight((int)((views.get("sv_panel").vw.getHeight()) - (0d)));
views.get("sv_lbl_msg").vw.setLeft((int)((1d / 100 * width)));
views.get("sv_lbl_msg").vw.setWidth((int)((views.get("sv_panel_text").vw.getWidth())-(1d / 100 * width) - ((1d / 100 * width))));
views.get("sv_lbl_msg").vw.setTop((int)((1d / 100 * height)));
views.get("sv_lbl_msg").vw.setHeight((int)((views.get("sv_panel_text").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));

}
}