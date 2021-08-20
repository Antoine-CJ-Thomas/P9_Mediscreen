
CREATE TABLE patient_information (
                id INT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(32) NOT NULL,
                last_name VARCHAR(32) NOT NULL,
                birth_date DATE NOT NULL,
                gender VARCHAR(1) NOT NULL,
                address VARCHAR(64) NOT NULL,
                phone_number VARCHAR(15) NOT NULL,
                PRIMARY KEY (id)
);
