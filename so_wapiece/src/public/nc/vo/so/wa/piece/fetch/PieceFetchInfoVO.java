package nc.vo.so.wa.piece.fetch;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class PieceFetchInfoVO extends SuperVO {
/**
*取数规则编码
*/
public String code;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*创建人
*/
public String creator;
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
*结束日期
*/
public Integer endday;
/**
*结束期间
*/
public Integer endperiod;
/**
*取数方案
*/
public Integer fetchscheme;
/**
*是否取数
*/
public UFBoolean fetchsf;
/**
*取数类型
*/
public Integer fetchtype;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*修改人
*/
public String modifier;
/**
*取数规则名称
*/
public String name;
/**
*主键
*/
public String pk_fetchid;
/**
*集团
*/
public String pk_group;
/**
*组织
*/
public String pk_org;
/**
*组织版本
*/
public String pk_org_v;
/**
*主表取数语义模型
*/
public String smartdef1;
/**
*子表取数语义模型
*/
public String smartdef2;
/**
*开始日期
*/
public Integer startday;
/**
*开始期间
*/
public Integer startperiod;
/**
*时间戳
*/
public UFDateTime ts;
/**
*主表VO类
*/
public String voclass1;
/**
*子表VO类
*/
public String voclass2;
/** 
* 获取取数规则编码
*
* @return 取数规则编码
*/
public String getCode () {
return this.code;
 } 

/** 
* 设置取数规则编码
*
* @param code 取数规则编码
*/
public void setCode ( String code) {
this.code=code;
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
* 获取结束日期
*
* @return 结束日期
* @see String
*/
public Integer getEndday () {
return this.endday;
 } 

/** 
* 设置结束日期
*
* @param endday 结束日期
* @see String
*/
public void setEndday ( Integer endday) {
this.endday=endday;
 } 

/** 
* 获取结束期间
*
* @return 结束期间
* @see String
*/
public Integer getEndperiod () {
return this.endperiod;
 } 

/** 
* 设置结束期间
*
* @param endperiod 结束期间
* @see String
*/
public void setEndperiod ( Integer endperiod) {
this.endperiod=endperiod;
 } 

/** 
* 获取取数方案
*
* @return 取数方案
* @see String
*/
public Integer getFetchscheme () {
return this.fetchscheme;
 } 

/** 
* 设置取数方案
*
* @param fetchscheme 取数方案
* @see String
*/
public void setFetchscheme ( Integer fetchscheme) {
this.fetchscheme=fetchscheme;
 } 

/** 
* 获取是否取数
*
* @return 是否取数
*/
public UFBoolean getFetchsf () {
return this.fetchsf;
 } 

/** 
* 设置是否取数
*
* @param fetchsf 是否取数
*/
public void setFetchsf ( UFBoolean fetchsf) {
this.fetchsf=fetchsf;
 } 

/** 
* 获取取数类型
*
* @return 取数类型
* @see String
*/
public Integer getFetchtype () {
return this.fetchtype;
 } 

/** 
* 设置取数类型
*
* @param fetchtype 取数类型
* @see String
*/
public void setFetchtype ( Integer fetchtype) {
this.fetchtype=fetchtype;
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
* 获取取数规则名称
*
* @return 取数规则名称
*/
public String getName () {
return this.name;
 } 

/** 
* 设置取数规则名称
*
* @param name 取数规则名称
*/
public void setName ( String name) {
this.name=name;
 } 

/** 
* 获取主键
*
* @return 主键
*/
public String getPk_fetchid () {
return this.pk_fetchid;
 } 

/** 
* 设置主键
*
* @param pk_fetchid 主键
*/
public void setPk_fetchid ( String pk_fetchid) {
this.pk_fetchid=pk_fetchid;
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
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return this.pk_org;
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.pk_org=pk_org;
 } 

/** 
* 获取组织版本
*
* @return 组织版本
*/
public String getPk_org_v () {
return this.pk_org_v;
 } 

/** 
* 设置组织版本
*
* @param pk_org_v 组织版本
*/
public void setPk_org_v ( String pk_org_v) {
this.pk_org_v=pk_org_v;
 } 

/** 
* 获取主表取数语义模型
*
* @return 主表取数语义模型
*/
public String getSmartdef1 () {
return this.smartdef1;
 } 

/** 
* 设置主表取数语义模型
*
* @param smartdef1 主表取数语义模型
*/
public void setSmartdef1 ( String smartdef1) {
this.smartdef1=smartdef1;
 } 

/** 
* 获取子表取数语义模型
*
* @return 子表取数语义模型
*/
public String getSmartdef2 () {
return this.smartdef2;
 } 

/** 
* 设置子表取数语义模型
*
* @param smartdef2 子表取数语义模型
*/
public void setSmartdef2 ( String smartdef2) {
this.smartdef2=smartdef2;
 } 

/** 
* 获取开始日期
*
* @return 开始日期
* @see String
*/
public Integer getStartday () {
return this.startday;
 } 

/** 
* 设置开始日期
*
* @param startday 开始日期
* @see String
*/
public void setStartday ( Integer startday) {
this.startday=startday;
 } 

/** 
* 获取开始期间
*
* @return 开始期间
* @see String
*/
public Integer getStartperiod () {
return this.startperiod;
 } 

/** 
* 设置开始期间
*
* @param startperiod 开始期间
* @see String
*/
public void setStartperiod ( Integer startperiod) {
this.startperiod=startperiod;
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
* 获取主表VO类
*
* @return 主表VO类
*/
public String getVoclass1 () {
return this.voclass1;
 } 

/** 
* 设置主表VO类
*
* @param voclass1 主表VO类
*/
public void setVoclass1 ( String voclass1) {
this.voclass1=voclass1;
 } 

/** 
* 获取子表VO类
*
* @return 子表VO类
*/
public String getVoclass2 () {
return this.voclass2;
 } 

/** 
* 设置子表VO类
*
* @param voclass2 子表VO类
*/
public void setVoclass2 ( String voclass2) {
this.voclass2=voclass2;
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.PieceFetchInfoVO");
  }
}