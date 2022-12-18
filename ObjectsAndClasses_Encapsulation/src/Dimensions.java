public class Dimensions {  // габариты

    private final int width; // ширина
    private final int height;  // высота
    private final int length; // длина
    private final int volume = 0;


    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public Dimensions getVolume(int width, int height, int length) {

        return new Dimensions(width, height, length);
    }

}


