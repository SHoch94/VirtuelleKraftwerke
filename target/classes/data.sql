insert into type (id, name) values(1, 'Windrad');
insert into type (id, name) values(2, 'Photovoltaikanlage');
insert into type (id, name)  values(3, 'Blockheizkraftwerk');

insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(1, 'Test', 1, 1000, 132000.00, 7890, 'Siemens');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(2, 'Beispiel', 2, 12, 1599.99, 8640, 'Bosch');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(3, 'Versuch', 3, 684, 78540.11, 210, 'Philipps');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(4, 'Experiment', 1, 1582, 65000, 13450, 'Miele');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(5, 'Hund', 2, 852, 6510, 6420, 'WMF');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(6, 'Katze', 3, 946, 680, 45680, 'Samsung');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(7, 'Maus', 1, 947, 5490, 310, 'Huawei');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(8, 'Wurst', 2, 820, 7410, 460, 'LG');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(9, 'Käse', 3, 377, 21030, 840, 'Apple');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(10, 'Holz', 1, 645, 65540, 80, 'Tesla');
insert into powerplant (id, name, type_id, power_conversion, price, runtime_in_hours, manufacturer)values(11, 'Stein', 2, 50, 5850, 58, 'Murks');

insert into user (id, name, password) values (1, 'Admin', '098F6BCD4621D373CADE4E832627B4F6');
insert into user (id, name, password) values (2, 'User', 'EE11CBB19052E40B07AAC0CA060C23EE');