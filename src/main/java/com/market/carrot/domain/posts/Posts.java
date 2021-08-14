package com.market.carrot.domain.posts;

import com.market.carrot.domain.BaseTimeEntity;
import com.market.carrot.domain.pictures.Pictures;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id  // 해당 테이블의 PK를 나타냄
    @GeneratedValue (strategy = GenerationType.IDENTITY)  // PK auto_increment
    private Long id;

    @Column (length = 500, nullable = false)
    private String title;

    @Column (columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @OneToMany
    @JoinColumn(name = "POST_PICTURES")
    private List<Pictures> pictures = new ArrayList<>();

    @Builder
    public Posts (String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
