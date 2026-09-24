#WORKLOAD FUNCTION
    
CREATE OR REPLACE FUNCTION calculate_workload(
    p_duration NUMBER,
    p_rpe NUMBER
)
RETURN NUMBER
IS
BEGIN
    RETURN p_duration * p_rpe;
END;
/

#ACWR Function
CREATE OR REPLACE FUNCTION calculate_acwr(
    p_acute NUMBER,
    p_chronic NUMBER
)
RETURN NUMBER
IS
BEGIN
    IF p_chronic = 0 THEN
        RETURN NULL;
    END IF;

    RETURN ROUND(p_acute / p_chronic, 2);
END;
/
