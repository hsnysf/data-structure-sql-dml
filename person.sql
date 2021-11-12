drop schema public cascade;

create schema public;

create table public.city
(
  ct_id serial,
  ct_name character varying(100),
  constraint pk_ct_id primary key (ct_id)
);

create table public.school
(
  sch_id serial,
  sch_name character varying(100),
  constraint pk_sch_id primary key (sch_id)
);

create table public.country
(
  cnt_id serial,
  cnt_name character varying(100),
  constraint pk_cnt_id primary key (cnt_id)
);

create table public.company
(
  cmp_id serial,
  cmp_name character varying(100),
  constraint pk_cmp_id primary key (cmp_id)
);

create table public.address
(
  addr_id serial,
  addr_building character varying(20),
  addr_road character varying(20),
  addr_block character varying(20),
  constraint pk_addr_id primary key (addr_id)
);

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
  prsn_annual_income decimal default 9000.55,
  prsn_date_of_birth date default '2010-10-10',
  prsn_registration_date_time timestamp default '2010-10-10 05:05:56',
  prsn_sleep_time time default '10:10:10',
  prsn_graduated boolean default true,
  prsn_certificates character varying(100)[],
  prsn_city_id integer,
  prsn_school_id integer,
  prsn_country_id integer,
  prsn_company_id integer,
  prsn_home_address_id integer,
  prsn_work_address_id integer,
  constraint pk_prsn_id primary key (prsn_id),
  constraint fk_city_id foreign key (prsn_city_id)
	references public.city (ct_id) match simple,
  constraint fk_school_id foreign key (prsn_school_id)
	references public.school (sch_id) match simple,
  constraint fk_country_id foreign key (prsn_country_id)
	references public.country (cnt_id) match simple,
  constraint fk_company_id foreign key (prsn_company_id)
	references public.company (cmp_id) match simple,
  constraint fk_home_address_id foreign key (prsn_home_address_id)
	references public.address (addr_id) match simple,
  constraint fk_work_address_id foreign key (prsn_work_address_id)
	references public.address (addr_id) match simple
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
and prsn_graduated = true;

select
prsn_name, prsn_gender, prsn_age, 
prsn_age, prsn_cpr, prsn_account_no, 
prsn_gpa, prsn_salary, prsn_annual_income, 
prsn_date_of_birth, prsn_registration_date_time, 
prsn_sleep_time, prsn_graduated
from public.person
where prsn_id = 1
order by prsn_name, prsn_id desc;

select prsn_name, prsn_gender, prsn_age, prsn_age 
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
order by prsn_id, prsn_name desc;

select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 1
union
select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 2
union all
select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 3
intersect
select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 4
intersect all
select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 5
except
select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 6
except all
select prsn_name, prsn_gender, prsn_age from public.person where prsn_id = 7;

select  count(prsn_id) as prsn_id_count, 
		sum(prsn_salary) as prsn_salary_sum, 
		avg(prsn_annual_income) as prsn_annual_income_avg, 
		min(prsn_cpr) as prsn_cpr_min, 
		max(prsn_gpa) as prsn_gpa_max 
from public.person 
where prsn_id = 1 
group by prsn_gender, prsn_age 
having (count(prsn_id) > 10 and sum(prsn_salary) < 100) 
order by prsn_gender;

select distinct(prsn_date_of_birth) as prsn_date_of_birth_distinct 
from public.person 
where prsn_gpa > 3.12;

select coalesce(prsn_date_of_birth, '12-12-2020') 
				as prsn_date_of_birth_coalesce 
from public.person;

select prsn_date_of_birth, 
count(prsn_id) as prsn_id_count 
from public.person 
group by prsn_date_of_birth 
having count(prsn_id) > 2
order by prsn_date_of_birth;

select  prsn_name, prsn_gender, prsn_age, 
		home_address.addr_road as home_address_addr_road, 
		home_address.addr_block as home_address_addr_block, 
		work_address.addr_road as work_address_addr_road, 
		work_address.addr_block as work_address_addr_block 
from public.person as person 
inner join public.address as home_address 
			on prsn_home_address_id = home_address.addr_id 
inner join public.address as work_address 
			on prsn_work_address_id = work_address.addr_id 
where prsn_id = 1;

select prsn_name, prsn_gender, prsn_age 
from public.person
inner join public.doctor on dctr_cpr = prsn_cpr
where prsn_gender = 'M'
and dctr_hospital = 'Alkindi';

select prsn_name, prsn_gender, prsn_age 
from 
	(select * from public.person where prsn_gender = 'M') as male_person 
inner join 
	(select * from public.doctor where dctr_hospital = 'Alkindi') as alkindi_doctor 
	on dctr_cpr = prsn_cpr;

select * 
from 
	(
		select * from (
					   select * from public.person 
					   where prsn_gender = 'M'
				      ) as person_gender 
		where prsn_age = 12
	) 	as person_age
inner join 
		(
			select * from (
							select * from public.city 
							where ct_id = 1
						  ) as city_id
			where ct_name = 'Manama'
		) as city_name
		  on prsn_city_id = ct_id
where prsn_gpa = 3.12;

select prsn_name, prsn_gender, 
		prsn_age, row_number() over(partition by prsn_age, prsn_graduated 
									order by prsn_age, prsn_cpr desc) as row_num 
from public.person;

select prsn_name, prsn_age 
from (
		select prsn_name, prsn_age, 
				row_number() over(partition by prsn_age 
									order by prsn_gpa desc) as person_row_number 
		from public.person
	 ) as person 
where person_row_number = 1;
