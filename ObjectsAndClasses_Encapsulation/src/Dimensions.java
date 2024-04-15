public class Dimensions {  // габариты

    private final int width; // ширина
    private final int height;  // высота
    private final int length; // длина

    public int getVolume() {

        return width * height * length;
    }

    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;

    }



}


