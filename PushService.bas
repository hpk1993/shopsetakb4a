Type=Service
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#StartAtBoot: False
#End Region

'Service module
'Version 0.9
Sub Process_Globals
	Private Num_notify As Int: Num_notify=0
   

End Sub
Sub Service_Create
	
End Sub

Sub Service_Start (StartingIntent As Intent)
	Select StartingIntent.Action
		Case "com.google.android.c2dm.intent.REGISTRATION"
			Log("REGISTRATION")
			HandleRegistrationResult(StartingIntent)
		Case "com.google.android.c2dm.intent.RECEIVE"
			Log("recived")
			MessageArrived(StartingIntent)
	End Select
End Sub

Sub MessageArrived (Intent As Intent)
	Dim From, CollapseKey, Data, Msg As String 'ignore
	If Intent.HasExtra("from") Then From = Intent.GetExtra("from")
	If Intent.HasExtra("data") Then Data = Intent.GetExtra("data")
		'Dim dd As String=Intent.ExtrasToString
	Log(Data & "//" & From)
	If Intent.HasExtra("collapse_key") Then CollapseKey = Intent.GetExtra("collapse_key")
	   
	If Intent.HasExtra("msg_reg") Then 
		Log("msg_reg")
	   Dim n As Notification
	   n.Initialize
	   n.Light = True
	   n.Vibrate = True
	   n.Sound = True
	   n.AutoCancel=True
	   Data = Intent.GetExtra("msg_reg")
	   n.Icon = "push1"
		n.SetInfo(Application.LabelName, Data, Main) 'Change Main to "" if this code is in the main module.
	   n.notify(Num_notify)
	   Num_notify=Num_notify+1
	   
	Else	'sent msg from server push
		
		Log("get data: " & Data)
			Dim nb As NotificationBuilder
			'Dim nbbigpicturestyle As NotificationBigPictureStyle
			
			nb.Initialize
			nb.setCustomLight(Colors.red,50,50)
			nb.DefaultVibrate=True
			nb.DefaultSound=True
			nb.Autocancel=True
		If Data.IndexOf(";") <> -1 Then
			Log(";")
			Dim arry_push_data() As String
			arry_push_data=Regex.Split(";",Data)
			
			nb.smallIcon="push1"
			nb.Ticker="محصول جدید "
			nb.ContentTitle=Application.LabelName
			nb.contentText=arry_push_data(0)'title project
			nb.contentInfo="(جدید)"
			#region
			'nb.UsesChrono=True
			'nb.setProgress(100,40,False)
		
'			Dim bigPicture As Bitmap
'			bigPicture.Initialize(File.DirAssets,"1.png")
'			
'			nbbigpicturestyle.Initialize
'			nbbigpicturestyle.BigContentTitle="BigPicture"
'			nbbigpicturestyle.SummaryText="مشاهده"
'			nbbigpicturestyle.BigPicture=bigPicture 
'			nb.SetStyle(nbbigpicturestyle)
			#end region
			Dim codep As Int=arry_push_data(1).Trim
			If codep > 0  Then
				nb.Tag=codep'code project
				nb.setParentActivity(push_active)
				nb.setActivity(Main)
			End If
			
			'nb.addAction2("message","action1",Data,push_get)
			
			nb.Notify(Num_notify)
			'Num_notify=Num_notify+1	
			Return
			
		Else if Data.IndexOf("#") <> -1 Then 'indexof '#'
			Log("#")
			Dim arry_push_msg() As String
			arry_push_msg=Regex.Split("#",Data)
			
			nb.smallIcon="push1"
			nb.Ticker="پیام جدید از سین سین"
			nb.ContentTitle=arry_push_msg(0)
			nb.contentInfo=arry_push_msg(1)
			nb.contentText=arry_push_msg(2)	'title project
			nb.Autocancel=True
			Log(arry_push_msg(3))
			nb.Tag=arry_push_msg(3)'.SubString2(0,arry_push_msg(3).Length-2) 'link
			
			nb.setParentActivity(push_active)
			nb.setActivity(push_active)
			
			nb.Notify(Num_notify)
			'Num_notify=Num_notify+1	
			Return
		Else if Data.IndexOf("Update") <> -1 Or Data.IndexOf("update") <> -1 Then 
			Dim arry_update_push() As String
			arry_update_push=Regex.Split(":",Data)
			Dim version As Int=arry_update_push(1)
			If Application.VersionCode < version Then
				nb.smallIcon="push1"
				nb.Ticker=$"پیام جدید از ${Application.LabelName}"$
				nb.ContentTitle=$"نسخه جدید ${Application.LabelName}"$
				nb.contentInfo="(جدید)"
				nb.contentText="کلیک کنید"	
				nb.Autocancel=False
				nb.Tag=$"${Starter.root_site}/android/${Starter.apk_name_for_dowanload}"$'"bazaar://details?id=hpk.software.besaz" 'link
				nb.setParentActivity(push_active)
				nb.setActivity(push_active)	'push_active
				
				nb.Notify(Num_notify)
			End If
		Else
			
		End If 'indexof 
			'Num_notify=Num_notify+1
			
	End If

End Sub


Sub RegisterDevice (Unregister As Boolean)
	Dim i As Intent
	If Unregister Then		
		i.Initialize("com.google.android.c2dm.intent.UNREGISTER", "")
	Else
		i.Initialize("com.google.android.c2dm.intent.REGISTER", "")
		i.PutExtra("sender", Starter.SenderId)
	End If
	
	Dim r As Reflector
	Dim i2 As Intent
	i2 = r.CreateObject("android.content.Intent")
	Dim pi As Object
	pi = r.RunStaticMethod("android.app.PendingIntent", "getBroadcast", _
		Array As Object(r.GetContext, 0, i2, 0), _
		Array As String("android.content.Context", "java.lang.int", "android.content.Intent", "java.lang.int"))
	i.PutExtra("app", pi)
	StartService(i)
End Sub

Sub HandleRegistrationResult(Intent As Intent)
	If Intent.HasExtra("error") Then
		Log("Error: " & Intent.GetExtra("error"))
		'ToastMessageShow("Error: " & Intent.GetExtra("error"), True)
		
	Else If Intent.HasExtra("unregistered") Then
		ToastMessageShow(Application.LabelName,False)' log is unrefesteredd
	
	
	Else If Intent.HasExtra("registration_id") Then
		Dim j As HttpJob
		Dim rid,phone_id As String
		Dim phone1 As PhoneId
		Dim phone_model As Phone
			rid = Intent.GetExtra("registration_id")
				j.Initialize("RegisterTask", Me)
				j.Download2(Starter.BoardUrl, Array As String("tell", phone1.GetLine1Number, _
					"name", phone_model.Model, "regId", rid,"phone_id",phone1.GetDeviceId,"project",Starter.SenderId))
					
					'ToastMessageShow(phone_model.Model & "/" & Main.SenderId  ,True)
	End If
End Sub
Sub JobDone(Job1 As HttpJob)
	If Job1.Success Then
		Select Job1.JobName
			Case "RegisterTask"
				'File.WriteString(myLibrary.rute,"push_gift","TRUE")
				'ToastMessageShow("Ready Notification News Tattoo", False)
				'StartActivity(Main)
				File.WriteString(myLibrary.rute,"reg_push.txt","True")
			Case "UnregisterTask"
				'ToastMessageShow("NOT Ready Notification News Tattoo", False)
		End Select
	Else
		'ToastMessageShow("Errore della richiesta.", True)
		Log(Job1.ErrorMessage)
	End If
	Job1.Release
End Sub

Sub Service_Destroy

End Sub



#Region	manifest
'	C2DM Permissions
'	AddManifestText(<permission android:name="$PACKAGE$.permission.C2D_MESSAGE" android:protectionLevel="signature" />)
'	AddPermission($PACKAGE$.permission.C2D_MESSAGE)
'	AddPermission(com.google.android.c2dm.permission.RECEIVE)
'	' Push Service Receiver Attribute
'	SetReceiverAttribute(PushService, android:permission, "com.google.android.c2dm.permission.SEND")
'	' Service Receiver Text
'	AddReceiverText(PushService,
'	<intent-filter>
'	<action android:name="com.google.android.c2dm.intent.RECEIVE" />
'	<category android:name="$PACKAGE$" />
'	</intent-filter>
'	<intent-filter>
'	<action android:name="com.google.android.c2dm.intent.REGISTRATION" />
'	<category android:name="$PACKAGE$" />
'	</intent-filter>)
#End Region
