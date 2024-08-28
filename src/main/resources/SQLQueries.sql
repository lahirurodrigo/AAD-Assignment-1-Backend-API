create table customer(
                         id varchar(100)primary key,
                         name varchar(100)not null,
                         address varchar(100)not null,
                         tel varchar(10)not null
);

create table item(
                        id varchar(100)primary key,
                        name varchar(100)not null ,
                        price double not null,
                        qty double not null
);

create table orders(
                       id varchar(100)primary key,
                       cusId varchar(100)not null,
                       date date not null,
                       foreign key(cusId)references customer(id)
                           on update cascade on delete cascade
);

create table orderDetails(
                             orderId varchar(100)not null ,
                             itemId varchar(100)not null ,
                             qty double not null ,
                             price double not null ,
                             foreign key(orderId)references orders(id)
                                 on update cascade on delete cascade,
                             foreign key(itemId)references item(id)
                                 on update cascade on delete cascade
);
