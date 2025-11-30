import java.util.Scanner;
import java.io.IOException;

public class Main {

    private static final String FILE_MENU = "menu.txt";
    private static final String FILE_STRUK = "struk.txt";

    private static Scanner in = new Scanner(System.in);
    private static DaftarMenu menu = new DaftarMenu();

    public static void main(String[] args) {
        muatAtauDefault();
        menuUtama();
        simpanMenu();
        in.close();
        System.out.println("Program selesai.");
    }

    // ====== MAIN MENU LOOP ======
    private static void menuUtama() {
        boolean jalan = true;
        while (jalan) {
            System.out.println("\n=== MENU UTAMA RESTORAN ===");
            System.out.println("1. Tambah item menu");
            System.out.println("2. Tampilkan menu");
            System.out.println("3. Buat pesanan");
            System.out.println("4. Tampilkan struk terakhir");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            String p = in.nextLine();

            if (p.equals("1")) {
                tambahItemMenu();
            } else if (p.equals("2")) {
                menu.tampil();
            } else if (p.equals("3")) {
                buatPesanan();
            } else if (p.equals("4")) {
                Pesanan.tampilStrukDariFile(FILE_STRUK);
            } else if (p.equals("5")) {
                jalan = false;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    //MENU & DATA AWAL
    private static void muatAtauDefault() {
        try {
            menu.muatDariFile(FILE_MENU);
            if (menu.jumlah() == 0) {
                isiMenuDefault();
            }
        } catch (IOException e) {
            System.out.println("Gagal muat menu, pakai default.");
            isiMenuDefault();
        }
    }

    private static void isiMenuDefault() {
        menu.tambah(new Makanan("Kepiting", 25000, "Seafood"));
        menu.tambah(new Makanan("Ikan Bakar", 30000, "Seafood"));
        menu.tambah(new Makanan("Salmon", 28000, "Seafood"));
        menu.tambah(new Makanan("Lobster", 40000, "Seafood"));

        menu.tambah(new Minuman("Es Teh", 8000, "Dingin"));
        menu.tambah(new Minuman("Es Jeruk", 10000, "Dingin"));
        menu.tambah(new Minuman("Kopi Hitam", 12000, "Panas"));
        menu.tambah(new Minuman("Jus Alpukat", 15000, "Dingin"));

        menu.tambah(new Diskon("Diskon Spesial 10%", 10));
    }

    private static void simpanMenu() {
        try {
            menu.simpanKeFile(FILE_MENU);
        } catch (IOException e) {
            System.out.println("Gagal simpan menu: " + e.getMessage());
        }
    }

    private static void tambahItemMenu() {
        System.out.println("\nTambah item:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon");
        System.out.print("Pilih: ");
        String j = in.nextLine();

        if (j.equals("1")) {
            System.out.print("Nama makanan: ");
            String n = in.nextLine();
            double h = inputDouble("Harga: ");
            System.out.print("Jenis (misal: Seafood): ");
            String jenis = in.nextLine();
            menu.tambah(new Makanan(n, h, jenis));
        } else if (j.equals("2")) {
            System.out.print("Nama minuman: ");
            String n = in.nextLine();
            double h = inputDouble("Harga: ");
            System.out.print("Jenis (Dingin/Panas): ");
            String jenis = in.nextLine();
            menu.tambah(new Minuman(n, h, jenis));
        } else if (j.equals("3")) {
            System.out.print("Nama diskon: ");
            String n = in.nextLine();
            double p = inputDouble("Persentase (misal 10): ");
            menu.tambah(new Diskon(n, p));
        } else {
            System.out.println("Jenis tidak valid.");
            return;
        }

        try {
            menu.simpanKeFile(FILE_MENU);
        } catch (IOException e) {
            System.out.println("Gagal simpan menu: " + e.getMessage());
        }
        System.out.println("Item berhasil ditambahkan.");
    }

    private static void buatPesanan() {
        if (menu.jumlah() == 0) {
            System.out.println("Belum ada menu.");
            return;
        }

        Pesanan pesanan = new Pesanan();

        while (true) {
            menu.tampil();
            System.out.print("Nomor menu (0 selesai): ");
            String s = in.nextLine();
            int no;
            try {
                no = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Harus angka.");
                continue;
            }

            if (no == 0) {
                break;
            }

            try {
                ItemMenu m = menu.getByIndex(no - 1);

                if (m instanceof Diskon) {
                    System.out.println("Diskon tidak bisa dipesan.");
                    continue;
                }

                int jml = (int)inputDouble("Jumlah: ");
                if (jml <= 0) {
                    System.out.println("Jumlah harus > 0.");
                    continue;
                }

                pesanan.tambah(m, jml);
                System.out.println("Ditambahkan: " + m.getNama() + " x" + jml);
            } catch (MenuTidakAda e) {
                System.out.println(e.getMessage());
            }
        }

        if (pesanan.banyak() == 0) {
            System.out.println("Tidak ada pesanan.");
            return;
        }

        double diskon = menu.diskonTerbesar();
        pesanan.cetakStruk(diskon, FILE_STRUK);
    }

    private static double inputDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String s = in.nextLine();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
            }
        }
    }
}
