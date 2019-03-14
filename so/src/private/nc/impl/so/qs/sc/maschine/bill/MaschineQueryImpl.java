package nc.impl.so.qs.sc.maschine.bill;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import nc.bs.framework.common.NCLocator;
//import nc.bs.scmpub.page.BillPageLazyQuery;
//import nc.bs.scmpub.tool.SchemeAppendCondition;
//import nc.bs.scmpub.tool.SchemeFixCondition;
/*     */ import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
/*     */ import nc.itf.bd.defdoc.IDefdoclistQryService;
/*     */ import nc.itf.bd.userdefitem.IUserdefitemQryService;
///*     */ import nc.itf.pd.pd0404.IPDWkQuery;
/*     */ import nc.md.persist.framework.IMDPersistenceQueryService;
/*     */ import nc.md.persist.framework.MDPersistenceService;
/*     */ import nc.ui.querytemplate.querytree.IQueryScheme;
/*     */ import nc.util.mmf.framework.base.MMArrayUtil;
/*     */ import nc.util.mmf.framework.base.MMValueCheck;
/*     */ import nc.vo.bd.defdoc.DefdoclistVO;
/*     */ import nc.vo.bd.userdefrule.UserdefitemVO;
///*     */ import nc.vo.pd.pd0404.entity.AggWkVO;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
			import nc.itf.so.qs.sc.maschine.service.IMaschineQuery;
//import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineQueryImpl
/*     */   implements IMaschineQuery
/*     */ {
/*     */   public MaschineQueryImpl() {}
/*     */   
/*     */   public AggMaschineVO[] queryByQueryScheme(IQueryScheme queryScheme)
/*     */     throws BusinessException
/*     */   {
/*     */     try
/*     */     {
/*  38 */       QuerySchemeProcessor processer = new QuerySchemeProcessor(queryScheme);
/*     */       
/*  40 */       processer.appendCurrentGroup();
/*     */       
/*  42 */       processer.appendFuncPermissionOrgSql();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  47 */       BillLazyQuery<AggMaschineVO> qry = new BillLazyQuery(AggMaschineVO.class);
/*  48 */       return (AggMaschineVO[])qry.query(queryScheme, null);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  52 */       ExceptionUtils.marsh(e);
/*     */     }
/*  54 */     return null;
/*     */   }
/*     */   
/*     */   public AggMaschineVO[] queryMaschineByWhereSql(String sqlWhere, UFBoolean isShowSeal, String pk_group)
/*     */     throws BusinessException
/*     */   {
/*     */     try
/*     */     {
/*  62 */       IMDPersistenceQueryService service = MDPersistenceService.lookupPersistenceQueryService();
/*  63 */       Collection<AggMaschineVO> retObjs = service.queryBillOfVOByCondWithOrder(AggMaschineVO.class, sqlWhere, true, false, new String[] { "pk_org", "machcode" });
/*     */       
/*     */ 
/*     */ 
/*  67 */       if ((retObjs != null) && (retObjs.size() > 0)) {
/*  68 */         return (AggMaschineVO[])retObjs.toArray(new AggMaschineVO[retObjs.size()]);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  72 */       ExceptionUtils.marsh(e);
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */   
///*     */   public Integer[] queryFreeNum(String pk_org) throws BusinessException
///*     */   {
///*     */     try {
///*  80 */       IUserdefitemQryService service = (IUserdefitemQryService)NCLocator.getInstance().lookup(IUserdefitemQryService.class);
///*     */       
///*  82 */       UserdefitemVO[] userdefitemVOs = service.queryUserdefitemVOsByUserdefruleCode("materialassistant", pk_org);
///*  83 */       if (MMArrayUtil.isEmpty(userdefitemVOs)) {
///*  84 */         return null;
///*     */       }
///*     */       
///*  87 */       DefdoclistVO[] defdocListVOs = getDefdocVO(userdefitemVOs);
///*  88 */       List<Integer> freeNumList = new ArrayList();
///*  89 */       for (UserdefitemVO userdefitemVO : userdefitemVOs) {
///*  90 */         if ((userdefitemVO.getEnabled().booleanValue()) && (isDefDoc(userdefitemVO, defdocListVOs))) {
///*  91 */           freeNumList.add(userdefitemVO.getPropindex());
///*     */         }
///*     */       }
///*  94 */       return (Integer[])freeNumList.toArray(new Integer[0]);
///*     */     }
///*     */     catch (Exception e) {
///*  97 */       ExceptionUtils.marsh(e);
///*     */     }
///*  99 */     return null;
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private boolean isDefDoc(UserdefitemVO userdefitemVO, DefdoclistVO[] defdocListVOs)
///*     */   {
///* 110 */     if (MMArrayUtil.isEmpty(defdocListVOs)) {
///* 111 */       return false;
///*     */     }
///* 113 */     for (DefdoclistVO defdocListVO : defdocListVOs) {
///* 114 */       if (defdocListVO.getPk_defdoclist().equals(userdefitemVO.getClassid())) {
///* 115 */         return true;
///*     */       }
///*     */     }
///* 118 */     return false;
///*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private DefdoclistVO[] getDefdocVO(UserdefitemVO[] userdefitemVOs)
///*     */     throws BusinessException
///*     */   {
///* 130 */     if (MMArrayUtil.isEmpty(userdefitemVOs)) {
///* 131 */       return null;
///*     */     }
///* 133 */     List<String> docList = new ArrayList();
///* 134 */     for (UserdefitemVO userdefitemVO : userdefitemVOs) {
///* 135 */       if (!MMValueCheck.isEmpty(userdefitemVO.getClassid())) {
///* 136 */         docList.add(userdefitemVO.getClassid());
///*     */       }
///*     */     }
///* 139 */     IDefdoclistQryService defdocListService = (IDefdoclistQryService)NCLocator.getInstance().lookup(IDefdoclistQryService.class);
///* 140 */     DefdoclistVO[] defdocListVOs = defdocListService.queryVOByPks((String[])docList.toArray(new String[0]));
///* 141 */     if (MMArrayUtil.isEmpty(defdocListVOs)) {
///* 142 */       return null;
///*     */     }
///*     */     
///* 145 */     List<DefdoclistVO> defdoclistVOList = new ArrayList();
///* 146 */     for (DefdoclistVO defdocListVO : defdocListVOs) {
///* 147 */       if (!MMValueCheck.isEmpty(defdocListVO)) {
///* 148 */         defdoclistVOList.add(defdocListVO);
///*     */       }
///*     */     }
///* 151 */     if (MMValueCheck.isEmpty(defdoclistVOList)) {
///* 152 */       return null;
///*     */     }
///* 154 */     return (DefdoclistVO[])defdoclistVOList.toArray(new DefdoclistVO[0]);
///*     */   }
/*     */
//@Override
//	public Object[] queryMDVOByPks(String[] pks) throws BusinessException {
//		// TODO 自动生成的方法存根
//		BillPageLazyQuery<AggMaschineVO> query=new BillPageLazyQuery(AggMaschineVO.class);
//		
//		query.addChildOrder(MaschineVO.class, "rowno");
//		
//		
//		return (AggMaschineVO[])query.queryPageBills(pks);
//	}
//	@Override
//	public PageQueryVO queryM30App(IQueryScheme Scheme) throws BusinessException {
//		// TODO 自动生成的方法存根
//		SchemeAppendCondition condition = new SchemeAppendCondition(Scheme);
//		SchemeFixCondition tool = new SchemeFixCondition(Scheme);
//		
//		condition.appendPermission();
//		BillPageLazyQuery<AggMaschineVO> query=new BillPageLazyQuery(AggMaschineVO.class);
//		
//		PageQueryVO page = null;
//		
//		try{
//			
//			query.addHeadOrder("code");
//			query.addChildOrder(MaschineVO.class, "rowno");
//			
//			page = query.query(Scheme);
//			
//		}catch(Exception ex){
//			
//		}
//		return page;
//	}
//	@Override
//	public AggMaschineVO[] queryM30App(String[] id)
//			throws BusinessException {
//		// TODO 自动生成的方法存根
//		BillPageLazyQuery<AggMaschineVO> query=new BillPageLazyQuery(AggMaschineVO.class);
//		
//		query.addChildOrder(MaschineVO.class, "rowno");
//		
//		return query.queryPageBills(id);
//	} 
}
