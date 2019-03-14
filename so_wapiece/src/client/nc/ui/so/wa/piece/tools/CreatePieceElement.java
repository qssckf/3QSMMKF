package nc.ui.so.wa.piece.tools;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.formulaedit.FormulaItem;

public class CreatePieceElement {
	
	String[][] svalue={};

	 
	private Map<String,PieceElement> PieceElements=new HashMap();

	public Map<String, PieceElement> getPieceElements() {
		return PieceElements;
	}

	 public CreatePieceElement(){
		 		
				for (int i = 0; i < svalue.length; i++) {
						this.PieceElements.put(svalue[i][1], new PieceElement(svalue[i][1],svalue[i][0],svalue[i][2],svalue[i][3],svalue[i][4],svalue[i][5],svalue[i][6]));
				}
	 }
	 
	 

}
