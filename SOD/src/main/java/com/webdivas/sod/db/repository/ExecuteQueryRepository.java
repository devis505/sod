package com.webdivas.sod.db.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.webdivas.sod.db.core.IConnectList;
import com.webdivas.sod.db.core.IRepository;
import com.webdivas.sod.db.model.Query;
import com.webdivas.sod.db.utils.QueryParams;
import com.webdivas.sod.request.json.JsonRequestParams;
import com.webdivas.sod.request.json.RequestParam;

@Component
public class ExecuteQueryRepository implements IRepository {

	@Autowired
	IConnectList connects;

	@Override
	public List<Map<String, Object>> create(JsonRequestParams params, Query query) {

		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();

		try {
			QueryParams qParam = new QueryParams();

			for (RequestParam param : params.getParams()) {
				if (param.getValue() instanceof String) {
					qParam.addParamValue(param.getName(), param.getValue(), Types.VARCHAR);
				} else if (param.getValue() instanceof Integer) {
					qParam.addParamValue(param.getName(), param.getValue(), Types.INTEGER);
				} else if (param.getValue() instanceof Double) {
					qParam.addParamValue(param.getName(), param.getValue(), Types.INTEGER);
				}
			}

			/*
			 * Возвращаем ID вставленной записи, если в запросе присутвует тег
			 * key_holder
			 */
			if (params.getKey_holder() != null) {
				UpdateQuery updateQuery = new UpdateQuery(connects.getDataSource(params.getOwner()), qParam,
						query.getVl_query(), new String[] { params.getKey_holder() });

				KeyHolder keyHolder = new GeneratedKeyHolder();
				updateQuery.updateByNamedParam(qParam.getParamsValue(), keyHolder);

				result.put("key_holder", keyHolder.getKey());
			} else {
				UpdateQuery updateQuery = new UpdateQuery(connects.getDataSource(params.getOwner()), qParam,
						query.getVl_query(), null);
				updateQuery.updateByNamedParam(qParam.getParamsValue());
			}

			result.put("result", 0);
			result.put("kd_error", 0);
			result.put("msg", "");

		} catch (Exception e) {
			result.put("result", 1);
			result.put("kd_error", 1);
			result.put("msg", e.getLocalizedMessage());
		}

		resultList.add(result);

		return resultList;
	}

	@Override
	public List<Map<String, Object>> update(JsonRequestParams params, Query query) {
		params.setKey_holder(null);

		return create(params, query);
	}

	@Override
	public List<Map<String, Object>> delete(JsonRequestParams params, Query query) {
		params.setKey_holder(null);

		return create(params, query);
	}

	@Override
	public List<?> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getByParams(JsonRequestParams params, Query query) {

		QueryParams qParam = new QueryParams();

		for (RequestParam param : params.getParams()) {
			if (param.getValue() instanceof String) {
				qParam.addParamValue(param.getName(), param.getValue(), Types.VARCHAR);
			} else if (param.getValue() instanceof Integer) {
				qParam.addParamValue(param.getName(), param.getValue(), Types.INTEGER);
			} else if (param.getValue() instanceof Double) {
				qParam.addParamValue(param.getName(), param.getValue(), Types.INTEGER);
			}
		}

		Select select = new Select(connects.getDataSource(params.getOwner()), qParam.getSqlParameters(),
				query.getVl_query());
		return select.executeByNamedParam(qParam.getParamsValue());

	}

	@Override
	public List<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	class Select extends MappingSqlQuery<Map<String, Object>> {

		public Select(DataSource dataSource, List<SqlParameter> sqlParameters, String queryBody) {

			super(dataSource, queryBody);

			for (SqlParameter sqlParameter : sqlParameters) {
				super.declareParameter(sqlParameter);
			}
		}

		@Override
		protected Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {

			Map<String, Object> mapResult = new HashMap<>();
			ResultSetMetaData rsmd = rs.getMetaData();

			for (int columnIndex = 1; columnIndex <= rsmd.getColumnCount(); columnIndex++) {

				int type = rsmd.getColumnType(columnIndex);

				switch (type) {
				case Types.VARCHAR:
					mapResult.put(rsmd.getColumnName(columnIndex), rs.getString(columnIndex));
					break;
				case Types.INTEGER:
					mapResult.put(rsmd.getColumnName(columnIndex), rs.getInt(columnIndex));
					break;
				case Types.DECIMAL:
					mapResult.put(rsmd.getColumnName(columnIndex), rs.getDouble(columnIndex));
					break;
				case Types.DATE:
					mapResult.put(rsmd.getColumnName(columnIndex), rs.getDate(columnIndex));
					break;
				case Types.TIMESTAMP:
					mapResult.put(rsmd.getColumnName(columnIndex), rs.getTimestamp(columnIndex));
					break;
				}
			}

			return mapResult;
		}
	}

	public class UpdateQuery extends SqlUpdate {

		public UpdateQuery(DataSource dataSource, QueryParams inParam, String ddlQuery, String[] returnKeys) {
			super(dataSource, ddlQuery);

			// Заполняем параметры запроса
			for (SqlParameter sqlParameter : inParam.getSqlParameters()) {
				super.declareParameter(sqlParameter);
			}

			// если надо возвращаем первичный ключ
			if (returnKeys != null && returnKeys.length > 0) {
				super.setGeneratedKeysColumnNames(returnKeys);
				super.setReturnGeneratedKeys(true);
			}
		}

	}
}
