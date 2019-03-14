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
		// TODO �Զ����ɵķ������
		
		List<ValidationFailure> fs = new java.util.ArrayList();
		
		if(this.getModel().getSftm()){
			
			if(obj instanceof MmPlanBillVO){
				
				MmPlanBillVO mp=(MmPlanBillVO)obj;
				
				if(mp.getVbdef1()==null){
					
					fs.add(new ValidationFailure("ͿĤ����Ʒ����ѡ����أ�����ʧ�ܣ�"));
				}
				
				if(mp.getVbdef2()==null){
					fs.add(new ValidationFailure("ͿĤ����Ʒչ�����������Բ���Ϊ�գ�����ʧ�ܣ�"));
				}
				
				if(mp.getVbdef3()==null){
					fs.add(new ValidationFailure("ͿĤ����BOMΪ�գ�����ʧ�ܣ�"));
				}
				
				if(mp.getVbdef4()==null){
					fs.add(new ValidationFailure("ͿĤ��չ��������������������ʧ�ܣ�"));
				}
				
				
			}
			
		}else{
			
			if(obj instanceof MmPlanBillVO){
				
				MmPlanBillVO mp=(MmPlanBillVO)obj;
				
				if(mp.getVbdef1()!=null){

					fs.add(new ValidationFailure("��ͿĤ����Ʒ����ѡ����أ�����ʧ�ܣ�"));
					
				}
				
				if(mp.getVbdef2()!=null){

					fs.add(new ValidationFailure("��ͿĤ����Ʒչ�����������Բ�����ֵ������ʧ�ܣ�"));
					
				}
				
			}
		}
		
		if(fs!=null && fs.size()>0){
			throw new ValidationException(fs);
		}
		
	
		
		
	}

}
