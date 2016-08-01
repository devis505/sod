package com.webdivas.sod.db.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webdivas.sod.db.core.IRepository;
import com.webdivas.sod.db.model.Connector;
import com.webdivas.sod.db.model.Query;
import com.webdivas.sod.db.repository.actions.SelectConnectList;
import com.webdivas.sod.request.json.JsonRequestParams;

@Component
public class ConnectListRepository implements IRepository {

	@Autowired
	DataSource dataSourceH2;
	
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
	public List<?> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getByParams(JsonRequestParams params, Query query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Connector> getAll() {

		SelectConnectList select = new SelectConnectList(dataSourceH2, null, SQL_GET_ALL_CONNETS);
		
		return select.execute();
	}
	
	private final static String SQL_GET_ALL_CONNETS = "SELECT * FROM sod_connector";

}
