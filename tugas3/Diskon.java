public class Diskon extends ItemMenu {
    private double persen; // 10 = 10%

    public Diskon(String nama, double persen) {
        super(nama, 0, "Diskon");
        this.persen = persen;
    }

    public double getPersen() {
        return persen;
    }

    public void tampil() {
        System.out.println("[Diskon] " + getNama() + " - " + persen + "%");
    }
}
