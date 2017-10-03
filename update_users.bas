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
End Sub

Sub Globals
	Dim mytoastMessage As MyToastMessageShow
	Dim effect_btn1 As RippleView
	Private progress_spot As SpotsDialog
	Private ime As IME

	Dim id_user As Int
	
	Private btn_back As Button
	Private Label3 As Label
	Private Panel1 As Panel
	Private scrol_main As ScrollView
	Private footer As Label
	Private btn_search As Button
	Private btn_share As Button
	Private btn_sabad As Button
	Private txt_name As EditText
	Private txt_lname As EditText
	Private txt_meli As EditText
	Private txt_mobile As EditText
	Private txt_tel As EditText
	Private spin_sex As Spinner
	Private spin_year As Spinner
	Private spin_month As Spinner
	Private spin_day As Spinner
	Private txt_pass As EditText
	Private txt_address As EditText
	Private txt_email As EditText
	Private txt_job As EditText
	Private txt_post As EditText
	
	Dim toast As TatyToast
	Dim su As StringUtils
	Dim bc As ByteConverter
	Private p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol_layout")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	scrol_main.Panel.LoadLayout("update_users")
	For Each v As View In Panel1.GetAllViewsRecursive
		If v Is Panel Then
			Dim pnlbg As Panel=v
			If pnlbg.Tag="bg_p" Then
				pnlbg.Background=color_header
			End If
		End If
	Next
	connector.Initialize2(Me)
	server_mysql=Starter.server_mysql
	
	
	function. initialize_spotdialog(progress_spot)
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF13879A,Colors.Red)
	ime.Initialize("IME")
	ime.AddHeightChangedEvent
	effect_btn1.Initialize(footer,Colors.White,400,False)
	scrol_main.Panel.Height=900dip'txt_address.Top + txt_address.Height + 3dip
	
	
	Label3.Text="ویرایش مشخصات"
	footer.Text="ذخیره تغییرات"
	
	Dim lst_user As List=File.ReadList(myLibrary.rute,Starter.filename_user)
	id_user=lst_user.Get(3)
	#region spin
		spin_sex.Add("زن")
		spin_sex.Add("مرد")
		spin_sex.SelectedIndex=0
		spin_sex.DropdownBackgroundColor=Colors.White
		spin_sex.DropdownTextColor=Colors.Black
		
		spin_year.Add("سال تولد")
		spin_month.Add("ماه")
		spin_day.Add("روز")
		
		Dim top_old As Int=myLibrary.persion_date("y")
		For i=1300 To top_old-10
			spin_year.Add(myLibrary.CovertEnglish2Persian(i))
		Next
		For i=1 To 12
			spin_month.Add(myLibrary.CovertEnglish2Persian(i))
		Next
		For i=1 To 31
			spin_day.Add(myLibrary.CovertEnglish2Persian(i))
		Next
		spin_day.SelectedIndex=0
		spin_day.DropdownBackgroundColor=Colors.White
		spin_day.DropdownTextColor=Colors.Black
		
		spin_month.SelectedIndex=0
		spin_month.DropdownBackgroundColor=Colors.White
		spin_month.DropdownTextColor=Colors.Black
		
		spin_year.SelectedIndex=0
		spin_year.DropdownBackgroundColor=Colors.White
		spin_year.DropdownTextColor=Colors.Black
	#end region
	
	get_user(id_user)
	
End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub btn_back_Click
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
	Return True
End Sub

Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "get_user"
				If records.Size > 0 Then
					Dim map1 As Map=records.Get(0)
						
					txt_name.Text=map1.Get("name")
					If txt_name.Text="null" Then txt_name.Text=""
						
					txt_lname.Text=map1.Get("lname")
					If txt_lname.Text="null" Then txt_lname.Text=""
						
					txt_meli.Text=map1.Get("code_meli")
					If txt_meli.Text="null" Then txt_meli.Text=""
						
					txt_mobile.Text=map1.Get("mobile")
					If txt_mobile.Text="null" Then txt_mobile.Text=""
						
					txt_tel.Text=map1.Get("tel")
					If txt_tel.Text="null" Then txt_tel.Text=""
						
					Dim sex As Int=map1.Get("sex")
					spin_sex.SelectedIndex=sex
						
					txt_post.Text=map1.Get("post_code")
					If txt_post.Text="null" Then txt_post.Text=""
						
					txt_job.Text=map1.Get("job")
					If txt_job.Text="null" Then txt_job.Text=""
					Try
						Dim date_born() As String=Regex.Split("/",map1.Get("born"))
						For i=0 To spin_year.Size-1
							If spin_year.GetItem(i).CompareTo(myLibrary.CovertEnglish2Persian(date_born(0)))=0 Then
								spin_year.SelectedIndex=i
								Exit
							End If
						Next
						For i=0 To spin_month.Size-1
							If spin_month.GetItem(i).CompareTo(myLibrary.CovertEnglish2Persian(date_born(1)))=0 Then
								spin_month.SelectedIndex=i
								Exit
							End If
						Next
						For i=0 To spin_day.Size-1
							If spin_day.GetItem(i).CompareTo(myLibrary.CovertEnglish2Persian(date_born(2)))=0 Then
								spin_day.SelectedIndex=i
								Exit
							End If
						Next
					Catch
							
					End Try
						
					txt_email.Text=map1.Get("email")
					If txt_email.Text="null" Then txt_email.Text=""
						
					txt_address.Text=map1.Get("address")
					If txt_address.Text="null" Then txt_address.Text=""
						
						
					If txt_pass.Text="null" Then txt_pass.Text=""
					Dim byte1() As Byte
					byte1=su.DecodeBase64(map1.Get("password"))
					txt_pass.Text=bc.StringFromBytes(byte1,"utf-8")
						
				End If
				progress_spot.DisMissDialog
				
			Case "update_user"
				Dim lst As List=File.ReadList(myLibrary.rute,Starter.filename_user)
				lst.Set(0,txt_mobile.Text.Trim)
				lst.Set(1,txt_name.Text.Trim)
				lst.Set(2,txt_lname.Text.Trim)
				File.WriteList(myLibrary.rute,Starter.filename_user,lst)
				progress_spot.DisMissDialog
					
				ToastMessageShow("تغییرات ذخیره شد",True)
				Activity.Finish
						
			
		
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





Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
		Return True
	End If
End Sub



Sub get_user(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `users` WHERE `id`=${id}"$,"get_user","")
End Sub

Sub update_user()
	progress_spot.ShowDialog
		Dim pass As String
	    pass=(su.EncodeBase64(bc.StringToBytes(txt_pass.Text.Trim,"utf-8")))
		Dim b As String=myLibrary.CovertPersian2English(spin_year.SelectedItem) & "/" & myLibrary.CovertPersian2English(spin_month.SelectedItem) & "/" & myLibrary.CovertPersian2English(spin_day.SelectedItem)
	connector.send_query($"update `users` set `sex`=${spin_sex.SelectedIndex},`name`=N'${txt_name.Text.Trim}',`lname`=N'${txt_lname.Text.Trim}',`code_meli`=N'${txt_meli.Text.Trim}',`born`=N'${b}',`job`=N'${txt_job.Text.Trim}',`mobile`=N'${txt_mobile.Text.Trim}',`tel`=N'${txt_tel.Text.Trim}',`address`=N'${txt_address.Text.Trim}',`post_code`=N'${txt_post.Text.Trim}',`email`=N'${txt_email.Text.Trim}',`password`=N'${pass}' WHERE `id`=${id_user}"$,"update_user","")
End Sub

Sub footer_Click
	If txt_pass.Text.Length >=6 Then
		If txt_name.Text.Length >= 3 And txt_lname.Text.Length >= 3 Then
			If txt_mobile.Text.Length =11 And txt_address.Text.Length > 5 And txt_post.Text.Length=10 Then
				update_user
			Else
				ToastMessageShow("فیلد های شماره همراه و آدرس و کد پستی را بررسی کنید",True)
			End If
		Else
			ToastMessageShow("فیلد های نام و نام خانوادگی را پر کنید",True)
		End If
	Else
		ToastMessageShow("حداقل طول گذرواژه 6 کاراکتر است",True)
	End If
End Sub