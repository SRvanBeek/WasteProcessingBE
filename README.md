<h1>DATABASE, MYSQL</h1> 

De applicatie is gebaseerd op een database in MYSQL, de database, server en de DBMS om de database te onderhouden, en eventueel aan te passen kan in 1 keer gedownload worden, ga naar deze link  

https://dev.mysql.com/downloads/installer/ 

Volg de instructies, selecteer de MySQL server, workbench en installeer. Aan het einde van de installatie komt de optie ‘Start MySQL server on startup’ voor, vink deze checkbox aan, zodat de database altijd beschikbaar blijft. 

Om de database te vullen, download de volgende SQL dump, en run deze query in MySQL, om de database meteen op te maken, en om te vullen met de benodigde data. 

In MySQL, creeer een nieuwe database, door te klikken op het plusje in de homescreen, vul de configuratie in voor waar de database gehost wordt. Zodra deze verbinding bestaat, klik in de database op File -> Create Schema. Klik hier op import SQL, klik op de SQL file met de queries voor het opstellen van de database. Dubbelklik op het schema waar de query gerund moet worden, zodra de letters van de naam van het schema vetgedrukt zijn, klik op het bliksemschichtje, de query is nu uitgevoerd. Refresh het schema, en de MySQL database is opgesteld, en klaar voor gebruik. 

<h1>BACKEND, JAVA SPRING BOOT</h1>

Om te beginnen, installeer de meest recente versie van Java, via de website van Java: 

https://www.oracle.com/java/technologies/downloads/ 

(Aangeraden wordt om op Java 17, of 19 te runnen, allebei werken voor de backend) 

Van deze repository: https://github.com/Dino-Yang/VDLBE wordt een kopie aangeleverd, wanneer de applicatie overgedragen wordt. Clone deze repository, en open deze in intelliJ(of een IDE naar keuze). Run de backend, en de API runt nu, en kan communiceren met de eerder opgestelde database. 

Alle endpoints staan in de code, als het eventueel nodig is om een nieuwe endpoint toe te voegen, alle Controllers, Services en DAO’s staan allemaal gescheiden in een aparte map, onder Controllers, Services, DAO’s, Models, en Repositories. Iedere functie bevat documentatie, om te beschrijven hoe de functies werken. 

In de application.properties, bij de lines username en password, verander deze naar de eigen ingestelde gebruikersnaam en wachtwoord. De url moet gelijk zijn aan de database connection url in het format: jdbc:mysql://[url] 
![chrome_7ZqBApJDZ0](https://user-images.githubusercontent.com/30380030/215520022-7e250b04-6461-41a3-b728-232cee78a869.png)
