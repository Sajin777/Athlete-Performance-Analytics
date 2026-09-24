# Atomicity

SAVEPOINT before_insert;

INSERT INTO training_session
VALUES (
    10,
    1,
    SYSDATE,
    'Test Session',
    60,
    7,
    145,
    'ACID demonstration'
);

ROLLBACK TO before_insert;

# Commit

INSERT INTO training_session
VALUES (
    11,
    1,
    SYSDATE,
    'Test Session',
    60,
    7,
    145,
    'Commit demonstration'
);

COMMIT;

# Rollback

INSERT INTO training_session
VALUES (
    12,
    1,
    SYSDATE,
    'Test Session',
    60,
    7,
    145,
    'Rollback demonstration'
);

ROLLBACK;
