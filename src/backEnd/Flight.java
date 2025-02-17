/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *
 * @author Adjatay
 */
public class Flight implements Comparable{
    private String flightNum;
    private String airlineName;
    private Time ETA;
    private Time ALDT;
    private Integer passengerCount;
    
    public Flight() {
        
    this.flightNum = new String();
    this.airlineName = new String();
    this.ETA = new Time();
    this.ALDT = new Time();
    this.passengerCount = 0;
    }
    
    public String getFlightNum() {return this.flightNum;}
    public String getAirlineName() { return this.airlineName; }
    public Time getETA() { return this.ETA; }
    public Time getALDT() { return this.ALDT; } 
    public Integer getPassengerCount() { return this.passengerCount; }
    public long getWaitingTime() { return ALDT.diff(ETA); }
    public long getUnloadingTime() {return ((passengerCount+5)/3);}
    
    public void setALDT(Time ALDT) {this.ALDT = ALDT; }
    
    public static Flight parseFlight(String text) {
        Flight flight = new Flight();
        
        String tokens[] = text.split(",");
        
        flight.flightNum = tokens[0];
        flight.airlineName = tokens[1];
        flight.ETA = Time.parseTime(tokens[2]);
        flight.passengerCount = Integer.parseInt(tokens[3]);
        
        return flight;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.flightNum); sb.append(",");
        sb.append(this.airlineName); sb.append(",");
        sb.append(this.ETA.toString()); sb.append(",");
        sb.append(this.ALDT.toString()); sb.append(",");
        sb.append(this.passengerCount); sb.append("\n");
        
        return sb.toString();
        
     
    }
    
    @Override
    public int compareTo(Object o) {
       Flight that = (Flight) o;
       return Time.compare(this.getETA(), that.getETA());
    }
    public static int compare(Object a, Object b) {
        Flight lhs = (Flight) a;
        Flight rhs = (Flight) b;
        
        return lhs.compareTo(rhs);
    }
}

