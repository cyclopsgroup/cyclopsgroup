
# -----------------------------------------------------------------------
# ID_TABLE
# -----------------------------------------------------------------------
drop table if exists ID_TABLE;

CREATE TABLE ID_TABLE
(
        ID_TABLE_ID $type $nullString $autoIncrement,
        TABLE_NAME $type (255) $nullString $autoIncrement,
        NEXT_ID $type $nullString $autoIncrement,
        QUANTITY $type $nullString $autoIncrement,
    PRIMARY KEY(ID_TABLE_ID),
    UNIQUE (TABLE_NAME)
);
  
