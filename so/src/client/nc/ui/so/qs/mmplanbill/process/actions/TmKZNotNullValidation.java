package nc.ui.so.qs.mmplanbill.process.actions;

import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.ui.so.qs.mmplanbill.process.model.PlanBillAppModel;
import nc.vo.so.qs.sc.MmPlanBillVO;

public class TmKZNotNullValidation implements nc.bs.uif2.validation.IValidationService{

	private PlanBillAppModel model;
	
	public PlanBillAppModel getModel() {
		return model;
	}

	public void setModel(PlanBillAppModel model) {
		this.model = model;
	}

	@Override
	public void validate(Object obj) throws ValidationException {
		// TODO 自动生成的方法存根
		
		List<ValidationFailure> fs = new java.util.ArrayList();
		
		if(this.getModel().getSftm()){
			
			if(obj instanceof MmPlanBillVO){
				
				MmPlanBillVO mp=(MmPlanBillVO)obj;
				
				if(mp.getVbdef1()==null){
					
					fs.add(new ValidationFailure("涂膜布产品必须选择克重，保存失败！"));
				}
				
				if(mp.getVbdef2()==null){
					fs.add(new ValidationFailure("涂膜布产品展开用物料属性不能为空，保存失败！"));
				}
				
				if(mp.getVbdef3()==null){
					fs.add(new ValidationFailure("涂膜布用BOM为空，保存失败！"));
				}
				
				if(mp.getVbdef4()==null){
					fs.add(new ValidationFailure("涂膜布展开物料子项数量，保存失败！"));
				}
				
				
			}
			
		}else{
			
			if(obj instanceof MmPlanBillVO){
				
				MmPlanBillVO mp=(MmPlanBillVO)obj;
				
				if(mp.getVbdef1()!=null){

					fs.add(new ValidationFailure("非涂膜布产品不能选择克重，保存失败！"));
					
				}
				
				if(mp.getVbdef2()!=null){

					fs.add(new ValidationFailure("非涂膜布产品展开用物料属性不能有值，保存失败！"));
					
				}
				
			}
		}
		
		if(fs!=null && fs.size()>0){
			throw new ValidationException(fs);
		}
		
	
		
		
	}

}
