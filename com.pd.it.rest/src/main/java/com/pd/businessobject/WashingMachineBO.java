package com.pd.businessobject;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.pd.standard.itf.IIdentity;

import lombok.Data;

@Data
@Document(collection = "WashingMachine")
public class WashingMachineBO implements Serializable, IIdentity<String> {
    private String id;
    private String source;
    private String data;

}
