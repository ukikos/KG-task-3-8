package lineDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class WULineDrawer implements LineDrawer {

    private PixelDrawer pd;

    public WULineDrawer (PixelDrawer pd) {
        this.pd = pd;
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        if (x2 < x1) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (Math.abs(dx) > Math.abs(dy)) {
            drawHorizontal(x1, y1, x2, y2, color);
        } else {
            drawVertical(x1, y1, x2, y2, color);
        }
    }

    private void drawVertical(int x1, int y1, int x2, int y2, Color color) {
        if (y2 < y1) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }

        int signX = 0;

        int dx = x2 - x1;
        int dy = y2 - y1;
        if (dx > 0) {
            signX = 1;
        } else {
            signX = -1;
        }

        int dxAbs = Math.abs(x1 - x2);
        int dyAbs = Math.abs(y1 - y2);
        int error = 0;
        int dErr = Math.abs(dx) + 1;
        int x = x1;

        float incr = (float) (dx) / (float) (dy);
        float gradient = incr;

        for (int y = y1; y < y2; y++) {
            pd.drawGradientPixel(x, y + 1, color, Math.abs((int)gradient - gradient));
            pd.drawGradientPixel(x, y, color, 1 - Math.abs((int)gradient - gradient));
            gradient += incr;
            error += dErr;
            if (error >= (dyAbs + 1)) {
                x += signX;
                error -= (dxAbs + 1);
            }
        }
    }

    private void drawHorizontal(int x1, int y1, int x2, int y2, Color color) {
        if (x2 < x1) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }

        int signY = 0;

        int dx = x2 - x1;
        int dy = y2 - y1;
        if (dy > 0) {
            signY = 1;
        } else {
            signY = -1;
        }

        int dxAbs = Math.abs(dx);
        int dyAbs = Math.abs(dy);
        int error = 0;
        int dErr = (dyAbs + 1);
        int y = y1;

        float incr = (float) (dy) / (float) (dx);
        float gradient = incr;
        for (int x = x1; x < x2; x++) {
            pd.drawGradientPixel(x, y + 1, color, Math.abs((int)gradient - gradient));
            pd.drawGradientPixel(x, y, color, 1 - Math.abs((int)gradient - gradient));
            gradient += incr;
            error += dErr;
            if (error >= (dxAbs + 1)) {
                y += signY;
                error -= (dxAbs + 1);
            }
        }
    }
}
