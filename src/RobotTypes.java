class ExplorerRobot extends Robot {
    public ExplorerRobot(String id, int battery) {
        this.id = id;
        this.battery = battery;
    }

    @Override
    public void performTask() {
        if (battery <= 0) return;
        if (strategy != null) {
            int found = strategy.execute();
            if (found > 0) {
                notifyObservers("Explorer " + id + " found " + found + " survivor(s)!");
                Main.survivorsFound += found;
            }
        }
    }

    @Override
    public void drainBattery() {
        this.battery -= 10;
    }
}

class MedicalRobot extends Robot {
    public MedicalRobot(String id, int battery) {
        this.id = id;
        this.battery = battery;
    }

    @Override
    public void performTask() {
        if (battery <= 0) return;
        if (strategy != null && Main.survivorsFound > 0) {
            Main.survivorsFound--;
            Main.totalRescued++;
            notifyObservers("Medical " + id + " rescued survivor.");
        }
    }

    @Override
    public void drainBattery() {
        this.battery -= 15;
    }
}