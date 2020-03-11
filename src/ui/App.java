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
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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
    private int numOfSidesForRegularPolygon = 5;
    private List<Drawable> drawables = new ArrayList<>();
    private Color borderColor = Color.BLACK;
    private Color innerColor = Color.BLUE;
    private int brushSize;
    private boolean isMoving = false;

    private Map<DrawActions, Consumer<MouseEvent>> mousePressedConsumers = new HashMap<>();
    private Map<DrawActions, Consumer<MouseEvent>> mouseReleasedConsumers = new HashMap<>();
    private Map<DrawActions, BiConsumer<MouseEvent, Drawable>> mouseDraggedConsumers = new HashMap<>();

    public App() {
        super("Paint™ 2020. Kabaye Inc.");
        configureApp();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }

    private void configureApp() {
        setContentPane(rootPane);
        setSize((int) (1920 / 1.3), (int) (1080 / 1.3));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        configureConsumers();
        controlPaneListenersConfig();
        configureUI();
        setVisible(true);
    }

    private void configureUI() {
        brushSize = brushSizeSld.getValue();
        drawPaneConfig();
    }

    private void drawPaneConfig() {
        drawPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    mousePressedConsumers.get(drawAction).accept(e);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    unselectAllButtons();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                // TODO: 10.03.2020 think about 2d figures
                drawables = drawables.stream().filter(drawable -> !drawable.nextForRemoving()).collect(Collectors.toList());
                Consumer<MouseEvent> consumer = mouseReleasedConsumers.get(drawAction);
                if (Objects.nonNull(consumer)) {
                    consumer.accept(e);
                }
            }
        });

        drawPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && !drawables.isEmpty()) {
                    mouseDraggedConsumers.get(drawAction).accept(e, drawables.get(drawables.size() - 1));
                    repaint();
                }
            }
        });
    }

    private void unselectAllButtons() {
        invisibleToggleBtn.doClick();
        invisibleToggleBtn.setSelected(true);
        drawAction = DrawActions.NOTHING;
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

    private void configureConsumers() {
        mousePressedConsumers.put(DrawActions.LINE_SEGMENT, (e) -> {
            drawables.add(Drawables.createLineSegment(e.getPoint(), e.getPoint(), borderColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.RAY, (e) -> drawables.add(Drawables.createRay(e.getPoint(), e.getPoint(), borderColor, brushSize)));
        mousePressedConsumers.put(DrawActions.LINE, (e) -> drawables.add(Drawables.createLine(e.getPoint(), e.getPoint(), borderColor, brushSize)));
        mousePressedConsumers.put(DrawActions.POLYGON, (e) -> {
            drawables.add(Drawables.createPolygon(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())), borderColor, innerColor, brushSize));
            drawAction = DrawActions.ADD_POINT_TO_POLYGON;
            repaint();
        });
        mousePressedConsumers.put(DrawActions.ADD_POINT_TO_POLYGON, (e) -> {
            Polygon polygon = (Polygon) drawables.get(drawables.size() - 1);
            polygon.addPoint(e.getPoint());
            repaint();
        });
        mousePressedConsumers.put(DrawActions.REGULAR_POLYGON, (e) -> {
            drawables.add(Drawables.createRegularPolygon(e.getPoint(), e.getPoint(), numOfSidesForRegularPolygon, borderColor, innerColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.POLYLINE, (e) -> {
            drawables.add(Drawables.createPolyline(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())), borderColor, brushSize));
            drawAction = DrawActions.ADD_POINT_TO_POLYLINE;
            repaint();
        });
        mousePressedConsumers.put(DrawActions.ADD_POINT_TO_POLYLINE, (e) -> {
            Polyline polyline = (Polyline) drawables.get(drawables.size() - 1);
            polyline.addPoint(e.getPoint());
            repaint();
        });
        mousePressedConsumers.put(DrawActions.RECTANGLE, (e) -> {
            drawables.add(Drawables.createRectangle(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())), borderColor, innerColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.RHOMBUS, (e) -> {
            drawables.add(Drawables.createRhombus(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())), borderColor, innerColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.TRIANGLE, (e) -> {
            drawables.add(Drawables.createTriangle(new ArrayList<>(Arrays.asList(e.getPoint(), e.getPoint())), borderColor, innerColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.LAST_POINT_TRIANGLE, (e) -> {
            Triangle triangle = (Triangle) drawables.get(drawables.size() - 1);
            triangle.addPoint(e.getPoint());
            repaint();
        });
        mousePressedConsumers.put(DrawActions.ELLIPSE, (e) -> {
            drawables.add(Drawables.createEllipse(e.getPoint(), e.getPoint(), borderColor, innerColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.CIRCLE, (e) -> {
            drawables.add(Drawables.createCircle(e.getPoint(), e.getPoint(), borderColor, innerColor, brushSize));
            repaint();
        });
        mousePressedConsumers.put(DrawActions.MOVE, (e) -> {
            ListIterator<Drawable> iterator = drawables.listIterator(drawables.size());
            while (iterator.hasPrevious()) {
                int index = iterator.previousIndex();
                if (iterator.previous().contains(e.getPoint())) {
                    isMoving = true;
                    drawables.add(drawables.remove(index));
                    break;
                }
            }
        });
        mousePressedConsumers.put(DrawActions.NOTHING, (e) -> {
        });

        mouseReleasedConsumers.put(DrawActions.TRIANGLE, (e) -> drawAction = DrawActions.LAST_POINT_TRIANGLE);
        mouseReleasedConsumers.put(DrawActions.LAST_POINT_TRIANGLE, (e) -> drawAction = DrawActions.TRIANGLE);
        mouseReleasedConsumers.put(DrawActions.MOVE, (e) -> isMoving = false);

        BiConsumer<MouseEvent, Drawable> mouseEventFigure1DBiConsumer = (e, drawable) -> {
            LineSegment segment = ((LineSegment) drawable);
            segment.setSecondPoint(e.getPoint());
            segment.calculateSecondPoint();
        };
        BiConsumer<MouseEvent, Drawable> mouseEventPolygonBiConsumer = (e, drawable) -> ((Polygon) drawable).setFigureVertex(e.getPoint());
        BiConsumer<MouseEvent, Drawable> mouseEventEllipseBiConsumer = (e, drawable) -> ((Ellipse) drawable).setPointOnBorder(e.getPoint());

        mouseDraggedConsumers.put(DrawActions.LINE_SEGMENT, mouseEventFigure1DBiConsumer);
        mouseDraggedConsumers.put(DrawActions.RAY, mouseEventFigure1DBiConsumer);
        mouseDraggedConsumers.put(DrawActions.LINE, mouseEventFigure1DBiConsumer);
        mouseDraggedConsumers.put(DrawActions.ADD_POINT_TO_POLYGON, mouseEventPolygonBiConsumer);
        mouseDraggedConsumers.put(DrawActions.REGULAR_POLYGON, mouseEventPolygonBiConsumer);
        mouseDraggedConsumers.put(DrawActions.RECTANGLE, mouseEventPolygonBiConsumer);
        mouseDraggedConsumers.put(DrawActions.RHOMBUS, mouseEventPolygonBiConsumer);
        mouseDraggedConsumers.put(DrawActions.TRIANGLE, mouseEventPolygonBiConsumer);
        mouseDraggedConsumers.put(DrawActions.LAST_POINT_TRIANGLE, mouseEventPolygonBiConsumer);
        mouseDraggedConsumers.put(DrawActions.ADD_POINT_TO_POLYLINE, (e, drawable) -> ((Polyline) drawable).setLastPoint(e.getPoint()));
        mouseDraggedConsumers.put(DrawActions.ELLIPSE, mouseEventEllipseBiConsumer);
        mouseDraggedConsumers.put(DrawActions.CIRCLE, mouseEventEllipseBiConsumer);
        mouseDraggedConsumers.put(DrawActions.MOVE, (e, drawable) -> {
            if (isMoving) {
                drawable.move(e.getPoint());
            }
        });
    }

    private void controlPaneListenersConfig() {
        lineSegmentBtn.addActionListener(e -> drawAction = DrawActions.LINE_SEGMENT);
        rayBtn.addActionListener(e -> drawAction = DrawActions.RAY);
        lineBtn.addActionListener(e -> drawAction = DrawActions.LINE);
        polylineBtn.addActionListener(e -> drawAction = DrawActions.POLYLINE);
        regularPolygonBtn.addActionListener(e -> {
            final String dialogOutput = JOptionPane.showInputDialog(rootPane,
                    "Please, input number of regular polygon sides:", numOfSidesForRegularPolygon);
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
        moveBtn.addActionListener(e -> drawAction = DrawActions.MOVE);

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
        brushSizeSld.addChangeListener(e -> this.brushSize = brushSizeSld.getValue());
    }
}
