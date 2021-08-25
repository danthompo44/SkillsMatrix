insert into skill_category(description) values ('Software');
insert into skill_category(description) values ('People');
insert into skill_category(description) values ('Networking');
create sequence skill_category_sequence_id  start  with (select max(id) + 1 from skill_category);

insert into skill (name, category_Id) values ('Java', 1);
insert into skill (name, category_Id) values ('Automation', 1);
insert into skill (name, category_Id) values ('VLANs', 3);
insert into skill (name, category_Id) values ('Communication', 2);
create sequence skill_sequence_id start  with (select max(id) + 1 from skill);

insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Dan', 'Thompson', '44 Garsdale Road', 'Weston-Super-Mare', 'Somerset', 'BS22 8PU');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Matt', 'Morgan', '15 Fulmar Road', 'Weston-Super-Mare', 'Somerset', 'BS22 6EJ');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('John', 'Oakes', '29 The Weind', 'Weston-Super-Mare', 'Somerset', 'BS22 9JT');
create sequence personal_details_id  start  with (select max(id) + 1 from personal_details);

insert into role (type) values ('ROLE_USER');
insert into role (type) values ('ROLE_ADMIN');

insert into app_user(username, password, email, details_id, role_id) values ('user1', '$2a$10$0tkDsotEK5feenJ.9wl5OO8TV5OB.gO7hzBMX1z.LFsIF/8OmpAiC', 'user1@email.com', 1, 1);
insert into app_user(username, password, email, details_id, role_id) values ('user2', '$2a$10$0tkDsotEK5feenJ.9wl5OO8TV5OB.gO7hzBMX1z.LFsIF/8OmpAiC', 'user2@email.com', 2, 1);
insert into app_user(username, password, email, details_id, role_id) values ('admin', '$2a$10$Pyv1dhPciGaim10Xy7QHM.dpzfOfF1WfksB8zCYVoZnTOmtaDsiL6', 'admin@email.com', 3, 2);



insert into app_user_skill (user_id, skill_id) values (1,1);
insert into app_user_skill (user_id, skill_id) values (2,2);
insert into app_user_skill (user_id, skill_id) values (3,4);
