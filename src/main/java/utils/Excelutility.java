package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Excelutility {


    public void ReadData() throws IOException {

        String path=System.getProperty("user.dir")+"";
//XSSFWorkBook

        XSSFWorkbook workbook=new XSSFWorkbook(path);
        XSSFSheet sheet=workbook.getSheet("");

        for(int i=0;i<workbook.getNumberOfSheets();i++){

            workbook.getSheetName(i).equalsIgnoreCase("");
            XSSFRow row=sheet.getRow(1);

        }

        int rowCount=    sheet.getPhysicalNumberOfRows();
        XSSFRow row=sheet.getRow(0);

        int colCount=row.getLastCellNum(); //last cell of the row will give column count

        Iterator<Row> rows=sheet.iterator(); //sheet is group of rows
        Row firstRow=rows.next();

        Iterator<Cell> cell=firstRow.cellIterator(); //each row is group of cells
        while(cell.hasNext()){

        if(cell.next().getStringCellValue().contains("")){

            ArrayList<Object> al=new ArrayList<>();
            al.add("");
            al.add(2);
        }
        }




    }
}
