/***************************************************************\
 *     The skeleton of this class is generated by an automatic *
 * code generator for NC product. It is based on Velocity.     *
\***************************************************************/
package nc.vo.so.qs.sc;
	
import java.lang.reflect.Method;

import org.apache.commons.beanutils.Converter;

import nc.jdbc.framework.util.BeanConvertor;
import nc.jdbc.framework.util.StringConvertor;
import nc.vo.hr.append.IAppendableVO;
import nc.vo.pub.*;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

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
public class MaschineVO extends SuperVO implements IAppendableVO{
	private java.lang.String pk_maschine;
	private java.lang.String pk_group;
	private java.lang.String pk_org;
	private java.lang.String pk_org_v;
	private java.lang.String machcode;
	private java.lang.String machname;
	private nc.vo.pub.lang.UFDouble machrangestart;
	private nc.vo.pub.lang.UFDouble machrangeend;
	private java.lang.String machspec;
	private java.lang.String machcust;
	private nc.vo.pub.lang.UFDouble silkwidestart;
	private nc.vo.pub.lang.UFDouble silkwideend;
	private nc.vo.pub.lang.UFDouble elongationstart;
	private nc.vo.pub.lang.UFDouble elongationend;
	private nc.vo.pub.lang.UFDouble tensionstart;
	private nc.vo.pub.lang.UFDouble tensionend;
	private nc.vo.pub.lang.UFBoolean spacer;
	private java.lang.String splitstofftype;
	private java.lang.Integer mstatus;
	private java.lang.String memo;
	private java.lang.String creator;
	private nc.vo.pub.lang.UFDateTime creationtime;
	private java.lang.String modifier;
	private nc.vo.pub.lang.UFDateTime modifiedtime;
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
	private nc.vo.pub.lang.UFDouble widthstart;
	private nc.vo.pub.lang.UFDouble widthend;
	private nc.vo.pub.lang.UFDouble lengthstart;
	private nc.vo.pub.lang.UFDouble lengend;
	private nc.vo.pub.lang.UFDouble latitudedensitystart;
	private nc.vo.pub.lang.UFDouble latitudedensityend;
	private java.lang.Integer dr = 0;
	private nc.vo.pub.lang.UFDateTime ts;

	public static final String PK_MASCHINE = "pk_maschine";
	public static final String PK_GROUP = "pk_group";
	public static final String PK_ORG = "pk_org";
	public static final String PK_ORG_V = "pk_org_v";
	public static final String MACHCODE = "machcode";
	public static final String MACHNAME = "machname";
	public static final String MACHRANGESTART = "machrangestart";
	public static final String MACHRANGEEND = "machrangeend";
	public static final String MACHSPEC = "machspec";
	public static final String MACHCUST = "machcust";
	public static final String SILKWIDESTART = "silkwidestart";
	public static final String SILKWIDEEND = "silkwideend";
	public static final String ELONGATIONSTART = "elongationstart";
	public static final String ELONGATIONEND = "elongationend";
	public static final String TENSIONSTART = "tensionstart";
	public static final String TENSIONEND = "tensionend";
	public static final String SPACER = "spacer";
	public static final String SPLITSTOFFTYPE = "splitstofftype";
	public static final String MSTATUS = "mstatus";
	public static final String MEMO = "memo";
	public static final String CREATOR = "creator";
	public static final String CREATIONTIME = "creationtime";
	public static final String MODIFIER = "modifier";
	public static final String MODIFIEDTIME = "modifiedtime";
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
	public static final String WIDTHSTART = "widthstart";
	public static final String WIDTHEND = "widthend";
	public static final String LENGTHSTART = "lengthstart";
	public static final String LENGEND = "lengend";
	public static final String LATITUDEDENSITYSTART = "latitudedensitystart";
	public static final String LATITUDEDENSITYEND = "latitudedensityend";
			
	/**
	 * 属性pk_maschine的Getter方法.属性名：机器主键
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getPk_maschine () {
		return pk_maschine;
	}   
	/**
	 * 属性pk_maschine的Setter方法.属性名：机器主键
	 * 创建日期:
	 * @param newPk_maschine java.lang.String
	 */
	public void setPk_maschine (java.lang.String newPk_maschine ) {
	 	this.pk_maschine = newPk_maschine;
	} 	  
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
	 * 属性machcode的Getter方法.属性名：机器号
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getMachcode () {
		return machcode;
	}   
	/**
	 * 属性machcode的Setter方法.属性名：机器号
	 * 创建日期:
	 * @param newMachcode java.lang.String
	 */
	public void setMachcode (java.lang.String newMachcode ) {
	 	this.machcode = newMachcode;
	} 	  
	/**
	 * 属性machname的Getter方法.属性名：机器名称
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getMachname () {
		return machname;
	}   
	/**
	 * 属性machname的Setter方法.属性名：机器名称
	 * 创建日期:
	 * @param newMachname java.lang.String
	 */
	public void setMachname (java.lang.String newMachname ) {
	 	this.machname = newMachname;
	} 	  
	/**
	 * 属性machrangestart的Getter方法.属性名：机器工作范围起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getMachrangestart () {
		return machrangestart;
	}   
	/**
	 * 属性machrangestart的Setter方法.属性名：机器工作范围起
	 * 创建日期:
	 * @param newMachrangestart nc.vo.pub.lang.UFDouble
	 */
	public void setMachrangestart (nc.vo.pub.lang.UFDouble newMachrangestart ) {
	 	this.machrangestart = newMachrangestart;
	} 	  
	/**
	 * 属性machrangeend的Getter方法.属性名：机器工作范围止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getMachrangeend () {
		return machrangeend;
	}   
	/**
	 * 属性machrangeend的Setter方法.属性名：机器工作范围止
	 * 创建日期:
	 * @param newMachrangeend nc.vo.pub.lang.UFDouble
	 */
	public void setMachrangeend (nc.vo.pub.lang.UFDouble newMachrangeend ) {
	 	this.machrangeend = newMachrangeend;
	} 	  
	/**
	 * 属性machspec的Getter方法.属性名：机器规格
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getMachspec () {
		return machspec;
	}   
	/**
	 * 属性machspec的Setter方法.属性名：机器规格
	 * 创建日期:
	 * @param newMachspec java.lang.String
	 */
	public void setMachspec (java.lang.String newMachspec ) {
	 	this.machspec = newMachspec;
	} 	  
	/**
	 * 属性machcust的Getter方法.属性名：机主
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getMachcust () {
		return machcust;
	}   
	/**
	 * 属性machcust的Setter方法.属性名：机主
	 * 创建日期:
	 * @param newMachcust java.lang.String
	 */
	public void setMachcust (java.lang.String newMachcust ) {
	 	this.machcust = newMachcust;
	} 	  
	/**
	 * 属性silkwidestart的Getter方法.属性名：丝宽起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getSilkwidestart () {
		return silkwidestart;
	}   
	/**
	 * 属性silkwidestart的Setter方法.属性名：丝宽起
	 * 创建日期:
	 * @param newSilkwidestart nc.vo.pub.lang.UFDouble
	 */
	public void setSilkwidestart (nc.vo.pub.lang.UFDouble newSilkwidestart ) {
	 	this.silkwidestart = newSilkwidestart;
	} 	  
	/**
	 * 属性silkwideend的Getter方法.属性名：丝宽止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getSilkwideend () {
		return silkwideend;
	}   
	/**
	 * 属性silkwideend的Setter方法.属性名：丝宽止
	 * 创建日期:
	 * @param newSilkwideend nc.vo.pub.lang.UFDouble
	 */
	public void setSilkwideend (nc.vo.pub.lang.UFDouble newSilkwideend ) {
	 	this.silkwideend = newSilkwideend;
	} 	  
	/**
	 * 属性elongationstart的Getter方法.属性名：伸长率起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getElongationstart () {
		return elongationstart;
	}   
	/**
	 * 属性elongationstart的Setter方法.属性名：伸长率起
	 * 创建日期:
	 * @param newElongationstart nc.vo.pub.lang.UFDouble
	 */
	public void setElongationstart (nc.vo.pub.lang.UFDouble newElongationstart ) {
	 	this.elongationstart = newElongationstart;
	} 	  
	/**
	 * 属性elongationend的Getter方法.属性名：伸长率止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getElongationend () {
		return elongationend;
	}   
	/**
	 * 属性elongationend的Setter方法.属性名：伸长率止
	 * 创建日期:
	 * @param newElongationend nc.vo.pub.lang.UFDouble
	 */
	public void setElongationend (nc.vo.pub.lang.UFDouble newElongationend ) {
	 	this.elongationend = newElongationend;
	} 	  
	/**
	 * 属性tensionstart的Getter方法.属性名：拉力起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getTensionstart () {
		return tensionstart;
	}   
	/**
	 * 属性tensionstart的Setter方法.属性名：拉力起
	 * 创建日期:
	 * @param newTensionstart nc.vo.pub.lang.UFDouble
	 */
	public void setTensionstart (nc.vo.pub.lang.UFDouble newTensionstart ) {
	 	this.tensionstart = newTensionstart;
	} 	  
	/**
	 * 属性tensionend的Getter方法.属性名：拉力止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getTensionend () {
		return tensionend;
	}   
	/**
	 * 属性tensionend的Setter方法.属性名：拉力止
	 * 创建日期:
	 * @param newTensionend nc.vo.pub.lang.UFDouble
	 */
	public void setTensionend (nc.vo.pub.lang.UFDouble newTensionend ) {
	 	this.tensionend = newTensionend;
	} 	  
	/**
	 * 属性spacer的Getter方法.属性名：垫片
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getSpacer () {
		return spacer;
	}   
	/**
	 * 属性spacer的Setter方法.属性名：垫片
	 * 创建日期:
	 * @param newSpacer nc.vo.pub.lang.UFBoolean
	 */
	public void setSpacer (nc.vo.pub.lang.UFBoolean newSpacer ) {
	 	this.spacer = newSpacer;
	} 	  
	/**
	 * 属性splitstofftype的Getter方法.属性名：剖布类型
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getSplitstofftype () {
		return splitstofftype;
	}   
	/**
	 * 属性splitstofftype的Setter方法.属性名：剖布类型
	 * 创建日期:
	 * @param newSplitstofftype java.lang.String
	 */
	public void setSplitstofftype (java.lang.String newSplitstofftype ) {
	 	this.splitstofftype = newSplitstofftype;
	} 	  
	/**
	 * 属性mstatus的Getter方法.属性名：状态
	 * 创建日期:
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getMstatus () {
		return mstatus;
	}   
	/**
	 * 属性mstatus的Setter方法.属性名：状态
	 * 创建日期:
	 * @param newMstatus java.lang.Integer
	 */
	public void setMstatus (java.lang.Integer newMstatus ) {
	 	this.mstatus = newMstatus;
	} 	  
	/**
	 * 属性memo的Getter方法.属性名：备注
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getMemo () {
		return memo;
	}   
	/**
	 * 属性memo的Setter方法.属性名：备注
	 * 创建日期:
	 * @param newMemo java.lang.String
	 */
	public void setMemo (java.lang.String newMemo ) {
	 	this.memo = newMemo;
	} 	  
	/**
	 * 属性creator的Getter方法.属性名：创建人
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getCreator () {
		return creator;
	}   
	/**
	 * 属性creator的Setter方法.属性名：创建人
	 * 创建日期:
	 * @param newCreator java.lang.String
	 */
	public void setCreator (java.lang.String newCreator ) {
	 	this.creator = newCreator;
	} 	  
	/**
	 * 属性creationtime的Getter方法.属性名：创建时间
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime () {
		return creationtime;
	}   
	/**
	 * 属性creationtime的Setter方法.属性名：创建时间
	 * 创建日期:
	 * @param newCreationtime nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime (nc.vo.pub.lang.UFDateTime newCreationtime ) {
	 	this.creationtime = newCreationtime;
	} 	  
	/**
	 * 属性modifier的Getter方法.属性名：修改人
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getModifier () {
		return modifier;
	}   
	/**
	 * 属性modifier的Setter方法.属性名：修改人
	 * 创建日期:
	 * @param newModifier java.lang.String
	 */
	public void setModifier (java.lang.String newModifier ) {
	 	this.modifier = newModifier;
	} 	  
	/**
	 * 属性modifiedtime的Getter方法.属性名：修改时间
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime () {
		return modifiedtime;
	}   
	/**
	 * 属性modifiedtime的Setter方法.属性名：修改时间
	 * 创建日期:
	 * @param newModifiedtime nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime (nc.vo.pub.lang.UFDateTime newModifiedtime ) {
	 	this.modifiedtime = newModifiedtime;
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
	 * 属性widthstart的Getter方法.属性名：宽度起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getWidthstart () {
		return widthstart;
	}   
	/**
	 * 属性widthstart的Setter方法.属性名：宽度起
	 * 创建日期:
	 * @param newWidthstart nc.vo.pub.lang.UFDouble
	 */
	public void setWidthstart (nc.vo.pub.lang.UFDouble newWidthstart ) {
	 	this.widthstart = newWidthstart;
	} 	  
	/**
	 * 属性widthend的Getter方法.属性名：宽度止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getWidthend () {
		return widthend;
	}   
	/**
	 * 属性widthend的Setter方法.属性名：宽度止
	 * 创建日期:
	 * @param newWidthend nc.vo.pub.lang.UFDouble
	 */
	public void setWidthend (nc.vo.pub.lang.UFDouble newWidthend ) {
	 	this.widthend = newWidthend;
	} 	  
	/**
	 * 属性lengthstart的Getter方法.属性名：长度起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getLengthstart () {
		return lengthstart;
	}   
	/**
	 * 属性lengthstart的Setter方法.属性名：长度起
	 * 创建日期:
	 * @param newLengthstart nc.vo.pub.lang.UFDouble
	 */
	public void setLengthstart (nc.vo.pub.lang.UFDouble newLengthstart ) {
	 	this.lengthstart = newLengthstart;
	} 	  
	/**
	 * 属性lengend的Getter方法.属性名：长度止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getLengend () {
		return lengend;
	}   
	/**
	 * 属性lengend的Setter方法.属性名：长度止
	 * 创建日期:
	 * @param newLengend nc.vo.pub.lang.UFDouble
	 */
	public void setLengend (nc.vo.pub.lang.UFDouble newLengend ) {
	 	this.lengend = newLengend;
	} 	  
	/**
	 * 属性latitudedensitystart的Getter方法.属性名：纬密起
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getLatitudedensitystart () {
		return latitudedensitystart;
	}   
	/**
	 * 属性latitudedensitystart的Setter方法.属性名：纬密起
	 * 创建日期:
	 * @param newLatitudedensitystart nc.vo.pub.lang.UFDouble
	 */
	public void setLatitudedensitystart (nc.vo.pub.lang.UFDouble newLatitudedensitystart ) {
	 	this.latitudedensitystart = newLatitudedensitystart;
	} 	  
	/**
	 * 属性latitudedensityend的Getter方法.属性名：纬密止
	 * 创建日期:
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getLatitudedensityend () {
		return latitudedensityend;
	}   
	/**
	 * 属性latitudedensityend的Setter方法.属性名：纬密止
	 * 创建日期:
	 * @param newLatitudedensityend nc.vo.pub.lang.UFDouble
	 */
	public void setLatitudedensityend (nc.vo.pub.lang.UFDouble newLatitudedensityend ) {
	 	this.latitudedensityend = newLatitudedensityend;
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
	  return "pk_maschine";
	}
    
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "so_machine";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "so_machine";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:
	  */
     public MaschineVO() {
		super();	
	}    
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName =  "nc.vo.so.qs.sc.MaschineVO" )
	public IVOMeta getMetaData() {
		IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.MaschineVO");
   		return meta;
  	}
	
	
	public void setAttributeValue(String name, Object value) {
		// TODO 自动生成的方法存根
		
		String attributeName = name.toLowerCase();
		Method method = BeanHelper.getMethod(this, attributeName);
		if (method != null)
		{
			Converter converter = BeanConvertor.getConVerter(method.getParameterTypes()[0]);
			if ((converter != null) && (value != null))
			{
				if (!(converter instanceof StringConvertor)) {
					value = converter.convert(method.getParameterTypes()[0], value);
				}
			}
			
			super.setAttributeValue(attributeName, value);
			}
		
		super.setAttributeValue(name, value);
	}
	
} 


