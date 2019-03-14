package nc.ui.so.qs.sc.maschine.view;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nc.ui.pub.beans.UITable.SortItem;
/*     */ import nc.ui.pub.bill.BillListPanel;
/*     */ import nc.ui.pub.bill.BillModel;
/*     */ import nc.ui.pub.bill.BillScrollPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineListViewSort
/*     */ {
/*     */   private BillListPanel list;
/*  20 */   private List<SortItem> headSortItems = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  25 */   private Map<String, List<SortItem>> bodySortItems = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MaschineListViewSort(BillListPanel list)
/*     */   {
/*  34 */     this.list = list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MaschineListViewSort addHeadSortItem(String key, boolean asc)
/*     */   {
/*  48 */     int index = this.list.getHeadBillModel().getBodyColByKey(key);
/*  49 */     SortItem item = new SortItem(index, asc);
/*  50 */     this.headSortItems.add(item);
/*  51 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MaschineListViewSort addHeadSortItem(String key)
/*     */   {
/*  62 */     return addHeadSortItem(key, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MaschineListViewSort addBodySortItem(String key, boolean asc, String tableCode)
/*     */   {
/*  78 */     int index = this.list.getBodyBillModel(tableCode).getBodyColByKey(key);
/*  79 */     SortItem item = new SortItem(index, asc);
/*     */     
/*     */ 
/*  82 */     if (!this.bodySortItems.containsKey(tableCode)) {
/*  83 */       this.bodySortItems.put(tableCode, new ArrayList());
/*     */     }
/*     */     
/*  86 */     ((List)this.bodySortItems.get(tableCode)).add(item);
/*  87 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MaschineListViewSort addBodySortItem(String key, boolean asc)
/*     */   {
/* 100 */     return addBodySortItem(key, asc, this.list.getChildListPanel().getTableCode());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MaschineListViewSort addBodySortItem(String key)
/*     */   {
/* 111 */     return addBodySortItem(key, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sort()
/*     */   {
/* 119 */     this.list.getHeadBillModel().sortByColumns(this.headSortItems);
/*     */     
/*     */ 
/* 122 */     for (String tableCode : this.bodySortItems.keySet()) {
/* 123 */       List<SortItem> sortItems = (List)this.bodySortItems.get(tableCode);
/* 124 */       this.list.getBodyBillModel(tableCode).sortByColumns(sortItems);
/*     */     }
/*     */   }
/*     */ }
