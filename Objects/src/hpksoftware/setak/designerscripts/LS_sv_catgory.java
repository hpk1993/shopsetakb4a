package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_catgory{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("img_cat").vw.setWidth((int)((views.get("panel1").vw.getHeight())*.95d));
views.get("img_cat").vw.setHeight((int)((views.get("panel1").vw.getHeight())));
views.get("img_cat").vw.setLeft((int)((99d / 100 * width)-(views.get("img_cat").vw.getWidth())));
views.get("lbl_cat").vw.setLeft((int)((2d / 100 * width)));
views.get("lbl_cat").vw.setWidth((int)((views.get("img_cat").vw.getLeft())-(10d * scale) - ((2d / 100 * width))));
views.get("lbl_cat").vw.setTop((int)((2d / 100 * height)));
views.get("lbl_cat").vw.setHeight((int)((views.get("panel1").vw.getHeight())-(2d / 100 * height) - ((2d / 100 * height))));

}
}