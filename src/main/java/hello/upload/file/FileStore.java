package hello.upload.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hello.upload.domain.UploadFile;

@Component
public class FileStore {

	@Value("${file.dir}")
	private String fileDir;

	public String getFullPath(String filename){
		//파일이름을 받아서 전체경로를 반환하는 메서드
		return fileDir+filename;
	}

	public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<UploadFile> storeFileResult = new ArrayList<>();
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()){
				UploadFile uploadFile = storeFile(multipartFile);
				storeFileResult.add(uploadFile);
			}
		}
		return storeFileResult;
	}

	public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.isEmpty()){
			return null;
		}

		String originalFilename = multipartFile.getOriginalFilename();
		//확장자 꺼내기
		String ext = extracted(originalFilename);

		//서버에 저장하는 파일명
		String uuid = UUID.randomUUID().toString();
		String storeFileName = uuid + "." + ext;

		multipartFile.transferTo(new File(getFullPath(storeFileName)));

		return new UploadFile(originalFilename, storeFileName);
	}

	//확장자 꺼내는 메서드
	private String extracted(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		return originalFilename.substring(pos + 1);
	}
}
