package dambi.projektupertsonala.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Sector {
    @Id
    String _Id;
    String sector;
    List<Company> companyList = new ArrayList<>();

    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public List<Company> getCompanyList() {
        return companyList;
    }
    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }
    public String get_Id() {
        return _Id;
    }
    public void set_Id(String _Id) {
        this._Id = _Id;
    }


}
