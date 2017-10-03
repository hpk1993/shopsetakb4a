package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_list_lat_order{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("sv_panel_list").vw.setLeft((int)(0d));
views.get("sv_panel_list").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lbl_id").vw.setLeft((int)(0d));
views.get("lbl_id").vw.setWidth((int)((views.get("sv_panel_list").vw.getWidth())-(2d / 100 * width) - (0d)));
views.get("lbl_price_order").vw.setLeft((int)(0d));
views.get("lbl_price_order").vw.setWidth((int)((views.get("sv_panel_list").vw.getWidth())-(2d / 100 * width) - (0d)));
views.get("lbl_tels").vw.setLeft((int)(0d));
views.get("lbl_tels").vw.setWidth((int)((views.get("sv_panel_list").vw.getWidth())-(2d / 100 * width) - (0d)));
views.get("lbl_status").vw.setLeft((int)(0d));
views.get("lbl_status").vw.setWidth((int)((views.get("sv_panel_list").vw.getWidth())-(2d / 100 * width) - (0d)));
views.get("lbl_date_last").vw.setLeft((int)(0d));
views.get("lbl_date_last").vw.setWidth((int)((views.get("sv_panel_list").vw.getWidth())-(2d / 100 * width) - (0d)));

}
}