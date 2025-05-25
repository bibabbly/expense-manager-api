package com.theo.expense_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories") // optional, for clarity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isDefault = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") // optional, clearer FK naming
    private User user;
}
