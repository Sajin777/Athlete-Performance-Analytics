import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/*
 * ATHLETE PERFORMANCE ANALYTICS DASHBOARD

 * Concepts used:
 * 1. Classes and Objects
 * 2. Encapsulation
 * 3. Inheritance
 * 4. Polymorphism
 * 5. Interface
 * 6. ArrayList
 * 7. Exception Handling
 * 8. LocalDate
 * 9. Collections
 * 10. Statistical calculations
 */
// ============================================================
// INTERFACE
// ============================================================

interface AnalyticsService {
    double calculateWorkload(TrainingSession session);
    double calculateACWR(int athleteId, LocalDate date);
    void showSquadComparison(int athleteId);
    void showPersonalBests(int athleteId);
}

// ============================================================
// ABSTRACT USER CLASS
// ============================================================

abstract class User {

    protected int userId;
    protected String username;
    protected String password;

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }

    public abstract void displayRole();
}

// ============================================================
// ADMIN CLASS
// ============================================================

class Admin extends User {

    public Admin(int userId, String username, String password) {
        super(userId, username, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Logged in as ADMIN");
    }
}

// ============================================================
// COACH CLASS
// ============================================================

class Coach extends User {

    public Coach(int userId, String username, String password) {
        super(userId, username, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Logged in as COACH / TRAINER");
    }
}

// ============================================================
// ATHLETE CLASS
// ============================================================

class AthleteUser extends User {

    private int athleteId;

    public AthleteUser(
            int userId,
            String username,
            String password,
            int athleteId) {

        super(userId, username, password);
        this.athleteId = athleteId;
    }

    public int getAthleteId() {
        return athleteId;
    }

    @Override
    public void displayRole() {
        System.out.println("Logged in as ATHLETE");
    }
}

// ============================================================
// ATHLETE CLASS
// ============================================================

class Athlete {

    private int athleteId;
    private String name;
    private int age;
    private String position;
    private String squad;

    public Athlete(
            int athleteId,
            String name,
            int age,
            String position,
            String squad) {

        this.athleteId = athleteId;
        this.name = name;
        this.age = age;
        this.position = position;
        this.squad = squad;
    }

    public int getAthleteId() {
        return athleteId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getSquad() {
        return squad;
    }

    public void display() {

        System.out.printf(
                "%-5d %-20s %-5d %-15s %-15s%n",
                athleteId,
                name,
                age,
                position,
                squad
        );
    }
}

// ============================================================
// TRAINING SESSION CLASS
// ============================================================

class TrainingSession {

    private int sessionId;
    private int athleteId;
    private LocalDate date;
    private String sessionType;

    private double durationMinutes;
    private double rpe;

    private double sprintTime;
    private double distanceKm;
    private double weightLifted;
    private double heartRate;

    public TrainingSession(
            int sessionId,
            int athleteId,
            LocalDate date,
            String sessionType,
            double durationMinutes,
            double rpe,
            double sprintTime,
            double distanceKm,
            double weightLifted,
            double heartRate) {

        this.sessionId = sessionId;
        this.athleteId = athleteId;
        this.date = date;
        this.sessionType = sessionType;
        this.durationMinutes = durationMinutes;
        this.rpe = rpe;
        this.sprintTime = sprintTime;
        this.distanceKm = distanceKm;
        this.weightLifted = weightLifted;
        this.heartRate = heartRate;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getAthleteId() {
        return athleteId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSessionType() {
        return sessionType;
    }

    public double getDurationMinutes() {
        return durationMinutes;
    }

    public double getRpe() {
        return rpe;
    }

    public double getSprintTime() {
        return sprintTime;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public double getWeightLifted() {
        return weightLifted;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public double getWorkload() {
        return durationMinutes * rpe;
    }

    public void display() {

        System.out.printf(
                "%-4d %-12s %-12s %-10.1f %-6.1f %-10.1f %-10.2f %-10.1f %-10.1f%n",
                sessionId,
                date,
                sessionType,
                durationMinutes,
                rpe,
                sprintTime,
                distanceKm,
                weightLifted,
                heartRate
        );
    }
}

// ============================================================
// PERFORMANCE ANALYTICS CLASS
// ============================================================

class PerformanceAnalytics implements AnalyticsService {

    private ArrayList<Athlete> athletes;
    private ArrayList<TrainingSession> sessions;

    public PerformanceAnalytics(
            ArrayList<Athlete> athletes,
            ArrayList<TrainingSession> sessions) {

        this.athletes = athletes;
        this.sessions = sessions;
    }

    // --------------------------------------------------------
    // Workload
    // --------------------------------------------------------

    @Override
    public double calculateWorkload(TrainingSession session) {

        return session.getDurationMinutes()
                * session.getRpe();
    }

    // --------------------------------------------------------
    // ACWR
    // --------------------------------------------------------

    @Override
    public double calculateACWR(
            int athleteId,
            LocalDate date) {

        double acuteWorkload = 0;

        double chronicWorkload = 0;

        /*
         * Acute workload:
         * Last 7 days
         */
        for (TrainingSession session : sessions) {

            if (session.getAthleteId() == athleteId) {

                long days = ChronoUnit.DAYS.between(
                        session.getDate(),
                        date
                );

                if (days >= 0 && days <= 6) {

                    acuteWorkload +=
                            calculateWorkload(session);
                }
            }
        }

        /*
         * Chronic workload:
         * Previous 28 days
         *
         * The previous 28-day workload is
         * divided by 4 to obtain average
         * weekly workload.
         */

        for (TrainingSession session : sessions) {

            if (session.getAthleteId() == athleteId) {

                long days = ChronoUnit.DAYS.between(
                        session.getDate(),
                        date
                );

                if (days >= 7 && days <= 34) {

                    chronicWorkload +=
                            calculateWorkload(session);
                }
            }
        }

        double chronicAverage =
                chronicWorkload / 4.0;

        if (chronicAverage == 0) {
            return 0;
        }

        return acuteWorkload / chronicAverage;
    }

    // --------------------------------------------------------
    // ACWR Status
    // --------------------------------------------------------

    public String getACWRStatus(double acwr) {

        if (acwr == 0) {
            return "INSUFFICIENT DATA";
        }

        if (acwr > 1.5) {
            return "HIGH WORKLOAD";
        }

        if (acwr >= 0.8) {
            return "NORMAL";
        }

        return "LOW WORKLOAD";
    }

    // --------------------------------------------------------
    // Athlete Performance
    // --------------------------------------------------------

    public void showAthletePerformance(
            int athleteId) {

        Athlete athlete = findAthlete(athleteId);

        if (athlete == null) {

            System.out.println(
                    "Athlete not found."
            );

            return;
        }

        System.out.println();
        System.out.println(
                "=============================================="
        );

        System.out.println(
                "ATHLETE PERFORMANCE"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Name     : " + athlete.getName()
        );

        System.out.println(
                "Position : " + athlete.getPosition()
        );

        System.out.println(
                "Squad    : " + athlete.getSquad()
        );

        System.out.println();

        boolean found = false;

        for (TrainingSession session : sessions) {

            if (session.getAthleteId() == athleteId) {

                found = true;

                System.out.println(
                        "Date       : " + session.getDate()
                );

                System.out.println(
                        "Session    : " + session.getSessionType()
                );

                System.out.println(
                        "Duration   : "
                                + session.getDurationMinutes()
                                + " min"
                );

                System.out.println(
                        "RPE        : "
                                + session.getRpe()
                );

                System.out.printf(
                        "Workload   : %.2f AU%n",
                        calculateWorkload(session)
                );

                System.out.printf(
                        "Sprint     : %.2f sec%n",
                        session.getSprintTime()
                );

                System.out.printf(
                        "Distance   : %.2f km%n",
                        session.getDistanceKm()
                );

                System.out.printf(
                        "Weight     : %.2f kg%n",
                        session.getWeightLifted()
                );

                System.out.printf(
                        "Heart Rate : %.2f bpm%n",
                        session.getHeartRate()
                );

                System.out.println("----------------------------------------------");
            }
        }

        if (!found) {

            System.out.println(
                    "No training records found."
            );
        }
    }

    // --------------------------------------------------------
    // ACWR Dashboard
    // --------------------------------------------------------

    public void showACWR(
            int athleteId,
            LocalDate date) {

        Athlete athlete = findAthlete(athleteId);

        if (athlete == null) {

            System.out.println(
                    "Athlete not found."
            );

            return;
        }

        double acute = calculateAcuteWorkload(
                athleteId,
                date
        );

        double chronic = calculateChronicWorkload(
                athleteId,
                date
        );

        double chronicAverage =
                chronic / 4.0;

        double acwr =
                calculateACWR(athleteId, date);

        System.out.println();
        System.out.println(
                "=============================================="
        );

        System.out.println(
                "ACWR ANALYSIS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Athlete : " + athlete.getName()
        );

        System.out.printf(
                "7-Day Acute Workload      : %.2f AU%n",
                acute
        );

        System.out.printf(
                "28-Day Workload           : %.2f AU%n",
                chronic
        );

        System.out.printf(
                "28-Day Average Weekly     : %.2f AU%n",
                chronicAverage
        );

        if (acwr == 0) {

            System.out.println(
                    "ACWR                      : Insufficient Data"
            );

        } else {

            System.out.printf(
                    "ACWR                      : %.2f%n",
                    acwr
            );

            System.out.println(
                    "Status                    : "
                            + getACWRStatus(acwr)
            );
        }

        System.out.println(
                "----------------------------------------------"
        );

        System.out.println(
                "Note: ACWR is used here as a workload"
        );

        System.out.println(
                "monitoring indicator, not as a diagnosis."
        );
    }

    // --------------------------------------------------------
    // Acute Workload
    // --------------------------------------------------------

    private double calculateAcuteWorkload(
            int athleteId,
            LocalDate date) {

        double total = 0;

        for (TrainingSession session : sessions) {

            if (session.getAthleteId() == athleteId) {

                long days = ChronoUnit.DAYS.between(
                        session.getDate(),
                        date
                );

                if (days >= 0 && days <= 6) {

                    total +=
                            calculateWorkload(session);
                }
            }
        }

        return total;
    }

    // --------------------------------------------------------
    // Chronic Workload
    // --------------------------------------------------------

    private double calculateChronicWorkload(
            int athleteId,
            LocalDate date) {

        double total = 0;

        for (TrainingSession session : sessions) {

            if (session.getAthleteId() == athleteId) {

                long days = ChronoUnit.DAYS.between(
                        session.getDate(),
                        date
                );

                if (days >= 7 && days <= 34) {

                    total +=
                            calculateWorkload(session);
                }
            }
        }

        return total;
    }

    // --------------------------------------------------------
    // Squad Comparison
    // --------------------------------------------------------

    @Override
    public void showSquadComparison(
            int athleteId) {

        Athlete target = findAthlete(athleteId);

        if (target == null) {

            System.out.println(
                    "Athlete not found."
            );

            return;
        }

        String squad =
                target.getSquad();

        ArrayList<Double> workloads =
                new ArrayList<>();

        double targetWorkload = 0;

        LocalDate latestDate = LocalDate.MIN;

        /*
         * Find latest session date
         */

        for (TrainingSession session : sessions) {

            if (session.getAthleteId() == athleteId) {

                if (session.getDate()
                        .isAfter(latestDate)) {

                    latestDate =
                            session.getDate();
                }
            }
        }

        /*
         * Calculate total workload
         * for every athlete in same squad.
         */

        for (Athlete athlete : athletes) {

            if (athlete.getSquad()
                    .equalsIgnoreCase(squad)) {

                double total = 0;

                for (TrainingSession session :
                        sessions) {

                    if (session.getAthleteId()
                            == athlete.getAthleteId()) {

                        total +=
                                calculateWorkload(session);
                    }
                }

                workloads.add(total);

                if (athlete.getAthleteId()
                        == athleteId) {

                    targetWorkload = total;
                }
            }
        }

        if (workloads.isEmpty()) {

            System.out.println(
                    "No squad data available."
            );

            return;
        }

        double average = 0;

        for (double value : workloads) {
            average += value;
        }

        average /= workloads.size();

        int lowerOrEqual = 0;

        for (double value : workloads) {

            if (value <= targetWorkload) {
                lowerOrEqual++;
            }
        }

        double percentile =
                ((double) lowerOrEqual
                        / workloads.size()) * 100;

        System.out.println();

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "SQUAD COMPARISON"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Athlete : " + target.getName()
        );

        System.out.println(
                "Squad   : " + squad
        );

        System.out.printf(
                "Athlete Total Workload : %.2f AU%n",
                targetWorkload
        );

        System.out.printf(
                "Squad Average Workload : %.2f AU%n",
                average
        );

        System.out.printf(
                "Squad Percentile       : %.2f%%%n",
                percentile
        );
    }

    // --------------------------------------------------------
    // Personal Bests
    // --------------------------------------------------------

    @Override
    public void showPersonalBests(
            int athleteId) {

        Athlete athlete = findAthlete(athleteId);

        if (athlete == null) {

            System.out.println(
                    "Athlete not found."
            );

            return;
        }

        double bestSprint =
                Double.MAX_VALUE;

        double bestDistance = 0;

        double bestWeight = 0;

        double bestHeartRate =
                Double.MAX_VALUE;

        boolean found = false;

        for (TrainingSession session :
                sessions) {

            if (session.getAthleteId()
                    == athleteId) {

                found = true;

                if (session.getSprintTime()
                        < bestSprint) {

                    bestSprint =
                            session.getSprintTime();
                }

                if (session.getDistanceKm()
                        > bestDistance) {

                    bestDistance =
                            session.getDistanceKm();
                }

                if (session.getWeightLifted()
                        > bestWeight) {

                    bestWeight =
                            session.getWeightLifted();
                }

                if (session.getHeartRate()
                        < bestHeartRate) {

                    bestHeartRate =
                            session.getHeartRate();
                }
            }
        }

        System.out.println();

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "PERSONAL BESTS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Athlete : " + athlete.getName()
        );

        if (!found) {

            System.out.println(
                    "No performance data available."
            );

            return;
        }

        System.out.printf(
                "Best Sprint Time : %.2f sec%n",
                bestSprint
        );

        System.out.printf(
                "Longest Distance : %.2f km%n",
                bestDistance
        );

        System.out.printf(
                "Highest Weight   : %.2f kg%n",
                bestWeight
        );

        System.out.printf(
                "Lowest Heart Rate: %.2f bpm%n",
                bestHeartRate
        );
    }

    // --------------------------------------------------------
    // Find Athlete
    // --------------------------------------------------------

    private Athlete findAthlete(
            int athleteId) {

        for (Athlete athlete : athletes) {

            if (athlete.getAthleteId()
                    == athleteId) {

                return athlete;
            }
        }

        return null;
    }
}

// ============================================================
// MAIN APPLICATION
// ============================================================

public class AthletePerformanceAnalytics {

    static Scanner scanner =
            new Scanner(System.in);

    static ArrayList<Athlete> athletes =
            new ArrayList<>();

    static ArrayList<TrainingSession> sessions =
            new ArrayList<>();

    static ArrayList<User> users =
            new ArrayList<>();

    static PerformanceAnalytics analytics;

    static int nextSessionId = 1;

    // ========================================================
    // MAIN
    // ========================================================

    public static void main(String[] args) {

        loadSampleData();

        analytics =
                new PerformanceAnalytics(
                        athletes,
                        sessions
                );

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "   ATHLETE PERFORMANCE ANALYTICS DASHBOARD"
        );

        System.out.println(
                "================================================"
        );

        login();
    }

    // ========================================================
    // SAMPLE DATA
    // ========================================================

    static void loadSampleData() {

        /*
         * Athletes
         */

        athletes.add(
                new Athlete(
                        1,
                        "Arun Kumar",
                        21,
                        "Forward",
                        "Senior Squad"
                )
        );

        athletes.add(
                new Athlete(
                        2,
                        "Rahul Kumar",
                        22,
                        "Midfielder",
                        "Senior Squad"
                )
        );

        athletes.add(
                new Athlete(
                        3,
                        "Akash Raj",
                        20,
                        "Defender",
                        "Senior Squad"
                )
        );

        athletes.add(
                new Athlete(
                        4,
                        "Suresh B",
                        21,
                        "Goalkeeper",
                        "Senior Squad"
                )
        );

        athletes.add(
                new Athlete(
                        5,
                        "Vijay Kumar",
                        19,
                        "Forward",
                        "Junior Squad"
                )
        );

        /*
         * Users
         */

        users.add(
                new Admin(
                        1,
                        "admin",
                        "admin123"
                )
        );

        users.add(
                new Coach(
                        2,
                        "coach",
                        "coach123"
                )
        );

        users.add(
                new AthleteUser(
                        3,
                        "arun",
                        "arun123",
                        1
                )
        );

        /*
         * Training sessions
         */

        LocalDate today =
                LocalDate.now();

        addSampleSession(
                1,
                today.minusDays(1),
                60,
                7,
                11.60,
                8.2,
                100,
                155
        );

        addSampleSession(
                1,
                today.minusDays(3),
                70,
                8,
                11.55,
                9.1,
                105,
                160
        );

        addSampleSession(
                1,
                today.minusDays(5),
                55,
                6,
                11.70,
                7.5,
                95,
                150
        );

        addSampleSession(
                1,
                today.minusDays(8),
                60,
                7,
                11.65,
                8.0,
                105,
                153
        );

        addSampleSession(
                1,
                today.minusDays(12),
                65,
                7,
                11.58,
                8.5,
                110,
                156
        );

        addSampleSession(
                1,
                today.minusDays(18),
                50,
                6,
                11.80,
                7.0,
                90,
                148
        );

        addSampleSession(
                1,
                today.minusDays(25),
                55,
                6,
                11.75,
                7.5,
                95,
                150
        );

        /*
         * Rahul
         */

        addSampleSession(
                2,
                today.minusDays(1),
                90,
                9,
                11.30,
                10.5,
                120,
                170
        );

        addSampleSession(
                2,
                today.minusDays(3),
                80,
                8,
                11.40,
                9.8,
                115,
                168
        );

        addSampleSession(
                2,
                today.minusDays(5),
                85,
                9,
                11.35,
                10.0,
                118,
                172
        );

        addSampleSession(
                2,
                today.minusDays(10),
                60,
                6,
                11.50,
                8.0,
                100,
                155
        );

        /*
         * Akash
         */

        addSampleSession(
                3,
                today.minusDays(2),
                50,
                6,
                12.10,
                7.0,
                90,
                145
        );

        addSampleSession(
                3,
                today.minusDays(7),
                55,
                6,
                12.00,
                7.5,
                95,
                148
        );

        /*
         * Suresh
         */

        addSampleSession(
                4,
                today.minusDays(2),
                65,
                7,
                11.90,
                8.0,
                100,
                150
        );

        addSampleSession(
                4,
                today.minusDays(9),
                60,
                7,
                11.95,
                7.8,
                95,
                151
        );
    }

    // ========================================================
    // ADD SAMPLE SESSION
    // ========================================================

    static void addSampleSession(
            int athleteId,
            LocalDate date,
            double duration,
            double rpe,
            double sprint,
            double distance,
            double weight,
            double heartRate) {

        sessions.add(
                new TrainingSession(
                        nextSessionId++,
                        athleteId,
                        date,
                        "TRAINING",
                        duration,
                        rpe,
                        sprint,
                        distance,
                        weight,
                        heartRate
                )
        );
    }

    // ========================================================
    // LOGIN
    // ========================================================

    static void login() {

        System.out.println();
        System.out.println(
                "Demo Login Details:"
        );

        System.out.println(
                "Admin    : admin / admin123"
        );

        System.out.println(
                "Coach    : coach / coach123"
        );

        System.out.println(
                "Athlete  : arun / arun123"
        );

        System.out.println();

        System.out.print(
                "Username: "
        );

        String username =
                scanner.nextLine();

        System.out.print(
                "Password: "
        );

        String password =
                scanner.nextLine();

        for (User user : users) {

            if (user.login(
                    username,
                    password)) {

                System.out.println();

                user.displayRole();

                if (user instanceof Coach) {

                    coachMenu();

                } else if (user instanceof AthleteUser) {

                    AthleteUser athleteUser =
                            (AthleteUser) user;

                    athleteMenu(
                            athleteUser.getAthleteId()
                    );

                } else if (user instanceof Admin) {

                    adminMenu();
                }

                return;
            }
        }

        System.out.println(
                "Invalid username or password."
        );
    }

    // ========================================================
    // ADMIN MENU
    // ========================================================

    static void adminMenu() {

        while (true) {

            System.out.println();
            System.out.println(
                    "============== ADMIN MENU =============="
            );

            System.out.println(
                    "1. View Athletes"
            );

            System.out.println(
                    "2. Add Athlete"
            );

            System.out.println(
                    "3. View Sessions"
            );

            System.out.println(
                    "4. Logout"
            );

            System.out.print(
                    "Enter choice: "
            );

            int choice =
                    readInt();

            switch (choice) {

                case 1:
                    displayAthletes();
                    break;

                case 2:
                    addAthlete();
                    break;

                case 3:
                    displayAllSessions();
                    break;

                case 4:
                    System.out.println(
                            "Logged out."
                    );
                    return;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // ========================================================
    // COACH MENU
    // ========================================================

    static void coachMenu() {

        while (true) {

            System.out.println();
            System.out.println(
                    "============== COACH MENU =============="
            );

            System.out.println(
                    "1. View Athletes"
            );

            System.out.println(
                    "2. Add Training Session"
            );

            System.out.println(
                    "3. View Athlete Performance"
            );

            System.out.println(
                    "4. ACWR Analysis"
            );

            System.out.println(
                    "5. Squad Comparison"
            );

            System.out.println(
                    "6. Personal Bests"
            );

            System.out.println(
                    "7. View All Sessions"
            );

            System.out.println(
                    "8. Logout"
            );

            System.out.print(
                    "Enter choice: "
            );

            int choice =
                    readInt();

            switch (choice) {

                case 1:
                    displayAthletes();
                    break;

                case 2:
                    addTrainingSession();
                    break;

                case 3:
                    System.out.print(
                            "Enter Athlete ID: "
                    );

                    int athleteId =
                            readInt();

                    analytics.showAthletePerformance(
                            athleteId
                    );

                    break;

                case 4:
                    System.out.print(
                            "Enter Athlete ID: "
                    );

                    athleteId =
                            readInt();

                    analytics.showACWR(
                            athleteId,
                            LocalDate.now()
                    );

                    break;

                case 5:
                    System.out.print(
                            "Enter Athlete ID: "
                    );

                    athleteId =
                            readInt();

                    analytics.showSquadComparison(
                            athleteId
                    );

                    break;

                case 6:
                    System.out.print(
                            "Enter Athlete ID: "
                    );

                    athleteId =
                            readInt();

                    analytics.showPersonalBests(
                            athleteId
                    );

                    break;

                case 7:
                    displayAllSessions();
                    break;

                case 8:
                    System.out.println(
                            "Logged out."
                    );
                    return;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // ========================================================
    // ATHLETE MENU
    // ========================================================

    static void athleteMenu(
            int athleteId) {

        while (true) {

            System.out.println();

            System.out.println(
                    "============= ATHLETE MENU ============="
            );

            System.out.println(
                    "1. My Performance"
            );

            System.out.println(
                    "2. My ACWR"
            );

            System.out.println(
                    "3. My Personal Bests"
            );

            System.out.println(
                    "4. Logout"
            );

            System.out.print(
                    "Enter choice: "
            );

            int choice =
                    readInt();

            switch (choice) {

                case 1:

                    analytics.showAthletePerformance(
                            athleteId
                    );

                    break;

                case 2:

                    analytics.showACWR(
                            athleteId,
                            LocalDate.now()
                    );

                    break;

                case 3:

                    analytics.showPersonalBests(
                            athleteId
                    );

                    break;

                case 4:

                    System.out.println(
                            "Logged out."
                    );

                    return;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    // ========================================================
    // ADD ATHLETE
    // ========================================================

    static void addAthlete() {

        System.out.print(
                "Enter Athlete ID: "
        );

        int id =
                readInt();

        System.out.print(
                "Enter Name: "
        );

        String name =
                scanner.nextLine();

        System.out.print(
                "Enter Age: "
        );

        int age =
                readInt();

        System.out.print(
                "Enter Position: "
        );

        String position =
                scanner.nextLine();

        System.out.print(
                "Enter Squad: "
        );

        String squad =
                scanner.nextLine();

        athletes.add(
                new Athlete(
                        id,
                        name,
                        age,
                        position,
                        squad
                )
        );

        System.out.println(
                "Athlete added successfully."
        );
    }

    // ========================================================
    // ADD TRAINING SESSION
    // ========================================================

    static void addTrainingSession() {

        try {

            System.out.print(
                    "Enter Athlete ID: "
            );

            int athleteId =
                    readInt();

            System.out.print(
                    "Enter Session Date (YYYY-MM-DD): "
            );

            LocalDate date =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

            System.out.print(
                    "Enter Session Type: "
            );

            String type =
                    scanner.nextLine();

            System.out.print(
                    "Enter Duration (minutes): "
            );

            double duration =
                    readDouble();

            System.out.print(
                    "Enter RPE (0-10): "
            );

            double rpe =
                    readDouble();

            if (rpe < 0 || rpe > 10) {

                System.out.println(
                        "RPE must be between 0 and 10."
                );

                return;
            }

            System.out.print(
                    "Enter Sprint Time (seconds): "
            );

            double sprint =
                    readDouble();

            System.out.print(
                    "Enter Distance (km): "
            );

            double distance =
                    readDouble();

            System.out.print(
                    "Enter Weight Lifted (kg): "
            );

            double weight =
                    readDouble();

            System.out.print(
                    "Enter Average Heart Rate: "
            );

            double heartRate =
                    readDouble();

            TrainingSession session =
                    new TrainingSession(
                            nextSessionId++,
                            athleteId,
                            date,
                            type,
                            duration,
                            rpe,
                            sprint,
                            distance,
                            weight,
                            heartRate
                    );

            sessions.add(session);

            double workload =
                    session.getWorkload();

            System.out.println();

            System.out.println(
                    "Training session added successfully."
            );

            System.out.printf(
                    "Calculated Workload = %.2f AU%n",
                    workload
            );

        } catch (Exception e) {

            System.out.println(
                    "Invalid input. Please try again."
            );
        }
    }

    // ========================================================
    // DISPLAY ATHLETES
    // ========================================================

    static void displayAthletes() {

        System.out.println();

        System.out.println(
                "=========================================================="
        );

        System.out.printf(
                "%-5s %-20s %-5s %-15s %-15s%n",
                "ID",
                "Name",
                "Age",
                "Position",
                "Squad"
        );

        System.out.println(
                "=========================================================="
        );

        for (Athlete athlete : athletes) {

            athlete.display();
        }
    }

    // ========================================================
    // DISPLAY ALL SESSIONS
    // ========================================================

    static void displayAllSessions() {

        System.out.println();

        System.out.println(
                "================================================================================"
        );

        System.out.printf(
                "%-4s %-12s %-12s %-10s %-6s %-10s %-10s %-10s %-10s%n",
                "ID",
                "Date",
                "Type",
                "Duration",
                "RPE",
                "Sprint",
                "Distance",
                "Weight",
                "HR"
        );

        System.out.println(
                "================================================================================"
        );

        for (TrainingSession session :
                sessions) {

            session.display();
        }
    }

    // ========================================================
    // INPUT METHODS
    // ========================================================

    static int readInt() {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Enter a valid integer: "
                );
            }
        }
    }

    static double readDouble() {

        while (true) {

            try {

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Enter a valid number: "
                );
            }
        }
    }
}
