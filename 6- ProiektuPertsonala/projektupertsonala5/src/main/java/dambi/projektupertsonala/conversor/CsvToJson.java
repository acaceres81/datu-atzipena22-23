package dambi.projektupertsonala.conversor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import dambi.projektupertsonala.model.Company;
import dambi.projektupertsonala.model.Data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvToJson {

    public static void main(String[] args) throws CsvValidationException {
        String csvFile = "src/main/resources/files/SB2022_World2500.csv";
        String jsonFile = "src/main/resources/files/SB2022_World2500.json";

        

        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            String[] headers = reader.readNext(); // Assuming first row is header
            List<Company> companyList = new ArrayList<>();

            String[] line;
            while ((line = reader.readNext()) != null) {
                Company company = new Company();
                // lerro bakoitza banatzen da ; dagoenean. -1 ipintzen da balio hutsak kontutan har ditzan.
                String[] values = line[0].split(";", -1); 
                
                
                company.setRank(Integer.parseInt(values[0]));
                company.setCompany(values[1]);
                company.setCountry(values[2]);
                company.setRegion(values[3]);
                company.setIndustry(values[4]);

                Data data = new Data();
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
                }// hemendik aurrera daudenak balio hutsak izan ditzakete.
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
                
                company.setData(data);
                companyList.add(company);

                
                
            }
            Company priCompany = companyList.get(26);
            
            System.out.println(priCompany.toString());

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(jsonFile), companyList);

            System.out.println("Conversion completed successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}