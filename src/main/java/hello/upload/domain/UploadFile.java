package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

	private String uploadFileName; //업로드한 파일명
	private String storeFileName; //같은 파일명일 수 있기 때문

	public UploadFile(String uploadFileName, String storeFileName) {
		this.uploadFileName = uploadFileName;
		this.storeFileName = storeFileName;
	}
}
