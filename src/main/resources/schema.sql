CREATE TABLE IF NOT EXISTS public.users (
    id UUID NOT NULL,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.authorities (
    id UUID NOT NULL,
    username VARCHAR(45) NOT NULL,
    authority VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);
