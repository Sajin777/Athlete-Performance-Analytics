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
