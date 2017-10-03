Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Public link_app As String
	Public ver As Int
End Sub

Sub Globals
	Private ProgressBar1 As ProgressBar
	Private lbl_progress As Label
'	Private panel_update As Panel
	Dim AsyncDownloader As RSAsyncDownloader
	Dim toast As TatyToast
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("update_app")
	AsyncDownloader.Initialize("AsyncDownloader")
	
	If File.Exists(File.DirRootExternal,$"${Application.LabelName}_${ver}"$ & ".apk")=False Then
		AsyncDownloader.FileName = $"${Application.LabelName}_${ver}"$ & ".apk"
		AsyncDownloader.Directory = File.DirRootExternal
		AsyncDownloader.Download(	link_app	)
	Else
		ToastMessageShow("مرحله نصب برنامه",True)
		Dim intent1 As Intent
		Dim s As String=File.DirRootExternal
		Log("path:" & "file:///" & s & $"/${Application.LabelName}_${ver}"$ & ".apk")
		intent1.Initialize(intent1.ACTION_VIEW,"file:///" & s & $"/${Application.LabelName}_${ver}"$ & ".apk")
		intent1.SetType("application/vnd.android.package-archive")
		StartActivity(intent1)
	End If
End Sub

Sub Activity_Resume
	If AsyncDownloader.IsCancelled=True Then Activity.Finish
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub AsyncDownloader_Started
	
End Sub

Private Sub AsyncDownloader_Update (progress As Int)
	Log(progress)
	ProgressBar1.Progress=progress
	lbl_progress.Text= "در حال بروزرسانی: " & progress & " / " & "100%"
End Sub

Private Sub AsyncDownloader_Finished (Result1 As String)
	Try
		
		toast.Initialize("مرحله نصب برنامه",toast.LENGTH_SHORT,toast.INFO)
		Dim i As Intent
		Dim s As String=File.DirRootExternal
		Log("path:" & "file:///" & s & $"/${Application.LabelName}_${ver}"$ & ".apk")
		i.Initialize(i.ACTION_VIEW,"file:///" & s & $"/${Application.LabelName}_${ver}"$ & ".apk")
		i.SetType("application/vnd.android.package-archive")
		StartActivity(i)
		Activity.Finish
	Catch
		
	End Try
End Sub
