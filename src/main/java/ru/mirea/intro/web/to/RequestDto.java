package ru.mirea.intro.web.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private String requestValue;
}
