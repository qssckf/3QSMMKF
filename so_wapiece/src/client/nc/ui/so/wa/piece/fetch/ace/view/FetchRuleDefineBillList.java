package nc.ui.so.wa.piece.fetch.ace.view;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.fetch.IPieceFetchInfoVOMaintain;
import nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable;
import nc.vo.smart.SmartDefVO;

public class FetchRuleDefineBillList extends ShowUpableBatchBillTable {
	
	private IPieceFetchInfoVOMaintain service;
	
	private SQLEditDialog Sqltextdlg;
	
	public SQLEditDialog getSQLDlg(){
		if (this.Sqltextdlg == null) {
			this.Sqltextdlg=new SQLEditDialog(this);
		}
		return this.Sqltextdlg;
	}

	public IPieceFetchInfoVOMaintain getService() {
		
		if(this.service==null){
			this.service=(IPieceFetchInfoVOMaintain)NCLocator.getInstance().lookup(IPieceFetchInfoVOMaintain.class);
		}
		
		return service;
	}

	private Map<String,SmartDefVO> SmartDef=new HashMap();

	public Map<String, SmartDefVO> getSmartDef() {
		return SmartDef;
	}

	public void setSmartDef(Map<String, SmartDefVO> smartDef) {
		SmartDef = smartDef;
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
	}
	
	

}
