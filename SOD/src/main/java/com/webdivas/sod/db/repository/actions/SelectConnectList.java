package com.webdivas.sod.db.repository.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.webdivas.sod.db.model.Connector;

public class SelectConnectList extends MappingSqlQuery<Connector> {

	public SelectConnectList(DataSource dataSource, List<SqlParameter> sqlParameters, String queryBody) {

		super(dataSource, queryBody);

		if (sqlParameters != null) {
			for (SqlParameter sqlParameter : sqlParameters) {
				super.declareParameter(sqlParameter);
			}
		}
	}

	@Override
	protected Connector mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Connector(rs.getLong("id_connector"), rs.getString("nm_connector"), rs.getString("nm_class"),
				rs.getString("nm_url"), rs.getString("nm_user"), rs.getString("nm_pass"));
	}

}
