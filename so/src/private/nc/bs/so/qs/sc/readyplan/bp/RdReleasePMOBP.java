package nc.bs.so.qs.sc.readyplan.bp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.core.service.TimeService;
import nc.bs.so.qs.readyplan.bp.rule.UpdatePushRdStautsRule;
import nc.bs.so.qs.sc.plugin.RdPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.template.CommonOperatorTemplate;
import nc.itf.bd.srmbdpub.OrgUnitPubService;
import nc.itf.so.qs.sc.planbill.service.IRdMmService;
import nc.pubitf.mmpac.pmo.pps.IPublicPMOService4PPS;
import nc.pubitf.org.IDeptPubService;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.util.mmf.busi.ModuleEnableCheckUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOAggVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOHeadVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.mmpps.mpm.res.MpmRes;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.qs.sc.RdPorductDetailVO;


public class RdReleasePMOBP {
	

	public RdReleasePMOBP(){};
	
	public class RdReleasePMOOperator implements nc.impl.pubapp.pattern.rule.template.IOperator<RdPorductDetailVO>{

		private IRdMmService rdservice;
		
		public IRdMmService getRdservice() {
			
			if(this.rdservice==null){
				this.rdservice=(IRdMmService)NCLocator.getInstance().lookup(IRdMmService.class);
			}
			return rdservice;
			
		}

		@Override
		public RdPorductDetailVO[] operate(RdPorductDetailVO[] objs) {
			// TODO 自动生成的方法存根
			
			try{
				
				PMOAggVO aggpvo=ConvertToPmo(objs);
				
				PMOAggVO[] aggs=this.getRdservice().pushPMO(new PMOAggVO[]{aggpvo});
				
				PMOAggVO agg=aggs[0];
				
				for(RdPorductDetailVO obj:objs){
					
					obj.setDef2(agg.getParentVO().getVbillcode());
					
				}
				
				
				
			}catch(Exception e){
				
				ExceptionUtils.wrappException(e);
				
			}
			
			return objs;

		}

		private PMOAggVO ConvertToPmo(RdPorductDetailVO[] objs) throws BusinessException {
			// TODO 自动生成的方法存根
			
			try{
				
				IMaterialPubService service = (IMaterialPubService)NCLocator.getInstance().lookup(IMaterialPubService.class);
				IDeptPubService deptservice =(IDeptPubService)NCLocator.getInstance().lookup(IDeptPubService.class);
				
				PMOAggVO aggpmo=new PMOAggVO();
				
				RdPorductDetailVO rd=objs[0];
				
				PMOHeadVO parent=new PMOHeadVO();
				
				parent.setPk_group(rd.getPk_group());
				parent.setPk_org(rd.getPk_org());
				parent.setPk_org_v(rd.getPk_org_v());
				parent.setCtrantypeid("1001ZZ100000008TKS90");
				parent.setVtrantypecode("55A2-Cxx-01");
				parent.setVersion("1.0");
				parent.setCplanfactoryid(rd.getPk_org());
				parent.setCplanfactoryvid(rd.getPk_org_v());
				parent.setFbillstatus(0);
				parent.setDbilldate(new UFDate(new Date(TimeService.getInstance().getTime())));
				parent.setCreator(InvocationInfoProxy.getInstance().getUserId());
				parent.setCreationtime(new UFDateTime(new Date(TimeService.getInstance().getTime())));
				
				List<PMOItemVO> items=new ArrayList();
				
				for(RdPorductDetailVO obj:objs){
					
					PMOItemVO item=new PMOItemVO();
					
					HashMap<String, String> deptvid=deptservice.getLastVIDSByDeptIDS(new String[]{obj.getPk_dept()});
					Map<String, MaterialVO> martermap=service.queryMaterialBaseInfoByPks(new String[]{obj.getPk_material()},new String[]{"pk_source"});
					String orgVid = OrgUnitPubService.getOrgVid(obj.getPk_org());
					
					item.setFitemstatus(0);
					item.setCmaterialid(obj.getPk_material());
					
					
					if(martermap.containsKey(obj.getPk_material())){
						
						item.setCmaterialvid(martermap.get(obj.getPk_material()).getPk_source());
						
					}else{
						
						item.setCmaterialvid(obj.getPk_material());
						
					}
					
					item.setCunitid(obj.getUnit());
					item.setCastunitid(obj.getQunit());
					item.setNnum(obj.getProdnum());
					item.setNastnum(obj.getProdnastnum());
					item.setVchangerate(obj.getRate());
					item.setNmmnum(obj.getProdnum());
					item.setNmmastnum(obj.getProdnastnum());
					item.setNwrnum(UFDouble.ZERO_DBL);
					item.setNwrastnum(UFDouble.ZERO_DBL);
					item.setCdeptid(obj.getPk_dept());
					item.setCdeptvid(deptvid.get(obj.getPk_dept()));
					item.setCbomversionid(obj.getPk_bomid());
					item.setTplanstarttime(obj.getProddate());
					item.setTplanendtime(obj.getEnddate());
					item.setTrequiredate(obj.getDef1()==null?null:new UFDate(obj.getDef1()));
					item.setBurgent(UFBoolean.FALSE);
					item.setFprintstatus(1);
					item.setCvendorid(obj.getCvendorid());
					item.setCproductorid(obj.getCproductorid());
					item.setCprojectid(obj.getCprojectid());
					item.setCcustomerid(obj.getPk_customer());
					item.setCrequireorg(obj.getPk_org());
					item.setCrequireorgvid(orgVid);
					item.setNzcgastnum(UFDouble.ZERO_DBL);
					item.setNzcgnum(UFDouble.ZERO_DBL);
					item.setNzwwastnum(UFDouble.ZERO_DBL);
					item.setNzwwnum(UFDouble.ZERO_DBL);
					item.setNzdbnum(UFDouble.ZERO_DBL);
					item.setNzdbastnum(UFDouble.ZERO_DBL);
					item.setNinnum(UFDouble.ZERO_DBL);
					item.setNinastnum(UFDouble.ZERO_DBL);
					item.setVdef2(obj.getEndcustomer());
					item.setVdef3(obj.getPk_machine());
					item.setVdef4(obj.getSplitstofftype());
					item.setVdef5(obj.getSilkwide()==null?null:obj.getSilkwide().toString());
					item.setVdef6(obj.getElongation()==null?null:obj.getElongation().toString());
					item.setVdef7(obj.getTension()==null?null:obj.getTension().toString());
					item.setVdef8(obj.getSpacer()==null?null:obj.getSpacer().toString());
					item.setVdef9(obj.getOtherproduction());
					item.setVdef10(obj.getTagline());
					item.setVdef11(obj.getWidth()==null?null:obj.getWidth().toString());
					item.setVdef12(obj.getLength()==null?null:obj.getLength().toString());
					item.setVdef13(obj.getLatitudedensity()==null?null:obj.getLatitudedensity().toString());
					item.setVsrctype("QS01");
					item.setVsrcid(obj.getVsrcplanbid());
					item.setVsrcbid(obj.getPk_rdpd());
					item.setVfree1(obj.getVfree1());
					item.setVfree2(obj.getVfree2());
					item.setVfree3(obj.getVfree3());
					item.setVfree4(obj.getVfree4());
					item.setVfree5(obj.getVfree5());
					item.setVfree6(obj.getVfree6());
					item.setVfree7(obj.getVfree7());
					item.setVfree8(obj.getVfree8());
					item.setVfree9(obj.getVfree9());
					item.setVfree10(obj.getVfree10());
					
					items.add(item);	
				}
				
				aggpmo.setParentVO(parent);
				aggpmo.setChildren(PMOItemVO.class, items.toArray(new PMOItemVO[items.size()]));
				
				return aggpmo;
				
			}catch(Exception e){
				throw new BusinessException(e);
			}
			
			
		}
		
		public IPublicPMOService4PPS getPacPMOServ()
		{
			return (IPublicPMOService4PPS)NCLocator.getInstance().lookup(IPublicPMOService4PPS.class);
		}
		
	}
	
	public void doPMORelease(RdPorductDetailVO[] VOs) throws BusinessException{
		
		if ((!ModuleEnableCheckUtil.isEnable(NCModule.MMPAC)) && (!ModuleEnableCheckUtil.isEnable(NCModule.MMPPAC)) && (!ModuleEnableCheckUtil.isEnable(NCModule.MMDPAC))){
			
			ExceptionUtils.wrappBusinessException(MpmRes.getPACMudlUnableExpt());
			
		}
		
		if (!ModuleEnableCheckUtil.isEnable(NCModule.MMPPAC)) {
			 ExceptionUtils.wrappBusinessException(MpmRes.getPPACMudlEnableExpt());
		}
		
		RdReleasePMOOperator op=new RdReleasePMOOperator();
		
		CommonOperatorTemplate<RdPorductDetailVO> bp = new CommonOperatorTemplate(RdPluginPoint.RDCREATEPMO, op);
		
		addBeforeRule(bp.getAroundProcesser());
		addAfterRule(bp.getAroundProcesser());
		
		bp.operate(VOs);
			
		
	}

	private void addAfterRule(AroundProcesser<RdPorductDetailVO> ard) {
		// TODO 自动生成的方法存根
		
		ard.addAfterRule(new UpdatePushRdStautsRule());
		
	}

	private void addBeforeRule(AroundProcesser<RdPorductDetailVO> ard) {
		// TODO 自动生成的方法存根
		
	}

}
