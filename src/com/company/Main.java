package com.company;

import java.util.List;

public class Main {

    public static void old() {
	FileReader readerMale = new FileReader("resources/oscar_age_female.csv");
	List<AwardItem> menList = readerMale.getAwardItemList();
    menList.stream().forEach(System.out::println);

    }
}
