package com.example.test;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test.test_code.CreateFolder;

@SpringBootApplication
public class TestProject1Application {

	public static void main(String[] args) {
	//	SpringApplication.run(TestProject1Application.class, args);
		CreateFolder createFolder = new CreateFolder();
		createFolder.foldeWriter("tester");
		createFolder.watchFolder();
	}

}
