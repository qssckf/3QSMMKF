package nc.ui.so.qs.mmplanbill.readyplan.ref;

import nc.ui.bd.ref.AbstractRefModel;

public class MachineRefModel extends AbstractRefModel{
	
	public MachineRefModel(){}
	
	public int getDefaultFieldCount(){
		
		return getHiddenFieldCode().length + getFieldCode().length;
		
	}
	
	public String[] getFieldCode() {
		
		return new String[] {
			"b.machcode", 
			"b.machname", 
			"b.machspec"
		};
	}

	public String[] getFieldName()
	{
		return new String[] {
			
			"机器号", 
			"机器名称", 
			"机器规格"
		};
	}
	
	public String[] getHiddenFieldCode()
	{
		return new String[] {
				"b.pk_group",
				"b.pk_org",
				"b.pk_maschine",
				"b.mstatus"
		};
	}
	
	public String getTableName()
	{
		return "(select pk_group,pk_org,pk_maschine,machcode,machname,machspec,mstatus from so_machine where dr=0) b";
	}
	
	
	public String getPkFieldCode() {
		return "b.pk_maschine";
	}
	
	public String getRefCodeField()
	{
		return "b.machcode";
	}
	
	public String getRefNameField()
	{
		return "b.machname";
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
		return "机器列表";
	}
	

}
