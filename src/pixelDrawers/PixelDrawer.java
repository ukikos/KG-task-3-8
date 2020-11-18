package pixelDrawers;

import java.awt.*;

public class PixelDrawer implements PixDrawer {
    private Graphics2D g;

    public PixelDrawer(Graphics2D g) {
        this.g = g;
    }

    public void drawPixel(int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, 1, 1);
    }

    public void drawGradientPixel(int x, int y, Color color, float brightness) {
        int[] comp = {color.getRed(), color.getGreen(), color.getBlue()};
        Color col = new Color(comp[0], comp[1], comp[2], (int) (brightness * 255));
        g.setColor(col);
        g.fillRect(x, y, 1, 1);
    }
}
