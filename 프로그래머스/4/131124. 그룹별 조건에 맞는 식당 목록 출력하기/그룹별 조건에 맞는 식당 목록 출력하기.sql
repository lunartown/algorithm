WITH REVIEW_COUNT AS (
    SELECT MEMBER_ID,
        rank() OVER (ORDER BY COUNT(*) DESC) AS `RANK`
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
)
SELECT p.MEMBER_NAME, r.REVIEW_TEXT, DATE_FORMAT(r.REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
FROM REST_REVIEW r
    INNER JOIN MEMBER_PROFILE p
    ON r.MEMBER_ID = p.MEMBER_ID
WHERE r.MEMBER_ID IN (
    SELECT MEMBER_ID
    FROM REVIEW_COUNT
    WHERE `RANK` = 1
)
ORDER BY r.REVIEW_DATE ASC, r.REVIEW_TEXT ASC;