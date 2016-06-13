package sqliteDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.IGuitar;
import model.*;
import util.DBUtil;

public class GuitarImpl implements IGuitar {

	@Override
	public List<Guitar> getAllGuitars(){
		Connection Conn = DBUtil.getSqliteConnection();
		String sql = "select * from Guitars";
		List<Guitar> inventory = new ArrayList<Guitar>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Map properties = new HashMap();
				properties.put("builder",  Builder.fromString(rs.getString("builder")));
				properties.put("backWood", Wood.fromString(rs.getString("backWood")));
				properties.put("topWood", Wood.fromString(rs.getString("topWood")));
				properties.put("model", rs.getString("model"));
				properties.put("type", Type.fromString(rs.getString("type")));
				properties.put("StringNum", rs.getString("stringNum"));
				GuitarSpec spec = new GuitarSpec(properties);
				Guitar Guitar = new Guitar(rs.getString("serialNumber"),rs.getDouble("price"),spec);
				inventory.add(Guitar);				
			}			
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return inventory;
	}
	
	}
