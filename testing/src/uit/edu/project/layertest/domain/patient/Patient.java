/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.edu.project.layertest.domain.patient;

/**
 *
 * @author thawaye
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

public class Patient implements Serializable {
	
    private String name;
    private String disease;
    private String firstDate;
    private String roomAndBed;
    private String prescribedDoctor;
    private String address;
    private double totalBill;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
       
		return disease;
    }

    public void setDisease(String disease) {
        this.disease= disease;
    }
    
    public void setFirstDate(String firstDate) {
        this.firstDate= firstDate;
    }

    public String getFirstDate() {
        return firstDate;
    }
    
    public String getRoomAndBed() {
        return roomAndBed;
    }
    
    public void setroomAndBed(String roomAndBed) {
        this.roomAndBed= roomAndBed;
    }
    
    public void setprescribedDoctor(String prescribedDoctor) {
        this.prescribedDoctor = prescribedDoctor;
    }

    public String getprescribedDoctor() {
        return prescribedDoctor;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
    public double getTotalBill() {
        return totalBill;
    }
}