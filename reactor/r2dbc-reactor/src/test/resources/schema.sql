create table order_request (
	id uuid NOT NULL constraint or_id_pk primary key,
	created_by varchar,
	created timestamp default now()              not null,
	updated timestamp default now()              not null
);