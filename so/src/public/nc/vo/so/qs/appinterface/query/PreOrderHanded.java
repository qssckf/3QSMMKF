package nc.vo.so.qs.appinterface.query;

import nc.vo.so.qs.appinterface.util.BillQuery;
import nc.vo.so.qs.appinterface.util.IBillType;

public class PreOrderHanded implements IBillType{

	@Override
	public String getCategory() {
		// TODO 自动生成的方法存根
		return "isPrehanded";
	}

	@Override
	public String getCode() {
		// TODO 自动生成的方法存根
		return "Prehanded";
	}

	@Override
	public String getName() {
		// TODO 自动生成的方法存根
		return "待处理";
	}

	@Override
	public BillQuery createNewTaskQuery() {
		// TODO 自动生成的方法存根
		return new PreOrderQuery();
	}
	
	

}
