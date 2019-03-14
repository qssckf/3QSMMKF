package nc.ui.so.wa.piece.pf.action;

import nc.itf.espub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.so.wa.piece.PieceProductVO;

public class PieceProductVOAddLineAction extends BatchAddLineAction {

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void setDefaultData(Object obj) {
    super.setDefaultData(obj);
	UFDateTime df=new UFDateTime();
	
    PieceProductVO baseDocVO = (PieceProductVO) obj;
    baseDocVO.setPk_group(this.getModel().getContext().getPk_group());
    baseDocVO.setPk_org(this.getModel().getContext().getPk_org());
    baseDocVO.setPk_org_v(OrgUnitPubService.getOrgVid(this.getModel().getContext().getPk_org()));
	baseDocVO.setCreationtime(df.getDateTimeAfter(0));
	baseDocVO.setCreator(this.getModel().getContext().getPk_loginUser());
  }


}
