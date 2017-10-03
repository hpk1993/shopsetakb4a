package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_mnu{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("ico_mnu").vw.setLeft((int)((100d / 100 * width)-(views.get("ico_mnu").vw.getWidth())-(2d * scale)));
views.get("lbl_txt_mnu").vw.setLeft((int)(1d));
views.get("lbl_txt_mnu").vw.setWidth((int)((views.get("ico_mnu").vw.getLeft())-(8d * scale) - (1d)));
views.get("ico_mnu").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("ico_mnu").vw.getHeight())/2d));
views.get("lbl_txt_mnu").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("lbl_txt_mnu").vw.getHeight())/2d));
views.get("line").vw.setLeft((int)(0d));
views.get("line").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("line").vw.setTop((int)((views.get("panel1").vw.getHeight())-(views.get("line").vw.getHeight())));

}
}