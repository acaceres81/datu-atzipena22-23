package dambi.projektupertsonala.model;


import org.bson.types.ObjectId;
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
    double rnd;
    double rndGrowth;
    double sales;
    double salesGrowth;
    double rndIntensity;
    double capex;
    double capexGrowth;
    double capexIntensity;
    double opProfits;
    double opProfitsGrouth;
    double profitability;
    int employees;
    double	employeesGrowth;
    double marketCap;
    double marketCapGrowth;


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
    public double getRnd() {
        return rnd;
    }
    public void setRnd(double rnd) {
        this.rnd = rnd;
    }
    public double getRndGrowth() {
        return rndGrowth;
    }
    public void setRndGrowth(double rndGrowth) {
        this.rndGrowth = rndGrowth;
    }
    public double getSales() {
        return sales;
    }
    public void setSales(double sales) {
        this.sales = sales;
    }
    public double getSalesGrowth() {
        return salesGrowth;
    }
    public void setSalesGrowth(double salesGrowth) {
        this.salesGrowth = salesGrowth;
    }
    public double getRndIntensity() {
        return rndIntensity;
    }
    public void setRndIntensity(double rndIntensity) {
        this.rndIntensity = rndIntensity;
    }
    public double getCapex() {
        return capex;
    }
    public void setCapex(double capex) {
        this.capex = capex;
    }
    public double getCapexGrowth() {
        return capexGrowth;
    }
    public void setCapexGrowth(double capexGrowth) {
        this.capexGrowth = capexGrowth;
    }
    public double getCapexIntensity() {
        return capexIntensity;
    }
    public void setCapexIntensity(double capexIntensity) {
        this.capexIntensity = capexIntensity;
    }
    public double getOpProfits() {
        return opProfits;
    }
    public void setOpProfits(double opProfits) {
        this.opProfits = opProfits;
    }
    public double getOpProfitsGrouth() {
        return opProfitsGrouth;
    }
    public void setOpProfitsGrouth(double opProfitsGrouth) {
        this.opProfitsGrouth = opProfitsGrouth;
    }
    public double getProfitability() {
        return profitability;
    }
    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }
    public int getEmployees() {
        return employees;
    }
    public void setEmployees(int employees) {
        this.employees = employees;
    }
    public double getEmployeesGrowth() {
        return employeesGrowth;
    }
    public void setEmployeesGrowth(double employeesGrowth) {
        this.employeesGrowth = employeesGrowth;
    }
    public double getMarketCap() {
        return marketCap;
    }
    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }
    public double getMarketCapGrowth() {
        return marketCapGrowth;
    }
    public void setMarketCapGrowth(double marketCapGrowth) {
        this.marketCapGrowth = marketCapGrowth;
    }

    
    
    
}
