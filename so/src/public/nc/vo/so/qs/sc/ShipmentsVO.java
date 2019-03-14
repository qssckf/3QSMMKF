package nc.vo.so.qs.sc;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ShipmentsVO extends SuperVO {
/**
*审批时间
*/
public UFDateTime approvedate;
/**
*审批批语
*/
public String approvenote;
/**
*审批人
*/
public String approver;
/**
*审批状态
*/
public Integer approvestatus;
/**
*单据ID
*/
public String billid;
/**
*制单人
*/
public String billmaker;
/**
*单据号
*/
public String billno;
/**
*单据类型
*/
public String billtype;
/**
*单据版本pk
*/
public String billversionpk;
/**
*业务类型
*/
public String busitype;
/**
*整单来源单据ID
*/
public String cbilsrcid;
/**
*客户
*/
public String ccustomerid;
/**
*销售部门版本
*/
public String cdeptid;
/**
*销售部门
*/
public String cdeptvid;
/**
*业务员
*/
public String cemployeeid;
/**
*开票客户
*/
public String cinvoicecustid;
/**
*币种
*/
public String corigcurrencyid;
/**
*收款协议
*/
public String cpaytermid;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
/**
*运输方式
*/
public String ctransporttypeid;
/**
*单据日期
*/
public UFDate dbilldate;
/**
*自定义项1
*/
public String def1;
/**
*自定义项10
*/
public String def10;
/**
*自定义项11
*/
public String def11;
/**
*自定义项12
*/
public String def12;
/**
*自定义项13
*/
public String def13;
/**
*自定义项14
*/
public String def14;
/**
*自定义项15
*/
public String def15;
/**
*自定义项16
*/
public String def16;
/**
*自定义项17
*/
public String def17;
/**
*自定义项18
*/
public String def18;
/**
*自定义项19
*/
public String def19;
/**
*自定义项2
*/
public String def2;
/**
*自定义项20
*/
public String def20;
/**
*自定义项3
*/
public String def3;
/**
*自定义项4
*/
public String def4;
/**
*自定义项5
*/
public String def5;
/**
*自定义项6
*/
public String def6;
/**
*自定义项7
*/
public String def7;
/**
*自定义项8
*/
public String def8;
/**
*自定义项9
*/
public String def9;
/**
*修订枚举
*/
public Integer emendenum;
/**
*终端客商
*/
public String finalcustomer;
/**
*单据状态
*/
public Integer fstatusflag;
/**
*打印次数
*/
public Integer iprintcount;
/**
*备注
*/
public String memo;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*整单折扣
*/
public UFDouble ndiscountrate;
/**
*价税合计
*/
public UFDouble nheadsummny;
/**
*总数量
*/
public UFDouble ntotalnum;
/**
*集团
*/
public String pk_group;
/**
*销售组织
*/
public String pk_org;
/**
*销售组织版本
*/
public String pk_org_v;
/**
*发货申请单主键
*/
public String pk_shipments;
/**
*所属组织
*/
public String pkorg;
/**
*销售类型标识
*/
public String saletypeoid;
/**
*来源单据id
*/
public String srcbillid;
/**
*来源单据类型
*/
public String srcbilltype;
/**
*交易类型
*/
public String transtype;
/**
*交易类型pk
*/
public String transtypepk;
/**
*时间戳
*/
public UFDateTime ts;
/**
*单据编号
*/
public String vbillcode;
/**
*整单来源单据类型
*/
public String vbillsrctype;
/** 
* 获取审批时间
*
* @return 审批时间
*/
public UFDateTime getApprovedate () {
return this.approvedate;
 } 

/** 
* 设置审批时间
*
* @param approvedate 审批时间
*/
public void setApprovedate ( UFDateTime approvedate) {
this.approvedate=approvedate;
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getApprovenote () {
return this.approvenote;
 } 

/** 
* 设置审批批语
*
* @param approvenote 审批批语
*/
public void setApprovenote ( String approvenote) {
this.approvenote=approvenote;
 } 

/** 
* 获取审批人
*
* @return 审批人
*/
public String getApprover () {
return this.approver;
 } 

/** 
* 设置审批人
*
* @param approver 审批人
*/
public void setApprover ( String approver) {
this.approver=approver;
 } 

/** 
* 获取审批状态
*
* @return 审批状态
* @see String
*/
public Integer getApprovestatus () {
return this.approvestatus;
 } 

/** 
* 设置审批状态
*
* @param approvestatus 审批状态
* @see String
*/
public void setApprovestatus ( Integer approvestatus) {
this.approvestatus=approvestatus;
 } 

/** 
* 获取单据ID
*
* @return 单据ID
*/
public String getBillid () {
return this.billid;
 } 

/** 
* 设置单据ID
*
* @param billid 单据ID
*/
public void setBillid ( String billid) {
this.billid=billid;
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getBillmaker () {
return this.billmaker;
 } 

/** 
* 设置制单人
*
* @param billmaker 制单人
*/
public void setBillmaker ( String billmaker) {
this.billmaker=billmaker;
 } 

/** 
* 获取单据号
*
* @return 单据号
*/
public String getBillno () {
return this.billno;
 } 

/** 
* 设置单据号
*
* @param billno 单据号
*/
public void setBillno ( String billno) {
this.billno=billno;
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getBilltype () {
return this.billtype;
 } 

/** 
* 设置单据类型
*
* @param billtype 单据类型
*/
public void setBilltype ( String billtype) {
this.billtype=billtype;
 } 

/** 
* 获取单据版本pk
*
* @return 单据版本pk
*/
public String getBillversionpk () {
return this.billversionpk;
 } 

/** 
* 设置单据版本pk
*
* @param billversionpk 单据版本pk
*/
public void setBillversionpk ( String billversionpk) {
this.billversionpk=billversionpk;
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getBusitype () {
return this.busitype;
 } 

/** 
* 设置业务类型
*
* @param busitype 业务类型
*/
public void setBusitype ( String busitype) {
this.busitype=busitype;
 } 

/** 
* 获取整单来源单据ID
*
* @return 整单来源单据ID
*/
public String getCbilsrcid () {
return this.cbilsrcid;
 } 

/** 
* 设置整单来源单据ID
*
* @param cbilsrcid 整单来源单据ID
*/
public void setCbilsrcid ( String cbilsrcid) {
this.cbilsrcid=cbilsrcid;
 } 

/** 
* 获取客户
*
* @return 客户
*/
public String getCcustomerid () {
return this.ccustomerid;
 } 

/** 
* 设置客户
*
* @param ccustomerid 客户
*/
public void setCcustomerid ( String ccustomerid) {
this.ccustomerid=ccustomerid;
 } 

/** 
* 获取销售部门版本
*
* @return 销售部门版本
*/
public String getCdeptid () {
return this.cdeptid;
 } 

/** 
* 设置销售部门版本
*
* @param cdeptid 销售部门版本
*/
public void setCdeptid ( String cdeptid) {
this.cdeptid=cdeptid;
 } 

/** 
* 获取销售部门
*
* @return 销售部门
*/
public String getCdeptvid () {
return this.cdeptvid;
 } 

/** 
* 设置销售部门
*
* @param cdeptvid 销售部门
*/
public void setCdeptvid ( String cdeptvid) {
this.cdeptvid=cdeptvid;
 } 

/** 
* 获取业务员
*
* @return 业务员
*/
public String getCemployeeid () {
return this.cemployeeid;
 } 

/** 
* 设置业务员
*
* @param cemployeeid 业务员
*/
public void setCemployeeid ( String cemployeeid) {
this.cemployeeid=cemployeeid;
 } 

/** 
* 获取开票客户
*
* @return 开票客户
*/
public String getCinvoicecustid () {
return this.cinvoicecustid;
 } 

/** 
* 设置开票客户
*
* @param cinvoicecustid 开票客户
*/
public void setCinvoicecustid ( String cinvoicecustid) {
this.cinvoicecustid=cinvoicecustid;
 } 

/** 
* 获取币种
*
* @return 币种
*/
public String getCorigcurrencyid () {
return this.corigcurrencyid;
 } 

/** 
* 设置币种
*
* @param corigcurrencyid 币种
*/
public void setCorigcurrencyid ( String corigcurrencyid) {
this.corigcurrencyid=corigcurrencyid;
 } 

/** 
* 获取收款协议
*
* @return 收款协议
*/
public String getCpaytermid () {
return this.cpaytermid;
 } 

/** 
* 设置收款协议
*
* @param cpaytermid 收款协议
*/
public void setCpaytermid ( String cpaytermid) {
this.cpaytermid=cpaytermid;
 } 

/** 
* 获取创建时间
*
* @return 创建时间
*/
public UFDateTime getCreationtime () {
return this.creationtime;
 } 

/** 
* 设置创建时间
*
* @param creationtime 创建时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
 } 

/** 
* 获取创建人
*
* @return 创建人
*/
public String getCreator () {
return this.creator;
 } 

/** 
* 设置创建人
*
* @param creator 创建人
*/
public void setCreator ( String creator) {
this.creator=creator;
 } 

/** 
* 获取运输方式
*
* @return 运输方式
*/
public String getCtransporttypeid () {
return this.ctransporttypeid;
 } 

/** 
* 设置运输方式
*
* @param ctransporttypeid 运输方式
*/
public void setCtransporttypeid ( String ctransporttypeid) {
this.ctransporttypeid=ctransporttypeid;
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return this.dbilldate;
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
 } 

/** 
* 获取自定义项1
*
* @return 自定义项1
*/
public String getDef1 () {
return this.def1;
 } 

/** 
* 设置自定义项1
*
* @param def1 自定义项1
*/
public void setDef1 ( String def1) {
this.def1=def1;
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getDef10 () {
return this.def10;
 } 

/** 
* 设置自定义项10
*
* @param def10 自定义项10
*/
public void setDef10 ( String def10) {
this.def10=def10;
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getDef11 () {
return this.def11;
 } 

/** 
* 设置自定义项11
*
* @param def11 自定义项11
*/
public void setDef11 ( String def11) {
this.def11=def11;
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getDef12 () {
return this.def12;
 } 

/** 
* 设置自定义项12
*
* @param def12 自定义项12
*/
public void setDef12 ( String def12) {
this.def12=def12;
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getDef13 () {
return this.def13;
 } 

/** 
* 设置自定义项13
*
* @param def13 自定义项13
*/
public void setDef13 ( String def13) {
this.def13=def13;
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getDef14 () {
return this.def14;
 } 

/** 
* 设置自定义项14
*
* @param def14 自定义项14
*/
public void setDef14 ( String def14) {
this.def14=def14;
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getDef15 () {
return this.def15;
 } 

/** 
* 设置自定义项15
*
* @param def15 自定义项15
*/
public void setDef15 ( String def15) {
this.def15=def15;
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getDef16 () {
return this.def16;
 } 

/** 
* 设置自定义项16
*
* @param def16 自定义项16
*/
public void setDef16 ( String def16) {
this.def16=def16;
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getDef17 () {
return this.def17;
 } 

/** 
* 设置自定义项17
*
* @param def17 自定义项17
*/
public void setDef17 ( String def17) {
this.def17=def17;
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getDef18 () {
return this.def18;
 } 

/** 
* 设置自定义项18
*
* @param def18 自定义项18
*/
public void setDef18 ( String def18) {
this.def18=def18;
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getDef19 () {
return this.def19;
 } 

/** 
* 设置自定义项19
*
* @param def19 自定义项19
*/
public void setDef19 ( String def19) {
this.def19=def19;
 } 

/** 
* 获取自定义项2
*
* @return 自定义项2
*/
public String getDef2 () {
return this.def2;
 } 

/** 
* 设置自定义项2
*
* @param def2 自定义项2
*/
public void setDef2 ( String def2) {
this.def2=def2;
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getDef20 () {
return this.def20;
 } 

/** 
* 设置自定义项20
*
* @param def20 自定义项20
*/
public void setDef20 ( String def20) {
this.def20=def20;
 } 

/** 
* 获取自定义项3
*
* @return 自定义项3
*/
public String getDef3 () {
return this.def3;
 } 

/** 
* 设置自定义项3
*
* @param def3 自定义项3
*/
public void setDef3 ( String def3) {
this.def3=def3;
 } 

/** 
* 获取自定义项4
*
* @return 自定义项4
*/
public String getDef4 () {
return this.def4;
 } 

/** 
* 设置自定义项4
*
* @param def4 自定义项4
*/
public void setDef4 ( String def4) {
this.def4=def4;
 } 

/** 
* 获取自定义项5
*
* @return 自定义项5
*/
public String getDef5 () {
return this.def5;
 } 

/** 
* 设置自定义项5
*
* @param def5 自定义项5
*/
public void setDef5 ( String def5) {
this.def5=def5;
 } 

/** 
* 获取自定义项6
*
* @return 自定义项6
*/
public String getDef6 () {
return this.def6;
 } 

/** 
* 设置自定义项6
*
* @param def6 自定义项6
*/
public void setDef6 ( String def6) {
this.def6=def6;
 } 

/** 
* 获取自定义项7
*
* @return 自定义项7
*/
public String getDef7 () {
return this.def7;
 } 

/** 
* 设置自定义项7
*
* @param def7 自定义项7
*/
public void setDef7 ( String def7) {
this.def7=def7;
 } 

/** 
* 获取自定义项8
*
* @return 自定义项8
*/
public String getDef8 () {
return this.def8;
 } 

/** 
* 设置自定义项8
*
* @param def8 自定义项8
*/
public void setDef8 ( String def8) {
this.def8=def8;
 } 

/** 
* 获取自定义项9
*
* @return 自定义项9
*/
public String getDef9 () {
return this.def9;
 } 

/** 
* 设置自定义项9
*
* @param def9 自定义项9
*/
public void setDef9 ( String def9) {
this.def9=def9;
 } 

/** 
* 获取修订枚举
*
* @return 修订枚举
*/
public Integer getEmendenum () {
return this.emendenum;
 } 

/** 
* 设置修订枚举
*
* @param emendenum 修订枚举
*/
public void setEmendenum ( Integer emendenum) {
this.emendenum=emendenum;
 } 

/** 
* 获取终端客商
*
* @return 终端客商
*/
public String getFinalcustomer () {
return this.finalcustomer;
 } 

/** 
* 设置终端客商
*
* @param finalcustomer 终端客商
*/
public void setFinalcustomer ( String finalcustomer) {
this.finalcustomer=finalcustomer;
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getFstatusflag () {
return this.fstatusflag;
 } 

/** 
* 设置单据状态
*
* @param fstatusflag 单据状态
* @see String
*/
public void setFstatusflag ( Integer fstatusflag) {
this.fstatusflag=fstatusflag;
 } 

/** 
* 获取打印次数
*
* @return 打印次数
*/
public Integer getIprintcount () {
return this.iprintcount;
 } 

/** 
* 设置打印次数
*
* @param iprintcount 打印次数
*/
public void setIprintcount ( Integer iprintcount) {
this.iprintcount=iprintcount;
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getMemo () {
return this.memo;
 } 

/** 
* 设置备注
*
* @param memo 备注
*/
public void setMemo ( String memo) {
this.memo=memo;
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return this.modifiedtime;
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return this.modifier;
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.modifier=modifier;
 } 

/** 
* 获取整单折扣
*
* @return 整单折扣
*/
public UFDouble getNdiscountrate () {
return this.ndiscountrate;
 } 

/** 
* 设置整单折扣
*
* @param ndiscountrate 整单折扣
*/
public void setNdiscountrate ( UFDouble ndiscountrate) {
this.ndiscountrate=ndiscountrate;
 } 

/** 
* 获取价税合计
*
* @return 价税合计
*/
public UFDouble getNheadsummny () {
return this.nheadsummny;
 } 

/** 
* 设置价税合计
*
* @param nheadsummny 价税合计
*/
public void setNheadsummny ( UFDouble nheadsummny) {
this.nheadsummny=nheadsummny;
 } 

/** 
* 获取总数量
*
* @return 总数量
*/
public UFDouble getNtotalnum () {
return this.ntotalnum;
 } 

/** 
* 设置总数量
*
* @param ntotalnum 总数量
*/
public void setNtotalnum ( UFDouble ntotalnum) {
this.ntotalnum=ntotalnum;
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return this.pk_group;
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.pk_group=pk_group;
 } 

/** 
* 获取销售组织
*
* @return 销售组织
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* 设置销售组织
*
* @param pk_org 销售组织
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* 获取销售组织版本
*
* @return 销售组织版本
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* 设置销售组织版本
*
* @param pk_org_v 销售组织版本
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* 获取发货申请单主键
*
* @return 发货申请单主键
*/
public String getPk_shipments () {
return this.pk_shipments;
 } 

/** 
* 设置发货申请单主键
*
* @param pk_shipments 发货申请单主键
*/
public void setPk_shipments ( String pk_shipments) {
this.pk_shipments=pk_shipments;
 } 

/** 
* 获取所属组织
*
* @return 所属组织
*/
public String getPkorg () {
return this.pkorg;
 } 

/** 
* 设置所属组织
*
* @param pkorg 所属组织
*/
public void setPkorg ( String pkorg) {
this.pkorg=pkorg;
 } 

/** 
* 获取销售类型标识
*
* @return 销售类型标识
*/
public String getSaletypeoid () {
return this.saletypeoid;
 } 

/** 
* 设置销售类型标识
*
* @param saletypeoid 销售类型标识
*/
public void setSaletypeoid ( String saletypeoid) {
this.saletypeoid=saletypeoid;
 } 

/** 
* 获取来源单据id
*
* @return 来源单据id
*/
public String getSrcbillid () {
return this.srcbillid;
 } 

/** 
* 设置来源单据id
*
* @param srcbillid 来源单据id
*/
public void setSrcbillid ( String srcbillid) {
this.srcbillid=srcbillid;
 } 

/** 
* 获取来源单据类型
*
* @return 来源单据类型
*/
public String getSrcbilltype () {
return this.srcbilltype;
 } 

/** 
* 设置来源单据类型
*
* @param srcbilltype 来源单据类型
*/
public void setSrcbilltype ( String srcbilltype) {
this.srcbilltype=srcbilltype;
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getTranstype () {
return this.transtype;
 } 

/** 
* 设置交易类型
*
* @param transtype 交易类型
*/
public void setTranstype ( String transtype) {
this.transtype=transtype;
 } 

/** 
* 获取交易类型pk
*
* @return 交易类型pk
*/
public String getTranstypepk () {
return this.transtypepk;
 } 

/** 
* 设置交易类型pk
*
* @param transtypepk 交易类型pk
*/
public void setTranstypepk ( String transtypepk) {
this.transtypepk=transtypepk;
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return this.ts;
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.ts=ts;
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return this.vbillcode;
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.vbillcode=vbillcode;
 } 

/** 
* 获取整单来源单据类型
*
* @return 整单来源单据类型
*/
public String getVbillsrctype () {
return this.vbillsrctype;
 } 

/** 
* 设置整单来源单据类型
*
* @param vbillsrctype 整单来源单据类型
*/
public void setVbillsrctype ( String vbillsrctype) {
this.vbillsrctype=vbillsrctype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.ShipmentsVO");
  }
}