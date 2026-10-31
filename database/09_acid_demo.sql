START TRANSACTION;
SAVEPOINT before_insert;

INSERT INTO training_session
    (athlete_id, session_date, session_type, duration_min, rpe, avg_heart_rate, notes)
VALUES
    (1, CURDATE(), 'Test Session', 60, 7, 145, 'ACID demonstration');

ROLLBACK TO SAVEPOINT before_insert;
ROLLBACK;

START TRANSACTION;
INSERT INTO training_session
    (athlete_id, session_date, session_type, duration_min, rpe, avg_heart_rate, notes)
VALUES
    (1, CURDATE(), 'Test Session', 60, 7, 145, 'Commit demonstration');
COMMIT;

START TRANSACTION;
INSERT INTO training_session
    (athlete_id, session_date, session_type, duration_min, rpe, avg_heart_rate, notes)
VALUES
    (1, CURDATE(), 'Test Session', 60, 7, 145, 'Rollback demonstration');
ROLLBACK;
