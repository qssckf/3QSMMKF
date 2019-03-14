package nc.ui.so.qs.sc.maschine.model;
/*    */ 
/*    */ import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator;
/*    */ import nc.ui.querytemplate.filter.IFilter;
/*    */ import nc.ui.querytemplate.querytree.IQueryScheme;
/*    */ import nc.ui.querytemplate.value.IFieldValue;
//import nc.ui.scmpub.page.model.IBillPageQuery;
/*    */ import nc.ui.uif2.IShowMsgConstant;
/*    */ import nc.ui.uif2.ShowStatusBarMsgUtil;
/*    */ import nc.ui.uif2.model.AbstractUIAppModel;
/*    */ import nc.ui.uif2.model.ModelDataDescriptor;
import nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel;
/*    */ import nc.vo.ml.AbstractNCLangRes;
///*    */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*    */ import nc.vo.pd.pd0404.entity.WkVO;
/*    */ import nc.vo.pub.lang.UFDouble;
/*    */ import nc.vo.pubapp.res.Variable;
//import nc.vo.scmpub.page.PageQueryVO;
		import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
import nc.ui.uif2.components.pagination.PaginationModel;

//		import nc.vo.uif2.LoginContext;
/*    */ 
/*    */ public class MaschineModelDataManager extends nc.ui.pubapp.uif2app.query2.model.ModelDataManager
/*    */ {
/*    */   public MaschineModelDataManager() {}

			private BillModelPaginationDelegator pageDelegator;
//			private IBillPageQuery pageQuery;
			private CardLayoutToolbarPanel pagePanel;
/*    */   
/*    */   public void initModelByQueryScheme(IQueryScheme qryScheme)
/*    */   {
/* 24 */     setQueryScheme(qryScheme);
/*    */     try {
/* 26 */       IFilter[] filters = (IFilter[])qryScheme.get("filters");
/* 27 */       String pk_org = getPk_org(filters);
/* 28 */       getModel().getContext().setPk_org(pk_org);
/* 29 */       Object[] objs = getQryService().queryByQueryScheme(qryScheme);
/* 30 */       if ((objs != null) && (objs.length == Variable.getMaxQueryCount())) {
/* 31 */         String hint = NCLangRes.getInstance().getStrByID("5001001_0", "05001001-0350", null, new String[] { String.valueOf(Variable.getMaxQueryCount()) });
/*    */         
/*    */ 
/* 34 */         nc.ui.pub.beans.MessageDialog.showHintDlg(getModel().getContext().getEntranceUI(), null, hint);
/*    */       }
/* 36 */       if (getModelDataProcessor() != null) {
/* 37 */         objs = getModelDataProcessor().processModelData(qryScheme, objs);
/*    */       }
/* 39 */       String schemeName = qryScheme.getName();
/* 40 */       if (!nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(schemeName)) {
/* 41 */         ModelDataDescriptor modelDataDescriptor = new ModelDataDescriptor(schemeName);
/* 42 */         getModel().initModel(objs, modelDataDescriptor);
/*    */       }
/*    */       else {
/* 45 */         ModelDataDescriptor modelDataDescriptor = new ModelDataDescriptor(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0144"));
/*    */         
/*    */ 
/*    */ 
/* 49 */         AggMaschineVO[] vos = (AggMaschineVO[])objs;
/*    */         
///* 51 */         if (vos != null) {
///* 52 */           for (AggMaschineVO vo : vos) {
///* 53 */             MaschineVO parentVO = (MaschineVO)vo.getParentVO();
///* 54 */             UFDouble ntime = parentVO.getNtime() == null ? UFDouble.ZERO_DBL : parentVO.getNtime();
///* 55 */             UFDouble nastnum = parentVO.getNastnum() == null ? UFDouble.ZERO_DBL : parentVO.getNastnum();
///* 56 */             UFDouble nprodfactor = ntime == UFDouble.ZERO_DBL ? null : nastnum.div(ntime);
///* 57 */             UFDouble nprodbeat = nastnum == UFDouble.ZERO_DBL ? null : ntime.div(nastnum);
///* 58 */             parentVO.setAttributeValue("nprodfactor", nprodfactor);
///* 59 */             parentVO.setAttributeValue("nprodbeat", nprodbeat);
///*    */           }
///*    */         }
/*    */         
/* 63 */         getModel().initModel(objs, modelDataDescriptor);
/*    */       }
/*    */       
/* 66 */       if (!nc.util.mmf.framework.base.MMArrayUtil.isEmpty(objs)) {
/* 67 */         ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getQuerySuccessInfo(objs.length), getModel().getContext());
/*    */ 
/*    */       }
/*    */       else
/*    */       {
/* 72 */         ShowStatusBarMsgUtil.showStatusBarMsg(IShowMsgConstant.getQueryNullInfo(), getModel().getContext());
/*    */       }
/*    */       
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 78 */       throw new nc.vo.pub.BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0007"), e);
/*    */     }
/*    */   }
/*    */   
/*    */   private String getPk_org(IFilter[] filters) {
/* 83 */     for (IFilter filter : filters) {
/* 84 */       if (filter.getFilterMeta().getFieldCode().equals("pk_org")) {
/* 85 */         return getFilterValue(filter);
/*    */       }
/*    */     }
/* 88 */     return null;
/*    */   }
/*    */   
/*    */   private String getFilterValue(IFilter filter) {
/* 92 */     String pk_org = null;
/* 93 */     if (!nc.util.mmf.framework.base.MMValueCheck.isEmpty(filter.getFieldValue().getFieldValues())) {
/* 94 */       pk_org = ((nc.ui.querytemplate.value.IFieldValueElement)filter.getFieldValue().getFieldValues().get(0)).getSqlString();
/*    */     }
/*    */     
/* 97 */     return pk_org;
/*    */   }
/*    */
//		public IBillPageQuery getPageQuery() {
//			return pageQuery;
//		}
//		public void setPageQuery(IBillPageQuery pageQuery) {
//			this.pageQuery = pageQuery;
//		}
//		public CardLayoutToolbarPanel getPagePanel() {
//			return pagePanel;
//		}
//		public void setPagePanel(CardLayoutToolbarPanel pagePanel) {
//			this.pagePanel = pagePanel;
//		}
//		private PageQueryVO query(IQueryScheme scheme){
//			
//			PaginationModel pageModel = this.pageDelegator.getPaginationModel();
//			
//			int recordInPage = pageModel.getPageSize();
//			
//			scheme.put("recordInPage", Integer.valueOf(recordInPage));
//			
//			int max = -1;
//			
//			scheme.put("max_query_count_constant", Integer.valueOf(max));
//			
//			return this.getPageQuery().query(scheme);
//			
//		}

}
