import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu extends JFrame {
    private int selectedIndex = -1;
    private ArrayList<Mahasiswa> listMahasiswa;
    private Database database;
    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JTextField notelpField;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel notelpLabel;
    private JComboBox agamaComboBox;
    private JLabel agamaLabel;

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setContentPane(window.mainPanel);
        window.getContentPane().setBackground(Color.white);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Menu() {
        listMahasiswa = new ArrayList<>();
        database = new Database();

        mahasiswaTable.setModel(setTable());

        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        String[] agamaData = {"", "Islam", "Kristen", "Katolik", "Buddha", "Hindu"};
        agamaComboBox.setModel(new DefaultComboBoxModel(agamaData));

        deleteButton.setVisible(false);
        refreshTable();

        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = mahasiswaTable.getSelectedRow();
                displaySelectedRowData();
            }
        });
    }

    private void displaySelectedRowData() {
        if (selectedIndex >= 0) {
            String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
            String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
            String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
            String selectedNoTelp = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();
            String selectedAgama = mahasiswaTable.getModel().getValueAt(selectedIndex, 5).toString();

            nimField.setText(selectedNim);
            namaField.setText(selectedNama);
            jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
            notelpField.setText(selectedNoTelp);
            agamaComboBox.setSelectedItem(selectedAgama);

            addUpdateButton.setText("Update");
            deleteButton.setVisible(true);
        }
    }

    private void refreshTable() {
        mahasiswaTable.setModel(setTable());
    }

    private DefaultTableModel setTable() {
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "No Telepon", "Agama"};
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            int i = 1;
            while (resultSet.next()) {
                Object[] row = new Object[6];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("noTelp");
                row[5] = resultSet.getString("agama");
                temp.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp;
    }

    private void insertData() {
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String noTelp = notelpField.getText();
        String agama = agamaComboBox.getSelectedItem().toString();

        // Periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || noTelp.isEmpty() || agama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Periksa apakah NIM sudah ada dalam database
        try {
            ResultSet resultSet = database.selectQuery("SELECT COUNT(*) FROM mahasiswa WHERE nim='" + nim + "'");
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "NIM sudah ada dalam database!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + noTelp + "', '" + agama + "');";
        database.insertUpdateDeleteQuery(sql);

        refreshTable();
        clearForm();

        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan!");
    }


    private void updateData() {
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String noTelp = notelpField.getText();
        String agama = agamaComboBox.getSelectedItem().toString();

        int selectedId = Integer.parseInt(mahasiswaTable.getModel().getValueAt(selectedIndex, 0).toString());

        // Periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || noTelp.isEmpty() || agama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "UPDATE mahasiswa SET " +
                "nim='" + nim + "', " +
                "nama='" + nama + "', " +
                "jenis_kelamin='" + jenisKelamin + "', " +
                "noTelp='" + noTelp + "', " +
                "agama='" + agama + "' " +
                "WHERE id=" + selectedId;

        database.insertUpdateDeleteQuery(sql);

        refreshTable();
        clearForm();

        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
    }

    private void deleteData() {
        int selectedId = Integer.parseInt(mahasiswaTable.getModel().getValueAt(selectedIndex, 0).toString());

        String sql = "DELETE FROM mahasiswa WHERE id=" + selectedId;

        database.insertUpdateDeleteQuery(sql);

        refreshTable();
        clearForm();

        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
    }

    private void clearForm() {
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        notelpField.setText("");
        agamaComboBox.setSelectedItem("");

        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}
