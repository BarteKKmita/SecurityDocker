drop table IF EXISTS User;

create TABLE User (
  user_Name VARCHAR(250) NOT NULL PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL,
  is_Account_Expired BOOLEAN DEFAULT FALSE,
  is_Credentials_Expired BOOLEAN DEFAULT FALSE,
  is_Account_Locked BOOLEAN DEFAULT FALSE,
  is_Disabled BOOLEAN DEFAULT FALSE
);

INSERT INTO User VALUES
    ('James Bond', 'James', 'Bond', '$2a$10$A8CVfxOGzPO4.hv7o20NTOSeKKIpwfJUrQuQT/6GiR0Zi9HQSwNPu', 'ADMIN', FALSE, FALSE, FALSE, FALSE);

INSERT INTO User VALUES
    ('Leonardo DiCaprio', 'Leonardo', 'DiCaprio', '$2a$10$AgwzVwKy1uT2xwCEYB.5u.swT3bAjkH.AvAyw0mVUGSwG8XQhHeUW', 'USER', FALSE, FALSE, FALSE, FALSE);

INSERT INTO User VALUES
    ('Sophie Marceau', 'Sophie', 'Marceau','$2a$10$/NXDaALRD5iqE3YcvFybmekxij0ZUbm7xJLKt.ROVonZ4RzAKBYbK','USER', FALSE, FALSE, FALSE, FALSE);




--(id, userName, name, surname, password,role)