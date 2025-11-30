public class Minuman extends ItemMenu {
    private String jenis; //

    public Minuman(String nama, double harga, String jenis) {
        super(nama, harga, "Minuman");
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    public void tampil() {
        System.out.println("[Minuman] " + getNama() + " (" + jenis + ") - Rp " + (int)getHarga());
    }
}
