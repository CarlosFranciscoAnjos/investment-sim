-- Investment Simulator @Local



-- Users
------------------------------
SELECT *
FROM users u ;

UPDATE users
SET is_enabled = false
WHERE id = 1;

UPDATE users
SET role = 0
WHERE id = 2;

DELETE
FROM users u
WHERE u.id = 2;
------------------------------



-- Simulations
------------------------------
SELECT *
FROM simulations s;

SELECT  u.id "user_id", u.username, s.id "simlation_id", s.container_id, s.description
FROM simulations s
INNER JOIN users u ON u.id = s.user_id
WHERE u.username = 'flow1';

DELETE
FROM simulations 
WHERE id = 1;
------------------------------



-- Assets
------------------------------
SELECT *
FROM assets a;

SELECT *
FROM assets a
WHERE a.container_id = 1;

SELECT a.id "asset_id", a.container_id, a.value, a.income, a.description 
FROM assets a
WHERE a.container_id = (
	SELECT s.container_id
	FROM simulations s
	WHERE s.user_id = (
		SELECT u.id
		FROM users u
		WHERE u.username = :v_username
	)
);

DELETE
FROM assets a
WHERE a.id = 1;

DELETE
FROM assets a
WHERE a.container_id = 1;
------------------------------



-- Liabilities
------------------------------
SELECT *
FROM liabilities l;

SELECT *
FROM liabilities l
WHERE l.asset_id = 1;

DELETE
FROM liabilities l
WHERE l.id = 1;

DELETE
FROM liabilities l
WHERE l.asset_id = 1;
------------------------------



-- Print information
------------------------------
CREATE OR REPLACE PROCEDURE pg_temp.print_space(v_size INT DEFAULT 1) AS $$
    BEGIN
		FOR i IN 1..v_size LOOP
			RAISE NOTICE '';
		END LOOP;
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE pg_temp.print_separator(v_size INT DEFAULT 120) AS $$
    BEGIN
		RAISE NOTICE '%', repeat('-', v_size);
	END;
$$ LANGUAGE plpgsql;

-- Simulations
CREATE OR REPLACE PROCEDURE pg_temp.print_simulations_header() AS $$
    BEGIN
		-- Define header and separator
		CALL pg_temp.print_space(2);
		RAISE NOTICE 'Simulations';
		CALL pg_temp.print_separator();
		RAISE NOTICE '%', format('%-15s | %-15s | %-50s', 'simulation_id', 'container_id', 'description');
		CALL pg_temp.print_separator();
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE pg_temp.format_print_simulations(v_simulation_id BIGINT, v_container_id BIGINT, v_description VARCHAR) AS $$
    BEGIN
	    RAISE NOTICE '%', format('%-15s | %-15s | %-50s',
                        v_simulation_id, 
                        v_container_id, 
                        v_description);
	END;
$$ LANGUAGE plpgsql;

-- Assets
CREATE OR REPLACE PROCEDURE pg_temp.print_assets_header() AS $$
    BEGIN
		-- Define header and separator
		CALL pg_temp.print_space(2);
		RAISE NOTICE 'Assets';
		CALL pg_temp.print_separator();
		RAISE NOTICE '%', format('%-15s | %-15s | %-15s | %-15s | %-50s', 'asset_id', 'container_id', 'value', 'income', 'description');
		CALL pg_temp.print_separator();
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE pg_temp.format_print_assets(v_user_id BIGINT, v_container_id BIGINT, v_value FLOAT, v_income FLOAT, v_description VARCHAR) AS $$
    BEGIN
	    RAISE NOTICE '%', format('%-15s | %-15s | %-15s | %-15s | %-50s',
                        v_user_id, 
                        v_container_id, 
                        v_value, 
                        v_income, 
                        v_description);
	END;
$$ LANGUAGE plpgsql;

-- Main function
CREATE OR REPLACE PROCEDURE pg_temp.retrieve_print_all_user_information(p_username VARCHAR)
AS $$
DECLARE
	v_user_id BIGINT;	
    r_simulation RECORD;
    r_asset RECORD;
BEGIN
	-- Print information about procedure and variables
	CALL pg_temp.print_space(2);
	CALL pg_temp.print_separator();	
	RAISE NOTICE 'Starting procedure...';
	RAISE NOTICE 'Retrieving all user information (username = "%")', p_username;
	CALL pg_temp.print_separator();

    -- Select and print simulations
	CALL pg_temp.print_simulations_header();

	-- Retrieve user_id
	SELECT u.id
	INTO v_user_id
	FROM users u
	WHERE u.username = p_username;

    FOR r_simulation IN
        SELECT s.id AS simulation_id, s.container_id, s.description
        FROM simulations s
        WHERE s.user_id = v_user_id
		ORDER BY s.id
    LOOP
        CALL pg_temp.format_print_simulations(
			r_simulation.simulation_id,
            r_simulation.container_id, 
			r_simulation.description
		);
    END LOOP;

    -- Select and print assets
	CALL pg_temp.print_assets_header();

    FOR r_asset IN
        SELECT a.id AS asset_id, a.container_id, a.value, a.income, a.description 
        FROM assets a
        WHERE a.container_id IN (
            SELECT s.container_id
            FROM simulations s
            WHERE s.user_id = v_user_id
        )
		ORDER BY a.container_id, a.id
    LOOP
        CALL pg_temp.format_print_assets(
			r_asset.asset_id, 
			r_asset.container_id, 
			r_asset.value,
            r_asset.income, 
			r_asset.description
		);
    END LOOP;

	-- End procedure
	CALL pg_temp.print_space(2);
	RAISE NOTICE 'Finished procedure successfully...';
	CALL pg_temp.print_separator();

END;
$$ LANGUAGE plpgsql;

-- Execute
CALL pg_temp.retrieve_print_all_user_information('flow1');
------------------------------
