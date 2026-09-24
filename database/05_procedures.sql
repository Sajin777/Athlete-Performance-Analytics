CREATE OR REPLACE PROCEDURE add_training_session(
    p_athlete_id NUMBER,
    p_session_date DATE,
    p_session_type VARCHAR2,
    p_duration NUMBER,
    p_rpe NUMBER,
    p_heart_rate NUMBER,
    p_notes VARCHAR2
)
IS
BEGIN

    INSERT INTO training_session
    VALUES (
        session_seq.NEXTVAL,
        p_athlete_id,
        p_session_date,
        p_session_type,
        p_duration,
        p_rpe,
        p_heart_rate,
        p_notes
    );

    COMMIT;

END;
/

CREATE OR REPLACE PROCEDURE eod_workload_processing(
    p_date DATE
)
IS
BEGIN

    DBMS_OUTPUT.PUT_LINE(
        'Workload processing completed for '
        || TO_CHAR(p_date, 'DD-MM-YYYY')
    );

END;
/
