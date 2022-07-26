package domain.entity;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class JobOpening {

    @Id @GeneratedValue
    @Column(name = "job_opening_id")
    private Long id;
    private Date createdAt;
    private Date finishedAt;
    private int numberPeople;
    private int minCareer;
    private String title;
    private String contents;
    private String contentsImgName;
    private String contentsImgPath;
    private String minSalary;
    private String jobPosition;
    private String workingDay;

    //공고 - 회사 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Company company;

    //공고 - 고용형태 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private TypeEmployment typeEmployment;

    //최종학력, 직무소분류, 구군 받아오기


}
