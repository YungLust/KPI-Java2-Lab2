package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndeadGenerator {

    private final List<List<String>> domainBasedTypes = new ArrayList<>();
    private final List<String> domains = Arrays.asList("Intelligent", "Unintelligent", "Incorporeal", "Infected", "Cursed", "Nocturnal");
    private final List<Integer> domainBasedAverageDamage = Arrays.asList(120, 180, 50, 90, 150, 200, 250);
    private final List<String> noNamedUndeads = Arrays.asList("Zombie","Skeleton","Mummy","Ghoul");
    private final List<String> names = Arrays.asList("Morveth", "Kaelzor", "Sanguivar", "Ulreth", "Zorakai", "Veshmor", "Aldryx", "Neroth", "Vaelgrim", "Noctyrix", "Xareth");

    public UndeadGenerator() {
        domainBasedTypes.add(List.of("Vampire","Lich","Revenant","Necromancer"));
        domainBasedTypes.add(List.of("Zombie","Skeleton","Mummy","Ghoul"));
        domainBasedTypes.add(List.of("Ghost","Wraith","Revenant","Banshee"));
        domainBasedTypes.add(List.of("Plagueeater","Zombie","Ghoul"));
        domainBasedTypes.add(List.of("Revenant","GraveWarden","Doomed"));
        domainBasedTypes.add(List.of("Stalker","Vampire","Nightcrawler"));
    }

    public generate
}
