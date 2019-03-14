package nc.ui.so.qs.intoprod.model;

import nc.ui.pubapp.uif2app.model.BillManageModel;

public class ManageAppModel extends BillManageModel{
	
	private IntoProdAppModel cmodel;

	public IntoProdAppModel getCmodel() {
		return cmodel;
	}

	public void setCmodel(IntoProdAppModel cmodel) {
		this.cmodel = cmodel;
	}

	@Override
	public void initModel(Object data) {
		// TODO 自动生成的方法存根
		if(data==null){
			super.initModel(data);
			this.getCmodel().initModel(null);
			
		}else{
			super.initModel(data);
		}
		
		
		
	}
	
	

}
