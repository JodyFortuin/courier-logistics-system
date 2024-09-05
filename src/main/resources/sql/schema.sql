DROP TABLE shipments;
DROP TABLE tracking;
DROP TABLE locations;

CREATE TABLE shipments (
    reference_Id uuid PRIMARY KEY,
    driver_Id integer,
    delivery_location_Id integer,
    status varchar,
    dateLogged date,
    dateDue date
);

CREATE TABLE tracking (
    reference_Id uuid,
    driver_Id integer PRIMARY KEY,
    driver varchar,
    current_location_Id integer,
    current_location varchar,
    capacity integer
);

CREATE TABLE locations (
    location_Id integer PRIMARY KEY,
    location varchar
);