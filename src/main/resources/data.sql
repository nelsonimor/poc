INSERT INTO t_continent (name,code,creation_date) VALUES ('Europe','EU',CURRENT_TIMESTAMP);

INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('France','FR','FRA','French',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Italy','IT','ITA','Italian',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Belgium','BE','BEL','Belgian',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Portugal','PO','POR','Portuguese',(select id from t_continent where name = 'Europe'));

INSERT INTO t_city (name,longitude,latitude,county,state,countycode,zip,fk_country_id) VALUES ('Tourcoing',3.15945,50.72234,'Nord','Nord-Pas-de-Calais',59,59220,(select id from t_country where name = 'France'));

INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Martin','Mario',CURRENT_TIMESTAMP,(select id from t_city where name = 'Tourcoing'),(select id from t_country where name = 'France'));
