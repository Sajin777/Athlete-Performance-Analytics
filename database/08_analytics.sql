# Total Workload
  
SELECT
    athlete_id,
    athlete_name,
    SUM(workload) AS total_workload
FROM athlete_workload_v
GROUP BY athlete_id, athlete_name;

# Athlete Average Workload

SELECT
    athlete_id,
    athlete_name,
    ROUND(AVG(workload), 2) AS average_workload
FROM athlete_workload_v
GROUP BY athlete_id, athlete_name;

# Personal Best

SELECT
    athlete_id,
    athlete_name,
    metric_name,
    MIN(metric_value) AS personal_best
FROM athlete_performance_v
WHERE metric_name = 'Sprint Time'
GROUP BY
    athlete_id,
    athlete_name,
    metric_name;

# Squad Average

SELECT
    s.squad_name,
    m.metric_name,
    ROUND(AVG(pr.metric_value), 2) AS squad_average
FROM squad s
JOIN athlete a
ON s.squad_id = a.squad_id
JOIN training_session ts
ON a.athlete_id = ts.athlete_id
JOIN performance_record pr
ON ts.session_id = pr.session_id
JOIN metric m
ON pr.metric_id = m.metric_id
GROUP BY
    s.squad_name,
    m.metric_name;

# Athlete Ranking

SELECT
    athlete_id,
    athlete_name,
    metric_name,
    metric_value,
    RANK() OVER (
        PARTITION BY metric_name
        ORDER BY metric_value
    ) AS athlete_rank
FROM athlete_performance_v
WHERE metric_name = 'Sprint Time';
