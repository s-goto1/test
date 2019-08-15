/*
ユーザー情報
auth1が管理
*/
CREATE TABLE userinfo(
id VARCHAR(100) PRIMARY KEY,
pass VARCHAR(100) NOT NULL,
name VARCHAR(100) NOT NULL,
auth INTEGER NOT NULL CHECK(auth=1 OR auth=2)
);