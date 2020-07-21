create database if not exists acdatabase;

use acdatabase;
create table if not exists users (
    id int auto_increment,
    account varchar(64) not null,
    name varchar(128) default 'δ֪',
    passWord varchar(64) default '12345678',
    cardNumber varchar(64) not null ,
    state varchar(64) default '����',
    registerTime datetime default now(),
    primary key(id),
    unique key (account,cardNumber)
)engine =InnoDB default charset =utf8;

insert into users (account, name, passWord, cardNumber, state, registerTime)
VALUES
('1446023280','����1','12345678','00000000','����',now()),
('1446023281','����2','12345679','00000001','����',now()),
('1446023282','����3','12345680','00000002','����',now()),
('1446023283','����1','12345681','00000003','����',now()),
('1446023284','����2','12345682','00000004','����',now()),
('1446023285','����3','12345683','00000005','����',now()),
('Կ��','Կ��','12345683','Կ��','����',now());

create table if not exists admins
(
	id int auto_increment,
	account varchar(64) not null,
	name varchar(128) default 'δ֪' null,
	passWord varchar(64) default '12345678' null,
	deviceNumber varchar(64) not null,
	deviceName varchar(128) default 'δ֪',
	deviceNikeName varchar(64) default 'δ֪',
	type varchar(64) default '��ͨ����Ա',
	state varchar(64) default '����' null,
	registerTime datetime default now() null,
	constraint admins_pk primary key (id),
	constraint admins_uk unique key (account)
)engine =InnoDB default charset =utf8;

insert into  admins(account, name, passWord, deviceNumber, deviceName,deviceNikeName,type,state, registerTime)
VALUES
('13364009753','��������Ա','heweiqq123.','�������豸','�������豸','�������豸','��������Ա','����',now()),
('13364009764','���Ź���Ա','heweiqq123.','10010103423','�����������Ž��ն�','����','��ͨ����Ա','����',now()),
('13364009774','���Ź���Ա','heweiqq123.','10010103434','�����������Ž��ն�','����','��ͨ����Ա','����',now()),
('13364009784','ǰ�Ź���Ա','heweiqq123.','10010103413','�����������Ž��ն�','ǰ��','��ͨ����Ա','����',now()),
('13364009794','���Ź���Ա','heweiqq123.','10010103403','�����������Ž��ն�','����','��ͨ����Ա','����',now());

create table if not exists devices
(
	id int auto_increment,
	deviceNumber varchar(64) not null,
	deviceName varchar(128) default 'δ֪' null,
	deviceNikeName varchar(64) default 'δ֪' null,
	state varchar(64) default '����' null,
	registerTime datetime default now() null,
	constraint devices_pk primary key (id),
	constraint devices_uk unique key (deviceNumber)
)engine =InnoDB default charset =utf8;

insert into devices(deviceNumber, deviceName,deviceNikeName, state, registerTime)
values
('10010103423','�����������Ž��ն�','����','����',now()),
('10010103434','�����������Ž��ն�','����','����',now()),
('10010103413','�����������Ž��ն�','ǰ��','����',now()),
('10010103403','�����������Ž��ն�','����','����',now());

create table if not exists records
(
	id int auto_increment,
	userAccount varchar(64) default 'δ֪' not null,
	userName varchar(128) default 'δ֪' null,
	deviceNumber varchar(64) not null,
	deviceNikeName varchar(64) default 'δ֪' null,
	state varchar(64) default 'δ֪' null,
	time datetime default now() null,
	constraint records_pk
		primary key (id)
)engine =InnoDB default charset =utf8;

insert into records(userAccount, userName, deviceNumber, deviceNikeName, state, time)
VALUES
('1446023280','����1','10010103423','����','��',now()),
('1446023280','����1','10010103423','����','��',now()),
('1446023281','����2','10010103423','����','��',now()),
('1446023281','����2','10010103423','����','��',now()),
('1446023284','����2','10010103403','����','��',now()),
('1446023284','����2','10010103403','����','��',now());

create table if not exists envirodata
(
	id int auto_increment,
	deviceNumber varchar(64) not null,
	deviceNikeName varchar(64) default 'δ֪' null,
	temperature float null,
	humidity float null,
	lightIntensity int null,
	time datetime default now(),
	constraint envirodata_pk primary key (id)
)engine =InnoDB default charset =utf8;


insert into envirodata(deviceNumber, deviceNikeName, temperature, humidity, lightIntensity, time)
VALUES
('10010103403','����',15.5,0.23,25535,'2019-05-18 00:00:00'),
('10010103403','����',16.0,0.23,25635,'2019-05-18 00:30:00'),
('10010103403','����',16.8,0.23,25735,'2019-05-18 01:00:00'),
('10010103403','����',19.5,0.23,25835,'2019-05-18 01:30:00'),
('10010103403','����',20.5,0.24,25935,'2019-05-18 02:00:00'),
('10010103403','����',20.9,0.24,26535,'2019-05-18 02:30:00'),
('10010103403','����',21.3,0.25,26635,'2019-05-18 03:00:00'),
('10010103403','����',22.5,0.25,26735,'2019-05-18 03:30:00'),
('10010103403','����',23.5,0.28,26835,'2019-05-18 04:00:00'),
('10010103403','����',24.5,0.28,26935,'2019-05-18 04:30:00'),
('10010103403','����',25.5,0.30,27535,'2019-05-18 05:00:00'),
('10010103403','����',26.5,0.31,27635,'2019-05-18 05:30:00'),
('10010103403','����',27.5,0.32,27735,'2019-05-18 06:00:00'),
('10010103403','����',28.5,0.33,27835,'2019-05-18 06:30:00'),
('10010103403','����',29.5,0.34,27935,'2019-05-18 07:00:00'),
('10010103403','����',30.5,0.34,28535,'2019-05-18 07:30:00'),
('10010103403','����',31.5,0.35,28635,'2019-05-18 08:00:00'),
('10010103403','����',32.5,0.35,28735,'2019-05-18 08:30:00'),
('10010103403','����',33.5,0.35,28835,'2019-05-18 09:00:00'),
('10010103403','����',34.5,0.36,28935,'2019-05-18 09:30:00'),
('10010103403','����',35.5,0.36,29535,'2019-05-18 10:00:00'),
('10010103403','����',36.5,0.36,29635,'2019-05-18 10:30:00'),
('10010103403','����',37.5,0.36,29735,'2019-05-18 11:00:00'),
('10010103403','����',37.5,0.35,29835,'2019-05-18 11:30:00'),
('10010103403','����',37.8,0.35,29935,'2019-05-18 12:00:00'),
('10010103403','����',37.9,0.34,35535,'2019-05-18 12:30:00'),
('10010103403','����',38.5,0.34,35635,'2019-05-18 13:00:00'),
('10010103403','����',38.5,0.33,35735,'2019-05-18 13:30:00'),
('10010103403','����',38.5,0.32,35835,'2019-05-18 14:00:00'),
('10010103403','����',37.5,0.32,35535,'2019-05-18 14:30:00'),
('10010103403','����',36.5,0.31,35035,'2019-05-18 15:00:00'),
('10010103403','����',35.5,0.31,34535,'2019-05-18 15:30:00'),
('10010103403','����',34.5,0.29,34035,'2019-05-18 16:00:00'),
('10010103403','����',33.5,0.29,33035,'2019-05-18 16:30:00'),
('10010103403','����',32.5,0.28,32535,'2019-05-18 17:00:00');

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
('��ȫ֪ͨ','��������ܶ�С������⵽С͵���ԣ���λ����Ա��߰�ȫ��ʶ���ϸ�ѹؽ���С������','text',now()),
('�������','https://baike.baidu.com/item/%E6%96%B0%E5%B9%B4%E5%BF%AB%E4%B9%90/12811187?fr=aladdin','html',now()),
('�Ž��豸�����ų�����','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559246251527&di=00822830bdf8727a5f064d99a2ad200d&imgtype=0&src=http%3A%2F%2Fs8.sinaimg.cn%2Fmw690%2F006898WKzy74N6oxMSre7%26690','image',now());

create table if not exists adminmsg
(
	id int auto_increment,
	adminAccount varchar(64) not null,
	title text null,
	msgId int null,
	state varchar(64) default 'δ��' null,
	createTime datetime default now() null,
	constraint adminmsg_pk primary key (id)
)engine =InnoDB default charset =utf8;

insert into adminmsg(adminAccount, title, msgId, state, createTime)
VALUES
('13364009794','��ȫ֪ͨ',1,'δ��',now()),
('13364009794','�������',2,'δ��',now()),
('13364009794','�Ž��豸�����ų�����',3,'δ��',now());

select * from users;