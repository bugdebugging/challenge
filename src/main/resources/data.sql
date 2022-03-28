-- challenges
insert into challenges (name, start_time, end_time, created_at, updated_at)
values ('round511 Div2', '2022-02-05 12:00:00', '2022-02-05 15:00:00', now(), now());

insert into challenges (name, start_time, end_time, created_at, updated_at)
values ('round512 Div3', '2022-02-01 18:00:00', '2022-02-01 20:00:00', now(), now());

insert into challenges (name, start_time, end_time, created_at, updated_at)
values ('round513 Div1', '2022-01-18 16:00:00', '2022-01-18 18:00:00', now(), now());

insert into challenges (name, start_time, end_time, created_at, updated_at)
values ('Educational Round', '2022-03-01 12:00:00', '2022-03-01 14:00:00', now(), now());

insert into challenges (name, start_time, end_time, created_at, updated_at)
values ('Global Round', '2022-03-02 12:00:00', '2022-03-02 14:00:00', now(), now());

--authors
--questions
-- 1
insert into questions(problem_id, title, challenge_id)
values (1L, 'greedy1', 1L);
insert into questions(problem_id, title, challenge_id)
values (2L, 'greedy2', 1L);
insert into questions(problem_id, title, challenge_id)
values (3L, 'dp1', 1L);
insert into questions(problem_id, title, challenge_id)
values (4L, 'dp2', 1L);
-- 2
insert into questions(problem_id, title, challenge_id)
values (1L, 'greedy1', 2L);
insert into questions(problem_id, title, challenge_id)
values (5L, 'math2', 2L);
-- 3
-- 4
insert into questions(problem_id, title, challenge_id)
values (6L, 'dfs', 4L);
-- 5
insert into questions(problem_id, title, challenge_id)
values (7L, 'graph', 5L);

--participations
-- 1
insert into participations(name, challenge_id, score)
values ('tourist', 1L, 1599);
insert into participations(name, challenge_id, score)
values ('ReReRERE', 1L, 2700);
insert into participations(name, challenge_id, score)
values ('koosaga', 1L, 1300);
-- 3
insert into participations(name, challenge_id)
values ('tourist', 3L);
insert into participations(name, challenge_id)
values ('umnik', 3L);
insert into participations(name, challenge_id)
values ('koosaga', 3L);
-- 4
insert into participations(name, challenge_id)
values ('koosaga', 4L);
-- 5
insert into participations(name, challenge_id)
values ('rie', 5L);
insert into participations(name, challenge_id)
values ('isvara', 5L);

--author
-- 1
insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (1L, 2000, 'tester1', 100L);
insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (1L, 2500, 'tester2', 101L);

-- 2
insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (2L, 2500, 'tester2', 101L);

-- 4
insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (4L, 2150, 'vovuh', 400L);
insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (4L, 1846, 'kisa', 401L);
insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (4L, 2200, 'koru', 402L);

insert into challenge_authors(challenge_id, accumulate_score, name, user_id)
values (5L, 2200, 'koru', 402L);
