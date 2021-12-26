create or replace view family_person_month_v as

select t.gain_person,
	date_format(t.creation_date,'%Y%m') as 'month',
	round(sum((case when (t.value > 0) then t.value else 0 end))) as 'income',
	round(sum((case when (t.value < 0) then t.value else 0 end))) as 'cost',
	group_concat((case when (t.value < 0) then concat('[',t.name,':',t.value,']') end)) as 'costDetail'
from ecnomic_line_t t 
group by date_format(t.creation_date,'%Y%m')