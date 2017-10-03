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
	Public code_kala As Int
	
	Public bool_propertis As Boolean=False
End Sub

Sub Globals
	Dim sv As CustomListView
	Private progress_spot As SpotsDialog
	Dim mytoastMessage As MyToastMessageShow
	Private panel_main As Panel
	Private sv_lbl_msg As Label
	Private sv_panel_text As Panel
	Private sv_panel As Panel
	Dim myfont As Typeface
	'/////////////////////////propertis
	Private sv_pro_value As Label
	Private sv_pro_panel As Panel
	Private sv_pro_key As Label
	Private Panel1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("show_nazar")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	
	connector.Initialize2(Me)
	
	server_mysql=Starter.server_mysql
	mytoastMessage.Initialize(Me,"DoAction_End",Activity,0xFF13879A,Colors.Red)
	
	function. initialize_spotdialog(progress_spot)
	sv.Initialize(Me,"sv1",0xffE0DDDD,8dip)
	panel_main.AddView(sv.AsView,0,0,panel_main.Width,panel_main.Height)
	
	
	myfont=Starter.font_body
	
	If bool_propertis=True Then
		get_propertis
	Else
		get_nazar
	End If
	
	bool_propertis=False
End Sub

Sub Activity_Resume
 	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub get_nazar
	progress_spot.ShowDialog
	connector.send_query($"SELECT * From `nazar` where `code_kala`=${code_kala} and `stuts`=1"$,"get_nazar","")
End Sub

Sub get_propertis
	progress_spot.ShowDialog
	connector.send_query($"SELECT * From `propertis_kala` where `code_kala`=${code_kala}"$,"get_propertis","")
End Sub

Sub db_connector(records As List,tag As Object)
Try
	Select tag
			Case "get_nazar"
				For i=0 To records.Size-1
					Dim map1 As Map
					map1=records.Get(i)
					Dim msg As String=map1.Get("msg")
					If msg.Length > 1 Then
						Dim pnl As Panel
						pnl.Initialize("")
						pnl.LoadLayout("sv_show_nazar")
						Dim txt As String="{BLUE}" & map1.Get("sender") & ":{BLUE}"  & CRLF  & map1.Get("msg")
						
						sv_panel.Height=myLibrary.art(txt,sv_lbl_msg,myfont,14,2) + 8dip
						sv_panel_text.SetLayout(3%x,0,sv_panel.Width-6%x,sv_panel.Height)
						sv_lbl_msg.SetLayout(1%x,1%y,sv_panel_text.Width-3%x,sv_panel_text.Width-2%y)
						
						sv.Add(pnl,sv_panel.Height,i)
					End If
				Next
				
			Case "get_propertis"
				
				Try
					If records.Size > 0 Then
						For i=0 To records.Size-1
							Dim map1 As Map=records.Get(i)
							Dim namepro As String=map1.Get("key_name")
							Dim valuepro As String=map1.Get("key_value")
							Dim pnl As Panel
							pnl.Initialize("")
							pnl.LoadLayout("sv_show_propertis")
							sv.Add(pnl,sv_pro_panel.Height,i)
							sv_pro_key.Text=namepro
							sv_pro_value.Text=valuepro
						Next
						
						
						progress_spot.DisMissDialog
					
					
					Else
						progress_spot.DisMissDialog
						mytoastMessage.ShowToastMessageShow("مشخصات فنی برای این محصول درج نشده است",2,False,True,True)
							
					End If
				Catch
					progress_spot.DisMissDialog
					mytoastMessage.ShowToastMessageShow("مشخصات فنی برای این محصول درج نشده است",2,False,True,True)
				End Try
				progress_spot.DisMissDialog
		Case "disconnect"
				StartActivity(disconnect)
				Activity.Finish
		Case "error"
				StartActivity(disconnect)
				Activity.Finish
				mytoastMessage.ShowToastMessageShow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",2,False,True,True)
	End Select
	progress_spot.DisMissDialog
	ProgressDialogHide
	Catch
		mytoastMessage.ShowToastMessageShow("متاسفانه بارگذاری نشد.دوباره تلاش کنید",2,False,True,True)
		progress_spot.DisMissDialog
	End Try
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
'	End If
End Sub