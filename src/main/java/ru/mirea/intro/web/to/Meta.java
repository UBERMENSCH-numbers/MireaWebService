package ru.mirea.intro.web.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Meta {
    private int code;
    private String description;
}
