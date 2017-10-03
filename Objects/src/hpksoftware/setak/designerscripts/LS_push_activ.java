package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_push_activ{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("imageview1").vw.setLeft((int)((1d / 100 * width)));
views.get("imageview1").vw.setWidth((int)((99d / 100 * width) - ((1d / 100 * width))));
views.get("imageview1").vw.setHeight((int)((views.get("imageview1").vw.getWidth())));
views.get("imageview1").vw.setTop((int)(((100d / 100 * height)/2d)-((views.get("imageview1").vw.getHeight())/2d)));

}
}