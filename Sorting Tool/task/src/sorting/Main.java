package sorting;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String data = "word";
    private static String sorting = "natural";
    
    public static void main(final String[] args) throws IOException {

        File file = new File("out.txt");
        file.createNewFile();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-sortingType")) {
                if (i + 1 >= args.length || args[i + 1].equals("")) {
                    System.out.println("No sorting type defined!");
                } else {
                    sorting = args[i + 1];
                }
            } else if (args[i].equals("-dataType")) {
                if (i + 1 >= args.length || args[i + 1].equals("")) {
                    System.out.println("No data type defined!");
                } else {
                    data = args[i + 1];
                }
            } else if (args[i].contains("-")) {
                System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", args[i]);
            }
        }

        switch (data) {
            case "word":
                word();
                break;
            case "long":
                number();
                break;
            case "line":
                line();
                break;
        }
    }

    private static void word() {
        List<String> word = new ArrayList<>();

        while (scanner.hasNext()) {
            word.add(scanner.next());
        }

        Collections.sort(word);
        System.out.printf("Total words: %d.%n", word.size());

        if ("byCount".equals(sorting)) {
            sort(word);
        } else {
            System.out.print("Sorted data: ");
            for (String s : word) {
                System.out.print(s + " ");
            }
        }
    }

    private static void sort(List<String> word) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : word) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        sortedMap.forEach((key, value) -> System.out.printf("%s: %d time(s), %d%%%n",
                key, value, value * 100 / map.size()));
    }

    private static void number() {
        List<Long> number = new ArrayList<>();

        do {
            try {
                number.add(scanner.nextLong());
            } catch (Exception e) {
                String x = scanner.next();
                System.out.println(x + "is not a long. It will be skipped.");
            }
        } while (scanner.hasNext());

        Collections.sort(number);
        System.out.printf("Total numbers: %d.%n", number.size());

        if ("byCount".equals(sorting)) {
            Map<Long, Long> map = new LinkedHashMap<>();
            for (Long aLong : number) {
                if (map.containsKey(aLong)) {
                    map.put(aLong, aLong + 1);
                } else {
                    map.put(aLong, 1L);
                }
            }

            Map<Long, Long> sortedMap = new LinkedHashMap<>();
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

            sortedMap.forEach((key, value) -> System.out.printf("%d: %d time(s), %d%%%n",
                    key, value, value * 100 / map.size()));
        } else {
            System.out.print("Sorted data: ");
            for (Long l : number) {
                System.out.print(l + " ");
            }
        }
    }

    private static void line() {
        List<String> line = new ArrayList<>();

        while (scanner.hasNext()) {
            line.add(scanner.nextLine());
        }

        Collections.sort(line);
        System.out.printf("Total lines: %d.%n", line.size());

        if ("byCount".equals(sorting)) {
            sort(line);
        } else {
            System.out.println("Sorted data: ");
            for (String s : line) {
                System.out.println(s);
            }
        }
    }
}
