@Date2024-12-12
数据倾斜产生的原因:在生产环境中,可能产生大量的数据,在进行分布式处理的过程中,每台节点的数据量分配不均匀
案例:当一个地区和另一个地区,数据量不对等的同时,数据占比为80000000:8000000 10000000:1
这种就是一个典型的数据倾斜问题
解决方案:
         1.提高map的数量
		 2. 通过将region和rand()函数进行拼接,按拼接后的字段进行分组,求count(),最后substr()切割后冲,计算
		 提高查询效率
		 with t1 as (
    select userid,
           order_no,
           concat(region,'-',rand()) as region,
           product_no,
           color_no,
           sale_amount,
           ts
    from date_east
),
    t2 as (
        select region,
               count(1) as cnt
        from t1
        group by region
    ),
    t3 as (
        select region,
               substr(region,1,2) as re,
               cnt
        from t2
    )
select re,
       count(1) as cnt
from t3
group by re;


