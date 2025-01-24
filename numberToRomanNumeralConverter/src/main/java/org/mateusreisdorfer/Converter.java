package org.mateusreisdorfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    public String numberToRomanNumeral(String value) {
        this.checkIfNumberIsValid(value);
        char[] valueArray = value.toCharArray();

        return this.buildResponse(valueArray);
    }

    private String buildResponse(char[] valueArray) {
        StringBuilder romanNumeral = new StringBuilder();
        Map<String, List<String>> romanNumeralMap = this.getRomanNumeralMap();
        List<String> romanNumeralMapKeys = List.of("hundredNumeral", "tenNumeral", "unityNumeral");
        int loopIndex = 0;

        if(valueArray.length == 4) {
            romanNumeral.append("M".repeat(Integer.parseInt(String.valueOf(valueArray[0]))));
            loopIndex = 1;
        }

        for(int i=loopIndex; i<valueArray.length; i++) {
            int index = valueArray.length-1;

            if(valueArray.length == 4)
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(i-1)));

            if(valueArray.length == 3)
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(i)));

            if(valueArray.length == 2)
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(index+i)));

            if(valueArray.length == 1)
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(romanNumeralMapKeys.size()-1)));

        }

        return romanNumeral.toString();
    }

    private Map<String, List<String>> getRomanNumeralMap() {
        Map<String, List<String>> romanNumerlMap = new HashMap<>();
        romanNumerlMap.put("hundredNumeral", List.of("C", "CD", "D", "CM"));
        romanNumerlMap.put("tenNumeral", List.of("X", "XL", "L", "XC"));
        romanNumerlMap.put("unityNumeral", List.of("I", "IV", "V", "IX"));

        return romanNumerlMap;
    }

    private void convert(char value, StringBuilder romanNumeral, List<String> listRomanNumeral) {
        int newValue = Integer.parseInt(String.valueOf(value));

        if(newValue < 4)
            romanNumeral.append(listRomanNumeral.get(0).repeat(newValue));

        if(newValue == 4)
            romanNumeral.append(listRomanNumeral.get(1));

        if(newValue == 5)
            romanNumeral.append(listRomanNumeral.get(2));

        if(newValue > 5 && newValue < 9) {
            romanNumeral.append(listRomanNumeral.get(2));

            switch (newValue) {
                case 6 -> romanNumeral.append(listRomanNumeral.get(0));
                case 7 -> romanNumeral.append(listRomanNumeral.get(0).repeat(2));
                case 8 -> romanNumeral.append(listRomanNumeral.get(0).repeat(3));
            }
        }

        if(newValue == 9)
            romanNumeral.append(listRomanNumeral.get(3));
    }

    private void checkIfNumberIsValid(String value) {
        if(value == null)
            throw new IllegalArgumentException("Value cannot be null");

        if(value.isEmpty())
            throw new IllegalArgumentException("Value cannot be empty");

        if(value.isBlank())
            throw new IllegalArgumentException("Value cannot be blank");

        if(!value.matches("^\\d+$"))
            throw new IllegalArgumentException("Value can only contain numbers");

        int newValue = Integer.parseInt(value);

        if(newValue < 1)
            throw new IllegalArgumentException("Value cannot be less or equal than 0");

        if(newValue > 3999)
            throw new IllegalArgumentException("Value cannot be greater or equal than 3999");
    }
}
