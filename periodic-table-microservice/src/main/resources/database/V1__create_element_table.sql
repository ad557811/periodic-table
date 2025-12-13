CREATE TABLE element
(
    "number"      smallint               NOT NULL,
    name          character varying(255) NOT NULL,
    symbol        character varying(5)   NOT NULL,
    appearance    character varying(1000),
    category      character varying(1000),
    density       numeric,
    discovered_by character varying(1000),
    named_by      character varying(1000),
    summary       text,
    CONSTRAINT element_pkey PRIMARY KEY ("number"),
    CONSTRAINT unique_name UNIQUE (name),
    CONSTRAINT unique_symbol UNIQUE (symbol)
)