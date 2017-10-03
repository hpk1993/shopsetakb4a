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
	Private Panel1 As Panel
	Dim daye_per As PersianDate
	Private footer As Label
	Private panel_history As Panel
	Private Label3 As Label
	
	Private spiner_daroo As ACSpinner
	Private txt_info As Label
	Private lbl_clock As Label
	
	Dim sql1 As SQL
	Dim cr1 As Cursor
	Dim hiden_id_daroo As Int
	Private txt_search As EditText
	Dim dp As DatePicker_persian_hpk
	
	Private lbl_date As Label
	Private ACSwitch1 As ACSwitch
	Private TabHost1 As TabHost
	
	Dim list_clock As List
	Private Panel3 As Panel

	Private spiner_date As ACSpinner
	Private panel_reged_date As Panel
	Private lbl_date_reg As Label
	Dim reged_date_next_visit_bool As Boolean=False
	Private ACSwitch2 As ACSwitch
End Sub

Sub Activity_Create(FirstTime As Boolean)
	

	Activity.LoadLayout("tab_noskhe")
	TabHost1.AddTab("ویزیت بعدی", "alarm_noskhe.bal")
	TabHost1.AddTab("نسخه پزشک","show_noskhe.bal")
	
	If File.Exists(File.DirDefaultExternal,"enabled_alarm_daroo") = True Then
		ACSwitch2.Checked=True
	Else
		ACSwitch2.Checked=False
	End If
	
'	scrol_main.Panel.LoadLayout("")
	
	Panel3.Top=txt_info.Top + txt_info.Height + 5dip
'	scrol_main.Panel.Height=Panel3.Top + Panel3.Height +10dip
	
'	scrol_main.Panel.Height=Panel3.Top+Panel3.Height + 5dip
	TabHost1.CurrentTab =1 '(TabHost1.CurrentTab + 1) Mod TabHost1.TabCount 'switch to the next tab.
	
	
	If File.Exists(Starter.rute_file_app,"db.db") = True Then
		If sql1.IsInitialized=False Then sql1.Initialize(Starter.rute_file_app,"db.db",True)
	End If
	
	Try
'		Dim is_now_date As Boolean=False
		cr1 = sql1.ExecQuery($"Select * From noskhe group by date_noskhe"$)
		For i = 0 To cr1.RowCount - 1
			cr1.Position = i
			spiner_date.Add(cr1.GetString("date_noskhe")	)
			spiner_date.SelectedIndex=i
			'If spiner_date.SelectedItem=daye_per.getDate(0,0,0,"/") Then is_now_date=True
		Next
'		If is_now_date=False Then spiner_date.Add(	daye_per.getDate(0,0,0,"/")	)
	Catch
		Msgbox(LastException.Message,"")
	End Try
	
	
	
	
	get_of_date
	
	
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btn_adddate_Click
	dp.Initialize("dp")
	dp.SetMaxYear(1400)
	dp.SetMinYear(1372)
	dp.SetPositiveButtonString("انتخاب")
	dp.SetNegativeButton("بستن")
	dp.SetTodayButtonVisible(True)
	dp.SetTypeFace(Typeface.LoadFromAssets("iransans Bold.ttf")	)
	dp.SetActionTextColor(Colors.Red)
	dp.setListener
	dp.show()
End Sub


Sub dp_dateClick(Text As String)
	lbl_date.Text=Text
	lbl_date.tag=Text
End Sub

Sub ACSwitch1_CheckedChange(Checked As Boolean)
	
	Dim enabled_switch_next_visit As Int
	If Checked=True Then
		File.WriteString(File.DirDefaultExternal,"enabled_alarm_visit",True)
		enabled_switch_next_visit=1
	Else
		If File.Exists(File.DirDefaultExternal,"enabled_alarm_visit") = True Then
			File.Delete(File.DirDefaultExternal,"enabled_alarm_visit")
		End If
		
		enabled_switch_next_visit=0
	End If
	sql1.ExecNonQuery($"update alarm set enabled=${enabled_switch_next_visit} where type='next_visit'"$)
	Log("Checked: " & Checked)
End Sub

Sub btn_back_Click
	Activity.Finish
End Sub


Sub txt_search_TextChanged (Old As String, New As String)
	
	Try
		cr1 = sql1.ExecQuery($"Select * From daroo where name like '%${New}%'"$)
		spiner_daroo.Clear
		If cr1.RowCount>0 Then
			For i = 0 To cr1.RowCount - 1
				cr1.Position = i
				If i=0 Then
					hiden_id_daroo=cr1.GetString("id")
					lbl_clock.Text= clock_split( cr1.GetString("clock") )
				End If
				spiner_daroo.Add(cr1.GetString("name")	)
			Next
			
		End If
		
	Catch
'		Msgbox(LastException.Message,"")
	End Try
End Sub

Sub clock_split(clock1 As String) As String
	Dim array1() As String=Regex.Split("،" , clock1)
	Dim str As String
	For i=0 To array1.Length-1
		If i=array1.Length-1 Then 
			str=str & array1(i)
		Else
			str=str & array1(i) & " | "
		End If
		
	Next
	Return str
End Sub

Sub spiner_daroo_ItemClick (Position As Int, Value As Object)
	Try
		cr1 = sql1.ExecQuery($"Select * From daroo where name='${Value}'"$)
	Catch
		Msgbox(LastException.Message,"")
	End Try
	
	cr1.Position = 0
	spiner_daroo.SelectedIndex=0
	lbl_clock.Text= clock_split( cr1.GetString("clock") )
	hiden_id_daroo=cr1.GetString("id")
	txt_info.Text=cr1.GetString("info")
	
	txt_info.Height=library.get_height_label(txt_info,txt_info.Text)+6dip
	If txt_info.Height< 35%y Then txt_info.Height=35%y
	Panel3.Top=txt_info.Top + txt_info.Height + 10dip
	
End Sub

Sub TabHost1_TabChanged
	Select TabHost1.CurrentTab
		Case 0
			
			Try
				
				cr1 = sql1.ExecQuery($"Select * From alarm where type='next_visit'"$)
				If cr1.RowCount > 0 Then 
					panel_reged_date.Visible=True
					cr1.Position = 0
					LogColor(cr1.GetInt("enabled"),Colors.red)
					Dim enabled_switch_next_visit As Int=cr1.GetInt("enabled")
					If enabled_switch_next_visit=1 Then
						ACSwitch1.Checked=True
					Else
						ACSwitch1.Checked=False
					End If
					reged_date_next_visit_bool=True
					lbl_date_reg.Text=$"تاریخ ملاقات بعدی شما با پزشک در ${cr1.GetString("clock")} است"$
				End If
		
			Catch
				Log(LastException.Message)
			End Try
			
			
		Case 1
			footer.Visible=False
			If File.Exists(Starter.rute_file_app,"db.db") = True Then
				If sql1.IsInitialized=False Then sql1.Initialize(Starter.rute_file_app,"db.db",True)
			End If
	
	
			Try
				spiner_daroo.Clear
				cr1 = sql1.ExecQuery($"Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo"$)
				For i = 0 To cr1.RowCount - 1
					cr1.Position = i
					If i=0 Then
						hiden_id_daroo=cr1.GetString("id_daroo")
						Log(lbl_clock.IsInitialized)
						lbl_clock.Text= clock_split( cr1.GetString("clock") )
						txt_info.Text=cr1.GetString("info")
						
						txt_info.Height=library.get_height_label(txt_info,txt_info.Text)+6dip
						If txt_info.Height< 35%y Then txt_info.Height=35%y
						Panel3.Top=txt_info.Top + txt_info.Height + 10dip
					
					End If
					spiner_daroo.Add(cr1.GetString("name")	)
				Next
			Catch
				Log(LastException.Message)
			End Try
	End Select
End Sub



Sub spiner_date_ItemClick (Position As Int, Value As Object)
	get_of_date
End Sub

Sub get_of_date
	Try
		spiner_daroo.Clear
		cr1 = sql1.ExecQuery($"Select * From noskhe Left join daroo on daroo.id=noskhe.id_daroo where date_noskhe='${spiner_date.SelectedItem}'"$)
		
		
		If list_clock.IsInitialized=False  Then
			list_clock.Initialize
			Dim map1 As Map
			map1.Initialize
			
			For i = 0 To cr1.RowCount - 1
				cr1.Position = i
				
				Dim clock1 As String=cr1.GetString("clock")
				If  clock1.Contains("،")=True Then
					Dim array1() As String=Regex.Split("،" , clock1)
					For y=0 To array1.Length-1
						If map1.ContainsKey(array1(y).Trim)=False Then map1.Put(array1(y).Trim,y)
					Next
				Else
					If map1.ContainsKey(clock1.Trim)=False Then map1.Put(clock1.Trim,y)
				End If
			Next
			
			For i=0 To map1.Size-1
				list_clock.Add(	map1.GetKeyAt(i)		)
			Next
			File.WriteList(File.DirDefaultExternal,"clock_daroo",list_clock)
			LogColor(File.ReadList(File.DirDefaultExternal,"clock_daroo"),Colors.blue	)
		End If
		
		
		For i = 0 To cr1.RowCount - 1
			cr1.Position = i
			If i=0 Then
				hiden_id_daroo=cr1.GetString("id_daroo")
				lbl_clock.Text=clock_split( cr1.GetString("clock") )
				txt_info.Text=cr1.GetString("info")
						
				txt_info.Height=library.get_height_label(txt_info,txt_info.Text)+6dip
				If txt_info.Height< 35%y Then txt_info.Height=35%y
				Panel3.Top=txt_info.Top + txt_info.Height + 10dip
				'				scrol_main.Panel.Height=Panel3.Top + Panel3.Height + 5dip
			End If
			spiner_daroo.Add(cr1.GetString("name")	)
		Next
		
		
		
		
		
	Catch
		Log(LastException.Message)
	End Try
End Sub

Sub btn_reg_Click
	If lbl_date.Text.Length>0 Then
		If reged_date_next_visit_bool=True Then
			LogColor($"update alarm set clock='${lbl_date.Tag}' where type='next_visit'"$  , Colors.Blue)
			sql1.ExecNonQuery($"update alarm set clock='${lbl_date.Tag}' where type='next_visit'"$)
			lbl_date_reg.Text=$"تاریخ ملاقات بعدی شما با پزشک در ${lbl_date.tag} است"$
			File.WriteString(File.DirDefaultExternal,"date_visit",lbl_date.tag)
			ToastMessageShow("تاریخ ویرایش شد",False)
		Else
			If DialogResponse.POSITIVE=Msgbox2("آیا میخواهید تاریخ ویزیت بعدی را درج کنید؟","","بله","انصراف","",Null) Then
				Dim title_visit_alarm As String="یادآور ملاقات شما با پزشک ستاک"
				Try
					sql1.ExecNonQuery($"INSERT INTO alarm(enabled,title,clock,type) VALUES(1,'${title_visit_alarm}','${lbl_date.Text}','next_visit')"$)
					panel_reged_date.Visible=True
					ACSwitch1.Checked=True
					lbl_date_reg.Text=$"تاریخ ملاقات بعدی شما با پزشک در ${lbl_date.tag} است"$
					File.WriteString(File.DirDefaultExternal,"date_visit",lbl_date.tag)
				Catch
					Log(LastException)
				End Try
			End If
		End If
		
	Else
		ToastMessageShow("لطفا تاریخ را درج کنید",False)
	End If
End Sub

Sub ACSwitch2_CheckedChange(Checked As Boolean)
	If Checked=True Then
		File.WriteString(File.DirDefaultExternal,"enabled_alarm_daroo",True)
	Else
		If File.Exists(File.DirDefaultExternal,"enabled_alarm_daroo") = True Then
			File.Delete(File.DirDefaultExternal,"enabled_alarm_daroo")
		End If
	End If
End Sub