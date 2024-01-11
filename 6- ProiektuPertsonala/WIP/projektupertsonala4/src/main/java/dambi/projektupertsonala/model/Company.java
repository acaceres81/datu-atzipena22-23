package dambi.projektupertsonala.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {

    @Id
    String id;
    int rank;
    String company;
    String country;
    String region;
    String industry;
    Data data;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "Company [id=" + id + ", rank=" + rank + ", company=" + company + ", country=" + country + ", region="
                + region + ", industry=" + industry + ", data=" + data + "]";
    }


    

    
    
    
}
