package backEnd;

import dataStructures.List;
import dataStructures.Queue;
import dataStructures.Stack;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FlightProcessor {
    
    public List<Flight> scheduledFlights;
    public List<Flight> landedFlights;
    public int terminalcount;
    
    private double longestDelay;
    private double largestCount;
    private double averageDelay;
    private double averageCount;
    private int numDelayValues;
    private int numCountValues;
    
    
    public FlightProcessor() {
        scheduledFlights = new List<>();
        landedFlights = new List<>();
        terminalcount = 35;
        
        longestDelay = 0;
        largestCount = 0;
        averageDelay = 0;
        averageCount = 0;
        numDelayValues = 0;
        numCountValues = 0;
    }
    
    public int getTerminalcount() {return this.terminalcount;}
    public double getLongestDelay() {return this.longestDelay;}
    public double getLargestCount() {return this.largestCount;}
    public double getAverageDelay() {return this.averageDelay + 0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001;}
    public double getAverageCount() {return this.averageCount;}
    
    public void setTerminalCount(int terminalCount) { this.terminalcount = terminalcount;}

 
    
    public boolean load(String filename){
        
        try {
        FileReader file = new FileReader(filename);
        Scanner scanner = new Scanner(file);
        
        String headers = scanner.nextLine();
        
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            Flight flight = Flight.parseFlight(line);
            
                scheduledFlights.insert(flight);
        }
        scanner.close();
        }
        
        catch(Exception ex) {
            System.err.print(ex);
            return false;
        }
        return true;
    }
    public boolean store(String filename){
        
        try{
        FileWriter file = new FileWriter(filename);
        BufferedWriter buffer = new BufferedWriter(file);
        
        buffer.append("FlightID,airlineName,ETA,ALDT,passengersCount\n");
        
        
        for(landedFlights.moveToFirst();
                landedFlights.hasData();
                landedFlights.moveToNext()){
            Flight flight = landedFlights.getData();
            buffer.append(flight.toString());
            
            buffer.close();
        }
        
        buffer.close();
        }
        catch(Exception ex) {
            System.err.print(ex);
            return false;
        }
        
        return true;
    }
    
    
    public String run() {
        List<String> ledger = new List<>();
        
        Stack<Flight> incomingFlights = new Stack<>();
        Queue<Flight> waitingFlights = new Queue<>();
        Queue<Integer> idleTerminals = new Queue<>();
        
        Terminal terminals[] = new Terminal[terminalcount];
        
        for (scheduledFlights.moveTolast();scheduledFlights.hasData(); scheduledFlights.moveToPrev()){
            Flight flight = scheduledFlights.getData();
            incomingFlights.push(flight);
            //System.out.println(flight);
        }
        
        for (int i = 0; i < terminalcount; i++){
            terminals[i] = new Terminal();
            idleTerminals.enqueue(i);
        }
        
        longestDelay = 0;
        largestCount = 0;
        averageDelay = 0;
        averageCount = 0;
        numDelayValues = 0;
        numCountValues = 0;
        
        
        
        for(Time clock = new Time(0, 0); clock.compareTo(new Time(24, 0)) < 0; clock.inc()){
            StringBuilder event = new StringBuilder();
            event.append(clock.toString()); 
            
            for (int i = 0; i < terminalcount; i++){
                
                if (terminals[i].isBusy()) {
                    terminals[i].unload();
                    
                    if(terminals[i].isAvailable()){
                        idleTerminals.enqueue(i);
                        
                        String line = " Terminal #" + i + "is now available. " ;
                        event.append(line);
                    }
                    }
                }
                
                while (!incomingFlights.isEmpty() && incomingFlights.peek().getETA().equals(clock))
                {
                    Flight flight = incomingFlights.pop();
                    waitingFlights.enqueue(flight);
                    
                    String line = "Flight " + flight.getFlightNum() + "joined the line. " ;
                    event.append(line);
                    
                }
                
                while (!idleTerminals.isEmpty() && !waitingFlights.isEmpty()) {
                    Flight flight = waitingFlights.dequeue();
                    int terminalID = idleTerminals.dequeue();
                    terminals[terminalID].setWorkload(flight.getUnloadingTime());
                    
                    flight.setALDT(clock);
                    landedFlights.insert(flight);
                    
                    String line = " Terminal #" + terminalID + "is now serving Flight " + flight.getFlightNum() + ". " ;
                    event.append(line);
                    
                    long delay = flight.getWaitingTime();
                    
                    if (delay > longestDelay) {
                        longestDelay = delay;
                    }
                    
                    averageDelay += delay;
                    numDelayValues++;
                }
                    event.append("\n");
                    //averageDelay = 0.0;
                    long size = waitingFlights.size();
                    
                    if(size > largestCount) {
                        largestCount = size;
                    }
                    
                    averageCount += size;
                    numCountValues++;
           
            ledger.insert(event.toString());
            
        }
        if(numDelayValues > 0) {
            averageDelay = averageDelay / numDelayValues;
        }
        
        if (numCountValues > 0) {
            averageCount = averageCount / numCountValues;
        }
        return ledger.toString();
    }
        /*
        averageSize /= (24 * 60.0);
        event.append(" \nAverage Size = ");
        event.append(averageSize);
        return ledger.toString();
*/
    }
/*
    public List<Flight> getScheduledFlights() {
        return scheduledFlights;
    }

    public void setScheduledFlights(List<Flight> scheduledFlights) {
        this.scheduledFlights = scheduledFlights;
    }

    public List<Flight> getLandedFlights() {
        return landedFlights;
    }

    public void setLandedFlights(List<Flight> landedFlights) {
        this.landedFlights = landedFlights;
    }

    

    public void setTerminalcount(int terminalcount) {
        this.terminalcount = terminalcount;
    }
*/
    
   

