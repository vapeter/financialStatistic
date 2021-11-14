package eu.vargapeter.financialstatistics.controller;

import eu.vargapeter.financialstatistics.exception.ImportFileFormatNotValidException;
import eu.vargapeter.financialstatistics.model.BankAccount;
import eu.vargapeter.financialstatistics.service.imports.ImportFromFileService;
import eu.vargapeter.financialstatistics.exception.StorageFileNotFoundException;
import eu.vargapeter.financialstatistics.service.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class FileUploadController {

    @Autowired
    ImportFromFileService importFromFileService;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/importNewTransaction")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   @ModelAttribute BankAccount bankAccount,
                                   @ModelAttribute("bankImportType") String bankImportType) {

        //save file
        storageService.store(file);

        log.debug("File upload controller received bankAccount: " + bankAccount + ", bankImportType: " + bankImportType);

        // import from file
        try {
            if (bankImportType.equals("UniCredit")) {
                importFromFileService.importFromHSSFExcel(file.getOriginalFilename(), bankAccount, bankImportType);
            } else {
                importFromFileService.importFromXSSFExcel(file.getOriginalFilename(), bankAccount, bankImportType);
            }

        } catch (ImportFileFormatNotValidException ex) {
            redirectAttributes.addFlashAttribute("error",
                    ex.getMessage());
        }

        redirectAttributes.addFlashAttribute("message",
                "The file was uploaded successfully: " + file.getOriginalFilename() + "!");

        return "redirect:/transactions";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
