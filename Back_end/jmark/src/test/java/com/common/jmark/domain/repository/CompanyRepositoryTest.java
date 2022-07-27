package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Enum.TypeCompany;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.QCompany;
import com.common.jmark.domain.entity.QJobOpening;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.common.jmark.domain.entity.Enum.TypeCompany.대기업;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CompanyRepositoryTest {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    JobOpeningRepository jobOpeningRepository;

    @PersistenceContext
    EntityManager em;
//    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);


    @Test
    public void CompanyTest(){

        Date date = new Date();
        Company company = new Company("가나다회사","root","root","1999","www","서울시",
                "이미지이름","이미지경로","123-123","0101111",대기업);
        companyRepository.save(company);
    }

    @Test
    public void test2(){
        Company company = companyRepository.findById(1L).get();
        System.out.println("company = " + company);

    }

    @Test
    public void test3(){
        JobOpening jobOpening = jobOpeningRepository.findById(3L).get();
        System.out.println("jobOpening = " + jobOpening);

    }

    @Test
    public void test4(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");

        List<JobOpening> fetch = jpaQueryFactory.selectFrom(j).where(j.minCareer.eq(6)).fetch();
        for (JobOpening jobOpening : fetch) {
            System.out.println("jobOpening = " + jobOpening);
        }
    }

    @Test
    public void test5(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QCompany c = new QCompany("c");

        Company company = jpaQueryFactory.selectFrom(c).where(c.id.eq(2L)).fetchOne();
        System.out.println("company = " + company);
    }

    @Test
    public void test6(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");

        List<JobOpening> fetch = jpaQueryFactory.selectFrom(j).where(j.contents.in("아무")).fetch();
        for (JobOpening jobOpening : fetch) {
            System.out.println("jobOpening = " + jobOpening);
        }
    }

}