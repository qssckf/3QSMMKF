/*      */ package nc.ui.uif2.editor;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import javax.swing.ListSelectionModel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.event.ListSelectionEvent;
/*      */ import javax.swing.event.ListSelectionListener;
/*      */ import nc.bs.framework.common.UserExit;
/*      */ import nc.bs.logging.Logger;
/*      */ import nc.ui.ml.NCLangRes;
/*      */ import nc.ui.pub.beans.UITable;
/*      */ import nc.ui.pub.bill.BillEditEvent;
/*      */ import nc.ui.pub.bill.BillEditListener;
/*      */ import nc.ui.pub.bill.BillItem;
/*      */ import nc.ui.pub.bill.BillItemUISet;
/*      */ import nc.ui.pub.bill.BillListData;
/*      */ import nc.ui.pub.bill.BillListPanel;
/*      */ import nc.ui.pub.bill.BillModel;
/*      */ import nc.ui.pub.bill.BillMouseEnent;
/*      */ import nc.ui.pub.bill.BillScrollPane;
/*      */ import nc.ui.pub.bill.BillTableMouseListener;
/*      */ import nc.ui.pub.bill.IBillListData;
/*      */ import nc.ui.pub.bill.IBillModelHeadRowStateChangeEventListener;
/*      */ import nc.ui.pub.bill.IBillModelRowStateChangeEventListener;
/*      */ import nc.ui.pub.bill.RowStateChangeEvent;
/*      */ import nc.ui.uif2.AppEvent;
/*      */ import nc.ui.uif2.AppEventListener;
/*      */ import nc.ui.uif2.UIStateChangeEvent;
/*      */ import nc.ui.uif2.components.BorderLayoutPanel;
/*      */ import nc.ui.uif2.components.pagination.PaginationBar;
/*      */ import nc.ui.uif2.model.BillManageModel;
/*      */ import nc.ui.uif2.model.IMultiRowSelectModel;
/*      */ import nc.ui.uif2.model.IRowSelectModel;
/*      */ import nc.ui.uif2.model.RowOperationInfo;
/*      */ import nc.ui.uif2.model.RowSelectionOperationInfo;
/*      */ import nc.vo.jcom.lang.StringUtil;
/*      */ import nc.vo.pub.AggregatedValueObject;
/*      */ import nc.vo.pub.CircularlyAccessibleValueObject;
/*      */ import nc.vo.pub.ExtendedAggregatedValueObject;
/*      */ import nc.vo.pub.bill.BillTempletVO;
/*      */ import nc.vo.trade.pub.IExAggVO;
/*      */ import nc.vo.uif2.AppStatusRegistery;
/*      */ import nc.vo.uif2.AppStatusRegisteryCallback;
/*      */ import nc.vo.uif2.LoginContext;
/*      */ import org.apache.commons.lang.ArrayUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BillListView
/*      */   extends BorderLayoutPanel
/*      */   implements AppEventListener, BillEditListener, BillTableMouseListener, IBillListPanelView
/*      */ {
/*      */   private static final long serialVersionUID = 689522100812512504L;
/*      */   private PaginationBar paginationBar;
/*      */   protected BillListPanel billListPanel;
/*      */   private BillManageModel model;
/*      */   private String nodekey;
/*      */   private String pos;
/*      */   private List<String> tabCode;
/*      */   private TemplateContainer templateContainer;
/*      */   private boolean multiSelectionEnable;
/*      */   private int multiSelectionMode;
/*   95 */   public static int MOUSE_CLICK_SELECTION = 0;
/*   96 */   public static int CHECKBOX_SELECTION = 1;
/*      */   private IBillListPanelValueSetter billListPanelValueSetter;
/*      */   private IBillListData userdefitemListPreparator;
/*      */   private BillListSelectionListener selectionListener;
/*      */   private IBillModelRowStateChangeEventListener multiModeListener;
/*      */   protected boolean handlingModelEvent;
/*      */   private boolean handlingControlEvent;
/*      */   private String beanId;
/*      */   private boolean showTotalLine;
/*      */   private List<String> showTotalLineTabcodes;
/*      */   private boolean showListPanelBorder;
/*      */   
/*      */   public BillListView()
/*      */   {
/*   68 */     this.paginationBar = null;
/*      */     
/*      */ 
/*   71 */     this.billListPanel = null;
/*      */     
/*      */ 
/*   74 */     this.model = null;
/*      */     
/*      */ 
/*   77 */     this.nodekey = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   84 */     this.pos = null;
/*      */     
/*   86 */     this.tabCode = new ArrayList();
/*      */     
/*      */ 
/*   89 */     this.templateContainer = null;
/*      */     
/*   91 */     this.multiSelectionEnable = true;
/*      */     
/*   93 */     this.multiSelectionMode = MOUSE_CLICK_SELECTION;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  102 */     this.selectionListener = null;
/*      */     
/*  104 */     this.multiModeListener = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  113 */     this.handlingModelEvent = false;
/*      */     
/*  115 */     this.handlingControlEvent = false;
/*      */     
/*  117 */     this.beanId = null;
/*      */     
/*      */ 
/*  120 */     this.showTotalLine = false;
/*      */     
/*      */ 
/*  123 */     this.showTotalLineTabcodes = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  128 */     this.showListPanelBorder = true;
/*      */   }
/*      */   
/*      */   public static abstract interface IBillListPanelValueSetter
/*      */   {
/*      */     public abstract void setHeaderDatas(BillListPanel paramBillListPanel, Object[] paramArrayOfObject);
/*      */     
/*      */     public abstract void setHeaderRowData(BillListPanel paramBillListPanel, Object paramObject, int paramInt);
/*      */     
/*      */     public abstract void setBodyData(BillListPanel paramBillListPanel, Object paramObject);
/*      */   }
/*      */   
/*      */   public static abstract interface IBillListPanelValueSetterExt extends BillListView.IBillListPanelValueSetter
/*      */   {
/*      */     public abstract void setHeaderRowsData(BillListPanel paramBillListPanel, Object[] paramArrayOfObject, int[] paramArrayOfInt);
/*      */   }
/*      */   
/*      */   public static class MDBillListPanelValueSetter implements BillListView.IBillListPanelValueSetterExt {
/*      */     public MDBillListPanelValueSetter() {}
/*      */     
/*      */     public void setBodyData(BillListPanel listPanel, Object selectedData) {
/*  149 */       String[] tabcodes = listPanel.getBillListData().getBodyTableCodes();
/*  150 */       if ((tabcodes == null) || (tabcodes.length == 0))
/*  151 */         return;
/*  152 */       listPanel.getBillListData().setBodyValueObjectByMetaData(selectedData);
/*      */       
/*  154 */       listPanel.getBillListData().getBodyBillModel().execLoadFormula();
/*      */     }
/*      */     
/*      */     public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas)
/*      */     {
/*  159 */       listPanel.getBillListData().setHeaderValueObjectByMetaData(allDatas);
/*      */       
/*  161 */       listPanel.getBillListData().getHeadBillModel().execLoadFormula();
/*      */     }
/*      */     
/*      */ 
/*      */     public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row)
/*      */     {
/*  167 */       listPanel.getBillListData().setHeaderValueRowObjectByMetaData(rowData, row);
/*      */       
/*  169 */       listPanel.getBillListData().getHeadBillModel().execLoadFormulaByRow(row);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public void setHeaderRowsData(BillListPanel listPanel, Object[] datas, int[] indexs)
/*      */     {
/*  176 */       if ((ArrayUtils.isEmpty(indexs)) || (ArrayUtils.isEmpty(datas)) || (datas.length != indexs.length))
/*  177 */         return;
/*  178 */       listPanel.getBillListData().setHeaderValueRowsObjectByMetaData(datas, indexs);
/*  179 */       listPanel.getBillListData().getHeadBillModel().execLoadFormula();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class VOBillListPanelValueSetter implements BillListView.IBillListPanelValueSetter {
/*      */     public VOBillListPanelValueSetter() {}
/*      */     
/*      */     public void setBodyData(BillListPanel listPanel, Object selectedData) {
/*  187 */       if ((selectedData != null) && (!(selectedData instanceof AggregatedValueObject)))
/*      */       {
/*  189 */         Logger.debug(NCLangRes.getInstance().getStrByID("uif2", "BillListView-000000"));
/*      */       }
/*  191 */       else if (selectedData == null) {
/*  192 */         listPanel.setBodyValueVO(null);
/*      */       }
/*      */       else {
/*  195 */         String[] tabcodes = listPanel.getBillListData().getBodyTableCodes();
/*  196 */         if ((tabcodes == null) || (tabcodes.length == 0)) {
/*  197 */           return;
/*      */         }
/*      */         
/*  200 */         setBodyData4VO(listPanel, selectedData, tabcodes);
/*  201 */         processLoadRelation(listPanel, tabcodes);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas)
/*      */     {
/*  208 */       if ((allDatas == null) || (allDatas.length == 0)) {
/*  209 */         listPanel.setHeaderValueVO(null);
/*  210 */       } else if (!AggregatedValueObject.class.isAssignableFrom(allDatas[0].getClass())) {
/*  211 */         Logger.debug(NCLangRes.getInstance().getStrByID("uif2", "BillListView-000001"));
/*      */       }
/*      */       else {
/*  214 */         CircularlyAccessibleValueObject[] vos = new CircularlyAccessibleValueObject[allDatas.length];
/*  215 */         for (int i = 0; i < allDatas.length; i++) {
/*  216 */           vos[i] = ((AggregatedValueObject)allDatas[i]).getParentVO();
/*      */         }
/*      */         
/*  219 */         listPanel.setHeaderValueVO(vos);
/*  220 */         BillModel headModel = listPanel.getHeadBillModel();
/*  221 */         if (headModel != null)
/*      */         {
/*      */ 
/*  224 */           if (listPanel.getBillListData().isMeataDataTemplate()) {
/*  225 */             headModel.loadLoadRelationItemValue();
/*      */           }
/*  227 */           headModel.execLoadFormula();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row)
/*      */     {
/*  234 */       if (rowData == null) {
/*  235 */         Logger.debug(NCLangRes.getInstance().getStrByID("uif2", "BillListView-000002", null, new String[] { String.valueOf(row) }));
/*  236 */       } else if (!(rowData instanceof AggregatedValueObject)) {
/*  237 */         Logger.debug(NCLangRes.getInstance().getStrByID("uif2", "BillListView-000003"));
/*      */       }
/*      */       else
/*      */       {
/*  241 */         BillModel headModel = listPanel.getBillListData().getHeadBillModel();
/*  242 */         if (headModel != null)
/*      */         {
/*  244 */           headModel.setBodyRowVO(((AggregatedValueObject)rowData).getParentVO(), row);
/*      */           
/*  246 */           if (listPanel.getBillListData().isMeataDataTemplate())
/*      */           {
/*  248 */             BillItem[] items = headModel.getBodyItems();
/*  249 */             if ((items != null) && (items.length > 0))
/*      */             {
/*  251 */               for (BillItem item : items) {
/*  252 */                 headModel.loadLoadRelationItemValue(row, item.getKey());
/*      */               }
/*      */             }
/*      */           }
/*  256 */           headModel.execLoadFormulaByRow(row);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     protected void setBodyData4VO(BillListPanel listPanel, Object data, String[] tabcodes)
/*      */     {
/*  263 */       if ((data instanceof ExtendedAggregatedValueObject))
/*      */       {
/*  265 */         ExtendedAggregatedValueObject extAggvo = (ExtendedAggregatedValueObject)data;
/*  266 */         for (String tabcode : tabcodes)
/*      */         {
/*  268 */           listPanel.setBodyValueVO(tabcode, extAggvo.getTableVO(tabcode));
/*      */         }
/*  270 */       } else if ((data instanceof IExAggVO))
/*      */       {
/*  272 */         IExAggVO extAggvo = (IExAggVO)data;
/*  273 */         for (String tabcode : tabcodes)
/*      */         {
/*  275 */           listPanel.setBodyValueVO(tabcode, extAggvo.getTableVO(tabcode));
/*      */         }
/*  277 */       } else if ((data instanceof AggregatedValueObject))
/*      */       {
/*  279 */         AggregatedValueObject avo = (AggregatedValueObject)data;
/*  280 */         listPanel.setBodyValueVO(avo.getChildrenVO());
/*      */       }
/*      */     }
/*      */     
/*      */     protected void processLoadRelation(BillListPanel listPanel, String[] tabcodes)
/*      */     {
/*  286 */       for (String tabcode : tabcodes)
/*      */       {
/*  288 */         BillModel bodyModel = listPanel.getBodyBillModel(tabcode);
/*  289 */         if (bodyModel != null)
/*      */         {
/*  291 */           if (listPanel.getBillListData().isMeataDataTemplate())
/*  292 */             bodyModel.loadLoadRelationItemValue();
/*  293 */           bodyModel.execLoadFormula();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private class BillListSelectionListener implements ListSelectionListener
/*      */   {
/*      */     private BillListSelectionListener() {}
/*      */     
/*      */     public void valueChanged(ListSelectionEvent e) {
/*  304 */       if (BillListView.this.handlingModelEvent) { return;
/*      */       }
/*  306 */       if (!e.getValueIsAdjusting())
/*      */       {
/*  308 */         if ((needSyncMultiSelection()) && ((BillListView.this.model instanceof IMultiRowSelectModel)))
/*      */         {
/*  310 */           int[] rows = BillListView.this.getBillListPanel().getHeadTable().getSelectedRows();
/*  311 */           BillListView.this.getModel().setSelectedOperaRows(rows);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private boolean needSyncMultiSelection() {
/*  317 */       return (!BillListView.this.isMultiSelectionEnable()) || (BillListView.this.getMultiSelectionMode() != BillListView.CHECKBOX_SELECTION);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class BillListMultiModelListener
/*      */     implements IBillModelRowStateChangeEventListener
/*      */   {
/*      */     private BillListMultiModelListener() {}
/*      */     
/*      */ 
/*      */     public void valueChanged(RowStateChangeEvent event)
/*      */     {
/*  331 */       if (BillListView.this.handlingModelEvent) { return;
/*      */       }
/*  333 */       BillListView.this.handlingControlEvent = true;
/*      */       
/*      */       try
/*      */       {
/*  337 */         if ((!BillListView.this.isMultiSelectionEnable()) || (BillListView.this.getMultiSelectionMode() != BillListView.CHECKBOX_SELECTION)) {
/*      */           return;
/*      */         }
/*  340 */         synModelMultiSelect(event);
/*  341 */         synBodyMultiSelect(event);
/*      */       }
/*      */       finally
/*      */       {
/*  345 */         BillListView.this.handlingControlEvent = false;
/*      */       }
/*      */     }
/*      */     
/*      */     private void synModelMultiSelect(RowStateChangeEvent event)
/*      */     {
/*  351 */       int startIndex = event.getRow();
/*  352 */       int endIndex = event.getEndRow();
/*  353 */       if ((BillListView.this.model instanceof IMultiRowSelectModel))
/*      */       {
/*  355 */         int[] indexs = new int[endIndex - startIndex + 1];
/*  356 */         for (int i = startIndex; i <= endIndex; i++)
/*      */         {
/*  358 */           indexs[(i - startIndex)] = i;
/*      */         }
/*  360 */         if (event.isSelectState())
/*      */         {
/*  362 */           BillListView.this.model.addSelectedOperaRow(indexs);
/*      */         }
/*      */         else {
/*  365 */           BillListView.this.model.removeSelectedOperaRow(indexs);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void synBodyMultiSelect(final RowStateChangeEvent event)
/*      */     {
/*  372 */       SwingUtilities.invokeLater(new Runnable()
/*      */       {
/*      */         public void run() {
/*  375 */           BillListView.this.multiSelectBodyRow(event);
/*      */         }
/*      */       });
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private class BillListHeadSynListener
/*      */     implements IBillModelHeadRowStateChangeEventListener
/*      */   {
/*      */     private BillListHeadSynListener() {}
/*      */     
/*      */ 
/*      */ 
/*      */     public void valueChanged(RowStateChangeEvent event)
/*      */     {
/*  392 */       if (BillListView.this.handlingModelEvent) { return;
/*      */       }
/*  394 */       BillListView.this.handlingControlEvent = true;
/*      */       try
/*      */       {
/*  397 */         if ((!BillListView.this.isMultiSelectionEnable()) || (BillListView.this.getMultiSelectionMode() != BillListView.CHECKBOX_SELECTION)) {
/*      */           return;
/*      */         }
/*  400 */         synModelMultiSelect(event);
/*      */       }
/*      */       finally
/*      */       {
/*  404 */         BillListView.this.handlingControlEvent = false;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     private void synModelMultiSelect(RowStateChangeEvent event)
/*      */     {
/*  411 */       int index = event.getRow();
/*  412 */       if (event.getOldRowStaus() == event.getRowStaus())
/*  413 */         return;
/*  414 */       if ((BillListView.this.model instanceof IMultiRowSelectModel))
/*      */       {
/*  416 */         if (event.isSelectState())
/*      */         {
/*  418 */           BillListView.this.model.addSelectedOperaRow(new int[] { index });
/*      */         }
/*      */         else {
/*  421 */           BillListView.this.model.removeSelectedOperaRow(new int[] { index });
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void initUI()
/*      */   {
/*  429 */     super.initUI();
/*      */     
/*  431 */     setBorder(null);
/*      */     
/*      */ 
/*      */ 
/*  435 */     createListPanel();
/*      */     
/*  437 */     setBillListData();
/*      */     
/*  439 */     setBillListPanelProp();
/*      */     
/*  441 */     setBillListPanelShowTotalProp();
/*      */     
/*  443 */     setListMultiProp();
/*      */     
/*  445 */     this.billListPanel.addMouseListener(this);
/*      */     
/*  447 */     add(this.billListPanel);
/*      */     
/*      */ 
/*      */ 
/*  451 */     if (this.paginationBar != null) {
/*  452 */       this.billListPanel.addHeadNavigatePanel(this.paginationBar);
/*      */     }
/*      */     
/*  455 */     setValueSetter();
/*      */     
/*  457 */     registeCallback();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void setValueSetter()
/*      */   {
/*  463 */     if (this.billListPanelValueSetter == null) {
/*  464 */       if (this.billListPanel.getBillListData().isMeataDataTemplate()) {
/*  465 */         this.billListPanelValueSetter = new MDBillListPanelValueSetter();
/*      */       } else {
/*  467 */         this.billListPanelValueSetter = new VOBillListPanelValueSetter();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void setListMultiProp()
/*      */   {
/*  474 */     if (isMultiSelectionEnable())
/*      */     {
/*  476 */       if (this.multiSelectionMode == MOUSE_CLICK_SELECTION)
/*      */       {
/*  478 */         this.selectionListener = new BillListSelectionListener();
/*  479 */         this.billListPanel.getHeadTable().getSelectionModel().addListSelectionListener(this.selectionListener);
/*  480 */         this.billListPanel.getHeadTable().setSelectionMode(2);
/*      */       } else {
/*  482 */         this.multiModeListener = new BillListMultiModelListener();
/*      */         
/*  484 */         this.billListPanel.setMultiSelect(true);
/*      */         
/*  486 */         this.billListPanel.getHeadBillModel().addRowStateChangeEventListener(this.multiModeListener);
/*      */         
/*  488 */         this.billListPanel.getHeadBillModel().addHeadRowStateChangeEventListener(new BillListHeadSynListener());
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/*  494 */       this.selectionListener = new BillListSelectionListener();
/*  495 */       this.billListPanel.getHeadTable().getSelectionModel().addListSelectionListener(this.selectionListener);
/*  496 */       this.billListPanel.getHeadTable().setSelectionMode(0);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void setBillListPanelProp()
/*      */   {
/*  502 */     this.billListPanel.setEnabled(false);
/*  503 */     if (!isShowListPanelBorder())
/*  504 */       this.billListPanel.setBorder(null);
/*  505 */     this.billListPanel.getHeadTable().setBorder(null);
/*      */     
/*      */ 
/*  508 */     this.billListPanel.getParentListPanel().setAutoAddLine(false);
/*      */     
/*      */ 
/*  511 */     this.billListPanel.getHeadBillModel().addSortRelaObjectListener(this.model);
/*  512 */     this.billListPanel.getHeadBillModel().addSortListener(this.model);
/*      */     
/*      */ 
/*  515 */     this.billListPanel.addEditListener(this);
/*      */     
/*  517 */     this.billListPanel.getHeadTable().setCellSelectionEnabled(true);
/*      */   }
/*      */   
/*      */ 
/*      */   protected void setBillListPanelShowTotalProp()
/*      */   {
/*  523 */     getBillListPanel().getParentListPanel().setTotalRowShow(isShowTotalLine());
/*      */     
/*  525 */     List<String> tabcodes = getShowTotalLineTabcodes();
/*  526 */     if (tabcodes == null)
/*  527 */       tabcodes = new ArrayList();
/*  528 */     String[] allTabcodes = getBillListPanel().getBillListData().getBodyTableCodes();
/*  529 */     if ((allTabcodes == null) || (allTabcodes.length == 0)) return;
/*  530 */     for (String code : allTabcodes)
/*      */     {
/*  532 */       if (null != getBillListPanel().getBodyScrollPane(code))
/*      */       {
/*      */ 
/*  535 */         if (tabcodes.contains(code)) {
/*  536 */           getBillListPanel().getBodyScrollPane(code).setTotalRowShow(true);
/*      */         } else
/*  538 */           getBillListPanel().getBodyScrollPane(code).setTotalRowShow(false);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void setBillListData() {
/*  544 */     BillTempletVO template = null;
/*  545 */     if (this.templateContainer == null)
/*      */     {
/*  547 */       String cuserid = this.model.getContext().getPk_loginUser();
/*  548 */       if (StringUtil.isEmptyWithTrim(cuserid))
/*  549 */         cuserid = UserExit.getInstance().getUserId();
/*  550 */       this.billListPanel.setBillType(this.model.getContext().getNodeCode());
/*  551 */       this.billListPanel.setOperator(cuserid);
/*  552 */       this.billListPanel.setCorp(this.model.getContext().getPk_group());
/*  553 */       template = this.billListPanel.getDefaultTemplet(this.billListPanel.getBillType(), null, this.billListPanel.getOperator(), this.billListPanel.getCorp(), this.nodekey, null);
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  562 */       template = this.templateContainer.getTemplate(this.nodekey, this.pos, this.tabCode);
/*      */     }
/*      */     
/*  565 */     if (template == null) {
/*  566 */       Logger.error(NCLangRes.getInstance().getStrByID("uif2", "BillCardPanelForm-000000", null, new String[] { this.nodekey }));
/*  567 */       throw new IllegalArgumentException(NCLangRes.getInstance().getStrByID("uif2", "BatchBillTable-000000"));
/*      */     }
/*      */     
/*  570 */     if (StringUtil.isEmptyWithTrim(this.beanId)) {
/*  571 */       this.beanId = (template.getPKBillTemplet() + "_BillListView");
/*      */     }
/*      */     
/*  574 */     processBillInfo(template);
/*      */   }
/*      */   
/*      */   protected void processBillInfo(BillTempletVO template)
/*      */   {
/*  579 */     processTemplateVO(template);
/*  580 */     BillListData bld = new BillListData(template, getBillStatus());
/*  581 */     if (getUserdefitemListPreparator() != null)
/*  582 */       getUserdefitemListPreparator().prepareBillListData(bld);
/*  583 */     processBillListData(bld);
/*  584 */     this.billListPanel.setListData(bld);
/*      */   }
/*      */   
/*      */   protected void createListPanel() {
/*  588 */     this.billListPanel = new BillListPanel();
/*      */   }
/*      */   
/*      */   protected void processTemplateVO(BillTempletVO templatevo) {}
/*      */   
/*      */   protected void processBillListData(BillListData bld) {}
/*      */   
/*      */   protected void multiSelectBodyRow(RowStateChangeEvent e) {
/*  596 */     BillModel model = getBillListPanel().getBodyBillModel();
/*  597 */     IBillModelRowStateChangeEventListener l = model.getRowStateChangeEventListener();
/*  598 */     model.removeRowStateChangeEventListener();
/*      */     
/*  600 */     if (e.isSelectState()) {
/*  601 */       getBillListPanel().getChildListPanel().selectAllTableRow();
/*      */     } else {
/*  603 */       getBillListPanel().getChildListPanel().cancelSelectAllTableRow();
/*      */     }
/*  605 */     model.addRowStateChangeEventListener(l);
/*      */   }
/*      */   
/*      */   public void handleEvent(AppEvent event)
/*      */   {
/*  610 */     boolean oldHandlingModelEvent = this.handlingModelEvent;
/*  611 */     this.handlingModelEvent = true;
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  616 */       if ("Selection_Changed" == event.getType())
/*      */       {
/*  618 */         handleSelectionChanged();
/*      */       }
/*  620 */       else if ("UiState_Changed" == event.getType())
/*      */       {
/*  622 */         doWhenUIStateChanged((UIStateChangeEvent)event);
/*      */       }
/*  624 */       else if (("Model_Initialized".equalsIgnoreCase(event.getType())) || ("Data_Refresh" == event.getType()))
/*      */       {
/*  626 */         synchronizeDataFromModel();
/*      */ 
/*      */       }
/*  629 */       else if ("Selected_Data_Changed" == event.getType()) {
/*  630 */         syschronizeSelectedRowUpdate();
/*  631 */       } else if ("Data_Updated" == event.getType()) {
/*  632 */         handleDataUpdate(event);
/*      */ 
/*      */       }
/*  635 */       else if ("Data_Deleted" == event.getType()) {
/*  636 */         handleDataDeleted(event);
/*  637 */       } else if ("Data_Inserted" == event.getType()) {
/*  638 */         handleRowInserted(event);
/*      */       }
/*  640 */       else if ("Multi_Selection_Changed" == event.getType())
/*      */       {
/*      */ 
/*      */ 
/*  644 */         if ((isMultiSelectionEnable()) && (getMultiSelectionMode() == CHECKBOX_SELECTION)) {
/*  645 */           setMultiRowSelection(event);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */     }
/*      */     finally
/*      */     {
/*  653 */       this.handlingModelEvent = oldHandlingModelEvent;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void setHeadTableHighLightByMouseClickSelection(AppEvent event)
/*      */   {
/*  660 */     if (this.billListPanel.getHeadTable().getRowCount() < 1) {
/*  661 */       return;
/*      */     }
/*      */     
/*      */ 
/*  665 */     int[] selectedOperaRows = null;
/*  666 */     if (event == null) {
/*  667 */       Integer[] rows = this.model.getSelectedOperaRows();
/*  668 */       if ((rows != null) && (rows.length > 0)) {
/*  669 */         selectedOperaRows = new int[rows.length];
/*  670 */         for (int i = 0; i < rows.length; i++) {
/*  671 */           selectedOperaRows[i] = rows[i].intValue();
/*      */         }
/*      */       }
/*      */     } else {
/*  675 */       RowSelectionOperationInfo info = (RowSelectionOperationInfo)event.getContextObject();
/*  676 */       selectedOperaRows = info.getRowIndexes();
/*      */     }
/*      */     
/*  679 */     if ((selectedOperaRows == null) || (selectedOperaRows.length == 0)) {
/*  680 */       return;
/*      */     }
/*      */     
/*  683 */     HashMap<Integer, ArrayList<Integer>> selectIndexSortGroupMap = getSelectIndexSortGroupMap(selectedOperaRows);
/*  684 */     Set<Integer> keySet = selectIndexSortGroupMap.keySet();
/*  685 */     for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
/*  686 */       Integer endindex = (Integer)iterator.next();
/*  687 */       Integer startindex = (Integer)((ArrayList)selectIndexSortGroupMap.get(endindex)).get(0);
/*      */       
/*  689 */       this.billListPanel.getHeadTable().addRowSelectionInterval(startindex.intValue(), endindex.intValue());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private HashMap<Integer, ArrayList<Integer>> getSelectIndexSortGroupMap(int[] selectedIndex)
/*      */   {
/*  697 */     Arrays.sort(selectedIndex);
/*      */     
/*  699 */     HashMap<Integer, ArrayList<Integer>> selectIndexSortGroupMap = new HashMap();
/*  700 */     for (int i = 0; i < selectedIndex.length; i++) {
/*  701 */       int eachSelectedIndex = selectedIndex[i];
/*  702 */       if (eachSelectedIndex >= 0)
/*      */       {
/*      */ 
/*  705 */         if ((eachSelectedIndex == 0) || (selectIndexSortGroupMap.get(Integer.valueOf(eachSelectedIndex - 1)) == null)) {
/*  706 */           ArrayList<Integer> intgroup = new ArrayList();
/*  707 */           intgroup.add(Integer.valueOf(eachSelectedIndex));
/*  708 */           selectIndexSortGroupMap.put(Integer.valueOf(eachSelectedIndex), intgroup);
/*      */         }
/*      */         
/*  711 */         if (selectIndexSortGroupMap.get(Integer.valueOf(eachSelectedIndex - 1)) != null) {
/*  712 */           ArrayList<Integer> intgroup = (ArrayList)selectIndexSortGroupMap.get(Integer.valueOf(eachSelectedIndex - 1));
/*  713 */           intgroup.add(Integer.valueOf(eachSelectedIndex));
/*      */           
/*  715 */           selectIndexSortGroupMap.remove(Integer.valueOf(eachSelectedIndex - 1));
/*  716 */           selectIndexSortGroupMap.put(Integer.valueOf(eachSelectedIndex), intgroup);
/*      */         }
/*      */       }
/*      */     }
/*  720 */     return selectIndexSortGroupMap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void doWhenUIStateChanged(UIStateChangeEvent event) {}
/*      */   
/*      */ 
/*      */ 
/*      */   public final void mouse_doubleclick(BillMouseEnent e)
/*      */   {
/*  732 */     if (e.getPos() == 0) {
/*  733 */       onHeadMouseDBClick(e);
/*  734 */     } else if (e.getPos() == 1) {
/*  735 */       onBodyMouseDBClick(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void handleRowInserted(AppEvent event)
/*      */   {
/*  745 */     RowOperationInfo roi = (RowOperationInfo)event.getContextObject();
/*  746 */     this.billListPanel.getBillListData().getHeadBillModel().insertRow(roi.getRowIndexes()[0]);
/*      */     
/*  748 */     this.billListPanelValueSetter.setHeaderRowData(this.billListPanel, roi.getRowDatas()[0], roi.getRowIndexes()[0]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void handleSelectionChanged()
/*      */   {
/*  756 */     if (!this.handlingControlEvent)
/*      */     {
/*  758 */       setHeadTableHighLightByModelSelection();
/*      */       
/*  760 */       synchronizeMultiSelection();
/*      */     }
/*  762 */     this.billListPanelValueSetter.setBodyData(this.billListPanel, this.model.getSelectedData());
/*      */   }
/*      */   
/*      */   protected void synchronizeMultiSelection() {
/*  766 */     if (this.handlingControlEvent) { return;
/*      */     }
/*      */     
/*  769 */     if (getMultiSelectionMode() == MOUSE_CLICK_SELECTION)
/*      */     {
/*  771 */       if (getModel().getSelectedRow() != -1) {
/*  772 */         getModel().setSelectedOperaRows(new int[] { getModel().getSelectedRow() });
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected HashMap<String, BillItemUISet> getBillStatus()
/*      */   {
/*  780 */     if (StringUtil.isEmptyWithTrim(getBeanId()))
/*  781 */       return null;
/*  782 */     if ((getModel().getContext() == null) || (getModel().getContext().getStatusRegistery() == null)) {
/*  783 */       return null;
/*      */     }
/*  785 */     Object statusObj = getModel().getContext().getStatusRegistery().getAppStatusObject(getBeanId());
/*  786 */     if ((statusObj == null) || (!(statusObj instanceof HashMap)))
/*  787 */       return null;
/*  788 */     return (HashMap)statusObj;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void setHeadTableHighLightByModelSelection()
/*      */   {
/*  795 */     if (this.billListPanel.getHeadTable().getRowCount() < 1) {
/*  796 */       return;
/*      */     }
/*  798 */     if ((this.model instanceof IRowSelectModel))
/*      */     {
/*  800 */       IRowSelectModel rowModel = this.model;
/*  801 */       if (rowModel.getSelectedRow() != -1) {
/*  802 */         this.billListPanel.getHeadTable().setRowSelectionInterval(rowModel.getSelectedRow(), rowModel.getSelectedRow());
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void handleDataDeleted(AppEvent event)
/*      */   {
/*  810 */     RowOperationInfo roi = (RowOperationInfo)event.getContextObject();
/*  811 */     if (roi != null) {
/*  812 */       this.billListPanel.getBillListData().getHeadBillModel().delLine(roi.getRowIndexes());
/*      */     }
/*      */   }
/*      */   
/*      */   protected void syschronizeSelectedRowUpdate()
/*      */   {
/*  818 */     if ((this.model instanceof IRowSelectModel))
/*      */     {
/*  820 */       this.billListPanelValueSetter.setHeaderRowData(this.billListPanel, this.model.getSelectedData(), this.model.getSelectedRow());
/*  821 */       this.billListPanelValueSetter.setBodyData(this.billListPanel, this.model.getSelectedData());
/*      */     }
/*      */   }
/*      */   
/*      */   protected void handleDataUpdate(AppEvent event)
/*      */   {
/*  827 */     RowOperationInfo operationInfo = (RowOperationInfo)event.getContextObject();
/*  828 */     if ((this.model instanceof IRowSelectModel))
/*      */     {
/*  830 */       if (((this.billListPanelValueSetter instanceof IBillListPanelValueSetterExt)) && (operationInfo.getRowIndexes().length > 1))
/*      */       {
/*  832 */         ((IBillListPanelValueSetterExt)this.billListPanelValueSetter).setHeaderRowsData(this.billListPanel, operationInfo.getRowDatas(), operationInfo.getRowIndexes());
/*  833 */         return;
/*      */       }
/*      */       
/*  836 */       for (int i = 0; i < operationInfo.getRowIndexes().length; i++)
/*      */       {
/*  838 */         if (operationInfo.getRowIndexes()[i] != this.model.getSelectedRow())
/*      */         {
/*  840 */           this.billListPanelValueSetter.setHeaderRowData(this.billListPanel, operationInfo.getRowDatas()[i], operationInfo.getRowIndexes()[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void synchronizeDataFromModel()
/*      */   {
/*  852 */     Object[] datas = this.model.getData().toArray();
/*      */     
/*  854 */     if ((datas == null) || (datas.length == 0)) {
/*  855 */       this.billListPanel.getHeadBillModel().clearBodyData();
/*  856 */       this.billListPanel.getBodyBillModel().clearBodyData();
/*      */     } else {
/*  858 */       this.billListPanelValueSetter.setHeaderDatas(this.billListPanel, datas);
/*  859 */       this.billListPanelValueSetter.setBodyData(this.billListPanel, this.model.getSelectedData());
/*      */       
/*  861 */       setHeadTableHighLightByModelSelection();
/*      */       
/*  863 */       setCheckBoxMultiUnstate();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setCheckBoxMultiUnstate()
/*      */   {
/*  873 */     if ((isMultiSelectionEnable()) && (getMultiSelectionMode() == CHECKBOX_SELECTION))
/*      */     {
/*  875 */       for (int i = 0; i < getModel().getRowCount(); i++)
/*      */       {
/*  877 */         this.billListPanel.getHeadBillModel().setRowState(i, -1);
/*      */       }
/*  879 */       updateUI();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setMultiRowSelection(AppEvent event)
/*      */   {
/*  890 */     if (this.handlingControlEvent) { return;
/*      */     }
/*      */     
/*  893 */     if ((isMultiSelectionEnable()) && (getMultiSelectionMode() == CHECKBOX_SELECTION) && ((this.model instanceof IMultiRowSelectModel)))
/*      */     {
/*  895 */       Integer[] selectedOperaRows = null;
/*  896 */       int state = 0;
/*  897 */       if (event == null)
/*      */       {
/*  899 */         selectedOperaRows = this.model.getSelectedOperaRows();
/*      */       }
/*      */       else {
/*  902 */         RowSelectionOperationInfo info = (RowSelectionOperationInfo)event.getContextObject();
/*  903 */         int[] indexs = info.getRowIndexes();
/*  904 */         state = info.getSelectionState();
/*  905 */         if ((indexs != null) && (indexs.length > 0))
/*      */         {
/*  907 */           selectedOperaRows = new Integer[indexs.length];
/*  908 */           for (int i = 0; i < indexs.length; i++) {
/*  909 */             selectedOperaRows[i] = Integer.valueOf(indexs[i]);
/*      */           }
/*      */         }
/*      */       }
/*  913 */       for (int i = 0; i < selectedOperaRows.length; i++)
/*      */       {
/*  915 */         Integer index = selectedOperaRows[i];
/*  916 */         if (state == 0) {
/*  917 */           this.billListPanel.getHeadBillModel().setRowState(index.intValue(), 4);
/*      */         } else
/*  919 */           this.billListPanel.getHeadBillModel().setRowState(index.intValue(), -1);
/*      */       }
/*  921 */       updateUI();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void onDelete(int[] lines)
/*      */   {
/*  931 */     this.billListPanel.getHeadBillModel().delLine(lines);
/*      */   }
/*      */   
/*      */   protected void onHeadMouseDBClick(BillMouseEnent e)
/*      */   {
/*  936 */     getModel().fireShowEditorEvent();
/*      */   }
/*      */   
/*      */ 
/*      */   protected void onBodyMouseDBClick(BillMouseEnent e) {}
/*      */   
/*      */ 
/*      */   public BillManageModel getModel()
/*      */   {
/*  945 */     return this.model;
/*      */   }
/*      */   
/*      */   public void setModel(BillManageModel model) {
/*  949 */     this.model = model;
/*  950 */     model.addAppEventListener(this);
/*      */   }
/*      */   
/*      */   private void registeCallback()
/*      */   {
/*  955 */     if ((this.model.getContext() == null) || (this.model.getContext().getStatusRegistery() == null))
/*  956 */       return;
/*  957 */     this.model.getContext().getStatusRegistery().addCallback(new AppStatusRegisteryCallback()
/*      */     {
/*      */       public Object getID()
/*      */       {
/*  961 */         return BillListView.this.getBeanId();
/*      */       }
/*      */       
/*      */       public Object getStatusObject()
/*      */       {
/*  966 */         return BillListView.this.getBillListPanel().getBillCardUISet();
/*      */       }
/*      */     });
/*      */   }
/*      */   
/*      */   public String getNodekey() {
/*  972 */     return this.nodekey;
/*      */   }
/*      */   
/*      */   public void setNodekey(String nodekey) {
/*  976 */     this.nodekey = nodekey;
/*      */   }
/*      */   
/*      */   public TemplateContainer getTemplateContainer() {
/*  980 */     return this.templateContainer;
/*      */   }
/*      */   
/*      */   public void setTemplateContainer(TemplateContainer templateContainer) {
/*  984 */     this.templateContainer = templateContainer;
/*      */   }
/*      */   
/*      */   public String getPos() {
/*  988 */     return this.pos;
/*      */   }
/*      */   
/*      */   public void setPos(String pos) {
/*  992 */     this.pos = pos;
/*      */   }
/*      */   
/*      */   public List<String> getTabCode() {
/*  996 */     return this.tabCode;
/*      */   }
/*      */   
/*      */   public void setTabCode(List<String> tabCode) {
/* 1000 */     this.tabCode = tabCode;
/*      */   }
/*      */   
/*      */   public void afterEdit(BillEditEvent e) {}
/*      */   
/*      */   public void bodyRowChange(BillEditEvent e)
/*      */   {
/* 1007 */     if (this.handlingModelEvent) { return;
/*      */     }
/* 1009 */     this.handlingControlEvent = true;
/*      */     try
/*      */     {
/* 1012 */       if ((e.getOldRow() != e.getRow()) && (e.getRow() < this.model.getRowCount()) && 
/* 1013 */         ((this.model instanceof IRowSelectModel)))
/*      */       {
/* 1015 */         this.model.setSelectedRow(e.getRow());
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/* 1022 */       this.handlingControlEvent = false;
/*      */     }
/*      */   }
/*      */   
/*      */   public IBillListPanelValueSetter getBillListPanelValueSetter() {
/* 1027 */     return this.billListPanelValueSetter;
/*      */   }
/*      */   
/*      */   public void setBillListPanelValueSetter(IBillListPanelValueSetter billListPanelValueSetter)
/*      */   {
/* 1032 */     this.billListPanelValueSetter = billListPanelValueSetter;
/*      */   }
/*      */   
/*      */   public boolean isMultiSelectionEnable() {
/* 1036 */     return this.multiSelectionEnable;
/*      */   }
/*      */   
/*      */   public void setMultiSelectionEnable(boolean multiSelectionEnable) {
/* 1040 */     this.multiSelectionEnable = multiSelectionEnable;
/*      */   }
/*      */   
/*      */   public BillListPanel getBillListPanel() {
/* 1044 */     return this.billListPanel;
/*      */   }
/*      */   
/*      */   public void setBillListPanel(BillListPanel billListPanel) {
/* 1048 */     this.billListPanel = billListPanel;
/*      */   }
/*      */   
/*      */   public IBillListData getUserdefitemListPreparator() {
/* 1052 */     return this.userdefitemListPreparator;
/*      */   }
/*      */   
/*      */   public void setUserdefitemListPreparator(IBillListData userdefitemListPreparator) {
/* 1056 */     this.userdefitemListPreparator = userdefitemListPreparator;
/*      */   }
/*      */   
/*      */   public String getBeanId()
/*      */   {
/* 1061 */     return this.beanId;
/*      */   }
/*      */   
/*      */   public void setBeanId(String beanId) {
/* 1065 */     this.beanId = beanId;
/*      */   }
/*      */   
/*      */   public int getMultiSelectionMode() {
/* 1069 */     return this.multiSelectionMode;
/*      */   }
/*      */   
/*      */   public void setMultiSelectionMode(int multiSelectionMode) {
/* 1073 */     this.multiSelectionMode = multiSelectionMode;
/*      */   }
/*      */   
/*      */   public boolean isShowTotalLine() {
/* 1077 */     return this.showTotalLine;
/*      */   }
/*      */   
/*      */   public void setShowTotalLine(boolean showTotalLine) {
/* 1081 */     this.showTotalLine = showTotalLine;
/*      */   }
/*      */   
/*      */   public List<String> getShowTotalLineTabcodes() {
/* 1085 */     return this.showTotalLineTabcodes;
/*      */   }
/*      */   
/*      */   public void setShowTotalLineTabcodes(List<String> showTotalLineTabcodes) {
/* 1089 */     this.showTotalLineTabcodes = showTotalLineTabcodes;
/*      */   }
/*      */   
/*      */   public PaginationBar getPaginationBar() {
/* 1093 */     return this.paginationBar;
/*      */   }
/*      */   
/*      */   public void setPaginationBar(PaginationBar paginationBar) {
/* 1097 */     this.paginationBar = paginationBar;
/*      */   }
/*      */   
/*      */   public boolean isShowListPanelBorder() {
/* 1101 */     return this.showListPanelBorder;
/*      */   }
/*      */   
/*      */   public void setShowListPanelBorder(boolean showListPanelBorder) {
/* 1105 */     this.showListPanelBorder = showListPanelBorder;
/*      */   }
/*      */ }

