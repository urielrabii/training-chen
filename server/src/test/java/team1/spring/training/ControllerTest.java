package team1.spring.training;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import team1.spring.training.controllers.FileController;
import team1.spring.training.models.File;
import team1.spring.training.service.FileService;

import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class ControllerTest {

    @MockBean
    FileService fileService;

    @Autowired
    private MockMvc mvc;

    File chen = new File("chen.txt", "uploads//chen.txt","11.2.4444");

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void allFilesTest() throws Exception {
        List<File> files = Arrays.asList(chen);
        given(fileService.getAllFiles()).willReturn(files);

        mvc.perform(get("/api/files")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(chen.getName())));
    }

    @Test
    public void addFileTest() throws FileAlreadyExistsException, Exception{
        ResultMatcher ok = MockMvcResultMatchers.status().isCreated();
        MockMultipartFile multipartFile =
              new MockMultipartFile("file",
                                    "chchch.txt",
                                    "text/plain",
                                     "some xml".getBytes());

         MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
         MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/api/upload").file(multipartFile);
        mockMvc.perform(builder)
                .andExpect(ok)
                .andDo(MockMvcResultHandlers.print());
    }
}
