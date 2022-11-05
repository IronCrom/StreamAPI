import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underageCount = persons.stream()
                .filter(underage -> underage.getAge() < 18)
                .count();
        System.out.println(underageCount);

        List<String> recruitsList = persons.stream()
                .filter(recruitSex -> recruitSex.getSex().equals(Sex.MAN))
                .filter(recruitAge -> recruitAge.getAge() >= 18 && recruitAge.getAge() <= 27)
                .map(Person::getFamily).toList();
        System.out.println(recruitsList.size());

        List<Person> workable = persons.stream()
                .filter(workableEducation -> workableEducation.getEducation().equals(Education.HIGHER))
                .filter(ageFilter -> ageFilter.getAge() >= 18)
                .filter(ageAndSexFilter -> (ageAndSexFilter.getSex().equals(Sex.WOMAN) && (ageAndSexFilter.getAge() <= 60)) ||
                        (ageAndSexFilter.getSex().equals(Sex.MAN) && (ageAndSexFilter.getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println(workable.size());
    }
}