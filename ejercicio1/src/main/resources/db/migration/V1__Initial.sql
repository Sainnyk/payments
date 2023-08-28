
CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY,name VARCHAR(20));

CREATE TABLE IF NOT EXISTS payments (id SERIAL PRIMARY KEY,payment_id INT REFERENCES users(id),dbtr_nm VARCHAR(100),
                debtor VARCHAR(100),dbtr_ctry VARCHAR(100),cdtr_nm VARCHAR(100),creditor VARCHAR(100),
                amount DECIMAL(10, 2),cre_dt_tm TIMESTAMP);

