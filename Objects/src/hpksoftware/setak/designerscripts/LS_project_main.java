package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_project_main{

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
views.get("btn_home").vw.setWidth((int)(Double.parseDouble(_height)-(0d * scale)));
views.get("btn_home").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
views.get("btn_home").vw.setLeft((int)((1d / 100 * width)));
views.get("btn_home").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d) - (views.get("btn_home").vw.getHeight() / 2)));
views.get("caption_header").vw.setHeight((int)((views.get("panel1").vw.getHeight())-(15d * scale)));
views.get("caption_header").vw.setLeft((int)((40d / 100 * width)));
views.get("caption_header").vw.setWidth((int)((views.get("panel1").vw.getWidth())-(1.5d / 100 * width) - ((40d / 100 * width))));
views.get("caption_header").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("caption_header").vw.getHeight())/2d));
views.get("panel_base").vw.setLeft((int)(0d));
views.get("panel_base").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_base").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("panel_base").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getHeight()))));

}
}