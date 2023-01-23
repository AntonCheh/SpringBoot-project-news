public class Container {
    private Integer count = 0;
    // если я правильно понял, то Integer не может быть не задан
    // либо нужно изменить Integer на примитив int


    public void addCount(int value) {

        count = count + value;
    }

    public int getCount() {
           return count;
    }
}
