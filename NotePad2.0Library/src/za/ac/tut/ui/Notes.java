/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.ui;

/**
 *
 * @author hp
 */
public class Notes {
    private String dates;
    private String notes;

    public Notes(String dates, String notes) {
        this.dates = dates;
        this.notes = notes;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Date: " + dates + "\n" + notes;
    }
    
}
