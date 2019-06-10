package edu.cnm.deepdive.atthemovies.model;

import androidx.annotation.NonNull;

public class Movie {

  private Long id;

  private static Long last_id = 0L;

  private String title;

  public Movie() {

    id = ++last_id;

  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @NonNull
  @Override
  public String toString() {
    return title;
  }
}
