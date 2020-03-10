insert into op_domofon(model, description, street_id, house, letter, building, porch, mounting_dt)
values('domofon_model_1', '', 1, '1', '', '', '1', now());

INSERT INTO op_client
(user_id, fio, street_id, house, letter, building, room, phone, description, fsb, gorod, create_dt)
VALUES(3, 'Тест Тест Тестович', 3, '1', 'A', '2', '131', '+79085898365', '', true, true, now());

INSERT INTO op_account
(client_id, domofon_id, account, has_device, device_switch_off, room, con_dt)
VALUES(1, 1, 'QWERTY1', true, false, '12', now());

