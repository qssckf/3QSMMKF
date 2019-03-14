package nc.impl.pub.ace;

import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoDeleteBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoInsertBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoUpdateBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoSendApproveBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoUnSendApproveBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoApproveBP;
import nc.bs.so.shipmentsinfo.ace.bp.AceShipmentsInfoUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
		import nc.vo.so.qs.sc.AggShipmentsVO;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
		import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.bill.pagination.PaginationTransferObject;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.impl.pubapp.pattern.data.bill.BillQuery;

public abstract class AceShipmentsInfoPubServiceImpl {
	//新增
    public AggShipmentsVO[] pubinsertBills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
        try {
          // 数据库中数据和前台传递过来的差异VO合并后的结果
          BillTransferTool<AggShipmentsVO> transferTool =new BillTransferTool<AggShipmentsVO>(clientFullVOs);
          // 调用BP
          AceShipmentsInfoInsertBP action = new AceShipmentsInfoInsertBP();
          AggShipmentsVO[] retvos = action.insert(clientFullVOs);
          // 构造返回数据
          return transferTool.getBillForToClient(retvos);
        }catch (Exception e) {
          ExceptionUtils.marsh(e);
        }
        return null;
    }
    //删除
    public void pubdeleteBills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
        try {
          // 调用BP
          new AceShipmentsInfoDeleteBP().delete(clientFullVOs);
        } catch (Exception e) {
          ExceptionUtils.marsh(e);
        }
    }
    //修改
    public AggShipmentsVO[] pubupdateBills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
        try {
          //加锁 + 检查ts
          BillTransferTool<AggShipmentsVO> transferTool =new BillTransferTool<AggShipmentsVO>(clientFullVOs);
          AceShipmentsInfoUpdateBP bp = new AceShipmentsInfoUpdateBP();
          AggShipmentsVO[] retvos = bp.update(clientFullVOs, originBills);
          // 构造返回数据
		          return transferTool.getBillForToClient(retvos);
		        }catch (Exception e) {
		          ExceptionUtils.marsh(e);
		        }
		          return null;
    		}


    //分页查询方法，查询所有PK
    public String[] pubquerypkbills(IQueryScheme queryScheme) throws BusinessException {
    // String beanId=(String) queryScheme.get(QueryConstants.BEAN_ID);
    	StringBuffer sql = new StringBuffer();
    	QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    	String mainAlias = processor.getMainTableAlias();
    	sql.append(" select distinct ");
    	sql.append(mainAlias);
    	sql.append(".");
    	sql.append("pk_shipments");
    	sql.append(processor.getFinalFromWhere());
    	DataAccessUtils dao = new DataAccessUtils();
    	IRowSet rowset = dao.query(sql.toString());
    	String[] keys = rowset.toOneDimensionStringArray();
    	return keys;
    }
  
    //分页查询方法，根据PK查单据
    public AggShipmentsVO[] pubquerybillbypkbills(String[] pks) throws BusinessException {
    	AggShipmentsVO[] bills = null;
    	BillQuery<AggShipmentsVO> query = new BillQuery<AggShipmentsVO>(AggShipmentsVO.class);
    	bills = query.query(pks);
    	return PaginationUtils.filterNotExistBills(bills, pks);
  		}
	//提交
  	public AggShipmentsVO[] pubsendapprovebills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
  		AceShipmentsInfoSendApproveBP bp = new AceShipmentsInfoSendApproveBP();
  		AggShipmentsVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
  		return retvos;
  	}
  
  	//收回
	public AggShipmentsVO[] pubunsendapprovebills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
	    AceShipmentsInfoUnSendApproveBP bp = new AceShipmentsInfoUnSendApproveBP();
	    AggShipmentsVO[] retvos = bp.unSend(clientFullVOs, originBills);
	    return retvos;
		}

	//审批
	public AggShipmentsVO[] pubapprovebills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceShipmentsInfoApproveBP bp = new AceShipmentsInfoApproveBP();
		AggShipmentsVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}
  
	//弃审
	public AggShipmentsVO[] pubunapprovebills(AggShipmentsVO[] clientFullVOs,AggShipmentsVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceShipmentsInfoUnApproveBP bp = new AceShipmentsInfoUnApproveBP();
		AggShipmentsVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}				
}