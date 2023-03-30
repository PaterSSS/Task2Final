package program;

import util.ArrayUtils;
import util.JTableUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static util.JTableUtils.initJTableForArray;
import static util.JTableUtils.resizeJTable;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JTable inputTable;
    private JTable outputTabel;
    private JButton shuffleBut;
    private JButton clearList;
    private JButton fromFile;
    private JFileChooser fileChooserOpen;

    public MainFrame() {
        this.setTitle("Task 2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        initJTableForArray(inputTable, 30, false, true,
                false, true);
        inputTable.setRowHeight(30);
        initJTableForArray(outputTabel,30,false,true,false,true);
        outputTabel.setRowHeight(30);

        fileChooserOpen = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);


        shuffleBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        fromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(fileChooserOpen.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                        int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(inputTable, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        clearList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultTableModel dm = (DefaultTableModel)inputTable.getModel();
                dm.getDataVector().removeAllElements();
                dm.fireTableDataChanged();
                resizeJTable(inputTable,1,3);
            }
        });
    }


}
