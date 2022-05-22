package Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommonUtils {

    public static String generateRandomEmailAddress(String domain,int len)
    {
        String RandChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder str=new StringBuilder();
        Random rand=new Random();
        while(str.length()<len)
        {
            int index = (int) (rand.nextFloat() * RandChars.length());
            str.append(RandChars.charAt(index));
        }
        String randomString=str.toString() + domain;
        return randomString;
    }

    public static <T> List<T> twoDArrayToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public static String[][] copy(String[][] src) {
        if (src == null) {
            return null;
        }

        return Arrays.stream(src).map(String[]::clone).toArray(String[][]::new);
    }
}
