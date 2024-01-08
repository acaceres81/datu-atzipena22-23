package dambi.projektupertsonala.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import dambi.projektupertsonala.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {

    

    
}
