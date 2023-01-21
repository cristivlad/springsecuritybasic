create table users(
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users values ('cristi','112233','1');
insert into authorities values ('cristi','admin');

create table customer (
    id int not null primary key,
    email varchar(45),
    pwd varchar(200),
    role varchar(45)
);