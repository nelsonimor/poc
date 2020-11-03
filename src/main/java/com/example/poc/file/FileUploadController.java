package com.example.poc.file;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {
   @Autowired
   private FileStorageService fileStorageService;
   @Autowired
   FileDAO fileDAO;

   @PostMapping({"/uploadFile"})
   public Response uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
      String fileName = this.fileStorageService.storeFile(file);
      String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
      FileBO fileBO = new FileBO();
      fileBO.setFilename(fileName);
      fileBO.setFiletype(type);
      this.fileDAO.save(fileBO);
      return new Response(fileName, fileDownloadUri, file.getContentType(), file.getSize());
   }

   @PostMapping({"/uploadMultipleFiles"})
   public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
      return (List)Arrays.asList(files).stream().map((file) -> {
         return this.uploadFile(file, (String)null);
      }).collect(Collectors.toList());
   }
}
