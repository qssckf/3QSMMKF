package nc.bs.so.qs.readyplan.bp.rule;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.qs.sc.en.Billstatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class SetRdBillStatusByPMORule implements IRule<RdPorductDetailVO>{
	
	public SetRdBillStatusByPMORule(){
		
	}

	@Override
	public void process(RdPorductDetailVO[] vos) {
		// TODO 自动生成的方法存根
		
		for (int i = 0; i < vos.length; i++) {
			
			vos[i].setBillstatus(2);
//			vos[i].setProcesser(InvocationInfoProxy.getInstance().getUserId());
		}
		
	}
	
	

}
