WITH RENTAL_MONTH AS (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE >= '2022-08-01'
        AND START_DATE < '2022-11-01'
    GROUP BY CAR_ID
    HAVING COUNT(*) >= 5
)
SELECT MONTH(h.START_DATE) AS MONTH, h.CAR_ID, COUNT(*) AS RENTAL_COUNT
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY h
    INNER JOIN RENTAL_MONTH m
    ON h.CAR_ID = m.CAR_ID
WHERE h.START_DATE >= '2022-08-01'
        AND h.START_DATE < '2022-11-01'    
GROUP BY h.CAR_ID, MONTH(h.START_DATE)
HAVING COUNT(*) > 0
ORDER BY MONTH ASC,
    h.CAR_ID DESC;