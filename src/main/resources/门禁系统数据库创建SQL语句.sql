create database if not exists acdatabase;

use acdatabase;
create table if not exists users (
    id int auto_increment,
    account varchar(64) not null,
    name varchar(128) default '未知',
    passWord varchar(64) default '12345678',
    cardNumber varchar(64) not null ,
    state varchar(64) default '可用',
    registerTime datetime default now(),
    primary key(id),
    unique key (account,cardNumber)
)engine =InnoDB default charset =utf8;

insert into users (account, name, passWord, cardNumber, state, registerTime)
VALUES
('1446023280','张三1','12345678','00000000','可用',now()),
('1446023281','张三2','12345679','00000001','可用',now()),
('1446023282','张三3','12345680','00000002','可用',now()),
('1446023283','李四1','12345681','00000003','可用',now()),
('1446023284','李四2','12345682','00000004','可用',now()),
('1446023285','李四3','12345683','00000005','禁用',now()),
('钥匙','钥匙','12345683','钥匙','可用',now());

create table if not exists admins
(
	id int auto_increment,
	account varchar(64) not null,
	name varchar(128) default '未知' null,
	passWord varchar(64) default '12345678' null,
	deviceNumber varchar(64) not null,
	deviceName varchar(128) default '未知',
	deviceNikeName varchar(64) default '未知',
	type varchar(64) default '普通管理员',
	state varchar(64) default '可用' null,
	registerTime datetime default now() null,
	constraint admins_pk primary key (id),
	constraint admins_uk unique key (account)
)engine =InnoDB default charset =utf8;

insert into  admins(account, name, passWord, deviceNumber, deviceName,deviceNikeName,type,state, registerTime)
VALUES
('13364009753','超级管理员','heweiqq123.','不管理设备','不管理设备','不管理设备','超级管理员','可用',now()),
('13364009764','后门管理员','heweiqq123.','10010103423','阿尔法智能门禁终端','后门','普通管理员','可用',now()),
('13364009774','中门管理员','heweiqq123.','10010103434','阿尔法智能门禁终端','中门','普通管理员','可用',now()),
('13364009784','前门管理员','heweiqq123.','10010103413','阿尔法智能门禁终端','前门','普通管理员','可用',now()),
('13364009794','西门管理员','heweiqq123.','10010103403','阿尔法智能门禁终端','西门','普通管理员','可用',now());

create table if not exists devices
(
	id int auto_increment,
	deviceNumber varchar(64) not null,
	deviceName varchar(128) default '未知' null,
	deviceNikeName varchar(64) default '未知' null,
	state varchar(64) default '可用' null,
	registerTime datetime default now() null,
	constraint devices_pk primary key (id),
	constraint devices_uk unique key (deviceNumber)
)engine =InnoDB default charset =utf8;

insert into devices(deviceNumber, deviceName,deviceNikeName, state, registerTime)
values
('10010103423','阿尔法智能门禁终端','后门','可用',now()),
('10010103434','阿尔法智能门禁终端','中门','可用',now()),
('10010103413','阿尔法智能门禁终端','前门','可用',now()),
('10010103403','阿尔法智能门禁终端','西门','可用',now());

create table if not exists records
(
	id int auto_increment,
	userAccount varchar(64) default '未知' not null,
	userName varchar(128) default '未知' null,
	deviceNumber varchar(64) not null,
	deviceNikeName varchar(64) default '未知' null,
	state varchar(64) default '未知' null,
	time datetime default now() null,
	constraint records_pk
		primary key (id)
)engine =InnoDB default charset =utf8;

insert into records(userAccount, userName, deviceNumber, deviceNikeName, state, time)
VALUES
('1446023280','张三1','10010103423','后门','进',now()),
('1446023280','张三1','10010103423','后门','出',now()),
('1446023281','张三2','10010103423','后门','进',now()),
('1446023281','张三2','10010103423','后门','出',now()),
('1446023284','李四2','10010103403','西门','进',now()),
('1446023284','李四2','10010103403','西门','出',now());

create table if not exists envirodata
(
	id int auto_increment,
	deviceNumber varchar(64) not null,
	deviceNikeName varchar(64) default '未知' null,
	temperature float null,
	humidity float null,
	lightIntensity int null,
	time datetime default now(),
	constraint envirodata_pk primary key (id)
)engine =InnoDB default charset =utf8;


insert into envirodata(deviceNumber, deviceNikeName, temperature, humidity, lightIntensity, time)
VALUES
('10010103403','西门',15.5,0.23,25535,'2019-05-18 00:00:00'),
('10010103403','西门',16.0,0.23,25635,'2019-05-18 00:30:00'),
('10010103403','西门',16.8,0.23,25735,'2019-05-18 01:00:00'),
('10010103403','西门',19.5,0.23,25835,'2019-05-18 01:30:00'),
('10010103403','西门',20.5,0.24,25935,'2019-05-18 02:00:00'),
('10010103403','西门',20.9,0.24,26535,'2019-05-18 02:30:00'),
('10010103403','西门',21.3,0.25,26635,'2019-05-18 03:00:00'),
('10010103403','西门',22.5,0.25,26735,'2019-05-18 03:30:00'),
('10010103403','西门',23.5,0.28,26835,'2019-05-18 04:00:00'),
('10010103403','西门',24.5,0.28,26935,'2019-05-18 04:30:00'),
('10010103403','西门',25.5,0.30,27535,'2019-05-18 05:00:00'),
('10010103403','西门',26.5,0.31,27635,'2019-05-18 05:30:00'),
('10010103403','西门',27.5,0.32,27735,'2019-05-18 06:00:00'),
('10010103403','西门',28.5,0.33,27835,'2019-05-18 06:30:00'),
('10010103403','西门',29.5,0.34,27935,'2019-05-18 07:00:00'),
('10010103403','西门',30.5,0.34,28535,'2019-05-18 07:30:00'),
('10010103403','西门',31.5,0.35,28635,'2019-05-18 08:00:00'),
('10010103403','西门',32.5,0.35,28735,'2019-05-18 08:30:00'),
('10010103403','西门',33.5,0.35,28835,'2019-05-18 09:00:00'),
('10010103403','西门',34.5,0.36,28935,'2019-05-18 09:30:00'),
('10010103403','西门',35.5,0.36,29535,'2019-05-18 10:00:00'),
('10010103403','西门',36.5,0.36,29635,'2019-05-18 10:30:00'),
('10010103403','西门',37.5,0.36,29735,'2019-05-18 11:00:00'),
('10010103403','西门',37.5,0.35,29835,'2019-05-18 11:30:00'),
('10010103403','西门',37.8,0.35,29935,'2019-05-18 12:00:00'),
('10010103403','西门',37.9,0.34,35535,'2019-05-18 12:30:00'),
('10010103403','西门',38.5,0.34,35635,'2019-05-18 13:00:00'),
('10010103403','西门',38.5,0.33,35735,'2019-05-18 13:30:00'),
('10010103403','西门',38.5,0.32,35835,'2019-05-18 14:00:00'),
('10010103403','西门',37.5,0.32,35535,'2019-05-18 14:30:00'),
('10010103403','西门',36.5,0.31,35035,'2019-05-18 15:00:00'),
('10010103403','西门',35.5,0.31,34535,'2019-05-18 15:30:00'),
('10010103403','西门',34.5,0.29,34035,'2019-05-18 16:00:00'),
('10010103403','西门',33.5,0.29,33035,'2019-05-18 16:30:00'),
('10010103403','西门',32.5,0.28,32535,'2019-05-18 17:00:00');

create table if not exists systemmsg
(
	id int auto_increment,
	title text null,
	msg text null,
	msgType varchar(64) null,
	createTime datetime default now() null,
	constraint systemmsg_pk primary key (id)
)engine =InnoDB default charset =utf8;

insert into systemmsg(title, msg, msgType, createTime)
VALUES
('安全通知','重庆地区很多小区最近遭到小偷行窃，各位管理员提高安全意识，严格把关进入小区的人','text',now()),
('新年快乐','https://baike.baidu.com/item/%E6%96%B0%E5%B9%B4%E5%BF%AB%E4%B9%90/12811187?fr=aladdin','html',now()),
('门禁设备故障排除方案','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559246251527&di=00822830bdf8727a5f064d99a2ad200d&imgtype=0&src=http%3A%2F%2Fs8.sinaimg.cn%2Fmw690%2F006898WKzy74N6oxMSre7%26690','image',now());

create table if not exists adminmsg
(
	id int auto_increment,
	adminAccount varchar(64) not null,
	title text null,
	msgId int null,
	state varchar(64) default '未读' null,
	createTime datetime default now() null,
	constraint adminmsg_pk primary key (id)
)engine =InnoDB default charset =utf8;

insert into adminmsg(adminAccount, title, msgId, state, createTime)
VALUES
('13364009794','安全通知',1,'未读',now()),
('13364009794','新年快乐',2,'未读',now()),
('13364009794','门禁设备故障排除方案',3,'未读',now());

select * from users;