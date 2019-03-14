package nc.bs.so.qs.sc.maschine.bp;


import nc.bs.so.qs.sc.maschine.bp.rule.BillCheckVOConsistence;
import nc.bs.so.qs.sc.maschine.bp.rule.BillSetDisableValueRule;
import nc.bs.so.qs.sc.maschine.bp.rule.BillVOSetValue;
import nc.bs.so.qs.sc.maschine.bp.rule.MaschineFlowBusLockRule;
import nc.bs.so.qs.sc.maschine.plugin.bpplugin.MaschinePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.qs.sc.AggMaschineVO;


public class MaschineDisableBP {
	
	public MaschineDisableBP(){
		
	}
	
	public AggMaschineVO[] disable(AggMaschineVO[] bills,AggMaschineVO[] oldbills) {
		// 调用修改模板
		UpdateBPTemplate<AggMaschineVO> bp = new UpdateBPTemplate<AggMaschineVO>(MaschinePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		
		return bp.update(bills,oldbills);
	}

	private void addAfterRule(CompareAroundProcesser<AggMaschineVO> Processer) {
		// TODO 自动生成的方法存根
		
		Processer.addAfterRule(new nc.bs.pubapp.pub.rule.FillUpdateDataRule());
		
	}

	private void addBeforeRule(CompareAroundProcesser<AggMaschineVO> Processer) {
		// TODO 自动生成的方法存根
		
		
		
		Processer.addBeforeRule(new MaschineFlowBusLockRule());
		
		Processer.addBeforeRule(new BillCheckVOConsistence());
		
		Processer.addBeforeRule(new BillSetDisableValueRule());
		
		BillVOSetValue bs=new BillVOSetValue();
		
		bs.setHeadField(new String[]{"mstatus"});
		
		bs.setHeadValue(new String[]{"2"});
		
		Processer.addBeforeRule(bs);
		
	}
	
	

	

}
