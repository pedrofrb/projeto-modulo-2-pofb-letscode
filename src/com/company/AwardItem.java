package com.company;

import java.time.LocalDate;

public class AwardItem {
    private String winnerName;
    private int winnerAge;
    private LocalDate yearOfAward;
    private String movieName;

    public AwardItem(String winnerName, int winnerAge, LocalDate yearOfAward, String movieName) {
        this.winnerName = winnerName;
        this.winnerAge = winnerAge;
        this.yearOfAward = yearOfAward;
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "AwardItem{" +
                "winnerName='" + winnerName + '\'' +
                ", winnerAge=" + winnerAge +
                ", yearOfAward=" + yearOfAward +
                ", movieName='" + movieName + '\'' +
                '}';
    }

    public static AwardItem of(String line) {
        String[] dividedLine = line.split("; ");
        return new AwardItem(
                dividedLine[3],
                Integer.parseInt(dividedLine[2]),
                LocalDate.ofYearDay(Integer.parseInt(dividedLine[1]), 1),
                dividedLine[4]


        );
    }

    public String getWinnerName() {
        return winnerName;
    }

    public int getWinnerAge() {
        return winnerAge;
    }

    public LocalDate getYearOfAward() {
        return yearOfAward;
    }

    public String getMovieName() {
        return movieName;
    }
}
