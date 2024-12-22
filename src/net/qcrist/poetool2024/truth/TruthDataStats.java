package net.qcrist.poetool2024.truth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TruthDataStats {
    public static void main(String[] args) throws IOException {
        List<Truth> truths = Truth.loadTruths(Truth.TRUTHS_DIR);
        printStatsFor(truths);
    }

    public static void printStatsFor(List<Truth> truths) {
        Map<Integer, Integer> sizes = new TreeMap<>();
        for (Truth truth : truths) {
            sizes.merge(truth.length, 1, Integer::sum);
        }
        System.out.println("sizes = " + sizes);
    }
}
