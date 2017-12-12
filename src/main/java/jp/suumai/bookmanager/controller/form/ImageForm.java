package jp.suumai.bookmanager.controller.form;

import lombok.Data;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageForm {

	private List<MultipartFile> file;
	private String fileName;

}
