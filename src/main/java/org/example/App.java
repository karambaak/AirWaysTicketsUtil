package org.example;

import org.example.entities.Ticket;
import org.example.utils.FileUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    private final List<Ticket> tickets;
    private static final String VVO = "VVO";
    private static final String TLV = "TLV";


    public App() {
        this.tickets = FileUtil.readFile();
    }

    public void run() {
        System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика:");
        var data = getMinTimeForEveryAirTransfer();
        for (Map.Entry<String, Long> entry : data.entrySet()) {
            String key = entry.getKey();
            Long totalMinutes = entry.getValue();
            long hours = totalMinutes / 60;
            long minutes = totalMinutes % 60;
            System.out.printf("%s: %02d:%02d%n", key, hours, minutes);
        }

        System.out.printf(
                "Разница между средней ценой и медианой для полета между городами Владивосток и Тель-Авив: %.2f%n",
                getDiffBetweenAverageAndMedian()
        );
    }

    private Map<String, Long> getMinTimeForEveryAirTransfer() {
        var someResult = tickets.stream()
                .filter(e -> e.getDestination().equals(TLV) && e.getOrigin().equals(VVO))
                .collect(Collectors.groupingBy(Ticket::getCarrier,
                        Collectors.minBy(Comparator.comparing(Ticket::getTimeInAir)))
                );

        return someResult.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()
                        .map(Ticket::getTimeInAir).orElse(0L)));
    }

    private double getDiffBetweenAverageAndMedian() {
        return getAveragePrice() - getMedian();
    }

    private double getAveragePrice() {
        var selectionFromTickets = tickets.stream()
                .filter(e -> e.getDestination().equals(TLV) && e.getOrigin().equals(VVO))
                .toList();
        double sum = selectionFromTickets.stream()
                .mapToDouble(Ticket::getPrice)
                .sum();
        return sum / selectionFromTickets.size();
    }

    private double getMedian() {
        List<Integer> prices = tickets.stream()
                .filter(e -> e.getDestination().equals(TLV) && e.getOrigin().equals(VVO))
                .map(Ticket::getPrice)
                .sorted(Integer::compare)
                .toList();
        int size = prices.size();
        return prices.size() % 2 == 0 ?
                (double) (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2 :
                (double) prices.get(size / 2 - 1);
    }
}
