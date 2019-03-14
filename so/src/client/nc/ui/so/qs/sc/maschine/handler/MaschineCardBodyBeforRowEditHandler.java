package nc.ui.so.qs.sc.maschine.handler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
///*     */ import nc.ui.pd.pd0404.utils.PDWkCardPanelValueUtils;
/*     */ import nc.ui.pub.beans.MessageDialog;
/*     */ import nc.ui.pub.beans.UITable;
/*     */ import nc.ui.pub.bill.BillCardPanel;
/*     */ import nc.ui.pub.bill.BillData;
/*     */ import nc.ui.pub.bill.BillModel;
/*     */ import nc.ui.pub.bill.BillScrollPane;
/*     */ import nc.ui.pubapp.uif2app.event.IAppEventHandler;
/*     */ import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
/*     */ import nc.ui.pubapp.uif2app.event.card.CardBodyRowEditEvent;
		import nc.ui.so.qs.sc.maschine.utils.MaschineCardPanelValueUtils;
/*     */ import nc.util.mmf.framework.base.MMValueCheck;
		import nc.vo.pd.pd0404.message.MMPDLangConstPd0404;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineCardBodyBeforRowEditHandler
/*     */   implements IAppEventHandler<CardBodyRowEditEvent>
/*     */ {
/*     */   public MaschineCardBodyBeforRowEditHandler() {}
/*     */   
/*     */   public void handleAppEvent(CardBodyRowEditEvent e)
/*     */   {
/*  31 */     if (e.getRowEditType().equals(BodyRowEditType.DELLINE)) {
/*  32 */       processDelLine(e);
/*     */     }
/*     */     else {
/*  35 */       e.setReturnValue(Boolean.valueOf(true));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private void processDelLine(CardBodyRowEditEvent e)
/*     */   {
/*  42 */     String tabCode = e.getBillCardPanel().getBodyPanel().getTableCode();
///*  43 */     if ("prodinv_items".equals(tabCode))
///*     */     {
///*  45 */       repltimeMaterialHandler(e);
///*     */     }
///*     */     else {
/*  48 */       e.setReturnValue(Boolean.valueOf(true));
///*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
///*     */   private void repltimeMaterialHandler(CardBodyRowEditEvent e)
///*     */   {
///*  59 */     int[] rows = e.getBillCardPanel().getBillTable().getSelectedRows();
///*  60 */     if (0 == rows.length) {
///*  61 */       e.setReturnValue(Boolean.valueOf(true));
///*  62 */       return;
///*     */     }
///*     */     
///*  65 */     List<String> cwk_prodinvidList = getDeletedCwk_prodinvids(e, rows);
///*  66 */     if (MMValueCheck.isEmpty(cwk_prodinvidList)) {
///*  67 */       e.setReturnValue(Boolean.valueOf(true));
///*  68 */       return;
///*     */     }
///*     */     
///*  71 */     List<Integer> rowNumList = new ArrayList();
///*  72 */     for (String cwk_prodinvid : (String[])cwk_prodinvidList.toArray(new String[cwk_prodinvidList.size()])) {
///*  73 */       rowNumList = findRepltimeMaterialRow(e, cwk_prodinvid, rowNumList);
///*     */     }
///*     */     
///*  76 */     Integer[] rowNum = (Integer[])rowNumList.toArray(new Integer[rowNumList.size()]);
///*  77 */     Arrays.sort(rowNum);
///*  78 */     if (rowNum.length > 0)
///*     */     {
///*  80 */       delConfirmdlg(e, rowNum);
///*     */       
///*  82 */       if (e.getReturnValue().booleanValue()) {
///*  83 */         removeRepltimeRow(e, rowNum);
///*     */       }
///*     */     }
///*     */     else {
///*  87 */       e.setReturnValue(Boolean.valueOf(true));
///*     */     }
///*     */   }
///*     */   
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   private List<String> getDeletedCwk_prodinvids(CardBodyRowEditEvent e, int[] rows)
///*     */   {
///* 100 */     BillModel billModel = e.getBillCardPanel().getBillData().getBillModel("replace_items");
///*     */     
///* 102 */     MaschineCardPanelValueUtils utils = new MaschineCardPanelValueUtils(e.getBillCardPanel());
///* 103 */     List<String> cwk_prodinvidList = new ArrayList();
///* 104 */     for (int row : rows) {
///* 105 */       Object cwk_prodinvid = utils.getBodyValue(row, "cwk_prodinvid", e.getBillCardPanel().getBodyPanel().getTableCode());
///*     */       
///*     */ 
///* 108 */       if ((billModel == null) || (billModel.getRowCount() == 0) || (MMValueCheck.isEmpty(cwk_prodinvid))) {
///* 109 */         e.setReturnValue(Boolean.valueOf(true));
///* 110 */         return null;
///*     */       }
///* 112 */       cwk_prodinvidList.add(cwk_prodinvid.toString());
///*     */     }
///* 114 */     return cwk_prodinvidList;
///*     */   }
///*     */   
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */   private void removeRepltimeRow(CardBodyRowEditEvent e, Integer[] rowNum)
///*     */   {
///* 124 */     for (int i = 0; i < rowNum.length; i++)
///*     */     {
///* 126 */       e.getBillCardPanel().getBillModel("replace_items").delLine(new int[] { rowNum[i].intValue() - i });
///*     */     }
///*     */   }
///*     */   
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
/*     */   private void delConfirmdlg(CardBodyRowEditEvent e, Integer[] rowNum)
/*     */   {
/* 139 */     String hint = constructHint(rowNum);
/* 140 */     int dlg = MessageDialog.showOkCancelDlg(e.getBillCardPanel().getParent(), MMPDLangConstPd0404.getUIBTN_DELETE(), hint);
/*     */     
/*     */ 
/*     */ 
/* 144 */     if (dlg == 1) {
/* 145 */       e.setReturnValue(Boolean.valueOf(true));
/*     */     }
/* 147 */     else if (dlg == 2) {
/* 148 */       e.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */     else {
/* 151 */       e.setReturnValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
///*     */   
///*     */ 
///*     */ 
///*     */ 
///*     */ 
///*     */ 
/*     */   private String constructHint(Integer[] rowNum)
/*     */   {
/* 162 */     StringBuffer hint = new StringBuffer();
/* 163 */     for (int i = 0; i < rowNum.length; i++) {
/* 164 */       if (i == rowNum.length - 1) {
/* 165 */         hint.append(rowNum[i].intValue() + 1);
/*     */       }
/*     */       else {
/* 168 */         hint.append(rowNum[i].intValue() + 1);
/* 169 */         hint.append(",");
/*     */       }
/*     */     }
/*     */     
/* 173 */     return MMPDLangConstPd0404.getTHISROWSTRING(hint.toString());
/*     */   }
///*     */   
///*     */ 
///*     */ 
///*     */ 
///*     */   private List<Integer> findRepltimeMaterialRow(CardBodyRowEditEvent e, Object cwk_prodinvid, List<Integer> rowNumList)
///*     */   {
///* 181 */     BillModel billModel = e.getBillCardPanel().getBillData().getBillModel("replace_items");
///*     */     
///* 183 */     for (int i = 0; i < billModel.getRowCount(); i++) {
///* 184 */       if (((cwk_prodinvid.equals(billModel.getValueAt(i, "caftermatrelat"))) || (cwk_prodinvid.equals(billModel.getValueAt(i, "cprematrelat")))) && (!rowNumList.contains(Integer.valueOf(i))))
///*     */       {
///* 186 */         rowNumList.add(Integer.valueOf(i));
///*     */       }
///*     */     }
///* 189 */     return rowNumList;
///*     */   }
/*     */ }
