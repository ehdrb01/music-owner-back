-- 미사용 --
-- 조직 view 생성
SELECT 
	A.DBRN_CODE AS GOOD_DEPT_CODE,
	A.DBRN_NM AS NAME,
	CASE A.DBRN_NM 
	   WHEN 
	   		B.HEADQ_NM
	   THEN CONCAT('.H_',B.HEADQ_CODE)
	   ELSE
	     IF(A.SUBHQ_CODE IS NULL, CONCAT('/H_' , A.HEADQ_CODE , '/S_' , A.DBRN_CODE),
	        IF(A.DBRN_NM=C.HEADQ_NM , CONCAT('/H_' , B.HEADQ_CODE , '/S_' ,C.HEADQ_CODE), CONCAT('/H_' , B.HEADQ_CODE , '/S_' , C.HEADQ_CODE , '/' , A.DBRN_CODE)))
	   END AS DEPTHPATH,
	CASE A.DBRN_NM 
	   WHEN 
	   		B.HEADQ_NM
	   THEN CONCAT('/H_' , A.DBRN_NM)
	   ELSE
	     IF(A.SUBHQ_CODE IS NULL, CONCAT('/H_' , B.HEADQ_NM  , '/S_' , A.DBRN_NM),
	        IF(A.DBRN_NM=C.HEADQ_NM , CONCAT('/' , B.HEADQ_NM , '/' , C.HEADQ_NM), CONCAT('/' , B.HEADQ_NM , '/' , C.HEADQ_NM , '/' , A.DBRN_CODE)))
	   END AS PATHNAME
FROM 
	th_dept A,
	th_bu_dept B,
	th_bu_dept C
WHERE 
	A.DBRN_CODE =2
	AND STR_TO_DATE(A.CLS_YMD,'%Y%m%d') > NOW()
	AND A.HEADQ_CODE IS NOT NULL
	AND A.HEADQ_CODE = B.HEADQ_CODE 
	AND A.SUBHQ_CODE = C.HEADQ_CODE 
UNION ALL
-- HEADQ_CODE가 존재하지 않는 SUBHQ 조직
SELECT 	
	A.DBRN_CODE AS GOOD_DEPT_CODE,
	A.DBRN_NM ,
	CASE A.DBRN_NM
		WHEN B.HEADQ_NM 
		THEN CONCAT('/S_', B.HEADQ_CODE)
		ELSE CONCAT('/S_', B.HEADQ_CODE, '/', A.DBRN_CODE)
	END DEPTPATH,
	CASE A.DBRN_NM
		WHEN B.HEADQ_NM 
		THEN CONCAT('/S_', B.HEADQ_CODE)
		ELSE CONCAT('/S_', B.HEADQ_CODE, '/', A.DBRN_CODE)
	END PATHNAME
FROM 
	th_dept A,
	th_bu_dept B
WHERE 
	A.DBRN_CODE =2
	AND STR_TO_DATE(A.CLS_YMD,'%Y%m%d') > NOW()
	AND A.HEADQ_CODE IS NULL
	AND A.SUBHQ_CODE IS NOT NULL
	AND A.HEADQ_CODE = B.HEADQ_CODE 
UNION ALL
-- 상위부서가 존재하지 않는 부서
SELECT 
	A.DBRN_CODE AS GOOD_DEPT_CODE,
	A.DBRN_NM ,
	CONCAT('/Z_', A.DBRN_CODE) AS DEPTPATH,
	CONCAT('/' , A.DBRN_NM) AS PATHNAME 
FROM 
	th_dept A
WHERE	 
	A.DBRN_TP_CODE =2
	AND STR_TO_DATE(A.CLS_YMD,'%Y%m%d') > NOW()
	AND A.HEADQ_CODE IS NULL 
	AND A.SUBHQ_CODE IS NULL
	

	
