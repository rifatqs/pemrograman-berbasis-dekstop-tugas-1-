import java.util.Scanner;

class Menu {
    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Restoran {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Array menu makanan dan minuman
        Menu[] menuList = {
            new Menu("Kepiting", 25000, "Makanan"),
            new Menu("Ikan Bakar", 30000, "Makanan"),
            new Menu("Salmon", 28000, "Makanan"),
            new Menu("Lobster", 40000, "Makanan"),
            new Menu("Es Teh", 8000, "Minuman"),
            new Menu("Es Jeruk", 10000, "Minuman"),
            new Menu("Kopi Hitam", 12000, "Minuman"),
            new Menu("Jus Alpukat", 15000, "Minuman")
        };

        // Tampilkan daftar menu
        System.out.println("------ DAFTAR MENU RIFAT_SEAFOOD ------");
        System.out.println(">> Makanan:");
        System.out.println("1. Kepiting - Rp 25.000");
        System.out.println("2. Ikan Bakar - Rp 30.000");
        System.out.println("3. Salmon - Rp 28.000");
        System.out.println("4. Lobster - Rp 40.000");

        System.out.println("\n>> Minuman:");
        System.out.println("5. Es Teh - Rp 8.000");
        System.out.println("6. Es Jeruk - Rp 10.000");
        System.out.println("7. Kopi Hitam - Rp 12.000");
        System.out.println("8. Jus Alpukat - Rp 15.000");
        System.out.println("====================================\n");

        // Input maksimal 4 pesanan
        System.out.print("Masukkan jumlah menu yang ingin dipesan 1-4 (maksimal 4): ");
        int jumlahPesanan = input.nextInt();

        // Variabel penyimpanan pesanan
        int[] nomorPesanan = new int[4];
        int[] jumlahPesananArray = new int[4];
        double total = 0;

        // Input berdasarkan jumlah pesanan
        if (jumlahPesanan >= 1) {
            System.out.print("Pesanan 1 (nomor menu): ");
            nomorPesanan[0] = input.nextInt();
            System.out.print("Jumlah: ");
            jumlahPesananArray[0] = input.nextInt();
            total += hitungHarga(nomorPesanan[0], jumlahPesananArray[0], menuList);
        }

        if (jumlahPesanan >= 2) {
            System.out.print("Pesanan 2 (nomor menu): ");
            nomorPesanan[1] = input.nextInt();
            System.out.print("Jumlah: ");
            jumlahPesananArray[1] = input.nextInt();
            total += hitungHarga(nomorPesanan[1], jumlahPesananArray[1], menuList);
        }

        if (jumlahPesanan >= 3) {
            System.out.print("Pesanan 3 (nomor menu): ");
            nomorPesanan[2] = input.nextInt();
            System.out.print("Jumlah: ");
            jumlahPesananArray[2] = input.nextInt();
            total += hitungHarga(nomorPesanan[2], jumlahPesananArray[2], menuList);
        }

        if (jumlahPesanan >= 4) {
            System.out.print("Pesanan 4 (nomor menu): ");
            nomorPesanan[3] = input.nextInt();
            System.out.print("Jumlah: ");
            jumlahPesananArray[3] = input.nextInt();
            total += hitungHarga(nomorPesanan[3], jumlahPesananArray[3], menuList);
        }

        // Hitung pajak dan biaya pelayanan
        double pajak = total * 0.10;
        double biayaPelayanan = 20000;
        double totalSebelumDiskon = total + pajak + biayaPelayanan;
        double diskon = 0;
        boolean dapatPromoMinuman = false;

        // diskon dan promo
        if (total > 100000) {
            diskon = totalSebelumDiskon * 0.10;
        } else if (total > 50000) {
            dapatPromoMinuman = true;
        }

        double totalAkhir = totalSebelumDiskon - diskon;

        // Cetak struk
        System.out.println("\n========= STRUK PEMBAYARAN =========");
        System.out.println("Pesanan:");
        for (int i = 0; i < jumlahPesanan; i++) {
            if (nomorPesanan[i] >= 1 && nomorPesanan[i] <= 8) {
                Menu m = menuList[nomorPesanan[i] - 1];
                System.out.println("- " + m.nama + " x" + jumlahPesananArray[i]);
            }
        }

        System.out.println("------------------------------------");
        System.out.println("Subtotal        : Rp " + total);
        System.out.println("Pajak (10%)     : Rp " + pajak);
        System.out.println("Pelayanan       : Rp " + biayaPelayanan);

        // informasi diskon/promo
        if (diskon > 0) {
            System.out.println("Diskon (10%)    : -Rp " + diskon);
        } else {
            if (dapatPromoMinuman) {
                System.out.println("Promo: Beli satu gratis satu untuk kategori Minuman");
            } else {
                System.out.println("Tidak ada diskon atau promo yang berlaku.");
            }
        }

        System.out.println("------------------------------------");
        System.out.println("Total Bayar     : Rp " + totalAkhir);
        System.out.println("====================================");
        System.out.println("---Terima kasih---");

        input.close();
    }

    // Method untuk menghitung harga pesanan berdasarkan nomor menu
    public static double hitungHarga(int nomorMenu, int jumlah, Menu[] daftarMenu) {
        if (nomorMenu >= 1 && nomorMenu <= daftarMenu.length) {
            return daftarMenu[nomorMenu - 1].harga * jumlah;
        } else {
            System.out.println("Nomor menu " + nomorMenu + " tidak valid!");
            return 0;
        }
    }
}
