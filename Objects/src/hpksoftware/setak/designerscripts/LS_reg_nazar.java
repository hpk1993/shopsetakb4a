package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_reg_nazar{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("btn_close").vw.setLeft((int)((100d / 100 * width)-(views.get("btn_close").vw.getWidth())-(5d * scale)));
views.get("btn_close").vw.setHeight((int)((views.get("panel1").vw.getHeight())/1.2d));
views.get("btn_close").vw.setWidth((int)((views.get("btn_close").vw.getHeight())));
views.get("btn_close").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-((views.get("btn_close").vw.getHeight())/2d)));
views.get("reg").vw.setLeft((int)((1d / 100 * width)));
views.get("reg").vw.setHeight((int)((views.get("panel1").vw.getHeight())));
views.get("reg").vw.setWidth((int)((views.get("reg").vw.getHeight())));
views.get("reg").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-((views.get("reg").vw.getHeight())/2d)));
views.get("panel2").vw.setLeft((int)(0d));
views.get("panel2").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel2").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("panel2").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getHeight()))));
views.get("label1").vw.setLeft((int)((5d / 100 * width)));
views.get("label1").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("label2").vw.setLeft((int)((5d / 100 * width)));
views.get("label2").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("label3").vw.setLeft((int)((5d / 100 * width)));
views.get("label3").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("rate_panel1").vw.setLeft((int)((5d / 100 * width)));
views.get("rate_panel1").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("rate_panel2").vw.setLeft((int)((5d / 100 * width)));
views.get("rate_panel2").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("rate_panel3").vw.setLeft((int)((5d / 100 * width)));
views.get("rate_panel3").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("panel_txt").vw.setLeft((int)((5d / 100 * width)));
views.get("panel_txt").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("txt_nazar").vw.setLeft((int)((2d / 100 * width)));
views.get("txt_nazar").vw.setWidth((int)((views.get("panel_txt").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("txt_nazar").vw.setTop((int)((views.get("txt_nazar").vw.getTop())));
views.get("txt_nazar").vw.setHeight((int)((views.get("panel_txt").vw.getHeight())-(views.get("txt_nazar").vw.getTop()) - ((views.get("txt_nazar").vw.getTop()))));
views.get("rate1_panel").vw.setLeft((int)((15d / 100 * width)));
views.get("rate1_panel").vw.setWidth((int)((views.get("rate_panel1").vw.getWidth())-(15d / 100 * width) - ((15d / 100 * width))));
views.get("rate2_panel").vw.setLeft((int)((15d / 100 * width)));
views.get("rate2_panel").vw.setWidth((int)((views.get("rate_panel2").vw.getWidth())-(15d / 100 * width) - ((15d / 100 * width))));
views.get("rate3_panel").vw.setLeft((int)((15d / 100 * width)));
views.get("rate3_panel").vw.setWidth((int)((views.get("rate_panel3").vw.getWidth())-(15d / 100 * width) - ((15d / 100 * width))));

}
}