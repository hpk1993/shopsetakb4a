Type=StaticCode
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@

Sub Process_Globals
End Sub

Sub FitTobuttonSize(L As Button)
	Dim su As StringUtils
	If L.Text <> "" Then
		For i = 1 To 1000
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

Sub FitToLabelSize(L As Label)
	Dim su As StringUtils
	If L.Text <> "" Then
		For i = 1 To 1000
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
	
	
	else if v Is Button Then
		#region label
				Dim btn As Button
				btn=v
				If btn.Text <> "" Then
					For i = 1 To 1000
					btn.TextSize = i
					If su.MeasureMultilineTextHeight(btn, btn.Text) = btn.Height Then
						Exit
					Else If su.MeasureMultilineTextHeight(btn, btn.Text) > btn.Height Then
						btn.TextSize = i-1
						Exit
					End If
					Next
				End If
		#end region
	End If
End Sub

Sub MinimomFontLabel(L As Label, Font As Int)
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



