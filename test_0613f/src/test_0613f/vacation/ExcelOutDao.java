package test_0613f.vacation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import entity.Vacation;

public class ExcelOutDao {
	public void excelOut(Vacation vacation, String path, String name,
			String file, int sheetNum, int size) throws FileNotFoundException {

		// 変更するエクセルファイルを指定
		// エクセルファイルを置いているフォルダー
		String INPUT_DIR = path + "/";
		String fileName = "休暇届.xls";
		String fileNameAfter = file;

		// 変数宣言
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		POIFSFileSystem fileSystem = null;

		// Calendarオブジェクト生成
		Calendar cal = Calendar.getInstance();

		// 2回目以降の書き込み？
		if (sheetNum > 0) {
			// 複製したファイルに書き込む
			fileName = fileNameAfter;
		}

		// ファイル書き込み準備
		FileInputStream in = new FileInputStream(INPUT_DIR + fileName);
		try {
			fileSystem = new POIFSFileSystem(in);
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		try {
			// 既存のエクセルファイルを編集する際は、WorkbookFactoryを使用
			wb = HSSFWorkbookFactory.createWorkbook(fileSystem);
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

		// HSSFPatriarchインスタンスを生成
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// HSSFClientAnchorインスタンスを生成
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
				(short) 3, 16,
				(short) 10, 17);

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
		//row = sheet.getRow(14);
		//cell = row.getCell(3);
		String division = vacation.getDivision();
		switch(division) {
			case "有給休暇":
				// オフセットを指定
				anchor.setDx1(30);
				anchor.setDy1(25);
				anchor.setDx2(-5700); // 6750
				anchor.setDy2(-800);

				// break処理
				break;
			case "生理休暇":
				// オフセットを指定
				anchor.setDx1(130);
				anchor.setDy1(25);
				anchor.setDx2(-4350); // 5400
				anchor.setDy2(-800);
				anchor.setCol1(4);

				// break処理
				break;
			case "慶弔休暇":
				// オフセットを指定
				anchor.setDx1(230);
				anchor.setDy1(25);
				anchor.setDx2(-3000); // 4050
				anchor.setDy2(-800);
				anchor.setCol1(5);

				// break処理
				break;
			case "産前産後休暇":
				// オフセットを指定
				anchor.setDx1(730);
				anchor.setDy1(25);
				anchor.setDx2(-1200); // 2550
				anchor.setDy2(-800);
				anchor.setCol1(6);

				// break処理
				break;
			case "転勤休暇":
				// オフセットを指定
				anchor.setDx1(30);
				anchor.setDy1(150);
				anchor.setDx2(-5700); // 6750
				anchor.setDy2(-100);

				// break処理
				break;
			case "特別休暇":
				// オフセットを指定
				anchor.setDx1(130);
				anchor.setDy1(150);
				anchor.setDx2(-4350); // 5400
				anchor.setDy2(-100);
				anchor.setCol1(4);

				// break処理
				break;
			case "その他":
				// オフセットを指定
				anchor.setDx1(230);
				anchor.setDy1(150);
				anchor.setDx2(-2100); // 3150
				anchor.setDy2(-100);
				anchor.setCol1(5);

				// break処理
				break;
		}

		System.out.println(anchor.getDx1());
		System.out.println(anchor.getDy1());
		System.out.println(anchor.getDx2());
		System.out.println(anchor.getDy2());

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

		// 円を指定したアンカー位置に作成
		HSSFSimpleShape shape = patriarch.createSimpleShape(anchor);

		// 円の設定
		shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_OVAL);
		shape.setNoFill(true);
		shape.setLineStyleColor(0, 0, 0);
		shape.setLineWidth(HSSFSimpleShape.LINEWIDTH_ONE_PT);
		shape.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);

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
