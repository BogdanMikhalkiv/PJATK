import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WyplatyList extends JFrame {
    private JButton backButton;
    private JList list1;
    private JPanel jpanel;
    private JLabel label;
    private JButton zmienicLiczbeWyplatButton;


    public WyplatyList(List<Wyplata> wyplaty, String nameAndSurname, List<Ksiegowy> ksiegowyList) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        label.setText(nameAndSurname + " wyplaty");
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        setContentPane(jpanel);
        getContentPane().setBackground(Color.getHSBColor(154, 18, 89));
        setSize(600,300);
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < wyplaty.size(); i++) {
            listModel.addElement(wyplaty.get(i));
        }
        list1.setModel(listModel);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListApp(ksiegowyList).setVisible(true);
                setVisible(false);
            }
        });

        zmienicLiczbeWyplatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new App().setVisible(true);
                setVisible(false);
            }
        });
    }
}
