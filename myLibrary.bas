Type=StaticCode
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'********select sub And (Ctr + f) to find sub in the class
'***************sub
#region sub1 	MinimomFontLabel 	#End Region
#region sub2	fit_view_textsize 	#End Region
#region sub3	art 				#End Region
#region sub4	SetDivider		 	#End Region
#region sub5	change_font_view 	#End Region
#region sub6	get_height_label 	#End Region
#region sub7	persion_date 		#End Region
#region sub8	list_to_arry 		#End Region
#region sub9	getColor		 	#End Region
#region sub10	CovertPersian2English #End Region
'================================================
Sub Process_Globals
Dim first As Boolean
Dim me1 As MediaPlayer
'Dim pre As AHPreferenceManager
Private daye_per As PersianDate
Private utils_str As StringUtils
Dim ht As Float
End Sub

Sub ShareContent(sContent As String,sTitle As String)
Dim share As Intent
share.Initialize(share.ACTION_SEND,"")
share.SetType("text/plain")
share.PutExtra("android.intent.extra.TEXT", sContent)
share.WrapAsIntentChooser(sTitle)
StartActivity(share)
End Sub

Sub SendSms(PhoneNumber As String, Text As String)
Dim In As Intent
    In.Initialize(In.ACTION_VIEW, "sms:" & PhoneNumber)
    In.PutExtra("sms_body", Text)
    StartActivity(In)
End Sub

Sub CheckInternet As Boolean 
'   Dim p As Phone
'   Dim connected As Boolean
'   connected = False
'   
'   If (p.GetDataState == "CONNECTED") Then
'      connected = True
'   End If
'   
'   If (p.GetSettings ("wifi_on") == 1) Then
'      connected = True
'   End If
'   
'   Return connected
	
Dim ph As Phone
Dim mylan As ServerSocket
	If (ph.GetDataState <> "CONNECTED" And mylan.GetMyIP == "127.0.0.1") Or mylan.GetMyIP == "127.0.0.1" Then
        Return False
    Else
        Return True
    End If
End Sub

Sub AnimationView(View1 As View,fromx As Int,fromy,tox As Int,toy As Int)
'Dim ani As AnimationPlus
'ani.InitializeScaleCenter("ani",fromx,fromy,tox,toy,View1)
'ani.Duration = 200
'ani.PersistAfter = True
'ani.Start(View1)
End Sub

#region navbar Colors
Sub SetStatusBarColor(clr As Int)
   Dim p As Phone
   If p.SdkVersion >= 21 Then
     Dim jo As JavaObject
     jo.InitializeContext
     Dim window As JavaObject = jo.RunMethodJO("getWindow", Null)
     window.RunMethod("addFlags", Array (0x80000000))
     window.RunMethod("clearFlags", Array (0x04000000))
     window.RunMethod("setStatusBarColor", Array(clr))
   End If
End Sub
#End Region

Sub OpenBazaarApp(sPackageName As String,blnOpinion As Boolean)
Dim market As Intent, uri As String
uri="bazaar://details?id=" & sPackageName
If blnOpinion = False Then
 market.Initialize(market.ACTION_EDIT,uri)
Else
 market.Initialize(market.ACTION_VIEW,uri)
End If
StartActivity(market)
End Sub

Sub SendMail(sTo As String,sBody As String,sSubject As String)
 Try
   Dim p1 As Email
   p1.To.add(sTo)
   p1.Subject = sSubject
   p1.Body    = sBody
   ProgressDialogShow2("در حال ارسال",True)
   StartActivity(p1.GetIntent)
   ProgressDialogHide
 Catch
  ProgressDialogHide
  Log(LastException.Message)
 End Try   
End Sub

Sub bookmark(sID As String) As Boolean
Dim m1 As Map
m1.Initialize
If File.Exists(File.DirInternal,"bookmark") Then
 m1 = File.ReadMap(File.DirInternal,"bookmark")
End If
If m1.ContainsKey(sID) = True Then
 m1.Remove(sID)
 File.WriteMap(File.DirInternal,"bookmark",m1)
 Return False
Else
 m1.Put(sID,sID)
 File.WriteMap(File.DirInternal,"bookmark",m1)
 Return True
End If
End Sub

Sub checkBookmark(sID As String) As Boolean
Dim m1 As Map
m1.Initialize
If File.Exists(File.DirInternal,"bookmark") Then
 m1 = File.ReadMap(File.DirInternal,"bookmark")
 If m1.ContainsKey(sID) = True Then
  Return True
 Else
  Return False
 End If
Else
 Return False
End If
End Sub


#region base64
public Sub base64(txt As String)as String
	Dim converter As ByteConverter
	Dim byte1() As Byte
	byte1=converter.StringToBytes(txt,"UTF-8")
	Dim st As StringUtils
	Return st.EncodeBase64	(byte1)
End Sub
#end region

Sub getbookmarklist As Map
Dim m1 As Map
m1.Initialize

If File.Exists(File.DirInternal,"bookmark") Then
 m1 = File.ReadMap(File.DirInternal,"bookmark")
 Return m1
Else
 Return Null
End If
End Sub


Sub SetAnimation(InAnimation As String, OutAnimation As String)
    Dim r As Reflector
    Dim package As String
    Dim In, out As Int
    package = r.GetStaticField("anywheresoftware.b4a.BA", "packageName")
    In = r.GetStaticField(package & ".R$anim", InAnimation)
    out = r.GetStaticField(package & ".R$anim", OutAnimation)
    r.Target = r.GetActivity
    r.RunMethod4("overridePendingTransition", Array As Object(In, out), Array As String("java.lang.int", "java.lang.int"))
End Sub

Sub getColor(color As String) As Int
Select color
 Case "Blue"
  Return Colors.RGB(36,92,154)
 Case "Red"
  Return Colors.RGB(202,40,40)
 Case "Green"
  Return Colors.RGB(26,159,16)
 Case "Yellow"
  Return Colors.RGB(212,203,53)
 Case "Black"
  Return Colors.Black
 End Select
 Return Colors.RGB(36,92,154)
End Sub

'return ex: "str1;str2;str3"
Public Sub list_to_arry(lst As List) As String
			Dim str As String
			For i=0 To lst.Size-1
				If i=0 Then
					str=lst.Get(i) & ";"
				Else if i > 0 And i < lst.Size-1 Then
					str=str & lst.Get(i) & ";"
				else if i=lst.Size-1 Then
					str=str & lst.Get(i)
				End If	
			Next
			Return str
End Sub
'ex: p= y or m or d or y/m/d
Sub persion_date(p As String)As Object
	Dim date,y,m,d As String
	date=daye_per.getDate(0,0,0,"/")
	Log("//////////" & date)
	Dim arrtemp() As String
	arrtemp=Regex.Split("/",date)
	Log(arrtemp(0))
	Log(arrtemp(1))
	Log(arrtemp(2))
	Select p
		Case "y"
			Return arrtemp(0)
		Case "m"
			Return arrtemp(1)
		Case "d"
			Return arrtemp(2)
		Case "y/m/d"
			
			Return (arrtemp(0) & "/" & arrtemp(1) & "/" & arrtemp(2))
	End Select
	
End Sub

Public Sub get_height_label(lbl As Label,str As String) As Float
	Dim lbl1 As Label
	lbl1.Initialize("")
	lbl1=lbl
	Return utils_str.MeasureMultilineTextHeight(lbl1,str)
End Sub

Public Sub change_font_view(label_view As Boolean,button_view As Boolean,edittext_view As Boolean,spiner_view As Boolean,check_view As Boolean,activity As Activity,font As String)
	For Each obj As View In activity.GetAllViewsRecursive
		If label_view=True Then
			If obj Is Label Then
				Dim lbl1 As Label
				lbl1=obj
				lbl1.Typeface=Typeface.LoadFromAssets(font)
			End If
		else if edittext_view=True Then
			If obj Is EditText Then
				Dim txt1 As EditText
				txt1=obj
				txt1.Typeface=Typeface.LoadFromAssets(font)
			End If
		else if  button_view=True Then
			If obj Is Button Then
				Dim btn1 As Button
				btn1=obj
				btn1.Typeface=Typeface.LoadFromAssets(font)
			End If
		else if  spiner_view=True Then
			If obj Is Spinner Then
				Dim spin1 As Spinner
				spin1=obj
				'spin1.Typeface=Typeface.LoadFromAssets(font)
			End If
		else if  check_view=True Then
			If obj Is CheckBox Then
				Dim check1 As CheckBox
				check1=obj
				check1.Typeface=Typeface.LoadFromAssets(font)
			End If
		End If
	Next
End Sub


Sub SetDivider(lv As ListView, Color As Int, Height As Int)
  Dim r As Reflector
  r.Target = lv
  Dim CD As ColorDrawable
  CD.Initialize(Color, 0)
  r.RunMethod4("setDivider", Array As Object(CD), Array As String("android.graphics.drawable.Drawable"))
  r.RunMethod2("setDividerHeight", Height, "java.lang.int")
End Sub

Sub art(txt As String,label As Label,font As Typeface,size As Int,line_space As Int)As Float
	Dim before,after As Float
	Dim obj1 As Reflector
	
	#region
		Dim r1 As RichString
		r1.Initialize(txt)
		r1.Style2(r1.STYLE_BOLD,"{B}")
		r1.Style2(r1.STYLE_ITALIC,"{I}")
		r1.Style2(r1.STYLE_BOLD_ITALIC,"{B_I}")
		r1.Subscript2("{SUBSCRPT}")
		r1.Strikethrough2("{STRIK}")
		
		r1.Underscore2("{UNDER}")
		r1.Color2(0xFF096DC6,"{BLUE}")
		r1.Color2(0xFFDC0C4C,"{RED}")
		r1.Color2(0xFF035E03,"{GREEN}")
		r1.Color2(0xFFEDF40E,"{YELLOW}")
		r1.Color2(0xFFBB09A0,"{MAGENTA}")
		'///
		r1.BackColor2(0xFF68B2F4,"{BG_BLUE}")
		r1.BackColor2(0xFFED1054,"{BG_RED}")
		r1.BackColor2(0xFF46F958,"{BG_GREEN}")
		r1.BackColor2(0xFFE1F52E,"{BG_YELLOW}")
		r1.BackColor2(0xFFEC32D0,"{BG_MAGENTA}")
		'//
		r1.RelativeSize2(1,"{BIG1}")
		r1.RelativeSize2(2,"{BIG2}")
		r1.RelativeSize2(3,"{BIG3}")
		r1.RelativeSize2(4,"{BIG4}")
	#end region
	label.Text=r1
	label.TextSize=size
	label.Gravity=Gravity.RIGHT
	label.Left=label.Left
	label.Typeface=font
	
	ht=utils_str.MeasureMultilineTextHeight(label,txt)
	obj1.Target=label
	before=obj1.RunMethod("getLineHeight")
	obj1.Target=label
	obj1.RunMethod3("setLineSpacing", .10,"java.lang.float",line_space,"java.lang.float")
	obj1.Target=label
	after=obj1.RunMethod("getLineHeight")
	ht=((after * ht)/before)
	'label.Height=ht
	
	Return ht
End Sub






Public Sub fit_view_textsize(v As View)
	Dim su As StringUtils
	If v Is Label Then
		#region label
				Dim lbl As Label
				lbl=v
				If lbl.Text <> "" Then
					For i = 1 To 1000
					lbl.TextSize = i
					If su.MeasureMultilineTextHeight(lbl, lbl.Text) = lbl.Height Then
						Exit
					Else If su.MeasureMultilineTextHeight(lbl, lbl.Text) > lbl.Height Then
						lbl.TextSize = i-1
						Exit
					End If
					Next
				End If
		#end region
	
	
'	else if v Is Button Then
'		#region Button
'				Dim btn As Button
'				btn=v
'				If btn.Text <> "" Then
'					For i = 1 To 1000
'					btn.TextSize = i
'					If su.MeasureMultilineTextHeight(btn, btn.Text) = btn.Height Then
'						Exit
'					Else If su.MeasureMultilineTextHeight(btn, btn.Text) > btn.Height Then
'						btn.TextSize = i-1
'						Exit
'					End If
'					Next
'				End If
'		#end region
	End If
End Sub
#region sub1 	MinimomFontLabel 
public Sub MinimomFontLabel(L As Label, Font As Int)
	Dim su As StringUtils
	If L.Text <> "" Then
		For i = 1 To Font
		L.TextSize = i
		If su.MeasureMultilineTextHeight(L, L.Text) = L.Height Then
			Exit
		Else If su.MeasureMultilineTextHeight(L, L.Text) > L.Height Then
			L.TextSize = i-1
			Exit
		End If
		Next
	End If
End Sub
public Sub MinimomFontbutton(L As Button, Font As Int)
	Dim su As StringUtils
	If L.Text <> "" Then
		For i = 1 To Font
		L.TextSize = i
		If su.MeasureMultilineTextHeight(L, L.Text) = L.Height Then
			Exit
		Else If su.MeasureMultilineTextHeight(L, L.Text) > L.Height Then
			L.TextSize = i-1
			Exit
		End If
		Next
	End If
End Sub

Sub CovertPersian2English(Str As String) As String
    Dim te As String
    te = Str
    te = te.Replace("۱","1")
    te = te.Replace("۲","2")
    te = te.Replace("۳","3")
    te = te.Replace("۴","4")
    te = te.Replace("۵","5")
    te = te.Replace("۶","6")
    te = te.Replace("۷","7")
    te = te.Replace("۸","8")
    te = te.Replace("۹","9")
    te = te.Replace("۰","0")
    Return te
End Sub
Sub CovertEnglish2Persian(Str As String) As String
    Dim te As String
    te = Str
    te = te.Replace("1","۱")
    te = te.Replace("2","۲")
    te = te.Replace("3","۳")
    te = te.Replace("4","۴")
    te = te.Replace("5","۵")
    te = te.Replace("6","۶")
    te = te.Replace("7","۷")
    te = te.Replace("8","۸")
    te = te.Replace("9","۹")
    te = te.Replace("0","۰")
    Return te
End Sub

Sub GetDevicePhysicalSize As Float
    Dim lv As LayoutValues
    lv = GetDeviceLayoutValues
    Return Sqrt(Power(lv.Height / lv.Scale / 160, 2) + Power(lv.Width / lv.Scale / 160, 2))
End Sub


Sub time_ago(time_now As Int,t As Int)As String
	Dim result1,Result2 As Int
	Dim ago As String
	
	result1=time_now-t
	result1=result1/60
	
	If(result1=0 ) Then
		Return "لحظاتی پیش"
	Else If(result1>=1 And result1<=59 ) Then
		Result2=Round(result1)
		ago="دقیقه پیش"
		Return Result2 & " " & ago
	Else If(result1>=60 And result1<=1440) Then
		Result2=Round(result1/60)
		ago="ساعت پیش"
		Return Result2 & " " & ago
	Else If(result1>=1441) Then
		Result2=Round(result1/60/24)
		ago="روز پیش"
		Return Result2 & " " & ago
	End If
End Sub

Sub set_title_background(v As View,b As Bitmap)
	Dim bd As BitmapDrawable
	bd.Initialize(b)
	
	Dim r As Reflector
	r.Target=bd
	r.RunMethod3("setTileModeXY","REPEAT","android.graphics.Shader$TileMode","REPEAT","android.graphics.Shader$TileMode")
	v.Background=bd
End Sub

'این تابع اشاره گر تکست ویوو رو مخفی و نمایش میده
Sub setCursorVisible(edt As EditText, Visible As Boolean)
    Dim jo = edt As JavaObject
    jo.RunMethod("setCursorVisible", Array As Object(Visible))
End Sub

'ان تابع قابلیت انتخاب شدن متن تکست ویوو رو تعین میکنه
'اگه True باشه قالب انتخاب میشه ولی False باشه غیرفعال میشه
Sub setTextIsSelectable(edt As EditText, Selectable As Boolean)
    Dim jo = edt As JavaObject
    jo.RunMethod("setTextIsSelectable", Array As Object(Selectable))
End Sub

Sub rute() As String
	Dim path As String
	
	If File.ExternalWritable=True Then
		path=File.DirDefaultExternal
	Else
		path=File.DirInternal
	End If
	Return path
End Sub

'Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
'	If KeyCode = KeyCodes.KEYCODE_BACK Then
'		Activity.Finish
'		myLibrary.SetAnimation("file3","file4")
'		Return True
'	End If
'End Sub


'intent.Initialize(intent.ACTION_EDIT,"bazaar://details?id=hpk.software.besaz")
'	intent.SetComponent("com.farsitel.bazaar")
'	StartActivity(intent)
'	Activity.Finish

'					Dim su As StringUtils
'					Dim bc As ByteConverter
'					Dim text As String = "hassan peykarestan"
'					txt_pass.Text=(su.EncodeBase64(bc.StringToBytes(text,"utf-8")))
'					Dim Base64String_to_Bytes() As Byte=su.DecodeBase64(txt_pass.Text)
'					'Msgbox(bc.StringFromBytes(Base64String_to_Bytes,"utf-8"),"")