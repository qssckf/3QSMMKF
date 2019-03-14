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
		// TODO 自动生成的方法存根
		
		List<SuperVO> newVos=new ArrayList();
		
		for(SuperVO vo:vos.getVos()){
			
			if(vo.getAttributeValue("sfmakebill").toString().equals("N")){
				newVos.add(vo);
			}else{
				vos.getErrLogList().add(new ErrLogElement(vo,"数据已经生成生产订单，不能删除！"));
			}
			
		}
		
		vos.setVos(newVos.toArray(new SuperVO[0]));
		
		return vos;
		
	}

	
	
}
