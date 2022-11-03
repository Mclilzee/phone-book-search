package project.searchable;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class ContactHashSearch extends Searcher<Contact> {

    private Map<String, Contact> hashMap;
    private Duration createDuration;

    public ContactHashSearch(Contact[] searchableContent, Contact[] toFind) {
        super(searchableContent, toFind);
        Duration start = Duration.ofMillis(System.currentTimeMillis());
        hashMap = Arrays.stream(searchableContent)
                .collect(toMap(Contact::getName, e -> e));
        Duration end = Duration.ofMillis(System.currentTimeMillis());
        createDuration = end.minus(start);
    }

    @Override
    boolean isElementInSearchableFile(Contact element) {
        return hashMap.containsKey(element.getName());
    }

    @Override
    public String search() {
        findElements();

        return String.format("%s\nCreating time: %s\nSearching time: %s", getFoundMessage(createDuration), getDurationString(createDuration), getDurationString(getSearchDuration()));
    }
}
