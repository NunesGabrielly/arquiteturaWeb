create table usuario(
id int not null primary key auto_increment,
nome varchar(100) not null,
email varchar(100) not null,
senha varchar(100) not null
);

INSERT INTO USUARIO(
    NOME, 
    EMAIL, 
    SENHA 
) 
VALUES (
    'Rafael Moreno',
    'Rafael.Moreno@teste.com',
    '123321'
)

DELETE FROM USUARIO WHERE id

UPDATE USUARIO SET (
    NOME,
    EMAIL,
    SENHA,
) 
WHERE id

SELECT * FROM USUARIO WHERE (EMAIL AND SENHA)

