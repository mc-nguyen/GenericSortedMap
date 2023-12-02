// PROGRAMMER: MC NGUYEN

import java.util.Scanner;
import java.io.IOException;

public class SortedMapTester {
    public static void defaultConstructorTest() throws IOException {
        System.out.println("\nTesting with default constructor and its methods\n");
        
        SortedMap<Integer, String> defaultInstance = new SortedMap<Integer, String>();
        System.out.println("Size: " + defaultInstance.getSize());
        System.out.println("Empty? " + defaultInstance.isEmpty());
        
        System.out.println("\nAdding Items...");
        defaultInstance.add(1, "Strawberry");
        defaultInstance.add(2, "Lemon");
        defaultInstance.add(3, "Blueberry");
        defaultInstance.add(4, "Cranberry");
        
        System.out.println("\nSize: " + defaultInstance.getSize());
        System.out.println("Empty? " + defaultInstance.isEmpty());
        System.out.printf("Smallest: %d - Largest: %d%n", defaultInstance.getSmallestValue(), defaultInstance.getLargestValue());
        System.out.println("Contains item 1: " + defaultInstance.contains(1));
        System.out.println("Contains item 2 and 3: " + defaultInstance.containsAll(new Integer[]{2, 3}));
        
        System.out.println("Remove smallest value: " + defaultInstance.removeSmallestValue());
        System.out.println("Size: " + defaultInstance.getSize());
        System.out.println("Remove largest value: " + defaultInstance.removeLargestValue());
        System.out.println("Size: " + defaultInstance.getSize());
        System.out.println("Remove key #2"); defaultInstance.remove(2);
        System.out.println("Size: " + defaultInstance.getSize());
        System.out.println("Replace key #3 with 'Cranberry'"); defaultInstance.replace(3, "Cranberry");
        System.out.println("Size: " + defaultInstance.getSize());
        
        System.out.print("\nSaving to file");
        Scanner console = new Scanner(System.in);
        String filename = console.next();
        System.out.println(filename);
        defaultInstance.saveToFile(filename);
        
        System.out.println("\nClear the list"); defaultInstance.clear();
        System.out.println("Size: " + defaultInstance.getSize());
        System.out.println("Empty? " + defaultInstance.isEmpty());
    }

    public static void overloadedConstructorTest() throws IOException {
        System.out.println("\nTesting with overloaded constructor and its methods\n");
        
        SortedMap<Integer, String> defaultInstance = new SortedMap<Integer, String>();
        System.out.println("\nAdding Items to default instance...");
        defaultInstance.add(1, "Strawberry");
        defaultInstance.add(2, "Lemon");
        defaultInstance.add(3, "Blueberry");
        defaultInstance.add(4, "Cranberry");
        
        System.out.println("Creating overloaded instance");
        SortedMap<Integer, String> overloaded = new SortedMap<Integer, String>(defaultInstance);
        
        System.out.println("\nSize: " + overloaded.getSize());
        System.out.println("Empty? " + overloaded.isEmpty());
        System.out.printf("Smallest: %d - Largest: %d%n", overloaded.getSmallestValue(), overloaded.getLargestValue());
        System.out.println("Contains item 1: " + overloaded.contains(1));
        System.out.println("Contains item 2 and 3: " + overloaded.containsAll(new Integer[]{2, 3}));
        
        System.out.println("\nAdding items to the overloaded...");
        overloaded.add(1, "Grapefruit");
        overloaded.add(5, "Strawberry");
        defaultInstance.add(5, "Banana");
        defaultInstance.add(6, "Grape");
        overloaded.addAll(defaultInstance);
        System.out.println("\nSize: " + overloaded.getSize());
        System.out.println("Empty? " + overloaded.isEmpty());
        
        System.out.println("\nContain key #1: " + overloaded.contains(1));
        System.out.println("Contain key #7: " + overloaded.contains(7));
        System.out.println("Contain key #2 and #5: " + overloaded.containsAll(new Integer[]{2, 5}));
        System.out.println("Contain key #3 and #8 " + overloaded.containsAll(new Integer[]{3, 8}));
        
        System.out.println("\nRemove smallest value: " + overloaded.removeSmallestValue());
        System.out.println("Size: " + overloaded.getSize());
        System.out.println("Remove largest value: " + overloaded.removeLargestValue());
        System.out.println("Size: " + overloaded.getSize());
        System.out.println("Remove key #2"); overloaded.remove(2);
        System.out.println("Size: " + overloaded.getSize());
        defaultInstance.remove(5);
        defaultInstance.remove(6);
        System.out.println("Remove keys from the default"); overloaded.remove(2);
        overloaded.removeAll(defaultInstance);
        System.out.println("Size: " + overloaded.getSize());
        System.out.println("Replace key #5 with 'Banana'"); overloaded.replace(5, "Banana");
        System.out.println("Size: " + overloaded.getSize());
        
        System.out.println("\nAdding keys from default again...");
        overloaded.addAll(defaultInstance);
        
        System.out.print("\nSaving to file ");
        Scanner console = new Scanner(System.in);
        String filename = console.next();
        System.out.println(filename);
        overloaded.saveToFile(filename);
        
        System.out.println("\nClear the list"); overloaded.clear();
        System.out.println("Size: " + overloaded.getSize());
        System.out.println("Empty? " + overloaded.isEmpty());
    }
}