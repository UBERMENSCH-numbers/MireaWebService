package ru.mirea.intro.web.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Response info")
public class Meta {
    @ApiModelProperty(value = "Response status code")
    private int code;
    @ApiModelProperty(value = "Response status description")
    private String description;
}
