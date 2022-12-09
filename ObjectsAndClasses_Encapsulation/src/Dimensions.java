public class Dimensions {  // габариты
    private int width; // ширина
    private int height;  // высота
    private int length;  // длина
    private int volume = width * height * length;

    public Dimensions(int volume) {

        this.volume = volume;
    }

    public int getVolume() {

        return volume;
    }

}


