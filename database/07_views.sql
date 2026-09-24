# Athlete Workload View
  
CREATE OR REPLACE VIEW athlete_workload_v AS
SELECT
    a.athlete_id,
    a.first_name || ' ' || a.last_name AS athlete_name,
    ts.session_id,
    ts.session_date,
    ts.session_type,
    ts.duration_min,
    ts.rpe,
    ts.duration_min * ts.rpe AS workload
FROM athlete a
JOIN training_session ts
ON a.athlete_id = ts.athlete_id;

# Athlete Performance View

CREATE OR REPLACE VIEW athlete_performance_v AS
SELECT
    a.athlete_id,
    a.first_name || ' ' || a.last_name AS athlete_name,
    m.metric_name,
    m.unit,
    pr.metric_value,
    ts.session_date
FROM athlete a
JOIN training_session ts
ON a.athlete_id = ts.athlete_id
JOIN performance_record pr
ON ts.session_id = pr.session_id
JOIN metric m
ON pr.metric_id = m.metric_id;
