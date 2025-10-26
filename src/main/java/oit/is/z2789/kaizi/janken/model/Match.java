package oit.is.z2789.kaizi.janken.model;

public class Match {
  int id;
  int user1;
  int user2;
  String hand1;
  String hand2;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUser1() {
    return user1;
  }

  public void setUser1(int user1) {
    this.user1 = user1;
  }

  public int getUser2() {
    return user2;
  }

  public void setUser2(int user2) {
    this.user2 = user2;
  }

  public String getHand1() {
    return hand1;
  }

  public void setHand1(String hand1) {
    this.hand1 = hand1;
  }

  public String getHand2() {
    return hand2;
  }

  public void setHand2(String hand2) {
    this.hand2 = hand2;
  }

}
