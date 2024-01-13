package dambi.projektupertsonala.conversor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.model.Data;
import dambi.projektupertsonala.model.Sector;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvToSector {
        private static int idCounter = 1;
        public static void main(String[] args) {
        String csvFilePath = "src/main/resources/files/SB2022_World2500.csv";
        String jsonFilePath = "src/main/resources/files/SB2022_World2500.json";

        Map<String, Sector> sectorMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] headers = reader.readNext(); // lehen lerroa ez du hartzen goiburua delako

            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] values = line[0].split(";", -1); 
                String sectorName = values[4]; 

                /*hemen map interfazea erabiltzen dugu sektorea existitzen bada sortzen ditugun objetu berriak
                * sektore horretan sartzeko eta sektorea ez bada existitzen, sektore berri bat sortzeko.
                */
                Sector sector = sectorMap.getOrDefault(sectorName, new Sector());
                sector.set_Id(generateId());
                sector.setSector(sectorName);


                // CSV fitxategitik Compnay Objetua sortu
                Company company = new Company();
                
                company.setRank(Integer.parseInt(values[0]));
                company.setCompany(values[1]);
                company.setCountry(values[2]);
                company.setRegion(values[3]);
                company.setIndustry(values[4]);

                // CSV fitxategitik Data Objetua sortu
                Data data = new Data();
                // Balio hutsak ez badira, csvko datua hartu. ez badago daturik null agertuko da.
                if (!values[5].isEmpty()) {
                data.setRnd(Double.parseDouble(values[5]));
                }
                if (!values[6].isEmpty()) {
                data.setRndGrowth(Double.parseDouble(values[6]));
                }
                if (!values[7].isEmpty()) {
                data.setSales(Double.parseDouble(values[7]));
                }
                if (!values[8].isEmpty()) {
                    data.setSalesGrowth(Double.parseDouble(values[8]));
                }
                if (!values[9].isEmpty()) {
                data.setRndIntensity(Double.parseDouble(values[9]));
                }
                if (!values[10].isEmpty()) {
                    data.setCapex(Double.parseDouble(values[10]));
                }
                if (!values[11].isEmpty()) {
                data.setCapexGrowth(Double.parseDouble(values[11]));
                }
                if (!values[12].isEmpty()){
                    data.setCapexIntensity(Double.parseDouble(values[12]));
                }
                if (!values[13].isEmpty()) {
                    data.setOpProfits(Double.parseDouble(values[13]));
                }
                if (!values[14].isEmpty()) {
                    data.setOpProfitsGrowth(Double.parseDouble(values[14]));
                }
                if (!values[15].isEmpty()) {
                    data.setProfitability(Double.parseDouble(values[15]));
                }
                if (!values[16].isEmpty()) {
                    data.setEmployees(Integer.parseInt(values[16]));
                }
                if (!values[17].isEmpty()) {
                    data.setEmployeesGrowth(Double.parseDouble(values[17]));
                }
                if (!values[18].isEmpty()) { 
                    data.setMarketCap(Double.parseDouble(values[18]));
                }
                if (!values[19].isEmpty()) {
                    data.setMarketCapGrowth(Double.parseDouble(values[19]));
                }

                //company objeturai data gehitu
                company.setData(data);

                // sektoreari gehitu company objetua
                sector.getCompanyList().add(company);

                // mapan sektorea eguneratu
                sectorMap.put(sectorName, sector);
            }

            // mapa lista batera pasatu JSONean idatzi aurretik.
            List<Sector> sectors = new ArrayList<>(sectorMap.values());
            writeToJson(sectors, jsonFilePath);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static void writeToJson(List<Sector> sectors, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(filePath), sectors);
            System.out.println("Conversion complete. JSON file created at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ida eman sektore bakoitzari
    private static String generateId() {
        return "SECTOR_" + idCounter++; 
    }
}