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
            List<Company> companyDataList = new ArrayList<>();

            String[] line;
            while ((line = reader.readNext()) != null) {
                Company companyData = new Company();
                String[] values = line[0].split(";"); // Split the line into individual values
                if(values.length<20){
                    System.out.println("Cantidad de valores encontrados en la línea: " + values.length + " " + values[0]);
                }
                 
                //companyData.setId("ID_" + values[0]); // Assuming 'rank' is the ID
                companyData.setRank(Integer.parseInt(values[0]));
                companyData.setCompany(values[1]);
                companyData.setCountry(values[2]);
                companyData.setRegion(values[3]);
                companyData.setIndustry(values[4]);

                Data data = new Data();if 
                (!values[5].equals(" ")) {
                    data.setRnd(Double.parseDouble(values[5]));
                }
                if (!values[6].equals(" ")) {
                    data.setRndGrowth(Double.parseDouble(values[6]));
                }
                if (!values[7].equals(" ")) {
                    data.setSales(Double.parseDouble(values[7]));
                }
                if (!values[8].equals(" ")) {
                data.setSalesGrowth(Double.parseDouble(values[8]));
                }
                if (!values[9].equals(" ")) {
                    data.setRndIntensity(Double.parseDouble(values[9]));
                }
                if (!values[10].equals(" ")) {
                    data.setCapex(Double.parseDouble(values[10]));
                }
                if (!values[11].equals(" ")) {
                data.setCapexGrowth(Double.parseDouble(values[11]));
                }
                if (!values[12].equals(" ")){
                    data.setCapexIntensity(Double.parseDouble(values[12]));
                }
                if (!values[13].equals(" ")) {
                    data.setOpProfits(Double.parseDouble(values[13]));
                }
                if (!values[14].equals(" ")) {
                    data.setOpProfitsGrouth(Double.parseDouble(values[14]));
                }
                if (!values[15].equals(" ")) {
                    data.setProfitability(Double.parseDouble(values[15]));
                }
                if (!values[16].equals(" ")) {
                    data.setEmployees(Integer.parseInt(values[16]));
                }
                if (!values[17].equals(" ")) {
                    data.setEmployeesGrowth(Double.parseDouble(values[17]));
                }
                if (!values[18].equals(" ")) { 
                    data.setMarketCap(Double.parseDouble(values[18]));
                }
                if (!values[19].equals(" ")) {
                    data.setMarketCapGrowth(Double.parseDouble(values[19]));
                }
                companyData.setData(data);
                companyDataList.add(companyData);
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(jsonFile), companyDataList);

            System.out.println("Conversion completed successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}