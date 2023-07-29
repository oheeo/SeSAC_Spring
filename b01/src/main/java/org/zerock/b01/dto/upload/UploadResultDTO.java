package org.zerock.b01.dto.upload;
// 여러 개의 파일이 업로드 되면 업로드 결과도 여러 개 발생하고 여러 정보를 반환해야 하므로 별도의 DTO를 구성해서 반환

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 업로드 파일의 UUID 값과 파일 이름(FileName), 이미지 여부를 객체로 구성하고 getLink()를 통해서 첨부파일의 경로 처리에 사용
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

    private String uuid;

    private String fileName;

    private boolean img;

    // JSON으로 처리될 때는 link라는 속성으로 자동 처리
    public String getLink() {

        if(img) {
            return "s_"+ uuid +"_"+fileName;  // 이미지인 경우 섬네일
        } else {
            return uuid+"_"+fileName;
        }
    }
}
