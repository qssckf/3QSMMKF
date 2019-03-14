package nc.impl.so.qs.sc.planbill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;

import nc.bs.bd.bp.utils.MDQueryUtil;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.core.service.TimeService;
import nc.bs.so.qs.planbill.bp.rule.PlanBillExpendByTMRule;
import nc.bs.so.qs.sc.planbill.bp.PlanBillDeleteBP;
import nc.bs.so.qs.sc.planbill.bp.PlanBillExendBPByTM;
import nc.bs.so.qs.sc.planbill.bp.PlanBillExpendBP;
import nc.bs.so.qs.sc.planbill.bp.PlanBillInsertBP;
import nc.bs.so.qs.sc.planbill.bp.PlanBillUnExpendBP;
import nc.bs.so.qs.sc.planbill.bp.PlanBillUpdateBP;
import nc.hr.frame.persistence.AppendBeanArrayProcessor;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.cust.custclass.CustClassVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.pub.sqlutil.BDSqlInUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.qs.sc.MaschineVO;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.planbill.process.BomChVO;
import nc.vo.so.qs.sc.planbill.process.BomVersionVO;
import nc.vo.util.BDVersionValidationUtil;

public class PlanBillServiceImpl implements IPlanBillSerive{
	
	private BaseDAO dao;
	private DataAccessUtils dataacc;
	
	private DataAccessUtils getDataAcc(){
		if (this.dataacc==null){
			this.dataacc= new DataAccessUtils();
		}
		return this.dataacc;
	}
	
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
	public String[] queryVOPks(String sqlwhere) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String sql="select case when so_mmplan.pk_planbill is null then so_preorder_b.cpreorderbid else so_mmplan.pk_planbill end pk_planbill,so_preorder_b.pk_group,so_preorder_b.pk_org,org_orgs.pk_vid pk_org_v,'38' vsrctype,so_preorder.vbillcode vscrcode,so_preorder_b.cpreorderid vsrcid,so_preorder_b.cpreorderbid vsrcbid,so_mmplan.vbillcode,so_preorder.ccustomerid pk_customer,";
		sql=sql+"so_preorder.vdef1 endcustomer,so_preorder.dbilldate,so_preorder_b.dsenddate deliverydate,so_preorder_b.cmaterialid pk_material,so_preorder_b.cunitid cunit,so_preorder_b.castunitid qunit,so_preorder_b.cprojectid cprojectid,so_preorder_b.cproductorid cproductorid,";
		sql=sql+"so_preorder_b.cvendorid cvendorid,so_preorder_b.vfree1,so_preorder_b.vfree2,so_preorder_b.vfree3,so_preorder_b.vfree4,so_preorder_b.vfree5,so_preorder_b.vfree6,so_preorder_b.vfree7,so_preorder_b.vfree8,so_preorder_b.vfree9,so_preorder_b.vfree10,";
		sql=sql+"so_preorder_b.vbdef1 silkwide,so_preorder_b.vbdef2 tagline,so_preorder_b.vbdef3 cutcloth,so_preorder_b.vbdef4 latitudedensity,so_preorder_b.vbdef5 width,so_preorder_b.vbdef6 tensiondd,so_preorder_b.vbdef7 length,so_preorder_b.vbdef8 otherproduction,so_preorder_b.vbdef9 elongation,so_preorder_b.vbdef10 spacer,";
		sql=sql+"so_preorder_b.nnum plannum,so_preorder_b.vchangerate rate,so_preorder_b.nastnum plannastnum,so_mmplan.bomid,so_mmplan.vbomversion,nvl(so_mmplan.sfexand,'N') sfexand,nvl(so_mmplan.sfclose,'N') sfclose,so_mmplan.vbdef1,so_mmplan.vbdef2,so_mmplan.vbdef3,so_mmplan.vbdef4,so_mmplan.vbdef5,so_mmplan.vbdef6,so_mmplan.vbdef7,so_mmplan.vbdef8,so_mmplan.vbdef9,so_mmplan.vbdef10,so_mmplan.vbdef11,";
		sql=sql+"so_mmplan.vbdef12,so_mmplan.vbdef13,so_mmplan.vbdef14,so_mmplan.vbdef15,so_mmplan.vbdef16,so_mmplan.vbdef17,so_mmplan.vbdef18,so_mmplan.vbdef19,so_mmplan.vbdef20,so_mmplan.creator,so_mmplan.creationtime,so_mmplan.modifier,so_mmplan.modifiedtime";
		sql=sql+" from so_preorder_b inner join so_preorder on so_preorder_b.cpreorderid=so_preorder.cpreorderid";
		sql=sql+" left join so_mmplan on so_preorder_b.cpreorderbid=so_mmplan.vsrcbid";
		sql=sql+" left join org_orgs on so_preorder_b.pk_org=org_orgs.pk_org";
		sql=sql +" where so_preorder.fstatusflag=2 and so_preorder_b.dr=0 and so_preorder.dr=0 and "+sqlwhere;
		
		MmPlanBillVO[] vos=(MmPlanBillVO[])executeQueryAppendableVOs(sql,MmPlanBillVO.class);
		
		
		String[] pks = new String[0];
		
		if(vos!=null){
			pks = new String[vos.length];
			for (int i = 0; i < vos.length; i++) {
				pks[i]=vos[i].getVsrcbid();
			}
		}
		return pks;
		
	}
	
	
	private <T> T[] executeQueryAppendableVOs(String sql,Class voclass) throws DAOException {
		
		return (T[])this.getDao().executeQuery(sql.toString(), new AppendBeanArrayProcessor(voclass));
	}

	@Override
	public SuperVO[] queryVObyPks(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String inSql = BDSqlInUtil.getInSql(pks, false);
		
		String sql="select case when so_mmplan.pk_planbill is null then so_preorder_b.cpreorderbid else so_mmplan.pk_planbill end pk_planbill,so_preorder_b.pk_group,so_preorder_b.pk_org,org_orgs.pk_vid pk_org_v,'38' vsrctype,so_preorder.vbillcode vscrcode,so_preorder_b.cpreorderid vsrcid,so_preorder_b.cpreorderbid vsrcbid,so_mmplan.vbillcode,so_preorder.ccustomerid pk_customer,";
		sql=sql+"so_preorder.vdef1 endcustomer,so_preorder.dbilldate,so_preorder_b.dsenddate deliverydate,so_preorder_b.cmaterialid pk_material,so_preorder_b.cunitid cunit,so_preorder_b.castunitid qunit,so_preorder_b.cprojectid cprojectid,so_preorder_b.cproductorid cproductorid,";
		sql=sql+"so_preorder_b.cvendorid cvendorid,so_preorder_b.vfree1,so_preorder_b.vfree2,so_preorder_b.vfree3,so_preorder_b.vfree4,so_preorder_b.vfree5,so_preorder_b.vfree6,so_preorder_b.vfree7,so_preorder_b.vfree8,so_preorder_b.vfree9,so_preorder_b.vfree10,";
		sql=sql+"so_preorder_b.vbdef1 silkwide,so_preorder_b.vbdef2 tagline,so_preorder_b.vbdef3 cutcloth,so_preorder_b.vbdef4 latitudedensity,so_preorder_b.vbdef5 width,so_preorder_b.vbdef6 tensiondd,so_preorder_b.vbdef7 length,so_preorder_b.vbdef8 otherproduction,so_preorder_b.vbdef9 elongation,so_preorder_b.vbdef10 spacer,";
		sql=sql+"so_preorder_b.nnum plannum,so_preorder_b.vchangerate rate,so_preorder_b.nastnum plannastnum,so_mmplan.bomid,so_mmplan.plandate,so_mmplan.vbomversion,nvl(so_mmplan.sfexand,'N') sfexand,nvl(so_mmplan.sfclose,'N') sfclose,so_mmplan.vbdef1,so_mmplan.vbdef2,so_mmplan.vbdef3,so_mmplan.vbdef4,so_mmplan.vbdef5,so_mmplan.vbdef6,so_mmplan.vbdef7,so_mmplan.vbdef8,so_mmplan.vbdef9,so_mmplan.vbdef10,so_mmplan.vbdef11,";
		sql=sql+"so_mmplan.vbdef12,so_mmplan.vbdef13,so_mmplan.vbdef14,so_mmplan.vbdef15,so_mmplan.vbdef16,so_mmplan.vbdef17,so_mmplan.vbdef18,so_mmplan.vbdef19,so_mmplan.vbdef20,so_mmplan.creator,so_mmplan.creationtime,so_mmplan.modifier,so_mmplan.modifiedtime,so_mmplan.ts";
		sql=sql+" from so_preorder_b inner join so_preorder on so_preorder_b.cpreorderid=so_preorder.cpreorderid";
		sql=sql+" left join so_mmplan on so_preorder_b.cpreorderbid=so_mmplan.vsrcbid";
		sql=sql+" left join org_orgs on so_preorder_b.pk_org=org_orgs.pk_org";
		sql=sql +" where so_preorder.fstatusflag=2 and so_preorder_b.dr=0 and so_preorder.dr=0 and so_preorder_b.cpreorderbid in "+inSql;
		
		MmPlanBillVO[] vos=(MmPlanBillVO[])executeQueryAppendableVOs(sql,MmPlanBillVO.class);
		
		return vos;
	}

	@Override
	public Boolean QueryPlanBillIsExists(String pk) throws BusinessException {
		// TODO 自动生成的方法存根
		
		Collection<MmPlanBillVO> planVos= this.getDao().retrieveByClause(MmPlanBillVO.class, "pk_planbill='"+pk+"'");
		
		if(planVos!=null && planVos.size()==1){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public SuperVO[] queryBomVersion(String pk_materail,String pk_org) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String sql="select bd_bom.pk_org,cbomid,hcmaterialid pk_material,bd_material.name matername,bd_material.materialspec materspec,bd_material.materialtype matertype,";
		sql=sql+"decode(bd_bom.fbomtype,1,'生产BOM',2,'包装BOM',3,'配置BOM') bomtype,bd_bom.hversion bomversion,bd_bom.hvnote memo from bd_bom";
		sql=sql+" left join bd_material on bd_bom.hcmaterialid=bd_material.pk_material";
		sql=sql+" where bd_bom.dr=0 and bd_bom.hcmaterialid='"+pk_materail+"' and bd_bom.pk_org='"+pk_org+"'";
		
		BomVersionVO[] vos=(BomVersionVO[])executeQueryAppendableVOs(sql,BomVersionVO.class);
		
		return vos;
	}

	@Override
	public SuperVO[] queryBomChildren(String bomid, String pk_org) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String sql="select hcmaterialid pk_materail,parentmatcode,parentmatname,matercode,matername,vitemversion cbomid,cbomversion,materspec,matertype,level bomlevel,munit,nitemnum itemnum,vnote itemmemo,munitid,qunitid,c_materail,rate from ";
		sql=sql+"(select a.pk_org,b.cbom_bid,a.cbomid,a.hcmaterialid ,mar.code parentmatcode,mar.name parentmatname,a.hversion,b.vitemversion,bbom.hversion cbomversion,b.cmaterialid,cmar.code matercode,cmar.name matername,cmar.materialspec materspec,cmar.materialtype matertype,unit.name munit,b.nitemnum,b.vnote,b.cmeasureid munitid,b.cassmeasureid qunitid,b.cmaterialid c_materail,b.vfree1 kz,b.vfree2 ys,b.vchangerate rate ";
		sql=sql+"from bd_bom a ";
		sql=sql+"inner join bd_bom_b b on a.cbomid=b.cbomid ";
		sql=sql+"left join bd_material mar on a.hcmaterialid=mar.pk_material ";
		sql=sql+"left join bd_material cmar on b.cmaterialid=cmar.pk_material ";
		sql=sql+"left join bd_bom bbom on b.vitemversion=bbom.cbomid ";
		sql=sql+"left join bd_measdoc unit on b.cmeasureid=unit.pk_measdoc ";
		sql=sql+"where a.pk_org='"+pk_org+"' and a.dr=0 and b.dr=0 and b.vitemversion<>'~') start with cbomid='"+bomid+"' connect by prior vitemversion=cbomid order by level";
		
		BomChVO[] vos=(BomChVO[])executeQueryAppendableVOs(sql,BomChVO.class);
		
		return vos;
	}
	
	
	
	
	public SuperVO[] queryBomleafChildren(String bomid, String pk_org) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String sql="select hcmaterialid pk_materail,parentmatcode,parentmatname,matercode,matername,cbomversion,materspec,matertype,level bomlevel,munit,nitemnum itemnum,vnote itemmemo,munitid,qunitid,c_materail,rate from ";
		sql=sql+"(select a.pk_org,b.cbom_bid,a.cbomid,a.hcmaterialid ,mar.code parentmatcode,mar.name parentmatname,a.hversion,b.vitemversion,bbom.hversion cbomversion,b.cmaterialid,cmar.code matercode,cmar.name matername,cmar.materialspec materspec,cmar.materialtype matertype,unit.name munit,b.nitemnum,b.vnote,b.cmeasureid munitid,b.cassmeasureid qunitid,b.cmaterialid c_materail,b.vfree1 kz,b.vfree2 ys,b.vchangerate rate ";
		sql=sql+"from bd_bom a ";
		sql=sql+"inner join bd_bom_b b on a.cbomid=b.cbomid ";
		sql=sql+"left join bd_material mar on a.hcmaterialid=mar.pk_material ";
		sql=sql+"left join bd_material cmar on b.cmaterialid=cmar.pk_material ";
		sql=sql+"left join bd_bom bbom on b.vitemversion=bbom.cbomid ";
		sql=sql+"left join bd_measdoc unit on b.cmeasureid=unit.pk_measdoc ";
		sql=sql+"where a.pk_org='"+pk_org+"' and a.dr=0 and b.dr=0) start with cbomid='"+bomid+"' connect by prior vitemversion=cbomid order by level";
		
		BomChVO[] vos=(BomChVO[])executeQueryAppendableVOs(sql,BomChVO.class);
		
		return vos;
	}

	@Override
	public MmPlanBillVO[] Insert(MmPlanBillVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		try{
			PlanBillInsertBP action=new PlanBillInsertBP();
			
			return action.insert(objs);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
		
	}

	@Override
	public MmPlanBillVO[] update(MmPlanBillVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			MmPlanBillVO[] oldVo=(MmPlanBillVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			PlanBillUpdateBP action = new PlanBillUpdateBP();
			
			return action.update(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
	}

	@Override
	public MmPlanBillVO[] Expend(MmPlanBillVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
	
		try{
			
			MmPlanBillVO[] oldVo=(MmPlanBillVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			PlanBillExpendBP action = new PlanBillExpendBP();
			
			return action.Expend(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
	}

	@Override
	public MmPlanBillVO[] UnExpend(MmPlanBillVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			MmPlanBillVO[] oldVo=(MmPlanBillVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			PlanBillUnExpendBP action = new PlanBillUnExpendBP();
			
			return action.UnExpend(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		

	}

	@Override
	public SuperVO[] quyerPlanDetailVOs(String wheresql) throws BusinessException {
		// TODO 自动生成的方法存根
		
		Collection<PlanProduceDetailVO> col = getMDQueryService().queryBillOfVOByCond(PlanProduceDetailVO.class,wheresql,false);
	
		return col.toArray(new PlanProduceDetailVO[col.size()]);
		
	}

	@Override
	public SuperVO[] queryVObypks(Class clazz, String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		Collection vos=getMDQueryService().queryBillOfVOByPKs(clazz,pks,false);
		
		return (SuperVO[]) vos.toArray(new SuperVO[vos.size()]);
	}

	@Override
	public SuperVO[] queryMasInfoBySql(String pk_material,String cus,String dept,String wheresql) throws BusinessException {
		// TODO 自动生成的方法存根
		
		Map<String,String> clsmap=queryMatInnerCodeByMatid(new String[]{pk_material});
		
		if(!clsmap.containsKey(pk_material)){
			return null;
		}
		
		String sql="select a.pk_maschine,a.machcode,a.machname from so_machine a";
		sql=sql+" left join so_mache_mater b on a.pk_maschine=b.machine_material";
		sql=sql+" left join bd_material mat on b.pk_material=mat.pk_material";
		sql=sql+" left join bd_marbasclass matcls on b.pk_materclass=matcls.pk_marbasclass";
		sql=sql+" where a.dr=0 and a.mstatus=1 and ('"+clsmap.get(pk_material)+"' like matcls.innercode||'%' or b.pk_material='"+pk_material+"')";
		sql=sql+" group by a.pk_maschine,a.machcode,a.machname";
		
		MaschineVO[] mavos=(MaschineVO[])executeQueryAppendableVOs(sql,MaschineVO.class);
		
		if(mavos!=null && mavos.length>0){
			
			Set<String> mapks=new HashSet();
			
			for(MaschineVO vo:mavos){
				mapks.add(vo.getPk_maschine());
			}
			
			
			clsmap=queryCusClsInnerCodeBypk(new String[]{cus});
			
			if(!clsmap.containsKey(cus)){
				return null;
			}
			
			String where = BDSqlInUtil.getInSql(mapks.toArray(new String[mapks.size()]), false);
			
			
			sql="select a.pk_maschine,a.machcode,a.machname from so_machine a";
			sql=sql+" left join so_mache_cus b on a.pk_maschine=b.machine_customer";
			sql=sql+" left join bd_customer cus on b.pk_customer=cus.pk_customer";
			sql=sql+" left join bd_custclass cuscls on b.pk_cusclass=cuscls.pk_custclass";
			sql=sql+" where a.dr=0 and a.mstatus=1 and ('"+clsmap.get(cus)+"' like cuscls.innercode||'%' or b.pk_customer='"+cus+"') and a.pk_maschine in "+where;
			sql=sql+" group by a.pk_maschine,a.machcode,a.machname";
			
			MaschineVO[] mavos1=(MaschineVO[])executeQueryAppendableVOs(sql,MaschineVO.class);
			
			if(mavos1!=null && mavos1.length>0){
				
				for(MaschineVO vo:mavos1){
					mapks.add(vo.getPk_maschine());
				}
				
				where = BDSqlInUtil.getInSql(mapks.toArray(new String[mapks.size()]), false);
				
				sql="select a.pk_maschine,a.machcode,a.machname from so_machine a";
				sql=sql+" left join so_mache_dept b on a.pk_maschine=b.machine_dept";
				sql=sql+" where a.dr=0 and a.mstatus=1 and b.pk_dept='"+dept+"' and a.pk_maschine in "+where;	
				
				if(wheresql!=null){
					sql=sql+wheresql;
				}
				
				MaschineVO[] retvo=(MaschineVO[])executeQueryAppendableVOs(sql,MaschineVO.class);
				
				if(retvo!=null && retvo.length>0){
					
					List<String> retpk=new ArrayList();
					
					for(MaschineVO mvo:retvo){
						retpk.add(mvo.getPk_maschine());
					}
					
					where = BDSqlInUtil.getInSql(retpk.toArray(new String[retpk.size()]), false);
					
					sql="select * from so_machine where pk_maschine in "+where;
					
					MaschineVO[] maret=(MaschineVO[])executeQueryAppendableVOs(sql,MaschineVO.class);
					
					return maret;
					
				}else{
					
					return null;
				}
				
			}else{
				return null;
			}
			

		}else{
			return null;
		}
		
		
	}

	private List<String> ArrayList() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Map<String, String> queryMatInnerCodeByMatid(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		
		Map<String, String> clsmap = new HashMap();
		
		if (ArrayUtils.isEmpty(pks)){
			return clsmap;
		}
		
		String where = BDSqlInUtil.formInSQLWithoutAnd("pk_material", pks, false);
		
		Collection<MaterialVO> col = getDao().retrieveByClause(MaterialVO.class, where, new String[] { "pk_material", "pk_marbasclass" });
		
		if (CollectionUtils.isEmpty(col)) {
			return clsmap;
		}
		
		List<String> clspks=new ArrayList();
		 
		for(MaterialVO vo:col){
			
			clspks.add(vo.getPk_marbasclass());
		}
		
		where = BDSqlInUtil.formInSQLWithoutAnd("pk_marbasclass", clspks.toArray(new String[col.size()]), false);
		
		Collection<MarBasClassVO> clscol=getDao().retrieveByClause(MarBasClassVO.class, where, new String[] { "pk_marbasclass", "innercode " });
		
		if (CollectionUtils.isEmpty(col)) {
			return clsmap;
		}
		
		
		for(MarBasClassVO matcls:clscol){
			
			for(MaterialVO mat:col){
				
				if(matcls.getPk_marbasclass().equals(mat.getPk_marbasclass())){
					clsmap.put(mat.getPk_material(), matcls.getInnercode());
					break;
				}
				
			}

		}
		
		return clsmap;
	}

	@Override
	public Map<String, String> queryCusClsInnerCodeBypk(String[] pks) throws BusinessException {
		// TODO 自动生成的方法存根
		
		Map<String, String> clsmap = new HashMap();
		
		if (ArrayUtils.isEmpty(pks)){
			return clsmap;
		}
		
		String where = BDSqlInUtil.formInSQLWithoutAnd("pk_customer", pks, false);
		
		Collection<nc.vo.bd.cust.CustomerVO> col = getDao().retrieveByClause(nc.vo.bd.cust.CustomerVO.class, where, new String[] { "pk_customer", "pk_custclass"});
		
		if (CollectionUtils.isEmpty(col)) {
			return clsmap;
		}
		
		List<String> clspks=new ArrayList();
		 
		for(CustomerVO vo:col){
			
			clspks.add(vo.getPk_custclass());
		}
		
		where = BDSqlInUtil.formInSQLWithoutAnd("pk_custclass", clspks.toArray(new String[col.size()]), false);
		
		Collection<CustClassVO> clscol=getDao().retrieveByClause(CustClassVO.class, where, new String[] { "pk_custclass", "innercode"});
		
		if (CollectionUtils.isEmpty(col)) {
			return clsmap;
		}
		
		
		for(CustClassVO cuscls:clscol){
			
			for(CustomerVO cus:col){
				
				if(cuscls.getPk_custclass().equals(cus.getPk_custclass())){
					clsmap.put(cus.getPk_customer(), cuscls.getInnercode());
					break;
				}
				
			}

		}
		
		return clsmap;
	}

	@Override
	public void validateVOTs(SuperVO[] vos) throws BusinessException {
		// TODO 自动生成的方法存根
		
		BDVersionValidationUtil.validateSuperVO(vos);
		
	}

	@Override
	public String[] delete(MmPlanBillVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			PlanBillDeleteBP bp=new PlanBillDeleteBP();
			
			return bp.delete(objs);
			
			
		}catch(Exception e){
			throw new BusinessException(e);
		}

	}

	@Override
	public Map<String, Map<String, UFDouble>> querBominfoByHybridPrice(String pk_org,Set<String> mats) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String sql;
		
		Map<String, Map<String, UFDouble>> ret=new HashMap();
		
		for(String mat:mats){
			
			String[] ids=mat.split("#");
			
			Map<String,Double> levelNumMap=new HashMap();
			Map<String,UFDouble> leafMap=new HashMap();
			
			sql="select hcmaterialid fmater,CONNECT_BY_ISLEAF leaf,level jc,cmaterialid cmater,fl,nitemnum zsl from";
			sql=sql+" (select a.pk_org,b.cbom_bid,a.cbomid,a.hcmaterialid,mar.def2 flx,a.hversion,b.vitemversion,b.cmaterialid,b.vdef3,case when b.vdef3='~' then mar1.def3 else b.vdef3 end fl,b.nitemnum from bd_bom a";
			sql=sql+" inner join bd_bom_b b on a.cbomid=b.cbomid";
			sql=sql+" left join bd_material mar on a.hcmaterialid=mar.pk_material";
			sql=sql+" left join bd_material mar1 on b.cmaterialid=mar1.pk_material";
			sql=sql+" where a.pk_org='"+pk_org+"' and a.dr=0 and b.dr=0) start with cbomid='"+ids[1]+"'";
			sql=sql+" connect by prior vitemversion=cbomid order by level";
			
			IRowSet row=this.getDataAcc().query(sql);
			
			
			
			levelNumMap.put("0|"+ids[0], 1.00);
			
			while(row.next()){
				
				String level=String.valueOf(row.getInt(2)-1);
				
				String fatherMaterial=row.getString(0);
				
				String bomkey=level+"|"+fatherMaterial;
				
				if(levelNumMap.containsKey(bomkey)){
					
					levelNumMap.put(row.getString(2)+"|"+row.getString(3), row.getUFDouble(5).multiply(levelNumMap.get(bomkey)).toDouble());
					
					if("1".equals(row.getString(1))){
						
						if(leafMap.containsKey(row.getString(4))){
							
							leafMap.put(row.getString(4), leafMap.get(row.getString(4)).add(row.getUFDouble(5).multiply(levelNumMap.get(bomkey))));
							
						}else{
							leafMap.put(row.getString(4), row.getUFDouble(5).multiply(levelNumMap.get(bomkey)));
						}
						
						
					}
					
				}else{
					throw new BusinessException("没有找到上层物料数量基数信息，计算中断！");
				}
				
				
				
			}
			
			ret.put(mat, leafMap);
			
			
			
			
		}
		
		
		return ret;
	}

	@Override
	public Map<String, Map<String, UFDouble>> querCusPriceByHybridPrice(String pk_org,Set<String> cus) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String ServerTime=TimeService.getInstance().getUFDateTime().toString().substring(0, 10);
		
		Map<String, Map<String, UFDouble>> ret=new HashMap();
		
		String inSql = BDSqlInUtil.getInSql(cus.toArray(new String[cus.size()]), false);
		
		String sql="select pk_org,cus,mat,ksrq,jsrq,dj from (select key1 pk_org,key2 cus,key4 mat,key3_name ksrq,lead(key3_name,1,0)over(PARTITION BY key1,key2,key4 order by key1,key2,key4) jsrq,zb_1 dj from";
		sql=sql+" (SELECT repdataprovider.key1 key1, repdataprovider.key2 key2, repdataprovider.key3 key3, repdataprovider.key4 key4, repdataprovider.key2_name key2_name, repdataprovider.key2_code key2_code, repdataprovider.key3_name key3_name, repdataprovider.key3_code key3_code, repdataprovider.key4_name key4_name, repdataprovider.key4_code key4_code, repdataprovider.org_name org_name, repdataprovider.org_code org_code, repdataprovider.zb_1 zb_1, repdataprovider.ver ver, repdataprovider.pk_org pk_org, repdataprovider.pk_group pk_group FROM (SELECT pubdata_table.alone_id alone_id, org_table.code org_code, org_table.name org_name, org_table.pk_reportorg pk_org, org_table.pk_group pk_group, pubdata_table.keyword1 key1, pubdata_table.keyword2 key2, key_detailtable1.code key2_code, key_detailtable1.name1 key2_name, pubdata_table.keyword3 key3, key_detailtable2.code key3_code, key_detailtable2.name1 key3_name, pubdata_table.keyword4 key4, key_detailtable3.code key4_code, key_detailtable3.name1 key4_name, iufo_measure_data_imn1vn42.m10000 zb_1, iufo_measure_data_imn1vn42.alone_id alone_id1, pubdata_table.ver ver FROM iufo_measpub_2BTW pubdata_table LEFT OUTER JOIN iufo_measure_data_imn1vn42 ON pubdata_table.alone_id = iufo_measure_data_imn1vn42.alone_id LEFT OUTER JOIN iufo_keydetail_2424 key_detailtable1 ON pubdata_table.keyword2 = key_detailtable1.keyval LEFT OUTER JOIN iufo_keydetail_5790 key_detailtable2 ON pubdata_table.keyword3 = key_detailtable2.keyval LEFT OUTER JOIN iufo_keydetail_7986 key_detailtable3 ON pubdata_table.keyword4 = key_detailtable3.keyval INNER JOIN org_reportorg org_table ON pubdata_table.keyword1 = org_table.pk_reportorg WHERE (iufo_measure_data_imn1vn42.alone_id IS NOT NULL)) repdataprovider) order by key1,key2,key4,key3_name) jg";
		sql=sql+" where pk_org='"+pk_org+"' and cus in "+inSql+" and ksrq>='2018-10-01' and (case when jsrq='0' then '2099-12-31' else jsrq end)>'2018-10-01'";
		
		IRowSet row=this.getDataAcc().query(sql);
		
		while(row.next()){
			
			String cuspk=row.getString(1);
			String matfl=row.getString(2);
			UFDouble dj=row.getUFDouble(5);
			
			if(ret.containsKey(cuspk)){
				ret.get(cuspk).put(matfl, dj);
			}else{
				
				Map<String,UFDouble> flmap=new HashMap();
				
				flmap.put(matfl, dj);
				
				ret.put(cuspk, flmap);
				
			}
			
		}
		
		
		return ret;
	}

	@Override
	public SuperVO[] queryBomVersionByTM(String pk_materail, String pk_org) throws BusinessException {
		// TODO 自动生成的方法存根
		
		String sql="select a.pk_org,b.cmaterialid,cmar.code matercode,a.cbomid,b.nitemnum";
		sql=sql+" from bd_bom a";
		sql=sql+" inner join bd_bom_b b on a.cbomid=b.cbomid";
		sql=sql+" left join bd_material mar on a.hcmaterialid=mar.pk_material";
		sql=sql+" left join bd_material cmar on b.cmaterialid=cmar.pk_material";
		sql=sql+" left join bd_bom bbom on b.vitemversion=bbom.cbomid";
		sql=sql+" left join bd_measdoc unit on b.cmeasureid=unit.pk_measdoc";
		sql=sql+" where a.pk_org='"+pk_org+"' and  a.dr=0 and b.dr=0 and a.cbomid=(select cbomid from bd_bom where hcmaterialid='"+pk_materail+"' and dr=0 and hvdef1='Y' and pk_org='"+pk_org+"')";
		
		IRowSet row=this.getDataAcc().query(sql);
		
		if(row.next()){
			
			String cmat=row.getString(1);
			
			sql="select bd_bom.pk_org,cbomid,hcmaterialid pk_material,bd_material.name matername,bd_material.materialspec materspec,bd_material.materialtype matertype,";
			sql=sql+"decode(bd_bom.fbomtype,1,'生产BOM',2,'包装BOM',3,'配置BOM') bomtype,bd_bom.hversion bomversion,bd_bom.hvnote memo from bd_bom";
			sql=sql+" left join bd_material on bd_bom.hcmaterialid=bd_material.pk_material";
			sql=sql+" where bd_bom.dr=0 and bd_bom.hcmaterialid='"+cmat+"' and bd_bom.pk_org='"+pk_org+"'";
			
			BomVersionVO[] vos=(BomVersionVO[])executeQueryAppendableVOs(sql,BomVersionVO.class);
			
			for(BomVersionVO vo:vos){
				vo.setTmbomid(row.getString(3));
				vo.setTmitemnum(row.getUFDouble(4).toDouble());
			}
			
			return vos;
			
		}
		
		return null;
	}

	@Override
	public MmPlanBillVO[] ExpendByTm(MmPlanBillVO[] objs) throws BusinessException {
		// TODO 自动生成的方法存根
		
		try{
			
			MmPlanBillVO[] oldVo=(MmPlanBillVO[])MDQueryUtil.lockValidateToRetrieveVO(objs);
			
			PlanBillExendBPByTM action = new PlanBillExendBPByTM();
			
			return action.ExpendByTM(objs, oldVo);
			
		}catch(Exception e){
			throw new BusinessException(e);
		}
		
	
	}

}
