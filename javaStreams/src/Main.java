import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 8, 3, 12, 18, 5);
        List<Integer> multiplied = list
                .stream()
                .map(i -> i * 2)
                //.forEach(System.out::println);
        .collect(Collectors.toList());

        System.out.println(multiplied);


     }
}
