package oit.is.z2789.kaizi.janken.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface MatchInfoMapper {
  @Insert("INSERT INTO matchinfo (user1, user2, user1hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive});")
  void insertMatchInfo(String user1, String user2, String user1Hand, Boolean isActive);
}
