package com.webdivas.sod.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.webdivas.sod.db.core.IConnectList;
import com.webdivas.sod.db.model.Connector;
import com.webdivas.sod.db.repository.ConnectListRepository;

@Component
public class ConnectList implements IConnectList {

	@Autowired
	private ConnectListRepository connectListRepository;

	private Map<String, DriverManagerDataSource> connects = new HashMap<>();

	public ConnectList() {

	}

	@PostConstruct
	public void Init() {
		loadConnects();
	}

	@SuppressWarnings("deprecation")
	public void loadConnects() {
		List<Connector> connectorList = connectListRepository.getAll();

		for (Connector connector : connectorList) {
			try {
				DriverManagerDataSource dataSource = null;

				// если в конекте указан класс драйвера то используем
				// конструктор с указанием этого класса
				if (connector.getNm_class() != null && !connector.getNm_class().isEmpty()) {
					dataSource = new DriverManagerDataSource(connector.getNm_class(), connector.getNm_url(),
							connector.getNm_user(), connector.getNm_pass());
				} else {
					dataSource = new DriverManagerDataSource(connector.getNm_url(), connector.getNm_user(),
							connector.getNm_pass());
				}

				connects.put(connector.getNm_connector(), dataSource);
				logger.info(String.format("Создали соединение %s", connector.getNm_connector()));
				
			} catch (Exception e) {
				logger.info(String.format("Неудалось получить соединение %s, возникла ошибка: %s",
						connector.getNm_connector(), e.getMessage()));
			}
		}
	}

	@Override
	public DriverManagerDataSource getDataSource(String name) {
		return connects.get(name);
	}

	private static final Logger logger = LoggerFactory.getLogger(ConnectList.class);
}
