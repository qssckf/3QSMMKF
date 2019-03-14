package nc.vo.so.qs.appinterface.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pf.pub.PFRequestDataCacheProxy;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.pf.pub.cache.CondStringKey;
import nc.bs.pf.pub.cache.ICacheDataQueryCallback;
import nc.bs.pf.pub.cache.IRequestDataCacheKey;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.org.SalesOrgVO;
import nc.vo.pf.mobileapp.exception.TaskNotValidException;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.qs.appinterface.util.BillQuery;
import nc.vo.so.qs.appinterface.util.TaskMetaData;

public abstract class AbstractPreOrderQuery extends BillQuery{
	
	
	protected String getBaseSql(){
	    
		if (!StringUtil.isEmptyWithTrim(getCondition()))
	    {
	      String sql = "select so_preorder.cpreorderid,so_preorder.vbillcode,so_preorder.dbilldate from so_preorder left join so_preorder_b on so_preorder.cpreorderid=so_preorder_b.cpreorderid where so_preorder.fstatusflag=1 and so_preorder.dr=0 and so_preorder_b.dr=0  and so_preorder.pk_group='#pk_group#' and so_preorder.billmaker='#billmaker#' and substr(so_preorder.dmakedate,0,10)<='#date#' and ("+this.getCondition()+") group by so_preorder.cpreorderid,so_preorder.vbillcode,so_preorder.dbilldate order by so_preorder.vbillcode desc,so_preorder.dbilldate desc";
	
	      return sql;
	    }
		
	    return "select so_preorder.cpreorderid,so_preorder.vbillcode,so_preorder.dbilldate from so_preorder left join so_preorder_b on so_preorder.cpreorderid=so_preorder_b.cpreorderid where so_preorder.fstatusflag=1 and so_preorder.dr=0 and so_preorder_b.dr=0  and so_preorder.pk_group='#pk_group#' and so_preorder.billmaker='#billmaker#' and substr(so_preorder.dmakedate,0,10)<='#date#' group by so_preorder.cpreorderid,so_preorder.vbillcode,so_preorder.dbilldate order by so_preorder.vbillcode desc,so_preorder.dbilldate desc";
	}
	

	@Override
	public TaskMetaData queryTaskMetaData(final String pk) throws BusinessException {
		// TODO 自动生成的方法存根
		
		IRequestDataCacheKey key = new CondStringKey("mobileapp_preorder_querytaskmetadata", new String[] { pk });
		ICacheDataQueryCallback<TaskMetaData> callback = new ICacheDataQueryCallback()
		{
			public TaskMetaData queryData() throws BusinessException{
				
				PreOrderHVO note = (PreOrderHVO)AbstractPreOrderQuery.this.getQueryService().retrieveByPK(PreOrderHVO.class, pk);
	        
				if (note == null) {
					throw new TaskNotValidException();
				}
	        
				return AbstractPreOrderQuery.this.convertToMeta(note);
	      }
	      
	    };
	    return (TaskMetaData)PFRequestDataCacheProxy.get(key, callback);
	    
	}
	
	protected TaskMetaData convertToMeta(PreOrderHVO note) throws BusinessException {
		// TODO 自动生成的方法存根
		TaskMetaData tmd=new TaskMetaData();
		
		tmd.setBillType(note.getVtrantypecode());
		tmd.setBillId(note.getCpreorderid());
		tmd.setBillNo(note.getVbillcode());
		tmd.setCuserid(note.getBillmaker());
		tmd.setStartDate(note.getDbilldate().toString().substring(0,10));
		tmd.setFstatusflag(note.getFstatusflag());
		tmd.setTitle(MakeTitle(note));
		tmd.setBillvo(note);
		
		return tmd;
	}

	private String MakeTitle(PreOrderHVO note) throws BusinessException {
		// TODO 自动生成的方法存根
		BilltypeVO btvo = PfDataCache.getBillTypeInfo(note.getVtrantypecode());
		
		if(btvo==null){
			throw new BusinessException("预订单："+note.getVbillcode()+"交易类型为空，不能继续完成获取列表操作");
		}
		
		SalesOrgVO orgs=(SalesOrgVO)getQueryService().retrieveByPK(SalesOrgVO.class, note.getPk_org());
		
		if(orgs==null){
			throw new BusinessException("预订单："+note.getVbillcode()+"销售组织为空，不能继续完成获取列表操作");
		}
		
		CustomerVO cus=(CustomerVO)getQueryService().retrieveByPK(CustomerVO.class, note.getCcustomerid());
		
		if(cus==null){
			throw new BusinessException("预订单："+note.getVbillcode()+"客户为空，不能继续完成获取列表操作");
		}
		

		String title="客户："+cus.getName()+" "+"单据时间："+note.getDbilldate()+" "+"销售类型："+btvo.getBilltypename()+" "+"单据编号："+note.getVbillcode()+" "+"订单数量："+note.getNtotalnum();

		return title;
	}



	@Override
	public List<Map<String, Object>> queryByPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		List<Map<String, Object>> list = new ArrayList();
		
		for (String pk : pks) {
			 
			 TaskMetaData tmd = queryTaskMetaData(pk);
			 Map<String, Object> map = convertToTask(tmd);
			 list.add(map);
		 }
		 
		 return list;
		

	}
	
	private Map<String, Object> convertToTask(TaskMetaData tmd) throws BusinessException {
	    
		Map<String, Object> map = new HashMap();
	    
	    map.put("billid", tmd.getBillId());
	    map.put("billstatus", tmd.getFstatusflag());
	    map.put("date", tmd.getStartDate());
	    map.put("title", tmd.getTitle());
	    map.put("billtype", tmd.getBillType());
	    map.put("vbillcode", tmd.getBillvo().getAttributeValue("vbillcode"));
	    map.put("dbilldate", tmd.getBillvo().getAttributeValue("dbilldate"));
	    map.put("ntotalnum", tmd.getBillvo().getAttributeValue("ntotalnum"));
	    BilltypeVO btvo = PfDataCache.getBillTypeInfo(tmd.getBillvo().getAttributeValue("vtrantypecode").toString());
	    if(btvo!=null){
	    	map.put("billtype", btvo.getBilltypename());
	    }
	    CustomerVO cus=(CustomerVO)getQueryService().retrieveByPK(CustomerVO.class, tmd.getBillvo().getAttributeValue("ccustomerid").toString());
	    if(cus!=null){
	    	map.put("customer", cus.getName());
	    }
	    
	    
	    return map;
	}

}
