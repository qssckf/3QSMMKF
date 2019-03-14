package nc.impl.so;

import java.util.HashMap;
import java.util.Map;
import nc.bs.so.fq.plugin.ServicePlugInPoint;
import nc.impl.pub.ace.AceShipmentsInfoPubServiceImpl;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsVO;
import nc.vo.so.qs.sc.ShipmentsViewVO;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.pubimpl.so.fq.so.m30.rule.Rewrite38RowStateRule;
import nc.pubimpl.so.fq.so.m30.rule.Rewrite38SetArrInfoRule;
import nc.pubimpl.so.fq.so.m30.rule.RewriteRowStateRule;
import nc.pubimpl.so.fq.so.m30.rule.RewriteSetArrInfoRule;
import nc.pubitf.so.fq.so.m30.Rewrite30Para;
import nc.pubitf.so.fq.so.m30.RewritefqPara;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.bill.pagination.PaginationTransferObject;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

public class ShipmentsInfoMaintainImpl extends AceShipmentsInfoPubServiceImpl implements nc.itf.so.IShipmentsInfoMaintain {

      @Override
      public void delete(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
    	  super.pubdeleteBills(clientFullVOs,originBills);
      }
  
      @Override
      public AggShipmentsVO[] insert(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
    	  return super.pubinsertBills(clientFullVOs,originBills);
      }
    
      @Override
      public AggShipmentsVO[] update(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
    	  return super.pubupdateBills(clientFullVOs,originBills);    
      }

	  @Override
	  public String[] queryPKs(IQueryScheme queryScheme) throws BusinessException {
		  return super.pubquerypkbills(queryScheme);
	  }
	  @Override
	  public AggShipmentsVO[] queryBillByPK(String[] pks) throws BusinessException {
		  return super.pubquerybillbypkbills(pks);
	  }

	  @Override
	  public AggShipmentsVO[] save(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
		  return super.pubsendapprovebills(clientFullVOs,originBills);
	  }

	  @Override
	  public AggShipmentsVO[] unsave(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
		  return super.pubunsendapprovebills(clientFullVOs,originBills);
	  }

	  @Override
	  public AggShipmentsVO[] approve(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
	      return super.pubapprovebills(clientFullVOs,originBills);
	  }

	  @Override
	  public AggShipmentsVO[] unapprove(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
	      return super.pubunapprovebills(clientFullVOs,originBills);
	  }
	  
	  public AggShipmentsVO[] queryShipmentsFor30(IQueryScheme queryScheme) throws BusinessException{
		  
		  
		  QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		  
		  String maintablename = processor.getMainTableAlias();
		  SqlBuilder sqlbuild = new SqlBuilder();
		  
		  sqlbuild.append(" and ");
		  String pk_group = BSContext.getInstance().getGroupID();
		  sqlbuild.append(maintablename + ".pk_group", pk_group);
		  sqlbuild.append(" and ");
		  sqlbuild.append(maintablename + ".fstatusflag", "1");
		  
		  String chidtable = processor.getTableAliasOfAttribute("so_shipmentsb.blineclose");
		  sqlbuild.append(" and ");
		  sqlbuild.append(chidtable + "." + "blineclose", UFBoolean.FALSE);
		  
		  sqlbuild.append(" and ");
		  sqlbuild.append(" abs( " + chidtable + ".nnum ) > abs( isnull(" + chidtable + ".narrnum,0)) ");
		  
		  processor.appendWhere(sqlbuild.toString());
		  processor.appendRefTrantypeWhere("FQ01", SOBillType.Order.getCode(), "transtype");
		
		  String ordersql = createOrderSql(queryScheme);
		  
		  SchemeViewQuery<ShipmentsViewVO> query = new SchemeViewQuery(ShipmentsViewVO.class);
		  ShipmentsViewVO[] views=(ShipmentsViewVO[])query.query(queryScheme, ordersql);
		  
		  if (org.apache.commons.lang.ArrayUtils.isEmpty(views)) {
			  return null;
		  }
		  
		  for(ShipmentsViewVO view:views){
			  
			  ShipmentsVO headvo=view.getHead();
			  ShipmentsBVO bodyvo=view.getItem();
			  headvo.setPk_group(bodyvo.getPk_group());
			  headvo.setPk_org(bodyvo.getPk_org());
			  
		  }
		  
		  AggShipmentsVO[] queryVos = (AggShipmentsVO[])new CombineViewToAggUtil(AggShipmentsVO.class, ShipmentsVO.class, ShipmentsBVO.class).combineViewToAgg(views, "pk_shipments");
		  
		  return queryVos;
	  }

	private String createPreOrderSql(IQueryScheme queryScheme) {
		// TODO 自动生成的方法存根
		SqlBuilder order = new SqlBuilder();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		order.append(" order by ");
		String tableName = processor.getTableAliasOfAttribute(PreOrderHVO.class, "vbillcode");
		
		
		order.append(tableName);
		order.append(".");
		order.append("vbillcode");
		order.append(",");
		tableName = processor.getTableAliasOfAttribute(PreOrderBVO.class, "crowno");
		
		
		order.append(tableName);
		order.append(".");
		order.append("crowno");
		return order.toString();
	}

	private String createOrderSql(IQueryScheme queryScheme) {
		// TODO 自动生成的方法存根
		
		SqlBuilder order = new SqlBuilder();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		order.append(" order by ");
		String tableName = processor.getTableAliasOfAttribute(ShipmentsVO.class, "vbillcode");
		
		order.append(tableName);
		order.append(".");
		order.append("vbillcode");
		
		return order.toString();
	}

	@Override
	public void rewriteFQNarrnumFor30(Rewrite30Para[] paras) throws BusinessException {
		// TODO 自动生成的方法存根
		try{
			rewrite(paras);
		}catch (RuntimeException ex) {
			ExceptionUtils.marsh(ex);
		}
		
	}
	
	@Override
	public void rewrite30NumForWithdraw(RewritefqPara[] paras) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			rewrite38(paras);
		}catch (RuntimeException ex) {
			ExceptionUtils.marsh(ex);
		}
		
	}
	
	private void rewrite38(RewritefqPara[] paras) {
		// TODO 自动生成的方法存根
		
		TimeLog.logStart();
	    Map<String, RewritefqPara> index = prepareFqParams(paras);
	    
	    BSContext.getInstance().setSession(RewritefqPara.class.getName(), index);
	    TimeLog.info("并处理参数");
	    
	    TimeLog.logStart();
	    PreOrderViewVO[] views = queryby38(index);
	    TimeLog.info("查询预订单表体");
	    
	    AroundProcesser<PreOrderViewVO> processer = new AroundProcesser(ServicePlugInPoint.rewrite38NarrnumForFQ);
	    
	    add38Rule(processer, views);
	    
	    TimeLog.logStart();
	    processer.before(views);
	    TimeLog.info("写数据库前执行业务规则");
	    
	    TimeLog.logStart();
	    String[] names = { "narrnum", "carrangeid", "darrdate","vbdef20"};
	    
	
	    ViewUpdate<PreOrderViewVO> bo = new ViewUpdate();
	    views = (PreOrderViewVO[])bo.update(views, PreOrderBVO.class, names);
	    TimeLog.info("更新数据库");
	    
	    TimeLog.logStart();
	    processer.after(views);
	    TimeLog.info("写数据库后执行业务规则");
	    
	
	    BSContext.getInstance().removeSession(RewritefqPara.class.getName());
		
	}

	private void add38Rule(AroundProcesser<PreOrderViewVO> processer,PreOrderViewVO[] views) {
		// TODO 自动生成的方法存根
		
		IRule<PreOrderViewVO> rule = new Rewrite38SetArrInfoRule();
		processer.addBeforeRule(rule);
		
		rule = new Rewrite38RowStateRule();
		processer.addAfterRule(rule);
		
		
	}

	private PreOrderViewVO[] queryby38(Map<String, RewritefqPara> index) {
		// TODO 自动生成的方法存根
		
		String[] ids = lock38Bills(index);
		ViewQuery<PreOrderViewVO> bo = new ViewQuery(PreOrderViewVO.class);
	  
		bo.setSharedHead(true);
	  
		PreOrderViewVO[] views = (PreOrderViewVO[])bo.query(ids);
		
		if (views.length != index.size()) {
			String message = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0031");
	    
	
			ExceptionUtils.wrappBusinessException(message);
		}
		return views;
	}

	private String[] lockBills(Map<String, Rewrite30Para> index) {
	    int size = index.size();
	    String[] bids = new String[size];
	    bids = (String[])index.keySet().toArray(bids);
	    LockOperator locker = new LockOperator();
	    String message = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0030");
	    
	
	    locker.lock(bids, message);
	    return bids;
	}
	
	private String[] lock38Bills(Map<String, RewritefqPara> index) {
	    int size = index.size();
	    String[] bids = new String[size];
	    bids = (String[])index.keySet().toArray(bids);
	    LockOperator locker = new LockOperator();
	    String message = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0030");

	    locker.lock(bids, message);
	    return bids;
	}
	
	private ShipmentsViewVO[] query(Map<String, Rewrite30Para> index) {
	  
		String[] ids = lockBills(index);
		ViewQuery<ShipmentsViewVO> bo = new ViewQuery(ShipmentsViewVO.class);
	  
		bo.setSharedHead(true);
	  
		ShipmentsViewVO[] views = (ShipmentsViewVO[])bo.query(ids);
		
		if (views.length != index.size()) {
			String message = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0031");
	    
	
			ExceptionUtils.wrappBusinessException(message);
		}
		return views;
	}
	
	private void rewrite(Rewrite30Para[] paras) throws BusinessException {
	    
		TimeLog.logStart();
	    Map<String, Rewrite30Para> index = prepareParams(paras);
	    
	    BSContext.getInstance().setSession(Rewrite30Para.class.getName(), index);
	    TimeLog.info("并处理参数");
	    
	    TimeLog.logStart();
	    ShipmentsViewVO[] views = query(index);
	    TimeLog.info("查询发货申请表体");
	    
	    AroundProcesser<ShipmentsViewVO> processer = new AroundProcesser(ServicePlugInPoint.rewriteFQNarrnumFor30);
	    
	
	    addRule(processer, views);
	    
	    TimeLog.logStart();
	    processer.before(views);
	    TimeLog.info("写数据库前执行业务规则");
	    
	    TimeLog.logStart();
	    String[] names = { "narrnum", "carrangeid", "darrdate" };
	    
	
	    ViewUpdate<ShipmentsViewVO> bo = new ViewUpdate();
	    views = (ShipmentsViewVO[])bo.update(views, ShipmentsBVO.class, names);
	    TimeLog.info("更新数据库");
	    
	    TimeLog.logStart();
	    processer.after(views);
	    TimeLog.info("写数据库后执行业务规则");
	    
	
	    BSContext.getInstance().removeSession(Rewrite30Para.class.getName());
	}
	
	private void addRule(AroundProcesser<ShipmentsViewVO> processer,ShipmentsViewVO[] views) {
		// TODO 自动生成的方法存根

		IRule<ShipmentsViewVO> rule = new RewriteSetArrInfoRule();
		processer.addBeforeRule(rule);
		
		rule = new RewriteRowStateRule();
		processer.addAfterRule(rule);
	}

	private Map<String, Rewrite30Para> prepareParams(Rewrite30Para[] paras) {
	   Map<String, Rewrite30Para> index = new HashMap();
	   for (Rewrite30Para para : paras) {
	     index.put(para.getPk_ship(), para);
	   }
	   return index;
	}
	
	private Map<String, RewritefqPara> prepareFqParams(RewritefqPara[] paras) {Map<String, RewritefqPara> index = new HashMap();
		   
		for (RewritefqPara para : paras) {
			index.put(para.getBid(), para);
		}
		
		return index;
	}

	@Override
	public PreOrderVO[] queryPreOrderFor30(IQueryScheme queryScheme) throws BusinessException {
		// TODO 自动生成的方法存根
		
		 QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		      
		 String maintablename = processor.getMainTableAlias();
		 SqlBuilder sqlbuild = new SqlBuilder();
		 
		 sqlbuild.append(" and ");
		 String pk_group = BSContext.getInstance().getGroupID();
		 sqlbuild.append(maintablename + ".pk_group", pk_group);
		 sqlbuild.append(" and ");
		 sqlbuild.append(maintablename + ".fstatusflag", BillStatus.AUDIT.getIntValue());
		 		 
		 String chidtable = processor.getTableAliasOfAttribute("so_preorder_b.blineclose");
		 sqlbuild.append(" and ");
		 sqlbuild.append(chidtable + "." + "blineclose", UFBoolean.FALSE);
		 
		 sqlbuild.append(" and ");
		 sqlbuild.append(" abs( " + chidtable + ".nnum ) > abs( isnull(" + chidtable + ".narrnum,0)) ");
		 
		 
		 processor.appendWhere(sqlbuild.toString());
		 processor.appendRefTrantypeWhere(SOBillType.PreOrder.getCode(), SOBillType.Order.getCode(), "vtrantypecode");
		 
		 
		 
		 String ordersql = createPreOrderSql(queryScheme);
		 
		 SchemeViewQuery<PreOrderViewVO> query = new SchemeViewQuery(PreOrderViewVO.class);
		 
		 PreOrderViewVO[] views = (PreOrderViewVO[])query.query(queryScheme, ordersql);
		 if (org.apache.commons.lang.ArrayUtils.isEmpty(views)) {
		   return null;
		 }
		 for (PreOrderViewVO view : views) {
		   PreOrderHVO headvo = view.getHead();
		   PreOrderBVO bodyvo = view.getItem();
		   headvo.setPk_group(bodyvo.getPk_group());
		   headvo.setPk_org(bodyvo.getPk_org());
		   headvo.setDbilldate(bodyvo.getDbilldate());
		   headvo.setCorigcurrencyid(bodyvo.getCorigcurrencyid());
		 }
		 PreOrderVO[] queryVos = (PreOrderVO[])new CombineViewToAggUtil(PreOrderVO.class, PreOrderHVO.class, PreOrderBVO.class).combineViewToAgg(views, "cpreorderid");
		 
		 
		 
		 return queryVos;
		

	}

}
