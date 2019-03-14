package nc.ui.so.qs.sc.maschine.view;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nc.bs.logging.Logger;
/*     */ import nc.md.model.IAttribute;
/*     */ import nc.ui.bd.ref.AbstractRefModel;
/*     */ import nc.ui.mmf.busi.ref.FilterDefaultRefUtils;
/*     */ import nc.ui.mmf.framework.view.BillFormFacade;
///*     */ import nc.ui.pd.pd0404.model.PDWkBillManageModel;
///*     */ import nc.ui.pd.pd0404.scale.PDWkDecimalCastidListener;
///*     */ import nc.ui.pd.pd0404.scale.PDWkDecimalCastunitidListener;
/*     */ import nc.ui.pub.beans.UIRefPane;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillData;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pub.bill.BillModel;
/*     */ import nc.ui.pub.bill.BillTabbedPane;
/*     */ import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
			import nc.ui.so.qs.sc.maschine.model.MaschineBillManageModel;
/*     */ import nc.ui.uif2.AppEvent;
import nc.ui.uif2.ShowStatusBarMsgUtil;
/*     */ import nc.ui.uif2.UIState;
/*     */ import nc.ui.uif2.model.AbstractAppModel;
/*     */ import nc.util.mmf.busi.service.OrgUnitPubService;
/*     */ import nc.util.mmf.framework.base.MMArrayUtil;
/*     */ import nc.util.mmf.framework.base.MMStringUtil;
/*     */ import nc.util.mmf.framework.base.MMValueCheck;
/*     */ import nc.vo.jcom.lang.StringUtil;
///*     */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*     */ import nc.vo.pd.pd0404.entity.WkProdinvVO;
///*     */ import nc.vo.pd.wk.wkg.entity.TimeUnitEnum;
/*     */ import nc.vo.pub.SuperVO;
/*     */ import nc.vo.pub.bill.MetaDataPropertyAdpter;
/*     */ import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.so.qs.en.MachStatus;
			import nc.vo.so.qs.sc.AggMaschineVO;
			import nc.vo.uif2.LoginContext;
import nc.vo.so.qs.sc.MachineMater_Mater;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.ui.so.pub.keyvalue.CardKeyValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineBillForm
/*     */   extends BillFormFacade
/*     */ {
/*     */   private static final long serialVersionUID = 3139730404929462443L;
/*     */   private Map<String, List<String>> visibleBodyRefMap;
/*     */   private List<String> visibleHeadRefList;
/*  57 */   private String wip_tableCode = "mater";
/*     */   
/*     */ 
/*     */   private AbstractAppModel model;
/*     */   
/*     */ 
/*     */   public MaschineBillForm() {}
/*     */   
/*     */ 
/*     */   public void handleEvent(AppEvent event)
/*     */   {
/*  68 */     super.handleEvent(event);
/*  69 */     if ((event instanceof ListHeadRowChangedEvent) || (("UiState_Changed" == event.getType()) && (getModel().getUiState() != UIState.ADD))) {
/*  73 */       maschineSyn();
/*     */     }
/*  75 */     if ((getModel().getUiState() == UIState.EDIT) || (getModel().getUiState() == UIState.ADD)) {
/*  76 */       setTimeUnit();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T extends SuperVO> T[] getBodyAllVOs(String tabCode, Class<T> voClass)
/*     */   {
/*  82 */     T[] bodyVOs = getBodyValueVOs(tabCode, voClass);
/*  83 */     return bodyVOs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void setTimeUnit()
/*     */   {
/*  90 */     String pk_org = getModel().getContext().getPk_org();
///*  91 */     if (MMStringUtil.isEmpty(pk_org)) {
///*  92 */       getBillCardPanel().setHeadItem("ftimeunit", TimeUnitEnum.HOUR.value());
///*     */     }
///*     */     else {
///*  95 */       MaschineBillManageModel wkModel = (MaschineBillManageModel)getModel();
///*  96 */       getBillCardPanel().setHeadItem("ftimeunit", wkModel.getTimeUnitByOrg(pk_org));
///*     */     }
///*  98 */     getBillCardPanel().getHeadItem("ftimeunit").setEdit(false);
/*     */   }
/*     */   
/*     */   protected void maschineSyn() {
/* 102 */     Logger.debug("entering synchronizeDataFromModel");
/* 103 */     if (MMValueCheck.isEmpty(getModel())) {
/* 104 */       return;
/*     */     }
/* 106 */     Object selectedData = getModel().getSelectedData();
/* 107 */     setValue(selectedData);
/* 108 */     Logger.debug("leaving synchronizeDataFromModel");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */    //表头字段“工作中心”字段
///* 114 */   private String[] cntEditFields = { "cwkid" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void showMeUp()
/*     */   {
/* 121 */     initRef();
/* 122 */     super.showMeUp();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setDefaultTabCodeEdit()
/*     */   {
				//根据表头“生产线”字段 判断是否允许编辑页签和表头的字段值
///* 131 */     Object prodlineObject = this.billCardPanel.getHeadItem("bprodline").getValueObject();
///* 132 */     if (MMValueCheck.isEmpty(prodlineObject)) {
///* 133 */       return;
///*     */     }
///* 135 */     boolean prodline = UFBoolean.valueOf(prodlineObject.toString()).booleanValue();
///* 136 */     if (prodline) {
///* 137 */       this.billCardPanel.setTabEnabled(1, "prodinv_items", true);
///* 138 */       this.billCardPanel.setTabEnabled(1, "replace_items", true);
///* 139 */       getBillCardPanel().getHeadItem("nastnum").setEdit(true);
///* 140 */       getBillCardPanel().getHeadItem("ntime").setEdit(true);
///* 141 */       getBillCardPanel().getHeadItem("castunitid").setEdit(true);
///*     */     }
///*     */     else {
///* 144 */       Object valueobj = getBillCardPanel().getBillData().getHeadItem("fcapacitycalc").getValueObject();
///* 145 */       this.billCardPanel.setTabEnabled(1, "prodinv_items", false);
///* 146 */       this.billCardPanel.setTabEnabled(1, "replace_items", false);
///* 147 */       getBillCardPanel().getHeadItem("nastnum").setEdit(false);
///* 148 */       getBillCardPanel().getHeadItem("ntime").setEdit(false);
///* 149 */       getBillCardPanel().getHeadItem("castunitid").setEdit(false);
///*     */     }
/*     */   }
/*     */   
/*     */   public void initUI()
/*     */   {
/* 155 */     super.initUI();
///* 156 */     UIRefPane panel = (UIRefPane)getBillCardPanel().getBodyItem("subst_items", "csubstitutewkid").getComponent();
///*     */     
///*     */ 
///* 159 */     panel.setMultiSelectedEnabled(true);
///* 160 */     panel = (UIRefPane)getBillCardPanel().getBodyItem("wip_items", "cstordocid").getComponent();
///* 161 */     panel.setMultiSelectedEnabled(true);
///* 162 */     panel = (UIRefPane)getBillCardPanel().getBodyItem("prodinv_items", "cmaterialvid").getComponent();

/* 156 */     UIRefPane panel = (UIRefPane)getBillCardPanel().getBodyItem("mater", "pk_material").getComponent();
/*     */     
/*     */ 
/* 159 */     panel.setMultiSelectedEnabled(true);
/* 160 */     panel = (UIRefPane)getBillCardPanel().getBodyItem("cust", "pk_customer").getComponent();
/* 161 */     panel.setMultiSelectedEnabled(true);
/* 162 */     panel = (UIRefPane)getBillCardPanel().getBodyItem("dept", "pk_dept").getComponent();
///*     */     
///*     */ 
/* 165 */     panel.setMultiSelectedEnabled(true);
/*     */     
/* 167 */     getBillCardPanel().setBodyAutoAddLine("mater", false);
/*     */     
/* 169 */     AggMaschineVO vo = (AggMaschineVO)getModel().getSelectedData();
/* 170 */     if (vo != null) {
/* 171 */       getBillCardPanel().getBillData().setHeaderValueVO(vo.getParentVO());
///* 172 */       getBillCardPanel().getBillData().loadEditHeadRelation("cwkid");
/*     */     }
/*     */     
/* 175 */     scaleProcess();
/*     */     
/*     */ 
/*     */ 
/* 179 */     getBillCardPanel().getBillModel(this.wip_tableCode).sortByColumn("pk_material", true);
/*     */   }
/*     */   
			
/*     */   private void scaleProcess()
/*     */   {
				//对数量字段的小数位的精度监听创建和控制
///* 184 */     BillItem item = getBillCardPanel().getBodyItem("prodinv_items", "nastnum");
///* 185 */     BillItem item2 = getBillCardPanel().getBodyItem("prodinv_items", "nnum");
///* 186 */     item.addDecimalListener(new PDWkDecimalCastunitidListener());
///* 187 */     item2.addDecimalListener(new PDWkDecimalCastidListener());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void setPreValue()
/*     */   {
				//换产前后的物料编码记录
///* 201 */     int rowCount = getBillCardPanel().getBillModel().getRowCount();
///* 202 */     for (int i = 0; i < rowCount; i++) {
///* 203 */       Object value = getBillCardPanel().getBodyValueAt(0, "cprematerialvid");
///* 204 */       Object value2 = getBillCardPanel().getBodyValueAt(0, "caftermaterialvid");
///* 205 */       getBillCardPanel().setBodyValueAt(value, 0, "cprematrelatshow");
///* 206 */       getBillCardPanel().setBodyValueAt(value2, 0, "caftermatrelatshow");
///*     */     }
///* 208 */     BillItem item = getBillCardPanel().getBodyItem("cprematrelatshow");
///* 209 */     BillItem item2 = getBillCardPanel().getBodyItem("caftermatrelatshow");
///* 210 */     if ((item == null) || (item2 == null)) {
///* 211 */       return;
///*     */     }
///* 213 */     MetaDataPropertyAdpter adapter = (MetaDataPropertyAdpter)item.getMetaDataProperty();
///*     */     
///* 215 */     IAttribute attribute = adapter.getAttribute();
///*     */     
///* 217 */     MetaDataPropertyAdpter newadapter = new MetaDataPropertyAdpter(attribute, false);
///*     */     
///* 219 */     item.setMetaDataProperty(newadapter);
///*     */     
///* 221 */     getBillCardPanel().getBillModel("replace_items").loadLoadRelationItemValue();
/*     */   }
/*     */   
///*     */   private void addUnitId(Object object)
///*     */   {
				//生产线物料的  计量单位  字段控制
///* 226 */     AggMaschineVO aggMaschineVO = (AggMaschineVO)object;
///* 227 */     if (!MMValueCheck.isEmpty(aggMaschineVO)) {
///* 228 */       WkProdinvVO[] prodinvItemVOs = (WkProdinvVO[])AggMaschineVO.getChildren(WkProdinvVO.class);
///*     */       
///* 230 */       if (MMArrayUtil.isEmpty(prodinvItemVOs)) {
///* 231 */         return;
///*     */       }
///* 233 */       for (WkProdinvVO itemVO : prodinvItemVOs) {
///* 234 */         String cAstUnitId = itemVO.getCastunitid();
///* 235 */         if (cAstUnitId == null) {
///* 236 */           itemVO.setCastunitid("@@@@@@@@");
///*     */         }
///*     */       }
///*     */     }
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void onAdd()
/*     */   {
///* 248 */     setEditProperty(true);
/* 249 */     super.onAdd();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void onEdit()
/*     */   {
///* 255 */     setEditProperty(false);
/*     */     
/*     */ 	  String status=this.getBillCardPanel().getHeadItem("mstatus").getValue();
/* 258 */     super.onEdit();
			  SetItemEnableStatusWhenEdit(status);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void getOrgVid()
/*     */   {
/* 307 */     String pk_org = getModel().getContext().getPk_org();
/* 308 */     if (!StringUtil.isEmptyWithTrim(pk_org)) {
/* 309 */       setHeadValue("pk_org_v", OrgUnitPubService.getNewVIDByOrgID(pk_org));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setHeadValue(String key, Object value)
/*     */   {
/* 322 */     getBillCardPanel().setHeadItem(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setDefaultValue()
/*     */   {
/* 345 */     execLoadFormula();

			//下属内容自己写的  
			  super.setDefaultValue();
			  
			  IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
			  
			  UFDate busidate = AppContext.getInstance().getBusiDate();
			  
			  keyValue.setHeadValue("creationtime", busidate);
			  keyValue.setHeadValue("mstatus", MachStatus.UnEnable.value().toString());

			  
//			  keyValue.setHeadValue("creator", busidate);
			  

			  //设置不可编辑字段
			  this.billCardPanel.getHeadItem("pk_group").setEnabled(false);
			  this.billCardPanel.getHeadItem("pk_org").setEnabled(false);
			  this.billCardPanel.getHeadItem("pk_org_v").setEnabled(false);
			  this.billCardPanel.getHeadItem("mstatus").setEnabled(false);
			  
			  
			  this.billCardPanel.getHeadTailItem("creationtime").setEnabled(false);
			  this.billCardPanel.getHeadTailItem("creator").setEnabled(false);			  
			  this.billCardPanel.getHeadTailItem("modifier").setEnabled(false);
			  this.billCardPanel.getHeadTailItem("modifiedtime").setEnabled(false);

			  
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void setEditProperty(boolean b)
///*     */   {
				//判断表头cwkid是否满足条件，并赋值。。。不使用
///* 355 */     if ((this.cntEditFields == null) || (this.cntEditFields.length == 0)) {
///* 356 */       return;
///*     */     }
///* 358 */     for (String field : this.cntEditFields) {
///* 359 */       getBillCardPanel().getHeadItem(field).setEdit(b);
///*     */     }
///*     */   }
			private void SetItemEnableStatusWhenEdit(String status) {
				
				if("1".equals(status)){
					
					this.getBillCardPanel().getHeadItem("pk_group").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("pk_org").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("pk_org_v").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("pk_maschine").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("machcode").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("machname").setEnabled(false);
										
					this.getBillCardPanel().getHeadItem("mstatus").setEnabled(false);															
					
					this.billCardPanel.getHeadTailItem("creator").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("creationtime").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("modifier").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("modifiedtime").setEnabled(false);
					
					ShowStatusBarMsgUtil.showStatusBarMsg("数据已经启用，部分关键数据不能修改",this.getModel().getContext());
					
				}else if("0".equals(status)){
					
					this.getBillCardPanel().getHeadItem("pk_group").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("pk_org").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("pk_org_v").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("pk_maschine").setEnabled(false);
					
					this.getBillCardPanel().getHeadItem("mstatus").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("creator").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("creationtime").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("modifier").setEnabled(false);
					
					this.billCardPanel.getHeadTailItem("modifiedtime").setEnabled(false);
				}
			}
/*     */   
/*     */   public void initRef()
/*     */   {
///* 365 */     setDefRef(getBillCardPanel().getHeadItem("ccalendarid"));
///* 366 */     setDefRef(getBillCardPanel().getHeadItem("cwkid"));
///* 367 */     setShiftRef(getBillCardPanel().getHeadItem("cshiftclassid"));
///* 368 */     setDefRef(getBillCardPanel().getBodyItem("subst_items", "csubstitutewkid"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 	//表头字段   能力核算值  值为 机时  字段设备利用率    可以为空
///*     */   public void setDefaultEditProperty()
///*     */   {
///* 377 */     this.billCardPanel.setTabEnabled(1, "prodinv_items", false);
///* 378 */     this.billCardPanel.setTabEnabled(1, "replace_items", false);
///*     */     
///* 380 */     if (this.billCardPanel.getBodyTabbedPane().getTabCount() > 1) {
///* 381 */       this.billCardPanel.getBodyTabbedPane().setSelectedComponent(this.billCardPanel.getBodyPanel("subst_items"));
///*     */     }
///*     */     
///*     */ 
///* 385 */     if (this.billCardPanel.getHeadItem("fcapacitycalc").getValueObject() == Integer.valueOf(1)) {
///* 386 */       this.billCardPanel.getHeadItem("nequipfactor").setNull(true);
///*     */     }
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 	//参照字段根据单据的表头pk_org和pk_group进行过滤
/*     */   private void setDefRef(BillItem item)
/*     */   {
/* 403 */     String pkGroup = getModel().getContext().getPk_group();
/* 404 */     String pkOrg = getModel().getContext().getPk_org();
/* 405 */     FilterDefaultRefUtils cdeptidRefUtils = new FilterDefaultRefUtils((UIRefPane)item.getComponent());
/* 406 */     cdeptidRefUtils.filterItemRefByGroup(pkGroup);
/* 407 */     cdeptidRefUtils.filterItemRefByOrg(pkOrg);
/*     */   }
/*     */   
/*     */ 
/*     */   //参照字段  班次  的过滤条件
/*     */   private void setShiftRef(BillItem item)
/*     */   {
/* 414 */     String pkGroup = getModel().getContext().getPk_group();
/* 415 */     String pkOrg = getModel().getContext().getPk_org();
/* 416 */     UIRefPane shift = (UIRefPane)item.getComponent();
/* 417 */     shift.getRefModel().setPk_group(pkGroup);
/* 418 */     shift.getRefModel().setPk_org(pkOrg);
/* 419 */     shift.setWhereString("enablestate".concat("= 2 ") + " and " + "iscapadjust".concat("='Y'"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, List<String>> getVisibleBodyRefMap()
/*     */   {
/* 448 */     return this.visibleBodyRefMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVisibleBodyRefMap(Map<String, List<String>> visibleBodyRefMap)
/*     */   {
/* 458 */     this.visibleBodyRefMap = visibleBodyRefMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<String> getVisibleHeadRefList()
/*     */   {
/* 467 */     return this.visibleHeadRefList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setVisibleHeadRefList(List<String> visibleRefList)
/*     */   {
/* 477 */     this.visibleHeadRefList = visibleRefList;
/*     */   }
/*     */ }
