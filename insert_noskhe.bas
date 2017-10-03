Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	Dim daye_per As PersianDate

	Private spiner_daroo As ACSpinner
	Private txt_info As Label
	Private lbl_clock As Label
	Private ListView1 As ListView
	Private Label4 As Label
	
	Private Panel1 As Panel
	Private scrol_main As ScrollView
	Private footer As Label
	Private panel_history As Panel
	Private Label3 As Label
	
	Dim line_height As line_height
	
	
	Dim input_dialog As InputDialog
	
	Dim sql1 As SQL
	Dim cr1 As Cursor
	Dim hiden_id_daroo As Int
	Private txt_search As EditText
	
	Dim ime1 As IME
	
	Private spiner_date As ACSpinner
	Private Panel_date As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	input_dialog.Hint="Password"
	input_dialog.HintColor=Colors.Gray
	input_dialog.InputType=input_dialog.INPUT_TYPE_DECIMAL_NUMBERS
	input_dialog.PasswordMode=True
	
	If input_dialog.Show("لطفا گذرواژه خود را وارد کنید","ورود پزشک","ورود","بستن","",Null)=DialogResponse.POSITIVE Then
		If input_dialog.Input=123456 Then
			
			If File.Exists(Starter.rute_file_app,"db.db") = True Then
				If sql1.IsInitialized=False Then sql1.Initialize(Starter.rute_file_app,"db.db",True)
			End If
			
			Activity.LoadLayout("scrol_layout")
			Dim color_header As ColorDrawable
			color_header.Initialize(Starter.color_base,0)
			Panel1.Background=color_header
			Label3.Text="درج نسخه توسط پزشک"
			scrol_main.Panel.LoadLayout("insert_noskhe")
	
			
	
	
			Log(	"height= " & 	line_height.Initialize(txt_info,1.5)			)
	
			footer.Visible=False
			scrol_main.Height=scrol_main.Height+footer.Height
	
		
	
	
			Try
				spiner_daroo.Clear
				cr1 = sql1.ExecQuery($"Select * From daroo"$)
				For i = 0 To cr1.RowCount - 1
					cr1.Position = i
					If i=0 Then
						hiden_id_daroo=cr1.GetString("id")
						lbl_clock.Text=cr1.GetString("clock")
						txt_info.Text=cr1.GetString("info")
						
						txt_info.Height=library.get_height_label(txt_info,txt_info.Text)+6dip
						If txt_info.Height< 35%y Then txt_info.Height=35%y
'						Panel_date.Top=txt_info.Top + txt_info.Height + 5dip
						Label4.Top=txt_info.Top + txt_info.Height + 5dip
						ListView1.Top=Label4.Top + Label4.Height
						scrol_main.Panel.Height=ListView1.Top + ListView1.Height + 5dip
					End If
					spiner_daroo.Add(cr1.GetString("name")	)
				Next
			Catch
				Msgbox(LastException.Message,"")
			End Try
			
			
			
			Try
				Dim is_now_date As Boolean=False
				cr1 = sql1.ExecQuery($"Select * From noskhe group by date_noskhe"$)
				For i = 0 To cr1.RowCount - 1
					cr1.Position = i
					spiner_date.Add(cr1.GetString("date_noskhe")	)
					spiner_date.SelectedIndex=i
					If spiner_date.SelectedItem=daye_per.getDate(0,0,0,"/") Then is_now_date=True
				Next
				spiner_date.SelectedIndex=spiner_date.Size-1
				
				If is_now_date=False Then spiner_date.Add(	daye_per.getDate(0,0,0,"/")	)
			Catch
				Msgbox(LastException.Message,"")
			End Try
			
			
			
			ListView1.SingleLineLayout.ItemHeight=40dip
			ListView1.SingleLineLayout.Label.Height=ListView1.SingleLineLayout.ItemHeight
	
			ListView1.SingleLineLayout.Label.TextColor=Colors.Black
			ListView1.SingleLineLayout.Label.TextSize=14
			ListView1.SingleLineLayout.Label.Gravity=Gravity.CENTER_HORIZONTAL + Gravity.CENTER_VERTICAL
			ListView1.SingleLineLayout.Label.Typeface=Typeface.LoadFromAssets("iransans Bold.ttf")
		
		
			#region
			Try
				ListView1.Clear
				cr1 = sql1.ExecQuery($"Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='${spiner_date.SelectedItem}'"$)
				For i=0 To cr1.RowCount-1
					cr1.Position = i
					ListView1.AddSingleLine2(	cr1.GetString("name")	, cr1.GetString("id_daroo"))
					
				Next
				ListView1.Height=(cr1.RowCount * 40dip)
				scrol_main.Panel.Height=ListView1.Top + ListView1.Height + 5dip
			
			Catch
				Log(LastException.Message)
			End Try
		#End Region
		
		
			
		
			ime1.Initialize("ime1")
'			ime1.AddHeightChangedEvent
			ime1.HideKeyboard
		
		
		
		
		
		
		
		Else
			ToastMessageShow("گذرواژه پزشک اشتباه است",True)
			Activity.Finish
		End If
	Else
		Activity.Finish
	End If
	
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ime1_HeightChanged (NewHeight As Int, OldHeight As Int)
		scrol_main.Panel.Height=NewHeight
End Sub


Sub spiner_daroo_ItemClick (Position As Int, Value As Object)
	Try
		cr1 = sql1.ExecQuery($"Select * From daroo where name='${Value}'"$)
	Catch
		Msgbox(LastException.Message,"")
	End Try
	
	cr1.Position = 0
	lbl_clock.Text=cr1.GetString("clock")
	hiden_id_daroo=cr1.GetString("id")
	txt_info.Text=cr1.GetString("info")
	
	txt_info.Height=library.get_height_label(txt_info,txt_info.Text)+6dip
	If txt_info.Height< 35%y Then txt_info.Height=35%y
'	Panel_date.Top=txt_info.Top + txt_info.Height + 5dip
	Label4.Top=txt_info.Top + txt_info.Height + 5dip
	ListView1.Top=Label4.Top + Label4.Height
	scrol_main.Panel.Height=ListView1.Top + ListView1.Height + 5dip
End Sub

Sub lbl_edit_Click
'	Dim time_p As DateTimePicker
'	time_p.Initialize("time_p")
'	time_p.TimePicker("tag")
End Sub

'Sub time_p_ontimeset(HourOfDay As Int, minute As Int)
'	Log(HourOfDay & "   |||||||  " & minute)
'End Sub
Sub btn_add_Click
	Dim  Pd As ParsMSGBOX
	Pd=function.style_msgbox("پیام سیستم","آیا میخواهید به نسخه بیمار اضافه کنید؟","بلی","خیر","")
	Dim i As Int = Pd.Create
	If i=DialogResponse.POSITIVE Then
		
	
		Try
			sql1.ExecNonQuery($"INSERT INTO noskhe(id_daroo,clock,date_noskhe) VALUES(${hiden_id_daroo},'${lbl_clock.Text}','${spiner_date.SelectedItem}')"$)

			Try
				ListView1.Clear
				cr1 = sql1.ExecQuery($"Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='${spiner_date.SelectedItem}'"$)
				For i=0 To cr1.RowCount-1
					cr1.Position = i
					ListView1.AddSingleLine2(	cr1.GetString("name")	, cr1.GetString("id_daroo"))
					
				Next
				ListView1.Height=(cr1.RowCount * 40dip)
				scrol_main.Panel.Height=ListView1.Top + ListView1.Height + 5dip
			
			Catch
				Log(LastException.Message)
				Dim Pd As ParsMSGBOX
				Pd=function.style_msgbox("پیام سیستم","این دارو از قبل در نسخه موجود است","","بسیار خب","")
				Pd.Create
			End Try
	
		
		Catch
'			Log("exist")
'			Dim Pd As ParsMSGBOX
'			Pd=function.style_msgbox("پیام سیستم","این دارو از قبل در نسخه موجود است","","خیر","")
'			Pd.Create
		End Try
	
	End If
End Sub

Sub btn_back_Click
	Activity.Finish
End Sub

Sub txt_search_TextChanged (Old As String, New As String)
	
	Try
		cr1 = sql1.ExecQuery($"Select * From daroo where name like '%${New}%'"$)
		spiner_daroo.Clear
		For i = 0 To cr1.RowCount - 1
			cr1.Position = i
			If i=0 Then
				hiden_id_daroo=cr1.GetString("id")
				lbl_clock.Text=cr1.GetString("clock")
			End If
			spiner_daroo.Add(cr1.GetString("name")	)
		Next
	Catch
		Msgbox(LastException.Message,"")
	End Try
End Sub

Sub ListView1_ItemLongClick (Position As Int, Value As Object)
	Dim  Pd As ParsMSGBOX
	Pd=function.style_msgbox("پیام سیستم","آیا میخواهید این دارو را از نسخه بیمار حذف کنید؟","بلی","خیر","")
	Dim i As Int = Pd.Create
	If i=DialogResponse.POSITIVE Then
		Try
			sql1.ExecNonQuery2($"Delete From noskhe Where id_daroo=? AND date_noskhe='${spiner_date.SelectedItem}'"$,Array As Object(Value)	)
			ListView1.Clear
			cr1 = sql1.ExecQuery($"Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='${spiner_date.SelectedItem}'"$)
			For i=0 To cr1.RowCount-1
				cr1.Position = i
				ListView1.AddSingleLine2(	cr1.GetString("name")	, cr1.GetString("id_daroo"))
			Next
			ListView1.Height=(cr1.RowCount * 40dip)
			scrol_main.Panel.Height=ListView1.Top + ListView1.Height + 5dip
		Catch
			Log(LastException)
		End Try
	End If
End Sub

Sub spiner_date_ItemClick (Position As Int, Value As Object)
	Try
		ListView1.Clear
		cr1 = sql1.ExecQuery($"Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='${spiner_date.SelectedItem}'"$)
		For i=0 To cr1.RowCount-1
			cr1.Position = i
			ListView1.AddSingleLine2(	cr1.GetString("name")	, cr1.GetString("id_daroo"))
		Next
		ListView1.Height=(cr1.RowCount * 40dip)
		scrol_main.Panel.Height=ListView1.Top + ListView1.Height + 5dip
	Catch
		Log(LastException)
	End Try
End Sub