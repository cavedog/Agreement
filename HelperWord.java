package AgreementMaker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.model.fields.merge.MailMerger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
public class HelperWord {

	
	
	// ������ ������ MyDataManager ��� ������ � �������
    private MyDataManager dmg;
    // ������ �� ���� *.xslx
    private List<HashMap> clientsRows;
    // �������������� ������ �� ��
    private List<HashMap> additionalData;

    // ���������� �������� ������ MS Word 
    public void createDocs() {
        // ������� ������ ������ MyDataManager ��� ������ � �������
        dmg = new MyDataManager();
        try {
            // ��������� ������ �� ����� MS Excel
            clientsRows = dmg.getDataBlock();
            // <editor-fold defaultstate="collapsed" desc="Catch clauses">            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            //</editor-fold>   
        }
        // ������� ���� MS Word � ��������� ���
        addDataBlock();    

    }

    // ������� ���� MS Word � ��������� ���
    private void addDataBlock() {
        int num = 0;
        // ��������� ���������� � ������ �������
        for (HashMap row : clientsRows) {            
            try {
                num++;
                // ��������� ������ � ������������ ������� MS Word
                WordprocessingMLPackage wordMLPackage =
                        WordprocessingMLPackage
                        .load(new File("template.docx"));
                // ������� ������ ��� ������� �������� � ���� �������
                List<Map<DataFieldName, String>> data = 
                        new ArrayList<Map<DataFieldName, String>>();
                // �������� �������������� ������ � ������� �� ����
                additionalData = dmg.getAddress(row.get("NAME").toString(), 
                        row.get("DOCDATE").toString());
                // ��������� �������� ��� ����� �������
                Map<DataFieldName, String> map = 
                        new HashMap<DataFieldName, String>();
                map.put(new DataFieldName("NAME"), row.get("NAME").toString());
                map.put(new DataFieldName("ADDRESS"), 
                        additionalData.get(0).get("ADDRESS").toString());                
                data.add(map);
                // ������� ����� ������ MS Word �� ������ ������������� � 
                // �������� ����� �������
                WordprocessingMLPackage output = 
                        MailMerger.getConsolidatedResultCrude(
                        wordMLPackage, data);
                // ��������� ������ � ����
                output.save(new File("T:\\VIPISKI_KK\\������\\" 
                        + num + ". " + row.get("NAME") + ".docx"));
                // <editor-fold defaultstate="collapsed" desc="Catch clauses">                 
            } catch (InvalidFormatException ex) {
                Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Docx4JException ex) {
                Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            }
            // </editor-fold>    
        }
    }
}
public class MyDataManager {

    public NamedParameterJdbcTemplate namedPar;

  
    //�������� ������ �� ��
    public List<HashMap> getAddress(String name, String date) 
            throws SQLException, FileNotFoundException, IOException {
        // �������� �������� ������
        OracleDataSource ds = getDataSource();
        // ��������� ������
        FileInputStream fins = new FileInputStream("query.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(fins, "UTF8"));
        String query = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            query += "\n";
            query += line;
        }
        // ��������� �������� ����������
        namedPar = new NamedParameterJdbcTemplate(ds);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("NAME", name);
        namedParameters.addValue("DOCDATE", date);
        // ��������� ������ � ������� ���������
        List<HashMap> res = (List<HashMap>) namedPar.query(query,
                namedParameters, new DataMapper());
        try {
            return res;
        } finally {
            ds.close();
        }
    }
}
