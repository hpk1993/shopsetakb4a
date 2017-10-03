Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	
	Private Panel1 As Panel

	Private panel_base As Panel
	Private divider_LayoutView As Hitex_DividerItemDecoration
	
	Dim LayoutView_project As Hitex_LayoutView
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("project_main")
	Dim color_header As ColorDrawable
	color_header.Initialize(Starter.color_base,0)
	Panel1.Background=color_header
	panel_base.Visible=True

	LayoutView_project.Initialize("LayoutView_project",False,"ListView","Vertical",False,True,2)
	panel_base.AddView(LayoutView_project,0,	0	,	panel_base.Width	,	panel_base.Height	)
	divider_LayoutView.Initialize(LayoutView_project)
	divider_LayoutView.AddItemDecoration1(0xFFF9F9F9,3%y)
	LayoutView_project.Show(6 , LayoutView_project.ANIMATION_ScaleIn,500,False,panel_base.Width,panel_base.Height)
				
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub LayoutView_project_onCreateViewHolder (Parent As Panel)	'* onCreate *'
	Log("LayoutView_project_onCreateViewHolder")
	'	Parent.Height=panel_brand.Height
	'	Parent.Width=panel_brand.Width/3
	'
	''	Dim img1  As ImageView
	''	img1.Initialize("")
	''	Parent.AddView(img1,2%x,0,Parent.Width-4%x,Parent.Height)
	''	apc.SetElevation(img1,30dip)
	'
	'	Dim lbl As Label
	'	lbl.Initialize("")
	'	Parent.AddView(lbl,2%x,0,Parent.Width-4%x,Parent.Height)
	'	Dim bgl As ColorDrawable
	'	bgl.Initialize(Rnd(Colors.Black,Colors.Yellow)	,5dip)
	'	lbl.Background=bgl
	
	Parent.Height=60%y
Parent.LoadLayout("sv_project")
	
End Sub

Sub LayoutView_project_onBindViewHolder (Parent As Panel,Position As Int)	'* onBind *'
	Log("LayoutView_project_onBindViewHolder: " & Position )
	'	Dim brand_list1=list_brand.Get(Position) As brands
	'	Dim pnl1 As Panel=Parent
	'	Dim img1  As ImageView=pnl1.GetView(0)
	'	Log(pnl1)
	'	Log(img1)
	'	piccaso.Load("http", Starter.dir_root_image_file_brand & brand_list1.pic).Resize(img1.Width,img1.Height).CenterCrop.SkipMemoryCache.Into(img1)
End Sub


Sub btn_home_Click
	Activity.Finish
End Sub