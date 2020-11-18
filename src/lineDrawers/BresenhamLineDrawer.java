package lineDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {

    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int dxAbs = Math.abs(x2 - x1);
        int dyAbs = Math.abs(y2 - y1);
        if (dxAbs > dyAbs) {
            drawHorizontal(pd, x1, y1, x2, y2, color);
        } else {
            drawVertical(pd, x1, y1, x2, y2, color);
        }
    }

    public void drawHorizontal(PixelDrawer pd, int x1, int y1, int x2, int y2, Color color) {
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

        for (int x = x1; x < x2; x++) {
            pd.drawPixel(x, y, color);
            error += dErr;
            if (error >= (dxAbs + 1)) {
                y += signY;
                error -= (dxAbs + 1);
            }
        }
    }

    public void drawVertical(PixelDrawer pd, int x1, int y1, int x2, int y2, Color color) {
        if (y2 < y1) {
            int temp = y2;
            y2 = y1;
            y1 = temp;
            temp = x2;
            x2 = x1;
            x1 = temp;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        int signX = 0;
        if (dx > 0) {
            signX = 1;
        } else {
            signX = -1;
        }

        int dxAbs = Math.abs(x1 - x2);
        int dyAbs = Math.abs(y1 - y2);
        int error = 0;
        int dErr = (dxAbs + 1);
        int x = x1;

        for (int y = y1; y < y2; y++) {
            pd.drawPixel(x, y, color);
            error += dErr;
            if (error >= (dyAbs + 1)) {
                x += signX;
                error -= (dyAbs + 1);
            }
        }
    }



}
