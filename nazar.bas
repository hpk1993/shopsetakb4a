Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	Public server_mysql As String
	Public code_kala As Int
End Sub

Sub Globals
	Dim mytoastMessage As MyToastMessageShow
	
	Dim rate1,rate2,rate3 As rate_star
	Dim val_rate1,val_rate2,val_rate3 As Int
	
	Private rate_panel1 As Panel
	Private rate_panel2 As Panel
	Private rate_panel3 As Panel
	
	
	Private rate1_panel As Panel
	Private rate2_panel As Panel
	Private rate3_panel As Panel
	'/////////////
	Private progress_spot As SpotsDialog
	Private txt_nazar As EditText
	Dim user As String
	
	Dim phone_id As PhoneId
	Private reg As Button
	Dim lst_dat As List
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	connector.Initialize2(Me)
	Activity.LoadLayout("reg_nazar")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF13879A,Colors.Red)
	
	function. initialize_spotdialog(progress_spot)
	server_mysql=Starter.server_mysql
	
	lst_dat =File.ReadList(myLibrary.rute,Starter.filename_user)
	user=lst_dat.Get(2)
			
	val_rate1=0
	val_rate2=0
	val_rate3=0
	#region reate
		rate1.Initialize(rate1_panel,Me,"get_rate1",rate1_panel.Width/5-2dip,rate1_panel.Width/5-2dip,LoadBitmap(File.DirAssets,"star1.png"),LoadBitmap(File.DirAssets,"star0.png"))
		rate1.set_rate
		
		rate2.Initialize(rate2_panel,Me,"get_rate2",rate2_panel.Width/5-2dip,rate2_panel.Width/5-2dip,LoadBitmap(File.DirAssets,"star1.png"),LoadBitmap(File.DirAssets,"star0.png"))
		rate2.set_rate
		
		rate3.Initialize(rate3_panel,Me,"get_rate3",rate3_panel.Width/5-2dip,rate3_panel.Width/5-2dip,LoadBitmap(File.DirAssets,"star1.png"),LoadBitmap(File.DirAssets,"star0.png"))
		rate3.set_rate
	#end region
	
	ckeck_nazar
	
End Sub

Sub font_label
	For Each view1 As View In Activity.GetAllViewsRecursive
		If view1 Is Label Then
			Dim label_font As Label
			label_font=view1
			label_font.Typeface=Starter.font_body
		End If
	Next
End Sub

Sub get_rate1(rate_number)
	'ToastMessageShow("star1:" & rate_number,False)
	val_rate1=rate_number
End Sub
Sub get_rate2(rate_number)
	'ToastMessageShow("star2:" & rate_number,False)
	val_rate2=rate_number
End Sub
Sub get_rate3(rate_number)
	'ToastMessageShow("star3:" & rate_number,False)
	val_rate3=rate_number
End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btn_close_Click
	Activity.Finish
	myLibrary.SetAnimation("file3","file4")
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
		Return True
	End If
End Sub

Sub reg_Click
	If val_rate1 > 0 And val_rate2>0 And val_rate3>0 Then
		insert_nazar
	Else
		ToastMessageShow("لطفا به ما ستاره بدید",False)
	End If
End Sub

Sub insert_nazar
	progress_spot.ShowDialog
	connector.send_query($"insert into `nazar`(`code_kala`,`rate1`,`rate2`,`rate3`,`phone`,`sender`,`user_id`,`msg`,`stuts`) values(${code_kala},${val_rate1},${val_rate2},${val_rate3},'${phone_id.GetDeviceId}',N'${user}',${lst_dat.Get(3)},N'${txt_nazar.Text.Trim}',0)"$,"insert_nazar","")
End Sub

Sub ckeck_nazar
	progress_spot.ShowDialog
	connector.send_query($"SELECT * From `nazar` where `phone`='${phone_id.GetDeviceId}' And `code_kala`=${code_kala}"$,"ckeck_nazar","")
End Sub


Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "insert_nazar"
				Activity.Finish
				ToastMessageShow("نظر شما ثبت شد",True)
				progress_spot.DisMissDialog
			
			Case "ckeck_nazar"
				If records.Size >0  Then
					Activity.Finish
					ToastMessageShow("نظر شما قبلا ثیت شده است",True)
				Else
					'insert_nazar
					reg.Enabled=True
				End If
				progress_spot.DisMissDialog
			
		
			Case "disconnect"
				StartActivity(disconnect)
				Activity.Finish
			Case "error"
				StartActivity(disconnect)
				Activity.Finish
				ToastMessageShow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",True)
		End Select
		progress_spot.DisMissDialog
		ProgressDialogHide
	Catch
		ToastMessageShow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",True)
		progress_spot.DisMissDialog
	End Try
End Sub






