package test_0613f.vacation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import entity.Vacation;

public class ExcelOutDao {
	public void excelOut(Vacation vacation, String path, String name,
			String file, int sheetNum, int size) throws FileNotFoundException {

		// 変更するエクセルファイルを指定
		// エクセルファイルを置いているフォルダー
		String INPUT_DIR = path + "/";
		String fileName = "休暇届.xls";
		String fileNameAfter = file;

		// Calendarオブジェクト生成
		Calendar cal = Calendar.getInstance();

		// 2回目以降の書き込み？
		if (sheetNum > 0) {
			// 複製したファイルに書き込む
			fileName = fileNameAfter;
		}

		// ファイル書き込み準備
		FileInputStream in = new FileInputStream(INPUT_DIR + fileName);

		// 変数宣言
		Workbook wb = null;
		Sheet sheet = null;

		try {
			// 既存のエクセルファイルを編集する際は、WorkbookFactoryを使用
			wb = WorkbookFactory.create(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// レコードが2件以上存在する？
		if(size > 1 && sheetNum == 0) {
			// シート必要分繰り返す
			for(int i = 1; i < size; i++) {
				// シート複製
				wb.cloneSheet(0);
			}
		}

		// 休暇届.xlsxの休暇届シートを取得
		sheet = wb.getSheet(wb.getSheetName(sheetNum));

		// シートの行数と列数の初期値設定
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);

		// 期間（開始）書き込み
		row = sheet.getRow(13);
		cell = row.getCell(3);
		cal.set(vacation.getYear(), vacation.getFromMonth() - 1, vacation.getFromDay());
		cell.setCellValue(cal);

		// 期間（終了）書き込み
		row = sheet.getRow(15);
		cell = row.getCell(3);
		cal.set(vacation.getYear(), vacation.getToMonth() - 1, vacation.getToDay());
		cell.setCellValue(cal);

		// 合計日数書き込み
		row = sheet.getRow(13);
		cell = row.getCell(6);
		cell.setCellValue("（　" + vacation.getTotalDay() + "　日間）");

		// 区分書き込み
		// 色々面倒な命令が必要なため現段階では省略

		// 事由書き込み
		row = sheet.getRow(18);
		cell = row.getCell(3);
		cell.setCellValue(vacation.getReason());

		// 名前を書き込み
		Row rowForName = sheet.getRow(12);
		Cell cellForName = rowForName.getCell(4);
		cellForName.setCellValue(name);

		// 申請日時を書き込み
		//Row rowForDate = sheet.getRow(4);
		//Cell cellForDate = rowForDate.getCell(8);
		//cal.getTime();
		//cellForDate.setCellValue(cal);

		// ファイル出力準備
		FileOutputStream out = null;

		wb.setForceFormulaRecalculation(true);
		//ブック再計算
		sheet.setForceFormulaRecalculation(true);
		//シート再計算
		//毎回やらないでいい、あとで直す

		try {
			// 変更するエクセルファイルを指定
			out = new FileOutputStream(INPUT_DIR + fileNameAfter);

			// 書き込み
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
		}
	}
}
