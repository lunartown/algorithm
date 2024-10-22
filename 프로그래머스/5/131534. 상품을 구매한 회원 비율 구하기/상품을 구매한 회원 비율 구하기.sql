WITH TOTAL_USERS AS (
    SELECT COUNT(*) as total
    FROM USER_INFO
    WHERE YEAR(JOINED) = 2021
)

SELECT 
    YEAR(s.SALES_DATE) AS YEAR,
    MONTH(s.SALES_DATE) AS MONTH,
    COUNT(DISTINCT i.USER_ID) AS PURCHASED_USERS,
    ROUND(COUNT(DISTINCT i.USER_ID) / t.total, 1) AS PURCHASED_RATIO
FROM 
    USER_INFO AS i
    INNER JOIN ONLINE_SALE AS s
        ON i.USER_ID = s.USER_ID
    CROSS JOIN TOTAL_USERS AS t
WHERE 
    YEAR(i.JOINED) = 2021
GROUP BY 
    DATE_FORMAT(s.SALES_DATE, "%Y-%m")
ORDER BY
    YEAR(s.SALES_DATE),
    MONTH(s.SALES_DATE);
    