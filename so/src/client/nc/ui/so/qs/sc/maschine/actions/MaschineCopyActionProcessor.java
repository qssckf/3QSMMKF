package nc.ui.so.qs.sc.maschine.actions;
/*     */ 
/*     */ import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
/*     */ import nc.ui.uif2.editor.BillForm;
/*     */ import nc.util.mmf.busi.service.OrgUnitPubService;
/*     */ import nc.util.mmf.framework.base.MMArrayUtil;
///*     */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*     */ import nc.vo.pd.pd0404.entity.WkProdinvVO;
///*     */ import nc.vo.pd.pd0404.entity.WkRepltimeVO;
///*     */ import nc.vo.pd.pd0404.entity.WkSubstVO;
///*     */ import nc.vo.pd.pd0404.entity.WkVO;
///*     */ import nc.vo.pd.pd0404.entity.WkWipVO;
		  import nc.vo.so.qs.sc.AggMaschineVO;
		  import nc.vo.uif2.LoginContext;
		  import nc.vo.so.qs.sc.MaschineVO;
		  import nc.vo.so.qs.sc.MachineMater_Mater;
		  import nc.vo.so.qs.sc.MachineCustomer;
		  import nc.vo.so.qs.sc.MachineDept;
/*     */ 
/*     */ 
/*     */ public class MaschineCopyActionProcessor
/*     */   implements ICopyActionProcessor<AggMaschineVO>
/*     */ {
/*  19 */   private BillForm editor = null;
/*     */   
/*     */   public MaschineCopyActionProcessor() {}
/*     */   
/*  23 */   public void processVOAfterCopy(AggMaschineVO billVO, LoginContext context) { 
			  String pk_org_v = OrgUnitPubService.getNewVIDByOrgID(((MaschineVO)billVO.getParentVO()).getPk_org());
/*     */     
/*  25 */     initHeadItem((MaschineVO)billVO.getParentVO(), pk_org_v);
/*     */     
/*  27 */     initMachineMater_MaterVO((MachineMater_Mater[])billVO.getChildren(MachineMater_Mater.class), pk_org_v);
/*     */     
/*  29 */     initMachineCustomerVO((MachineCustomer[])billVO.getChildren(MachineCustomer.class), pk_org_v);
/*     */     
/*  31 */     initMachineDeptVO((MachineDept[])billVO.getChildren(MachineDept.class), pk_org_v);
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void initHeadItem(MaschineVO headVO, String pk_org_v)
/*     */   {
/*  40 */     headVO.setAttributeValue("pk_maschine", null);
			  headVO.setAttributeValue("machcode", null);
			  headVO.setAttributeValue("mstatus", '0');
///*  41 */     headVO.setAttributeValue("cwkid", null);
/*  42 */     headVO.setAttributeValue("modifier", null);
/*  43 */     headVO.setAttributeValue("modifiedtime", null);
/*  44 */     headVO.setAttributeValue("creator", null);
/*  45 */     headVO.setAttributeValue("creationtime", null);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */     headVO.setPk_org_v(pk_org_v);
/*     */   }
/*     */   
/*     */   private void initMachineMater_MaterVO(MachineMater_Mater[] machineMater_MaterItems, String pk_org_v)
/*     */   {
/*  57 */     if (MMArrayUtil.isEmpty(machineMater_MaterItems)) {
/*  58 */       return;
/*     */     }
/*  60 */     for (MachineMater_Mater machineMater_MaterItem : machineMater_MaterItems) {
/*  61 */       machineMater_MaterItem.setAttributeValue("pk_maschine", null);
/*  62 */       machineMater_MaterItem.setAttributeValue("pk_mach_mater", null);
///*  63 */       machineMater_MaterItem.setAttributeValue("pk_org_v", pk_org_v);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void initMachineCustomerVO(MachineCustomer[] machineCustomerItems, String pk_org_v)
/*     */   {
/*  71 */     if (MMArrayUtil.isEmpty(machineCustomerItems)) {
/*  72 */       return;
/*     */     }
/*  74 */     for (MachineCustomer machineCustomerItem : machineCustomerItems) {
/*  75 */       machineCustomerItem.setAttributeValue("pk_maschine", null);
/*  76 */       machineCustomerItem.setAttributeValue("pk_mach_cus", null);
///*  77 */       machineCustomerItem.setAttributeValue("pk_org_v", pk_org_v);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void initMachineDeptVO(MachineDept[] machineDeptItems, String pk_org_v)
/*     */   {
/*  85 */     if (MMArrayUtil.isEmpty(machineDeptItems)) {
/*  86 */       return;
/*     */     }
/*  88 */     for (MachineDept machineDeptItem : machineDeptItems) {
/*  89 */       machineDeptItem.setAttributeValue("pk_maschine", null);
/*  90 */       machineDeptItem.setAttributeValue("pk_mache_dept", null);
///*  91 */       machineDeptItem.setAttributeValue("pk_org_v", pk_org_v);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   
/*     */   public BillForm getEditor() {
/* 110 */     return this.editor;
/*     */   }
/*     */   
/*     */   public void setEditor(BillForm editor) {
/* 114 */     this.editor = editor;
/*     */   }
/*     */ }
