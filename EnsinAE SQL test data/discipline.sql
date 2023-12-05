create table discipline
(
    id_discipline int auto_increment
        primary key,
    id_professor  int          not null,
    name          varchar(128) not null,
    description   varchar(512) null,
    access_code   varchar(8)   not null,
    constraint access_code
        unique (access_code),
    constraint name
        unique (name),
    constraint discipline_ibfk_1
        foreign key (id_professor) references user (id_user)
);

create index id_professor
    on discipline (id_professor);

INSERT INTO db_ensinae.discipline (id_discipline, id_professor, name, description, access_code) VALUES (1, 3, 'Linguagem e Algoritmos de Programação', 'A disciplina de Linguagem e Algoritmos de Programação é fundamental para qualquer aspirante a desenvolvedor de software, proporcionando uma base sólida para a compreensão e aplicação de conceitos essenciais no mundo da programação. Esta disciplina visa capacitar os estudantes com as habilidades necessárias para criar algoritmos eficientes e implementá-los em linguagens de programação modernas.

', 'ifpr');
INSERT INTO db_ensinae.discipline (id_discipline, id_professor, name, description, access_code) VALUES (2, 2, 'Programação Orientada a Objetos', 'Programação Orientada a Objetos organiza código em objetos e classes, encapsula dados e operações, promove herança e polimorfismo. Abstrai complexidades, utiliza métodos e atributos, estimula comunicação entre objetos e segue princípios SOLID. Padrões de design e aplicações práticas, tornando o desenvolvimento modular e flexível.', 'ifpr2023');
INSERT INTO db_ensinae.discipline (id_discipline, id_professor, name, description, access_code) VALUES (3, 3, 'Frameworks', 'Frameworks: Disciplina foca em Frameworks, conjuntos de ferramentas e bibliotecas para desenvolvimento eficiente. Aborda conceitos como inversão de controle, desacoplamento e reutilização de código. Explora frameworks populares, promovendo melhores práticas no desenvolvimento de software escalável e modular.', 'Frame23');
