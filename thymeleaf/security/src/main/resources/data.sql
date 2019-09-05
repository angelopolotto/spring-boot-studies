insert into role values (1,'ADMIN');
-- password 123 generated with https://bcrypt-generator.com/ with 10 Rounds
insert into user values (1, 1, 'admin@admin.com', 'owner', 'admin', '$2y$10$ff18NajL21q7fH6vPm7u4uWeXw7XQ0aSZbyWLdytyV0T15CzPkX8a');
insert into user_role values (1, 1);