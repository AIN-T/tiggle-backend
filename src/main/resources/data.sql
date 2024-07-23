INSERT INTO tiggle.grade (grade_name)
VALUES ('VIP');
INSERT INTO tiggle.grade (grade_name)
VALUES ('S');
INSERT INTO tiggle.grade (grade_name)
VALUES ('A');
INSERT INTO tiggle.location (location_name)
VALUES ('올림픽공원 올림픽 홀');
INSERT INTO tiggle.location (location_name)
VALUES ('인천아시아드 주경기장');
INSERT INTO tiggle.location (location_name)
VALUES ('서울랜드');
INSERT INTO tiggle.location (location_name)
VALUES ('고척 스카이돔');
INSERT INTO tiggle.location (location_name)
VALUES ('플러스 씨어터');
INSERT INTO tiggle.location (location_name)
VALUES ('대학로 TOM 관');
INSERT INTO tiggle.location (location_name)
VALUES ('대학로 자유극장');
INSERT INTO tiggle.category (category_name)
VALUES ('뮤지컬');
INSERT INTO tiggle.category (category_name)
VALUES ('콘서트');
INSERT INTO tiggle.category (category_name)
VALUES ('연극');
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 14, '2024-09-29 16:24:34.000000', '뮤지컬 〈시카고〉', '시카고', '2024-06-27 16:25:34.000000',
        '2024-05-01 16:25:46.000000', 145, 1, 1);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 12, '2024-10-31 19:00:00.000000', '뮤지컬 〈레 미제라블〉', '레 미제라블', '2024-08-01 19:00:00.000000',
        '2024-06-01 10:00:00.000000', 160, 1, 2);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 8, '2024-12-15 19:30:00.000000', '뮤지컬 〈라이온 킹〉', '라이온 킹', '2024-09-15 19:30:00.000000',
        '2024-07-01 09:00:00.000000', 135, 1, 3);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 10, '2024-11-20 18:00:00.000000', '뮤지컬 〈위키드〉', '위키드', '2024-08-20 18:00:00.000000',
        '2024-06-15 12:00:00.000000', 150, 1, 4);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 7, '2024-08-30 20:00:00.000000', '뮤지컬 〈캣츠〉', '캣츠', '2024-05-30 20:00:00.000000',
        '2024-04-01 11:00:00.000000', 130, 1, 5);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 15, '2024-09-30 21:00:00.000000', '공연 〈베토벤 교향곡〉', '베토벤 교향곡', '2024-07-01 21:00:00.000000',
        '2024-05-01 14:00:00.000000', 120, 2, 1);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 16, '2024-11-10 19:00:00.000000', '공연 〈모차르트 레퀴엠〉', '모차르트 레퀴엠', '2024-09-10 19:00:00.000000',
        '2024-07-10 10:00:00.000000', 90, 2, 2);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 18, '2024-10-25 18:30:00.000000', '공연 〈바흐 무반주 첼로 모음곡〉', '바흐 무반주 첼로 모음곡', '2024-08-25 18:30:00.000000',
        '2024-06-25 09:00:00.000000', 105, 2, 3);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 13, '2024-12-05 20:00:00.000000', '공연 〈쇼팽 피아노 협주곡〉', '쇼팽 피아노 협주곡', '2024-09-05 20:00:00.000000',
        '2024-07-05 11:00:00.000000', 110, 2, 4);
INSERT INTO tiggle.program (id, age, program_end_date, program_info, program_name, program_start_date,
                            reservation_open_date, runtime, category_id, location_id)
VALUES (null, 12, '2024-08-15 19:30:00.000000', '공연 〈비발디 사계〉', '비발디 사계', '2024-06-15 19:30:00.000000',
        '2024-05-01 12:00:00.000000', 100, 2, 5);
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (100000, 1, 1); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (80000, 2, 1); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (60000, 3, 1); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (120000, 1, 2); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (90000, 2, 2); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (70000, 3, 2); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (110000, 1, 3); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (85000, 2, 3); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (65000, 3, 3); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (130000, 1, 4); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (95000, 2, 4); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (75000, 3, 4); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (90000, 1, 5); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (70000, 2, 5); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (50000, 3, 5); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (150000, 1, 6); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (110000, 2, 6); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (90000, 3, 6); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (140000, 1, 7); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (100000, 2, 7); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (80000, 3, 7); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (130000, 1, 8); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (95000, 2, 8); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (75000, 3, 8); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (120000, 1, 9); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (90000, 2, 9); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (70000, 3, 9); -- A
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (110000, 1, 10); -- VIP
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (85000, 2, 10); -- S
INSERT INTO tiggle.program_grade (price, grade_id, program_id)
VALUES (65000, 3, 10); -- A
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-06-28 19:00:00.000000', '2024-06-28 18:30:00.000000', 1, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-06-29 19:00:00.000000', '2024-06-29 18:30:00.000000', 2, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-06-30 19:00:00.000000', '2024-06-30 18:30:00.000000', 3, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-02 19:00:00.000000', '2024-08-02 18:30:00.000000', 1, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-03 19:00:00.000000', '2024-08-03 18:30:00.000000', 2, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-04 19:00:00.000000', '2024-08-04 18:30:00.000000', 3, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-09-16 19:30:00.000000', '2024-09-16 19:00:00.000000', 1, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-09-17 19:30:00.000000', '2024-09-17 19:00:00.000000', 2, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-09-18 19:30:00.000000', '2024-09-18 19:00:00.000000', 3, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-21 18:00:00.000000', '2024-08-21 17:30:00.000000', 1, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-22 18:00:00.000000', '2024-08-22 17:30:00.000000', 2, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-23 18:00:00.000000', '2024-08-23 17:30:00.000000', 3, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-05-31 20:00:00.000000', '2024-05-31 19:30:00.000000', 1, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-06-01 20:00:00.000000', '2024-06-01 19:30:00.000000', 2, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-06-02 20:00:00.000000', '2024-06-02 19:30:00.000000', 3, 1);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-07-02 21:00:00.000000', '2024-07-02 20:30:00.000000', 1, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-07-03 21:00:00.000000', '2024-07-03 20:30:00.000000', 2, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-07-04 21:00:00.000000', '2024-07-04 20:30:00.000000', 3, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-09-11 19:00:00.000000', '2024-09-11 18:30:00.000000', 1, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-09-12 19:00:00.000000', '2024-09-12 18:30:00.000000', 2, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-09-13 19:00:00.000000', '2024-09-13 18:30:00.000000', 3, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-26 18:30:00.000000', '2024-08-26 18:00:00.000000', 1, 2);
INSERT INTO tiggle.times (date, limit_enter_time, round, program_id)
VALUES ('2024-08-27 18:30:00.000000', '2024-08-27 18:00:00.000000', 2, 2);
INSERT INTO tiggle.section (section_name, grade_id, location_id)
VALUES ('A구역', 1, 1);
INSERT INTO tiggle.section (section_name, grade_id, location_id)
VALUES ('B구역', 2, 1);
INSERT INTO tiggle.section (section_name, grade_id, location_id)
VALUES ('C구역', 3, 1);
INSERT INTO tiggle.section (section_name, grade_id, location_id)
VALUES ('D구역', 1, 2);
INSERT INTO tiggle.section (section_name, grade_id, location_id)
VALUES ('E구역', 2, 2);
INSERT INTO tiggle.section (section_name, grade_id, location_id)
VALUES ('F구역', 3, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (1, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (2, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (3, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (4, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (5, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (6, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (7, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (8, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (9, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (10, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (11, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (12, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (13, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (14, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (15, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (16, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (17, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (18, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (19, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (20, 1);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (1, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (2, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (3, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (4, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (5, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (6, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (7, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (8, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (9, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (10, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (11, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (12, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (13, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (14, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (15, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (16, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (17, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (18, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (19, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (20, 2);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (1, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (2, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (3, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (4, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (5, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (6, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (7, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (8, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (9, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (10, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (11, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (12, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (13, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (14, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (15, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (16, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (17, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (18, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (19, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (20, 3);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (1, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (2, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (3, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (4, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (5, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (6, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (7, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (8, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (9, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (10, 4);
INSERT INTO tiggle.seat (seat_number, section_id)
VALUES (11, 4);
INSERT INTO tiggle.user (created_at, email, enable, login_type, name, password, phone_number, region_1depth_name,
                         region_2depth_name, region_3depth_name, region_4depth_name, role, status, verified_at)
VALUES ('2024-07-15 15:11:24.000000', 'test@gmail.com', true, null, '홍길동', 'qwer1234', '01012341234', null, null, null,
        null, 'ADMIN', true, '2024-07-16 15:12:19.000000');
INSERT INTO tiggle.user (created_at, email, enable, login_type, name, password, phone_number, region_1depth_name,
                         region_2depth_name, region_3depth_name, region_4depth_name, role, status, verified_at)
VALUES ('2024-07-15 15:14:00.000000', 'test02@gmail.com', true, null, '홍이동', 'qwer1234', '01012341234', null, null,
        null, null, 'ADMIN', true, '2024-07-17 15:13:19.000000');
INSERT INTO tiggle.reservation (available, pay_method, request_limit, status, ticket_number, total_price, program_id,
                                seat_id, times_id, user_id)
VALUES (DEFAULT, null, null, 'COMPLETED', '20240721130104-g1az', 100000, 4, 3, 1, 1);
INSERT INTO tiggle.reservation (available, pay_method, request_limit, status, ticket_number, total_price, program_id,
                                seat_id, times_id, user_id)
VALUES (DEFAULT, null, null, 'IN_PROGRESS', '20240721130213-fe12', 80000, 4, 4, 1, 2);
INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:35.000000', null, 1, 1, 1, 1, null, '20240721140511-fdsz', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:36.000000', null, 1, 2, 1, 1, null, '20240721130223-qe14', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:37.000000', null, 1, 3, 1, 2, null, '20240721130232-fcgh', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:38.000000', null, 1, 4, 1, 2, null, '20240721130211-fe23', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:39.000000', null, 1, 5, 1, 2, null, '20240721130213-fe5f', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:40.000000', null, 1, 6, 1, 2, null, '20240721130213-fea2', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:58:41.000000', null, 1, 7, 1, 2, null, '20240721130213-fd1d', null);

INSERT INTO tiggle.reservation (available, request_limit, total_price, created_at, modified_at, program_id, seat_id,
                                times_id, user_id, pay_method, ticket_number, status)
VALUES (DEFAULT, 5, 100000, '2024-07-23 16:57:42.000000', null, 1, 8, 1, 2, null, '20240721130213-fexw', null);