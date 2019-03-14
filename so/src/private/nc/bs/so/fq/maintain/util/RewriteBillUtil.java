package nc.bs.so.fq.maintain.util;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.so.IShipmentsInfoMaintain;
import nc.pubitf.so.fq.so.m30.RewritefqPara;
import nc.pubitf.so.m30.so.withdraw.IRewriteSaleOrderByWithdraw;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.m4h.entity.BorrowOutBodyVO;
import nc.vo.ic.m4h.entity.BorrowOutHeadVO;
import nc.vo.opc.mc1.entity.CustomerPOBVO;
import nc.vo.opc.mc1.entity.CustomerPOHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.qs.sc.ShipmentsBVO;
import nc.vo.so.qs.sc.ShipmentsVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class RewriteBillUtil {
	
	 public BillRewriter getSrcBillRewriter(){
		 
	      ItemKeyMapping mapping = new ItemKeyMapping();
	      mapping.setVsrctypeKey("vsrctype");
	      mapping.setCsrcidKey("csrcid");
	      mapping.setCsrcbidKey("csrcbid");
	      mapping.setNnumKey("nnum");
	      mapping.setNnum2Key("nastnum");
	      mapping.setSrcTSKey("srcts");
	      
	      BillRewriter tool = new BillRewriter(mapping);
	      	  
	      tool.addSRCHeadClazz(SOBillType.PreOrder.getCode(), PreOrderHVO.class);
	      tool.addSRCItemClazz(SOBillType.PreOrder.getCode(), PreOrderBVO.class);
	      
	      return tool;
	}

	public void reWriteSrc38(List<RewritePara> paraList) {
		// TODO 自动生成的方法存根
		
		int size = paraList.size();
		RewritefqPara[] paras = new RewritefqPara[size];
		
		for (int i = 0; i < size; i++) {
			
			String bid = ((RewritePara)paraList.get(i)).getCsrcbid();
			UFDouble nnum = ((RewritePara)paraList.get(i)).getNnum();
			paras[i] = new RewritefqPara(bid, nnum);
		}
		
		IShipmentsInfoMaintain api = (IShipmentsInfoMaintain)NCLocator.getInstance().lookup(IShipmentsInfoMaintain.class);
		try
		{
			api.rewrite30NumForWithdraw(paras);
		}catch (BusinessException ex){
			ExceptionUtils.wrappException(ex);
		}
		
		
	}

}
