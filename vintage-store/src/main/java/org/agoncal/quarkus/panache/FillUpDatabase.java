package org.agoncal.quarkus.panache;

import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

public class FillUpDatabase {

  private static Faker faker = new Faker();
  private static FileWriter fileWriter;
  private static PrintWriter printWriter;

  public static void main(String[] args) throws IOException {
    fileWriter = new FileWriter("fileName.sql");
    printWriter = new PrintWriter(fileWriter);

    fillsUpTheDatabase();

    printWriter.print("ALTER SEQUENCE hibernate_sequence RESTART WITH 2000;");
    printWriter.close();
    fileWriter.close();
  }

  public static void fillsUpTheDatabase() {
    fillUpArtists();
    fillUpPublishers();
    fillUpCustomers();
    fillUpCDs();
    fillUpTracks();
    fillUpBooks();
    fillUpPurchaseOrders();
    fillUpPurchaseOrdersLines();
  }

  private static void fillUpArtists() {
    String insert = "INSERT INTO t_artists (id, bio, created_date, name) VALUES (%d, '%s', '%s', '%s');\n";
    for (int i = 0; i < 500; i++) {
      printWriter.printf(insert, i, faker.lorem().paragraph(), Instant.now(), faker.book().author());
    }
  }

  private static void fillUpPublishers() {
    String insert = "INSERT INTO t_publishers (id, created_date, name) VALUES ((%d, '%s', '%s');\n";
  }

  private static void fillUpCustomers() {
    String insert = "INSERT INTO t_customers (id, created_date, e_mail, first_name, last_name) VALUES (1, '2021-05-17 18:00:31.155863', 'crystal.stokes@hotmail.com', 'Lawerence', 'Tillman');\n";
  }

  private static void fillUpCDs() {
    String insert = "INSERT INTO t_items (dtype, id, created_date, description, price, title, genre, music_company, artist_fk) VALUES ('CD', 1, '2021-05-18 15:09:58.425683', 'Dolores vel aliquid necessitatibus. Dolorum perspiciatis sed ipsum. Labore impedit quis molestias ducimus enim et. Est at aspernatur quae quia. Dolorum est officia culpa.', 28.00, 'The Who', 'Rock', 'Larson-Kerluke', 758);\n";
  }

  private static void fillUpTracks() {
    String insert = "INSERT INTO t_tracks (id, created_date, duration, title, cd_id) VALUES (2, '2021-05-18 15:25:55.936791', 773000000000, 'Eddie Bull', 521);\n";
  }

  private static void fillUpBooks() {
    String insert = "INSERT INTO t_items (dtype, id, created_date, description, price, title, isbn, language, nb_of_pages, publication_date, artist_fk, publisher_fk) VALUES ('Book', 1001, '2021-05-18 15:27:47.527836', 'In nam omnis nihil rerum blanditiis reprehenderit. Totam molestiae facilis. A at necessitatibus. Voluptas est facere ut provident nisi sapiente sapiente.', 46.00, 'When the Green Woods Laugh', '9791771336481', 'CHINESE', 658, '2016-03-11', 122, 85);\n";
  }

  private static void fillUpPurchaseOrders() {
    String insert = "INSERT INTO t_purchase_orders (id, created_date, purchase_order_date, customer_fk) VALUES (1000, '2021-05-18 16:23:16.942551', '2021-05-18', 549);\n";
  }

  private static void fillUpPurchaseOrdersLines() {
    String insert = "INSERT INTO t_purchase_order_lines (id, created_date, quantity, item_fk, purchase_order_fk) VALUES (1032, '2021-05-18 16:23:17.187980', 2, 635, 1031);\n";
  }
}
