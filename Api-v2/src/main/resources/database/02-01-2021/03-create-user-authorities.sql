-- liquibase formatted sql
-- changeset nwaszkowiak:6
create table roles(
                      id varchar ( 60 ) not null PRIMARY KEY,
                      name varchar ( 20 ) not null UNIQUE
);

-- changeset nwaszkowiak:7
insert into roles(id, name) values ('1', 'ROLE_USER');
insert into roles(id, name) values ('2', 'ROLE_MODERATOR');
insert into roles(id, name) values ('3', 'ROLE_ADMIN');

-- changeset nwaszkowiak:8
create table users(
                      id varchar ( 60 ) not null PRIMARY KEY,
                      username varchar ( 50 ) not null UNIQUE,
                      password varchar ( 100 ) not null,
                      email varchar ( 100 ) not null,
                      enabled boolean not null
);

-- changeset nwaszkowiak:9
create table users_roles(
                          user_id varchar ( 60 ) not null,
                          role_id varchar ( 60 ) not null,
                          PRIMARY KEY (`user_id`,`role_id`),
                          constraint fk_user_id foreign key (user_id) references users(id),
                          constraint fk_roles_id foreign key (role_id) references roles(id)
);
-- changeset nwaszkowiak:10
insert into users (ID, username, password, email, enabled) VALUES ('0eb75ab1-676e-4b23-a842-f33c96efbeed', 'test1', '$2a$10$/vw0CyEz000WG7qQsYt9V.PnrIx6eEFn2tC6J0lmOazksf5NP0MxW', 'test1', '1');
-- changeset nwaszkowiak:11
insert into users_roles (user_id,role_id) VALUES ('0eb75ab1-676e-4b23-a842-f33c96efbeed','1');
insert into users_roles (user_id,role_id) VALUES ('0eb75ab1-676e-4b23-a842-f33c96efbeed','2');
insert into users_roles (user_id,role_id) VALUES ('0eb75ab1-676e-4b23-a842-f33c96efbeed','3');

-- insert into users (ID, username, password, enabled)
-- values (1, 'test', '{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);
-- insert into authorities (username, authority) values ('test','USER');
