public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public int moveDown() {
        --this.currentFloor;
        if (this.currentFloor < this.minFloor) {
            System.out.println("Ошибка: этаж отсутствует, нижний этаж: " + this.minFloor);
            return 1;
        } else {
            return this.currentFloor;
        }
    }

    public int moveUp() {
        ++this.currentFloor;
        if (this.currentFloor > this.maxFloor) {
            System.out.println("Ошибка: этаж отсутствует, верхний этаж: " + this.maxFloor);
            return 1;
        } else {
            return this.currentFloor;
        }
    }

    public boolean move(int floor) {
        this.currentFloor = floor;
        if (floor >= this.minFloor) {
            System.out.println("Лифт едет на " + floor + " этаж");
        }

        if (floor == 1) {
            System.out.println("Вы на этом этаже, введите другой этаж");
        }

        return false;
    }
}
