package com.company;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aplication {
    private List<AwardItem> maleList;
    private List<AwardItem> femaleList;

    public static void main(String[] args) {
        Aplication app = new Aplication("resources/oscar_age_male.csv", "resources/oscar_age_female.csv");
        app.getYoungestActorWinner();
        app.getMostAwardedActress();
        app.getMostAwardedActressBetween20And30Years();
        app.getPeopleWithMoreThanOneAward();
        app.getPersonResume("Tom Hanks");
    }

    private void getYoungestActorWinner() {
        this.maleList.stream()
                .min(Comparator.comparingInt(AwardItem::getWinnerAge))
                .ifPresent(item -> System.out.println("O ator mais jovem premiado foi "
                        + item.getWinnerName() + " com "
                        + item.getWinnerAge() + " anos.\n"));
    }


    private void getMostAwardedActress() {
        Map<String, Long> actresses = this.femaleList.stream()
                .map(AwardItem::getWinnerName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        actresses.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(c -> System.out.println("A atriz mais premiada foi "
                        + c.getKey() + " com "
                        + c.getValue() + " prêmios.\n"));
    }

    private void getMostAwardedActressBetween20And30Years() {
        Map<String, Long> actresses = this.femaleList.stream()
                .filter(p -> p.getWinnerAge() >= 20 && p.getWinnerAge() <= 30)
                .map(AwardItem::getWinnerName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        actresses.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(c -> System.out.println("A atriz mais premiada entre 20 e 30 anos foi "
                        + c.getKey() + " com "
                        + c.getValue() + " prêmios.\n"));
    }

    private void getPeopleWithMoreThanOneAward() {
        System.out.println("Atores ou Atrizes com mais de um prêmio: ");
        List<AwardItem> completeList = Stream.concat(this.femaleList.stream(), this.maleList.stream())
                .collect(Collectors.toList());
        Map<String, Long> actressesAndActors = completeList.stream()
                .map(AwardItem::getWinnerName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        actressesAndActors.entrySet().stream()
                .filter(item -> item.getValue() > 1)
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(c -> System.out.println(c.getKey() + " com "
                        + c.getValue() + " prêmios."));
    }

    private void getPersonResume(String name) {
        List<AwardItem> personAwards = Stream.concat(this.femaleList.stream(), this.maleList.stream())
                .filter(item -> item.getWinnerName().equals(name))
                .collect(Collectors.toList());

        if(personAwards.isEmpty()){
            System.out.println("Não foram encontrados prêmios para a pessoa solicitada.");
        }else {
            System.out.println(name+" ganhou "+personAwards.size()+" prêmios, dentre eles:");
            personAwards.forEach(item -> System.out.println(
                    item.getMovieName()+" em "+
                        item.getYearOfAward().getYear()+" com "+
                        item.getWinnerAge()+" anos de idade."
                    ));
        }





    }

    public Aplication(String csvPathMale, String csvPathFemale) {
        FileReader fileReaderMale = new FileReader(csvPathMale);
        FileReader fileReaderFemale = new FileReader(csvPathFemale);
        this.maleList = fileReaderMale.getAwardItemList();
        this.femaleList = fileReaderFemale.getAwardItemList();
    }


}
