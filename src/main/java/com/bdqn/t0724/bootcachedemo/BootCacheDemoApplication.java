package com.bdqn.t0724.bootcachedemo;

import com.bdqn.t0724.bootcachedemo.model.Book;
import com.bdqn.t0724.bootcachedemo.service.BookService;
import com.bdqn.t0724.bootcachedemo.service.MyCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@EnableCaching //用注解启动SpringCache
public class BootCacheDemoApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(BootCacheDemoApplication.class, args);
	}

	@Autowired
	private BookService bookService;

	@Override
	public void run(String... strings) throws Exception {
		//getBooks();//第一次没有参考意义

		System.out.println("=======================");
		for (int i=0;i<10;i++) {
			long start = System.nanoTime();
			List<Book> list = bookService.getBooks();

			long end = System.nanoTime();


			System.out.println(list.size());

			System.out.println(end - start + " nano seconds");
		}
		System.out.println("=======================");

//		Jedis jedis = new Jedis("127.0.0.1");
//		Set<String> keys = jedis.keys("*");
//		//System.out::println是把println方法作为lambda传给foreash了
//		keys.forEach(System.out::println);

	}
	@Autowired
	private MyCache cache;

	private List<Book> books=null;

	/**
	 * **如果**数据库不会发生变化，那个代码的效率就非常高了
	 * 不管怎样，这就是缓存的最基本的理念，把数据放到离用户近的地方。
	 * 因为内存访问速度远远高于数据库查询（从时间上讲，就是近）
	 *
	 * @return
	 * @throws SQLException
	 */
//	public List<Book> getCachedBooks() throws SQLException{
//		final String books_key = "books";
//		Object o = cache.get(books_key);
//		if(o==null){//如果内存中没有结果（也就是说如果缓存有结果，就不去数据库了）
//			o =getBooks();//就去数据库中取一次结果
//			cache.put(books_key,o);
//		}
//		return (List<Book>)o;//返回结果
//	}

//	@Cacheable("t0724")//因为缓存也可以分类，这是本方法所属的分类名字
//	public List<Book> getBooks() throws SQLException {
//		Connection connection =
//				DriverManager.getConnection("jdbc:mysql:///t0724", "root", "123456");
//
//		PreparedStatement preparedStatement = connection.prepareStatement("select * from book");
//
//		ResultSet resultSet = preparedStatement.executeQuery();
//
//		List<Book> list=new ArrayList<>();
//
//		while (resultSet.next()){
//			Book book=new Book();
//			book.setId((Integer)resultSet.getInt("id"));
//			book.setName(resultSet.getString("name"));
//			book.setAuthor(resultSet.getString("author"));
//			list.add(book);
//
//		}
//		connection.close();
//
//		return list;
//	}
}
