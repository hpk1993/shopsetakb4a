package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_page_main{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _height_panel="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel_slider").vw.setLeft((int)(0d));
views.get("panel_slider").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("btn_list_category1").vw.setTop((int)((views.get("btn_p3").vw.getTop() + views.get("btn_p3").vw.getHeight())+(10d * scale)));
views.get("btn_list_category1").vw.setLeft((int)((5d / 100 * width)));
views.get("btn_list_category1").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("img_vip").vw.setTop((int)(((views.get("panel_title_visue").vw.getHeight())/2d)-((views.get("img_vip").vw.getHeight())/2d)));
views.get("panel_timer").vw.setLeft((int)((0d / 100 * width)));
views.get("panel_timer").vw.setWidth((int)((35d / 100 * width) - ((0d / 100 * width))));
views.get("img_vip").vw.setLeft((int)((50d / 100 * width)));
views.get("img_vip").vw.setWidth((int)((95d / 100 * width) - ((50d / 100 * width))));
views.get("lbl_title_news").vw.setLeft((int)((45d / 100 * width)));
views.get("lbl_title_news").vw.setWidth((int)((95d / 100 * width) - ((45d / 100 * width))));
_height_panel = BA.NumberToString((40d / 100 * height));
views.get("panel_obj0").vw.setTop((int)((views.get("panel_baner_1").vw.getTop() + views.get("panel_baner_1").vw.getHeight())+(10d * scale)));
views.get("panel_obj0").vw.setLeft((int)(0d));
views.get("panel_obj0").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_obj0").vw.setHeight((int)(Double.parseDouble(_height_panel)));
views.get("panel_title_visue").vw.setLeft((int)(0d));
views.get("panel_title_visue").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("viesue_panel").vw.setLeft((int)(0d));
views.get("viesue_panel").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("viesue_panel").vw.setTop((int)((views.get("panel_title_visue").vw.getTop() + views.get("panel_title_visue").vw.getHeight())+(5d * scale)));
views.get("viesue_panel").vw.setHeight((int)((views.get("panel_obj0").vw.getHeight()) - ((views.get("panel_title_visue").vw.getTop() + views.get("panel_title_visue").vw.getHeight())+(5d * scale))));
views.get("panel_cat").vw.setTop((int)((views.get("btn_p3").vw.getTop() + views.get("btn_p3").vw.getHeight())+(10d * scale)));
views.get("panel_cat").vw.setLeft((int)(0d));
views.get("panel_cat").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("img_cat1").vw.setLeft((int)((2d / 100 * width)));
views.get("img_cat1").vw.setWidth((int)(((views.get("panel_cat").vw.getWidth())/2d)-(2d / 100 * width) - ((2d / 100 * width))));
views.get("img_cat2").vw.setLeft((int)((views.get("img_cat1").vw.getLeft() + views.get("img_cat1").vw.getWidth())+(2d / 100 * width)));
views.get("img_cat2").vw.setWidth((int)(((views.get("panel_cat").vw.getWidth()))-(2d / 100 * width) - ((views.get("img_cat1").vw.getLeft() + views.get("img_cat1").vw.getWidth())+(2d / 100 * width))));
views.get("img_cat3").vw.setLeft((int)((2d / 100 * width)));
views.get("img_cat3").vw.setWidth((int)(((views.get("panel_cat").vw.getWidth())/2d)-(2d / 100 * width) - ((2d / 100 * width))));
views.get("img_cat4").vw.setLeft((int)((views.get("img_cat3").vw.getLeft() + views.get("img_cat3").vw.getWidth())+(2d / 100 * width)));
views.get("img_cat4").vw.setWidth((int)(((views.get("panel_cat").vw.getWidth()))-(2d / 100 * width) - ((views.get("img_cat3").vw.getLeft() + views.get("img_cat3").vw.getWidth())+(2d / 100 * width))));
views.get("img_cat5").vw.setLeft((int)((2d / 100 * width)));
views.get("img_cat5").vw.setWidth((int)(((views.get("panel_cat").vw.getWidth())/2d)-(2d / 100 * width) - ((2d / 100 * width))));
views.get("img_cat6").vw.setLeft((int)((views.get("img_cat5").vw.getLeft() + views.get("img_cat5").vw.getWidth())+(2d / 100 * width)));
views.get("img_cat6").vw.setWidth((int)(((views.get("panel_cat").vw.getWidth()))-(2d / 100 * width) - ((views.get("img_cat5").vw.getLeft() + views.get("img_cat5").vw.getWidth())+(2d / 100 * width))));
views.get("img_cat1").vw.setHeight((int)((views.get("img_cat1").vw.getWidth())-((10d * scale))));
views.get("img_cat2").vw.setHeight((int)((views.get("img_cat1").vw.getHeight())));
views.get("img_cat3").vw.setHeight((int)((views.get("img_cat1").vw.getHeight())));
views.get("img_cat4").vw.setHeight((int)((views.get("img_cat1").vw.getHeight())));
views.get("img_cat5").vw.setHeight((int)((views.get("img_cat1").vw.getHeight())));
views.get("img_cat6").vw.setHeight((int)((views.get("img_cat1").vw.getHeight())));
views.get("img_cat3").vw.setTop((int)((views.get("img_cat1").vw.getTop() + views.get("img_cat1").vw.getHeight())+(2d / 100 * width)));
views.get("img_cat4").vw.setTop((int)((views.get("img_cat3").vw.getTop())));
views.get("img_cat5").vw.setTop((int)((views.get("img_cat3").vw.getTop() + views.get("img_cat3").vw.getHeight())+(2d / 100 * width)));
views.get("img_cat6").vw.setTop((int)((views.get("img_cat5").vw.getTop())));
views.get("panel_cat").vw.setHeight((int)((views.get("img_cat6").vw.getTop() + views.get("img_cat6").vw.getHeight())+(2d / 100 * height)));
views.get("panel_baner_1").vw.setTop((int)((views.get("panel_cat").vw.getTop() + views.get("panel_cat").vw.getHeight())+(10d * scale)));
views.get("panel_baner_1").vw.setLeft((int)(0d));
views.get("panel_baner_1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_obj1").vw.setLeft((int)(0d));
views.get("panel_obj1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel_obj1").vw.setTop((int)((views.get("panel_baner_1").vw.getTop() + views.get("panel_baner_1").vw.getHeight())+(10d * scale)));
views.get("panel_title_porfroosh").vw.setLeft((int)(0d));
views.get("panel_title_porfroosh").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("porfroush_panel").vw.setHeight((int)(Double.parseDouble(_height_panel)-(2d / 100 * height)));
views.get("btn_list_porfroush").vw.setLeft((int)((0d / 100 * width)));
views.get("btn_list_porfroush").vw.setWidth((int)((40d / 100 * width) - ((0d / 100 * width))));
views.get("img_1").vw.setLeft((int)(0d));
views.get("img_1").vw.setWidth((int)((40d / 100 * width) - (0d)));
views.get("label_title1").vw.setLeft((int)((50d / 100 * width)));
views.get("label_title1").vw.setWidth((int)((95d / 100 * width) - ((50d / 100 * width))));
views.get("porfroush_panel").vw.setTop((int)((views.get("panel_title_porfroosh").vw.getTop())+(views.get("panel_title_porfroosh").vw.getHeight())+(5d * scale)));
//BA.debugLineNum = 62;BA.debugLine="porfroush_panel.SetLeftAndRight(0,100%x)"[page_main/General script]
views.get("porfroush_panel").vw.setLeft((int)(0d));
views.get("porfroush_panel").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 63;BA.debugLine="panel_obj1.Height=porfroush_panel.Bottom+3dip"[page_main/General script]
views.get("panel_obj1").vw.setHeight((int)((views.get("porfroush_panel").vw.getTop() + views.get("porfroush_panel").vw.getHeight())+(3d * scale)));
//BA.debugLineNum = 65;BA.debugLine="panel_brand.Top=panel_obj1.Bottom+10dip"[page_main/General script]
views.get("panel_brand").vw.setTop((int)((views.get("panel_obj1").vw.getTop() + views.get("panel_obj1").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 66;BA.debugLine="panel_brand.SetLeftAndRight(0,100%x)"[page_main/General script]
views.get("panel_brand").vw.setLeft((int)(0d));
views.get("panel_brand").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 69;BA.debugLine="panel_baner_2.Top=panel_obj1.Bottom+10dip'panel_brand.Bottom+10dip"[page_main/General script]
views.get("panel_baner_2").vw.setTop((int)((views.get("panel_obj1").vw.getTop() + views.get("panel_obj1").vw.getHeight())+(10d * scale)));
//BA.debugLineNum = 70;BA.debugLine="panel_baner_2.SetLeftAndRight(0,100%x)"[page_main/General script]
views.get("panel_baner_2").vw.setLeft((int)(0d));
views.get("panel_baner_2").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 77;BA.debugLine="progress_porfroosh.HorizontalCenter=porfroush_panel.Width/2"[page_main/General script]
views.get("progress_porfroosh").vw.setLeft((int)((views.get("porfroush_panel").vw.getWidth())/2d - (views.get("progress_porfroosh").vw.getWidth() / 2)));
//BA.debugLineNum = 78;BA.debugLine="progress_porfroosh.VerticalCenter=porfroush_panel.VerticalCenter"[page_main/General script]
views.get("progress_porfroosh").vw.setTop((int)((views.get("porfroush_panel").vw.getTop() + views.get("porfroush_panel").vw.getHeight()/2) - (views.get("progress_porfroosh").vw.getHeight() / 2)));

}
}