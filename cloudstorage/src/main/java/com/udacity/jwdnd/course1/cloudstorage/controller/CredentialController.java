package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.data.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.view.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;


@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    @Autowired
    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    // add credential
    @PutMapping("/add")
    public String addCredential(CredentialForm credentialForm, Model model, HttpSession session) {
        // gather the user id using HttpSession
        int userId = (int)session.getAttribute("userId");
        // create credential through the credential form
        // public Credential(int credentialId, String url, String username, String password)
        Credential credential = new Credential(credentialForm.getCredentialId(), credentialForm.getUrl(), credentialForm.getUsername(), credentialForm.getPassword());
        this.credentialService.insert(credential);
        // insert the credential
        credentialService.insert(credential);

        // update model attributes to reflect changes
        model.addAttribute("credentialForm", credentialForm);
        return "result";
    }

    // delete cred
    @GetMapping("/delete")
    public String deleteCredential(@RequestParam(name="credentialId") String credentialId, Model model) {
        int deleteStatus = this.credentialService.delete(Integer.parseInt(credentialId));
        // conditional to determine result success
        if(deleteStatus == 1){
            model.addAttribute("successResult", true);
        } else {
            model.addAttribute("errorResult", true);
        }
        return "result";
    }
}
