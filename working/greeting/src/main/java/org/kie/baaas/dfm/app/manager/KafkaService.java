package org.kie.baaas.dfm.app.manager;


import io.quarkus.security.credential.Credential;

public interface KafkaService {
    /**
     * This method connects to Kafka service, and creates/replaces service account.
     * The credential of the service account is collected and stored in a vault.
     */
    Credential getCustomerCredential(String customerId);
}
