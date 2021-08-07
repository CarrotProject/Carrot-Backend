package com.market.carrot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  // JPA Entity들이 이 클래스를 상속받는 경우 필드값도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)  // 시간에 대한 값을 자동으로 넣어주는 Auditing 기능 추가
public class BaseTimeEntity {

    @CreatedDate  // Entity가 생성되어 저장될 때 시간이 자동 저장 됨
    private LocalDateTime createdDate;

    @LastModifiedDate  // Entity가 조회되어 수정될 때 시간이 자동 저장 됨
    private LocalDateTime modifiedDate;
}