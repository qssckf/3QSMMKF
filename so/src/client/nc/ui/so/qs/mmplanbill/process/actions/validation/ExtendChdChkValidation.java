package nc.ui.so.qs.mmplanbill.process.actions.validation;

import java.util.Collection;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.MmPlanBillVO;
import nc.vo.so.qs.sc.PlanProduceDetailVO;

public class ExtendChdChkValidation implements nc.bs.uif2.validation.IValidationService{

	public static IMDPersistenceQueryService getMDQueryService(){
		
		return MDPersistenceService.lookupPersistenceQueryService();
		
	}
	
	
	@Override
	public void validate(Object obj) throws ValidationException {
		// TODO 自动生成的方法存根
		
		
		if(obj instanceof MmPlanBillVO){
			
			MmPlanBillVO vo=(MmPlanBillVO) obj;
			
			Collection<PlanProduceDetailVO> col=null;
			
			try {
				
				col=this.getMDQueryService().queryBillOfVOByCond(PlanProduceDetailVO.class, "pk_planbill='"+vo.getPk_planbill()+"' and dr=0 and totalnum>0", false);
			
			
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				ExceptionUtils.wrappException(e);
			}
			
			if(col!=null && col.size()>0){
				
				List<ValidationFailure> fs = new java.util.ArrayList();
				
				fs.add(new ValidationFailure("生产调度单：["+vo.getVbillcode()+"]数据展开数据已经排产，不能进行取消展开操作！"));
				
				throw new ValidationException(fs);
				
				
			}
			
			
			
		}
		
		
		
	}
	
	
	

}
