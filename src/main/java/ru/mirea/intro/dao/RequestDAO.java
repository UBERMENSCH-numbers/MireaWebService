package ru.mirea.intro.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Requests", schema = "public")
public class RequestDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "request_id")
    @Column(name = "id")
    private Long id;
    @Column(name = "request_value")
    private String requestValue;
}

