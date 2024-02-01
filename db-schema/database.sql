/********************* ROLES **********************/

CREATE ROLE R_WEB;

/******************** TABLES **********************/

CREATE TABLE TEST_TABLE
(
  ID Integer NOT NULL,
  DESCRIPTION Varchar(50),
  CONSTRAINT TEST_TABLE_PK PRIMARY KEY (ID)
);

/******************** DATA **********************/

INSERT INTO TEST_TABLE (ID, DESCRIPTION) VALUES (0, 'Black');
INSERT INTO TEST_TABLE (ID, DESCRIPTION) VALUES (1, 'Maroon');
INSERT INTO TEST_TABLE (ID, DESCRIPTION) VALUES (2, 'Green');
INSERT INTO TEST_TABLE (ID, DESCRIPTION) VALUES (3, 'Olive Green');

/******************** PERMISSIONS **********************/

GRANT R_WEB TO WEB;

GRANT DELETE, INSERT, REFERENCES, SELECT, UPDATE
 ON TEST_TABLE TO ROLE R_WEB;