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
	Public id_user As Int
	Public is_admin As Boolean=False
End Sub

Sub Globals
	Dim piccaso As Hitex_Picasso
	Dim mytoastMessage As MyToastMessageShow
	Dim effect_btn1 As RippleView
	Private progress_spot As SpotsDialog
	Dim sv ,sv2 As CustomListView
	Dim mony As money
	Dim njMenu As AnimatedSlidingMenu 'menu top
	Private scrol_main As ScrollView
	Private btn_back As Button
	Private btn_search As Button
	Private btn_share As Button
	Private btn_sabad As Button
	Private Label3 As Label
	Private footer As Label
	Private Panel1 As Panel
	Private sv_panel As Panel
	Private lbl_code As Label
	Private lbl_date As Label
	Private lbl_price As Label
	Private lbl_stat As Label
	Private panel_history As Panel
	Dim toman As money
	
	Private lbl_name As Label
	Dim toast As TatyToast
	Private img_kala As ImageView
	Private Label2 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scrol_layout")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	connector.Initialize2(Me)
	server_mysql=Starter.server_mysql
	
	
	function. initialize_spotdialog(progress_spot)
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF13879A,Colors.Red)
	mony.Initialize
	effect_btn1.Initialize(footer,Colors.White,400,False)
	
	Dim bt As BitmapDrawable
	bt.Initialize(LoadBitmap(File.DirAssets,"filter2.png"))
	btn_back.Background=bt
	Label3.Text="تاریخچه همه سفارشات"
	
	footer.Visible=False
	scrol_main.SetLayout(scrol_main.Left,scrol_main.Top,scrol_main.Width,100%y-(Panel1.Height))
	
	initialize_menu
	
	sv.Initialize(Me,"sv1",0xffE0DDDD,6dip)
	sv2.Initialize(Me,"sv2",0xffE0DDDD,6dip)
	
	'panel_history.Panel.Height=100%y-(Panel1.Height)
	scrol_main.Visible=False
	panel_history.Visible=True
	panel_history.AddView(sv.AsView,0,0,panel_history.Width,panel_history.Height)
	
	
	'	myfont=Starter.font_body

'**********************************************
	'	Dim lst_user As List=File.ReadList(myLibrary.rute,Starter.filename_user)
'	id_user=lst_user.Get(3)
	connector.progress_circle_initialize(Activity)
	connector.progress_circle_visible(False)
	Log("id_user" & id_user)
	get_all_order(id_user)

End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub footer_Click
	
End Sub

Sub initialize_menu
	
	Dim myfont As Typeface=Starter.font_body
	njMenu.Initialize(Activity, Me, "", "AnimatedMenu", "T", Panel1.Height,50%x, Colors.White,myfont,14,"right",Null)
End Sub

Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "get_time"

			Case "get_all_order"
				sv.Clear
				If records.Size > 0 Then
						
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						Dim p1 As Panel
						p1.Initialize("")
						p1.LoadLayout("sv_history_order")
						sv.Add(p1,sv_panel.Height,map1.Get("order_id")  )'& ";" & map1.Get("status")
							
						Dim stat_str As String
						Dim rich1 As RichString
						Dim stat_color As Int
						Dim stat As Int=map1.Get("status")
						Select stat
							Case 1
								stat_str="وضعیت سفارش: " &  "{C}" & "پرداخت نشده" & "{C}"
								stat_color=0xFFEB1045
							Case 2
								stat_str="وضعیت سفارش: " &  "{C}" & "پرداخت شده و در حال بررسی" & "{C}"
								stat_color=0xFF09477F
							Case 3
								stat_str="وضعیت سفارش: " &  "{C}" & "ارسال شد" & "{C}"
								stat_color=0xFF135504
							Case 4
								stat_str="وضعیت سفارش: " &  "{C}" & "پرداخت در محل" & "{C}"
								stat_color=0xFFF89F0D
						End Select
						rich1.Initialize(stat_str)
						rich1.Color2(stat_color,"{C}")
						lbl_stat.Text=rich1
							
						Dim code_rah As String=map1.Get("code_rahgiri")
						If code_rah.Length > 2 Then	lbl_code.Text="پیگیری: " & code_rah
							
						lbl_price.Text="مبلغ کل: " & mony.number(map1.Get("amount_price")) & " تومان"
							
						Dim time_reg() As String
						time_reg=Regex.Split(" ",map1.Get("updated_at"))
							
						Dim date() As String=Regex.Split("-",time_reg(0))
							
						Dim clock As String=time_reg(1)
						Log("date:" & time_reg(0) )
						Log("clock:" & time_reg(1) )
								
						Dim daye_per As PersianDate
						lbl_date.Text="تاریخ سفارش: " & daye_per.getDate(date(0),date(1),date(2),"/")
					Next
				End If
					
				
				progress_spot.DisMissDialog
					
			Case "update_order_pay"
				Activity.Finish
				ToastMessageShow("با موفقیت انجام شد",True)
			
			Case "get_order_detils"
				panel_history.RemoveAllViews
				panel_history.AddView(sv2.AsView,0,0,panel_history.Width,panel_history.Height)
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						Dim p1 As Panel
						p1.Initialize("")
						p1.LoadLayout("sv_sabad")
						sv2.Add(p1,140dip,map1.Get("id"))
						lbl_name.Text=map1.Get("name_kala")
						
						Dim url_pic As String=map1.Get("pic")
						
						If url_pic.Trim.Contains("http://")=False Then
							url_pic=Starter.dir_root_image_file_thumnail & url_pic
						Else
							piccaso.Load("http",url_pic).SkipMemoryCache.Into(img_kala)
						End If
						
						lbl_price.Text=toman.number( map1.Get("price")	) & " تومان"
						Label2.Text=map1.Get("quality")
					Next
				End If
		
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






Sub get_all_order(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`=${id} ORDER BY  `id` DESC "$,"get_all_order","")
End Sub

Sub get_pardakhti_order(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`=${id} AND `order_payment`.`status` =2 ORDER BY  `id` DESC "$,"get_all_order","")
End Sub

Sub get_not_pardakht_order(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`=${id} AND `order_payment`.`status` =1 ORDER BY  `id` DESC "$,"get_all_order","")
End Sub

Sub get_send_order(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`=${id} AND `order_payment`.`status` =3 ORDER BY  `id` DESC "$,"get_all_order","")
End Sub

Sub get_pay_location(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT `order_payment`.*,`order`.`user_id` FROM `order` inner join `order_payment` on `order`.`id`=`order_payment`.`order_id` WHERE `user_id`=${id} AND `order_payment`.`status` =4 ORDER BY  `id` DESC "$,"get_all_order","")
End Sub

Sub get_order_detils(id As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT DISTINCT  `kala`.`id` ,  `kala`.`name_kala`,`kala`.`pic` ,  `order_detail`. * ,  `order`.`user_id`
FROM  `kala` 
LEFT JOIN  `order_detail` ON  `kala`.`id` =  `order_detail`.`code_kala` 
LEFT JOIN  `order` ON  `order_detail`.`order_id` =  `order`.`id` 
WHERE  `order_detail`.`order_id` =${id}
ORDER BY  `order`.`id` DESC 
LIMIT 0 , 30
 "$,"get_order_detils","")
End Sub

Sub btn_back_Click
	     njMenu.AddItem(Null, "همه سفارشات", Colors.Black, Colors.White, 1)
		 njMenu.AddItem(Null, "سفارشات در حال بررسی", Colors.Black, Colors.White, 2)
		  njMenu.AddItem(Null, "سفارشات پرداخت نشده", Colors.Black, Colors.White, 3)
		 njMenu.AddItem(Null, "سفارشات ارسال شده", Colors.Black, Colors.White, 4)
	njMenu.OpenMenu("Flip")	'	Cascade		
End Sub

Sub AnimatedMenu_Click(SelectedItem As Object)
	Select SelectedItem
	
		Case 1:
			get_all_order(id_user)
			Label3.Text="تاریخچه همه سفارشات"
		Case 2:
			get_pardakhti_order(id_user)
			Label3.Text="سفارشات در حال بررسی"
			
		Case 3:	
			get_not_pardakht_order(id_user)
			Label3.Text="سفارشات پرداخت نشده"
		Case 4:	
			get_send_order(id_user)
			Label3.Text="سفارشات ارسال شده"
		Case 5:
			get_pay_location(id_user)
			Label3.Text="سفارشات پرداخت در محل"
	End Select

End Sub

Sub sv2_ItemClick(Position As Int, Value As Object)

End Sub

Sub sv1_ItemLongClick (Position As Int, Value As Object)
	connector.Initialize2(Me)
	Dim stu() As String=Regex.Split(";",Value)
	Dim st As Int=stu(1)
	If is_admin=True Then
		If st<>1 And st<>3 Then
			Dim  Pd As ParsMSGBOX
			Pd=function.style_msgbox("سوال","آیا این محصول را ارسال کردید؟","آره","نه","")
			Dim d As Int = Pd.Create
			If d=DialogResponse.POSITIVE Then
					
				Dim  Pd As ParsMSGBOX
				Pd=function.style_msgbox("","مطمعنی؟","اره بابا","نه شرمنده","")
				Dim d2 As Int = Pd.Create
				If d2=DialogResponse.POSITIVE Then
					connector.progress_circle_visible(True)
					connector.send_query($"UPDATE `order_payment` SET `status`=3 WHERE `order_id`=${stu(0)}"$,"update_order_pay","")
				End If
					
			End If
		End If
	End If
	
End Sub

Sub sv1_ItemClick(Position As Int, Value As Object)
	Log(Value)
	get_order_detils(Value)
'	ToastMessageShow("click",False)
	
End Sub



Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
		Return True
	End If
End Sub