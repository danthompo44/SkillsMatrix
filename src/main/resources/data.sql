insert into skill_category(description) values ('Software');
insert into skill_category(description) values ('People');
insert into skill_category(description) values ('Networking');
insert into skill_category(description) values ('Management');
insert into skill_category(description) values ('Cyber Security');
create sequence skill_category_sequence_id  start  with (select max(id) + 1 from skill_category);

insert into skill (name, category_Id) values ('Java', 1);
insert into skill (name, category_Id) values ('Automation', 1);
insert into skill (name, category_Id) values ('VLANs', 3);
insert into skill (name, category_Id) values ('Communication', 2);
insert into skill (name, category_Id) values ('Penetration Testing', 5);
create sequence skill_sequence_id start  with (select max(id) + 1 from skill);

insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Dan', 'Thompson', '44 Garsdale Road', 'Weston-Super-Mare', 'Somerset', 'BS22 8PU');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Matt', 'Morgan', '15 Fulmar Road', 'Weston-Super-Mare', 'Somerset', 'BS22 6EJ');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('John', 'Oakes', '29 The Weind', 'Weston-Super-Mare', 'Somerset', 'BS22 9JT');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Darren', 'Hinton', '55 At Worlds End', 'Honiton', 'Somerset', 'BD45 2ET');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Lucy', 'Reed', '27 Sandpiper Drive', 'Norwich', 'Norfolk', 'NW22 6TD');
insert into personal_details (first_name, surname, address_first_line, address_second_line, county, postcode) values ('Isaac', 'Sharkey', '118 Milton Road', 'Bradley Stoke', 'Somerset', 'BS2 7TR');
create sequence personal_details_id  start  with (select max(id) + 1 from personal_details);

insert into role (type) values ('ROLE_USER');
insert into role (type) values ('ROLE_ADMIN');

insert into app_user(username, password, email) values ('user', '$2a$10$0tkDsotEK5feenJ.9wl5OO8TV5OB.gO7hzBMX1z.LFsIF/8OmpAiC', 'matt@morgan.com');
insert into app_user(username, password, email) values ('user1', '$2a$10$0tkDsotEK5feenJ.9wl5OO8TV5OB.gO7hzBMX1z.LFsIF/8OmpAiC', 'john@oakes.com');
insert into app_user(username, password, email) values ('user2', '$2a$10$0tkDsotEK5feenJ.9wl5OO8TV5OB.gO7hzBMX1z.LFsIF/8OmpAiC', 'darren@hinton.co.uk');
insert into app_user(username, password, email) values ('admin', '$2a$10$Pyv1dhPciGaim10Xy7QHM.dpzfOfF1WfksB8zCYVoZnTOmtaDsiL6', 'dan@thompson.co.uk');
insert into app_user(username, password, email) values ('admin1', '$2a$10$Pyv1dhPciGaim10Xy7QHM.dpzfOfF1WfksB8zCYVoZnTOmtaDsiL6', 'lucy@reed.co.uk');
insert into app_user(username, password, email) values ('user3', '$2a$10$0tkDsotEK5feenJ.9wl5OO8TV5OB.gO7hzBMX1z.LFsIF/8OmpAiC', 'isaac@sharkey.com');

insert into manager(details_id, user_id) values (1, 4);
insert into manager(details_id, user_id) values (5, 5);

insert into staff(details_id, user_id, manager_id) values (2, 1, 1);
insert into staff(details_id, user_id, manager_id) values (3, 2, 1);
insert into staff(details_id, user_id, manager_id) values (4, 3, 2);
insert into staff(details_id, user_id, manager_id) values (6, 6, 2);


insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-06-21', '2022-06-21', 8, 1, 1);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-06-21', '2023-06-21', 7, 1, 2);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-08-21', '2022-08-21', 9, 2, 3);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-06-13', '2023-06-13', 5, 2, 2);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-02-19', '2023-02-19', 8, 2, 5);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-02-19', '2023-02-19', 5, 4, 2);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-02-19', '2023-02-19', 9, 4, 3);
insert into staff_skill(created_at, expiry_date, skill_strength, staff_id, skill_id) values ('2021-02-19', '2023-02-19', 4, 4, 5);

insert into app_user_role (user_id, role_id) values (1,1);
insert into app_user_role (user_id, role_id) values (2,1);
insert into app_user_role (user_id, role_id) values (3,1);
insert into app_user_role (user_id, role_id) values (4,2);
insert into app_user_role (user_id, role_id) values (5,2);
insert into app_user_role (user_id, role_id) values (6,1);


