INSERT INTO totem (id, localizacao)
VALUES
(1, 'Rio de Janeiro');


INSERT INTO bicicleta (id, marca, modelo, ano, numero, status)
VALUES
(1, 'Caloi', 'Caloi', '2020-01-01', 12345, 'DISPONIVEL'),
(2, 'Caloi', 'Caloi', '2020-01-01', 12345, 'REPARO_SOLICITADO'),
(3, 'Caloi', 'Caloi', '2020-01-01', 12345, 'EM_USO'),
(4, 'Caloi', 'Caloi', '2020-01-01', 12345, 'EM_REPARO'),
(5, 'Caloi', 'Caloi', '2020-01-01', 12345, 'EM_USO');


INSERT INTO tranca (id, localizacao, numero, ano_de_fabricacao, modelo, status, bicicleta_id, totem_id)
VALUES
(1, 'Rio de Janeiro', 12345, '2020-01-01', 'Caloi', 'OCUPADA', 1, 1),
(2, 'Rio de Janeiro', 12345, '2020-01-01', 'Caloi', 'DISPONIVEL', NULL, 1),
(3, 'Rio de Janeiro', 12345, '2020-01-01', 'Caloi', 'OCUPADA', 2, 1),
(4, 'Rio de Janeiro', 12345, '2020-01-01', 'Caloi', 'OCUPADA', 5, 1),
(5, 'Rio de Janeiro', 12345, '2020-01-01', 'Caloi', 'EM_REPARO', NULL, NULL),
(6, 'Rio de Janeiro', 12345, '2020-01-01', 'Caloi', 'REPARO_SOLICITADO', NULL, 1);

