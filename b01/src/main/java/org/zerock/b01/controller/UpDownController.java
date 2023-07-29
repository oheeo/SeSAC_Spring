package org.zerock.b01.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.upload.UploadFileDTO;
import org.zerock.b01.dto.upload.UploadResultDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 파일 업로드와 파일을 보여주는 기능을 메소드로 처리
@RestController
@Log4j2
public class UpDownController {

    @Value("${org.zerock.upload.path}") // import 시에 springframework으로 시작하는 Value
    private String uploadPath;

    @ApiOperation(value = "Upload POST", notes = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {

        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() != null) {
            
            final List<UploadResultDTO> list = new ArrayList<>();  // List<UploadResultDTO>를 반환

            uploadFileDTO.getFiles().forEach(multipartFile -> {

                String originalName = multipartFile.getOriginalFilename();
                log.info(originalName);

                String uuid = UUID.randomUUID().toString();

                Path savePath = Paths.get(uploadPath, uuid+"_"+originalName);
                // 파일명 중복을 해결하고자 'UUID(16자리)+언더바+원래 파일명'

                boolean image = false;

                try {
                    multipartFile.transferTo(savePath);  // 실제 파일 저장

                    // 이미지 파일의 종류라면
                    if(Files.probeContentType(savePath).startsWith("image")) {

                        // 이미지 파일명 앞에 's_'로 시작하는 섬네일 파일 추가 생성 (원본, 섬네일 두개 생성)
                        File thumbFile = new File(uploadPath, "s_" + uuid+"_"+ originalName);

                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200,200);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .fileName(originalName)
                        .img(image).build()
                );

            });  // end each

        }  // end if

        return null;
    }

}
