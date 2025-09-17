import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class  extends JFrame {

    // Componentes de la interfaz
    private JTextArea codeArea;
    private JTextArea outputArea;
    private JTextArea logArea;
    private JTextArea errorArea;
    private JLabel statusLabel;
    private JButton executeButton;
    private JButton loadFileButton;
    private JButton saveOutputButton;
    private JButton clearButton;
    private JTextField filePathField;

    // Intérprete
    private RuInterpreter interpreter;

    public RuGUI() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        interpreter = new RuInterpreter();
    }

    private void initializeComponents() {
        setTitle("Traductor del Lenguaje Ru - UAM Cuajimalpa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Área de código
        codeArea = new JTextArea(20, 40);
        codeArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        codeArea.setTabSize(4);
        codeArea.setText(getDefaultCode());

        // Área de salida
        outputArea = new JTextArea(10, 40);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(248, 248, 248));

        // Área de logs
        logArea = new JTextArea(8, 40);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        logArea.setEditable(false);
        logArea.setBackground(new Color(240, 255, 240));

        // Área de errores
        errorArea = new JTextArea(6, 40);
        errorArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        errorArea.setEditable(false);
        errorArea.setBackground(new Color(255, 240, 240));

        // Botones
        executeButton = new JButton("Ejecutar Código");
        executeButton.setBackground(new Color(0, 120, 0));
        executeButton.setForeground(Color.WHITE);
        executeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        loadFileButton = new JButton("Cargar Archivo");
        saveOutputButton = new JButton("Guardar Salida");
        clearButton = new JButton("Limpiar");

        // Campo de archivo
        filePathField = new JTextField(30);
        filePathField.setEditable(false);

        // Etiqueta de estado
        statusLabel = new JLabel("Listo para ejecutar código Ru");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel principal dividido
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setResizeWeight(0.5);

        // Panel izquierdo (código)
        JPanel leftPanel = new JPanel(new BorderLayout());

        // Panel de código con título
        JPanel codePanel = new JPanel(new BorderLayout());
        codePanel.setBorder(new TitledBorder("Código Ru"));
        codePanel.add(new JScrollPane(codeArea), BorderLayout.CENTER);

        // Panel de botones de archivo
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filePanel.add(new JLabel("Archivo:"));
        filePanel.add(filePathField);
        filePanel.add(loadFileButton);

        leftPanel.add(filePanel, BorderLayout.NORTH);
        leftPanel.add(codePanel, BorderLayout.CENTER);

        // Panel de botones de control
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(executeButton);
        controlPanel.add(clearButton);
        controlPanel.add(saveOutputButton);

        leftPanel.add(controlPanel, BorderLayout.SOUTH);

        // Panel derecho (resultados)
        JPanel rightPanel = new JPanel(new BorderLayout());

        // Panel de pestañas para diferentes tipos de salida
        JTabbedPane outputTabbedPane = new JTabbedPane();

        // Pestaña de salida
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(new TitledBorder("Salida del Programa"));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        outputTabbedPane.addTab("Salida", outputPanel);

        // Pestaña de logs
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(new TitledBorder("Logs"));
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        outputTabbedPane.addTab("Logs", logPanel);

        // Pestaña de errores
        JPanel errorPanel = new JPanel(new BorderLayout());
        errorPanel.setBorder(new TitledBorder("Errores"));
        errorPanel.add(new JScrollPane(errorArea), BorderLayout.CENTER);
        outputTabbedPane.addTab("Errores", errorPanel);

        rightPanel.add(outputTabbedPane, BorderLayout.CENTER);

        // Agregar paneles al split pane
        mainSplitPane.setLeftComponent(leftPanel);
        mainSplitPane.setRightComponent(rightPanel);

        // Agregar al frame principal
        add(mainSplitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void setupEventHandlers() {
        // Botón ejecutar
        executeButton.addActionListener(e -> executeCode());

        // Botón cargar archivo
        loadFileButton.addActionListener(e -> loadFile());

        // Botón guardar salida
        saveOutputButton.addActionListener(e -> saveOutput());

        // Botón limpiar
        clearButton.addActionListener(e -> clearAll());

        // Atajo de teclado para ejecutar (Ctrl+Enter)
        codeArea.getInputMap().put(KeyStroke.getKeyStroke("ctrl ENTER"), "execute");
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

        // Limpiar salidas anteriores
        outputArea.setText("");
        logArea.setText("");
        errorArea.setText("");
        interpreter.clearOutput();

        showStatus("Ejecutando código...", Color.BLUE);
        executeButton.setEnabled(false);

        // Ejecutar en un hilo separado para no bloquear la UI
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    // Crear el input stream
                    ANTLRInputStream input = new ANTLRInputStream(code);

                    // Crear lexer
                    RuLexer lexer = new RuLexer(input);
                    CommonTokenStream tokens = new CommonTokenStream(lexer);

                    // Crear parser
                    RuParser parser = new RuParser(tokens);

                    // Configurar manejo de errores
                    parser.removeErrorListeners();
                    parser.addErrorListener(new BaseErrorListener() {
                        @Override
                        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                                int line, int charPositionInLine,
                                                String msg, RecognitionException e) {
                            SwingUtilities.invokeLater(() -> {
                                errorArea.append("Error de sintaxis en línea " + line +
                                        ", posición " + charPositionInLine + ": " + msg + "\n");
                            });
                        }
                    });

                    // Parsear
                    ParseTree tree = parser.programa();

                    // Ejecutar con el intérprete
                    interpreter.visit(tree);

                    return null;

                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        errorArea.append("Error durante la ejecución: " + e.getMessage() + "\n");
                        if (e.getCause() != null) {
                            errorArea.append("Causa: " + e.getCause().getMessage() + "\n");
                        }
                    });
                    throw e;
                }
            }

            @Override
            protected void done() {
                try {
                    get(); // Esto lanzará excepción si hubo error

                    // Mostrar resultados
                    outputArea.setText(interpreter.getOutput());
                    logArea.setText(interpreter.getLogOutput());

                    if (errorArea.getText().trim().isEmpty()) {
                        showStatus("Ejecución completada exitosamente", Color.GREEN);
                    } else {
                        showStatus("Ejecución completada con errores", Color.ORANGE);
                    }

                } catch (Exception e) {
                    showStatus("Error durante la ejecución", Color.RED);
                } finally {
                    executeButton.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Ru (*.ru)", "ru"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());

            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                codeArea.setText(content);
                showStatus("Archivo cargado: " + selectedFile.getName(), Color.BLUE);
            } catch (IOException e) {
                errorArea.setText("Error al cargar archivo: " + e.getMessage());
                showStatus("Error al cargar archivo", Color.RED);
            }
        }
    }

    private void saveOutput() {
        String output = outputArea.getText();
        String logs = logArea.getText();
        String errors = errorArea.getText();

        if (output.isEmpty() && logs.isEmpty() && errors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay salida para guardar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Asegurar extensión .txt
            if (!selectedFile.getName().toLowerCase().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }

            try (PrintWriter writer = new PrintWriter(selectedFile)) {
                writer.println("=== SALIDA DEL PROGRAMA ===");
                writer.println(output);
                writer.println();
                writer.println("=== LOGS ===");
                writer.println(logs);
                writer.println();
                writer.println("=== ERRORES ===");
                writer.println(errors);

                showStatus("Salida guardada en: " + selectedFile.getName(), Color.GREEN);
            } catch (IOException e) {
                errorArea.append("Error al guardar archivo: " + e.getMessage() + "\n");
                showStatus("Error al guardar archivo", Color.RED);
            }
        }
    }

    private void clearAll() {
        codeArea.setText(getDefaultCode());
        outputArea.setText("");
        logArea.setText("");
        errorArea.setText("");
        filePathField.setText("");
        interpreter.clearOutput();
        showStatus("Todo limpiado", Color.BLUE);
    }

    private void showStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    private String getDefaultCode() {
        return "# Ejemplo de código en Ru\n" +
                "# Asignación de variables\n" +
                "x = 10;\n" +
                "y = 20;\n" +
                "z = x + y;\n" +
                "\n" +
                "# Imprimir resultado\n" +
                "imprime \"El resultado es: \" + z;\n" +
                "\n" +
                "# Estructura condicional\n" +
                "if z > 25 {\n" +
                "    imprime \"z es mayor que 25\";\n" +
                "    log \"Condición verdadera\";\n" +
                "} else {\n" +
                "    imprime \"z es menor o igual a 25\";\n" +
                "}\n" +
                "\n" +
                "# Bucle while\n" +
                "contador = 1;\n" +
                "while contador <= 3 {\n" +
                "    imprime \"Iteración: \" + contador;\n" +
                "    contador = contador + 1;\n" +
                "}";
    }

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            // Usar look and feel por defecto si falla
        }

        SwingUtilities.invokeLater(() -> {
            new RuGUI().setVisible(true);
        });
    }
}