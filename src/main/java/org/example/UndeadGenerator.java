package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class UndeadGenerator {

    private final List<List<String>> domainBasedTypes = new ArrayList<>();
    private final List<String> domains = Arrays.asList("Intelligent", "Unintelligent", "Incorporeal", "Infected", "Cursed", "Nocturnal");
    private final List<Integer> domainBasedAverageDamage = Arrays.asList(240, 360, 100, 180, 300, 400, 500);
    private final List<String> unNamedCreatures = Arrays.asList("Zombie", "Skeleton", "Mummy", "Ghoul");
    private final List<String> names = Arrays.asList("Morveth", "Kaelzor", "Sanguivar", "Ulreth", "Zorakai", "Veshmor", "Aldryx", "Neroth", "Vaelgrim", "Noctyrix", "Xareth", "Rotmaw", "Velkan", "Hemaryn", "Umbrion", "Gravemarch", "Duskwretch", "Ebonthar", "Malzareth", "Ashmor");

    public UndeadGenerator() {
        domainBasedTypes.add(List.of("Vampire", "Lich", "Revenant", "Necromancer"));
        domainBasedTypes.add(List.of("Zombie", "Skeleton", "Mummy", "Ghoul"));
        domainBasedTypes.add(List.of("Ghost", "Wraith", "Revenant", "Banshee"));
        domainBasedTypes.add(List.of("Plagueeater", "Zombie", "Ghoul"));
        domainBasedTypes.add(List.of("Revenant", "GraveWarden", "Doomed"));
        domainBasedTypes.add(List.of("Stalker", "Vampire", "Nightcrawler"));
    }

    public Undead generateOne() {
        Random random = new Random();

        //get random domain and save its index
        int domainIdx = random.nextInt(domains.size());
        String domain = domains.get(domainIdx);

        //get available types based on domain to get random type
        List<String> types = domainBasedTypes.get(domainIdx);
        String type = types.get(random.nextInt(domainBasedTypes.get(domainIdx).size()));

        //get average damage to get random damage
        int averageDamage = domainBasedAverageDamage.get(domainIdx);
        float damageScalar = random.nextFloat(1,2);
        int damage = Math.round(random.nextInt(averageDamage - 50, averageDamage + 50) * damageScalar);

        //get name of the creature if it has one
        String name = unNamedCreatures.contains(type) ? type : names.get(random.nextInt(names.size()));

        //get random date from 1000 BC to today's date
        long start = LocalDate.of(-1000, 1, 1).toEpochDay();
        long end = LocalDate.now().toEpochDay();

        LocalDate date = LocalDate.ofEpochDay(random.nextLong(start, end));

        //create Undead object
        return new Undead(name, type, domain, date, damage);
    }

    public List<Undead> generate(int n) {
        return IntStream.range(0, n).
                mapToObj(i -> generateOne()).
                toList();
    }

}
