class RobotFactory {
    public static Robot create(String type, String id, int battery) {
        if (type.equalsIgnoreCase("explorer")) 
            return new Robot.Builder().setId(id).setBattery(battery).buildExplorer();
        if (type.equalsIgnoreCase("medical")) 
            return new Robot.Builder().setId(id).setBattery(battery).buildMedical();
        return null;
    }
}

interface RobotStrategy {
    int execute();
}

class SearchStrategy implements RobotStrategy {
    public int execute() { return (int) (Math.random() * 3); }
}

class RescueStrategy implements RobotStrategy {
    public int execute() { return 1; }
}

interface RobotObserver {
    void onEvent(String msg);
}

class ConsoleObserver implements RobotObserver {
    public void onEvent(String msg) {
        System.out.println("EVENT: " + msg);
    }
}