package nc.itf.so.qs.appinterface.MobileApprove;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;

public interface IMobileApproveService {
	
	public Map<String,String> getRefName(String voclass,String entityname,String value) throws Exception;
	
	public abstract List<Map<String, Object>> getPFTaskList(String pk_group, String userid, String date, String statuskey, String statuscode, String cond,int startline, int count,int pagenum) throws BusinessException;
	
	public abstract Map<String, Object> getTaskBill(String groupid, String userid, String taskid, String statuskey, String statuscode) throws BusinessException;
	
	public abstract Map<String, Object> getUserInfo(String usercode, String password,String date) throws Exception;
		  
	public abstract Map<String, Object> getApprovedDetail(String groupid, String userid, String taskid, String statuskey, String statuscode, int startline, int count) throws Exception;
	
	public Map<String, Object> getUserList(String groupid, String userid, String taskid, int startline, int count, String condition) throws BusinessException;
	
	public Map<String, Object> doAgree(String groupid, String userid, String taskid, String note, String cuserids) throws BusinessException;
	
	public Map<String, Object> getAssignPsnList(String groupid, String userid, String taskid, String isagree, int startline, int count, String condition) throws Exception;
	
	public Map<String, Object> doDisAgree(String groupid, String userid, String taskid, String note, String cuserids) throws BusinessException;
	
	public abstract Map<String, Object> doReject(String groupid, String userid, String taskid, String note, String nodeid) throws BusinessException;
	
	public abstract Map<String, Object> getRejectNodeList(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4) throws Exception;
	
	public List<Map<String, Object>> getRefDataList(String pk_group,String cond,String voclass,String entityname,String field,int startline, int count,int pagenum) throws BusinessException;
	
	public abstract List<Map<String, Object>> getBillList(String pk_group, String userid, String date, String statuskey, String statuscode, String cond,int startline, int pagenum,int count) throws BusinessException;

	public abstract Map<String, Object> getBillCardInfo(String pk_group, String userid, String billid, String billtype,String statuskey, String statuscode) throws BusinessException;
	
	public Map<String,Object> PreOrderSave(String pk_group,String userid,JSONObject prejson,String billstatus) throws BusinessException;

	public List<Map<String, Object>> queryBomVerInfo(String pk_group,String user, String mat,String pk_org, int startline, int count, int pagenum) throws BusinessException;
	
	public List<Map<String, Object>> queryBomChInfo(String pk_group,String user, String bomid,String pk_org, int startline, int count, int pagenum) throws BusinessException;

	public Map<String, Object> queryHfPrice(String pk_group,String user,String pk_org,String mat,String bomid,String pk_cus,double num) throws BusinessException;
	
	public Map<String, Object> PreOrderDelete(String pk_group, String userid,String bid) throws BusinessException;
	
	public Map<String, Object> PreOrderApprove(String pk_group, String userid,String bid) throws BusinessException;
	
	public Map<String, Object> PreOrderSendApprove(String pk_group, String userid,String bid) throws BusinessException;
	
	public Map<String, Object> getTempletInfo(String pk_group, String userid,String billtype) throws BusinessException, JSONException;
	
	public Map<String, Object> MatrialEditAfterHandler(String pk_group,String user,String billtype,String pk_org, String material,String customer,String corigcurrencyid,String dbilldate) throws Exception;
	
	public List<Map<String, Object>> queryPreOrderInfo(String pk_group,String user, String cond, int startline,int count,int pagenum) throws BusinessException;
	
}

