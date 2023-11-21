INSERT INTO bicicleta (marca, modelo, ano, numero, status) VALUES ('Marca1', 'Modelo1', '2022-01-01', 123, 'DISPONÍVEL');
INSERT INTO bicicleta (marca, modelo, ano, numero, status) VALUES ('Marca2', 'Modelo2', '2021-01-01', 456, 'EM_USO');
INSERT INTO bicicleta (marca, modelo, ano, numero, status) VALUES ('Marca3', 'Modelo3', '2020-01-01', 789, 'EM_REPARO');

INSERT INTO tranca (localizacao, modelo, ano_de_fabricacao, numero, status, bicicleta_id) VALUES ('Localizacao1', 'Modelo1', '2022-01-01', 123, 'LIVRE', 1);
INSERT INTO tranca (localizacao, modelo, ano_de_fabricacao, numero, status, bicicleta_id) VALUES ('Localizacao2', 'Modelo2', '2021-01-01', 456, 'NOVA', 2);
INSERT INTO tranca (localizacao, modelo, ano_de_fabricacao, numero, status, bicicleta_id) VALUES ('Localizacao3', 'Modelo3', '2020-01-01', 789, 'LIVRE', null);
