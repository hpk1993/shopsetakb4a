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
	Public server_get_time As String
	Public id_user As Int	
	Public order_detiles As List
End Sub

Sub Globals
	Private progress_spot As SpotsDialog
	Dim mytoastMessage As MyToastMessageShow
	Dim effect_btn1 As RippleView
	Private time As Int
		
	Private scrol_main As ScrollView
	Private ime As IME
	Private txt_gift As EditText
	Private txt_get As EditText
	Private txt_tell As EditText
	Private txt_phone As EditText
	Private txt_address As EditText
	Private txt_post_code As EditText
	Private footer As Label
	'//////////////order
	Dim order_id As Int
	Dim code_kala As Int
	Dim number As Int
	Dim price As String
	Dim date_reg As Object
	Dim toman As money
	Dim price_kol_sabad As Int
	Dim toast As TatyToast
	Dim run_payment As Boolean=False
	Private lbl_offline As Label
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol_layout2")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	scrol_main.Panel.LoadLayout("reg_order")
	connector.Initialize2(Me)
	server_get_time=Starter.server_get_time
	server_mysql=Starter.server_mysql
	
	
	function. initialize_spotdialog(progress_spot)
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF13879A,Colors.Red)
	ime.Initialize("IME")
	ime.AddHeightChangedEvent
	effect_btn1.Initialize(footer,Colors.White,400,False)
	scrol_main.Panel.Height=100%y'txt_address.Top + txt_address.Height + 3dip
	
	get_user
End Sub

Sub IME_HeightChanged(NewHeight As Int, OldHeight As Int)
	scrol_main.Height=NewHeight - footer.Height
End Sub

Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "get_time"
				time=records.Get(0)
			Case "get_user"
				
				If records.Size > 0 Then
					Dim map1 As Map=records.Get(0)
					'txt_gift.Text=map1.Get("gift_name")
					txt_phone.Text=map1.Get("mobile")
					txt_tell.Text=map1.Get("tel")
					txt_post_code.Text=map1.Get("post_code")
					txt_address.Text=map1.Get("address")
					txt_get.Text=map1.Get("name") & " " & map1.Get("lname")
					progress_spot.DisMissDialog
				Else
					Activity.Finish
				End If
			Case "insert_order"
				
				get_id_order'@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			Case "insert_order2"
				
				get_id_order2'@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				
		
			Case "get_id_order"
				
				If records.Size > 0 Then
					Dim map1 As Map=records.Get(0)
					order_id=map1.Get("id")
				End If
					
				For i=0 To order_detiles.Size-1
					Dim tmp_arry() As String
					tmp_arry=Regex.Split(",",order_detiles.Get(i))
					code_kala =tmp_arry(0)
					number=tmp_arry(2)
					price=tmp_arry(1)
					price_kol_sabad=price_kol_sabad + (price * number)
					insert_order_detiles(order_detiles.Size,i+1)
				Next
				run_payment=True
				
				
			Case "get_id_order2"
				If records.Size > 0 Then
					Dim map1 As Map=records.Get(0)
					order_id=map1.Get("id")
				End If
				
				For i=0 To order_detiles.Size-1
					Dim tmp_arry() As String
					tmp_arry=Regex.Split(",",order_detiles.Get(i))
					code_kala =tmp_arry(0)
					number=tmp_arry(2)
					price=tmp_arry(1)
					price_kol_sabad=price_kol_sabad + (price * number)
					insert_order_detiles2(order_detiles.Size,i+1)
				Next
				
				run_payment=True
				
			
				
			Case "insert_order_detiles"
				If run_payment=True Then
					insert_order_payment
					run_payment=False
				End If
				
				
				
				
			Case "insert_order_detiles2"
				If run_payment=True Then
					insert_order_payment2
					run_payment=False
				End If
		
					
			Case "insert_order_payment"
					
				File.Delete(myLibrary.rute,Starter.filename_sabad)
					
				Dim brow As PhoneIntents
				StartActivity(brow.OpenBrowser($"${Starter.Address_payment}?a=${myLibrary.base64(price_kol_sabad*10)}&o=${myLibrary.base64(order_id)}"$))'myLibrary.base64(
				progress_spot.DisMissDialog
				Activity.Finish
			
			Case "insert_order_payment2"
				File.Delete(myLibrary.rute,Starter.filename_sabad)
				toast.Initialize("سفارش شما ثبت شد",toast.LENGTH_LONG,toast.SUCCESS)
				send_push
				
				Dim lst As List=File.ReadList(myLibrary.rute,Starter.filename_user)
				Dim http_sms As HttpJob
				http_sms.Initialize("sms_send",Me)
				Dim msgsms As String=$"سفارش شما به ثبت رسید.از شما متشکریم.کد سفارش شما :  ${order_id}"$
				http_sms.PostString(Starter.address_send_sms,$"offline_buy=true&orderid=${order_id}&userid=${id_user+100}&msg=${msgsms}&to=${lst.Get(0)}"$)
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


Sub send_push

	progress_spot.ShowDialog
	Dim http_push As HttpJob
	http_push.Initialize("send_push",Me)
	http_push.PostString(Starter.Addres_send_pushe,$"buy_user=true&price=${ toman.number(price_kol_sabad) & " تومان" }&uid=${100 + id_user}"$)
End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub JobDone(res As HttpJob)
	If res.Success=True Then
		Select res.JobName
			Case "sms_send"
				progress_spot.DisMissDialog
				
			Case "send_push"
				progress_spot.DisMissDialog
		End Select
	End If
End Sub


Sub btn_back_Click
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
		Return True
End Sub

Sub btn_sabad_Click
	
End Sub

Sub btn_share_Click
	Dim share As AriaLib
	StartActivity(share.ShareApplication(Application.PackageName,Application.LabelName))
End Sub

Sub btn_search_Click
	
End Sub


Sub get_user
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `users` where `id`='${id_user}'"$,"get_user","")
End Sub

Sub get_id_order
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `order` WHERE `user_id`=${id_user} order by `id` desc limit 1"$,"get_id_order","")
End Sub
Sub get_id_order2
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `order` WHERE `user_id`=${id_user} order by `id` desc limit 1"$,"get_id_order2","")
End Sub 

Sub insert_order(address As String,user_id As Int,customer_name As String,phone As String,tel As String,gift_name As String,email As String,timee As Int,date As String)
	progress_spot.ShowDialog
	connector.send_query($"insert into `order`(`address`,`customer_name`,`gift_name`,`tel`,`mobile`,`email`,`user_id`,`created_at`,`updated_at`) values(N'${address}',N'${customer_name}',N'${gift_name}','${tel}','${phone}','${email}',${id_user},'${date}','${date}')"$,"insert_order","")
End Sub

Sub insert_order2(address As String,user_id As Int,customer_name As String,phone As String,tel As String,gift_name As String,email As String,timee As Int,date As String)
	progress_spot.ShowDialog
	connector.send_query($"insert into `order`(`address`,`customer_name`,`gift_name`,`tel`,`mobile`,`email`,`user_id`,`created_at`,`updated_at`) values(N'${address}',N'${customer_name}',N'${gift_name}','${tel}','${phone}','${email}',${id_user},'${date}','${date}')"$,"insert_order2","")
End Sub

Sub insert_order_detiles(total As Int,curent As Int)
	progress_spot.ShowDialog
	connector.send_query($"insert into `order_detail`(`order_id`,`code_kala`,`quality`,`price`,`created_at`,`updated_at`) values(${order_id},${code_kala},${number},'${price}','${date_reg}','${date_reg}')"$,"insert_order_detiles","")
End Sub

Sub insert_order_detiles2(total As Int,curent As Int)
	progress_spot.ShowDialog
	connector.send_query($"insert into `order_detail`(`order_id`,`code_kala`,`quality`,`price`,`created_at`,`updated_at`) values(${order_id},${code_kala},${number},'${price}','${date_reg}','${date_reg}')"$,"insert_order_detiles2","")
End Sub

Sub insert_order_payment
	progress_spot.ShowDialog
	connector.send_query($"insert into `order_payment`(`order_id`,`date`,`amount_price`,`code_rahgiri`,`refnum`,`status`,`created_at`,`updated_at`) values(${order_id},${0},${price_kol_sabad},'${" "}','${" "}',1,'${date_reg}','${date_reg}')"$,"insert_order_payment","")
End Sub

Sub insert_order_payment2
	progress_spot.ShowDialog
	connector.send_query($"insert into `order_payment`(`order_id`,`date`,`amount_price`,`code_rahgiri`,`refnum`,`status`,`created_at`,`updated_at`) values(${order_id},${0},${price_kol_sabad},'${" "}','${" "}',4,'${date_reg}','${date_reg}')"$,"insert_order_payment2","")
End Sub

Sub get_time
	connector.send_query($"time"$,"get_time","")
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
		Return True
	End If
End Sub

Sub footer_Click
	txt_post_code.Text=""
	If txt_get.Text.Length > 3 Then ' And txt_gift.Text.Length > 2
			If  txt_address.Text.Length > 8 And txt_phone.Text.Length = 11 Then
				
				#region
					DateTime.TimeFormat="HH:mm:ss"
					DateTime.DateFormat="yyyy-MM-dd"
					date_reg=DateTime.Date(DateTime.Now) & " " & DateTime.Time(DateTime.Now)
				#end region
				
				insert_order(txt_address.Text.Trim,id_user,txt_get.Text.Trim,txt_phone.Text.Trim,txt_tell.Text.Trim,txt_gift.Text.Trim,"example@server.com",time,date_reg)
			Else
			If txt_phone.Text.Length < 11 Then mytoastMessage.ShowToastMessageShow("فیلد های خواسته شده را بررسی کنید,تلفن همراه 11 رقم است",2,False,True,True)
			If txt_address.Text.Length >=8  Then mytoastMessage.ShowToastMessageShow("فیلد های خواسته شده را بررسی کنید,آدرس حداقل 8 حرف",2,False,True,True)
			 	
			End If
		Else
			mytoastMessage.ShowToastMessageShow("نام دلخواه کالا و نام گیرنده را وارد کنید.حداقل 4 کاراکتر",2,False,True,True)
		End If
End Sub

Sub lbl_offline_Click
	Log("lbl_offline_Click")
	Dim  Pd As ParsMSGBOX
	Pd=function.style_msgbox("توجه","آیا مطمعن به ثبت سفارش خود هستید؟","بلی","خیر","")
	Dim i As Int = Pd.Create
	If i=DialogResponse.POSITIVE Then
		Log("lbl_offline_Click")
		txt_post_code.Text=""
		If txt_get.Text.Length > 3  Then ' And txt_gift.Text.Length > 2
			If  txt_address.Text.Length >=8 And txt_phone.Text.Length = 11 Then
					
					#region
				DateTime.TimeFormat="HH:mm:ss"
				DateTime.DateFormat="yyyy-MM-dd"
				date_reg=DateTime.Date(DateTime.Now) & " " & DateTime.Time(DateTime.Now)
					#end region
					
				insert_order2(txt_address.Text.Trim,id_user,txt_get.Text.Trim,txt_phone.Text.Trim,txt_tell.Text.Trim,txt_gift.Text.Trim,"example@server.com",time,date_reg)
			Else
				
				If txt_phone.Text.Length < 11 Then mytoastMessage.ShowToastMessageShow("فیلد های خواسته شده را بررسی کنید,تلفن همراه 11 رقم است",2,False,True,True)
				If txt_address.Text.Length >=8 Then mytoastMessage.ShowToastMessageShow("فیلد های خواسته شده را بررسی کنید,آدرس حداقل 8 حرف",2,False,True,True)
				
				
			End If
		Else
			mytoastMessage.ShowToastMessageShow("نام دلخواه کالا و نام گیرنده را وارد کنید.حداقل 4 کاراکتر",2,False,True,True)
		End If
	End If
End Sub