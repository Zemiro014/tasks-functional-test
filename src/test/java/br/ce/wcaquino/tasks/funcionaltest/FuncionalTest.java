package br.ce.wcaquino.tasks.funcionaltest;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FuncionalTest {
	
	public WebDriver startDriver() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = startDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste funcional com selenium");
			
			driver.findElement(By.id("dueDate")).sendKeys("05/05/2023");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
		} finally {
			//		Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = startDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("dueDate")).sendKeys("05/05/2023");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
		} finally {
			//		Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = startDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste funcional com selenium");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
		} finally {
			//		Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = startDriver();
		
		try {
			driver.findElement(By.id("addTodo")).click();
			
			driver.findElement(By.id("task")).sendKeys("Teste funcional com selenium");
			
			driver.findElement(By.id("dueDate")).sendKeys("05/05/2022");
			
			driver.findElement(By.id("saveButton")).click();
			
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			//		Fechar o browser
			driver.quit();
		}
	}
}
