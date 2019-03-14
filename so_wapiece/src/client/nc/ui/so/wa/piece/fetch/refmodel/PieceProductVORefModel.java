package nc.ui.so.wa.piece.fetch.refmodel;

import nc.ui.bd.ref.AbstractRefModel;

public class PieceProductVORefModel extends AbstractRefModel {

	public PieceProductVORefModel() {}

	public int getDefaultFieldCount()
	{
		return getHiddenFieldCode().length + getFieldCode().length;
	}
	
	public String[] getFieldCode() {
		return new String[] {
				"ref_b.code", 
				"ref_b.name"
				};
		}

	public String[] getFieldName()
	{
		return new String[] {
				"编码", 
				"计件产品名称"
		};
	}
		
	public String[] getHiddenFieldCode()
	{
		return new String[] {
		"ref_b.id"
		};
	}

	public String getTableName()
	{
		String sql="(select pk_ppid id,code,name from so_pd where dr = 0) ref_b";
		return sql.toString();
	}
	
	public String getPkFieldCode() {
		return "ref_b.id";
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
		return "计件产品选择";
	}
	
}