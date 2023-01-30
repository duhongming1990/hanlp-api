package com.github.hanlp.api.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class RecognizeNameVO implements Serializable {
    Set<String> names;
    Set<String> places;
    Set<String> organizations;

    public RecognizeNameVO() {
        this.names = new HashSet<>();
        this.places = new HashSet<>();
        this.organizations = new HashSet<>();
    }

    public void addAllNames(Set<String> data) {
        this.names.addAll(data);
    }

    public void addAllPlaces(Set<String> data) {
        this.places.addAll(data);
    }

    public void addAllOrganizations(Set<String> data) {
        this.organizations.addAll(data);
    }
}
