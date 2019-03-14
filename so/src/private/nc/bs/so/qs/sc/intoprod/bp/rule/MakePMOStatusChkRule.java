package nc.bs.so.qs.sc.intoprod.bp.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.mmpps.mpm.res.MpmRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class MakePMOStatusChkRule implements IRule<IntoProdDetailVO>{

	public MakePMOStatusChkRule(){
		
	}
	
	@Override
	public void process(IntoProdDetailVO[] vos) {
		// TODO �Զ����ɵķ������
		
		List<ValidationFailure> failures = new ArrayList();
		
		for (int i = 0; i < vos.length; i++) {
			
			if("Y".equals(vos[i].getSfmakebill().toString())){
				failures.add(new ValidationFailure("�Ų����ڣ���"+vos[i].getProddate()+"��,�Ų���������"+vos[i].getProdnum()+"�����Ѿ������������������ܼ�����"));
			}
			
		}
		
		if (failures.size() > 0) {
			ExceptionUtils.wrappException(new ValidationException(failures));
		}
	}
	
	

}
