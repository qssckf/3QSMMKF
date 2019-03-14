/*      */ package nc.ui.so.m38.billui.model;
/*      */ 
/*      */ import java.util.List;
/*      */ import java.util.Map;

import javax.swing.Action;

import nc.ui.uif2.editor.UserdefQueryParam;
/*      */ 
/*      */ public class preorder_config extends nc.ui.uif2.factory.AbstractJavaBeanDefinition
/*      */ {
/*      */   public preorder_config() {}
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
/*      */   public PreOrderModelService getManageModelService() {
/*   23 */     if (this.context.get("manageModelService") != null)
/*   24 */       return (PreOrderModelService)this.context.get("manageModelService");
/*   25 */     PreOrderModelService bean = new PreOrderModelService();
/*   26 */     this.context.put("manageModelService", bean);
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
/*   43 */     if (this.context.get("manageAppModel") != null)
/*   44 */       return (nc.ui.pubapp.uif2app.model.BillManageModel)this.context.get("manageAppModel");
/*   45 */     nc.ui.pubapp.uif2app.model.BillManageModel bean = new nc.ui.pubapp.uif2app.model.BillManageModel();
/*   46 */     this.context.put("manageAppModel", bean);
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
/*      */   public nc.ui.so.m38.billui.view.PreOrderListView getListView() {
/*   90 */     if (this.context.get("listView") != null)
/*   91 */       return (nc.ui.so.m38.billui.view.PreOrderListView)this.context.get("listView");
/*   92 */     nc.ui.so.m38.billui.view.PreOrderListView bean = new nc.ui.so.m38.billui.view.PreOrderListView();
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
/*      */   public nc.ui.so.m38.billui.view.PreOrderEditor getBillFormEditor() {
/*  105 */     if (this.context.get("billFormEditor") != null)
/*  106 */       return (nc.ui.so.m38.billui.view.PreOrderEditor)this.context.get("billFormEditor");
/*  107 */     nc.ui.so.m38.billui.view.PreOrderEditor bean = new nc.ui.so.m38.billui.view.PreOrderEditor();
/*  108 */     this.context.put("billFormEditor", bean);
/*  109 */     bean.setModel(getManageAppModel());
/*  110 */     bean.setTemplateContainer(getTemplateContainer());
/*  111 */     bean.setTemplateNotNullValidate(true);
/*  112 */     bean.setClearHyperlink(getManagedList0());
/*  113 */     bean.setAutoAddLine(true);
/*  114 */     bean.setBlankChildrenFilter(getSingleFieldBlankChildrenFilter_92c540());
/*  115 */     bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
/*  116 */     bean.setBodyLineActions(getManagedList1());
/*  117 */     bean.initUI();
/*  118 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  119 */     invokeInitializingBean(bean);
/*  120 */     return bean;
/*      */   }
/*      */   
/*  123 */   private List getManagedList0() { List list = new java.util.ArrayList();list.add("vbillcode");return list;
/*      */   }
/*      */   
/*  126 */   private nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter getSingleFieldBlankChildrenFilter_92c540() { if (this.context.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#92c540") != null)
/*  127 */       return (nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter)this.context.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#92c540");
/*  128 */     nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter();
/*  129 */     this.context.put("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#92c540", bean);
/*  130 */     bean.setFieldName("cmaterialid");
/*  131 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  132 */     invokeInitializingBean(bean);
/*  133 */     return bean;
/*      */   }
/*      */   
/*  136 */   private List getManagedList1() { List list = new java.util.ArrayList();list.add(getBodyAddLineAction_1d42055());list.add(getBodyInsertLineAction_196dd99());list.add(getBodyDelLineAction_1ae98d6());list.add(getBodyCopyLineAction_1b41efb());list.add(getBodyPasteLineAction_5df3a9());list.add(getBodyPasteToTailAction_2817ee());list.add(getActionsBar_ActionsBarSeparator_1158876());list.add(getRearrangeRowNoBodyLineAction_3ba710());list.add(getActionsBar_ActionsBarSeparator_550ea2());list.add(getDefaultBodyZoomAction_33c98c());return list;
/*      */   }
/*      */   
/*  139 */   private nc.ui.pubapp.uif2app.actions.BodyAddLineAction getBodyAddLineAction_1d42055() { if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1d42055") != null)
/*  140 */       return (nc.ui.pubapp.uif2app.actions.BodyAddLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1d42055");
/*  141 */     nc.ui.pubapp.uif2app.actions.BodyAddLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyAddLineAction();
/*  142 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyAddLineAction#1d42055", bean);
/*  143 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  144 */     invokeInitializingBean(bean);
/*  145 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.BodyInsertLineAction getBodyInsertLineAction_196dd99() {
/*  149 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#196dd99") != null)
/*  150 */       return (nc.ui.pubapp.uif2app.actions.BodyInsertLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#196dd99");
/*  151 */     nc.ui.pubapp.uif2app.actions.BodyInsertLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyInsertLineAction();
/*  152 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyInsertLineAction#196dd99", bean);
/*  153 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  154 */     invokeInitializingBean(bean);
/*  155 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.BodyDelLineAction getBodyDelLineAction_1ae98d6() {
/*  159 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1ae98d6") != null)
/*  160 */       return (nc.ui.pubapp.uif2app.actions.BodyDelLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1ae98d6");
/*  161 */     nc.ui.pubapp.uif2app.actions.BodyDelLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyDelLineAction();
/*  162 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyDelLineAction#1ae98d6", bean);
/*  163 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  164 */     invokeInitializingBean(bean);
/*  165 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_1b41efb() {
/*  169 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1b41efb") != null)
/*  170 */       return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1b41efb");
/*  171 */     nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
/*  172 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1b41efb", bean);
/*  173 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  174 */     invokeInitializingBean(bean);
/*  175 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.BodyPasteLineAction getBodyPasteLineAction_5df3a9() {
/*  179 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#5df3a9") != null)
/*  180 */       return (nc.ui.pubapp.uif2app.actions.BodyPasteLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#5df3a9");
/*  181 */     nc.ui.pubapp.uif2app.actions.BodyPasteLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteLineAction();
/*  182 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyPasteLineAction#5df3a9", bean);
/*  183 */     bean.setClearItems(getManagedList2());
/*  184 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  185 */     invokeInitializingBean(bean);
/*  186 */     return bean;
/*      */   }
/*      */   
/*  189 */   private List getManagedList2() { List list = new java.util.ArrayList();list.add("cpreorderbid");list.add("ts");return list;
/*      */   }
/*      */   
/*  192 */   private nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction_2817ee() { if (this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#2817ee") != null)
/*  193 */       return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)this.context.get("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#2817ee");
/*  194 */     nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
/*  195 */     this.context.put("nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction#2817ee", bean);
/*  196 */     bean.setClearItems(getManagedList3());
/*  197 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  198 */     invokeInitializingBean(bean);
/*  199 */     return bean;
/*      */   }
/*      */   
/*  202 */   private List getManagedList3() { List list = new java.util.ArrayList();list.add("cpreorderbid");list.add("ts");return list;
/*      */   }
/*      */   
/*  205 */   private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_1158876() { if (this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1158876") != null)
/*  206 */       return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1158876");
/*  207 */     nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
/*  208 */     this.context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#1158876", bean);
/*  209 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  210 */     invokeInitializingBean(bean);
/*  211 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_3ba710() {
/*  215 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#3ba710") != null)
/*  216 */       return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction)this.context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#3ba710");
/*  217 */     nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
/*  218 */     this.context.put("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#3ba710", bean);
/*  219 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  220 */     invokeInitializingBean(bean);
/*  221 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_550ea2() {
/*  225 */     if (this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#550ea2") != null)
/*  226 */       return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#550ea2");
/*  227 */     nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
/*  228 */     this.context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#550ea2", bean);
/*  229 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  230 */     invokeInitializingBean(bean);
/*  231 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_33c98c() {
/*  235 */     if (this.context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#33c98c") != null)
/*  236 */       return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)this.context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#33c98c");
/*  237 */     nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
/*  238 */     this.context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#33c98c", bean);
/*  239 */     bean.setPos(1);
/*  240 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  241 */     invokeInitializingBean(bean);
/*  242 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.bill.BillCodeMediator getBillCodeMediator() {
/*  246 */     if (this.context.get("billCodeMediator") != null)
/*  247 */       return (nc.ui.pubapp.bill.BillCodeMediator)this.context.get("billCodeMediator");
/*  248 */     nc.ui.pubapp.bill.BillCodeMediator bean = new nc.ui.pubapp.bill.BillCodeMediator();
/*  249 */     this.context.put("billCodeMediator", bean);
/*  250 */     bean.setBillCodeKey("vbillcode");
/*  251 */     bean.setBillType("38");
/*  252 */     bean.setBillForm(getBillFormEditor());
/*  253 */     bean.initUI();
/*  254 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  255 */     invokeInitializingBean(bean);
/*  256 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.FunNodeClosingHandler getClosingListener() {
/*  260 */     if (this.context.get("ClosingListener") != null)
/*  261 */       return (nc.ui.uif2.FunNodeClosingHandler)this.context.get("ClosingListener");
/*  262 */     nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
/*  263 */     this.context.put("ClosingListener", bean);
/*  264 */     bean.setModel(getManageAppModel());
/*  265 */     bean.setSaveaction(getSaveAction());
/*  266 */     bean.setCancelaction(getCancelAction());
/*  267 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  268 */     invokeInitializingBean(bean);
/*  269 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator() {
/*  273 */     if (this.context.get("mouseClickShowPanelMediator") != null)
/*  274 */       return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)this.context.get("mouseClickShowPanelMediator");
/*  275 */     nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
/*  276 */     this.context.put("mouseClickShowPanelMediator", bean);
/*  277 */     bean.setListView(getListView());
/*  278 */     bean.setShowUpComponent(getBillFormEditor());
/*  279 */     bean.setHyperLinkColumn("vbillcode");
/*  280 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  281 */     invokeInitializingBean(bean);
/*  282 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getFormInterceptor() {
/*  286 */     if (this.context.get("formInterceptor") != null)
/*  287 */       return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)this.context.get("formInterceptor");
/*  288 */     nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
/*  289 */     this.context.put("formInterceptor", bean);
/*  290 */     bean.setShowUpComponent(getBillFormEditor());
/*  291 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  292 */     invokeInitializingBean(bean);
/*  293 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor() {
/*  297 */     if (this.context.get("listInterceptor") != null)
/*  298 */       return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)this.context.get("listInterceptor");
/*  299 */     nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
/*  300 */     this.context.put("listInterceptor", bean);
/*  301 */     bean.setShowUpComponent(getListView());
/*  302 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  303 */     invokeInitializingBean(bean);
/*  304 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator getCardPanelOrgSetterForAllRefMediator() {
/*  308 */     if (this.context.get("cardPanelOrgSetterForAllRefMediator") != null)
/*  309 */       return (nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator)this.context.get("cardPanelOrgSetterForAllRefMediator");
/*  310 */     nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator bean = new nc.ui.pubapp.uif2app.view.CardPanelOrgSetterForAllRefMediator(getBillFormEditor());this.context.put("cardPanelOrgSetterForAllRefMediator", bean);
/*  311 */     bean.setModel(getManageAppModel());
/*  312 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  313 */     invokeInitializingBean(bean);
/*  314 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator() {
/*  318 */     if (this.context.get("rowNoMediator") != null)
/*  319 */       return (nc.ui.pubapp.uif2app.view.RowNoMediator)this.context.get("rowNoMediator");
/*  320 */     nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
/*  321 */     this.context.put("rowNoMediator", bean);
/*  322 */     bean.setModel(getManageAppModel());
/*  323 */     bean.setEditor(getBillFormEditor());
/*  324 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  325 */     invokeInitializingBean(bean);
/*  326 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator() {
/*  330 */     if (this.context.get("fractionFixMediator") != null)
/*  331 */       return (nc.ui.pubapp.uif2app.view.FractionFixMediator)this.context.get("fractionFixMediator");
/*  332 */     nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getBillFormEditor());this.context.put("fractionFixMediator", bean);
/*  333 */     bean.initUI();
/*  334 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  335 */     invokeInitializingBean(bean);
/*  336 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator() {
/*  340 */     if (this.context.get("billBodySortMediator") != null)
/*  341 */       return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)this.context.get("billBodySortMediator");
/*  342 */     nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(), getBillFormEditor(), getListView());this.context.put("billBodySortMediator", bean);
/*  343 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  344 */     invokeInitializingBean(bean);
/*  345 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator() {
/*  349 */     if (this.context.get("appEventHandlerMediator") != null)
/*  350 */       return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)this.context.get("appEventHandlerMediator");
/*  351 */     nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
/*  352 */     this.context.put("appEventHandlerMediator", bean);
/*  353 */     bean.setModel(getManageAppModel());
/*  354 */     bean.setHandlerMap(getManagedMap0());
/*  355 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  356 */     invokeInitializingBean(bean);
/*  357 */     return bean;
/*      */   }
/*      */   
/*  360 */   private Map getManagedMap0() { Map map = new java.util.HashMap();map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent", getManagedList4());map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent", getManagedList5());map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent", getManagedList6());map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent", getManagedList7());map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent", getManagedList8());map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent", getManagedList9());return map; }
/*      */   
/*  362 */   private List getManagedList4() { List list = new java.util.ArrayList();list.add(getHeadBeforeEditHandler_ca0145());return list;
/*      */   }
/*      */   
/*  365 */   private nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler getHeadBeforeEditHandler_ca0145() { if (this.context.get("nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler#ca0145") != null)
/*  366 */       return (nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler)this.context.get("nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler#ca0145");
/*  367 */     nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler bean = new nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler();
/*  368 */     this.context.put("nc.ui.so.m38.billui.editor.headevent.HeadBeforeEditHandler#ca0145", bean);
/*  369 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  370 */     invokeInitializingBean(bean);
/*  371 */     return bean;
/*      */   }
/*      */   
/*  374 */   private List getManagedList5() { List list = new java.util.ArrayList();list.add(getHeadAfterEditHandler_1962a6());return list;
/*      */   }
/*      */   
/*  377 */   private nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler getHeadAfterEditHandler_1962a6() { if (this.context.get("nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler#1962a6") != null)
/*  378 */       return (nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler)this.context.get("nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler#1962a6");
/*  379 */     nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler bean = new nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler();
/*  380 */     this.context.put("nc.ui.so.m38.billui.editor.headevent.HeadAfterEditHandler#1962a6", bean);
/*  381 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  382 */     invokeInitializingBean(bean);
/*  383 */     return bean;
/*      */   }
/*      */   
/*  386 */   private List getManagedList6() { List list = new java.util.ArrayList();list.add(getBodyBeforeEditHandler_9b867e());return list;
/*      */   }
/*      */   
/*  389 */   private nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler getBodyBeforeEditHandler_9b867e() { if (this.context.get("nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler#9b867e") != null)
/*  390 */       return (nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler)this.context.get("nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler#9b867e");
/*  391 */     nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler bean = new nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler();
/*  392 */     this.context.put("nc.ui.so.m38.billui.editor.bodyevent.BodyBeforeEditHandler#9b867e", bean);
/*  393 */     bean.setEditor(getBillFormEditor());
/*  394 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  395 */     invokeInitializingBean(bean);
/*  396 */     return bean;
/*      */   }
/*      */   
/*  399 */   private List getManagedList7() { List list = new java.util.ArrayList();list.add(getBodyAfterEditHandler_9ae69d());return list;
/*      */   }
/*      */   
/*  402 */   private nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler getBodyAfterEditHandler_9ae69d() { if (this.context.get("nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler#9ae69d") != null)
/*  403 */       return (nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler)this.context.get("nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler#9ae69d");
/*  404 */     nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler bean = new nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler();
/*  405 */     this.context.put("nc.ui.so.m38.billui.editor.bodyevent.BodyAfterEditHandler#9ae69d", bean);
/*  406 */     bean.setEditor(getBillFormEditor());
/*  407 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  408 */     invokeInitializingBean(bean);
/*  409 */     return bean;
/*      */   }
/*      */   
/*  412 */   private List getManagedList8() { List list = new java.util.ArrayList();list.add(getCardBodyAfterRowEditHandler_19a8d54());return list;
/*      */   }
/*      */   
/*  415 */   private nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler getCardBodyAfterRowEditHandler_19a8d54() { if (this.context.get("nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler#19a8d54") != null)
/*  416 */       return (nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler)this.context.get("nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler#19a8d54");
/*  417 */     nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler bean = new nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler();
/*  418 */     this.context.put("nc.ui.so.m38.billui.editor.bodyevent.CardBodyAfterRowEditHandler#19a8d54", bean);
/*  419 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  420 */     invokeInitializingBean(bean);
/*  421 */     return bean;
/*      */   }
/*      */   
/*  424 */   private List getManagedList9() { List list = new java.util.ArrayList();list.add(getOrgEditHandler_1b494fb());return list;
/*      */   }
/*      */   
/*  427 */   private nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler getOrgEditHandler_1b494fb() { if (this.context.get("nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler#1b494fb") != null)
/*  428 */       return (nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler)this.context.get("nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler#1b494fb");
/*  429 */     nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler bean = new nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler(getBillFormEditor(), getContext());this.context.put("nc.ui.so.m38.billui.editor.orgevent.OrgEditHandler#1b494fb", bean);
/*  430 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  431 */     invokeInitializingBean(bean);
/*  432 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.TangramContainer getContainer() {
/*  436 */     if (this.context.get("container") != null)
/*  437 */       return (nc.ui.uif2.TangramContainer)this.context.get("container");
/*  438 */     nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
/*  439 */     this.context.put("container", bean);
/*  440 */     bean.setModel(getManageAppModel());
/*  441 */     bean.setTangramLayoutRoot(getTBNode_1810970());
/*  442 */     bean.initUI();
/*  443 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  444 */     invokeInitializingBean(bean);
/*  445 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_1810970() {
/*  449 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.TBNode#1810970") != null)
/*  450 */       return (nc.ui.uif2.tangramlayout.node.TBNode)this.context.get("nc.ui.uif2.tangramlayout.node.TBNode#1810970");
/*  451 */     nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
/*  452 */     this.context.put("nc.ui.uif2.tangramlayout.node.TBNode#1810970", bean);
/*  453 */     bean.setShowMode("CardLayout");
/*  454 */     bean.setTabs(getManagedList10());
/*  455 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  456 */     invokeInitializingBean(bean);
/*  457 */     return bean;
/*      */   }
/*      */   
/*  460 */   private List getManagedList10() { List list = new java.util.ArrayList();list.add(getHSNode_d45e92());list.add(getVSNode_402dd5());return list;
/*      */   }
/*      */   
/*  463 */   private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_d45e92() { if (this.context.get("nc.ui.uif2.tangramlayout.node.HSNode#d45e92") != null)
/*  464 */       return (nc.ui.uif2.tangramlayout.node.HSNode)this.context.get("nc.ui.uif2.tangramlayout.node.HSNode#d45e92");
/*  465 */     nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
/*  466 */     this.context.put("nc.ui.uif2.tangramlayout.node.HSNode#d45e92", bean);
/*  467 */     bean.setLeft(getCNode_a0f1c4());
/*  468 */     bean.setRight(getVSNode_18b5662());
/*  469 */     bean.setDividerLocation(0.22F);
/*  470 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  471 */     invokeInitializingBean(bean);
/*  472 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_a0f1c4() {
/*  476 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#a0f1c4") != null)
/*  477 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#a0f1c4");
/*  478 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  479 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#a0f1c4", bean);
/*  480 */     bean.setComponent(getQueryArea());
/*  481 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  482 */     invokeInitializingBean(bean);
/*  483 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_18b5662() {
/*  487 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#18b5662") != null)
/*  488 */       return (nc.ui.uif2.tangramlayout.node.VSNode)this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#18b5662");
/*  489 */     nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
/*  490 */     this.context.put("nc.ui.uif2.tangramlayout.node.VSNode#18b5662", bean);
/*  491 */     bean.setUp(getCNode_60a123());
/*  492 */     bean.setDown(getCNode_5c6b57());
/*  493 */     bean.setDividerLocation(25.0F);
/*  494 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  495 */     invokeInitializingBean(bean);
/*  496 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_60a123() {
/*  500 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#60a123") != null)
/*  501 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#60a123");
/*  502 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  503 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#60a123", bean);
/*  504 */     bean.setComponent(getListToolbarPnl());
/*  505 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  506 */     invokeInitializingBean(bean);
/*  507 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_5c6b57() {
/*  511 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#5c6b57") != null)
/*  512 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#5c6b57");
/*  513 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  514 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#5c6b57", bean);
/*  515 */     bean.setName(getI18nFB_1f3072e());
/*  516 */     bean.setComponent(getListView());
/*  517 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  518 */     invokeInitializingBean(bean);
/*  519 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_1f3072e() {
/*  523 */     if (this.context.get("nc.ui.uif2.I18nFB#1f3072e") != null)
/*  524 */       return (String)this.context.get("nc.ui.uif2.I18nFB#1f3072e");
/*  525 */     nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
/*  526 */     this.context.put("&nc.ui.uif2.I18nFB#1f3072e", bean);bean.setResDir("common");
/*  527 */     bean.setResId("UC001-0000107");
/*  528 */     bean.setDefaultValue("列表");
/*  529 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  530 */     invokeInitializingBean(bean);
/*      */     try {
/*  532 */       Object product = bean.getObject();
/*  533 */       this.context.put("nc.ui.uif2.I18nFB#1f3072e", product);
/*  534 */       return (String)product;
/*      */     } catch (Exception e) {
/*  536 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/*  539 */   private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_402dd5() { if (this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#402dd5") != null)
/*  540 */       return (nc.ui.uif2.tangramlayout.node.VSNode)this.context.get("nc.ui.uif2.tangramlayout.node.VSNode#402dd5");
/*  541 */     nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
/*  542 */     this.context.put("nc.ui.uif2.tangramlayout.node.VSNode#402dd5", bean);
/*  543 */     bean.setUp(getCNode_8cfd1d());
/*  544 */     bean.setDown(getCNode_12d70ac());
/*  545 */     bean.setDividerLocation(30.0F);
/*  546 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  547 */     invokeInitializingBean(bean);
/*  548 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_8cfd1d() {
/*  552 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#8cfd1d") != null)
/*  553 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#8cfd1d");
/*  554 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  555 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#8cfd1d", bean);
/*  556 */     bean.setComponent(getCardToolbarPnl());
/*  557 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  558 */     invokeInitializingBean(bean);
/*  559 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.tangramlayout.node.CNode getCNode_12d70ac() {
/*  563 */     if (this.context.get("nc.ui.uif2.tangramlayout.node.CNode#12d70ac") != null)
/*  564 */       return (nc.ui.uif2.tangramlayout.node.CNode)this.context.get("nc.ui.uif2.tangramlayout.node.CNode#12d70ac");
/*  565 */     nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
/*  566 */     this.context.put("nc.ui.uif2.tangramlayout.node.CNode#12d70ac", bean);
/*  567 */     bean.setComponent(getBillFormEditor());
/*  568 */     bean.setName(getI18nFB_3d7e47());
/*  569 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  570 */     invokeInitializingBean(bean);
/*  571 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_3d7e47() {
/*  575 */     if (this.context.get("nc.ui.uif2.I18nFB#3d7e47") != null)
/*  576 */       return (String)this.context.get("nc.ui.uif2.I18nFB#3d7e47");
/*  577 */     nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
/*  578 */     this.context.put("&nc.ui.uif2.I18nFB#3d7e47", bean);bean.setResDir("common");
/*  579 */     bean.setResId("UC001-0000106");
/*  580 */     bean.setDefaultValue("卡片");
/*  581 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  582 */     invokeInitializingBean(bean);
/*      */     try {
/*  584 */       Object product = bean.getObject();
/*  585 */       this.context.put("nc.ui.uif2.I18nFB#3d7e47", product);
/*  586 */       return (String)product;
/*      */     } catch (Exception e) {
/*  588 */       throw new RuntimeException(e);
/*      */     } }
/*      */   
/*  591 */   public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader() { if (this.context.get("billLazilyLoader") != null)
/*  592 */       return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)this.context.get("billLazilyLoader");
/*  593 */     nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
/*  594 */     this.context.put("billLazilyLoader", bean);
/*  595 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  596 */     invokeInitializingBean(bean);
/*  597 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator() {
/*  601 */     if (this.context.get("LasilyLodadMediator") != null)
/*  602 */       return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)this.context.get("LasilyLodadMediator");
/*  603 */     nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
/*  604 */     this.context.put("LasilyLodadMediator", bean);
/*  605 */     bean.setModel(getManageAppModel());
/*  606 */     bean.setLoader(getBillLazilyLoader());
/*  607 */     bean.setLazilyLoadSupporter(getManagedList11());
/*  608 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  609 */     invokeInitializingBean(bean);
/*  610 */     return bean;
/*      */   }
/*      */   
/*  613 */   private List getManagedList11() { List list = new java.util.ArrayList();list.add(getCardPanelLazilyLoad_92b58e());list.add(getListPanelLazilyLoad_1cda1d4());list.add(getActionLazilyLoad_13b8b26());return list;
/*      */   }
/*      */   
/*  616 */   private nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad getCardPanelLazilyLoad_92b58e() { if (this.context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#92b58e") != null)
/*  617 */       return (nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad)this.context.get("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#92b58e");
/*  618 */     nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad();
/*  619 */     this.context.put("nc.ui.pubapp.uif2app.lazilyload.CardPanelLazilyLoad#92b58e", bean);
/*  620 */     bean.setBillform(getBillFormEditor());
/*  621 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  622 */     invokeInitializingBean(bean);
/*  623 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad getListPanelLazilyLoad_1cda1d4() {
/*  627 */     if (this.context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1cda1d4") != null)
/*  628 */       return (nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad)this.context.get("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1cda1d4");
/*  629 */     nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad();
/*  630 */     this.context.put("nc.ui.pubapp.uif2app.lazilyload.ListPanelLazilyLoad#1cda1d4", bean);
/*  631 */     bean.setListView(getListView());
/*  632 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  633 */     invokeInitializingBean(bean);
/*  634 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getActionLazilyLoad_13b8b26() {
/*  638 */     if (this.context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#13b8b26") != null)
/*  639 */       return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)this.context.get("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#13b8b26");
/*  640 */     nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
/*  641 */     this.context.put("nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad#13b8b26", bean);
/*  642 */     bean.setModel(getManageAppModel());
/*  643 */     bean.setActionList(getManagedList12());
/*  644 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  645 */     invokeInitializingBean(bean);
/*  646 */     return bean;
/*      */   }
/*      */   
/*  649 */   private List getManagedList12() { List list = new java.util.ArrayList();list.add(getPrintAction());list.add(getPreviewAction());list.add(getOutputAction());return list;
/*      */   }
/*      */   
/*  652 */   public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea() { if (this.context.get("queryArea") != null)
/*  653 */       return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)this.context.get("queryArea");
/*  654 */     nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
/*  655 */     this.context.put("queryArea", bean);
/*  656 */     bean.setQueryAreaCreator(getQueryAction());
/*  657 */     bean.initUI();
/*  658 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  659 */     invokeInitializingBean(bean);
/*  660 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getListToolbarPnl() {
/*  664 */     if (this.context.get("listToolbarPnl") != null)
/*  665 */       return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)this.context.get("listToolbarPnl");
/*  666 */     nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
/*  667 */     this.context.put("listToolbarPnl", bean);
/*  668 */     bean.setModel(getManageAppModel());
/*  669 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  670 */     invokeInitializingBean(bean);
/*  671 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardToolbarPnl() {
/*  675 */     if (this.context.get("cardToolbarPnl") != null)
/*  676 */       return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)this.context.get("cardToolbarPnl");
/*  677 */     nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
/*  678 */     this.context.put("cardToolbarPnl", bean);
/*  679 */     bean.setTitleAction(getReturnaction());
/*  680 */     bean.setModel(getManageAppModel());
/*  681 */     bean.setRightExActions(getManagedList13());
/*  682 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  683 */     invokeInitializingBean(bean);
/*  684 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction() {
/*  688 */     if (this.context.get("returnaction") != null)
/*  689 */       return (nc.ui.pubapp.uif2app.actions.UEReturnAction)this.context.get("returnaction");
/*  690 */     nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
/*  691 */     this.context.put("returnaction", bean);
/*  692 */     bean.setGoComponent(getListView());
/*  693 */     bean.setSaveAction(getSaveAction());
/*  694 */     bean.setModel(getManageAppModel());
/*  695 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  696 */     invokeInitializingBean(bean);
/*  697 */     return bean;
/*      */   }
/*      */   
/*  700 */   private List getManagedList13() { List list = new java.util.ArrayList();list.add(getActionsBar_ActionsBarSeparator_2c9a95());list.add(getHeadZoomAction());return list;
/*      */   }
/*      */   
/*  703 */   private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_2c9a95() { if (this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2c9a95") != null)
/*  704 */       return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)this.context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2c9a95");
/*  705 */     nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
/*  706 */     this.context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2c9a95", bean);
/*  707 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  708 */     invokeInitializingBean(bean);
/*  709 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction() {
/*  713 */     if (this.context.get("headZoomAction") != null)
/*  714 */       return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)this.context.get("headZoomAction");
/*  715 */     nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
/*  716 */     this.context.put("headZoomAction", bean);
/*  717 */     bean.setBillForm(getBillFormEditor());
/*  718 */     bean.setModel(getManageAppModel());
/*  719 */     bean.setPos(0);
/*  720 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  721 */     invokeInitializingBean(bean);
/*  722 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors() {
/*  726 */     if (this.context.get("toftpanelActionContributors") != null)
/*  727 */       return (nc.ui.uif2.actions.ActionContributors)this.context.get("toftpanelActionContributors");
/*  728 */     nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
/*  729 */     this.context.put("toftpanelActionContributors", bean);
/*  730 */     bean.setContributors(getManagedList14());
/*  731 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  732 */     invokeInitializingBean(bean);
/*  733 */     return bean;
/*      */   }
/*      */   
/*  736 */   private List getManagedList14() { List list = new java.util.ArrayList();list.add(getActionsOfList());list.add(getActionsOfCard());return list;
/*      */   }
/*      */   
/*  739 */   public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList() { if (this.context.get("actionsOfList") != null)
/*  740 */       return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)this.context.get("actionsOfList");
/*  741 */     nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());this.context.put("actionsOfList", bean);
/*  742 */     bean.setActions(getManagedList15());
/*  743 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  744 */     invokeInitializingBean(bean);
/*  745 */     return bean;
/*      */   }
/*      */   
/*  748 */   private List getManagedList15() { List list = new java.util.ArrayList();list.add(getAddAction());list.add(getEditAction());list.add(getDeleteAction());list.add(getCopyAction());list.add(getSeparatorAction());list.add(getQueryAction());list.add(getListRefreshAction());list.add(getSeparatorAction());list.add(getSendActionGroup());list.add(getApproveGroupAction());list.add(getAssitFuncActionGroup());list.add(getSeparatorAction());list.add(getLinkQueryActionGroup());list.add(getSeparatorAction());list.add(getPrintActionGroup());return list;
/*      */   }
/*      */   
/*  751 */   public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard() { if (this.context.get("actionsOfCard") != null)
/*  752 */       return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)this.context.get("actionsOfCard");
/*  753 */     nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());this.context.put("actionsOfCard", bean);
/*  754 */     bean.setActions(getManagedList16());
/*  755 */     bean.setEditActions(getManagedList17());
/*  756 */     bean.setModel(getManageAppModel());
/*  757 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  758 */     invokeInitializingBean(bean);
/*  759 */     return bean;
/*      */   }
/*      */   
/*  762 */   private List getManagedList16() { List list = new java.util.ArrayList();list.add(getAddAction());list.add(getEditAction());list.add(getDeleteAction());list.add(getCopyAction());list.add(getSeparatorAction());list.add(getQueryAction());list.add(getCardRefreshAction());list.add(getSeparatorAction());list.add(getSendActionGroup());list.add(getApproveGroupAction());list.add(getAssitFuncActionGroup());list.add(getSeparatorAction());list.add(getLinkQueryActionGroup());list.add(getSeparatorAction());list.add(getPrintActionGroup());return list; }
/*      */   
/*  764 */   private List getManagedList17() { List list = new java.util.ArrayList();list.add(getSaveAction());list.add(getSeparatorAction());list.add(getCancelAction());list.add(getSeparatorAction());list.add(getCalcHyBridPriceAction());list.add(getSeparatorAction());list.add(getEditAssitFuncActionGroup());list.add(getSeparatorAction());list.add(getEditLinkQueryActionGroup());return list;
/*      */   }
/*      */   
/*  767 */   public nc.funcnode.ui.action.SeparatorAction getSeparatorAction() { if (this.context.get("separatorAction") != null)
/*  768 */       return (nc.funcnode.ui.action.SeparatorAction)this.context.get("separatorAction");
/*  769 */     nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
/*  770 */     this.context.put("separatorAction", bean);
/*  771 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  772 */     invokeInitializingBean(bean);
/*  773 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.PreOrderAddAction getAddAction() {
/*  777 */     if (this.context.get("addAction") != null)
/*  778 */       return (nc.ui.so.m38.billui.action.PreOrderAddAction)this.context.get("addAction");
/*  779 */     nc.ui.so.m38.billui.action.PreOrderAddAction bean = new nc.ui.so.m38.billui.action.PreOrderAddAction();
/*  780 */     this.context.put("addAction", bean);
/*  781 */     bean.setModel(getManageAppModel());
/*  782 */     bean.setEditor(getBillFormEditor());
/*  783 */     bean.setInterceptor(getFormInterceptor());
/*  784 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  785 */     invokeInitializingBean(bean);
/*  786 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.PreOrderSaveAction getSaveAction() {
/*  790 */     if (this.context.get("saveAction") != null)
/*  791 */       return (nc.ui.so.m38.billui.action.PreOrderSaveAction)this.context.get("saveAction");
/*  792 */     nc.ui.so.m38.billui.action.PreOrderSaveAction bean = new nc.ui.so.m38.billui.action.PreOrderSaveAction();
/*  793 */     this.context.put("saveAction", bean);
/*  794 */     bean.setModel(getManageAppModel());
/*  795 */     bean.setEditor(getBillFormEditor());
/*  796 */     bean.setValidationService(getCompositevalidateService());
/*  797 */     bean.setActionName("WRITE");
/*  798 */     bean.setBillType("38");
/*  799 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  800 */     invokeInitializingBean(bean);
/*  801 */     return bean;
/*      */   }

			 public nc.funcnode.ui.action.GroupAction getSendActionGroup() {
				
				if (this.context.get("sendActionGroup") != null)
				  return (nc.funcnode.ui.action.GroupAction)this.context.get("sendActionGroup");
				
				nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
				this.context.put("sendActionGroup", bean);
				bean.setActions(getManagedList30());
				setBeanFacotryIfBeanFacatoryAware(bean);
				invokeInitializingBean(bean);
				return bean;
				
			 }
			 
             private List<Action> getManagedList30() {
				// TODO 自动生成的方法存根
            	 
            	 List list = new java.util.ArrayList();
            	 list.add(getSendApproveAction());
            	 list.add(getUnsendApproveAction());
            	 
            	 return list;

			 }
             
             

             private nc.ui.so.m38.billui.action.PreOrderUnSendApproveAction getUnsendApproveAction() {
				// TODO 自动生成的方法存根
            	 if (this.context.get("unsendApproveAction") != null)
            		 return (nc.ui.so.m38.billui.action.PreOrderUnSendApproveAction)this.context.get("unsendApproveAction");
            	 
            	 nc.ui.so.m38.billui.action.PreOrderUnSendApproveAction bean=new nc.ui.so.m38.billui.action.PreOrderUnSendApproveAction();
            	 this.context.put("unsendApproveAction", bean);
            	 bean.setModel(getManageAppModel());
            	 bean.setEditor(getBillFormEditor());
            	 bean.setFilledUpInFlow(true);
            	 bean.setActionName("UNSAVE");
            	 bean.setBillType("38");
            	 invokeInitializingBean(bean);
            	 return bean;
         
             }
             
             private nc.ui.so.m38.billui.action.PreOrderSendApproveAction getSendApproveAction() {
				// TODO 自动生成的方法存根
            	 if (this.context.get("sendApproveAction") != null)
            		 return (nc.ui.so.m38.billui.action.PreOrderSendApproveAction)this.context.get("sendApproveAction");
            	 
            	 nc.ui.so.m38.billui.action.PreOrderSendApproveAction bean=new nc.ui.so.m38.billui.action.PreOrderSendApproveAction();
            	 this.context.put("sendApproveAction", bean);
            	 bean.setModel(getManageAppModel());
            	 bean.setEditor(getBillFormEditor());
            	 bean.setFilledUpInFlow(true);
            	 bean.setActionName("SAVE");
            	 bean.setBillType("38");
            	 invokeInitializingBean(bean);
            	 return bean;
            	 
			 }
             
             
             
/*      */   public nc.ui.pubapp.uif2app.validation.CompositeValidation getCompositevalidateService() {
/*  805 */     if (this.context.get("compositevalidateService") != null)
/*  806 */       return (nc.ui.pubapp.uif2app.validation.CompositeValidation)this.context.get("compositevalidateService");
/*  807 */     nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
/*  808 */     this.context.put("compositevalidateService", bean);
/*  809 */     bean.setValidators(getManagedList18());
/*  810 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  811 */     invokeInitializingBean(bean);
/*  812 */     return bean;
/*      */   }
/*      */   
/*  815 */   private List getManagedList18() { List list = new java.util.ArrayList();list.add(getValidateService());return list;
/*      */   }
/*      */   
/*  818 */   public PreOrderValidationService getValidateService() { if (this.context.get("validateService") != null)
/*  819 */       return (PreOrderValidationService)this.context.get("validateService");
/*  820 */     PreOrderValidationService bean = new PreOrderValidationService();
/*  821 */     this.context.put("validateService", bean);
/*  822 */     bean.setEditor(getBillFormEditor());
/*  823 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  824 */     invokeInitializingBean(bean);
/*  825 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction() {
/*  829 */     if (this.context.get("cancelAction") != null)
/*  830 */       return (nc.ui.pubapp.uif2app.actions.CancelAction)this.context.get("cancelAction");
/*  831 */     nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
/*  832 */     this.context.put("cancelAction", bean);
/*  833 */     bean.setModel(getManageAppModel());
/*  834 */     bean.setEditor(getBillFormEditor());
/*  835 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  836 */     invokeInitializingBean(bean);
/*  837 */     return bean;
/*      */   }

			public nc.ui.so.m38.billui.action.CalcHyBridPriceAction getCalcHyBridPriceAction() {
/*  829 */     if (this.context.get("CalcHyBridPriceAction") != null)
/*  830 */       return (nc.ui.so.m38.billui.action.CalcHyBridPriceAction)this.context.get("CalcHyBridPriceAction");
/*  831 */     nc.ui.so.m38.billui.action.CalcHyBridPriceAction bean = new nc.ui.so.m38.billui.action.CalcHyBridPriceAction();
/*  832 */     this.context.put("cancelAction", bean);
/*  833 */     bean.setModel(getManageAppModel());
/*  834 */     bean.setEditor(getBillFormEditor());
/*  835 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  836 */     invokeInitializingBean(bean);
/*  837 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction() {
/*  841 */     if (this.context.get("queryAction") != null)
/*  842 */       return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)this.context.get("queryAction");
/*  843 */     nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
/*  844 */     this.context.put("queryAction", bean);
/*  845 */     bean.setModel(getManageAppModel());
/*  846 */     bean.setDataManager(getModelDataManager());
/*  847 */     bean.setQryCondDLGInitializer(getPreorderQryCondDLGInitializer());
/*  848 */     bean.setInterceptor(getListInterceptor());
/*  849 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  850 */     invokeInitializingBean(bean);
/*  851 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer getPreorderQryCondDLGInitializer() {
/*  855 */     if (this.context.get("preorderQryCondDLGInitializer") != null)
/*  856 */       return (nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer)this.context.get("preorderQryCondDLGInitializer");
/*  857 */     nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer bean = new nc.ui.so.m38.billui.query.PreOrderQryCondDLGInitializer();
/*  858 */     this.context.put("preorderQryCondDLGInitializer", bean);
/*  859 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  860 */     invokeInitializingBean(bean);
/*  861 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getListRefreshAction() {
/*  865 */     if (this.context.get("listRefreshAction") != null)
/*  866 */       return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)this.context.get("listRefreshAction");
/*  867 */     nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
/*  868 */     this.context.put("listRefreshAction", bean);
/*  869 */     bean.setDataManager(getModelDataManager());
/*  870 */     bean.setModel(getManageAppModel());
/*  871 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  872 */     invokeInitializingBean(bean);
/*  873 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction() {
/*  877 */     if (this.context.get("cardRefreshAction") != null)
/*  878 */       return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)this.context.get("cardRefreshAction");
/*  879 */     nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
/*  880 */     this.context.put("cardRefreshAction", bean);
/*  881 */     bean.setModel(getManageAppModel());
/*  882 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  883 */     invokeInitializingBean(bean);
/*  884 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.PreOrderEditAction getEditAction() {
/*  888 */     if (this.context.get("editAction") != null)
/*  889 */       return (nc.ui.so.m38.billui.action.PreOrderEditAction)this.context.get("editAction");
/*  890 */     nc.ui.so.m38.billui.action.PreOrderEditAction bean = new nc.ui.so.m38.billui.action.PreOrderEditAction();
/*  891 */     this.context.put("editAction", bean);
/*  892 */     bean.setModel(getManageAppModel());
/*  893 */     bean.setEditor(getBillFormEditor());
/*  894 */     bean.setInterceptor(getFormInterceptor());
/*  895 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  896 */     invokeInitializingBean(bean);
/*  897 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.CopyAction getCopyAction() {
/*  901 */     if (this.context.get("copyAction") != null)
/*  902 */       return (nc.ui.pubapp.uif2app.actions.CopyAction)this.context.get("copyAction");
/*  903 */     nc.ui.pubapp.uif2app.actions.CopyAction bean = new nc.ui.pubapp.uif2app.actions.CopyAction();
/*  904 */     this.context.put("copyAction", bean);
/*  905 */     bean.setModel(getManageAppModel());
/*  906 */     bean.setEditor(getBillFormEditor());
/*  907 */     bean.setCopyActionProcessor(getCopyActionProcessor_195e4cf());
/*  908 */     bean.setInterceptor(getFormInterceptor());
/*  909 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  910 */     invokeInitializingBean(bean);
/*  911 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.so.m38.billui.action.process.CopyActionProcessor getCopyActionProcessor_195e4cf() {
/*  915 */     if (this.context.get("nc.ui.so.m38.billui.action.process.CopyActionProcessor#195e4cf") != null)
/*  916 */       return (nc.ui.so.m38.billui.action.process.CopyActionProcessor)this.context.get("nc.ui.so.m38.billui.action.process.CopyActionProcessor#195e4cf");
/*  917 */     nc.ui.so.m38.billui.action.process.CopyActionProcessor bean = new nc.ui.so.m38.billui.action.process.CopyActionProcessor();
/*  918 */     this.context.put("nc.ui.so.m38.billui.action.process.CopyActionProcessor#195e4cf", bean);
/*  919 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  920 */     invokeInitializingBean(bean);
/*  921 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.PreOrderDeleteAction getDeleteAction() {
/*  925 */     if (this.context.get("deleteAction") != null)
/*  926 */       return (nc.ui.so.m38.billui.action.PreOrderDeleteAction)this.context.get("deleteAction");
/*  927 */     nc.ui.so.m38.billui.action.PreOrderDeleteAction bean = new nc.ui.so.m38.billui.action.PreOrderDeleteAction();
/*  928 */     this.context.put("deleteAction", bean);
/*  929 */     bean.setModel(getManageAppModel());
/*  930 */     bean.setEditor(getBillFormEditor());
/*  931 */     bean.setActionName("DELETE");
/*  932 */     bean.setBillType("38");
/*  933 */     bean.setValidationService(getPowerdeletevalidservice());
/*  934 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  935 */     invokeInitializingBean(bean);
/*  936 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerdeletevalidservice() {
/*  940 */     if (this.context.get("powerdeletevalidservice") != null)
/*  941 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerdeletevalidservice");
/*  942 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/*  943 */     this.context.put("powerdeletevalidservice", bean);
/*  944 */     bean.setActionCode("delete");
/*  945 */     bean.setBillCodeFiledName("vbillcode");
/*  946 */     bean.setPermissionCode("38");
/*  947 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  948 */     invokeInitializingBean(bean);
/*  949 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.GroupAction getApproveGroupAction() {
/*  953 */     if (this.context.get("approveGroupAction") != null)
/*  954 */       return (nc.funcnode.ui.action.GroupAction)this.context.get("approveGroupAction");
/*  955 */     nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
/*  956 */     this.context.put("approveGroupAction", bean);
/*  957 */     bean.setCode("assitFunc");
/*  958 */     bean.setName(getI18nFB_1e0fada());
/*  959 */     bean.setActions(getManagedList19());
/*  960 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  961 */     invokeInitializingBean(bean);
/*  962 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_1e0fada() {
/*  966 */     if (this.context.get("nc.ui.uif2.I18nFB#1e0fada") != null)
/*  967 */       return (String)this.context.get("nc.ui.uif2.I18nFB#1e0fada");
/*  968 */     nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
/*  969 */     this.context.put("&nc.ui.uif2.I18nFB#1e0fada", bean);bean.setResDir("common");
/*  970 */     bean.setResId("UC001-0000027");
/*  971 */     bean.setDefaultValue("审核");
/*  972 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  973 */     invokeInitializingBean(bean);
/*      */     try {
/*  975 */       Object product = bean.getObject();
/*  976 */       this.context.put("nc.ui.uif2.I18nFB#1e0fada", product);
/*  977 */       return (String)product;
/*      */     } catch (Exception e) {
/*  979 */       throw new RuntimeException(e); } }
/*      */   
/*  981 */   private List getManagedList19() { List list = new java.util.ArrayList();list.add(getApproveAction());list.add(getUnApproveAction());list.add(getQueryAuditFlowAction());return list;
/*      */   }
/*      */   
/*  984 */   public nc.ui.so.m38.billui.action.PreOrderUnApproveAction getUnApproveAction() { if (this.context.get("unApproveAction") != null)
/*  985 */       return (nc.ui.so.m38.billui.action.PreOrderUnApproveAction)this.context.get("unApproveAction");
/*  986 */     nc.ui.so.m38.billui.action.PreOrderUnApproveAction bean = new nc.ui.so.m38.billui.action.PreOrderUnApproveAction();
/*  987 */     this.context.put("unApproveAction", bean);
/*  988 */     bean.setModel(getManageAppModel());
/*  989 */     bean.setEditor(getBillFormEditor());
/*  990 */     bean.setFilledUpInFlow(true);
/*  991 */     bean.setActionName("UNAPPROVE");
/*  992 */     bean.setBillType("38");
/*  993 */     bean.setValidationService(getPowerunapprovevalidservice());
/*  994 */     setBeanFacotryIfBeanFacatoryAware(bean);
/*  995 */     invokeInitializingBean(bean);
/*  996 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerunapprovevalidservice() {
/* 1000 */     if (this.context.get("powerunapprovevalidservice") != null)
/* 1001 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerunapprovevalidservice");
/* 1002 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1003 */     this.context.put("powerunapprovevalidservice", bean);
/* 1004 */     bean.setActionCode("unapprove");
/* 1005 */     bean.setBillCodeFiledName("vbillcode");
/* 1006 */     bean.setPermissionCode("38");
/* 1007 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1008 */     invokeInitializingBean(bean);
/* 1009 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.PreOrderApproveAction getApproveAction() {
/* 1013 */     if (this.context.get("approveAction") != null)
/* 1014 */       return (nc.ui.so.m38.billui.action.PreOrderApproveAction)this.context.get("approveAction");
/* 1015 */     nc.ui.so.m38.billui.action.PreOrderApproveAction bean = new nc.ui.so.m38.billui.action.PreOrderApproveAction();
/* 1016 */     this.context.put("approveAction", bean);
/* 1017 */     bean.setModel(getManageAppModel());
/* 1018 */     bean.setEditor(getBillFormEditor());
/* 1019 */     bean.setFilledUpInFlow(true);
/* 1020 */     bean.setActionName("APPROVE");
/* 1021 */     bean.setBillType("38");
/* 1022 */     bean.setValidationService(getPowerapprovevalidservice());
/* 1023 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1024 */     invokeInitializingBean(bean);
/* 1025 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.pub.power.PowerValidateService getPowerapprovevalidservice() {
/* 1029 */     if (this.context.get("powerapprovevalidservice") != null)
/* 1030 */       return (nc.ui.pubapp.pub.power.PowerValidateService)this.context.get("powerapprovevalidservice");
/* 1031 */     nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
/* 1032 */     this.context.put("powerapprovevalidservice", bean);
/* 1033 */     bean.setActionCode("approve");
/* 1034 */     bean.setBillCodeFiledName("vbillcode");
/* 1035 */     bean.setPermissionCode("38");
/* 1036 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1037 */     invokeInitializingBean(bean);
/* 1038 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction getAssitFuncActionGroup() {
/* 1042 */     if (this.context.get("assitFuncActionGroup") != null)
/* 1043 */       return (nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction)this.context.get("assitFuncActionGroup");
/* 1044 */     nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction();
/* 1045 */     this.context.put("assitFuncActionGroup", bean);
/* 1046 */     bean.setActions(getManagedList20());
/* 1047 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1048 */     invokeInitializingBean(bean);
/* 1049 */     return bean;
/*      */   }
/*      */   
/* 1052 */   private List getManagedList20() { List list = new java.util.ArrayList();list.add(getBillCloseAction());list.add(getBillOpenAction());list.add(getRowCloseAction());list.add(getRowOpenAction());list.add(getSeparatorAction());list.add(getPriceFormAction());list.add(getReviseAction());list.add(getSeparatorAction());list.add(getDocmngAction());return list;
/*      */   }
/*      */   
/* 1055 */   public nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction getEditAssitFuncActionGroup() { if (this.context.get("editAssitFuncActionGroup") != null)
/* 1056 */       return (nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction)this.context.get("editAssitFuncActionGroup");
/* 1057 */     nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderAssitMenuAction();
/* 1058 */     this.context.put("editAssitFuncActionGroup", bean);
/* 1059 */     bean.setActions(getManagedList21());
/* 1060 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1061 */     invokeInitializingBean(bean);
/* 1062 */     return bean;
/*      */   }
/*      */   
/* 1065 */   private List getManagedList21() { List list = new java.util.ArrayList();list.add(getAskqtAction());list.add(getPriceFormAction());return list;
/*      */   }
/*      */   
/* 1068 */   public nc.ui.so.m38.billui.action.assit.PreOrderCloseAction getBillCloseAction() { if (this.context.get("billCloseAction") != null)
/* 1069 */       return (nc.ui.so.m38.billui.action.assit.PreOrderCloseAction)this.context.get("billCloseAction");
/* 1070 */     nc.ui.so.m38.billui.action.assit.PreOrderCloseAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderCloseAction();
/* 1071 */     this.context.put("billCloseAction", bean);
/* 1072 */     bean.setModel(getManageAppModel());
/* 1073 */     bean.setEditor(getBillFormEditor());
/* 1074 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1075 */     invokeInitializingBean(bean);
/* 1076 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderOpenAction getBillOpenAction() {
/* 1080 */     if (this.context.get("billOpenAction") != null)
/* 1081 */       return (nc.ui.so.m38.billui.action.assit.PreOrderOpenAction)this.context.get("billOpenAction");
/* 1082 */     nc.ui.so.m38.billui.action.assit.PreOrderOpenAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderOpenAction();
/* 1083 */     this.context.put("billOpenAction", bean);
/* 1084 */     bean.setModel(getManageAppModel());
/* 1085 */     bean.setEditor(getBillFormEditor());
/* 1086 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1087 */     invokeInitializingBean(bean);
/* 1088 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderRowCloseAction getRowCloseAction() {
/* 1092 */     if (this.context.get("rowCloseAction") != null)
/* 1093 */       return (nc.ui.so.m38.billui.action.assit.PreOrderRowCloseAction)this.context.get("rowCloseAction");
/* 1094 */     nc.ui.so.m38.billui.action.assit.PreOrderRowCloseAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderRowCloseAction();
/* 1095 */     this.context.put("rowCloseAction", bean);
/* 1096 */     bean.setModel(getManageAppModel());
/* 1097 */     bean.setEditor(getBillFormEditor());
/* 1098 */     bean.setListView(getListView());
/* 1099 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1100 */     invokeInitializingBean(bean);
/* 1101 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderRowOpenAction getRowOpenAction() {
/* 1105 */     if (this.context.get("rowOpenAction") != null)
/* 1106 */       return (nc.ui.so.m38.billui.action.assit.PreOrderRowOpenAction)this.context.get("rowOpenAction");
/* 1107 */     nc.ui.so.m38.billui.action.assit.PreOrderRowOpenAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderRowOpenAction();
/* 1108 */     this.context.put("rowOpenAction", bean);
/* 1109 */     bean.setModel(getManageAppModel());
/* 1110 */     bean.setEditor(getBillFormEditor());
/* 1111 */     bean.setListView(getListView());
/* 1112 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1113 */     invokeInitializingBean(bean);
/* 1114 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderPriceFormAction getPriceFormAction() {
/* 1118 */     if (this.context.get("priceFormAction") != null)
/* 1119 */       return (nc.ui.so.m38.billui.action.assit.PreOrderPriceFormAction)this.context.get("priceFormAction");
/* 1120 */     nc.ui.so.m38.billui.action.assit.PreOrderPriceFormAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderPriceFormAction();
/* 1121 */     this.context.put("priceFormAction", bean);
/* 1122 */     bean.setModel(getManageAppModel());
/* 1123 */     bean.setEditor(getBillFormEditor());
/* 1124 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1125 */     invokeInitializingBean(bean);
/* 1126 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderReviseAction getReviseAction() {
/* 1130 */     if (this.context.get("reviseAction") != null)
/* 1131 */       return (nc.ui.so.m38.billui.action.assit.PreOrderReviseAction)this.context.get("reviseAction");
/* 1132 */     nc.ui.so.m38.billui.action.assit.PreOrderReviseAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderReviseAction();
/* 1133 */     this.context.put("reviseAction", bean);
/* 1134 */     bean.setModel(getManageAppModel());
/* 1135 */     bean.setEditor(getBillFormEditor());
/* 1136 */     bean.setInterceptor(getFormInterceptor());
/* 1137 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1138 */     invokeInitializingBean(bean);
/* 1139 */     return bean;
/*      */   }


			public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getQueryAuditFlowAction() {
/* 2083 */     if (this.context.get("queryAuditFlowAction") != null)
/* 2084 */       return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)this.context.get("queryAuditFlowAction");
/* 2085 */     nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
/* 2086 */     this.context.put("queryAuditFlowAction", bean);
/* 2087 */     bean.setModel(getManageAppModel());
/* 2088 */     bean.setEditor(getBillFormEditor());
/* 2089 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 2090 */     invokeInitializingBean(bean);
/* 2091 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.assit.PreOrderAskqtAction getAskqtAction() {
/* 1143 */     if (this.context.get("askqtAction") != null)
/* 1144 */       return (nc.ui.so.m38.billui.action.assit.PreOrderAskqtAction)this.context.get("askqtAction");
/* 1145 */     nc.ui.so.m38.billui.action.assit.PreOrderAskqtAction bean = new nc.ui.so.m38.billui.action.assit.PreOrderAskqtAction();
/* 1146 */     this.context.put("askqtAction", bean);
/* 1147 */     bean.setModel(getManageAppModel());
/* 1148 */     bean.setEditor(getBillFormEditor());
/* 1149 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1150 */     invokeInitializingBean(bean);
/* 1151 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.pub.actions.SOManageDocumentAction getDocmngAction() {
/* 1155 */     if (this.context.get("docmngAction") != null)
/* 1156 */       return (nc.ui.so.pub.actions.SOManageDocumentAction)this.context.get("docmngAction");
/* 1157 */     nc.ui.so.pub.actions.SOManageDocumentAction bean = new nc.ui.so.pub.actions.SOManageDocumentAction();
/* 1158 */     this.context.put("docmngAction", bean);
/* 1159 */     bean.setModel(getManageAppModel());
/* 1160 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1161 */     invokeInitializingBean(bean);
/* 1162 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction getLinkQueryActionGroup() {
/* 1166 */     if (this.context.get("linkQueryActionGroup") != null)
/* 1167 */       return (nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction)this.context.get("linkQueryActionGroup");
/* 1168 */     nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction bean = new nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction();
/* 1169 */     this.context.put("linkQueryActionGroup", bean);
/* 1170 */     bean.setActions(getManagedList22());
/* 1171 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1172 */     invokeInitializingBean(bean);
/* 1173 */     return bean;
/*      */   }
/*      */   
/* 1176 */   private List getManagedList22() { List list = new java.util.ArrayList();list.add(getLinkQueryAction());list.add(getSpShowHiddenAction());list.add(getCreditQueryAction());list.add(getEstProfitAction());return list;
/*      */   }
/*      */   
/* 1179 */   public nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction getEditLinkQueryActionGroup() { if (this.context.get("editLinkQueryActionGroup") != null)
/* 1180 */       return (nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction)this.context.get("editLinkQueryActionGroup");
/* 1181 */     nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction bean = new nc.ui.so.m38.billui.action.link.PreOrderLinkQueryMenuAction();
/* 1182 */     this.context.put("editLinkQueryActionGroup", bean);
/* 1183 */     bean.setActions(getManagedList23());
/* 1184 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1185 */     invokeInitializingBean(bean);
/* 1186 */     return bean;
/*      */   }
/*      */   
/* 1189 */   private List getManagedList23() { List list = new java.util.ArrayList();list.add(getSpShowHiddenAction());list.add(getCreditQueryAction());return list;
/*      */   }
/*      */   
/* 1192 */   public nc.ui.so.m38.billui.action.link.PreOrderLinkQueryAction getLinkQueryAction() { if (this.context.get("linkQueryAction") != null)
/* 1193 */       return (nc.ui.so.m38.billui.action.link.PreOrderLinkQueryAction)this.context.get("linkQueryAction");
/* 1194 */     nc.ui.so.m38.billui.action.link.PreOrderLinkQueryAction bean = new nc.ui.so.m38.billui.action.link.PreOrderLinkQueryAction();
/* 1195 */     this.context.put("linkQueryAction", bean);
/* 1196 */     bean.setModel(getManageAppModel());
/* 1197 */     bean.setBillType("38");
/* 1198 */     bean.setOpenMode(1);
/* 1199 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1200 */     invokeInitializingBean(bean);
/* 1201 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.link.PreOrderSPShowHidAction getSpShowHiddenAction() {
/* 1205 */     if (this.context.get("spShowHiddenAction") != null)
/* 1206 */       return (nc.ui.so.m38.billui.action.link.PreOrderSPShowHidAction)this.context.get("spShowHiddenAction");
/* 1207 */     nc.ui.so.m38.billui.action.link.PreOrderSPShowHidAction bean = new nc.ui.so.m38.billui.action.link.PreOrderSPShowHidAction();
/* 1208 */     this.context.put("spShowHiddenAction", bean);
/* 1209 */     bean.setContain(getContainer());
/* 1210 */     bean.setModel(getManageAppModel());
/* 1211 */     bean.setEditor(getBillFormEditor());
/* 1212 */     bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
/* 1213 */     bean.setLogincontext(getContext());
/* 1214 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1215 */     invokeInitializingBean(bean);
/* 1216 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.link.PreOrderCreditQueryAction getCreditQueryAction() {
/* 1220 */     if (this.context.get("creditQueryAction") != null)
/* 1221 */       return (nc.ui.so.m38.billui.action.link.PreOrderCreditQueryAction)this.context.get("creditQueryAction");
/* 1222 */     nc.ui.so.m38.billui.action.link.PreOrderCreditQueryAction bean = new nc.ui.so.m38.billui.action.link.PreOrderCreditQueryAction();
/* 1223 */     this.context.put("creditQueryAction", bean);
/* 1224 */     bean.setModel(getManageAppModel());
/* 1225 */     bean.setEditor(getBillFormEditor());
/* 1226 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1227 */     invokeInitializingBean(bean);
/* 1228 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.link.PreOrderEstProfitAction getEstProfitAction() {
/* 1232 */     if (this.context.get("estProfitAction") != null)
/* 1233 */       return (nc.ui.so.m38.billui.action.link.PreOrderEstProfitAction)this.context.get("estProfitAction");
/* 1234 */     nc.ui.so.m38.billui.action.link.PreOrderEstProfitAction bean = new nc.ui.so.m38.billui.action.link.PreOrderEstProfitAction();
/* 1235 */     this.context.put("estProfitAction", bean);
/* 1236 */     bean.setModel(getManageAppModel());
/* 1237 */     bean.setEditor(getBillFormEditor());
/* 1238 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1239 */     invokeInitializingBean(bean);
/* 1240 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.funcnode.ui.action.GroupAction getPrintActionGroup() {
/* 1244 */     if (this.context.get("printActionGroup") != null)
/* 1245 */       return (nc.funcnode.ui.action.GroupAction)this.context.get("printActionGroup");
/* 1246 */     nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
/* 1247 */     this.context.put("printActionGroup", bean);
/* 1248 */     bean.setCode("printMenuAction");
/* 1249 */     bean.setName(getI18nFB_1e5a6d8());
/* 1250 */     bean.setActions(getManagedList24());
/* 1251 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1252 */     invokeInitializingBean(bean);
/* 1253 */     return bean;
/*      */   }
/*      */   
/*      */   private String getI18nFB_1e5a6d8() {
/* 1257 */     if (this.context.get("nc.ui.uif2.I18nFB#1e5a6d8") != null)
/* 1258 */       return (String)this.context.get("nc.ui.uif2.I18nFB#1e5a6d8");
/* 1259 */     nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
/* 1260 */     this.context.put("&nc.ui.uif2.I18nFB#1e5a6d8", bean);bean.setResDir("common");
/* 1261 */     bean.setResId("UC001-0000007");
/* 1262 */     bean.setDefaultValue("打印");
/* 1263 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1264 */     invokeInitializingBean(bean);
/*      */     try {
/* 1266 */       Object product = bean.getObject();
/* 1267 */       this.context.put("nc.ui.uif2.I18nFB#1e5a6d8", product);
/* 1268 */       return (String)product;
/*      */     } catch (Exception e) {
/* 1270 */       throw new RuntimeException(e); } }
/*      */   
/* 1272 */   private List getManagedList24() { List list = new java.util.ArrayList();list.add(getPrintAction());list.add(getPreviewAction());list.add(getOutputAction());return list;
/*      */   }
/*      */   
/* 1275 */   public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction() { if (this.context.get("outputAction") != null)
/* 1276 */       return (nc.ui.pubapp.uif2app.actions.OutputAction)this.context.get("outputAction");
/* 1277 */     nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
/* 1278 */     this.context.put("outputAction", bean);
/* 1279 */     bean.setModel(getManageAppModel());
/* 1280 */     bean.setParent(getBillFormEditor());
/* 1281 */     bean.setBeforePrintDataProcess(getPrintProcessor());
/* 1282 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1283 */     invokeInitializingBean(bean);
/* 1284 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction getPreviewAction() {
/* 1288 */     if (this.context.get("previewAction") != null)
/* 1289 */       return (nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction)this.context.get("previewAction");
/* 1290 */     nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction bean = new nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction();
/* 1291 */     this.context.put("previewAction", bean);
/* 1292 */     bean.setPreview(true);
/* 1293 */     bean.setModel(getManageAppModel());
/* 1294 */     bean.setBeforePrintDataProcess(getPrintProcessor());
/* 1295 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1296 */     invokeInitializingBean(bean);
/* 1297 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.print.PreOrderPrintAction getPrintAction() {
/* 1301 */     if (this.context.get("printAction") != null)
/* 1302 */       return (nc.ui.so.m38.billui.action.print.PreOrderPrintAction)this.context.get("printAction");
/* 1303 */     nc.ui.so.m38.billui.action.print.PreOrderPrintAction bean = new nc.ui.so.m38.billui.action.print.PreOrderPrintAction();
/* 1304 */     this.context.put("printAction", bean);
/* 1305 */     bean.setPreview(false);
/* 1306 */     bean.setModel(getManageAppModel());
/* 1307 */     bean.setBeforePrintDataProcess(getPrintProcessor());
/* 1308 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1309 */     invokeInitializingBean(bean);
/* 1310 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.so.m38.billui.action.print.PreOrderPrintProcessor getPrintProcessor() {
/* 1314 */     if (this.context.get("printProcessor") != null)
/* 1315 */       return (nc.ui.so.m38.billui.action.print.PreOrderPrintProcessor)this.context.get("printProcessor");
/* 1316 */     nc.ui.so.m38.billui.action.print.PreOrderPrintProcessor bean = new nc.ui.so.m38.billui.action.print.PreOrderPrintProcessor();
/* 1317 */     this.context.put("printProcessor", bean);
/* 1318 */     bean.setModel(getManageAppModel());
/* 1319 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1320 */     invokeInitializingBean(bean);
/* 1321 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener() {
/* 1325 */     if (this.context.get("InitDataListener") != null)
/* 1326 */       return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)this.context.get("InitDataListener");
/* 1327 */     nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
/* 1328 */     this.context.put("InitDataListener", bean);
/* 1329 */     bean.setContext(getContext());
/* 1330 */     bean.setModel(getManageAppModel());
/* 1331 */     bean.setQueryAction(getQueryAction());
/* 1332 */     bean.setVoClassName("nc.vo.so.m38.entity.PreOrderVO");
/* 1333 */     bean.setAutoShowUpComponent(getBillFormEditor());
/* 1334 */     bean.setProcessorMap(getManagedMap1());
/* 1335 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1336 */     invokeInitializingBean(bean);
/* 1337 */     return bean;
/*      */   }
/*      */   
/* 1340 */   private Map getManagedMap1() { Map map = new java.util.HashMap();map.put("101", getPreOrderDataProcessor_2cbb29());return map;
/*      */   }
/*      */   
/* 1343 */   private PreOrderDataProcessor getPreOrderDataProcessor_2cbb29() { if (this.context.get("nc.ui.so.m38.billui.model.PreOrderDataProcessor#2cbb29") != null)
/* 1344 */       return (PreOrderDataProcessor)this.context.get("nc.ui.so.m38.billui.model.PreOrderDataProcessor#2cbb29");
/* 1345 */     PreOrderDataProcessor bean = new PreOrderDataProcessor();
/* 1346 */     this.context.put("nc.ui.so.m38.billui.model.PreOrderDataProcessor#2cbb29", bean);
/* 1347 */     bean.setModel(getManageAppModel());
/* 1348 */     bean.setEditor(getBillFormEditor());
/* 1349 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1350 */     invokeInitializingBean(bean);
/* 1351 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator() {
/* 1355 */     if (this.context.get("userdefAndMarAsstListPreparator") != null)
/* 1356 */       return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)this.context.get("userdefAndMarAsstListPreparator");
/* 1357 */     nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
/* 1358 */     this.context.put("userdefAndMarAsstListPreparator", bean);
/* 1359 */     bean.setBillListDataPrepares(getManagedList25());
/* 1360 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1361 */     invokeInitializingBean(bean);
/* 1362 */     return bean;
/*      */   }
/*      */   
/* 1365 */   private List getManagedList25() { List list = new java.util.ArrayList();list.add(getUserdefitemlistPreparator());list.add(getMarAsstPreparator());return list;
/*      */   }
/*      */   
/* 1368 */   public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator() { if (this.context.get("userdefAndMarAsstCardPreparator") != null)
/* 1369 */       return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)this.context.get("userdefAndMarAsstCardPreparator");
/* 1370 */     nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
/* 1371 */     this.context.put("userdefAndMarAsstCardPreparator", bean);
/* 1372 */     bean.setBillDataPrepares(getManagedList26());
/* 1373 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1374 */     invokeInitializingBean(bean);
/* 1375 */     return bean;
/*      */   }
/*      */   
/* 1378 */   private List getManagedList26() { List list = new java.util.ArrayList();list.add(getUserdefitemPreparator());list.add(getMarAsstPreparator());return list;
/*      */   }
/*      */   
/* 1381 */   public UserdefQueryParam getUserQueryParams1() { if (this.context.get("userQueryParams1") != null)
/* 1382 */       return (UserdefQueryParam)this.context.get("userQueryParams1");
/* 1383 */     UserdefQueryParam bean = new UserdefQueryParam();
/* 1384 */     this.context.put("userQueryParams1", bean);
/* 1385 */     bean.setMdfullname(" ic.OnhandDimVO");
/* 1386 */     bean.setPos(0);
/* 1387 */     bean.setPrefix("vdef");
/* 1388 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1389 */     invokeInitializingBean(bean);
/* 1390 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.userdefitem.QueryParam getQueryParams1() {
/* 1394 */     if (this.context.get("queryParams1") != null)
/* 1395 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("queryParams1");
/* 1396 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/* 1397 */     this.context.put("queryParams1", bean);
/* 1398 */     bean.setMdfullname("ic.OnhandDimVO");
/* 1399 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1400 */     invokeInitializingBean(bean);
/* 1401 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator() {
/* 1405 */     if (this.context.get("userdefitemPreparator") != null)
/* 1406 */       return (nc.ui.uif2.editor.UserdefitemContainerPreparator)this.context.get("userdefitemPreparator");
/* 1407 */     nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
/* 1408 */     this.context.put("userdefitemPreparator", bean);
/* 1409 */     bean.setContainer(getUserdefitemContainer());
/* 1410 */     bean.setParams(getManagedList27());
/* 1411 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1412 */     invokeInitializingBean(bean);
/* 1413 */     return bean;
/*      */   }
/*      */   
/* 1416 */   private List getManagedList27() { List list = new java.util.ArrayList();list.add(getUserQueryParams1());list.add(getUserdefQueryParam_b3b527());list.add(getUserdefQueryParam_3a14d3());return list;
/*      */   }
/*      */   
/* 1419 */   private UserdefQueryParam getUserdefQueryParam_b3b527() { if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#b3b527") != null)
/* 1420 */       return (UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#b3b527");
/* 1421 */     UserdefQueryParam bean = new UserdefQueryParam();
/* 1422 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#b3b527", bean);
/* 1423 */     bean.setMdfullname("so.preorder");
/* 1424 */     bean.setPos(0);
/* 1425 */     bean.setPrefix("vdef");
/* 1426 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1427 */     invokeInitializingBean(bean);
/* 1428 */     return bean;
/*      */   }
/*      */   
/*      */   private UserdefQueryParam getUserdefQueryParam_3a14d3() {
/* 1432 */     if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#3a14d3") != null)
/* 1433 */       return (UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#3a14d3");
/* 1434 */     UserdefQueryParam bean = new UserdefQueryParam();
/* 1435 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#3a14d3", bean);
/* 1436 */     bean.setMdfullname("so.preorder_b");
/* 1437 */     bean.setPos(1);
/* 1438 */     bean.setPrefix("vbdef");
/* 1439 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1440 */     invokeInitializingBean(bean);
/* 1441 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator() {
/* 1445 */     if (this.context.get("marAsstPreparator") != null)
/* 1446 */       return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)this.context.get("marAsstPreparator");
/* 1447 */     nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
/* 1448 */     this.context.put("marAsstPreparator", bean);
/* 1449 */     bean.setModel(getManageAppModel());
/* 1450 */     bean.setContainer(getUserdefitemContainer());
/* 1451 */     bean.setPrefix("vfree");
/* 1452 */     bean.setMaterialField("cmaterialid");
/* 1453 */     bean.setProjectField("cprojectid");
/* 1454 */     bean.setSupplierField("cvendorid");
/* 1455 */     bean.setProductorField("cproductorid");
/* 1456 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1457 */     invokeInitializingBean(bean);
/* 1458 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer() {
/* 1462 */     if (this.context.get("userdefitemContainer") != null)
/* 1463 */       return (nc.ui.uif2.userdefitem.UserDefItemContainer)this.context.get("userdefitemContainer");
/* 1464 */     nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
/* 1465 */     this.context.put("userdefitemContainer", bean);
/* 1466 */     bean.setContext(getContext());
/* 1467 */     bean.setParams(getManagedList28());
/* 1468 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1469 */     invokeInitializingBean(bean);
/* 1470 */     return bean;
/*      */   }
/*      */   
/* 1473 */   private List getManagedList28() { List list = new java.util.ArrayList();list.add(getQueryParams1());list.add(getQueryParam_12d5c0d());list.add(getQueryParam_f66a7f());list.add(getQueryParam_1c9ccdb());return list;
/*      */   }
/*      */   
/* 1476 */   private nc.ui.uif2.userdefitem.QueryParam getQueryParam_12d5c0d() { if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#12d5c0d") != null)
/* 1477 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("nc.ui.uif2.userdefitem.QueryParam#12d5c0d");
/* 1478 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/* 1479 */     this.context.put("nc.ui.uif2.userdefitem.QueryParam#12d5c0d", bean);
/* 1480 */     bean.setMdfullname("so.preorder");
/* 1481 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1482 */     invokeInitializingBean(bean);
/* 1483 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.userdefitem.QueryParam getQueryParam_f66a7f() {
/* 1487 */     if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#f66a7f") != null)
/* 1488 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("nc.ui.uif2.userdefitem.QueryParam#f66a7f");
/* 1489 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/* 1490 */     this.context.put("nc.ui.uif2.userdefitem.QueryParam#f66a7f", bean);
/* 1491 */     bean.setMdfullname("so.preorder_b");
/* 1492 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1493 */     invokeInitializingBean(bean);
/* 1494 */     return bean;
/*      */   }
/*      */   
/*      */   private nc.ui.uif2.userdefitem.QueryParam getQueryParam_1c9ccdb() {
/* 1498 */     if (this.context.get("nc.ui.uif2.userdefitem.QueryParam#1c9ccdb") != null)
/* 1499 */       return (nc.ui.uif2.userdefitem.QueryParam)this.context.get("nc.ui.uif2.userdefitem.QueryParam#1c9ccdb");
/* 1500 */     nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
/* 1501 */     this.context.put("nc.ui.uif2.userdefitem.QueryParam#1c9ccdb", bean);
/* 1502 */     bean.setRulecode("materialassistant");
/* 1503 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1504 */     invokeInitializingBean(bean);
/* 1505 */     return bean;
/*      */   }
/*      */   
/*      */   public String getSelfDef() {
/* 1509 */     if (this.context.get("selfDef") != null)
/* 1510 */       return (String)this.context.get("selfDef");
/* 1511 */     String bean = new String("vbdef");this.context.put("selfDef", bean);
/* 1512 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1513 */     invokeInitializingBean(bean);
/* 1514 */     return bean;
/*      */   }
/*      */   
/*      */   public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator() {
/* 1518 */     if (this.context.get("userdefitemlistPreparator") != null)
/* 1519 */       return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)this.context.get("userdefitemlistPreparator");
/* 1520 */     nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
/* 1521 */     this.context.put("userdefitemlistPreparator", bean);
/* 1522 */     bean.setContainer(getUserdefitemContainer());
/* 1523 */     bean.setParams(getManagedList29());
/* 1524 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1525 */     invokeInitializingBean(bean);
/* 1526 */     return bean;
/*      */   }
/*      */   
/* 1529 */   private List getManagedList29() { List list = new java.util.ArrayList();list.add(getUserdefQueryParam_1a863b5());list.add(getUserdefQueryParam_2a3123());return list;
/*      */   }
/*      */   
/* 1532 */   private UserdefQueryParam getUserdefQueryParam_1a863b5() { if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#1a863b5") != null)
/* 1533 */       return (UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#1a863b5");
/* 1534 */     UserdefQueryParam bean = new UserdefQueryParam();
/* 1535 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#1a863b5", bean);
/* 1536 */     bean.setMdfullname("so.preorder");
/* 1537 */     bean.setPos(0);
/* 1538 */     bean.setPrefix("vdef");
/* 1539 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1540 */     invokeInitializingBean(bean);
/* 1541 */     return bean;
/*      */   }
/*      */   
/*      */   private UserdefQueryParam getUserdefQueryParam_2a3123() {
/* 1545 */     if (this.context.get("nc.ui.uif2.editor.UserdefQueryParam#2a3123") != null)
/* 1546 */       return (UserdefQueryParam)this.context.get("nc.ui.uif2.editor.UserdefQueryParam#2a3123");
/* 1547 */     UserdefQueryParam bean = new UserdefQueryParam();
/* 1548 */     this.context.put("nc.ui.uif2.editor.UserdefQueryParam#2a3123", bean);
/* 1549 */     bean.setMdfullname("so.preorder_b");
/* 1550 */     bean.setTabcode("body");
/* 1551 */     bean.setPos(1);
/* 1552 */     bean.setPrefix("vbdef");
/* 1553 */     setBeanFacotryIfBeanFacatoryAware(bean);
/* 1554 */     invokeInitializingBean(bean);
/* 1555 */     return bean;
/*      */   }
/*      */ }

