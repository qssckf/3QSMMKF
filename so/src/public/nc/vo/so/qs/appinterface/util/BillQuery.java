package nc.vo.so.qs.appinterface.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.qs.sc.bill.data.access.BillAccess;
import nc.vo.pf.mobileapp.exception.LoopTerminateNotification;
import nc.vo.pf.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public abstract class BillQuery implements IBillQuery{

	private IUAPQueryBS qry = null;
	private IBillType billType = null;
	private String pk_group = null;
	private String cuserid = null;
	private String date = null;
	private String condition = null;
	
	public IBillType getBillType() {
		   return billType;
	}


	public void setBillType(IBillType billType) {
	   this.billType = billType;
	}
	
	protected IUAPQueryBS getQueryService() {
		
		if (this.qry == null) {
			this.qry = ((IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class));
		}
		
		return this.qry;
	}
	
	
	public String getPk_group() {
		return this.pk_group;
	}
	
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}
	
	public String getCuserid() {
		return this.cuserid;
	}
	
	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getDateAsEnd() {
		return new UFDate(this.date).asEnd().toString();
	}
	
	public String getIdentifier(){
	 
		StringBuffer sb = new StringBuffer();
	 
		sb.append(getBillType().getCategory());
		sb.append(getBillType().getCode());
		sb.append(getPk_group());
		sb.append(getCuserid());
		sb.append(getDate());
	 
		return sb.toString();
	}
	   

	@Override
	public Map<String, Object> queryBillInfo(String pk_group, String user,String billtype, String pk) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String templateid = ExMobileAppUtil.queryBillTemplateId(billtype, user);
		
		BillTempletVO tvo = ExMobileAppUtil.queryTemplate(templateid);
		
		AggregatedValueObject bvo = ExMobileAppUtil.queryBillEntity(billtype,pk);
		
		processEntity(bvo);
		
		BillAccess ba = new BillAccess(tvo, bvo);
		
		Map<String, List<Map<String, Object>>> billVOMap = ba.billVO2Map(billtype);
		
		int origin = ExMobileAppUtil.getRowCount().intValue();
		int current = ArrayUtil.getLength(bvo.getChildrenVO());
		Map<String, Object> result = processResultSetRowCount(billVOMap, origin, current);

		return result;

	}
	
	   private Map<String, Object> processResultSetRowCount(Map<String, List<Map<String, Object>>> billVOMap, int origin, int current){
	       
		   Map<String, Object> resultMap = ExMobileAppUtil.createLinkedHashMap();
	       
	       if (billVOMap != null) {
	         Iterator<Map.Entry<String, List<Map<String, Object>>>> it = billVOMap.entrySet().iterator();
	         while (it.hasNext()) {
	           Map.Entry<String, List<Map<String, Object>>> entry = (Map.Entry)it.next();
	           
	           String key = (String)entry.getKey();
	           List<Map<String, Object>> value = (List)entry.getValue();
	           
	           resultMap.put(key, value);
	           try
	           {
	             if (key.equals("body"))
	             {
	   
	               Map<String, Object> bodyMap = (Map)value.get(0);
	               bodyMap.put("rowcnt", String.valueOf(origin));
	             }
	           } catch (Exception e) {
	             Logger.error(e.getMessage(), e);
	           }
	         }
	       }
	       
	       if (origin > current)
	       {
	         try
	         {
	           processElement(resultMap);
	         }
	         catch (Throwable e) {}
	       }
	       
	   
	   
	   
	       return resultMap;
	 }
	   
	 private void processElement(Object obj) throws LoopTerminateNotification {
		 
		 Map map;
		 Iterator<String> it;
		 
		 if ((obj instanceof List)) {
			 
			 for (Object sub : (List)obj) {
				 if (sub != null) {
			        processElement(sub);
			      }
			 }
		 } else if ((obj instanceof Map)) {
			 map = (Map)obj;
			    
			 if (map.containsKey("ibodyrowcount")) {
				 Integer rowcnt = ExMobileAppUtil.getRowCount();
				 map.put("ibodyrowcount", String.valueOf(rowcnt));
				 throw new LoopTerminateNotification();
			 }
			 
			 for (it = map.keySet().iterator(); it.hasNext();) {
				 
				 String key = (String)it.next();
				 Object value = map.get(key);
				 
				 processElement(value);
			 }
		 }
	 }
	
	 private void processEntity(AggregatedValueObject aggVo){
	       
		  processRowCount(aggVo);
	       
	      CircularlyAccessibleValueObject parent = aggVo.getParentVO();
	      CircularlyAccessibleValueObject[] children = aggVo.getChildrenVO();
	       
	   
	      processDouble(parent);
	      if (!ArrayUtil.isNull(children)) {
	         for (CircularlyAccessibleValueObject c : children) {
	           processDouble(c);
	         }
	       }
	 }
	 
	 private void processDouble(CircularlyAccessibleValueObject vo) {
	 	   
		 String[] attrs = vo.getAttributeNames();
		   
		 for (String att : attrs) {
			 
			 Object value = vo.getAttributeValue(att);
		     
			 if ((value instanceof UFDouble)) {
		       UFDouble d = (UFDouble)value;
		       d = ExMobileAppUtil.adjust2Scale(d);
		       
		       vo.setAttributeValue(att, d);
		     }
		 }
	}
	 
	private void processRowCount(AggregatedValueObject aggVo){
		    
		try{
	   			
			CircularlyAccessibleValueObject[] children = aggVo.getChildrenVO();
			Integer rowCount = Integer.valueOf(children == null ? 0 : children.length);
		      
			Logger.debug("____row count: " + rowCount);
		      
			ExMobileAppUtil.setRowCount(rowCount);
		      
			if (rowCount.intValue() > ExMobileAppUtil.TRIM_TO_COUNT.intValue()){

				List<CircularlyAccessibleValueObject> subList = Arrays.asList(children).subList(0, ExMobileAppUtil.TRIM_TO_COUNT.intValue());
	   			CircularlyAccessibleValueObject[] casted = (CircularlyAccessibleValueObject[])ArrayUtil.convertToArray(subList);
	   			aggVo.setChildrenVO((CircularlyAccessibleValueObject[])casted);
	   		}
	   	} catch (Exception e) {
	   		
	   		Logger.error(e.getMessage(), e);
		}
	}

}
