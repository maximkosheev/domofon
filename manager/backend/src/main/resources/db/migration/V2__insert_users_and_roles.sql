-- заполняем таблицу пользователей
-- Пользователь admin/admin
insert into op_user(id, username, password, locked)
  values (1, 'admin', '$2a$08$rv5GQwrg47VUDwuoinhBle2MFeeOOvWJ4Ie0uBOb6/zlOQUCfqgd2', true);

-- Пользователь manager/manager
insert into op_user(id, username, password, locked)
  values (2, 'manager', '$2a$08$r/cqBih8G835s3ABiRHWEOYzQHlw79P7kEoQ2cpyo.pjmp/.NgNH.', false);

-- Пользователь test/password
insert into op_user(id, username, password, locked)
  values (3, 'test', '$2a$08$P/Zk8vG42KDXuXwq0Esv6Op3kwii7xB63V.aZUwrY8k2F6hc8o/m6', false);

SELECT setval('user_seq', 3, true);

-- заполняем таблицу ролей
insert into op_role (id, name, description) values (1, 'ROLE_ADMIN', 'Администратор');
insert into op_role (id, name, description) values (2, 'ROLE_MANAGER', 'Менеджер');
insert into op_role (id, name, description) values (3, 'ROLE_CLIENT', 'Клиент');

-- связи между пользователями и ролями
insert into user_role(user_id, role_id) values (1, 1), (1, 2), (2, 2), (3, 3);
