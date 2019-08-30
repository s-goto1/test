CREATE TABLE timetable(
timetable_id SERIAL PRIMARY KEY,
id VARCHAR(100)  REFERENCES userinfo(id),
company_num INTEGER,
visit_name VARCHAR(100),
visit_come_time TIME,
visit_leave_time TIME,
visit_brake_time TIME,
round_time INTEGER NOT NULL,
vacation_day REAL NOT NULL
);
