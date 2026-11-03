


USE log_analyzer;

CREATE TABLE login_logs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100),
    ip_address VARCHAR(50),
    event_type VARCHAR(30),
    event_time DATETIME
);

CREATE TABLE security_alerts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100),
    ip_address VARCHAR(50),
    alert_type VARCHAR(100),
    description VARCHAR(255),
    alert_time DATETIME
);
