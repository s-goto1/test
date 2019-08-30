/*
出勤簿のテーブル
*/
CREATE TABLE work(
work_id SERIAL PRIMARY KEY,
id VARCHAR(100)  REFERENCES userinfo(id),
year INTEGER NOT NULL,
month INTEGER NOT NULL,
day INTEGER NOT NULL,
come_time TIME
leave_time TIME,
brake_time TIME,
work_time TIME,
over_time TIME,
notes VARCHAR(100),
vacation VARCHAR(100) NOT NULL
);
