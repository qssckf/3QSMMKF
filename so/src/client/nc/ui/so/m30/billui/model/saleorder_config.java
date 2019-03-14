/*      */ package nc.ui.so.m30.billui.model;
/*      */ 
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import nc.ui.uif2.I18nFB;
/*      */ 
/*      */ public class saleorder_config extends nc.ui.uif2.factory.AbstractJavaBeanDefinition
/*      */ {
/*      */   public saleorder_config() {}
/*      */   
/*   11 */   private Map<String, Object> context = new java.util.HashMap();
/*      */   
/*   13 */   public nc.vo.uif2.LoginContext getContext() { if (this.context.get("context") != null)
/*   14 */       return (nc.vo.uif2.LoginContext)this.context.get("context");
/*   15 */     nc.vo.uif2.LoginContext bean = new nc.vo.uif2.LoginContext();
/*   16 */     this.context.put("context", bean);
/*   17 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   18 */     invokeInitializingBean(bean);
/*   19 */     return bean;
/*      */   }
/*      */   
/*      */   public SaleOrderModelService getManageModelService() {
/*   23 */     if (this.context.get("ManageModelService") != null)
/*   24 */       return (SaleOrderModelService)this.context.get("ManageModelService");
/*   25 */     SaleOrderModelService bean = new SaleOrderModelService();
/*   26 */     this.context.put("ManageModelService", bean);
/*   27 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   28 */     invokeInitializingBean(bean);
/*   29 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory() {
/*   33 */     if (this.context.get("boadatorfactory") != null)
/*   34 */       return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)this.context.get("boadatorfactory");
/*   35 */     nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
/*   36 */     this.context.put("boadatorfactory", bean);
/*   37 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   38 */     invokeInitializingBean(bean);
/*   39 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.BillManageModel getManageAppModel() {
/*   43 */     if (this.context.get("ManageAppModel") != null)
/*   44 */       return (nc.ui.pubapp.uif2app.model.BillManageModel)this.context.get("ManageAppModel");
/*   45 */     nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
/*   46 */     this.context.put("ManageAppModel", bean);
/*   47 */     bean.setService(getManageModelService());
/*   48 */     bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
/*   49 */     bean.setContext(getContext());
/*   50 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   51 */     invokeInitializingBean(bean);
/*   52 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.query2.model.ModelDataManager getModelDataManager() {
/*   56 */     if (this.context.get("modelDataManager") != null)
/*   57 */       return (nc.ui.pubapp.uif2app.query2.model.ModelDataManager)this.context.get("modelDataManager");
/*   58 */     nc.ui.pubapp.uif2app.query2.model.ModelDataManager bean = new nc.ui.pubapp.uif2app.query2.model.ModelDataManager();
/*   59 */     this.context.put("modelDataManager", bean);
/*   60 */     bean.setModel(getManageAppModel());
/*   61 */     bean.setService(getManageModelService());
/*   62 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   63 */     invokeInitializingBean(bean);
/*   64 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getTrantypeTempMender() {
/*   68 */     if (this.context.get("trantypeTempMender") != null)
/*   69 */       return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)this.context.get("trantypeTempMender");
/*   70 */     nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());this.context.put("trantypeTempMender", bean);
/*   71 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   72 */     invokeInitializingBean(bean);
/*   73 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer() {
/*   77 */     if (this.context.get("templateContainer") != null)
/*   78 */       return (nc.ui.pubapp.uif2app.view.TemplateContainer)this.context.get("templateContainer");
/*   79 */     nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
/*   80 */     this.context.put("templateContainer", bean);
/*   81 */     bean.setContext(getContext());
/*   82 */     bean.setBillTemplateMender(getTrantypeTempMender());
/*   83 */     bean.load();
/*   84 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*   85 */     invokeInitializingBean(bean);
/*   86 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.view.SaleOrderBillListView getListView() {
/*   90 */     if (this.context.get("listView") != null)
/*   91 */       return (nc.ui.so.m30.billui.view.SaleOrderBillListView)this.context.get("listView");
/*   92 */     nc.ui.so.m30.billui.view.SaleOrderBillListView bean = new nc.ui.so.m30.billui.view.SaleOrderBillListView();
/*   93 */     this.context.put("listView", bean);
/*   94 */     bean.setModel(getManageAppModel());
/*   95 */     bean.setMultiSelectionMode(0);
/*   96 */     bean.setTemplateContainer(getTemplateContainer());
/*   97 */     bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
/*   98 */     bean.initUI();
/*   99 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  100 */     invokeInitializingBean(bean);
/*  101 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator() {
/*  105 */     if (this.context.get("userdefAndMarAsstListPreparator") != null)
/*  106 */       return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)this.context.get("userdefAndMarAsstListPreparator");
/*  107 */     nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
/*  108 */     this.context.put("userdefAndMarAsstListPreparator", bean);
/*  109 */     bean.setBillListDataPrepares(getManagedList0());
/*  110 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  111 */     invokeInitializingBean(bean);
/*  112 */     return bean;
/*      */   }
/*      */   
/*  115 */   private List getManagedList0() { List list = new java.util.ArrayList();list.add(getUserdefitemlistPreparator());list.add(getMarAsstPreparator());return list;
/*      */   }
/*      */   
/*  118 */   public nc.ui.so.m30.billui.view.SaleOrderBillForm getBillFormEditor() { if (this.context.get("billFormEditor") != null)
/*  119 */       return (nc.ui.so.m30.billui.view.SaleOrderBillForm)this.context.get("billFormEditor");
/*  120 */     nc.ui.so.m30.billui.view.SaleOrderBillForm bean = new nc.ui.so.m30.billui.view.SaleOrderBillForm();
/*  121 */     this.context.put("billFormEditor", bean);
/*  122 */     bean.setModel(getManageAppModel());
/*  123 */     bean.setTemplateContainer(getTemplateContainer());
/*  124 */     bean.setTemplateNotNullValidate(true);
/*  125 */     bean.setAutoAddLine(true);
/*  126 */     bean.setUserdefitemPreparator(getCompositeBillDataPrepare_154eefc());
/*  127 */     bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_115fcb8());
/*  128 */     bean.setClearHyperlink(getManagedList3());
/*  129 */     bean.setBodyLineActions(getManagedList4());
/*  130 */     bean.initUI();
/*  131 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  132 */     invokeInitializingBean(bean);
/*  133 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getCompositeBillDataPrepare_154eefc() {
/*  137 */     if (this.context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#154eefc") != null)
/*  138 */       return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)this.context.get("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#154eefc");
/*  139 */     nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
/*  140 */     this.context.put("nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare#154eefc", bean);
/*  141 */     bean.setBillDataPrepares(getManagedList1());
/*  142 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  143 */     invokeInitializingBean(bean);
/*  144 */     return bean;
/*      */   }
/*      */   
/*  147 */   private List getManagedList1() { List list = new java.util.ArrayList();list.add(getUserdefitemPreparator());list.add(getMarAsstPreparator());return list;
/*      */   }
/*      */   
/*  150 */   private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_115fcb8() { if (this.context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#115fcb8") != null)
/*  151 */       return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter)this.context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#115fcb8");
/*  152 */     nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
/*  153 */     this.context.put("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#115fcb8", bean);
/*  154 */     bean.setFilterMap(getManagedMap0());
/*  155 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  156 */     invokeInitializingBean(bean);
/*  157 */     return bean;
/*      */   }
/*      */   
/*  160 */   private Map getManagedMap0() { Map map = new java.util.HashMap();map.put("bodytable1", getManagedList2());return map; }
/*      */   
/*  162 */   private List getManagedList2() { List list = new java.util.ArrayList();list.add("cmaterialvid");return list; }
/*      */   
/*  164 */   private List getManagedList3() { List list = new java.util.ArrayList();list.add("vbillcode");return list; }
/*      */   
/*  166 */   private List getManagedList4() { List list = new java.util.ArrayList();list.add(getBodyAddLineAction_829abe());list.add(getSaleOrderInsertLineAction());list.add(getSaleOrderDelLineAction_7d3f98());list.add(getBodyCopyLineAction_da1c9d());list.add(getBodyPasteLineAction_18c8f09());list.add(getBodyPasteToTailAction_102a0df());list.add(getActionsBar_ActionsBarSeparator_18fa26e());list.add(getRearrangeRowNoBodyLineAction_f0e48e());list.add(getActionsBar_ActionsBarSeparator_12d66b7());list.add(getDefaultBodyZoomAction_1b28e86());return list;
/*      */   }
/*      */   
/*  169 */   private nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction_829abe() { if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#829abe") != null)
/*  170 */       return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#829abe");
/*  171 */     nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
/*  172 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#829abe", bean);
/*  173 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  174 */     invokeInitializingBean(bean);
/*  175 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction getSaleOrderDelLineAction_7d3f98() {
/*  179 */     if (this.context.get("nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction#7d3f98") != null)
/*  180 */       return (nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction)this.context.get("nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction#7d3f98");
/*  181 */     nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction bean = new nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction();
/*  182 */     this.context.put("nc.ui.so.m30.billui.action.line.SaleOrderDelLineAction#7d3f98", bean);
/*  183 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  184 */     invokeInitializingBean(bean);
/*  185 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_da1c9d() {
/*  189 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#da1c9d") != null)
/*  190 */       return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#da1c9d");
/*  191 */     nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
/*  192 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#da1c9d", bean);
/*  193 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  194 */     invokeInitializingBean(bean);
/*  195 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_18c8f09() {
/*  199 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#18c8f09") != null)
/*  200 */       return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#18c8f09");
/*  201 */     nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
/*  202 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#18c8f09", bean);
/*  203 */     bean.setClearItems(getManagedList5());
/*  204 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  205 */     invokeInitializingBean(bean);
/*  206 */     return bean;
/*      */   }
/*      */   
/*  209 */   private List getManagedList5() { List list = new java.util.ArrayList();list.add("csaleorderbid");list.add("ts");list.add("nreqrsnum");return list;
/*      */   }
/*      */   
/*  212 */   private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_102a0df() { if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#102a0df") != null)
/*  213 */       return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#102a0df");
/*  214 */     nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
/*  215 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#102a0df", bean);
/*  216 */     bean.setClearItems(getManagedList6());
/*  217 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  218 */     invokeInitializingBean(bean);
/*  219 */     return bean;
/*      */   }
/*      */   
/*  222 */   private List getManagedList6() { List list = new java.util.ArrayList();list.add("csaleorderbid");list.add("ts");list.add("nreqrsnum");return list;
/*      */   }
/*      */   
/*  225 */   private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_18fa26e() { if (this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#18fa26e") != null)
/*  226 */       return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#18fa26e");
/*  227 */     nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
/*  228 */     this.context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#18fa26e", bean);
/*  229 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  230 */     invokeInitializingBean(bean);
/*  231 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_f0e48e() {
/*  235 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#f0e48e") != null)
/*  236 */       return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#f0e48e");
/*  237 */     nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
/*  238 */     this.context.put("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#f0e48e", bean);
/*  239 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  240 */     invokeInitializingBean(bean);
/*  241 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_12d66b7() {
/*  245 */     if (this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#12d66b7") != null)
/*  246 */       return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#12d66b7");
/*  247 */     nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
/*  248 */     this.context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#12d66b7", bean);
/*  249 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  250 */     invokeInitializingBean(bean);
/*  251 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_1b28e86() {
/*  255 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1b28e86") != null)
/*  256 */       return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)this.context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1b28e86");
/*  257 */     nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
/*  258 */     this.context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#1b28e86", bean);
/*  259 */     bean.setPos(1);
/*  260 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  261 */     invokeInitializingBean(bean);
/*  262 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getSaleOrderInsertLineAction() {
/*  266 */     if (this.context.get("SaleOrderInsertLineAction") != null)
/*  267 */       return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)this.context.get("SaleOrderInsertLineAction");
/*  268 */     nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
/*  269 */     this.context.put("SaleOrderInsertLineAction", bean);
/*  270 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  271 */     invokeInitializingBean(bean);
/*  272 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
/*  276 */     if (this.context.get("mouseClickShowPanelMediator") != null)
/*  277 */       return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)this.context.get("mouseClickShowPanelMediator");
/*  278 */     nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
/*  279 */     this.context.put("mouseClickShowPanelMediator", bean);
/*  280 */     bean.setListView(getListView());
/*  281 */     bean.setShowUpComponent(getBillFormEditor());
/*  282 */     bean.setHyperLinkColumn("vbillcode");
/*  283 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  284 */     invokeInitializingBean(bean);
/*  285 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getFormInterceptor() {
/*  289 */     if (this.context.get("formInterceptor") != null)
/*  290 */       return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)this.context.get("formInterceptor");
/*  291 */     nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
/*  292 */     this.context.put("formInterceptor", bean);
/*  293 */     bean.setShowUpComponent(getBillFormEditor());
/*  294 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  295 */     invokeInitializingBean(bean);
/*  296 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor() {
/*  300 */     if (this.context.get("listInterceptor") != null)
/*  301 */       return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)this.context.get("listInterceptor");
/*  302 */     nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
/*  303 */     this.context.put("listInterceptor", bean);
/*  304 */     bean.setShowUpComponent(getListView());
/*  305 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  306 */     invokeInitializingBean(bean);
/*  307 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator() {
/*  311 */     if (this.context.get("billCodeMediator") != null)
/*  312 */       return (nc.ui.pubapp.bill.BillCodeMediator)this.context.get("billCodeMediator");
/*  313 */     nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
/*  314 */     this.context.put("billCodeMediator", bean);
/*  315 */     bean.setBillCodeKey("vbillcode");
/*  316 */     bean.setBillType("30");
/*  317 */     bean.setBillForm(getBillFormEditor());
/*  318 */     bean.initUI();
/*  319 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  320 */     invokeInitializingBean(bean);
/*  321 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.FunNodeClosingHandler getClosingListener() {
/*  325 */     if (this.context.get("ClosingListener") != null)
/*  326 */       return (nc.ui.uif2.FunNodeClosingHandler)this.context.get("ClosingListener");
/*  327 */     nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
/*  328 */     this.context.put("ClosingListener", bean);
/*  329 */     bean.setModel(getManageAppModel());
/*  330 */     bean.setSaveaction(getSaveAction());
/*  331 */     bean.setCancelaction(getCancelAction());
/*  332 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  333 */     invokeInitializingBean(bean);
/*  334 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
/*  338 */     if (this.context.get("InitDataListener") != null)
/*  339 */       return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)this.context.get("InitDataListener");
/*  340 */     nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
/*  341 */     this.context.put("InitDataListener", bean);
/*  342 */     bean.setContext(getContext());
/*  343 */     bean.setModel(getManageAppModel());
/*  344 */     bean.setVoClassName("nc.vo.so.m30.entity.SaleOrderVO");
/*  345 */     bean.setAutoShowUpComponent(getBillFormEditor());
/*  346 */     bean.setQueryAction(getQueryAction());
/*  347 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  348 */     invokeInitializingBean(bean);
/*  349 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
/*  353 */     if (this.context.get("rowNoMediator") != null)
/*  354 */       return (nc.ui.pubapp.uif2app.view.RowNoMediator)this.context.get("rowNoMediator");
/*  355 */     nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
/*  356 */     this.context.put("rowNoMediator", bean);
/*  357 */     bean.setModel(getManageAppModel());
/*  358 */     bean.setEditor(getBillFormEditor());
/*  359 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  360 */     invokeInitializingBean(bean);
/*  361 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
/*  365 */     if (this.context.get("userdefitemPreparator") != null)
/*  366 */       return (nc.ui.uif2.editor.UserdefitemContainerPreparator)this.context.get("userdefitemPreparator");
/*  367 */     nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
/*  368 */     this.context.put("userdefitemPreparator", bean);
/*  369 */     bean.setContainer(getUserdefitemContainer());
/*  370 */     bean.setParams(getManagedList7());
/*  371 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  372 */     invokeInitializingBean(bean);
/*  373 */     return bean;
/*      */   }
/*      */   
/*  376 */   private List getManagedList7() { List list = new java.util.ArrayList();list.add(getUserQueryParams1());list.add(getUserdefQueryParam_1ab18e7());list.add(getUserdefQueryParam_1f0075f());return list;
/*      */   }
/*      */   
/*  379 */   private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1ab18e7() { if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#1ab18e7") != null)
/*  380 */       return (nc.ui.uif2.editor.UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#1ab18e7");
/*  381 */     nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
/*  382 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#1ab18e7", bean);
/*  383 */     bean.setMdfullname("so.so_saleorder");
/*  384 */     bean.setPos(0);
/*  385 */     bean.setPrefix("vdef");
/*  386 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  387 */     invokeInitializingBean(bean);
/*  388 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1f0075f() {
/*  392 */     if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#1f0075f") != null)
/*  393 */       return (nc.ui.uif2.editor.UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#1f0075f");
/*  394 */     nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
/*  395 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#1f0075f", bean);
/*  396 */     bean.setMdfullname("so.so_saleorder_b");
/*  397 */     bean.setPos(1);
/*  398 */     bean.setPrefix("vbdef");
/*  399 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  400 */     invokeInitializingBean(bean);
/*  401 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1() {
/*  405 */     if (this.context.get("userQueryParams1") != null)
/*  406 */       return (nc.ui.uif2.editor.UserdefQueryParam)this.context.get("userQueryParams1");
/*  407 */     nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
/*  408 */     this.context.put("userQueryParams1", bean);
/*  409 */     bean.setMdfullname(" ic.OnhandDimVO");
/*  410 */     bean.setPos(0);
/*  411 */     bean.setPrefix("vdef");
/*  412 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  413 */     invokeInitializingBean(bean);
/*  414 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator() {
/*  418 */     if (this.context.get("userdefitemlistPreparator") != null)
/*  419 */       return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)this.context.get("userdefitemlistPreparator");
/*  420 */     nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
/*  421 */     this.context.put("userdefitemlistPreparator", bean);
/*  422 */     bean.setContainer(getUserdefitemContainer());
/*  423 */     bean.setParams(getManagedList8());
/*  424 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  425 */     invokeInitializingBean(bean);
/*  426 */     return bean;
/*      */   }
/*      */   
/*  429 */   private List getManagedList8() { List list = new java.util.ArrayList();list.add(getUserdefQueryParam_17412de());list.add(getUserdefQueryParam_182ddc7());return list;
/*      */   }
/*      */   
/*  432 */   private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_17412de() { if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#17412de") != null)
/*  433 */       return (nc.ui.uif2.editor.UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#17412de");
/*  434 */     nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
/*  435 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#17412de", bean);
/*  436 */     bean.setMdfullname("so.so_saleorder");
/*  437 */     bean.setPos(0);
/*  438 */     bean.setPrefix("vdef");
/*  439 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  440 */     invokeInitializingBean(bean);
/*  441 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_182ddc7() {
/*  445 */     if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#182ddc7") != null)
/*  446 */       return (nc.ui.uif2.editor.UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#182ddc7");
/*  447 */     nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
/*  448 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#182ddc7", bean);
/*  449 */     bean.setMdfullname("so.so_saleorder_b");
/*  450 */     bean.setPos(1);
/*  451 */     bean.setTabcode("bodytable1");
/*  452 */     bean.setPrefix("vbdef");
/*  453 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  454 */     invokeInitializingBean(bean);
/*  455 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
/*  459 */     if (this.context.get("marAsstPreparator") != null)
/*  460 */       return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)this.context.get("marAsstPreparator");
/*  461 */     nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
/*  462 */     this.context.put("marAsstPreparator", bean);
/*  463 */     bean.setModel(getManageAppModel());
/*  464 */     bean.setContainer(getUserdefitemContainer());
/*  465 */     bean.setPrefix("vfree");
/*  466 */     bean.setMaterialField("cmaterialid");
/*  467 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  468 */     invokeInitializingBean(bean);
/*  469 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
/*  473 */     if (this.context.get("userdefitemContainer") != null)
/*  474 */       return (nc.ui.uif2.userdefitem.UserDefItemContainer)this.context.get("userdefitemContainer");
/*  475 */     nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
/*  476 */     this.context.put("userdefitemContainer", bean);
/*  477 */     bean.setContext(getContext());
/*  478 */     bean.setParams(getManagedList9());
/*  479 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  480 */     invokeInitializingBean(bean);
/*  481 */     return bean;
/*      */   }
/*      */   
/*  484 */   private List getManagedList9() { List list = new java.util.ArrayList();list.add(getQueryParams1());list.add(getQueryParam_ae535d());list.add(getQueryParam_e32d2d());list.add(getQueryParam_ab1c2d());return list;
/*      */   }
/*      */   
/*  487 */   private nc.ui.uif2.userdefitem.QueryParam getQueryParam_ae535d() { if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#ae535d") != null)
/*  488 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("nc.ui.uif2.userdefitem.QueryParam#ae535d");
/*  489 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/*  490 */     this.context.put("nc.ui.uif2.userdefitem.QueryParam#ae535d", bean);
/*  491 */     bean.setMdfullname("so.so_saleorder");
/*  492 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  493 */     invokeInitializingBean(bean);
/*  494 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.userdefitem.QueryParam getQueryParam_e32d2d() {
/*  498 */     if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#e32d2d") != null)
/*  499 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("nc.ui.uif2.userdefitem.QueryParam#e32d2d");
/*  500 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/*  501 */     this.context.put("nc.ui.uif2.userdefitem.QueryParam#e32d2d", bean);
/*  502 */     bean.setMdfullname("so.so_saleorder_b");
/*  503 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  504 */     invokeInitializingBean(bean);
/*  505 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.userdefitem.QueryParam getQueryParam_ab1c2d() {
/*  509 */     if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#ab1c2d") != null)
/*  510 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("nc.ui.uif2.userdefitem.QueryParam#ab1c2d");
/*  511 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/*  512 */     this.context.put("nc.ui.uif2.userdefitem.QueryParam#ab1c2d", bean);
/*  513 */     bean.setRulecode("materialassistant");
/*  514 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  515 */     invokeInitializingBean(bean);
/*  516 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.userdefitem.QueryParam getQueryParams1() {
/*  520 */     if (this.context.get("queryParams1") != null)
/*  521 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("queryParams1");
/*  522 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/*  523 */     this.context.put("queryParams1", bean);
/*  524 */     bean.setMdfullname("ic.OnhandDimVO");
/*  525 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  526 */     invokeInitializingBean(bean);
/*  527 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
/*  531 */     if (this.context.get("fractionFixMediator") != null)
/*  532 */       return (nc.ui.pubapp.uif2app.view.FractionFixMediator)this.context.get("fractionFixMediator");
/*  533 */     nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor());this.context.put("fractionFixMediator", bean);
/*  534 */     bean.initUI();
/*  535 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  536 */     invokeInitializingBean(bean);
/*  537 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
/*  541 */     if (this.context.get("billBodySortMediator") != null)
/*  542 */       return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)this.context.get("billBodySortMediator");
/*  543 */     nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(), getBillFormEditor(), getListView());this.context.put("billBodySortMediator", bean);
/*  544 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  545 */     invokeInitializingBean(bean);
/*  546 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
/*  550 */     if (this.context.get("appEventHandlerMediator") != null)
/*  551 */       return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)this.context.get("appEventHandlerMediator");
/*  552 */     nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
/*  553 */     this.context.put("appEventHandlerMediator", bean);
/*  554 */     bean.setModel(getManageAppModel());
/*  555 */     bean.setHandlerMap(getManagedMap1());
/*  556 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  557 */     invokeInitializingBean(bean);
/*  558 */     return bean;
/*      */   }
/*      */   
/*  561 */   private Map getManagedMap1() { Map map = new java.util.HashMap();map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent", getManagedList10());map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent", getManagedList11());map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent", getManagedList12());map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent", getManagedList13());map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent", getManagedList14());map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent", getManagedList15());return map; }
/*      */   
/*  563 */   private List getManagedList10() { List list = new java.util.ArrayList();list.add(getHeadBeforeEditHandler_1c7040a());return list;
/*      */   }
/*      */   
/*  566 */   private nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler getHeadBeforeEditHandler_1c7040a() { if (this.context.get("nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler#1c7040a") != null)
/*  567 */       return (nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler)this.context.get("nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler#1c7040a");
/*  568 */     nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler bean = new nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler();
/*  569 */     this.context.put("nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler#1c7040a", bean);
/*  570 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  571 */     invokeInitializingBean(bean);
/*  572 */     return bean;
/*      */   }
/*      */   
/*  575 */   private List getManagedList11() { List list = new java.util.ArrayList();list.add(getHeadAfterEditHandler_da4a28());return list;
/*      */   }
/*      */   
/*  578 */   private nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler getHeadAfterEditHandler_da4a28() { if (this.context.get("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#da4a28") != null)
/*  579 */       return (nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler)this.context.get("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#da4a28");
/*  580 */     nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler bean = new nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler();
/*  581 */     this.context.put("nc.ui.so.m30.billui.editor.headevent.HeadAfterEditHandler#da4a28", bean);
/*  582 */     bean.setBillform(getBillFormEditor());
/*  583 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  584 */     invokeInitializingBean(bean);
/*  585 */     return bean;
/*      */   }
/*      */   
/*  588 */   private List getManagedList12() { List list = new java.util.ArrayList();list.add(getBodyBeforeEditHandler_13aa541());return list;
/*      */   }
/*      */   
/*  591 */   private nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler getBodyBeforeEditHandler_13aa541() { if (this.context.get("nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler#13aa541") != null)
/*  592 */       return (nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler)this.context.get("nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler#13aa541");
/*  593 */     nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler();
/*  594 */     this.context.put("nc.ui.so.m30.billui.editor.bodyevent.BodyBeforeEditHandler#13aa541", bean);
/*  595 */     bean.setBillform(getBillFormEditor());
/*  596 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  597 */     invokeInitializingBean(bean);
/*  598 */     return bean;
/*      */   }
/*      */   
/*  601 */   private List getManagedList13() { List list = new java.util.ArrayList();list.add(getBodyAfterEditHandler_137500e());return list;
/*      */   }
/*      */   
/*  604 */   private nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler getBodyAfterEditHandler_137500e() { if (this.context.get("nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler#137500e") != null)
/*  605 */       return (nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler)this.context.get("nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler#137500e");
/*  606 */     nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler();
/*  607 */     this.context.put("nc.ui.so.m30.billui.editor.bodyevent.BodyAfterEditHandler#137500e", bean);
/*  608 */     bean.setBillform(getBillFormEditor());
/*  609 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  610 */     invokeInitializingBean(bean);
/*  611 */     return bean;
/*      */   }
/*      */   
/*  614 */   private List getManagedList14() { List list = new java.util.ArrayList();list.add(getCardBodyAfterRowEditHandler_690862());return list;
/*      */   }
/*      */   
/*  617 */   private nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler getCardBodyAfterRowEditHandler_690862() { if (this.context.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#690862") != null)
/*  618 */       return (nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler)this.context.get("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#690862");
/*  619 */     nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler bean = new nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler();
/*  620 */     this.context.put("nc.ui.so.m30.billui.editor.bodyevent.CardBodyAfterRowEditHandler#690862", bean);
/*  621 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  622 */     invokeInitializingBean(bean);
/*  623 */     return bean;
/*      */   }
/*      */   
/*  626 */   private List getManagedList15() { List list = new java.util.ArrayList();list.add(getOrgEditHandler_1a55e3e());return list;
/*      */   }
/*      */   
/*  629 */   private nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler getOrgEditHandler_1a55e3e() { if (this.context.get("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#1a55e3e") != null)
/*  630 */       return (nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler)this.context.get("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#1a55e3e");
/*  631 */     nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler bean = new nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler(getBillFormEditor(), getContext());this.context.put("nc.ui.so.m30.billui.editor.orgevent.OrgEditHandler#1a55e3e", bean);
/*  632 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  633 */     invokeInitializingBean(bean);
/*  634 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.TangramContainer getContainer() {
/*  638 */     if (this.context.get("container") != null)
/*  639 */       return (nc.ui.uif2.TangramContainer)this.context.get("container");
/*  640 */     nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
/*  641 */     this.context.put("container", bean);
/*  642 */     bean.setModel(getManageAppModel());
/*  643 */     bean.setTangramLayoutRoot(getTBNode_87abd8());
/*  644 */     bean.initUI();
/*  645 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  646 */     invokeInitializingBean(bean);
/*  647 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_87abd8() {
/*  651 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.TBNode#87abd8") != null)
/*  652 */       return (nc.ui.uif2.tangramlayout.node.TBNode)this.context.get("nc.ui.uif2.tangramlayout.node.TBNode#87abd8");
/*  653 */     nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
/*  654 */     this.context.put("nc.ui.uif2.tangramlayout.node.TBNode#87abd8", bean);
/*  655 */     bean.setShowMode("CardLayout");
/*  656 */     bean.setTabs(getManagedList16());
/*  657 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  658 */     invokeInitializingBean(bean);
/*  659 */     return bean;
/*      */   }
/*      */   
/*  662 */   private List getManagedList16() { List list = new java.util.ArrayList();list.add(getHSNode_194b891());list.add(getHSNode_9e9211());return list;
/*      */   }
/*      */   
/*  665 */   private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_194b891() { if (this.context.get("nc.ui.uif2.tangramlayout.node.HSNode#194b891") != null)
/*  666 */       return (nc.ui.uif2.tangramlayout.node.HSNode)this.context.get("nc.ui.uif2.tangramlayout.node.HSNode#194b891");
/*  667 */     nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
/*  668 */     this.context.put("nc.ui.uif2.tangramlayout.node.HSNode#194b891", bean);
/*  669 */     bean.setLeft(getCNode_1ce4cc7());
/*  670 */     bean.setRight(getVSNode_1556db1());
/*  671 */     bean.setDividerLocation(0.22F);
/*  672 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  673 */     invokeInitializingBean(bean);
/*  674 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_1ce4cc7() {
/*  678 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#1ce4cc7") != null)
/*  679 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#1ce4cc7");
/*  680 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  681 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#1ce4cc7", bean);
/*  682 */     bean.setComponent(getQueryArea());
/*  683 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  684 */     invokeInitializingBean(bean);
/*  685 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_1556db1() {
/*  689 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#1556db1") != null)
/*  690 */       return (nc.ui.uif2.tangramlayout.node.VSNode)this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#1556db1");
/*  691 */     nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
/*  692 */     this.context.put("nc.ui.uif2.tangramlayout.node.VSNode#1556db1", bean);
/*  693 */     bean.setUp(getCNode_112cae0());
/*  694 */     bean.setDown(getCNode_191607c());
/*  695 */     bean.setDividerLocation(25.0F);
/*  696 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  697 */     invokeInitializingBean(bean);
/*  698 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_112cae0() {
/*  702 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#112cae0") != null)
/*  703 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#112cae0");
/*  704 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  705 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#112cae0", bean);
/*  706 */     bean.setComponent(getListToolbarPnl());
/*  707 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  708 */     invokeInitializingBean(bean);
/*  709 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_191607c() {
/*  713 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#191607c") != null)
/*  714 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#191607c");
/*  715 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  716 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#191607c", bean);
/*  717 */     bean.setName(getI18nFB_c57532());
/*  718 */     bean.setComponent(getListView());
/*  719 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  720 */     invokeInitializingBean(bean);
/*  721 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_c57532() {
/*  725 */     if (this.context.get("nc.ui.uif2.I18nFB#c57532") != null)
/*  726 */       return (String)this.context.get("nc.ui.uif2.I18nFB#c57532");
/*  727 */     I18nFB bean = new I18nFB();
/*  728 */     this.context.put("&nc.ui.uif2.I18nFB#c57532", bean);bean.setResDir("common");
/*  729 */     bean.setResId("UC001-0000107");
/*  730 */     bean.setDefaultValue("列表");
/*  731 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  732 */     invokeInitializingBean(bean);
/*      */     try {
/*  734 */       Object product = bean.getObject();
/*  735 */       this.context.put("nc.ui.uif2.I18nFB#c57532", product);
/*  736 */       return (String)product;
/*      */     } catch (Exception e) {
/*  738 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/*  741 */   private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_9e9211() { if (this.context.get("nc.ui.uif2.tangramlayout.node.HSNode#9e9211") != null)
/*  742 */       return (nc.ui.uif2.tangramlayout.node.HSNode)this.context.get("nc.ui.uif2.tangramlayout.node.HSNode#9e9211");
/*  743 */     nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
/*  744 */     this.context.put("nc.ui.uif2.tangramlayout.node.HSNode#9e9211", bean);
/*  745 */     bean.setLeft(getVSNode_fcdb79());
/*  746 */     bean.setRight(getCNode_ad1293());
/*  747 */     bean.setDividerLocation(0.8F);
/*  748 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  749 */     invokeInitializingBean(bean);
/*  750 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_fcdb79() {
/*  754 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#fcdb79") != null)
/*  755 */       return (nc.ui.uif2.tangramlayout.node.VSNode)this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#fcdb79");
/*  756 */     nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
/*  757 */     this.context.put("nc.ui.uif2.tangramlayout.node.VSNode#fcdb79", bean);
/*  758 */     bean.setUp(getCNode_e1616e());
/*  759 */     bean.setDown(getCNode_5d7a20());
/*  760 */     bean.setDividerLocation(30.0F);
/*  761 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  762 */     invokeInitializingBean(bean);
/*  763 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_e1616e() {
/*  767 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#e1616e") != null)
/*  768 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#e1616e");
/*  769 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  770 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#e1616e", bean);
/*  771 */     bean.setComponent(getCardToolbarPnl());
/*  772 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  773 */     invokeInitializingBean(bean);
/*  774 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_5d7a20() {
/*  778 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#5d7a20") != null)
/*  779 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#5d7a20");
/*  780 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  781 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#5d7a20", bean);
/*  782 */     bean.setComponent(getBillFormEditor());
/*  783 */     bean.setName(getI18nFB_c3bbc8());
/*  784 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  785 */     invokeInitializingBean(bean);
/*  786 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_c3bbc8() {
/*  790 */     if (this.context.get("nc.ui.uif2.I18nFB#c3bbc8") != null)
/*  791 */       return (String)this.context.get("nc.ui.uif2.I18nFB#c3bbc8");
/*  792 */     I18nFB bean = new I18nFB();
/*  793 */     this.context.put("&nc.ui.uif2.I18nFB#c3bbc8", bean);bean.setResDir("common");
/*  794 */     bean.setResId("UC001-0000106");
/*  795 */     bean.setDefaultValue("卡片");
/*  796 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  797 */     invokeInitializingBean(bean);
/*      */     try {
/*  799 */       Object product = bean.getObject();
/*  800 */       this.context.put("nc.ui.uif2.I18nFB#c3bbc8", product);
/*  801 */       return (String)product;
/*      */     } catch (Exception e) {
/*  803 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/*  806 */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_ad1293() { if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#ad1293") != null)
/*  807 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#ad1293");
/*  808 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  809 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#ad1293", bean);
/*  810 */     bean.setComponent(getBesidewidget());
/*  811 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  812 */     invokeInitializingBean(bean);
/*  813 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea() {
/*  817 */     if (this.context.get("queryArea") != null)
/*  818 */       return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)this.context.get("queryArea");
/*  819 */     nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
/*  820 */     this.context.put("queryArea", bean);
/*  821 */     bean.setQueryAreaCreator(getQueryAction());
/*  822 */     bean.initUI();
/*  823 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  824 */     invokeInitializingBean(bean);
/*  825 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getListToolbarPnl() {
/*  829 */     if (this.context.get("listToolbarPnl") != null)
/*  830 */       return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)this.context.get("listToolbarPnl");
/*  831 */     nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
/*  832 */     this.context.put("listToolbarPnl", bean);
/*  833 */     bean.setModel(getManageAppModel());
/*  834 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  835 */     invokeInitializingBean(bean);
/*  836 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel getCardToolbarPnl() {
/*  840 */     if (this.context.get("cardToolbarPnl") != null)
/*  841 */       return (nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel)this.context.get("cardToolbarPnl");
/*  842 */     nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel bean = new nc.ui.so.m30.billui.view.SaleOrderUECardLayoutToolbarPanel();
/*  843 */     this.context.put("cardToolbarPnl", bean);
/*  844 */     bean.setActions(getManagedList17());
/*  845 */     bean.setTitleAction(getReturnaction());
/*  846 */     bean.setModel(getManageAppModel());
/*  847 */     bean.setRightExActions(getManagedList18());
/*  848 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  849 */     invokeInitializingBean(bean);
/*  850 */     return bean;
/*      */   }
/*      */   
/*  853 */   private List getManagedList17() { List list = new java.util.ArrayList();list.add(getFirstLineAction());list.add(getPreLineAction());list.add(getNextLineAction());list.add(getLastLineAction());return list;
/*      */   }
/*      */   
/*  856 */   private nc.ui.uif2.actions.FirstLineAction getFirstLineAction() { if (this.context.get("firstLineAction") != null)
/*  857 */       return (nc.ui.uif2.actions.FirstLineAction)this.context.get("firstLineAction");
/*  858 */     nc.ui.uif2.actions.FirstLineAction bean = new nc.ui.uif2.actions.FirstLineAction();
/*  859 */     this.context.put("firstLineAction", bean);
/*  860 */     bean.setModel(getManageAppModel());
/*  861 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  862 */     invokeInitializingBean(bean);
/*  863 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.actions.PreLineAction getPreLineAction() {
/*  867 */     if (this.context.get("preLineAction") != null)
/*  868 */       return (nc.ui.uif2.actions.PreLineAction)this.context.get("preLineAction");
/*  869 */     nc.ui.uif2.actions.PreLineAction bean = new nc.ui.uif2.actions.PreLineAction();
/*  870 */     this.context.put("preLineAction", bean);
/*  871 */     bean.setModel(getManageAppModel());
/*  872 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  873 */     invokeInitializingBean(bean);
/*  874 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.actions.NextLineAction getNextLineAction() {
/*  878 */     if (this.context.get("nextLineAction") != null)
/*  879 */       return (nc.ui.uif2.actions.NextLineAction)this.context.get("nextLineAction");
/*  880 */     nc.ui.uif2.actions.NextLineAction bean = new nc.ui.uif2.actions.NextLineAction();
/*  881 */     this.context.put("nextLineAction", bean);
/*  882 */     bean.setModel(getManageAppModel());
/*  883 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  884 */     invokeInitializingBean(bean);
/*  885 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.actions.LastLineAction getLastLineAction() {
/*  889 */     if (this.context.get("lastLineAction") != null)
/*  890 */       return (nc.ui.uif2.actions.LastLineAction)this.context.get("lastLineAction");
/*  891 */     nc.ui.uif2.actions.LastLineAction bean = new nc.ui.uif2.actions.LastLineAction();
/*  892 */     this.context.put("lastLineAction", bean);
/*  893 */     bean.setModel(getManageAppModel());
/*  894 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  895 */     invokeInitializingBean(bean);
/*  896 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction() {
/*  900 */     if (this.context.get("returnaction") != null)
/*  901 */       return (nc.ui.pubapp.uif2app.actions.UEReturnAction)this.context.get("returnaction");
/*  902 */     nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
/*  903 */     this.context.put("returnaction", bean);
/*  904 */     bean.setGoComponent(getListView());
/*  905 */     bean.setSaveAction(getSaveAction());
/*  906 */     bean.setModel(getManageAppModel());
/*  907 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  908 */     invokeInitializingBean(bean);
/*  909 */     return bean;
/*      */   }
/*      */   
/*  912 */   private List getManagedList18() { List list = new java.util.ArrayList();list.add(getActionsBar_ActionsBarSeparator_1231f50());list.add(getHeadZoomAction());return list;
/*      */   }
/*      */   
/*  915 */   private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1231f50() { if (this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1231f50") != null)
/*  916 */       return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1231f50");
/*  917 */     nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
/*  918 */     this.context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1231f50", bean);
/*  919 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  920 */     invokeInitializingBean(bean);
/*  921 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction() {
/*  925 */     if (this.context.get("headZoomAction") != null)
/*  926 */       return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)this.context.get("headZoomAction");
/*  927 */     nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
/*  928 */     this.context.put("headZoomAction", bean);
/*  929 */     bean.setBillForm(getBillFormEditor());
/*  930 */     bean.setModel(getManageAppModel());
/*  931 */     bean.setPos(0);
/*  932 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  933 */     invokeInitializingBean(bean);
/*  934 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.components.widget.BesideWidget getBesidewidget() {
/*  938 */     if (this.context.get("besidewidget") != null)
/*  939 */       return (nc.ui.uif2.components.widget.BesideWidget)this.context.get("besidewidget");
/*  940 */     nc.ui.uif2.components.widget.BesideWidget bean = new nc.ui.uif2.components.widget.BesideWidget();
/*  941 */     this.context.put("besidewidget", bean);
/*  942 */     bean.setBesideWidgetlets(getManagedList19());
/*  943 */     bean.setContext(getContext());
/*  944 */     bean.setShowAllAction(getShowAllAction());
/*  945 */     bean.initUI();
/*  946 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  947 */     invokeInitializingBean(bean);
/*  948 */     return bean;
/*      */   }
/*      */   
/*  951 */   private List getManagedList19() { List list = new java.util.ArrayList();list.add(getCreditSideForm());list.add(getPaySideForm());list.add(getATPSideForm());return list;
/*      */   }
/*      */   
/*  954 */   public nc.ui.so.m30.billui.view.sideform.ShowAllAction getShowAllAction() { if (this.context.get("showAllAction") != null)
/*  955 */       return (nc.ui.so.m30.billui.view.sideform.ShowAllAction)this.context.get("showAllAction");
/*  956 */     nc.ui.so.m30.billui.view.sideform.ShowAllAction bean = new nc.ui.so.m30.billui.view.sideform.ShowAllAction();
/*  957 */     this.context.put("showAllAction", bean);
/*  958 */     bean.setMediator(getSideFormMediator());
/*  959 */     bean.setExceptionHandler(getExceptionHandler());
/*  960 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  961 */     invokeInitializingBean(bean);
/*  962 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.view.sideform.CreditSideForm getCreditSideForm() {
/*  966 */     if (this.context.get("creditSideForm") != null)
/*  967 */       return (nc.ui.so.m30.billui.view.sideform.CreditSideForm)this.context.get("creditSideForm");
/*  968 */     nc.ui.so.m30.billui.view.sideform.CreditSideForm bean = new nc.ui.so.m30.billui.view.sideform.CreditSideForm();
/*  969 */     this.context.put("creditSideForm", bean);
/*  970 */     bean.setBillForm(getBillFormEditor());
/*  971 */     bean.setExceptionHandler(getExceptionHandler());
/*  972 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  973 */     invokeInitializingBean(bean);
/*  974 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.view.sideform.PaySideForm getPaySideForm() {
/*  978 */     if (this.context.get("paySideForm") != null)
/*  979 */       return (nc.ui.so.m30.billui.view.sideform.PaySideForm)this.context.get("paySideForm");
/*  980 */     nc.ui.so.m30.billui.view.sideform.PaySideForm bean = new nc.ui.so.m30.billui.view.sideform.PaySideForm();
/*  981 */     this.context.put("paySideForm", bean);
/*  982 */     bean.setBillForm(getBillFormEditor());
/*  983 */     bean.setExceptionHandler(getExceptionHandler());
/*  984 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  985 */     invokeInitializingBean(bean);
/*  986 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.view.sideform.ATPSideForm getATPSideForm() {
/*  990 */     if (this.context.get("ATPSideForm") != null)
/*  991 */       return (nc.ui.so.m30.billui.view.sideform.ATPSideForm)this.context.get("ATPSideForm");
/*  992 */     nc.ui.so.m30.billui.view.sideform.ATPSideForm bean = new nc.ui.so.m30.billui.view.sideform.ATPSideForm();
/*  993 */     this.context.put("ATPSideForm", bean);
/*  994 */     bean.setBillForm(getBillFormEditor());
/*  995 */     bean.setExceptionHandler(getExceptionHandler());
/*  996 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  997 */     invokeInitializingBean(bean);
/*  998 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler() {
/* 1002 */     if (this.context.get("exceptionHandler") != null)
/* 1003 */       return (nc.ui.uif2.DefaultExceptionHanler)this.context.get("exceptionHandler");
/* 1004 */     nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler();
/* 1005 */     this.context.put("exceptionHandler", bean);
/* 1006 */     bean.setContext(getContext());
/* 1007 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1008 */     invokeInitializingBean(bean);
/* 1009 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator getSideFormMediator() {
/* 1013 */     if (this.context.get("sideFormMediator") != null)
/* 1014 */       return (nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator)this.context.get("sideFormMediator");
/* 1015 */     nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator bean = new nc.ui.so.m30.billui.view.sideform.SaleOrderSideFormMediator();
/* 1016 */     this.context.put("sideFormMediator", bean);
/* 1017 */     bean.setModel(getManageAppModel());
/* 1018 */     bean.setCreditSideForm(getCreditSideForm());
/* 1019 */     bean.setPaySideForm(getPaySideForm());
/* 1020 */     bean.setAtpSideForm(getATPSideForm());
/* 1021 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1022 */     invokeInitializingBean(bean);
/* 1023 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
/* 1027 */     if (this.context.get("toftpanelActionContributors") != null)
/* 1028 */       return (nc.ui.uif2.actions.ActionContributors)this.context.get("toftpanelActionContributors");
/* 1029 */     nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
/* 1030 */     this.context.put("toftpanelActionContributors", bean);
/* 1031 */     bean.setContributors(getManagedList20());
/* 1032 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1033 */     invokeInitializingBean(bean);
/* 1034 */     return bean;
/*      */   }
/*      */   
/* 1037 */   private List getManagedList20() { List list = new java.util.ArrayList();list.add(getActionsOfList());list.add(getActionsOfCard());return list;
/*      */   }
/*      */   
/* 1040 */   public nc.ui.so.m30.billui.action.SaleOrderOffsetAction getOffsetAction() { if (this.context.get("offsetAction") != null)
/* 1041 */       return (nc.ui.so.m30.billui.action.SaleOrderOffsetAction)this.context.get("offsetAction");
/* 1042 */     nc.ui.so.m30.billui.action.SaleOrderOffsetAction bean = new nc.ui.so.m30.billui.action.SaleOrderOffsetAction();
/* 1043 */     this.context.put("offsetAction", bean);
/* 1044 */     bean.setModel(getManageAppModel());
/* 1045 */     bean.setEditor(getBillFormEditor());
/* 1046 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1047 */     invokeInitializingBean(bean);
/* 1048 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction getUnoffsetAction() {
/* 1052 */     if (this.context.get("unoffsetAction") != null)
/* 1053 */       return (nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction)this.context.get("unoffsetAction");
/* 1054 */     nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction bean = new nc.ui.so.m30.billui.action.SaleOrderUnOffsetAction();
/* 1055 */     this.context.put("unoffsetAction", bean);
/* 1056 */     bean.setModel(getManageAppModel());
/* 1057 */     bean.setEditor(getBillFormEditor());
/* 1058 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1059 */     invokeInitializingBean(bean);
/* 1060 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderAddAction getAddManualAction() {
/* 1064 */     if (this.context.get("addManualAction") != null)
/* 1065 */       return (nc.ui.so.m30.billui.action.SaleOrderAddAction)this.context.get("addManualAction");
/* 1066 */     nc.ui.so.m30.billui.action.SaleOrderAddAction bean = new nc.ui.so.m30.billui.action.SaleOrderAddAction();
/* 1067 */     this.context.put("addManualAction", bean);
/* 1068 */     bean.setSourceBillType("MANUAL");
/* 1069 */     bean.setSourceBillName(getI18nFB_dcd76a());
/* 1070 */     bean.setModel(getManageAppModel());
/* 1071 */     bean.setEditor(getBillFormEditor());
/* 1072 */     bean.setInterceptor(getFormInterceptor());
/* 1073 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1074 */     invokeInitializingBean(bean);
/* 1075 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_dcd76a() {
/* 1079 */     if (this.context.get("nc.ui.uif2.I18nFB#dcd76a") != null)
/* 1080 */       return (String)this.context.get("nc.ui.uif2.I18nFB#dcd76a");
/* 1081 */     I18nFB bean = new I18nFB();
/* 1082 */     this.context.put("&nc.ui.uif2.I18nFB#dcd76a", bean);bean.setResDir("4006011_0");
/* 1083 */     bean.setResId("04006011-0393");
/* 1084 */     bean.setDefaultValue("自制");
/* 1085 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1086 */     invokeInitializingBean(bean);
/*      */     try {
/* 1088 */       Object product = bean.getObject();
/* 1089 */       this.context.put("nc.ui.uif2.I18nFB#dcd76a", product);
/* 1090 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1092 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1095 */   public nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action getAddZ3Action() { if (this.context.get("addZ3Action") != null)
/* 1096 */       return (nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action)this.context.get("addZ3Action");
/* 1097 */     nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFromZ3Action();
/* 1098 */     this.context.put("addZ3Action", bean);
/* 1099 */     bean.setSourceBillType("Z3");
/* 1100 */     bean.setSourceBillName(getI18nFB_c2947e());
/* 1101 */     bean.setFlowBillType(false);
/* 1102 */     bean.setModel(getManageAppModel());
/* 1103 */     bean.setEditor(getBillFormEditor());
/* 1104 */     bean.setTransferViewProcessor(getTransferProcessorforZ3());
/* 1105 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1106 */     invokeInitializingBean(bean);
/* 1107 */     return bean;
/*      */   }

			public nc.ui.so.m30.billui.action.SaleOrderAddFromFQ01Action getAddFQ01Action() { 
			   
			   if (this.context.get("addFQ01Action") != null)
/* 1096 */       return (nc.ui.so.m30.billui.action.SaleOrderAddFromFQ01Action)this.context.get("addFQ01Action");
/* 1097 */     nc.ui.so.m30.billui.action.SaleOrderAddFromFQ01Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFromFQ01Action();
/* 1098 */     this.context.put("addFQ01Action", bean);
/* 1099 */     bean.setSourceBillType("FQ01");
/* 1100 */     bean.setSourceBillName("发货申请单");
/* 1101 */     bean.setFlowBillType(false);
/* 1102 */     bean.setModel(getManageAppModel());
/* 1103 */     bean.setEditor(getBillFormEditor());
/* 1104 */     bean.setTransferViewProcessor(getTransferProcessorforFQ());
/* 1105 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1106 */     invokeInitializingBean(bean);
/* 1107 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_c2947e() {
/* 1111 */     if (this.context.get("nc.ui.uif2.I18nFB#c2947e") != null)
/* 1112 */       return (String)this.context.get("nc.ui.uif2.I18nFB#c2947e");
/* 1113 */     I18nFB bean = new I18nFB();
/* 1114 */     this.context.put("&nc.ui.uif2.I18nFB#c2947e", bean);bean.setResDir("4006011_0");
/* 1115 */     bean.setResId("04006011-0394");
/* 1116 */     bean.setDefaultValue("销售合同");
/* 1117 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1118 */     invokeInitializingBean(bean);
/*      */     try {
/* 1120 */       Object product = bean.getObject();
/* 1121 */       this.context.put("nc.ui.uif2.I18nFB#c2947e", product);
/* 1122 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1124 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1127 */   public nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic getTransferLogicforZ3() { if (this.context.get("transferLogicforZ3") != null)
/* 1128 */       return (nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic)this.context.get("transferLogicforZ3");
/* 1129 */     nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30RefZ3TransferBillDataLogic();
/* 1130 */     this.context.put("transferLogicforZ3", bean);
/* 1131 */     bean.setBillForm(getBillFormEditor());
/* 1132 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1133 */     invokeInitializingBean(bean);
/* 1134 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforZ3() {
/* 1138 */     if (this.context.get("transferProcessorforZ3") != null)
/* 1139 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorforZ3");
/* 1140 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 1141 */     this.context.put("transferProcessorforZ3", bean);
/* 1142 */     bean.setList(getListView());
/* 1143 */     bean.setActionContainer(getActionsOfList());
/* 1144 */     bean.setCardActionContainer(getActionsOfCard());
/* 1145 */     bean.setTransferLogic(getTransferLogicforZ3());
/* 1146 */     bean.setBillForm(getBillFormEditor());
/* 1147 */     bean.setCancelAction(getCancelAction());
/* 1148 */     bean.setSaveAction(getSaveAction());
/* 1149 */     bean.setCommitAction(getSendApproveAction());
/* 1150 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1151 */     invokeInitializingBean(bean);
/* 1152 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action getAdd4310Action() {
/* 1156 */     if (this.context.get("add4310Action") != null)
/* 1157 */       return (nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action)this.context.get("add4310Action");
/* 1158 */     nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom4310Action();
/* 1159 */     this.context.put("add4310Action", bean);
/* 1160 */     bean.setSourceBillType("4310");
/* 1161 */     bean.setSourceBillName(getI18nFB_a30390());
/* 1162 */     bean.setFlowBillType(false);
/* 1163 */     bean.setModel(getManageAppModel());
/* 1164 */     bean.setEditor(getBillFormEditor());
/* 1165 */     bean.setTransferViewProcessor(getTransferProcessorfor4310());
/* 1166 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1167 */     invokeInitializingBean(bean);
/* 1168 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_a30390() {
/* 1172 */     if (this.context.get("nc.ui.uif2.I18nFB#a30390") != null)
/* 1173 */       return (String)this.context.get("nc.ui.uif2.I18nFB#a30390");
/* 1174 */     I18nFB bean = new I18nFB();
/* 1175 */     this.context.put("&nc.ui.uif2.I18nFB#a30390", bean);bean.setResDir("4006011_0");
/* 1176 */     bean.setResId("04006011-0395");
/* 1177 */     bean.setDefaultValue("销售报价单");
/* 1178 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1179 */     invokeInitializingBean(bean);
/*      */     try {
/* 1181 */       Object product = bean.getObject();
/* 1182 */       this.context.put("nc.ui.uif2.I18nFB#a30390", product);
/* 1183 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1185 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1188 */   public nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic getTransferLogicfor4310() { if (this.context.get("transferLogicfor4310") != null)
/* 1189 */       return (nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic)this.context.get("transferLogicfor4310");
/* 1190 */     nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref4310TransferBillDataLogic();
/* 1191 */     this.context.put("transferLogicfor4310", bean);
/* 1192 */     bean.setBillForm(getBillFormEditor());
/* 1193 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1194 */     invokeInitializingBean(bean);
/* 1195 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor4310() {
/* 1199 */     if (this.context.get("transferProcessorfor4310") != null)
/* 1200 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorfor4310");
/* 1201 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 1202 */     this.context.put("transferProcessorfor4310", bean);
/* 1203 */     bean.setList(getListView());
/* 1204 */     bean.setActionContainer(getActionsOfList());
/* 1205 */     bean.setCardActionContainer(getActionsOfCard());
/* 1206 */     bean.setTransferLogic(getTransferLogicfor4310());
/* 1207 */     bean.setBillForm(getBillFormEditor());
/* 1208 */     bean.setCancelAction(getCancelAction());
/* 1209 */     bean.setCommitAction(getSendApproveAction());
/* 1210 */     bean.setSaveAction(getSaveAction());
/* 1211 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1212 */     invokeInitializingBean(bean);
/* 1213 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action getAdd38Action() {
/* 1217 */     if (this.context.get("add38Action") != null)
/* 1218 */       return (nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action)this.context.get("add38Action");
/* 1219 */     nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom38Action();
/* 1220 */     this.context.put("add38Action", bean);
/* 1221 */     bean.setSourceBillType("38");
/* 1222 */     bean.setSourceBillName(getI18nFB_3f21d6());
/* 1223 */     bean.setFlowBillType(false);
/* 1224 */     bean.setModel(getManageAppModel());
/* 1225 */     bean.setEditor(getBillFormEditor());
/* 1226 */     bean.setTransferViewProcessor(getTransferProcessorfor38());
/* 1227 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1228 */     invokeInitializingBean(bean);
/* 1229 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_3f21d6() {
/* 1233 */     if (this.context.get("nc.ui.uif2.I18nFB#3f21d6") != null)
/* 1234 */       return (String)this.context.get("nc.ui.uif2.I18nFB#3f21d6");
/* 1235 */     I18nFB bean = new I18nFB();
/* 1236 */     this.context.put("&nc.ui.uif2.I18nFB#3f21d6", bean);bean.setResDir("4006011_0");
/* 1237 */     bean.setResId("04006011-0396");
/* 1238 */     bean.setDefaultValue("预订单");
/* 1239 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1240 */     invokeInitializingBean(bean);
/*      */     try {
/* 1242 */       Object product = bean.getObject();
/* 1243 */       this.context.put("nc.ui.uif2.I18nFB#3f21d6", product);
/* 1244 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1246 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1249 */   public nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic getTransferLogicfor38() { if (this.context.get("transferLogicfor38") != null)
/* 1250 */       return (nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic)this.context.get("transferLogicfor38");
/* 1251 */     nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref38TRansferBillDataLogic();
/* 1252 */     this.context.put("transferLogicfor38", bean);
/* 1253 */     bean.setBillForm(getBillFormEditor());
/* 1254 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1255 */     invokeInitializingBean(bean);
/* 1256 */     return bean;
/*      */   }

			public nc.ui.so.m30.billui.tranferbill.M30RefFRTRansferBillDataLogic getTransferLogicforFQ() { 
				if (this.context.get("transferLogicforFQ") != null)
/* 1250 */       return (nc.ui.so.m30.billui.tranferbill.M30RefFRTRansferBillDataLogic)this.context.get("transferLogicforFQ");
/* 1251 */     nc.ui.so.m30.billui.tranferbill.M30RefFRTRansferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30RefFRTRansferBillDataLogic();
/* 1252 */     this.context.put("transferLogicforFQ", bean);
/* 1253 */     bean.setBillForm(getBillFormEditor());
/* 1254 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1255 */     invokeInitializingBean(bean);
/* 1256 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor38() {
/* 1260 */     if (this.context.get("transferProcessorfor38") != null)
/* 1261 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorfor38");
/* 1262 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 1263 */     this.context.put("transferProcessorfor38", bean);
/* 1264 */     bean.setList(getListView());
/* 1265 */     bean.setActionContainer(getActionsOfList());
/* 1266 */     bean.setCardActionContainer(getActionsOfCard());
/* 1267 */     bean.setTransferLogic(getTransferLogicfor38());
/* 1268 */     bean.setBillForm(getBillFormEditor());
/* 1269 */     bean.setCancelAction(getCancelAction());
/* 1270 */     bean.setSaveAction(getSaveAction());
/* 1271 */     bean.setCommitAction(getSendApproveAction());
/* 1272 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1273 */     invokeInitializingBean(bean);
/* 1274 */     return bean;
/*      */   }

			public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforFQ() {
/* 1260 */     if (this.context.get("transferProcessorforFQ") != null)
/* 1261 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorforFQ");
/* 1262 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 1263 */     this.context.put("transferProcessorforFQ", bean);
/* 1264 */     bean.setList(getListView());
/* 1265 */     bean.setActionContainer(getActionsOfList());
/* 1266 */     bean.setCardActionContainer(getActionsOfCard());
/* 1267 */     bean.setTransferLogic(getTransferLogicforFQ());
/* 1268 */     bean.setBillForm(getBillFormEditor());
/* 1269 */     bean.setCancelAction(getCancelAction());
/* 1270 */     bean.setSaveAction(getSaveAction());
/* 1271 */     bean.setCommitAction(getSendApproveAction());
/* 1272 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1273 */     invokeInitializingBean(bean);
/* 1274 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction getAdd4HAction() {
/* 1278 */     if (this.context.get("add4HAction") != null)
/* 1279 */       return (nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction)this.context.get("add4HAction");
/* 1280 */     nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction bean = new nc.ui.so.m30.billui.action.SaleOrderAddFrom4HAction();
/* 1281 */     this.context.put("add4HAction", bean);
/* 1282 */     bean.setSourceBillType("4H");
/* 1283 */     bean.setSourceBillName(getI18nFB_c69c04());
/* 1284 */     bean.setFlowBillType(false);
/* 1285 */     bean.setModel(getManageAppModel());
/* 1286 */     bean.setEditor(getBillFormEditor());
/* 1287 */     bean.setTransferViewProcessor(getTransferProcessorfor4H());
/* 1288 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1289 */     invokeInitializingBean(bean);
/* 1290 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_c69c04() {
/* 1294 */     if (this.context.get("nc.ui.uif2.I18nFB#c69c04") != null)
/* 1295 */       return (String)this.context.get("nc.ui.uif2.I18nFB#c69c04");
/* 1296 */     I18nFB bean = new I18nFB();
/* 1297 */     this.context.put("&nc.ui.uif2.I18nFB#c69c04", bean);bean.setResDir("4006011_0");
/* 1298 */     bean.setResId("04006011-0397");
/* 1299 */     bean.setDefaultValue("库存借出单");
/* 1300 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1301 */     invokeInitializingBean(bean);
/*      */     try {
/* 1303 */       Object product = bean.getObject();
/* 1304 */       this.context.put("nc.ui.uif2.I18nFB#c69c04", product);
/* 1305 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1307 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1310 */   public nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic getTransferLogicfor4H() { if (this.context.get("transferLogicfor4H") != null)
/* 1311 */       return (nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic)this.context.get("transferLogicfor4H");
/* 1312 */     nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref4HTransferBillDataLogic();
/* 1313 */     this.context.put("transferLogicfor4H", bean);
/* 1314 */     bean.setBillForm(getBillFormEditor());
/* 1315 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1316 */     invokeInitializingBean(bean);
/* 1317 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorfor4H() {
/* 1321 */     if (this.context.get("transferProcessorfor4H") != null)
/* 1322 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorfor4H");
/* 1323 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 1324 */     this.context.put("transferProcessorfor4H", bean);
/* 1325 */     bean.setList(getListView());
/* 1326 */     bean.setActionContainer(getActionsOfList());
/* 1327 */     bean.setCardActionContainer(getActionsOfCard());
/* 1328 */     bean.setTransferLogic(getTransferLogicfor4H());
/* 1329 */     bean.setBillForm(getBillFormEditor());
/* 1330 */     bean.setCancelAction(getCancelAction());
/* 1331 */     bean.setSaveAction(getSaveAction());
/* 1332 */     bean.setCommitAction(getSendApproveAction());
/* 1333 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1334 */     invokeInitializingBean(bean);
/* 1335 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderEditAction getEditAction() {
/* 1339 */     if (this.context.get("editAction") != null)
/* 1340 */       return (nc.ui.so.m30.billui.action.SaleOrderEditAction)this.context.get("editAction");
/* 1341 */     nc.ui.so.m30.billui.action.SaleOrderEditAction bean = new nc.ui.so.m30.billui.action.SaleOrderEditAction();
/* 1342 */     this.context.put("editAction", bean);
/* 1343 */     bean.setModel(getManageAppModel());
/* 1344 */     bean.setEditor(getBillFormEditor());
/* 1345 */     bean.setInterceptor(getFormInterceptor());
/* 1346 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1347 */     invokeInitializingBean(bean);
/* 1348 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderDeleteAction getDeleteAction() {
/* 1352 */     if (this.context.get("deleteAction") != null)
/* 1353 */       return (nc.ui.so.m30.billui.action.SaleOrderDeleteAction)this.context.get("deleteAction");
/* 1354 */     nc.ui.so.m30.billui.action.SaleOrderDeleteAction bean = new nc.ui.so.m30.billui.action.SaleOrderDeleteAction();
/* 1355 */     this.context.put("deleteAction", bean);
/* 1356 */     bean.setModel(getManageAppModel());
/* 1357 */     bean.setEditor(getBillFormEditor());
/* 1358 */     bean.setActionName("DELETE");
/* 1359 */     bean.setBillType("30");
/* 1360 */     bean.setValidationService(getPowerdeletevalidservice());
/* 1361 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1362 */     invokeInitializingBean(bean);
/* 1363 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerdeletevalidservice() {
/* 1367 */     if (this.context.get("powerdeletevalidservice") != null)
/* 1368 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerdeletevalidservice");
/* 1369 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1370 */     this.context.put("powerdeletevalidservice", bean);
/* 1371 */     bean.setActionCode("delete");
/* 1372 */     bean.setBillCodeFiledName("vbillcode");
/* 1373 */     bean.setPermissionCode("30");
/* 1374 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1375 */     invokeInitializingBean(bean);
/* 1376 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderSaveAction getSaveAction() {
/* 1380 */     if (this.context.get("saveAction") != null)
/* 1381 */       return (nc.ui.so.m30.billui.action.SaleOrderSaveAction)this.context.get("saveAction");
/* 1382 */     nc.ui.so.m30.billui.action.SaleOrderSaveAction bean = new nc.ui.so.m30.billui.action.SaleOrderSaveAction();
/* 1383 */     this.context.put("saveAction", bean);
/* 1384 */     bean.setModel(getManageAppModel());
/* 1385 */     bean.setEditor(getBillFormEditor());
/* 1386 */     bean.setActionName("WRITE");
/* 1387 */     bean.setBillType("30");
/* 1388 */     bean.setRefreshAction(getCardRefreshAction());
/* 1389 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1390 */     invokeInitializingBean(bean);
/* 1391 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderCancelAction getCancelAction() {
/* 1395 */     if (this.context.get("cancelAction") != null)
/* 1396 */       return (nc.ui.so.m30.billui.action.SaleOrderCancelAction)this.context.get("cancelAction");
/* 1397 */     nc.ui.so.m30.billui.action.SaleOrderCancelAction bean = new nc.ui.so.m30.billui.action.SaleOrderCancelAction();
/* 1398 */     this.context.put("cancelAction", bean);
/* 1399 */     bean.setModel(getManageAppModel());
/* 1400 */     bean.setEditor(getBillFormEditor());
/* 1401 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1402 */     invokeInitializingBean(bean);
/* 1403 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getListRefreshAction() {
/* 1407 */     if (this.context.get("listRefreshAction") != null)
/* 1408 */       return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)this.context.get("listRefreshAction");
/* 1409 */     nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
/* 1410 */     this.context.put("listRefreshAction", bean);
/* 1411 */     bean.setDataManager(getModelDataManager());
/* 1412 */     bean.setModel(getManageAppModel());
/* 1413 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1414 */     invokeInitializingBean(bean);
/* 1415 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
/* 1419 */     if (this.context.get("cardRefreshAction") != null)
/* 1420 */       return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)this.context.get("cardRefreshAction");
/* 1421 */     nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
/* 1422 */     this.context.put("cardRefreshAction", bean);
/* 1423 */     bean.setModel(getManageAppModel());
/* 1424 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1425 */     invokeInitializingBean(bean);
/* 1426 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.GroupAction getSendActionGroup() {
/* 1430 */     if (this.context.get("sendActionGroup") != null)
/* 1431 */       return (nc.funcnode.ui.action.GroupAction)this.context.get("sendActionGroup");
/* 1432 */     nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
/* 1433 */     this.context.put("sendActionGroup", bean);
/* 1434 */     bean.setActions(getManagedList21());
/* 1435 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1436 */     invokeInitializingBean(bean);
/* 1437 */     return bean;
/*      */   }
/*      */   
/* 1440 */   private List getManagedList21() { List list = new java.util.ArrayList();list.add(getSendApproveAction());list.add(getUnsendApproveAction());return list;
/*      */   }
/*      */   
/* 1443 */   public nc.ui.so.m30.billui.action.SaleOrderSendApproveAction getSendApproveAction() { if (this.context.get("sendApproveAction") != null)
/* 1444 */       return (nc.ui.so.m30.billui.action.SaleOrderSendApproveAction)this.context.get("sendApproveAction");
/* 1445 */     nc.ui.so.m30.billui.action.SaleOrderSendApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderSendApproveAction();
/* 1446 */     this.context.put("sendApproveAction", bean);
/* 1447 */     bean.setModel(getManageAppModel());
/* 1448 */     bean.setEditor(getBillFormEditor());
/* 1449 */     bean.setFilledUpInFlow(true);
/* 1450 */     bean.setActionName("SAVE");
/* 1451 */     bean.setBillType("30");
/* 1452 */     bean.setValidationService(getPowersendappvalidservice());
/* 1453 */     bean.setTpaProgressUtil(getTpaprogressutil());
/* 1454 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1455 */     invokeInitializingBean(bean);
/* 1456 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.components.progress.TPAProgressUtil getTpaprogressutil() {
/* 1460 */     if (this.context.get("tpaprogressutil") != null)
/* 1461 */       return (nc.ui.uif2.components.progress.TPAProgressUtil)this.context.get("tpaprogressutil");
/* 1462 */     nc.ui.uif2.components.progress.TPAProgressUtil bean = new nc.ui.uif2.components.progress.TPAProgressUtil();
/* 1463 */     this.context.put("tpaprogressutil", bean);
/* 1464 */     bean.setContext(getContext());
/* 1465 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1466 */     invokeInitializingBean(bean);
/* 1467 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction getSaveandsendApproveAction() {
/* 1471 */     if (this.context.get("saveandsendApproveAction") != null)
/* 1472 */       return (nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction)this.context.get("saveandsendApproveAction");
/* 1473 */     nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction bean = new nc.ui.so.m30.billui.action.SaleOrderSaveAndSendAction(getSaveAction(), getSendApproveAction());this.context.put("saveandsendApproveAction", bean);
/* 1474 */     bean.setModel(getManageAppModel());
/* 1475 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1476 */     invokeInitializingBean(bean);
/* 1477 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowersendappvalidservice() {
/* 1481 */     if (this.context.get("powersendappvalidservice") != null)
/* 1482 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powersendappvalidservice");
/* 1483 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1484 */     this.context.put("powersendappvalidservice", bean);
/* 1485 */     bean.setActionCode("commit");
/* 1486 */     bean.setBillCodeFiledName("vbillcode");
/* 1487 */     bean.setPermissionCode("30");
/* 1488 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1489 */     invokeInitializingBean(bean);
/* 1490 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction getUnsendApproveAction() {
/* 1494 */     if (this.context.get("unsendApproveAction") != null)
/* 1495 */       return (nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction)this.context.get("unsendApproveAction");
/* 1496 */     nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderUnSendApproveAction();
/* 1497 */     this.context.put("unsendApproveAction", bean);
/* 1498 */     bean.setModel(getManageAppModel());
/* 1499 */     bean.setEditor(getBillFormEditor());
/* 1500 */     bean.setFilledUpInFlow(true);
/* 1501 */     bean.setActionName("UNSAVE");
/* 1502 */     bean.setBillType("30");
/* 1503 */     bean.setValidationService(getPowerunsendappvalidservice());
/* 1504 */     bean.setTpaProgressUtil(getTpaprogressutil());
/* 1505 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1506 */     invokeInitializingBean(bean);
/* 1507 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerunsendappvalidservice() {
/* 1511 */     if (this.context.get("powerunsendappvalidservice") != null)
/* 1512 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerunsendappvalidservice");
/* 1513 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1514 */     this.context.put("powerunsendappvalidservice", bean);
/* 1515 */     bean.setActionCode("uncommit");
/* 1516 */     bean.setBillCodeFiledName("vbillcode");
/* 1517 */     bean.setPermissionCode("30");
/* 1518 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1519 */     invokeInitializingBean(bean);
/* 1520 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderApproveAction getApproveAction() {
/* 1524 */     if (this.context.get("approveAction") != null)
/* 1525 */       return (nc.ui.so.m30.billui.action.SaleOrderApproveAction)this.context.get("approveAction");
/* 1526 */     nc.ui.so.m30.billui.action.SaleOrderApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderApproveAction();
/* 1527 */     this.context.put("approveAction", bean);
/* 1528 */     bean.setModel(getManageAppModel());
/* 1529 */     bean.setEditor(getBillFormEditor());
/* 1530 */     bean.setFilledUpInFlow(true);
/* 1531 */     bean.setActionName("APPROVE");
/* 1532 */     bean.setBillType("30");
/* 1533 */     bean.setValidationService(getPowerapprovevalidservice());
/* 1534 */     bean.setTpaProgressUtil(getTpaprogressutil());
/* 1535 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1536 */     invokeInitializingBean(bean);
/* 1537 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerapprovevalidservice() {
/* 1541 */     if (this.context.get("powerapprovevalidservice") != null)
/* 1542 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerapprovevalidservice");
/* 1543 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1544 */     this.context.put("powerapprovevalidservice", bean);
/* 1545 */     bean.setActionCode("approve");
/* 1546 */     bean.setBillCodeFiledName("vbillcode");
/* 1547 */     bean.setPermissionCode("30");
/* 1548 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1549 */     invokeInitializingBean(bean);
/* 1550 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderUnApproveAction getUnApproveAction() {
/* 1554 */     if (this.context.get("unApproveAction") != null)
/* 1555 */       return (nc.ui.so.m30.billui.action.SaleOrderUnApproveAction)this.context.get("unApproveAction");
/* 1556 */     nc.ui.so.m30.billui.action.SaleOrderUnApproveAction bean = new nc.ui.so.m30.billui.action.SaleOrderUnApproveAction();
/* 1557 */     this.context.put("unApproveAction", bean);
/* 1558 */     bean.setModel(getManageAppModel());
/* 1559 */     bean.setEditor(getBillFormEditor());
/* 1560 */     bean.setFilledUpInFlow(true);
/* 1561 */     bean.setActionName("UNAPPROVE");
/* 1562 */     bean.setBillType("30");
/* 1563 */     bean.setValidationService(getPowerunapprovevalidservice());
/* 1564 */     bean.setTpaProgressUtil(getTpaprogressutil());
/* 1565 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1566 */     invokeInitializingBean(bean);
/* 1567 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerunapprovevalidservice() {
/* 1571 */     if (this.context.get("powerunapprovevalidservice") != null)
/* 1572 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerunapprovevalidservice");
/* 1573 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1574 */     this.context.put("powerunapprovevalidservice", bean);
/* 1575 */     bean.setActionCode("unapprove");
/* 1576 */     bean.setBillCodeFiledName("vbillcode");
/* 1577 */     bean.setPermissionCode("30");
/* 1578 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1579 */     invokeInitializingBean(bean);
/* 1580 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.GroupAction getApproveActionGroup() {
/* 1584 */     if (this.context.get("approveActionGroup") != null)
/* 1585 */       return (nc.funcnode.ui.action.GroupAction)this.context.get("approveActionGroup");
/* 1586 */     nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
/* 1587 */     this.context.put("approveActionGroup", bean);
/* 1588 */     bean.setActions(getManagedList22());
/* 1589 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1590 */     invokeInitializingBean(bean);
/* 1591 */     return bean;
/*      */   }
/*      */   
/* 1594 */   private List getManagedList22() { List list = new java.util.ArrayList();list.add(getApproveAction());list.add(getUnApproveAction());list.add(getQueryAuditFlowAction());return list;
/*      */   }
/*      */   
/* 1597 */   public nc.ui.so.m30.billui.action.SaleOrderCopyAction getCopyAction() { if (this.context.get("copyAction") != null)
/* 1598 */       return (nc.ui.so.m30.billui.action.SaleOrderCopyAction)this.context.get("copyAction");
/* 1599 */     nc.ui.so.m30.billui.action.SaleOrderCopyAction bean = new nc.ui.so.m30.billui.action.SaleOrderCopyAction();
/* 1600 */     this.context.put("copyAction", bean);
/* 1601 */     bean.setModel(getManageAppModel());
/* 1602 */     bean.setEditor(getBillFormEditor());
/* 1603 */     bean.setInterceptor(getFormInterceptor());
/* 1604 */     bean.setCopyActionProcessor(getCopyActionProcessor_12d0266());
/* 1605 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1606 */     invokeInitializingBean(bean);
/* 1607 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.so.m30.billui.action.CopyActionProcessor getCopyActionProcessor_12d0266() {
/* 1611 */     if (this.context.get("nc.ui.so.m30.billui.action.CopyActionProcessor#12d0266") != null)
/* 1612 */       return (nc.ui.so.m30.billui.action.CopyActionProcessor)this.context.get("nc.ui.so.m30.billui.action.CopyActionProcessor#12d0266");
/* 1613 */     nc.ui.so.m30.billui.action.CopyActionProcessor bean = new nc.ui.so.m30.billui.action.CopyActionProcessor();
/* 1614 */     this.context.put("nc.ui.so.m30.billui.action.CopyActionProcessor#12d0266", bean);
/* 1615 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1616 */     invokeInitializingBean(bean);
/* 1617 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderGatheringAction getGatheringAction() {
/* 1621 */     if (this.context.get("gatheringAction") != null)
/* 1622 */       return (nc.ui.so.m30.billui.action.SaleOrderGatheringAction)this.context.get("gatheringAction");
/* 1623 */     nc.ui.so.m30.billui.action.SaleOrderGatheringAction bean = new nc.ui.so.m30.billui.action.SaleOrderGatheringAction();
/* 1624 */     this.context.put("gatheringAction", bean);
/* 1625 */     bean.setModel(getManageAppModel());
/* 1626 */     bean.setEditor(getBillFormEditor());
/* 1627 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1628 */     invokeInitializingBean(bean);
/* 1629 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction getPayVerifyAction() {
/* 1633 */     if (this.context.get("payVerifyAction") != null)
/* 1634 */       return (nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction)this.context.get("payVerifyAction");
/* 1635 */     nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction bean = new nc.ui.so.m30.billui.action.SaleOrderReceiveVerifyAction();
/* 1636 */     this.context.put("payVerifyAction", bean);
/* 1637 */     bean.setModel(getManageAppModel());
/* 1638 */     bean.setEditor(getBillFormEditor());
/* 1639 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1640 */     invokeInitializingBean(bean);
/* 1641 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderCashSaleAction getSaleOrderCashSaleAction() {
/* 1645 */     if (this.context.get("saleOrderCashSaleAction") != null)
/* 1646 */       return (nc.ui.so.m30.billui.action.SaleOrderCashSaleAction)this.context.get("saleOrderCashSaleAction");
/* 1647 */     nc.ui.so.m30.billui.action.SaleOrderCashSaleAction bean = new nc.ui.so.m30.billui.action.SaleOrderCashSaleAction();
/* 1648 */     this.context.put("saleOrderCashSaleAction", bean);
/* 1649 */     bean.setModel(getManageAppModel());
/* 1650 */     bean.setEditor(getBillFormEditor());
/* 1651 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1652 */     invokeInitializingBean(bean);
/* 1653 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.MenuAction getGatherActionGroup() {
/* 1657 */     if (this.context.get("gatherActionGroup") != null)
/* 1658 */       return (nc.funcnode.ui.action.MenuAction)this.context.get("gatherActionGroup");
/* 1659 */     nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
/* 1660 */     this.context.put("gatherActionGroup", bean);
/* 1661 */     bean.setCode("gathergroup");
/* 1662 */     bean.setName(getI18nFB_1f8a968());
/* 1663 */     bean.setActions(getManagedList23());
/* 1664 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1665 */     invokeInitializingBean(bean);
/* 1666 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_1f8a968() {
/* 1670 */     if (this.context.get("nc.ui.uif2.I18nFB#1f8a968") != null)
/* 1671 */       return (String)this.context.get("nc.ui.uif2.I18nFB#1f8a968");
/* 1672 */     I18nFB bean = new I18nFB();
/* 1673 */     this.context.put("&nc.ui.uif2.I18nFB#1f8a968", bean);bean.setResDir("4006011_0");
/* 1674 */     bean.setResId("04006011-0398");
/* 1675 */     bean.setDefaultValue("订单收款");
/* 1676 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1677 */     invokeInitializingBean(bean);
/*      */     try {
/* 1679 */       Object product = bean.getObject();
/* 1680 */       this.context.put("nc.ui.uif2.I18nFB#1f8a968", product);
/* 1681 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1683 */       throw new RuntimeException(e); } }
/*      */   
/* 1685 */   private List getManagedList23() { List list = new java.util.ArrayList();list.add(getGatheringAction());list.add(getPayVerifyAction());list.add(getSaleOrderCashSaleAction());return list;
/*      */   }
/*      */   
/* 1688 */   public nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction getSendArrangeAction() { if (this.context.get("sendArrangeAction") != null)
/* 1689 */       return (nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction)this.context.get("sendArrangeAction");
/* 1690 */     nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderSendArrangeAction();
/* 1691 */     this.context.put("sendArrangeAction", bean);
/* 1692 */     bean.setModel(getManageAppModel());
/* 1693 */     bean.setEditor(getBillFormEditor());
/* 1694 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1695 */     invokeInitializingBean(bean);
/* 1696 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction getBhArrangeAction() {
/* 1700 */     if (this.context.get("bhArrangeAction") != null)
/* 1701 */       return (nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction)this.context.get("bhArrangeAction");
/* 1702 */     nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderBHArrangeAction();
/* 1703 */     this.context.put("bhArrangeAction", bean);
/* 1704 */     bean.setModel(getManageAppModel());
/* 1705 */     bean.setEditor(getBillFormEditor());
/* 1706 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1707 */     invokeInitializingBean(bean);
/* 1708 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction getZyArrangeAction() {
/* 1712 */     if (this.context.get("zyArrangeAction") != null)
/* 1713 */       return (nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction)this.context.get("zyArrangeAction");
/* 1714 */     nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderZYArrangeAction();
/* 1715 */     this.context.put("zyArrangeAction", bean);
/* 1716 */     bean.setModel(getManageAppModel());
/* 1717 */     bean.setEditor(getBillFormEditor());
/* 1718 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1719 */     invokeInitializingBean(bean);
/* 1720 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.MenuAction getArrangeActionGroup() {
/* 1724 */     if (this.context.get("arrangeActionGroup") != null)
/* 1725 */       return (nc.funcnode.ui.action.MenuAction)this.context.get("arrangeActionGroup");
/* 1726 */     nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
/* 1727 */     this.context.put("arrangeActionGroup", bean);
/* 1728 */     bean.setCode("gt2");
/* 1729 */     bean.setName(getI18nFB_39fb3d());
/* 1730 */     bean.setActions(getManagedList24());
/* 1731 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1732 */     invokeInitializingBean(bean);
/* 1733 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_39fb3d() {
/* 1737 */     if (this.context.get("nc.ui.uif2.I18nFB#39fb3d") != null)
/* 1738 */       return (String)this.context.get("nc.ui.uif2.I18nFB#39fb3d");
/* 1739 */     I18nFB bean = new I18nFB();
/* 1740 */     this.context.put("&nc.ui.uif2.I18nFB#39fb3d", bean);bean.setResDir("4006011_0");
/* 1741 */     bean.setResId("04006011-0399");
/* 1742 */     bean.setDefaultValue("发货安排");
/* 1743 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1744 */     invokeInitializingBean(bean);
/*      */     try {
/* 1746 */       Object product = bean.getObject();
/* 1747 */       this.context.put("nc.ui.uif2.I18nFB#39fb3d", product);
/* 1748 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1750 */       throw new RuntimeException(e); } }
/*      */   
/* 1752 */   private List getManagedList24() { List list = new java.util.ArrayList();list.add(getSendArrangeAction());list.add(getBhArrangeAction());list.add(getZyArrangeAction());return list;
/*      */   }
/*      */   
/* 1755 */   public nc.ui.so.m30.billui.action.assist.FreezeAction getFreezeAction() { if (this.context.get("freezeAction") != null)
/* 1756 */       return (nc.ui.so.m30.billui.action.assist.FreezeAction)this.context.get("freezeAction");
/* 1757 */     nc.ui.so.m30.billui.action.assist.FreezeAction bean = new nc.ui.so.m30.billui.action.assist.FreezeAction();
/* 1758 */     this.context.put("freezeAction", bean);
/* 1759 */     bean.setModel(getManageAppModel());
/* 1760 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1761 */     invokeInitializingBean(bean);
/* 1762 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.UnFreezeAction getUnFreezeAction() {
/* 1766 */     if (this.context.get("unFreezeAction") != null)
/* 1767 */       return (nc.ui.so.m30.billui.action.assist.UnFreezeAction)this.context.get("unFreezeAction");
/* 1768 */     nc.ui.so.m30.billui.action.assist.UnFreezeAction bean = new nc.ui.so.m30.billui.action.assist.UnFreezeAction();
/* 1769 */     this.context.put("unFreezeAction", bean);
/* 1770 */     bean.setModel(getManageAppModel());
/* 1771 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1772 */     invokeInitializingBean(bean);
/* 1773 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction getCloseAction() {
/* 1777 */     if (this.context.get("closeAction") != null)
/* 1778 */       return (nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction)this.context.get("closeAction");
/* 1779 */     nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderCloseAction();
/* 1780 */     this.context.put("closeAction", bean);
/* 1781 */     bean.setModel(getManageAppModel());
/* 1782 */     bean.setSingleBillService(getM30CloseBillCloseService_9da289());
/* 1783 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1784 */     invokeInitializingBean(bean);
/* 1785 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.so.m30.closemanage.model.M30CloseBillCloseService getM30CloseBillCloseService_9da289() {
/* 1789 */     if (this.context.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#9da289") != null)
/* 1790 */       return (nc.ui.so.m30.closemanage.model.M30CloseBillCloseService)this.context.get("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#9da289");
/* 1791 */     nc.ui.so.m30.closemanage.model.M30CloseBillCloseService bean = new nc.ui.so.m30.closemanage.model.M30CloseBillCloseService();
/* 1792 */     this.context.put("nc.ui.so.m30.closemanage.model.M30CloseBillCloseService#9da289", bean);
/* 1793 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1794 */     invokeInitializingBean(bean);
/* 1795 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction getReverseAction() {
/* 1799 */     if (this.context.get("reverseAction") != null)
/* 1800 */       return (nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction)this.context.get("reverseAction");
/* 1801 */     nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderReverseAction();
/* 1802 */     this.context.put("reverseAction", bean);
/* 1803 */     bean.setModel(getManageAppModel());
/* 1804 */     bean.setEditor(getBillFormEditor());
/* 1805 */     bean.setListView(getListView());
/* 1806 */     bean.setRefreshAction(getCardRefreshAction());
/* 1807 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1808 */     invokeInitializingBean(bean);
/* 1809 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction getQueryReverseAction() {
/* 1813 */     if (this.context.get("queryReverseAction") != null)
/* 1814 */       return (nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction)this.context.get("queryReverseAction");
/* 1815 */     nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction bean = new nc.ui.so.m30.billui.action.assist.SaleorderReverseQueryAction();
/* 1816 */     this.context.put("queryReverseAction", bean);
/* 1817 */     bean.setModel(getManageAppModel());
/* 1818 */     bean.setEditor(getBillFormEditor());
/* 1819 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1820 */     invokeInitializingBean(bean);
/* 1821 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.TransInfoAction getTransInfoAction() {
/* 1825 */     if (this.context.get("transInfoAction") != null)
/* 1826 */       return (nc.ui.so.m30.billui.action.assist.TransInfoAction)this.context.get("transInfoAction");
/* 1827 */     nc.ui.so.m30.billui.action.assist.TransInfoAction bean = new nc.ui.so.m30.billui.action.assist.TransInfoAction();
/* 1828 */     this.context.put("transInfoAction", bean);
/* 1829 */     bean.setModel(getManageAppModel());
/* 1830 */     bean.setEditor(getBillFormEditor());
/* 1831 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1832 */     invokeInitializingBean(bean);
/* 1833 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action getPushTo21Action() {
/* 1837 */     if (this.context.get("pushTo21Action") != null)
/* 1838 */       return (nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action)this.context.get("pushTo21Action");
/* 1839 */     nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action bean = new nc.ui.so.m30.billui.action.assist.SaleOrderPushTo21Action();
/* 1840 */     this.context.put("pushTo21Action", bean);
/* 1841 */     bean.setSourceBillName(getI18nFB_119d8e4());
/* 1842 */     bean.setFlowBillType(false);
/* 1843 */     bean.setModel(getManageAppModel());
/* 1844 */     bean.setEditor(getBillFormEditor());
/* 1845 */     bean.setRefreshAction(getCardRefreshAction());
/* 1846 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1847 */     invokeInitializingBean(bean);
/* 1848 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_119d8e4() {
/* 1852 */     if (this.context.get("nc.ui.uif2.I18nFB#119d8e4") != null)
/* 1853 */       return (String)this.context.get("nc.ui.uif2.I18nFB#119d8e4");
/* 1854 */     I18nFB bean = new I18nFB();
/* 1855 */     this.context.put("&nc.ui.uif2.I18nFB#119d8e4", bean);bean.setResDir("4006011_0");
/* 1856 */     bean.setResId("04006011-0464");
/* 1857 */     bean.setDefaultValue("生成协同采购订单");
/* 1858 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1859 */     invokeInitializingBean(bean);
/*      */     try {
/* 1861 */       Object product = bean.getObject();
/* 1862 */       this.context.put("nc.ui.uif2.I18nFB#119d8e4", product);
/* 1863 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1865 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1868 */   public nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action getRefCoop21Action() { if (this.context.get("refCoop21Action") != null)
/* 1869 */       return (nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action)this.context.get("refCoop21Action");
/* 1870 */     nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action bean = new nc.ui.so.m30.billui.action.assist.SaleOrderRefCoop21Action();
/* 1871 */     this.context.put("refCoop21Action", bean);
/* 1872 */     bean.setSourceBillType("21");
/* 1873 */     bean.setSourceBillName(getI18nFB_1ee907c());
/* 1874 */     bean.setFlowBillType(false);
/* 1875 */     bean.setModel(getManageAppModel());
/* 1876 */     bean.setEditor(getBillFormEditor());
/* 1877 */     bean.setTransferBillViewProcessor(getTransferProcessorforCoop21());
/* 1878 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1879 */     invokeInitializingBean(bean);
/* 1880 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_1ee907c() {
/* 1884 */     if (this.context.get("nc.ui.uif2.I18nFB#1ee907c") != null)
/* 1885 */       return (String)this.context.get("nc.ui.uif2.I18nFB#1ee907c");
/* 1886 */     I18nFB bean = new I18nFB();
/* 1887 */     this.context.put("&nc.ui.uif2.I18nFB#1ee907c", bean);bean.setResDir("4006011_0");
/* 1888 */     bean.setResId("04006011-0401");
/* 1889 */     bean.setDefaultValue("参照协同采购订单新增");
/* 1890 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1891 */     invokeInitializingBean(bean);
/*      */     try {
/* 1893 */       Object product = bean.getObject();
/* 1894 */       this.context.put("nc.ui.uif2.I18nFB#1ee907c", product);
/* 1895 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1897 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1900 */   public nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic getTransferLogicforCoop21() { if (this.context.get("transferLogicforCoop21") != null)
/* 1901 */       return (nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic)this.context.get("transferLogicforCoop21");
/* 1902 */     nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30RefCoop21TransferBillDataLogic();
/* 1903 */     this.context.put("transferLogicforCoop21", bean);
/* 1904 */     bean.setBillForm(getBillFormEditor());
/* 1905 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1906 */     invokeInitializingBean(bean);
/* 1907 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferProcessorforCoop21() {
/* 1911 */     if (this.context.get("transferProcessorforCoop21") != null)
/* 1912 */       return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor)this.context.get("transferProcessorforCoop21");
/* 1913 */     nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
/* 1914 */     this.context.put("transferProcessorforCoop21", bean);
/* 1915 */     bean.setList(getListView());
/* 1916 */     bean.setActionContainer(getContainer());
/* 1917 */     bean.setTransferLogic(getTransferLogicforCoop21());
/* 1918 */     bean.setBillForm(getBillFormEditor());
/* 1919 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1920 */     invokeInitializingBean(bean);
/* 1921 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action getReturnRef30Action() {
/* 1925 */     if (this.context.get("returnRef30Action") != null)
/* 1926 */       return (nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action)this.context.get("returnRef30Action");
/* 1927 */     nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action bean = new nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom30Action();
/* 1928 */     this.context.put("returnRef30Action", bean);
/* 1929 */     bean.setSourceBillType("30");
/* 1930 */     bean.setSourceBillName(getI18nFB_14fe3d6());
/* 1931 */     bean.setFlowBillType(false);
/* 1932 */     bean.setModel(getManageAppModel());
/* 1933 */     bean.setEditor(getBillFormEditor());
/* 1934 */     bean.setTransferViewProcessor(getTransferProcessorforwithdraw());
/* 1935 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1936 */     invokeInitializingBean(bean);
/* 1937 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_14fe3d6() {
/* 1941 */     if (this.context.get("nc.ui.uif2.I18nFB#14fe3d6") != null)
/* 1942 */       return (String)this.context.get("nc.ui.uif2.I18nFB#14fe3d6");
/* 1943 */     I18nFB bean = new I18nFB();
/* 1944 */     this.context.put("&nc.ui.uif2.I18nFB#14fe3d6", bean);bean.setResDir("4006011_0");
/* 1945 */     bean.setResId("04006011-0402");
/* 1946 */     bean.setDefaultValue("销售订单");
/* 1947 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1948 */     invokeInitializingBean(bean);
/*      */     try {
/* 1950 */       Object product = bean.getObject();
/* 1951 */       this.context.put("nc.ui.uif2.I18nFB#14fe3d6", product);
/* 1952 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1954 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 1957 */   public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforwithdraw() { if (this.context.get("transferProcessorforwithdraw") != null)
/* 1958 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorforwithdraw");
/* 1959 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 1960 */     this.context.put("transferProcessorforwithdraw", bean);
/* 1961 */     bean.setList(getListView());
/* 1962 */     bean.setActionContainer(getActionsOfList());
/* 1963 */     bean.setCardActionContainer(getActionsOfCard());
/* 1964 */     bean.setTransferLogic(getTransferLogicforwithdraw());
/* 1965 */     bean.setBillForm(getBillFormEditor());
/* 1966 */     bean.setCancelAction(getCancelAction());
/* 1967 */     bean.setSaveAction(getSaveAction());
/* 1968 */     bean.setCommitAction(getSendApproveAction());
/* 1969 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1970 */     invokeInitializingBean(bean);
/* 1971 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic getTransferLogicforwithdraw() {
/* 1975 */     if (this.context.get("transferLogicforwithdraw") != null)
/* 1976 */       return (nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic)this.context.get("transferLogicforwithdraw");
/* 1977 */     nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref30TransferBillDataLogic();
/* 1978 */     this.context.put("transferLogicforwithdraw", bean);
/* 1979 */     bean.setBillForm(getBillFormEditor());
/* 1980 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1981 */     invokeInitializingBean(bean);
/* 1982 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction getReturnRef4CAction() {
/* 1986 */     if (this.context.get("returnRef4CAction") != null)
/* 1987 */       return (nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction)this.context.get("returnRef4CAction");
/* 1988 */     nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction bean = new nc.ui.so.m30.billui.action.assist.ReturnSaleOrderAddFrom4CAction();
/* 1989 */     this.context.put("returnRef4CAction", bean);
/* 1990 */     bean.setSourceBillType("4C");
/* 1991 */     bean.setSourceBillName(getI18nFB_1f7049c());
/* 1992 */     bean.setFlowBillType(false);
/* 1993 */     bean.setModel(getManageAppModel());
/* 1994 */     bean.setEditor(getBillFormEditor());
/* 1995 */     bean.setTransferViewProcessor(getTransferProcessorforsaleout());
/* 1996 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1997 */     invokeInitializingBean(bean);
/* 1998 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_1f7049c() {
/* 2002 */     if (this.context.get("nc.ui.uif2.I18nFB#1f7049c") != null)
/* 2003 */       return (String)this.context.get("nc.ui.uif2.I18nFB#1f7049c");
/* 2004 */     I18nFB bean = new I18nFB();
/* 2005 */     this.context.put("&nc.ui.uif2.I18nFB#1f7049c", bean);bean.setResDir("4006011_0");
/* 2006 */     bean.setResId("04006011-0465");
/* 2007 */     bean.setDefaultValue("销售出库单");
/* 2008 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2009 */     invokeInitializingBean(bean);
/*      */     try {
/* 2011 */       Object product = bean.getObject();
/* 2012 */       this.context.put("nc.ui.uif2.I18nFB#1f7049c", product);
/* 2013 */       return (String)product;
/*      */     } catch (Exception e) {
/* 2015 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/* 2018 */   public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferProcessorforsaleout() { if (this.context.get("transferProcessorforsaleout") != null)
/* 2019 */       return (nc.ui.pubapp.billref.dest.TransferViewProcessor)this.context.get("transferProcessorforsaleout");
/* 2020 */     nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
/* 2021 */     this.context.put("transferProcessorforsaleout", bean);
/* 2022 */     bean.setList(getListView());
/* 2023 */     bean.setActionContainer(getActionsOfList());
/* 2024 */     bean.setCardActionContainer(getActionsOfCard());
/* 2025 */     bean.setTransferLogic(getTransferLogicforsaleout());
/* 2026 */     bean.setBillForm(getBillFormEditor());
/* 2027 */     bean.setCancelAction(getCancelAction());
/* 2028 */     bean.setSaveAction(getSaveAction());
/* 2029 */     bean.setCommitAction(getSendApproveAction());
/* 2030 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2031 */     invokeInitializingBean(bean);
/* 2032 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic getTransferLogicforsaleout() {
/* 2036 */     if (this.context.get("transferLogicforsaleout") != null)
/* 2037 */       return (nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic)this.context.get("transferLogicforsaleout");
/* 2038 */     nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic bean = new nc.ui.so.m30.billui.tranferbill.M30Ref4CTransferBillDataLogic();
/* 2039 */     this.context.put("transferLogicforsaleout", bean);
/* 2040 */     bean.setBillForm(getBillFormEditor());
/* 2041 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2042 */     invokeInitializingBean(bean);
/* 2043 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction getPriceFormAction() {
/* 2047 */     if (this.context.get("priceFormAction") != null)
/* 2048 */       return (nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction)this.context.get("priceFormAction");
/* 2049 */     nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction bean = new nc.ui.so.m30.billui.action.assist.SaleOrderPriceFormAction();
/* 2050 */     this.context.put("priceFormAction", bean);
/* 2051 */     bean.setModel(getManageAppModel());
/* 2052 */     bean.setEditor(getBillFormEditor());
/* 2053 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2054 */     invokeInitializingBean(bean);
/* 2055 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.pub.actions.SOManageDocumentAction getDocManageAction() {
/* 2059 */     if (this.context.get("docManageAction") != null)
/* 2060 */       return (nc.ui.so.pub.actions.SOManageDocumentAction)this.context.get("docManageAction");
/* 2061 */     nc.ui.so.pub.actions.SOManageDocumentAction bean = new nc.ui.so.pub.actions.SOManageDocumentAction();
/* 2062 */     this.context.put("docManageAction", bean);
/* 2063 */     bean.setModel(getManageAppModel());
/* 2064 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2065 */     invokeInitializingBean(bean);
/* 2066 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.scmmm.ui.uif2.actions.SCMLinkQueryAction getLinkQueryAction() {
/* 2070 */     if (this.context.get("linkQueryAction") != null)
/* 2071 */       return (nc.scmmm.ui.uif2.actions.SCMLinkQueryAction)this.context.get("linkQueryAction");
/* 2072 */     nc.scmmm.ui.uif2.actions.SCMLinkQueryAction bean = new nc.scmmm.ui.uif2.actions.SCMLinkQueryAction();
/* 2073 */     this.context.put("linkQueryAction", bean);
/* 2074 */     bean.setModel(getManageAppModel());
/* 2075 */     bean.setBillType("30");
/* 2076 */     bean.setOpenMode(1);
/* 2077 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2078 */     invokeInitializingBean(bean);
/* 2079 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.link.QueryAuditFlowAction getQueryAuditFlowAction() {
/* 2083 */     if (this.context.get("queryAuditFlowAction") != null)
/* 2084 */       return (nc.ui.so.m30.billui.action.link.QueryAuditFlowAction)this.context.get("queryAuditFlowAction");
/* 2085 */     nc.ui.so.m30.billui.action.link.QueryAuditFlowAction bean = new nc.ui.so.m30.billui.action.link.QueryAuditFlowAction();
/* 2086 */     this.context.put("queryAuditFlowAction", bean);
/* 2087 */     bean.setModel(getManageAppModel());
/* 2088 */     bean.setEditor(getBillFormEditor());
/* 2089 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2090 */     invokeInitializingBean(bean);
/* 2091 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.link.QueryCustInfoAction getQueryCustInfoAction() {
/* 2095 */     if (this.context.get("queryCustInfoAction") != null)
/* 2096 */       return (nc.ui.so.m30.billui.action.link.QueryCustInfoAction)this.context.get("queryCustInfoAction");
/* 2097 */     nc.ui.so.m30.billui.action.link.QueryCustInfoAction bean = new nc.ui.so.m30.billui.action.link.QueryCustInfoAction();
/* 2098 */     this.context.put("queryCustInfoAction", bean);
/* 2099 */     bean.setModel(getManageAppModel());
/* 2100 */     bean.setEditor(getBillFormEditor());
/* 2101 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2102 */     invokeInitializingBean(bean);
/* 2103 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.link.SaleOrderProfitAction getCrossProfAction() {
/* 2107 */     if (this.context.get("crossProfAction") != null)
/* 2108 */       return (nc.ui.so.m30.billui.action.link.SaleOrderProfitAction)this.context.get("crossProfAction");
/* 2109 */     nc.ui.so.m30.billui.action.link.SaleOrderProfitAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderProfitAction();
/* 2110 */     this.context.put("crossProfAction", bean);
/* 2111 */     bean.setModel(getManageAppModel());
/* 2112 */     bean.setEditor(getBillFormEditor());
/* 2113 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2114 */     invokeInitializingBean(bean);
/* 2115 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.link.QueryExecInfoAction getQueryExecInfoAction() {
/* 2119 */     if (this.context.get("queryExecInfoAction") != null)
/* 2120 */       return (nc.ui.so.m30.billui.action.link.QueryExecInfoAction)this.context.get("queryExecInfoAction");
/* 2121 */     nc.ui.so.m30.billui.action.link.QueryExecInfoAction bean = new nc.ui.so.m30.billui.action.link.QueryExecInfoAction();
/* 2122 */     this.context.put("queryExecInfoAction", bean);
/* 2123 */     bean.setModel(getManageAppModel());
/* 2124 */     bean.setEditor(getBillFormEditor());
/* 2125 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2126 */     invokeInitializingBean(bean);
/* 2127 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction getReceivePlanAction() {
/* 2131 */     if (this.context.get("ReceivePlanAction") != null)
/* 2132 */       return (nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction)this.context.get("ReceivePlanAction");
/* 2133 */     nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderRecPlanAction();
/* 2134 */     this.context.put("ReceivePlanAction", bean);
/* 2135 */     bean.setModel(getManageAppModel());
/* 2136 */     bean.setEditor(getBillFormEditor());
/* 2137 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2138 */     invokeInitializingBean(bean);
/* 2139 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.MenuAction getBrowLinkGroupAction() {
/* 2143 */     if (this.context.get("browLinkGroupAction") != null)
/* 2144 */       return (nc.funcnode.ui.action.MenuAction)this.context.get("browLinkGroupAction");
/* 2145 */     nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
/* 2146 */     this.context.put("browLinkGroupAction", bean);
/* 2147 */     bean.setCode("browlinkgroup");
/* 2148 */     bean.setName(getI18nFB_4fab15());
/* 2149 */     bean.setActions(getManagedList25());
/* 2150 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2151 */     invokeInitializingBean(bean);
/* 2152 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_4fab15() {
/* 2156 */     if (this.context.get("nc.ui.uif2.I18nFB#4fab15") != null)
/* 2157 */       return (String)this.context.get("nc.ui.uif2.I18nFB#4fab15");
/* 2158 */     I18nFB bean = new I18nFB();
/* 2159 */     this.context.put("&nc.ui.uif2.I18nFB#4fab15", bean);bean.setResDir("4006011_0");
/* 2160 */     bean.setResId("04006011-0392");
/* 2161 */     bean.setDefaultValue("联查");
/* 2162 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2163 */     invokeInitializingBean(bean);
/*      */     try {
/* 2165 */       Object product = bean.getObject();
/* 2166 */       this.context.put("nc.ui.uif2.I18nFB#4fab15", product);
/* 2167 */       return (String)product;
/*      */     } catch (Exception e) {
/* 2169 */       throw new RuntimeException(e); } }
/*      */   
/* 2171 */   private List getManagedList25() { List list = new java.util.ArrayList();list.add(getLinkQueryAction());list.add(getSpShowHiddenAction());list.add(getCreditQueryAction());list.add(getQueryCustInfoAction());list.add(getCrossProfAction());list.add(getQueryExecInfoAction());list.add(getReceivePlanAction());return list;
/*      */   }
/*      */   
/* 2174 */   public nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction getPreviewAction() { if (this.context.get("previewAction") != null)
/* 2175 */       return (nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction)this.context.get("previewAction");
/* 2176 */     nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction();
/* 2177 */     this.context.put("previewAction", bean);
/* 2178 */     bean.setPreview(true);
/* 2179 */     bean.setModel(getManageAppModel());
/* 2180 */     bean.setBeforePrintDataProcess(getPrintProcessor());
/* 2181 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2182 */     invokeInitializingBean(bean);
/* 2183 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction getPrintAction() {
/* 2187 */     if (this.context.get("printAction") != null)
/* 2188 */       return (nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction)this.context.get("printAction");
/* 2189 */     nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPrintAction();
/* 2190 */     this.context.put("printAction", bean);
/* 2191 */     bean.setPreview(false);
/* 2192 */     bean.setModel(getManageAppModel());
/* 2193 */     bean.setBeforePrintDataProcess(getPrintProcessor());
/* 2194 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2195 */     invokeInitializingBean(bean);
/* 2196 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor getPrintProcessor() {
/* 2200 */     if (this.context.get("printProcessor") != null)
/* 2201 */       return (nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor)this.context.get("printProcessor");
/* 2202 */     nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderPrintProcessor();
/* 2203 */     this.context.put("printProcessor", bean);
/* 2204 */     bean.setModel(getManageAppModel());
/* 2205 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2206 */     invokeInitializingBean(bean);
/* 2207 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.GroupAction getPrintActionGroup() {
/* 2211 */     if (this.context.get("printActionGroup") != null)
/* 2212 */       return (nc.funcnode.ui.action.GroupAction)this.context.get("printActionGroup");
/* 2213 */     nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
/* 2214 */     this.context.put("printActionGroup", bean);
/* 2215 */     bean.setCode("printMenuAction");
/* 2216 */     bean.setName(getI18nFB_690460());
/* 2217 */     bean.setActions(getManagedList26());
/* 2218 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2219 */     invokeInitializingBean(bean);
/* 2220 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_690460() {
/* 2224 */     if (this.context.get("nc.ui.uif2.I18nFB#690460") != null)
/* 2225 */       return (String)this.context.get("nc.ui.uif2.I18nFB#690460");
/* 2226 */     I18nFB bean = new I18nFB();
/* 2227 */     this.context.put("&nc.ui.uif2.I18nFB#690460", bean);bean.setResDir("common");
/* 2228 */     bean.setResId("UC001-0000007");
/* 2229 */     bean.setDefaultValue("打印");
/* 2230 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2231 */     invokeInitializingBean(bean);
/*      */     try {
/* 2233 */       Object product = bean.getObject();
/* 2234 */       this.context.put("nc.ui.uif2.I18nFB#690460", product);
/* 2235 */       return (String)product;
/*      */     } catch (Exception e) {
/* 2237 */       throw new RuntimeException(e); } }
/*      */   
/* 2239 */   private List getManagedList26() { List list = new java.util.ArrayList();list.add(getPrintAction());list.add(getPreviewAction());list.add(getOutputAction());list.add(getSeparatorAction());list.add(getSplitPrintAction());return list;
/*      */   }
/*      */   
/* 2242 */   public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() { if (this.context.get("separatorAction") != null)
/* 2243 */       return (nc.funcnode.ui.action.SeparatorAction)this.context.get("separatorAction");
/* 2244 */     nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
/* 2245 */     this.context.put("separatorAction", bean);
/* 2246 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2247 */     invokeInitializingBean(bean);
/* 2248 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() {
/* 2252 */     if (this.context.get("outputAction") != null)
/* 2253 */       return (nc.ui.pubapp.uif2app.actions.OutputAction)this.context.get("outputAction");
/* 2254 */     nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
/* 2255 */     this.context.put("outputAction", bean);
/* 2256 */     bean.setModel(getManageAppModel());
/* 2257 */     bean.setParent(getBillFormEditor());
/* 2258 */     bean.setBeforePrintDataProcess(getPrintProcessor());
/* 2259 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2260 */     invokeInitializingBean(bean);
/* 2261 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction getSplitPrintAction() {
/* 2265 */     if (this.context.get("SplitPrintAction") != null)
/* 2266 */       return (nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction)this.context.get("SplitPrintAction");
/* 2267 */     nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction bean = new nc.ui.so.m30.billui.action.printaction.SaleOrderSplitPrintAction();
/* 2268 */     this.context.put("SplitPrintAction", bean);
/* 2269 */     bean.setModel(getManageAppModel());
/* 2270 */     bean.setPrintAction(getPrintAction());
/* 2271 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2272 */     invokeInitializingBean(bean);
/* 2273 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderExchangeAction getExchangeAction() {
/* 2277 */     if (this.context.get("exchangeAction") != null)
/* 2278 */       return (nc.ui.so.m30.billui.action.SaleOrderExchangeAction)this.context.get("exchangeAction");
/* 2279 */     nc.ui.so.m30.billui.action.SaleOrderExchangeAction bean = new nc.ui.so.m30.billui.action.SaleOrderExchangeAction();
/* 2280 */     this.context.put("exchangeAction", bean);
/* 2281 */     bean.setModel(getManageAppModel());
/* 2282 */     bean.setEditor(getBillFormEditor());
/* 2283 */     bean.setLineaction(getSaleOrderInsertLineAction());
/* 2284 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2285 */     invokeInitializingBean(bean);
/* 2286 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderFindPriceAction getFindPriceAction() {
/* 2290 */     if (this.context.get("findPriceAction") != null)
/* 2291 */       return (nc.ui.so.m30.billui.action.SaleOrderFindPriceAction)this.context.get("findPriceAction");
/* 2292 */     nc.ui.so.m30.billui.action.SaleOrderFindPriceAction bean = new nc.ui.so.m30.billui.action.SaleOrderFindPriceAction();
/* 2293 */     this.context.put("findPriceAction", bean);
/* 2294 */     bean.setModel(getManageAppModel());
/* 2295 */     bean.setEditor(getBillFormEditor());
/* 2296 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2297 */     invokeInitializingBean(bean);
/* 2298 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction getLargessApportionAction() {
/* 2302 */     if (this.context.get("largessApportionAction") != null)
/* 2303 */       return (nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction)this.context.get("largessApportionAction");
/* 2304 */     nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction bean = new nc.ui.so.m30.billui.action.SaleOrderLargessApportionAction();
/* 2305 */     this.context.put("largessApportionAction", bean);
/* 2306 */     bean.setModel(getManageAppModel());
/* 2307 */     bean.setEditor(getBillFormEditor());
/* 2308 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2309 */     invokeInitializingBean(bean);
/* 2310 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction getUnLargessApportionAction() {
/* 2314 */     if (this.context.get("unLargessApportionAction") != null)
/* 2315 */       return (nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction)this.context.get("unLargessApportionAction");
/* 2316 */     nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction bean = new nc.ui.so.m30.billui.action.SaleOrderUndoLargessApportionAction();
/* 2317 */     this.context.put("unLargessApportionAction", bean);
/* 2318 */     bean.setModel(getManageAppModel());
/* 2319 */     bean.setEditor(getBillFormEditor());
/* 2320 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2321 */     invokeInitializingBean(bean);
/* 2322 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.MenuAction getEditAstActionGroup() {
/* 2326 */     if (this.context.get("editAstActionGroup") != null)
/* 2327 */       return (nc.funcnode.ui.action.MenuAction)this.context.get("editAstActionGroup");
/* 2328 */     nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
/* 2329 */     this.context.put("editAstActionGroup", bean);
/* 2330 */     bean.setCode("editastgroup");
/* 2331 */     bean.setName(getI18nFB_9e3de9());
/* 2332 */     bean.setActions(getManagedList27());
/* 2333 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2334 */     invokeInitializingBean(bean);
/* 2335 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_9e3de9() {
/* 2339 */     if (this.context.get("nc.ui.uif2.I18nFB#9e3de9") != null)
/* 2340 */       return (String)this.context.get("nc.ui.uif2.I18nFB#9e3de9");
/* 2341 */     I18nFB bean = new I18nFB();
/* 2342 */     this.context.put("&nc.ui.uif2.I18nFB#9e3de9", bean);bean.setResDir("4006011_0");
/* 2343 */     bean.setResId("04006011-0400");
/* 2344 */     bean.setDefaultValue("辅助功能");
/* 2345 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2346 */     invokeInitializingBean(bean);
/*      */     try {
/* 2348 */       Object product = bean.getObject();
/* 2349 */       this.context.put("nc.ui.uif2.I18nFB#9e3de9", product);
/* 2350 */       return (String)product;
/*      */     } catch (Exception e) {
/* 2352 */       throw new RuntimeException(e); } }
/*      */   
/* 2354 */   private List getManagedList27() { List list = new java.util.ArrayList();list.add(getOffsetAction());list.add(getUnoffsetAction());list.add(getSaleOrderCashSaleAction());list.add(getExchangeAction());list.add(getFindPriceAction());list.add(getPriceFormAction());list.add(getLargessApportionAction());list.add(getUnLargessApportionAction());return list;
/*      */   }
/*      */   
/* 2357 */   public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() { if (this.context.get("userdefAndMarAsstCardPreparator") != null)
/* 2358 */       return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)this.context.get("userdefAndMarAsstCardPreparator");
/* 2359 */     nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
/* 2360 */     this.context.put("userdefAndMarAsstCardPreparator", bean);
/* 2361 */     bean.setBillDataPrepares(getManagedList28());
/* 2362 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2363 */     invokeInitializingBean(bean);
/* 2364 */     return bean;
/*      */   }
/*      */   
/* 2367 */   private List getManagedList28() { List list = new java.util.ArrayList();list.add(getUserdefitemPreparator());list.add(getMarAsstPreparator());return list;
/*      */   }
/*      */   
/* 2370 */   public nc.ui.so.m30.billui.action.SPShowHidAction getSpShowHiddenAction() { if (this.context.get("spShowHiddenAction") != null)
/* 2371 */       return (nc.ui.so.m30.billui.action.SPShowHidAction)this.context.get("spShowHiddenAction");
/* 2372 */     nc.ui.so.m30.billui.action.SPShowHidAction bean = new nc.ui.so.m30.billui.action.SPShowHidAction();
/* 2373 */     this.context.put("spShowHiddenAction", bean);
/* 2374 */     bean.setContain(getContainer());
/* 2375 */     bean.setCard(getBillFormEditor());
/* 2376 */     bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
/* 2377 */     bean.setLogincontext(getContext());
/* 2378 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2379 */     invokeInitializingBean(bean);
/* 2380 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction getCreditQueryAction() {
/* 2384 */     if (this.context.get("creditQueryAction") != null)
/* 2385 */       return (nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction)this.context.get("creditQueryAction");
/* 2386 */     nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction bean = new nc.ui.so.m30.billui.action.link.SaleOrderCreditQueryAction();
/* 2387 */     this.context.put("creditQueryAction", bean);
/* 2388 */     bean.setModel(getManageAppModel());
/* 2389 */     bean.setEditor(getBillFormEditor());
/* 2390 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2391 */     invokeInitializingBean(bean);
/* 2392 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.MenuAction getEditLinkActionGroup() {
/* 2396 */     if (this.context.get("editLinkActionGroup") != null)
/* 2397 */       return (nc.funcnode.ui.action.MenuAction)this.context.get("editLinkActionGroup");
/* 2398 */     nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
/* 2399 */     this.context.put("editLinkActionGroup", bean);
/* 2400 */     bean.setCode("editlinkgroup");
/* 2401 */     bean.setName(getI18nFB_f2b972());
/* 2402 */     bean.setActions(getManagedList29());
/* 2403 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2404 */     invokeInitializingBean(bean);
/* 2405 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_f2b972() {
/* 2409 */     if (this.context.get("nc.ui.uif2.I18nFB#f2b972") != null)
/* 2410 */       return (String)this.context.get("nc.ui.uif2.I18nFB#f2b972");
/* 2411 */     I18nFB bean = new I18nFB();
/* 2412 */     this.context.put("&nc.ui.uif2.I18nFB#f2b972", bean);bean.setResDir("4006011_0");
/* 2413 */     bean.setResId("04006011-0392");
/* 2414 */     bean.setDefaultValue("联查");
/* 2415 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2416 */     invokeInitializingBean(bean);
/*      */     try {
/* 2418 */       Object product = bean.getObject();
/* 2419 */       this.context.put("nc.ui.uif2.I18nFB#f2b972", product);
/* 2420 */       return (String)product;
/*      */     } catch (Exception e) {
/* 2422 */       throw new RuntimeException(e); } }
/*      */   
/* 2424 */   private List getManagedList29() { List list = new java.util.ArrayList();list.add(getSpShowHiddenAction());list.add(getCreditQueryAction());list.add(getQueryCustInfoAction());list.add(getCrossProfAction());list.add(getQueryExecInfoAction());return list;
/*      */   }
/*      */   
/* 2427 */   public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader() { if (this.context.get("billLazilyLoader") != null)
/* 2428 */       return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)this.context.get("billLazilyLoader");
/* 2429 */     nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
/* 2430 */     this.context.put("billLazilyLoader", bean);
/* 2431 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2432 */     invokeInitializingBean(bean);
/* 2433 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator() {
/* 2437 */     if (this.context.get("LasilyLodadMediator") != null)
/* 2438 */       return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)this.context.get("LasilyLodadMediator");
/* 2439 */     nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
/* 2440 */     this.context.put("LasilyLodadMediator", bean);
/* 2441 */     bean.setModel(getManageAppModel());
/* 2442 */     bean.setLoader(getBillLazilyLoader());
/* 2443 */     bean.setLazilyLoadSupporter(getManagedList30());
/* 2444 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2445 */     invokeInitializingBean(bean);
/* 2446 */     return bean;
/*      */   }
/*      */   
/* 2449 */   private List getManagedList30() { List list = new java.util.ArrayList();list.add(getCardPanelLazilyLoad_16d2b74());list.add(getListPanelLazilyLoad_3fded7());list.add(getActionLazilyLoad_13f2077());return list;
/*      */   }
/*      */   
/* 2452 */   private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_16d2b74() { if (this.context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#16d2b74") != null)
/* 2453 */       return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)this.context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#16d2b74");
/* 2454 */     nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
/* 2455 */     this.context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#16d2b74", bean);
/* 2456 */     bean.setBillform(getBillFormEditor());
/* 2457 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2458 */     invokeInitializingBean(bean);
/* 2459 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_3fded7() {
/* 2463 */     if (this.context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#3fded7") != null)
/* 2464 */       return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)this.context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#3fded7");
/* 2465 */     nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
/* 2466 */     this.context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#3fded7", bean);
/* 2467 */     bean.setListView(getListView());
/* 2468 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2469 */     invokeInitializingBean(bean);
/* 2470 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_13f2077() {
/* 2474 */     if (this.context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#13f2077") != null)
/* 2475 */       return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)this.context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#13f2077");
/* 2476 */     nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
/* 2477 */     this.context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#13f2077", bean);
/* 2478 */     bean.setModel(getManageAppModel());
/* 2479 */     bean.setActionList(getManagedList31());
/* 2480 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2481 */     invokeInitializingBean(bean);
/* 2482 */     return bean;
/*      */   }
/*      */   
/* 2485 */   private List getManagedList31() { List list = new java.util.ArrayList();list.add(getPrintAction());list.add(getPreviewAction());list.add(getOutputAction());list.add(getSplitPrintAction());return list;
/*      */   }
/*      */   
/* 2488 */   public nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer getQueryDLGInitializer() { if (this.context.get("queryDLGInitializer") != null)
/* 2489 */       return (nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer)this.context.get("queryDLGInitializer");
/* 2490 */     nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer bean = new nc.ui.so.m30.billui.view.SaleOrderQueryDLGInitializer();
/* 2491 */     this.context.put("queryDLGInitializer", bean);
/* 2492 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2493 */     invokeInitializingBean(bean);
/* 2494 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer() {
/* 2498 */     if (this.context.get("queryTemplateContainer") != null)
/* 2499 */       return (nc.ui.uif2.editor.QueryTemplateContainer)this.context.get("queryTemplateContainer");
/* 2500 */     nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
/* 2501 */     this.context.put("queryTemplateContainer", bean);
/* 2502 */     bean.setContext(getContext());
/* 2503 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2504 */     invokeInitializingBean(bean);
/* 2505 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
/* 2509 */     if (this.context.get("queryAction") != null)
/* 2510 */       return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)this.context.get("queryAction");
/* 2511 */     nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
/* 2512 */     this.context.put("queryAction", bean);
/* 2513 */     bean.setDataManager(getModelDataManager());
/* 2514 */     bean.setQryCondDLGInitializer(getQueryDLGInitializer());
/* 2515 */     bean.setModel(getManageAppModel());
/* 2516 */     bean.setTemplateContainer(getQueryTemplateContainer());
/* 2517 */     bean.setInterceptor(getListInterceptor());
/* 2518 */     bean.setShowUpComponent(getListView());
/* 2519 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2520 */     invokeInitializingBean(bean);
/* 2521 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader() {
/* 2525 */     if (this.context.get("pfAddInfoLoader") != null)
/* 2526 */       return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)this.context.get("pfAddInfoLoader");
/* 2527 */     nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
/* 2528 */     this.context.put("pfAddInfoLoader", bean);
/* 2529 */     bean.setBillType("30");
/* 2530 */     bean.setModel(getManageAppModel());
/* 2531 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2532 */     invokeInitializingBean(bean);
/* 2533 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller() {
/* 2537 */     if (this.context.get("remoteCallCombinatorCaller") != null)
/* 2538 */       return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)this.context.get("remoteCallCombinatorCaller");
/* 2539 */     nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
/* 2540 */     this.context.put("remoteCallCombinatorCaller", bean);
/* 2541 */     bean.setRemoteCallers(getManagedList32());
/* 2542 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2543 */     invokeInitializingBean(bean);
/* 2544 */     return bean;
/*      */   }
/*      */   
/* 2547 */   private List getManagedList32() { List list = new java.util.ArrayList();list.add(getQueryTemplateContainer());list.add(getTemplateContainer());list.add(getUserdefitemContainer());list.add(getPfAddInfoLoader());return list;
/*      */   }
/*      */   
/* 2550 */   public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() { if (this.context.get("actionsOfList") != null)
/* 2551 */       return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)this.context.get("actionsOfList");
/* 2552 */     nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());this.context.put("actionsOfList", bean);
/* 2553 */     bean.setActions(getManagedList33());
/* 2554 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2555 */     invokeInitializingBean(bean);
/* 2556 */     return bean;
/*      */   }
/*      */   
/* 2559 */   private List getManagedList33() { List list = new java.util.ArrayList();list.add(getAddMenuGroup());list.add(getEditAction());list.add(getDeleteAction());list.add(getCopyAction());list.add(getSeparatorAction());list.add(getQueryAction());list.add(getListRefreshAction());list.add(getSeparatorAction());list.add(getSendActionGroup());list.add(getApproveActionGroup());list.add(getGatherActionGroup());list.add(getArrangeActionGroup());list.add(getBrowAstActionGroup());list.add(getSeparatorAction());list.add(getBrowLinkGroupAction());list.add(getSeparatorAction());list.add(getPrintActionGroup());return list;
/*      */   }
/*      */   
/* 2562 */   public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() { if (this.context.get("actionsOfCard") != null)
/* 2563 */       return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)this.context.get("actionsOfCard");
/* 2564 */     nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());this.context.put("actionsOfCard", bean);
/* 2565 */     bean.setActions(getManagedList34());
/* 2566 */     bean.setEditActions(getManagedList35());
/* 2567 */     bean.setModel(getManageAppModel());
/* 2568 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2569 */     invokeInitializingBean(bean);
/* 2570 */     return bean;
/*      */   }
/*      */   
/* 2573 */   private List getManagedList34() { List list = new java.util.ArrayList();list.add(getAddMenuGroup());list.add(getEditAction());list.add(getDeleteAction());list.add(getCopyAction());list.add(getSeparatorAction());list.add(getQueryAction());list.add(getCardRefreshAction());list.add(getSeparatorAction());list.add(getSendActionGroup());list.add(getApproveActionGroup());list.add(getGatherActionGroup());list.add(getArrangeActionGroup());list.add(getBrowAstActionGroup());list.add(getSeparatorAction());list.add(getBrowLinkGroupAction());list.add(getSeparatorAction());list.add(getPrintActionGroup());return list; }
/*      */   
/* 2575 */   private List getManagedList35() { List list = new java.util.ArrayList();list.add(getSaveAction());list.add(getSaveandsendApproveAction());list.add(getSeparatorAction());list.add(getCancelAction());list.add(getSeparatorAction());list.add(getEditAstActionGroup());list.add(getSeparatorAction());list.add(getEditLinkActionGroup());return list;
/*      */   }
/*      */   
/* 2578 */   public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenuGroup() { if (this.context.get("addMenuGroup") != null)
/* 2579 */       return (nc.ui.pubapp.uif2app.actions.AddMenuAction)this.context.get("addMenuGroup");
/* 2580 */     nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
/* 2581 */     this.context.put("addMenuGroup", bean);
/* 2582 */     bean.setBillType("30");
/* 2583 */     bean.setActions(getManagedList36());
/* 2584 */     bean.setModel(getManageAppModel());
/* 2585 */     bean.setPfAddInfoLoader(getPfAddInfoLoader());
/* 2586 */     bean.initUI();
/* 2587 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2588 */     invokeInitializingBean(bean);
/* 2589 */     return bean;
/*      */   }
/*      */   
/* 2592 */   private List getManagedList36() { List list = new java.util.ArrayList();list.add(getAddManualAction());list.add(getSeparatorAction());list.add(getAddZ3Action());list.add(getAdd4310Action());list.add(getAdd38Action());list.add(getAdd4HAction());list.add(getAddFQ01Action());return list;
/*      */   }
/*      */   
/* 2595 */   public nc.funcnode.ui.action.MenuAction getBrowAstActionGroup() { if (this.context.get("browAstActionGroup") != null)
/* 2596 */       return (nc.funcnode.ui.action.MenuAction)this.context.get("browAstActionGroup");
/* 2597 */     nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
/* 2598 */     this.context.put("browAstActionGroup", bean);
/* 2599 */     bean.setCode("browastgroup");
/* 2600 */     bean.setName(getI18nFB_16c7bd6());
/* 2601 */     bean.setActions(getManagedList37());
/* 2602 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2603 */     invokeInitializingBean(bean);
/* 2604 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_16c7bd6() {
/* 2608 */     if (this.context.get("nc.ui.uif2.I18nFB#16c7bd6") != null)
/* 2609 */       return (String)this.context.get("nc.ui.uif2.I18nFB#16c7bd6");
/* 2610 */     I18nFB bean = new I18nFB();
/* 2611 */     this.context.put("&nc.ui.uif2.I18nFB#16c7bd6", bean);bean.setResDir("4006011_0");
/* 2612 */     bean.setResId("04006011-0400");
/* 2613 */     bean.setDefaultValue("辅助功能");
/* 2614 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2615 */     invokeInitializingBean(bean);
/*      */     try {
/* 2617 */       Object product = bean.getObject();
/* 2618 */       this.context.put("nc.ui.uif2.I18nFB#16c7bd6", product);
/* 2619 */       return (String)product;
/*      */     } catch (Exception e) {
/* 2621 */       throw new RuntimeException(e); } }
/*      */   
/* 2623 */   private List getManagedList37() { List list = new java.util.ArrayList();list.add(getFreezeAction());list.add(getUnFreezeAction());list.add(getSeparatorAction());list.add(getCloseAction());list.add(getQueryReverseAction());list.add(getReverseAction());list.add(getTransInfoAction());list.add(getPushTo21Action());list.add(getRefCoop21Action());list.add(getReturnRef30Action());list.add(getReturnRef4CAction());list.add(getSeparatorAction());list.add(getPriceFormAction());list.add(getDocManageAction());return list;
/*      */   }
/*      */ }

