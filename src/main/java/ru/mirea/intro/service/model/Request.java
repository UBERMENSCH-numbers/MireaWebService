package ru.mirea.intro.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Request {
    private Long id;
    private String requestValue;
    private List<Book> bookList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(requestValue, request.requestValue) && Objects.equals(bookList, request.bookList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestValue, bookList);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requestValue='" + requestValue + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
