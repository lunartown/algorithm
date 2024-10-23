SELECT h.HISTORY_ID, 
    ROUND(c.DAILY_FEE * 
    (DATEDIFF(h.END_DATE, h.START_DATE) + 1) * 
    (100 - COALESCE(p.DISCOUNT_RATE, 0)) / 100, 0) AS FEE
FROM CAR_RENTAL_COMPANY_CAR c
    INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY h
    ON c.CAR_ID = h.CAR_ID
    LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
    ON c.CAR_TYPE = p.CAR_TYPE
    AND p.DURATION_TYPE = (
        CASE 
            WHEN DATEDIFF(h.END_DATE, h.START_DATE) >= 90 THEN '90일 이상'
            WHEN DATEDIFF(h.END_DATE, h.START_DATE) >= 30 THEN '30일 이상'
            WHEN DATEDIFF(h.END_DATE, h.START_DATE) >= 7 THEN '7일 이상'
        END
    )
WHERE c.CAR_TYPE = '트럭'
ORDER BY FEE DESC,
    h.HISTORY_ID DESC;
        