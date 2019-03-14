package nc.ui.so.wa.piece.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.wa.piece.IPieceWaMaintain;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formula.dialog.AbstractFormulaRealEditorPanel;
import nc.vo.pub.BusinessException;

public class PieceFormulaParse {

	 	private int leftBracket = 0;//左括号个数
		private int rightBracket = 0;//右括号个数
		private int startL = 0;//左括号的位置
	    private int startR = 0;//右括号的位置
	    private double answer = 0;
	    private String leftNumber = "0";
	    private String rightNumber = "0";
		public String Msg = "";
	    private String formula="";
		private int[] sym = new int[4];
		private  Map<String,PieceElement> PieceElements;
		private List<String> fetchrules=new ArrayList<String>();
	    private Vector<String> list = new Vector<String>();//用来存放从字符串解析出来的字符
	    private CreatePieceElement cp=new CreatePieceElement(); //建立计件要素
		private IPieceWaMaintain queryervice;
	    
	    static Vector<Integer> paras = new Vector<Integer>();//用来存放变量参数
	    
	    
	    public  PieceFormulaParse(){
	    	this.PieceElements=cp.getPieceElements();
	    	
	    	try {
				this.fetchrules=this.getQueryService().QueryFetchRule();
			} catch (BusinessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    	
	    }
	    
	    public IPieceWaMaintain getQueryService(){
			if (this.queryervice==null){
				this.queryervice=(IPieceWaMaintain)NCLocator.getInstance().lookup(IPieceWaMaintain.class);
			}
			return this.queryervice;
		}
	    
	    public String getFormula() {
			return formula;
		}
	    
		public void setFormula(String formula) {
		    formula="("+formula+")";
			this.formula = formula;
			
		}
		
		//支持负数，该方法将'-'号作为负号将‘-’号替换成'`'
	    private String replaceSubtration(String vstr) {
			// TODO 自动生成的方法存根
	    	String tmp="";
	    	String result="";
	    	int startS = vstr.indexOf("-");
	        if (startS !=-1) {
	    	        if (startS > 0) {
	    	        	tmp = vstr.substring(startS - 1, startS);
	    	        	if (!"+".equals(tmp) && !"-".equals(tmp) && !"*".equals(tmp) &&!"/".equals(tmp) &&!"(".equals(tmp)){
	    	        		result = result + vstr.substring(0, startS) + "`";
	    	        	}
	    	        	else
	    	        		result = result + vstr.substring(0, startS + 1);
	    	        	}else result = result + vstr.substring(0, startS + 1);
	    	        	
	    	        vstr = vstr.substring(startS + 1);
	    	      }
	    	   
	        while (startS != -1) {
	        		startS = vstr.indexOf("-");
	    	        if (startS > 0) {
	    	        	tmp = vstr.substring(startS - 1, startS);
	    	        	if (!"+".equals(tmp) && !"-".equals(tmp) && !"*".equals(tmp) &&!"/".equals(tmp) && !"(".equals(tmp))
	    	        		result = result + vstr.substring(0, startS) + "`";
	    	        	else
	    	        		result = result + vstr.substring(0, startS + 1);
	    	        	}
	    	        else result = result + vstr.substring(0, startS + 1);
	    	         
	    	        vstr = vstr.substring(startS + 1);
	    	      }
	        
	    	      result+=vstr;
	    	      return result;
		}

		public int getLeftBracket() {
				return leftBracket;
		}
		
	    public void setLeftBracket(int leftBracket) {
				this.leftBracket = leftBracket;
		}
	    
	    public int getRightBracket() {
				return rightBracket;
		}

		public void setRightBracket(int rightBracket) {
				this.rightBracket = rightBracket;
		}
	    
	    //获得左括号数目
	    private int getLeftBracket(String calRule) {
	        leftBracket = 0;
	        startL = calRule.indexOf("(");
	        if (startL != -1) {
	            calRule = calRule.substring(startL + 1, calRule.length());
	        }
	        while (startL != -1) {
	            leftBracket++;
	            startL = calRule.indexOf("(");
	            calRule = calRule.substring(startL + 1, calRule.length());
	        }
	        return leftBracket;
	    }
	    
	    //获得右括号数目
	    private int getRightBracket(String calRule) {
	        rightBracket = 0;
	        startR = calRule.indexOf(")");
	        if (startR != -1) {
	            calRule = calRule.substring(startR + 1, calRule.length());
	        }
	        while (startR != -1) {
	            rightBracket++;
	            startR = calRule.indexOf(")");
	            calRule = calRule.substring(startR + 1, calRule.length());
	        }
	        return rightBracket;
	    }
	    
	    //检查公式是否符合运算逻辑
	    public boolean CheckFormula(AbstractFormulaRealEditorPanel formulaRealEditorPanel){
	    	
	    	String formulaStr = "", calRule = "";
	    	int left=this.getLeftBracket(this.formula);
	    	double value = 0.0;
	    	boolean sf=false,sf1=false;
	    	calRule = this.formula;
	  	    PieceElement lpe = null,rpe;
    	    String element;
    	    String[] applyrange;
    	    String[] type_opera;
    	    String[] operas;
    	    String[] type_position;
    	    String[] positions;
    	    String leftname="";
    	    String righttype;
	    	
	    	for(int i=0;i<left;i++){
	    		int iStart=calRule.lastIndexOf("(") + 1;
	    	    //获得最里层括号里的内容
	    	    formulaStr = calRule.substring(iStart, iStart+calRule.substring(iStart).indexOf(")")).trim();
	    	    symbolParse(formulaStr);
	    	    
	    	    int spd = list.indexOf("/");
	    	    while (spd != -1) {
	                leftNumber = list.get(spd - 1).toString();
	                rightNumber = list.get(spd + 1).toString();
	                list.remove(spd - 1);
	                list.remove(spd - 1);
	                list.remove(spd - 1);
	               
	                if (!isNum(leftNumber)&&!isFloat(leftNumber)){
	                		if(leftNumber.indexOf("{")==0){
	                			element=leftNumber.substring(leftNumber.indexOf("{")+1, leftNumber.indexOf("}"));
	                			lpe=this.PieceElements.get(element);
	                			
	                			if(!this.fetchrules.contains(lpe.getCode())&&lpe.getFetchrulesf()=="Y"){
	                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
	                				return false;
	                			}

	                			if(!isNum(rightNumber)&&!isFloat(rightNumber)){
	                				if(rightNumber.indexOf("{")==0){
	                					element=rightNumber.substring(rightNumber.indexOf("{")+1, rightNumber.indexOf("}"));
	    	                			rpe=this.PieceElements.get(element);
	    	                			
	    	                			if(!this.fetchrules.contains(rpe.getCode())&&rpe.getFetchrulesf()=="Y"){
	    	                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
	    	                				return false;
	    	                			}
	    	                			
	                					applyrange=lpe.getApplyrange();
	                					sf=false;
	    	                			sf1=false;
	    	                			
	    	                			if (applyrange.length==0){
	    	                				sf=true;
	    	                				sf1=true;
	    	                			}else{
			                				for(String ftype:applyrange){
			                					type_opera=ftype.split("-");
			                					if(type_opera.length==2){
			                						if (type_opera[0].equals(rpe.getType())){
			                							sf=true;
			                							operas=type_opera[1].split("~");
			                							for(String opera:operas){
			                								if(opera.equals("/")){
			                									sf1=true;
			                									break;
			                								}
			                							}
			                							break;
			                						}
			                					}
			                				}
	    	                			}
	    	             
		                				if (!sf){
		                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}配合定义公式，请检查计件公式计算逻辑！");
			                				return false;
		                				}else if(!sf1){
		                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}通过除号连接，请检查计件公式计算逻辑！");
			                				return false;
		                				}
		                				
		                				
		                				type_position=lpe.getApplyposition();
		                				sf=false;

		                				if (type_position.length==0){
		                					sf=true;
		                				}else{
		                					for(String position:type_position){
		    	                				positions=position.split("-");
		    	                				if(positions.length==2){
		    	                					if(positions[0].equals(rpe.getType())){
		    	                						if(positions[1].equals("left")){
		    	                							sf=true;
		    	                							break;
		    	                						}
		    	                					}
		    	                				}
		    	                			}
		                				}
		                				
		                				if (!sf){
		                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}不能放在计件要素{"+rpe.getName()+"}左边，请检查计件公式计算逻辑！");
			                				return false;
		                				}
	                				}
	                				
	                				
	                			}
	                	
	                		}
	                }
	                     
	            
	               value= 0;
	                list.add(spd - 1, String.valueOf(value));
	                spd = list.indexOf("/");
	            }
	    	    
	    	    int spm = list.indexOf("*");
	    	    while (spm != -1) {
	                leftNumber = list.get(spm - 1).toString();
	                rightNumber = list.get(spm + 1).toString();
	                list.remove(spm - 1);
	                list.remove(spm - 1);
	                list.remove(spm - 1);
	                
	                if (!isNum(leftNumber)&&!isFloat(leftNumber)){
                		if(leftNumber.indexOf("{")==0){
                			element=leftNumber.substring(leftNumber.indexOf("{")+1, leftNumber.indexOf("}"));
                			lpe=this.PieceElements.get(element);
                			
                			if(!this.fetchrules.contains(lpe.getCode())&&lpe.getFetchrulesf()=="Y"){
                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
                				return false;
                			}

                			if(!isNum(rightNumber)&&!isFloat(rightNumber)){
                				if(rightNumber.indexOf("{")==0){
                					element=rightNumber.substring(rightNumber.indexOf("{")+1, rightNumber.indexOf("}"));
    	                			rpe=this.PieceElements.get(element);
    	                			
    	                			if(!this.fetchrules.contains(rpe.getCode())&&rpe.getFetchrulesf()=="Y"){
    	                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
    	                				return false;
    	                			}
    	                			
                					applyrange=lpe.getApplyrange();
                					sf=false;
    	                			sf1=false;
    	                			
    	                			if (applyrange.length==0){
    	                				sf=true;
    	                				sf1=true;
    	                			}else{
		                				for(String ftype:applyrange){
		                					type_opera=ftype.split("-");
		                					if(type_opera.length==2){
		                						if (type_opera[0].equals(rpe.getType())){
		                							sf=true;
		                							operas=type_opera[1].split("~");
		                							for(String opera:operas){
		                								if(opera.equals("*")){
		                									sf1=true;
		                									break;
		                								}
		                							}
		                							break;
		                						}
		                					}
		                				}
    	                			}
    	             
	                				if (!sf){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}配合定义公式，请检查计件公式计算逻辑！");
		                				return false;
	                				}else if(!sf1){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}通过乘号连接，请检查计件公式计算逻辑！");
		                				return false;
	                				}
	                				
	                				
	                				type_position=lpe.getApplyposition();
	                				sf=false;

	                				if (type_position.length==0){
	                					sf=true;
	                				}else{
	                					for(String position:type_position){
	    	                				positions=position.split("-");
	    	                				if(positions.length==2){
	    	                					if(positions[0].equals(rpe.getType())){
	    	                						if(positions[1].equals("left")){
	    	                							sf=true;
	    	                							break;
	    	                						}
	    	                					}
	    	                				}
	    	                			}
	                				}
	                				
	                				if (!sf){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}不能放在计件要素{"+rpe.getName()+"}左边，请检查计件公式计算逻辑！");
		                				return false;
	                				}
                				}
                				
                				
                			}
                	
                		}
                }
	                
	            
	                value = 0;
	                list.add(spm - 1, String.valueOf(value));
	                spm = list.indexOf("*");
	            }
	    	    

	    	    int sps = list.indexOf("-");
	    	    while (sps != -1) {
	                leftNumber = list.get(sps - 1).toString();
	                rightNumber = list.get(sps + 1).toString();
	                list.remove(sps - 1);
	                list.remove(sps - 1);
	                list.remove(sps - 1);
	                
	                if (!isNum(leftNumber)&&!isFloat(leftNumber)){
                		if(leftNumber.indexOf("{")==0){
                			element=leftNumber.substring(leftNumber.indexOf("{")+1, leftNumber.indexOf("}"));
                			lpe=this.PieceElements.get(element);
                			
                			if(!this.fetchrules.contains(lpe.getCode())&&lpe.getFetchrulesf()=="Y"){
                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
                				return false;
                			}

                			if(!isNum(rightNumber)&&!isFloat(rightNumber)){
                				if(rightNumber.indexOf("{")==0){
                					element=rightNumber.substring(rightNumber.indexOf("{")+1, rightNumber.indexOf("}"));
    	                			rpe=this.PieceElements.get(element);
    	                			
    	                			if(!this.fetchrules.contains(rpe.getCode())&&rpe.getFetchrulesf()=="Y"){
    	                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
    	                				return false;
    	                			}
    	                			
                					applyrange=lpe.getApplyrange();
                					sf=false;
    	                			sf1=false;
    	                			
    	                			if (applyrange.length==0){
    	                				sf=true;
    	                				sf1=true;
    	                			}else{
		                				for(String ftype:applyrange){
		                					type_opera=ftype.split("-");
		                					if(type_opera.length==2){
		                						if (type_opera[0].equals(rpe.getType())){
		                							sf=true;
		                							operas=type_opera[1].split("~");
		                							for(String opera:operas){
		                								if(opera.equals("-")){
		                									sf1=true;
		                									break;
		                								}
		                							}
		                							break;
		                						}
		                					}
		                				}
    	                			}
    	             
	                				if (!sf){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}配合定义公式，请检查计件公式计算逻辑！");
		                				return false;
	                				}else if(!sf1){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}通过减号连接，请检查计件公式计算逻辑！");
		                				return false;
	                				}
	                				
	                				
	                				type_position=lpe.getApplyposition();
	                				sf=false;

	                				if (type_position.length==0){
	                					sf=true;
	                				}else{
	                					for(String position:type_position){
	    	                				positions=position.split("-");
	    	                				if(positions.length==2){
	    	                					if(positions[0].equals(rpe.getType())){
	    	                						if(positions[1].equals("left")){
	    	                							sf=true;
	    	                							break;
	    	                						}
	    	                					}
	    	                				}
	    	                			}
	                				}
	                				
	                				if (!sf){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}不能放在计件要素{"+rpe.getName()+"}左边，请检查计件公式计算逻辑！");
		                				return false;
	                				}
                				}
                				
                				
                			}
                	
                		}
                }	                
	            
	               value = 0;
	                list.add(sps - 1, String.valueOf(value));
	                sps = list.indexOf("-");
	            }
	    	    
	    	    int spa = list.indexOf("+");
	    	    while (spa != -1) {
	                leftNumber = list.get(spa - 1).toString();
	                rightNumber = list.get(spa + 1).toString();
	                list.remove(spa - 1);
	                list.remove(spa - 1);
	                list.remove(spa - 1);
	            
	                if (!isNum(leftNumber)&&!isFloat(leftNumber)){
                		if(leftNumber.indexOf("{")==0){
                			element=leftNumber.substring(leftNumber.indexOf("{")+1, leftNumber.indexOf("}"));
                			lpe=this.PieceElements.get(element);
                			
                			if(!this.fetchrules.contains(lpe.getCode())&&lpe.getFetchrulesf()=="Y"){
                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
                				return false;
                			}

                			if(!isNum(rightNumber)&&!isFloat(rightNumber)){
                				if(rightNumber.indexOf("{")==0){
                					element=rightNumber.substring(rightNumber.indexOf("{")+1, rightNumber.indexOf("}"));
    	                			rpe=this.PieceElements.get(element);
    	                			
    	                			if(!this.fetchrules.contains(rpe.getCode())&&rpe.getFetchrulesf()=="Y"){
    	                				MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}没有定义取数规则，不能参与公式定义!");
    	                				return false;
    	                			}
    	                			
                					applyrange=lpe.getApplyrange();
                					sf=false;
    	                			sf1=false;
    	                			
    	                			if (applyrange.length==0){
    	                				sf=true;
    	                				sf1=true;
    	                			}else{
		                				for(String ftype:applyrange){
		                					type_opera=ftype.split("-");
		                					if(type_opera.length==2){
		                						if (type_opera[0].equals(rpe.getType())){
		                							sf=true;
		                							operas=type_opera[1].split("~");
		                							for(String opera:operas){
		                								if(opera.equals("+")){
		                									sf1=true;
		                									break;
		                								}
		                							}
		                							break;
		                						}
		                					}
		                				}
    	                			}
    	             
	                				if (!sf){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}配合定义公式，请检查计件公式计算逻辑！");
		                				return false;
	                				}else if(!sf1){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+rpe.getName()+ "}不能与计件要素{"+lpe.getName()+"}通过加号连接，请检查计件公式计算逻辑！");
		                				return false;
	                				}
	                				
	                				
	                				type_position=lpe.getApplyposition();
	                				sf=false;

	                				if (type_position.length==0){
	                					sf=true;
	                				}else{
	                					for(String position:type_position){
	    	                				positions=position.split("-");
	    	                				if(positions.length==2){
	    	                					if(positions[0].equals(rpe.getType())){
	    	                						if(positions[1].equals("left")){
	    	                							sf=true;
	    	                							break;
	    	                						}
	    	                					}
	    	                				}
	    	                			}
	                				}
	                				
	                				if (!sf){
	                					MessageDialog.showHintDlg(formulaRealEditorPanel, "警告","计件要素{"+lpe.getName()+ "}不能放在计件要素{"+rpe.getName()+"}左边，请检查计件公式计算逻辑！");
		                				return false;
	                				}
                				}
                				
                				
                			}
                	
                		}
                }
	                
	                value = 0;
	                list.add(spa - 1, String.valueOf(value));
	                spa = list.indexOf("*");
	            }
	    	    
	    	    iStart=calRule.lastIndexOf("(");
	    	    int iEnd=calRule.substring(iStart).indexOf(")")+1;
	    	    calRule = calRule.substring(0,iStart).trim() +value +calRule.substring(iStart+iEnd, calRule.length()).trim();
	    	}
	    	
	    	return true;
	    }

		private void symbolParse(String formulaStr) {
			// TODO 自动生成的方法存根
			
	        list.clear();
	        int count = 0;
	        compareMin(formulaStr);
	        for (int i = 0; i < 4; i++) {
	            while (sym[i] != -1) {
	            	String insStr = formulaStr.substring(0, sym[i]).trim();
	            
	                list.add(insStr);
	                insStr = formulaStr.substring(sym[i], sym[i] + 1).trim();
	                list.add(insStr);
	                
	                formulaStr = formulaStr.substring(sym[i] + 1, formulaStr.length()).trim();
	                compareMin(formulaStr);
	                count++;
	            }
	        }
	        list.add(formulaStr);
	        }

		//根据运算符的位置从到远进行排序
		private void compareMin(String str) {

			  int sps = str.indexOf("-");//减法subtration
		        sym[0] = sps;
		        int spa = str.indexOf("+");//加法addition
		        sym[1] = spa;
		        int spd = str.indexOf("/");//除法division
		        sym[2] = spd;
		        int spm = str.indexOf("*");//乘法multiplication
		        sym[3] = spm;
		        for (int i = 1; i < sym.length; i++) {
		            for (int j = 0; j < sym.length - i; j++)
		                if (sym[j] > sym[j + 1]) {
		                    int temp = sym[j];
		                    sym[j] = sym[j + 1];
		                    sym[j + 1] = temp;
		                }
		        }
			
		}
		
		   private boolean isNum(String baseFormula)
		   {
			   	Pattern pattern = Pattern.compile("^-?\\d+$");
			   	Matcher matcher = pattern.matcher(baseFormula);
			   	if (matcher.find()) {
			   		return true;
		     }
			   	return false;
		   }
		   
		   private boolean isFloat(String baseFormula)
		   {
			   Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?");
			   Matcher matcher = pattern.matcher(baseFormula);
			   if (matcher.find()) {
				   return true;
		     }
			   return false;
		   }
}
