package Zoo_Project;

import java.time.LocalDateTime;

public class Visitors {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDateTime entryTime;
    private int ticketNumber;

    public Visitors() {
    }

    public Visitors(String firstName, String lastName, LocalDateTime entryTime, int ticketNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.entryTime = entryTime;
        this.ticketNumber = ticketNumber;
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", firstName='" + firstName +
                        ", lastName='" + lastName +
                        ", entryTime=" + entryTime +
                        ", ticketNumber=" + ticketNumber;
    }


}
