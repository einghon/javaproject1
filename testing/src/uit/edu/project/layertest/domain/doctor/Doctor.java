/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.edu.project.layertest.domain.doctor;

import java.io.Serializable;

public class Doctor implements Serializable {

    private String name;
    private Double confee;
    private String contactNo;
    private String speciality;
    private String address;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
       
		return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setConfee(Double confee) {
        this.confee = confee;
    }

    public Double getConfee() {
        return confee;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
