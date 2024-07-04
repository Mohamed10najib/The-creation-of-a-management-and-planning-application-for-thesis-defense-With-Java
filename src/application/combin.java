package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class combin {

	public static List<List<List<Integer>>> generateGroups(List<Integer> teachers, int groupSize) {
	    List<List<List<Integer>>> finalGroups = new ArrayList<>();

	    for (int hour = 1; hour <= 8; hour++) {
	        List<Integer> teachersCopy = new ArrayList<>(teachers);
	        List<List<Integer>> hourGroups = new ArrayList<>();
	        Set<Integer> usedTeachers = new HashSet<>();
	        int attempts = 0;
	        int maxAttempts = 1000; 

	        while (hourGroups.size() < 7 && attempts < maxAttempts) {
	            
	            Collections.shuffle(teachersCopy);

	            List<Integer> selectedCombination = new ArrayList<>(teachersCopy.subList(0, groupSize));

	           
	            if (!usedTeachers.stream().anyMatch(selectedCombination::contains)) {
	               
	                hourGroups.add(selectedCombination);

	               
	                usedTeachers.addAll(selectedCombination);
	            }

	            attempts++;
	        }

	        
	        finalGroups.add(hourGroups);
	    }

	    return finalGroups;
	}
	

    public static void main11(String[] args) {
        String url = "jdbc:mysql://localhost:3306/soutenance";
        String username = "root";
        String pass = "";
        String selectSQL = "SELECT id_p FROM profs WHERE departement='Département Mathématiques et Informatique' ORDER BY exeprience DESC LIMIT ?";
        String selectSQL1 = "SELECT id_p FROM profs WHERE departement='Département Génie Civil Energétique et Environnement (GCEE)' ORDER BY exeprience DESC LIMIT ?";
        String selectSQL2 = "SELECT id_p FROM profs WHERE departement='Département Mathématiques et Informatique' ORDER BY exeprience ASC LIMIT ?";
        String selectSQL3 = "SELECT id_p FROM profs WHERE departement='Département Génie Civil Energétique et Environnement (GCEE)' ORDER BY exeprience ASC LIMIT ?";
        String selectSQL4 = "SELECT count(*) FROM profs WHERE departement='Département Génie Civil Energétique et Environnement (GCEE)'";
        String selectSQL5 = "SELECT count(*) FROM profs WHERE departement='Département Mathématiques et Informatique'";
        String selectSQL6 = "DELETE FROM jury_membres ";
        String selectSQL7 = "DELETE FROM soutnance ";

        String insertSQL = "INSERT INTO jury_membres (prof1, prof2, prof3, departement,	encadreur) VALUES (?, ?, ?, ?,?)";

        try {
            Connection con = DriverManager.getConnection(url, username, pass);
            PreparedStatement selectStatement = con.prepareStatement(selectSQL);
            PreparedStatement selectStatement1 = con.prepareStatement(selectSQL1);
            PreparedStatement selectStatement2 = con.prepareStatement(selectSQL2);
            PreparedStatement selectStatement3 = con.prepareStatement(selectSQL3);
            PreparedStatement selectStatement4 = con.prepareStatement(selectSQL4);
            PreparedStatement selectStatement5 = con.prepareStatement(selectSQL5);
            PreparedStatement selectStatement6 = con.prepareStatement(selectSQL6);

            PreparedStatement selectStatement7 = con.prepareStatement(selectSQL7);
            int lignesSupprimees1=selectStatement7.executeUpdate();
            int lignesSupprimees=selectStatement6.executeUpdate();
           
            
            if (lignesSupprimees > 0) {
                System.out.println(lignesSupprimees + " ligne(s) supprimée(s) avec succès.");
            } else {
                System.out.println("Aucune ligne supprimée.");
            }
            if (lignesSupprimees1 > 0) {
                System.out.println(lignesSupprimees1 + " ligne(s) supprimée(s) avec succès.");
            } else {
                System.out.println("Aucune ligne supprimée.");
            }
            
            PreparedStatement insertStatement = con.prepareStatement(insertSQL);
            
            ResultSet resultSet4 = selectStatement4.executeQuery();
            ResultSet resultSet5 = selectStatement5.executeQuery();
            int countCivile = 0;
            int countMaths = 0;
            int n=0,s=0,j=0,p=0;

            if (resultSet4.next()) {
                countCivile = resultSet4.getInt(1);
                n=(2*countCivile/3);
                j=countCivile-n;
            }

            if (resultSet5.next()) {
                countMaths = resultSet5.getInt(1);
                s=(2*countMaths/3);
                p=countMaths-s;
            }
            selectStatement.setInt(1, n);
            selectStatement1.setInt(1, s);
            selectStatement2.setInt(1, p);
            selectStatement3.setInt(1, j);
            System.out.println(n);
            System.out.println(s);
            System.out.println(p);
            System.out.println(j);

            ResultSet resultSet = selectStatement.executeQuery();
            ResultSet resultSet1 = selectStatement1.executeQuery();
            ResultSet resultSet2 = selectStatement2.executeQuery();
            ResultSet resultSet3 = selectStatement3.executeQuery();
            
            List<Integer> idList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_p");
                idList.add(id);
            }
            List<Integer> idList1 = new ArrayList<>();
            while (resultSet1.next()) {
                int id = resultSet1.getInt("id_p");
                idList1.add(id);
            }
            List<Integer> idList2 = new ArrayList<>();
            while (resultSet2.next()) {
                int id = resultSet2.getInt("id_p");
                idList2.add(id);
            }
            List<Integer> idList3 = new ArrayList<>();
            while (resultSet3.next()) {
                int id = resultSet3.getInt("id_p");
                idList3.add(id);
            }
            System.out.println(idList.size());
            System.out.println(idList1.size());
            System.out.println(idList2.size());
            System.out.println(idList3.size());



           
            List<List<List<Integer>>> finalGroups = generateGroups(idList, 3);
            List<List<List<Integer>>> finalGroups1 = generateGroups(idList1, 3);
            int k=0;
            System.out.println("finalGroups"+finalGroups.size());
           
            
            for (int hour = 1; hour <= finalGroups.size(); hour++) {
                List<List<Integer>> hourGroups = finalGroups.get(hour - 1);
                

                for (int i = 1; i <= hourGroups.size(); i++) {
                    List<Integer> combination = hourGroups.get(i - 1);
                    
                    System.out.println("hello");
                    System.out.println(combination.get(0));
                    System.out.println(idList2.get(k));

                    insertStatement.setInt(1, combination.get(0));
                    insertStatement.setInt(2, combination.get(1));
                    insertStatement.setInt(3, idList2.get(k));
                    insertStatement.setInt(5, combination.get(2));
                    insertStatement.setString(4, "Département Mathématiques et Informatique");
                   k++;
                   if(k>=p) {
                   	k=0;
                   	break;
                   }
                    
                    insertStatement.executeUpdate();
                }
               
            }
            int m=0;
           
            for (int hour = 1; hour <= finalGroups1.size(); hour++) {
                List<List<Integer>> hourGroups1 = finalGroups1.get(hour - 1);
                

                for (int i = 1; i <= hourGroups1.size(); i++) {
                    List<Integer> combination1 = hourGroups1.get(i - 1);

                   
                    insertStatement.setInt(1, combination1.get(0));
                    insertStatement.setInt(2, combination1.get(1));
                    
                    insertStatement.setInt(3, idList3.get(m));
                    
                    insertStatement.setString(4, "Département Génie Civil Energétique et Environnement (GCEE)");
                    insertStatement.setInt(5, combination1.get(2));
                    m++;
                    if(m>=j) {
                    	m=0;
                    	break;
                    }
                    
                    insertStatement.executeUpdate();
                }
                
            }

           
            selectStatement.close();
            selectStatement1.close();
            selectStatement2.close();
            selectStatement3.close();
            selectStatement4.close();
            selectStatement5.close();
            insertStatement.close();
            con.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("Error " + e.getMessage());
        }
    }
}