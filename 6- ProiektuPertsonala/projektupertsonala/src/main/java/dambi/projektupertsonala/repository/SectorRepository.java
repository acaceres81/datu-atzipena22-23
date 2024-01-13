package dambi.projektupertsonala.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dambi.projektupertsonala.model.Sector;

/**
* Sector erakundearentzat errepositorio bat definitzen duen interfazea.
* Spring Data MongoDB erabiltzen du MongoDB datu-basean CRUD eragiketak egiteko.
*/

public interface SectorRepository extends MongoRepository<Sector, String>{
    Optional<Sector> findBySector(String sector);
    
}
