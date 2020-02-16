package com.lego.bestJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BestJavaApplication {
    public static void main(String[] args) {
        char[] array1 = {'a', 'b', 'c', 'z'};
        char[] array2 = {'w', 'x', 'y', 'g'};
        System.out.println(areTheyRelated(array1, array2));
        SpringApplication.run(BestJavaApplication.class, args);
    }

    public static boolean areTheyRelated(char[] array1, char[] array2) {
        Map<Character, Boolean> mind = new HashMap<>();
        Boolean result = null;
        for (int i = 0; i < array1.length || i < array2.length; i++) {
            if (i < array1.length) {
            	result = mind.putIfAbsent((char) (array1[i]-'a'), true);
            }
            if(result != null) {
                return true;
            }
            if (i < array2.length) {
                result = mind.putIfAbsent((char) (array2[i]-'a'),true);
            }
            if(result != null) {
                return true;
            }
        }
        return false;
    }
}
