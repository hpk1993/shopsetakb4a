package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_reg_login{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _height="";
String _w_txt="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((40d * scale) - (0d)));
views.get("btn_back").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_back").vw.getHeight())/2d));
views.get("btn_back").vw.setLeft((int)((100d / 100 * width)-(views.get("btn_back").vw.getWidth())-(5d * scale)));
_height = BA.NumberToString((views.get("panel1").vw.getHeight())-(15d * scale));
views.get("btn_back").vw.setWidth((int)(Double.parseDouble(_height)+(10d * scale)));
views.get("btn_back").vw.setHeight((int)(Double.parseDouble(_height)+(10d * scale)));
views.get("btn_back").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_back").vw.getHeight())/2d));
views.get("btn_share").vw.setLeft((int)((1d / 100 * width)));
views.get("btn_share").vw.setWidth((int)(Double.parseDouble(_height)));
views.get("btn_share").vw.setHeight((int)(Double.parseDouble(_height)));
views.get("btn_share").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_share").vw.getHeight())/2d));
views.get("label3").vw.setWidth((int)(Double.parseDouble(_height)));
views.get("label3").vw.setHeight((int)(Double.parseDouble(_height)+(5d * scale)));
views.get("label3").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("label3").vw.getHeight())/2d));
views.get("label3").vw.setLeft((int)((45d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("btn_back").vw.getLeft())-(5d * scale) - ((45d / 100 * width))));
views.get("label3").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("label3").vw.getHeight())/2d));
views.get("img_login").vw.setTop((int)((views.get("panel1").vw.getHeight())+(2d * scale)));
views.get("panel_login").vw.setTop((int)((views.get("img_login").vw.getTop())+(views.get("img_reg").vw.getHeight())/2d));
views.get("panel_login").vw.setLeft((int)((10d / 100 * width)));
views.get("panel_login").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("img_login").vw.setLeft((int)((50d / 100 * width)-((views.get("img_login").vw.getWidth())/2d)));
_w_txt = BA.NumberToString((12d / 100 * width));
views.get("txt_user").vw.setLeft((int)(Double.parseDouble(_w_txt)));
views.get("txt_user").vw.setWidth((int)((views.get("panel_login").vw.getWidth())-Double.parseDouble(_w_txt) - (Double.parseDouble(_w_txt))));
views.get("txt_pass").vw.setLeft((int)(Double.parseDouble(_w_txt)));
views.get("txt_pass").vw.setWidth((int)((views.get("panel_login").vw.getWidth())-Double.parseDouble(_w_txt) - (Double.parseDouble(_w_txt))));
views.get("btn_login").vw.setLeft((int)(Double.parseDouble(_w_txt)+(5d / 100 * width)));
views.get("btn_login").vw.setWidth((int)(((views.get("panel_login").vw.getWidth())-Double.parseDouble(_w_txt))-(5d / 100 * width) - (Double.parseDouble(_w_txt)+(5d / 100 * width))));
views.get("line1").vw.setTop((int)((views.get("txt_user").vw.getTop())+(views.get("txt_user").vw.getHeight())));
views.get("line1").vw.setHeight((int)((2d * scale)));
views.get("line1").vw.setLeft((int)(Double.parseDouble(_w_txt)));
views.get("line1").vw.setWidth((int)((views.get("panel_login").vw.getWidth())-Double.parseDouble(_w_txt) - (Double.parseDouble(_w_txt))));
views.get("line2").vw.setTop((int)((views.get("txt_pass").vw.getTop())+(views.get("txt_pass").vw.getHeight())));
views.get("line2").vw.setHeight((int)((2d * scale)));
views.get("line2").vw.setLeft((int)(Double.parseDouble(_w_txt)));
views.get("line2").vw.setWidth((int)((views.get("panel_login").vw.getWidth())-Double.parseDouble(_w_txt) - (Double.parseDouble(_w_txt))));
views.get("img_reg").vw.setTop((int)((views.get("panel1").vw.getHeight())+(2d * scale)));
views.get("panel_reg").vw.setLeft((int)((2d / 100 * width)));
views.get("panel_reg").vw.setWidth((int)((98d / 100 * width) - ((2d / 100 * width))));
views.get("panel_reg").vw.setTop((int)((views.get("panel1").vw.getHeight())+(5d * scale)));
views.get("panel_reg").vw.setHeight((int)((99d / 100 * height) - ((views.get("panel1").vw.getHeight())+(5d * scale))));
views.get("scrol_reg").vw.setTop((int)((1d / 100 * height)));
views.get("scrol_reg").vw.setHeight((int)((views.get("panel_reg").vw.getHeight())-(1d / 100 * height) - ((1d / 100 * height))));
views.get("scrol_reg").vw.setLeft((int)((5d / 100 * width)));
views.get("scrol_reg").vw.setWidth((int)((views.get("panel_reg").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("btn_down").vw.setLeft((int)((10d / 100 * width)));
views.get("btn_down").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("btn_down").vw.setTop((int)((views.get("panel_login").vw.getTop())+(views.get("panel_login").vw.getHeight())+(10d * scale)));
views.get("img_reg").vw.setLeft((int)((50d / 100 * width)-(views.get("img_reg").vw.getWidth())/2d));
views.get("btn_forget").vw.setLeft((int)((10d / 100 * width)));
views.get("btn_forget").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("btn_forget").vw.setTop((int)((views.get("btn_down").vw.getTop())+(views.get("btn_down").vw.getHeight())+(10d * scale)));
views.get("panel_forget").vw.setTop((int)((views.get("btn_forget").vw.getTop())));
views.get("panel_forget").vw.setLeft((int)((10d / 100 * width)));
views.get("panel_forget").vw.setWidth((int)((90d / 100 * width) - ((10d / 100 * width))));
views.get("btn_send_forgt").vw.setLeft((int)((views.get("panel_forget").vw.getWidth())-(views.get("btn_send_forgt").vw.getWidth())));
views.get("txt_forget").vw.setLeft((int)(0d));
views.get("txt_forget").vw.setWidth((int)((views.get("btn_send_forgt").vw.getLeft())-(1d * scale) - (0d)));

}
}