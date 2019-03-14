/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.so.wa.piece;
	
import nc.vo.pub.*;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 *     在此处添加此类的描述信息
 * </p>
 * 创建日期:
 * @author 
 * @version NCPrj ??
 */
@SuppressWarnings("serial")
public class PieceWaDataVO extends SuperVO {
	private java.lang.String pk_group;
	private java.lang.String pk_org;
	private java.lang.String pk_org_v;
	private java.lang.String pk_wadataid;
	private java.lang.String pk_psndoc;
	private java.lang.String pk_psnjob;
	private java.lang.Integer assgid;
	private java.lang.String cyear;
	private java.lang.String cmonth;
	private nc.vo.pub.lang.UFBoolean calcsf;
	private nc.vo.pub.lang.UFBoolean stopsf;
	private nc.vo.pub.lang.UFBoolean checksf;
	private nc.vo.pub.lang.UFDouble piecepay;
	private java.lang.String piecerule;
	private java.lang.String rule;
	private java.lang.String stard;
	private java.lang.String pk_psnorg;
	private java.lang.String pieceteam;
	private java.lang.String teamname;
	private java.lang.String stardname;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String deptname;
	private java.lang.String postname;
	private java.lang.String def1;
	private java.lang.String def2;
	private java.lang.String def3;
	private java.lang.String def4;
	private java.lang.String def5;
	private java.lang.String def6;
	private java.lang.String def7;
	private java.lang.String def8;
	private java.lang.String def9;
	private java.lang.String def10;
	private java.lang.String def11;
	private java.lang.String def12;
	private java.lang.String def13;
	private java.lang.String def14;
	private java.lang.String def15;
	private java.lang.String def16;
	private java.lang.String def17;
	private java.lang.String def18;
	private java.lang.String def19;
	private java.lang.String def20;
	private java.lang.Integer dr = 0;
	private nc.vo.pub.lang.UFDateTime ts;

	public static final String PK_GROUP = "pk_group";
	public static final String PK_ORG = "pk_org";
	public static final String PK_ORG_V = "pk_org_v";
	public static final String PK_WADATAID = "pk_wadataid";
	public static final String PK_PSNDOC = "pk_psndoc";
	public static final String PK_PSNJOB = "pk_psnjob";
	public static final String ASSGID = "assgid";
	public static final String CYEAR = "cyear";
	public static final String CMONTH = "cmonth";
	public static final String CALCSF = "calcsf";
	public static final String STOPSF = "stopsf";
	public static final String CHECKSF = "checksf";
	public static final String PIECEPAY = "piecepay";
	public static final String PIECERULE = "piecerule";
	public static final String RULE = "rule";
	public static final String STARD = "stard";
	public static final String PK_PSNORG = "pk_psnorg";
	public static final String PIECETEAM = "pieceteam";
	public static final String TEAMNAME = "teamname";
	public static final String STARDNAME = "stardname";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String DEPTNAME = "deptname";
	public static final String POSTNAME = "postname";
	public static final String DEF1 = "def1";
	public static final String DEF2 = "def2";
	public static final String DEF3 = "def3";
	public static final String DEF4 = "def4";
	public static final String DEF5 = "def5";
	public static final String DEF6 = "def6";
	public static final String DEF7 = "def7";
	public static final String DEF8 = "def8";
	public static final String DEF9 = "def9";
	public static final String DEF10 = "def10";
	public static final String DEF11 = "def11";
	public static final String DEF12 = "def12";
	public static final String DEF13 = "def13";
	public static final String DEF14 = "def14";
	public static final String DEF15 = "def15";
	public static final String DEF16 = "def16";
	public static final String DEF17 = "def17";
	public static final String DEF18 = "def18";
	public static final String DEF19 = "def19";
	public static final String DEF20 = "def20";
			
	/**
	 * 属性pk_group的Getter方法.属性名：集团
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return pk_group;
	}   
	/**
	 * 属性pk_group的Setter方法.属性名：集团
	 * 创建日期:
	 * @param newPk_group java.lang.String
	 */
	public void setPk_group (java.lang.String newPk_group ) {
	 	this.pk_group = newPk_group;
	} 	  
	/**
	 * 属性pk_org的Getter方法.属性名：组织
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return pk_org;
	}   
	/**
	 * 属性pk_org的Setter方法.属性名：组织
	 * 创建日期:
	 * @param newPk_org java.lang.String
	 */
	public void setPk_org (java.lang.String newPk_org ) {
	 	this.pk_org = newPk_org;
	} 	  
	/**
	 * 属性pk_org_v的Getter方法.属性名：组织版本
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return pk_org_v;
	}   
	/**
	 * 属性pk_org_v的Setter方法.属性名：组织版本
	 * 创建日期:
	 * @param newPk_org_v java.lang.String
	 */
	public void setPk_org_v (java.lang.String newPk_org_v ) {
	 	this.pk_org_v = newPk_org_v;
	} 	  
	/**
	 * 属性pk_wadataid的Getter方法.属性名：主键
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_wadataid () {
		return pk_wadataid;
	}   
	/**
	 * 属性pk_wadataid的Setter方法.属性名：主键
	 * 创建日期:
	 * @param newPk_wadataid java.lang.String
	 */
	public void setPk_wadataid (java.lang.String newPk_wadataid ) {
	 	this.pk_wadataid = newPk_wadataid;
	} 	  
	/**
	 * 属性pk_psndoc的Getter方法.属性名：人员信息主键
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_psndoc () {
		return pk_psndoc;
	}   
	/**
	 * 属性pk_psndoc的Setter方法.属性名：人员信息主键
	 * 创建日期:
	 * @param newPk_psndoc java.lang.String
	 */
	public void setPk_psndoc (java.lang.String newPk_psndoc ) {
	 	this.pk_psndoc = newPk_psndoc;
	} 	  
	/**
	 * 属性pk_psnjob的Getter方法.属性名：人员任职主键
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_psnjob () {
		return pk_psnjob;
	}   
	/**
	 * 属性pk_psnjob的Setter方法.属性名：人员任职主键
	 * 创建日期:
	 * @param newPk_psnjob java.lang.String
	 */
	public void setPk_psnjob (java.lang.String newPk_psnjob ) {
	 	this.pk_psnjob = newPk_psnjob;
	} 	  
	/**
	 * 属性assgid的Getter方法.属性名：任职关系ID
	 * 创建日期:
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getAssgid () {
		return assgid;
	}   
	/**
	 * 属性assgid的Setter方法.属性名：任职关系ID
	 * 创建日期:
	 * @param newAssgid java.lang.Integer
	 */
	public void setAssgid (java.lang.Integer newAssgid ) {
	 	this.assgid = newAssgid;
	} 	  
	/**
	 * 属性cyear的Getter方法.属性名：年份
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getCyear () {
		return cyear;
	}   
	/**
	 * 属性cyear的Setter方法.属性名：年份
	 * 创建日期:
	 * @param newCyear java.lang.String
	 */
	public void setCyear (java.lang.String newCyear ) {
	 	this.cyear = newCyear;
	} 	  
	/**
	 * 属性cmonth的Getter方法.属性名：期间
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getCmonth () {
		return cmonth;
	}   
	/**
	 * 属性cmonth的Setter方法.属性名：期间
	 * 创建日期:
	 * @param newCmonth java.lang.String
	 */
	public void setCmonth (java.lang.String newCmonth ) {
	 	this.cmonth = newCmonth;
	} 	  
	/**
	 * 属性calcsf的Getter方法.属性名：计算标志
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getCalcsf () {
		return calcsf;
	}   
	/**
	 * 属性calcsf的Setter方法.属性名：计算标志
	 * 创建日期:
	 * @param newCalcsf nc.vo.pub.lang.UFBoolean
	 */
	public void setCalcsf (nc.vo.pub.lang.UFBoolean newCalcsf ) {
	 	this.calcsf = newCalcsf;
	} 	  
	/**
	 * 属性stopsf的Getter方法.属性名：停算标志
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getStopsf () {
		return stopsf;
	}   
	/**
	 * 属性stopsf的Setter方法.属性名：停算标志
	 * 创建日期:
	 * @param newStopsf nc.vo.pub.lang.UFBoolean
	 */
	public void setStopsf (nc.vo.pub.lang.UFBoolean newStopsf ) {
	 	this.stopsf = newStopsf;
	} 	  
	/**
	 * 属性checksf的Getter方法.属性名：审核标志
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getChecksf () {
		return checksf;
	}   
	/**
	 * 属性checksf的Setter方法.属性名：审核标志
	 * 创建日期:
	 * @param newChecksf nc.vo.pub.lang.UFBoolean
	 */
	public void setChecksf (nc.vo.pub.lang.UFBoolean newChecksf ) {
	 	this.checksf = newChecksf;
	} 	  
	/**
	 * 属性piecepay的Getter方法.属性名：计件工资
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getPiecepay () {
		return piecepay;
	}   
	/**
	 * 属性piecepay的Setter方法.属性名：计件工资
	 * 创建日期:
	 * @param newPiecepay nc.vo.pub.lang.UFDouble
	 */
	public void setPiecepay (nc.vo.pub.lang.UFDouble newPiecepay ) {
	 	this.piecepay = newPiecepay;
	} 	  
	/**
	 * 属性piecerule的Getter方法.属性名：计件公式
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPiecerule () {
		return piecerule;
	}   
	/**
	 * 属性piecerule的Setter方法.属性名：计件公式
	 * 创建日期:
	 * @param newPiecerule java.lang.String
	 */
	public void setPiecerule (java.lang.String newPiecerule ) {
	 	this.piecerule = newPiecerule;
	} 	  
	/**
	 * 属性rule的Getter方法.属性名：公式实例
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getRule () {
		return rule;
	}   
	/**
	 * 属性rule的Setter方法.属性名：公式实例
	 * 创建日期:
	 * @param newRule java.lang.String
	 */
	public void setRule (java.lang.String newRule ) {
	 	this.rule = newRule;
	} 	  
	/**
	 * 属性stard的Getter方法.属性名：等级
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getStard () {
		return stard;
	}   
	/**
	 * 属性stard的Setter方法.属性名：等级
	 * 创建日期:
	 * @param newStard java.lang.String
	 */
	public void setStard (java.lang.String newStard ) {
	 	this.stard = newStard;
	} 	  
	/**
	 * 属性pk_psnorg的Getter方法.属性名：组织关系
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_psnorg () {
		return pk_psnorg;
	}   
	/**
	 * 属性pk_psnorg的Setter方法.属性名：组织关系
	 * 创建日期:
	 * @param newPk_psnorg java.lang.String
	 */
	public void setPk_psnorg (java.lang.String newPk_psnorg ) {
	 	this.pk_psnorg = newPk_psnorg;
	} 	  
	/**
	 * 属性pieceteam的Getter方法.属性名：计件班组
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPieceteam () {
		return pieceteam;
	}   
	/**
	 * 属性pieceteam的Setter方法.属性名：计件班组
	 * 创建日期:
	 * @param newPieceteam java.lang.String
	 */
	public void setPieceteam (java.lang.String newPieceteam ) {
	 	this.pieceteam = newPieceteam;
	} 	  
	/**
	 * 属性teamname的Getter方法.属性名：班组名称
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getTeamname () {
		return teamname;
	}   
	/**
	 * 属性teamname的Setter方法.属性名：班组名称
	 * 创建日期:
	 * @param newTeamname java.lang.String
	 */
	public void setTeamname (java.lang.String newTeamname ) {
	 	this.teamname = newTeamname;
	} 	  
	/**
	 * 属性stardname的Getter方法.属性名：等级名称
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getStardname () {
		return stardname;
	}   
	/**
	 * 属性stardname的Setter方法.属性名：等级名称
	 * 创建日期:
	 * @param newStardname java.lang.String
	 */
	public void setStardname (java.lang.String newStardname ) {
	 	this.stardname = newStardname;
	} 	  
	/**
	 * 属性code的Getter方法.属性名：人员编码
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getCode () {
		return code;
	}   
	/**
	 * 属性code的Setter方法.属性名：人员编码
	 * 创建日期:
	 * @param newCode java.lang.String
	 */
	public void setCode (java.lang.String newCode ) {
	 	this.code = newCode;
	} 	  
	/**
	 * 属性name的Getter方法.属性名：姓名
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getName () {
		return name;
	}   
	/**
	 * 属性name的Setter方法.属性名：姓名
	 * 创建日期:
	 * @param newName java.lang.String
	 */
	public void setName (java.lang.String newName ) {
	 	this.name = newName;
	} 	  
	/**
	 * 属性deptname的Getter方法.属性名：所属部门
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDeptname () {
		return deptname;
	}   
	/**
	 * 属性deptname的Setter方法.属性名：所属部门
	 * 创建日期:
	 * @param newDeptname java.lang.String
	 */
	public void setDeptname (java.lang.String newDeptname ) {
	 	this.deptname = newDeptname;
	} 	  
	/**
	 * 属性postname的Getter方法.属性名：岗位
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPostname () {
		return postname;
	}   
	/**
	 * 属性postname的Setter方法.属性名：岗位
	 * 创建日期:
	 * @param newPostname java.lang.String
	 */
	public void setPostname (java.lang.String newPostname ) {
	 	this.postname = newPostname;
	} 	  
	/**
	 * 属性def1的Getter方法.属性名：自定义项1
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef1 () {
		return def1;
	}   
	/**
	 * 属性def1的Setter方法.属性名：自定义项1
	 * 创建日期:
	 * @param newDef1 java.lang.String
	 */
	public void setDef1 (java.lang.String newDef1 ) {
	 	this.def1 = newDef1;
	} 	  
	/**
	 * 属性def2的Getter方法.属性名：自定义项2
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef2 () {
		return def2;
	}   
	/**
	 * 属性def2的Setter方法.属性名：自定义项2
	 * 创建日期:
	 * @param newDef2 java.lang.String
	 */
	public void setDef2 (java.lang.String newDef2 ) {
	 	this.def2 = newDef2;
	} 	  
	/**
	 * 属性def3的Getter方法.属性名：自定义项3
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef3 () {
		return def3;
	}   
	/**
	 * 属性def3的Setter方法.属性名：自定义项3
	 * 创建日期:
	 * @param newDef3 java.lang.String
	 */
	public void setDef3 (java.lang.String newDef3 ) {
	 	this.def3 = newDef3;
	} 	  
	/**
	 * 属性def4的Getter方法.属性名：自定义项4
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef4 () {
		return def4;
	}   
	/**
	 * 属性def4的Setter方法.属性名：自定义项4
	 * 创建日期:
	 * @param newDef4 java.lang.String
	 */
	public void setDef4 (java.lang.String newDef4 ) {
	 	this.def4 = newDef4;
	} 	  
	/**
	 * 属性def5的Getter方法.属性名：自定义项5
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef5 () {
		return def5;
	}   
	/**
	 * 属性def5的Setter方法.属性名：自定义项5
	 * 创建日期:
	 * @param newDef5 java.lang.String
	 */
	public void setDef5 (java.lang.String newDef5 ) {
	 	this.def5 = newDef5;
	} 	  
	/**
	 * 属性def6的Getter方法.属性名：自定义项6
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef6 () {
		return def6;
	}   
	/**
	 * 属性def6的Setter方法.属性名：自定义项6
	 * 创建日期:
	 * @param newDef6 java.lang.String
	 */
	public void setDef6 (java.lang.String newDef6 ) {
	 	this.def6 = newDef6;
	} 	  
	/**
	 * 属性def7的Getter方法.属性名：自定义项7
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef7 () {
		return def7;
	}   
	/**
	 * 属性def7的Setter方法.属性名：自定义项7
	 * 创建日期:
	 * @param newDef7 java.lang.String
	 */
	public void setDef7 (java.lang.String newDef7 ) {
	 	this.def7 = newDef7;
	} 	  
	/**
	 * 属性def8的Getter方法.属性名：自定义项8
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef8 () {
		return def8;
	}   
	/**
	 * 属性def8的Setter方法.属性名：自定义项8
	 * 创建日期:
	 * @param newDef8 java.lang.String
	 */
	public void setDef8 (java.lang.String newDef8 ) {
	 	this.def8 = newDef8;
	} 	  
	/**
	 * 属性def9的Getter方法.属性名：自定义项9
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef9 () {
		return def9;
	}   
	/**
	 * 属性def9的Setter方法.属性名：自定义项9
	 * 创建日期:
	 * @param newDef9 java.lang.String
	 */
	public void setDef9 (java.lang.String newDef9 ) {
	 	this.def9 = newDef9;
	} 	  
	/**
	 * 属性def10的Getter方法.属性名：自定义项10
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef10 () {
		return def10;
	}   
	/**
	 * 属性def10的Setter方法.属性名：自定义项10
	 * 创建日期:
	 * @param newDef10 java.lang.String
	 */
	public void setDef10 (java.lang.String newDef10 ) {
	 	this.def10 = newDef10;
	} 	  
	/**
	 * 属性def11的Getter方法.属性名：自定义项11
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef11 () {
		return def11;
	}   
	/**
	 * 属性def11的Setter方法.属性名：自定义项11
	 * 创建日期:
	 * @param newDef11 java.lang.String
	 */
	public void setDef11 (java.lang.String newDef11 ) {
	 	this.def11 = newDef11;
	} 	  
	/**
	 * 属性def12的Getter方法.属性名：自定义项12
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef12 () {
		return def12;
	}   
	/**
	 * 属性def12的Setter方法.属性名：自定义项12
	 * 创建日期:
	 * @param newDef12 java.lang.String
	 */
	public void setDef12 (java.lang.String newDef12 ) {
	 	this.def12 = newDef12;
	} 	  
	/**
	 * 属性def13的Getter方法.属性名：自定义项13
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef13 () {
		return def13;
	}   
	/**
	 * 属性def13的Setter方法.属性名：自定义项13
	 * 创建日期:
	 * @param newDef13 java.lang.String
	 */
	public void setDef13 (java.lang.String newDef13 ) {
	 	this.def13 = newDef13;
	} 	  
	/**
	 * 属性def14的Getter方法.属性名：自定义项14
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef14 () {
		return def14;
	}   
	/**
	 * 属性def14的Setter方法.属性名：自定义项14
	 * 创建日期:
	 * @param newDef14 java.lang.String
	 */
	public void setDef14 (java.lang.String newDef14 ) {
	 	this.def14 = newDef14;
	} 	  
	/**
	 * 属性def15的Getter方法.属性名：自定义项15
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef15 () {
		return def15;
	}   
	/**
	 * 属性def15的Setter方法.属性名：自定义项15
	 * 创建日期:
	 * @param newDef15 java.lang.String
	 */
	public void setDef15 (java.lang.String newDef15 ) {
	 	this.def15 = newDef15;
	} 	  
	/**
	 * 属性def16的Getter方法.属性名：自定义项16
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef16 () {
		return def16;
	}   
	/**
	 * 属性def16的Setter方法.属性名：自定义项16
	 * 创建日期:
	 * @param newDef16 java.lang.String
	 */
	public void setDef16 (java.lang.String newDef16 ) {
	 	this.def16 = newDef16;
	} 	  
	/**
	 * 属性def17的Getter方法.属性名：自定义项17
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef17 () {
		return def17;
	}   
	/**
	 * 属性def17的Setter方法.属性名：自定义项17
	 * 创建日期:
	 * @param newDef17 java.lang.String
	 */
	public void setDef17 (java.lang.String newDef17 ) {
	 	this.def17 = newDef17;
	} 	  
	/**
	 * 属性def18的Getter方法.属性名：自定义项18
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef18 () {
		return def18;
	}   
	/**
	 * 属性def18的Setter方法.属性名：自定义项18
	 * 创建日期:
	 * @param newDef18 java.lang.String
	 */
	public void setDef18 (java.lang.String newDef18 ) {
	 	this.def18 = newDef18;
	} 	  
	/**
	 * 属性def19的Getter方法.属性名：自定义项19
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef19 () {
		return def19;
	}   
	/**
	 * 属性def19的Setter方法.属性名：自定义项19
	 * 创建日期:
	 * @param newDef19 java.lang.String
	 */
	public void setDef19 (java.lang.String newDef19 ) {
	 	this.def19 = newDef19;
	} 	  
	/**
	 * 属性def20的Getter方法.属性名：自定义项20
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getDef20 () {
		return def20;
	}   
	/**
	 * 属性def20的Setter方法.属性名：自定义项20
	 * 创建日期:
	 * @param newDef20 java.lang.String
	 */
	public void setDef20 (java.lang.String newDef20 ) {
	 	this.def20 = newDef20;
	} 	  
	/**
	 * 属性dr的Getter方法.属性名：dr
	 * 创建日期:
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return dr;
	}   
	/**
	 * 属性dr的Setter方法.属性名：dr
	 * 创建日期:
	 * @param newDr java.lang.Integer
	 */
	public void setDr (java.lang.Integer newDr ) {
	 	this.dr = newDr;
	} 	  
	/**
	 * 属性ts的Getter方法.属性名：ts
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return ts;
	}   
	/**
	 * 属性ts的Setter方法.属性名：ts
	 * 创建日期:
	 * @param newTs nc.vo.pub.lang.UFDateTime
	 */
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
	 	this.ts = newTs;
	} 	  
 
	/**
	  * <p>取得父VO主键字段.
	  * <p>
	  * 创建日期:
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
	  return "pk_wadataid";
	}
    
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "so_pwdata";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "so_pwdata";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:
	  */
     public PieceWaDataVO() {
		super();	
	}    
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName =  "nc.vo.so.wa.piece.PieceWaDataVO" )
	public IVOMeta getMetaData() {
   		return null;
  	}
} 


