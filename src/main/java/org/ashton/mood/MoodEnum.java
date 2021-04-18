package org.ashton.mood;

public enum MoodEnum {
  DEPRESSION("(Fight depression OR Win depression)"),
  ANXIETY("(Fight anxiety OR Conquer anxiety OR Overcome anxiety)"),
  SADNESS("(Fight sadness OR Win sadness)"),
  NEGATIVE("(Fight negative OR Win negative)"),
  STRESSED("(Fight stressed OR Win stressed)");

  private String searchKey;

  MoodEnum(String key) {
    searchKey = key;
  }

  public String getSearchKey() {
    return searchKey;
  }

  public static MoodEnum fromValue(String value) {
    for (MoodEnum e: MoodEnum.values()) {
      if (e.name().toLowerCase().equals(value.toLowerCase())) {
        return e;
      }
    }
    return null;
  }
}
