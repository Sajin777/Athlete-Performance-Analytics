ALTER TABLE app_user
ADD CONSTRAINT chk_user_role
CHECK (role IN ('ADMIN', 'COACH', 'ATHLETE'));

ALTER TABLE app_user
ADD CONSTRAINT chk_user_status
CHECK (status IN ('ACTIVE', 'INACTIVE'));

ALTER TABLE athlete
ADD CONSTRAINT chk_athlete_status
CHECK (status IN ('ACTIVE', 'INACTIVE'));

ALTER TABLE training_session
ADD CONSTRAINT chk_duration
CHECK (duration_min > 0);

ALTER TABLE training_session
ADD CONSTRAINT chk_rpe
CHECK (rpe BETWEEN 1 AND 10);

ALTER TABLE metric
ADD CONSTRAINT chk_higher_better
CHECK (higher_is_better IN ('Y', 'N'));
