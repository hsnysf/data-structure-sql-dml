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
where prsn_id = 1;

select 
prsn_id, prsn_name, prsn_gender, 
prsn_age, prsn_cpr, prsn_account_no, 
prsn_gpa, prsn_salary, prsn_annual_income, 
prsn_date_of_birth, prsn_registration_date_time, 
prsn_sleep_time, prsn_graduated
from public.person
where prsn_id = 1
order by prsn_id;