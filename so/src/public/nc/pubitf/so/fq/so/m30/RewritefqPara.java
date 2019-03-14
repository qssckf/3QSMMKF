package nc.pubitf.so.fq.so.m30;
 
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;














public class RewritefqPara
{
  private String bid;

  private UFDouble nnum;
  
  public String getBid() {
	return bid;
  }

  public UFDouble getNnum() {
	return nnum;
  }

  public RewritefqPara(String bid, UFDouble nnum){
    if (PubAppTool.isNull(bid)) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0033"));
    }
    this.bid = bid;
    
    if ((nnum == null) || (UFDouble.ZERO_DBL.equals(nnum))) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0", "04006012-0034"));
    }
    this.nnum = nnum;
  }
  

}

