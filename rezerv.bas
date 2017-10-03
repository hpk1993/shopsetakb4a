Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#Extends: android.support.v7.app.AppCompatActivity
#End Region

Sub Process_Globals


End Sub

Sub Globals
	Dim map_day As Map
	Dim date_per As String
	Private progress_spot As SpotsDialog
	
	Private Panel1 As Panel
	Dim daye_per As PersianDate
	Dim val_day As String
	
	Private scrol_main As ScrollView
	Private footer As Label
	Private panel_history As Panel

	Private Label3 As Label
	
	Dim dp As DatePicker_persian_hpk
	
	Private spin_clock As ACSpinner
	Private spin_city As ACSpinner
	Private spin_day As ACSpinner
'	Private lbl_date As Label
	
	Type Adapter_Clock_Reserv(id As Int,city As String,clocks As String,day As String)
	Dim List_Clocks As List
	Type Adapter_visited(id As Int,user_id As Int,city As String,date_visit As String,clock_visit As String,day_visit As String)
	Dim List_visited As List
	Private btn_back As Button
	Dim persian As ManamPerianDateUltimate
	Private spin_month As ACSpinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol_layout")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	Label3.Text="رزرو آنلاین نوبت"
	Label3.Gravity=Gravity.CENTER_HORIZONTAL
	Label3.SetLayout(10%x,Label3.Top,80%x,Label3.Height)
	scrol_main.Panel.LoadLayout("rezerv")
	
	panel_history.Visible=False
	footer.Visible=False
	scrol_main.Height=scrol_main.Height+footer.Height
	function. initialize_spotdialog(progress_spot)
	

	connector.Initialize(Me,"db",Starter.server_mysql,Starter.dn,Starter.du,Starter.dp)
	progress_spot.ShowDialog
	connector.send_query("select * from `clock_visit` order by `id`","get_rezerv","")
	
	
	scrol_main.Panel.Height=100%y
End Sub

Sub Activity_Resume

End Sub





'Sub dp_dateClick(Text As String)
'	lbl_date.Text=Text
'	lbl_date.tag=Text
'	
'	spin_clock.Clear
'	For i=0 To List_Clocks.Size-1
'		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
'		If Adapter_Clock_Reserv1.city=spin_city.SelectedItem And Adapter_Clock_Reserv1.day=spin_day.SelectedItem Then
'			
'			Dim parser As JSONParser
'			parser.Initialize(Adapter_Clock_Reserv1.clocks)
'			Dim root As List = parser.NextArray
'			For Each colroot As String In root
'				Dim reserved_clock As Boolean=False
'				For z=0 To List_visited.Size-1
'					Dim Adapter_visited1 As Adapter_visited=List_visited.Get(z)
'					
''					LogColor(Adapter_visited1.city & " | " & spin_city.SelectedItem ,Colors.Magenta)
''					LogColor(Adapter_visited1.clock_visit & " | " & colroot,Colors.Magenta)
''					LogColor(Adapter_visited1.day_visit & " | " & spin_day.SelectedItem,Colors.Magenta)
'					LogColor(lbl_date.Text & " | " & Adapter_visited1.date_visit & " *** " & Adapter_visited1.clock_visit & " | " & colroot,Colors.Magenta)
'					LogColor(colroot,Colors.Blue)
'							
'					If Adapter_visited1.city=spin_city.SelectedItem And Adapter_visited1.clock_visit=colroot And Adapter_visited1.day_visit=spin_day.SelectedItem And Text=Adapter_visited1.date_visit Then
'						Log("exist visit clock this time")
'						reserved_clock=True
'						'reserved
'						Exit
'					Else
'						reserved_clock=False
'						
'					End If
'					
'				Next
'				If reserved_clock=False Then spin_clock.Add(colroot)
'				
'				
'			Next
'			
'		End If
'	Next
'	
'End Sub

Sub db_connector(records As List,tag As Object)

	Select tag
		Case "get_rezerv"
			If records.Size>0 Then
				spin_city.Clear
				List_Clocks.Initialize
				Dim map_city As Map
				'			Dim map_day As Map
				map_city.Initialize
				'			map_day.Initialize
				For i=0 To records.Size-1
					Dim col As Map=records.Get(i)
					Dim city As String=col.Get("city")
					Dim clocks As String=col.Get("clocks")
					Dim day As String=col.Get("day")
				
					map_city.Put(city,i)
				
					Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv
					Adapter_Clock_Reserv1.Initialize
					Adapter_Clock_Reserv1.city=city
					Adapter_Clock_Reserv1.clocks=clocks
					Adapter_Clock_Reserv1.day=day
					List_Clocks.Add(Adapter_Clock_Reserv1)
				
				Next
			
				For y=0 To map_city.Size-1
					spin_city.Add(map_city.GetKeyAt(y) )
				Next
			
				progress_spot.ShowDialog
				connector.send_query($"SELECT * FROM `visit`"$,"get_visit","")
			End If
		
		Case "get_visit"
			List_visited.Initialize
			If records.Size>0 Then
				For i=0 To records.Size-1
					Dim col As Map=records.Get(i)
					Dim Adapter_visited1 As Adapter_visited
					Adapter_visited1.date_visit=col.Get("date_visit")
					Adapter_visited1.clock_visit=col.Get("clock_visit")
					Adapter_visited1.day_visit=col.Get("day_visit")
					Adapter_visited1.user_id=col.Get("user_id")
					Adapter_visited1.city=col.Get("city")
					List_visited.Add(Adapter_visited1)
					Log("add visited")
				Next
				
			End If
			
			
			map_day.Initialize
			#region
			spin_city.SelectedIndex=0
			Dim value As String=spin_city.SelectedItem
			For i=0 To List_Clocks.Size-1
				Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
				
				If Adapter_Clock_Reserv1.city=value Then
'					spin_day.Add(Adapter_Clock_Reserv1.day)
					map_day.Put(Adapter_Clock_Reserv1.day,i)
				End If
			Next
	
			spin_day.SelectedIndex=0
			
			
			spin_month.Add(get_nameofmonth_string(persian.PersianMonth) )
			spin_month.Add(get_nameofmonth_string(persian.PersianMonth+1) )
			
			For i=1 To get_dayofmonth(get_numberofmonth_int(spin_month.SelectedItem) )
				Dim day_select As String= persian.getDayOfWeekByPerianDate(1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & i)
				If map_day.ContainsKey(day_select)=True Then
					spin_day.Add( day_select & " " & i & " " & get_nameofmonth_string(get_numberofmonth_int(spin_month.SelectedItem)) ) 'day_select & " " & i & " "
				End If
			Next
			
			Dim array_day() As String=Regex.Split(" ",spin_day.SelectedItem)
			 date_per=1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & array_day(1)
		
		
			For i=0 To List_Clocks.Size-1
				Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
				If Adapter_Clock_Reserv1.city=value And Adapter_Clock_Reserv1.day=array_day(0)Then
			
					Dim parser As JSONParser
					parser.Initialize(Adapter_Clock_Reserv1.clocks)
				
					
					Dim root As List = parser.NextArray
					
					For Each colroot As String In root
						Dim reserved_clock As Boolean=False
						For z=0 To List_visited.Size-1
							Dim Adapter_visited1 As Adapter_visited=List_visited.Get(z)
							
							If Adapter_visited1.city=value And Adapter_visited1.clock_visit=colroot And Adapter_visited1.day_visit=spin_day.SelectedItem And date_per=Adapter_visited1.date_visit Then
								Log("exist visit clock this time")
								reserved_clock=True
								'reserved
								Exit
							Else
								reserved_clock=False
						
							End If
					
						Next
						If reserved_clock=False Then spin_clock.Add(colroot)
					
						
					Next
				End If
			Next
			#end region
			
			
		Case "insert_visit"
			Log(records)
			ToastMessageShow("نوبت شما با موفقیت ثبت شد" & CRLF & "وضعیت:" & CRLF & spin_city.SelectedItem & CRLF & val_day & " " & date_per & CRLF & spin_clock.SelectedItem,True)
			
			progress_spot.DisMissDialog
			progress_spot.ShowDialog
'			connector.send_query("select * from `clock_visit` order by `id`","get_rezerv","")
			Activity.Finish
			
			
			
		
		Case "disconnect"
			progress_spot.DisMissDialog
			Log("no internet")
		Case "error"
			Log("eror")
			progress_spot.DisMissDialog
	End Select
	progress_spot.DisMissDialog
	ProgressDialogHide
End Sub


Sub get_dayofmonth(m As Int) As Int
	
	Select m
		Case 1,2,3,4,5,6:
			Return 31
		Case 12
			Return 20
		Case Else
			Return 30
		
	End Select
End Sub

Sub get_nameofmonth_string(m As Int) As String
	
	Select m
		Case 1:
			Return "فروردین"
		Case 2:
			Return "اردیبهشت"
		Case 3:
			Return "خرداد"
		Case 4:
			Return "تیر"
		Case 5:
			Return "مرداد"
		Case 6:
			Return "شهریور"
		Case 7:
			Return "مهر"
		Case 8:
			Return "آبان"
		Case 9:
			Return "آذر"
		Case 10:
			Return "دی"
		Case 11:
			Return "بهمن"
		Case 12:
			Return "اسفند"
		
		
	End Select
End Sub

Sub get_numberofmonth_int(m As String) As Int
	
	Select m
		Case "فروردین":
			Return 1
		Case "اردیبهشت":
			Return 2
		Case "خرداد":
			Return 3
		Case "تیر":
			Return 4
		Case "مرداد":
			Return 5
		Case "شهریور":
			Return 6
		Case "مهر":
			Return 7
		Case "آبان":
			Return 8
		Case "آذر":
			Return 9
		Case "دی":
			Return 10
		Case "بهمن":
			Return 11
		Case "اسفند":
			Return 12
		
		
	End Select
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub spin_clock_ItemClick (Position As Int, Value As Object)
	
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

Sub btn_reg_Click
	If File.Exists(myLibrary.rute,Starter.filename_user)=True Then
'		If lbl_date.Text.Length>3 Then
			Dim user_info As List
			user_info.Initialize
			user_info=File.ReadList(myLibrary.rute,Starter.filename_user)
			progress_spot.ShowDialog
		Dim array_day() As String=Regex.Split(" ",val_day)
		connector.send_query($"insert into `visit`(`user_id`,`date_visit`,`day_visit`,`clock_visit`,`city`) Values(${user_info.Get(3)},'${date_per}',N'${array_day(0)}','${spin_clock.SelectedItem}',N'${spin_city.SelectedItem}')"$,"insert_visit","")
'		Else
'			ToastMessageShow("لطفا تاریخ مورد نظر را انتخاب کنید",False)
		
'		End If
		
	Else
		
		ToastMessageShow("لطفا ابتدا وارد حساب کاربری خود شوید",False)
	End If
	
		
End Sub

Sub spin_city_ItemClick (Position As Int, Value As Object)
	spin_clock.Clear
	spin_day.Clear
	map_day.Clear
	Dim Value_city As String=spin_city.SelectedItem
	For i=0 To List_Clocks.Size-1
		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
				
		If Adapter_Clock_Reserv1.city=Value_city Then
			'					spin_day.Add(Adapter_Clock_Reserv1.day)
			map_day.Put(Adapter_Clock_Reserv1.day,i)
		End If
	Next
	
	For i=1 To get_dayofmonth(get_numberofmonth_int(spin_month.SelectedItem) )
		Dim day_select As String= persian.getDayOfWeekByPerianDate(1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & i)
		If map_day.ContainsKey(day_select)=True Then
			spin_day.Add( day_select & " " & i & " " & get_nameofmonth_string(get_numberofmonth_int(spin_month.SelectedItem)) ) 'day_select & " " & i & " "
		End If
	Next
			
	Dim array_day() As String=Regex.Split(" ",val_day)
	date_per=1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & array_day(1)

	
		
	For i=0 To List_Clocks.Size-1
		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
		If Adapter_Clock_Reserv1.city=spin_city.SelectedItem Then 'And Adapter_Clock_Reserv1.day=array_day(0)
			
			Dim parser As JSONParser
			parser.Initialize(Adapter_Clock_Reserv1.clocks)
				
					
			Dim root As List = parser.NextArray
		
			For Each colroot As String In root
				LogColor(colroot  , Colors.Blue)
				Dim reserved_clock As Boolean=False
				For z=0 To List_visited.Size-1
					Dim Adapter_visited1 As Adapter_visited=List_visited.Get(z)
					LogColor(Adapter_visited1.city & "|" & Value , Colors.red)
					LogColor(Adapter_visited1.clock_visit & "|" & colroot , Colors.red)
					LogColor(Adapter_visited1.day_visit & "|" & array_day(0) , Colors.red)
					LogColor(date_per & "|" & Adapter_visited1.date_visit , Colors.red)
					If Adapter_visited1.city=Value And Adapter_visited1.clock_visit=colroot And Adapter_visited1.day_visit=array_day(0) And date_per=Adapter_visited1.date_visit Then
						Log("exist visit clock this time")
						reserved_clock=True
						'reserved
						Exit
					Else
						reserved_clock=False
						
					End If
					
				Next
				If reserved_clock=False Then spin_clock.Add(colroot)
					
						
			Next
		End If
	Next

	
End Sub

Sub spin_day_ItemClick (Position As Int, Value As Object)
	spin_day.Clear
	spin_clock.Clear
	map_day.Clear
	Dim Value_city As String=spin_city.SelectedItem
	For i=0 To List_Clocks.Size-1
		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
				
		If Adapter_Clock_Reserv1.city=Value_city Then
			'					spin_day.Add(Adapter_Clock_Reserv1.day)
			If map_day.ContainsKey(Adapter_Clock_Reserv1.day)=False Then map_day.Put(Adapter_Clock_Reserv1.day,i)
		End If
	Next
	
	For i=1 To get_dayofmonth(get_numberofmonth_int(spin_month.SelectedItem) )
		Dim day_select As String= persian.getDayOfWeekByPerianDate(1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & i)
		If map_day.ContainsKey(day_select)=True Then
			spin_day.Add( day_select & " " & i & " " & get_nameofmonth_string(get_numberofmonth_int(spin_month.SelectedItem)) ) 'day_select & " " & i & " "
		End If
	Next
			
	Dim array_day() As String=Regex.Split(" ",Value)
	
	 date_per =1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & array_day(1)

	LogColor(List_visited,Colors.Blue)
	For i=0 To List_Clocks.Size-1
		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
		If Adapter_Clock_Reserv1.city=spin_city.SelectedItem Then
			
			Dim parser As JSONParser
			parser.Initialize(Adapter_Clock_Reserv1.clocks)
			Dim root As List = parser.NextArray
			
			For Each colroot As String In root
				
				Dim reserved_clock As Boolean=False
				
				For z=0 To List_visited.Size-1
					Dim Adapter_visited1 As Adapter_visited=List_visited.Get(z)
					If Adapter_visited1.city=spin_city.SelectedItem And Adapter_visited1.clock_visit=colroot And Adapter_visited1.day_visit=array_day(0) And date_per=Adapter_visited1.date_visit Then
						Log("exist visit clock this time")
						reserved_clock=True
						'reserved
						Exit
					Else
						reserved_clock=False
						
					End If
					
				Next
				If reserved_clock=False Then
					LogColor(colroot  , Colors.Blue)
					 spin_clock.Add(colroot)
				End If
					
						
			Next
		End If
	Next
	
	val_day=Value
	Log("****** " & Value)
End Sub


Sub btn_back_Click
	Activity.Finish
End Sub

Sub spin_month_ItemClick (Position As Int, Value As Object)
	spin_day.Clear
	spin_clock.Clear
	map_day.Clear
	Dim Value_city As String=spin_city.SelectedItem
	For i=0 To List_Clocks.Size-1
		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
				
		If Adapter_Clock_Reserv1.city=Value_city Then
			'					spin_day.Add(Adapter_Clock_Reserv1.day)
			map_day.Put(Adapter_Clock_Reserv1.day,i)
		End If
	Next
	
	For i=1 To get_dayofmonth(get_numberofmonth_int(spin_month.SelectedItem) )
		Dim day_select As String= persian.getDayOfWeekByPerianDate(1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & i)
		If map_day.ContainsKey(day_select)=True Then
			spin_day.Add( day_select & " " & i & " " & get_nameofmonth_string(get_numberofmonth_int(spin_month.SelectedItem)) ) 'day_select & " " & i & " "
		End If
	Next
			
	Dim array_day() As String=Regex.Split(" ",val_day)
	date_per=1396 & "/" & get_numberofmonth_int(spin_month.SelectedItem) & "/" & array_day(1)
		
		
	For i=0 To List_Clocks.Size-1
		Dim Adapter_Clock_Reserv1 As Adapter_Clock_Reserv=List_Clocks.Get(i)
		If Adapter_Clock_Reserv1.city=spin_city.SelectedItem Then
			
			Dim parser As JSONParser
			parser.Initialize(Adapter_Clock_Reserv1.clocks)
				
					
			Dim root As List = parser.NextArray
					
			For Each colroot As String In root
				Dim reserved_clock As Boolean=False
				For z=0 To List_visited.Size-1
					Dim Adapter_visited1 As Adapter_visited=List_visited.Get(z)
							
					If Adapter_visited1.city=spin_city.SelectedItem And Adapter_visited1.clock_visit=colroot And Adapter_visited1.day_visit=spin_day.SelectedItem And date_per=Adapter_visited1.date_visit Then
						Log("exist visit clock this time")
						reserved_clock=True
						'reserved
						Exit
					Else
						reserved_clock=False
						
					End If
					
				Next
				If reserved_clock=False Then spin_clock.Add(colroot)
					
						
			Next
		End If
	Next
End Sub