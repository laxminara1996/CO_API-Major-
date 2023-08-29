package in.co.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CoNoticeEntity {
private String noticeStatus;
private Long caseNum;
private String fileUrl;
}
