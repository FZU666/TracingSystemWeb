package com.psf.core.web;

import com.psf.core.config.StorageProperties;
import com.psf.core.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author psf
 */
@Slf4j
@RestController
@RequestMapping("files")
public class FileUploadController {

	private final StorageService storageService;

	private final Path imageRootLocation;

	private final Path fileRootLocation;

	@Autowired
	public FileUploadController(StorageService storageService, StorageProperties properties) {

		this.storageService = storageService;
		this.imageRootLocation = Paths.get(properties.getImageLocation());
		this.fileRootLocation = Paths.get(properties.getFileLocation());
	}

	/**
	 * list all uploaded files(include images and other files)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<String>> listUploadedFiles() {

		List<String> imageList = storageService.loadAll(imageRootLocation).map(
			path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
				"serveImageFile", path.getFileName().toString()).build().toUri().toString())
			.collect(Collectors.toList());

		List<String> fileList = storageService.loadAll(fileRootLocation).map(
			path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
				"serveFile", path.getFileName().toString()).build().toUri().toString())
			.collect(Collectors.toList());

		imageList.addAll(fileList);
		return ResponseEntity.ok(imageList);
	}

	/**
	 * get server image file
	 */
	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename, fileRootLocation);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
			"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	/**
	 * get server other files
	 */
	@GetMapping("/images/{filename:.+}")
	public ResponseEntity<Resource> serveImageFile(@PathVariable("filename") String imageFilename) {

		Resource file = storageService.loadAsResource(imageFilename, imageRootLocation);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
			"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	/**
	 * upload only images
	 */
	@PostMapping("/upload/images")
	public ResponseEntity<Object> handleAvatarUpload(@RequestParam("image") MultipartFile file) {

		String url = storageService.store(file, imageRootLocation);
		log.info("You successfully uploaded " + file.getOriginalFilename() + "!");
		return ResponseEntity.ok().body(url);
		//return ResponseEntity.noContent().build();
	}

	/**
	 * upload all types of files
	 */
	@PostMapping("/upload")
	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file) {

		String url = storageService.store(file, fileRootLocation);
		log.info("You successfully uploaded " + file.getOriginalFilename() + "!");
		return ResponseEntity.ok().body(url);
		//return ResponseEntity.noContent().build();
	}
}
