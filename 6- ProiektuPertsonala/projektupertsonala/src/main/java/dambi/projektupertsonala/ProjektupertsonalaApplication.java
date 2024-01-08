package dambi.projektupertsonala;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.repository.CompanyRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ProjektupertsonalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektupertsonalaApplication.class, args);
	}

	/*@Autowired
	CompanyRepository companyRepository;

	@PostConstruct
	public void populateData(){
		Company company1 = new Company();
		company1.setCompany("Example1");
		company1.setRank(0);
		company1.setCapex(100000.25);
		company1.setCapexGrowth(12.5);
		company1.setCapexIntensity(17.3);
		company1.setEmployees(1574);
		company1.setEmployeesGrowth(-7.32);
		company1.setIndustry("General Services");
		company1.setMarketCap(1235410516823.05);
		company1.setMarketCapGrowth(0.12);
		company1.setOpProfits(123546.32);
		company1.setOpProfitsGrouth(0);
		company1.setProfitability(27);

		companyRepository.save(company1);

	}*/

	

}
