import java.util.ArrayList;
import java.io.*;

public class Pesanan {
    private ArrayList<ItemMenu> item = new ArrayList<ItemMenu>();
    private ArrayList<Integer> jumlah = new ArrayList<Integer>();

    public void tambah(ItemMenu m, int j) {
        item.add(m);
        jumlah.add(j);
    }

    public int banyak() {
        return item.size();
    }

    public double hitungSubtotal() {
        double total = 0;
        for (int i = 0; i < item.size(); i++) {
            total += item.get(i).getHarga() * jumlah.get(i);
        }
        return total;
    }

    public void cetakStruk(double persenDiskon, String namaFileStruk) {
        double subtotal = hitungSubtotal();
        double pajak = subtotal * 0.10;
        double layanan = 20000;
        double totalSblm = subtotal + pajak + layanan;
        double potDiskon = totalSblm * persenDiskon / 100.0;
        double totalAkhir = totalSblm - potDiskon;

        StringBuilder sb = new StringBuilder();
        sb.append("\n==== STRUK PEMBAYARAN ====\n");
        for (int i = 0; i < item.size(); i++) {
            ItemMenu m = item.get(i);
            int j = jumlah.get(i);
            double t = m.getHarga() * j;
            sb.append("- ").append(m.getNama())
              .append(" x").append(j)
              .append(" @Rp ").append((int)m.getHarga())
              .append(" = Rp ").append((int)t).append("\n");
        }
        sb.append("--------------------------\n");
        sb.append("Subtotal   : Rp ").append((int)subtotal).append("\n");
        sb.append("Pajak 10%  : Rp ").append((int)pajak).append("\n");
        sb.append("Layanan    : Rp ").append((int)layanan).append("\n");
        sb.append("Diskon     : Rp ").append((int)potDiskon)
          .append(" (").append(persenDiskon).append("%)\n");
        sb.append("--------------------------\n");
        sb.append("Total Bayar: Rp ").append((int)totalAkhir).append("\n");

        // cetak ke layar
        System.out.println(sb.toString());

        // simpan ke file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(namaFileStruk));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }

    public static void tampilStrukDariFile(String namaFileStruk) {
        File f = new File(namaFileStruk);
        if (!f.exists()) {
            System.out.println("Belum ada struk tersimpan.");
            return;
        }
        System.out.println("\n==== STRUK DARI FILE ====");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Gagal membaca struk: " + e.getMessage());
        }
    }
}
