package com.mashang.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Option {
    @JsonProperty("a")
    private String A;
    @JsonProperty("b")
    private String B;
    @JsonProperty("c")
    private String C;
    @JsonProperty("d")
    private String D;
}
