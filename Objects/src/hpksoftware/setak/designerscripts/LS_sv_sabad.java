package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_sabad{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lbl_price_kol").vw.setTop((int)((views.get("panel1").vw.getHeight())-(views.get("lbl_price_kol").vw.getHeight())));
views.get("img_kala").vw.setTop((int)(0d));
views.get("img_kala").vw.setHeight((int)((views.get("lbl_price_kol").vw.getTop()) - (0d)));
views.get("img_kala").vw.setWidth((int)((views.get("img_kala").vw.getHeight())*.9d));
views.get("img_kala").vw.setLeft((int)((views.get("panel1").vw.getWidth())-(views.get("img_kala").vw.getWidth())));
views.get("txt_tedad").vw.setLeft((int)((2d / 100 * width)));
views.get("txt_tedad").vw.setWidth((int)((15d / 100 * width) - ((2d / 100 * width))));
views.get("lbl_name").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl_name").vw.setWidth((int)((views.get("img_kala").vw.getLeft())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("label2").vw.setLeft((int)(((views.get("img_kala").vw.getLeft())-(views.get("label2").vw.getWidth()))-((2d / 100 * width))));
views.get("spin_tedad").vw.setLeft((int)((views.get("label2").vw.getLeft())-(views.get("spin_tedad").vw.getWidth())));
views.get("lbl_fi").vw.setLeft((int)((views.get("img_kala").vw.getLeft())-(views.get("lbl_fi").vw.getWidth())-(2d * scale)));
views.get("lbl_fi").vw.setTop((int)((views.get("label2").vw.getTop())));
views.get("label1").vw.setLeft((int)(((views.get("img_kala").vw.getLeft())-(views.get("label1").vw.getWidth()))-((2d / 100 * width))));
views.get("label1").vw.setHeight((int)((views.get("txt_tedad").vw.getHeight())));
views.get("lbl_price").vw.setLeft((int)((1d / 100 * width)));
views.get("lbl_price").vw.setWidth((int)((views.get("label1").vw.getLeft())-(2d * scale) - ((1d / 100 * width))));
views.get("lbl_price_kol").vw.setLeft((int)(0d));
views.get("lbl_price_kol").vw.setWidth((int)((views.get("panel1").vw.getWidth()) - (0d)));

}
}