package nc.bs.so.qs.sc.intoprod.bp.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.org.OrgVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.IntoProdDetailVO;
import nc.vo.so.qs.sc.RdPorductDetailVO;

public class StockOrgChkRule implements IRule<IntoProdDetailVO>{

	@Override
	public void process(IntoProdDetailVO[] vos) {
		// TODO 自动生成的方法存根
		
		List<ValidationFailure> failures = new ArrayList();
		
		for (int i = 0; i < vos.length; i++) {
			
			String pk_org=vos[i].getPk_org();
			
			OrgVO org=OrgUnitPubService.getOrg(pk_org);

			Boolean isstock=this.getOrgType(pk_org, "STOCKORGTYPE00000000");

			
			if(!isstock){
				failures.add(new ValidationFailure("组织："+org.getCode()+"不是库存组织，操作失败！"));
			}
			
			
		}
		
		if (failures.size() > 0) {
			ExceptionUtils.wrappException(new ValidationException(failures));
		}
		
		
	}
	
	
	private boolean getOrgType(String pk_org, String type) {
		if (pk_org == null) {
			return false;
		}
  
		String key = pk_org + type;
		
		boolean istype = OrgUnitPubService.isTypeOf(pk_org, type);
		
		return istype;
	}

}
