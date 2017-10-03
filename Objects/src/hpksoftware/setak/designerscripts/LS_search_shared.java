package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_search_shared{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("btn_search").vw.setHeight((int)((views.get("panel1").vw.getHeight())));
views.get("btn_search").vw.setWidth((int)((views.get("btn_search").vw.getHeight())));
views.get("btn_list").vw.setLeft((int)(0d));
views.get("btn_search").vw.setLeft((int)((views.get("btn_list").vw.getLeft() + views.get("btn_list").vw.getWidth())+(1d * scale)));
views.get("txt_search").vw.setLeft((int)((views.get("btn_search").vw.getLeft() + views.get("btn_search").vw.getWidth())));
views.get("txt_search").vw.setWidth((int)((views.get("panel1").vw.getWidth()) - ((views.get("btn_search").vw.getLeft() + views.get("btn_search").vw.getWidth()))));
views.get("panel_list").vw.setLeft((int)(0d));
views.get("panel_list").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_list").vw.setTop((int)((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight())));
views.get("panel_list").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getTop() + views.get("panel1").vw.getHeight()))));

}
}