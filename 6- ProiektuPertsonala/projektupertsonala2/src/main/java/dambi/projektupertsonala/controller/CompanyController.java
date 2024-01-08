package dambi.projektupertsonala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.service.CompanyService;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getCompanies();

    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable String id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/company/{company}")
    public Company getCompanyByCompany(@PathVariable String company) {
        return companyService.getCompanyByCompany(company);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable String id, @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable String id) {
        companyService.deleteCompany(id);
    }

    @DeleteMapping()
    public void deleteAll() {
        companyService.deleteAll();
    }

    @GetMapping("/salesGreaterThan/{value}")
    public List<Company> getCompaniesBySalesGreaterThan(@PathVariable double value) {
        return companyService.getCompaniesWithSalesGreaterThan(value);
    }

    @GetMapping("/rndGreaterThan/{value}")
    public List<Company> getCompaniesByRndGreaterThan(@PathVariable double value) {
        return companyService.getCompaniesWithRndGreaterThan(value);
    }

    @GetMapping("/countryEquals/{value}")
    public List<Company> getCompaniesByCountry(@PathVariable String value) {
        return companyService.getCompaniesByountry(value);
    }

}
