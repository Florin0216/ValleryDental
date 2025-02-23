ALTER TABLE appointment
    ADD appointment_confirmation VARCHAR(100);

ALTER TABLE treatment
    ALTER COLUMN price TYPE DECIMAL USING (price::DECIMAL);

ALTER TABLE receipt
    ALTER COLUMN total_cost TYPE DECIMAL USING (total_cost::DECIMAL);