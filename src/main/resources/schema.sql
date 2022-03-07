DROP TABLE IF EXISTS challenge_authors;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS submits;
DROP TABLE IF EXISTS participations;
DROP TABLE IF EXISTS challenges;

create table challenges
(
    id         bigint       NOT NULL AUTO_INCREMENT,
    end_time   timestamp    NOT NULL,
    start_time timestamp    NOT NULL,
    created_at timestamp    NOT NULL,
    name       varchar(255) NOT NULL,
    updated_at timestamp    NOT NULL,
    primary key (id),
    INDEX (start_time)
);

create table challenge_authors
(
    challenge_id     bigint not null,
    accumulate_score integer,
    name             varchar(255),
    user_id          bigint,
    FOREIGN KEY (challenge_id) REFERENCES challenges (id)
);

create table participations
(
    id           bigint       NOT NULL AUTO_INCREMENT,
    score        integer      NOT NULL default 0,
    name         varchar(255) NOT NULL,
    user_id      bigint       NOT NULL,
    challenge_id bigint       NOT NULL,
    primary key (id),
    foreign key (challenge_id) REFERENCES challenges (id),
    INDEX (score)
);


create table questions
(
    id           bigint       NOT NULL AUTO_INCREMENT,
    problem_id   bigint       NOT NULL,
    title        varchar(255) NOT NULL,
    challenge_id bigint       NOT NULL,
    primary key (id),
    foreign key (challenge_id) REFERENCES challenges (id)
);


create table submits
(
    id                   bigint       NOT NULL AUTO_INCREMENT,
    problem_id           bigint       NOT NULL,
    programming_language varchar(255) NOT NULL,
    score                integer      NOT NULL default 0,
    source_code          varchar(2048) NOT NULL,
    submit_status        integer      NOT NULL,
    submitted_at         timestamp    NOT NULL,
    participation_id     bigint       NOT NULL,
    primary key (id),
    foreign key (participation_id) REFERENCES participations (id)
);