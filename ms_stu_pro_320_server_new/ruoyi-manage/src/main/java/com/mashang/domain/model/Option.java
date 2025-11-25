package com.mashang.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("题目选项")
public class Option {
    @JsonProperty("value")
    @ApiModelProperty("选项")
    private String value;
    @JsonProperty("text")
    @ApiModelProperty("文本")
    private String text;
}
