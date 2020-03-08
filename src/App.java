import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.util.ArrayList;

public class App extends JFrame {
    private JToggleButton rectangleButton, ellipseButton, regularPolygonButton, segmentButton,
            lineButton, rayButton, polygonButton, parallelogramButton, rhombusButton;
    private JToggleButton moveShapesButton;
    private JPanel rootPanel;
    private JPanel drawPanel;
    private JColorChooser colorChooser;
    private JButton frameColorButton, fillColorButton;
    private JComboBox<Icon> widthComboBox;
    private JCheckBox transparencyCheckBox;
    private RegularPolygonChooser sideNumDialog;
    private ArrayList<Figure> figures = new ArrayList<>();
    private FigureAction figureAction;
    private FigureCreator figureCreator;
}
