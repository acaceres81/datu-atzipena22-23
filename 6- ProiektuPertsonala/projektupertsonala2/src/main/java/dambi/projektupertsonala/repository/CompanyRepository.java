package dambi.projektupertsonala.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import dambi.projektupertsonala.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findByCompany(String company);
    List<Company> findBySalesGreaterThan(double salesValue);
    List<Company> findByRndGreaterThan(double rndValue);
    List<Company> findByCountryEquals(String countryValue);
}
