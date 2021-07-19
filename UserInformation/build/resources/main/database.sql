
CREATE TABLE user_information (
                user_id INT AUTO_INCREMENT NOT NULL,
                first_name VARCHAR(32) NOT NULL,
                last_name VARCHAR(32) NOT NULL,
                birth_date DATE NOT NULL,
                gender CHAR NOT NULL,
                address VARCHAR(40) NOT NULL,
                phone_number INT NOT NULL,
                PRIMARY KEY (user_id)
);
