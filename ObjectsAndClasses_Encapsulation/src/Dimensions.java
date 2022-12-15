public class Dimensions {  // габариты

    private final int width; // ширина
    private final int height;  // высота
    private final int length;  // длина

    public Dimensions volume (int width, int height, int length) {
        return new Dimensions (width, height, length);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public Dimensions(int width, int height, int length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    }


