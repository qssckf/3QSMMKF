package nc.vo.so.qs.sc;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ShipmentsBVO extends SuperVO {
/**
*赠品
*/
public UFBoolean blargessflag;
/**
*是否行关闭
*/
public UFBoolean blineclose;
/**
*三角贸易
*/
public static final String BTRIATRADEFLAG="btriatradeflag";
/**
*应收组织最新版本
*/
public String carorgid;
/**
*应收组织
*/
public String carorgvid;
/**
*最后安排人
*/
public String carrangeid;
/**
*单位
*/
public String castunitid;
/**
*本位币
*/
public String ccurrencyid;
/**
*源头单据子表
*/
public String cfirstbid;
/**
*源头单据主表
*/
public String cfirstid;
/**
*物料档案
*/
public String cmaterialid;
/**
*产品编码
*/
public String cmaterialvid;
/**
*币种
*/
public String corigcurrencyid;
/**
*价格组成
*/
public static final String CPRICEFORMID="cpriceformid";
/**
*价格项目
*/
public String cpriceitemid;
/**
*价格表
*/
public String cpriceitemtableid;
/**
*价格政策
*/
public String cpricepolicyid;
/**
*利润中心最新版本
*/
public String cprofitcenterid;
/**
*利润中心
*/
public String cprofitcentervid;
/**
*报价单位
*/
public static final String CQTUNITID="cqtunitid";
/**
*收货国家/地区
*/
public static final String CRECECOUNTRYID="crececountryid";
/**
*收货地址
*/
public String creceiveaddrid;
/**
*收货地区
*/
public String creceiveareaid;
/**
*收货客户
*/
public String creceivecustid;
/**
*收货地点
*/
public String creceivesiteid;
/**
*料比
*/
public String csbomversionid;
/**
*发货国家/地区
*/
public static final String CSENDCOUNTRYID="csendcountryid";
/**
*发货库存组织最新版本
*/
public String csendstockorgid;
/**
*发货库存组织
*/
public String csendstockorgvid;
/**
*发货仓库
*/
public String csendstordocid;
/**
*结算财务组织最新版本
*/
public String csettleorgid;
/**
*结算财务组织
*/
public String csettleorgvid;
/**
*来源单据附表
*/
public String csrcbid;
/**
*来源单据主表
*/
public String csrcid;
/**
*税码
*/
public String ctaxcodeid;
/**
*报税国家/地区
*/
public static final String CTAXCOUNTRYID="ctaxcountryid";
/**
*物流组织最新版本
*/
public String ctrafficorgid;
/**
*物流组织
*/
public String ctrafficorgvid;
/**
*主单位
*/
public String cunitid;
/**
*剖布类型
*/
public String cutcloth;
/**
*最后安排日期
*/
public UFDate darrdate;
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
*要求到货日期
*/
public UFDate dreceivedate;
/**
*计划发货日期
*/
public UFDate dsenddate;
/**
*定金
*/
public UFDouble earnestmoney;
/**
*购销类型
*/
public static final String FBUYSELLFLAG="fbuysellflag";
/**
*扣税类别
*/
public Integer ftaxtypeflag;
/**
*订价
*/
public UFDouble listprice;
/**
*产品分类编码
*/
public String marbasclassid;
/**
*备注
*/
public String memo;
/**
*混合料价
*/
public UFDouble mixtureprice;
/**
*累计安排数量
*/
public UFDouble narrnum;
/**
*询价无税净价
*/
public static final String NASKQTORIGNETPRICE="naskqtorignetprice";
/**
*询价无税单价
*/
public static final String NASKQTORIGPRICE="naskqtorigprice";
/**
*询价含税单价
*/
public static final String NASKQTORIGTAXPRC="naskqtorigtaxprc";
/**
*询价含税净价
*/
public static final String NASKQTORIGTXNTPRC="naskqtorigtxntprc";
/**
*数量
*/
public UFDouble nastnum;
/**
*计税金额
*/
public static final String NCALTAXMNY="ncaltaxmny";
/**
*本币折扣额
*/
public static final String NDISCOUNT="ndiscount";
/**
*整单折扣
*/
public UFDouble ndiscountrate;
/**
*全局本位币汇率
*/
public static final String NGLOBALEXCHGRATE="nglobalexchgrate";
/**
*全局本币无税金额
*/
public static final String NGLOBALMNY="nglobalmny";
/**
*全局本币价税合计
*/
public static final String NGLOBALTAXMNY="nglobaltaxmny";
/**
*集团本位币汇率
*/
public static final String NGROUPEXCHGRATE="ngroupexchgrate";
/**
*集团本币无税金额
*/
public static final String NGROUPMNY="ngroupmny";
/**
*集团本币价税合计
*/
public static final String NGROUPTAXMNY="ngrouptaxmny";
/**
*单品折扣
*/
public UFDouble nitemdiscountrate;
/**
*本币无税金额
*/
public static final String NMNY="nmny";
/**
*主本币无税净价
*/
public static final String NNETPRICE="nnetprice";
/**
*主数量
*/
public UFDouble nnum;
/**
*折扣额
*/
public UFDouble norigdiscount;
/**
*无税金额
*/
public UFDouble norigmny;
/**
*主无税净价
*/
public UFDouble norignetprice;
/**
*主无税单价
*/
public UFDouble norigprice;
/**
*价税合计
*/
public UFDouble norigtaxmny;
/**
*主含税净价
*/
public UFDouble norigtaxnetprice;
/**
*主含税单价
*/
public UFDouble norigtaxprice;
/**
*主本币无税单价
*/
public static final String NPRICE="nprice";
/**
*本币无税净价
*/
public static final String NQTNETPRICE="nqtnetprice";
/**
*无税净价
*/
public UFDouble nqtorignetprice;
/**
*无税单价
*/
public UFDouble nqtorigprice;
/**
*含税净价
*/
public UFDouble nqtorigtaxnetprc;
/**
*含税单价
*/
public UFDouble nqtorigtaxprice;
/**
*本币无税单价
*/
public static final String NQTPRICE="nqtprice";
/**
*本币含税净价
*/
public static final String NQTTAXNETPRICE="nqttaxnetprice";
/**
*本币含税单价
*/
public static final String NQTTAXPRICE="nqttaxprice";
/**
*报价单位数量
*/
public static final String NQTUNITNUM="nqtunitnum";
/**
*税额
*/
public UFDouble ntax;
/**
*本币价税合计
*/
public static final String NTAXMNY="ntaxmny";
/**
*主本币含税净价
*/
public static final String NTAXNETPRICE="ntaxnetprice";
/**
*主本币含税单价
*/
public static final String NTAXPRICE="ntaxprice";
/**
*税率
*/
public UFDouble ntaxrate;
/**
*其他生产要求
*/
public String otherproduction;
/**
*单包重(KG)
*/
public String packageweight;
/**
*批次档案
*/
public String pk_batchcode;
/**
*集团
*/
public String pk_group;
/**
*销售组织
*/
public String pk_org;
/**
*发货申请行主键
*/
public String pk_shipments_b;
/**
*行号
*/
public String rowno;
/**
*上层单据主键
*/
public String so_shipmentsb;
/**
*srcbts（可持续化字段，可以远程传递值，但不能保存到数据库）
*/
public static final String SRCBTS="srcbts";
/**
*srcts（可持续化字段，可以远程传递值，但不能保存到数据库）
*/
public static final String SRCTS="srcts";
/**
*标识线
*/
public String tagline;
/**
*时间戳
*/
public UFDateTime ts;
/**
*批次号
*/
public String vbatchcode;
/**
*换算率
*/
public String vchangerate;
/**
*源头单据号
*/
public String vfirstcode;
/**
*源头单据行号
*/
public String vfirstrowno;
/**
*源头交易类型
*/
public String vfirsttrantype;
/**
*源头单据类型
*/
public String vfirsttype;
/**
*自由辅助属性1
*/
public String vfree1;
/**
*自由辅助属性10
*/
public String vfree10;
/**
*自由辅助属性2
*/
public String vfree2;
/**
*自由辅助属性3
*/
public String vfree3;
/**
*自由辅助属性4
*/
public String vfree4;
/**
*自由辅助属性5
*/
public String vfree5;
/**
*自由辅助属性6
*/
public String vfree6;
/**
*自由辅助属性7
*/
public String vfree7;
/**
*自由辅助属性8
*/
public String vfree8;
/**
*自由辅助属性9
*/
public String vfree9;
/**
*报价换算率
*/
public static final String VQTUNITRATE="vqtunitrate";
/**
*来源单据号
*/
public String vsrccode;
/**
*来源单据行号
*/
public String vsrcrowno;
/**
*来源交易类型
*/
public String vsrctrantype;
/**
*来源单据类型
*/
public String vsrctype;
/** 
* 获取赠品
*
* @return 赠品
*/
public UFBoolean getBlargessflag () {
return this.blargessflag;
 } 

/** 
* 设置赠品
*
* @param blargessflag 赠品
*/
public void setBlargessflag ( UFBoolean blargessflag) {
this.blargessflag=blargessflag;
 } 

/** 
* 获取是否行关闭
*
* @return 是否行关闭
*/
public UFBoolean getBlineclose () {
return this.blineclose;
 } 

/** 
* 设置是否行关闭
*
* @param blineclose 是否行关闭
*/
public void setBlineclose ( UFBoolean blineclose) {
this.blineclose=blineclose;
 } 

/** 
* 获取三角贸易
*
* @return 三角贸易
*/
public UFBoolean getBtriatradeflag () {
return (UFBoolean) this.getAttributeValue( ShipmentsBVO.BTRIATRADEFLAG);
 } 

/** 
* 设置三角贸易
*
* @param btriatradeflag 三角贸易
*/
public void setBtriatradeflag ( UFBoolean btriatradeflag) {
this.setAttributeValue( ShipmentsBVO.BTRIATRADEFLAG,btriatradeflag);
 } 

/** 
* 获取应收组织最新版本
*
* @return 应收组织最新版本
*/
public String getCarorgid () {
return this.carorgid;
 } 

/** 
* 设置应收组织最新版本
*
* @param carorgid 应收组织最新版本
*/
public void setCarorgid ( String carorgid) {
this.carorgid=carorgid;
 } 

/** 
* 获取应收组织
*
* @return 应收组织
*/
public String getCarorgvid () {
return this.carorgvid;
 } 

/** 
* 设置应收组织
*
* @param carorgvid 应收组织
*/
public void setCarorgvid ( String carorgvid) {
this.carorgvid=carorgvid;
 } 

/** 
* 获取最后安排人
*
* @return 最后安排人
*/
public String getCarrangeid () {
return this.carrangeid;
 } 

/** 
* 设置最后安排人
*
* @param carrangeid 最后安排人
*/
public void setCarrangeid ( String carrangeid) {
this.carrangeid=carrangeid;
 } 

/** 
* 获取单位
*
* @return 单位
*/
public String getCastunitid () {
return this.castunitid;
 } 

/** 
* 设置单位
*
* @param castunitid 单位
*/
public void setCastunitid ( String castunitid) {
this.castunitid=castunitid;
 } 

/** 
* 获取本位币
*
* @return 本位币
*/
public String getCcurrencyid () {
return this.ccurrencyid;
 } 

/** 
* 设置本位币
*
* @param ccurrencyid 本位币
*/
public void setCcurrencyid ( String ccurrencyid) {
this.ccurrencyid=ccurrencyid;
 } 

/** 
* 获取源头单据子表
*
* @return 源头单据子表
*/
public String getCfirstbid () {
return this.cfirstbid;
 } 

/** 
* 设置源头单据子表
*
* @param cfirstbid 源头单据子表
*/
public void setCfirstbid ( String cfirstbid) {
this.cfirstbid=cfirstbid;
 } 

/** 
* 获取源头单据主表
*
* @return 源头单据主表
*/
public String getCfirstid () {
return this.cfirstid;
 } 

/** 
* 设置源头单据主表
*
* @param cfirstid 源头单据主表
*/
public void setCfirstid ( String cfirstid) {
this.cfirstid=cfirstid;
 } 

/** 
* 获取物料档案
*
* @return 物料档案
*/
public String getCmaterialid () {
return this.cmaterialid;
 } 

/** 
* 设置物料档案
*
* @param cmaterialid 物料档案
*/
public void setCmaterialid ( String cmaterialid) {
this.cmaterialid=cmaterialid;
 } 

/** 
* 获取产品编码
*
* @return 产品编码
*/
public String getCmaterialvid () {
return this.cmaterialvid;
 } 

/** 
* 设置产品编码
*
* @param cmaterialvid 产品编码
*/
public void setCmaterialvid ( String cmaterialvid) {
this.cmaterialvid=cmaterialvid;
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
* 获取价格组成
*
* @return 价格组成
*/
public String getCpriceformid () {
return (String) this.getAttributeValue( ShipmentsBVO.CPRICEFORMID);
 } 

/** 
* 设置价格组成
*
* @param cpriceformid 价格组成
*/
public void setCpriceformid ( String cpriceformid) {
this.setAttributeValue( ShipmentsBVO.CPRICEFORMID,cpriceformid);
 } 

/** 
* 获取价格项目
*
* @return 价格项目
*/
public String getCpriceitemid () {
return this.cpriceitemid;
 } 

/** 
* 设置价格项目
*
* @param cpriceitemid 价格项目
*/
public void setCpriceitemid ( String cpriceitemid) {
this.cpriceitemid=cpriceitemid;
 } 

/** 
* 获取价格表
*
* @return 价格表
*/
public String getCpriceitemtableid () {
return this.cpriceitemtableid;
 } 

/** 
* 设置价格表
*
* @param cpriceitemtableid 价格表
*/
public void setCpriceitemtableid ( String cpriceitemtableid) {
this.cpriceitemtableid=cpriceitemtableid;
 } 

/** 
* 获取价格政策
*
* @return 价格政策
*/
public String getCpricepolicyid () {
return this.cpricepolicyid;
 } 

/** 
* 设置价格政策
*
* @param cpricepolicyid 价格政策
*/
public void setCpricepolicyid ( String cpricepolicyid) {
this.cpricepolicyid=cpricepolicyid;
 } 

/** 
* 获取利润中心最新版本
*
* @return 利润中心最新版本
*/
public String getCprofitcenterid () {
return this.cprofitcenterid;
 } 

/** 
* 设置利润中心最新版本
*
* @param cprofitcenterid 利润中心最新版本
*/
public void setCprofitcenterid ( String cprofitcenterid) {
this.cprofitcenterid=cprofitcenterid;
 } 

/** 
* 获取利润中心
*
* @return 利润中心
*/
public String getCprofitcentervid () {
return this.cprofitcentervid;
 } 

/** 
* 设置利润中心
*
* @param cprofitcentervid 利润中心
*/
public void setCprofitcentervid ( String cprofitcentervid) {
this.cprofitcentervid=cprofitcentervid;
 } 

/** 
* 获取报价单位
*
* @return 报价单位
*/
public String getCqtunitid () {
return (String) this.getAttributeValue( ShipmentsBVO.CQTUNITID);
 } 

/** 
* 设置报价单位
*
* @param cqtunitid 报价单位
*/
public void setCqtunitid ( String cqtunitid) {
this.setAttributeValue( ShipmentsBVO.CQTUNITID,cqtunitid);
 } 

/** 
* 获取收货国家/地区
*
* @return 收货国家/地区
*/
public String getCrececountryid () {
return (String) this.getAttributeValue( ShipmentsBVO.CRECECOUNTRYID);
 } 

/** 
* 设置收货国家/地区
*
* @param crececountryid 收货国家/地区
*/
public void setCrececountryid ( String crececountryid) {
this.setAttributeValue( ShipmentsBVO.CRECECOUNTRYID,crececountryid);
 } 

/** 
* 获取收货地址
*
* @return 收货地址
*/
public String getCreceiveaddrid () {
return this.creceiveaddrid;
 } 

/** 
* 设置收货地址
*
* @param creceiveaddrid 收货地址
*/
public void setCreceiveaddrid ( String creceiveaddrid) {
this.creceiveaddrid=creceiveaddrid;
 } 

/** 
* 获取收货地区
*
* @return 收货地区
*/
public String getCreceiveareaid () {
return this.creceiveareaid;
 } 

/** 
* 设置收货地区
*
* @param creceiveareaid 收货地区
*/
public void setCreceiveareaid ( String creceiveareaid) {
this.creceiveareaid=creceiveareaid;
 } 

/** 
* 获取收货客户
*
* @return 收货客户
*/
public String getCreceivecustid () {
return this.creceivecustid;
 } 

/** 
* 设置收货客户
*
* @param creceivecustid 收货客户
*/
public void setCreceivecustid ( String creceivecustid) {
this.creceivecustid=creceivecustid;
 } 

/** 
* 获取收货地点
*
* @return 收货地点
*/
public String getCreceivesiteid () {
return this.creceivesiteid;
 } 

/** 
* 设置收货地点
*
* @param creceivesiteid 收货地点
*/
public void setCreceivesiteid ( String creceivesiteid) {
this.creceivesiteid=creceivesiteid;
 } 

/** 
* 获取料比
*
* @return 料比
*/
public String getCsbomversionid () {
return this.csbomversionid;
 } 

/** 
* 设置料比
*
* @param csbomversionid 料比
*/
public void setCsbomversionid ( String csbomversionid) {
this.csbomversionid=csbomversionid;
 } 

/** 
* 获取发货国家/地区
*
* @return 发货国家/地区
*/
public String getCsendcountryid () {
return (String) this.getAttributeValue( ShipmentsBVO.CSENDCOUNTRYID);
 } 

/** 
* 设置发货国家/地区
*
* @param csendcountryid 发货国家/地区
*/
public void setCsendcountryid ( String csendcountryid) {
this.setAttributeValue( ShipmentsBVO.CSENDCOUNTRYID,csendcountryid);
 } 

/** 
* 获取发货库存组织最新版本
*
* @return 发货库存组织最新版本
*/
public String getCsendstockorgid () {
return this.csendstockorgid;
 } 

/** 
* 设置发货库存组织最新版本
*
* @param csendstockorgid 发货库存组织最新版本
*/
public void setCsendstockorgid ( String csendstockorgid) {
this.csendstockorgid=csendstockorgid;
 } 

/** 
* 获取发货库存组织
*
* @return 发货库存组织
*/
public String getCsendstockorgvid () {
return this.csendstockorgvid;
 } 

/** 
* 设置发货库存组织
*
* @param csendstockorgvid 发货库存组织
*/
public void setCsendstockorgvid ( String csendstockorgvid) {
this.csendstockorgvid=csendstockorgvid;
 } 

/** 
* 获取发货仓库
*
* @return 发货仓库
*/
public String getCsendstordocid () {
return this.csendstordocid;
 } 

/** 
* 设置发货仓库
*
* @param csendstordocid 发货仓库
*/
public void setCsendstordocid ( String csendstordocid) {
this.csendstordocid=csendstordocid;
 } 

/** 
* 获取结算财务组织最新版本
*
* @return 结算财务组织最新版本
*/
public String getCsettleorgid () {
return this.csettleorgid;
 } 

/** 
* 设置结算财务组织最新版本
*
* @param csettleorgid 结算财务组织最新版本
*/
public void setCsettleorgid ( String csettleorgid) {
this.csettleorgid=csettleorgid;
 } 

/** 
* 获取结算财务组织
*
* @return 结算财务组织
*/
public String getCsettleorgvid () {
return this.csettleorgvid;
 } 

/** 
* 设置结算财务组织
*
* @param csettleorgvid 结算财务组织
*/
public void setCsettleorgvid ( String csettleorgvid) {
this.csettleorgvid=csettleorgvid;
 } 

/** 
* 获取来源单据附表
*
* @return 来源单据附表
*/
public String getCsrcbid () {
return this.csrcbid;
 } 

/** 
* 设置来源单据附表
*
* @param csrcbid 来源单据附表
*/
public void setCsrcbid ( String csrcbid) {
this.csrcbid=csrcbid;
 } 

/** 
* 获取来源单据主表
*
* @return 来源单据主表
*/
public String getCsrcid () {
return this.csrcid;
 } 

/** 
* 设置来源单据主表
*
* @param csrcid 来源单据主表
*/
public void setCsrcid ( String csrcid) {
this.csrcid=csrcid;
 } 

/** 
* 获取税码
*
* @return 税码
*/
public String getCtaxcodeid () {
return this.ctaxcodeid;
 } 

/** 
* 设置税码
*
* @param ctaxcodeid 税码
*/
public void setCtaxcodeid ( String ctaxcodeid) {
this.ctaxcodeid=ctaxcodeid;
 } 

/** 
* 获取报税国家/地区
*
* @return 报税国家/地区
*/
public String getCtaxcountryid () {
return (String) this.getAttributeValue( ShipmentsBVO.CTAXCOUNTRYID);
 } 

/** 
* 设置报税国家/地区
*
* @param ctaxcountryid 报税国家/地区
*/
public void setCtaxcountryid ( String ctaxcountryid) {
this.setAttributeValue( ShipmentsBVO.CTAXCOUNTRYID,ctaxcountryid);
 } 

/** 
* 获取物流组织最新版本
*
* @return 物流组织最新版本
*/
public String getCtrafficorgid () {
return this.ctrafficorgid;
 } 

/** 
* 设置物流组织最新版本
*
* @param ctrafficorgid 物流组织最新版本
*/
public void setCtrafficorgid ( String ctrafficorgid) {
this.ctrafficorgid=ctrafficorgid;
 } 

/** 
* 获取物流组织
*
* @return 物流组织
*/
public String getCtrafficorgvid () {
return this.ctrafficorgvid;
 } 

/** 
* 设置物流组织
*
* @param ctrafficorgvid 物流组织
*/
public void setCtrafficorgvid ( String ctrafficorgvid) {
this.ctrafficorgvid=ctrafficorgvid;
 } 

/** 
* 获取主单位
*
* @return 主单位
*/
public String getCunitid () {
return this.cunitid;
 } 

/** 
* 设置主单位
*
* @param cunitid 主单位
*/
public void setCunitid ( String cunitid) {
this.cunitid=cunitid;
 } 

/** 
* 获取剖布类型
*
* @return 剖布类型
*/
public String getCutcloth () {
return this.cutcloth;
 } 

/** 
* 设置剖布类型
*
* @param cutcloth 剖布类型
*/
public void setCutcloth ( String cutcloth) {
this.cutcloth=cutcloth;
 } 

/** 
* 获取最后安排日期
*
* @return 最后安排日期
*/
public UFDate getDarrdate () {
return this.darrdate;
 } 

/** 
* 设置最后安排日期
*
* @param darrdate 最后安排日期
*/
public void setDarrdate ( UFDate darrdate) {
this.darrdate=darrdate;
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
* 获取要求到货日期
*
* @return 要求到货日期
*/
public UFDate getDreceivedate () {
return this.dreceivedate;
 } 

/** 
* 设置要求到货日期
*
* @param dreceivedate 要求到货日期
*/
public void setDreceivedate ( UFDate dreceivedate) {
this.dreceivedate=dreceivedate;
 } 

/** 
* 获取计划发货日期
*
* @return 计划发货日期
*/
public UFDate getDsenddate () {
return this.dsenddate;
 } 

/** 
* 设置计划发货日期
*
* @param dsenddate 计划发货日期
*/
public void setDsenddate ( UFDate dsenddate) {
this.dsenddate=dsenddate;
 } 

/** 
* 获取定金
*
* @return 定金
*/
public UFDouble getEarnestmoney () {
return this.earnestmoney;
 } 

/** 
* 设置定金
*
* @param earnestmoney 定金
*/
public void setEarnestmoney ( UFDouble earnestmoney) {
this.earnestmoney=earnestmoney;
 } 

/** 
* 获取购销类型
*
* @return 购销类型
* @see String
*/
public Integer getFbuysellflag () {
return (Integer) this.getAttributeValue( ShipmentsBVO.FBUYSELLFLAG);
 } 

/** 
* 设置购销类型
*
* @param fbuysellflag 购销类型
* @see String
*/
public void setFbuysellflag ( Integer fbuysellflag) {
this.setAttributeValue( ShipmentsBVO.FBUYSELLFLAG,fbuysellflag);
 } 

/** 
* 获取扣税类别
*
* @return 扣税类别
* @see String
*/
public Integer getFtaxtypeflag () {
return this.ftaxtypeflag;
 } 

/** 
* 设置扣税类别
*
* @param ftaxtypeflag 扣税类别
* @see String
*/
public void setFtaxtypeflag ( Integer ftaxtypeflag) {
this.ftaxtypeflag=ftaxtypeflag;
 } 

/** 
* 获取订价
*
* @return 订价
*/
public UFDouble getListprice () {
return this.listprice;
 } 

/** 
* 设置订价
*
* @param listprice 订价
*/
public void setListprice ( UFDouble listprice) {
this.listprice=listprice;
 } 

/** 
* 获取产品分类编码
*
* @return 产品分类编码
*/
public String getMarbasclassid () {
return this.marbasclassid;
 } 

/** 
* 设置产品分类编码
*
* @param marbasclassid 产品分类编码
*/
public void setMarbasclassid ( String marbasclassid) {
this.marbasclassid=marbasclassid;
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
* 获取混合料价
*
* @return 混合料价
*/
public UFDouble getMixtureprice () {
return this.mixtureprice;
 } 

/** 
* 设置混合料价
*
* @param mixtureprice 混合料价
*/
public void setMixtureprice ( UFDouble mixtureprice) {
this.mixtureprice=mixtureprice;
 } 

/** 
* 获取累计安排数量
*
* @return 累计安排数量
*/
public UFDouble getNarrnum () {
return this.narrnum;
 } 

/** 
* 设置累计安排数量
*
* @param narrnum 累计安排数量
*/
public void setNarrnum ( UFDouble narrnum) {
this.narrnum=narrnum;
 } 

/** 
* 获取询价无税净价
*
* @return 询价无税净价
*/
public UFDouble getNaskqtorignetprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NASKQTORIGNETPRICE);
 } 

/** 
* 设置询价无税净价
*
* @param naskqtorignetprice 询价无税净价
*/
public void setNaskqtorignetprice ( UFDouble naskqtorignetprice) {
this.setAttributeValue( ShipmentsBVO.NASKQTORIGNETPRICE,naskqtorignetprice);
 } 

/** 
* 获取询价无税单价
*
* @return 询价无税单价
*/
public UFDouble getNaskqtorigprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NASKQTORIGPRICE);
 } 

/** 
* 设置询价无税单价
*
* @param naskqtorigprice 询价无税单价
*/
public void setNaskqtorigprice ( UFDouble naskqtorigprice) {
this.setAttributeValue( ShipmentsBVO.NASKQTORIGPRICE,naskqtorigprice);
 } 

/** 
* 获取询价含税单价
*
* @return 询价含税单价
*/
public UFDouble getNaskqtorigtaxprc () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NASKQTORIGTAXPRC);
 } 

/** 
* 设置询价含税单价
*
* @param naskqtorigtaxprc 询价含税单价
*/
public void setNaskqtorigtaxprc ( UFDouble naskqtorigtaxprc) {
this.setAttributeValue( ShipmentsBVO.NASKQTORIGTAXPRC,naskqtorigtaxprc);
 } 

/** 
* 获取询价含税净价
*
* @return 询价含税净价
*/
public UFDouble getNaskqtorigtxntprc () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NASKQTORIGTXNTPRC);
 } 

/** 
* 设置询价含税净价
*
* @param naskqtorigtxntprc 询价含税净价
*/
public void setNaskqtorigtxntprc ( UFDouble naskqtorigtxntprc) {
this.setAttributeValue( ShipmentsBVO.NASKQTORIGTXNTPRC,naskqtorigtxntprc);
 } 

/** 
* 获取数量
*
* @return 数量
*/
public UFDouble getNastnum () {
return this.nastnum;
 } 

/** 
* 设置数量
*
* @param nastnum 数量
*/
public void setNastnum ( UFDouble nastnum) {
this.nastnum=nastnum;
 } 

/** 
* 获取计税金额
*
* @return 计税金额
*/
public UFDouble getNcaltaxmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NCALTAXMNY);
 } 

/** 
* 设置计税金额
*
* @param ncaltaxmny 计税金额
*/
public void setNcaltaxmny ( UFDouble ncaltaxmny) {
this.setAttributeValue( ShipmentsBVO.NCALTAXMNY,ncaltaxmny);
 } 

/** 
* 获取本币折扣额
*
* @return 本币折扣额
*/
public UFDouble getNdiscount () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NDISCOUNT);
 } 

/** 
* 设置本币折扣额
*
* @param ndiscount 本币折扣额
*/
public void setNdiscount ( UFDouble ndiscount) {
this.setAttributeValue( ShipmentsBVO.NDISCOUNT,ndiscount);
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
* 获取全局本位币汇率
*
* @return 全局本位币汇率
*/
public UFDouble getNglobalexchgrate () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NGLOBALEXCHGRATE);
 } 

/** 
* 设置全局本位币汇率
*
* @param nglobalexchgrate 全局本位币汇率
*/
public void setNglobalexchgrate ( UFDouble nglobalexchgrate) {
this.setAttributeValue( ShipmentsBVO.NGLOBALEXCHGRATE,nglobalexchgrate);
 } 

/** 
* 获取全局本币无税金额
*
* @return 全局本币无税金额
*/
public UFDouble getNglobalmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NGLOBALMNY);
 } 

/** 
* 设置全局本币无税金额
*
* @param nglobalmny 全局本币无税金额
*/
public void setNglobalmny ( UFDouble nglobalmny) {
this.setAttributeValue( ShipmentsBVO.NGLOBALMNY,nglobalmny);
 } 

/** 
* 获取全局本币价税合计
*
* @return 全局本币价税合计
*/
public UFDouble getNglobaltaxmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NGLOBALTAXMNY);
 } 

/** 
* 设置全局本币价税合计
*
* @param nglobaltaxmny 全局本币价税合计
*/
public void setNglobaltaxmny ( UFDouble nglobaltaxmny) {
this.setAttributeValue( ShipmentsBVO.NGLOBALTAXMNY,nglobaltaxmny);
 } 

/** 
* 获取集团本位币汇率
*
* @return 集团本位币汇率
*/
public UFDouble getNgroupexchgrate () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NGROUPEXCHGRATE);
 } 

/** 
* 设置集团本位币汇率
*
* @param ngroupexchgrate 集团本位币汇率
*/
public void setNgroupexchgrate ( UFDouble ngroupexchgrate) {
this.setAttributeValue( ShipmentsBVO.NGROUPEXCHGRATE,ngroupexchgrate);
 } 

/** 
* 获取集团本币无税金额
*
* @return 集团本币无税金额
*/
public UFDouble getNgroupmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NGROUPMNY);
 } 

/** 
* 设置集团本币无税金额
*
* @param ngroupmny 集团本币无税金额
*/
public void setNgroupmny ( UFDouble ngroupmny) {
this.setAttributeValue( ShipmentsBVO.NGROUPMNY,ngroupmny);
 } 

/** 
* 获取集团本币价税合计
*
* @return 集团本币价税合计
*/
public UFDouble getNgrouptaxmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NGROUPTAXMNY);
 } 

/** 
* 设置集团本币价税合计
*
* @param ngrouptaxmny 集团本币价税合计
*/
public void setNgrouptaxmny ( UFDouble ngrouptaxmny) {
this.setAttributeValue( ShipmentsBVO.NGROUPTAXMNY,ngrouptaxmny);
 } 

/** 
* 获取单品折扣
*
* @return 单品折扣
*/
public UFDouble getNitemdiscountrate () {
return this.nitemdiscountrate;
 } 

/** 
* 设置单品折扣
*
* @param nitemdiscountrate 单品折扣
*/
public void setNitemdiscountrate ( UFDouble nitemdiscountrate) {
this.nitemdiscountrate=nitemdiscountrate;
 } 

/** 
* 获取本币无税金额
*
* @return 本币无税金额
*/
public UFDouble getNmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NMNY);
 } 

/** 
* 设置本币无税金额
*
* @param nmny 本币无税金额
*/
public void setNmny ( UFDouble nmny) {
this.setAttributeValue( ShipmentsBVO.NMNY,nmny);
 } 

/** 
* 获取主本币无税净价
*
* @return 主本币无税净价
*/
public UFDouble getNnetprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NNETPRICE);
 } 

/** 
* 设置主本币无税净价
*
* @param nnetprice 主本币无税净价
*/
public void setNnetprice ( UFDouble nnetprice) {
this.setAttributeValue( ShipmentsBVO.NNETPRICE,nnetprice);
 } 

/** 
* 获取主数量
*
* @return 主数量
*/
public UFDouble getNnum () {
return this.nnum;
 } 

/** 
* 设置主数量
*
* @param nnum 主数量
*/
public void setNnum ( UFDouble nnum) {
this.nnum=nnum;
 } 

/** 
* 获取折扣额
*
* @return 折扣额
*/
public UFDouble getNorigdiscount () {
return this.norigdiscount;
 } 

/** 
* 设置折扣额
*
* @param norigdiscount 折扣额
*/
public void setNorigdiscount ( UFDouble norigdiscount) {
this.norigdiscount=norigdiscount;
 } 

/** 
* 获取无税金额
*
* @return 无税金额
*/
public UFDouble getNorigmny () {
return this.norigmny;
 } 

/** 
* 设置无税金额
*
* @param norigmny 无税金额
*/
public void setNorigmny ( UFDouble norigmny) {
this.norigmny=norigmny;
 } 

/** 
* 获取主无税净价
*
* @return 主无税净价
*/
public UFDouble getNorignetprice () {
return this.norignetprice;
 } 

/** 
* 设置主无税净价
*
* @param norignetprice 主无税净价
*/
public void setNorignetprice ( UFDouble norignetprice) {
this.norignetprice=norignetprice;
 } 

/** 
* 获取主无税单价
*
* @return 主无税单价
*/
public UFDouble getNorigprice () {
return this.norigprice;
 } 

/** 
* 设置主无税单价
*
* @param norigprice 主无税单价
*/
public void setNorigprice ( UFDouble norigprice) {
this.norigprice=norigprice;
 } 

/** 
* 获取价税合计
*
* @return 价税合计
*/
public UFDouble getNorigtaxmny () {
return this.norigtaxmny;
 } 

/** 
* 设置价税合计
*
* @param norigtaxmny 价税合计
*/
public void setNorigtaxmny ( UFDouble norigtaxmny) {
this.norigtaxmny=norigtaxmny;
 } 

/** 
* 获取主含税净价
*
* @return 主含税净价
*/
public UFDouble getNorigtaxnetprice () {
return this.norigtaxnetprice;
 } 

/** 
* 设置主含税净价
*
* @param norigtaxnetprice 主含税净价
*/
public void setNorigtaxnetprice ( UFDouble norigtaxnetprice) {
this.norigtaxnetprice=norigtaxnetprice;
 } 

/** 
* 获取主含税单价
*
* @return 主含税单价
*/
public UFDouble getNorigtaxprice () {
return this.norigtaxprice;
 } 

/** 
* 设置主含税单价
*
* @param norigtaxprice 主含税单价
*/
public void setNorigtaxprice ( UFDouble norigtaxprice) {
this.norigtaxprice=norigtaxprice;
 } 

/** 
* 获取主本币无税单价
*
* @return 主本币无税单价
*/
public UFDouble getNprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NPRICE);
 } 

/** 
* 设置主本币无税单价
*
* @param nprice 主本币无税单价
*/
public void setNprice ( UFDouble nprice) {
this.setAttributeValue( ShipmentsBVO.NPRICE,nprice);
 } 

/** 
* 获取本币无税净价
*
* @return 本币无税净价
*/
public UFDouble getNqtnetprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NQTNETPRICE);
 } 

/** 
* 设置本币无税净价
*
* @param nqtnetprice 本币无税净价
*/
public void setNqtnetprice ( UFDouble nqtnetprice) {
this.setAttributeValue( ShipmentsBVO.NQTNETPRICE,nqtnetprice);
 } 

/** 
* 获取无税净价
*
* @return 无税净价
*/
public UFDouble getNqtorignetprice () {
return this.nqtorignetprice;
 } 

/** 
* 设置无税净价
*
* @param nqtorignetprice 无税净价
*/
public void setNqtorignetprice ( UFDouble nqtorignetprice) {
this.nqtorignetprice=nqtorignetprice;
 } 

/** 
* 获取无税单价
*
* @return 无税单价
*/
public UFDouble getNqtorigprice () {
return this.nqtorigprice;
 } 

/** 
* 设置无税单价
*
* @param nqtorigprice 无税单价
*/
public void setNqtorigprice ( UFDouble nqtorigprice) {
this.nqtorigprice=nqtorigprice;
 } 

/** 
* 获取含税净价
*
* @return 含税净价
*/
public UFDouble getNqtorigtaxnetprc () {
return this.nqtorigtaxnetprc;
 } 

/** 
* 设置含税净价
*
* @param nqtorigtaxnetprc 含税净价
*/
public void setNqtorigtaxnetprc ( UFDouble nqtorigtaxnetprc) {
this.nqtorigtaxnetprc=nqtorigtaxnetprc;
 } 

/** 
* 获取含税单价
*
* @return 含税单价
*/
public UFDouble getNqtorigtaxprice () {
return this.nqtorigtaxprice;
 } 

/** 
* 设置含税单价
*
* @param nqtorigtaxprice 含税单价
*/
public void setNqtorigtaxprice ( UFDouble nqtorigtaxprice) {
this.nqtorigtaxprice=nqtorigtaxprice;
 } 

/** 
* 获取本币无税单价
*
* @return 本币无税单价
*/
public UFDouble getNqtprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NQTPRICE);
 } 

/** 
* 设置本币无税单价
*
* @param nqtprice 本币无税单价
*/
public void setNqtprice ( UFDouble nqtprice) {
this.setAttributeValue( ShipmentsBVO.NQTPRICE,nqtprice);
 } 

/** 
* 获取本币含税净价
*
* @return 本币含税净价
*/
public UFDouble getNqttaxnetprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NQTTAXNETPRICE);
 } 

/** 
* 设置本币含税净价
*
* @param nqttaxnetprice 本币含税净价
*/
public void setNqttaxnetprice ( UFDouble nqttaxnetprice) {
this.setAttributeValue( ShipmentsBVO.NQTTAXNETPRICE,nqttaxnetprice);
 } 

/** 
* 获取本币含税单价
*
* @return 本币含税单价
*/
public UFDouble getNqttaxprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NQTTAXPRICE);
 } 

/** 
* 设置本币含税单价
*
* @param nqttaxprice 本币含税单价
*/
public void setNqttaxprice ( UFDouble nqttaxprice) {
this.setAttributeValue( ShipmentsBVO.NQTTAXPRICE,nqttaxprice);
 } 

/** 
* 获取报价单位数量
*
* @return 报价单位数量
*/
public UFDouble getNqtunitnum () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NQTUNITNUM);
 } 

/** 
* 设置报价单位数量
*
* @param nqtunitnum 报价单位数量
*/
public void setNqtunitnum ( UFDouble nqtunitnum) {
this.setAttributeValue( ShipmentsBVO.NQTUNITNUM,nqtunitnum);
 } 

/** 
* 获取税额
*
* @return 税额
*/
public UFDouble getNtax () {
return this.ntax;
 } 

/** 
* 设置税额
*
* @param ntax 税额
*/
public void setNtax ( UFDouble ntax) {
this.ntax=ntax;
 } 

/** 
* 获取本币价税合计
*
* @return 本币价税合计
*/
public UFDouble getNtaxmny () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NTAXMNY);
 } 

/** 
* 设置本币价税合计
*
* @param ntaxmny 本币价税合计
*/
public void setNtaxmny ( UFDouble ntaxmny) {
this.setAttributeValue( ShipmentsBVO.NTAXMNY,ntaxmny);
 } 

/** 
* 获取主本币含税净价
*
* @return 主本币含税净价
*/
public UFDouble getNtaxnetprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NTAXNETPRICE);
 } 

/** 
* 设置主本币含税净价
*
* @param ntaxnetprice 主本币含税净价
*/
public void setNtaxnetprice ( UFDouble ntaxnetprice) {
this.setAttributeValue( ShipmentsBVO.NTAXNETPRICE,ntaxnetprice);
 } 

/** 
* 获取主本币含税单价
*
* @return 主本币含税单价
*/
public UFDouble getNtaxprice () {
return (UFDouble) this.getAttributeValue( ShipmentsBVO.NTAXPRICE);
 } 

/** 
* 设置主本币含税单价
*
* @param ntaxprice 主本币含税单价
*/
public void setNtaxprice ( UFDouble ntaxprice) {
this.setAttributeValue( ShipmentsBVO.NTAXPRICE,ntaxprice);
 } 

/** 
* 获取税率
*
* @return 税率
*/
public UFDouble getNtaxrate () {
return this.ntaxrate;
 } 

/** 
* 设置税率
*
* @param ntaxrate 税率
*/
public void setNtaxrate ( UFDouble ntaxrate) {
this.ntaxrate=ntaxrate;
 } 

/** 
* 获取其他生产要求
*
* @return 其他生产要求
*/
public String getOtherproduction () {
return this.otherproduction;
 } 

/** 
* 设置其他生产要求
*
* @param otherproduction 其他生产要求
*/
public void setOtherproduction ( String otherproduction) {
this.otherproduction=otherproduction;
 } 

/** 
* 获取单包重(KG)
*
* @return 单包重(KG)
*/
public String getPackageweight () {
return this.packageweight;
 } 

/** 
* 设置单包重(KG)
*
* @param packageweight 单包重(KG)
*/
public void setPackageweight ( String packageweight) {
this.packageweight=packageweight;
 } 

/** 
* 获取批次档案
*
* @return 批次档案
*/
public String getPk_batchcode () {
return this.pk_batchcode;
 } 

/** 
* 设置批次档案
*
* @param pk_batchcode 批次档案
*/
public void setPk_batchcode ( String pk_batchcode) {
this.pk_batchcode=pk_batchcode;
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
* 获取发货申请行主键
*
* @return 发货申请行主键
*/
public String getPk_shipments_b () {
return this.pk_shipments_b;
 } 

/** 
* 设置发货申请行主键
*
* @param pk_shipments_b 发货申请行主键
*/
public void setPk_shipments_b ( String pk_shipments_b) {
this.pk_shipments_b=pk_shipments_b;
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getRowno () {
return this.rowno;
 } 

/** 
* 设置行号
*
* @param rowno 行号
*/
public void setRowno ( String rowno) {
this.rowno=rowno;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getSo_shipmentsb () {
return this.so_shipmentsb;
 } 

/** 
* 设置上层单据主键
*
* @param so_shipmentsb 上层单据主键
*/
public void setSo_shipmentsb ( String so_shipmentsb) {
this.so_shipmentsb=so_shipmentsb;
 } 

/** 
* 获取srcbts（可持续化字段，可以远程传递值，但不能保存到数据库）
*
* @return srcbts
*/
public UFDateTime getSrcbts () {
return (UFDateTime) this.getAttributeValue( ShipmentsBVO.SRCBTS);
 } 

/** 
* 设置srcbts（可持续化字段，可以远程传递值，但不能保存到数据库）
*
* @param srcbts srcbts
*/
public void setSrcbts ( UFDateTime srcbts) {
this.setAttributeValue( ShipmentsBVO.SRCBTS,srcbts);
 } 

/** 
* 获取srcts（可持续化字段，可以远程传递值，但不能保存到数据库）
*
* @return srcts
*/
public UFDateTime getSrcts () {
return (UFDateTime) this.getAttributeValue( ShipmentsBVO.SRCTS);
 } 

/** 
* 设置srcts（可持续化字段，可以远程传递值，但不能保存到数据库）
*
* @param srcts srcts
*/
public void setSrcts ( UFDateTime srcts) {
this.setAttributeValue( ShipmentsBVO.SRCTS,srcts);
 } 

/** 
* 获取标识线
*
* @return 标识线
*/
public String getTagline () {
return this.tagline;
 } 

/** 
* 设置标识线
*
* @param tagline 标识线
*/
public void setTagline ( String tagline) {
this.tagline=tagline;
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
* 获取批次号
*
* @return 批次号
*/
public String getVbatchcode () {
return this.vbatchcode;
 } 

/** 
* 设置批次号
*
* @param vbatchcode 批次号
*/
public void setVbatchcode ( String vbatchcode) {
this.vbatchcode=vbatchcode;
 } 

/** 
* 获取换算率
*
* @return 换算率
*/
public String getVchangerate () {
return this.vchangerate;
 } 

/** 
* 设置换算率
*
* @param vchangerate 换算率
*/
public void setVchangerate ( String vchangerate) {
this.vchangerate=vchangerate;
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstcode () {
return this.vfirstcode;
 } 

/** 
* 设置源头单据号
*
* @param vfirstcode 源头单据号
*/
public void setVfirstcode ( String vfirstcode) {
this.vfirstcode=vfirstcode;
 } 

/** 
* 获取源头单据行号
*
* @return 源头单据行号
*/
public String getVfirstrowno () {
return this.vfirstrowno;
 } 

/** 
* 设置源头单据行号
*
* @param vfirstrowno 源头单据行号
*/
public void setVfirstrowno ( String vfirstrowno) {
this.vfirstrowno=vfirstrowno;
 } 

/** 
* 获取源头交易类型
*
* @return 源头交易类型
*/
public String getVfirsttrantype () {
return this.vfirsttrantype;
 } 

/** 
* 设置源头交易类型
*
* @param vfirsttrantype 源头交易类型
*/
public void setVfirsttrantype ( String vfirsttrantype) {
this.vfirsttrantype=vfirsttrantype;
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getVfirsttype () {
return this.vfirsttype;
 } 

/** 
* 设置源头单据类型
*
* @param vfirsttype 源头单据类型
*/
public void setVfirsttype ( String vfirsttype) {
this.vfirsttype=vfirsttype;
 } 

/** 
* 获取自由辅助属性1
*
* @return 自由辅助属性1
*/
public String getVfree1 () {
return this.vfree1;
 } 

/** 
* 设置自由辅助属性1
*
* @param vfree1 自由辅助属性1
*/
public void setVfree1 ( String vfree1) {
this.vfree1=vfree1;
 } 

/** 
* 获取自由辅助属性10
*
* @return 自由辅助属性10
*/
public String getVfree10 () {
return this.vfree10;
 } 

/** 
* 设置自由辅助属性10
*
* @param vfree10 自由辅助属性10
*/
public void setVfree10 ( String vfree10) {
this.vfree10=vfree10;
 } 

/** 
* 获取自由辅助属性2
*
* @return 自由辅助属性2
*/
public String getVfree2 () {
return this.vfree2;
 } 

/** 
* 设置自由辅助属性2
*
* @param vfree2 自由辅助属性2
*/
public void setVfree2 ( String vfree2) {
this.vfree2=vfree2;
 } 

/** 
* 获取自由辅助属性3
*
* @return 自由辅助属性3
*/
public String getVfree3 () {
return this.vfree3;
 } 

/** 
* 设置自由辅助属性3
*
* @param vfree3 自由辅助属性3
*/
public void setVfree3 ( String vfree3) {
this.vfree3=vfree3;
 } 

/** 
* 获取自由辅助属性4
*
* @return 自由辅助属性4
*/
public String getVfree4 () {
return this.vfree4;
 } 

/** 
* 设置自由辅助属性4
*
* @param vfree4 自由辅助属性4
*/
public void setVfree4 ( String vfree4) {
this.vfree4=vfree4;
 } 

/** 
* 获取自由辅助属性5
*
* @return 自由辅助属性5
*/
public String getVfree5 () {
return this.vfree5;
 } 

/** 
* 设置自由辅助属性5
*
* @param vfree5 自由辅助属性5
*/
public void setVfree5 ( String vfree5) {
this.vfree5=vfree5;
 } 

/** 
* 获取自由辅助属性6
*
* @return 自由辅助属性6
*/
public String getVfree6 () {
return this.vfree6;
 } 

/** 
* 设置自由辅助属性6
*
* @param vfree6 自由辅助属性6
*/
public void setVfree6 ( String vfree6) {
this.vfree6=vfree6;
 } 

/** 
* 获取自由辅助属性7
*
* @return 自由辅助属性7
*/
public String getVfree7 () {
return this.vfree7;
 } 

/** 
* 设置自由辅助属性7
*
* @param vfree7 自由辅助属性7
*/
public void setVfree7 ( String vfree7) {
this.vfree7=vfree7;
 } 

/** 
* 获取自由辅助属性8
*
* @return 自由辅助属性8
*/
public String getVfree8 () {
return this.vfree8;
 } 

/** 
* 设置自由辅助属性8
*
* @param vfree8 自由辅助属性8
*/
public void setVfree8 ( String vfree8) {
this.vfree8=vfree8;
 } 

/** 
* 获取自由辅助属性9
*
* @return 自由辅助属性9
*/
public String getVfree9 () {
return this.vfree9;
 } 

/** 
* 设置自由辅助属性9
*
* @param vfree9 自由辅助属性9
*/
public void setVfree9 ( String vfree9) {
this.vfree9=vfree9;
 } 

/** 
* 获取报价换算率
*
* @return 报价换算率
*/
public String getVqtunitrate () {
return (String) this.getAttributeValue( ShipmentsBVO.VQTUNITRATE);
 } 

/** 
* 设置报价换算率
*
* @param vqtunitrate 报价换算率
*/
public void setVqtunitrate ( String vqtunitrate) {
this.setAttributeValue( ShipmentsBVO.VQTUNITRATE,vqtunitrate);
 } 

/** 
* 获取来源单据号
*
* @return 来源单据号
*/
public String getVsrccode () {
return this.vsrccode;
 } 

/** 
* 设置来源单据号
*
* @param vsrccode 来源单据号
*/
public void setVsrccode ( String vsrccode) {
this.vsrccode=vsrccode;
 } 

/** 
* 获取来源单据行号
*
* @return 来源单据行号
*/
public String getVsrcrowno () {
return this.vsrcrowno;
 } 

/** 
* 设置来源单据行号
*
* @param vsrcrowno 来源单据行号
*/
public void setVsrcrowno ( String vsrcrowno) {
this.vsrcrowno=vsrcrowno;
 } 

/** 
* 获取来源交易类型
*
* @return 来源交易类型
*/
public String getVsrctrantype () {
return this.vsrctrantype;
 } 

/** 
* 设置来源交易类型
*
* @param vsrctrantype 来源交易类型
*/
public void setVsrctrantype ( String vsrctrantype) {
this.vsrctrantype=vsrctrantype;
 } 

/** 
* 获取来源单据类型
*
* @return 来源单据类型
*/
public String getVsrctype () {
return this.vsrctype;
 } 

/** 
* 设置来源单据类型
*
* @param vsrctype 来源单据类型
*/
public void setVsrctype ( String vsrctype) {
this.vsrctype=vsrctype;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.ShipmentsBVO");
  }
}