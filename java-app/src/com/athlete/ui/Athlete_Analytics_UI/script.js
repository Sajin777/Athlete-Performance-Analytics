const data = {

  athletes: [

    {
      id: "AT-101",
      name: "Arjun Kumar",
      age: 22,
      squad: "Elite Squad",
      position: "Sprinter",
      sessions: 12,
      workload: 482,
      acwr: 1.08,
      status: "Normal",
      performance: 92,
      hr: 158,
      pb: "10.84s"
    },

    {
      id: "AT-102",
      name: "Meera Nair",
      age: 21,
      squad: "Elite Squad",
      position: "Middle Distance",
      sessions: 11,
      workload: 451,
      acwr: 1.17,
      status: "Normal",
      performance: 89,
      hr: 161,
      pb: "4:18.2"
    },

    {
      id: "AT-103",
      name: "Rahul Das",
      age: 23,
      squad: "Development",
      position: "Sprinter",
      sessions: 10,
      workload: 398,
      acwr: 1.31,
      status: "Elevated",
      performance: 86,
      hr: 166,
      pb: "11.12s"
    },

    {
      id: "AT-104",
      name: "Ananya Roy",
      age: 20,
      squad: "Elite Squad",
      position: "Jumper",
      sessions: 13,
      workload: 521,
      acwr: 0.94,
      status: "Normal",
      performance: 95,
      hr: 154,
      pb: "6.42m"
    },

    {
      id: "AT-105",
      name: "Vikram Singh",
      age: 24,
      squad: "Development",
      position: "Thrower",
      sessions: 8,
      workload: 376,
      acwr: 1.48,
      status: "High",
      performance: 81,
      hr: 169,
      pb: "14.82m"
    },

    {
      id: "AT-106",
      name: "Diya Joseph",
      age: 19,
      squad: "Youth Squad",
      position: "Sprinter",
      sessions: 9,
      workload: 342,
      acwr: 1.02,
      status: "Normal",
      performance: 84,
      hr: 157,
      pb: "12.01s"
    }

  ],

  squads: [

    ["Elite Squad", 24, 88, 1.06, 78],
    ["Development", 16, 81, 1.22, 61],
    ["Youth Squad", 8, 76, 1.02, 44]

  ]

};


let role = "Coach";
let current = "dashboard";


const $ = selector =>
  document.querySelector(selector);


function badge(status) {

  return `
    <span class="badge ${status.toLowerCase()}">
      ${status}
    </span>
  `;

}


function avatar(name) {

  return `
    <div class="avatar">
      ${name
        .split(" ")
        .map(x => x[0])
        .join("")
        .slice(0, 2)}
    </div>
  `;

}


/* =========================
   NAVIGATION
========================= */

const coachNav = [

  ["dashboard", "▦", "Dashboard"],
  ["athletes", "♙", "Athletes"],
  ["squads", "◉", "Squads"],
  ["sessions", "◷", "Training Sessions"],
  ["performance", "↗", "Performance"],
  ["workload", "▥", "Workload"],
  ["acwr", "◒", "ACWR Analysis"],
  ["compare", "⇄", "Comparisons"],
  ["pbs", "★", "Personal Bests"],
  ["reports", "▤", "Reports"]

];


function buildNav() {

  let items = [...coachNav];

  if (role === "Athlete") {

    items = [

      ["dashboard", "▦", "Personal Dashboard"],
      ["performance", "↗", "Personal Performance"],
      ["workload", "▥", "Personal Workload"],
      ["pbs", "★", "Personal Bests"],
      ["sessions", "◷", "Training History"]

    ];

  }


  let html =
    `<div class="group">MAIN</div>`;


  html += items
    .map(item => `
      <button
        class="nav ${current === item[0] ? "active" : ""}"
        data-page="${item[0]}"
      >
        <span>${item[1]}</span>
        ${item[2]}
      </button>
    `)
    .join("");


  if (role === "Admin") {

    html += `

      <div class="group">
        ADMINISTRATION
      </div>

      <button class="nav" data-page="users">
        <span>♙</span>
        Manage Users
      </button>

      <button class="nav" data-page="metrics">
        <span>⚙</span>
        Manage Metrics
      </button>

      <button class="nav" data-page="settings">
        <span>⚙</span>
        Settings
      </button>

    `;

  } else {

    html += `

      <div class="group">
        SYSTEM
      </div>

      <button class="nav" data-page="settings">
        <span>⚙</span>
        Settings
      </button>

    `;

  }


  $("#nav").innerHTML = html;


  document
    .querySelectorAll(".nav")
    .forEach(button => {

      button.onclick = () => {

        current = button.dataset.page;

        render();

      };

    });

}


/* =========================
   COMMON UI
========================= */

function title(title, subtitle, actions = "") {

  return `

    <div class="page-title">

      <div>

        <h1>${title}</h1>

        <p>${subtitle}</p>

      </div>

      <div class="actions">
        ${actions}
      </div>

    </div>

  `;

}


function kpi(label, value, trend, icon) {

  return `

    <div class="card kpi">

      <small>
        ${icon} &nbsp; ${label}
      </small>

      <h2>${value}</h2>

      <span
        class="${trend[0] === "-" ? "down" : "up"}"
      >
        ${trend} vs previous period
      </span>

    </div>

  `;

}


/* =========================
   COACH / ADMIN DASHBOARD
========================= */

function dashboard() {

  if (role === "Athlete") {

    return athleteDashboard();

  }


  return title(
    role === "Admin"
      ? "Admin Dashboard"
      : "Good morning, Coach",

    role === "Admin"
      ? "Manage users, athletes and analytics configuration."
      : "Monitor squad performance, workload and training trends.",

    `
      <select class="filter">
        <option>All Squads</option>
        <option>Elite Squad</option>
      </select>

      <button
        class="primary"
        onclick="sessionModal()"
      >
        + Add Session
      </button>
    `
  )

  +

  `

  <div class="kpis">

    ${kpi(
      "Total Athletes",
      "48",
      "+8%",
      "♙"
    )}

    ${kpi(
      "Active Training Sessions",
      "126",
      "+12%",
      "◷"
    )}

    ${kpi(
      "Average Workload",
      "426",
      "+5.4%",
      "▥"
    )}

    ${kpi(
      "Athletes at Risk",
      "4",
      "-18%",
      "⚠"
    )}

    ${kpi(
      "Average ACWR",
      "1.12",
      "+2.1%",
      "◒"
    )}

  </div>


  <div class="grid">

    <div class="card chart">

      <div class="card-head">

        <h3>Performance Trend</h3>

        <small>
          Last 30 days
        </small>

      </div>

      <canvas id="performance"></canvas>

    </div>


    <div class="card chart">

      <div class="card-head">

        <h3>ACWR Status</h3>

        <small>
          Current squad
        </small>

      </div>

      ${data.athletes
        .slice(0, 5)
        .map(a => `

          <div class="status-row">

            <span>${a.name}</span>

            <b>${a.acwr}</b>

            ${badge(a.status)}

          </div>

        `)
        .join("")}

    </div>

  </div>


  <div class="grid">

    <div class="card chart">

      <div class="card-head">

        <h3>Training Workload</h3>

        <small>
          Daily / weekly
        </small>

      </div>

      <canvas id="workload"></canvas>

    </div>


    <div class="card table">

      <div class="table-head">

        <b>Recent Training Sessions</b>

        <button
          class="outline"
          onclick="
            current='sessions';
            render();
          "
        >
          View all
        </button>

      </div>

      ${sessionTable()}

    </div>

  </div>

  `;

}


/* =========================
   ATHLETE DASHBOARD
========================= */

function athleteDashboard() {

  const athlete = data.athletes[0];

  return title(
    `Welcome back, ${athlete.name}`,
    "Your personal performance overview."
  )

  +

  `

  <div class="kpis">

    ${kpi(
      "Performance",
      athlete.performance,
      "+4.2%",
      "↗"
    )}

    ${kpi(
      "Workload",
      athlete.workload,
      "+3.1%",
      "▥"
    )}

    ${kpi(
      "ACWR",
      athlete.acwr,
      "+1.8%",
      "◒"
    )}

    ${kpi(
      "Personal Best",
      athlete.pb,
      "New",
      "★"
    )}

  </div>


  <div class="grid">

    <div class="card chart">

      <div class="card-head">
        <h3>My Performance Trend</h3>
      </div>

      <canvas id="performance"></canvas>

    </div>


    <div class="card chart">

      <div class="card-head">
        <h3>My Workload Trend</h3>
      </div>

      <canvas id="workload"></canvas>

    </div>

  </div>


  <div class="card table">

    <div class="table-head">
      <b>Recent Training Sessions</b>
    </div>

    ${sessionTable(athlete)}

  </div>

  `;

}


/* =========================
   TRAINING TABLE
========================= */

function sessionTable(athlete) {

  const list = athlete
    ? [athlete]
    : data.athletes.slice(0, 5);


  return `

  <div class="table-wrap">

    <table>

      <thead>

        <tr>

          <th>Date</th>
          <th>Athlete</th>
          <th>Session</th>
          <th>Duration</th>
          <th>RPE</th>
          <th>Workload</th>
          <th>Heart Rate</th>

        </tr>

      </thead>

      <tbody>

        ${list.map((a, i) => `

          <tr>

            <td>
              Sep ${28 - i}
            </td>

            <td>

              <div class="person">

                ${avatar(a.name)}

                ${a.name}

              </div>

            </td>

            <td>
              ${
                [
                  "Speed Training",
                  "Endurance",
                  "Strength",
                  "Recovery"
                ][i % 4]
              }
            </td>

            <td>
              ${55 + i * 8} min
            </td>

            <td>
              ${6 + i % 4}
            </td>

            <td>
              ${a.workload}
            </td>

            <td>
              ${a.hr} bpm
            </td>

          </tr>

        `).join("")}

      </tbody>

    </table>

  </div>

  `;

}


/* =========================
   ATHLETES
========================= */

function athletes() {

  return title(
    "Athletes",
    "Manage and monitor athlete profiles.",

    `
      <button
        class="primary"
        onclick="athleteModal()"
      >
        + Add Athlete
      </button>
    `
  )

  +

  `

  <div class="card table">

    <div class="table-head">

      <div class="filters">

        <input
          id="athSearch"
          placeholder="Search athletes..."
          oninput="filterAthletes()"
        >

        <select
          id="squadFilter"
          onchange="filterAthletes()"
        >

          <option>All Squads</option>
          <option>Elite Squad</option>
          <option>Development</option>
          <option>Youth Squad</option>

        </select>

        <select
          id="statusFilter"
          onchange="filterAthletes()"
        >

          <option>All Status</option>
          <option>Normal</option>
          <option>Elevated</option>
          <option>High</option>

        </select>

      </div>

    </div>


    <div class="table-wrap">

      <table>

        <thead>

          <tr>

            <th>Athlete</th>
            <th>ID</th>
            <th>Age</th>
            <th>Squad</th>
            <th>Position</th>
            <th>Workload</th>
            <th>ACWR</th>
            <th>Status</th>
            <th></th>

          </tr>

        </thead>

        <tbody id="athRows">

          ${athleteRows(data.athletes)}

        </tbody>

      </table>

    </div>

  </div>

  `;

}


function athleteRows(list) {

  return list.map(a => `

    <tr>

      <td>

        <div class="person">

          ${avatar(a.name)}

          <b>${a.name}</b>

        </div>

      </td>

      <td>${a.id}</td>

      <td>${a.age}</td>

      <td>${a.squad}</td>

      <td>${a.position}</td>

      <td>${a.workload}</td>

      <td>${a.acwr}</td>

      <td>${badge(a.status)}</td>

      <td>

        <button
          class="outline"
          onclick="profile('${a.id}')"
        >
          View
        </button>

      </td>

    </tr>

  `).join("");

}


function filterAthletes() {

  const query =
    $("#athSearch").value.toLowerCase();

  const squad =
    $("#squadFilter").value;

  const status =
    $("#statusFilter").value;


  const filtered =
    data.athletes.filter(a =>

      (
        a.name
          .toLowerCase()
          .includes(query)

        ||

        a.id
          .toLowerCase()
          .includes(query)
      )

      &&

      (
        squad === "All Squads"
        ||
        a.squad === squad
      )

      &&

      (
        status === "All Status"
        ||
        a.status === status
      )

    );


  $("#athRows").innerHTML =
    athleteRows(filtered);

}


/* =========================
   SQUADS
========================= */

function squads() {

  return title(
    "Squads",
    "Manage squads and squad-level analytics.",

    `
      <button
        class="primary"
        onclick="
          toast('Create squad form opened')
        "
      >
        + Create Squad
      </button>
    `
  )

  +

  `

  <div class="squads">

    ${data.squads.map(s => `

      <div class="card squad">

        <h3>${s[0]}</h3>

        <p>
          ${s[1]} athletes
        </p>

        <div class="squad-stats">

          <span>
            Performance
            <b>${s[2]}</b>
          </span>

          <span>
            ACWR
            <b>${s[3]}</b>
          </span>

        </div>

        <div class="progress">

          <i style="width:${s[4]}%"></i>

        </div>

        <p>
          ${s[4]}% workload capacity
        </p>

        <button
          class="outline"
          onclick="
            toast('Viewing ${s[0]}')
          "
        >
          View Squad
        </button>

      </div>

    `).join("")}

  </div>

  `;

}


/* =========================
   TRAINING SESSIONS
========================= */

function sessions() {

  return title(
    "Training Sessions",
    "Add and manage training sessions.",

    `
      <button
        class="outline"
        onclick="
          toast('Report exported')
        "
      >
        Export Report
      </button>

      <button
        class="primary"
        onclick="sessionModal()"
      >
        + Add Training Session
      </button>
    `
  )

  +

  `

  <div class="card table">

    ${sessionTable()}

  </div>

  `;

}


/* =========================
   ANALYTICS
========================= */

function analytics(type) {

  const names = {

    performance:
      "Performance Analytics",

    workload:
      "Workload Analytics",

    acwr:
      "ACWR Analysis"

  };


  const name = names[type];


  return title(
    name,
    "Analyze athlete data using interactive sports metrics.",

    `
      <select class="filter">

        <option>
          Last 30 days
        </option>

        <option>
          Last 90 days
        </option>

        <option>
          This season
        </option>

      </select>
    `
  )

  +

  `

  <div class="metric-grid">

    <div class="card metric">

      <small>Total Workload</small>

      <strong>426</strong>

      <span class="up">+8.2%</span>

    </div>

    <div class="card metric">

      <small>Average Workload</small>

      <strong>84</strong>

      <span class="up">+4.2%</span>

    </div>

    <div class="card metric">

      <small>Acute Workload</small>

      <strong>122</strong>

      <span class="up">+7.2%</span>

    </div>

    <div class="card metric">

      <small>Chronic Workload</small>

      <strong>116</strong>

      <span class="up">+3.2%</span>

    </div>

  </div>


  <div class="grid">

    <div class="card chart">

      <div class="card-head">

        <h3>${name} Trend</h3>

        <small>
          Interactive view
        </small>

      </div>

      <canvas id="mainChart"></canvas>

    </div>


    <div class="card chart">

      <div class="card-head">

        <h3>Athlete Comparison</h3>

      </div>

      <canvas id="compareChart"></canvas>

    </div>

  </div>


  ${
    type === "acwr"
      ? acwrTable()
      : ""
  }


  ${
    type === "workload"
      ? `

        <div class="card table">

          <div class="table-head">

            <b>
              Athlete Workload Comparison
            </b>

          </div>

          ${workloadTable()}

        </div>

      `
      : ""
  }

  `;

}


function acwrTable() {

  return `

  <div class="card table">

    <div class="table-head">

      <b>
        ACWR Athlete Status
      </b>

      <div class="filters">

        <select>
          <option>All Squads</option>
        </select>

        <select>
          <option>All Status</option>
        </select>

      </div>

    </div>


    <div class="table-wrap">

      <table>

        <thead>

          <tr>

            <th>Athlete</th>
            <th>Acute</th>
            <th>Chronic</th>
            <th>ACWR</th>
            <th>Status</th>

          </tr>

        </thead>

        <tbody>

          ${data.athletes.map(a => `

            <tr>

              <td>${a.name}</td>

              <td>
                ${Math.round(a.workload * .32)}
              </td>

              <td>
                ${Math.round(a.workload * .30)}
              </td>

              <td>
                <b>${a.acwr}</b>
              </td>

              <td>
                ${badge(a.status)}
              </td>

            </tr>

          `).join("")}

        </tbody>

      </table>

    </div>

  </div>

  `;

}


function workloadTable() {

  return `

  <div class="table-wrap">

    <table>

      <thead>

        <tr>

          <th>Athlete</th>
          <th>Sessions</th>
          <th>Daily Avg</th>
          <th>Weekly</th>
          <th>Status</th>

        </tr>

      </thead>

      <tbody>

        ${data.athletes.map(a => `

          <tr>

            <td>${a.name}</td>

            <td>${a.sessions}</td>

            <td>
              ${Math.round(
                a.workload / a.sessions
              )}
            </td>

            <td>${a.workload}</td>

            <td>
              ${badge(a.status)}
            </td>

          </tr>

        `).join("")}

      </tbody>

    </table>

  </div>

  `;

}


/* =========================
   COMPARISON
========================= */

function compare() {

  return title(
    "Squad Comparison",
    "Compare selected athletes across key performance indicators.",

    `
      <button
        class="primary"
        onclick="
          toast('Comparison report exported')
        "
      >
        Export
      </button>
    `
  )

  +

  `

  <div class="card chart">

    <div class="card-head">

      <h3>
        Performance / Workload Comparison
      </h3>

    </div>

    <canvas id="compareChart"></canvas>

  </div>


  <div
    class="card table"
    style="margin-top:16px"
  >

    <div class="table-head">

      <b>
        Comparison Details
      </b>

    </div>

    <div class="table-wrap">

      <table>

        <thead>

          <tr>

            <th>Athlete</th>
            <th>Performance</th>
            <th>Workload</th>
            <th>ACWR</th>
            <th>Heart Rate</th>
            <th>Personal Best</th>

          </tr>

        </thead>

        <tbody>

          ${data.athletes.map(a => `

            <tr>

              <td>${a.name}</td>
              <td>${a.performance}</td>
              <td>${a.workload}</td>
              <td>${a.acwr}</td>
              <td>${a.hr}</td>
              <td>${a.pb}</td>

            </tr>

          `).join("")}

        </tbody>

      </table>

    </div>

  </div>

  `;

}


/* =========================
   PERSONAL BESTS
========================= */

function pbs() {

  return title(
    "Personal Bests",
    "Track and highlight recently achieved personal bests.",

    `
      <button
        class="outline"
        onclick="
          toast('PB report exported')
        "
      >
        Export
      </button>
    `
  )

  +

  `

  <div class="card table">

    <div class="table-head">

      <div class="filters">

        <input
          placeholder="Search metric..."
        >

        <select>
          <option>
            All Athletes
          </option>
        </select>

        <select>
          <option>
            All Metrics
          </option>
        </select>

      </div>

    </div>


    <div class="table-wrap">

      <table>

        <thead>

          <tr>

            <th>Athlete</th>
            <th>Metric</th>
            <th>Personal Best</th>
            <th>Date</th>
            <th>Previous Best</th>

          </tr>

        </thead>

        <tbody>

          ${data.athletes.map((a, i) => `

            <tr>

              <td>${a.name}</td>

              <td>
                ${
                  a.position === "Sprinter"
                    ? "100m Sprint"
                    : a.position === "Jumper"
                    ? "Long Jump"
                    : "Performance Score"
                }
              </td>

              <td>
                <b>${a.pb}</b>
              </td>

              <td>
                Sep ${25 - i}
              </td>

              <td>
                Previous record
              </td>

            </tr>

          `).join("")}

        </tbody>

      </table>

    </div>

  </div>

  `;

}


/* =========================
   REPORTS
========================= */

function reports() {

  const reportNames = [

    "Athlete Performance Report",
    "Training Workload Report",
    "ACWR Report",
    "Squad Comparison Report",
    "Personal Best Report",
    "Training History"

  ];


  return title(
    "Reports",
    "Generate and export analytics reports.",

    `
      <button
        class="primary"
        onclick="
          toast('Report generated')
        "
      >
        Generate Report
      </button>
    `
  )

  +

  `

  <div class="report-grid">

    ${reportNames.map(name => `

      <div class="card report">

        <h3>${name}</h3>

        <p>
          Select date range, athlete,
          squad and metric before
          generating the report.
        </p>

        <button
          class="outline"
          onclick="
            toast('${name} configured')
          "
        >
          Configure
        </button>

      </div>

    `).join("")}

  </div>

  `;

}


/* =========================
   ADMIN
========================= */

function users() {

  return title(
    "Manage Users",
    "Manage system accounts and roles.",

    `
      <button
        class="primary"
        onclick="
          toast('Add user form opened')
        "
      >
        + Add User
      </button>
    `
  )

  +

  `

  <div class="card table">

    <div class="table-head">
      <b>Users</b>
    </div>

    <div class="table-wrap">

      <table>

        <thead>

          <tr>
            <th>User</th>
            <th>Username</th>
            <th>Role</th>
            <th>Status</th>
            <th>Last Login</th>
          </tr>

        </thead>

        <tbody>

          ${[
            "Sajin",
            "Priya Menon",
            "Arun Coach",
            "Meera Nair"
          ].map((name, i) => `

            <tr>

              <td>${name}</td>

              <td>
                ${name.toLowerCase().replaceAll(" ", ".")}
              </td>

              <td>
                ${
                  ["Admin","Coach","Coach","Athlete"][i]
                }
              </td>

              <td>
                ${badge("Normal")}
              </td>

              <td>
                Today
              </td>

            </tr>

          `).join("")}

        </tbody>

      </table>

    </div>

  </div>

  `;

}


function metrics() {

  const metrics = [

    "Sprint Time",
    "Heart Rate",
    "RPE",
    "Jump Distance",
    "Performance Score",
    "Duration"

  ];


  return title(
    "Manage Metrics",
    "Configure performance metrics used by the application.",

    `
      <button
        class="primary"
        onclick="
          toast('Metric form opened')
        "
      >
        + Add Metric
      </button>
    `
  )

  +

  `

  <div class="report-grid">

    ${metrics.map(name => `

      <div class="card report">

        <h3>${name}</h3>

        <p>
          Active performance metric.
        </p>

        <button
          class="outline"
          onclick="
            toast('Metric edited')
          "
        >
          Edit
        </button>

      </div>

    `).join("")}

  </div>

  `;

}


/* =========================
   SETTINGS
========================= */

function settings() {

  return title(
    "Settings",
    "Application and integration preferences."
  )

  +

  `

  <div
    class="card report"
    style="max-width:700px"
  >

    <h3>
      Backend Integration
    </h3>

    <p>
      Frontend is prepared to communicate
      with Java Application → JDBC →
      Oracle Database → SQL / PL-SQL →
      Analytics.
    </p>

    <div class="form-grid">

      ${field(
        "Organization",
        "Athlete Performance Center"
      )}

      ${field(
        "Database",
        "Oracle Database"
      )}

      ${field(
        "Backend",
        "Java + JDBC"
      )}

      ${field(
        "Default Range",
        "Last 30 days"
      )}

    </div>

    <button
      class="primary"
      onclick="
        toast('Settings saved')
      "
    >
      Save Settings
    </button>

  </div>

  `;

}


/* =========================
   ATHLETE PROFILE
========================= */

function profile(id) {

  const athlete =
    data.athletes.find(a => a.id === id);


  $("#page").innerHTML =

    title(
      athlete.name,
      `${athlete.id} · ${athlete.squad} · ${athlete.position}`,

      `
        <button
          class="outline"
          onclick="
            current='athletes';
            render();
          "
        >
          ← Back
        </button>
      `
    )

    +

    `

    <div class="profile">

      <div class="card profile-card">

        ${avatar(athlete.name)}

        <h2>${athlete.name}</h2>

        <p>
          ${athlete.position}
          ·
          ${athlete.squad}
        </p>

        <div class="profile-stats">

          <div>
            <strong>${athlete.workload}</strong>
            <small>Current Workload</small>
          </div>

          <div>
            <strong>${athlete.performance}</strong>
            <small>Avg Performance</small>
          </div>

          <div>
            <strong>${athlete.acwr}</strong>
            <small>ACWR</small>
          </div>

          <div>
            <strong>${athlete.sessions}</strong>
            <small>Sessions</small>
          </div>

          <div>
            <strong>${athlete.pb}</strong>
            <small>Personal Best</small>
          </div>

          <div>
            <strong>${athlete.hr}</strong>
            <small>Avg Heart Rate</small>
          </div>

        </div>

      </div>


      <div class="card chart">

        <div class="card-head">

          <h3>
            Performance Trend
          </h3>

        </div>

        <canvas id="profileChart"></canvas>

      </div>

    </div>


    <div
      class="grid"
      style="margin-top:16px"
    >

      <div class="card chart">

        <div class="card-head">

          <h3>
            Workload Trend
          </h3>

        </div>

        <canvas id="profileWorkload"></canvas>

      </div>


      <div class="card table">

        <div class="table-head">

          <b>
            Recent Training Sessions
          </b>

        </div>

        ${sessionTable(athlete)}

      </div>

    </div>

    `;


  setTimeout(() => {

    draw("profileChart", "line");

    draw("profileWorkload", "bar");

  }, 20);

}


/* =========================
   FORM HELPERS
========================= */

function field(label, value) {

  return `

    <div class="field">

      <label>${label}</label>

      <input value="${value}">

    </div>

  `;

}


/* =========================
   ADD ATHLETE
========================= */

function athleteModal() {

  $("#modalBody").innerHTML = `

    <h2>Add Athlete</h2>

    <p style="color:#7b8497">
      Create a new athlete profile.
    </p>

    <div class="form-grid">

      ${field("Athlete ID", "AT-107")}

      ${field("Name", "")}

      ${field("Date of Birth", "")}

      ${field("Gender", "")}

      ${field("Squad", "Elite Squad")}

      ${field("Position", "")}

      ${field(
        "Contact Information",
        ""
      )}

    </div>

    <div class="form-actions">

      <button
        class="outline"
        onclick="closeModal()"
      >
        Cancel
      </button>

      <button
        class="primary"
        onclick="
          closeModal();
          toast('Athlete saved successfully')
        "
      >
        Save Athlete
      </button>

    </div>

  `;


  $("#modal").classList.remove("hidden");

}


/* =========================
   ADD TRAINING SESSION
========================= */

function sessionModal() {

  $("#modalBody").innerHTML = `

    <h2>
      Add Training Session
    </h2>

    <p style="color:#7b8497">
      Workload = Duration × RPE
    </p>

    <div class="form-grid">

      ${field(
        "Athlete",
        "Arjun Kumar"
      )}

      ${field(
        "Squad",
        "Elite Squad"
      )}

      ${field(
        "Date",
        "2026-09-28"
      )}

      ${field(
        "Session Type",
        "Speed Training"
      )}

      ${field(
        "Duration (min)",
        "60"
      )}

      ${field(
        "RPE (1-10)",
        "7"
      )}

      ${field(
        "Average Heart Rate",
        "158"
      )}

      ${field(
        "Maximum Heart Rate",
        "178"
      )}

      ${field(
        "Performance Metric",
        "92"
      )}

    </div>

    <div
      class="field"
      style="margin-top:13px"
    >

      <label>Notes</label>

      <textarea
        placeholder="Training notes..."
      ></textarea>

    </div>

    <div class="form-actions">

      <button
        class="outline"
        onclick="closeModal()"
      >
        Cancel
      </button>

      <button
        class="primary"
        onclick="
          closeModal();
          toast(
            'Session saved. Workload calculated.'
          )
        "
      >
        Save Session
      </button>

    </div>

  `;


  $("#modal").classList.remove("hidden");

}


function closeModal() {

  $("#modal").classList.add("hidden");

}


/* =========================
   TOAST
========================= */

function toast(message) {

  $("#toast").textContent = message;

  $("#toast").style.display = "block";

  setTimeout(() => {

    $("#toast").style.display = "none";

  }, 2200);

}


/* =========================
   CHART ENGINE
========================= */

function draw(id, type = "line") {

  const canvas = $("#" + id);

  if (!canvas) return;


  const rect =
    canvas.getBoundingClientRect();


  const dpr =
    window.devicePixelRatio || 1;


  const width =
    Math.max(300, rect.width);


  const height = 235;


  canvas.width =
    width * dpr;

  canvas.height =
    height * dpr;


  const ctx =
    canvas.getContext("2d");


  ctx.scale(dpr, dpr);


  ctx.clearRect(
    0,
    0,
    width,
    height
  );


  /* Grid */

  ctx.strokeStyle =
    "#e8ecf2";

  ctx.lineWidth = 1;


  for (let i = 0; i < 5; i++) {

    ctx.beginPath();

    ctx.moveTo(
      30,
      20 + i * 45
    );

    ctx.lineTo(
      width - 10,
      20 + i * 45
    );

    ctx.stroke();

  }


  /* BAR CHART */

  if (type === "bar") {

    const values = [
      55,70,48,78,66,
      87,75,92,81,96
    ];


    values.forEach((value, i) => {

      ctx.fillStyle =
        "#5d80ed";


      const barWidth =
        (width - 50) / 10 - 6;


      const barHeight =
        value * 1.65;


      ctx.fillRect(

        35 +
        i * (barWidth + 6),

        height - 28 - barHeight,

        barWidth,

        barHeight

      );

    });


    return;

  }


  /* LINE CHART */

  const values = [
    52,60,57,68,
    64,74,70,82,
    78,89,84,94
  ];


  const points =
    values.map((value, i) => [

      35 +
      i * (width - 55) /
      (values.length - 1),

      height -
      30 -
      value * 1.7

    ]);


  ctx.beginPath();


  points.forEach((point, i) => {

    if (i === 0) {

      ctx.moveTo(
        point[0],
        point[1]
      );

    } else {

      ctx.lineTo(
        point[0],
        point[1]
      );

    }

  });


  ctx.strokeStyle =
    "#587df0";

  ctx.lineWidth = 3;

  ctx.stroke();


  points.forEach(point => {

    ctx.beginPath();

    ctx.arc(
      point[0],
      point[1],
      3,
      0,
      Math.PI * 2
    );

    ctx.fillStyle = "#fff";

    ctx.fill();

    ctx.strokeStyle =
      "#587df0";

    ctx.stroke();

  });

}


/* =========================
   RENDER
========================= */

function render() {

  buildNav();


  const pages = {

    dashboard,

    athletes,

    squads,

    sessions,

    performance:
      () => analytics("performance"),

    workload:
      () => analytics("workload"),

    acwr:
      () => analytics("acwr"),

    compare,

    pbs,

    reports,

    users,

    metrics,

    settings

  };


  $("#page").innerHTML =
    (pages[current] || dashboard)();


  setTimeout(() => {

    [

      "performance",
      "workload",
      "mainChart",
      "compareChart",
      "profileChart",
      "profileWorkload"

    ].forEach(id => {

      if ($("#" + id)) {

        draw(
          id,

          id === "workload" ||
          id === "compareChart" ||
          id === "profileWorkload"

            ? "bar"

            : "line"
        );

      }

    });

  }, 20);

}


/* =========================
   EVENTS
========================= */

$("#loginForm").onsubmit = event => {

  event.preventDefault();

  $("#login")
    .classList
    .add("hidden");

  $("#app")
    .classList
    .remove("hidden");

  render();

};


$("#role").onchange = event => {

  role =
    event.target.value;

  $("#accountRole")
    .textContent = role;

  current =
    "dashboard";

  render();

};


$("#showPass").onclick = () => {

  $("#password").type =
    $("#password").type === "password"
      ? "text"
      : "password";

};


$("#closeModal").onclick =
  closeModal;


$("#logout").onclick = () => {

  $("#app")
    .classList
    .add("hidden");

  $("#login")
    .classList
    .remove("hidden");

};


$("#menu").onclick = () => {

  $("#sidebar")
    .classList
    .toggle("open");

};


window.addEventListener(
  "resize",
  () => render()
);


render();
