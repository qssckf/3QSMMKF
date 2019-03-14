package nc.bs.so.qs.planbill.bp.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.emory.mathcs.backport.java.util.Arrays;
import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.validation.Validator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.qs.sc.planbill.service.IPlanBillSerive;
import nc.ui.uif2.IExceptionHandler;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.planbill.process.BomChVO;
import nc.vo.util.BDUniqueRuleValidate;

public class PlanBillExpendByTMRule extends BatchDocBaseDAO implements IRule{
	
	private IPlanBillSerive PlanService;
	private IExceptionHandler exceptionHandler;
	
	public IPlanBillSerive getPlanService() {
		
		if(this.PlanService==null){
			this.PlanService=NCLocator.getInstance().lookup(IPlanBillSerive.class);
		}
		return PlanService;
	}

	@Override
	public void process(Object[] vos) {
		// TODO 自动生成的方法存根
		
		try{
			if(vos instanceof MmPlanBillVO[]){
				
				
				MmPlanBillVO[] pbvos=(MmPlanBillVO[]) vos;
			
			
				for(MmPlanBillVO vo:pbvos){
				
					Map<String,Double> levelNumMap=new HashMap();
				
					levelNumMap.put("0|"+vo.getVbdef2(), 1.00);

					BomChVO[] boms=(BomChVO[]) this.getPlanService().queryBomChildren(vo.getBomid(),vo.getPk_org());
				
					if(boms!=null && boms.length>0){
					
						Arrays.sort(boms);
					
						for(BomChVO bom:boms){
						
							String level=String.valueOf(bom.getBomlevel()-1);
						
							String fatherMaterial=bom.getPk_materail();
						
							String bomkey=level+"|"+fatherMaterial;
						
							if(levelNumMap.containsKey(bomkey)){
							
								levelNumMap.put(bom.getBomlevel()+"|"+bom.getC_materail(), bom.getItemnum().multiply(levelNumMap.get(bomkey)).toDouble());
							
							}else{
								throw new BusinessException("没有找到上层物料数量基数信息，不能继续展开！");
							}
						
						}
					
						PlanProduceDetailVO[] planb=new PlanProduceDetailVO[boms.length+2];
					
						//预订单产品下单排产信息--start
						
						int j=0;
						
						PlanProduceDetailVO pbb1=new PlanProduceDetailVO();
						
						pbb1.setPk_group(vo.getPk_group());
						pbb1.setPk_org(vo.getPk_org());
						pbb1.setPk_org_v(vo.getPk_org_v());
						pbb1.setPk_planbill(vo.getPk_planbill());
						pbb1.setPk_customer(vo.getPk_customer());
						pbb1.setEndcustomer(vo.getEndcustomer());
						pbb1.setPk_material(vo.getPk_material());
						pbb1.setBomid(vo.getVbdef3());
						pbb1.setVfree1(vo.getVfree1());
						pbb1.setVfree2(vo.getVfree2());
						pbb1.setVfree3(vo.getVfree3());
						pbb1.setSilkwide(vo.getSilkwide());
						pbb1.setSplitstofftype(vo.getCutcloth());
						pbb1.setElongation(vo.getElongation());
						pbb1.setTension(vo.getTensiondd());
						pbb1.setSpacer(vo.getSpacer());
						pbb1.setOtherproduction(vo.getOtherproduction());
						pbb1.setTagline(vo.getTagline());
						pbb1.setLatitudedensity(vo.getLatitudedensity());
						pbb1.setWidth(vo.getWidth());
						pbb1.setLength(vo.getLength());
						pbb1.setUnit(vo.getCunit());
						pbb1.setVchangerate(vo.getRate());
						pbb1.setMunit(vo.getQunit());
						pbb1.setPlannum(vo.getPlannum());
						pbb1.setPlannastnum(vo.getPlannastnum());
						pbb1.setTotalnum(UFDouble.ZERO_DBL);
						pbb1.setTotalnastuum(UFDouble.ZERO_DBL);
						planb[j]=pbb1;
					
						j=+1;
						
						PlanProduceDetailVO pbb=new PlanProduceDetailVO();
					
						pbb.setPk_group(vo.getPk_group());
						pbb.setPk_org(vo.getPk_org());
						pbb.setPk_org_v(vo.getPk_org_v());
						pbb.setPk_planbill(vo.getPk_planbill());
						pbb.setPk_customer(vo.getPk_customer());
						pbb.setEndcustomer(vo.getEndcustomer());
						pbb.setPk_material(vo.getVbdef2());
						pbb.setBomid(vo.getBomid());
						pbb.setVfree1(vo.getVbdef1());
//						pbb.setVfree2(vo.getVfree2());
//						pbb.setVfree3(vo.getVfree3());
						pbb.setUnit(vo.getCunit());
						pbb.setVchangerate(vo.getRate());
						pbb.setMunit(vo.getQunit());
						pbb.setPlannum(vo.getPlannum().multiply(new UFDouble(vo.getVbdef4())));
						pbb.setPlannastnum(vo.getPlannastnum().multiply(new UFDouble(vo.getVbdef4())));
						pbb.setTotalnum(UFDouble.ZERO_DBL);
						pbb.setTotalnastuum(UFDouble.ZERO_DBL);
						planb[j]=pbb;
						j+=1;
						
				
						//预订单产品下单排产信息--end
					
						for(int i=0;i<boms.length;i++){
						
							String chkey=boms[i].getBomlevel()+"|"+boms[i].getC_materail();
						
							PlanProduceDetailVO pbbb=new PlanProduceDetailVO();
							pbbb.setPk_group(vo.getPk_group());
							pbbb.setPk_org(vo.getPk_org());
							pbbb.setPk_org_v(vo.getPk_org_v());
							pbbb.setPk_planbill(vo.getPk_planbill());
							pbbb.setPk_customer(vo.getPk_customer());
							pbbb.setEndcustomer(vo.getEndcustomer());
							pbbb.setPk_material(boms[i].getC_materail());
							pbbb.setBomid(boms[i].getCbomid());
							pbbb.setVfree1(boms[i].getKz());
							pbbb.setVfree2(boms[i].getYs());
							pbbb.setUnit(boms[i].getMunitid());
							pbbb.setVchangerate(boms[i].getRate());
							pbbb.setMunit(boms[i].getQunitid());
						
							if(levelNumMap.containsKey(chkey)){
								pbbb.setPlannum(vo.getPlannum().multiply(levelNumMap.get(chkey)));
								pbbb.setPlannastnum(vo.getPlannastnum().multiply(levelNumMap.get(chkey)));
							}else{
								throw new BusinessException("["+boms[i].getParentmatcode()+"]"+boms[i].getParentmatname()+"产品的子项：["+boms[i].getMatercode()+"]"+boms[i].getMatername()+"没有找到BOM开展基数，无法继续进行开展业务！");
							}
							pbbb.setTotalnum(UFDouble.ZERO_DBL);
							pbbb.setTotalnastuum(UFDouble.ZERO_DBL);
						
							planb[j]=pbbb;
							j+=1;

						}	
					
					
						BomInfoExpend(planb);
					
		
				}else{
					throw new BusinessException("没有找到Bom中的生产物料信息，不能展开！");
				}
	
			}
			
			
			
			}
			
		}catch(BusinessException e){
			ExceptionUtils.wrappException(e);
		}
		
	}
	
	private void BomInfoExpend(PlanProduceDetailVO[] planb) throws BusinessException {
		// TODO 自动生成的方法存根
		
		BatchOperateVO returnVO = new BatchOperateVO();
		
		List<Validator> insertValList = new ArrayList();
		insertValList.add(new BDUniqueRuleValidate());
		
		returnVO.setAddObjs(planb);
		
		Object[] retObj = null;
		if (returnVO != null) {
			retObj = batchSave(returnVO, insertValList, null, null).getAddObjs();
		}
		
	}
	
	public IExceptionHandler getExceptionHandler() {
		if (this.exceptionHandler == null) {
			this.exceptionHandler = new nc.ui.uif2.DefaultExceptionHanler();
		}
		return this.exceptionHandler;
	}

}
