package dambi.projektupertsonala.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dambi.projektupertsonala.exception.ResourceNotFoundException;
import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.model.Sector;
import dambi.projektupertsonala.repository.SectorRepository;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getSectors() {
        return sectorRepository.findAll();
    }

    public Sector getSectorById(String id) {
        Optional<Sector> sector = sectorRepository.findById(id);

        if (sector.isPresent()) {
            return sector.get();
        } else {
            throw new ResourceNotFoundException("The id of the sector (" + id + ") was not found.");
        }
    }

    public Sector creaSector(Sector sector) {
        Optional<Sector> existingSector = sectorRepository.findBySector(sector.getSector());

        if (existingSector.isPresent()) {
            Sector foundSector = existingSector.get();
            // Add the new company to the existing sector's companyList
            foundSector.getCompanyList().addAll(sector.getCompanyList());
            // Save the updated sector with the added company
            return sectorRepository.save(foundSector);
        } else {
            // If the sector doesn't exist, save the new sector
            return sectorRepository.save(sector);
        }
    }

    public Sector getSectorBySector(String sector) {
        Optional<Sector> foundSector = sectorRepository.findBySector(sector);

        if (foundSector.isPresent()) {
            return foundSector.get();
        } else {
            throw new ResourceNotFoundException("The sector  " + sector + " was not found.");
        }
    }

    public void deleteCompanyFromSector(String companyName) {
        List<Sector> sectors = sectorRepository.findAll();

        for (Sector sector : sectors) {
            boolean removed = sector.getCompanyList().removeIf(company -> company.getCompany().equals(companyName));
            if (removed) {
                // Save the updated sector without the removed company
                sectorRepository.save(sector);
                return;
            }
        }

        // Handle scenarios where the company with companyId is not found in any sector
        // You might want to throw an exception or handle it based on your logic
    }

    public void deleteAll() {

        sectorRepository.deleteAll();

    }

    public void updateSectorName(String oldName, String newName) {
        Optional<Sector> optionalSector = sectorRepository.findBySector(oldName);

        if (optionalSector.isPresent()) {
            Sector sector = optionalSector.get();
            // Aldatu sektorearen izena sektore horatko enpresa bakoitzari
            for (int i = 0; i < sector.getCompanyList().size(); i++) {
                sector.getCompanyList().get(i).setIndustry(newName);
            }
            sector.setSector(newName);
            sectorRepository.save(sector);

        } else {
            throw new NoSuchElementException("Sector with name " + oldName + " not found");
        }
    }

    public List<Company> getAllCompanies() {
        List<Sector> sectors = sectorRepository.findAll();
        List<Company> allCompanies = new ArrayList<>();

        // Sektore guztietako enpresak lista baten sartu
        for (Sector sector : sectors) {
            allCompanies.addAll(sector.getCompanyList());
        }

        
        orderCompanies(allCompanies);

        return allCompanies;
    }

    public List<Company> findCompaniesByCountry(String country) {
        List<Sector> sectors = sectorRepository.findAll();
        List<Company> companiesByCountry = new ArrayList<>();
        for (int i = 0; i < sectors.size(); i++) {
            List<Company> companies = sectors.get(i).getCompanyList();
            for (int j = 0; j < companies.size(); j++) {
                if (companies.get(j).getCountry().equals(country)) {
                    companiesByCountry.add(companies.get(j));
                }
            }
        }
        
        orderCompanies(companiesByCountry);
        
        return companiesByCountry;

    }

    
    public List<Company> findRndDGreaterThan(double rnd) {
        List<Sector> sectors = sectorRepository.findAll();
        List<Company> companiesRnd = new ArrayList<>();
    
        for (int i = 0; i < sectors.size(); i++) {
            List<Company> companies = sectors.get(i).getCompanyList();
            for (int j = 0; j < companies.size(); j++) {
                Company company = companies.get(j);
                if (company.getData().getRnd() > rnd) {
                        companiesRnd.add(company);                   
                }
            }
        }

        orderCompanies(companiesRnd);
    
        return companiesRnd;
    }

    public Sector getSectorWithMostCompanies() {
        List<Sector> sectors = sectorRepository.findAll();
        Sector sector = new Sector();
        for(int i = 0; i<sectors.size(); i++){
            if(sectors.get(i).getCompanyList().size() > sector.getCompanyList().size()){
                sector = sectors.get(i);
            }
        }

        return sector;/* .stream()
                .max(Comparator.comparingInt(sector -> sector.getCompanyList().size()));*/
    }

    // Enpresak rankinaren arabera ordenatu
    public List<Company> orderCompanies(List<Company> companies){
    for (int i = 0; i < companies.size() - 1; i++) {
            for (int j = 0; j < companies.size() - 1 - i; j++) {
                if (companies.get(j).getRank() > companies.get(j + 1).getRank()) {
                    Company temp = companies.get(j);
                    companies.set(j, companies.get(j + 1));
                    companies.set(j + 1, temp);
                }
            }
        }
        return companies;
    }
    

}
