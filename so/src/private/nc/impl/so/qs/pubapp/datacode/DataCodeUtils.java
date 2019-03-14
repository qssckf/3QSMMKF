package nc.impl.so.qs.pubapp.datacode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pub.billcode.DuplicateException;
import nc.pub.billcode.ILeveledBillCodeManage;
import nc.pub.billcode.itf.IBillcodeManage;
import nc.pub.billcode.vo.BillCodeContext;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;


public class DataCodeUtils {
	
	private String allflag = "a";
	
	private String groupflag = "g";
	
	private String orgflag = "o";
	
	private DataCodeInfo codeInfo;
	
	public DataCodeUtils(DataCodeInfo cf){
		this.codeInfo=cf;
	}
	
	public void createBillCode(SuperVO[] vos){
		
		Map<String, List<SuperVO>> index = new java.util.HashMap();
		
		for(SuperVO vo:vos){
			
			String vbillcode = (String)vo.getAttributeValue(this.codeInfo.getCodeItem());
			
			if ((vbillcode == null) || (vbillcode.length() < 0)){
				
				 String key = createKey(vo);
				 
				 List<SuperVO> list = null;
				 if (index.containsKey(key)) {
					 list = (List)index.get(key);
				 } else {
					 list = new ArrayList();
					 index.put(key, list);
				 }
				 list.add(vo);
			}
		}
		
		for (List<SuperVO> list : index.values()) {
			createCode(list);
		}
		
	}

	private void createCode(List<SuperVO> list) {
		// TODO 自动生成的方法存根

		int size = list.size();
		if (size == 0) {
			return;
		}
		
		SuperVO[] supVo = new SuperVO[size];
		supVo = (SuperVO[])list.toArray(supVo);
		
		String cbilltype = this.codeInfo.getCbilltype();
		String cgroup = supVo[0].getAttributeValue(this.codeInfo.getGroupItem()).toString();
     
		String pk_org = supVo[0].getAttributeValue(this.codeInfo.getOrgItem()).toString();
     
		String[] vbillcodes = null;
		
		try
		{
	
			IBillcodeManage codemanage = (IBillcodeManage)NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
         	
			vbillcodes = codemanage.getBatchBillCodesByVOArray(cbilltype, cgroup, pk_org, supVo);
 
			
			if (!isValidBillCode(vbillcodes, size)) {
				String message = NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0289");
         
 
 
				ExceptionUtils.wrappBusinessException(message);
			}
		}
		catch (Exception e) {
			ExceptionUtils.wrappException(e);
    	}
		
		int cursor = 0;
		
		if (vbillcodes != null) {
			for (SuperVO bill : list) {
				
				bill.setAttributeValue(this.codeInfo.getCodeItem(), vbillcodes[cursor]);
				
				cursor++;
			}
     }
   
		
		
	}
	
	
	private boolean isValidBillCode(String[] vbillcodes, int billsize) {
		
		if ((vbillcodes == null) || (vbillcodes.length == 0)) {
			return false;
		}
		
		if (vbillcodes.length != billsize) {
			return false;
		}
		
		for (int i = 0; i < vbillcodes.length; i++) {
			
			if ((vbillcodes[i] == null) || (nc.vo.jcom.lang.StringUtil.isEmpty(vbillcodes[i])))
				return false;
			}
		
			return true;
		}

	private String createKey(SuperVO vo) {
		// TODO 自动生成的方法存根
		String cgroupid = (String)vo.getAttributeValue(this.codeInfo.getGroupItem());
		
		String corgid = (String)vo.getAttributeValue(this.codeInfo.getOrgItem());
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.codeInfo.getCbilltype());
		buffer.append(",");
		buffer.append(cgroupid);
		buffer.append(",");
		buffer.append(corgid);
		return buffer.toString();
	}
	
	
	public BillCodeContext getBillCodeContext(SuperVO vo){
		
	    IBillcodeManage codemanage = (IBillcodeManage)NCLocator.getInstance().lookup(IBillcodeManage.class.getName());
	    
	    String cbilltype = this.codeInfo.getCbilltype();
	    String cgroup = (String)vo.getAttributeValue(this.codeInfo.getGroupItem());
	    
	    String pk_org = (String)vo.getAttributeValue(this.codeInfo.getOrgItem());
	    

	   	
	    BillCodeContext context = null;
	    try {
	        context = codemanage.getBillCodeContext(cbilltype, cgroup, pk_org);
	  
	    } catch (Exception e) {
	      ExceptionUtils.wrappException(e);
	    }
	    
	    if (context == null) {
	      String message = NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0176");

	      ExceptionUtils.wrappBusinessException(message);
	    }
	    
	    return context;
	 }
	
	
	public void checkUnique(SuperVO[] vos){
		
		if ((vos == null) || (vos.length == 0)) {
	      return;
	    }
	    
	    Map<String, BillCodeContext> map = new java.util.HashMap();
	    
	    for (SuperVO vo : vos) {
	    	
	      BillCodeContext context = getBillCodeContext(vo);
	      map.put(vo.getPrimaryKey(), context);
	      
	    }
	    
	    DataAccessUtils utils = new DataAccessUtils();
	    IRowSet set = null;
	    StringBuilder errorBillCodes = new StringBuilder();
	    LockOperator lock = new LockOperator();

	    String sql = getCheckUniqueSql(vos, map);
	    List<String> reduplicateBillCodes = new ArrayList();
	    if (!PubAppTool.isNull(sql)) {
	      set = utils.query(sql);
	      while (set.next()) {
	        if (set.getInt(1) > 1) {
	          reduplicateBillCodes.add(set.getString(0));
	          errorBillCodes.append(',').append(set.getString(0));
	        }
	      }
	    }
	    
	    sql = getCheckGroupUinqueSql(vos, map);
	    
	    if (!PubAppTool.isNull(sql)) {
	      set = utils.query(sql);
	      while (set.next()) {
	        if (set.getInt(1) > 1) {
	          reduplicateBillCodes.add(set.getString(0));
	          errorBillCodes.append(',').append(set.getString(0));
	        }
	      }
	    }
	    
	    sql = getCheckOrgUinqueSql(vos, map);
	    if (!PubAppTool.isNull(sql)) {
	      set = utils.query(sql);
	      while (set.next()) {
	        if (set.getInt(1) > 1) {
	          reduplicateBillCodes.add(set.getString(0));
	          errorBillCodes.append(',').append(set.getString(0));
	        }
	      }
	    }
	    
	    if (errorBillCodes.length() > 0) {
	    	
	    	String cgroup = vos[0].getAttributeValue(this.codeInfo.getGroupItem()).toString();
	      
	    	String pk_org = vos[0].getAttributeValue(this.codeInfo.getOrgItem()).toString();
	      
	    	String cbilltype = this.codeInfo.getCbilltype();
	    	
	    	IBillcodeManage codemanage = (IBillcodeManage)NCLocator.getInstance().lookup(IBillcodeManage.class.getName());

	        try {
	          
	        	BillCodeContext bcCtx = codemanage.getBillCodeContext(cbilltype, cgroup, pk_org);
	          
	        	errorBillCodes.deleteCharAt(0);
	        	errorBillCodes.insert(0, ':');
	          
	
	        	if (!bcCtx.isEditable()) {
	        		for (String reduplicateBillCode : reduplicateBillCodes) {
	        			codemanage.AbandonBillCode_RequiresNew(cbilltype, cgroup, pk_org, reduplicateBillCode);
	        		}
	            
	        		errorBillCodes.insert(0, NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0345"));
	            
	        		DuplicateException duplicateEx = new DuplicateException(errorBillCodes.toString());
	            
	        		ExceptionUtils.wrappException(duplicateEx);
	        	} else {
	        		errorBillCodes.insert(0, NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0175"));
	        	}
	          
	        	ExceptionUtils.wrappBusinessException(errorBillCodes.toString());
	        }
	        catch (BusinessException e) {
	          ExceptionUtils.wrappException(e);
	        }
	      
	    }
	  }
	
	private String getCheckUniqueSql(SuperVO[] vos, Map<String, BillCodeContext> map){
	     
		String tableName = vos[0].getMetaData().getPrimaryAttribute().getColumn().getTable().getName();
	     
		Set<String> billcodelist = new HashSet();
	     
	    for (int i = 0; i < vos.length; i++) {
	    	
	       BillCodeContext context = (BillCodeContext)map.get(vos[i].getPrimaryKey());
	       
	       if (PubAppTool.isEqual(context.getUniquescope(), this.allflag)) {
	    	   
	    	   billcodelist.add(StringUtils.trim(vos[i].getAttributeValue(this.codeInfo.getCodeItem()).toString()));
	    	   
	       }
	       
	    }
	     
	    if (billcodelist.size() == 0) {
	    	return null;
	    }
	     
	    
	    String[] billCodes = (String[])billcodelist.toArray(new String[0]);
	     
	    SqlBuilder builder = new SqlBuilder();
	    builder.append("select ");
	    builder.append(this.codeInfo.getCodeItem());
	    builder.append(", count(*) billCodeCount ");
	    builder.append(" from ");
	    builder.append(tableName);
	    builder.append("  where dr = 0 and ");
	     
	 
	    BillCodeInSqlBuilder sqlBuilder = new BillCodeInSqlBuilder();
	    builder.append(sqlBuilder.buildBillCodeSql(this.codeInfo.getCodeItem(), billCodes));
	    
	    builder.append(" group by ");
	    builder.append(this.codeInfo.getCodeItem());
	    
	    return builder.toString();
	}
	
	private String getCheckGroupUinqueSql(SuperVO[] bills, Map<String, BillCodeContext> map){
		
		String tableName = bills[0].getMetaData().getPrimaryAttribute().getColumn().getTable().getName();
	     
		Set<String> billcodelist = new HashSet();
		Set<String> grouplist = new HashSet();
	     
	    for (int i = 0; i < bills.length; i++) {
	       BillCodeContext context = (BillCodeContext)map.get(bills[i].getPrimaryKey());
	       if (PubAppTool.isEqual(context.getUniquescope(), this.groupflag)) {
	         billcodelist.add(StringUtils.trim(bills[i].getAttributeValue(this.codeInfo.getCodeItem()).toString()));
	         
	         grouplist.add(bills[i].getAttributeValue(this.codeInfo.getGroupItem()).toString());
	       }
	    }
	     
	    if (billcodelist.size() == 0) {
	       return null;
	    }
	     
	 
	    String[] billCodes = (String[])billcodelist.toArray(new String[0]);
	    String[] groups = (String[])grouplist.toArray(new String[0]);
	     
	    SqlBuilder builder = new SqlBuilder();
	    builder.append("select ");
	    builder.append(this.codeInfo.getCodeItem());
	    builder.append(", count(*) billCodeCount ");
	    builder.append(", " + this.codeInfo.getGroupItem());
	    builder.append(" from ");
	    builder.append(tableName);
	    builder.append("  where dr = 0 and ");
	    builder.append(this.codeInfo.getGroupItem(), groups);
	    builder.append(" and ");
	     
	    BillCodeInSqlBuilder sqlBuilder = new BillCodeInSqlBuilder();
	    builder.append(sqlBuilder.buildBillCodeSql(this.codeInfo.getCodeItem(), billCodes));
	     
	    builder.append(" group by ");
	    builder.append(this.codeInfo.getCodeItem());
	     
	 
	    builder.append(", " + this.codeInfo.getGroupItem());
	    return builder.toString();
	}
	
	  
	private String getCheckOrgUinqueSql(SuperVO[] bills, Map<String, BillCodeContext> map){
		  
		String tableName = bills[0].getMetaData().getPrimaryAttribute().getColumn().getTable().getName();
	      
	    Set<String> billcodelist = new HashSet();
	    Set<String> orglist = new HashSet();
	      
	    for (int i = 0; i < bills.length; i++) {
	        BillCodeContext context = (BillCodeContext)map.get(bills[i].getPrimaryKey());
	        if (PubAppTool.isEqual(context.getUniquescope(), this.orgflag)) {
	          
	        	billcodelist.add(StringUtils.trim(bills[i].getAttributeValue(this.codeInfo.getCodeItem()).toString()));
	          
	          orglist.add(bills[i].getAttributeValue(this.codeInfo.getOrgItem()).toString());
	        }
	    }
	      
	    
	    if (billcodelist.size() == 0) {
	        return null;
	    }
	      
	    String[] billCodes = (String[])billcodelist.toArray(new String[0]);
	      
	    String[] orgs = (String[])orglist.toArray(new String[0]);
	      
	    SqlBuilder builder = new SqlBuilder();
	    builder.append("select ");
	    builder.append(this.codeInfo.getCodeItem());
	    builder.append(", count(*) billCodeCount ");
	    builder.append(", " + this.codeInfo.getOrgItem());
	    builder.append(" from ");
	    builder.append(tableName);
	    builder.append("  where dr = 0 and ");
	    builder.append(this.codeInfo.getOrgItem(), orgs);
	    builder.append(" and ");
	      
	    BillCodeInSqlBuilder sqlBuilder = new BillCodeInSqlBuilder();
	    builder.append(sqlBuilder.buildBillCodeSql(this.codeInfo.getCodeItem(), billCodes));
	      
	    builder.append(" group by ");
	    builder.append(this.codeInfo.getCodeItem());
	      
	  
	    builder.append(", " + this.codeInfo.getOrgItem());
	      
	    return builder.toString();
	}
	
	private class BillCodeInSqlBuilder{
		
	  public BillCodeInSqlBuilder() {}
	  
	  private String buildBillCodeSql(String billCodeName, String[] billCodes)
	  {
	    LockOperator lock = new LockOperator();
	    lock.lock(billCodes, "出现并发！");
	    
	
	
	    SqlBuilder sql = new SqlBuilder();
	    int length = billCodes.length;
	    if (length <= 100) {
	      sql.append(billCodeName, billCodes);
	    } else {
	      sql.append(billCodeName);
	      sql.append(" in ");
	      sql.startParentheses();
	      sql.append(" select id1 from ");
	      
	      String temptable = get(billCodes);
	      sql.append(temptable);
	      sql.endParentheses();
	    }
	    return sql.toString();
	  }
	  
	  private String get(String[] billCodes) {
	    String tableName = null;
	    int length = billCodes.length;
	    if (length <= 500) {
	      tableName = get(billCodes, "TEMP_PUBAPP_C_5H");
	    } else if (length <= 1000) {
	      tableName = get(billCodes, "TEMP_PUBAPP_C_1T");
	    } else if (length <= 5000) {
	      tableName = get(billCodes, "TEMP_PUBAPP_C_5T");
	    } else if (length <= 10000) {
	      tableName = get(billCodes, "TEMP_PUBAPP_C_10T");
	    } else {
	      tableName = get(billCodes, "TEMP_PUBAPP_C_L");
	    }
	    
	    return tableName;
	  }
	
	  
	  private String get(String[] billCodes, String tableName) {
	    List<List<Object>> data = new ArrayList();
	    
	    int length = billCodes.length;
	    for (int i = 0; i < length; i++) {
	      List<Object> row = new ArrayList();
	      data.add(row);
	      row.add(StringUtils.trim(billCodes[i]));
	    }
	    String[] columns = { "id1" };
	    String[] columnTypes = { "VARCHAR(40)" };
	    JavaType[] types = { JavaType.String };
	    
	    nc.impl.pubapp.pattern.database.TempTable dao = new nc.impl.pubapp.pattern.database.TempTable();
	    String name = dao.getTempTable(tableName, columns, columnTypes, columns, types, data);
	    
	    return name;
	  }
	}

}
