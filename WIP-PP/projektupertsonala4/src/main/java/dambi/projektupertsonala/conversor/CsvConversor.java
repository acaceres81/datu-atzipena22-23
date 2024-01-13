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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvConversor {

    public static void main(String[] args) throws CsvValidationException {
        convertCSVToJson("src/main/resources/files/SB2022_World2500.csv",
                "src/main/resources/files/SB2022_World2500.json");
    }

    public static void convertCSVToJson(String csvFile, String jsonFile) throws CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {

            String[] headers = reader.readNext(); // Skip header row
            String[] line;
            List<Company> companies = new ArrayList<>();

            while ((line = reader.readNext()) != null) {
                Company company = parseCompany(headers, line);
                if (company != null) {
                    companies.add(company);
                }
            }

            writeToJsonFile(companies, jsonFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Company parseCompany(String[] headers, String[] data) {
        Company company = new Company();
        Data companyData = new Data();

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            String value = (i < data.length) ? data[i] : ""; // Handle empty values
            switch (header) {
                case "rank":
                    company.setRank(Integer.parseInt(value));
                    break;
                case "company":
                    company.setCompany(value);
                    break;
                case "country":
                    company.setCountry(value);
                    break;
                case "region":
                    company.setRegion(value);
                    break;
                case "industry":
                    company.setIndustry(value);
                    break;
                case "data.rnd":
                    companyData.setRnd(Double.parseDouble(value));
                    break;
                // Handle other fields in the Data object similarly
                default:
                    break;
            }
        }

        company.setData(companyData);
        return company;
    }

    private static void writeToJsonFile(List<Company> companies, String jsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            objectMapper.writeValue(fileWriter, companies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
