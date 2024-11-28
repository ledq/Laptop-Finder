package laptop.service;

public class Laptop {
	public String name;
	public String brand;
	public String CPU; 
	public String GPU;
	public String RAM;
	public String storage;
	public String screensize;
	public String color;
	public String os;
	
	public Laptop(String name, String brand, String cpu, String gpu, String ram,String storage,String screensize, String color, String os) {
		this.get(name, brand, cpu, gpu, ram, storage, screensize, color, os);
	}
	
	public void get(String name, String brand, String cpu, String gpu, String ram,String storage,String screensize, String color, String os) {
		this.name =name;
		this.brand =brand;
		this.CPU=cpu;
		this.GPU=gpu;
		this.RAM=ram;
		this.storage=storage;
		this.screensize=screensize;
		this.color = color;
		this.os =os;
	}
}
