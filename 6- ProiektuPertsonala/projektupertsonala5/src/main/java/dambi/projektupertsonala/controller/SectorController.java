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
import dambi.projektupertsonala.model.Sector;
import dambi.projektupertsonala.service.SectorService;

@RestController
@RequestMapping("api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public List<Sector> getSectors() {
        return sectorService.getSectors();

    }

    @GetMapping("/{id}")
    public Sector getSectorById(@PathVariable String id) {
        return sectorService.getSectorById(id);
    }

    @GetMapping("/sector/{sector}")
    public Sector getSectorbySector(@PathVariable String sector){
        return sectorService.getSectorBySector(sector);
    }

    @DeleteMapping()
    public void deleteAll() {
        sectorService.deleteAll();
    }
    @DeleteMapping("/company/{company}")
    public void deleteCompany(@PathVariable String company) {
        sectorService.deleteCompanyFromSector(company);
    }

    @PostMapping()
    public Sector adSector(@RequestBody Sector sector) {
        return sectorService.creaSector(sector);
    }
    
}
