package dambi.projektupertsonala.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import dambi.projektupertsonala.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findByCompany(String company);
    List<Company> findByDataSalesGreaterThan(double salesValue);
    List<Company> findByDataRndGreaterThan(double value);
    List<Company> findByCountryEquals(String countryValue);
    List<Company> findByDataRnd(double rnd);

    
}
