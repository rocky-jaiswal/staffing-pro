CREATE ROLE postgresdev WITH LOGIN PASSWORD 'postgresdev';

CREATE DATABASE auth_service_dev;
CREATE DATABASE auth_service_test;

CREATE DATABASE staffing_pro_dev;
CREATE DATABASE staffing_pro_test;

GRANT ALL PRIVILEGES ON DATABASE auth_service_dev TO postgresdev;
GRANT ALL PRIVILEGES ON DATABASE auth_service_test TO postgresdev;

GRANT ALL PRIVILEGES ON DATABASE staffing_pro_dev TO postgresdev;
GRANT ALL PRIVILEGES ON DATABASE staffing_pro_test TO postgresdev;
