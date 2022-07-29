package com.common.jmark.dto;

import com.common.jmark.domain.entity.Eval;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class EvalDto {
    private Long id;
    private String name;
    private Long companyId;

    private CompanyDto companyDto;

    public EvalDto(Eval eval , CompanyDto companyDto) {
        this.id = eval.getId();
        this.name = eval.getName();
        this.companyId = eval.getCompany().getId();
        this.companyDto = companyDto;
//        this.companyId = evalForm.getCompany().getId();
    }

//    public void setLinkEntity(CompanyDto companyDto){
//        this.companyDto = companyDto;
//    }
}
