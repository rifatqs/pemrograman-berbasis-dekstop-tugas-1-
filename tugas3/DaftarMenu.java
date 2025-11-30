import java.util.ArrayList;
import java.io.*;

public class DaftarMenu {
    private ArrayList<ItemMenu> daftar = new ArrayList<ItemMenu>();

    public void tambah(ItemMenu m) {
        daftar.add(m);
    }

    public int jumlah() {
        return daftar.size();
    }

    public ItemMenu getByIndex(int idx) throws MenuTidakAda {
        if (idx < 0 || idx >= daftar.size()) {
            throw new MenuTidakAda("Nomor menu tidak valid.");
        }
        return daftar.get(idx);
    }

    public double diskonTerbesar() {
        double max = 0;
        for (ItemMenu m : daftar) {
            if (m instanceof Diskon) {
                double p = ((Diskon)m).getPersen();
                if (p > max) {
                    max = p;
                }
            }
        }
        return max;
    }

    public void tampil() {
        System.out.println("\n=== DAFTAR MENU RESTORAN ===");

        System.out.println("> Makanan:");
        for (int i = 0; i < daftar.size(); i++) {
            ItemMenu m = daftar.get(i);
            if (m.getKategori().equalsIgnoreCase("Makanan")) {
                System.out.print((i + 1) + ". ");
                m.tampil();
            }
        }

        System.out.println("> Minuman:");
        for (int i = 0; i < daftar.size(); i++) {
            ItemMenu m = daftar.get(i);
            if (m.getKategori().equalsIgnoreCase("Minuman")) {
                System.out.print((i + 1) + ". ");
                m.tampil();
            }
        }

        System.out.println("> Diskon:");
        for (int i = 0; i < daftar.size(); i++) {
            ItemMenu m = daftar.get(i);
            if (m.getKategori().equalsIgnoreCase("Diskon")) {
                System.out.print((i + 1) + ". ");
                m.tampil();
            }
        }
    }

    //File I/O untuk MENU
    public void simpanKeFile(String namaFile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(namaFile));
        for (ItemMenu m : daftar) {
            if (m instanceof Makanan) {
                Makanan mk = (Makanan)m;
                bw.write("MAKANAN;" + mk.getNama() + ";" + mk.getHarga() + ";" + mk.getJenis());
            } else if (m instanceof Minuman) {
                Minuman mn = (Minuman)m;
                bw.write("MINUMAN;" + mn.getNama() + ";" + mn.getHarga() + ";" + mn.getJenis());
            } else if (m instanceof Diskon) {
                Diskon d = (Diskon)m;
                bw.write("DISKON;" + d.getNama() + ";" + d.getPersen());
            }
            bw.newLine();
        }
        bw.close();
    }

    public void muatDariFile(String namaFile) throws IOException {
        daftar.clear();
        File f = new File(namaFile);
        if (!f.exists()) {
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            String[] b = line.split(";");
            if (b[0].equalsIgnoreCase("MAKANAN") && b.length >= 4) {
                String nama = b[1];
                double harga = Double.parseDouble(b[2]);
                String jenis = b[3];
                tambah(new Makanan(nama, harga, jenis));
            } else if (b[0].equalsIgnoreCase("MINUMAN") && b.length >= 4) {
                String nama = b[1];
                double harga = Double.parseDouble(b[2]);
                String jenis = b[3];
                tambah(new Minuman(nama, harga, jenis));
            } else if (b[0].equalsIgnoreCase("DISKON") && b.length >= 3) {
                String nama = b[1];
                double persen = Double.parseDouble(b[2]);
                tambah(new Diskon(nama, persen));
            }
        }
        br.close();
    }
}
