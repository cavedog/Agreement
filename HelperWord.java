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

	
	
	// Объект класса MyDataManager для работы с данными
    private MyDataManager dmg;
    // Данные из фала *.xslx
    private List<HashMap> clientsRows;
    // Дополнительные данные из БД
    private List<HashMap> additionalData;

    // Инициирует создание файлов MS Word 
    public void createDocs() {
        // Создаем объект класса MyDataManager для работы с данными
        dmg = new MyDataManager();
        try {
            // Извлекаем данные из файла MS Excel
            clientsRows = dmg.getDataBlock();
            // <editor-fold defaultstate="collapsed" desc="Catch clauses">            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelperWord.class.getName()).log(Level.SEVERE, null, ex);
            //</editor-fold>   
        }
        // Создаем файл MS Word и заполняем его
        addDataBlock();    

    }

    // Создает файл MS Word и заполняет его
    private void addDataBlock() {
        int num = 0;
        // Считываем информацию о каждом клменте
        for (HashMap row : clientsRows) {            
            try {
                num++;
                // Извлекаем данные о существующем объекте MS Word
                WordprocessingMLPackage wordMLPackage =
                        WordprocessingMLPackage
                        .load(new File("template.docx"));
                // Создаем объект для вставки значений в поля слияния
                List<Map<DataFieldName, String>> data = 
                        new ArrayList<Map<DataFieldName, String>>();
                // Получаем дополнительные данные о клиенте из базы
                additionalData = dmg.getAddress(row.get("NAME").toString(), 
                        row.get("DOCDATE").toString());
                // Заполняем значения для полей слияния
                Map<DataFieldName, String> map = 
                        new HashMap<DataFieldName, String>();
                map.put(new DataFieldName("NAME"), row.get("NAME").toString());
                map.put(new DataFieldName("ADDRESS"), 
                        additionalData.get(0).get("ADDRESS").toString());                
                data.add(map);
                // Создаем новый объект MS Word на основе существующего и 
                // значений полей слияния
                WordprocessingMLPackage output = 
                        MailMerger.getConsolidatedResultCrude(
                        wordMLPackage, data);
                // Сохраняем объект в файл
                output.save(new File("T:\\VIPISKI_KK\\Письма\\" 
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

  
    //Получаем данные из БД
    public List<HashMap> getAddress(String name, String date) 
            throws SQLException, FileNotFoundException, IOException {
        // Получаем источник данных
        OracleDataSource ds = getDataSource();
        // Считываем запрос
        FileInputStream fins = new FileInputStream("query.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(fins, "UTF8"));
        String query = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            query += "\n";
            query += line;
        }
        // Вставляем значения параметров
        namedPar = new NamedParameterJdbcTemplate(ds);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("NAME", name);
        namedParameters.addValue("DOCDATE", date);
        // Исполняем запрос и получае результат
        List<HashMap> res = (List<HashMap>) namedPar.query(query,
                namedParameters, new DataMapper());
        try {
            return res;
        } finally {
            ds.close();
        }
    }
}
