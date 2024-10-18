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

CREATE OR REPLACE FUNCTION pg_temp.print_separator(v_size INT DEFAULT 120, v_char VARCHAR(1) DEFAULT '-')
RETURNS VOID AS $$
    BEGIN
		RAISE NOTICE '%', repeat(v_char, v_size);
	END;
$$ LANGUAGE plpgsql;

-- Simulations
CREATE OR REPLACE PROCEDURE pg_temp.print_simulations_header(v_id BIGINT DEFAULT -1) AS $$
    BEGIN
		-- Define header and separator
		CALL pg_temp.print_space();
		IF v_id > 0 THEN
			RAISE NOTICE '%', format('Simulation (id=%s)', v_id);
		ELSE
			RAISE NOTICE 'Simulations';
		END IF;
		EXECUTE pg_temp.print_separator();
		RAISE NOTICE '%', format('%-15s | %-15s | %-50s', 'simulation_id', 'container_id', 'description');
		EXECUTE pg_temp.print_separator();
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE pg_temp.format_print_simulations(p_simulation RECORD) AS $$
    BEGIN
	    RAISE NOTICE '%', format('%-15s | %-15s | %-50s',
                        p_simulation.id, 
                        p_simulation.container_id, 
                        p_simulation.description);
	END;
$$ LANGUAGE plpgsql;

-- Assets
CREATE OR REPLACE PROCEDURE pg_temp.print_assets_header() AS $$
    BEGIN
		-- Define header and separator
		CALL pg_temp.print_space();
		RAISE NOTICE 'Assets';
		EXECUTE pg_temp.print_separator();
		RAISE NOTICE '%', format('%-15s | %-15s | %-15s | %-15s | %-50s', 'asset_id', 'container_id', 'value', 'income', 'description');
		EXECUTE pg_temp.print_separator();
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE pg_temp.format_print_assets(p_asset RECORD) AS $$
    BEGIN
	    RAISE NOTICE '%', format('%-15s | %-15s | %-15s | %-15s | %-50s',
                        p_asset.id, 
                        p_asset.container_id, 
                        p_asset.value, 
                        p_asset.income, 
                        p_asset.description);
	END;
$$ LANGUAGE plpgsql;

-- Secondary function(s)
CREATE OR REPLACE PROCEDURE pg_temp.retrieve_print_all_simulation_information(p_simulation RECORD) AS $$
DECLARE    
    r_asset RECORD;
BEGIN
		CALL pg_temp.print_space();
		EXECUTE pg_temp.print_separator(v_char := '=');	

		-- Select and print simulation information
		CALL pg_temp.print_simulations_header(p_simulation.id);
		CALL pg_temp.format_print_simulations(p_simulation);

		-- Select and print assets
		CALL pg_temp.print_assets_header();
	    FOR r_asset IN
	        SELECT a.id, a.container_id, a.value, a.income, a.description 
	        FROM assets a
	        WHERE a.container_id IN (
	            SELECT s.container_id
	            FROM simulations s
	            WHERE s.id = p_simulation.id
	        )
			ORDER BY a.id
	    LOOP
	        CALL pg_temp.format_print_assets(r_asset);
	    END LOOP;
		CALL pg_temp.print_space();
		EXECUTE pg_temp.print_separator(v_char := '=');	
	END;
$$ LANGUAGE plpgsql;


-- Main function
CREATE OR REPLACE PROCEDURE pg_temp.retrieve_print_all_user_information(p_username VARCHAR, p_simulation_id BIGINT DEFAULT -1)
AS $$
DECLARE
	v_user_id BIGINT;
    r_simulation RECORD;
BEGIN
	-- Print information about procedure and variables
	EXECUTE pg_temp.print_separator(v_char := '=');	
	RAISE NOTICE 'Starting procedure...';
	RAISE NOTICE 'Retrieving all user information (username = "%")', p_username;
	EXECUTE pg_temp.print_separator(v_char := '=');

	-- Retrieve user_id
	SELECT u.id
	INTO v_user_id
	FROM users u
	WHERE u.username = p_username;

    -- Select and print simulations
	CALL pg_temp.print_simulations_header();

    FOR r_simulation IN
		SELECT s.id, s.container_id, s.description	
		FROM simulations s
		WHERE s.user_id = v_user_id
		ORDER BY s.id
    LOOP
        CALL pg_temp.format_print_simulations(r_simulation);
    END LOOP;
	CALL pg_temp.print_space(2);

	-- Select and print simulation(s) detailed information
	SELECT s.id, s.container_id, s.description
	INTO r_simulation
	FROM simulations s
	WHERE s.id = p_simulation_id;

	IF r_simulation IS NOT NULL THEN
		CALL pg_temp.retrieve_print_all_simulation_information(r_simulation);
		CALL pg_temp.print_space(2);
	ELSE
		FOR r_simulation IN
			SELECT s.id, s.container_id, s.description	
			FROM simulations s
			WHERE s.user_id = v_user_id
			ORDER BY s.id
	    LOOP
	        CALL pg_temp.retrieve_print_all_simulation_information(r_simulation);
			CALL pg_temp.print_space(2);
	    END LOOP;
	END IF;

	-- End procedure
	CALL pg_temp.print_space();
	RAISE NOTICE 'Finished procedure successfully!';
	EXECUTE pg_temp.print_separator();

END;
$$ LANGUAGE plpgsql;

-- Execute
CALL pg_temp.retrieve_print_all_user_information('flow1', -1);
------------------------------
