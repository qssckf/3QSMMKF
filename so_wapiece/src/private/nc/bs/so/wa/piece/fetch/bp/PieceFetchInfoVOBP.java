package nc.bs.so.wa.piece.fetch.bp;

import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.wa.piece.fetch.PieceFetchInfoVO;

public class PieceFetchInfoVOBP {
  
  public PieceFetchInfoVO[] queryByQueryScheme(IQueryScheme querySheme) {
    QuerySchemeProcessor p = new QuerySchemeProcessor(querySheme);
    p.appendFuncPermissionOrgSql();
    return new SchemeVOQuery<PieceFetchInfoVO>(PieceFetchInfoVO.class).query(
        querySheme, null);
  }

}
