Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	Dim sv ,sv2 As CustomListView
	Dim mony As money
	
	
	Private btn_search As Button
	Private txt_search As EditText
	Private panel_list As Panel
	
	Private lbl_name As Label
	Private lbl_job As Label
	Private lbl_tell As Label
	Private lbl_address As Label
	Private sv_user_panel As Panel
	
	Private lbl_id As Label
	Private sv_Panel_list As Panel
	Private lbl_price_order As Label
	Private lbl_tels As Label
	Private lbl_status As Label
	Private lbl_date_last As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("search_shared")
	connector.progress_circle_initialize(Activity)
	connector.progress_circle_visible(False)
	connector.Initialize2(Me)

	sv.Initialize(Me,"sv1",0xFFF5F2F5,4dip)
	panel_list.AddView(sv.AsView,0,0,panel_list.Width,panel_list.Height)
	
	
	
	
	Dim Intent1 As Intent
	Intent1 = Activity.GetStartingIntent
	If Intent1.HasExtra("Notification_Tag") Then
		Try
			Dim Notification_Tag As String= Intent1.GetExtra("Notification_Tag")
			If Notification_Tag.SubString2(0,1).IndexOf("u")<>-1 Then
				connector.Initialize(Me,"db",Starter.server_mysql,Starter.dn,Starter.du,Starter.dp)
				If function.is_admin=True Then
					
					Dim uid As Int=Notification_Tag.SubString(1)
					Log("is_admin: " & uid )
					connector. progress_circle_visible(True)
					connector.send_query($"select * from `users` where id=${uid-100}"$,"get_user","")
				End If
			
			End If
		Catch
		End Try
	End If
	
End Sub

Sub Activity_Resume
	connector.Initialize2(Me)
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub db_connector(list_records As List,tag As Object)
	ProgressDialogHide
	Try
		Select tag
			Case "get_user"
				If list_records.Size>0 Then
					sv.Clear
					Log("# " & list_records)
					For i=0 To list_records.Size-1
						Dim map1 As Map=list_records.Get(i)
						Dim pnl As Panel
						pnl.Initialize("")
						pnl.LoadLayout("sv_users")
						sv.Add(pnl,sv_user_panel.Height,map1.Get("id")	)
						Dim idu As Int=map1.Get("id")
						lbl_name.Text=map1.Get("name") & " " & map1.Get("lname") &  "  با کد اشتراک " &  (idu+100)
						lbl_job.Text="شغل: " & map1.Get("job")
						lbl_tell.Text="موبایل: " & map1.Get("mobile")
						lbl_address.Text=map1.Get("address")
					Next
				
				
				End If
				connector. progress_circle_visible(False)
				
			Case "get_all"
				If list_records.Size>0 Then
					sv.Clear
					For i=0 To list_records.Size-1
						Dim map1 As Map=list_records.Get(i)
						Dim pnl As Panel
						pnl.Initialize("")
						pnl.LoadLayout("sv_list_lat_order")
						Dim vip As Int=map1.Get("user_id")
						sv.Add(pnl,sv_Panel_list.Height,vip	)	'map1.Get("")
						Dim toman As money
						lbl_id.Text= map1.Get("customer_name") & " با اشتراک  " & (vip+100)
						lbl_price_order.Text="مبلغ سفارش " &  toman.number(map1.Get("amount_price")) & " تومان " & " با تعداد " & map1.Get("quality") & " عدد"
						lbl_tels.Text="تلفن : " & map1.Get("mobile") & "     "   &  map1.Get("tel")
						Dim stat_str As String
						Dim rich1 As RichString
						Dim stat_color As Int
						Dim stat As Int=map1.Get("status")
						Select stat
							Case 2
								stat_str="کد سفارش " &  map1.Get("id_order") & "  |  " &"{C}" & "پرداخت شده و در حال بررسی" & "{C}"
								stat_color=0xFF09477F
							Case 3
								stat_str="کد سفارش " &  map1.Get("id_order") & "  |  " &"{C}" & "ارسال شد" & "{C}"
								stat_color=0xFF135504
							Case 4
								stat_str="کد سفارش " &  map1.Get("id_order") & "  |  " &"وضعیت سفارش: " &  "{C}" & "پرداخت در محل" & "{C}"
								stat_color=0xFFF89F0D
						End Select
						rich1.Initialize(stat_str)
						rich1.Color2(stat_color,"{C}")
						lbl_status.Text= rich1
						
				
						Dim time_reg() As String
						time_reg=Regex.Split(" ",map1.Get("updated_at"))
							
						Dim date() As String=Regex.Split("-",time_reg(0))
							
						Dim clock As String=time_reg(1)
					
						Dim daye_per As PersianDate
						lbl_date_last.Text="تاریخ سفارش: " & daye_per.getDate(date(0),date(1),date(2),"/")
						If map1.Get("updated_at")="0000-00-00 00:00:00" Then  lbl_date_last.Text="تاریخ ثبت نشده"
							
						
					Next
				End If
				connector. progress_circle_visible(False)
					
			Case "disconnect"
				StartActivity(disconnect)
				ProgressDialogHide
				connector. progress_circle_visible(False)
			
		End Select
	Catch
		
	End Try
	connector. progress_circle_visible(False)
End Sub

Sub sv1_ItemClick (Position As Int, Value As Object)
	history_order.id_user=Value
	history_order.is_admin=True
	Log("id_user" & Value)
	StartActivity(history_order)
End Sub




Sub btn_search_Click
	Dim q As String=txt_search.Text.Trim
	Log(q)
	If IsNumber(q)=True  Then
		Dim id As Int=q
		Log(id)
		If id > 100 Then
			Log(id)
			connector.progress_circle_visible(True)
			connector.send_query($"select * from `users` where id=${id-100}"$,"get_user","")
		End If
	End If
	
	If IsNumber(q)=False  Then
		Dim name As String=q
		Log(name)
		If name.Length>0 Then
			Log(name)
			connector.progress_circle_visible(True)
			connector.send_query($"select * from `users` where `lname`='${name.Trim}' "$,"get_user","")
		End If
	End If
	
End Sub

Sub btn_list_Click
	connector.progress_circle_visible(True)
	connector.send_query($"SELECT  `order`.`id` AS  'id_order',  `order_payment`.`amount_price` ,  `order_payment`.`status` ,  `order`.`customer_name` , `order`.`mobile` ,  `order`.`tel` ,  `order`.`user_id` ,  `order_detail`.`quality` ,`order`.`updated_at`
FROM  `order` 
LEFT JOIN  `order_detail` ON  `order_detail`.`order_id` =  `order`.`id` 
LEFT JOIN  `order_payment` ON  `order_payment`.`order_id` =  `order`.`id` 
WHERE `order_payment`.`status`>1 
GROUP BY  `order`.`id` 
ORDER BY  `order`.`id` DESC 
LIMIT 0 , 15"$,"get_all","")
End Sub