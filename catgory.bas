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
	Public bool_brand_layout As Boolean=False
	

End Sub

Sub Globals
	Dim dir_root_image_file_brand As String=Starter.dir_root_image_file_brand
	Dim dir_root_image_file_cat As String=Starter.dir_root_image_file_cat
	
	Private progress_spot As SpotsDialog
	Dim piccaso As Hitex_Picasso
	Dim error_image,progress_image As BitmapDrawable
	
	Dim sv_cat,sv_brand As CustomListView
	
	Private Panel1 As Panel 'header page
	Private panel_main As Panel
	Private lbl_cat As Label
	Private img_cat As ImageView
	'//////////brand
	Private lbl_brand As Label
	Private img_brand As ImageView
	Private Panel2 As Panel

	
	Dim list_kala As List
	Private lbl_badge As Label
	
End Sub

Sub Activity_Create(FirstTime As Boolean)

	
	connector.Initialize2(Me)
	
	function. initialize_spotdialog(progress_spot)
	
	'//////////////////////////پروگرس عکس برای دانلود
		error_image.Initialize(LoadBitmap(File.DirAssets,"no_image.png"))
		progress_image.Initialize(LoadBitmap(File.DirAssets,"prog_img.png"))
	'//////////////////////////
	server_mysql=Starter.server_mysql
	Activity.LoadLayout("category")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	If bool_brand_layout=False Then
		sv_cat.Initialize(Me,"lsv_cat",0,5dip)
		panel_main.AddView(sv_cat.AsView,0,0,panel_main.Width,panel_main.Height)
		get_cat	
	Else if bool_brand_layout=True Then
		sv_brand.Initialize(Me,"lsv_brand",0,2dip)
		panel_main.AddView(sv_brand.AsView,0,0,panel_main.Width,panel_main.Height)
'		get_brand	
	End If
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

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub db_connector(records As List,tag As Object)
	Try
		Select tag
			Case "get_cat"
				
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						Dim p1 As Panel
						p1.Initialize("")
						p1.LoadLayout("sv_catgory")
						sv_cat.Add(p1,Panel1.Height,map1.Get("id") & ";" & map1.Get("parent_id") & ";" & map1.Get("name_cat"))
							
						lbl_cat.Text=map1.Get("name_cat")
						lbl_cat.Typeface=Starter.font_body
							
							#region تشخیص آدرس عکس
						Dim url_pic As String=map1.Get("pic_cat")
						If url_pic.Trim.Contains("http://")=False Then
							url_pic=dir_root_image_file_cat & map1.Get("pic_cat")
						End If

						#end region
						piccaso.Load("http",url_pic).Resize(img_cat.Width,img_cat.Height).SkipMemoryCache.Into(img_cat)
					Next
				End If
				progress_spot.DisMissDialog
			
			Case "get_brand"
				
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
						Dim p1 As Panel
						p1.Initialize("")
						p1.LoadLayout("sv_brand")
						sv_brand.Add(p1,Panel2.Height,map1.Get("id")& ";" & map1.Get("name_brand"))
						
						lbl_brand.Text=map1.Get("name_brand")
						lbl_brand.Typeface=Starter.font_body
						
						#region تشخیص آدرس عکس
						Dim url_pic As String=map1.Get("logo_brand")
						If url_pic.Trim.Contains("http://")=False Then
							url_pic=dir_root_image_file_brand & map1.Get("logo_brand")
						End If
						#end region
						piccaso.Load("http",url_pic).Resize(img_brand.Width,img_brand.Height).SkipMemoryCache.Into(img_brand)
					Next
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



Sub lsv_cat_ItemClick(Position As Int, Value As Object)
	Dim arr_temp() As String
	arr_temp=Regex.Split(";",Value)
	show_list_kala.cat_name=arr_temp(2)'نام گروه
	show_list_kala.bool_cat=True
	show_list_kala.bool_news_list=False
	show_list_kala.bool_porfroush=False
	show_list_kala.code_cat=arr_temp(0)'کد گروه
	show_list_kala.code_sub_cat=arr_temp(1)'کد زیر گروه
	StartActivity(show_list_kala)
End Sub

Sub lsv_brand_ItemClick(Position As Int, Value As Object)
	Dim arr_temp() As String
	arr_temp=Regex.Split(";",Value)
	show_list_kala.brand_name=arr_temp(1)'نام برند
	show_list_kala.bool_brand_layout=True
	show_list_kala.bool_cat=False
	show_list_kala.bool_news_list=False
	show_list_kala.bool_porfroush=False
	show_list_kala.code_brand=arr_temp(0)'کد برند
	StartActivity(show_list_kala)
End Sub



Sub get_cat
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `category` where `parent_id`=0 and `show_cat`=1 ORDER BY `id` ASC"$,"get_cat","")
End Sub
Sub get_brand
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `brand` where `show_brand`=1 ORDER BY `id` ASC"$,"","")
End Sub



Sub btn_sabad_Click()
	StartActivity(sabad)
End Sub

Sub btn_share_Click
	Dim share As AriaLib
	StartActivity(share.ShareApplication(Application.PackageName,Application.LabelName))
End Sub

Sub btn_search_Click
	
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