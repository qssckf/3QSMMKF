package nc.ui.so.qs.sc.m38.billref.fq01;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
/*     */ import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
/*     */ import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
/*     */ import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
/*     */ import nc.ui.scmpub.query.refregion.QCustomerFilter;
/*     */ import nc.ui.scmpub.query.refregion.QDeptFilter;
/*     */ import nc.ui.scmpub.query.refregion.QMarSaleClassFilter;
/*     */ import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
/*     */ import nc.ui.scmpub.query.refregion.QMarterialFilter;
/*     */ import nc.ui.scmpub.query.refregion.QPsndocFilter;
/*     */ import nc.ui.scmpub.query.refregion.QStockOrgFilter;
/*     */ import nc.ui.scmpub.query.refregion.QTransTypeFilter;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.querytemplate.TemplateInfo;
/*     */ import nc.vo.scmpub.res.billtype.SOBillType;
/*     */ 
/*     */ 
/*     */ public class FQ01Ref38BillReferQuery
/*     */   extends DefaultBillReferQuery
/*     */ {
/*     */   public FQ01Ref38BillReferQuery(Container c, TemplateInfo info)
/*     */   {
/*  26 */     super(c, info);
/*     */   }
/*     */   
/*     */   public void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator)
/*     */   {
/*  31 */     setDefaultPk_org(dlgDelegator);
/*  32 */     initFilterRef(dlgDelegator);
/*  33 */     initBodyRedundancyItem(dlgDelegator);
/*     */     
/*     */ 
/*  36 */     dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] { "pk_org" });
/*     */     
/*     */ 
/*     */ 
/*  40 */     dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
/*     */     
/*  42 */     dlgDelegator.setPowerEnable(true);
/*     */   }
/*     */   
/*     */   private void initBodyRedundancyItem(QueryConditionDLGDelegator dlgDelegator)
/*     */   {
/*  47 */     dlgDelegator.addRedundancyInfo("pk_org", "so_preorder_b.pk_org");
/*     */     
/*  49 */     dlgDelegator.addRedundancyInfo("dbilldate", "so_preorder_b.dbilldate");
/*     */   }
/*     */   
/*     */ 
/*     */   private void initFilterRef(QueryConditionDLGDelegator dlgDelegator)
/*     */   {
/*  55 */     QTransTypeFilter trantype = new QTransTypeFilter(dlgDelegator, SOBillType.PreOrder.getCode());
/*     */     
/*  57 */     trantype.filter();
/*     */     
/*     */ 
/*  60 */     QCustomerFilter invoicecust = new QCustomerFilter(dlgDelegator, "ccustomerid");
/*     */     
/*  62 */     invoicecust.addEditorListener();
/*     */     
/*     */ 
/*  65 */     QDeptFilter dept = new QDeptFilter(dlgDelegator, "cdeptid");
/*  66 */     dept.setPk_orgCode("pk_org");
/*  67 */     dept.addEditorListener();
/*     */     
/*     */ 
/*  70 */     QMarbasclassFilter marclass = new QMarbasclassFilter(dlgDelegator, "so_preorder_b.cmaterialid.pk_marbasclass");
/*     */     
/*     */ 
/*  73 */     marclass.addEditorListener();
/*     */     
/*     */ 
/*  76 */     QMarSaleClassFilter marSaleClass = new QMarSaleClassFilter(dlgDelegator, "so_preorder_b.cmaterialvid.materialsale.pk_marsaleclass");
/*     */     
/*     */ 
/*  79 */     marSaleClass.setPk_orgCode("pk_org");
/*  80 */     marSaleClass.addEditorListener();
/*     */     
/*     */ 
/*  83 */     QMarterialFilter marteral = new QMarterialFilter(dlgDelegator, "pk_org", "so_preorder_b.cmaterialid.code");
/*     */     
/*     */ 
/*  86 */     marteral.addEditorListener();
/*     */     
/*     */ 
/*  89 */     QPsndocFilter employee = new QPsndocFilter(dlgDelegator, "cemployeeid");
/*     */     
/*  91 */     employee.setPk_orgCode("pk_org");
/*  92 */     employee.addEditorListener();
/*     */     
/*     */ 
/*  95 */     QStockOrgFilter stockOrg = new QStockOrgFilter(dlgDelegator, "so_preorder_b.csendstockorgid");
/*     */     
/*  97 */     stockOrg.filter();
/*     */   }
/*     */   
/*     */   private void setDefaultPk_org(QueryConditionDLGDelegator dlgDelegator) {
/* 101 */     String defaultOrg = null;
/*     */     try {
/* 103 */       defaultOrg = DefaultDataSettingAccessor.getDefaultSaleOrg();
/*     */     }
/*     */     catch (Exception ex) {
/* 106 */       ExceptionUtils.wrappException(ex);
/*     */     }
/* 108 */     if ((defaultOrg != null) && (defaultOrg.trim().length() > 0)) {
/* 109 */       dlgDelegator.setDefaultValue("pk_org", defaultOrg);
/*     */     }
/*     */   }
/*     */ }