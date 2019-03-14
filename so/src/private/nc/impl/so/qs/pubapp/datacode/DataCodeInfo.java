package nc.impl.so.qs.pubapp.datacode;

public class DataCodeInfo {
	
	private String cbilltype;
	
	private String codeItem;
	
	private String groupItem;
	private String orgItem;
	

	public DataCodeInfo(String cbilltype,String codeItem,String group,String org){
		
		this.cbilltype=cbilltype;
		
		this.codeItem=codeItem;
		
		this.groupItem=group;
		
		this.orgItem=org;
		
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
