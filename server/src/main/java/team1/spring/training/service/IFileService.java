package team1.spring.training.service;

import org.springframework.web.multipart.MultipartFile;
import team1.spring.training.models.File;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public interface IFileService {
    List<File> getAllFiles();

    File getFileByName(String name) throws FileNotFoundException;

    void addFile(MultipartFile file) throws FileAlreadyExistsException;

    void updateFile(MultipartFile file);

    void deleteFile(String name) throws FileNotFoundException;
}