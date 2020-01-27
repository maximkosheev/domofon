create sequence user_seq
    start with 1
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

create table op_user (
  id bigint primary key,
  username character varying(100),
  password character varying(250),
  locked boolean default false,
  create_dt timestamp with time zone default now() not null
);

alter table op_user add constraint uk_user_username unique (username);

create table op_role (
  id integer primary key ,
  name character varying(50),
  description character varying(255)
);

alter table op_role add constraint uk_role_name unique (name);

create table user_role (
  user_id bigint not null,
  role_id integer not null
);

alter table user_role add constraint fk_user_roles_user foreign key (user_id) references op_user(id);
alter table user_role add constraint fk_user_roles_role foreign key (role_id) references op_role(id);

create table op_client (
	id bigint not null primary key,
	account character varying(25) not null,
	device boolean not null default true,
	switch_off boolean not null default false,
	street_id bigint,
	house character varying(10),
	letter character varying(5),
	building character varying(5),
	porch character varying(5),
	room character varying(50),
	fio character varying(255),
	phone character varying(25),
	description text,
	fsb boolean default true,
	gorod boolean default true,
	setup_dt timestamp with time zone default now()
);

create unique index inx_client_account on op_client (account);

comment on table op_client is 'Таблица клиентов';
comment on column op_client.account is 'Лицевой счет';
comment on column op_client.device is 'Признак установки квартирного автомата';
comment on column op_client.switch_off is 'Признак отключенного квартирного автомата';
comment on column op_client.street_id is 'Идентификатор улицы из справочника';
comment on column op_client.house is 'Дом';
comment on column op_client.letter is 'Буква';
comment on column op_client.building is 'Корпус/Строение';
comment on column op_client.porch is 'Подъезд';
comment on column op_client.room is 'Квартира';
comment on column op_client.fio is 'Фамилия Имя Отчество';
comment on column op_client.phone is 'Телефон';
comment on column op_client.description is 'Примечание';
comment on column op_client.fsb is 'Признак передачи данных в ФСБ';
comment on column op_client.gorod is 'Признак передачи данных в систему ГОРОД';
comment on column op_client.setup_dt is 'Дата установки';
