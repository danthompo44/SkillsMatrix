create table personal_details
(
    id                  int auto_increment primary key,
    first_name          varchar(50) not null,
    surname             varchar(50) not null,
    address_first_line  varchar(50) not null,
    address_second_line varchar(50) not null,
    county              varchar(50) not null,
    postcode            varchar(50) not null
);

create table role
(
    id   int auto_increment primary key,
    type varchar(45) not null
);

create table skill_category
(
    id          int auto_increment primary key,
    description varchar(50) not null
);

create table app_user
(
    id         int auto_increment primary key,
    username   varchar(50) unique,
    password   varchar(255) not null,
    email      varchar(50)  not null unique
);

create table skill
(
    id          int auto_increment primary key,
    name        varchar(50) not null,
    category_id int         not null,
    foreign key (category_id) references skill_category(id)
);

create table manager
(
    id int auto_increment primary key,
    details_id int not null,
    user_id int not null,
    foreign key (details_id) references personal_details(id),
    foreign key (user_id) references app_user(id)
);

create table staff
(
    id int auto_increment primary key,
    details_id int not null,
    user_id int not null,
    manager_id int not null,
    foreign key (details_id) references personal_details(id),
    foreign key (user_id) references app_user(id),
    foreign key (manager_id) references manager(id)
);

create table staff_skill
(
    id      int auto_increment primary key,
    staff_id int not null,
    skill_id int not null,
    expiry_date date not null,
    skill_strength int not null,
    foreign key (staff_id) references staff (id),
    foreign key (skill_id) references skill (id)
);

create table app_user_role(
    id int auto_increment primary key,
    user_id int not null,
    role_id int not null,
    foreign key(user_id) references app_user(id),
    foreign key(role_id) references role(id)
);
