create table coin(id int primary key auto_increment,name varchar(200));
create table coinsettings(id int primary key auto_increment,cid int,p2pport int,imageurl varchar(500),bitcoinforum varchar(500),coinmarketcapurl varchar(500));
create table user(id int primary key auto_increment,username varchar(20),password varchar(200),secret varchar(200),isemailverified int,isphoneverified int,status int,createdate datetime);
create table profile(id int primary key auto_increment,uid int,firstname varchar(200),lastname varchar(200),email varchar(100),phone varchar(50),address varchar(200),address2 varchar(200),city int,state int,country int,createdate datetime);
create table coinbalance(id int primary key auto_increment,cid int,amount double);
create table transaction(id int primary key auto_increment,description varchar(200),credit double,debit double,tax double,commission double,createdate datetime);