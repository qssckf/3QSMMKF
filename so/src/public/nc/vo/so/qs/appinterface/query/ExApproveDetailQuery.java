package nc.vo.so.qs.appinterface.query;
 
 import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

 import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.IPFMobileAppService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ArrayProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.MultiLangUtil;
import nc.vo.pf.mobileapp.MobileAppUtil;
import nc.vo.pf.mobileapp.TaskMetaData;
import nc.vo.pf.mobileapp.query.IPaginationQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.trade.sqlutil.IInSqlBatchCallBack;
import nc.vo.trade.sqlutil.InSqlBatchCaller;
import nc.vo.wfengine.definition.WorkflowTypeEnum;
import nc.vo.wfengine.pub.WFTask;
import nc.vo.wfengine.pub.WfTaskOrInstanceStatus;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ExApproveDetailQuery
   implements IPaginationQuery<Map<String, Object>>
 {
   private static final String BILLTYPE = "#billtype#";
   private static final String BILLID = "#billid#";
   private final String SQL = "select pk_checkflow from pub_workflownote where pk_billtype='#billtype#' and billversionpk='#billid#' and approvestatus in (" + WfTaskOrInstanceStatus.Inefficient.getIntValue() + ","+ WfTaskOrInstanceStatus.Finished.getIntValue() + "," + WfTaskOrInstanceStatus.Started.getIntValue() + ") and actiontype like '" + "Z" + "%'";
   
 
 
 
 
 
 
 
 
 
   private TaskMetaData tmd = null;
   
   public ExApproveDetailQuery(TaskMetaData tmd) {
     this.tmd = tmd;
   }
   
   public String getIdentifier()
   {
     return this.tmd.getBillType() + this.tmd.getBillId();
   }
   
   public String getPksSql()
   {
     return this.SQL.replace("#billtype#", this.tmd.getBillType()).replace("#billid#", this.tmd.getBillId());
   }
   
   public List<Map<String, Object>> queryByPks(String[] pks) throws BusinessException
   {
	   
	 JSONArray rets=new JSONArray();
     final IUAPQueryBS qry = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class);
     
     StringBuffer sb = new StringBuffer();
     String suffix = MultiLangUtil.getCurrentLangSeqSuffix();
     sb.append("select ");
     sb.append("u_t.user_name, ");
     sb.append("u_t.user_name");
     sb.append(suffix);
     sb.append(", ");
     sb.append("i_t.billmaker, i_t.startts,i_t.procstatus from pub_wf_instance i_t left join sm_user u_t on i_t.billmaker=u_t.cuserid  where i_t.billtype=? and i_t.billversionpk=? and i_t.workflow_type=?");
     SQLParameter param = new SQLParameter();
     param.addParam(this.tmd.getBillType());
     param.addParam(this.tmd.getBillId());
     
     param.addParam(WorkflowTypeEnum.Approveflow.getIntValue());
     Object[] summaries = (Object[])qry.executeQuery(sb.toString(), param, new ArrayProcessor());
     
     final Map<String, WorkflownoteVO> pkVOMap = new HashMap();
     try
     {
       InSqlBatchCaller caller = new InSqlBatchCaller(pks);
       caller.execute(new IInSqlBatchCallBack()
       {
         public Object doWithInSql(String inSql)
           throws BusinessException, SQLException
         {
           String suffix = MultiLangUtil.getCurrentLangSeqSuffix();
           
           StringBuffer sb = new StringBuffer();
           
 
           sb.append("select ");
           sb.append("u_t.user_name");
           sb.append(suffix);
           sb.append(" as checknameml, ");
           sb.append(" u_t.user_name as checkname, w_t.* from pub_workflownote w_t left join sm_user u_t on w_t.checkman=u_t.cuserid");
           sb.append(" where w_t.pk_checkflow in ");
           sb.append(inSql);
           Collection<WorkflownoteVO> col = (Collection)qry.executeQuery(sb.toString(), new BeanListProcessor(WorkflownoteVO.class));
           
           if ((col != null) && (col.size() > 0)) {
             for (WorkflownoteVO vo : col) {
               pkVOMap.put(vo.getPk_checkflow(), vo);
             }
           }
           
           return null;
         }
       });
       
       
       if ((pks.length > 0) && (summaries != null))
       {
      	 
         JSONObject subjson=new JSONObject();
         
         Map<String, Object> submitHistoryUnitMap = MobileAppUtil.createHashMap();
         
         
         
         String billmakerName = summaries[0] == null ? null : String.valueOf(summaries[0]);
         String billmakerNameMl = summaries[1] == null ? null : String.valueOf(summaries[1]);
         String billmaker = summaries[2] == null ? null : String.valueOf(summaries[2]);
         String startts = summaries[3] == null ? null : String.valueOf(summaries[3]);
         int procstatus = summaries[4] == null ? 0 : Integer.parseInt(String.valueOf(summaries[4]));
         
         
         subjson.put("submitpsnname", billmakerName);
         subjson.put("submitpsnid", billmaker);
         subjson.put("workflowtype", "submit");
         subjson.put("time", startts);
         rets.put(subjson);
   
         
         IPFMobileAppService srv = (IPFMobileAppService)NCLocator.getInstance().lookup(IPFMobileAppService.class);
         for (int i = 0; i < pks.length; i++) {
           WorkflownoteVO wvo = (WorkflownoteVO)pkVOMap.get(pks[i]);
           
           JSONObject historyjson=new JSONObject();
           
           if (wvo != null) {
        	   
        	   boolean isMakebill = "MAKEBILL".equalsIgnoreCase(wvo.getActiontype());
        	   String action = WFTask.resolveApproveResult(isMakebill ? null : wvo.getApproveresult());
        	   
        	   if (i != pks.length - 1)
               {
        		   historyjson.put("workflowtype", "solved");
        		   historyjson.put("actionname", action);
        		   historyjson.put("approvenote", wvo.getChecknote());
     
               }
               else if (procstatus == WfTaskOrInstanceStatus.Finished.getIntValue())
               {
            	   historyjson.put("workflowtype", "final");
            	   historyjson.put("actionname", action);
            	   historyjson.put("advice", wvo.getChecknote());
               }
               else {
            	   historyjson.put("workflowtype", "handling");
               }
        	   
        	   historyjson.put("senddate", wvo.getSenddate());
        	   if (!StringUtil.isEmptyWithTrim(wvo.getSendernameml())) {
        		   historyjson.put("senderman",wvo.getSendernameml());
        	   }else{
        		   historyjson.put("senderman", wvo.getSendername());
        	   }
        	   
        	   historyjson.put("senderid", wvo.getSenderman());
        	   
        	   historyjson.put("dealtime", wvo.getDealdate());
        	   
        	   if (!StringUtil.isEmptyWithTrim(wvo.getChecknameml())) {
        		   historyjson.put("psnname", wvo.getChecknameml());
               } else {
                   historyjson.put("psnname", wvo.getCheckname());
               }
        	   
        	   historyjson.put("psnnameid", wvo.getCheckman());
        	   
        	   rets.put(historyjson);
        	   
           }

         }
         
         
 
        
         
       }
       
       
     } catch (Exception e) {
       throw new BusinessException(e);
     }
     
     Map<String,Object> map=new HashMap();
     map.put("flowhistory", rets);
     
     List<Map<String,Object>> list=new ArrayList();
     
     list.add(map);
     
     return list;

   }
 }

