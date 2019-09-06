insert into role values (1,'ADMIN');
-- password: 123 - $2a$10$50mE1ujAbfY4uv.zgY.Jh.nB1sjQPhyv4zTmRkoyz6KGvfi.RTyaK
insert into user values (1, true , true, true, 'admin@email.com', true, 'owner', 'admin', '$2a$10$50mE1ujAbfY4uv.zgY.Jh.nB1sjQPhyv4zTmRkoyz6KGvfi.RTyaK');
insert into user_role values (1, 1);