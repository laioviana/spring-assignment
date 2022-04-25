COPY location(id, postcode, latitude, longitude)
    FROM 'ukpostcodes.csv'
    DELIMITER ','
    CSV HEADER;