package ellipseDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamCircleDrawer {

    private PixelDrawer pd;

    public BresenhamCircleDrawer (PixelDrawer pd) {
        this.pd = pd;
    }

    public void drawCircle (int centerX, int centerY, int radius, Color color) {
        int x = 0;
        int y = radius;
        int delta = 2 - 2 * radius;
        int error = 0;
        while (y >= 0){
            pd.drawPixel(centerX + x, centerY + y, color);
            pd.drawPixel(centerX + x, centerY - y, color);
            pd.drawPixel(centerX - x, centerY + y, color);
            pd.drawPixel(centerX - x, centerY - y, color);
            error = 2 * (delta + y) - 1;
            if (delta < 0 && error <= 0) {
                x++;
                delta += x + 1;
            } else if (delta > 0 && error > 0) {
                y--;
                delta -= y + 1;
            } else {
                x++;
                delta += x - y;
                y--;
            }
        }
    }

}
