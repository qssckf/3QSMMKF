package nc.vo.so.wa.piece.fetch;

import nc.vo.pub.SuperVO;

public class UIAllDataVO extends SuperVO{
	
	private String pk_org;


	private String daccountperiod;
	private String pk_accperiodscheme;
	private PieceFetchRecordVO[] vos;
	private boolean isFetchBefore=false;
	
	public UIAllDataVO(){};
	
	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getDaccountperiod() {
		return daccountperiod;
	}

	public void setDaccountperiod(String daccountperiod) {
		this.daccountperiod = daccountperiod;
	}

	public String getPk_accperiodscheme() {
		return pk_accperiodscheme;
	}

	public void setPk_accperiodscheme(String pk_accperiodscheme) {
		this.pk_accperiodscheme = pk_accperiodscheme;
	}

	public PieceFetchRecordVO[] getVos() {
		return vos;
	}

	public void setVos(PieceFetchRecordVO[] pieceFetchRecordVOs) {
		this.vos = pieceFetchRecordVOs;
	}

	public boolean isFetchBefore() {
		return isFetchBefore;
	}

	public void setFetchBefore(boolean isFetchBefore) {
		this.isFetchBefore = isFetchBefore;
	}
	
	

}
