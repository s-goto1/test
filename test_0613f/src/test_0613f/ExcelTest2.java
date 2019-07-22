package test_0613f;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import entity.TotalM;

public class ExcelTest2 {

	//エクセルファイルを置いているフォルダー
	static final String INPUT_DIR = "C:\\Users\\jsd01\\Desktop\\";

	public void excelOut(TotalM totalM,int test) throws FileNotFoundException {

		// 変更するエクセルファイルを指定
		FileInputStream in = new FileInputStream(INPUT_DIR + "d1470e67a491f5e34e3520fa0bee5ce9.xls");
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
			Row row = sheet.getRow(test+13);

			//6行目の3こ目のセルを取得
			for (int j = 1; j < 11; j++) {

				Cell cell = row.getCell(j);

				switch (j) {
				case 1 :
					//取得したセルにセットする値を指定
					cell.setCellValue(totalM.getDate());
					break;
				case 3:
					cell.setCellValue(totalM.getDepature());
					break;
				case 5:
					cell.setCellValue(totalM.getDestination());
					break;
				case 10:
					cell.setCellValue(totalM.getMoney());
					break;
				default:
					System.out.println("error");
				}

			//}

		}
		FileOutputStream out = null;

		try {
			// 変更するエクセルファイルを指定
			out = new FileOutputStream(INPUT_DIR + "d1470e67a491f5e34e3520fa0bee5ce9.xls");

			// 書き込み
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			try {
				wb.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}
		System.out.println("終わり");
	}

}
