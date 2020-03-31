package team1.spring.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team1.spring.training.models.File;
import team1.spring.training.models.User;
import team1.spring.training.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

   // public File getUserById(Strin fileId) {
     //   return userRepository.findOne(fileId);
   // }

    public boolean addFile(User user) {
 /**       File fileToAdd = convertMultiFileToFile(multipartFile);
        List<File> list = fileRepository.findByName(fileToAdd.getName());
        if (list.size() > 0) {
            return false;
        } else {
            fileRepository.save(fileToAdd);
            return true;
        } **/
 return true;
    }
}
