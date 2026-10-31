CREATE OR REPLACE VIEW athlete_workload_v AS
SELECT
    a.athlete_id,
    CONCAT(a.first_name, ' ', COALESCE(a.last_name, '')) AS athlete_name,
    ts.session_id,
    ts.session_date,
    ts.session_type,
    ts.duration_min,
    ts.rpe,
    ts.duration_min * ts.rpe AS workload
FROM athlete AS a
JOIN training_session AS ts
    ON a.athlete_id = ts.athlete_id;

CREATE OR REPLACE VIEW athlete_performance_v AS
SELECT
    a.athlete_id,
    CONCAT(a.first_name, ' ', COALESCE(a.last_name, '')) AS athlete_name,
    m.metric_name,
    m.unit,
    pr.metric_value,
    ts.session_date
FROM athlete AS a
JOIN training_session AS ts
    ON a.athlete_id = ts.athlete_id
JOIN performance_record AS pr
    ON ts.session_id = pr.session_id
JOIN metric AS m
    ON pr.metric_id = m.metric_id;
