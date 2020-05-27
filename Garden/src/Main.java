/*
* Small Description of how to solve the exercise.
* Calculate the ConvexHull Polygon of the roses. Check for each weed, whether it lies in the garden. I.e. word check if
* there is some segment defined by two roses, such that the weed is on the right of this segment.
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        // In.open("public/sample.in");
        // Out.compareTo("public/sample.out");

        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }

        // Uncomment this line if you want to read from a file
        // In.close();
    }

    public static void testCase() {
        // Input using In.java class
        int n = In.readInt();
        int m = In.readInt();

        Point[] roses = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = In.readInt();
            int y = In.readInt();
            roses[i] = new Point(x, y);
        }

        Point[] weed = new Point[m];

        for (int j = 0; j < m; j++) {
            int x = In.readInt();
            int y = In.readInt();
            weed[j] = new Point(x, y);
        }

        // Output using Out.java class
        List<Point> polygon = getConvexHull(roses);
        for (Point p: weed) {
            if (isInConvexHull(polygon, p)){
                    Out.print("y");}
            else{
                Out.print("n");
            }
        }
        Out.println();
    }

    private static List<Point> getConvexHull(Point[] points) {
        int h = 0;
        int n = points.length;
        //
        List<Point> pointsList = Arrays.asList(points);
        pointsList.sort(null);
        List<Point> polygon = new ArrayList<>(points.length);
        //
        polygon.add(pointsList.get(0)); // The most left element of the point set is definitely in the polygon
        //
        for (int i = 1; i < n; i++) { // start at second element, first element will definitely remain in poly.
            Point currentPoint = pointsList.get(i);
            while (h >= 1 && (polygon.get(h).isOnTheLeft(polygon.get(h-1), currentPoint))) {
                h--;
            }
            // poly[h] is on the right of poly[h-1] --> currentPoint; we can safely add currentRose to poly[h+1]
            h++;
            if (h < polygon.size()) {
                polygon.set(h, currentPoint);
            } else {
                polygon.add(currentPoint);
            }
        }
        // poly[h] = points.last() = points[n-1];
        int h2 = h;
        for (int i = n-2; i >= 0; i--) { // start at second lat element
            Point currentPoint = pointsList.get(i);
            while (h > h2 && (polygon.get(h).isOnTheLeft(polygon.get(h - 1), currentPoint))) {
                // h is currently last index of polygon
                h--;
            }
            h++;
            // poly[h] is on the right of poly[h-1] --> currentPoint; we can safely add currentRose to poly[h+1]
            if (h < polygon.size()) { // checking wherther list is large enough
                polygon.set(h, currentPoint);
            } else {
                polygon.add(currentPoint);
            }
        }
        polygon = polygon.subList(0, h); // remove all elements that have been added but are logically deleted.
        return polygon;
    }

    private static boolean isInConvexHull(List<Point> poly, Point test) {
        int n = poly.size();
        for (int i = 0; i < n; i++) {
            if (! test.isOnTheLeft(poly.get(i%n), poly.get((i+1)%n))) {
                return false;
            }
        }
        return true;
    }
}


class Point implements Comparable<Point> {
    int x;
    int y;
    static int ID_CLASS = 1;
    int ID = ID_CLASS++;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean isOnTheLeft(Point from, Point to) {
        return (this.y-from.y)*(to.x-from.x) >= (this.x-from.x)*(to.y-from.y) ; // positive determinant
        // Script: (from.x-this.x)*(to.y-this.y) > (from.y-this.y)*(to.x-this.x)
        // this equivalence can be proven be calculating via distributivity.
    }

    @Override
    public int compareTo(Point point) {
        if (this.x < point.x) {
            return -1;
        } else if (this.x == point.x) {
            return (this.y < point.y) ? -1: 1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + " ("+this.x+","+this.y+")";
    }
}