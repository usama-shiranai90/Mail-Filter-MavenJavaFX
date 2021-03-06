use mailFilterDemoDB;


create table MailFilter
(
    userid           int         not null,
    password         varchar(20) check ( length(password) > 5 and length(password) <= 30 ),
    systemMACAddress varchar(30) null,
    primary key (userid)
);

create table MacReference
(
    userid    int,
    ipaddress varchar(30),
    foreign key (userid) references MailFilter (userid)
);


insert into mailfilter(userid, password) VALUE (15963 , 'usama123');

UPDATE mailfilter SET systemMACAddress = ? WHERE userid = ?;