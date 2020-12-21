package ru.mirea.intro.web.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Request transfer model")
public class RequestDto {
    @ApiModelProperty(value = "Request id in dataBase. Generated automatically")
    private Long id;
    @ApiModelProperty(value = "Request status")
    private String requestValue;
    @ApiModelProperty("List of BookTo")
    private List<BookTO> bookTOList;
}
