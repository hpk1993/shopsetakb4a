Type=Service
Version=6.5
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	
#End Region

Sub Process_Globals
   Private fm As FirebaseMessaging
End Sub

Sub Service_Create
	fm.Initialize("fm")
End Sub

Public Sub SubscribeToTopics
	fm.SubscribeToTopic("general") 'you can subscribe to more topics
End Sub

Sub Service_Start (StartingIntent As Intent)
	If StartingIntent.IsInitialized And fm.HandleIntent(StartingIntent) Then Return
End Sub

Sub fm_MessageArrived (Message As RemoteMessage)
	Log("Message arrived")
	Log($"Message data: ${Message.GetData}"$)
	Dim n As Notification
	n.Initialize
	n.Icon = "icon"
	n.SetInfo(Message.GetData.Get("title"), Message.GetData.Get("body"), Main)
	n.Notify(1)
End Sub

Sub Service_Destroy

End Sub
