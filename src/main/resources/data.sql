INSERT INTO t_continent (name,code,creation_date) VALUES ('Europe','EU',CURRENT_TIMESTAMP);

INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('France','FR','FRA','French',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Italy','IT','ITA','Italian',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Belgium','BE','BEL','Belgian',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Portugal','PO','POR','Portuguese',(select id from t_continent where name = 'Europe'));

INSERT INTO t_city (name,longitude,latitude,county,state,countycode,zip,fk_country_id) VALUES ('Villeurbanne',3.15945,50.72234,'Rhône','Auvergne-Rhône-Alpes',59,59220,(select id from t_country where name = 'France'));

INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Cole','Norris',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Diot','Antoine',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Kahudi','Charles',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Lighty','David',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Lacombe','Paul',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));

INSERT INTO t_team(name,fk_team_city1_id) VALUES ('ASVEL',(select id from t_city where name = 'Villeurbanne'));

INSERT INTO t_competition(name,fk_continent_id) VALUES ('Euroleague',(select id from t_continent where name = 'Europe'));
INSERT INTO t_competition(name,fk_country_id) VALUES ('Jeep Elite',(select id from t_country where name = 'France'));

INSERT INTO t_roster(fk_team_id,startdate,enddate) values ((select id from t_team where name = 'ASVEL'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);