package team1.spring.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team1.spring.training.models.File;
import team1.spring.training.service.IFileService;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

@RestController
public class FileController {
    public static final String FUNCTION_PATH = "files";

    @Autowired
    private IFileService fileService;

    @GetMapping(FileController.FUNCTION_PATH + "/{name}")
    public ResponseEntity<File> getFileByName(@PathVariable("name") String name) {
        File file = null;
        try {
            file = fileService.getFileByName(name);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<File>(file, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(FileController.FUNCTION_PATH)
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> list = null;
        try{
            list = fileService.getAllFiles();
        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<List<File>>(list, HttpStatus.OK);
    }

    @PostMapping(FileController.FUNCTION_PATH)
    public ResponseEntity<Void> addFile(@RequestBody MultipartFile file) {
        try {
            fileService.addFile(file);
        } catch (FileAlreadyExistsException e) {
            // should user know the reason? that he already have that file
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping(FileController.FUNCTION_PATH)
    public ResponseEntity<File> updateFile(@RequestBody MultipartFile file) {
        fileService.updateFile(file);
        return new ResponseEntity<File>( HttpStatus.OK);
    }

    @DeleteMapping(FileController.FUNCTION_PATH + "/{name}")
    public ResponseEntity<Void> deleteFile(@PathVariable("name") String name) {
        try {
            fileService.deleteFile(name);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
