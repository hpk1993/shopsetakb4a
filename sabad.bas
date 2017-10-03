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
	Public dir_root_image_file As String

	
End Sub

Sub Globals
	Dim toman As money
	Dim lsv_sabad As CustomListView
	Private progress_spot As SpotsDialog
	Dim piccaso As Hitex_Picasso
	Dim error_image,progress_image As BitmapDrawable
	
	Private panel_main As Panel
	Dim mytoastMessage As MyToastMessageShow
	'//////////////
	Dim time As Long
	Private img_kala As ImageView
	Private lbl_name As Label
	Private lbl_price As Label
	Private txt_tedad As EditText
	Private Panel1 As Panel
	'/////////////////
	Dim keyboard As IME
	Dim pos_panel As Int

	Dim list_kala As List	
	'/////////////////
	
	Private Label3 As Label
	Private Label2 As Label
	Private lbl_fi As Button
	
	'//////////////////mnu_del
	Dim timer1 As Timer
	Private panel_setting As Panel
	Dim code_kala As Int
	Dim apc As AppCompat
	
	Private btn_sabad As Button
	Private btn_share As Button
	Private btn_search As Button
	
	Dim toast As TatyToast
	'///////////////////////////////////////flat button
	
	Private mLastScrollY As Int = 0
	'//////////////////////////////////////////
	Private spin_tedad As Spinner
	Private lbl_price_kol As Label
	'//////////////
	Dim count_item_sabad As Int=0
	Private titlebar_price_kol As Label
	Private lbl_badge As Label
	Private btn_buy As Button
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("category")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	connector.Initialize2(Me)
	btn_buy.Visible=True
	titlebar_price_kol.Visible=True
	
	Dim bg_titlebar_price_kol As ColorDrawable
	bg_titlebar_price_kol.Initialize(Starter.color_base,0)
	titlebar_price_kol.Background=bg_titlebar_price_kol
	apc.SetElevation(titlebar_price_kol,7)
	apc.SetElevation(panel_setting,7)
	panel_main.SetLayout(0,titlebar_price_kol.Top + titlebar_price_kol.Height,panel_main.Width,100%y-(Panel1.Height+titlebar_price_kol.Height))
	
	
	
	
	
	'//////////////////////////پروگرس عکس برای دانلود
	error_image.Initialize(LoadBitmap(File.DirAssets,"no_image.png"))
	progress_image.Initialize(LoadBitmap(File.DirAssets,"prog_img.png"))
	'//////////////////////////
	
	server_get_time=Starter.server_get_time
	server_mysql=Starter.server_mysql
	dir_root_image_file=Starter.dir_root_image_file_thumnail
	#region
	keyboard.Initialize("keyboard_t")
	keyboard.AddHeightChangedEvent
	#end region	
	#region private this latout
		
	Label3.Text=""
	Dim btn_bitmap As BitmapDrawable
	btn_bitmap.Initialize(LoadBitmap(File.DirAssets,"delete.png"))
	btn_bitmap.Gravity=Gravity.FILL
	btn_sabad.Background=btn_bitmap
	btn_bitmap.Initialize(LoadBitmap(File.DirAssets,"history.png"))
	btn_share.Background=btn_bitmap
	btn_search.Visible=False
	#end region
	timer1.Initialize("timer1",2500)
	timer1.Enabled=False
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF13879A,Colors.Red)
	function. initialize_spotdialog(progress_spot)
	lsv_sabad.Initialize(Me,"sv_sabad_me",0xFFF6F1F1,6dip)
	panel_main.AddView(lsv_sabad.AsView,0,0,panel_main.Width,panel_main.Height-btn_buy.Height-2dip)
	panel_main.Color=0xFFB6B3B6
	If File.Exists(myLibrary.rute,Starter.filename_sabad)=True Then
		list_kala.Initialize
		list_kala=File.ReadList(myLibrary.rute,Starter.filename_sabad)
		Log("sabad: " & list_kala)
		If list_kala.Size > 0 Then
			For i=0 To list_kala.Size-1
				Log("kala= " & list_kala.Get(i))
				
				'get_sabad'(list_kala.Get(i))
			Next
			get_sabad
		End If
	Else
		titlebar_price_kol.Text="سبد خرید شما خالی است"
		btn_buy.Enabled=False
	End If
		
					
	
End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
	If File.Exists(myLibrary.rute,Starter.filename_sabad)=False Then
		 lsv_sabad.Clear
		' list_kala.Clear
		 titlebar_price_kol.Text="سبد خرید شما خالی است"
		btn_buy.Enabled=False
		lbl_badge.Visible=False
	End If
	If File.Exists(myLibrary.rute,Starter.filename_sabad)=True Then
		list_kala.Initialize
		list_kala=File.ReadList(myLibrary.rute,Starter.filename_sabad)
		lbl_badge.Text=list_kala.Size
		If list_kala.Size<=0 Then lbl_badge.Visible=False
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "get_sabad"
				#region
				If records.Size > 0 Then
					
					Dim btn_fi(records.Size) As Button
					Dim spin_tedad2(records.Size) As ACSpinner
						
					For i=0 To records.Size-1
						'i=count_item_sabad
						
						Dim map1 As Map=records.Get(i)
						Dim p1 As Panel
						p1.Initialize("")
						p1.LoadLayout("sv_sabad")
						apc.SetElevation(Panel1,8)
						lsv_sabad.Add(p1,Panel1.Height,map1.Get("id"))
						lbl_name.Text=map1.Get("name_kala")
							
						Dim price_of As Int
						Dim is_visue As Int=map1.Get("stut_viesue")
						If is_visue=1 Then
							price_of=map1.Get("price_off")
						Else
							price_of=map1.Get("price")
						End If
							
						txt_tedad.Tag=map1.Get("id") '& ";" & price_of
						lbl_price.Text="" & toman.number(price_of) & " تومان"
						lbl_price.Tag=price_of
						lbl_price.Typeface=Starter.font_body
							
						spin_tedad2(i).Initialize("spin_tedad2")
						
						Dim number_of As Int=map1.Get("number")
						For j=1 To number_of
							spin_tedad2(i).Add(myLibrary.CovertEnglish2Persian(j))
						Next
						If number_of<=0 Then spin_tedad2(i).Add(myLibrary.CovertEnglish2Persian(0))
							
						spin_tedad2(i).TextColor=Colors.Black
						spin_tedad2(i).SelectedIndex=0
						spin_tedad2(i).DropdownBackgroundColor=Colors.White
						spin_tedad2(i).DropdownTextColor=Colors.DarkGray
						spin_tedad2(i).SetVisibleAnimated(500,True)
						spin_tedad2(i).TextSize=16
					
						spin_tedad2(i).Tag=count_item_sabad
							
						Panel1.AddView(spin_tedad2(i),spin_tedad.Left,spin_tedad.Top,spin_tedad.Width,spin_tedad.Height)
							#region برای دکمه محاسه فی قیمت	
						Dim color_fi As ColorDrawable
						color_fi.Initialize2(Colors.Transparent,50,2,Colors.Black)
						btn_fi(i).Initialize("btn_fi")
						btn_fi(i).Text="!"
						btn_fi(i).Background=color_fi
						btn_fi(i).TextSize=13
						btn_fi(i).TextColor=Colors.Blue
						Try
							btn_fi(i).Tag=myLibrary.CovertPersian2English(spin_tedad2(i).SelectedItem) * price_of
						Catch
							toast.Initialize(LastException.Message,toast.LENGTH_LONG,toast.WARNING)
						End Try
								
						Panel1.AddView(btn_fi(i),1,Panel1.Height-lbl_fi.Height,lbl_fi.Width,lbl_fi.Height)
						btn_fi(i).Visible=False
						lbl_price_kol.Tag=myLibrary.CovertPersian2English(spin_tedad2(i).SelectedItem) * price_of
						lbl_price_kol.Text="قیمت این محصول : " & toman.number(myLibrary.CovertPersian2English(spin_tedad2(i).SelectedItem) * price_of) & " تومان"
						lbl_price_kol.Typeface=Starter.font_body
							#end region
						progress_spot.DisMissDialog
							
							
							#region تشخیص آدرس عکس
						Dim url_pic As String=map1.Get("pic")
						If url_pic.Trim.Contains("http://")=False Then
							url_pic=dir_root_image_file & map1.Get("pic")
						End If
							#end region
						piccaso.Load("http",url_pic).Resize(img_kala.Width,img_kala.Height).SkipMemoryCache.Into(img_kala)
						progress_spot.DisMissDialog
						count_item_sabad=count_item_sabad+1
					Next
					
					get_kol_price
				End If
					#end region	
					
					
			
		
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





Sub get_kol_price
	Dim kol_price As Int=0
	Try			
	For i=0 To lsv_sabad.GetSize-1
		
		Dim pnl As Panel=lsv_sabad.GetPanel(i).GetView(0)
		Dim btn As Button= pnl.GetView(10)
			Dim pr As Int=btn.Tag
			kol_price=kol_price + pr
	Next'base for
	titlebar_price_kol.Text="مجموع سبد خرید: " & toman.number(kol_price) & " تومان"
	
		'Label3.Typeface=Starter.font_body
	Catch
		toast.Initialize(LastException.Message,toast.LENGTH_LONG,toast.ERROR)
	End Try
End Sub

Sub sv_sabad_me_ItemClick(Position As Int, Value As Object)
	pos_panel=Position
	code_kala=Value
	panel_setting.Visible=True
	panel_setting.SetLayoutAnimated(1000,100%x-panel_setting.Width,panel_setting.Top,panel_setting.Width,panel_setting.Height)
	panel_setting.BringToFront
	timer1.Enabled=True
End Sub


Sub timer1_Tick
	timer1.Enabled=False
	panel_setting.SetLayoutAnimated(1000,100%x+panel_setting.Width,panel_setting.Top,panel_setting.Width,panel_setting.Height)
End Sub




Sub get_sabad'(code As Int)
	progress_spot.ShowDialog
		Dim values_id As String
		For z=0 To list_kala.Size-1
			If z=0 Then  values_id=list_kala.Get(z)
			values_id=values_id & "," & list_kala.Get(z)
		Next
	connector.send_query($"SELECT `kala` . * ,  `viesue`.`price_off` ,  `viesue`.`code_kala`
FROM `kala`
LEFT JOIN `viesue`
ON `kala`.`id`=`viesue`.`code_kala`
where `kala`.`id` in (${values_id})"$,"get_sabad","")
End Sub

Sub get_time
	connector.send_query($"time"$,"get_time","")
End Sub


'//////////////////////////////////////////////////////////////////
Sub btn_buy_LongClick
	Log("Long click")
End Sub

Sub btn_buy_Click
	'	If btn_buy.IsShowing Then
	If File.Exists(myLibrary.rute,Starter.filename_user) Then
		Dim lst As List=File.ReadList(myLibrary.rute,Starter.filename_user)
		
		
			
			#region 
		order.id_user=lst.Get(3)
		order.order_detiles=get_factor
		StartActivity(order)

			#end region
	Else 'اگر عضو نبود
		Dim  Pd As ParsMSGBOX
		Pd=function.style_msgbox("توجه","شما وارد حساب کاربری خود نشدید.اگر عضو نیستید میتوانید رایگان ثبت نام کنید","ورد یا ثبت نام","","انصراف")
		Dim r  As Int = Pd.Create
		If r=DialogResponse.POSITIVE Then
			StartActivity(reg_login)
		End If
		
	End If
		
	'	End If
End Sub


'///////////////////////////////////////////////////////////////

Sub btn_del_kala_sabad_Click
	Dim tmp As String=code_kala
	list_kala.Initialize
	list_kala=File.ReadList(myLibrary.rute,Starter.filename_sabad)
	For i=0 To list_kala.Size-1
		Dim select_kala As String=list_kala.Get(i)
		If tmp.CompareTo(select_kala)=0 Then 
			list_kala.RemoveAt(i)
			lsv_sabad.RemoveAt(pos_panel)
			File.WriteList(myLibrary.rute,Starter.filename_sabad,list_kala)
				list_kala.Clear
			list_kala=File.ReadList(myLibrary.rute,Starter.filename_sabad)
				lbl_badge.Text=list_kala.Size
				Exit
		End If
	Next
	panel_setting.SetLayoutAnimated(1000,100%x+panel_setting.Width,panel_setting.Top,panel_setting.Width,panel_setting.Height)
End Sub

Sub btn_show_kala_Click
	info_kala.code_kala=code_kala
	StartActivity(info_kala)
	panel_setting.SetLayoutAnimated(1000,100%x+panel_setting.Width,panel_setting.Top,panel_setting.Width,panel_setting.Height)
End Sub


Sub btn_share_Click
	If File.Exists(myLibrary.rute,Starter.filename_user) Then
		Dim lst_user As List=File.ReadList(myLibrary.rute,Starter.filename_user)
		history_order.id_user=lst_user.Get(3)
		Log("id_user" & lst_user.Get(3))
		StartActivity(history_order)
	Else 'اگر عضو نبود
		Dim  Pd As ParsMSGBOX
		Pd=function.style_msgbox("توجه","شما وارد حساب کاربری خود نشدید.اگر عضو نیستید میتوانید رایگان ثبتنام کنید","ورد یا ثبت نام","","انصراف")
		Dim r  As Int = Pd.Create
		If r=DialogResponse.POSITIVE Then
			StartActivity(reg_login)
		End If
	End If
	
End Sub

Sub btn_sabad_Click
	Dim  Pd As ParsMSGBOX
	Pd=function.style_msgbox("","آیا میخواهید لیست خرید را خالی کنید؟","بلی","خیر","")
	Dim r As Int = Pd.Create
	If DialogResponse.POSITIVE=r Then
		delete_sabad
	End If
End Sub

Sub delete_sabad
		File.Delete(myLibrary.rute,Starter.filename_sabad)
	
		lsv_sabad.Clear
		titlebar_price_kol.Text="لیست خرید شما خالی است"
		mytoastMessage.ShowToastMessageShow("لیست خرید شما خالی شد",2,True,True,True)
		lbl_badge.Text="0"
	btn_buy.Enabled=False
End Sub
Sub btn_back_Click
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

Sub spin_tedad2_ItemClick (Position As Int, Value As Object)
	Dim spin As ACSpinner
	spin=Sender
	Log("id=" & spin.Tag)
	Dim pnl As Panel=lsv_sabad.GetPanel(spin.Tag).GetView(0)
	
	Dim lbl_kol As Label=pnl.GetView(8)
	Dim price_of As Label=pnl.GetView(4)
	
	Dim sum As Int=myLibrary.CovertPersian2English(spin.SelectedItem) * price_of.Tag
	lbl_kol.Text="قیمت کل : " & toman.number(sum) & " تومان"
	Log(pnl.GetView(2))
	
	Dim btn As Button=pnl.GetView(10)
	btn.Tag=sum
	get_kol_price
End Sub

Sub get_factor() As List
	Dim lst_fact As List
	lst_fact.Initialize
	For i=0 To lsv_sabad.GetSize-1
		Dim pnl As Panel=lsv_sabad.GetPanel(i).GetView(0)
			Dim id As EditText=pnl.GetView(2)
			Dim price_of As Label=pnl.GetView(4)
		Dim number_of As ACSpinner=pnl.GetView(9)
			lst_fact.Add(id.Tag & "," & price_of.Tag & "," & myLibrary.CovertPersian2English(number_of.SelectedItem))
	Next
	Return lst_fact
End Sub