package nc.ui.so.wa.piece.fetch.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class SmartDefRefModel extends AbstractRefModel {
	
	public int getDefaultFieldCount()
	{
		return getHiddenFieldCode().length + getFieldCode().length;
	}
	
	public String[] getFieldCode() {
		return new String[] {
				"ref_b.defcode", 
				"ref_b.defname"};
		}

	public String[] getFieldName()
	{
		return new String[] {
				"语义模型编码", 
				"语义模型名称"};
		}
		
	public String[] getHiddenFieldCode()
	{
		return new String[] {
		"ref_b.pk_def"};
	}

	public String getTableName()
	{
		String sql="(select pk_def,defcode,defname from bi_smart_def where dr = 0 and pk_dir='1001AF1000000008Z7GL') ref_b";
		return sql.toString();
	}
	
	public String getPkFieldCode() {
		return "ref_b.pk_def";
	}
	
	public String getRefCodeField()
	{
		return "ref_b.defcode";
	}
	
	public String getRefNameField()
	{
		return "ref_b.defname";
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
		return "语义模型选择";
	}

}
