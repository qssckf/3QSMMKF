package nc.bs.so.wa.piece.pf.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.wa.piece.PieceProductVO;

public class PieceProductVOBP {
  
  public PieceProductVO[] queryByQueryScheme(IQueryScheme querySheme) {
    QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
    p.appendFuncPermissionOrgSql();
    return new SchemeVOQuery<PieceProductVO>(PieceProductVO.class).query(
        querySheme, null);
  }

}
