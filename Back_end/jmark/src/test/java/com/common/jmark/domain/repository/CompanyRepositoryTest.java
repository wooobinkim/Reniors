package domain.repository;

import com.common.jmark.JmarkApplication;
import com.common.jmark.domain.entity.TypeCompany;
import com.common.jmark.domain.repository.CompanyRepository;
import com.common.jmark.domain.repository.TypeCompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = JmarkApplication.class)
@Transactional
@Rollback(value = false)
class CompanyRepositoryTest {

    @Autowired
    TypeCompanyRepository typeCompanyRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void CompanyTest(){
        TypeCompany typeCompany = new TypeCompany("중견기업");
        typeCompanyRepository.save(typeCompany);
    }

}