package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_category{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _height="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("panel1").vw.setLeft((int)(0d));
views.get("panel1").vw.setWidth((int)((100d / 100 * width) - (0d)));
views.get("panel1").vw.setTop((int)(0d));
views.get("panel1").vw.setHeight((int)((47d * scale) - (0d)));
//BA.debugLineNum = 15;BA.debugLine="height=Panel1.Height - 19dip"[category/General script]
_height = BA.NumberToString((views.get("panel1").vw.getHeight())-(19d * scale));
//BA.debugLineNum = 16;BA.debugLine="btn_sabad.Width=height-5dip:btn_sabad.Height=height -5dip"[category/General script]
views.get("btn_sabad").vw.setWidth((int)(Double.parseDouble(_height)-(5d * scale)));
//BA.debugLineNum = 16;BA.debugLine="btn_sabad.Width=height-5dip:btn_sabad.Height=height -5dip"[category/General script]
views.get("btn_sabad").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
//BA.debugLineNum = 17;BA.debugLine="btn_share.Width=height-5dip:btn_share.Height=height-5dip"[category/General script]
views.get("btn_share").vw.setWidth((int)(Double.parseDouble(_height)-(5d * scale)));
//BA.debugLineNum = 17;BA.debugLine="btn_share.Width=height-5dip:btn_share.Height=height-5dip"[category/General script]
views.get("btn_share").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
//BA.debugLineNum = 18;BA.debugLine="btn_search.Width=height-5dip:btn_search.Height=height-5dip"[category/General script]
views.get("btn_search").vw.setWidth((int)(Double.parseDouble(_height)-(5d * scale)));
//BA.debugLineNum = 18;BA.debugLine="btn_search.Width=height-5dip:btn_search.Height=height-5dip"[category/General script]
views.get("btn_search").vw.setHeight((int)(Double.parseDouble(_height)-(5d * scale)));
//BA.debugLineNum = 22;BA.debugLine="btn_back.Height=height-1dip"[category/General script]
views.get("btn_back").vw.setHeight((int)(Double.parseDouble(_height)-(1d * scale)));
//BA.debugLineNum = 23;BA.debugLine="btn_back.Width=height-1dip"[category/General script]
views.get("btn_back").vw.setWidth((int)(Double.parseDouble(_height)-(1d * scale)));
//BA.debugLineNum = 26;BA.debugLine="btn_back.Top=(Panel1.Height/2)-btn_back.Height/2"[category/General script]
views.get("btn_back").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_back").vw.getHeight())/2d));
//BA.debugLineNum = 28;BA.debugLine="btn_back.Left=100%x - btn_back.Width - 5dip"[category/General script]
views.get("btn_back").vw.setLeft((int)((100d / 100 * width)-(views.get("btn_back").vw.getWidth())-(5d * scale)));
//BA.debugLineNum = 30;BA.debugLine="btn_sabad.Left=1%x"[category/General script]
views.get("btn_sabad").vw.setLeft((int)((1d / 100 * width)));
//BA.debugLineNum = 31;BA.debugLine="btn_share.Left=btn_sabad.Left + btn_sabad.Width + 12dip"[category/General script]
views.get("btn_share").vw.setLeft((int)((views.get("btn_sabad").vw.getLeft())+(views.get("btn_sabad").vw.getWidth())+(12d * scale)));
//BA.debugLineNum = 32;BA.debugLine="btn_search.Left=btn_share.Left + btn_share.Width + 12dip"[category/General script]
views.get("btn_search").vw.setLeft((int)((views.get("btn_share").vw.getLeft())+(views.get("btn_share").vw.getWidth())+(12d * scale)));
//BA.debugLineNum = 35;BA.debugLine="btn_sabad.Top=(Panel1.Height/2)-btn_sabad.Height/2"[category/General script]
views.get("btn_sabad").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_sabad").vw.getHeight())/2d));
//BA.debugLineNum = 36;BA.debugLine="btn_share.Top=(Panel1.Height/2)-btn_share.Height/2"[category/General script]
views.get("btn_share").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_share").vw.getHeight())/2d));
//BA.debugLineNum = 37;BA.debugLine="btn_search.Top=(Panel1.Height/2)-btn_search.Height/2"[category/General script]
views.get("btn_search").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("btn_search").vw.getHeight())/2d));
//BA.debugLineNum = 39;BA.debugLine="Label3.Height=Panel1.Height-15dip"[category/General script]
views.get("label3").vw.setHeight((int)((views.get("panel1").vw.getHeight())-(15d * scale)));
//BA.debugLineNum = 40;BA.debugLine="Label3.SetLeftAndRight(42%x,btn_back.Left-4dip)"[category/General script]
views.get("label3").vw.setLeft((int)((42d / 100 * width)));
views.get("label3").vw.setWidth((int)((views.get("btn_back").vw.getLeft())-(4d * scale) - ((42d / 100 * width))));
//BA.debugLineNum = 41;BA.debugLine="Label3.Top=(Panel1.Height/2)-Label3.Height/2"[category/General script]
views.get("label3").vw.setTop((int)(((views.get("panel1").vw.getHeight())/2d)-(views.get("label3").vw.getHeight())/2d));
//BA.debugLineNum = 44;BA.debugLine="lbl_badge.Width=btn_sabad.Width'/1.5"[category/General script]
views.get("lbl_badge").vw.setWidth((int)((views.get("btn_sabad").vw.getWidth())));
//BA.debugLineNum = 45;BA.debugLine="lbl_badge.Height=btn_sabad.Height'/1.5"[category/General script]
views.get("lbl_badge").vw.setHeight((int)((views.get("btn_sabad").vw.getHeight())));
//BA.debugLineNum = 46;BA.debugLine="lbl_badge.Left=btn_sabad.Right/2"[category/General script]
views.get("lbl_badge").vw.setLeft((int)((views.get("btn_sabad").vw.getLeft() + views.get("btn_sabad").vw.getWidth())/2d));
//BA.debugLineNum = 48;BA.debugLine="panel_main.SetLeftAndRight(0,100%x)"[category/General script]
views.get("panel_main").vw.setLeft((int)(0d));
views.get("panel_main").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 49;BA.debugLine="panel_main.SetTopAndBottom(Panel1.Height,100%y)"[category/General script]
views.get("panel_main").vw.setTop((int)((views.get("panel1").vw.getHeight())));
views.get("panel_main").vw.setHeight((int)((100d / 100 * height) - ((views.get("panel1").vw.getHeight()))));
//BA.debugLineNum = 54;BA.debugLine="panel_setting.Width=20%x"[category/General script]
views.get("panel_setting").vw.setWidth((int)((20d / 100 * width)));
//BA.debugLineNum = 55;BA.debugLine="btn_show_kala.Width=panel_setting.Width-3dip"[category/General script]
views.get("btn_show_kala").vw.setWidth((int)((views.get("panel_setting").vw.getWidth())-(3d * scale)));
//BA.debugLineNum = 56;BA.debugLine="btn_show_kala.Left=1.5dip"[category/General script]
views.get("btn_show_kala").vw.setLeft((int)((1.5d * scale)));
//BA.debugLineNum = 57;BA.debugLine="btn_show_kala.Height=btn_show_kala.Width"[category/General script]
views.get("btn_show_kala").vw.setHeight((int)((views.get("btn_show_kala").vw.getWidth())));
//BA.debugLineNum = 59;BA.debugLine="btn_del_kala_sabad.Top=btn_show_kala.Top + btn_show_kala.Height + 2dip"[category/General script]
views.get("btn_del_kala_sabad").vw.setTop((int)((views.get("btn_show_kala").vw.getTop())+(views.get("btn_show_kala").vw.getHeight())+(2d * scale)));
//BA.debugLineNum = 60;BA.debugLine="btn_del_kala_sabad.Width=panel_setting.Width-3dip"[category/General script]
views.get("btn_del_kala_sabad").vw.setWidth((int)((views.get("panel_setting").vw.getWidth())-(3d * scale)));
//BA.debugLineNum = 61;BA.debugLine="btn_del_kala_sabad.Left=1.5dip"[category/General script]
views.get("btn_del_kala_sabad").vw.setLeft((int)((1.5d * scale)));
//BA.debugLineNum = 62;BA.debugLine="btn_del_kala_sabad.Height=btn_del_kala_sabad.Width"[category/General script]
views.get("btn_del_kala_sabad").vw.setHeight((int)((views.get("btn_del_kala_sabad").vw.getWidth())));
//BA.debugLineNum = 64;BA.debugLine="panel_setting.Height=btn_del_kala_sabad.Top + btn_del_kala_sabad.Height"[category/General script]
views.get("panel_setting").vw.setHeight((int)((views.get("btn_del_kala_sabad").vw.getTop())+(views.get("btn_del_kala_sabad").vw.getHeight())));
//BA.debugLineNum = 65;BA.debugLine="panel_setting.Top=(100%y/2)-(btn_show_kala.Top + btn_del_kala_sabad.Height/2)"[category/General script]
views.get("panel_setting").vw.setTop((int)(((100d / 100 * height)/2d)-((views.get("btn_show_kala").vw.getTop())+(views.get("btn_del_kala_sabad").vw.getHeight())/2d)));
//BA.debugLineNum = 66;BA.debugLine="panel_setting.Left=100%x"[category/General script]
views.get("panel_setting").vw.setLeft((int)((100d / 100 * width)));
//BA.debugLineNum = 69;BA.debugLine="titlebar_price_kol.SetLeftAndRight(0,100%x)"[category/General script]
views.get("titlebar_price_kol").vw.setLeft((int)(0d));
views.get("titlebar_price_kol").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 70;BA.debugLine="titlebar_price_kol.Top=Panel1.Height"[category/General script]
views.get("titlebar_price_kol").vw.setTop((int)((views.get("panel1").vw.getHeight())));

}
}