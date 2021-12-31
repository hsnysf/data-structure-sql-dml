drop schema public cascade;

create schema public;

create table public.governorate
(
  gvn_id serial,
  gvn_name character varying(100),
  constraint pk_gvn_id primary key (gvn_id)
);

insert into public.governorate(gvn_name) values('Center');

create table public.city
(
  ct_id serial,
  ct_name character varying(100),
  ct_governorate_id integer,
  constraint pk_ct_id primary key (ct_id),
  constraint fk_governorate_id foreign key (ct_governorate_id)
	references public.governorate (gvn_id)
);

insert into public.city(ct_name, ct_governorate_id) values('Manama', 1);

create table public.school
(
  sch_id serial,
  sch_name character varying(100),
  constraint pk_sch_id primary key (sch_id)
);

insert into public.school(sch_name) values('Bin Khuldon');

create table public.country
(
  cnt_id serial,
  cnt_name character varying(100),
  constraint pk_cnt_id primary key (cnt_id)
);

insert into public.country(cnt_name) values('Kuwait');

create table public.company
(
  cmp_id serial,
  cmp_name character varying(100),
  constraint pk_cmp_id primary key (cmp_id)
);

insert into public.company(cmp_name) values('Maraei');

create table public.address
(
  addr_id serial,
  addr_building character varying(20),
  addr_road character varying(20),
  addr_block character varying(20),
  constraint pk_addr_id primary key (addr_id)
);

insert into public.address(addr_building, addr_road, addr_block) values('Building 1', 'Road 1', 'Block 1');
insert into public.address(addr_building, addr_road, addr_block) values('Building 2', 'Road 2', 'Block 2');

create table public.person
(
  prsn_id serial,
  prsn_name character varying(100) default 'Hasan',
  prsn_gender character default 'M',
  prsn_age smallint default 33,
  prsn_cpr int default 40100162,
  prsn_account_no bigint default 12345678912345,
  prsn_gpa real default 3.14,
  prsn_salary double precision default 666.55,
  prsn_annual_income decimal default 9000.55,
  prsn_date_of_birth date default '2010-10-10',
  prsn_registration_date_time timestamp default '2010-10-10 05:05:56',
  prsn_sleep_time time default '10:10:10',
  prsn_graduated boolean default true,
  prsn_certificates character varying(100)[] default '{C++, C#}',
  prsn_city_id integer,
  prsn_school_id integer,
  prsn_country_id integer,
  prsn_company_id integer,
  prsn_home_address_id integer,
  prsn_work_address_id integer,
  prsn_active boolean,
  constraint pk_prsn_id primary key (prsn_id),
  constraint fk_city_id foreign key (prsn_city_id)
	references public.city (ct_id),
  constraint fk_school_id foreign key (prsn_school_id)
	references public.school (sch_id),
  constraint fk_country_id foreign key (prsn_country_id)
	references public.country (cnt_id),
  constraint fk_company_id foreign key (prsn_company_id)
	references public.company (cmp_id),
  constraint fk_home_address_id foreign key (prsn_home_address_id)
	references public.address (addr_id),
  constraint fk_work_address_id foreign key (prsn_work_address_id)
	references public.address (addr_id)
);

insert into public.person
(
prsn_name, prsn_gender, 
prsn_age, prsn_cpr, prsn_account_no, 
prsn_gpa, prsn_salary, prsn_annual_income, 
prsn_date_of_birth, prsn_registration_date_time, 
prsn_sleep_time, prsn_graduated, prsn_certificates,
prsn_city_id, prsn_school_id, prsn_country_id, prsn_company_id,
prsn_home_address_id, prsn_work_address_id
)
values 
('Hasan Yusuf', 'M', 33, 880101261, 123456789012345, 
3.12, 50.45, 100.6, '1988-01-27', '2000-01-27 10:10:10', '20:30', true, '{DB2, Java}', 1, 1, 1, 1, 1, 2);

insert into public.person
(
prsn_name, prsn_gender, 
prsn_age, prsn_cpr, prsn_account_no, 
prsn_gpa, prsn_salary, prsn_annual_income, 
prsn_date_of_birth, prsn_registration_date_time, 
prsn_sleep_time, prsn_graduated, prsn_certificates,
prsn_city_id, prsn_school_id, prsn_country_id, prsn_company_id,
prsn_home_address_id, prsn_work_address_id
)
values 
('Fatima Yusuf', 'F', 22, 040100162, 9876543210, 
2.12, 100.45, 50.6, '2000-01-27', '2010-01-27 11:11:11', '15:10', true, '{Postgres, Java EE}', 1, 1, 1, 1, 1, 2);

create table public.student
(
	std_id integer,
	std_college_name character varying(100),
	std_current_year integer,
    constraint fk_std_id foreign key (std_id)
	references public.person (prsn_id)
);

insert into public.student(std_id, std_college_name, std_current_year) values(1, 'IT', 2);
insert into public.student(std_id, std_college_name, std_current_year) values(2, 'Science', 3);

create table public.unique_date_of_birth
(
  udob_id serial,
  udob_date date,
  udob_leap_year boolean default true,
  constraint pk_udob_id primary key (udob_id)
);

insert into public.unique_date_of_birth(udob_date, udob_leap_year) values('2015-10-10', true);

create table public.doctor
(
  dctr_id serial,
  dctr_cpr integer,
  dctr_hospital character varying(100),
  constraint pk_dctr_id primary key (dctr_id)
);

insert into public.doctor(dctr_cpr, dctr_hospital) values(870501236, 'Alkindi');