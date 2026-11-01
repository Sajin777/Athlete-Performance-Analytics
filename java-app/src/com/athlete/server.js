const express = require("express");
const mysql = require("mysql2/promise");
const dotenv = require("dotenv");
const path = require("path");

dotenv.config({
    path: require("path").join(__dirname, ".env")
});

const app = express();

app.use(express.json());

// Serve the existing HTML dashboard.
app.use(express.static(path.join(__dirname, "public")));
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "public", "athlete1.html"));
});

// Create a connection pool for MySQL.
const pool = mysql.createPool({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    waitForConnections: true,
    connectionLimit: 10
});

// Test the database connection.
app.get("/api/health", async (req, res) => {
    try {
        const [rows] = await pool.query(
            "SELECT DATABASE() AS database_name"
        );

        res.json({
            success: true,
            message: "MySQL connected successfully",
            database: rows[0].database_name
        });
    } catch (error) {
        console.error("Database error:", error.message);

        res.status(500).json({
            success: false,
            message: "Database connection failed"
        });
    }
});

// Retrieve athlete records.
app.get("/api/athletes", async (req, res) => {
    try {
        const [rows] = await pool.execute(
            `SELECT
                id,
                name,
                age,
                event,
                category,
                coach_id
             FROM athletes
             ORDER BY id`
        );

        res.json(rows);
    } catch (error) {
        console.error("Athlete retrieval error:", error.message);

        res.status(500).json({
            message: "Could not retrieve athletes"
        });
    }
});

// Start the backend server.
const PORT = process.env.PORT || 3000;

app.listen(PORT, "0.0.0.0", () => {
    console.log(
        `Server running on port ${PORT}`
    );
});