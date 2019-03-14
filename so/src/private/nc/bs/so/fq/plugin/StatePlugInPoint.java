package nc.bs.so.fq.plugin;
 
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

public enum StatePlugInPoint implements IPluginPoint
{

	RowCloseState("nc.bs.so.fq.state.row.RowCloseState"), 

	RowOpenState("nc.bs.so.fq.state.row.RowOpenState"),
	
	Row38CloseState("nc.bs.so.fq.state.row.Row38CloseState"), 

	Row38OpenState("nc.bs.so.fq.state.row.Row38OpenState");
	
	
  
	private String point;
  
	private StatePlugInPoint(String point){
		this.point = point;
	}
  
	public String getComponent(){
		return "FQ01";
	}
  
	public String getModule(){
		return NCModule.SO.getName();
	}
  
	public String getPoint(){
		return this.point;
	}
}

