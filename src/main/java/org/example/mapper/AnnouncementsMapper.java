package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Announcements;

import java.util.List;

@Mapper
public interface AnnouncementsMapper {
    @Select("select * from announcements")
    List<Announcements> findAll();

    @Insert("insert into announcements(title,content,create_time,update_time) value " +
            "(#{title},#{content},#{createTime},#{updateTime})")
    void insert(Announcements announcements);

    @Delete("delete from announcements where id=#{id}")
    void deleteById(Integer id);

    void update(Announcements announcements);

    Announcements selectById(Integer id);

    List<Announcements> searchAnnouncements(String keyword);
}
