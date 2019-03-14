package nc.ui.so.wa.piece.fetch.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class DeptRefModel extends AbstractRefModel {
	
	public DeptRefModel() {
		// TODO 自动生成的构造函数存根
	}
	
	public int getDefaultFieldCount()
	{
		return getHiddenFieldCode().length + getFieldCode().length;
	}
	
	public String[] getFieldCode() {
		return new String[] {
				"ref_b.code", 
				"ref_b.name"};
		}

	public String[] getFieldName()
	{
		return new String[] {
				"车间编码", 
				"车间名称"};
		}
		
	public String[] getHiddenFieldCode()
	{
		return new String[] {
		"ref_b.pk_org",		
		"ref_b.def1",
		"ref_b.pk_dept"};
	}

	public String getTableName()
	{
		String sql="(select PK_DEPT,PK_ORG,CODE,NAME,DEF1 from org_dept WHERE dr=0) ref_b";
		return sql;
	}
	
	public String getPkFieldCode() {
		return "ref_b.pk_dept";
	}
	
	public String getRefCodeField()
	{
		return "ref_b.code";
	}
	
	public String getRefNameField()
	{
		return "ref_b.name";
	}
	
	public String getWherePart()
	{
		String wherePart = super.getWherePart();
		
		return wherePart;
	}

	public void setWherePart(String strWhereSQL)
	{
		super.setWherePart(strWhereSQL);
	}

	public String getRefTitle()
	{
		return "车间选择";
	}


}
