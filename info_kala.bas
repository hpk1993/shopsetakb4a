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
	Public code_kala As Int':code_kala=116
	Public kala_txt_info As String
	Public server_mysql As String
	

'///////////
End Sub

Sub Globals
	Dim apc As AppCompat
	Dim dir_root_image_file_detailes As String=Starter.dir_root_image_file_detailes_tumnail
	Dim mytoastMessage As MyToastMessageShow
	Private progress_spot As SpotsDialog
	Dim toman As money
	Dim piccaso As Hitex_Picasso

	Private scrol As ScrollView
	Private header As Panel
	Private footer As Label
	
	'///////////////////////هدر
	Dim btn_sabad,btn_search,btn_share,btn_back As Button
	Dim lbl_badge As Label
	Dim list_kala As List
	'/////////////////////
	
	Dim color_header As ColorDrawable
	Dim color_footer As ColorDrawable
	
	Private Panel2 As Panel
	Private Panel3 As Panel
	Private Label4 As Label
	'///////////////////ویژه
	Dim this_visue_bool As Boolean=False
	'//////////////////
	Dim height As Int
	Private Button1 As Button'//////////ادامه مطلب
	Dim error_image,progress_image As BitmapDrawable
	Dim font_ico,font_txt As Typeface
	Private btn_nazar As Button
	Private btn_fani As Button
	Private slide As Panel
	Private img_mark As ImageView
	Private lbl_name_kala,label3 As Label
	Private price_kala As String
	'//////////nazarat
	
	Dim rate As rate_star
	Private rate1 As Panel
	Private rate2 As Panel
	Private rate3 As Panel
	Private Label1 As Label
	Private Label5 As Label
	Private Label6 As Label
	Private lbl_count_nazar As Label
	Private btn_reg_nazar As Button
	'//////////////////
	Dim bool_login As Boolean=False
	Dim name_dat As String
	
	Private btn_share_kala As Button
	'///////////////////rate
	Dim val_rate1,val_rate2,val_rate3 As Int
	Dim toast As TatyToast
	'////////////////exist
	Dim exist_kala As Boolean=True
	Private img_exist As ImageView
	'slide
	Dim pa As PagerBulletAdapter
	Dim pb As PagerBullet
	Dim mapslider As List
	Private Progress_slider As ProgressBar
	Private html_info As htmltv
	Dim panel_info As Panel
	Dim panel_info_inside As Panel
	Dim webview_info As WebView
	
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	connector.Initialize2(Me)
	mapslider.Initialize
	Log("code" & code_kala)
	Try
		name_dat =Starter.filename_user
		If File.Exists(myLibrary.rute,name_dat)=True Then
			bool_login=True
		End If
		server_mysql=Starter.server_mysql
	
	
		function. initialize_spotdialog(progress_spot)
		
		'//////////////////////////پروگرس عکس برای دانلود
		error_image.Initialize(LoadBitmap(File.DirAssets,"no_image.png"))
		progress_image.Initialize(LoadBitmap(File.DirAssets,"prog_img.png"))
		'//////////////////////////
	
		'/////فونت دکمه
		font_ico=Typeface.LoadFromAssets("pack icon1.ttf")
		font_txt=Starter.font_body
	
	
		scrol.Initialize2(1000,"scrol")
		header.Initialize("")
		footer.Initialize("add_to_sabad")
		btn_sabad.Initialize("btn_sabad")
		btn_search.Initialize("btn_search")
		btn_share.Initialize("btn_share")
		btn_back.Initialize("btn_back")
	
		panel_info.Initialize("panel_info")
		panel_info_inside.Initialize("panel_info_inside")
		webview_info.Initialize("webview_info")
		
		color_header.Initialize(Starter.color_base,0)
		color_footer.Initialize(0xff66BB6A,0)'Colors.ARGB(255,6,197,243)
		
		
		Activity.AddView(header,0,0,100%x,apc.GetMaterialActionBarHeight)
		Dim height_ico As Int=header.Height
		Dim center_ver As Int=(header.Height/2)-(height_ico/2)
		apc.SetElevation(header,7)
		#region
		Dim btn_header_color As ColorDrawable
		btn_header_color.Initialize(Colors.ARGB(0,255,255,255),0)
		#region سبد خرید
		btn_sabad.Background=btn_header_color
		btn_sabad.Typeface=font_ico
		btn_sabad.Text="*"
		btn_sabad.TextSize=15
		btn_sabad.TextColor=Colors.White
		btn_sabad.Gravity=Gravity.CENTER
			
		btn_sabad.SendToBack
		'badge number
		Dim badge_bg As ColorDrawable
		badge_bg.Initialize(Colors.Red,50)
				
		lbl_badge.Initialize("lbl_badge")
		lbl_badge.TextColor=Colors.White
		lbl_badge.Background=badge_bg
		lbl_badge.TextSize=13
		lbl_badge.Text=""
		If File.Exists(myLibrary.rute,Starter.filename_sabad)=True Then
			list_kala.Initialize
			list_kala=File.ReadList(myLibrary.rute,Starter.filename_sabad)
			lbl_badge.Text=list_kala.Size
		End If
				
		lbl_badge.Typeface=font_txt
		lbl_badge.Gravity=Gravity.CENTER
		header.AddView(lbl_badge,height_ico/2+4dip,1dip,height_ico/2,height_ico/2)
		header.AddView(btn_sabad,1%x,center_ver,height_ico,height_ico)
		btn_sabad.SendToBack
		lbl_badge.BringToFront
		#end region
		#region اشتراک گذاری
		'				btn_share.Background=btn_header_color
		'				btn_share.Typeface=font_ico
		'				btn_share.Text="+"
		'				btn_share.TextSize=20
		'				btn_share.TextColor=Colors.White
		'				btn_share.Gravity=Gravity.CENTER
		'				header.AddView(btn_share,btn_sabad.Left + btn_sabad.Width + 5dip,1,50dip,50dip)
				
		#end region
			
			
		#region جستجو
		btn_search.Background=btn_header_color
		btn_search.Typeface=font_ico
		btn_search.Text="-"
		btn_search.TextSize=13
		btn_search.TextColor=Colors.White
		btn_search.Gravity=Gravity.CENTER
		'header.AddView(btn_search,btn_sabad.Left + btn_sabad.Width + 5dip,center_ver,height_ico,height_ico)
		btn_search.Visible=False
		#end region
		#region بازگشت
		btn_back.Background=btn_header_color
		btn_back.Typeface=font_ico
		btn_back.Text="/"
		btn_back.TextSize=15
		btn_back.TextColor=Colors.White
		btn_back.Gravity=Gravity.CENTER
		header.AddView(btn_back,(header.Width - height_ico) - 2dip,center_ver,height_ico,height_ico)
							
		#end region
		#end region
		Activity.AddView(footer,0,100%y-50dip,100%x,50dip)
		Activity.AddView(scrol,0,header.Height,100%x,100%y)
		
		#region webview info product
		Activity.AddView(panel_info,0,header.Height,100%x,100%y-header.Height)
		Dim bg_panel_info As ColorDrawable
		bg_panel_info.Initialize(0x6E000000,0)
		panel_info.Background=bg_panel_info
		panel_info.Visible=False
		
		panel_info.AddView(panel_info_inside,3%x,3%y,panel_info.Width-6%x,panel_info.Height-6%y)
		Dim bg_panel_info_inside As ColorDrawable
		bg_panel_info_inside.Initialize(Colors.White,20)
		panel_info_inside.Background=bg_panel_info_inside
		apc.SetElevation(panel_info_inside,7)
		panel_info_inside.AddView(webview_info,1%x,1%y,panel_info_inside.Width-2%x,panel_info_inside.Height-2%y)
		webview_info.ZoomEnabled=False
		
		#end region
		
		header.Background=color_header
		footer.Background=color_footer
		header.BringToFront
		btn_sabad.BringToFront
		footer.BringToFront
		scrol.Panel.LoadLayout("info_kala")
		lbl_name_kala.Typeface=Starter.font_body
		
		panel_info.BringToFront
		
		Dim apc As AppCompat
		apc.SetElevation(Panel2,7)
		apc.SetElevation(Panel3,7)
		apc.SetElevation(Button1,7)
		apc.SetElevation(btn_fani,7)
		apc.SetElevation(btn_nazar,7)
		apc.SetElevation(btn_reg_nazar,7)
		'		function.hide_scrollbar_horizontal(slide)
		#region  زمان بالا امدن لایوت
		If bool_login=True Then btn_reg_nazar.Visible=True
		btn_share_kala.Typeface=font_ico
		#end region
		scrol.Panel.Height=Panel3.Top + Panel3.Height + footer.Height + 5dip
			
				
	#region ///////دکمه سبد خرید
		footer.Text="(  |  " & "اضافه به سبد خرید"
		footer.Gravity=Gravity.CENTER
		footer.TextColor=Colors.White
		footer.TextSize=17
		footer.Typeface=font_ico
	#end region
	#region   /////////فونت
		btn_nazar.Typeface=font_ico
		btn_fani.Typeface=font_ico
		Label4.Typeface=font_txt
		Button1.Typeface=font_txt
	#end region
	
	
	
		Label1.Typeface=Starter.font_body
		Label5.Typeface=Starter.font_body
		Label6.Typeface=Starter.font_body
		lbl_count_nazar.Typeface=Starter.font_body
	
		'//////////////////////
		get_kala(code_kala)
		'/////////////////////

	Catch
		toast.Initialize(LastException.Message,toast.LENGTH_LONG,toast.ERROR)
	End Try
	
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



Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
	If File.Exists(myLibrary.rute,Starter.filename_sabad)=True Then
		list_kala.Initialize
		list_kala=File.ReadList(myLibrary.rute,Starter.filename_sabad)
		lbl_badge.Text=list_kala.Size
		If list_kala.Size<=0 Then lbl_badge.Visible=False
	Else
		lbl_badge.Visible=False
	End If
End Sub


Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "get_kala"
				Dim pic As String
				Log("****" & records)
				If records.Size > 0 Then
	
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						
						pic=map1.Get("pic")
							
						Dim number_kala As Int=map1.Get("number")
						If number_kala > 0 Then
							exist_kala=True
							img_exist.Bitmap=LoadBitmap(File.DirAssets,"available.png")
						Else
							exist_kala=False
							img_exist.Bitmap=LoadBitmap(File.DirAssets,"unavailabl.png")
						End If
							
						#region تشخیص آدرس عکس
						Dim url_pic As String=pic
						If url_pic.Trim.Contains("http://")=False Then
							url_pic=Starter.dir_root_image_file_thumnail & pic
						End If
						#end region
						mapslider.Add(url_pic)
						''						piccaso.Load("http",url_pic).Resize(img_slide(i).Width,img_slide(i).Height).SkipMemoryCache.Into(img_slide(i))
						'						piccaso.Load("http",url_pic).SkipMemoryCache.Into(img_slide(i))	'.Resize(img_slide(i).Width,img_slide(i).Height).CenterCrop.
					Next
					progress_spot.DisMissDialog
					'					slide.Panel.Width=img_slide(img_slide.Length-1).Left + img_slide(img_slide.Length-1).Width
				#end region
							
					lbl_name_kala.Text=map1.Get("name_kala")
					label3.Text=map1.Get("garanti")
					kala_txt_info=map1.Get("info")
					html_info.setHtmlFromString(kala_txt_info,False)
				#region //////////گرفتن اندازه لیبل	
					Dim s As StringUtils
					Dim line As line_height
					If kala_txt_info.Length < 180 Then
						Button1.Visible=False
						Label4.Text=kala_txt_info
								
						'line.Initialize(Label4,2)
						Label4.Height=myLibrary.art(Label4.Text,Label4,font_txt,14,1) +  Button1.Height
					Else
						Button1.Visible=True
						Label4.Text=kala_txt_info.SubString2(0,180) & "..."
						Label4.Height=myLibrary.art(Label4.Text,Label4,font_txt,12,1.5)
						'line.Initialize(Label4,2)
						'Label4.Height=Label4.Height+5dip
					End If
							
					height=myLibrary.art(kala_txt_info,Label4,font_txt,12,2) +  Button1.Height/2	's.MeasureMultilineTextHeight(Label4,kala_txt_info) + Button1.Height/2
						#end region
						
						#region برچسب ویژه
					Dim stus_viesue As Int=map1.Get("stut_viesue")
							
					If stus_viesue=1 Then
						img_mark.Visible=True
						price_kala=toman.number(map1.Get("price")) & " تومان"
						get_viesue(code_kala)
					Else
						footer.Text=footer.Text & "  " & toman.number(map1.Get("price")) & " تومان"
						price_kala=toman.number(map1.Get("price")) & " تومان"
						#region rate نظرات کاربران
						get_image_kala(code_kala)
						#end region
					End If
						#end region
				End If
			
			Case "get_viesue"
				Dim price_base As String=price_kala
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						price_kala=map1.Get("price_off")
					Next
					this_visue_bool=True
					footer.Text="(  |  " & "  " & price_base  & " با تخفیف " & toman.number(price_kala) & " تومان"
				End If

				get_image_kala(code_kala)
				
				
		
			Case "get_image_kala"
				Log("*****" & records)
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						pic=map1.Get("pic_kala")
						#region تشخیص آدرس عکس
							Dim url_pic As String=pic
							If url_pic.Trim.Contains("http://")=False Then
								url_pic=Starter.dir_root_image_file_detailes_main & pic
							End If
						#end region
						mapslider.Add(	url_pic	)
					Next
				End If
				#region ////////////slide
				initialize_slideshow(mapslider)
				#End Region
				
				progress_spot.DisMissDialog
			Case "get_rate"
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
							
						Dim temp_1 As Int=map1.Get("rate1")
						val_rate1=val_rate1 + temp_1
						Dim temp_2 As Int=map1.Get("rate2")
						val_rate2=val_rate2 + temp_2
						Dim temp_3 As Int=map1.Get("rate3")
						val_rate3=val_rate3 + temp_3
					Next
							
				End If
				Dim w As Int=(rate1.Width/5)-2dip
				
				rate.Initialize(rate1,Me,"",w,w,LoadBitmap(File.DirAssets,"star1.png"),LoadBitmap(File.DirAssets,"star0.png"))
				rate.show_rate(Round(val_rate1/records.Size))
		
				rate.Initialize(rate2,Me,"",w,w,LoadBitmap(File.DirAssets,"star1.png"),LoadBitmap(File.DirAssets,"star0.png"))
				rate.show_rate(Round(val_rate2/records.Size))
		
				rate.Initialize(rate3,Me,"",w,w,LoadBitmap(File.DirAssets,"star1.png"),LoadBitmap(File.DirAssets,"star0.png"))
				rate.show_rate(Round(val_rate3/records.Size))
				
				
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



Sub get_viesue(code As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT * from `viesue` where `code_kala`=${code}"$,"get_viesue","")
End Sub

Sub get_rate(code As Int)
	Log("rate ")
	progress_spot.ShowDialog
	connector.send_query($"SELECT * from `nazar` where `code_kala`=${code}"$,"get_rate","")
End Sub

Sub get_kala(code As Int)
	progress_spot.ShowDialog
'	connector.send_query($"SELECT DISTINCT * FROM `kala` INNER JOIN `image_kala` ON `kala`.`id`= `image_kala`.`code_kala` where `image_kala`.`code_kala`=${code} ORDER BY `code_kala` Desc"$,"get_kala","")
	connector.send_query($"SELECT DISTINCT * FROM `kala` where `id`=${code}"$,"get_kala","")
End Sub

Sub get_image_kala(code As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `image_kala` WHERE `code_kala`=${code}"$,"get_image_kala","")
End Sub

Sub Button1_Click
'	If Button1.Text="ادامه مطلب" Then 	
'		Label4.Height=height
'		Panel2.Height=height + Button1.Height
'		Button1.Top=Panel2.Height - Button1.Height
'		Panel3.Top=Panel2.Top + Panel2.Height + 5dip
'		scrol.Panel.Height=Panel3.Top + Panel3.Height + footer.Height + 5dip
'		Label4.Text=kala_txt_info
'		Button1.Text="بستن"
'	Else
'		Panel2.Height=160dip
'		Button1.Top=Panel2.Height - Button1.Height
'		Label4.Height=Button1.Top - Label4.Top - 7dip
'		Panel3.Top=Panel2.Top + Panel2.Height + 5dip
'		scrol.Panel.Height=Panel3.Top + Panel3.Height + footer.Height + 5dip
'		Label4.Text=kala_txt_info.SubString2(0,180) & "..."
'		Button1.Text="ادامه مطلب"
	'	End If
	panel_info.Visible=True
	Dim txt_info As String
	txt_info=kala_txt_info.Replace("width:auto","width:100%")
	txt_info= txt_info.Replace("/>",$"class="img-responsive" />"$	)
	webview_info.LoadHtml(File.ReadString(File.DirAssets,"index1.html") & txt_info	& $"</div></div></div><script src="js/bootstrap.min.js"></script></body></html>"$	 )
	
End Sub

Sub panel_info_Touch (Action As Int, X As Float, Y As Float)
	panel_info.Visible=False
End Sub
	

Sub add_to_sabad_Click()
If exist_kala=True Then
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF0BA41B,Colors.Red)
		If File.Exists(myLibrary.rute,Starter.filename_sabad)=True Then
		Dim list_kala2 As List
		Dim bol_duplicate_kala As Boolean=False	
		list_kala2.Initialize
			list_kala2=File.ReadList(myLibrary.rute,Starter.filename_sabad)
		For i=0 To list_kala2.Size-1
			If code_kala=list_kala2.Get(i) Then
				bol_duplicate_kala=True
				Exit
			End If
		Next
		If bol_duplicate_kala=False Then
			list_kala2.Add(code_kala)
			mytoastMessage.ShowToastMessageShow("کالای مورد نظر به سبد خرید اضاف شد",4,True,True,False)
				lbl_badge.Text=list_kala.Size +1
				
		Else
			mytoastMessage.ShowToastMessageShow("این کالا در سبد خرید شما موجود است",4,False,True,True)
		End If
			File.WriteList(myLibrary.rute,Starter.filename_sabad,list_kala2)
		
	Else
		Dim list_kala2 As List
		list_kala2.Initialize
		list_kala2.Add(code_kala)
			File.WriteList(myLibrary.rute,Starter.filename_sabad,list_kala2)
			lbl_badge.Visible=True
			lbl_badge.Text="1"
			
		mytoastMessage.ShowToastMessageShow("کالای مورد نظر به سبد خرید اضاف شد",4,True,True,False)
	End If
Else
		toast.Initialize("متاسفانه کالای مورد نظر به اتمام رسیده",toast.LENGTH_LONG,toast.ERROR)
End If

	Log("kala: " &  list_kala2)
End Sub

Sub btn_sabad_Click()
	StartActivity(sabad)
End Sub

Sub btn_share_Click()
	Dim share As AriaLib
	StartActivity(share.ShareApplication(Application.PackageName,Application.LabelName))
End Sub

#region searcheble
If Result.SearchState=Result.SS_RESULT_SUCCESS Then
			ToastMessageShow(Result.SearchResult,True)
			Result.SearchState=Result.SS_NONE
End If
	
Sub btn_search_Click
	RequestSearchBar
End Sub
	
Sub RequestSearchBar
	 Dim ref As Reflector
	 ref.Target = ref.GetActivity
	 ref.RunPublicmethod("onSearchRequested", Null, Null)
End Sub
#end region

Sub btn_back_Click()
	If panel_info.Visible=True Then
		panel_info.Visible=False
	Else
		Activity.Finish
		myLibrary.SetAnimation("file3","file4")
	End If
End Sub

Sub scrol_ScrollChanged(Position As Int)
	Dim h As Int=255-Position
'	If h <=15 Then 
'		btn_sabad.Visible=False
'		btn_share.Visible=False
'		btn_search.Visible=False
'		btn_back.Visible=False
'	Else
'		btn_sabad.Visible=True
'		btn_share.Visible=True
'		btn_search.Visible=True
'		btn_back.Visible=True
'	End If
	If h <= 255 And h >= 0 Then 
'		color_header.Initialize(Colors.ARGB(h,29,181,202),0)
'		header.Background=color_header
	End If
End Sub

Sub btn_reg_nazar_Click
	nazar.code_kala=code_kala
	StartActivity(nazar)
End Sub

Sub btn_share_kala_Click
	Dim share As Intent
	If this_visue_bool=True Then price_kala=toman.number(price_kala) & " تومان" & CRLF & "🏃🏻" & "پیشنهاد ویژه به مدت محدود" & "🎯"
	Dim mytext As String=lbl_name_kala.Text &  CRLF & CRLF & "💶 " &  price_kala  & CRLF & CRLF & "برای ثبت سفارش به لینک زیر مراجعه کنید 👇🏻." & CRLF & CRLF & "🆔 " & Starter.root_site & "/product/detail/" & code_kala & CRLF & CRLF & "لینک دانلود فروشگاه : "  & CRLF & Starter.root_site & "/android/" & Starter.apk_name_for_dowanload'CRLF & kala_txt_info &
	share.Initialize(share.ACTION_SEND,"")
	share.SetType("text/plain")
	share.PutExtra("android.intent.extra.TEXT", mytext)
	share.WrapAsIntentChooser("اشتراک با")
	StartActivity(share)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If panel_info.Visible=True Then
			panel_info.Visible=False
		Else
			Activity.Finish
			myLibrary.SetAnimation("file3","file4")
		End If
	
		Return True
	End If
End Sub

Sub btn_nazar_Click
	show_nazar.code_kala=code_kala
	StartActivity(show_nazar)
End Sub

Sub btn_fani_Click
	show_nazar.bool_propertis=True
	show_nazar.code_kala=code_kala
	StartActivity(show_nazar)
End Sub

#Region Slider
Sub initialize_slideshow(list_url As List)
	Dim p_img(list_url.Size) As ImageView'//////////////////////////
	pa.Initialize
	For i = 0 To list_url.Size-1
		p_img(i).Initialize("")
		Target1(list_url.Get(i),p_img(i))
		pa.AddPage(p_img(i)) 'map_info_baner.GetkeyAt(i)
	Next
	
	Progress_slider.Visible=False
	
	pb.Initialize(pa,"pb1")
	'pb.setDotsColor(Colors.Red,Colors.Black)
	pb.Transition = pb.ZOOM_OUT_SIDE
	slide.AddView(pb,0,0,100%x,slide.Height)
	'	Dim panel_pos As Panel=pa.GetPageObject(0)
	'	timer_slide.Initialize("timer_slide",Starter.timer_slideshow)
	'	timer_slide.Enabled=True

	#region rate نظرات کاربران
			get_rate(code_kala)
	#end region
End Sub

Sub timer_slide_Tick
'	Dim count As Int=pa.Count-1
'	pb.GotoPage(index_slider)
'	If index_slider >=count Then
'		index_slider=0
'		
'	Else
'		index_slider=index_slider+1
'	End If

End Sub

Sub Target1(img As String, Tag As Object)
	Dim image_view As ImageView=Tag
	'	Glide.Load("http", img ).AsBitmap.SkipMemoryCache(True).Into(image_view)
	Log("url: " & img)
	piccaso.Load("http",img).SkipMemoryCache.Into(image_view)
	'.Resize(image_view.Width,image_view.Height).CenterInside
End Sub

#end Region