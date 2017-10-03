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
	Public code_cat,code_sub_cat,code_brand As Int
	Public brand_name,cat_name As String
	Public bool_brand_layout,bool_cat As Boolean=False'تشخیص باز شدن یا نشدن برندها
	Public bool_porfroush,bool_news_list As Boolean=False 'تشخیص باز کردن پرفروش ها
	Public dir_root_image_file As String
	
	Public bool_search As Boolean=False
	Public query_search As String

	
End Sub

Sub Globals
	Private progress_spot As SpotsDialog
		
	Dim sv_list_cat,sv_list_brand As CustomListView
	Dim toast As TatyToast
	Private panel_main As Panel
	Private lbl_info As Label
	Private lbl_name_kala As Label
	Private img_kala As ImageView
	Private lbl_price_off As Label
	Private lbl_price As Label
	Dim toman As money
	
	Dim piccaso As Hitex_Picasso
	Dim error_image,progress_image As BitmapDrawable
	Private Label3 As Label ' titlebar
	'///////////
	Dim count_rows As Int
	Dim bo_load_rows As Boolean=True
	Private Panel1 As Panel
	Private img_mark_viesue As ImageView
	
	Dim list_kala As List
	Private lbl_badge As Label
	Private img_exist As ImageView
	

End Sub

Sub Activity_Create(FirstTime As Boolean)
	connector.Initialize2(Me)
	function. initialize_spotdialog(progress_spot)

	'//////////////////////////پروگرس عکس برای دانلود
		error_image.Initialize(LoadBitmap(File.DirAssets,"no_image.png"))
		progress_image.Initialize(LoadBitmap(File.DirAssets,"prog_img.png"))
	'//////////////////////////
	server_mysql=Starter.server_mysql
	dir_root_image_file=Starter.dir_root_image_file_main
	
	Activity.LoadLayout("category")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	sv_list_cat.Initialize(Me,"lsv_list_cat",0xFFEFEBEB,4dip)
	panel_main.AddView(sv_list_cat.AsView,0,0,panel_main.Width,panel_main.Height)
	
	
	
	
	If bool_search =True Then
		If query_search.Length > 4 Then
			result_query_search(query_search)
		Else
			ToastMessageShow("عبارت مورد جستجو کوتاه است",True)
			Activity.Finish
		End If
	else If bool_porfroush=True Then
		get_porfroush
	Else if bool_news_list=True Then
		get_news
	else If bool_cat=True Then
		get_count(code_cat)
	Else If bool_brand_layout=True Then
		get_count_brand(code_brand)
	 
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
			Case "get_list_cat","get_porfroush"
				
				If records.Size > 0 Then
					For i=0 To records.Size-1
						Dim map1 As Map=records.Get(i)
							
						Dim p1 As Panel
						p1.Initialize("")
						p1.LoadLayout("sv_list_catgory")
						sv_list_cat.Add(p1,Panel1.Height,map1.Get("id"))
						Dim apc As AppCompat
						apc.SetElevation( Panel1,6)
						lbl_name_kala.Text=map1.Get("name_kala")
						lbl_name_kala.TextSize=13
						Dim info As String=map1.Get("info")
						If info.Length > 45 Then
							lbl_info.Text=info.SubString2(0,45) & "..."
						Else
							lbl_info.Text=info
						End If
						
							
						Dim line_h As line_height
						lbl_info.TextSize=10
						line_h.Initialize(lbl_info,.75)
							
						Dim number_kala As Int=map1.Get("number")
						If number_kala <=0 Then img_exist.Visible=True
							
						Dim stut_viesue As Int=map1.Get("stut_viesue")
						If stut_viesue=1 Then
							img_mark_viesue.Visible=True
							Dim strick As RichString
							Dim price_off_strick As String=toman.number(map1.Get("price")) & " تومان"
							strick.Initialize(price_off_strick)
							strick.Strikethrough(0,price_off_strick.Length)
							lbl_price_off.Text=strick
							lbl_price.Text=toman.number(map1.Get("price_off")) & " تومان"
						Else
							img_mark_viesue.Visible=False
							lbl_price_off.Text=""
							lbl_price.Text=toman.number(map1.Get("price")) & " تومان"
						End If
							
						lbl_price_off.TextSize=10
						lbl_price.TextSize=10
							
							#region تشخیص آدرس عکس
						Dim url_pic As String=map1.Get("pic")
						If url_pic.Trim.Contains("http://")=False Then
							url_pic=dir_root_image_file & map1.Get("pic")
						End If
							#end region
							
	
						piccaso.Load("http",url_pic).Resize(img_kala.Width,img_kala.Height).SkipMemoryCache.Into(img_kala)
						If bool_brand_layout=True Then
							Label3.Text=brand_name
						Else
							Label3.Text=cat_name
						End If
					Next
					progress_spot.DisMissDialog
					font_label
					bo_load_rows=True
				Else
					progress_spot.DisMissDialog
							
					toast.Initialize("محصولی درج نشده است",toast.LENGTH_LONG,toast.ERROR)
					Activity.Finish
				End If
					
			Case "get_count"
				count_rows=records.Size
				get_list_cat(code_cat,0,10)
'				progress_spot.DisMissDialog
					
			Case "get_count_brand"
				count_rows=records.Size
				get_list_brand(code_brand,0,10)
'				progress_spot.DisMissDialog
							
			
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







Sub lsv_list_cat_ItemClick(Position As Int, Value As Object)
	info_kala.code_kala=Value
	StartActivity(info_kala)
End Sub

Sub lsv_list_cat_Position(Position As Int)
		If Position + sv_list_cat.Height >= sv_list_cat.PanelHeight  Then
			'Label3.Text=sv_list_cat.GetSize & "/" & count_rows
			If sv_list_cat.GetSize < count_rows And bo_load_rows=True Then 
					bo_load_rows=False
					get_list_cat(code_cat,sv_list_cat.GetSize,sv_list_cat.GetSize+3)
			End If
		End If
		
	
End Sub



Sub get_list_cat(code As Int,begin As Int,ends As Int)
	progress_spot.ShowDialog
	connector.send_query($"
SELECT `kala` . * ,  `viesue`.`price_off` ,  `viesue`.`code_kala` 
FROM  `kala` 
LEFT JOIN `viesue`
ON `kala`.`id`=`viesue`.`code_kala`
WHERE  `code_cat` 
IN (

SELECT  `category`.`id` 
FROM  `category` 
WHERE (
`category`.`id` =${code}
AND  `category`.`parent_id` =0
)
OR (
`category`.`parent_id` =${code}
)
)
and `show_kala`=1 ORDER BY  `kala`.`number` DESC LIMIT ${begin} , ${ends} "$,"get_list_cat","")

End Sub

Sub get_list_brand(code As Int,begin As Int,ends As Int)
	progress_spot.ShowDialog
	connector.send_query($"
SELECT `kala` . * ,  `viesue`.`price_off` ,  `viesue`.`code_kala`
FROM `kala`
LEFT JOIN `viesue`
ON `kala`.`id`=`viesue`.`code_kala`
where `code_brand`=${code} and `show_kala`=1  ORDER BY `kala`.`number` desc LIMIT ${begin} , ${ends} "$,"get_list_cat","")
End Sub


'*******************************
Sub get_count(code As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `kala` where `code_cat`=${code} and `show_kala`=1 ORDER BY `id`"$,"get_count","")
End Sub

Sub get_porfroush
	progress_spot.ShowDialog
	connector.send_query($"
SELECT DISTINCT `kala`.*,`por_froush`.`code_kala` as پرفروش,`viesue`.`code_kala`,`viesue`.`price_off`
FROM  `kala` 
INNER JOIN  `por_froush` ON  `kala`.`id` =  `por_froush`.`code_kala` 
LEFT JOIN  `viesue` ON  `kala`.`id` =  `viesue`.`code_kala`
where `kala`.`id` =  `por_froush`.`code_kala` and `show_kala`=1
ORDER BY  `kala`.`id` DESC 
LIMIT 0 , 15"$,$"get_porfroush"$,"")
End Sub

Sub get_count_brand(code As Int)
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `kala` where `code_brand`=${code} and `show_kala`=1 ORDER BY `code_brand`"$,"get_count_brand","")
End Sub

Sub get_news
	progress_spot.ShowDialog
	connector.send_query($"Select `kala`.*,`viesue`.`price_off`,`viesue`.`code_kala` FROM `kala` LEFT JOIN `viesue` ON `kala`.`id`=`viesue`.`code_kala` where `show_kala`=1 order BY `kala`.`id` desc LIMIT 0 , 12 "$,"get_list_cat","")
End Sub  

Sub result_query_search(q As String)
	progress_spot.ShowDialog
	connector.send_query($"SELECT * FROM `kala` where (`name_kala` like '% ${q} %' and `kala`.`number` > 0) OR (`info` like '% ${q} %' and `kala`.`number` > 0) and (`show_kala`=1) ORDER BY `id` DESC"$,"get_list_cat","")
End Sub



Sub btn_back_Click
	Activity.Finish
	myLibrary.SetAnimation("file3","file4")
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

Sub font_label
	For Each view1 As View In Activity.GetAllViewsRecursive
		If view1 Is Label Then
			Dim label_font As Label
			label_font=view1
			label_font.Typeface=Starter.font_body
		End If
	Next
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
	
End Sub