create table todo
(
    id          bigserial
        primary key,
    completed   boolean      not null,
    description varchar(255),
    due_date    timestamp(6) not null,
    title       varchar(255) not null
);
