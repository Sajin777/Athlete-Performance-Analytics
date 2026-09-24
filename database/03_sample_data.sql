INSERT INTO squad
VALUES (1, 'Elite Squad', 'Athletics', 'Coach Kumar');

INSERT INTO squad
VALUES (2, 'Development Squad', 'Athletics', 'Coach Ravi');


INSERT INTO athlete
VALUES (
    1, 1, 'Arun', 'Kumar',
    DATE '2003-05-10',
    'Sprinter',
    'Male',
    DATE '2025-06-01',
    'ACTIVE'
);

INSERT INTO athlete
VALUES (
    2, 1, 'Rahul', 'Raj',
    DATE '2004-02-15',
    'Sprinter',
    'Male',
    DATE '2025-06-10',
    'ACTIVE'
);

INSERT INTO athlete
VALUES (
    3, 2, 'Priya', 'S',
    DATE '2003-11-20',
    'Runner',
    'Female',
    DATE '2025-07-01',
    'ACTIVE'
);


INSERT INTO metric
VALUES (
    1,
    'Sprint Time',
    'seconds',
    '100 metre sprint time',
    'N'
);

INSERT INTO metric
VALUES (
    2,
    'Distance',
    'metres',
    'Training distance',
    'Y'
);

INSERT INTO metric
VALUES (
    3,
    'Weight Lifted',
    'kg',
    'Maximum weight lifted',
    'Y'
);

INSERT INTO metric
VALUES (
    4,
    'Heart Rate',
    'bpm',
    'Average heart rate',
    'N'
);


INSERT INTO app_user
VALUES (
    1,
    NULL,
    'admin',
    'admin123',
    'ADMIN',
    'ACTIVE'
);

INSERT INTO app_user
VALUES (
    2,
    NULL,
    'coach',
    'coach123',
    'COACH',
    'ACTIVE'
);

INSERT INTO app_user
VALUES (
    3,
    1,
    'arun',
    'arun123',
    'ATHLETE',
    'ACTIVE'
);


INSERT INTO training_session
VALUES (
    1,
    1,
    SYSDATE - 1,
    'Sprint Training',
    60,
    7,
    150,
    'Speed training'
);

INSERT INTO training_session
VALUES (
    2,
    1,
    SYSDATE - 3,
    'Strength Training',
    75,
    8,
    145,
    'Gym session'
);

INSERT INTO training_session
VALUES (
    3,
    2,
    SYSDATE - 2,
    'Sprint Training',
    50,
    6,
    140,
    'Acceleration training'
);

INSERT INTO performance_record
VALUES (1, 1, 1, 11.20);

INSERT INTO performance_record
VALUES (2, 1, 2, 5000);

INSERT INTO performance_record
VALUES (3, 2, 3, 120);

INSERT INTO performance_record
VALUES (4, 3, 1, 11.50);

COMMIT;
