package dambi.projektupertsonala.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.model.Sector;

public interface SectorRepository extends MongoRepository<Sector, String>{
    Optional<Sector> findBySector(String sector);
    
}
