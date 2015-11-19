package com.devdream.model;

import java.sql.Date;

public class Product {

	//
	// Attributes
	private String nombre, descripcion;
	private Date fecha;
	private float precio;
	
	//
	// Constructor
	public Product(String nombre, String descripcion, int precio) {
		setNombre(nombre);
		setDescripcion(descripcion);
		setPrecio(precio);
	}

	//
	// Methods
	@Override
	public String toString() {
		return super.toString();
	}
	
	//
	// Getters and Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}