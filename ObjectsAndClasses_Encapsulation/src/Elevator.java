public class Elevator {
    private int currentFloor = 0;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int moveDown() {
        currentFloor = currentFloor + 1;
        return currentFloor;
    }

    public int moveUp() {
        currentFloor = currentFloor - 1;
        return currentFloor;
    }

    public boolean move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Error");
        } else if (floor > currentFloor)  {
            System.out.println("going to level: " + moveUp());
        } else if (floor < currentFloor){
            System.out.println(moveDown());
        } else {
            System.out.println("current level");
        }
        return false;
    }


}
