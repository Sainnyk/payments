INSERT INTO users (name) VALUES ('John Doe');
INSERT INTO users (name) VALUES ('Silvia Jimenez');
INSERT INTO users (name) VALUES ('Juan Martin');
INSERT INTO users (name) VALUES ('Marina Sanchez');
INSERT INTO users (name) VALUES ('Alison Mcguire');
INSERT INTO users (name) VALUES ('Bruny Sambil');


INSERT INTO payments (payment_id, dbtr_nm, debtor, dbtr_ctry, cdtr_nm, creditor, amount, cre_dt_tm)
VALUES (1, 'John Doe', 'ES4502337953000620004450', 'ES', 'Silvia Jimenez',
        'ES5300490418450200051332', 4531.31, NOW());

INSERT INTO payments (payment_id, dbtr_nm, debtor, dbtr_ctry, cdtr_nm, creditor, amount, cre_dt_tm)
VALUES (1, 'John Doe', 'ES4502337953000620004450', 'ES', 'Silvia Jimenez',
        'ES5300490418450200051332', 61.5, NOW());

INSERT INTO payments (payment_id, dbtr_nm, debtor, dbtr_ctry, cdtr_nm, creditor, amount, cre_dt_tm)
VALUES (5, 'Alison Mcguire', 'ES4502337943000620004444', 'ES', 'Tomas Jacinto',
        'ES5300490418450200051336', 501.38, NOW());

INSERT INTO payments (payment_id, dbtr_nm, debtor, dbtr_ctry, cdtr_nm, creditor, amount, cre_dt_tm)
VALUES (6, 'Bruny Sambil', 'ES4502337943000620001122', 'ES', 'Julian Campo',
        'ES5300490418450200051333', 231.38, NOW());