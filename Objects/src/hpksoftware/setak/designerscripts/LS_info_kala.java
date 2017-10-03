package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_info_kala{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("slide").vw.setTop((int)((0d * scale)));
views.get("slide").vw.setLeft((int)(0d));
views.get("slide").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("slide").vw.setHeight((int)((220d * scale)));
views.get("btn_share_kala").vw.setTop((int)((views.get("slide").vw.getTop())+(views.get("slide").vw.getHeight())-(views.get("btn_share_kala").vw.getHeight())));
views.get("img_mark").vw.setWidth((int)((100d * scale)));
views.get("img_mark").vw.setHeight((int)((100d * scale)));
views.get("img_mark").vw.setTop((int)((views.get("slide").vw.getTop())));
views.get("img_mark").vw.setLeft((int)((100d / 100 * width)-(views.get("img_mark").vw.getWidth())));
views.get("img_exist").vw.setHeight((int)((25d * scale)));
views.get("img_exist").vw.setWidth((int)((65d * scale)));
views.get("img_exist").vw.setLeft((int)((views.get("slide").vw.getWidth())-(views.get("img_exist").vw.getWidth())));
views.get("img_exist").vw.setTop((int)((views.get("btn_share_kala").vw.getTop())+((views.get("img_exist").vw.getHeight())/2d)));
views.get("lbl_name_kala").vw.setTop((int)((views.get("slide").vw.getTop())+(views.get("slide").vw.getHeight())+(1d * scale)));
views.get("lbl_name_kala").vw.setLeft((int)(0d));
views.get("lbl_name_kala").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("btn_fani").vw.setTop((int)((views.get("lbl_name_kala").vw.getTop())+(views.get("lbl_name_kala").vw.getHeight())+(1d * scale)));
views.get("btn_fani").vw.setLeft((int)((55d / 100 * width)));
views.get("btn_fani").vw.setWidth((int)((95d / 100 * width) - ((55d / 100 * width))));
views.get("btn_nazar").vw.setTop((int)((views.get("btn_fani").vw.getTop())));
views.get("btn_nazar").vw.setLeft((int)((5d / 100 * width)));
views.get("btn_nazar").vw.setWidth((int)((45d / 100 * width) - ((5d / 100 * width))));
views.get("panel1").vw.setTop((int)((views.get("btn_fani").vw.getTop())+(views.get("btn_fani").vw.getHeight())+(4d * scale)));
views.get("panel1").vw.setLeft((int)((5d / 100 * width)));
views.get("panel1").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("panel2").vw.setTop((int)((views.get("panel1").vw.getTop())));
views.get("panel2").vw.setLeft((int)((5d / 100 * width)));
views.get("panel2").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("panel3").vw.setTop((int)((views.get("panel2").vw.getTop())+(views.get("panel2").vw.getHeight())+(4d * scale)));
views.get("panel3").vw.setLeft((int)((5d / 100 * width)));
views.get("panel3").vw.setWidth((int)((95d / 100 * width) - ((5d / 100 * width))));
views.get("button1").vw.setLeft((int)(0d));
views.get("button1").vw.setWidth((int)((views.get("panel2").vw.getWidth()) - (0d)));
views.get("label2").vw.setLeft((int)((60d / 100 * width)));
views.get("label2").vw.setWidth((int)((views.get("panel1").vw.getWidth())-(1d / 100 * width) - ((60d / 100 * width))));
views.get("label3").vw.setLeft((int)((2d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("label2").vw.getLeft()) - ((2d / 100 * width))));
views.get("label4").vw.setLeft((int)((2d / 100 * width)));
views.get("label4").vw.setWidth((int)((views.get("panel1").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("html_info").vw.setLeft((int)((2d / 100 * width)));
views.get("html_info").vw.setWidth((int)((views.get("panel1").vw.getWidth())-(2d / 100 * width) - ((2d / 100 * width))));
views.get("lbl_count_nazar").vw.setLeft((int)((7d / 100 * width)));
views.get("lbl_count_nazar").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(3d / 100 * width) - ((7d / 100 * width))));
views.get("btn_reg_nazar").vw.setLeft((int)(0d));
views.get("btn_reg_nazar").vw.setWidth((int)((views.get("panel3").vw.getWidth()) - (0d)));
views.get("rate1").vw.setLeft((int)((7d / 100 * width)));
views.get("rate1").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(55d / 100 * width) - ((7d / 100 * width))));
views.get("rate2").vw.setLeft((int)((7d / 100 * width)));
views.get("rate2").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(55d / 100 * width) - ((7d / 100 * width))));
views.get("rate3").vw.setLeft((int)((7d / 100 * width)));
views.get("rate3").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(55d / 100 * width) - ((7d / 100 * width))));
views.get("rate1").vw.setTop((int)((((views.get("label1").vw.getTop())+(views.get("label1").vw.getHeight())/2d))-(((views.get("rate1").vw.getHeight()))/2d)));
views.get("rate2").vw.setTop((int)((((views.get("label5").vw.getTop())+(views.get("label5").vw.getHeight())/2d))-(((views.get("rate2").vw.getHeight()))/2d)));
views.get("rate3").vw.setTop((int)((((views.get("label6").vw.getTop())+(views.get("label6").vw.getHeight())/2d))-(((views.get("rate3").vw.getHeight()))/2d)));
views.get("label1").vw.setLeft((int)((views.get("rate1").vw.getLeft())+(views.get("rate1").vw.getWidth())+(3d * scale)));
views.get("label1").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(3d / 100 * width) - ((views.get("rate1").vw.getLeft())+(views.get("rate1").vw.getWidth())+(3d * scale))));
views.get("label5").vw.setLeft((int)((views.get("rate1").vw.getLeft())+(views.get("rate1").vw.getWidth())+(3d * scale)));
views.get("label5").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(3d / 100 * width) - ((views.get("rate1").vw.getLeft())+(views.get("rate1").vw.getWidth())+(3d * scale))));
//BA.debugLineNum = 54;BA.debugLine="Label6.SetLeftAndRight(rate1.Left + rate1.Width + 3dip,Panel3.Width-3%x)"[info_kala/General script]
views.get("label6").vw.setLeft((int)((views.get("rate1").vw.getLeft())+(views.get("rate1").vw.getWidth())+(3d * scale)));
views.get("label6").vw.setWidth((int)((views.get("panel3").vw.getWidth())-(3d / 100 * width) - ((views.get("rate1").vw.getLeft())+(views.get("rate1").vw.getWidth())+(3d * scale))));
//BA.debugLineNum = 55;BA.debugLine="Panel3.Height=btn_reg_nazar.Top + btn_reg_nazar.Height"[info_kala/General script]
views.get("panel3").vw.setHeight((int)((views.get("btn_reg_nazar").vw.getTop())+(views.get("btn_reg_nazar").vw.getHeight())));
//BA.debugLineNum = 60;BA.debugLine="Progress_slider.VerticalCenter=slide.Height/2"[info_kala/General script]
views.get("progress_slider").vw.setTop((int)((views.get("slide").vw.getHeight())/2d - (views.get("progress_slider").vw.getHeight() / 2)));
//BA.debugLineNum = 61;BA.debugLine="Progress_slider.HorizontalCenter=slide.Width/2"[info_kala/General script]
views.get("progress_slider").vw.setLeft((int)((views.get("slide").vw.getWidth())/2d - (views.get("progress_slider").vw.getWidth() / 2)));

}
}