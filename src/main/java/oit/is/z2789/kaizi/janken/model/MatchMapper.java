package oit.is.z2789.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {
  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user1, user2, user1hand, user2hand) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand});")
  void insertMatch(Integer user1, Integer user2, String user1Hand, String user2Hand);
}
