import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ListApp extends JFrame {
    private JButton backButton;
    private JPanel panel1;
    private JList list1;
    private JLabel label;




    public ListApp(List<Ksiegowy> ksiegowyList) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        setContentPane(panel1);
        getContentPane().setBackground(Color.getHSBColor(154, 18, 89));
        setSize(600,300);
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < ksiegowyList.size(); i++)
        {
            listModel.addElement(ksiegowyList.get(i));
        }
        list1.setModel(listModel);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = list1.getSelectedIndex();
                List<Wyplata> wyplaty = new ArrayList<>();
                wyplaty = ksiegowyList.get(index).getWyplatyZrobione();
                new WyplatyList(
                        wyplaty,
                        ksiegowyList.get(index).getImie() + " " + ksiegowyList.get(index).getNazwisko(),
                        ksiegowyList
                ).setVisible(true);

                setVisible(false);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new App().setVisible(true);
                setVisible(false);
            }
        });
    }

}
