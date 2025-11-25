
DROP TYPE IF EXISTS gender;
CREATE TYPE gender AS ENUM(
    'M','F'
);

CREATE TABLE IF NOT EXISTS patients (
    pid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender GENDER NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    address VARCHAR(100) NOT NULL,
    suburb VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postcode VARCHAR(4) NOT NULL
);