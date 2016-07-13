package com.webdivas.sod.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import com.webdivas.sod.db.core.IRepository;
import com.webdivas.sod.db.model.Query;
import com.webdivas.sod.db.utils.QueryParams;
import com.webdivas.sod.request.json.JsonRequestParams;

@Component
public class QueryRepository implements IRepository {

    @Autowired
    private ConnectList connects;

    @Override
    public List<?> create(JsonRequestParams params, Query query) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<?> update(JsonRequestParams params, Query query) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<?> delete(JsonRequestParams params, Query query) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Query> getByName(String name) {

	QueryParams qParams = new QueryParams();
	qParams.addParamValue("nm_query", name, Types.VARCHAR);

	Select select = new Select(connects.getDataSource("dataSourceH2"), qParams.getSqlParameters(),
		SQL_FIND_QUERY_BY_ID);
	return select.executeByNamedParam(qParams.getParamsValue());
    }

    @Override
    public List<?> getByParams(JsonRequestParams params, Query query) {
	// TODO Auto-generated method stub
	return null;
    }

    class Select extends MappingSqlQuery<Query> {

	public Select(DataSource dataSource, List<SqlParameter> sqlParameters, String queryBody) {

	    super(dataSource, queryBody);

	    for (SqlParameter sqlParameter : sqlParameters) {
		super.declareParameter(sqlParameter);
	    }
	}

	@Override
	protected Query mapRow(ResultSet rs, int rowNum) throws SQLException {
	    return new Query(rs.getLong("id_query"), rs.getString("nm_query"), rs.getString("vl_query"),
		    rs.getInt("id_connector"), rs.getInt("id_query_type"));
	}
    }

    private static final String SQL_FIND_QUERY_BY_ID = "select * from sod_query s where s.nm_query = :nm_query";

}
