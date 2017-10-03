package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_list_catgory{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)((1d / 100 * width)));
views.get("panel1").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("img_kala").vw.setTop((int)((2d * scale)));
views.get("img_kala").vw.setHeight((int)((views.get("panel1").vw.getHeight())-(4d * scale)));
views.get("img_kala").vw.setLeft((int)((62d / 100 * width)));
views.get("img_kala").vw.setWidth((int)((views.get("panel1").vw.getWidth())-(2d * scale) - ((62d / 100 * width))));
views.get("lbl_name_kala").vw.setLeft((int)((1d / 100 * width)));
views.get("lbl_name_kala").vw.setWidth((int)((views.get("img_kala").vw.getLeft()) - ((1d / 100 * width))));
views.get("lbl_info").vw.setTop((int)((views.get("lbl_name_kala").vw.getTop() + views.get("lbl_name_kala").vw.getHeight())));
views.get("lbl_info").vw.setLeft((int)((1d / 100 * width)));
views.get("lbl_info").vw.setWidth((int)((views.get("img_kala").vw.getLeft()) - ((1d / 100 * width))));
views.get("lbl_price_off").vw.setTop((int)((views.get("lbl_name_kala").vw.getTop() + views.get("lbl_name_kala").vw.getHeight())));
views.get("lbl_price_off").vw.setLeft((int)((1d / 100 * width)));
views.get("lbl_price_off").vw.setWidth((int)((views.get("img_kala").vw.getLeft()) - ((1d / 100 * width))));
views.get("lbl_price").vw.setTop((int)((views.get("lbl_price_off").vw.getTop() + views.get("lbl_price_off").vw.getHeight())));
views.get("lbl_price").vw.setLeft((int)((3d / 100 * width)));
views.get("lbl_price").vw.setWidth((int)((views.get("img_kala").vw.getLeft()) - ((3d / 100 * width))));
views.get("img_mark_viesue").vw.setLeft((int)(((views.get("img_kala").vw.getLeft())+(views.get("img_kala").vw.getWidth()))-(views.get("img_mark_viesue").vw.getWidth())));
views.get("img_mark_viesue").vw.setTop((int)((views.get("img_kala").vw.getTop())));
views.get("img_exist").vw.setHeight((int)((25d * scale)));
views.get("img_exist").vw.setWidth((int)((65d * scale)));
views.get("img_exist").vw.setLeft((int)((views.get("img_kala").vw.getLeft() + views.get("img_kala").vw.getWidth())-(views.get("img_exist").vw.getWidth())));
views.get("img_exist").vw.setTop((int)((views.get("img_kala").vw.getTop() + views.get("img_kala").vw.getHeight()) - (views.get("img_exist").vw.getHeight())));

}
}