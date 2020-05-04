---
--- Создание таблицы "Пользователи"
---
create sequence user_seq start with 1 increment by 1;

create table op_user (
  id bigint primary key,
  username character varying(100),
  password character varying(250),
  locked boolean default false,
  enabled boolean default true,
  create_dt timestamp with time zone default now() not null
);

alter table op_user add constraint uk_user_username unique (username);

---
--- Создание таблицы "Роли"
---
create table op_role (
  id integer primary key ,
  name character varying(50),
  description character varying(255)
);

alter table op_role add constraint uk_role_name unique (name);

---
--- Создание таблицы "Пользователи-Роли"
---
create table user_role (
  user_id bigint not null,
  role_id integer not null
);

alter table user_role add constraint fk_user_roles_user foreign key (user_id) references op_user(id);
alter table user_role add constraint fk_user_roles_role foreign key (role_id) references op_role(id);

---
--- Создание справочника "Города"
---
create sequence ref_city_seq start with 1 increment by 1;

create table ref_city (
  id int not null default nextval('ref_city_seq') primary key,
  name character varying(255)
);

alter table ref_city add constraint uk_op_city_name unique (name);

---
--- Создание справочника "Улицы"
---
create sequence ref_street_seq start with 1 increment by 1;

create table ref_street (
  id bigint not null default nextval('ref_street_seq') primary key,
  city_id int not null,
  name character varying(255)
);

alter table ref_street add constraint fk_ref_street_ref_city foreign key(city_id) references ref_city(id);

---
--- Создание таблицы "Домофоны"
---
create sequence op_domofon_seq start with 1 increment by 1;

create table op_domofon (
  id bigint not null default nextval('op_domofon_seq') primary key,
  model character varying(15),
  street_id bigint not null,
	house character varying(10),
	letter character varying(5),
	building character varying(5),
	porch character varying(5),
	mounting_dt timestamp with time zone default now(),
	description text
);
comment on table op_domofon is 'Установленные домофонные аппараты';
comment on column op_domofon.street_id is 'Идентификатор улицы из справочника';
comment on column op_domofon.house is 'Дом';
comment on column op_domofon.letter is 'Буква';
comment on column op_domofon.building is 'Корпус/Строение';
comment on column op_domofon.porch is 'Подъезд';
comment on column op_domofon.mounting_dt is 'Дата установки';
comment on column op_domofon.description is 'Примечание';

alter table op_domofon add constraint fk_op_domofon_ref_street foreign key (street_id) references ref_street(id);
alter table op_domofon add constraint uk_op_domofon unique (street_id, house, letter, building, porch);

---
--- Создание таблицы "Акаунты"
---
create sequence op_account_seq start with 1 increment by 1;

create table op_account (
  id bigint not null default nextval('op_account_seq') primary key,
  domofon_id bigint not null,
  account character varying(25) not null,
  fio character varying(255),
  phone character varying(50),
  has_device boolean not null default true,
  device_switch_off boolean not null default false,
	flat character varying(50),
	create_dt timestamp with time zone default now(),
	con_dt timestamp with time zone,
	discon_dt timestamp with time zone,
	fsb boolean default true,
	gorod boolean default true,
	description text,
	CONSTRAINT uk_op_account_account UNIQUE (account)
);

comment on table op_account is 'Подключение клиентов к домофонным аппаратам';
comment on column op_account.domofon_id is 'Идентификатор домофонного аппарата';
comment on column op_account.account is 'Лицевой счет';
comment on column op_account.fio is 'ФИО клиента';
comment on column op_account.phone is 'Телефон';
comment on column op_account.has_device is 'Признак установки квартирного автомата';
comment on column op_account.device_switch_off is 'Признак отключенного квартирного автомата';
comment on column op_account.flat is 'Квартира';
comment on column op_account.create_dt is 'Дата создания аккаунта';
comment on column op_account.con_dt is 'Дата подключения';
comment on column op_account.discon_dt is 'Дата отключения';
comment on column op_account.fsb is 'Признак передачи данных в ФСБ';
comment on column op_account.gorod is 'Признак передачи данных в систему ГОРОД';
comment on column op_account.description is 'Примечание';

alter table op_account add constraint fk_op_account_op_domofon foreign key(domofon_id) references op_domofon(id);
alter table op_account add constraint uk_op_account unique (domofon_id, flat);