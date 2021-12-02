DROP TABLE IF EXISTS Event;

CREATE TABLE Event (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  event_name VARCHAR(30) NOT NULL,
  event_description VARCHAR(250) NOT NULL,
  start_date DATETIME,
  end_date DATETIME ,
  zone_offset VARCHAR(10)

);