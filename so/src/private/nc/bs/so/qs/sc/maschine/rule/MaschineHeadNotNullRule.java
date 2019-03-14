package nc.bs.so.qs.sc.maschine.rule;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import nc.bs.uif2.validation.ValidationException;
/*     */ import nc.bs.uif2.validation.ValidationFailure;
/*     */ import nc.impl.pubapp.pattern.rule.IRule;
/*     */ import nc.util.mmf.framework.base.MMArrayUtil;
/*     */ import nc.util.mmf.framework.base.MMValueCheck;
///*     */ import nc.vo.pd.pd0404.entity.AggWkVO;
///*     */ import nc.vo.pd.pd0404.entity.WkVO;
/*     */ import nc.vo.pd.pd0404.message.MMPDLangConstPd0404;
/*     */ import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.qs.sc.AggMaschineVO;
import nc.vo.so.qs.sc.MaschineVO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaschineHeadNotNullRule
/*     */   implements IRule<AggMaschineVO>
/*     */ {
/*     */   private List<String> codeList;
/*     */   private List<String> nameList;
/*     */   
/*     */   public MaschineHeadNotNullRule()
/*     */   {
/*  28 */     this.codeList = new ArrayList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  33 */     this.nameList = new ArrayList();
/*     */     
///*  35 */     this.codeList.add("pk_org");
///*  36 */     this.codeList.add("cwkid");
///*  37 */     this.codeList.add("ccalendarid");
///*  38 */     this.codeList.add("cshiftclassid");
///*  39 */     this.codeList.add("bprodline");
///*  40 */     this.codeList.add("nparallelnum");
///*     */     
///*  42 */     this.nameList.add(MMPDLangConstPd0404.getFLD_ORG());
///*  43 */     this.nameList.add(MMPDLangConstPd0404.getFLD_WK());
///*  44 */     this.nameList.add(MMPDLangConstPd0404.getFLD_WORKCALENDAR());
///*  45 */     this.nameList.add(MMPDLangConstPd0404.getFLD_SHIFTCLASS());
///*  46 */     this.nameList.add(MMPDLangConstPd0404.getFLD_BPRODINE());
///*  47 */     this.nameList.add(MMPDLangConstPd0404.getFLD_NPARALLELNUM());

/*  35 */     this.codeList.add("pk_org");
/*  36 */     this.codeList.add("machcode");
/*  37 */     this.codeList.add("machname");
/*  38 */     this.codeList.add("mstatus");

/*     */     MaschineVO masvo=new MaschineVO();

/*  42 */     this.nameList.add("组织");
/*  43 */     this.nameList.add("机器号");
/*  44 */     this.nameList.add("机器名称");
/*  45 */     this.nameList.add("状态");
/*     */   }
/*     */   
/*     */   public void process(AggMaschineVO[] vos)
/*     */   {
/*  52 */     if (MMArrayUtil.isEmpty(vos)) {
/*  53 */       return;
/*     */     }
/*  55 */     List<ValidationFailure> excpMsgList = new ArrayList();
/*  56 */     for (int i = 0; i < vos.length; i++) {
/*  57 */       MaschineVO vo = (MaschineVO)vos[i].getParent();
/*  58 */       for (int j = 0; j < this.codeList.size(); j++) {
/*  59 */         if (vo.getAttributeValue((String)this.codeList.get(j)) == null)
/*     */         {
/*     */ 
/*  62 */           excpMsgList.add(new ValidationFailure((String)this.nameList.get(j)+ "不能为空"));
/*     */         }
/*     */       }
///*  65 */       boolean bprodline = vo.getBprodline().booleanValue();
///*  66 */       if ((!bprodline) && ((!MMValueCheck.isEmpty(vo.getNpreparetime())) || (!MMValueCheck.isEmpty(vo.getNdisassemtime()))))
///*     */       {
///*  68 */         if (null == vo.getFtimeunit()) {
///*  69 */           excpMsgList.add(new ValidationFailure(MMPDLangConstPd0404.getHIT_TIMEUNITNULL()));
///*     */         }
///*     */       }
///*  72 */       if ((bprodline) && ((!MMValueCheck.isEmpty(vo.getNtime())) || (!MMValueCheck.isEmpty(vo.getNpreparetime())) || (!MMValueCheck.isEmpty(vo.getNdisassemtime()))))
///*     */       {
///*     */ 
///*  75 */         if (null == vo.getFtimeunit()) {
///*  76 */           excpMsgList.add(new ValidationFailure(MMPDLangConstPd0404.getHIT_TIMEUNITNULL()));
///*     */         }
///*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  86 */     if (!MMValueCheck.isEmpty(excpMsgList))
/*     */     {
///*  88 */       excpMsgList.add(0, new ValidationFailure("表头"));
/*  89 */       ExceptionUtils.wrappException(new ValidationException(excpMsgList));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCodeList(List<String> codeList)
/*     */   {
/* 100 */     this.codeList = codeList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNameList(List<String> nameList)
/*     */   {
/* 110 */     this.nameList = nameList;
/*     */   }
/*     */ }