package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    private final List<AwardItem> awardItemList;

    public FileReader(String filename) {
        this.awardItemList = readFile(filename);
    }

    private List<AwardItem> readFile(String filename) {
        try (Stream<String> fileLines = Files.lines(Paths.get(filename))) {
            return fileLines
                    .skip(1)
                    .map(AwardItem::of)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<AwardItem> getAwardItemList() {
        return awardItemList;
    }
}
