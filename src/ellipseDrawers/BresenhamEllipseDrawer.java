package ellipseDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamEllipseDrawer implements EllipseDrawer {

    private PixelDrawer pd;

    public BresenhamEllipseDrawer (PixelDrawer pd) {
        this.pd = pd;
    }

    public void drawEllipse (int x, int y, int a, int b, Color color) {
        int centerX = x;
        int centerY = y;
        x = 0;
        y = b;
        int squareA = a * a;
        int squareB = b * b;
        int delta = 4 * squareB * ((x + 1) * (x + 1)) + squareA * ((2 * y - 1) * (2 * y - 1)) - 4 * squareA * squareB;

        while (squareA * (2 * y - 1) > 2 * squareB * (x + 1)) {
            pd.drawPixel(centerX + x, centerY + y, color);
            pd.drawPixel(centerX + x, centerY - y, color);
            pd.drawPixel(centerX - x, centerY + y, color);
            pd.drawPixel(centerX - x, centerY - y, color);
            if (delta < 0) {
                x++;
                delta += 4 * squareB * (2 * x + 3);
            } else {
                x++;
                delta += -8 * squareA * (y - 1) + 4 * squareB * (2 * x + 3);
                y--;
            }
        }
        delta = squareB * ((2 * x + 1) * (2 * x + 1)) + 4 * squareA * ((y + 1) * (y + 1)) - 4 * squareA * squareB;
        while (y + 1 != 0) {
            pd.drawPixel(centerX + x, centerY + y, color);
            pd.drawPixel(centerX + x, centerY - y, color);
            pd.drawPixel(centerX - x, centerY + y, color);
            pd.drawPixel(centerX - x, centerY - y, color);
            if (delta < 0) {
                y--;
                delta += 4 * squareA * (2 * y + 3);
            } else {
                y--;
                delta += -8 * squareB * (x + 1) + 4 * squareA * (2 * y + 3);
                x++;
            }
        }
    }
}
