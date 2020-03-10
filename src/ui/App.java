package ui;

import figure.Drawable;
import figure.Ellipse;
import figure.LineSegment;
import figure.Polygon;
import figure.Polyline;
import figure.Triangle;
import utils.Drawables;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
    private JPanel sizePane;
    private JSlider brushSizeSld;
    private JToggleButton polygonBtn;
    private JToggleButton polylineBtn;
    private JToggleButton rectangleBtn;
    private JToggleButton rhombusBtn;
    private JToggleButton triangleBtn;
    private JToggleButton ellipseBtn;
    private JToggleButton circleBtn;
    private JToggleButton moveBtn;
    private DrawActions drawAction = DrawActions.MOVE;
    private int numOfSidesForRegularPolygon;
    private List<Drawable> drawables;
    private Color borderColor = Color.BLACK;
    private Color innerColor = Color.BLUE;
    private int brushSize;

    public App() {
        super("Paint™ 2020. Kabaye Inc.");
        setContentPane(rootPane);
        drawables = new ArrayList<>();
        setSize((int) (1920 / 1.3), (int) (1080 / 1.3));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        configureUI();
    }

    public static void main(String[] args) {
        new App();
    }

    private void configureUI() {
        brushSize = brushSizeSld.getValue();
        controlPaneListenersConfig();
        drawPaneConfig();

        setVisible(true);
    }

    private void controlPaneListenersConfig() {
        lineSegmentBtn.addActionListener(e -> drawAction = DrawActions.LINE_SEGMENT);
        rayBtn.addActionListener(e -> drawAction = DrawActions.RAY);
        lineBtn.addActionListener(e -> drawAction = DrawActions.LINE);
        polylineBtn.addActionListener(e -> drawAction = DrawActions.POLYLINE);
        regularPolygonBtn.addActionListener(e -> {
            final String dialogOutput = JOptionPane.showInputDialog(rootPane,
                    "Please, input number of regular polygon sides:", 5);
            try {
                drawAction = DrawActions.REGULAR_POLYGON;
                numOfSidesForRegularPolygon = Integer.parseInt(dialogOutput);
            } catch (NumberFormatException | NullPointerException exc) {
                unselectAllButtons();
            }
        });
        polygonBtn.addActionListener(e -> drawAction = DrawActions.POLYGON);
        rectangleBtn.addActionListener(e -> drawAction = DrawActions.RECTANGLE);
        rhombusBtn.addActionListener(e -> drawAction = DrawActions.RHOMBUS);
        triangleBtn.addActionListener(e -> drawAction = DrawActions.TRIANGLE);
        ellipseBtn.addActionListener(e -> drawAction = DrawActions.ELLIPSE);
        circleBtn.addActionListener(e -> drawAction = DrawActions.CIRCLE);

        innerColorBtn.addActionListener(e -> {
            Color innerColor = JColorChooser.showDialog(rootPane, "Choose inner color:", this.innerColor, true);
            this.innerColor = Objects.nonNull(innerColor) ? innerColor : this.innerColor;
            innerColorBtn.setBackground(this.innerColor);
        });
        borderColorBtn.addActionListener(e -> {
            Color borderColor = JColorChooser.showDialog(rootPane, "Choose border color:", this.borderColor, true);
            this.borderColor = Objects.nonNull(borderColor) ? borderColor : this.borderColor;
            borderColorBtn.setBackground(this.borderColor);
        });
        brushSizeSld.addChangeListener(e -> {
            this.brushSize = brushSizeSld.getValue();
        });
    }

    private void drawPaneConfig() {
        drawPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    switch (drawAction) {
                        case LINE_SEGMENT:
                            drawables.add(Drawables.createLineSegment(e.getPoint(), e.getPoint(), borderColor, brushSize));
                            repaint();
                            break;
                        case RAY:
                            drawables.add(Drawables.createRay(e.getPoint(), e.getPoint(), borderColor, brushSize));
                            break;
                        case LINE:
                            drawables.add(Drawables.createLine(e.getPoint(), e.getPoint(), borderColor, brushSize));
                            break;
                        case POLYGON:
                            drawables.add(Drawables.createPolygon(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())),
                                    borderColor, innerColor, brushSize));
                            drawAction = DrawActions.ADD_POINT_TO_POLYGON;
                            repaint();
                            break;
                        case ADD_POINT_TO_POLYGON:
                            Polygon polygon = (Polygon) drawables.get(drawables.size() - 1);
                            polygon.addPoint(e.getPoint());
                            repaint();
                            break;
                        case REGULAR_POLYGON:
                            drawables.add(Drawables.createRegularPolygon(e.getPoint(), e.getPoint(), numOfSidesForRegularPolygon,
                                    borderColor, innerColor, brushSize));
                            repaint();
                            break;
                        case POLYLINE:
                            drawables.add(Drawables.createPolyline(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())),
                                    borderColor, brushSize));
                            drawAction = DrawActions.ADD_POINT_TO_POLYLINE;
                            repaint();
                            break;
                        case ADD_POINT_TO_POLYLINE:
                            Polyline polyline = (Polyline) drawables.get(drawables.size() - 1);
                            polyline.addPoint(e.getPoint());
                            repaint();
                        case RECTANGLE:
                            drawables.add(Drawables.createRectangle(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())),
                                    borderColor, innerColor, brushSize));
                            repaint();
                            break;
                        case RHOMBUS:
                            drawables.add(Drawables.createRhombus(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())),
                                    borderColor, innerColor, brushSize));
                            repaint();
                            break;
                        case TRIANGLE:
                            drawables.add(Drawables.createTriangle(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())),
                                    borderColor, innerColor, brushSize));
                            repaint();
                            break;
                        case LAST_POINT_TRIANGLE:
                            Triangle triangle = (Triangle) drawables.get(drawables.size() - 1);
                            triangle.addPoint(e.getPoint());
                            repaint();
                            break;
                        case ELLIPSE:
                            drawables.add(Drawables.createEllipse(e.getPoint(), e.getPoint(), borderColor, innerColor, brushSize));
                            repaint();
                            break;
                        case CIRCLE:
                            drawables.add(Drawables.createCircle(e.getPoint(), e.getPoint(), borderColor, innerColor, brushSize));
                            repaint();
                            break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                // TODO: 10.03.2020 think about 2d figures
                drawables = drawables.stream().filter(drawable -> !drawable.nextForRemoving()).collect(Collectors.toList());

                switch (drawAction) {
                    case TRIANGLE:
                        drawAction = DrawActions.LAST_POINT_TRIANGLE;
                        break;
                    case LAST_POINT_TRIANGLE:
                        drawAction = DrawActions.TRIANGLE;
                        break;
                }
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
                            segment.calculateSecondPoint();
                            break;
                        case ADD_POINT_TO_POLYGON:
                        case REGULAR_POLYGON:
                        case RECTANGLE:
                        case RHOMBUS:
                        case TRIANGLE:
                        case LAST_POINT_TRIANGLE:
                            ((Polygon) drawable).setFigureVertex(e.getPoint());
                            break;
                        case ADD_POINT_TO_POLYLINE:
                            ((Polyline) drawable).setLastPoint(e.getPoint());
                            break;
                        case ELLIPSE:
                        case CIRCLE:
                            ((Ellipse) drawable).setPointOnBorder(e.getPoint());
                            break;


                    }
                    repaint();
                }
            }

        });
    }

    private void unselectAllButtons() {
        invisibleToggleBtn.doClick();
        invisibleToggleBtn.setSelected(true);
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
