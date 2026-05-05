-- TEAM
INSERT INTO team (id, name, nationality, deleted) VALUES
(NEXT VALUE FOR team_seq, 'Team Yandex', 'Russia', false),
(NEXT VALUE FOR team_seq, 'Xtreme Gaming', 'China', false),
(NEXT VALUE FOR team_seq, 'T1', 'South Korea', false);

-- GAME PROFILE
INSERT INTO game_profile (id, gamer_tag, games_owned, elo, achivements, deleted) VALUES
(NEXT VALUE FOR gameprofile_seq, 'Faker', 50, 3200, 30, false),
(NEXT VALUE FOR gameprofile_seq, 'Oner', 70, 2900, 45, false),
(NEXT VALUE FOR gameprofile_seq, 'Saksa', 40, 1890, 20, false),
(NEXT VALUE FOR gameprofile_seq, 'Xxs', 90, 2580, 60, false);

-- PLAYER
INSERT INTO player (id, name, age, nationality, team_id, gameprofile_id, deleted) VALUES
(NEXT VALUE FOR player_seq, 'Lee Sang-hyeok', 29, 'Corea del Sud', 3, 1, false),
(NEXT VALUE FOR player_seq, 'Moon Hyeon-jun', 23, 'Corea del Sud', 3, 2, false),
(NEXT VALUE FOR player_seq, 'Martin Sazdov', 31, 'Macedonia del Nord', 1, 3, false),
(NEXT VALUE FOR player_seq, 'Lin Jing', 27, 'Cina', 2, 4, false);