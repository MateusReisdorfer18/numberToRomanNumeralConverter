package org.mateusreisdorfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    public String numberToRomanNumeral(String value) {
        this.checkIfNumberIsValid(value);
        return this.buildResponse(value.toCharArray());
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

            if(valueArray.length == 1) {
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(2)));
                continue;
            }

            if(valueArray.length == 2) {
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(index+i)));
                continue;
            }

            if(valueArray.length == 3) {
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(i)));
                continue;
            }

            if(valueArray.length == 4)
                this.convert(valueArray[i], romanNumeral, romanNumeralMap.get(romanNumeralMapKeys.get(i-1)));

        }

        return romanNumeral.toString();
    }

    private void convert(char value, StringBuilder romanNumeral, List<String> listRomanNumeral) {
        int newValue = Character.getNumericValue(value);

        if(newValue < 4) {
            romanNumeral.append(listRomanNumeral.get(0).repeat(newValue));
            return;
        }

        if(newValue == 4) {
            romanNumeral.append(listRomanNumeral.get(1));
            return;
        }

        if(newValue == 5) {
            romanNumeral.append(listRomanNumeral.get(2));
            return;
        }

        if(newValue < 9) {
            romanNumeral.append(listRomanNumeral.get(2));
            romanNumeral.append(listRomanNumeral.get(0).repeat(newValue - 5));
            return;
        }

        if(newValue == 9)
            romanNumeral.append(listRomanNumeral.get(3));
    }

    private Map<String, List<String>> getRomanNumeralMap() {
        Map<String, List<String>> romanNumeralMap = new HashMap<>();
        romanNumeralMap.put("hundredNumeral", List.of("C", "CD", "D", "CM"));
        romanNumeralMap.put("tenNumeral", List.of("X", "XL", "L", "XC"));
        romanNumeralMap.put("unityNumeral", List.of("I", "IV", "V", "IX"));

        return romanNumeralMap;
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
