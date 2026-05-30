package prak8.library.models;

public class Buku {
    private String idBuku;
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private boolean isTersedia;
    private String peminjam;

    // Constructor
    public Buku(String idBuku, String judul, String penulis, int tahunTerbit, boolean isTersedia, String peminjam) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.isTersedia = isTersedia;
        this.peminjam = peminjam;
    }

    // --- GETTER & SETTER (Encapsulation) ---
    public String getIdBuku() { return idBuku; }
    public void setIdBuku(String idBuku) { this.idBuku = idBuku; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }

    public int getTahunTerbit() { return tahunTerbit; }
    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }

    public boolean isTersedia() { return isTersedia; }
    public void setTersedia(boolean isTersedia) { this.isTersedia = isTersedia; }

    public String getPeminjam() { return peminjam; }
    public void setPeminjam(String peminjam) { this.peminjam = peminjam; }
}