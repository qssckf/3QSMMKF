package nc.ui.so.qs.sc.maschine.service;
/*    */ 
/*    */ import nc.bs.framework.common.NCLocator;
///*    */ import nc.itf.pd.pd0404.IPDWkQuery;
		import nc.itf.so.qs.sc.maschine.service.IMaschineQuery;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;

import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
/*    */ import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
//import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
//import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.qs.sc.AggMaschineVO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
///*    */ public class MaschineQueryService
///*    */   implements IQueryService,IPaginationInitQuery,IBillPageQuery {
	
/*    */ public class MaschineQueryService
/*    */   implements IQueryService {	
/*    */   public MaschineQueryService() {}
			private IMaschineQuery service;
			
			public IMaschineQuery getService(){
				if(this.service == null){
					this.service = NCLocator.getInstance().lookup(IMaschineQuery.class);
				}
				return service;
			}

/*    */   public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception
/*    */   {
/* 19 */     return getService().queryByQueryScheme(queryScheme);
/*    */   }
/*    */
//@Override
//public Object[] queryObjectByPks(String[] pks) throws BusinessException {
//	// TODO 自动生成的方法存根
//	AggMaschineVO[] bill=null;
//	
//	try{
//		
//		bill=this.getService().queryM30App(pks);
//		
//	}catch(BusinessException ex){
//		ExceptionUtils.wrappException(ex);
//	}
//	
//	return bill;
//}
//@Override
//public PageQueryVO query(IQueryScheme scheme) {
//	// TODO 自动生成的方法存根
//	PageQueryVO page = null;
//	
//	try{
//		
//		page = getService().queryM30App(scheme);
//		
//	}catch(BusinessException ex){
//		ExceptionUtils.wrappException(ex);
//	}
//	
//	return page;
//}
//@Override
//public Integer getPageSize() {
//	// TODO 自动生成的方法存根
//	return Integer.valueOf(10);
//}
//@Override
//public PaginationQueryVO queryPaginationQueryVO(String paramString, Integer paramInteger, Object... paramVarArgs) throws BusinessException {
//	// TODO 自动生成的方法存根
//	return null;
//}
}
