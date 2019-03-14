package nc.bs.so.qs.planbill.bp.rule;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import nc.bs.bd.cache.CacheProxy;
import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.UsePermChangeEvent;
import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFrameworkUtil;
import nc.bs.uif2.validation.Validator;
import nc.impl.ambd.db.QueryUtil;
import nc.md.MDBaseQueryFacade;
import nc.vo.ambd.common.util.ArrayUtils;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.util.AuditInfoUtil;
import nc.vo.util.BDPKLockUtil;
import nc.vo.util.BDVersionValidationUtil;
import nc.vo.util.bizlock.BizlockDataUtil;

public class BatchDocBaseDAO {
	
	public BatchDocBaseDAO(){}
	
	public BatchOperateVO batchSave(BatchOperateVO vo, List<Validator> insertValList, List<Validator> updateValList, List<Validator> delValList) throws BusinessException{
		
		
		if(vo==null)
		{
			return null;
		}
		
		Object[] deledVOs=vo.getDelObjs();
		batchDelete(deledVOs,delValList);
		
		Object[] addedObjects=vo.getAddObjs();
		addedObjects = batchInsert(addedObjects, insertValList);
		
		Object[] updatedVOs = vo.getUpdObjs();
		updatedVOs = batchUpdate(updatedVOs, updateValList);
		
		
		BatchOperateVO returnVO = new BatchOperateVO();
		returnVO.setDelObjs(deledVOs);
		returnVO.setAddObjs(addedObjects);
		returnVO.setUpdObjs(updatedVOs);
		
		return returnVO;
		
	}
	

	protected <T extends SuperVO> T[] sealOperation(T[] vos,List<Validator> sealVals) throws BusinessException{
		// TODO 自动生成的方法存根
		
		if (ArrayUtils.isEmpty(vos)) {
			return null;
		}
		
		BDPKLockUtil.lockSuperVO(vos);
		
		BizlockDataUtil.lockDataByBizlock(vos);
		
		BDVersionValidationUtil.validateSuperVO(vos);
		
		validateFailure(vos, sealVals);
		
		
		for (T vo : vos) {
			AuditInfoUtil.updateData(vo);
		}
		
		String eventTypeBefore = "1070";
		String eventTypeAfter = "1071";
	
		
		String beanID = MDBaseQueryFacade.getInstance().getBeanByFullClassName(vos[0].getClass().getName()).getID();
		
		BusinessEvent beforeEvent = new BusinessEvent(beanID, eventTypeBefore, vos);
		
		EventDispatcher.fireEvent(beforeEvent);
		
		BaseDAO baseDAO = new BaseDAO();
		baseDAO.updateVOArray(vos);
		
		BusinessEvent afterEvent = new BusinessEvent(beanID, eventTypeAfter, vos);
		
		EventDispatcher.fireEvent(afterEvent);
		
		String[] pk_vos = (String[])getBaseVOIDsWithNotNull(vos).toArray(new String[0]);
		
		List<SuperVO> changedVOs = getChangedVOs(baseDAO, vos[0].getPKFieldName(), vos[0].getClass(), pk_vos);
		
		CacheProxy.fireDataUpdated(vos[0].getTableName());
		
		if ((changedVOs != null) && (changedVOs.size() > 0)) {
			return (T[])changedVOs.toArray(new SuperVO[changedVOs.size()]);
		}
		
		return null;
	}

	private Object[] batchUpdate(Object[] updatedVOs,List<Validator> updateVals) throws BusinessException{
		// TODO 自动生成的方法存根
		
		if ((updatedVOs == null) || (updatedVOs.length == 0)) {
			return null;
		}
		
		SuperVO[] vos = new SuperVO[updatedVOs.length];
		
		String[] changedPks = new String[updatedVOs.length];
		
		for (int i = 0; i < updatedVOs.length; i++) {
			
			vos[i] = ((SuperVO)updatedVOs[i]);
			
			changedPks[i] = vos[i].getPrimaryKey();
			
			AuditInfoUtil.updateData(vos[i]);
			
		}
		
		BDPKLockUtil.lockSuperVO(vos);
		
		BDVersionValidationUtil.validateSuperVO(vos);
		
		BizlockDataUtil.lockDataByBizlock(vos);
		
		validateFailure(vos, updateVals);
		
		String beanID = MDBaseQueryFacade.getInstance().getBeanByFullClassName(vos[0].getClass().getName()).getID();
		
		BusinessEvent beforeEvent = new BusinessEvent(beanID, "1003", vos);
		EventDispatcher.fireEvent(beforeEvent);
		
		BaseDAO baseDAO = new BaseDAO();
		baseDAO.updateVOArray(vos);
		
		List<SuperVO> changedVOs = getChangedVOs(baseDAO, vos[0].getPKFieldName(), vos[0].getClass(), changedPks);
		
		
		CacheProxy.fireDataInserted(vos[0].getTableName(), null);
		
		BusinessEvent afterEvent = new BusinessEvent(beanID, "1004", vos);
		
		EventDispatcher.fireEvent(afterEvent);
		
		EventDispatcher.fireEvent(new UsePermChangeEvent(beanID, "1004"));
		
		if ((changedVOs != null) && (changedVOs.size() > 0)) {
			return (SuperVO[])changedVOs.toArray(new SuperVO[changedVOs.size()]);
		}
		 
		return null;
	}

	private Object[] batchInsert(Object[] addedVOs,List<Validator> insertValList) throws BusinessException {
		// TODO 自动生成的方法存根
		
		 if ((addedVOs == null) || (addedVOs.length == 0)){
			 return null;
		 }
		 
		 SuperVO[] vos = new SuperVO[addedVOs.length];
		 
		 for (int i = 0; i < addedVOs.length; i++) {
			 vos[i] = ((SuperVO)addedVOs[i]);
			 AuditInfoUtil.addData(addedVOs[i]);
		 }
		 
		 //按照vo主键进行加锁，同时操作主键数据时会抛出异常
//		 BizlockDataUtil.lockDataByBizlock(vos);
		 
		 //根据传入的insertVaList验证规则列表对数据进行验证
//		 validateFailure(vos, insertValList);
		 
		 MDBaseQueryFacade.getInstance().getBeanByFullClassName(vos[0].getClass().getName()).getID();
		 
		 String beanID = MDBaseQueryFacade.getInstance().getBeanByFullClassName(vos[0].getClass().getName()).getID();
		 
		 BusinessEvent beforeEvent = new BusinessEvent(beanID, "1001", vos);
		 
		 EventDispatcher.fireEvent(beforeEvent);
		 
		 BaseDAO baseDAO = new BaseDAO();
		 String[] newPks = baseDAO.insertVOArray(vos);
		 
		 List<SuperVO> newVOs = getChangedVOs(baseDAO, vos[0].getPKFieldName(), vos[0].getClass(), newPks);
		 
		 
		 CacheProxy.fireDataInserted(vos[0].getTableName(), null);
		 
		 BusinessEvent afterEvent = new BusinessEvent(beanID, "1002", vos);
		 
		 EventDispatcher.fireEvent(afterEvent);
		 
		 EventDispatcher.fireEvent(new UsePermChangeEvent(beanID, "1002"));
		 
		 if ((newVOs != null) && (newVOs.size() > 0)) {
			 return (SuperVO[])newVOs.toArray(new SuperVO[newVOs.size()]);
		 }
		 
		
		return null;
	}

	private <T extends SuperVO> List<T> getChangedVOs(BaseDAO baseDAO, String pkFieldName,Class className, String[] newPks) throws BusinessException{
		// TODO 自动生成的方法存根
		List<T> results = new ArrayList();
		String insql = pkFieldName + " in " + getInsql(newPks);
		
		try {
			results.addAll(baseDAO.retrieveByClause(className, insql));
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
			
		return results;
		
	}
	
	private String getInsql(String[] keys){
		StringBuilder insql = new StringBuilder();
		if (keys.length > 0) {
			insql.append("(");
			for (String key : keys) {
				insql.append("'");
				insql.append(key);
				insql.append("',");
			}
			insql.deleteCharAt(insql.length() - 1);
			insql.append(")");
		}
		return insql.toString();
	}

	private void validateFailure(SuperVO[] vos, List<Validator> validators) throws BusinessException {
		// TODO 自动生成的方法存根
		if (CollectionUtils.isNotEmpty(validators)) {
			IValidationService validationService = ValidationFrameworkUtil.createValidationService((Validator[])validators.toArray(new Validator[0]));
			
			validationService.validate(vos);
		}

	}

	private void batchDelete(Object[] deledVOs, List<Validator> delVals) throws BusinessException{
		// TODO 自动生成的方法存根
		
		if ((deledVOs == null) || (deledVOs.length == 0)) {
			return;
		}
		SuperVO[] vos = new SuperVO[deledVOs.length];
		
		for (int i = 0; i < deledVOs.length; i++) {
			vos[i] = ((SuperVO)deledVOs[i]);
		}
		
		BDPKLockUtil.lockSuperVO(vos);
		BDVersionValidationUtil.validateSuperVO(vos);
		
		validateFailure(vos, delVals);
		
		String beanID = MDBaseQueryFacade.getInstance().getBeanByFullClassName(vos[0].getClass().getName()).getID();
		
		BusinessEvent beforeEvent = new BusinessEvent(beanID, "1005", vos);
		
		EventDispatcher.fireEvent(beforeEvent);
		
		BaseDAO baseDAO = new BaseDAO();
		baseDAO.deleteVOArray(vos);
		
		CacheProxy.fireDataDeletedBatch(vos[0].getTableName(), (String[])getBaseVOIDsWithNotNull(vos).toArray(new String[0]));
		
		BusinessEvent afterEvent = new BusinessEvent(beanID, "1006", vos);
		
		EventDispatcher.fireEvent(afterEvent);
		
		EventDispatcher.fireEvent(new UsePermChangeEvent(beanID, "1006"));
	}

	private List<String> getBaseVOIDsWithNotNull(SuperVO[] baseVOs) {
		// TODO 自动生成的方法存根
		List<String> baseVOIds = new ArrayList();
		if (ArrayUtils.isEmpty(baseVOs)){
			return baseVOIds;
		}
		for (CircularlyAccessibleValueObject itemVO : baseVOs) {
			try{
				
				String primaryKey = itemVO.getPrimaryKey();
				
				if (!StringUtils.isBlank(primaryKey)){
					 baseVOIds.add(primaryKey);
				}
					
				
			}catch(BusinessException e){
				Logger.error("获取主键出错", e);
			}
			
			
		}
		return baseVOIds;
	}

}
