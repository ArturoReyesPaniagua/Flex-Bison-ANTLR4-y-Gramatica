import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class GUIRu extends JFrame {

    // Componentes principales
    private JTextArea codeArea;
    private JTextArea outputArea;
    private JButton executeButton;
    private JButton clearButton;
    private JButton loadFileButton;
    private JButton saveOutputButton;
    private JTextField inputFileField;
    private JLabel statusLabel;

    // Traductor
    private MyVisitor visitor;

    public GUIRu() {
        initComponents();
        setupLayout();
        setupEventHandlers();
        visitor = new MyVisitor();
        visitor.setParentComponent(this);
    }

    private void initComponents() {
        setTitle("Traductor del Lenguaje Ru - UAM Cuajimalpa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Área de código
        codeArea = new JTextArea(20, 50);
        codeArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        codeArea.setTabSize(4);
        codeArea.setText(getDefaultCode());
        codeArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Área de salida
        outputArea = new JTextArea(15, 50);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(248, 248, 248));
        outputArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Botones
        executeButton = new JButton("Ejecutar Código");
        executeButton.setBackground(new Color(0, 120, 0));
        executeButton.setForeground(Color.WHITE);
        executeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        executeButton.setPreferredSize(new Dimension(150, 35));

        clearButton = new JButton("Limpiar");
        clearButton.setPreferredSize(new Dimension(100, 35));

        loadFileButton = new JButton("Cargar ");
        loadFileButton.setPreferredSize(new Dimension(120, 35));

        saveOutputButton = new JButton("Guardar ");
        saveOutputButton.setPreferredSize(new Dimension(120, 35));

        // Campo de archivo
        inputFileField = new JTextField(25);
        inputFileField.setEditable(false);
        inputFileField.setBackground(Color.WHITE);

        // Etiqueta de estado
        statusLabel = new JLabel("Listo para ejecutar código Ru");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // ===== PANEL SUPERIOR: ARCHIVOS Y BOTONES =====
        JPanel topPanel = new JPanel(new BorderLayout());

        // Panel de archivo
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filePanel.add(new JLabel("Archivo: "));
        filePanel.add(inputFileField);
        filePanel.add(loadFileButton);

        // Panel de botones de control
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(executeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveOutputButton);

        topPanel.add(filePanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ===== PANEL CENTRAL: DIVISIÓN CÓDIGO/SALIDA =====
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setResizeWeight(0.5); // División 50/50

        // Panel izquierdo: Editor de código
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.setBorder(new TitledBorder("Código Ru"));
        JScrollPane codeScroll = new JScrollPane(codeArea);
        codeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        codePanel.add(codeScroll, BorderLayout.CENTER);

        // Panel derecho: Salida
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(new TitledBorder("Salida del Programa"));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        outputPanel.add(outputScroll, BorderLayout.CENTER);

        mainSplitPane.setLeftComponent(codePanel);
        mainSplitPane.setRightComponent(outputPanel);

        // ===== AGREGAR COMPONENTES AL FRAME =====
        add(topPanel, BorderLayout.NORTH);
        add(mainSplitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void setupEventHandlers() {
        executeButton.addActionListener(e -> executeCode());
        clearButton.addActionListener(e -> clearAll());
        loadFileButton.addActionListener(e -> loadFile());
        saveOutputButton.addActionListener(e -> saveOutput());

        // Atajo de teclado para ejecutar (Ctrl+Enter)
        KeyStroke ctrlEnter = KeyStroke.getKeyStroke("ctrl ENTER");
        codeArea.getInputMap().put(ctrlEnter, "execute");
        codeArea.getActionMap().put("execute", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCode();
            }
        });
    }

    private void executeCode() {
        String code = codeArea.getText().trim();

        if (code.isEmpty()) {
            showStatus("No hay código para ejecutar", Color.ORANGE);
            return;
        }

        try {
            outputArea.setText("");
            visitor.clearOutput();

            showStatus("Ejecutando código...", Color.BLUE);
            executeButton.setEnabled(false);

            // Variables para capturar errores
            final StringBuilder errorMessages = new StringBuilder();

            // Crear input stream
            ANTLRInputStream input = new ANTLRInputStream(code);

            // Crear lexer
            RuLexer lexer = new RuLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Crear parser
            RuParser parser = new RuParser(tokens);

            // Manejar errores
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    String error = "ERROR línea " + line + ", pos " + charPositionInLine + ": " + msg + "\n";
                    errorMessages.append(error);
                }
            });

            // Parsear
            ParseTree tree = parser.programa();

            // Verificar errores
            if (errorMessages.length() > 0) {
                outputArea.setText("=== ERRORES DE SINTAXIS ===\n" + errorMessages.toString());
                showStatus("Error de sintaxis", Color.RED);
                return;
            }

            if (tree == null) {
                outputArea.setText("ERROR: No se pudo parsear el código");
                showStatus("Error de parsing", Color.RED);
                return;
            }

            // Ejecutar con MyVisitor
            visitor.visit(tree);

            // Mostrar resultados
            String output = visitor.getOutput();
            String logs = visitor.getLogOutput();

            // Combinar salida y logs
            StringBuilder fullOutput = new StringBuilder();
            if (!output.isEmpty()) {
                fullOutput.append("=== SALIDA ===\n").append(output);
            }
            if (!logs.isEmpty()) {
                if (fullOutput.length() > 0) fullOutput.append("\n");
                fullOutput.append("=== LOGS ===\n").append(logs);
            }

            if (fullOutput.length() == 0) {
                outputArea.setText("Ejecución completada (sin salida)");
            } else {
                outputArea.setText(fullOutput.toString());
            }

            showStatus("Ejecución completada exitosamente", Color.GREEN);

        } catch (Exception e) {
            String errorDetails = "ERROR DE EJECUCIÓN:\n" +
                    e.getClass().getSimpleName() + ": " + e.getMessage() + "\n\n";

            // Agregar stack trace si es necesario para debugging
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                errorDetails += "DETALLES:\n" + getStackTrace(e);
            }

            outputArea.setText(errorDetails);
            showStatus("Error durante la ejecución: " + e.getMessage(), Color.RED);

        } finally {
            executeButton.setEnabled(true);
        }
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Ru (*.ru)", "ru"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            inputFileField.setText(selectedFile.getAbsolutePath());

            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                codeArea.setText(content);
                showStatus("Archivo cargado: " + selectedFile.getName(), Color.BLUE);
            } catch (IOException e) {
                outputArea.setText("Error al cargar archivo: " + e.getMessage());
                showStatus("Error al cargar archivo", Color.RED);
            }
        }
    }

    private void saveOutput() {
        String output = outputArea.getText();

        if (output.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay resultados para guardar",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
        fileChooser.setSelectedFile(new File("salida.txt"));

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();

            // Asegurar extensión .txt
            if (!outputFile.getName().toLowerCase().endsWith(".txt")) {
                outputFile = new File(outputFile.getAbsolutePath() + ".txt");
            }

            try (PrintWriter writer = new PrintWriter(outputFile)) {
                writer.println("=== TRADUCTOR DEL LENGUAJE RU - UAM CUAJIMALPA ===");
                writer.println("Archivo de entrada: " + inputFileField.getText());
                writer.println("Fecha de ejecución: " + new java.util.Date());
                writer.println();

                writer.println("=== CÓDIGO EJECUTADO ===");
                writer.println(codeArea.getText());
                writer.println();

                writer.println("=== RESULTADOS ===");
                writer.println(output);

                showStatus("Salida guardada en: " + outputFile.getName(), Color.GREEN);
                JOptionPane.showMessageDialog(this,
                        "Resultados guardados exitosamente en:\n" + outputFile.getAbsolutePath(),
                        "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                outputArea.append("\nError al guardar archivo: " + e.getMessage());
                showStatus("Error al guardar archivo", Color.RED);
            }
        }
    }

    private void clearAll() {

        codeArea.setText(getEmptyCode());
            outputArea.setText("");
            inputFileField.setText("");
            visitor.clearOutput();
            showStatus("Todo limpiado", Color.BLUE);

    }

    private void showStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    private String getEmptyCode() {
        return "";
    }
    private String getDefaultCode() {
        return "# Ejemplo simple del Lenguaje Ru\n" +
                "\n" +
                "# Declaracion de variables\n" +
                "var a = 10;\n" +
                "var b = 5;\n" +
                "var nombre = \"Usuario\";\n" +
                "\n" +
                "# Salida basica\n" +
                "imprime \"=== Ejemplo 1: Variables y Operaciones ===\";\n" +
                "imprime \"Nombre: \" + nombre;\n" +
                "imprime \"a = \" + a;\n" +
                "imprime \"b = \" + b;\n" +
                "\n" +
                "# Operaciones aritmeticas\n" +
                "var suma = a + b;\n" +
                "var resta = a - b;\n" +
                "var multiplicacion = a * b;\n" +
                "var division = a / b;\n" +
                "\n" +
                "imprime \"Suma: \" + a + \" + \" + b + \" = \" + suma;\n" +
                "imprime \"Resta: \" + a + \" - \" + b + \" = \" + resta;\n" +
                "imprime \"Multiplicacion: \" + a + \" * \" + b + \" = \" + multiplicacion;\n" +
                "imprime \"Division: \" + a + \" / \" + b + \" = \" + division;\n" +
                "\n" +
                "# Operaciones logicas\n" +
                "var mayor = a > b;\n" +
                "var igual = a == b;\n" +
                "\n" +
                "imprime \"a > b? \" + mayor;\n" +
                "imprime \"a == b? \" + igual;\n" +
                "\n" +
                "# Estructura condicional\n" +
                "if (mayor) {\n" +
                "    imprime \"a es mayor que b\";\n" +
                "    log \"Condicion verdadera\";\n" +
                "} else {\n" +
                "    imprime \"a no es mayor que b\";\n" +
                "}\n" +
                "\n" +
                "log \"Programa ejecutado correctamente\";";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIRu().setVisible(true);
            }
        });
    }
}