package nc.ui.so.qs.intoprod.input.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.itf.so.qs.sc.base.IFilterRule;
import nc.so.qs.sc.bs.bd.service.ErrLogElement;
import nc.so.qs.sc.bs.bd.service.ValueObjWithErrLog;
import nc.vo.pub.SuperVO;

public class IpDeleteFilterRule implements IFilterRule{

	@Override
	public ValueObjWithErrLog process(ValueObjWithErrLog vos) {
		// TODO �Զ����ɵķ������
		
		List<SuperVO> newVos=new ArrayList();
		
		for(SuperVO vo:vos.getVos()){
			
			if(vo.getAttributeValue("sfmakebill").toString().equals("N")){
				newVos.add(vo);
			}else{
				vos.getErrLogList().add(new ErrLogElement(vo,"�����Ѿ�������������������ɾ����"));
			}
			
		}
		
		vos.setVos(newVos.toArray(new SuperVO[0]));
		
		return vos;
		
	}

	
	
}
