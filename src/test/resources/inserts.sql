
INSERT INTO brouwers(id,naam,straat,huisNr,postcode,gemeente,omzet) VALUES
(11111,'Testbrouwer1','Teststraat1',10,1000,'Testgemeente1',10000),
(22222,'Testbrouwer2','Teststraat2',20,2000,'Testgemeente2',20000);
INSERT INTO bieren   (naam,alcohol,brouwerid,prijs,besteld)  VALUES
('test1',5,11111,10,10),
('test2',6.5,11111,20,10),
('test3',5,22222,10,10),
('test4',6.5,22222,20,10);
INSERT INTO bestelbonnen(id,naam,straat,huisNr,postcode,gemeente) VALUES
(9999,'Testklant1','Teststraat1',10,1000,'Testgemeente1');

