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
	Dim username As String=Starter.username_sms
	Dim password As String=Starter.password_sms
	Dim key_software As String=Starter.Api_key_sms
	Dim sender_sms As String=Starter.sender_sms(0)
	Dim name_dat As String
	'*************************************************************************
	Dim server_adress_forget As String=Starter.server_address_send_ForgetPassword
	Dim effect_btn1 As RippleView ',effect_btn2,effect_btn3,effect_btn4,effect_btn5
	Dim IME As IME
	Private progress_spot As SpotsDialog
	Dim uid As Int
	Private btn_down As Button
	Private scrol_reg As ScrollView

	Private txt_pass As EditText
	'//////////////////
	Private txt_name,txt_lname,txt_job,txt_phone,txt_adress As EditText
	Private txt_reg_pass1,txt_reg_pass2 As EditText
	Private btn_reg As Button
	'////////////////base64
	Dim su As StringUtils
	Dim bc As ByteConverter
	Private txt_user As EditText
	Private btn_forget As Button
	Private txt_forget As EditText
	Private panel_forget As Panel
	Private Panel1 As Panel
	Private btn_login As Button
	Private panel_login As Panel
	Private panel_reg As Panel
	Private img_login As ImageView
	Dim toast As TatyToast
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("reg_login")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	function. initialize_spotdialog(progress_spot)
	server_mysql=Starter.server_mysql
	connector.Initialize2(Me)
	name_dat=Starter.filename_user
	IME.Initialize("IME")
	IME.AddHeightChangedEvent	
	
	'effect_btn1.Initialize(btn_down,Colors.Red,700,False)

'	effect_btn3.Initialize(btn_forget,Colors.Red,700,False)	
'	effect_btn4.Initialize(btn_login,Colors.Red,700,False)	
		
#region register_users
	btn_reg.Initialize("btn_reg")
	txt_name.Initialize("txt_name")
	txt_lname.Initialize("txt_lname")
	txt_job.Initialize("txt_job")
	txt_phone.Initialize("txt_phone")
	txt_adress.Initialize("txt_adress")
	txt_reg_pass1.Initialize("txt_reg_pass1")
	txt_reg_pass2.Initialize("txt_reg_pass2")
	
	btn_reg.Text="ثبت نام"
	btn_reg.TextSize=13
	btn_reg.TextColor=Colors.White
		Dim bg_btn As ColorDrawable
		bg_btn.Initialize2(0xff66BB6A,0,0,0)
	btn_reg.Background=bg_btn
	'اضافه کردن به پنل
	scrol_reg.Panel.AddView(txt_name,5%x,6%y,scrol_reg.Width-10%x,35dip)
	scrol_reg.Panel.AddView(txt_lname,5%x,(txt_name.Top+txt_name.Height)+20dip,scrol_reg.Width-10%x,35dip)
	scrol_reg.Panel.AddView(txt_job,5%x,(txt_lname.Top+txt_lname.Height)+20dip,scrol_reg.Width-10%x,35dip)
	scrol_reg.Panel.AddView(txt_phone,5%x,(txt_job.Top+txt_job.Height)+20dip,scrol_reg.Width-10%x,35dip)
	scrol_reg.Panel.AddView(txt_adress,5%x,(txt_phone.Top+txt_phone.Height)+20dip,scrol_reg.Width-10%x,60dip)
	
	scrol_reg.Panel.AddView(txt_reg_pass1,5%x,(txt_adress.Top+txt_adress.Height)+20dip,scrol_reg.Width-10%x,35dip)
	scrol_reg.Panel.AddView(txt_reg_pass2,5%x,(txt_reg_pass1.Top+txt_reg_pass1.Height)+20dip,scrol_reg.Width-10%x,35dip)
	scrol_reg.Panel.AddView(btn_reg,0,(txt_reg_pass2.Top+txt_reg_pass2.Height)+20dip,scrol_reg.Width,35dip)
		
	'متن
	txt_name.Hint="نام"
		txt_name.SingleLine=True	
	txt_lname.Hint="نام خانوادگی"
		txt_lname.SingleLine=True
	txt_job.Hint="شغل"
		txt_job.SingleLine=True
	txt_phone.Hint="تلفن همراه مثال :09123456789"
		txt_phone.SingleLine=True
	txt_phone.InputType=txt_phone.INPUT_TYPE_PHONE
	
	txt_adress.Hint="آدرس"
	txt_reg_pass1.Hint="گذرواژه"
	txt_reg_pass1.SingleLine=True
	txt_reg_pass2.SingleLine=True
	txt_reg_pass2.Hint="تکرار گذرواژه"
	'رنگ متن اصلی
	txt_name.TextColor=0xFF0C0249
	txt_lname.TextColor=txt_name.TextColor
	txt_job.TextColor=txt_name.TextColor
	txt_phone.TextColor=txt_name.TextColor
	txt_adress.TextColor=txt_name.TextColor
	txt_reg_pass1.TextColor=txt_name.TextColor
	txt_reg_pass2.TextColor=txt_name.TextColor
	'رنگ متن پیشفرض
	txt_name.HintColor=0xFF9B949B
	txt_lname.HintColor=txt_name.HintColor
	txt_job.HintColor=txt_name.HintColor
	txt_phone.HintColor=txt_name.HintColor
	txt_adress.HintColor=txt_name.HintColor
	txt_reg_pass1.HintColor=txt_name.HintColor
	txt_reg_pass2.HintColor=txt_name.HintColor
	'ترازبندی
	txt_name.Gravity=Gravity.CENTER_HORIZONTAL
	txt_lname.Gravity=Gravity.CENTER_HORIZONTAL
	txt_job.Gravity=Gravity.CENTER_HORIZONTAL
	txt_phone.Gravity=Gravity.CENTER_HORIZONTAL
	txt_adress.Gravity=Gravity.RIGHT + Gravity.TOP
	txt_reg_pass1.Gravity=Gravity.CENTER_HORIZONTAL
	txt_reg_pass2.Gravity=Gravity.CENTER_HORIZONTAL
	'اندازه متن
	txt_name.TextSize=13
	txt_lname.TextSize=txt_name.TextSize
	txt_job.TextSize=txt_name.TextSize
	txt_phone.TextSize=txt_name.TextSize
	txt_adress.TextSize=txt_name.TextSize
	txt_reg_pass1.TextSize=txt_name.TextSize
	txt_reg_pass2.TextSize=txt_name.TextSize
#end region
	'scrol_reg.Panel.Visible=False
	scrol_reg.Panel.Height=btn_reg.Top + btn_reg.Height + 2dip
End Sub


Sub IME_HeightChanged(NewHeight As Int, OldHeight As Int)
	If scrol_reg.Height > 100dip  Then 
		scrol_reg.Height=NewHeight - scrol_reg.Top
	End If
	
	If panel_forget.Top=btn_forget.Top + btn_forget.Height And txt_forget.RequestFocus   Then 
		panel_forget.Top=(NewHeight-NewHeight)+Panel1.Height + 2dip
	End If
End Sub



Sub Activity_Resume
	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub btn_down_Click
	
	panel_login.Visible=False
	panel_forget.Visible=False
	btn_forget.Visible=False
	btn_down.Visible=False
	img_login.Visible=False
	panel_reg.Visible=True
End Sub


Sub btn_back_Click
	If panel_reg.Visible=True Then 
		panel_reg.Visible=False
		panel_login.Visible=True
		panel_forget.Visible=True
		btn_down.Visible=True
		img_login.Visible=True
	Else
		StartActivity(Main)
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
	End If
End Sub

Sub btn_reg_Click
	If txt_reg_pass1.Text.CompareTo(txt_reg_pass2.Text)=0 And txt_reg_pass1.Text.Length >= 5 Then
		If txt_name.Text.Trim.Length >= 3 And txt_lname.Text.Trim.Length >= 3  And txt_phone.Text.Trim.Length = 11 And txt_phone.Text.Trim.Contains("09")=True Then
			If txt_job.Text.Trim.Length > 3 Then
				check_users_reg
			Else
				ToastMessageShow("لطفا شغل را به درستی وارد کنید",True)
			End If
		Else
			ToastMessageShow("اطلاعات خود را کامل وارد کنید(حداقل 3 کاراکتر)" & CRLF & "شماره تلفن همراه را مانند : 09123456789 وارد کنید",True)
		End If
	Else
		ToastMessageShow("نکته : گذرواژه باید حداقل 5 کاراکتر باشد" & CRLF & "دو فیلد گذرواژه باید مشابه هم باشند",True)
	End If
End Sub

Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "check_users_reg"
				If records.Size <=0 Then
					insert_users
					
				Else
					progress_spot.DisMissDialog
					txt_name.Text=""
					txt_lname.Text=""
					txt_job.Text=""
					txt_phone.Text=""
					txt_reg_pass1.Text=""
					txt_reg_pass2.Text=""
					toast.Initialize("محصولی درج نشده است",toast.LENGTH_SHORT,toast.ERROR)
				End If
				
				
			Case "insert_users"
				get_id
				'progress_spot.DisMissDialog
				
				
			Case "get_id"
				Dim id_user As Int
				
				If records.Size > 0 Then
					Dim map1 As Map=records.Get(0)
					id_user=map1.Get("id")
					Dim user_type As Int=map1.Get("user_type")
					Dim lst1 As List
					lst1.Initialize
					lst1.Add(txt_phone.Text.Trim) 'phone
					lst1.Add(txt_name.Text.Trim) ' نام
					lst1.Add(txt_lname.Text.Trim) 'فامیلی
					lst1.Add(id_user) 'id user
					lst1.Add(user_type)
					uid=id_user
					File.WriteList(myLibrary.rute,name_dat,lst1)
					
					Dim http_sms As HttpJob
					http_sms.Initialize("sms_send",Me)
					Dim msgsms As String=$"از عضویت در فروشگاه ${Application.LabelName} سپاسگذاریم.کد اشتراک شما ${100+id_user}"$
					http_sms.PostString(Starter.address_send_sms,$"reg_user=true&name=${txt_name.Text.Trim}&lname=${txt_lname.Text.Trim}&code=${100 + id_user}&msg=${msgsms}&to=${txt_phone.Text.Trim}"$)
					
					
				Else
					ToastMessageShow("مشکل در ثبت اطلاعات.لطفا دوباره تلاش کنید",True)
					progress_spot.DisMissDialog
				End If
			
			Case "check_login"
				
				If records.Size > 0 Then
					Dim map1 As Map=records.Get(0)
					progress_spot.DisMissDialog
					Dim lst_user As List
					lst_user.Initialize
					Dim mobile As String=map1.Get("mobile")
					If mobile.Length <=0 Then
						lst_user.Add(map1.Get("email"))
					Else
						lst_user.Add(map1.Get("mobile"))
					End If
					lst_user.Add(map1.Get("name"))
					lst_user.Add(map1.Get("lname"))
					lst_user.Add(map1.Get("id"))
					lst_user.Add(map1.Get("user_type")	)
					File.WriteList(myLibrary.rute,name_dat,lst_user)
					StartActivity(Main)
					Activity.Finish
				Else
					txt_user.Text=""
					txt_pass.Text=""
					progress_spot.DisMissDialog
					toast.Initialize("نام کاربری یا گذرواژه شما در سیستم موجود نمی باشد",toast.LENGTH_LONG,toast.ERROR)
				End If
			Case "disconnect"
				StartActivity(disconnect)
				Activity.Finish
			Case "error"
				StartActivity(disconnect)
				Activity.Finish
				ToastMessageShow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",True)
		End Select
		
		ProgressDialogHide
	Catch
		ToastMessageShow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",True)
		progress_spot.DisMissDialog
	End Try
End Sub


Sub JobDone(Job As HttpJob)
	ProgressDialogHide
	If Job.Success Then
		Dim res As String
		res = Job.GetString
		Log("Response from server: " & res)
		Dim json As JSONParser
		json.Initialize(res)
		Select Job.JobName
			Case "send_param"
					
				progress_spot.DisMissDialog
				DateTime.DateFormat="yyyy_MM_dd"
				Dim count_sms As Int=File.ReadString(myLibrary.rute,DateTime.Date(DateTime.Now))
				File.WriteString(myLibrary.rute,DateTime.Date(DateTime.Now),count_sms+1)
				Log(DateTime.Date(DateTime.Now))
				Log(count_sms)
				Dim  Pd As ParsMSGBOX
				Pd=function.style_msgbox2("پیام",Job.Getstring)
				Pd.Create
				
			Case "sms_send"
				send_push
				
				
			Case "send_push"
				ToastMessageShow("خوش آمدید",True)
				StartActivity(Main)
				Activity.Finish
				progress_spot.DisMissDialog
				
		End Select
	Else
		Log("")
	End If
End Sub


Sub send_push
	progress_spot.ShowDialog
	Dim http_push As HttpJob
	http_push.Initialize("send_push",Me)
	http_push.PostString(Starter.Addres_send_pushe,$"reg_user=true&name_lname=${txt_name.Text.Trim & " " & txt_lname.Text.Trim}&uid=${100 + uid}"$)
End Sub



Sub insert_users
	progress_spot.ShowDialog
	Dim pass As String
	pass=(su.EncodeBase64(bc.StringToBytes(txt_reg_pass1.Text.Trim,"utf-8")))
	connector.send_query($"insert into `users`(`name`,`lname`,`mobile`,`job`,`password`,`user_type`,`address`) values(N'${txt_name.Text.Trim}',N'${txt_lname.Text.Trim}',N'${txt_phone.Text.Trim}',N'${txt_job.Text.trim}',N'${pass}',2,N'${txt_adress.Text.Trim}')"$,"insert_users","")
End Sub
Sub check_users_reg
	progress_spot.ShowDialog
	connector.send_query($"SELECT * From `users` where `mobile`='${txt_phone.Text.Trim}' "$,"check_users_reg","")
End Sub

Sub check_login
	progress_spot.ShowDialog
	Dim pass As String
	pass=(su.EncodeBase64(bc.StringToBytes(txt_pass.Text.Trim,"utf-8")))
	connector.send_query($"SELECT * From `users` where (`mobile`='${txt_user.Text.Trim}' OR `email`='${txt_user.Text.Trim}') AND `password`='${pass}'"$,"check_login","")
End Sub

Sub get_id
	progress_spot.ShowDialog
	connector.send_query($"SELECT * From `users` where `mobile`='${txt_phone.Text.Trim}'"$,"get_id","")
End Sub

Sub send_function_sms(a() As String)
	progress_spot.ShowDialog
	Dim J As HttpJob
	J.Initialize("send_param",Me)
	'ProgressDialogShow2("در حال ارسال پیامک تایید...",False)
	progress_spot.ShowDialog
	J.Download2(server_adress_forget,a)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If panel_reg.Visible=True Then 
				panel_reg.Visible=False
				
				panel_login.Visible=True
				panel_forget.Visible=True
				btn_down.Visible=True
				img_login.Visible=True
				
			Return True
		Else
			
			StartActivity(Main)
			Activity.Finish
			myLibrary.SetAnimation("file3","file4")
			Return True
		End If
	End If
End Sub

Sub btn_login_Click
	If txt_user.Text.Length > 0 And txt_pass.Text.Length > 0 Then
		If txt_user.Text.Contains("09")=True And (txt_user.Text.Length = 11 ) Then
			check_login
		Else if txt_user.Text.Contains("@")=True Then
			check_login
		Else
			ToastMessageShow("نام کاریری مورد تایید نیست" & CRLF & "مثال : 09123456789" & CRLF & "یا مثال : example@domain.com",True)
		End If
	Else 
		ToastMessageShow("لطفا فیلد ها را پر کنید",True)
	End If
End Sub

Sub btn_forget_Click
	If panel_forget.Top= btn_forget.Top Then
		panel_forget.SetLayoutAnimated(500,panel_forget.Left,btn_forget.Top + btn_forget.Height,panel_forget.Width,panel_forget.Height)
	Else
		panel_forget.SetLayoutAnimated(500,panel_forget.Left,btn_forget.Top,panel_forget.Width,panel_forget.Height)
	End If
	'btn_forget.BringToFront
End Sub

Sub scrol_reg_ScrollChanged(Position As Int)
	
End Sub

Sub txt_forget_FocusChanged (HasFocus As Boolean)
	If HasFocus=False Then panel_forget.Top=btn_forget.Top
End Sub

Sub btn_send_forgt_Click
	DateTime.DateFormat="yyyy_MM_dd"
	If File.Exists(myLibrary.rute,DateTime.Date(DateTime.Now))=False Then
		File.WriteString(myLibrary.rute,DateTime.Date(DateTime.Now),1)
	End If
	
	Dim count_sms As Int=File.ReadString(myLibrary.rute,DateTime.Date(DateTime.Now))
		Log("count_sms:" & count_sms)
		If count_sms <= 3 Then
			
					
						#region sms
						Dim phone_number As String=txt_forget.Text.Trim
						If phone_number.SubString2(0,3).Contains("09")=True Then	'شماره همراه
							If phone_number.SubString2(0,3).CompareTo("091")=0 Or phone_number.SubString2(0,3).CompareTo("090")=0 Then
									sender_sms=Starter.sender_sms(2)
							Else
									sender_sms=Starter.sender_sms(1)
							End If
							
							send_function_sms(Array As String("user",username,"pass",password,"op","send","from",sender_sms,"to",phone_number.Trim,"function_panel",key_software))
						
						
						Else  if phone_number.Contains("@")=True Then	'ایمیل
							
							send_function_sms(Array As String("user",username,"pass",password,"op","send","from",sender_sms,"email",phone_number.Trim,"function_panel",key_software))
							
						Else
							ToastMessageShow("ایمیل یا شماره تماس را به صورت صحیح وارد کنید",True)
						End If
						#end region
		Else
				ToastMessageShow("محدودیت در ارسال روزانه.متاسفانه شما امروز بیشتر از 3 بار سعی در ارسال کد کردید",True)
		End If
	
	
	
End Sub