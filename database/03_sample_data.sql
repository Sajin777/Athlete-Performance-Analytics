INSERT INTO squad (squad_id, squad_name, sport, coach_name) VALUES
(1, 'Elite Squad', 'Athletics', 'Coach Kumar'),
(2, 'Development Squad', 'Athletics', 'Coach Ravi');

INSERT INTO athlete
(athlete_id, squad_id, first_name, last_name, dob, position, gender, join_date, status)
VALUES
(1, 1, 'Arun', 'Kumar', '2003-05-10', 'Sprinter', 'Male', '2025-06-01', 'ACTIVE'),
(2, 1, 'Rahul', 'Raj', '2004-02-15', 'Sprinter', 'Male', '2025-06-10', 'ACTIVE'),
(3, 2, 'Priya', 'S', '2003-11-20', 'Runner', 'Female', '2025-07-01', 'ACTIVE');

INSERT INTO metric (metric_id, metric_name, unit, description, higher_is_better) VALUES
(1, 'Sprint Time', 'seconds', '100 metre sprint time', 'N'),
(2, 'Distance', 'metres', 'Training distance', 'Y'),
(3, 'Weight Lifted', 'kg', 'Maximum weight lifted', 'Y'),
(4, 'Heart Rate', 'bpm', 'Average heart rate', 'N');

INSERT INTO app_user (user_id, athlete_id, username, password_hash, role, status) VALUES
(1, NULL, 'admin', 'admin123', 'ADMIN', 'ACTIVE'),
(2, NULL, 'coach', 'coach123', 'COACH', 'ACTIVE'),
(3, 1, 'arun', 'arun123', 'ATHLETE', 'ACTIVE');

INSERT INTO training_session
(session_id, athlete_id, session_date, session_type, duration_min, rpe, avg_heart_rate, notes)
VALUES
(1, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'Sprint Training', 60, 7, 150, 'Speed training'),
(2, 1, DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'Strength Training', 75, 8, 145, 'Gym session'),
(3, 2, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'Sprint Training', 50, 6, 140, 'Acceleration training');

INSERT INTO performance_record (record_id, session_id, metric_id, metric_value) VALUES
(1, 1, 1, 11.20),
(2, 1, 2, 5000),
(3, 2, 3, 120),
(4, 3, 1, 11.50);

-- Keep AUTO_INCREMENT counters above explicitly inserted IDs.
ALTER TABLE squad AUTO_INCREMENT = 3;
ALTER TABLE athlete AUTO_INCREMENT = 4;
ALTER TABLE app_user AUTO_INCREMENT = 4;
ALTER TABLE metric AUTO_INCREMENT = 5;
ALTER TABLE training_session AUTO_INCREMENT = 4;
ALTER TABLE performance_record AUTO_INCREMENT = 5;
