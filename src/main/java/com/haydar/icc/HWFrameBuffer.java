package main.java.com.haydar.icc;

public class HWFrameBuffer implements Runnable {

    private char[][] hwData;
    private boolean frameStatus;

    public HWFrameBuffer() {
        this.hwData = new char[1000][1000];
    }

    public boolean isFrameStatus() {
        return frameStatus;
    }

    public void setFrameStatus(boolean frameStatus) {
        this.frameStatus = frameStatus;
    }

    public void write(char c) {
        for (int i = 0; i < hwData.length; i++) {
            for (int j = 0; j < hwData[0].length; j++) {
                hwData[i][j] = c;
            }
        }
    }

    public char getPixel(int x, int y) {
        return hwData[x][y];
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
