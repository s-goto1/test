package test_0613f;

import java.util.List;

import entity.TotalM;

public class DaoTest {
	public static void main(String[] args) {

		// DAOクラスのインスタンスの生成
		TotalMDao dao = new TotalMDao();

		// findAll()メソッドの戻り値OrdersDTOクラスのインスタンスが格納されたList
		List<TotalM> tom = dao.findAll("1");

//		LoginUser lu = new LoginUser();
//		UserInfo ui = lu.findUser("1");

		// Listの中のOrdersDTOクラスのインスタンスをループで処理
		//		for (TotalM total : tom) {
		//			System.out.println(total.getName());
		//			System.out.println(total.getDepature());
		//			System.out.println(total.getDate());

//		InsertDao IDao = new InsertDao();
//		IDao.insert("飯塚", "6月2日", "池尻大橋", "渋谷");
//
//		System.out.println(ui);
		System.out.println(tom);
	}
}
