package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interaction;


public class GenesDao {
	
	public List<Genes> getVertici(Map<String, Genes> idMap){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome "
				+ "FROM genes "
				+ "WHERE Essential = 'Essential'";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
				idMap.put(genes.getGeneId(), genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Interaction> getArchi(Map<String, Genes> idMap){
		String sql = "SELECT DISTINCT i.GeneID1, i.GeneID2, i.Expression_Corr "
				+ "FROM genes g1, genes g2, interactions i "
				+ "WHERE g1.GeneID <> g2.GeneID  AND i.GeneID1 <> i.GeneID2 "
				+ "AND (g1.GeneID = i.GeneID1 AND g2.GeneID = i.GeneID2) "
				+ "AND g1.Essential = 'Essential' AND g2.Essential = 'Essential'";
		List<Interaction> result = new ArrayList<Interaction>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				
				if(idMap.containsKey(res.getString("GeneID2")) && idMap.containsKey(res.getString("GeneID1"))) {
					Interaction i = new Interaction(idMap.get(res.getString("GeneID1")), 
						idMap.get(res.getString("GeneID2")), 
						res.getDouble("Expression_Corr"));
					result.add(i);
				}

				
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	


	
}
