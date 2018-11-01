package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtils {

    public static final String EMPTY_LIST = "[]";

    private static final String ARRAY_DELIMITER_REGEX = "[\\[,\"\\]]+";

    public static List<String> getStringArray(String array) {
        String[] arr = array.trim().split(ARRAY_DELIMITER_REGEX);

        return Arrays.stream(arr).filter(s -> !s.equals("")).collect(Collectors.toList());
    }

    public static String addValueToArray(String array, String value) {
        List<String> list = getStringArray(array);
        list.add(value);
        return toArrayJson(list);
    }

    public static String deleteValueFromArray(String array, String value) {
        List<String> list = getStringArray(array);
        while (list.contains(value)) {
            list.remove(value);
        }
        return toArrayJson(list);
    }

    private static String toArrayJson(List<String> list) {
        return list.stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(",", "[", "]"));
    }

    public static void main(String[] args) {
        String s = "[\"12345\"]";
        s = addValueToArray(s, "12345");
        s = addValueToArray(s, "zsa s");
        s = deleteValueFromArray(s, "12345");
        s = deleteValueFromArray(s, "zsa s");
        System.out.println(getStringArray(s).size());
    }
}
