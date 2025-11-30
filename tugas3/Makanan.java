public class Makanan extends ItemMenu {
    private String jenis;

    public Makanan(String nama, double harga, String jenis) {
        super(nama, harga, "Makanan");
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    public void tampil() {
        System.out.println("[Makanan] " + getNama() + " (" + jenis + ") - Rp " + (int)getHarga());
    }
}
