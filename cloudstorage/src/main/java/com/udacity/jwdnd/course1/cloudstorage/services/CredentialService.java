package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.data.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    @Autowired
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int insert(Credential credential) {
        // password needs to be encrypted then it can be stored
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return this.credentialMapper.insert(credential);
    }

    public List<Credential> getAll(Integer userId) {
        return this.credentialMapper.getAll(userId);
    }
    public Credential getCredByCredId(int credentialid) {
        return this.credentialMapper.getCredByCredId(credentialid);
    }

    public int update(Credential credential) {
        // check to see if there is a key associated with credential
        if(credential.getKey() == null || credential.getKey() == "") {
            String key = this.credentialMapper.getKey(credential.getCredentialId());
            // set the password to using encryption service
            credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        }
        return this.credentialMapper.update(credential);
    }

    public int delete(Integer credentialid) {
        return this.credentialMapper.delete(credentialid);
    }
    public int deleteAll() {
        return this.credentialMapper.deleteAll();
    }
}
