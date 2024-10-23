WITH SUM_ORDER AS (
    SELECT *
    FROM FIRST_HALF
    
    UNION ALL
    
    SELECT *
    FROM JULY
),
ORDER_RANK AS (
    SELECT FLAVOR,
        rank() OVER (ORDER BY SUM(TOTAL_ORDER) DESC) AS rk
    FROM SUM_ORDER
    GROUP BY FLAVOR
)
SELECT FLAVOR
FROM ORDER_RANK
ORDER BY rk
LIMIT 3;