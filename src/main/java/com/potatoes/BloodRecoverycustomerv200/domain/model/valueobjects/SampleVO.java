package com.potatoes.BloodRecoverycustomerv200.domain.model.valueobjects;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class SampleVO {
    private String sampleDataStr;

    protected SampleVO() {

    }

    public SampleVO(String sampleDataStr) {
        this.sampleDataStr = sampleDataStr;
    }
}
