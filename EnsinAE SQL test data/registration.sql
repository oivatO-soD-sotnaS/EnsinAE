create table registration
(
    id_registration int auto_increment
        primary key,
    id_user         int                  not null,
    id_discipline   int                  not null,
    status          tinyint(1) default 0 not null,
    constraint registration_ibfk_1
        foreign key (id_user) references user (id_user),
    constraint registration_ibfk_2
        foreign key (id_discipline) references discipline (id_discipline),
    UNIQUE(id_user, id_discipline)
);

create index id_discipline
    on registration (id_discipline);

create index id_user
    on registration (id_user);

INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (2, 5, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (4, 7, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (5, 8, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (6, 9, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (7, 10, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (8, 11, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (9, 12, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (10, 13, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (11, 14, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (12, 15, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (13, 16, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (14, 17, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (15, 18, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (16, 19, 1, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (17, 20, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (18, 21, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (19, 22, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (20, 23, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (21, 24, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (22, 25, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (23, 26, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (24, 27, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (25, 28, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (26, 29, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (27, 30, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (28, 31, 1, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (29, 32, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (30, 33, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (32, 35, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (33, 36, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (34, 37, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (35, 38, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (36, 39, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (37, 40, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (38, 41, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (39, 42, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (40, 43, 1, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (43, 6, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (44, 7, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (45, 8, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (46, 9, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (47, 10, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (48, 11, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (49, 12, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (50, 13, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (51, 14, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (52, 15, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (53, 16, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (54, 17, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (55, 18, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (56, 19, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (57, 20, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (58, 21, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (59, 22, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (60, 23, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (62, 25, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (63, 26, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (64, 27, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (65, 28, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (66, 29, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (67, 30, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (68, 31, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (69, 32, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (70, 33, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (71, 34, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (72, 35, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (73, 36, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (74, 37, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (75, 38, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (76, 39, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (77, 40, 3, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (78, 41, 3, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (79, 42, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (80, 43, 2, 0);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (82, 44, 1, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (83, 44, 2, 1);
INSERT INTO db_ensinae.registration (id_registration, id_user, id_discipline, status) VALUES (85, 3, 1, 1);
