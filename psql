ALTER TABLE TRANSACTION_DATA ALTER COLUMN bankid TYPE varchar(10),ALTER COLUMN amount  TYPE int USING amount::integer;
insert into user_points (bankid,channel,points) values ('123','AW',20);
insert into user_points (bankid,channel,points) values ('123','EL',10);
insert into user_points (bankid,channel,points) values ('123','REVERSAL',10);
select * from user_points;
insert into transaction_data (txnid,bankid,status,amount) values ('1121','123','SUCCESS',10);
select * from transaction_data;