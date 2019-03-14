package nc.vo.so.qs.sc.FQ01trantype.entity;
/*     */ 
/*     */ import nc.vo.pub.IVOMeta;
/*     */ import nc.vo.pub.SuperVO;
/*     */ import nc.vo.pub.lang.UFBoolean;
/*     */ import nc.vo.pub.lang.UFDateTime;
/*     */ import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
/*     */ 

/*     */ public class FQ01TranTypeVO
/*     */   extends SuperVO
/*     */ {
/*     */   private static final long serialVersionUID = 3003866045382547885L;
/*     */   public static final String BARRANGE = "barrange";
/*     */   public static final String BMODIFYASKEDQT = "bmodifyaskedqt";
/*     */   public static final String BMODIFYDISCOUNT = "bmodifydiscount";
/*     */   public static final String BMODIFYUNASKEDQT = "bmodifyunaskedqt";
/*     */   public static final String BMOREROWS = "bmorerows";
/*     */   public static final String BNOFINDPRICEHIT = "bnofindpricehit";
/*     */   public static final String CTRANTYPEID = "ctrantypeid";
/*     */   public static final String FASKQTRULE = "faskqtrule";
/*     */   public static final String FLARGESSGETQTRULE = "flargessgetqtrule";
/*     */   public static final String FMODIFYMNY = "fmodifymny";
/*     */   public static final String PK_GROUP = "pk_group";
/*     */   public static final String PK_TRANTYPE = "pk_trantype";
/*     */   public static final String TS = "ts";
/*     */   public static final String VTRANTYPECODE = "vtrantypecode";
/*     */   
/*     */   public FQ01TranTypeVO() {}
/*     */   
/*     */   public UFBoolean getBarrange()
/*     */   {
/*  33 */     return (UFBoolean)getAttributeValue("barrange");
/*     */   }
/*     */   
/*     */   public UFBoolean getBmodifyaskedqt() {
/*  37 */     return (UFBoolean)getAttributeValue("bmodifyaskedqt");
/*     */   }
/*     */   
/*     */   public UFBoolean getBmodifydiscount() {
/*  41 */     return (UFBoolean)getAttributeValue("bmodifydiscount");
/*     */   }
/*     */   
/*     */   public UFBoolean getBmodifyunaskedqt() {
/*  45 */     return (UFBoolean)getAttributeValue("bmodifyunaskedqt");
/*     */   }
/*     */   
/*     */   public UFBoolean getBmorerows() {
/*  49 */     return (UFBoolean)getAttributeValue("bmorerows");
/*     */   }
/*     */   
/*     */   public UFBoolean getBnofindpricehit() {
/* 53 */     return (UFBoolean)getAttributeValue("bnofindpricehit");
/*     */   }
/*     */   
/*     */   public String getCtrantypeid() {
/* 57 */     return (String)getAttributeValue("ctrantypeid");
/*     */   }
/*     */   
/*     */   public Integer getFaskqtrule() {
/* 61 */     return (Integer)getAttributeValue("faskqtrule");
/*     */   }
/*     */   
/*     */   public Integer getFlargessgetqtrule() {
/* 65 */     return (Integer)getAttributeValue("flargessgetqtrule");
/*     */   }
/*     */   
/*     */   public Integer getFmodifymny() {
/* 69 */     return (Integer)getAttributeValue("fmodifymny");
/*     */   }
/*     */   
/*     */   public IVOMeta getMetaData()
/*     */   {
/* 74 */     IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.m38trantype");
/* 75 */     return meta;
/*     */   }
/*     */   
/*     */   public String getPk_group() {
/* 79 */     return (String)getAttributeValue("pk_group");
/*     */   }
/*     */   
/*     */   public String getPkTrantype() {
/* 83 */     return (String)getAttributeValue("pk_trantype");
/*     */   }
/*     */   
/*     */   public UFDateTime getTs() {
/* 87 */     return (UFDateTime)getAttributeValue("ts");
/*     */   }
/*     */   
/*     */   public String getVtrantypecode() {
/* 91 */     return (String)getAttributeValue("vtrantypecode");
/*     */   }
/*     */   
/*     */   public void setBarrange(String barrange) {
/* 95 */     setAttributeValue("barrange", barrange);
/*     */   }
/*     */   
/*     */   public void setBmodifyaskedqt(String bmodifyaskedqt) {
/* 99 */     setAttributeValue("bmodifyaskedqt", bmodifyaskedqt);
/*     */   }
/*     */   
/*     */   public void setBmodifydiscount(String bmodifydiscount) {
/* 103 */     setAttributeValue("bmodifydiscount", bmodifydiscount);
/*     */   }
/*     */   
/*     */   public void setBmodifyunaskedqt(String bmodifyunaskedqt) {
/* 107 */     setAttributeValue("bmodifyunaskedqt", bmodifyunaskedqt);
/*     */   }
/*     */   
/*     */   public void setBmorerows(String bmorerows) {
/* 111 */     setAttributeValue("bmorerows", bmorerows);
/*     */   }
/*     */   
/*     */   public void setBnofindpricehit(String bnofindpricehit) {
/* 115 */     setAttributeValue("bnofindpricehit", bnofindpricehit);
/*     */   }
/*     */   
/*     */   public void setCtrantypeid(String ctrantypeid) {
/* 119 */     setAttributeValue("ctrantypeid", ctrantypeid);
/*     */   }
/*     */   
/*     */   public void setFaskqtrule(String faskqtrule) {
/* 123 */     setAttributeValue("faskqtrule", faskqtrule);
/*     */   }
/*     */   
/*     */   public void setFlargessgetqtrule(String flargessgetqtrule) {
/* 127 */     setAttributeValue("flargessgetqtrule", flargessgetqtrule);
/*     */   }
/*     */   
/*     */   public void setFmodifymny(String fmodifymny) {
/* 131 */     setAttributeValue("fmodifymny", fmodifymny);
/*     */   }
/*     */   
/*     */   public void setPk_group(String pk_group) {
/* 135 */     setAttributeValue("pk_group", pk_group);
/*     */   }
/*     */   
/*     */   public void setPkTrantype(String pkTrantype) {
/* 139 */     setAttributeValue("pk_trantype", pkTrantype);
/*     */   }
/*     */   
/*     */   public void setTs(UFDateTime ts) {
/* 143 */     setAttributeValue("ts", ts);
/*     */   }
/*     */   
/*     */   public void setVtrantypecode(String vtrantypecode) {
/* 147 */     setAttributeValue("vtrantypecode", vtrantypecode);
/*     */   }
/*     */ }