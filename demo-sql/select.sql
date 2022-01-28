SELECT t_A.id
FROM t_A
         INNER JOIN load_balancer
                    ON t_A.id = t_B.id and t_B.bandwidth = 0;

SELECT t_A.id
FROM t_A
         INNER JOIN load_balancer
                    ON t_A.id = t_B.id and t_B.bandwidth!=0;

-- select t_A.attachment_name, t_B.cancel_reason, t_C.cargo_level_name
select *
from t_A
         INNER JOIN t_B ON t_A.id = t_B.id and
                           t_A.id = "EC0010000000546"
         JOIN t_C ON t_C.id = t_B.id;

select *
from t_A
         left join t_B on t_A.id = t_B.id
         left join t_C on t_B.id = t_C.id;
