-- we don't know how to generate root <with-no-name> (class Root) :(
CREATE SCHEMA IF NOT EXISTS MAIN;
SET SCHEMA MAIN;

create sequence SEQUENCE_URLS
    as INTEGER;

create table TABLE_URLS
(
    ID        LONG           not null,
    SHORTEN_URL CHARACTER VARYING not null,
    FULL_URL  CHARACTER VARYING not null,
    constraint PRIMARY_ID
        primary key (ID)
);

create index TABLE_URLS_URL_REDIR_INDEX
    on TABLE_URLS (SHORTEN_URL);

