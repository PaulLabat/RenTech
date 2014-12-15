connect 'jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true';
insert into ADMIN(ID, MAIL, MDP, NOM)
values (0, 'admin@rentech.com', 'dc76e9f0c0006e8f919e0c515c66dbba3982f785', 'admin');
select * from ADMIN;
disconnect;
exit;