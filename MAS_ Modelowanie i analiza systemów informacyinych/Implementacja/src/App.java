import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame{
    private JLabel label;
    private JPanel panel;
    private JTextField textField1;
    private JButton OKButton;
    private boolean checkForNumber = false;

    public App() {
        textField1.setText("");
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        setContentPane(panel);
        getContentPane().setBackground(Color.getHSBColor(214, 186, 188));
        setSize(600,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"nie musze byc puste");
                } else {
                    if (!textField1.getText().matches("\\d+")) {
                        Object[] options = {"tak", "finish"};
                        int result = JOptionPane.showOptionDialog(null,
                                "musisz wpisać tylko cyfry nieujemne i bez liter",
                                "lol",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if (result == JOptionPane.NO_OPTION) {
                            System.exit(0);
                        }
                    } else {

                            try {

                                ObjectInputStream read = new ObjectInputStream(new FileInputStream("person.txt"));
                                Dyrektor.readExtents(read);
                                Ksiegowy.readExtents(read);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            List<Dyrektor> dyrektor = new ArrayList<>();
                            dyrektor = Dyrektor.getExtent();
                            List<Ksiegowy> ksiegowy = dyrektor.get(0).getListOfKsiegowy(Integer.parseInt(textField1.getText()));

                            if (ksiegowy.isEmpty())  {
                                JOptionPane.showMessageDialog(null,"nie ma rekordow");
                            } else {
                                new ListApp(ksiegowy).setVisible(true);
                                setVisible(false);
                            }

                    }
                }
            }
        });
    }
    public static void main(String[] args) {
       App app = new App();

    }
}
