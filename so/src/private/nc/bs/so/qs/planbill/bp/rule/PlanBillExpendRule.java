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
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;
import nc.vo.so.qs.sc.planbill.process.BomChVO;
import nc.vo.util.BDUniqueRuleValidate;

public class PlanBillExpendRule extends BatchDocBaseDAO implements IRule{
	
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
		
		try {
		
			if(vos instanceof MmPlanBillVO[]){
			
			MmPlanBillVO[] pbvos=(MmPlanBillVO[]) vos;
			
			
			for(MmPlanBillVO vo:pbvos){
				
				Map<String,Double> levelNumMap=new HashMap();
				
				levelNumMap.put("0|"+vo.getPk_material(), 1.00);
				
			
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
					
					PlanProduceDetailVO[] planb=new PlanProduceDetailVO[boms.length+1];
					
					//预订单产品下单排产信息--start
					
					PlanProduceDetailVO pbb=new PlanProduceDetailVO();
					
					pbb.setPk_group(vo.getPk_group());
					pbb.setPk_org(vo.getPk_org());
					pbb.setPk_org_v(vo.getPk_org_v());
					pbb.setPk_planbill(vo.getPk_planbill());
					pbb.setPk_customer(vo.getPk_customer());
					pbb.setEndcustomer(vo.getEndcustomer());
					pbb.setPk_material(vo.getPk_material());
					pbb.setBomid(vo.getBomid());
					pbb.setVfree1(vo.getVfree1());
					pbb.setVfree2(vo.getVfree2());
					pbb.setVfree3(vo.getVfree3());
					pbb.setSilkwide(vo.getSilkwide());
					pbb.setSplitstofftype(vo.getCutcloth());
					pbb.setElongation(vo.getElongation());
					pbb.setTension(vo.getTensiondd());
					pbb.setSpacer(vo.getSpacer());
					pbb.setOtherproduction(vo.getOtherproduction());
					pbb.setTagline(vo.getTagline());
					pbb.setLatitudedensity(vo.getLatitudedensity());
					pbb.setWidth(vo.getWidth());
					pbb.setLength(vo.getLength());
					pbb.setUnit(vo.getCunit());
					pbb.setVchangerate(vo.getRate());
					pbb.setMunit(vo.getQunit());
					pbb.setPlannum(vo.getPlannum());
					pbb.setPlannastnum(vo.getPlannastnum());
					pbb.setTotalnum(UFDouble.ZERO_DBL);
					pbb.setTotalnastuum(UFDouble.ZERO_DBL);
					planb[0]=pbb;
				
					//预订单产品下单排产信息--end
					
					for(int i=1;i<=boms.length;i++){
						
						String chkey=boms[i-1].getBomlevel()+"|"+boms[i-1].getC_materail();
						
						PlanProduceDetailVO pbbb=new PlanProduceDetailVO();
						pbbb.setPk_group(vo.getPk_group());
						pbbb.setPk_org(vo.getPk_org());
						pbbb.setPk_org_v(vo.getPk_org_v());
						pbbb.setPk_planbill(vo.getPk_planbill());
						pbbb.setPk_customer(vo.getPk_customer());
						pbbb.setEndcustomer(vo.getEndcustomer());
						pbbb.setPk_material(boms[i-1].getC_materail());
						pbbb.setBomid(boms[i-1].getCbomid());
						pbbb.setVfree1(boms[i-1].getKz());
						pbbb.setVfree2(boms[i-1].getYs());
						pbbb.setUnit(boms[i-1].getMunitid());
						pbbb.setVchangerate(boms[i-1].getRate());
						pbbb.setMunit(boms[i-1].getQunitid());
						
						if(levelNumMap.containsKey(chkey)){
							pbbb.setPlannum(vo.getPlannum().multiply(levelNumMap.get(chkey)));
							pbbb.setPlannastnum(vo.getPlannastnum().multiply(levelNumMap.get(chkey)));
						}else{
							throw new BusinessException("["+boms[i-1].getParentmatcode()+"]"+boms[i-1].getParentmatname()+"产品的子项：["+boms[i-1].getMatercode()+"]"+boms[i-1].getMatername()+"没有找到BOM开展基数，无法继续进行开展业务！");
						}
						pbbb.setTotalnum(UFDouble.ZERO_DBL);
						pbbb.setTotalnastuum(UFDouble.ZERO_DBL);
						
						planb[i]=pbbb;

					}
					
					
					BomInfoExpend(planb);
					
					
					
					
				}else{
					throw new BusinessException("没有找到Bom中的生产物料信息，不能展开！");
				}
	
			}
			
			
			
			}
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
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
