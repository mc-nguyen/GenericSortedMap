// PROGRAMMER: MC NGUYEN

public class SortedMapNodeTester
{
    public static void testing() {
        SortedMapNode<Integer, String> studentA = new SortedMapNode<Integer, String>(
            1,
            "MC NGUYEN",
            null,
            null
        );
        SortedMapNode<Integer, String> studentB = new SortedMapNode<Integer, String>(
            2,
            "HOANG LE",
            null,
            null
        );
        SortedMapNode<Integer, String> studentC = new SortedMapNode<Integer, String>(
            3,
            "PHUC DO",
            null,
            null
        );
        
        System.out.println("\nTesting Sorted Map Node:\n");
        System.out.printf("Student A: %d - %s%n", studentA.getKey(), studentA.getValue());
        System.out.println("Less: " + studentA.getLessThanNode() + " - Greater: " + studentA.getGreaterThanNode());
        System.out.printf("Student B: %d - %s%n", studentB.getKey(), studentB.getValue());
        System.out.println("Less: " + studentB.getLessThanNode() + " - Greater: " + studentB.getGreaterThanNode());
        System.out.printf("Student C: %d - %s%n", studentC.getKey(), studentC.getValue());
        System.out.println("Less: " + studentC.getLessThanNode() + " - Greater: " + studentC.getGreaterThanNode());
        
        System.out.println("\nSetting less and greater");
        studentA.setGreaterThanNode(studentB);
        studentB.setLessThanNode(studentA);
        studentB.setGreaterThanNode(studentC);
        studentC.setLessThanNode(studentB);
        
        System.out.printf("Student A: %d - %s%n", studentA.getKey(), studentA.getValue());
        System.out.println("Less: " + studentA.getLessThanNode() + " - Greater: " + studentA.getGreaterThanNode().getKey());
        System.out.printf("Student B: %d - %s%n", studentB.getKey(), studentB.getValue());
        System.out.println("Less: " + studentB.getLessThanNode().getKey() + " - Greater: " + studentB.getGreaterThanNode().getKey());
        System.out.printf("Student C: %d - %s%n", studentC.getKey(), studentC.getValue());
        System.out.println("Less: " + studentC.getLessThanNode().getKey() + " - Greater: " + studentC.getGreaterThanNode());
        
        System.out.println("\nChange key and value of student A");
        studentA.setKey(0); studentA.setValue("Marvin Calvin Nguyen");
        System.out.printf("Student A: %d - %s%n", studentA.getKey(), studentA.getValue());
    }
}
