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
	
End Sub

Sub Globals
	Dim intent As Intent
	Dim extra_text As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("push_activ")
	Log("intent")
	intent=Activity.GetStartingIntent
	If intent.HasExtra("Notification_Tag") Then
		
		extra_text=intent.GetExtra("Notification_Tag")
		Log("extra_text: "&extra_text )
			If extra_text.IndexOf("bazaar://") <> -1 Or extra_text.IndexOf("bazzar://")<> -1 Then
				intent.Initialize(intent.ACTION_EDIT,extra_text)
				intent.SetComponent("com.farsitel.bazaar")
				StartActivity(intent)
				Activity.Finish
			
			else If extra_text.IndexOf("https://telegram.me/")<>-1 Then
				intent.Initialize(intent.ACTION_VIEW,extra_text)
			    StartActivity(intent)	' تلگرام
				Activity.Finish
				
			else if extra_text.IndexOf("https://instagram.com/")<>-1 Then
				intent.Initialize(intent.ACTION_VIEW,extra_text)
				intent.SetComponent("com.instagram.android")
		    	StartActivity(intent)	'اینستگرام
				Activity.Finish
					
			else If extra_text.IndexOf("http://")<>-1 Or  extra_text.IndexOf("https://")<>-1 Or extra_text.IndexOf(".apk")<>-1 Or extra_text.IndexOf(".APK")<>-1 Then
				Dim browsers As PhoneIntents
				StartActivity(browsers.OpenBrowser(extra_text))	'مرورگر
				Activity.Finish
		End If
	End If


End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
