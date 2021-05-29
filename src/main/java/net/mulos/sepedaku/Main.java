/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.mulos.sepedaku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SA GSSI
 */
public class Main {
  public void display_menu() {
    System.out.println ( "1. Tambah Sepeda \n2. Lihat Stock Sepeda\n3. Penjualan Sepeda\n4. Keluar" );
    System.out.println ( "Pilih Menu: " );
   
  }
  
  static String idSepeda(String ukuran){
      String u ="B";
      if(ukuran.equals("Kecil")){
          u="K";
      }
      if(ukuran.equals("Sedang")){
          u="S";
    }
    Random rd = new Random();
    String abc = "0123456789";

    char y1 = abc.charAt(rd.nextInt(abc.length()));
    char y2 = abc.charAt(rd.nextInt(abc.length()));
    char y3 = abc.charAt(rd.nextInt(abc.length()));
    return "B"+u+""+y1+""+y2+""+y3;
  }
  
  public Main() {
    Scanner in = new Scanner ( System.in );
    
    display_menu();
    switch ( in.nextInt() ) {
      case 1:
        System.out.println ( "1. Tambah Sepeda \n Nama Sepeda :" );
        String namaSepeda = in.next();
        System.out.println ( "Ukuran Sepeda :" );
        String ukuranSepeda = in.next();
        System.out.println ( "Harga Sepeda :" );
        int harga = in.nextInt();
        System.out.println ( "Stock :" );
        int stock = in.nextInt();
        System.out.println ( "Simpan (Y/N) :" );
        String simpan = in.next();
        if(simpan.equals("Y")||simpan.equals("y")){
            simpanSepeda(namaSepeda, ukuranSepeda,harga,stock);
        }
        break;
      case 2:
        menu2();
        break;
      case 3:
        menu3(in);
        break;
      default:
        System.err.println ( "Anda Telah Keluar" );
        break;
    }
  }
  
  void menu2(){
        try {
            String sql = "select * from sepeda order by nama asc";
            Connection conn=(Connection)Config.configDB();
            PreparedStatement  stmt = conn.prepareStatement(sql);
            ResultSet r = stmt.executeQuery();
            System.out.println ( "\nNama Sepeda | Id Sepeda | Ukuran | Harga | Stock" );
            while (r.next()) {
                int id = r.getInt(1);
                String idSepeda = r.getString(2);
                String nama = r.getString(3);
                String ukuran = r.getString(4);
                int harga = r.getInt(5);
                int stock = r.getInt(6);
                System.out.println ( nama+" | "+idSepeda +" | "+ukuran+" | "+harga+" | " + stock );
            }
            conn.close();
            System.out.println();
            new Main();
        } catch (SQLException ex) {
           System.out.println ( "Error, Tidak ada data" );
        }
    }
  
    void menu3(Scanner in){
        try {
            String sql = "select * from sepeda order by nama asc";
            Connection conn=(Connection)Config.configDB();
            PreparedStatement  stmt = conn.prepareStatement(sql);
            ResultSet r = stmt.executeQuery();
            System.out.println ( "\nNama Sepeda | Id Sepeda | Ukuran | Harga | Stock" );
            while (r.next()) {
                int id = r.getInt(1);
                String idSepeda = r.getString(2);
                String nama = r.getString(3);
                String ukuran = r.getString(4);
                int harga = r.getInt(5);
                int stock = r.getInt(6);
                System.out.println ( nama+" | "+idSepeda +" | "+ukuran+" | "+harga+" | " + stock );
            }
            conn.close();
            System.out.println();
            System.out.println ( "Pilih ID Sepeda Yang Akan dijual :" );
            String idSepeda = in.next();
            System.out.println ( "Lanjutkan (Y/N) :" );
            String simpan = in.next();
            if(simpan.equals("Y")||simpan.equals("y")){
                if(idSepeda.equals("0")){
                    new Main();
                }else jualSepeda(idSepeda);
            }
            
        } catch (SQLException ex) {
           System.out.println ( "Error, Tidak ada data" );
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main ( String[] args ) {
        new Main();
    }
    
    
    
    static void simpanSepeda(String nama, String ukuran, int harga, int stock){
        try {
            String sql = "INSERT INTO sepeda (id_sepeda, nama,ukuran,harga,stock) values ('"+idSepeda(ukuran)+"','Sepeda "+nama+"','"+ukuran+"',"+harga+","+stock+")";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
           System.out.println ( "Berhasi disimpam" );
           new Main();
        } catch (Exception e) {
            System.out.println ( "Gagal " + e.getMessage());
        }
    }
    
    static void jualSepeda(String idSepeda){
        try {
            String sql = "update sepeda set stock = stock - 1 where id_sepeda='"+idSepeda+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
           System.out.println ( "Berhasil Terjual" );
           new Main();
        } catch (Exception e) {
            System.out.println ( "Gagal " + e.getMessage());
        }
    }
    
}
