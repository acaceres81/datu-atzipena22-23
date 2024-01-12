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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.model.Data;
import dambi.projektupertsonala.model.Sector;
import dambi.projektupertsonala.service.SectorService;

/*
* Kontrolatzaile honek sektoreekin eta enpresekin lotutako eskaerak kudeatzen ditu.
* Endpointak ematen ditu sektoreei eta enpresei buruzko informazioa berreskuratzeko eta hainbat eragiketa egiteko ere.
*/

@RestController
@RequestMapping("api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    // sektore guztiak erakutsiko digu metodo honek
    @GetMapping
    public List<Sector> getSectors() {
        return sectorService.getSectors();

    }

    // sektorea erakutsiko digu metodo honek
    @GetMapping("/{id}")
    public Sector getSectorById(@PathVariable String id) {
        return sectorService.getSectorById(id);
    }

    // Sektore bat erakutsiko digu metodo honek
    @GetMapping("/{sector}")
    public Sector getSectorbySector(@PathVariable String sector) {
        return sectorService.getSectorBySector(sector);
    }

    // Enpresa guztiak erakutsiko ditu metodo honek
    @GetMapping("/allCompanies")
    public List<Company> getAllCompanies() {
        return sectorService.getAllCompanies();
    }

    // Indormazio guztia ezabatu
    @DeleteMapping()
    public void deleteAll() {
        sectorService.deleteAll();
    }

    // Enpresa bat ezabatu
    @DeleteMapping("/company/{company}")
    public void deleteCompany(@PathVariable String company) {
        sectorService.deleteCompanyFromSector(company);
    }

    // Enpresa berri bat gehituko du. Sektorea berria bada sektorea sortuko du.
    // Sektorea ezisittzen bada, existitzen den sektorean sartuko du enpresa berria.
    @PostMapping("/company")
    public Sector adSector(@RequestBody Sector sector) {
        return sectorService.creaSector(sector);
    }

    // Sektore baten izena aldatu
    @PutMapping("/sector/{oldName}")
    public void updateSectorName(@PathVariable String oldName, @RequestParam String newName) {
        sectorService.updateSectorName(oldName, newName);
    }

    @GetMapping("/companies/{country}")
    public List<Company> getCompaniesByCountry(@PathVariable String country) {
        return sectorService.findCompaniesByCountry(country);
    }

    // erabiltzaileak sartutatko R&Da baino R&D handiagoa duten enpresak
    @GetMapping("/companiesWithRnDHigherThan/{rnd}")
    public List<Company> getCompaniesByRnd(@PathVariable double rnd) {
        return sectorService.findRndDGreaterThan(rnd);
    }

    // Enpresa gehien dituen sektorea ematen digu
    @GetMapping("/sectorWithMostCompanies")
    public String getSectorWithMostCompanies() {
        Sector sector = sectorService.getSectorWithMostCompanies();

        return sector.getSector() + " - " + sector.getCompanyList().size();
    }

    // Sektore baten dauden enpresa kopurua ematen digu.
    @GetMapping("/numberCompaniesSector/{sector}")
    public int getCompaniesPerSector(@PathVariable String sector) {
        Sector sectorObject = sectorService.getSectorBySector(sector);
            
        return sectorObject.getCompanyList().size();
    }

    // Sectore bateko batazbesteko DATA ematen digu.
    // Urteko igoerak, intentsitatea eta profitabilityak kalkulatzeak ez du zentzurik, beraz null bezala agertuko da.
    @GetMapping("/averageDataBySector/{sector}")
    public Data getAverageDataSector(@PathVariable String sector) {
        return sectorService.getAverageDataSector(sector);
    }

}
