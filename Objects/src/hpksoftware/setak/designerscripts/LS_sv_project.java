package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_sv_project{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("img_project").vw.setLeft((int)(0d));
views.get("img_project").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("img_project").vw.setTop((int)(0d));
views.get("img_project").vw.setHeight((int)((50d / 100 * height) - (0d)));
views.get("label1").vw.setHeight((int)((12.5d / 100 * height)));
views.get("label2").vw.setHeight((int)((12.5d / 100 * height)));
views.get("label3").vw.setHeight((int)((12.5d / 100 * height)));
views.get("label4").vw.setHeight((int)((12.5d / 100 * height)));
views.get("label1").vw.setLeft((int)(0d));
views.get("label1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("label2").vw.setLeft((int)(0d));
views.get("label2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("label3").vw.setLeft((int)(0d));
views.get("label3").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("label4").vw.setLeft((int)(0d));
views.get("label4").vw.setWidth((int)((50d / 100 * width) - (0d)));
views.get("btn_help").vw.setLeft((int)((55d / 100 * width)));
views.get("btn_help").vw.setWidth((int)((106d / 100 * width) - ((55d / 100 * width))));
views.get("label1").vw.setTop((int)((views.get("img_project").vw.getTop() + views.get("img_project").vw.getHeight())));
views.get("label2").vw.setTop((int)((views.get("label1").vw.getTop() + views.get("label1").vw.getHeight())));
views.get("label3").vw.setTop((int)((views.get("label2").vw.getTop() + views.get("label2").vw.getHeight())));
views.get("label4").vw.setTop((int)((views.get("label3").vw.getTop() + views.get("label3").vw.getHeight())));
views.get("btn_help").vw.setTop((int)((views.get("label4").vw.getTop())+(.5d / 100 * height)));
views.get("btn_help").vw.setHeight((int)((views.get("label4").vw.getTop() + views.get("label4").vw.getHeight())-(.5d / 100 * height) - ((views.get("label4").vw.getTop())+(.5d / 100 * height))));

}
}