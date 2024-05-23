package dev.reddy.olm.service.impl;

import dev.reddy.olm.entity.Credentials;
import dev.reddy.olm.repository.CredentialRepository;
import dev.reddy.olm.service.CredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public void saveCredentials(Credentials credentials) {
        credentialRepository.save(credentials);
    }
}
