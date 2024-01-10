package dambi.projektupertsonala.service;

import java.util.List;
import java.util.Optional;

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

    public List<Sector> getSectors(){
        return sectorRepository.findAll();
    }

    public Sector getSectorById(String id){
        Optional<Sector> sector = sectorRepository.findById(id);

        if(sector.isPresent()){
            return sector.get();
        }else{
            throw new ResourceNotFoundException("The id of the sector (" + id +") was not found.");
        }
    }

    public Sector creaSector(Sector sector){
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

    public Sector getSectorBySector(String sector){
        Optional<Sector> foundSector = sectorRepository.findBySector(sector);
    
        if(foundSector.isPresent()){
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

    public void deleteAll(){
        
        sectorRepository.deleteAll();

    }
    
}
