
-- ===========================================================================
-- location - Contains the following information for names:
-- ===========================================================================
--     id (bigint) - unique identifier of the location
--     postcode (string)– postcode information
--     latitude (double) – latitude of the postcode
--     longitude (double) – longitude of the postcode
CREATE TABLE IF NOT EXISTS location
(
    id bigint NOT NULL,
    postcode character varying  NOT NULL,
    latitude double precision,
    longitude double precision,
    CONSTRAINT location_pkey PRIMARY KEY (id)
    )


