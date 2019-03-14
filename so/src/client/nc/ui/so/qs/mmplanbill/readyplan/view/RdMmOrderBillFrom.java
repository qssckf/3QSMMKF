package nc.ui.so.qs.mmplanbill.readyplan.view;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class RdMmOrderBillFrom extends ShowUpableBillForm implements OnhandPanelSrc{

	private String[] headFields={"processer","pk_dept","splitstofftype","silkwide","elongation","tension","spacer","otherproduction","spacer","tagline","latitudedensity","width","length","prodnum","prodnastnum","proddate","enddate","def1"};
	
	private OnhandPanelAdaptor adaptor;
	
	 public OnhandPanelAdaptor getExtendedPanel(){
       return this.adaptor;
     }
     
     public void setExtendedPanel(OnhandPanelAdaptor adaptor) {
       this.adaptor = adaptor;
     }

	@Override
	protected void onEdit() {
		// TODO 自动生成的方法存根
		super.onEdit();
		
		BillItem[] heads=this.getBillCardPanel().getHeadItems();
		
		for(BillItem item:heads){
			item.setEnabled(false);
		}
		
		for(String field:headFields){
			if(this.billCardPanel.getHeadTailItem(field)!=null){
				this.billCardPanel.getHeadTailItem(field).setEnabled(true);
			}
		}
		
		
		
	}

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		
		this.setComponentVisible(true);
	}

	@Override
	public OnhandDimParamVO getQryOnhandDim(int paramInt) {
		// TODO 自动生成的方法存根
		try{
			
			IKeyValue util = new CardKeyValue(getBillCardPanel());
			IMaterialPubService service = (IMaterialPubService)NCLocator.getInstance().lookup(IMaterialPubService.class);
			
			Map<String, MaterialVO> matermap = service.queryMaterialBaseInfoByPks(new String[]{util.getHeadStringValue("pk_material")},new String[]{"pk_source"});
			String pk_material_v= matermap.get(util.getHeadStringValue("pk_material")).getPk_source();
			
			
			OnhandDimParamVO paraVO = new OnhandDimParamVO();
			paraVO.setPk_group(getModel().getContext().getPk_group());
			paraVO.setCastunitid(util.getHeadStringValue("munit"));
			paraVO.setCmaterialoid(util.getHeadStringValue("pk_material"));
			
			paraVO.setCmaterialvid(pk_material_v);
			
			paraVO.setCproductorid(util.getHeadStringValue("cproductorid"));
			
			paraVO.setCprojectid(util.getHeadStringValue("cprojectid"));
			paraVO.setCvendorid(util.getHeadStringValue("cprojectid"));
			paraVO.setCwarehouseid(util.getHeadStringValue("cvendorid"));
			
			paraVO.setPk_batchcode(null);
			
			paraVO.setVbatchcode(null);
			paraVO.setPk_org(util.getHeadStringValue("pk_org"));
			
			paraVO.setPk_org_v(util.getHeadStringValue("pk_org_v"));
			
			paraVO.setVbatchcode(null);
			paraVO.setVchangerate(util.getHeadStringValue("vchangerate"));
			
			paraVO.setVfree1(util.getHeadStringValue("vfree1"));
			paraVO.setVfree2(util.getHeadStringValue("vfree2"));
			paraVO.setVfree3(util.getHeadStringValue("vfree3"));
			paraVO.setVfree4(util.getHeadStringValue("vfree4"));
			paraVO.setVfree5(util.getHeadStringValue("vfree5"));
			paraVO.setVfree6(util.getHeadStringValue("vfree6"));
			paraVO.setVfree7(util.getHeadStringValue("vfree7"));
			paraVO.setVfree8(util.getHeadStringValue("vfree8"));
			paraVO.setVfree9(util.getHeadStringValue("vfree9"));
			paraVO.setVfree10(util.getHeadStringValue("vfree10"));
			return paraVO;
			
			
			
		}catch(Exception e){
			ExceptionUtils.wrappException(e);
		}
		return null;
		
	
		
	}

	@Override
	public void addCardBodyRowChangedEvent(
			IAppEventHandler<CardBodyRowChangedEvent> l) {
		// TODO 自动生成的方法存根
		((IAppModelEx)getModel()).addAppEventListener(CardBodyRowChangedEvent.class, l);
	}
	
	
	
	

}
