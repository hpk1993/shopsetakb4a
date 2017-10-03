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
	
	'Public activity_name As String
End Sub

Sub Globals

End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("disconnect")

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub btn_check_net_Click
	If myLibrary.CheckInternet=True Then 
		StartActivity(Main)
			Activity.Finish
			myLibrary.SetAnimation("file3","file4")
		Else
			ToastMessageShow("تلاش شما ناموفق بود",True)
	End If
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		 	ExitApplication
			Return True
	End If
End Sub