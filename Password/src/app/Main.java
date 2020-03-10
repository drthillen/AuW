package app;

import java.util.*;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file

        In.open("Password/publicTests/test2.in");

        int t = In.readInt();

        for (int i = 0; i < t; i++) {
            testCase();
        }

        // Uncomment this line if you want to read from a file
        In.close();
    }

    public static void testCase() {
        // Input using In.java class
        // Construction
        int n = In.readInt();
        // Getting Strings
        In.readLine();
        ArrayList<String> availableStrings = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            availableStrings.add(In.readLine());
        }
        // Creating HashMap
        HashMap<String, Integer> stringMap = new HashMap<>();
        HashMap<String, Integer> stringMapIn = new HashMap<>();
        HashMap<String, Integer> stringMapOut = new HashMap<>();
        HashMap<String, Integer> components = new HashMap<>(); // UnionFind

        for (String v : availableStrings) {
            stringMap.put(v.substring(0, 2), 0);
            stringMap.put(v.substring(1, 3), 0);
            stringMapIn.put(v.substring(0, 2), 0);
            stringMapIn.put(v.substring(1, 3), 0);
            stringMapOut.put(v.substring(0, 2), 0);
            stringMapOut.put(v.substring(1, 3), 0);

        }

        int componentIndex = 0;
        for (String v : stringMap.keySet()) {
            components.put(v, componentIndex++);
        }

        // add edges
        for (String v : availableStrings) {
            stringMap.put(v.substring(0, 2), stringMap.get(v.substring(0, 2)) + 1);
            stringMap.put(v.substring(1, 3), stringMap.get(v.substring(1, 3)) + 1);
            stringMapOut.put(v.substring(0, 2), stringMapOut.get(v.substring(0, 2)) + 1);
            stringMapIn.put(v.substring(1, 3), stringMapIn.get(v.substring(1, 3)) + 1);
            merge(components, v.substring(0, 2), v.substring(1, 3));
        }

        boolean inOutDegreeParity = true;

        for (String v : stringMap.keySet()) {
            if (Math.abs((stringMapOut.get(v) - stringMapIn.get(v))) > 1) {
                inOutDegreeParity = false;
            }
        }

        int odd = 0;
        int even = 0;
        for (String v : stringMap.keySet()) {
            if (stringMap.get(v) % 2 == 1) {
                odd++;
            } else {
                even++;
            }

        }
        HashSet<Integer> componentSet = new HashSet<Integer>();
        componentSet.addAll(components.values());
        if ((odd == 2 || odd == 0) && componentSet.size() == 1 && inOutDegreeParity) {
            Out.println("yes");
            // System.out.println("odd: " + odd);
            // System.out.println(components);
        } else {
            Out.println("no");
            // System.out.println("odd: " + odd);
            // System.out.println(components);
        }
        // System.out.println();
    }

    public static void merge(HashMap<String, Integer> components, String vertex1, String vertex2) {
        int comp1 = components.get(vertex1);
        int comp2 = components.get(vertex2);

        int common = Math.min(comp1, comp2);

        for (String v : components.keySet()) {
            if (components.get(v) == comp1 || components.get(v) == comp2) {
                components.put(v, common);
            }
        }

    }
}