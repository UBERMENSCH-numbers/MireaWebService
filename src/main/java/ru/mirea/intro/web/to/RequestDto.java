package ru.mirea.intro.web.to;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Long id;
    private String requestValue;
    private List<BookTO> bookTOList;
}
