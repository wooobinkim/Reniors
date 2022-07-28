package com.common.jmark.domain.repository;

import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Enum.TypeCompany;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.QCompany;
import com.common.jmark.domain.entity.QJobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.category.JobParentCategory;
import com.common.jmark.domain.entity.category.Sido;
import com.common.jmark.domain.repository.category.GugunRepository;
import com.common.jmark.domain.repository.category.JobChildCategoryRepository;
import com.common.jmark.domain.repository.category.JobParentCategoryRepository;
import com.common.jmark.domain.repository.category.SidoRepository;
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

    @Autowired
    SidoRepository sidoRepository;

    @Autowired
    GugunRepository gugunRepository;

    @Autowired
    JobParentCategoryRepository jobParentCategoryRepository;

    @Autowired
    JobChildCategoryRepository jobChildCategoryRepository;

    @PersistenceContext
    EntityManager em;
//    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);


//    @Test
//    public void CompanyTest(){
//
//        Date date = new Date();
//        Company company = new Company("가나다회사","root","root","1999","www","서울시",
//                "이미지이름","이미지경로","123-123","0101111",대기업);
//        companyRepository.save(company);
//    }

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

    @Test
    public void JobCategoryInsert(){
        JobParentCategory jobParentCategory1 = JobParentCategory.create("기획");
        JobParentCategory jobParentCategory2 = JobParentCategory.create("인사");
        JobParentCategory jobParentCategory3 = JobParentCategory.create("교육");
        jobParentCategoryRepository.save(jobParentCategory1);
        jobParentCategoryRepository.save(jobParentCategory2);
        jobParentCategoryRepository.save(jobParentCategory3);

        Optional<JobParentCategory> parentCategory1 = jobParentCategoryRepository.findById(1L);
        Optional<JobParentCategory> parentCategory2 = jobParentCategoryRepository.findById(2L);
        Optional<JobParentCategory> parentCategory3 = jobParentCategoryRepository.findById(3L);

        JobChildCategory jobChildCategory1 = JobChildCategory.create("경영기획",parentCategory1.get());
        JobChildCategory jobChildCategory2 = JobChildCategory.create("브랜드기획",parentCategory1.get());
        JobChildCategory jobChildCategory3 = JobChildCategory.create("노무사",parentCategory2.get());
        JobChildCategory jobChildCategory4 = JobChildCategory.create("채용담당자",parentCategory2.get());
        JobChildCategory jobChildCategory5 = JobChildCategory.create("과외",parentCategory3.get());
        JobChildCategory jobChildCategory6 = JobChildCategory.create("교직원",parentCategory3.get());

        jobChildCategoryRepository.save(jobChildCategory1);
        jobChildCategoryRepository.save(jobChildCategory2);
        jobChildCategoryRepository.save(jobChildCategory3);
        jobChildCategoryRepository.save(jobChildCategory4);
        jobChildCategoryRepository.save(jobChildCategory5);
        jobChildCategoryRepository.save(jobChildCategory6);

    }

    @Test
    public void AddressCategoryInsert(){
        Sido sido1 = Sido.create("서울특별시", 11L);
        Sido sido2 = Sido.create("부산광역시", 12L);
        sidoRepository.save(sido1);
        sidoRepository.save(sido2);

        Optional<Sido> s1 = sidoRepository.findById(1L);
        Optional<Sido> s2 = sidoRepository.findById(2L);

        Gugun gugun1 = Gugun.create("강남구", 111L, s1.get());
        Gugun gugun2 = Gugun.create("구로구", 112L, s1.get());
        Gugun gugun3 = Gugun.create("중구", 121L, s2.get());
        Gugun gugun4 = Gugun.create("서구", 122L, s2.get());

        gugunRepository.save(gugun1);
        gugunRepository.save(gugun2);
        gugunRepository.save(gugun3);
        gugunRepository.save(gugun4);
    }

    @Test
    public void CreateJobOpening(){
        JobOpening jobOpening = jobOpeningRepository.findById(3L).get();

    }



}