/*    */ package nc.bs.so.m38.plugin;
/*    */ 
/*    */ import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
/*    */ import nc.vo.pubapp.res.NCModule;
/*    */ 
/*    */ public enum ActionPlugInPoint
/*    */   implements IPluginPoint
/*    */ {
/*  9 */   ApproveAction("nc.impl.so.m38.action.ApprovePreOrderAction"), 
/*    */   
/*    */ 
/* 12 */   DeleteAction("nc.impl.so.m38.action.DeletePreOrderAction"), 

		   SendAction("nc.ui.so.m38.billui.action.PreOrderSendApproveAction"),
		   
		   UnSendAction("nc.ui.so.m38.billui.action.PreOrderUnSendApproveAction"),
/*    */   
/*    */ 
/* 15 */   InsertAction("nc.impl.so.m38.action.InsertPreOrderAction"), 
/*    */   
/*    */ 
/* 18 */   UnApproveAction("nc.impl.so.m38.action.UnApprovePreOrderAction"), 
/*    */   
/*    */ 
/* 21 */   UpdateAction("nc.impl.so.m38.action.UpdatePreOrderAction");
/*    */   
/*    */   private String point;
/*    */   
/*    */   private ActionPlugInPoint(String point)
/*    */   {
/* 27 */     this.point = point;
/*    */   }
/*    */   
/*    */   public String getComponent()
/*    */   {
/* 32 */     return "m38";
/*    */   }
/*    */   
/*    */   public String getModule()
/*    */   {
/* 37 */     return NCModule.SO.getName();
/*    */   }
/*    */   
/*    */   public String getPoint()
/*    */   {
/* 42 */     return this.point;
/*    */   }
/*    */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_sellingrequisition.jar
 * Qualified Name:     nc.bs.so.m38.plugin.ActionPlugInPoint
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */