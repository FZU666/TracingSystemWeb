package com.psf.core.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

	void init(List<Path> locations);

	String store(MultipartFile file, Path location);

	String storeImage(MultipartFile file);

	Stream<Path> loadAll(Path location);

	Path load(String filename, Path location);

	Resource loadAsResource(String filename, Path location);

	int deleteFile(Path file);

	void deleteAll(Path location);
}
