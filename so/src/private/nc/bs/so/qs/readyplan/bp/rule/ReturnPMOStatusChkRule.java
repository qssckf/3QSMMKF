package nc.bs.so.qs.readyplan.bp.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mmpps.mpm.res.MpmRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class ReturnPMOStatusChkRule implements IRule<RdPorductDetailVO>{

	public ReturnPMOStatusChkRule(){
		
	}
	
	@Override
	public void process(RdPorductDetailVO[] vos) {
		// TODO 自动生成的方法存根
		
		List<ValidationFailure> failures = new ArrayList();
		
		for (int i = 0; i < vos.length; i++) {
			
			if(vos[i].getBillstatus()!=2){
				failures.add(new ValidationFailure("生产日期：【"+vos[i].getProddate()+"】,生产数量：【"+vos[i].getProdnum()+"】，不为待处理状态，不能继续！"));
			}
			
			if(vos[i].getTintoprodnum()!=null && vos[i].getTintoprodnum().toDouble()>0){
				failures.add(new ValidationFailure("生产调度单明细记录已经有投产信息，不能继续！"));
			}
			
			
		}
		
		if (failures.size() > 0) {
			ExceptionUtils.wrappException(new ValidationException(failures));
		}
	}
	
	

}
