package nc.bs.so.qs.readyplan.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.qs.sc.en.Billstatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class SetBillStatusByPMORule implements IRule<RdPorductDetailVO>{
	
	public SetBillStatusByPMORule(){
		
	}

	@Override
	public void process(RdPorductDetailVO[] vos) {
		// TODO 自动生成的方法存根
		
		for (int i = 0; i < vos.length; i++) {
			
			vos[i].setBillstatus(3);
			vos[i].setSfmakebill(UFBoolean.TRUE);

		}
		
	}
	
	

}
