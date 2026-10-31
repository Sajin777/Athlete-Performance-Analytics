SELECT athlete_id, athlete_name, SUM(workload) AS total_workload
FROM athlete_workload_v
GROUP BY athlete_id, athlete_name;

SELECT athlete_id, athlete_name, ROUND(AVG(workload), 2) AS average_workload
FROM athlete_workload_v
GROUP BY athlete_id, athlete_name;

SELECT athlete_id, athlete_name, metric_name, MIN(metric_value) AS personal_best
FROM athlete_performance_v
WHERE metric_name = 'Sprint Time'
GROUP BY athlete_id, athlete_name, metric_name;

SELECT s.squad_name, m.metric_name, ROUND(AVG(pr.metric_value), 2) AS squad_average
FROM squad AS s
JOIN athlete AS a ON s.squad_id = a.squad_id
JOIN training_session AS ts ON a.athlete_id = ts.athlete_id
JOIN performance_record AS pr ON ts.session_id = pr.session_id
JOIN metric AS m ON pr.metric_id = m.metric_id
GROUP BY s.squad_name, m.metric_name;

SELECT
    athlete_id,
    athlete_name,
    metric_name,
    metric_value,
    RANK() OVER (
        PARTITION BY metric_name
        ORDER BY metric_value ASC
    ) AS athlete_rank
FROM athlete_performance_v
WHERE metric_name = 'Sprint Time';
