create table if not exists clients (
    id bigint auto_increment primary key,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    passport varchar(50) not null
);

create table if not exists accounts (
    id bigint auto_increment primary key,
    client bigint not null,
    currency varchar(5) not null,
    balance double,
    iban varchar(50),
    status varchar(20),
    foreign key (client) references clients(id)
);

create table if not exists transfers (
    id bigint auto_increment primary key,
    amount double,
    receiver bigint not null,
    sender bigint not null,
    created_at timestamp default CURRENT_TIMESTAMP(),
    processed_at timestamp,
    conversion double,
    status varchar(20),
    notes varchar(255),
    foreign key (receiver) references accounts(id),
    foreign key (sender) references accounts(id)
);