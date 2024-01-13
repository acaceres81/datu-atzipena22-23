# Dokumentazioa
Proiektu hau exekutatzean menu nagusi bat agertzen da. Bertan csv fitxategia json-era konbertitzeko aukera dugu. Bestalde Rest APIa martxan jarri dezakegu.

## Datuak
Datuak "The 2022 EU Industrial R&D Investment Scoreboard"-eko datuak dira. Bertan munduan I+G-ean gehien inbertitzen duten enpresen datuak agertzen dira. Datuak .xlsx formatuan deskargatu daitezke Scoreboardaren webgunetik (https://iri.jrc.ec.europa.eu/scoreboard/2022-eu-industrial-rd-investment-scoreboard).

Datuak erabili ahal izateko lehenik eta behin csv moduan gorde ditut. Hortaz gain, industry zutabeko 2 industria aldatu behar izan ditut ("Oil Equipment, Services & Distribution" eta "Gas, Water & Multiutilities"). Hauek komak dituzte beraien izenean eta ondorioz, jsonera konbertsioa egitean errorea ematen zuen. Izen berriak "Oil Equipment and Services & Distribution" eta "Gas and Water & Multiutilities".

Datuei sakontasuna emateko, Sector objektua sortu dut, objektu honen barnean Company objektuen array list bat dago. Gainera, enpresaren datu batzuk (R&D, Sales, Employees...) Data objektu baten sartu ditut.

Datuak .csv-tik .json-era pasatzeko konbertsore sinple bat sortu dut.

## Rest zerbitzua
Irudian ikus daitekenez, ondorengo endpointak agertzen dira Swagerrean. Put, Post, Get eta Delete motakoak daude.
![Swagger](../Swagger.png)

Ondorengo taulan endpointak azalduta daude.
![Endpoint taulan](<../endpointen taula.png>)

## Mongo
Datuak MongoDB Atlasen gorde ditut (Alberto's Org - 2023-12-20 > scoreboard > Databases). 
Bertara konektzatzeko spring.data.mongodb.uri=mongodb+srv://user1:Admin123@cluster0.lwihhnq.mongodb.net/?retryWrites=true&w=majority
Datu basearen izena companies da eta kolekzioaren izena sector da. Beste kolekzio bat dago company izenekoa, baina azkenean proiektu honetarako ez dut erabili.
Dokumentu bakoitza sektore bat da. Sektore bakoitzak ID, sektorearen izena eta enpresen Arraylist bat du. Enpresa bakoitzak (Company) enpresaren datuak ditu. Datu finantzarioak Data objektu baten barruan daude sartuta,
