package nc.pubitf.so.fq.so.m30;
 
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;














public class Rewrite30Para
{
  private String pk_ship;
  private UFDouble nnum;
  
  public String getPk_ship() {
	return pk_ship;
  }

  public UFDouble getNnum() {
	return nnum;
  }


  public Rewrite30Para(String bid, UFDouble nnum){
    if (PubAppTool.isNull(bid)) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0033"));
    }
    this.pk_ship = bid;
    
    if ((nnum == null) || (UFDouble.ZERO_DBL.equals(nnum))) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0034"));
    }
    this.nnum = nnum;
  }
  

}

