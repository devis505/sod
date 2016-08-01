package com.webdivas.sod.db.repository;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webdivas.sod.db.core.IRepository;
import com.webdivas.sod.db.model.Query;
import com.webdivas.sod.db.repository.actions.SelectQuery;
import com.webdivas.sod.db.utils.QueryParams;
import com.webdivas.sod.request.json.JsonRequestParams;

@Component
public class QueryRepository implements IRepository {

	@Autowired
	private DataSource dataSourceH2;

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

		SelectQuery select = new SelectQuery(dataSourceH2, qParams.getSqlParameters(),
				SQL_FIND_QUERY_BY_ID);
		return select.executeByNamedParam(qParams.getParamsValue());
	}

	@Override
	public List<?> getByParams(JsonRequestParams params, Query query) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String SQL_FIND_QUERY_BY_ID = "select * from sod_query s where s.nm_query = :nm_query";

}
