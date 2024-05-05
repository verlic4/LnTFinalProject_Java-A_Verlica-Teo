package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import index.DatabaseConnection;
import index.DatabaseSingleton;
import models.Pudding;

public class PuddingController {
	private static DatabaseConnection db = DatabaseSingleton.getInstance();
	
	public void createDefaultPudding() {
		insertPudding(new Pudding("pisang", 10000, 10));
		insertPudding(new Pudding("apel", 12000, 90));
		insertPudding(new Pudding("melon", 20000, 20));		
	}
	
	public static List<Pudding> getAllPuddings(){
		String query = "SELECT * FROM pudding";
		List<Pudding> puddings = new ArrayList<>();
		
		try {
			PreparedStatement statement = db.connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String namaMenu = rs.getString("namaMenu");
				String hargaMenu = rs.getString("hargaMenu");
				String stokMenu = rs.getString("stokMenu");
				
				Pudding pudding = new Pudding(namaMenu, Integer.parseInt(hargaMenu), Integer.parseInt(stokMenu));
				puddings.add(pudding);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return puddings;
	}
	
	public static boolean insertPudding(Pudding pudding) {
		if(puddingExist(pudding.getNamaMenu())) {
			return false;
		}
		String query = "INSERT INTO pudding (namaMenu, hargaMenu, stokMenu) VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, pudding.getNamaMenu());
            statement.setInt(2, pudding.getHargaMenu());
            statement.setInt(3, pudding.getStokMenu());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
		}catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
	
	private static boolean puddingExist(String namaMenu) {
		String query = "SELECT COUNT(*) FROM pudding WHERE namaMenu= ?";
		try {
			PreparedStatement statement = db.connection.prepareStatement(query);
			statement.setString(1, namaMenu);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int count = resultSet.getInt(1);
				return count>0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updatePudding(String namaMenu, int hargaMenu, int stokMenu) {
		if(puddingExist(namaMenu)) {
			String query = "UPDATE pudding SET hargaMenu = ? WHERE namaMenu = ?";
			
			try {
				PreparedStatement statement = db.connection.prepareStatement(query);
				statement.setLong(1, hargaMenu);
				statement.setString(2, namaMenu);
				statement.setInt(3, stokMenu);
				
				int rowsUpdated = statement.executeUpdate();
				return rowsUpdated > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return false;
	}
	
	 public static List<String> getNamaPuddings() {
	        String query = "SELECT namaMenu FROM pudding";
	        List<String> namaPuddings = new ArrayList<>();

	        try {
	            PreparedStatement statement = db.connection.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                String namaMenu = resultSet.getString("namaMenu");
	                namaPuddings.add(namaMenu);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return namaPuddings;
	    }
	

	public static boolean deletePudding(String namaMenu) {
		if(puddingExist(namaMenu)) {
			String query = "DELETE FROM pudding WHERE namaMenu = ?";
			try {
				PreparedStatement statement = db.connection.prepareStatement(query);
				statement.setString(1, namaMenu);
				
				int rowsUpdated = statement.executeUpdate();
				return rowsUpdated > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
