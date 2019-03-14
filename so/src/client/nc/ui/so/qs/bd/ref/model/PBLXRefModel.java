package nc.ui.so.qs.bd.ref.model;

import nc.ui.bd.ref.AbstractRefModel;

public class PBLXRefModel extends AbstractRefModel {
	
	public PBLXRefModel(){}
	
	public int getDefaultFieldCount()
	{
		return getHiddenFieldCode().length + getFieldCode().length;
	}
	
	public String[] getFieldCode() {
		
		return new String[] {
				"b.code", 
				"b.name"};
		}

	public String[] getFieldName()
	{
		return new String[] {
				"编码", 
				"名称"};
		}
		
	public String[] getHiddenFieldCode()
	{
		return new String[] {
				"b.pk_defdoc",
				"b.pk_defdoclist",
				"b.pk_group",
				"b.pk_org"
		};
	}

	public String getTableName()
	{
		return "(select pk_group,pk_org,pk_defdoc,pk_defdoclist,code,name from bd_defdoc where pk_defdoclist='1001E4100000008RYDR4' and dr=0) b";
	}
	
	public String getPkFieldCode() {
		return "b.pk_defdoc";
	}
	
	public String getRefCodeField()
	{
		return "b.code";
	}
	
	public String getRefNameField()
	{
		return "b.name";
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
		return "剖布类型";
	}
	

}
