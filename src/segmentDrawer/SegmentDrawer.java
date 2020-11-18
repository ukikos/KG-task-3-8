package segmentDrawer;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class SegmentDrawer extends BresenhamLineDrawer{
    PixelDrawer pd;

    public SegmentDrawer (PixelDrawer pd) {
        super(pd);
        this.pd = pd;
    }

    public void drawSegment (int centerX, int centerY, int radius, double startAngle, double endAngle, Color color) {
        if (startAngle > endAngle) {
            double temp = endAngle;
            endAngle = startAngle;
            startAngle = temp;
        }
        int startX = (int) (centerX + radius * Math.cos(startAngle));
        int startY = (int) (centerY - radius * Math.sin(startAngle));
        int x = 0, y = 0;
        double theta = startAngle;
        while (theta <= endAngle) {
            x = (int) (centerX + radius * Math.cos(theta));
            y = (int) (centerY - radius * Math.sin(theta));
            pd.drawPixel(x, y, color);
            theta = theta + 0.0001;
        }
        drawLine(x, y, startX, startY, color);
    }
}
