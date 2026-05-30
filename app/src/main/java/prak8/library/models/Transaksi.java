package prak8.library.models;

import java.time.LocalDate;

public class Transaksi {
    private String idTransaksi;
    private Member peminjam;          // Menyimpan objek Member utuh
    private Buku bukuYangDipinjam;    // Menyimpan objek Buku utuh
    private LocalDate tanggalPinjam;
    private LocalDate tenggatWaktu;
    private LocalDate tanggalKembali; // Bisa null/kosong jika buku belum dikembalikan
    private String status;            // Contoh: "Dipinjam", "Dikembalikan", atau "Terlambat"

    // Constructor untuk peminjaman baru
    public Transaksi(String idTransaksi, Member peminjam, Buku bukuYangDipinjam, LocalDate tanggalPinjam, int durasiPinjamHari) {
        this.idTransaksi = idTransaksi;
        this.peminjam = peminjam;
        this.bukuYangDipinjam = bukuYangDipinjam;
        this.tanggalPinjam = tanggalPinjam;
        this.tenggatWaktu = tanggalPinjam.plusDays(durasiPinjamHari);
        this.status = "Dipinjam";
        
        // Ubah status buku menjadi tidak tersedia saat dipinjam
        this.bukuYangDipinjam.setTersedia(false); 
    }

    // --- GETTER & SETTER ---
    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public Member getPeminjam() { return peminjam; }
    public void setPeminjam(Member peminjam) { this.peminjam = peminjam; }

    public Buku getBukuYangDipinjam() { return bukuYangDipinjam; }
    public void setBukuYangDipinjam(Buku bukuYangDipinjam) { this.bukuYangDipinjam = bukuYangDipinjam; }

    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }

    public LocalDate getTenggatWaktu() { return tenggatWaktu; }
    public void setTenggatWaktu(LocalDate tenggatWaktu) { this.tenggatWaktu = tenggatWaktu; }

    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(LocalDate tanggalKembali) { 
        this.tanggalKembali = tanggalKembali; 
        // Logika bisnis: jika dikembalikan, buku tersedia lagi
        if (tanggalKembali != null) {
            this.bukuYangDipinjam.setTersedia(true);
            this.status = "Dikembalikan";
        }
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
