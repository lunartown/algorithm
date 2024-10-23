SELECT DISTINCT CAR_ID,
    CASE
        WHEN EXISTS (
            SELECT CAR_ID
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
            WHERE CAR_ID = a.CAR_ID
                AND END_DATE >= '2022-10-16' 
                AND START_DATE <= '2022-10-16' 
            ) THEN '대여중'
        ELSE '대여 가능'
    END AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY a
ORDER BY CAR_ID DESC;