package com.wlf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class Chapter
 * Date  2019/12/19 16:51
 * Author 王龙飞
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "debate")
public class Chapter {
    @Id
    private String id;
    private String title;
    private String size;
    private String duration;
    private String cover;
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @Column(name = "album_id")
    private String albumId;
}
