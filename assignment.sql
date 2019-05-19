create database assignment default character set utf8;

use assignment;

create table AUTHOR(
	EmailAddr varchar(20) not null default '',
	LastName varchar(20) not null default '',
	FirstName varchar(20) not null default '',
	primary key(EmailAddr)
) engine=myisam charset=utf8;

insert into AUTHOR values('tom123@pace.edu', 'Tom', 'James');
insert into AUTHOR values('James456@pace.edu', 'Chin', 'James');
insert into AUTHOR values('Chh776@pace.edu', 'Jerry', 'Gg');

select * from AUTHOR;

create table PAPER(
	ID smallint(4) unsigned not null auto_increment,
	Title varchar(30) not null default '',
	Abstract text not null,
	FileName varchar(20) not null default '',
	primary key(ID)
) engine=myisam charset=utf8 auto_increment=1;

insert into PAPER (Title, Abstract, FileName) values('Java', 'Learning', 'JavaLearning');
insert into PAPER (Title, Abstract, FileName) values('WebDevelopment', 'Develop a website', 'WebsiteLearning');
insert into PAPER (Title, Abstract, FileName) values('ComputerDevelop', 'Basic competer science', 'ComputerLearning');

create table AUTHOR_SUBMITS_PAPER(
	ID_PAPER smallint(4) unsigned not null,
	EmailAddr_AUTHOR varchar(20) not null default '',
	foreign key(ID_PAPER) references PAPER(ID),
	foreign key(EmailAddr_AUTHOR) references AUTHOR(EmailAddr)
) engine=myisam charset=utf8;

insert into AUTHOR_SUBMITS_PAPER values(1, 'tom123@pace.edu');
insert into AUTHOR_SUBMITS_PAPER values(2, 'James456@pace.edu');	

create table REVIEWER(
	EmailAddr varchar(20) not null default '',
	FirstName varchar(20) not null default '',
	LastName varchar(20) not null default '',
	AuthorFeedback varchar(30) not null default '',
	PhoneNum int(15) not null default 0,
	Affiliation varchar(20) not null default '',
	primary key(EmailAddr)
) engine=myisam charset=utf8;

insert into REVIEWER values('aaa111@pace.edu', 'aa', 'bb', 'Good', '553244', 'Project1');
insert into REVIEWER values('bbb222@pace.edu', 'bb', 'cc', 'Awesome', '544544', 'Project2');
insert into REVIEWER values('ccc233@pace.edu', 'sb', 'cd', 'Not good', '544544', 'Project3');

create table REVIEWER_ASSIGNED_PAPER(
	ID_PAPER smallint(4) unsigned not null,
	EmailAddr_REVIEWER varchar(20) not null default '',
	foreign key(ID_PAPER) references PAPER(ID),
	foreign key(EmailAddr_REVIEWER) references REVIEWER(EmailAddr)
) engine=myisam charset=utf8;

insert into REVIEWER_ASSIGNED_PAPER values(1, 'aaa111@pace.edu');
insert into REVIEWER_ASSIGNED_PAPER values(2, 'bbb222@pace.edu');
insert into REVIEWER_ASSIGNED_PAPER values(3, 'ccc233@pace.edu');

create table TOPIC(
	ID smallint(4) unsigned not null auto_increment,
	TopicName varchar(30) not null default '',
	primary key(ID)
) engine=myisam charset=utf8 auto_increment=1;

insert into TOPIC (TopicName) values('ComputerLab');
insert into TOPIC (TopicName) values('ComputerDevelop');

create table REVIEWER_HAS_TOPIC(
	EmailAddr_REVIEWER varchar(20) not null default '',
	ID_TOPIC smallint(4) unsigned not null,
	foreign key(ID_TOPIC) references TOPIC(ID),
	foreign key(EmailAddr_REVIEWER) references REVIEWER(EmailAddr)
) engine=myisam charset=utf8;

insert into REVIEWER_HAS_TOPIC values('aaa111@pace.edu', 1);
insert into REVIEWER_HAS_TOPIC values('bbb222@pace.edu', 2);
insert into REVIEWER_HAS_TOPIC values('ccc233@pace.edu', 3);

create table REVIEW(
	ID smallint(4) unsigned not null auto_increment,
	Recommendation text not null,
	MeritScore smallint(4) not null default 0,
	PaperId smallint(4) not null default 1,
	ReadabilityScore smallint(4) not null default 0,
	ReviewerId smallint(4) not null default 1,
	OriginalityScore smallint(4) not null default 0,
	RelevanceScore smallint(4) not null default 0,
	foreign key(PaperId) references PAPER(ID),
	primary key(ID)
) engine=myisam charset=utf8 auto_increment=1;

insert into REVIEW values(1, ' ', 100, 1, 80, 1, 50, 90);
insert into REVIEW values(2, ' ', 99, 2, 60, 2, 70, 80);
insert into REVIEW values(3, ' ', 88, 3, 70, 3, 80, 100);

create table REVIEWER_SUBMITS_REVIEW(
	ID_REVIEW smallint(4) unsigned not null,
	EmailAddr_REVIEWER varchar(20) not null default '',
	foreign key(ID_REVIEW) references REVIEW(ID),
	foreign key(EmailAddr_REVIEWER) references REVIEWER(EmailAddr)
) engine=myisam charset=utf8;

insert into REVIEWER_SUBMITS_REVIEW values(1, 'aaa111@pace.edu');
insert into REVIEWER_SUBMITS_REVIEW values(2, 'bbb222@pace.edu');
insert into REVIEWER_SUBMITS_REVIEW values(3, 'ccc233@pace.edu');

