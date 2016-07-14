INSERT INTO sod_connector VALUES (1, 'dsHobotun');
	
INSERT INTO sod_query_type VALUES (1, 'SELECT');
INSERT INTO sod_query_type VALUES (2, 'INSERT');
INSERT INTO sod_query_type VALUES (3, 'UPDATE');
INSERT INTO sod_query_type VALUES (4, 'DELETE');
INSERT INTO sod_query_type VALUES (5, 'FUNCTION');

INSERT INTO sod_query VALUES (1, 'qHbInsertPay', 
	'INSERT INTO hb_pay(id_user, sm_pay, dt_pay, kd_state, id_modele) ' || char(10) ||
	'VALUES (:id_user, :sm_pay, now(), :kd_state, :id_modele)', 1, 2);
	
INSERT INTO sod_query VALUES (2, 'qHbPayById', 'SELECT * FROM hb_pay p WHERE p.id_pay = :id_pay', 1, 1);
INSERT INTO sod_query VALUES (3, 'qHbPayAll', 'SELECT * FROM hb_pay p', 1, 1);
insert into sod_query values (4, 'qHbUser', 'SELECT * FROM hb_user', 1, 1);

INSERT INTO sod_query VALUES (5, 'qHbUpdatePayStatus', 
	'UPDATE hb_pay ' || char(10) ||
    '   SET kd_state = :kd_state ' || char(10) ||
    ' WHERE id_pay = :id_pay ', 1, 3);
    
INSERT INTO sod_query VALUES (6, 'qHbInsertUserModel', 
	'INSERT INTO hb_user_model(idUser, IdModel, idEntityType) ' || char(10) ||
    'VALUES (:id_user, :id_model, :id_entity_type) ', 1, 2);