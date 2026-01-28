import java.util.ArrayList;
import java.util.List;

public abstract class Robot {
    protected String id;
    protected int battery;
    protected RobotStrategy strategy;
    protected List<RobotObserver> observers = new ArrayList<>();

    public void addObserver(RobotObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers(String message) {
        for (RobotObserver obs : observers) {
            obs.onEvent(message);
        }
    }

    public abstract void performTask();
    public abstract void drainBattery();

    public void setStrategy(RobotStrategy strategy) {
        this.strategy = strategy;
    }

    public String getId() { return id; }
    public int getBattery() { return battery; }

    public static class Builder {
        private String id;
        private int battery = 100;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setBattery(int battery) {
            this.battery = battery;
            return this;
        }

        public ExplorerRobot buildExplorer() {
            return new ExplorerRobot(id, battery);
        }

        public MedicalRobot buildMedical() {
            return new MedicalRobot(id, battery);
        }
    }
}