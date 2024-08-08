-- grade 테이블
INSERT INTO tiggle.grade (grade_name) VALUES
                                          ('VIP'),
                                          ('S'),
                                          ('A');
-- location 테이블
INSERT INTO tiggle.location (location_name) VALUES
                                                ('올림픽공원 올림픽 홀'),
                                                ('인천아시아드 주경기장'),
                                                ('서울랜드'),
                                                ('고척 스카이돔'),
                                                ('플러스 씨어터'),
                                                ('대학로 TOM 관'),
                                                ('대학로 자유극장');
-- category 테이블
INSERT INTO tiggle.category (category_name) VALUES
                                                ('뮤지컬'),
                                                ('콘서트'),
                                                ('연극');
-- program 테이블
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id) VALUES
                                                                                          (null, 19, '2024-07-30', '<시카고>는 여전히 최면을 걸 듯 반짝인다', '<뮤지컬> 시카고', '2024-07-01', '2024-06-28 20:00:00', 145, 1, 2),
                                                                                          (null, 12, '2024-09-30', '눈과 귀를 위한 축제, 전세계 흥행 1위', '<뮤지컬> 라이온 킹', '2024-09-02', '2024-08-10 20:00:00', 150, 1, 3),
                                                                                          (null, 12, '2024-09-30', '오즈를 방문하시는 관객들을 위한 가이드', '<뮤지컬> 위키드', '2024-09-03', '2024-08-05 20:00:00', 170, 1, 4),
                                                                                          (null, 12, '2024-09-30', '전 세계가 사랑한 영원한 명작 캣츠', '<뮤지컬> 캣츠', '2024-09-01', '2024-08-04 20:00:00', 130, 1, 5),
                                                                                          (null, 12, '2024-09-07', '놓치면 안 될 올해 최고의 화제작, 가슴 벅찬 감동!', '<뮤지컬> 베토벤 교향곡', '2024-08-01', '2024-07-31 20:00:00', 170, 2, 1),
                                                                                          (null, 12, '2024-08-01', '네 남녀가 만나면서 일어나는 레트로 코믹극', '<연극> 서울 대학로 연극 라면', '2024-07-01', '2024-06-15 20:00:00', 100, 3, 4),
                                                                                          (null, 12, '2024-07-20', '여러분의 어떤날들을 선명하게 해줄 플레이리스트', '<콘서트> 유승우 앨범 발매 기념 콘서트', '2024-07-01', '2024-06-15 20:00:00', 105, 2, 3),
                                                                                          (null, 12, '2024-09-30', 'MONSTA X 7TH OFFICIAL FANCLUB MONBEBE FAN－CONCERT', '<콘서트> 2024 MONSTA X', '2024-09-01', '2024-08-02 20:00:00', 110, 2, 4),
                                                                                          (null, 12, '2024-09-30', '유애나들을 위한 아이유의 공연 ! 전국을 찾아갑니다 !', '<콘서트> 2024 IU H．E．R．WORLD TOUR CONCERT IN SEOUL', '2024-09-01', '2024-08-01 20:00:00', 150, 2, 5),
                                                                                          (null, 14, '2024-09-30', '2025 여름특별공연 연극 새빨간 거짓말', '<연극> 2025 새빨간 거짓말', '2024-09-01', '2024-07-31 20:00:00', 90, 3, 5),
                                                                                          (null, 8, '2024-09-30', '준생俊生：영웅으로 살다．', '<연극> 준생 俊生', '2024-09-01', '2024-07-30 20:00:00', 70, 3, 5),
                                                                                          (null, 14, '2024-10-30', '프랑스 연극의 현재 조엘 폼므라가 그리는 미래', '<연극> 조엘 폼므라 ‘이야기와 전설’', '2024-10-01', '2024-09-15 20:00:00', 110, 3, 5),
                                                                                          (null, 14, '2024-09-30', '피나를 통해 들여다보는 우리의 과거와 현재', '<연극> Creator’s Box：‘P와 함께 춤을’', '2024-09-01', '2024-07-28 20:00:00', 120, 3, 5),
                                                                                          (null, 12, '2024-09-30', '최정윤의 청량하고 맑은 목소리로 함께하는 여름 콘서트！', '<콘서트> 최정윤 단독 콘서트 ‘summer swim’', '2024-09-01', '2024-08-20 20:00:00', 90, 2, 5),
                                                                                          (null, 12, '2024-09-30', '광야로 걸어가는 에스파의 2025년 콘서트! ', '<콘서트> 2025 aespa LIVE TOUR－SYNK：PARALLEL LINE－', '2024-09-01', '2024-07-26 20:00:00', 120, 2, 5);
INSERT INTO tiggle.section (location_id, grade_id, section_name, row_count, column_count) VALUES
                                                                                       (1, 1, 'Square', 5, 5),
                                                                                       (1, 1, 'Rectangle', 5, 5),
                                                                                       (1, 1, 'Cross', 5, 5),
                                                                                       (1, 1, 'L Shape', 4, 4),
                                                                                       (1, 1, 'Inverted L Shape', 4, 4);
-- program_grade 테이블
INSERT INTO tiggle.program_grade (price, grade_id, program_id) VALUES
                                                                   (100000, 1, 1),
                                                                   (80000, 2, 1),
                                                                   (60000, 3, 1),
                                                                   (120000, 1, 2),
                                                                   (90000, 2, 2),
                                                                   (70000, 3, 2),
                                                                   (110000, 1, 3),
                                                                   (85000, 2, 3),
                                                                   (65000, 3, 3),
                                                                   (130000, 1, 4),
                                                                   (95000, 2, 4),
                                                                   (75000, 3, 4),
                                                                   (90000, 1, 5),
                                                                   (70000, 2, 5),
                                                                   (50000, 3, 5),
                                                                   (150000, 1, 6),
                                                                   (110000, 2, 6),
                                                                   (90000, 3, 6),
                                                                   (140000, 1, 7),
                                                                   (100000, 2, 7),
                                                                   (80000, 3, 7),
                                                                   (130000, 1, 8),
                                                                   (95000, 2, 8),
                                                                   (75000, 3, 8),
                                                                   (120000, 1, 9),
                                                                   (90000, 2, 9),
                                                                   (70000, 3, 9),
                                                                   (110000, 1, 10),
                                                                   (85000, 2, 10),
                                                                   (65000, 3, 10);
-- times 테이블
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id) VALUES
                                                                         ('2024-08-28 19:00:00.000000', '2024-08-28 18:30:00.000000', 1, 1),
                                                                         ('2024-08-29 19:00:00.000000', '2024-08-29 18:30:00.000000', 2, 1),
                                                                         ('2024-08-30 19:00:00.000000', '2024-08-30 18:30:00.000000', 3, 1),
                                                                         ('2024-08-31 19:00:00.000000', '2024-08-31 18:30:00.000000', 4, 1),
                                                                         ('2024-09-01 19:00:00.000000', '2024-09-01 18:30:00.000000', 5, 1),
                                                                         ('2024-09-02 19:00:00.000000', '2024-09-02 18:30:00.000000', 6, 1),
                                                                         ('2024-09-03 19:00:00.000000', '2024-09-03 18:30:00.000000', 7, 1),
                                                                         ('2024-09-04 19:00:00.000000', '2024-09-04 18:30:00.000000', 8, 1),
                                                                         ('2024-09-05 19:00:00.000000', '2024-09-05 18:30:00.000000', 9, 1),
                                                                         ('2024-09-06 19:00:00.000000', '2024-09-06 18:30:00.000000', 10, 1),
                                                                         ('2024-08-24 19:00:00.000000', '2024-08-24 18:30:00.000000', 1, 2),
                                                                         ('2024-08-25 19:00:00.000000', '2024-08-25 18:30:00.000000', 2, 2),
                                                                         ('2024-08-26 19:00:00.000000', '2024-08-26 18:30:00.000000', 3, 2),
                                                                         ('2024-08-27 19:00:00.000000', '2024-08-27 18:30:00.000000', 4, 2),
                                                                         ('2024-08-28 19:00:00.000000', '2024-08-28 18:30:00.000000', 5, 2),
                                                                         ('2024-08-29 19:00:00.000000', '2024-08-29 18:30:00.000000', 6, 2),
                                                                         ('2024-08-30 19:00:00.000000', '2024-08-30 18:30:00.000000', 7, 2),
                                                                         ('2024-08-31 19:00:00.000000', '2024-08-31 18:30:00.000000', 8, 2),
                                                                         ('2024-09-01 19:00:00.000000', '2024-09-01 18:30:00.000000', 9, 2),
                                                                         ('2024-09-02 19:00:00.000000', '2024-09-02 18:30:00.000000', 10, 2),

                                                                         ('2024-09-16 19:00:00.000000', '2024-09-16 18:30:00.000000', 1, 3),
                                                                         ('2024-09-17 19:00:00.000000', '2024-09-17 18:30:00.000000', 2, 3),
                                                                         ('2024-09-18 19:00:00.000000', '2024-09-18 18:30:00.000000', 3, 3),
                                                                         ('2024-09-19 19:00:00.000000', '2024-09-19 18:30:00.000000', 4, 3),
                                                                         ('2024-09-20 19:00:00.000000', '2024-09-20 18:30:00.000000', 5, 3),
                                                                         ('2024-09-21 19:00:00.000000', '2024-09-21 18:30:00.000000', 6, 3),
                                                                         ('2024-09-22 19:00:00.000000', '2024-09-22 18:30:00.000000', 7, 3),
                                                                         ('2024-09-23 19:00:00.000000', '2024-09-23 18:30:00.000000', 8, 3),
                                                                         ('2024-09-24 19:00:00.000000', '2024-09-24 18:30:00.000000', 9, 3),
                                                                         ('2024-09-25 19:00:00.000000', '2024-09-25 18:30:00.000000', 10, 3),

                                                                         ('2024-08-21 19:00:00.000000', '2024-08-21 18:30:00.000000', 1, 4),
                                                                         ('2024-08-22 19:00:00.000000', '2024-08-22 18:30:00.000000', 2, 4),
                                                                         ('2024-08-23 19:00:00.000000', '2024-08-23 18:30:00.000000', 3, 4),
                                                                         ('2024-08-24 19:00:00.000000', '2024-08-24 18:30:00.000000', 4, 4),
                                                                         ('2024-08-25 19:00:00.000000', '2024-08-25 18:30:00.000000', 5, 4),
                                                                         ('2024-08-26 19:00:00.000000', '2024-08-26 18:30:00.000000', 6, 4),
                                                                         ('2024-08-27 19:00:00.000000', '2024-08-27 18:30:00.000000', 7, 4),
                                                                         ('2024-08-28 19:00:00.000000', '2024-08-28 18:30:00.000000', 8, 4),
                                                                         ('2024-08-29 19:00:00.000000', '2024-08-29 18:30:00.000000', 9, 4),
                                                                         ('2024-08-30 19:00:00.000000', '2024-08-30 18:30:00.000000', 10, 4),

                                                                         ('2024-09-01 19:00:00.000000', '2024-09-01 18:30:00.000000', 1, 5),
                                                                         ('2024-09-02 19:00:00.000000', '2024-09-02 18:30:00.000000', 2, 5),
                                                                         ('2024-09-03 19:00:00.000000', '2024-09-03 18:30:00.000000', 3, 5),
                                                                         ('2024-09-04 19:00:00.000000', '2024-09-04 18:30:00.000000', 4, 5),
                                                                         ('2024-09-05 19:00:00.000000', '2024-09-05 18:30:00.000000', 5, 5);
INSERT INTO tiggle.seat (section_id, seat_number, row, active) VALUES
-- Square
(1, 1, 'A', true),
(1, 2, 'A', true),
(1, 3, 'A', true),
(1, 4, 'A', true),
(1, 5, 'A', true),
(1, 1, 'B', true),
(1, 2, 'B', true),
(1, 3, 'B', true),
(1, 4, 'B', true),
(1, 5, 'B', true),
(1, 1, 'C', true),
(1, 2, 'C', true),
(1, 3, 'C', true),
(1, 4, 'C', true),
(1, 5, 'C', true),
(1, 1, 'D', true),
(1, 2, 'D', true),
(1, 3, 'D', true),
(1, 4, 'D', true),
(1, 5, 'D', true),
(1, 1, 'E', true),
(1, 2, 'E', true),
(1, 3, 'E', true),
(1, 4, 'E', true),
(1, 5, 'E', true),

-- Rectangle
(2, 1, 'A', true),
(2, 2, 'A', true),
(2, 3, 'A', true),
(2, 4, 'A', false),
(2, 5, 'A', false),
(2, 1, 'B', true),
(2, 2, 'B', true),
(2, 3, 'B', true),
(2, 4, 'B', false),
(2, 5, 'B', false),
(2, 1, 'C', true),
(2, 2, 'C', true),
(2, 3, 'C', true),
(2, 4, 'C', false),
(2, 5, 'C', false),

-- Cross
(3, 1, 'A', false),
(3, 2, 'A', false),
(3, 3, 'A', true),
(3, 4, 'A', false),
(3, 5, 'A', false),
(3, 1, 'B', false),
(3, 2, 'B', true),
(3, 3, 'B', true),
(3, 4, 'B', true),
(3, 5, 'B', false),
(3, 1, 'C', true),
(3, 2, 'C', true),
(3, 3, 'C', true),
(3, 4, 'C', true),
(3, 5, 'C', true),
(3, 1, 'D', false),
(3, 2, 'D', true),
(3, 3, 'D', true),
(3, 4, 'D', true),
(3, 5, 'D', false),
(3, 1, 'E', false),
(3, 2, 'E', false),
(3, 3, 'E', true),
(3, 4, 'E', false),
(3, 5, 'E', false),

-- L Shape
(4, 1, 'A', true),
(4, 2, 'A', true),
(4, 3, 'A', true),
(4, 4, 'A', true),
(4, 1, 'B', true),
(4, 2, 'B', true),
(4, 3, 'B', true),
(4, 4, 'B', false),
(4, 1, 'C', true),
(4, 2, 'C', true),
(4, 3, 'C', false),
(4, 4, 'C', false),
(4, 1, 'D', true),
(4, 2, 'D', false),
(4, 3, 'D', false),
(4, 4, 'D', false),

-- Inverted L Shape
(5, 1, 'A', true),
(5, 2, 'A', true),
(5, 3, 'A', false),
(5, 4, 'A', false),
(5, 1, 'B', true),
(5, 2, 'B', true),
(5, 3, 'B', true),
(5, 4, 'B', false),
(5, 1, 'C', true),
(5, 2, 'C', false),
(5, 3, 'C', false),
(5, 4, 'C', false),
(5, 1, 'D', true),
(5, 2, 'D', false),
(5, 3, 'D', false),
(5, 4, 'D', false);