package Dao;

import java.util.List;

import model.User;

public interface Idao<T>{
	public boolean create(T object);
}
