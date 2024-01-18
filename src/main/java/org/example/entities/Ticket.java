package org.example.entities;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private Date departureDate;
    private LocalTime departureTime;
    private Date arrivalDate;
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private int price;

    public long getTimeInAir() {
        LocalDateTime departure = convertToLocalDateTime(departureDate, departureTime);
        LocalDateTime arrive = convertToLocalDateTime(arrivalDate, arrivalTime);
        return Duration.between(departure, arrive).toMinutes();
    }

    private LocalDateTime convertToLocalDateTime(Date date, LocalTime time) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                .withHour(time.getHour())
                .withMinute(time.getMinute())
                .withSecond(time.getSecond())
                .withNano(time.getNano());
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
