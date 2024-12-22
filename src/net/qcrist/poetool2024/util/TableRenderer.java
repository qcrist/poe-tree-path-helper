package net.qcrist.poetool2024.util;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TableRenderer {
    public static void render(List<Object[]> table) {
        render(table.toArray(Object[][]::new));
    }

    private static String[][] toString(Object[][] table) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        return Arrays.stream(table).map(
                row -> Arrays.stream(row).map(
                        obj -> {
                            if (obj instanceof Number) {
                                return nf.format(obj);
                            }
                            return obj.toString();
                        }).toArray(String[]::new)
        ).toArray(String[][]::new);
    }

    public static void render(Object[][] table) {
        render(toString(table));
    }


    private static void render_row(int[] sizes, String[] row) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizes.length; i++) {
            if (i > 0)
                sb.append("|");
            String read = row[i];
            if (sizes[i] > 0) {
                if (read.length() == sizes[i])
                    sb.append(read);
                else {
                    sb.repeat(' ', sizes[i] - read.length() - 1);
                    sb.append(read);
                    sb.append(' ');
                }
            }
        }
        System.out.println(sb);
    }

    public static void render(String[][] table) {
        int columns = table[0].length;

        int[] columnSizes = new int[columns];
        Arrays.fill(columnSizes, 0);

        for (String[] strings : table) {
            for (int col = 0; col < strings.length; col++) {
                String c = strings[col];
                if (!c.isEmpty())
                    columnSizes[col] = Math.max(columnSizes[col], c.length() + 2);
            }
        }

        for (int i = 0; i < table.length; i++) {
            String[] r = table[i];
            render_row(columnSizes, r);
            if (i == 0) {
                render_row(columnSizes, Arrays.stream(columnSizes).mapToObj("-"::repeat).toArray(String[]::new));
            }
        }
    }
}
