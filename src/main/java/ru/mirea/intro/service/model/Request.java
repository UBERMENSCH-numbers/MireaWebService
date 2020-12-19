package ru.mirea.intro.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Request {
    private Long id;
    private String requestValue;
    private List<Book> bookList;
}
