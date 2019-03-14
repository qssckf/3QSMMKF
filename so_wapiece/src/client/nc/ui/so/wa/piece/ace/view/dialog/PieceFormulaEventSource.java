 package nc.ui.so.wa.piece.ace.view.dialog;

 import nc.ui.pub.formula.dialog.FormulaEventSource;
 import nc.ui.pub.formula.dialog.FormulaEventSource.FormulaEventType;

 public class PieceFormulaEventSource extends FormulaEventSource
{
	 private Object eventSource;
	 private FormulaEventSource.FormulaEventType eventType;
	 private String originalString;
	 private String newString;
	 private Object contextObject;
	 private String newValueString;
	 
	 public PieceFormulaEventSource() {}
   
   public Object getEventSource()
   {
	   return this.eventSource;
   }
   

   public void setEventSource(Object eventSource)
   {
	   this.eventSource = eventSource;
   }
   
 
   public FormulaEventSource.FormulaEventType getEventType()
   {
	   return this.eventType;
   }
   

   public void setEventType(FormulaEventSource.FormulaEventType eventType)
   {
	   this.eventType = eventType;
   }

   
   public String getOriginalString()
   {
	   return this.originalString;
   }
   
 
   public void setOriginalString(String originalString)
   {
	   this.originalString = originalString;
   }

 
   public String getNewString()
   {
	   return this.newString;
   }
   
 

   public void setNewString(String newString)
   {
	   this.newString = newString;
   }
   
 
 
   public Object getContextObject()
   {
	   return this.contextObject;
   }
   

   public void setContextObject(Object contextObject)
   {
	   this.contextObject = contextObject;
   }
   

   public String getNewValueString()
   {
	   return this.newValueString;
   }
   

   public void setNewValueString(String newValueString)
   {
	   this.newValueString = newValueString;
   }
 }