package dambi.projektupertsonala.model;

public class Data {
    // Double primitiboak erabitzen ditut double erabili beharrean csv fitxategiak balio hutsak dituelako,
    // double erabili ezkero, jsonera konbertsioa egiterakoan 0.0 balio ematen du null balioa eman beharrena.
    Double rnd;
    Double rndGrowth;
    Double sales;
    Double salesGrowth;
    Double rndIntensity;
    Double capex;
    Double capexGrowth;
    Double capexIntensity;
    Double opProfits;
    Double opProfitsGrowth; // Changed variable name to match methods
    Double profitability;
    Integer employees; // Using Integer wrapper class for employees
    Double employeesGrowth;
    Double marketCap;
    Double marketCapGrowth;
    
   

    public Double getRnd() {
        return rnd;
    }



    public void setRnd(Double rnd) {
        this.rnd = rnd;
    }



    public Double getRndGrowth() {
        return rndGrowth;
    }



    public void setRndGrowth(Double rndGrowth) {
        this.rndGrowth = rndGrowth;
    }



    public Double getSales() {
        return sales;
    }



    public void setSales(Double sales) {
        this.sales = sales;
    }



    public Double getSalesGrowth() {
        return salesGrowth;
    }



    public void setSalesGrowth(Double salesGrowth) {
        this.salesGrowth = salesGrowth;
    }



    public Double getRndIntensity() {
        return rndIntensity;
    }



    public void setRndIntensity(Double rndIntensity) {
        this.rndIntensity = rndIntensity;
    }



    public Double getCapex() {
        return capex;
    }



    public void setCapex(Double capex) {
        this.capex = capex;
    }



    public Double getCapexGrowth() {
        return capexGrowth;
    }



    public void setCapexGrowth(Double capexGrowth) {
        this.capexGrowth = capexGrowth;
    }



    public Double getCapexIntensity() {
        return capexIntensity;
    }



    public void setCapexIntensity(Double capexIntensity) {
        this.capexIntensity = capexIntensity;
    }



    public Double getOpProfits() {
        return opProfits;
    }



    public void setOpProfits(Double opProfits) {
        this.opProfits = opProfits;
    }



    public Double getOpProfitsGrowth() {
        return opProfitsGrowth;
    }



    public void setOpProfitsGrowth(Double opProfitsGrowth) {
        this.opProfitsGrowth = opProfitsGrowth;
    }



    public Double getProfitability() {
        return profitability;
    }



    public void setProfitability(Double profitability) {
        this.profitability = profitability;
    }



    public Integer getEmployees() {
        return employees;
    }



    public void setEmployees(Integer employees) {
        this.employees = employees;
    }



    public Double getEmployeesGrowth() {
        return employeesGrowth;
    }



    public void setEmployeesGrowth(Double employeesGrowth) {
        this.employeesGrowth = employeesGrowth;
    }



    public Double getMarketCap() {
        return marketCap;
    }



    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }



    public Double getMarketCapGrowth() {
        return marketCapGrowth;
    }



    public void setMarketCapGrowth(Double marketCapGrowth) {
        this.marketCapGrowth = marketCapGrowth;
    }



    @Override
    public String toString() {
        return "Data [rnd=" + rnd + ", rndGrowth=" + rndGrowth + ", sales=" + sales + ", salesGrowth=" + salesGrowth
                + ", rndIntensity=" + rndIntensity + ", capex=" + capex + ", capexGrowth=" + capexGrowth
                + ", capexIntensity=" + capexIntensity + ", opProfits=" + opProfits + ", opProfitsGrouth="
                + opProfitsGrowth + ", profitability=" + profitability + ", employees=" + employees
                + ", employeesGrowth=" + employeesGrowth + ", marketCap=" + marketCap + ", marketCapGrowth="
                + marketCapGrowth + "]";
    }

    
}
