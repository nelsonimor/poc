INSERT INTO t_continent (name,code,creation_date) VALUES ('Europe','EU',CURRENT_TIMESTAMP);

INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('France','FR','FRA','French',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Italy','IT','ITA','Italian',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Belgium','BE','BEL','Belgian',(select id from t_continent where name = 'Europe'));
INSERT INTO t_country (name,codeIso2,codeIso3,nationality,fk_continent_id) VALUES ('Portugal','PO','POR','Portuguese',(select id from t_continent where name = 'Europe'));

INSERT INTO t_city (name,longitude,latitude,county,state,countycode,zip,fk_country_id) VALUES ('Villeurbanne',3.15945,50.72234,'Rhône','Auvergne-Rhône-Alpes',59,59220,(select id from t_country where name = 'France'));
INSERT INTO t_city (name,longitude,latitude,county,state,countycode,zip,fk_country_id) VALUES ('Starsbourg',3.15945,50.72234,'Alsace','Haut Rhin',59,59220,(select id from t_country where name = 'France'));

INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Cole','Norris',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Diot','Antoine',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Kahudi','Charles',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Lighty','David',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Lacombe','Paul',CURRENT_TIMESTAMP,(select id from t_city where name = 'Villeurbanne'),(select id from t_country where name = 'France'));

INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Leloup','Jeremy',CURRENT_TIMESTAMP,(select id from t_city where name = 'Starsbourg'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Traore','Ali',CURRENT_TIMESTAMP,(select id from t_city where name = 'Starsbourg'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Campbell','Louis',CURRENT_TIMESTAMP,(select id from t_city where name = 'Starsbourg'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Labeyrie','Louis',CURRENT_TIMESTAMP,(select id from t_city where name = 'Starsbourg'),(select id from t_country where name = 'France'));
INSERT INTO t_person (lastname,firstname,birthdate,fk_city_id,fk_nationality1_id)  VALUES ('Morris','Moochie',CURRENT_TIMESTAMP,(select id from t_city where name = 'Starsbourg'),(select id from t_country where name = 'France'));

INSERT INTO t_team(name,fk_team_city1_id) VALUES ('ASVEL',(select id from t_city where name = 'Villeurbanne'));
INSERT INTO t_team(name,fk_team_city1_id) VALUES ('SIG',(select id from t_city where name = 'Strasbourg'));

INSERT INTO t_competition(name,fk_continent_id) VALUES ('Euroleague',(select id from t_continent where name = 'Europe'));
INSERT INTO t_competition(name,fk_country_id) VALUES ('Jeep Elite',(select id from t_country where name = 'France'));

INSERT INTO t_competition_organization(fk_competition_id,startdate,enddate) values ((select id from t_competition where name = 'Euroleague'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO t_competition_organization(fk_competition_id,startdate,enddate) values ((select id from t_competition where name = 'Jeep Elite'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO t_phasis(name,fk_competition_id) VALUES ('Regular Season',(select id from t_competition where name = 'Euroleague'));
INSERT INTO t_phasis(name,fk_competition_id) VALUES ('Regular Season',(select id from t_competition where name = 'Jeep Elite'));

INSERT INTO t_phasis_organization(fk_phasis_id,startdate,enddate) values ((select id from t_phasis where name = 'Regular Season' AND fk_competition_id in (select id from t_competition where name = 'Euroleague')),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO t_phasis_organization(fk_phasis_id,startdate,enddate) values ((select id from t_phasis where name = 'Regular Season' AND fk_competition_id in (select id from t_competition where name = 'Jeep Elite')),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO t_roster(id,fk_team_id,startdate,enddate) VALUES (1,(select id from t_team where name = 'ASVEL'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);
INSERT INTO t_roster(id,fk_team_id,startdate,enddate) VALUES (2,(select id from t_team where name = 'SIG'),CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (1,(select id from t_person where lastname = 'Cole'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (1,(select id from t_person where lastname = 'Diot'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (1,(select id from t_person where lastname = 'Kahudi'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (1,(select id from t_person where lastname = 'Lighty'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (1,(select id from t_person where lastname = 'Lacombe'),null,null);

INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (2,(select id from t_person where lastname = 'Leloup'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (2,(select id from t_person where lastname = 'Traore'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (2,(select id from t_person where lastname = 'Campbell'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (2,(select id from t_person where lastname = 'Labeyrie'),null,null);
INSERT INTO t_roster_item(fk_roster_id,fk_person_id,startdate,enddate) VALUES (2,(select id from t_person where lastname = 'Morris'),null,null);

INSERT INTO t_competition_participation(fk_competition_organization_id,fk_roster_id) VALUES (1,1);
INSERT INTO t_competition_participation(fk_competition_organization_id,fk_roster_id) VALUES (2,1);

INSERT INTO t_competition_participation(fk_competition_organization_id,fk_roster_id) VALUES (1,2);
INSERT INTO t_competition_participation(fk_competition_organization_id,fk_roster_id) VALUES (2,2);

INSERT INTO t_phasis_participation(fk_phasis_organization_id,fk_roster_id) VALUES (1,1);
INSERT INTO t_phasis_participation(fk_phasis_organization_id,fk_roster_id) VALUES (2,1);

INSERT INTO t_phasis_participation(fk_phasis_organization_id,fk_roster_id) VALUES (1,2);
INSERT INTO t_phasis_participation(fk_phasis_organization_id,fk_roster_id) VALUES (2,2);

INSERT INTO t_game(fk_local_roster_id,fk_visitor_roster_id,localscore,visitscore,gamedate,fk_phasis_organization_id) VALUES (1,2,100,90,CURRENT_TIMESTAMP,1);
INSERT INTO t_game(fk_local_roster_id,fk_visitor_roster_id,localscore,visitscore,gamedate,fk_phasis_organization_id) VALUES (1,2,100,90,CURRENT_TIMESTAMP,1);

INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Cole'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Diot'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Kahudi'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Lighty'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Lacombe'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Leloup'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Traore'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Campbell'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Labeyrie'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (1,(select id from t_person where lastname = 'Morris'),2,10);

INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Cole'),1,20);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Diot'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Kahudi'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Lighty'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Lacombe'),1,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Leloup'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Traore'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Campbell'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Labeyrie'),2,10);
INSERT INTO t_boxline(fk_game_id,fk_person_id,fk_roster_id,points) VALUES (2,(select id from t_person where lastname = 'Morris'),2,10);
