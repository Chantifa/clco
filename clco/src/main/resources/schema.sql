DROP TABLE IF EXISTS quote;

CREATE TABLE public.quote (
                            id     INTEGER NOT NULL CONSTRAINT quote_pk PRIMARY KEY,
                            author VARCHAR(255),
                            "quote"  VARCHAR(500)
);

ALTER TABLE public.quote OWNER TO chantifa;

create sequence public.quote_seq
    as integer;

alter sequence public.quote_seq owner to chantifa;
