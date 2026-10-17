
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> GenreComboBox;
    private JComboBox<String> AwardComboBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable peopleTable;

    private Database database;
    private ButtonGroup genderGroup;
    private String selectedId = null;

    public PeopleMenu() {
        setTitle("People Data");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        database = new Database();

        // Mengelompokkan pilihan gender
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Pilihan genre
        GenreComboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "Select Genre",
                        "Drama / Biopic",
                        "Action / Sci-Fi",
                        "Action",
                        "Adventure",
                        "Animation",
                        "Comedy",
                        "Crime",
                        "Drama",
                        "Fantasy",
                        "Horror",
                        "Mystery",
                        "Romance",
                        "Science Fiction",
                        "Thriller"
                }
        ));

        // Pilihan awardee status
        AwardComboBox.setModel(new DefaultComboBoxModel<>(
                new String[]{
                        "Select Awardee Status",
                        "Oscar Winner",
                        "Golden Globe Winner",
                        "Rising Star",
                        "BAFTA Winner",
                        "Emmy Winner",
                        "Multiple Awards Winner",
                        "Nominee",
                        "No Major Awards"
                }
        ));

        // Menampilkan data dari database
        setTable();

        // Tombol tambah atau update
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    if (selectedId == null) {
                        insertData();
                    } else {
                        updateData();
                    }
                }
            }
        });

        // Tombol cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Tombol delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        // Mengambil data tabel saat baris dipilih
        peopleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = peopleTable.getSelectedRow();

                if (row != -1) {
                    selectedId = peopleTable.getValueAt(row, 0).toString();

                    idField.setText(selectedId);
                    idField.setEditable(false);

                    namaField.setText(
                            peopleTable.getValueAt(row, 1).toString()
                    );

                    tahunLahirField.setText(
                            peopleTable.getValueAt(row, 2).toString()
                    );

                    GenreComboBox.setSelectedItem(
                            peopleTable.getValueAt(row, 3).toString()
                    );

                    AwardComboBox.setSelectedItem(
                            peopleTable.getValueAt(row, 4).toString()
                    );

                    String gender =
                            peopleTable.getValueAt(row, 5).toString();

                    if (gender.equalsIgnoreCase("Male")) {
                        maleRadioButton.setSelected(true);
                    } else if (gender.equalsIgnoreCase("Female")) {
                        femaleRadioButton.setSelected(true);
                    }

                    addUpdateButton.setText("Update");
                }
            }
        });

        setVisible(true);
    }

    // Menampilkan data dari tabel actor
    private void setTable() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Birth Year");
        model.addColumn("Genre");
        model.addColumn("Awardee Status");
        model.addColumn("Gender");

        peopleTable.setModel(model);

        refreshTable();
    }

    // Mengambil data dari database dan memasukkannya ke tabel
    private void refreshTable() {
        DefaultTableModel model =
                (DefaultTableModel) peopleTable.getModel();

        model.setRowCount(0);

        Connection conn = database.getConnection();

        if (conn == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Koneksi database gagal!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            String sql = "SELECT * FROM actor";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                model.addRow(new Object[]{
                        result.getString("id"),
                        result.getString("name"),
                        result.getInt("birth_year"),
                        result.getString("category"),
                        result.getString("award_status"),
                        result.getString("gender")
                });
            }

            result.close();
            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal mengambil data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Validasi setiap input
    private boolean validateInput() {
        if (idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "ID tidak boleh kosong!",
                    "Validasi ID",
                    JOptionPane.WARNING_MESSAGE
            );
            idField.requestFocus();
            return false;
        }

        if (namaField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama tidak boleh kosong!",
                    "Validasi Nama",
                    JOptionPane.WARNING_MESSAGE
            );
            namaField.requestFocus();
            return false;
        }

        if (tahunLahirField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Tahun lahir tidak boleh kosong!",
                    "Validasi Tahun Lahir",
                    JOptionPane.WARNING_MESSAGE
            );
            tahunLahirField.requestFocus();
            return false;
        }

        if (GenreComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih genre terlebih dahulu!",
                    "Validasi Genre",
                    JOptionPane.WARNING_MESSAGE
            );
            GenreComboBox.requestFocus();
            return false;
        }

        if (AwardComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih awardee status terlebih dahulu!",
                    "Validasi Awardee Status",
                    JOptionPane.WARNING_MESSAGE
            );
            AwardComboBox.requestFocus();
            return false;
        }

        if (!maleRadioButton.isSelected()
                && !femaleRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih gender terlebih dahulu!",
                    "Validasi Gender",
                    JOptionPane.WARNING_MESSAGE
            );
            maleRadioButton.requestFocus();
            return false;
        }

        if (!idField.getText().trim().matches("^ACT\\d+$")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Format ID harus ACT diikuti angka, contoh: ACT005!",
                    "Format ID Tidak Valid",
                    JOptionPane.WARNING_MESSAGE
            );
            idField.requestFocus();
            return false;
        }

        try {
            int tahun = Integer.parseInt(
                    tahunLahirField.getText().trim()
            );

            if (tahun <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tahun lahir harus lebih besar dari 0!",
                        "Tahun Lahir Tidak Valid",
                        JOptionPane.WARNING_MESSAGE
                );
                tahunLahirField.requestFocus();
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Tahun lahir harus berupa angka!",
                    "Format Tahun Lahir Tidak Valid",
                    JOptionPane.WARNING_MESSAGE
            );
            tahunLahirField.requestFocus();
            return false;
        }

        return true;
    }

    // Menambahkan data baru
    private void insertData() {
        Connection conn = database.getConnection();

        if (conn == null) {
            return;
        }

        String sql = "INSERT INTO actor " +
                "(id, name, birth_year, category, award_status, gender) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, idField.getText().trim());
            ps.setString(2, namaField.getText().trim());
            ps.setInt(3, Integer.parseInt(
                    tahunLahirField.getText().trim()
            ));
            ps.setString(4, GenreComboBox.getSelectedItem().toString());
            ps.setString(5, AwardComboBox.getSelectedItem().toString());

            if (maleRadioButton.isSelected()) {
                ps.setString(6, "Male");
            } else {
                ps.setString(6, "Female");
            }

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil ditambahkan!"
            );

            refreshTable();
            clearForm();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "ID sudah digunakan! Silakan gunakan ID lain.",
                    "ID Duplikat",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(
                        this,
                        "ID sudah digunakan! Silakan gunakan ID lain.",
                        "ID Duplikat",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menambahkan data: " + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Mengubah data yang dipilih
    private void updateData() {
        Connection conn = database.getConnection();

        if (conn == null) {
            return;
        }

        String sql = "UPDATE actor SET name = ?, birth_year = ?, " +
                "category = ?, award_status = ?, gender = ? WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, namaField.getText().trim());
            ps.setInt(2, Integer.parseInt(
                    tahunLahirField.getText().trim()
            ));
            ps.setString(3, GenreComboBox.getSelectedItem().toString());
            ps.setString(4, AwardComboBox.getSelectedItem().toString());

            if (maleRadioButton.isSelected()) {
                ps.setString(5, "Male");
            } else {
                ps.setString(5, "Female");
            }

            ps.setString(6, selectedId);

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil diperbarui!"
            );

            refreshTable();
            clearForm();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal memperbarui data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Menghapus data yang dipilih
    private void deleteData() {
        int row = peopleTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data yang ingin dihapus terlebih dahulu!",
                    "Data Belum Dipilih",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah kamu yakin ingin menghapus data ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Connection conn = database.getConnection();

        if (conn == null) {
            return;
        }

        String sql = "DELETE FROM actor WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, peopleTable.getValueAt(row, 0).toString());

            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil dihapus!"
            );

            refreshTable();
            clearForm();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menghapus data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Mengosongkan form
    private void clearForm() {
        idField.setText("");
        idField.setEditable(true);

        namaField.setText("");
        tahunLahirField.setText("");

        GenreComboBox.setSelectedIndex(0);
        AwardComboBox.setSelectedIndex(0);

        genderGroup.clearSelection();

        selectedId = null;
        peopleTable.clearSelection();

        addUpdateButton.setText("Add");
        idField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PeopleMenu();
            }
        });
    }
}
