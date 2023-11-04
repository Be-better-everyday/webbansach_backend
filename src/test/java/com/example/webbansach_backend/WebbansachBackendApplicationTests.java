package com.example.webbansach_backend;

import com.example.webbansach_backend.entity.BookCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebbansachBackendApplicationTests {

    @Test
    void contextLoads() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setCategoryName("");
    }

}
