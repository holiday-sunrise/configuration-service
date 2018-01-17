package org.mule.modules.springcloudconfig.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ApplicationConfigurationBuilder {
    private String name;
    private String version;
    private String environment;
    private Map<String, String> properties;
    private List<ApplicationConfiguration> parents;
    private List<ApplicationDocument> documents;

    public ApplicationConfigurationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ApplicationConfigurationBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public ApplicationConfigurationBuilder setEnvironment(String environment) {
        this.environment = environment;
        return this;
    }

    public ApplicationConfigurationBuilder setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public ApplicationConfigurationBuilder setParents(List<ApplicationConfiguration> parents) {
        this.parents = parents;
        return this;
    }

    public ApplicationConfigurationBuilder setDocuments(List<ApplicationDocument> documents) {
        this.documents = documents;
        return this;
    }

    public ApplicationConfiguration build() {
        return new ApplicationConfiguration(name, version, environment,
                Collections.unmodifiableMap(properties),
                Collections.unmodifiableList(parents),
                Collections.unmodifiableList(documents));
    }

    public ApplicationConfigurationBuilder parent(ApplicationConfiguration config) {

        synchronized (this) {
            if (parents == null) {
                parents = new LinkedList<>();
            }
        }

        parents.add(config);

        return this;
    }

    public ApplicationConfigurationBuilder document(ApplicationDocument document) {
        synchronized (this) {
            if (documents == null) {
                documents = new LinkedList<>();
            }
        }

        documents.add(document);

        return this;
    }
}