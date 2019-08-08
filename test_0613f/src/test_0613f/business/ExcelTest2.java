package test_0613f.business;

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

	public boolean excelOut(TotalM totalM, int test, String path, String name,
			String file, int point, int sheetNum) throws FileNotFoundException {

		// 変更するエクセルファイルを指定
		// エクセルファイルを置いているフォルダー
		String INPUT_DIR = path + "/";
		String fileName = "d1470e67a491f5e34e3520fa0bee5ce9.xls";
		String fileNameAfter = file;

		// フラグ関係
		//boolean nextSheet = false;
		boolean rowReset = false;

		if (test != 0 || sheetNum > 0) {
			fileName = fileNameAfter;

		}

		FileInputStream in = new FileInputStream(INPUT_DIR + fileName);
		Workbook wb = null;
		Sheet sheet = null;

		try {
			// 既存のエクセルファイルを編集する際は、WorkbookFactoryを使用
			wb = WorkbookFactory.create(in);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// レコードが22件以上存在する？
		if(point != 0) {
			// シート必要分繰り返す
			for(int i = 0; i < point; i++) {
				// シート複製
				wb.cloneSheet(0);

				// シート並び替え
				wb.setSheetOrder(wb.getSheetName(i + 2), i + 1);
			}
		}

//		// 2枚目以降のシートに書き込むべき？
//		if(sheetNum > 0) {
//			// 2枚目以降のシートに移動する許可
//			nextSheet = true;
//		}

		// 次が34行目の書き込み？
		if(test > 19) {
			// 2枚目以降のシートに移動する許可
			//nextSheet = true;

			// 行数リセットフラグ
			rowReset = true;
		}

//		// 1枚目のシートの空きがない？
//		if(nextSheet) {
//			// 勤務表.xlsxの勤務表シートを取得
//			// sheet = wb.getSheet("日帰出張精算明細書 (" + (sheetNum + 1) + ")");
//		} else {
//			// 勤務表.xlsxの勤務表シートを取得
//			// sheet = wb.getSheet("日帰出張精算明細書");
//		}

		// 勤務表.xlsxの勤務表シートを取得
		sheet = wb.getSheet(wb.getSheetName(sheetNum));

		//シートの6行目を取得　(名前が入っている)
		//for (int i = 13; i < test+13; i++)

		//{
		Row row = sheet.getRow(test + 13);

		for (int j = 1; j < 13; j++) {
			Cell cell = row.getCell(j);

			switch (j) {
			case 1:
				// 取得したセルにセットする値を指定
				cell.setCellValue(totalM.getMonth());
				System.out.println(totalM.getMonth());
				break;
			case 2:
				cell.setCellValue(totalM.getDay());
				System.out.println(totalM.getDay());
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
				//totalM.getDivision() +  ここにdivision入れて金額と一緒に表示するか考え中
				cell.setCellValue(totalM.getMoney());
				break;
			case 12:
				if(totalM.getDivision().equals("往復")) {
					cell.setCellValue(totalM.getPurpose() +
							"(" + totalM.getDivision() + ")");
					// 往復の場合のみ用件に(往復)を追加するテスト
				} else {
					cell.setCellValue(totalM.getPurpose());
				}
				break;
			//default:
			//	System.out.println("error");
			}
		}

		if (test == 0 ) {
			Row rowForName = sheet.getRow(test + 6);

			Cell cellForName = rowForName.getCell(4);

			cellForName.setCellValue(name);
		}

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
			System.out.println("終わり");
		}
		return rowReset;
	}
}
