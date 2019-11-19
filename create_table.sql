use chargesystem;

create table if not exists house_owner
(
	owner_id varchar(5),
    owner_name varchar(20),
    house_id varchar(5),
    work_place varchar(100),
    phone_number varchar(11),
    primary key (house_id)
); 

create table if not exists house
(
	house_id varchar(5),
    area float,
    room smallint unsigned,
    primary key (house_id)
);

create table if not exists department
(
	department_id varchar(3),
    department_name varchar(20),
    manager varchar(20),
    phone_number varchar(11),
    primary key (department_id)
);

create table if not exists staff
(
	staff_id varchar(3),
    staff_name varchar(20),
    birthday date,
    /*M——男性，F——女性*/
    gender ENUM('M','F'),
    address varchar(20),
    phone varchar(11),
    department_id varchar(3),
    /*M——经理，S——员工*/
    job ENUM('M','S'),
    pwd varchar(100),
    primary key (staff_id),
    foreign key (department_id) references department(department_id)
);

create table if not exists charge
(
	property float,
    clean float,
    water float,
    electricity float
);