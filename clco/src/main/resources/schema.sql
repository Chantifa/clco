DROP TABLE IF EXISTS quote;

CREATE TABLE clco.quote (
                            id     INTEGER NOT NULL CONSTRAINT quote_pk PRIMARY KEY,
                            author VARCHAR(255),
                            "quote"  VARCHAR(500)
);

ALTER TABLE clco.quote OWNER TO chantifa;

create sequence clco.quote_seq
    as integer;

alter sequence clco.quote_seq owner to chantifa;
