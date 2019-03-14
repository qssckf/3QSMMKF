/*     */ package nc.impl.so.m38;
/*     */ 
/*     */ import nc.bs.so.m38.maintain.PreOrderSendApproveBP;
import nc.bs.so.m38.maintain.PreOrderUnSendApproveBP;
import nc.impl.pubapp.env.BSContext;
/*     */ import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
/*     */ import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
/*     */ import nc.impl.so.m38.action.DeletePreOrderAction;
/*     */ import nc.impl.so.m38.action.InsertPreOrderAction;
/*     */ import nc.impl.so.m38.action.InvalidatePreorderAction;
/*     */ import nc.impl.so.m38.action.QueryPreOrderAction;
/*     */ import nc.impl.so.m38.action.UpdatePreOrderAction;
/*     */ import nc.itf.so.m38.IPreOrderMaintain;
/*     */ import nc.ui.querytemplate.querytree.IQueryScheme;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pubapp.AppContext;
/*     */ import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/*     */ import nc.vo.pubapp.pattern.pub.SqlBuilder;
/*     */ import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
/*     */ import nc.vo.pubapp.util.CombineViewToAggUtil;
/*     */ import nc.vo.scmpub.res.billtype.SOBillType;
/*     */ import nc.vo.so.m38.entity.PreOrderBVO;
/*     */ import nc.vo.so.m38.entity.PreOrderHVO;
/*     */ import nc.vo.so.m38.entity.PreOrderVO;
/*     */ import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
/*     */ 
/*     */ public class PreOrderMaintainImpl implements IPreOrderMaintain
/*     */ {
/*     */   public PreOrderMaintainImpl() {}
/*     */   
/*     */   public void deletePreOrder(PreOrderVO[] bills) throws BusinessException
/*     */   {
/*     */     try
/*     */     {
/*  35 */       DeletePreOrderAction action = new DeletePreOrderAction();
/*  36 */       action.delete(bills);
/*     */     }
/*     */     catch (Exception ex) {
/*  39 */       ExceptionUtils.marsh(ex);
/*     */     }
/*     */   }
/*     */   
/*     */   public PreOrderVO insertPreOrder(PreOrderVO bill) throws BusinessException
/*     */   {
/*  45 */     PreOrderVO[] ret = null;
/*  46 */     PreOrderVO[] bills = { bill };
/*     */     
/*     */     try
/*     */     {
/*  50 */       InsertPreOrderAction action = new InsertPreOrderAction();
/*  51 */       ret = action.insert(bills);
/*     */     }
/*     */     catch (Exception ex) {
/*  54 */       ExceptionUtils.marsh(ex);
/*     */     }
/*  56 */     return null == ret ? null : ret[0];
/*     */   }
/*     */   
/*     */   public PreOrderVO[] invalidationPreorder(PreOrderVO[] vos)
/*     */     throws BusinessException
/*     */   {
/*  62 */     InvalidatePreorderAction action = new InvalidatePreorderAction();
/*  63 */     return action.invalidatePreorder(vos);
/*     */   }
/*     */   
/*     */   public PreOrderVO[] queryPreOrder(IQueryScheme queryScheme)
/*     */     throws BusinessException
/*     */   {
/*  69 */     PreOrderVO[] bills = null;
/*     */     try {
/*  71 */       QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
/*  72 */       String headTableName = processor.getMainTableAlias();
/*  73 */       processor.appendCurrentGroup();
/*  74 */       processor.appendFuncPermissionOrgSql();
/*     */       
/*  76 */       SqlBuilder order = new SqlBuilder();
/*  77 */       order.append("order by ");
/*  78 */       order.append(headTableName);
/*  79 */       order.append(".vbillcode");
/*     */       
/*  81 */       BillLazyQuery<PreOrderVO> qry = new BillLazyQuery(PreOrderVO.class);
/*     */       
/*  83 */       bills = (PreOrderVO[])qry.query(queryScheme, order.toString());
/*     */     }
/*     */     catch (Exception ex) {
/*  86 */       ExceptionUtils.marsh(ex);
/*     */     }
/*  88 */     return bills;
/*     */   }
/*     */   
/*     */   public PreOrderVO[] queryPreOrder(String sql)
/*     */     throws BusinessException
/*     */   {
/*  94 */     QueryPreOrderAction bo = new QueryPreOrderAction();
/*  95 */     return bo.queryPreOrder(sql);
/*     */   }
/*     */   
/*     */   public PreOrderVO[] queryPreOrderFor30(IQueryScheme queryScheme)
/*     */     throws BusinessException
/*     */   {
/* 101 */     QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
/*     */     
/* 103 */     String maintablename = processor.getMainTableAlias();
/* 104 */     SqlBuilder sqlbuild = new SqlBuilder();
/*     */     
/* 106 */     sqlbuild.append(" and ");
/* 107 */     String pk_group = BSContext.getInstance().getGroupID();
/* 108 */     sqlbuild.append(maintablename + ".pk_group", pk_group);
/* 109 */     sqlbuild.append(" and ");
/* 110 */     sqlbuild.append(maintablename + ".fstatusflag", BillStatus.AUDIT.getIntValue());
/*     */     
/*     */ 
/* 113 */     nc.vo.pub.lang.UFDate busiDate = AppContext.getInstance().getBusiDate();
/* 114 */     sqlbuild.append(" and ");
/* 115 */     sqlbuild.append(maintablename + ".dabatedate" + " > '" + busiDate + "'");
/*     */     
/* 117 */     String chidtable = processor.getTableAliasOfAttribute("so_preorder_b.blineclose");
/*     */     
/*     */ 
/* 120 */     sqlbuild.append(" and ");
/* 121 */     sqlbuild.append(chidtable + "." + "blineclose", UFBoolean.FALSE);
/*     */     
/* 123 */     sqlbuild.append(" and ");
/* 124 */     sqlbuild.append(" abs( " + chidtable + ".nnum ) > abs( isnull(" + chidtable + ".narrnum,0)) ");
/*     */     
/*     */ 
/* 127 */     processor.appendWhere(sqlbuild.toString());
/* 128 */     processor.appendRefTrantypeWhere(SOBillType.PreOrder.getCode(), SOBillType.Order.getCode(), "vtrantypecode");
/*     */     
/*     */ 
/*     */ 
/* 132 */     String ordersql = createOrderSql(queryScheme);
/*     */     
/* 134 */     SchemeViewQuery<PreOrderViewVO> query = new SchemeViewQuery(PreOrderViewVO.class);
/*     */     
/* 136 */     PreOrderViewVO[] views = (PreOrderViewVO[])query.query(queryScheme, ordersql);
/* 137 */     if (org.apache.commons.lang.ArrayUtils.isEmpty(views)) {
/* 138 */       return null;
/*     */     }
/* 140 */     for (PreOrderViewVO view : views) {
/* 141 */       PreOrderHVO headvo = view.getHead();
/* 142 */       PreOrderBVO bodyvo = view.getItem();
/* 143 */       headvo.setPk_group(bodyvo.getPk_group());
/* 144 */       headvo.setPk_org(bodyvo.getPk_org());
/* 145 */       headvo.setDbilldate(bodyvo.getDbilldate());
/* 146 */       headvo.setCorigcurrencyid(bodyvo.getCorigcurrencyid());
/*     */     }
/* 148 */     PreOrderVO[] queryVos = (PreOrderVO[])new CombineViewToAggUtil(PreOrderVO.class, PreOrderHVO.class, PreOrderBVO.class).combineViewToAgg(views, "cpreorderid");
/*     */     
/*     */ 
/*     */ 
/* 152 */     return queryVos;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private String createOrderSql(IQueryScheme queryScheme)
/*     */   {
/* 163 */     SqlBuilder order = new SqlBuilder();
/* 164 */     QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
/* 165 */     order.append(" order by ");
/* 166 */     String tableName = processor.getTableAliasOfAttribute(PreOrderHVO.class, "vbillcode");
/*     */     
/*     */ 
/* 169 */     order.append(tableName);
/* 170 */     order.append(".");
/* 171 */     order.append("vbillcode");
/* 172 */     order.append(",");
/* 173 */     tableName = processor.getTableAliasOfAttribute(PreOrderBVO.class, "crowno");
/*     */     
/*     */ 
/* 176 */     order.append(tableName);
/* 177 */     order.append(".");
/* 178 */     order.append("crowno");
/* 179 */     return order.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public PreOrderVO[] updatePreOrder(PreOrderVO[] bills, PreOrderVO[] originBill)
/*     */     throws BusinessException
/*     */   {
/* 186 */     PreOrderVO[] ret = null;
/*     */     try
/*     */     {
/* 189 */       UpdatePreOrderAction action = new UpdatePreOrderAction();
/* 190 */       ret = action.update(bills, originBill);
/*     */     }
/*     */     catch (Exception ex) {
/* 193 */       ExceptionUtils.marsh(ex);
/*     */     }
/* 195 */     return ret;
/*     */   }
/*     */
	@Override
	public PreOrderVO[] save(PreOrderVO[] clientFullVOs, PreOrderVO[] originBills) throws BusinessException {
	// TODO 自动生成的方法存根
		
		PreOrderSendApproveBP bp=new PreOrderSendApproveBP();
		
		PreOrderVO[] retvos=bp.sendApprove(clientFullVOs, originBills);
		
		return retvos;
	}
	@Override
	public PreOrderVO[] unsave(PreOrderVO[] clientFullVOs, PreOrderVO[] originBills) throws BusinessException {
	// TODO 自动生成的方法存根
		
		PreOrderUnSendApproveBP bp=new PreOrderUnSendApproveBP();
		
		PreOrderVO[] retvos=bp.unSend(clientFullVOs, originBills);
		
		return retvos;
	} 	
}

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_sellingrequisition.jar
 * Qualified Name:     nc.impl.so.m38.PreOrderMaintainImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */