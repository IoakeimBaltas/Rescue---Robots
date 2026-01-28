import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int survivorsFound = 0; 
    public static int totalRescued = 0;   
    private static ArrayList<Robot> robotList = new ArrayList<>();
    private static ConsoleObserver observer = new ConsoleObserver();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- Rescue Robots Menu ---");
            System.out.println("1. Create robot");
            System.out.println("2. List robots");
            System.out.println("3. Assign strategy to robot");
            System.out.println("4. Run simulation round");
            System.out.println("5. Exit");
            System.out.print("Select: ");
            
            int choice = sc.nextInt();
            
            if (choice == 1) {
                System.out.print("Type (explorer/medical): ");
                String type = sc.next();
                System.out.print("ID: ");
                String id = sc.next();
                System.out.print("Initial Battery %: ");
                int batt = sc.nextInt();
                
                Robot r = RobotFactory.create(type, id, batt);
                if (r != null) {
                    r.addObserver(observer);
                    robotList.add(r);
                    System.out.println("Creating " + type + " with " + batt + "% battery...");
                }
            } 
            else if (choice == 2) {
                for (Robot r : robotList) {
                    System.out.println("ID: " + r.getId() + " | Battery: " + r.getBattery() + "%");
                }
            } 
            else if (choice == 3) {
                System.out.print("Enter Robot ID: ");
                String id = sc.next();
                for (Robot r : robotList) {
                    if (r.getId().equals(id)) {
                        if (r instanceof ExplorerRobot) r.setStrategy(new SearchStrategy());
                        if (r instanceof MedicalRobot) r.setStrategy(new RescueStrategy());
                        System.out.println("Strategy assigned!");
                    }
                }
            } 
            else if (choice == 4) {
                runRound();
            } 
            else if (choice == 5) {
                break;
            }

            if (totalRescued >= 5) {
                System.out.println("MISSION SUCCESS: 5 survivors rescued!");
                break;
            }
        }
    }

    private static void runRound() {
        System.out.println("--- Running Round ---");
        boolean anyRobotAlive = false;

        for (Robot r : robotList) {
            if (r.getBattery() > 0) {
                anyRobotAlive = true;
                r.performTask();   
                r.drainBattery();  
            }
        }

        System.out.println("Remaining survivors to rescue: " + (5 - totalRescued));
        
        if (!anyRobotAlive && robotList.size() > 0) {
            System.out.println("MISSION FAILED: All robots deactivated.");
        }
    }
}