package nc.bs.so.qs.sc.intoprod.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.qs.sc.en.Billstatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class SetBillStatusByPMORule implements IRule<IntoProdDetailVO>{
	
	public SetBillStatusByPMORule(){
		
	}

	@Override
	public void process(IntoProdDetailVO[] vos) {
		// TODO 自动生成的方法存根
		
		for (int i = 0; i < vos.length; i++) {
			
			vos[i].setSfmakebill(UFBoolean.TRUE);

		}
		
	}
	
	

}
