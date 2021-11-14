CREATE DATABASE IF NOT EXISTS `keycloak`;
CREATE DATABASE IF NOT EXISTS `financialstatistic`;

CREATE USER 'keycloak'@'%' IDENTIFIED BY 'KeycloakAdmin';
GRANT ALL ON `keycloak`.* TO 'keycloak'@'%';

CREATE USER 'financialstatistics_db_user'@'%' IDENTIFIED BY 'db_user';
GRANT ALL ON `financialstatistic`.* TO 'financialstatistics_db_user'@'%';