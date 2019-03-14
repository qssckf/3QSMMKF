package nc.bs.so.qs.sc.maschine.bp.rule;

import nc.impl.pubapp.pattern.rule.IRule;
//import nc.vo.so.xlx.tranflow.AggTranFlowVO;
//import nc.vo.so.xlx.tranflow.TranFlowBVO;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MachineMater_Mater;

public class BillVOSetValue implements IRule<AggMaschineVO>{
	
	private String[] headField={};
	private String[] headValue={};
	private String[] BodyField={};
	private String[] BodyValue={};
	

	public String[] getHeadField() {
		return headField;
	}


	public void setHeadField(String[] headField) {
		this.headField = headField;
	}


	public String[] getHeadValue() {
		return headValue;
	}


	public void setHeadValue(String[] headValue) {
		this.headValue = headValue;
	}


	public String[] getBodyField() {
		return BodyField;
	}


	public void setBodyField(String[] bodyField) {
		BodyField = bodyField;
	}


	public String[] getBodyValue() {
		return BodyValue;
	}


	public void setBodyValue(String[] bodyValue) {
		BodyValue = bodyValue;
	}
	
	
	public BillVOSetValue(){
		
	}

	@Override
	public void process(AggMaschineVO[] objs) {
		// TODO 自动生成的方法存根
		
		if(this.headField.length>0){
			
			for(AggMaschineVO obj:objs){
				
				for(int i=0;i<headField.length;i++){
					
					obj.getParent().setAttributeValue(headField[i], headValue[i]);
					
				}
				
				obj.getParentVO().setStatus(1);
			}
		}
		
		if(this.BodyField.length>0){
			
			
			for(AggMaschineVO obj:objs){
				
				MachineMater_Mater[] bvos=(MachineMater_Mater[])obj.getChildren(MachineMater_Mater.class);
				
				for(MachineMater_Mater bvo:bvos){
					
					for(int j=0;j<this.BodyField.length;j++){
						
						bvo.setAttributeValue(BodyField[j], BodyValue[j]);
						
					}	
					bvo.setStatus(1);	
				}	
			}
		}				
	}
}
