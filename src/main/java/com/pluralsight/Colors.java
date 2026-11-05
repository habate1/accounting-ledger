package com.pluralsight;

public class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    // foreground
    public static final String PHOSPHOR = "\u001B[92m";
    public static final String AMBER = "\u001B[38;5;214m";
    public static final String BLACK = "\u001B[30m";

    // background
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_AMBER = "\u001B[48;5;214m";

    // background bar: pads the text to a fixed width, then colors the whole thing
    public static String bar(String text, int width, String bg, String fg) {
        return bg + fg + String.format("%-" + width + "s", text) + RESET;
    }

    // byte line: a divider of random binary bytes
    public static String byteLine(int bytes) {
        java.util.Random r = new java.util.Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes; i++) {
            String b = String.format("%8s", Integer.toBinaryString(r.nextInt(256))).replace(' ', '0');
            sb.append(b).append(i < bytes - 1 ? " " : "");
        }
        return sb.toString();
    }
}