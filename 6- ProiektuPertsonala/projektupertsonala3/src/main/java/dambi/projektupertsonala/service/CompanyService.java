package dambi.projektupertsonala.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dambi.projektupertsonala.exception.ResourceNotFoundException;
import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.repository.CompanyRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(String id){
        Optional<Company> company = companyRepository.findById(id);

        if(company.isPresent()){
            return company.get();
        }else{
            throw new ResourceNotFoundException("The id for the company you entered (" + id +") was not found.");
        }
    }

    public Company getCompanyByCompany(String company){
        Optional<Company> foundCompany = companyRepository.findByCompany(company);
    
        if(foundCompany.isPresent()){
            return foundCompany.get();
        } else {
            throw new ResourceNotFoundException("The company with the name " + company + " was not found.");
        }
    }
    

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getCompaniesByRnd(double rnd) {
        return companyRepository.findByDataRnd(rnd);
    }

  
    public Company updateCompany(String id, Company updatedCompany){
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company existingCompany = companyOptional.get();
            existingCompany.getData().setCapex(updatedCompany.getData().getCapex());
            existingCompany.getData().setCapexGrowth(updatedCompany.getData().getCapexGrowth());
            existingCompany.getData().setCapexIntensity(updatedCompany.getData().getCapexIntensity());
            existingCompany.setCompany(updatedCompany.getCompany());
            existingCompany.getData().setEmployees(updatedCompany.getData().getEmployees());
            existingCompany.getData().setEmployeesGrowth(updatedCompany.getData().getEmployeesGrowth());
            existingCompany.setIndustry(updatedCompany.getIndustry());
            existingCompany.getData().setMarketCap(updatedCompany.getData().getMarketCap());
            existingCompany.getData().setMarketCapGrowth(updatedCompany.getData().getMarketCapGrowth());
            existingCompany.getData().setOpProfits(updatedCompany.getData().getOpProfits());
            existingCompany.getData().setOpProfitsGrouth(updatedCompany.getData().getOpProfitsGrouth());
            existingCompany.getData().setProfitability(updatedCompany.getData().getProfitability());
            existingCompany.setRegion(updatedCompany.getRegion());
            existingCompany.setRank(updatedCompany.getRank());
            existingCompany.getData().setRnd(updatedCompany.getData().getRnd());
            existingCompany.getData().setRndGrowth(updatedCompany.getData().getRndGrowth());
            existingCompany.getData().setRndIntensity(updatedCompany.getData().getRndIntensity());
            existingCompany.getData().setSales(updatedCompany.getData().getSales());
            existingCompany.getData().setSalesGrowth(updatedCompany.getData().getSalesGrowth());
            existingCompany.setCountry(updatedCompany.getCountry());


            return companyRepository.save(existingCompany);
        }else{
            throw new ResourceNotFoundException("The id for the company you entered (" + id +") was not found.");
        }
    }

    public void deleteCompany(String id){
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            companyRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("The id for the company you entered (" + id +") was not found.");
        }
    }

    public void deleteAll(){
        
            companyRepository.deleteAll();
    
    }

    public List<Company> getCompaniesWithSalesGreaterThan(double salesValue){
        return companyRepository.findByDataSalesGreaterThan(salesValue);
    }

    public List<Company> getCompaniesByRndGreaterThan(double value) {
        return companyRepository.findByDataRndGreaterThan(value);
    }

    public List<Company> getCompaniesByountry(String countryValue){
        return companyRepository.findByCountryEquals(countryValue);
    }
    
    
}
