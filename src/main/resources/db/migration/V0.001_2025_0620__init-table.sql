-- devices
create table devices
(
    id                 bigserial               not null primary key,
    team_id            bigint,
    device_type        varchar(50)             not null,
    name               varchar(100)            not null,
    description        text,
    model              varchar(100)            not null,
    manufacturer       varchar(100)            not null,
    serial_number      varchar(100),
    os                 varchar(100),
    os_version         varchar(20),
    cpu                varchar(100),
    status             varchar(50)             not null,
    purchase_date      timestamp               not null,
    ip_address         varchar(100),
    options            json,
    created_date       timestamp default now() not null,
    created_by         varchar(100)            not null,
    last_modified_date timestamp,
    last_modified_by   varchar(100)
);


-- devices_history
create table devices_histories
(
    id                 bigserial               not null primary key,
    history_type       varchar(50)             not null,
    user_id            bigint,
    device_id          bigint                  not null,
    reason             text                    not null,
    start_date         timestamp               not null,
    end_date           timestamp,
    created_date       timestamp default now() not null,
    created_by         varchar(100)            not null,
    last_modified_date timestamp,
    last_modified_by   varchar(100),

    foreign key (device_id) references devices (id)
);

-- software
create table software
(
    id                 bigserial               not null primary key,
    team_id            bigint,
    platform           varchar(50)             not null,
    name               varchar(100)            not null,
    description        text,
    manufacturer       varchar(100)            not null,
    version            varchar(100),
    license_key        varchar(500),
    status             varchar(50)             not null,
    options            json,
    purchase_date      timestamp               not null,
    created_date       timestamp default now() not null,
    created_by         varchar(100)            not null,
    last_modified_date timestamp,
    last_modified_by   varchar(100)
);

-- devices_software
create table software_histories
(
    id                 bigserial               not null primary key,
    user_id            bigint,
    device_id          bigint,
    software_id        bigint                  not null,
    reason             text                    not null,
    start_date         timestamp               not null,
    end_date           timestamp,
    created_date       timestamp default now() not null,
    last_modified_date timestamp,

    foreign key (device_id) references devices (id),
    foreign key (software_id) references software (id)
);

-- users_devices
create table users_devices
(
    id        bigserial not null primary key,
    user_id   bigint    not null,
    device_id bigint    not null,

    foreign key (device_id) references devices (id)
);

-- users_software
create table users_software
(
    id          bigserial not null primary key,
    user_id     bigint    not null,
    software_id bigint    not null,

    foreign key (software_id) references software (id)
);
