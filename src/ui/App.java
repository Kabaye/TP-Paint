package ui;

import figure.Drawable;
import figure.LineSegment;
import utils.Figures;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App extends JFrame {
    private JPanel rootPane;
    private JPanel controlPane;
    private JPanel buttonPane;
    private JPanel colorPane;
    private JButton innerColorBtn;
    private JButton borderColorBtn;
    private JLabel innerColorLabel;
    private JLabel borderColorLabel;
    private JToggleButton regularPolygonBtn;
    private JToggleButton rayBtn;
    private JToggleButton lineSegmentBtn;
    private JToggleButton lineBtn;
    private JPanel drawPane;
    private JToggleButton invisibleToggleBtn;
    private DrawActions drawAction = DrawActions.MOVE;
    private int numOfSidesForRegularPolygon;
    private List<Drawable> drawables;
    private Color borderColor = Color.BLACK;
    private Color innerColor = Color.BLUE;
    public App() {
        super("Paint™ 2020. Kabaye Inc.");
        setContentPane(rootPane);
        drawables = new ArrayList<>();
        setSize(1400, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        configureUI();
    }

    public static void main(String[] args) {
        new App();
    }

    private void configureUI() {
        lineSegmentBtn.addActionListener(e -> drawAction = DrawActions.LINE_SEGMENT);
        rayBtn.addActionListener(e -> drawAction = DrawActions.RAY);
        lineBtn.addActionListener(e -> drawAction = DrawActions.LINE);

        regularPolygonBtn.addActionListener(e -> {
            final String dialogOutput = JOptionPane.showInputDialog(rootPane,
                    "Please, input number of regular polygon sides:", 5);
            try {
                drawAction = DrawActions.REGULAR_POLYGON;
                numOfSidesForRegularPolygon = Integer.parseInt(dialogOutput);
            } catch (NumberFormatException | NullPointerException exc) {
                regularPolygonBtn.setSelected(false);
                invisibleToggleBtn.doClick();
            }
        });

        drawPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    switch (drawAction) {
                        case LINE_SEGMENT:
                            drawables.add(Figures.createLineSegment(e.getPoint(), e.getPoint(), borderColor));
                            repaint();
                            break;
                        case RAY:
                            drawables.add(Figures.createRay(e.getPoint(), e.getPoint(), borderColor));
                            break;
                        case LINE:
                            drawables.add(Figures.createLine(e.getPoint(), e.getPoint(), borderColor));
                            break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                drawables = drawables.stream().filter(drawable -> !drawable.nextForRemoving()).collect(Collectors.toList());
            }
        });

        drawPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && !drawables.isEmpty()) {
                    Drawable drawable = drawables.get(drawables.size() - 1);
                    switch (drawAction) {
                        case LINE_SEGMENT:
                        case RAY:
                        case LINE:
                            LineSegment segment = ((LineSegment) drawable);
                            segment.setSecondPoint(e.getPoint());
                    }
                    repaint();
                }
            }
        });


        setVisible(true);
    }

    private void createUIComponents() {
        drawPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graphics2D = ((Graphics2D) g);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                for (Drawable drawable : drawables) {
                    drawable.draw(graphics2D);
                }
            }
        };
    }
}
