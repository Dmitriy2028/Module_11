package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "John", "Peter", "Alice", "Bob");

        String task1 = getFormattedNames(names);
        System.out.println(task1);
        System.out.println();

        List<String> task2 = processStrings(names);
        System.out.println(task2.toString());
        System.out.println();


        String[] array = {"1, 2, 0", "4, 5"};

        String task3 = getSortedNumbers(array);
        System.out.println(task3);
        System.out.println();


        RandomAlg ra = new RandomAlg();
        Stream<Integer> task4 = Stream.iterate(11, (seed) -> ra.withSeed(seed).next());

        List<Integer> collect = task4
                .peek(System.out::println)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println();


        Stream<Integer> iterate1 = Stream.iterate(42, (seed) -> ra.withSeed(seed).next());
        Stream<Integer> iterate2 = Stream.iterate(24, (seed) -> ra.withSeed(seed).next());
        List<Integer> collect1 = iterate1
                .limit(3)
                .collect(Collectors.toList());

        List<Integer> collect2 = iterate2
                .limit(2)
                .collect(Collectors.toList());

        Stream<Integer> stream1 = collect1.stream();
        Iterator<Integer> iterator = stream1.iterator();
        Stream<Integer> stream2 = collect2.stream();

        List<Integer> task5 = stream2.map(i -> {
                    if (iterator.hasNext()) {
                        return List.of(i, iterator.next());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .flatMap(java.util.Collection::stream)
                .collect(Collectors.toList());
        for (int i : task5) {
            System.out.println(i);
        }
    }

    public static String getFormattedNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(i -> (i+1) % 2 != 0)
                .mapToObj(i -> (i+1) + ". " + names.get(i))
                .collect(Collectors.joining(", "));
    }
    public static List<String> processStrings(List<String> inputList) {
        return inputList.stream()
                .map(String::toUpperCase)
                .sorted((s1, s2) -> s2.compareTo(s1))
                .collect(Collectors.toList());
    }
    public static String getSortedNumbers(String[] inputArray) {
        return Arrays.stream(inputArray)
                .flatMap(s -> Arrays.stream(s.split(",\\s*")))
                .map(String::trim)
                .sorted()
                .collect(Collectors.joining(", "));
    }
}