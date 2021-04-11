package org.agoncal.quarkus.jpa.model;

import javax.persistence.Persistence;

public class GenerateDDL {

  public static void main(String[] args) {
    Persistence.generateSchema("vintageStorePU", null);
    System.exit(0);
  }
}
