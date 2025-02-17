/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *
 * @author Adjatay
 */
public class Terminal {
    private int terminalID;
    private long workload;
    
    public Terminal() {
        this.terminalID = 0;
        this.workload = 0;
    }
    
    public Terminal(int terminalID, long workload) {
    }
    public boolean isBusy() {return (workload > 0);}
    public boolean isAvailable() {return (workload <= 0); }
    
    public void setWorkload(long workload) {this.workload = workload;}
    public void unload() {workload--;}
    
    
    }

    

