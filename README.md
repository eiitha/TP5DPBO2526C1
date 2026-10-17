# TP 5 DPBO — PeopleData (Data Aktor + MySQL)

## Janji

Saya Jillena Surbakti dengan NIM 2501485 mengerjakan Tugas Praktikum 5 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO). Untuk keberkahan-Nya, saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## Struktur Folder

```text
TP5DPBO2526C1/
├── README.md
├── db_people.sql
├── PeopleData/
|   ├── PeopleData.iml
|   ├── src/
|   │   ├── Database.java
|   │   └── PeopleMenu.java
|   └── lib/
|       └── mysql-connector-j.jar
|
└── Dokumentasi/
    ├── add.mp4
    ├── cancel.mp4
    ├── delete.mp4
    ├── read.mp4
    ├── update.mp4
    ├── validasi_1.mp4
    ├── validasi_2.mp4
    ├── validasi_3.mp4
    ├── validasi_4.mp4
    ├── validasi_5.mp4
    ├── validasi_6.mp4
    ├── validasi_7.mp4
    ├── validasi_8.mp4
    ├── validasi_9.mp4
    └── validasi_10.mp4

```

## Desain / Fungsi Program

**Tema:** Data Aktor — setiap baris pada tabel `actor` merepresentasikan satu aktor dengan enam atribut.

### Atribut Data Aktor

| No. | Atribut | Tipe Data | Komponen GUI | Keterangan |
|---|---|---|---|---|
| 1 | ID | `String` | `JTextField` (`idField`) | ID aktor, contoh `ACT001` |
| 2 | Nama | `String` | `JTextField` (`namaField`) | Nama aktor |
| 3 | Tahun Lahir | `int` | `JTextField` (`tahunLahirField`) | Tahun lahir aktor |
| 4 | Genre | `String` | `JComboBox` (`GenreComboBox`) | Genre atau kategori film |
| 5 | Awardee Status | `String` | `JComboBox` (`AwardComboBox`) | Status penghargaan aktor |
| 6 | Gender | `String` | `JRadioButton` (`maleRadioButton`, `femaleRadioButton`) | Male atau Female |

### File Program

- `Database.java` — membuat koneksi JDBC ke MySQL dan menyediakan akses koneksi melalui `getConnection()`.
- `PeopleMenu.java` — JFrame utama yang mengatur GUI, pengambilan data, operasi CRUD, validasi, dan listener komponen.
- `db_people.sql` — file SQL untuk membuat database dan tabel beserta data awal, jika disertakan dalam pengumpulan.
- `README.md` — dokumentasi project.

### Fungsi Komponen

- Form input digunakan untuk memasukkan dan mengubah informasi aktor.
- `GenreComboBox` menyediakan pilihan genre film.
- `AwardComboBox` menyediakan pilihan status penghargaan.
- `maleRadioButton` dan `femaleRadioButton` digunakan untuk memilih gender.
- `addUpdateButton` digunakan untuk menambah data atau memperbarui data yang dipilih.
- `deleteButton` digunakan untuk menghapus data yang dipilih.
- `cancelButton` digunakan untuk mengosongkan form dan kembali ke mode Add.
- `peopleTable` menampilkan data aktor dari database.
- Klik pada baris tabel akan mengisi form dengan data aktor yang dipilih dan mengubah tombol menjadi Update.

## Pilihan Genre dan Awardee Status

### Genre

Pilihan genre yang tersedia:

- Drama / Biopic
- Action / Sci-Fi
- Action
- Adventure
- Animation
- Comedy
- Crime
- Drama
- Fantasy
- Horror
- Mystery
- Romance
- Science Fiction
- Thriller

### Awardee Status

Pilihan status penghargaan yang tersedia:

- Oscar Winner
- Golden Globe Winner
- Rising Star
- BAFTA Winner
- Emmy Winner
- Multiple Awards Winner
- Nominee
- No Major Awards
  
## Method Program

| Method | Fungsi |
|---|---|
| `main()` | Titik masuk program dan membuat objek `PeopleMenu` melalui `SwingUtilities.invokeLater()`. |
| `PeopleMenu()` | Mengatur JFrame, koneksi database, pilihan ComboBox, tabel, dan listener tombol. |
| `setTable()` | Membuat model tabel dan menentukan kolom ID, Name, Birth Year, Genre, Awardee Status, dan Gender. |
| `refreshTable()` | Mengambil data menggunakan `SELECT * FROM actor` dan memperbarui isi tabel. |
| `validateInput()` | Memvalidasi input yang kosong, format ID, gender, pilihan ComboBox, dan tahun lahir. |
| `insertData()` | Menambahkan data aktor baru ke tabel `actor` menggunakan PreparedStatement. |
| `updateData()` | Memperbarui data aktor berdasarkan ID yang dipilih. |
| `deleteData()` | Menghapus data aktor berdasarkan ID setelah konfirmasi pengguna. |
| `clearForm()` | Mengosongkan input, mengatur ulang pilihan, membersihkan seleksi tabel, dan mengembalikan mode ke Add. |

## Penjelasan Alur Program

1. **Inisialisasi:** program membuat jendela utama, menghubungkan aplikasi ke MySQL, mengisi pilihan Genre dan Awardee Status, kemudian menampilkan data dari database.
2. **Read:** aplikasi menjalankan `SELECT * FROM actor` untuk menampilkan seluruh data aktor pada tabel.
3. **Create:** pengguna mengisi form dan menekan Add. Program memvalidasi input, kemudian memasukkan data baru ke database jika valid.
4. **Select:** pengguna mengeklik baris tabel. Data pada baris tersebut dimasukkan ke form, ID dikunci, dan tombol berubah menjadi Update.
5. **Update:** pengguna mengubah data pada form dan menekan Update. Program menjalankan perintah SQL `UPDATE` berdasarkan ID yang dipilih.
6. **Delete:** pengguna memilih baris dan menekan Delete. Program meminta konfirmasi sebelum menghapus data dari database.
7. **Cancel:** tombol Cancel mengosongkan form dan mengembalikan aplikasi ke mode Add.
8. **Penyimpanan:** data disimpan di MySQL sehingga perubahan tetap tersedia setelah aplikasi ditutup dan dijalankan kembali.

## Validasi / Error Handling

| Bagian | Kondisi Tidak Valid | Penanganan |
|---|---|---|
| ID | Kosong | Menampilkan pesan `ID tidak boleh kosong!` |
| Nama | Kosong | Menampilkan pesan `Nama tidak boleh kosong!` |
| Tahun Lahir | Kosong | Menampilkan pesan `Tahun lahir tidak boleh kosong!` |
| Genre | Belum dipilih | Menampilkan pesan agar pengguna memilih genre |
| Awardee Status | Belum dipilih | Menampilkan pesan agar pengguna memilih status penghargaan |
| Gender | Belum dipilih | Menampilkan pesan agar pengguna memilih gender |
| Format ID | Tidak sesuai pola `ACT` diikuti angka | Menampilkan pesan format ID tidak valid |
| ID Duplikat | ID sudah digunakan | Menampilkan pesan bahwa ID sudah digunakan |
| Tahun Lahir | Bukan angka | Menampilkan pesan bahwa tahun lahir harus berupa angka |
| Tahun Lahir | Nol atau negatif | Menampilkan pesan bahwa tahun lahir harus lebih besar dari 0 |
| Update/Delete | Tidak ada data yang dipilih untuk dihapus atau diubah | Menampilkan pesan agar pengguna memilih data terlebih dahulu |
| Delete | Pengguna belum memberikan konfirmasi | Data tidak dihapus sebelum pengguna memilih Yes |

## Konfigurasi Database

Database yang digunakan adalah `db_people`, dengan tabel `actor`.

| Kolom | Tipe Data |
|---|---|
| `id` | `VARCHAR(20)` |
| `name` | `VARCHAR(100)` |
| `birth_year` | `INT` |
| `category` | `VARCHAR(50)` |
| `award_status` | `VARCHAR(50)` |
| `gender` | `VARCHAR(10)` |

Koneksi database menggunakan konfigurasi lokal berikut:

- Host: `localhost`
- Port: `3306`
- Username: `root`
- Password: kosong, sesuai konfigurasi default XAMPP
- JDBC URL: `jdbc:mysql://localhost:3306/db_people`

## Dokumentasi

### Read — Menampilkan Data Aktor



https://github.com/user-attachments/assets/b9361e50-010b-46ae-adac-d93e5a2d8f91



### Create — Menambahkan Data Aktor



https://github.com/user-attachments/assets/6c25943c-18fc-4cb8-a91d-a3f4b119f725



### Update — Memperbarui Data Aktor



https://github.com/user-attachments/assets/add9cb24-09ee-475d-9012-3f2213500c08



### Delete — Menghapus Data Aktor


https://github.com/user-attachments/assets/b420472f-f55f-4dff-b1fd-e2c815b1c958



### Cancel — Mengosongkan Form



https://github.com/user-attachments/assets/b26ae1cd-3a4d-4140-99b2-512bdffd142e


### Validasi — ID dan Nama Kosong


https://github.com/user-attachments/assets/bc691b1c-20a7-4e29-bf22-fad4ba32b67d



https://github.com/user-attachments/assets/49fb605e-cdc2-4bc8-80ed-71bd08088686




### Validasi — Format ID Tidak Valid


https://github.com/user-attachments/assets/4383a12f-9d0f-4ec0-9dfb-9ba7a2ed7d1f



### Validasi — ID Duplikat


https://github.com/user-attachments/assets/b2635d39-ef8f-4d8f-b737-15a1ea5dfc1e



### Validasi — Tahun Lahir Kosong



https://github.com/user-attachments/assets/021a6c2e-907a-4199-a77e-3dcb997e7f80



### Validasi — Genre Belum Dipilih



https://github.com/user-attachments/assets/6ea36371-0cc9-41d0-b086-df163cd1bd03

### Validasi — Awardee Status Belum Dipilih



https://github.com/user-attachments/assets/e97ac3de-a720-4cc9-baa3-083f7ab3fd1e



### Validasi — Gender Belum Dipilih



https://github.com/user-attachments/assets/e64421dd-2975-438b-915e-8e7ce01641eb



### Validasi — Tahun Lahir Tidak Valid


https://github.com/user-attachments/assets/e72dd2a4-e4c9-4b94-a5af-7d1b623d47d2



https://github.com/user-attachments/assets/fba257d9-befd-4a0c-ad1e-c68b4c7aad3f



## Kesimpulan

PeopleData merupakan aplikasi pengelolaan data aktor berbasis Java Swing dan MySQL yang menerapkan operasi CRUD melalui JDBC. Aplikasi menyediakan pilihan Genre dan Awardee Status, pemilihan gender, validasi input, serta konfirmasi penghapusan. Dengan penyimpanan berbasis MySQL, data aktor dapat disimpan secara persisten dan dikelola melalui antarmuka grafis.
