package com.bdqn.t0724.bootcachedemo.service;

import com.bdqn.t0724.bootcachedemo.model.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@CacheConfig(cacheNames = "t0725")
public class BookService {
    @Cacheable()//因为缓存也可以分类，这是本方法所属的分类名字
    public List<Book> getBooks() throws SQLException {
        System.out.println("getBooks is working hard digging databases!");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql:///t0724", "root", "123456");

        PreparedStatement preparedStatement = connection.prepareStatement("select * from book");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Book> list=new ArrayList<>();

        while (resultSet.next()){
            Book book=new Book();
            book.setId((Integer)resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            list.add(book);

        }
        connection.close();

        return list;
    }

}
