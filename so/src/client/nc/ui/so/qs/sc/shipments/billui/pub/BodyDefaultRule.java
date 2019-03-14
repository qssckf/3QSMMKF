package nc.ui.so.qs.sc.shipments.billui.pub;
/*    */ 
/*    */ import nc.ui.pubapp.AppUiContext;
/*    */ import nc.vo.pub.lang.UFDate;
/*    */ import nc.vo.pub.lang.UFDouble;
/*    */ import nc.vo.pubapp.AppContext;
/*    */ import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
/*    */ 
/*    */ 
/*    */ public class BodyDefaultRule
/*    */ {
/*    */   private IKeyValue keyValue;
/*    */   
/*    */   public BodyDefaultRule(IKeyValue keyValue)
/*    */   {
/* 17 */     this.keyValue = keyValue;
/*    */   }
/*    */   
/*    */   public void setBodyDefaultValue(int[] rows)
/*    */   {String headorigcur = this.keyValue.getHeadStringValue("corigcurrencyid");
/*    */     
/*    */ 
/* 25 */     UFDate billdate = this.keyValue.getHeadUFDateValue("dbilldate");
/*    */     
/* 27 */     UFDouble discountrate = this.keyValue.getHeadUFDoubleValue("ndiscountrate");
/*    */     
/* 29 */     if (null == discountrate) {
/* 30 */       discountrate = SOConstant.ONEHUNDRED;
/*    */     }
/*    */     
/* 33 */     UFDate busidate = AppUiContext.getInstance().getBusiDate();
/* 34 */     busidate = busidate.asLocalEnd();
/*    */     
/* 36 */     for (int row : rows)
/*    */     {
/*    */ 
/* 39 */       this.keyValue.setBodyValue(row, "corigcurrencyid", headorigcur);
/*    */       
/* 41 */       this.keyValue.setBodyValue(row, "dbilldate", billdate);
/*    */       
/*    */ 
/* 44 */       this.keyValue.setBodyValue(row, "ndiscountrate", discountrate);
/*    */       
/* 46 */       this.keyValue.setBodyValue(row, "nitemdiscountrate", SOConstant.ONEHUNDRED);
/*    */       
/*    */ 
/* 49 */       this.keyValue.setBodyValue(row, "dsenddate", busidate);
/* 50 */       this.keyValue.setBodyValue(row, "dreceivedate", busidate);
/*    */       
/*    */ 
/* 53 */       this.keyValue.setBodyValue(row, "nnum", null);
/* 54 */       this.keyValue.setBodyValue(row, "nastnum", null);
/* 55 */       this.keyValue.setBodyValue(row, "nqtunitnum", null);
			}
/*    */   }
/*    */ }
