package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "prototype")  // this will not create object at SpringApplication.run container initialization.
public class Alien {
	private int aid;
	private String aname;
	private String tech;
	@Autowired
	private Laptop laptop;
	
	public int getAid() {
		return aid;
	}
	public Alien() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("object created.");
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	
	
	public Laptop getLaptop() {
		return laptop;
	}
	public void show() {
		System.out.println("i am showing");
		laptop.compile();
	}
	
}
