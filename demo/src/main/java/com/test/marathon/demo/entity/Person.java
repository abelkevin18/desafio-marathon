package com.test.marathon.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "persona")
@Accessors(chain = true)
@Data
public class Person {

  @MongoId(FieldType.OBJECT_ID)
  private String id;

  @Field
  private String ruc;

  @Field
  @JsonProperty("razon_social")
  private String razonSocial;
  @Field
  private String estado;
  @Field
  private String direccion;
  @Field
  private String ubigeo;
  @Field
  private String departamento;
  @Field
  private String provincia;
  @Field
  private String distrito;

}
