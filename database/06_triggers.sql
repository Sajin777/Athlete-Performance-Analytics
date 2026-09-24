CREATE OR REPLACE TRIGGER trg_session_audit
AFTER INSERT OR UPDATE OR DELETE
ON training_session
FOR EACH ROW
BEGIN

    IF INSERTING THEN

        INSERT INTO audit_log
        VALUES (
            audit_seq.NEXTVAL,
            'TRAINING_SESSION',
            :NEW.session_id,
            'INSERT',
            NULL,
            'Athlete ID=' || :NEW.athlete_id,
            USER,
            SYSTIMESTAMP
        );

    ELSIF UPDATING THEN

        INSERT INTO audit_log
        VALUES (
            audit_seq.NEXTVAL,
            'TRAINING_SESSION',
            :NEW.session_id,
            'UPDATE',
            'Duration=' || :OLD.duration_min,
            'Duration=' || :NEW.duration_min,
            USER,
            SYSTIMESTAMP
        );

    ELSIF DELETING THEN

        INSERT INTO audit_log
        VALUES (
            audit_seq.NEXTVAL,
            'TRAINING_SESSION',
            :OLD.session_id,
            'DELETE',
            'Athlete ID=' || :OLD.athlete_id,
            NULL,
            USER,
            SYSTIMESTAMP
        );

    END IF;

END;
/
