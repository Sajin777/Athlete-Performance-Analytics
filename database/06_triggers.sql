DROP TRIGGER IF EXISTS trg_session_audit_insert;
DROP TRIGGER IF EXISTS trg_session_audit_update;
DROP TRIGGER IF EXISTS trg_session_audit_delete;

DELIMITER $$

CREATE TRIGGER trg_session_audit_insert
AFTER INSERT ON training_session
FOR EACH ROW
BEGIN
    INSERT INTO audit_log
        (table_name, record_id, operation, old_value, new_value, changed_by, changed_at)
    VALUES
        ('TRAINING_SESSION', NEW.session_id, 'INSERT', NULL,
         CONCAT('Athlete ID=', NEW.athlete_id), CURRENT_USER(), CURRENT_TIMESTAMP);
END$$

CREATE TRIGGER trg_session_audit_update
AFTER UPDATE ON training_session
FOR EACH ROW
BEGIN
    INSERT INTO audit_log
        (table_name, record_id, operation, old_value, new_value, changed_by, changed_at)
    VALUES
        ('TRAINING_SESSION', NEW.session_id, 'UPDATE',
         CONCAT('Duration=', OLD.duration_min),
         CONCAT('Duration=', NEW.duration_min), CURRENT_USER(), CURRENT_TIMESTAMP);
END$$

CREATE TRIGGER trg_session_audit_delete
AFTER DELETE ON training_session
FOR EACH ROW
BEGIN
    INSERT INTO audit_log
        (table_name, record_id, operation, old_value, new_value, changed_by, changed_at)
    VALUES
        ('TRAINING_SESSION', OLD.session_id, 'DELETE',
         CONCAT('Athlete ID=', OLD.athlete_id), NULL, CURRENT_USER(), CURRENT_TIMESTAMP);
END$$

DELIMITER ;
