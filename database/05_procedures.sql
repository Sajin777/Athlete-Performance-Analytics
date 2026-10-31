DROP PROCEDURE IF EXISTS add_training_session;
DROP PROCEDURE IF EXISTS eod_workload_processing;

DELIMITER $$

CREATE PROCEDURE add_training_session(
    IN p_athlete_id INT,
    IN p_session_date DATE,
    IN p_session_type VARCHAR(50),
    IN p_duration DECIMAL(6,2),
    IN p_rpe DECIMAL(3,1),
    IN p_heart_rate INT,
    IN p_notes VARCHAR(500)
)
BEGIN
    INSERT INTO training_session
        (athlete_id, session_date, session_type, duration_min, rpe, avg_heart_rate, notes)
    VALUES
        (p_athlete_id, p_session_date, p_session_type, p_duration, p_rpe, p_heart_rate, p_notes);
END$$

CREATE PROCEDURE eod_workload_processing(IN p_date DATE)
BEGIN
    SELECT CONCAT(
        'Workload processing completed for ',
        DATE_FORMAT(p_date, '%d-%m-%Y')
    ) AS message;
END$$

DELIMITER ;
