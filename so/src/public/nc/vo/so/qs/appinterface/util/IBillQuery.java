package nc.vo.so.qs.appinterface.util;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import nc.vo.pf.mobileapp.query.IPaginationQuery;
import nc.vo.pub.BusinessException;

public abstract interface IBillQuery extends IPaginationQuery<Map<String, Object>>
{
  public abstract TaskMetaData queryTaskMetaData(String paramString)
    throws BusinessException;
  
  public abstract Map<String, Object> queryBillInfo(String pk_group,String user,String billtype,String paramString)
    throws BusinessException;
 
  
}

