
/*
ユーザー情報 
auth1が管理
*/
CREATE TABLE userinfo(
id VARCHAR(100) PRIMARY KEY,
pass VARCHAR(100) NOT NULL,
auth INTEGER NOT NULL CHECK(auth=1 OR auth=2)
);

/*
請求書のテーブル
*/
CREATE TABLE totalm(
id VARCHAR(100)  REFERENCES userinfo(id),
name VARCHAR(100) NOT NULL,
year INTEGER NOT NULL,
month INTEGER NOT NULL,
day INTEGER NOT NULL,
transportation  VARCHAR(100) NOT NULL,
depature VARCHAR(100) NOT NULL,
destination VARCHAR(100) NOT NULL,
totalm_id SERIAL,
money INTEGER NOT NULL,
devision VARCHAR(100) NOT NULL,
place VARCHAR(100) NOT NULL,
purpose VARCHAR(100) NOT NULL
);



INSERT INTO userinfo (id,pass,auth) VALUES 
('123','123',1),
('345','345',2),
('567','567',2);



