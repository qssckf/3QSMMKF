package nc.impl.so.qs.sc.planbill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import nc.bs.bd.bp.utils.MDQueryUtil;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.mmpac.pmo.pac0002.bp.PMOInsertBP;
import nc.bs.so.qs.sc.intoprod.bp.IntoProdDeleteBP;
import nc.bs.so.qs.sc.intoprod.bp.IntoProdInsertBP;
import nc.bs.so.qs.sc.intoprod.bp.IntoProdReleaseBP;
import nc.bs.so.qs.sc.intoprod.bp.IntoProdUpdateBP;
import nc.bs.so.qs.sc.readyplan.bp.RdDeleteBP;
import nc.bs.so.qs.sc.readyplan.bp.RdInsertBP;
import nc.bs.so.qs.sc.readyplan.bp.RdMakePMOBP;
import nc.bs.so.qs.sc.readyplan.bp.RdReleaseBP;
import nc.bs.so.qs.sc.readyplan.bp.RdReturnPMOBP;
import nc.bs.so.qs.sc.readyplan.bp.RdUpdateBp;
import nc.hr.frame.persistence.AppendBeanArrayProcessor;
import nc.itf.mmpps.plo.IPloReleaseService;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.mmpac.pmo.pps.IPublicPMOService4PPS;
import nc.util.mmf.busi.measure.MeasureHelper;
import nc.util.mmf.busi.measure.NumScaleUtil;
import nc.util.mmf.busi.service.MaterialPubService;
import nc.util.mmf.framework.base.MMArrayUtil;
import nc.util.mmf.framework.base.MMNumberUtil;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemMeasureParam;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;
import nc.vo.so.qs.sc.planbill.readyplan.PmoViewVO;
import nc.vo.util.BDVersionValidationUtil;

public class RdMmServiceImpl implements IRdMmService{

	private BaseDAO dao;
	
	public BaseDAO getDao() {
		
		if(this.dao==null){
			
			this.dao=new BaseDAO();
		}
		
		return dao;
	}
	
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}
	
	@Override
	public RdPorductDetailVO[] Insert(RdPorductDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			RdInsertBP action=new RdInsertBP();
			
			return action.insert(objs);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		

	}

	@Override
	public RdPorductDetailVO[] update(RdPorductDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		try{
			
			RdPorductDetailVO[] oldVo=(RdPorductDetailVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			RdUpdateBp action=new RdUpdateBp();
			
			return action.update(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
	}

	@Override
	public void delete(RdPorductDetailVO objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			RdDeleteBP action=new RdDeleteBP();
			
			action.delete(new RdPorductDetailVO[]{objs});
			
		}catch(Exception e){
			throw new BusinessException(e);
		}

	}

	@Override
	public RdPorductDetailVO[] DoRelease(RdPorductDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			RdPorductDetailVO[] oldVo=(RdPorductDetailVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			RdReleaseBP action=new RdReleaseBP();

			return action.doRelease(objs);
			
		}catch(Exception e){
			
			throw new BusinessException(e);
			
		}
		

	}
	
	
	
	public static UFDouble numberOfInputs(UFDouble outputNum, MaterialPlanVO mp)
	{
	  UFDouble coefficient = null;
	  UFDouble one = new UFDouble(1);
	  if ((MMValueCheck.isEmpty(mp)) || (mp.getWasterrate() == null)) {
	    coefficient = UFDouble.ZERO_DBL;
	  }
	  else {
	    coefficient = new UFDouble(mp.getWasterrate());
	  }
	  one = one.sub(coefficient);
	  
	  return outputNum.div(one);
	}
	
	public void fillNumberByNum(PMOAggVO[] aggvos)
	{
	     Map<String, MaterialPlanVO> resultMaps = new HashMap();
	     
	     getWasterrate(aggvos, resultMaps);
	     for (PMOAggVO aggvo : aggvos) {
	       PMOItemVO[] itemvos = (PMOItemVO[])aggvo.getChildren(PMOItemVO.class);
	       for (PMOItemVO itemvo : itemvos)
	       {
	         UFDouble nNum = numberOfInputs(itemvo.getNnum(), (MaterialPlanVO)resultMaps.get(itemvo.getCmaterialvid() + aggvo.getParentVO().getPk_org()));
	         
	 
	         nNum = new NumScaleUtil().calNumScale(nNum, itemvo.getCunitid());
	         if ((MMNumberUtil.isEqualZero(itemvo.getNastnum())) || (MMValueCheck.isEmpty(itemvo.getNastnum())))
	         {
	           PMOItemMeasureParam param = new PMOItemMeasureParam();
	           MeasureHelper.fillAssNumber(itemvo, param, "nnum", "nastnum");
	         }
	         MaterialPlanVO materPlanvo = (MaterialPlanVO)resultMaps.get(itemvo.getCmaterialvid() + aggvo.getParentVO().getPk_org());
	         if ((!MMValueCheck.isEmpty(materPlanvo)) && (!MMValueCheck.isEmpty(materPlanvo.getWasterrate()))) {
	           itemvo.setNrwxis(materPlanvo.getWasterrate());
	         }
	         else {
	           itemvo.setNrwxis(UFDouble.ZERO_DBL);
	         }
	         
	         itemvo.setNplanputnum(nNum);
	         
	         itemvo.setNmmnum(itemvo.getNnum());
	         itemvo.setNmmastnum(itemvo.getNastnum());
	         if (MMNumberUtil.isEqual(nNum, itemvo.getNnum()))
	         {
	           itemvo.setNplanputastnum(itemvo.getNastnum());
	         }
	         else
	         {
	           PMOItemMeasureParam param = new PMOItemMeasureParam();
	           MeasureHelper.fillAssNumber(itemvo, param, "nplanputnum", "nplanputastnum");
	         }
	       }
	     }
	   }
	
	private void getWasterrate(PMOAggVO[] aggvos, Map<String, MaterialPlanVO> resultMaps)
	{
	  MapSet<String, String> materialVid = new MapSet();
	  for (PMOAggVO aggvo : aggvos) {
	    PMOItemVO[] itemvos = (PMOItemVO[])aggvo.getChildren(PMOItemVO.class);
	    for (PMOItemVO itemvo : itemvos) {
	      materialVid.put(aggvo.getParentVO().getPk_org(), itemvo.getCmaterialvid());
	    }
	  }
	  
	  for (Iterator i$ = materialVid.entrySet().iterator(); i$.hasNext();) { 
	
		Entry set = (Map.Entry)i$.next();
	    
	    Map<String, MaterialPlanVO> resultMap = MaterialPubService.queryMaterialPlanInfoByPks((String[])((Set)set.getValue()).toArray(new String[0]), (String)set.getKey(), new String[] { "wasterrate" });
	    
	    for (Map.Entry<String, MaterialPlanVO> result : resultMap.entrySet())
	      resultMaps.put((String)result.getKey() + (String)set.getKey(), result.getValue());
	  }
	  Map.Entry<String, Set<String>> set;
	}

	@Override
	public PMOAggVO[] pushPMO(PMOAggVO[] aggvos) throws BusinessException {
		// TODO 自动生成的方法存根
	
		
		try{
			
			IPublicPMOService4PPS ploservice=(IPublicPMOService4PPS)NCLocator.getInstance().lookup(IPublicPMOService4PPS.class);
			
			if (MMArrayUtil.isEmpty(aggvos)) {
			     return null;
			}
			
			MapList<String, PMOAggVO> mapAggvo = new MapList();
			fillNumberByNum(aggvos);
			
			for (PMOAggVO aggvo : aggvos) {
				
		       mapAggvo.put(aggvo.getParentVO().getPk_org(), aggvo);
		       
		    }
			
			List<PMOAggVO> returnobj=new ArrayList();
		     
		    for (Entry<String, List<PMOAggVO>> set : mapAggvo.entrySet()){
		    	
		    	PMOAggVO[] aggs = ploservice.RdpushMO((PMOAggVO[])((List)set.getValue()).toArray(new PMOAggVO[0]));
		    	
		    	for(PMOAggVO agg:aggs){
		    		
		    		returnobj.add(agg);
		    		
		    	}
		       
		    }
		    
		    return returnobj.toArray(new PMOAggVO[returnobj.size()]);
		    
		}catch (Exception e) {
			
		     ExceptionUtils.marsh(e);
		     
		}
		
		return null;
		
	}

	@Override
	public RdPorductDetailVO[] DoRdPMO(RdPorductDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			RdPorductDetailVO[] oldVo=(RdPorductDetailVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			RdMakePMOBP action=new RdMakePMOBP();
			
			return action.MakeRdPMO(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}

	}

	@Override
	public RdPorductDetailVO[] DoRePMO(RdPorductDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			RdPorductDetailVO[] oldVo=(RdPorductDetailVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			RdReturnPMOBP action=new RdReturnPMOBP();
			
			return action.ReturnPMO(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
	}

	@Override
	public SuperVO[] queryPmoVObyPks(String[] maids) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String inSql = BDSqlInUtil.getInSql(maids, false);
		
		
		String sql="select ma.machcode machinecode,ma.machname machinename,a.cpmohid,billtype.billtypename ctrantypename,a.dbilldate,a.vbillcode,cus.code cuscode,cus.name cusname,dept.name dept,mat.code matcode,mat.name matname,mat.materialspec matspec,def.name splitstofftype,b.vdef4 silkwide,b.vdef5 elongation,b.vdef6 tension,nvl(b.vdef7,'N') spacer,b.vdef8 otherproduction,b.vdef9 tagline,b.vdef12 latitudedensity,b.vdef10 width,b.vdef11 length,zdw.name unit,b.nnum,fdw.name qunit,b.nastnum,b.tplanstarttime startdate,b.tplanendtime enddate from mm_pmo a";
		sql=sql+" left join mm_mo b on a.cpmohid=b.cpmohid";
		sql=sql+" left join bd_billtype billtype on a.ctrantypeid=billtype.pk_billtypeid";
		sql=sql+" left join bd_customer cus on b.vdef1=cus.pk_customer";
		sql=sql+" left join org_dept dept on b.cdeptid=dept.pk_dept";
		sql=sql+" left join so_machine ma on b.vdef2=ma.pk_maschine";
		sql=sql+" left join bd_material mat on b.cmaterialid=mat.pk_material";
		sql=sql+" left join bd_defdoc def on b.vdef3=def.pk_defdoc";
		sql=sql+" left join bd_measdoc zdw on b.cunitid=zdw.pk_measdoc";
		sql=sql+" left join bd_measdoc fdw on b.castunitid=fdw.pk_measdoc";
		sql=sql+" where a.dr=0 and b.dr=0 and b.vdef2 in "+inSql;
		
		PmoViewVO[] vos=(PmoViewVO[])executeQueryAppendableVOs(sql,PmoViewVO.class);
		
		return vos;
		
	}

	private <T> T[] executeQueryAppendableVOs(String sql,Class voclass) throws DAOException {
		
		return (T[])this.getDao().executeQuery(sql.toString(), new AppendBeanArrayProcessor(voclass));
	}

	@Override
	public String[] queryVOPksByIp(String sqlwhere) throws BusinessException {
		// TODO 自动生成的方法存根
		
		System.out.println("进入查询方法");
		
		Collection<RdPorductDetailVO> rdvos=getMDQueryService().queryBillOfVOByCond(RdPorductDetailVO.class, sqlwhere, false);
		
		RdPorductDetailVO[] vos = rdvos.toArray(new RdPorductDetailVO[rdvos.size()]);
		
		String[] pks = new String[0];
		
		if(vos!=null){
			pks = new String[vos.length];
			for (int i = 0; i < vos.length; i++) {
				pks[i]=vos[i].getPk_rdpd();
			}
		}
		return pks;

	}

	@Override
	public Object[] queryVOsByPksByIp(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		Collection<RdPorductDetailVO> vos= getMDQueryService().queryBillOfVOByPKs(RdPorductDetailVO.class, pks, false);
		
		return vos.toArray(new RdPorductDetailVO[vos.size()]);
	}

	@Override
	public void validateVOTs(SuperVO[] vos) throws BusinessException {
		// TODO 自动生成的方法存根
		BDVersionValidationUtil.validateSuperVO(vos);
	}

	@Override
	public IntoProdDetailVO[] InsertIp(IntoProdDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			IntoProdInsertBP action = new IntoProdInsertBP();
			
			return action.insert(objs);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
	}

	@Override
	public void deleteIp(IntoProdDetailVO obj) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			IntoProdDeleteBP action = new IntoProdDeleteBP();
			action.delete(new IntoProdDetailVO[]{obj});
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
		
	}

	@Override
	public IntoProdDetailVO[] updateIp(IntoProdDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			IntoProdDetailVO[] oldVo=(IntoProdDetailVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			IntoProdUpdateBP action = new IntoProdUpdateBP();
			
			return action.update(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
	}

	@Override
	public IntoProdDetailVO[] DoRelease(IntoProdDetailVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		try{
			
			IntoProdDetailVO[] oldvos = (IntoProdDetailVO[]) MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			IntoProdReleaseBP action=new IntoProdReleaseBP();

			return action.doRelease(objs);
			
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
		
	}

}
