CREATE TABLE IF NOT EXISTS squad (
    squad_id INT PRIMARY KEY AUTO_INCREMENT,
    squad_name VARCHAR(100) NOT NULL,
    sport VARCHAR(50) NOT NULL,
    coach_name VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS athlete (
    athlete_id INT PRIMARY KEY AUTO_INCREMENT,
    squad_id INT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    dob DATE,
    position VARCHAR(50),
    gender VARCHAR(20),
    join_date DATE,
    status VARCHAR(20),
    CONSTRAINT fk_athlete_squad
        FOREIGN KEY (squad_id) REFERENCES squad(squad_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS app_user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    athlete_id INT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(20),
    CONSTRAINT fk_user_athlete
        FOREIGN KEY (athlete_id) REFERENCES athlete(athlete_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS metric (
    metric_id INT PRIMARY KEY AUTO_INCREMENT,
    metric_name VARCHAR(100) UNIQUE NOT NULL,
    unit VARCHAR(30),
    description VARCHAR(255),
    higher_is_better CHAR(1)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS training_session (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    athlete_id INT NOT NULL,
    session_date DATE NOT NULL,
    session_type VARCHAR(50),
    duration_min DECIMAL(6,2),
    rpe DECIMAL(3,1),
    avg_heart_rate INT,
    notes VARCHAR(500),
    CONSTRAINT fk_session_athlete
        FOREIGN KEY (athlete_id) REFERENCES athlete(athlete_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS performance_record (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    session_id INT NOT NULL,
    metric_id INT NOT NULL,
    metric_value DECIMAL(10,2),
    CONSTRAINT fk_record_session
        FOREIGN KEY (session_id) REFERENCES training_session(session_id),
    CONSTRAINT fk_record_metric
        FOREIGN KEY (metric_id) REFERENCES metric(metric_id),
    CONSTRAINT uq_session_metric UNIQUE (session_id, metric_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS audit_log (
    audit_id INT PRIMARY KEY AUTO_INCREMENT,
    table_name VARCHAR(100),
    record_id INT,
    operation VARCHAR(20),
    old_value VARCHAR(4000),
    new_value VARCHAR(4000),
    changed_by VARCHAR(100),
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;
