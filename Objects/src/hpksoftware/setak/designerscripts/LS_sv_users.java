package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_users{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("sv_user_panel").vw.setLeft((int)(0d));
views.get("sv_user_panel").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("lbl_name").vw.setLeft((int)((3d / 100 * width)));
views.get("lbl_name").vw.setWidth((int)((views.get("sv_user_panel").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("lbl_address").vw.setLeft((int)((3d / 100 * width)));
views.get("lbl_address").vw.setWidth((int)((views.get("sv_user_panel").vw.getWidth())-(3d / 100 * width) - ((3d / 100 * width))));
views.get("lbl_job").vw.setLeft((int)((3d / 100 * width)));
views.get("lbl_job").vw.setWidth((int)(((views.get("sv_user_panel").vw.getWidth())/2d)-(3d / 100 * width) - ((3d / 100 * width))));
views.get("lbl_tell").vw.setLeft((int)((views.get("lbl_job").vw.getLeft() + views.get("lbl_job").vw.getWidth())+(1d * scale)));
views.get("lbl_tell").vw.setWidth((int)((views.get("sv_user_panel").vw.getWidth())-(3d / 100 * width) - ((views.get("lbl_job").vw.getLeft() + views.get("lbl_job").vw.getWidth())+(1d * scale))));

}
}