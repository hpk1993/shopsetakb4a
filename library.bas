Type=StaticCode
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	Private utils_str As StringUtils
	Dim ht As Float

End Sub

Sub art(txt As String,label As Label,font As Typeface,size As Int,line_space As Int)As Float
	Dim before,after As Float
	Dim obj1 As Reflector
	label.Text=txt
	label.TextSize=size
	label.Gravity=Gravity.CENTER
	label.Left=label.Left
	label.Typeface=font
	
	ht=utils_str.MeasureMultilineTextHeight(label,txt)
	obj1.Target=label
	before=obj1.RunMethod("getLineHeight")
	obj1.Target=label
	obj1.RunMethod3("setLineSpacing", .10,"java.lang.float",line_space,"java.lang.float")
	obj1.Target=label
	after=obj1.RunMethod("getLineHeight")
	ht=((after * ht)/before)
	'label.Height=ht
	
	Return ht
End Sub

Public Sub get_height_label(lbl As Label,str As String) As Float
	Dim lbl1 As Label
	lbl1.Initialize("")
	lbl1=lbl
	Return utils_str.MeasureMultilineTextHeight(lbl1,str)
End Sub