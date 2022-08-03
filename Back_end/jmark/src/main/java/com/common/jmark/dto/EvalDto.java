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

    private CompanyDto companyDto;

    public EvalDto(Eval eval , CompanyDto companyDto) {
        this.id = eval.getId();
        this.name = eval.getName();

        this.companyDto = companyDto;
    }

//    public void setLinkEntity(CompanyDto companyDto){
//        this.companyDto = companyDto;
//    }
}
