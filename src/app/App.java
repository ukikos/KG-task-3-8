package app;

import javax.swing.*;

public class App extends JFrame {
    private DrawPanel drawPanel;

    public App() {
        super("Таск3");
        drawPanel = new DrawPanel();
        this.add(drawPanel);
        this.addKeyListener(drawPanel);
    }
}
