package test_0613f;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import entity.TotalM;

public class ExcelTest2 {

	//エクセルファイルを置いているフォルダーaaa
	//static final String INPUT_DIR = "C:\\Users\\jsd01\\Desktop\\";

	public void excelOut(TotalM totalM, int test, String path, String name ,String file) throws FileNotFoundException {

		// 変更するエクセルファイルを指定
		//エクセルファイルを置いているフォルダー
		String INPUT_DIR = path + "/";

		String fileName = "d1470e67a491f5e34e3520fa0bee5ce9.xls";
		String fileNameAfter =file;

		if (test != 0) {
			fileName = fileNameAfter ;
		}


		FileInputStream in = new FileInputStream(INPUT_DIR + fileName);
		Workbook wb = null;

		try {
			// 既存のエクセルファイルを編集する際は、WorkbookFactoryを使用
			wb = WorkbookFactory.create(in);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//勤務表.xlsxの勤務表シートを取得
		Sheet sheet = wb.getSheet("日帰出張精算明細書");

		//シートの6行目を取得　(名前が入っている)
		//for (int i = 13; i < test+13; i++)

		//{
		Row row = sheet.getRow(test + 13);

		//6行目の3こ目のセルを取得
		for (int j = 1; j < 13; j++) {

			Cell cell = row.getCell(j);

			switch (j) {
			case 1:
				//取得したセルにセットする値を指定
				cell.setCellValue(totalM.getMonth());
				break;
			case 2:
				cell.setCellValue(totalM.getDay());
				break;
			case 3:
				cell.setCellValue(totalM.getDepature());
				break;
			case 5:
				cell.setCellValue(totalM.getDestination());
				break;
			case 7:
				cell.setCellValue(totalM.getTransportation());
				break;
			case 8:
				cell.setCellValue(totalM.getPlace());
				break;

			case 10:
				cell.setCellValue(totalM.getDivision() + totalM.getMoney());
				break;
			case 12:
				cell.setCellValue(totalM.getPurpose());
				break;
			default:
				System.out.println("error");
			}


		}
		if (test == 0 ) {
			Row rowForName = sheet.getRow(test + 6);

			Cell cellForName = rowForName.getCell(4);
			cellForName.setCellValue(name);
		}
		FileOutputStream out = null;

		try {
			// 変更するエクセルファイルを指定
			out = new FileOutputStream(INPUT_DIR + fileNameAfter);

			// 書き込みaaaa
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				out.close();
				wb.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
			System.out.println("終わり");
		}

	}
}
