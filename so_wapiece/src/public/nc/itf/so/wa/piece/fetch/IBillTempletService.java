package nc.itf.so.wa.piece.fetch;

import java.sql.SQLException;

import nc.vo.pub.BusinessException;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletVO;

public interface IBillTempletService {
	
	public abstract BillTempletVO queryByID(String id,String corp)
		    throws BusinessException, SQLException;

}
