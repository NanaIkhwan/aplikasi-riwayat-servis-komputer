# Aplikasi Riwayat Servis Komputer/Laptop

Aplikasi desktop berbasis Java untuk mencatat dan mengelola riwayat servis komputer atau laptop secara sistematis dan efisien.

---

## Latar Belakang

Komputer dan laptop telah menjadi perangkat penting dalam kehidupan sehari-hari, baik dalam dunia pendidikan, pekerjaan, maupun bisnis. Seiring dengan frekuensi penggunaan yang tinggi, kerusakan perangkat menjadi hal yang umum terjadi. Namun, banyak pengguna dan teknisi tidak memiliki sistem pencatatan yang rapi terhadap riwayat servis perangkat tersebut.

Ketidakteraturan dalam pencatatan servis menyebabkan masalah seperti keterlambatan perbaikan, kehilangan data histori kerusakan, atau bahkan pengeluaran biaya servis yang tidak terkontrol.

Untuk mengatasi permasalahan tersebut, dibutuhkan sebuah **aplikasi desktop pintar berbasis Java** yang dapat membantu pengguna atau teknisi dalam mencatat, mengelola, dan menelusuri riwayat servis perangkat komputer/laptop secara aman, sistematis, dan efisien.

---

## Tujuan Aplikasi

- Membantu pengguna mencatat data servis perangkat seperti nama pelanggan, jenis perangkat, kerusakan, dan status perbaikan.
- Menyediakan histori lengkap servis untuk setiap perangkat yang pernah diperbaiki.
- Memberikan kemampuan pencarian data servis berdasarkan nama pelanggan, perangkat, atau tanggal.
- Memungkinkan teknisi untuk melihat riwayat masalah pada perangkat tertentu agar diagnosis lebih akurat.
- Menyediakan sistem login untuk keamanan akses data dan enkripsi password.

---

## Ruang Lingkup Aplikasi

- Aplikasi berbasis desktop (standalone) menggunakan **Java (NetBeans)**.
- Form input data servis: nama pelanggan, perangkat, kerusakan, tanggal masuk, status, dan catatan teknisi.
- Tabel data servis lengkap dengan fitur: edit, hapus, pencarian, dan filter.
- Autentikasi pengguna (login & registrasi teknisi/admin) dengan **enkripsi password**.
- Penyimpanan data menggunakan **MySQL** (dikelola via phpMyAdmin).
- Dukungan **multibahasa**: Bahasa Indonesia & Inggris menggunakan `ResourceBundle`.
- Fitur ekspor riwayat servis ke file PDF (opsional).
- Backup lokal menggunakan **serialisasi** ke file `.ser` (opsional).

---

## ⚙️ Kebutuhan Teknis

- Java Netbeans
- JDBC (MYSQL)
- Threading
- Generic Class
- Serialisasi
- Cryptography
- Internationalisasi
