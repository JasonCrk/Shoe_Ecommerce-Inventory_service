package com.shoe_ecommerce.inventory.shared.domain.utils;

public final class StringFormatUtils {

    public static String toSnake(String text) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(text.charAt(0)));

        for (int i = 1; i <= text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append('_');
                result.append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static String toCamel(String text) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char currentChar : text.toCharArray()) {
            if (currentChar == '_') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(currentChar));
                capitalizeNext = false;
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    public static String toCamelFirstLower(String text) {
        if (text == null || text.isEmpty()) return text;

        String camelCase = toCamel(text);
        return Character.toLowerCase(camelCase.charAt(0)) + camelCase.substring(1);
    }
}
