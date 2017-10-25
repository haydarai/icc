package main.java.com.haydar.icc;

public class ICC {

    private boolean busy;
    private int currentFrame;
    private HWFrameBuffer hwFrameBuffers[];
    private char c;

    public ICC() {
        hwFrameBuffers = new HWFrameBuffer[2];
        hwFrameBuffers[0] = new HWFrameBuffer();
        hwFrameBuffers[1] = new HWFrameBuffer();
        c = 'A';
        currentFrame = 0;
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public synchronized int capture() throws InterruptedException {
        if (busy) {
            System.out.println("System is busy, please wait...");
            wait();
        } else if (hwFrameBuffers[0].isFrameStatus() && hwFrameBuffers[1].isFrameStatus()) {
            System.out.println("All buffer is new...");
            wait();
        }
        busy = true;
        hwFrameBuffers[currentFrame].write(c);
        hwFrameBuffers[currentFrame].setFrameStatus(true);
        Thread.sleep(10);
        currentFrame = 1 - currentFrame;
        busy = false;
        if (c == 'Z') {
            c = 'A';
        } else {
            c++;
        }

        return currentFrame;
    }

    public synchronized int memCopy(char[][] buffer, int fno) {
        int res = fno;
        for (int i = 0; i < buffer.length; i++) {
            for (int j = 0; j < buffer.length; j++) {
                buffer[i][j] = hwFrameBuffers[fno].getPixel(i, j);
            }
        }
        this.hwFrameBuffers[fno].setFrameStatus(false);
        this.notify();

        return res;
    }
}
