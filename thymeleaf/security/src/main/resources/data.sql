insert into role values (1,'ADMIN');
-- password 123 generated with https://bcrypt-generator.com/ with 10 Rounds
-- @Autowired
-- private PasswordEncoder passwordEncoder;
-- String pass = passwordEncoder.encode("123");
-- 123 - $2a$10$jy7.6rsCsMlY0oRlQPfegeiza9AVOu1QD0yXaEwm1VaxUfTNZ9hoS
insert into user values (1, 1, 'admin@email.com', 'owner', 'admin', '123');
insert into user_role values (1, 1);