package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_scrol_layout2{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _height="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((47d * scale) - (0d)));
views.get("panel_history").vw.setLeft((int)(0d));
views.get("panel_history").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_history").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("panel_history").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getHeight()))));
_height = BA.NumberToString((views.get("panel1").vw.getHeight())-(19d * scale));
views.get("btn_sabad").vw.setWidth((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_sabad").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_share").vw.setWidth((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_share").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_search").vw.setWidth((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_search").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_back").vw.setHeight((int)(Double.parseDouble(_height)-(1d * scale)));
views.get("btn_back").vw.setWidth((int)(Double.parseDouble(_height)-(1d * scale)));
views.get("btn_back").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_back").vw.getHeight())/2d));
views.get("btn_back").vw.setLeft((int)((100d / 100 * width)-(views.get("btn_back").vw.getWidth())-(5d * scale)));
views.get("btn_sabad").vw.setLeft((int)((1d / 100 * width)));
views.get("btn_share").vw.setLeft((int)((views.get("btn_sabad").vw.getLeft())+(views.get("btn_sabad").vw.getWidth())+(12d * scale)));
views.get("btn_search").vw.setLeft((int)((views.get("btn_share").vw.getLeft())+(views.get("btn_share").vw.getWidth())+(12d * scale)));
views.get("btn_sabad").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_sabad").vw.getHeight())/2d));
views.get("btn_share").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_share").vw.getHeight())/2d));
views.get("btn_search").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_search").vw.getHeight())/2d));
views.get("label3").vw.setHeight((int)((views.get("panel1").vw.getHeight())-(15d * scale)));
views.get("label3").vw.setLeft((int)((42d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("btn_back").vw.getLeft())-(4d * scale) - ((42d / 100 * width))));
views.get("label3").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("label3").vw.getHeight())/2d));
views.get("footer").vw.setLeft((int)((50d / 100 * width)));
views.get("footer").vw.setWidth((int)((100d / 100 * width) - ((50d / 100 * width))));
views.get("lbl_offline").vw.setLeft((int)((0d / 100 * width)));
views.get("lbl_offline").vw.setWidth((int)((50d / 100 * width) - ((0d / 100 * width))));
views.get("footer").vw.setTop((int)((100d / 100 * height)-(views.get("footer").vw.getHeight())));
views.get("lbl_offline").vw.setTop((int)((100d / 100 * height)-(views.get("footer").vw.getHeight())));
views.get("scrol_main").vw.setLeft((int)(0d));
views.get("scrol_main").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("scrol_main").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("scrol_main").vw.setHeight((int)((views.get("footer").vw.getTop()) - ((views.get("panel1").vw.getHeight()))));

}
}