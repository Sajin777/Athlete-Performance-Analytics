CREATE TABLE squad (
    squad_id NUMBER PRIMARY KEY,
    squad_name VARCHAR2(100) NOT NULL,
    sport VARCHAR2(50) NOT NULL,
    coach_name VARCHAR2(100)
);

CREATE TABLE athlete (
    athlete_id NUMBER PRIMARY KEY,
    squad_id NUMBER,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50),
    dob DATE,
    position VARCHAR2(50),
    gender VARCHAR2(20),
    join_date DATE,
    status VARCHAR2(20),

    CONSTRAINT fk_athlete_squad
    FOREIGN KEY (squad_id)
    REFERENCES squad(squad_id)
);

CREATE TABLE app_user (
    user_id NUMBER PRIMARY KEY,
    athlete_id NUMBER,
    username VARCHAR2(50) UNIQUE NOT NULL,
    password_hash VARCHAR2(255) NOT NULL,
    role VARCHAR2(20) NOT NULL,
    status VARCHAR2(20),

    CONSTRAINT fk_user_athlete
    FOREIGN KEY (athlete_id)
    REFERENCES athlete(athlete_id)
);

CREATE TABLE metric (
    metric_id NUMBER PRIMARY KEY,
    metric_name VARCHAR2(100) UNIQUE NOT NULL,
    unit VARCHAR2(30),
    description VARCHAR2(255),
    higher_is_better CHAR(1)
);

CREATE TABLE training_session (
    session_id NUMBER PRIMARY KEY,
    athlete_id NUMBER NOT NULL,
    session_date DATE NOT NULL,
    session_type VARCHAR2(50),
    duration_min NUMBER(6,2),
    rpe NUMBER(3,1),
    avg_heart_rate NUMBER(4),
    notes VARCHAR2(500),

    CONSTRAINT fk_session_athlete
    FOREIGN KEY (athlete_id)
    REFERENCES athlete(athlete_id)
);

CREATE TABLE performance_record (
    record_id NUMBER PRIMARY KEY,
    session_id NUMBER NOT NULL,
    metric_id NUMBER NOT NULL,
    metric_value NUMBER(10,2),

    CONSTRAINT fk_record_session
    FOREIGN KEY (session_id)
    REFERENCES training_session(session_id),

    CONSTRAINT fk_record_metric
    FOREIGN KEY (metric_id)
    REFERENCES metric(metric_id),

    CONSTRAINT uq_session_metric
    UNIQUE(session_id, metric_id)
);

CREATE TABLE audit_log (
    audit_id NUMBER PRIMARY KEY,
    table_name VARCHAR2(100),
    record_id NUMBER,
    operation VARCHAR2(20),
    old_value VARCHAR2(4000),
    new_value VARCHAR2(4000),
    changed_by VARCHAR2(100),
    changed_at TIMESTAMP
);

CREATE SEQUENCE squad_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE athlete_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE metric_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE session_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE performance_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE audit_seq START WITH 1 INCREMENT BY 1;
