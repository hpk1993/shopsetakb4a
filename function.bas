Type=StaticCode
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	#region style_msgbox
	Public set_color_title As Int=0xFF1C1B1C
	Public set_color_msg As Int=0xFF020202
	Public set_color_positive As Int=0xFF033055
	Public set_color_negetive As Int=0xFF033055
	Public set_color_cancel As Int=0xFF033055
	Public set_size_text As Int=15
	Public set_font_text As Typeface=Starter.font_body
	Public set_Gravity_text As String=Gravity.RIGHT
	#End Region

End Sub

Sub initialize_spotdialog(progress_spot As SpotsDialog)
	progress_spot.Initialize2("در حال بارگذاری اطلاعات لطفا شکیبا باشید..",progress_spot.Theme_Custom,Colors.White,12,Starter.font_body,6,0xFF0BE721,False)
End Sub


Sub hide_scrollbar_horizontal(v As HorizontalScrollView)
	Dim r As Reflector
	r.Target = v
	r.RunMethod2("setHorizontalScrollBarEnabled", False, "java.lang.boolean")
	r.RunMethod2("setOverScrollMode", 2, "java.lang.int" )
End Sub

'<code>
'	Dim  Pd As ParsMSGBOX
'	Pd=function.style_msgbox("title","msg","yes","no","cancel")
'	Dim Action As Int = Pd.Create
'</code>
Sub style_msgbox(title As String,msg As String,positive As String,negetive As String,cancel As String) As  ParsMSGBOX
	Dim  Pd As ParsMSGBOX
	Pd.Initialize("pd")
	Pd.setTitle(title,set_Gravity_text,set_color_title,set_size_text,set_font_text)
	Pd.setMessage(msg,set_Gravity_text,set_color_msg,set_size_text,set_font_text)
	Pd.setNegative(negetive,set_color_negetive,set_size_text,set_font_text)
	Pd.setNeutral(cancel,set_color_cancel,set_size_text,set_font_text)
	Pd.setPositive(positive,set_color_positive,set_size_text,set_font_text)
	Return Pd
End Sub

'<code>
'	Dim  Pd As ParsMSGBOX
'	Pd=function.style_msgbox2("","")
'	Pd.Create
'</code>
Sub style_msgbox2(title As String,msg As String) As  ParsMSGBOX
	Dim cancel As String="بسیار خب"
	Dim  Pd As ParsMSGBOX
	Pd.Initialize("pd")
	Pd.setTitle(title,set_Gravity_text,set_color_title,set_size_text,set_font_text)
	Pd.setMessage(msg,set_Gravity_text,set_color_msg,set_size_text,set_font_text)
'	Pd.setNegative(negetive,set_color_negetive,set_size_text,set_font_text)
	Pd.setNeutral(cancel,set_color_cancel,set_size_text,set_font_text)
'	Pd.setPositive(positive,set_color_positive,set_size_text,set_font_text)
	Return Pd
End Sub

Sub is_admin() As Boolean
	If File.Exists(myLibrary.rute,Starter.filename_user)=True Then
		Dim lst As List=File.ReadList(myLibrary.rute,Starter.filename_user)
		Dim user_type As Int= lst.Get(4)
		If user_type=1 Then
			Return True
		Else
			Return False
		End If
	End If
End Sub