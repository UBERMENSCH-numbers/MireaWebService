package ru.mirea.intro.web.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Book model")
public class BookTO {
    @ApiModelProperty(value = "Book id in database. Generated automatically")
    private Long id;
    @ApiModelProperty(value = "Author name")
    private String author;
    @ApiModelProperty("Book title")
    private String name;
}
