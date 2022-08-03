package com.common.jmark.controller;

import com.common.jmark.common.config.web.LoginUser;
import com.common.jmark.domain.entity.Company;
import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import com.common.jmark.domain.entity.JobOpening;
import com.common.jmark.domain.entity.category.Gugun;
import com.common.jmark.domain.entity.category.JobChildCategory;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.*;
import com.common.jmark.dto.category.GugunResponse;
import com.common.jmark.service.JobOpeningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobopening")
@RequiredArgsConstructor
@Api(tags={"공고 API"})
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;

    //채용공고 조회(조건포함)
    @GetMapping("/search/{searchConditionId}")
    @ApiOperation(value = "공고 조회(조건포함)", notes = "조건검색을 통해 공고를 조회한다.")
    public ResponseEntity<?> getJobOpeningList(@PathVariable("searchConditionId")Long searchConditionId, Pageable pageable){
        Page<JobOpeningDto> JobOpeningList = jobOpeningService.getJobOpeningConditionList(searchConditionId, pageable);
//        List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(x->new JobOpeningDto(x)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(JobOpeningList);
    }

    //공고 전체조회
    @GetMapping("/search")
    @ApiOperation(value = "공고 조회", notes = "전체 공고를 조회한다.")
    public ResponseEntity<?> getJobOpening(Pageable pageable){
        Page<JobOpeningDto> jobOpeningList = jobOpeningService.getJobOpening(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //채용공고 상세조회
    @GetMapping("/{jobOpeningId}")
    @ApiOperation(value = "공고 상세조회", notes = "공고 하나를 조회한다.")
    public ResponseEntity<?> getJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDto JobOpening = jobOpeningService.getJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);

    }

    //지원하기
    @PostMapping("/{jobOpeningId}/apply")
    @ApiOperation(value = "지원하기", notes = "공고에 지원한다.")
    public ResponseEntity<?> applyJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId, @LoginUser User user){
        JobOpeningProcess jobOpeningProcess = JobOpeningProcess.서류심사중;
        ApplyDto applyDto = new ApplyDto(jobOpeningProcess);

        ApplyDto Apply = jobOpeningService.applyJobOpening(user, jobOpeningId, applyDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Apply);
    }

    //지원이력 조회
    @GetMapping("/apply")
    @ApiOperation(value = "지원이력 조회", notes = "지원이력을 조회한다.")
    public ResponseEntity<?> getApplyList(@LoginUser User user){
        List<ApplyDto> ApplyList = jobOpeningService.getApplyList(user);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //지원이력 상세조회
    @GetMapping("/apply/{applyId}")
    @ApiOperation(value = "지원이력 상세조회", notes = "지원이력을 상세조회한다.")
    public ResponseEntity<?> getApplyList(@LoginUser User user, @PathVariable("applyId")Long applyId){
        ApplyDto Apply = jobOpeningService.getApply(user, applyId);

        return ResponseEntity.status(HttpStatus.OK).body(Apply);
    }

    //공고 조건 생성(지역, 직무 제외)
    @PostMapping("/condition")
    @ApiOperation(value = "공고 조건생성", notes = "조건검색에 이용한 조건을 만든다.")
    public ResponseEntity<?> postSearchCondition(@LoginUser User user, @RequestBody SearchConditionDto searchConditionDto){
        SearchConditionDto SearchCondition = jobOpeningService.postSearchCondition(user, searchConditionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(SearchCondition);
    }

    //공고 조건 희망지역 설정
    @PostMapping("/condition/{searchConditionId}/hopearea")
    @ApiOperation(value = "희망지역 조건 생성(다중선택)", notes = "희망하는 지역을 등록한다(다중)")
    public ResponseEntity<?> postHopeArea(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeAreaDto hopeAreaDto){
        jobOpeningService.postHopeArea(searchConditionId, hopeAreaDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    @PutMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    @ApiOperation(value = "희망지역 조건 수정", notes = "희망하는 지역을 수정한다.")
    //공고 조건 희망지역 수정
    public ResponseEntity<?> updateHopeArea(@PathVariable("searchConditionId")Long searchConditionId,
                                            @RequestBody HopeAreaDto hopeAreaDto,
                                            @PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.updateHopeArea(searchConditionId,hopeAreaDto,hopeAreaId);

        return ResponseEntity.status(HttpStatus.OK).body("성공~");
    }

    //공고 조건 희망지역 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    @ApiOperation(value = "희망지역 조건 삭제", notes = "희망하는 지역을 삭제한다.")
    public ResponseEntity<?> deleteHopeArea(@PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.deleteHopeArea(hopeAreaId);

        return ResponseEntity.status(HttpStatus.OK).body("성공~");
    }

    //공고 조건 희망직무 설정
    @PostMapping("/condition/{searchConditionId}/hopejob")
    @ApiOperation(value = "희망직무 조건 생성(다중선택)", notes = "희망하는 직무를 등록한다.(다중선택)")
    public ResponseEntity<?> postHopeJob(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeJobDto hopeJobDto){
        jobOpeningService.postHopeJob(searchConditionId,hopeJobDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 희망지역 수정
    @PutMapping("/condition/{searchConditionId}/hopejob/{hopeJobId}")

    @ApiOperation(value = "희망직무 조건 수정", notes = "희망하는 직무를 수정한다.")
    public ResponseEntity<?> updateHopeJob(@PathVariable("searchConditionId")Long searchConditionId,
                                           @RequestBody HopeJobDto hopeJobDto,
                                           @PathVariable("hopeJobId")Long hopeJobId){
        jobOpeningService.updateHopeJob(searchConditionId,hopeJobDto,hopeJobId);
        return ResponseEntity.status(HttpStatus.OK).body("성공~");
    }

    //공고 조건 희망지역 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopejob/{hopeJobId}")
    @ApiOperation(value = "희망직무 조건 삭제", notes = "희망하는 직무를 삭제한다.")
    public ResponseEntity<?> deleteHopeJob(@PathVariable("hopeJobId")Long hopeJobId){
        jobOpeningService.deleteHopeJob(hopeJobId);
        return ResponseEntity.status(HttpStatus.OK).body("성공~");
    }

    //공고 조건 조회
    @GetMapping("/condition")
    @ApiOperation(value = "공고조건 조회", notes = "공고 조건들을 조회한다.")
    public ResponseEntity<?> getSearchConditionList(@LoginUser User user){
        List<SearchConditionDto> searchConditionList = jobOpeningService.getSearchConditionList(user);

        return ResponseEntity.status(HttpStatus.OK).body(searchConditionList);
    }

    //공고 조건 상세조회
    @GetMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 상세조회", notes = "공고조건 하나를 상세조회한다.")
    public ResponseEntity<?> getSearchCondition(@PathVariable("searchConditionId")Long searchConditionId){
        SearchConditionDto searchCondition = jobOpeningService.getSearchCondition(searchConditionId);
        return ResponseEntity.status(HttpStatus.OK).body(searchCondition);
    }

    //공고 조건 수정
    @PutMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 수정", notes = "공고 조건을 수정한다.")
    public ResponseEntity<?> updateSearchCondition(@LoginUser User user,
                                                   @PathVariable("searchConditionId")Long searchConditionId,
                                                   @RequestBody SearchConditionDto searchConditionDto){
        jobOpeningService.updateSearchCondition(user,searchConditionId,searchConditionDto);

        return ResponseEntity.status(HttpStatus.OK).body("성공~");
    }

    //공고 조건 삭제
    @DeleteMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 삭제", notes = "공고 조건을 삭제한다.")
    public ResponseEntity<?> deleteSearchCondition( @PathVariable("searchConditionId")Long searchConditionId){
        jobOpeningService.deleteSearchCondition(searchConditionId);
        return ResponseEntity.status(HttpStatus.OK).body("성공~");
    }




}
