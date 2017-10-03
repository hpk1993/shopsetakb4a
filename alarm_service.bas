Type=Service
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: true
	
#End Region

Sub Process_Globals
	Dim daye_per As PersianDate
	Dim timer1 As Timer
	Dim index As Int
End Sub

Sub Service_Create
	Log("Service_Create")
	timer1.Initialize("timer1",2000)
	
	
End Sub

Sub Service_Start (StartingIntent As Intent)
'	Log("service start")
	timer1.Enabled=True
	If File.Exists(File.DirDefaultExternal,"clock_daroo") Then
		Dim enabled_alaram_daroo As Boolean=File.Exists(File.DirDefaultExternal,"enabled_alarm_daroo")
		DateTime.TimeFormat="HH:mm"
'		Log(index & "| " & DateTime.Time(DateTime.Now) & " | is enabled=" & enabled_alaram_daroo	)
		
		If enabled_alaram_daroo=True Then
			Dim list_clock As List=File.ReadList(File.DirDefaultExternal,"clock_daroo")
			
			For i=0 To list_clock.Size-1
				Dim time1 As String=list_clock.Get(i)
				DateTime.TimeFormat="HH:mm"
				If time1.Length=1 Then
					DateTime.TimeFormat="H"
				else if time1.Length=2 Then
					DateTime.TimeFormat="HH"
				End If
				
'				Log("* " & time1 )
				Dim timeset As Long=DateTime.TimeParse(	time1 )
'				LogColor(	timeset	,Colors.Red)
				If (DateTime.Now >= timeset) And (DateTime.Now <= (timeset+10000))  Then
					Dim nb As NotificationBuilder
					nb.Initialize
					nb.setCustomLight(Colors.Magenta,500,500)
					nb.DefaultVibrate=True
					nb.DefaultSound=True
					nb.smallIcon="icon"
					nb.Ticker="زمان مصرف دارو رسیده"
					nb.ContentTitle= "مصرف دارو"
					nb.contentInfo="(ستاک دارو)"
					nb.contentText= "لطفا دارو مورد نظر را مصرف کنید"
					nb.Autocancel=True
					'					nb.Tag="u" & (uid)
					nb.setActivity(show_noskhe)
					nb.Notify(	i )
				End If
				
			Next
			
		End If
		
		Dim enabled_alaram_visit As Boolean=File.Exists(File.DirDefaultExternal,"enabled_alarm_visit")
		If File.Exists(File.DirDefaultExternal,"date_visit")=True And enabled_alaram_visit=True Then
			Dim date_selected As String=File.ReadString(File.DirDefaultExternal,"date_visit")
'			Log(date_selected & " | " & daye_per.getDate(0,0,0,"/")	)
'			Log( "CompareTo: " & 	date_selected.CompareTo(daye_per.getDate(0,0,0,"/"))	)
			If date_selected.CompareTo(daye_per.getDate(0,0,0,"/"))=0 Then
				Dim nb As NotificationBuilder
				nb.Initialize
				nb.setCustomLight(Colors.Magenta,500,500)
				nb.DefaultVibrate=False
				nb.DefaultSound=False
				nb.smallIcon="icon"
				nb.Ticker="با پزشک خود قرار ملاقات دارید"
				nb.ContentTitle= "ملاقات پزشک "
				nb.contentInfo="(ستاک)"
				nb.contentText= "امروز قراره ملاقات با پزشک ستاک دارید"
				nb.Autocancel=True
				nb.setActivity(show_noskhe)
				nb.Notify(	1000 )
			End If
			
		End If
		
	End If
	
	
	
	
End Sub

Sub Service_Destroy

End Sub

Sub timer1_Tick
'	Log("timer1.Enabled=  False")
	timer1.Enabled=False
	index=index+1
	StartServiceAt(Me,DateTime.Now,True)
End Sub
