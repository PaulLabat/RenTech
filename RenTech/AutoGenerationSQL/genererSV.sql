connect 'jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true';
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (0,51,1,7),(1,14,4,6),(2,66,4,4),(3,3,16,2),(4,40,11,8),(5,19,12,2),(6,20,9,1),(7,43,1,1),(8,21,1,4),(9,38,9,5);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (10,94,12,5),(11,39,8,5),(12,50,5,5),(13,91,2,2),(14,19,1,6),(15,8,1,2),(16,83,9,4),(17,80,16,1),(18,30,1,1),(19,54,13,2);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (20,86,7,2),(21,27,7,3),(22,15,3,4),(23,50,9,1),(24,53,10,4),(25,58,7,1),(26,99,5,4),(27,62,10,6),(28,19,6,7),(29,19,1,8);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (30,32,1,6),(31,17,5,3),(32,2,10,3),(33,17,6,6),(34,95,16,8),(35,85,14,8),(36,19,1,8),(37,75,5,3),(38,28,13,4),(39,47,10,4);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (40,41,4,2),(41,82,8,2),(42,39,2,8),(43,61,3,8),(44,82,15,1),(45,27,1,3),(46,23,11,1),(47,15,8,6),(48,22,4,6),(49,99,5,7);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (50,92,5,7),(51,53,13,3),(52,25,1,5),(53,15,2,1),(54,37,4,5),(55,13,3,3),(56,63,12,7),(57,12,1,8),(58,74,11,8),(59,75,11,5);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (60,65,13,1),(61,51,7,7),(62,54,15,4),(63,32,3,8),(64,82,15,2),(65,17,1,1),(66,49,2,1),(67,8,3,1),(68,29,15,4),(69,39,2,3);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (70,79,9,2),(71,58,12,4),(72,71,3,8),(73,27,6,5),(74,84,5,7),(75,37,1,3),(76,1,14,7),(77,69,9,6),(78,13,6,4),(79,39,6,7);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (80,65,2,2),(81,19,7,1),(82,36,15,1),(83,28,8,3),(84,17,12,4),(85,15,14,8),(86,86,3,4),(87,85,4,1),(88,5,15,3),(89,6,1,6);
INSERT INTO SERVEURVIRTUEL (id,tailleDisk,ram,nbreCoeur) VALUES (90,26,16,4),(91,73,5,8),(92,74,14,5),(93,56,15,5),(94,21,16,8),(95,8,4,4),(96,82,9,7),(97,18,3,6),(98,67,2,2),(99,13,3,8);

select * from SERVEURVIRTUEL;
disconnect;
exit;