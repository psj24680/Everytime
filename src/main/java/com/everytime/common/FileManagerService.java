package com.everytime.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {

  public final static String FILE_UPLOAD_PATH =
      "D:\\web-study\\clone-everytime\\workspace\\images/";

  /**
   * userLoginId로 디렉토리명 만들고 그 디렉토리에 file 저장
   * 
   * @param userLoginId
   * @param file
   * @return
   */
  public String saveFile(String userLoginId, MultipartFile file) {
    // 디렉토리(폴더)명 - 유저 로그인 아이디_현재 시간/
    String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";

    // 파일 경로: ex) D:\\web-study\\clone-everytime\\workspace\\images/test2_1658475667594
    String filePath = FILE_UPLOAD_PATH + directoryName;

    // 디렉토리 생성
    File directory = new File(filePath);
    if (directory.mkdir() == false) {
      return null;
    }

    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(filePath, file.getOriginalFilename());
      Files.write(path, bytes);

      // 이미지 업로드 성공 시
      return "/images/" + directoryName + file.getOriginalFilename();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public void deleteFile(String imagePath) throws IOException {
    imagePath = imagePath.replace("/images/", "");
    Path path = Paths.get(FILE_UPLOAD_PATH + imagePath);
    if (Files.exists(path)) { // 이미지 파일이 있으면 삭제
      Files.delete(path);
    }

    // 디렉토리(폴더) 삭제
    path = path.getParent();
    if (Files.exists(path)) { // 폴더가 있으면 삭제
      Files.delete(path);
    }
  }

}
