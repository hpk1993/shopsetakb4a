Type=Service
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	#region Database
	Public dn As String="cp28393_setak"
	Public du As String="cp28393_hpk"
	Public dp As String="147963"'+_Q$b6#rEQHR
	#end region
	
	#region direction urls site
	Public root_site As String="http://setakteb.ir"
	
	Public dir_root_image_file_thumnail As String=root_site  & "/images/shop/product/thumnail/"
	Public dir_root_image_file_main As String=root_site  & "/images/shop/product/main/"
	
	Public dir_root_image_file_slideshow As String=root_site  & "/images/shop/slideshow-image/"
	
	Public server_mysql As String = root_site  & "/server/database/connector.php"
	
	Public dir_root_image_file_detailes_tumnail As String=root_site & "/images/shop/product-details/tumnail/"
	Public dir_root_image_file_detailes_main As String=root_site & "/images/shop/product-details/main/"
	
	Public dir_root_image_file_brand As String=root_site & "/images/shop/brand-image/"
	Public dir_root_image_file_cat As String=root_site & "/images/shop/category-image/"
	
	Public server_address_send_ForgetPassword As String=root_site & "/server/forget_password/index.php"
	Public server_get_time As String=root_site  & "/server/get_time.php"
	#End Region
	
	#region data file app
	Public filename_user As String="setak"
	Public filename_sabad As String="setak_sabad"
	#End Region
	
	
	#region پروگرس عکس برای دانلود
	Dim error_image As Bitmap=LoadBitmap(File.DirAssets,"no_image.png")
	Dim progress_image As Bitmap=LoadBitmap(File.DirAssets,"prog_img.png")

	'	error_image.Initialize(File.DirAssets,"no_image.png")
	'	progress_image.Initialize(File.DirAssets,"prog_img.png")
	#End Region
	
	#region Font
	Public Font_Awsome As Typeface=Typeface.LoadFromAssets("FontAwesome.otf")
	Public font_body As Typeface=Typeface.LoadFromAssets("iransans Bold.ttf")
	#End Region
	
	#region 	panel sms
		#region درگاه و صفحه ارسال پیامک
	Public Address_payment As String="http://padidehsms.ir/payment_setak/index.php"
	Public address_send_sms As String="http://www.padidehsms.ir/payment_setak/sms.php"
		#End Region
	Public username_sms As String="hpk"
	Public password_sms As String="147963"
	Public Api_key_sms As String="hpksoftware"
	Public sender_sms() As String=Array As String("+9810009","+98sim","+98100020400")
	#End Region
	
	Public apk_name_for_dowanload="setak.apk"
	
	Public color_base As Int=0xFF26A69A
	
	Public timer_slideshow As Int=10              *1000	'Milisecond to secound
	
	#region system Pushe
	Public Addres_send_pushe As String=root_site & "/server/pushe/index.php"
	Public ckeck_app_access As Boolean=True	'برای اینکه ورژن برنامه را بررسی کند یا نه
	#End Region
	
	#region فعال بودن ثبت نام سیستم پوش
	Public enable_push As Boolean=True
	#End Region


	#region GCM customize
	Public BoardUrl As String
	Public SenderId As String
	#End Region
	
	
	Dim rute_file_app As String=File.DirDefaultExternal
	
End Sub

Sub Service_Create
'	CallSubDelayed(FirebaseMessaging, "SubscribeToTopics")

End Sub

Sub Service_Start (StartingIntent As Intent)
	'StartService("")
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Log(Error )
	'Return False
	
End Sub

Sub Service_Destroy

End Sub


