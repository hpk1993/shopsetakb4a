package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_brand{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("img_brand").vw.setHeight((int)((views.get("panel2").vw.getHeight())));
views.get("img_brand").vw.setWidth((int)((views.get("img_brand").vw.getHeight())*1.7d));
views.get("img_brand").vw.setLeft((int)((views.get("panel2").vw.getWidth())-(views.get("img_brand").vw.getWidth())));
views.get("lbl_brand").vw.setLeft((int)((3d / 100 * width)));
views.get("lbl_brand").vw.setWidth((int)((views.get("img_brand").vw.getLeft())-(2d / 100 * width) - ((3d / 100 * width))));
views.get("lbl_brand").vw.setTop((int)((3d / 100 * height)));
views.get("lbl_brand").vw.setHeight((int)((views.get("panel2").vw.getHeight())-(3d / 100 * height) - ((3d / 100 * height))));

}
}