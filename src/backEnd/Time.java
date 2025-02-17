/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backEnd;

/**
 *
 * @author Adjatay
 */
public class Time implements Comparable{
    private int minutes;
    private int hours;
    
    public Time() {
        this.minutes = 0;
        this.hours = 0;
    }
    public Time(int hours, int minutes) {
        this.minutes = minutes;
        this.hours = hours;
    }
    public void inc() {
        minutes++;
        if (minutes == 60) {
            minutes = 0;
            hours++;
        }
    
        
    }
    public long toNumber() { return (minutes + hours * 60);}
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (hours < 10) sb.append("0");
        sb.append(hours);
        sb.append(":");
        
        if (minutes < 10) sb.append("0");
        sb.append(minutes);
        
        
        
        return sb.toString();
    }
    public static Time parseTime(String text) {
        Time time = new Time();
        String tokens[] = text.split(":");
        time.hours = Integer.parseInt(tokens[0]);
        time.minutes = Integer.parseInt(tokens[1]);
        
        return time;
        
    }
    @Override
    public int compareTo(Object o) {
        Time that = (Time) o;
        return Long.compare(this.toNumber(), that.toNumber());
    }
    public static int compare(Object a, Object b) {
        Time lhs = (Time) a;
        Time rhs = (Time) b;
        return lhs.compareTo(rhs);
    }
    
    @Override
    public boolean equals(Object o) {
        Time that = (Time) o;
        return (this.toNumber() == that.toNumber());
    }
    public long diff(Object o) {
        Time that = (Time) o;
        return (this.toNumber() - that.toNumber());
    }
}
