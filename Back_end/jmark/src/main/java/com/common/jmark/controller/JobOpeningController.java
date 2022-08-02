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
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;

    //채용공고 조회(조건포함)
    @GetMapping("/conditionlist")
    public ResponseEntity<?> getJobOpeningList(@RequestBody SearchConditionDto searchConditionDto, Pageable pageable){
        Page<JobOpeningDto> JobOpeningList = jobOpeningService.getJobOpeningConditionList(searchConditionDto, pageable);
//        List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(x->new JobOpeningDto(x)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(JobOpeningList);
    }


    //채용공고 상세조회
    @GetMapping("/{jobOpeningId}")
    public ResponseEntity<?> getJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDto JobOpening = jobOpeningService.getJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(JobOpening);

    }

    //지원하기
    @PostMapping("/{jobOpeningId}/apply")
    public ResponseEntity<?> applyJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId, @LoginUser User user){
        JobOpeningProcess jobOpeningProcess = JobOpeningProcess.서류심사중;
        ApplyDto applyDto = new ApplyDto(jobOpeningProcess);

        ApplyDto Apply = jobOpeningService.applyJobOpening(user, jobOpeningId, applyDto);

        return ResponseEntity.status(HttpStatus.OK).body(Apply);
    }

    //지원이력 조회
    @GetMapping("/apply")
    public ResponseEntity<?> getApplyList(@LoginUser User user){
        List<ApplyDto> ApplyList = jobOpeningService.getApplyList(user);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //지원이력 상세조회
    @GetMapping("/apply/{applyId}")
    public ResponseEntity<?> getApplyList(@LoginUser User user, @PathVariable("applyId")Long applyId){
        ApplyDto Apply = jobOpeningService.getApply(user, applyId);

        return ResponseEntity.status(HttpStatus.OK).body(Apply);
    }

    //공고 조건 생성(지역, 직무 제외)
    @PostMapping("/condition")
    public ResponseEntity<?> postSearchCondition(@LoginUser User user, @RequestBody SearchConditionDto searchConditionDto){
        SearchConditionDto SearchCondition = jobOpeningService.postSearchCondition(user, searchConditionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(SearchCondition);
    }

    //공고 조건 희망지역 설정
    @PostMapping("/condition/{searchConditionId}/hopearea")
    public ResponseEntity<?> postHopeArea(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeAreaDto hopeAreaDto){
        jobOpeningService.postHopeArea(searchConditionId, hopeAreaDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    @PutMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    //공고 조건 희망지역 수정
    public ResponseEntity<?> updateHopeArea(@PathVariable("searchConditionId")Long searchConditionId,
                                            @RequestBody HopeAreaDto hopeAreaDto,
                                            @PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.updateHopeArea(searchConditionId,hopeAreaDto,hopeAreaId);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 희망지역 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    public ResponseEntity<?> deleteHopeArea(@PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.deleteHopeArea(hopeAreaId);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 희망직무 설정
    @PostMapping("/condition/{searchConditionId}/hopejob")
    public ResponseEntity<?> postHopeJob(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeJobDto hopeJobDto){
        jobOpeningService.postHopeJob(searchConditionId,hopeJobDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 희망지역 수정
    @PutMapping("/condition/{searchConditionId}/hopejob/{hopeJobId}")
    public ResponseEntity<?> updateHopeJob(@PathVariable("searchConditionId")Long searchConditionId,
                                           @RequestBody HopeJobDto hopeJobDto,
                                           @PathVariable("hopeJobId")Long hopeJobId){
        jobOpeningService.updateHopeJob(searchConditionId,hopeJobDto,hopeJobId);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 희망지역 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopejob/{hopeJobId}")
    public ResponseEntity<?> deleteHopeJob(@PathVariable("hopeJobId")Long hopeJobId){
        jobOpeningService.deleteHopeJob(hopeJobId);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    @GetMapping("/condition")
    //공고 조건 조회
    public ResponseEntity<?> getSearchConditionList(@LoginUser User user){
        List<SearchConditionDto> searchConditionList = jobOpeningService.getSearchConditionList(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(searchConditionList);
    }

    //공고 조건 수정
    @PutMapping("/condition/{searchConditionId}")
    public ResponseEntity<?> updateSearchCondition(@LoginUser User user,
                                                   @PathVariable("searchConditionId")Long searchConditionId,
                                                   @RequestBody SearchConditionDto searchConditionDto){
        jobOpeningService.updateSearchCondition(user,searchConditionId,searchConditionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 삭제
    @DeleteMapping("/condition/{searchConditionId}")
    public ResponseEntity<?> deleteSearchCondition( @PathVariable("searchConditionId")Long searchConditionId){
        jobOpeningService.deleteSearchCondition(searchConditionId);
        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
    }

    //공고 조건 상세조회
    @GetMapping("/condition/{searchConditionId}")
    public ResponseEntity<?> getSearchCondition(@PathVariable("searchConditionId")Long searchConditionId){
        SearchConditionDto searchCondition = jobOpeningService.getSearchCondition(searchConditionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(searchCondition);
    }

    //공고 전체조회
    @GetMapping()
    public ResponseEntity<?> getJobOpening(Pageable pageable){
        Page<JobOpeningDto> jobOpeningList = jobOpeningService.getJobOpening(pageable);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOpeningList);
    }
}
