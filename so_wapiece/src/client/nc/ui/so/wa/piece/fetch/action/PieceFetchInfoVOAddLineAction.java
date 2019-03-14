package nc.ui.so.wa.piece.fetch.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;

public class PieceFetchInfoVOAddLineAction extends BatchAddLineAction {

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void setDefaultData(Object obj) {
    super.setDefaultData(obj);
    UFDateTime df=new UFDateTime();
    
    PieceFetchInfoVO baseDocVO = (PieceFetchInfoVO) obj;
    baseDocVO.setPk_group(this.getModel().getContext().getPk_group());
    baseDocVO.setPk_org(this.getModel().getContext().getPk_org());
    baseDocVO.setCreationtime(df.getDateTimeAfter(0));
    baseDocVO.setCreator(this.getModel().getContext().getPk_loginUser());
  }


}
