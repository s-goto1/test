/*
休暇届のテーブル
*/
CREATE TABLE vacation(
vacation_id SERIAL PRIMARY KEY,
id VARCHAR(100) REFERENCES userinfo(id),
year INTEGER NOT NULL,
from_month INTEGER NOT NULL,
from_day INTEGER NOT NULL,
to_month INTEGER NOT NULL,
to_day INTEGER NOT NULL,
total_day INTEGER NOT NULL,
division VARCHAR(100) NOT NULL,
reason VARCHAR(100) NOT NULL
);