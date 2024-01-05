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

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public Company updateCompany(String id, Company updatedCompany){
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company existingCompany = companyOptional.get();
            existingCompany.setCapex(updatedCompany.getCapex());
            existingCompany.setCapexGrowth(updatedCompany.getCapexGrowth());
            existingCompany.setCapexIntensity(updatedCompany.getCapexIntensity());
            existingCompany.setCompany(updatedCompany.getCompany());
            existingCompany.setEmployees(updatedCompany.getEmployees());
            existingCompany.setEmployeesGrowth(updatedCompany.getEmployeesGrowth());
            existingCompany.setIndustry(updatedCompany.getIndustry());
            existingCompany.setMarketCap(updatedCompany.getMarketCap());
            existingCompany.setMarketCapGrowth(updatedCompany.getMarketCapGrowth());
            existingCompany.setOpProfits(updatedCompany.getOpProfits());
            existingCompany.setOpProfitsGrouth(updatedCompany.getOpProfitsGrouth());
            existingCompany.setProfitability(updatedCompany.getProfitability());
            existingCompany.setRegion(updatedCompany.getRegion());
            existingCompany.setRank(updatedCompany.getRank());
            existingCompany.setRnd(updatedCompany.getRnd());
            existingCompany.setRndGrowth(updatedCompany.getRndGrowth());
            existingCompany.setRndIntensity(updatedCompany.getRndIntensity());
            existingCompany.setSales(updatedCompany.getSales());
            existingCompany.setSalesGrowth(updatedCompany.getSalesGrowth());
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
    
}
