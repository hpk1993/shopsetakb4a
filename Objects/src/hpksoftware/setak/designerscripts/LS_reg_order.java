package hpksoftware.setak.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_reg_order{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _start="";
String _end_t="";
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
_start = BA.NumberToString((10d / 100 * width));
_end_t = BA.NumberToString((90d / 100 * width));
views.get("p1").vw.setLeft((int)(Double.parseDouble(_start)));
views.get("p1").vw.setWidth((int)(Double.parseDouble(_end_t) - (Double.parseDouble(_start))));
views.get("pp1").vw.setLeft((int)((5d / 100 * width)));
views.get("pp1").vw.setWidth((int)((views.get("p1").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("txt_gift").vw.setLeft((int)(0d));
views.get("txt_gift").vw.setWidth((int)((views.get("pp1").vw.getWidth()) - (0d)));
views.get("p2").vw.setLeft((int)(Double.parseDouble(_start)));
views.get("p2").vw.setWidth((int)(Double.parseDouble(_end_t) - (Double.parseDouble(_start))));
views.get("pp2").vw.setLeft((int)((5d / 100 * width)));
views.get("pp2").vw.setWidth((int)((views.get("p2").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("txt_get").vw.setLeft((int)(0d));
views.get("txt_get").vw.setWidth((int)((views.get("pp2").vw.getWidth()) - (0d)));
views.get("p3").vw.setLeft((int)(Double.parseDouble(_start)));
views.get("p3").vw.setWidth((int)(Double.parseDouble(_end_t) - (Double.parseDouble(_start))));
views.get("pp3").vw.setLeft((int)((5d / 100 * width)));
views.get("pp3").vw.setWidth((int)((views.get("p3").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("txt_tell").vw.setLeft((int)(0d));
views.get("txt_tell").vw.setWidth((int)((views.get("pp3").vw.getWidth()) - (0d)));
views.get("p4").vw.setLeft((int)(Double.parseDouble(_start)));
views.get("p4").vw.setWidth((int)(Double.parseDouble(_end_t) - (Double.parseDouble(_start))));
views.get("pp4").vw.setLeft((int)((5d / 100 * width)));
views.get("pp4").vw.setWidth((int)((views.get("p4").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("txt_phone").vw.setLeft((int)(0d));
views.get("txt_phone").vw.setWidth((int)((views.get("pp4").vw.getWidth()) - (0d)));
views.get("p5").vw.setLeft((int)(Double.parseDouble(_start)));
views.get("p5").vw.setWidth((int)(Double.parseDouble(_end_t) - (Double.parseDouble(_start))));
views.get("pp5").vw.setLeft((int)((5d / 100 * width)));
views.get("pp5").vw.setWidth((int)((views.get("p5").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("txt_address").vw.setLeft((int)(0d));
views.get("txt_address").vw.setWidth((int)((views.get("pp5").vw.getWidth()) - (0d)));
views.get("p6").vw.setLeft((int)(Double.parseDouble(_start)));
views.get("p6").vw.setWidth((int)(Double.parseDouble(_end_t) - (Double.parseDouble(_start))));
views.get("pp6").vw.setLeft((int)((5d / 100 * width)));
views.get("pp6").vw.setWidth((int)((views.get("p6").vw.getWidth())-(5d / 100 * width) - ((5d / 100 * width))));
views.get("txt_post_code").vw.setLeft((int)(0d));
views.get("txt_post_code").vw.setWidth((int)((views.get("pp6").vw.getWidth()) - (0d)));
views.get("p6").vw.setTop((int)((100d / 100 * height)+(1d * scale)));

}
}