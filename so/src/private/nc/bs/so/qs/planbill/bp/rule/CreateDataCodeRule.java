package nc.bs.so.qs.planbill.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.qs.pubapp.datacode.DataCodeInfo;
import nc.impl.so.qs.pubapp.datacode.DataCodeUtils;
import nc.vo.pub.SuperVO;

public class CreateDataCodeRule implements IRule{
	
	private String cbilltype;
	private String codeItem;
	private String groupItem;
	private String orgItem;
	


	public CreateDataCodeRule() {}
	
	public CreateDataCodeRule(String cbilltype, String codeItem, String groupItem, String orgItem)
	{
		this.cbilltype = cbilltype;
		this.codeItem = codeItem;
		this.groupItem = groupItem;
		this.orgItem = orgItem;
	}

	@Override
	public void process(Object[] vos) {
		// TODO 自动生成的方法存根
		
		DataCodeInfo info=new DataCodeInfo(this.cbilltype,this.codeItem,this.groupItem,this.orgItem);
		
		DataCodeUtils util=new DataCodeUtils(info);
		
		util.createBillCode((SuperVO[])vos);
		

	}
	
	public String getCbilltype() {
		return cbilltype;
	}

	public void setCbilltype(String cbilltype) {
		this.cbilltype = cbilltype;
	}

	public String getCodeItem() {
		return codeItem;
	}

	public void setCodeItem(String codeItem) {
		this.codeItem = codeItem;
	}

	public String getGroupItem() {
		return groupItem;
	}

	public void setGroupItem(String groupItem) {
		this.groupItem = groupItem;
	}

	public String getOrgItem() {
		return orgItem;
	}

	public void setOrgItem(String orgItem) {
		this.orgItem = orgItem;
	}

}
