package com.common.reniors.controller;

import com.common.reniors.common.config.data.service.AwsS3Service;
import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.board.BoardCreateRequest;
import com.common.reniors.dto.recoding.RecodingCreateRequest;
import com.common.reniors.service.recoding.RecodingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.mail.Multipart;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/recoding")
@RequiredArgsConstructor
@Api(tags={"녹화본 API"})
public class RecodingController {
    private static final String baseURL = "https://reniors.s3.ap-northeast-2.amazonaws.com/";
    private final RecodingService recodingService;
    private final AwsS3Service awsS3Service;

    @PostMapping
    @ApiOperation(value = "녹화본 저장", notes = "녹화본을 저장한다.")
    public ResponseEntity<?> createRecoding(
            @ApiIgnore @LoginUser User user,
            @RequestPart(value = "recoding") MultipartFile file,
            @Valid @RequestPart(value = "data") RecodingCreateRequest request
    ){
        String recodeName = awsS3Service.uploadFile(file, "recode/");
        Long recodingId = recodingService.create(request, baseURL,"recode/"+recodeName, user);
        Map<String, Long> response = new HashMap<>();
        response.put("recodingId", recodingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    @ApiOperation(value = "녹화본 리스트 조회", notes = "저장한 녹화본들을 조회한다.")
    public ResponseEntity<?> getRecodingList(
            @ApiIgnore @LoginUser User user
    ){
        return ResponseEntity.ok(recodingService.getRecodingList(user));
    }

    @DeleteMapping("/{recodingId}")
    @ApiOperation(value = "녹화본을 삭제한다.", notes = "저장된 녹화본을 삭제한다.")
    public ResponseEntity<?> deleteRecoding(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long recodingId
    ){
        String recodeName = recodingService.delete(recodingId, user);
        awsS3Service.deleteFile(recodeName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
