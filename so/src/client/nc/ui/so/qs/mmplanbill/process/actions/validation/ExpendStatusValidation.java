package nc.ui.so.qs.mmplanbill.process.actions.validation;

import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class ExpendStatusValidation implements nc.bs.uif2.validation.IValidationService{

	@Override
	public void validate(Object obj) throws ValidationException {
		// TODO 自动生成的方法存根
		
		if(obj instanceof MmPlanBillVO){
			
			MmPlanBillVO vo=(MmPlanBillVO) obj;
			
			if(UFBoolean.TRUE.equals(vo.getSfexand())){
				
				List<ValidationFailure> fs = new java.util.ArrayList();
				
				fs.add(new ValidationFailure("生产调度单：["+vo.getVbillcode()+"]数据已经展开，不能进行展开操作！"));
				
				throw new ValidationException(fs);
				
			}
			
			
		}
		
		
		
	}

}
