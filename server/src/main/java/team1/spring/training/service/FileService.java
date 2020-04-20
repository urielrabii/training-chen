package team1.spring.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team1.spring.training.models.File;
import team1.spring.training.repository.FileRepository;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FileService implements IFileService {

    @Value("${service.formatter-Pattern:yyyy-MM-dd hh:mm:ss}")
    private String formatterPattern;

    private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Value("${service.directory-name:C:\\upload\\}")
    private String directoryName;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<File> getAllFiles() throws NullPointerException{
        if(fileRepository.findAll().isEmpty()){
            throw new NullPointerException();
        }
        return fileRepository.findAll();
    }

    @Override
    public File getFileByName(String name) throws FileNotFoundException {
        if(fileRepository.findByName(name).isEmpty()){
            throw new FileNotFoundException();
        }
        return fileRepository.findByName(name).get(0);
    }

    @Override
    public void addFile(MultipartFile multipartFile) throws FileAlreadyExistsException {
       File fileToAdd = convertMultiFileToFile(multipartFile);
        List<File> allFiles = fileRepository.findAll();
       for(File file : allFiles) {
           if(file.getName().equals(fileToAdd.getName())) {
                throw new FileAlreadyExistsException(fileToAdd.getName());
           }
       }
        fileRepository.save(fileToAdd);
    }

    @Override
    public void updateFile(MultipartFile multipartFile) {
        fileRepository.save(convertMultiFileToFile(multipartFile));
    }

    private File convertMultiFileToFile(MultipartFile multipartFile) {
       return new File(multipartFile.getOriginalFilename(),directoryName + multipartFile.getOriginalFilename(),
               LocalDateTime.now().format(formatter), multipartFile.getSize());
    }

    @Override
    public void deleteFile(String name) throws FileNotFoundException {
        if(fileRepository.findByName(name).isEmpty()){
            throw new FileNotFoundException();
        }
        fileRepository.delete(fileRepository.findByName(name));
    }
}