START TRANSACTION;

ALTER TABLE patients DROP CONSTRAINT patients_phone_key;

CREATE UNIQUE INDEX patients_phone_key ON patients (phone) WHERE active = true;

COMMIT;