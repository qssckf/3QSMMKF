package nc.bs.so.fq.plugin;
/*    */ 
/*    */ import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
/*    */ import nc.vo.pubapp.res.NCModule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ServicePlugInPoint
/*    */   implements IPluginPoint
/*    */ {
/* 16 */   rewriteFQNarrnumFor30("nc.itf.so.IShipmentsInfoMaintain.rewriteFQNarrnumFor30"),
		   rewrite38NarrnumForFQ("nc.itf.so.IShipmentsInfoMaintain.rewrite30NumForWithdraw");
/*    */   
/*    */   private String point;
/*    */   
/*    */   private ServicePlugInPoint(String point)
/*    */   {
/* 23 */     this.point = point;
/*    */   }
/*    */   
/*    */   public String getComponent()
/*    */   {
/* 28 */     return "FQ01";
/*    */   }
/*    */   
/*    */   public String getModule()
/*    */   {
/* 33 */     return NCModule.SO.getName();
/*    */   }
/*    */   
/*    */   public String getPoint()
/*    */   {
/* 38 */     return this.point;
/*    */   }
/*    */ }

/* Location:           E:\CODE1\NC633GOLD20180407\NC633GOLD20180407\modules\so\META-INF\lib\so_sellingrequisition.jar
 * Qualified Name:     nc.bs.so.m38.plugin.ServicePlugInPoint
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.0.1
 */