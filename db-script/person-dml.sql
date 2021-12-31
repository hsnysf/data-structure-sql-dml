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

insert into public.person 
(prsn_date_of_birth, 
prsn_registration_date_time, 
prsn_sleep_time) 
values 
(current_date + interval '1 days' - interval '2 months', 
current_timestamp - interval '1 hours' + interval '2 years', 
current_timestamp + interval '1 minutes' - interval '2 hours');

update public.person 
set prsn_age = prsn_age + 1, 
prsn_cpr = prsn_cpr - 2, 
prsn_account_no = prsn_account_no * 3, 
prsn_gpa = prsn_gpa / 4, 
prsn_salary = prsn_salary * prsn_salary, 
prsn_annual_income = prsn_annual_income + prsn_annual_income, 
prsn_date_of_birth = current_date + interval '1 days' - interval '2 months', 
prsn_registration_date_time = current_timestamp - interval '1 hours' + interval '2 years', 
prsn_sleep_time = current_timestamp + interval '1 minutes' - interval '2 hours' 
where prsn_id = 1;
