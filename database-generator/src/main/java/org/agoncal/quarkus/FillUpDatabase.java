package org.agoncal.quarkus;

import com.github.javafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;

public class FillUpDatabase {

  private static Faker faker = new Faker();
  private static FileWriter fileWriter;
  private static PrintWriter printWriter;

  public static final int NB_ARTISTS = 100;
  public static final int NB_PUBLISHERS = 100;
  public static final int NB_CUSTOMERS = 100;
  public static final int NB_CDS = 100;
  public static final int NB_TRACKS = 100;
  public static final int NB_BOOKS = 100;
  public static final int NB_PURCHASE_ORDERS = 50;

  public static void main(String[] args) throws IOException {
//    fileWriter = new FileWriter("import.sql");
    fileWriter = new FileWriter("../vintage-store/src/main/resources/import.sql");
    printWriter = new PrintWriter(fileWriter);

    fillsUpTheDatabase();

    printWriter.printf("\nALTER SEQUENCE hibernate_sequence RESTART WITH %d;", NB_ARTISTS + NB_PUBLISHERS + NB_CUSTOMERS + NB_CDS + NB_TRACKS + NB_BOOKS);
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
  }

  private static void fillUpArtists() {
    printWriter.println("\n-- ARTISTS");
    printWriter.println("INSERT INTO t_artists (id, name, bio, created_date) VALUES");
    String insert = "\t(%d, '%s', '%s', '%s')%s\n";
    for (int i = 1; i < NB_ARTISTS; i++) {
      printWriter.printf(insert, i, format(faker.book().author()), faker.lorem().paragraph(), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_ARTISTS, format(faker.book().author()), faker.lorem().paragraph(), Instant.now(), ";");
  }

  private static void fillUpPublishers() {
    printWriter.println("\n-- PUBLISHERS");
    printWriter.println("INSERT INTO t_publishers (id, name, created_date) VALUES");
    String insert = "\t(%d, '%s', '%s')%s\n";
    for (int i = 1; i < NB_PUBLISHERS; i++) {
      printWriter.printf(insert, i, format(faker.book().publisher()), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_ARTISTS, format(faker.book().publisher()), Instant.now(), ";");
  }

  private static void fillUpCustomers() {
    printWriter.println("\n-- CUSTOMERS");
    printWriter.println("INSERT INTO t_customers (id, first_name, last_name, e_mail, created_date) VALUES");
    String insert = "\t(%d, '%s', '%s', '%s', '%s')%s\n";
    for (int i = 1; i < NB_CUSTOMERS; i++) {
      printWriter.printf(insert, i, format(faker.name().firstName()), format(faker.name().lastName()), faker.internet().emailAddress(), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_ARTISTS, format(faker.name().firstName()), format(faker.name().lastName()), faker.internet().emailAddress(), Instant.now(), ";");
  }

  private static void fillUpCDs() {
    printWriter.println("\n-- CDS");
    printWriter.println("INSERT INTO t_items (dtype, id, artist_fk, title, price, genre, music_company, description, created_date) VALUES");
    String insert = "\t('CD', %d, %d, '%s', %d, '%s', '%s', '%s', '%s')%s\n";
    for (int i = 1; i < NB_CDS; i++) {
      printWriter.printf(insert, i, faker.number().numberBetween(1, NB_ARTISTS), format(faker.rockBand().name()), faker.number().numberBetween(1, 100), format(faker.music().genre()), format(faker.company().name()), faker.lorem().paragraph(), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_CDS, faker.number().numberBetween(1, NB_ARTISTS), format(faker.rockBand().name()), faker.number().numberBetween(1, 100), format(faker.music().genre()), format(faker.company().name()), faker.lorem().paragraph(), Instant.now(), ";");
  }

  private static void fillUpTracks() {
    printWriter.println("\n-- TRACKS");
    printWriter.println("INSERT INTO t_tracks (id, cd_fk, title, duration, created_date) VALUES");
    String insert = "\t(%d, %d, '%s', %d, '%s')%s\n";
    for (int i = 1; i < NB_TRACKS; i++) {
      printWriter.printf(insert, i, faker.number().numberBetween(1, NB_CDS), format(faker.funnyName().name()), faker.number().numberBetween(1, 1_000), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_TRACKS, faker.number().numberBetween(1, NB_CDS), format(faker.funnyName().name()), faker.number().numberBetween(1, 1_000), Instant.now(), ";");
  }

  private static void fillUpBooks() {
    printWriter.println("\n-- BOOKS");
    printWriter.println("INSERT INTO t_items (dtype, id, artist_fk, publisher_fk, title, isbn, price, language, nb_of_pages, publication_date, description, created_date) VALUES");
    String insert = "\t('Book', %d, %d, %d, '%s', '%s', %d, '%s', %d, '%s', '%s', '%s')%s\n";
    for (int i = NB_CDS + 1; i < NB_CDS + NB_BOOKS; i++) {
      printWriter.printf(insert, i, faker.number().numberBetween(1, NB_ARTISTS), faker.number().numberBetween(1, NB_PUBLISHERS), format(faker.book().title()), faker.code().isbn13(), faker.number().numberBetween(1, 100), faker.options().option(Language.class), faker.number().numberBetween(10, 800), LocalDate.now().minusDays(faker.number().numberBetween(100, 10_000)), faker.lorem().paragraph(), Instant.now(), ",");
    }
    printWriter.printf(insert, NB_CDS + NB_BOOKS, faker.number().numberBetween(1, NB_ARTISTS), faker.number().numberBetween(1, NB_PUBLISHERS), format(faker.book().title()), faker.code().isbn13(), faker.number().numberBetween(1, 100), faker.options().option(Language.class), faker.number().numberBetween(10, 800), LocalDate.now().minusDays(faker.number().numberBetween(100, 10_000)), faker.lorem().paragraph(), Instant.now(), ";");
  }

  private static void fillUpPurchaseOrders() {
    printWriter.println("\n-- PURCHASE ORDER");
    for (int i = 1; i < NB_PURCHASE_ORDERS; i++) {
      String insertPO = "INSERT INTO t_purchase_orders (id, customer_fk, purchase_order_date, created_date) VALUES (%d, %d, '%s', '%s')%s\n";
      printWriter.printf(insertPO, i, faker.number().numberBetween(1, NB_CUSTOMERS), LocalDate.now().minusDays(faker.number().numberBetween(1, 1_000)), Instant.now(), ";");

      printWriter.println("\tINSERT INTO t_purchase_order_lines (id, purchase_order_fk, item_fk, quantity, created_date) VALUES");
      String insertPOL = "\t\t(%d, %d, %d, %d, '%s')%s\n";
      int NB_PURCHASE_ORDERS_LINES = faker.number().numberBetween(2, 5);
      for (int j = 1; j < NB_PURCHASE_ORDERS_LINES; j++) {
        printWriter.printf(insertPOL, (i * 10) + j, i, faker.number().numberBetween(1, NB_CDS + NB_BOOKS), faker.number().numberBetween(1, 5), Instant.now(), ",");
      }
      printWriter.printf(insertPOL, (i * 10) + NB_PURCHASE_ORDERS_LINES, i, faker.number().numberBetween(1, NB_CDS + NB_BOOKS), faker.number().numberBetween(1, 5), Instant.now(), ";");
    }
  }

  private static String format(String value) {
    value = value.trim();
    value = value.replaceAll("'", "");
    value = value.replaceAll("\"", "");
    return value;
  }
}
