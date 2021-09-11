drop schema public cascade;

create schema public;

create table public.person
(
  prsn_id serial,
  prsn_name character varying(100) default 'Hasan',
  prsn_gender character default 'M',
  prsn_age smallint default 33,
  prsn_cpr integer default 40100162,
  prsn_account_no bigint default 12345678912345,
  prsn_gpa float default 3.14,
  prsn_salary double precision default 666.55,
  prsn_annual_income decimal default 9000.55 ,
  prsn_date_of_birth date default '2010-10-10',
  prsn_registration_date_time timestamp default '2010-10-10 05:05:56',
  prsn_sleep_time time default '10:10:10',
  prsn_graduated boolean default true,
  constraint pk_prsn_id primary key (prsn_id)
);

create table public.unique_date_of_birth
(
  udob_id serial,
  udob_date date,
  udob_leap_year boolean default true,
  constraint pk_udob_id primary key (udob_id)
);

create table public.doctor
(
  dctr_id serial,
  dctr_cpr integer,
  dctr_hospital character varying(100),
  constraint pk_dctr_id primary key (dctr_id)
);

insert into public.person
(
prsn_name, prsn_gender, 
prsn_age, prsn_cpr, prsn_account_no, 
prsn_gpa, prsn_salary, prsn_annual_income, 
prsn_date_of_birth, prsn_registration_date_time, 
prsn_sleep_time, prsn_graduated
)
values 
('Hasan Yusuf', 'M', 33, 880101261, 123456789012345, 
3.12, 50.45, 100.6, '1988-01-27', '2000-01-27 10:10:10', '20:30', true);

update public.person
set
prsn_name = 'Hasan Yusuf', prsn_gender = 'M', 
prsn_age = 33, prsn_cpr = 880101261, prsn_account_no = 123456789012345, 
prsn_gpa = 3.12, prsn_salary = 50.45, prsn_annual_income = 100.6, 
prsn_date_of_birth = '1988-01-27', prsn_registration_date_time = '2000-01-27 10:10:10', 
prsn_sleep_time = '20:30', prsn_graduated = true
where prsn_id = 1;

delete from public.person
where prsn_id = 1
and prsn_name = 'Hasan Yusuf'
and prsn_gender = 'M'
and prsn_age = 33
and prsn_cpr = 880101261
and prsn_account_no = 123456789012345
and prsn_gpa = 3.12
and prsn_salary = 50.45
and prsn_annual_income = 100.6
and prsn_date_of_birth = '1988-01-27'
and prsn_registration_date_time = '2000-01-27 10:10:10'
and prsn_sleep_time = '20:30'
and prsn_graduated = true

select
prsn_id, prsn_name, prsn_gender, 
prsn_age, prsn_cpr, prsn_account_no, 
prsn_gpa, prsn_salary, prsn_annual_income, 
prsn_date_of_birth, prsn_registration_date_time, 
prsn_sleep_time, prsn_graduated
from public.person
where prsn_id = 1
order by prsn_name, prsn_id desc;

select prsn_id, prsn_name, prsn_gender, prsn_age 
from public.person 
where prsn_id = 1
and prsn_name ilike '%Hasan%' 
and prsn_cpr <> 88111111 
and prsn_age > 12
and prsn_gpa < 3.12 
and prsn_salary <= 500 
and prsn_annual_income >= 100
and prsn_account_no in (111, 222, 333)
and prsn_age not in (11, 12, 13)
and prsn_sleep_time between '08:10:10' and '10:30:10' 
and prsn_gender is null 
and prsn_graduated is not null 
and prsn_date_of_birth in(select udob_date 
							from public.unique_date_of_birth 
							where udob_leap_year = true) 
and prsn_date_of_birth not in(select udob_date 
								from public.unique_date_of_birth 
								where udob_leap_year = false) 
and exists (select dctr_id 
				from public.doctor 
				where dctr_cpr = prsn_cpr 
				and dctr_hospital = 'Alkindi') 
and not exists (select dctr_id 
					from public.doctor 
					where dctr_cpr = prsn_cpr and dctr_hospital = 'Bin Hayan') 
order by prsn_id, prsn_name desc