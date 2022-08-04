package com.common.jmark.controller;

import com.common.jmark.common.config.web.LoginUser;
import com.common.jmark.domain.entity.Enum.JobOpeningProcess;
import com.common.jmark.domain.entity.user.User;
import com.common.jmark.dto.*;
import com.common.jmark.dto.Apply.ApplyResponse;
import com.common.jmark.dto.JobOpening.*;
import com.common.jmark.service.JobOpeningService;
import com.common.jmark.service.bookmark.BookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobopening")
@RequiredArgsConstructor
@Api(tags={"공고 API"})
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;
    private final BookmarkService bookmarkService;

    //채용공고 조회(조건포함)
    @GetMapping("/search/{searchConditionId}")
    @ApiOperation(value = "공고 조회(조건포함)", notes = "조건검색을 통해 공고를 조회한다.")
    public ResponseEntity<?> getJobOpeningList(@PathVariable("searchConditionId")Long searchConditionId, Pageable pageable){
        Page<JobOpeningResponse> jobOpeningConditionList = jobOpeningService.getJobOpeningConditionList(searchConditionId, pageable);
//        List<JobOpeningDto> jobOpeningDtoList = jobOpeningList.stream().map(x->new JobOpeningDto(x)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningConditionList);
    }

    //공고 전체조회
    @GetMapping("/search")
    @ApiOperation(value = "공고 조회", notes = "전체 공고를 조회한다.")
    public ResponseEntity<?> getJobOpening(Pageable pageable){
        Page<JobOpeningResponse> jobOpeningList = jobOpeningService.getJobOpening(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //채용공고 상세조회
    @GetMapping("/{jobOpeningId}")
    @ApiOperation(value = "공고 상세조회", notes = "공고 하나를 조회한다.")
    public ResponseEntity<?> getJobOpening(@PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDetailResponse jobOpening = jobOpeningService.getJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(jobOpening);

    }

    //지원하기
    @PostMapping("/{jobOpeningId}/apply")
    @ApiOperation(value = "지원하기", notes = "공고에 지원한다.")
    public ResponseEntity<?> applyJobOpening( @PathVariable("jobOpeningId") Long jobOpeningId, @LoginUser User user){

        Long applyId = jobOpeningService.applyJobOpening(user, jobOpeningId);

        return ResponseEntity.status(HttpStatus.CREATED).body(applyId);
    }

    //지원이력 조회
    @GetMapping("/apply")
    @ApiOperation(value = "지원이력 조회", notes = "지원이력을 조회한다.")
    public ResponseEntity<?> getApplyList(@ApiIgnore @LoginUser User user){
        List<ApplyResponse> ApplyList = jobOpeningService.getApplyList(user);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //지원이력 상세조회
    @GetMapping("/apply/{applyId}")
    @ApiOperation(value = "지원이력 상세조회", notes = "지원이력을 상세조회한다.")
    public ResponseEntity<?> getApplyList(@ApiIgnore @LoginUser User user, @PathVariable("applyId")Long applyId){
        ApplyResponse apply = jobOpeningService.getApply(user, applyId);

        return ResponseEntity.status(HttpStatus.OK).body(apply);
    }

    //공고 조건 생성(지역, 직무 제외)
    @PostMapping("/condition")
    @ApiOperation(value = "공고 조건생성", notes = "조건검색에 이용한 조건을 만든다.")
    public ResponseEntity<?> postSearchCondition(@ApiIgnore @LoginUser User user, @RequestBody SearchConditionCreateRequest searchConditionCreateRequest){
        Long jobOpeningId = jobOpeningService.postSearchCondition(user, searchConditionCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOpeningId);
    }

    //공고 조건 희망지역 설정
    @PostMapping("/condition/{searchConditionId}/hopearea")
    @ApiOperation(value = "희망지역 조건 생성(다중선택)", notes = "희망하는 지역을 등록한다(다중)")
    public ResponseEntity<?> postHopeArea(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeAreaCreateRequest hopeAreaCreateRequest){
        Long hopeAreaId = jobOpeningService.postHopeArea(searchConditionId, hopeAreaCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(hopeAreaId);
    }

    @PutMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    @ApiOperation(value = "희망지역 조건 수정", notes = "희망하는 지역을 수정한다.")
    //공고 조건 희망지역 수정
    public ResponseEntity<?> updateHopeArea(@PathVariable("searchConditionId")Long searchConditionId,
                                            @RequestBody HopeAreaUpdateRequest hopeAreaUpdateRequest,
                                            @PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.updateHopeArea(searchConditionId, hopeAreaUpdateRequest,hopeAreaId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //공고 조건 희망지역 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    @ApiOperation(value = "희망지역 조건 삭제", notes = "희망하는 지역을 삭제한다.")
    public ResponseEntity<?> deleteHopeArea(@PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.deleteHopeArea(hopeAreaId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

//    //공고 조건 희망직무 설정
//    @PostMapping("/condition/{searchConditionId}/hopejob")
//    @ApiOperation(value = "희망직무 조건 생성(다중선택)", notes = "희망하는 직무를 등록한다.(다중선택)")
//    public ResponseEntity<?> postHopeJob(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeJobDto hopeJobDto){
//        jobOpeningService.postHopeJob(searchConditionId,hopeJobDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body("성공~");
//    }
//
//    //공고 조건 희망직무 수정
//    @PutMapping("/condition/{searchConditionId}/hopejob/{hopeJobId}")
//
//    @ApiOperation(value = "희망직무 조건 수정", notes = "희망하는 직무를 수정한다.")
//    public ResponseEntity<?> updateHopeJob(@PathVariable("searchConditionId")Long searchConditionId,
//                                           @RequestBody HopeJobDto hopeJobDto,
//                                           @PathVariable("hopeJobId")Long hopeJobId){
//        jobOpeningService.updateHopeJob(searchConditionId,hopeJobDto,hopeJobId);
//        return ResponseEntity.status(HttpStatus.OK).body("성공~");
//    }
//
//    //공고 조건 희망직무 삭제
//    @DeleteMapping("/condition/{searchConditionId}/hopejob/{hopeJobId}")
//    @ApiOperation(value = "희망직무 조건 삭제", notes = "희망하는 직무를 삭제한다.")
//    public ResponseEntity<?> deleteHopeJob(@PathVariable("hopeJobId")Long hopeJobId){
//        jobOpeningService.deleteHopeJob(hopeJobId);
//        return ResponseEntity.status(HttpStatus.OK).body("성공~");
//    }

    //공고 조건 조회
    @GetMapping("/condition")
    @ApiOperation(value = "공고조건 조회", notes = "공고 조건들을 조회한다.")
    public ResponseEntity<?> getSearchConditionList(@ApiIgnore @LoginUser User user){
        List<SearchConditionResponse> searchConditionList = jobOpeningService.getSearchConditionList(user);

        return ResponseEntity.status(HttpStatus.OK).body(searchConditionList);
    }

    //공고 조건 상세조회
    @GetMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 상세조회", notes = "공고조건 하나를 상세조회한다.")
    public ResponseEntity<?> getSearchCondition(@PathVariable("searchConditionId")Long searchConditionId){
        SearchConditionResponse searchCondition = jobOpeningService.getSearchCondition(searchConditionId);

        return ResponseEntity.status(HttpStatus.OK).body(searchCondition);
    }

    //공고 조건 수정
    @PutMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 수정", notes = "공고 조건을 수정한다.")
    public ResponseEntity<?> updateSearchCondition(@ApiIgnore @LoginUser User user,
                                                   @PathVariable("searchConditionId")Long searchConditionId,
                                                   @RequestBody SearchConditionUpdateRequest searchConditionUpdateRequest){
        jobOpeningService.updateSearchCondition(user,searchConditionId, searchConditionUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //공고 조건 삭제
    @DeleteMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 삭제", notes = "공고 조건을 삭제한다.")
    public ResponseEntity<?> deleteSearchCondition( @PathVariable("searchConditionId")Long searchConditionId){
        jobOpeningService.deleteSearchCondition(searchConditionId);

        return ResponseEntity.status(HttpStatus.OK).body("success");

    }

    // 관심 공고 등록
    @PostMapping("/{userId}/bookmark/{jobOpenigId}")
    public ResponseEntity<?> createBookmark(
            @PathVariable Long userId,
            @PathVariable Long jobOpenigId
    ) {
        Long bookmarkId = bookmarkService.create(userId, jobOpenigId);
        Map<String, Long> response = new HashMap<>();
        response.put("bookmarkId", bookmarkId);
        return ResponseEntity.ok(response);
    }

    // 관심 공고 조회
    @GetMapping("/{userId}/bookmark")
    public ResponseEntity<?> readBookmarkList(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(bookmarkService.readList(userId));
    }

    // 관심 공고 상세 조회
    @GetMapping("/bookmark/{bookmarkId}")
    public ResponseEntity<?> readBookmark(
            @PathVariable Long bookmarkId
    ) {
        return ResponseEntity.ok(bookmarkService.read(bookmarkId));
    }

    // 관심 공고 해제
    @DeleteMapping("/bookmark/{bookmarkId}")
    public ResponseEntity<Map<String, Long>> deleteBookmark (
            @PathVariable Long bookmarkId
    ) {
        bookmarkService.delete(bookmarkId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
