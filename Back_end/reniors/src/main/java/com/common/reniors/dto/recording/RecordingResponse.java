package com.common.reniors.dto.recording;

import com.common.reniors.domain.entity.recording.Recording;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordingResponse {
    @NotNull
    Long recodingId;

    @NotBlank
    String fileName;

    @NotBlank
    String recordeURL;

    @NotNull LocalDateTime updatedAt;

    public static RecordingResponse response(Recording recording){
        return new RecordingResponse(recording.getId(), recording.getFileName(), recording.getRecordeURL(), recording.getCreatedAt());
    }
}
