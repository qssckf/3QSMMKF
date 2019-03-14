package nc.ui.so.qs.sc.maschine.view;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import nc.ui.mmf.framework.action.ListBodyTableExtendAction;
///*     */ import nc.ui.pd.pd0404.scale.PDWkBodyScaleUtil;
///*     */ import nc.ui.pd.pd0404.scale.PDWkDecimalCastidListener;
///*     */ import nc.ui.pd.pd0404.scale.PDWkDecimalCastunitidListener;
///*     */ import nc.ui.pd.pd0404.scale.PDWkScaleUtil;
/*     */ import nc.ui.pub.beans.UITable;
/*     */ import nc.ui.pub.bill.BillItem;
/*     */ import nc.ui.pub.bill.BillListData;
/*     */ import nc.ui.pub.bill.BillListPanel;
/*     */ import nc.ui.pub.bill.BillModel;
/*     */ import nc.ui.pub.bill.BillTabbedPane;
/*     */ import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.qs.sc.maschine.scale.MaschineBodyScaleUtil;
import nc.ui.so.qs.sc.maschine.scale.MaschineScaleUtil;
/*     */ import nc.ui.uif2.AppEvent;
/*     */ import nc.ui.uif2.model.BillManageModel;
/*     */ import nc.util.mmf.framework.base.MMArrayUtil;
/*     */ import nc.util.mmf.framework.base.MMValueCheck;
/*     */ import nc.vo.pd.pd0404.entity.AggWkVO;
/*     */ import nc.vo.pd.pd0404.entity.WkProdinvVO;
import nc.vo.uif2.LoginContext;
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
/*     */ public class MaschineBillListView extends ShowUpableBillListView {
/*     */   private static final long serialVersionUID = 632212855802408L;
/*  40 */   private String wip_tableCode = "mater";
			private String head_tableCode="MaschineVO";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  45 */   private List<? extends ListBodyTableExtendAction> bodyLineActions = Collections.EMPTY_LIST;
/*     */   private MaschineScaleUtil maschineScaleUtil;
/*     */   private MaschineBodyScaleUtil maschineBodyScaleUtil;
/*     */   
/*     */   public MaschineBillListView() {}
/*     */   
/*     */   public List<? extends ListBodyTableExtendAction> getBodyLineActions()
/*     */   {
/*  53 */     return this.bodyLineActions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBodyLineActions(List<? extends ListBodyTableExtendAction> bodyLineActions)
/*     */   {
/*  63 */     this.bodyLineActions = bodyLineActions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void sortViewData()
/*     */   {
///*  70 */     new MaschineListViewSort(getBillListPanel()).addHeadSortItem("pk_org", true).addHeadSortItem("cwkid", true).sort();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void handleEvent(AppEvent event)
/*     */   {
/*  77 */     super.handleEvent(event);
///*  78 */     if ("Model_Initialized".equals(event.getType())) {
///*  79 */       sortViewData();
///*     */     }
/*     */     
///*  82 */     if ("Selection_Changed".equals(event.getType())) {
///*  83 */       getBillListPanel().getBodyBillModel("replace_items").execLoadFormulasByKey("cprematrelatshow");
///*     */       
///*  85 */       getBillListPanel().getBodyBillModel("replace_items").execLoadFormulasByKey("caftermatrelatshow");
///*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void initUI()
/*     */   {
/*  93 */     super.initUI();
/*     */     
/*  95 */     BillListPanel listPanel = getBillListPanel();
/*  96 */     String[] tableCodes = listPanel.getBillListData().getBodyTableCodes();
/*  97 */     if (tableCodes != null) {
/*  98 */       for (String tableCode : tableCodes) {
/*  99 */         listPanel.getBodyTable(tableCode).setSelectionMode(2);
/*     */       }
/*     */     }
/* 102 */     if (getBodyLineActions().size() > 0) {
/* 103 */       getBillListPanel().getBodyTabbedPane().addTabActions(getBodyLineActions());
/* 104 */       for (ListBodyTableExtendAction action : getBodyLineActions()) {
/* 105 */         action.setEnabled(false);
/* 106 */         action.setListPanel(getBillListPanel());
/* 107 */         action.setBillModel(getModel());
/*     */       }
/*     */     }
/*     */     
/* 111 */     getMaschineScaleUtil().setListScale(getBillListPanel(), getModel().getContext().getPk_group(), getModel().getContext().getPk_org());
/*     */     
///* 113 */     scaleProcess();
/*     */     
/*     */ 
/*     */ 		//ап╠МобеепР
///* 117 */     getBillListPanel().getBodyBillModel(this.wip_tableCode).sortByColumn("pk_material", true);
				getBillListPanel().getHeadBillModel().sortByColumn("machcode", true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   public void scaleProcess()
///*     */   {
///* 129 */     BillItem item = getBillListPanel().getBodyItem("prodinv_items", "nastnum");
///* 130 */     BillItem item2 = getBillListPanel().getBodyItem("prodinv_items", "nnum");
///* 131 */     BillItem item3 = getBillListPanel().getBodyItem("prodinv_items", "vchangerate");
///* 132 */     item.addDecimalListener(new PDWkDecimalCastunitidListener());
///* 133 */     item2.addDecimalListener(new PDWkDecimalCastidListener());
///* 134 */     item3.addDecimalListener(new PDWkDecimalCastunitidListener());
///*     */   }
/*     */   
/*     */   protected void synchronizeDataFromModel()
/*     */   {
/* 139 */     String pk_org = getModel().getContext().getPk_org();
/* 140 */     getMaschineBodyScaleUtil().setListTimeScale(getBillListPanel(), pk_org);
/* 141 */     super.synchronizeDataFromModel();
/* 142 */     Object[] datas = getModel().getData().toArray();
/* 143 */     super.synchronizeDataFromModel();
///* 144 */     for (Object data : datas) {
///* 145 */       setValue(data);
///*     */     }
/*     */   }
/*     */   
///*     */   public void setValue(Object object)
///*     */   {
///* 151 */     addUnitId(object);
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void addUnitId(Object object)
///*     */   {
///* 160 */     AggWkVO aggWkVO = (AggWkVO)object;
///* 161 */     WkProdinvVO[] prodinvItemVOs = (WkProdinvVO[])aggWkVO.getChildren(WkProdinvVO.class);
///* 162 */     if (!MMArrayUtil.isEmpty(prodinvItemVOs)) {
///* 163 */       for (WkProdinvVO itemVO : prodinvItemVOs) {
///* 164 */         String cAstUnitId = itemVO.getCastunitid();
///* 165 */         String cUnitId = itemVO.getCunitid();
///* 166 */         if (cAstUnitId == null) {
///* 167 */           itemVO.setCastunitid("@@@@@@@@");
///*     */         }
///* 169 */         if (cUnitId == null) {
///* 170 */           itemVO.setCunitid("$$$$$$$$");
///*     */         }
///*     */       }
///*     */     }
///*     */   }
/*     */   
/*     */   protected void syschronizeSelectedRowUpdate()
/*     */   {
///* 178 */     AggWkVO aggvo = (AggWkVO)getModel().getSelectedData();
///* 179 */     if ((!MMValueCheck.isEmpty(aggvo)) && 
///* 180 */       (aggvo.getChildrenVO() != null)) {
///* 181 */       WkProdinvVO[] itemvos = (WkProdinvVO[])aggvo.getTableVO("prodinv_items");
///* 182 */       if (!MMArrayUtil.isEmpty(itemvos)) {
///* 183 */         for (WkProdinvVO itemVO : itemvos) {
///* 184 */           String cAstUnitId = itemVO.getCastunitid();
///* 185 */           String cUnitId = itemVO.getCunitid();
///* 186 */           if (cAstUnitId == null) {
///* 187 */             itemVO.setCastunitid("@@@@@@@@");
///*     */           }
///* 189 */           if (cUnitId == null) {
///* 190 */             itemVO.setCunitid("$$$$$$$$");
///*     */           }
///*     */         }
///*     */       }
///*     */     }
///*     */     
/* 196 */     super.syschronizeSelectedRowUpdate();
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
/*     */   public MaschineScaleUtil getMaschineScaleUtil()
/*     */   {
/* 210 */     return this.maschineScaleUtil;
/*     */   }
/*     */   
/*     */   public void setMaschineScaleUtil(MaschineScaleUtil maschineScaleUtil) {
/* 214 */     this.maschineScaleUtil = maschineScaleUtil;
/*     */   }
/*     */   
/*     */   public MaschineBodyScaleUtil getMaschineBodyScaleUtil() {
/* 218 */     return this.maschineBodyScaleUtil;
/*     */   }
/*     */   
/*     */   public void setMaschineBodyScaleUtil(MaschineBodyScaleUtil maschineBodyScaleUtil) {
/* 222 */     this.maschineBodyScaleUtil = maschineBodyScaleUtil;
/*     */   }
/*     */ }
