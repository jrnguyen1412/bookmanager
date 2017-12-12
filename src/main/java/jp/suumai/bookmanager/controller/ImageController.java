package jp.suumai.bookmanager.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.suumai.bookmanager.controller.form.ImageForm;


@Controller
public class ImageController {

    //Save the uploaded file to this folder
    private final Path rootFolder = Paths.get("src/main/webapp/images");

    @GetMapping("/image")
    public String index(Model model) {
    	// New object form to transfer data from browser
		ImageForm imageForm = new ImageForm();

		model.addAttribute("imageForm", imageForm);

        return "/image/upload";
    }

    @PostMapping("/image/upload") // //new annotation since 4.3
    public String singleFileUpload(@ModelAttribute("imageForm") ImageForm imageForm, RedirectAttributes redirectAttributes) {

    	List<MultipartFile> files = imageForm.getFile();

        if (files == null || files.size() == 0) {
        	redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
        	return "redirect:uploadStatus";
        }

        try {

        	DateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_");
        	Calendar cal = Calendar.getInstance();
        	List<String> filesName = new ArrayList<String>();
        	
        	for (MultipartFile file : files) {
        		StringBuilder fileName = new StringBuilder(sdf.format(cal.getTime())).append(file.getOriginalFilename());

        		Files.copy(file.getInputStream(), this.rootFolder.resolve(fileName.toString()));
        		filesName.add(fileName.toString());
        	}
        	
        	redirectAttributes.addFlashAttribute("message", "You successfully uploaded.");
        	//filesName = null;
    		redirectAttributes.addFlashAttribute("files", filesName);

        } catch (IOException e) {
        	redirectAttributes.addFlashAttribute("message", "Image doesn't successfully uploaded.");
            e.printStackTrace();
        }

        return "redirect:/image/uploadStatus";
    }

    @GetMapping("/image/uploadStatus")
    public String uploadStatus() {

        return "/image/uploadStatus";
    }

}