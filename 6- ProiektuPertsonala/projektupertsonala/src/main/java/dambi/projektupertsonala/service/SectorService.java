package dambi.projektupertsonala.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dambi.projektupertsonala.exception.ResourceNotFoundException;
import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.model.Data;
import dambi.projektupertsonala.model.Sector;
import dambi.projektupertsonala.repository.SectorRepository;

/*
* Zerbitzu honek logika ematen du sektoreak eta enpresak kudeatzeko.
* Sector Repository-arekin elkarreragiten du, eta hainbat eragiketa egiten ditu, adibidez datuak erakutsi,
* sektoreak eta enpresak sortzea, eguneratzea eta ezabatzea.
*/

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getSectors() {
        return sectorRepository.findAll();
    }

    // MongoDB atlasen dokumentuei ematen dien IDa erabilitza topatzen ditu enpresak.
    public Sector getSectorById(String id) {
        Optional<Sector> sector = sectorRepository.findById(id);

        if (sector.isPresent()) {
            return sector.get();
        } else {
            throw new ResourceNotFoundException("The id of the sector (" + id + ") was not found.");
        }
    }

    // Enpresa berri bat sortuko du. Sektorea berria bada sektorea sortuko du.
    // Sektorea exisittzen bada, existitzen den sektorean sartuko du enpresa berria.
    public Sector createCompany(Sector sector) {
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

    public Sector getSectorByName(String sector) {
        Optional<Sector> foundSector = sectorRepository.findBySector(sector);

        if (foundSector.isPresent()) {
            return foundSector.get();
        } else {
            throw new ResourceNotFoundException("The sector  " + sector + " was not found.");
        }
    }

    // enpresa bat ezabatzen, horetarako enpresa sektoreetan bilatzen du eta topatzen duenean ezabatu egiten du
    public void deleteCompanyFromSector(String companyName) {
        List<Sector> sectors = sectorRepository.findAll();
        
        for (int i = 0; i < sectors.size(); i++) {
            Sector sector = sectors.get(i);
            List<Company> companyList = sector.getCompanyList();

            for (int j = 0; j < companyList.size(); j++) {
                Company company = companyList.get(j);
                
                if (company.getCompany().equals(companyName)) {
                    companyList.remove(j);
                    sectorRepository.save(sector);
                    return;
                }
            }
        }

    }

    // Dokumentu guztiak ezabatzen ditu
    public void deleteAll() {

        sectorRepository.deleteAll();

    }


    public void updateSectorName(String oldName, String newName) {
        Optional<Sector> optionalSector = sectorRepository.findBySector(oldName);

        if (optionalSector.isPresent()) {
            Sector sector = optionalSector.get();
            //  sektore horatko enpresa bakoitzari industry-aren izena aldatu sektorearekin bat etor dadin.
            for (int i = 0; i < sector.getCompanyList().size(); i++) {
                sector.getCompanyList().get(i).setIndustry(newName);
            }
            // sektoreari izena aldatu
            sector.setSector(newName);
            // sektorea eguneratu errepositorioan
            sectorRepository.save(sector);

        } else {
            throw new NoSuchElementException("Sector with name " + oldName + " not found");
        }
    }

    // Enpresa guztiak erakutsi rakingaren arabera ordenatuz
    public List<Company> getAllCompanies() {
        List<Sector> sectors = sectorRepository.findAll();
        List<Company> allCompanies = new ArrayList<>();

        // Sektore guztietako enpresak lista baten sartu
        for (int i = 0; i < sectors.size(); i++) {
            List<Company> companies = sectors.get(i).getCompanyList(); 
            for (int j = 0; j < companies.size(); j++) {
                allCompanies.add(companies.get(j));
            }
        }

        // Lista rankinaren arabera ordenatu.
        orderCompanies(allCompanies);

        return allCompanies;
    }

    // Herrialde bateko enpresa guztiak erakutsi rakingaren arabera ordenatuz.
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

    //I+G erabiltzaileak esandako I+G baino hanfiagoa duten enpresak erakusten ditu.
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
        for (int i = 0; i < sectors.size(); i++) {
            if (sectors.get(i).getCompanyList().size() > sector.getCompanyList().size()) {
                sector = sectors.get(i);
            }
        }

        return sector;
    }

    public Data getAverageDataSector(String sectorName) {
        Optional<Sector> optionalSector = sectorRepository.findBySector(sectorName);

        if (optionalSector.isPresent()) {
            Sector sector = optionalSector.get();
            List<Company> companies = sector.getCompanyList();
            Data averageData = new Data();

            int companyCount = companies.size();
            if (companyCount > 0) {
                for (int i = 0; i < companyCount; i++) {
                    Data data = companies.get(i).getData();

                    // enpresa bakoitzeko atributu bakoitzaren batura kalkulatu.
                    // Urteko igoerak, intentsitatea eta profitabilityak kalkulatzeak ez du
                    // zentzurik, beraz null bezala agertuko da.
                    if (data != null) {
                        averageData.setRnd(getValueOrDefault(averageData.getRnd()) + getValueOrDefault(data.getRnd()));
                        averageData.setSales(
                                getValueOrDefault(averageData.getSales()) + getValueOrDefault(data.getSales()));
                        averageData.setCapex(
                                getValueOrDefault(averageData.getCapex()) + getValueOrDefault(data.getCapex()));
                        averageData.setOpProfits(
                                getValueOrDefault(averageData.getOpProfits()) + getValueOrDefault(data.getOpProfits()));
                        averageData.setEmployees(
                                getValueOrDefault(averageData.getEmployees()) + getValueOrDefault(data.getEmployees()));
                        averageData.setMarketCap(
                                getValueOrDefault(averageData.getMarketCap()) + getValueOrDefault(data.getMarketCap()));

                    }
                }

                // Batazbestekoak kalkulatu
                averageData.setRnd(averageData.getRnd() / companyCount);
                averageData.setSales(averageData.getSales() / companyCount);
                averageData.setCapex(averageData.getCapex() / companyCount);
                averageData.setOpProfits(averageData.getOpProfits() / companyCount);
                averageData.setEmployees(averageData.getEmployees() / companyCount);
                averageData.setMarketCap(averageData.getMarketCap() / companyCount);
                averageData.setCapexIntensity(averageData.getCapex() / averageData.getSales() * 100);
                averageData.setRndIntensity(averageData.getRnd() / averageData.getSales() * 100);
                averageData.setProfitability(averageData.getOpProfits() / averageData.getSales() * 100);


                return averageData;

                // sektorean ez badago enpresarik guztia 0 izango da.
            } else {
                averageData.setRnd(0.0);
                averageData.setSales(0.0);
                averageData.setCapex(0.0);
                averageData.setOpProfits(0.0);
                averageData.setEmployees(0);
                averageData.setMarketCap(0.0);
                averageData.setCapexIntensity(0.0);
                averageData.setRndIntensity(0.0);

                return averageData;

            }
        } else {
            throw new NoSuchElementException(sectorName + " sektorea ez da existitzen.");
        }
    }

    // Enpresak rankinaren arabera ordenatu
    public List<Company> orderCompanies(List<Company> companies) {
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

    // metodo honek null balioak daudenean 0.0 balioa ematen digu
    // NullPointerException-a ekiditeko.
    public double getValueOrDefault(Double value) {
        return value != null ? value : 0.0;
    }

    // metodo honek null balioak daudenean 0 balioa ematen digu
    // NullPointerException-a ekiditeko.
    public int getValueOrDefault(Integer value) {
        return value != null ? value : 0;
    }

}
