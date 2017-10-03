Type=Service
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

'Service module

Sub Process_Globals
Dim PusheUtil As PusheB4AUtil
End Sub
Sub Service_Create
	
End Sub

Sub Service_Start (StartingIntent As Intent)
	Select StartingIntent.Action
		Case "com.google.android.c2dm.intent.RECEIVE" 
			MessageArrived(StartingIntent)
	End Select
End Sub

Sub MessageArrived (pIntent As Intent)
	
	Dim JsonMsg As String
	JsonMsg = PusheUtil.getPusheJsonMsg(pIntent)
	If JsonMsg <> "" Then
		Dim parser As JSONParser
		parser.Initialize(JsonMsg)
		Dim root As Map = parser.NextObject
		Dim action As String=root.Get("action")
		
		
		If function.is_admin=True Then
			
			If action="reg_user" Then
				Dim uid As Int = root.Get("uid")
				Dim name_lname As String = root.Get("name_lname")
				Dim nb As NotificationBuilder
				nb.Initialize
				nb.setCustomLight(Colors.red,50,50)
				nb.DefaultVibrate=True
				nb.DefaultSound=True
				nb.Autocancel=False
				nb.smallIcon="icon"
				nb.Ticker="یک ثبت نام جدید"
				nb.ContentTitle= $"ثبت نام جدید با اشتراک  ${uid}"$
				nb.contentInfo="(جدید)"
				nb.contentText= $"کاربر: ${name_lname}"$
				nb.Autocancel=True
				nb.Tag="u" & (uid)
				nb.setActivity(shared_admin)
				nb.Notify(	Rnd(1,100) )
			End If	'/reg_user
			
			If action="buy_user" Then
				Dim uid As Int = root.Get("uid")
				Dim price As String = root.Get("price")
				Dim nb As NotificationBuilder
				nb.Initialize
				nb.setCustomLight(Colors.red,50,50)
				nb.DefaultVibrate=True
				nb.DefaultSound=True
				nb.Autocancel=False
				nb.smallIcon="icon"
				nb.Ticker="یک سفارش جدید"
				nb.ContentTitle= $"سفارش جدید اشتراک   ${uid}"$
				nb.contentInfo="(سفارش)"
				nb.contentText= $"مبلغ: ${price}"$
				nb.Autocancel=True
				nb.Tag="u" & (uid)
'				nb.setParentActivity(Main)
				nb.setActivity(shared_admin)
				nb.Notify(	Rnd(1,100) )
			End If
			
		End If'/is_admin?
		
		If action="new_post" Then
			Dim id_post As Int = root.Get("id_post")
			Dim title_post As String = root.Get("title_post")
			Dim nb As NotificationBuilder
			nb.Initialize
			nb.DefaultVibrate=True
			nb.DefaultSound=True
			nb.Autocancel=True
			nb.smallIcon="icon"
			nb.Ticker="معرفی محصول "
			nb.ContentTitle="معرفی محصول "
			nb.contentText=title_post
			nb.contentInfo="( " & Application.LabelName & " )"
			nb.Tag=id_post
'			nb.setParentActivity(push_active)
			nb.setActivity(Main)
			nb.Notify(	Rnd(1,100) )
		End If
		
	End If '/not null
End Sub


Sub Service_Destroy

End Sub


