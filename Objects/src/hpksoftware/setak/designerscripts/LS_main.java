package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_main{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _height="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((47d * scale) - (0d)));
if ((anywheresoftware.b4a.keywords.LayoutBuilder.getScreenSize()>6.5d)) { 
;
views.get("panel1").vw.setHeight((int)((64d * scale)));
;}else{ 
;
if ((BA.ObjectToBoolean( String.valueOf(anywheresoftware.b4a.keywords.LayoutBuilder.isPortrait())))) { 
;
views.get("panel1").vw.setHeight((int)((56d * scale)));
;}else{ 
;
views.get("panel1").vw.setHeight((int)((48d * scale)));
;};
;};
_height = BA.NumberToString((views.get("panel1").vw.getHeight())-(10d * scale));
views.get("btn_sabad").vw.setWidth((int)(Double.parseDouble(_height)-(0d * scale)));
views.get("btn_sabad").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_share").vw.setWidth((int)(Double.parseDouble(_height)-(0d * scale)));
views.get("btn_share").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_search").vw.setWidth((int)(Double.parseDouble(_height)-(0d * scale)));
views.get("btn_search").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_menu").vw.setHeight((int)(Double.parseDouble(_height)));
views.get("btn_menu").vw.setWidth((int)(Double.parseDouble(_height)));
views.get("btn_menu").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_menu").vw.getHeight())/2d));
views.get("btn_menu").vw.setLeft((int)((100d / 100 * width)-(views.get("btn_menu").vw.getWidth())-(5d * scale)));
views.get("btn_sabad").vw.setLeft((int)((1d / 100 * width)));
views.get("btn_share").vw.setLeft((int)((views.get("btn_sabad").vw.getLeft())+(views.get("btn_sabad").vw.getWidth())+(2d * scale)));
views.get("btn_search").vw.setLeft((int)((views.get("btn_share").vw.getLeft())+(views.get("btn_share").vw.getWidth())+(2d * scale)));
views.get("btn_sabad").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_sabad").vw.getHeight())/2d));
views.get("btn_share").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_share").vw.getHeight())/2d));
views.get("btn_search").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_search").vw.getHeight())/2d));
views.get("logo").vw.setHeight((int)((views.get("panel1").vw.getHeight())-(15d * scale)));
views.get("logo").vw.setLeft((int)((55d / 100 * width)));
views.get("logo").vw.setWidth((int)((views.get("btn_menu").vw.getLeft())-(4d * scale) - ((55d / 100 * width))));
views.get("logo").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("logo").vw.getHeight())/2d));
views.get("lbl_badge").vw.setWidth((int)((views.get("btn_sabad").vw.getWidth())/1.9d));
views.get("lbl_badge").vw.setHeight((int)((views.get("lbl_badge").vw.getWidth())));
views.get("lbl_badge").vw.setLeft((int)((views.get("btn_sabad").vw.getWidth())/2d));
views.get("scrollview1").vw.setLeft((int)(0d));
views.get("scrollview1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("scrollview1").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("scrollview1").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getHeight()))));
views.get("panel_base").vw.setLeft((int)(0d));
views.get("panel_base").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_base").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("panel_base").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getHeight()))));
views.get("splash").vw.setLeft((int)(0d));
views.get("splash").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("image_splash").vw.setLeft((int)(0d));
views.get("image_splash").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("splash").vw.setTop((int)(0d));
views.get("splash").vw.setHeight((int)((100d / 100 * height) - (0d)));
views.get("image_splash").vw.setTop((int)(0d));
views.get("image_splash").vw.setHeight((int)((100d / 100 * height) - (0d)));
//BA.debugLineNum = 65;BA.debugLine="ProgressBar1.VerticalCenter=(splash.Height/2)+((splash.Height/2)/2)"[main/General script]
views.get("progressbar1").vw.setTop((int)(((views.get("splash").vw.getHeight())/2d)+(((views.get("splash").vw.getHeight())/2d)/2d) - (views.get("progressbar1").vw.getHeight() / 2)));
//BA.debugLineNum = 66;BA.debugLine="ProgressBar1.HorizontalCenter=splash.Width/2"[main/General script]
views.get("progressbar1").vw.setLeft((int)((views.get("splash").vw.getWidth())/2d - (views.get("progressbar1").vw.getWidth() / 2)));

}
}