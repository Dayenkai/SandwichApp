package com.source.sandwich_app.Model;

import java.io.Serializable;

public class Sandwich  implements Serializable {
    private     String  name;
    private     String  prix;
    private     String  pain;
    private     String  url;
    private     String  provenance;

    public String getName() {
        return name;
    }

    public String getPrix() {
        return prix;
    }

    public String getPain() {
        return pain;
    }

    public String getUrl() {
        return url;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setPain(String pain) {
        this.pain = pain;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }
}
