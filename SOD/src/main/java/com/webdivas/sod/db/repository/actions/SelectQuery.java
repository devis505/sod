package com.webdivas.sod.db.repository.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.webdivas.sod.db.model.Query;

public class SelectQuery extends MappingSqlQuery<Query> {

	public SelectQuery(DataSource dataSource, List<SqlParameter> sqlParameters, String queryBody) {

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
