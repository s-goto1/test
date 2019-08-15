/*
請求書のテーブル
*/
CREATE TABLE totalm(
id VARCHAR(100)  REFERENCES userinfo(id),
year INTEGER NOT NULL,
month INTEGER NOT NULL,
day INTEGER NOT NULL,
transportation  VARCHAR(100) NOT NULL,
depature VARCHAR(100) NOT NULL,
destination VARCHAR(100) NOT NULL,
totalm_id SERIAL PRIMARY KEY,
money INTEGER NOT NULL,
division VARCHAR(100) NOT NULL,
place VARCHAR(100) NOT NULL,
purpose VARCHAR(100) NOT NULL
);