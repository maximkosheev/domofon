package ru.monsterdev.domofon;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import java.io.IOException;

public class PostgresDatabase {

  private static EmbeddedPostgres embeddedPostgres;

  /**
   * Старт базы.
   */
  public static void start() throws IOException {
    embeddedPostgres = EmbeddedPostgres.start();
  }

  /**
   * Остановка базы.
   */
  public static void stop() {
    if (embeddedPostgres != null) {
      try {
        embeddedPostgres.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static EmbeddedPostgres getInstance() {
    return embeddedPostgres;
  }

}
