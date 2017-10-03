package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_history_order{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("sv_panel").vw.setLeft((int)(0d));
views.get("sv_panel").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lbl_code").vw.setLeft((int)((1d / 100 * width)));
views.get("lbl_code").vw.setWidth((int)((50d / 100 * width) - ((1d / 100 * width))));
views.get("lbl_stat").vw.setLeft((int)((50.5d / 100 * width)));
views.get("lbl_stat").vw.setWidth((int)((views.get("sv_panel").vw.getWidth())-(1d / 100 * width) - ((50.5d / 100 * width))));
views.get("lbl_date").vw.setLeft((int)((1d / 100 * width)));
views.get("lbl_date").vw.setWidth((int)((55d / 100 * width) - ((1d / 100 * width))));
views.get("lbl_price").vw.setLeft((int)((55.5d / 100 * width)));
views.get("lbl_price").vw.setWidth((int)((views.get("sv_panel").vw.getWidth())-(1d / 100 * width) - ((55.5d / 100 * width))));

}
}