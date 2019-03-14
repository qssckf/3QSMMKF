package nc.itf.so.qs.sc.planbill.service;

import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.qs.sc.MmPlanBillVO;

public abstract interface IPlanBillSerive {
	
	public abstract String[] queryVOPks(String sqlwhere) throws BusinessException;
	
	public abstract SuperVO[] queryVObyPks(String[] pks) throws BusinessException;
	
	public abstract SuperVO[] queryVObypks(Class clazz,String[] pks) throws BusinessException;
	
	public Boolean QueryPlanBillIsExists(String pk) throws BusinessException;
	
	public abstract SuperVO[] queryBomVersion(String pk_materail,String pk_org) throws BusinessException;
	
	public abstract SuperVO[] queryBomVersionByTM(String pk_materail,String pk_org) throws BusinessException;
	
	public abstract SuperVO[] queryBomChildren(String bomid,String pk_org) throws BusinessException;
	
	public abstract SuperVO[] quyerPlanDetailVOs(String wheresql) throws BusinessException;
	
	public abstract MmPlanBillVO[] Insert(MmPlanBillVO[] objs) throws BusinessException;
	
	public abstract MmPlanBillVO[] update(MmPlanBillVO[] objs) throws BusinessException;
	
	public String[] delete(MmPlanBillVO[] objs) throws BusinessException;
	
	public abstract MmPlanBillVO[] Expend(MmPlanBillVO[] objs) throws BusinessException;
	
	public abstract MmPlanBillVO[] ExpendByTm(MmPlanBillVO[] objs) throws BusinessException;
	
	public abstract MmPlanBillVO[] UnExpend(MmPlanBillVO[] objs) throws BusinessException;
	
	public abstract SuperVO[] queryMasInfoBySql(String pk_material,String cus,String dept,String wheresql) throws BusinessException;
	
	public abstract Map<String, String> queryMatInnerCodeByMatid(String[] pks) throws BusinessException;
	
	public abstract Map<String,String> queryCusClsInnerCodeBypk(String[] pks) throws BusinessException;
	
	public abstract void validateVOTs(SuperVO[] vos) throws BusinessException;
	
	public Map<String,Map<String,UFDouble>> querBominfoByHybridPrice(String pk_org,Set<String> mat) throws BusinessException;
	
	public Map<String,Map<String,UFDouble>> querCusPriceByHybridPrice(String pk_org,Set<String> cus) throws BusinessException;
	
	public SuperVO[] queryBomleafChildren(String bomid, String pk_org) throws BusinessException;

}
