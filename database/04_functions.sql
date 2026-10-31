DROP FUNCTION IF EXISTS calculate_workload;
DROP FUNCTION IF EXISTS calculate_acwr;

DELIMITER $$

CREATE FUNCTION calculate_workload(
    p_duration DECIMAL(10,2),
    p_rpe DECIMAL(4,2)
)
RETURNS DECIMAL(12,2)
DETERMINISTIC
NO SQL
BEGIN
    RETURN p_duration * p_rpe;
END$$

CREATE FUNCTION calculate_acwr(
    p_acute DECIMAL(12,2),
    p_chronic DECIMAL(12,2)
)
RETURNS DECIMAL(12,2)
DETERMINISTIC
NO SQL
BEGIN
    IF p_chronic IS NULL OR p_chronic = 0 THEN
        RETURN NULL;
    END IF;

    RETURN ROUND(p_acute / p_chronic, 2);
END$$

DELIMITER ;
