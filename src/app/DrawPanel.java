package app;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;
import segmentDrawer.SegmentDrawer;
import utils.Line;
import utils.RealPoint;
import utils.ScreenPoint;
import utils.Segment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener {
    private ScreenPoint lastPosition = null;
    private PixelDrawer pixelDrawer = null;
    private LineDrawer lineDrawer = null;

    private Segment currentSegment = null;
    private Segment lastSegment = null;

    SegmentDrawer segmentDrawer = null;
    private List<Segment> segments = new LinkedList<>();

    public DrawPanel() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
    }

    void addSegment(Segment segment) {
        segments.add(segment);
        repaint();
    }

    void removeSegment(Segment segment) {
        segments.removeIf(segment1 -> segment1.equals(segment));
        repaint();
    }

    private void drawSegments(SegmentDrawer segmentDrawer) {
        for (Segment segment : segments) {
            segmentDrawer.drawSegment(segment.getCenterX(), segment.getCenterY(), segment.getRadius(), segment.getStartAngle(), segment.getEndAngle(), segment.getColor());
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        pixelDrawer = new PixelDrawer(graphics2D);
        lineDrawer = new BresenhamLineDrawer(pixelDrawer);
        segmentDrawer = new SegmentDrawer(pixelDrawer);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        drawLegend(graphics2D);
        drawSegments(segmentDrawer);
        graphics2D.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            currentSegment = new Segment(e.getX(), e.getY(), 100, 1, 4, Color.BLACK);
            addSegment(currentSegment);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) lastPosition = new ScreenPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint currentPosition = new ScreenPoint(e.getX(), e.getY());
        if (lastPosition != null) {
            ScreenPoint deltaScreen = new ScreenPoint(
                    currentPosition.getX() - lastPosition.getX(),
                    currentPosition.getY() - lastPosition.getY());
            currentSegment.setCenterX(currentSegment.getCenterX() + deltaScreen.getX());
            currentSegment.setCenterY(currentSegment.getCenterY() + deltaScreen.getY());
            lastPosition = currentPosition;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        int scale = 0;
        int delta = clicks < 0 ? 2 : -2;
        for (int i = 0; i < Math.abs(clicks); i++) {
            scale += delta;
        }
        currentSegment.setRadius(currentSegment.getRadius() + scale);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                currentSegment.setStartAngle(currentSegment.getStartAngle() - 0.05);
                break;
            case KeyEvent.VK_RIGHT:
                currentSegment.setStartAngle(currentSegment.getStartAngle() + 0.05);
                break;
            case KeyEvent.VK_UP:
                currentSegment.setEndAngle(currentSegment.getEndAngle() + 0.05);
                break;
            case KeyEvent.VK_DOWN:
                currentSegment.setEndAngle(currentSegment.getEndAngle() - 0.05);
                break;
            case KeyEvent.VK_Q:
                currentSegment.setStartAngle(currentSegment.getStartAngle() - 0.05);
                currentSegment.setEndAngle(currentSegment.getEndAngle() - 0.05);
                break;
            case KeyEvent.VK_E:
                currentSegment.setStartAngle(currentSegment.getStartAngle() + 0.05);
                currentSegment.setEndAngle(currentSegment.getEndAngle() + 0.05);
                break;
            case KeyEvent.VK_SPACE:
                segments.clear();
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void drawLegend(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        graphics2D.drawString("Управление: ", getWidth() - 250, getHeight() - 230);
        graphics2D.drawString("ЛКМ -- создать сегмент", getWidth() - 250, getHeight() - 230 + 20);
        graphics2D.drawString("ПКМ -- перемещение", getWidth() - 250, getHeight() - 230 + 40);
        graphics2D.drawString("СКМ -- масштабирование", getWidth() - 250, getHeight() - 230 + 60);
        graphics2D.drawString("Q -- поворот против часовой стр.", getWidth() - 250, getHeight() - 230 + 80);
        graphics2D.drawString("E -- поворот по часовой стр.", getWidth() - 250, getHeight() - 230 + 100);
        graphics2D.drawString("Пробел -- очистить панель", getWidth() - 250, getHeight() - 230 + 120);
        graphics2D.drawString("↑ -- увеличить конечный угол", getWidth() - 250, getHeight() - 230 + 140);
        graphics2D.drawString("↓ -- уменьшить конечный угол", getWidth() - 250, getHeight() - 230 + 160);
        graphics2D.drawString("← -- уменьшить начальный угол", getWidth() - 250, getHeight() - 230 + 180);
        graphics2D.drawString("→ -- увеличить начальный угол", getWidth() - 250, getHeight() - 230 + 200);
    }
}
