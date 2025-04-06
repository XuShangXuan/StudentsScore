package com.gjun.studentsscore.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gjun.studentsscore.model.StudentsReport;
import com.gjun.studentsscore.service.StudentsScoreService;
import com.gjun.studentsscore.util.SortUtil;

public class StudentsScoreJob {

	public static void main(String[] args) {

		// 方式一
		// 使用System.getProperty("user.dir") 取得Java程式執行時所在的工作目錄(也就是.class檔所在的資料夾)
		// 準備要讀取的原始csv檔的路徑
		Path basePath = Paths.get(System.getProperty("user.dir")); 
		Path filePath = basePath.resolve("src/com/gjun/studentsscore/StudentsScoreData.csv");

		// 準備要輸出的經過計算後csv檔的路徑
		Path targetPath = basePath.resolve("src/com/gjun/studentsscore/StudentsScoreResulet.csv");
		
		/*
		// 方式二
		// 使用相對路徑創建 Path 物件，實際路徑會基於程式執行時的當前工作目錄(由 System.getProperty("user.dir") 決定)
		Path filePath = Paths.get("src/com/gjun/studentsscore/StudentsScoreData.csv");
		Path targetPath = Paths.get("src/com/gjun/studentsscore/StudentsScoreResulet.csv");
		*/
		
		if (Files.notExists(filePath)) {
			System.out.println("檔案路徑不存在");
			return;
		}

		StudentsReport studentsReport = new StudentsReport();// 用於儲存及傳遞成績單的資料
		StudentsScoreService service = new StudentsScoreService();// 需要呼叫StudentsScoreService這個class的讀取和計算,和輸出的method

		try {

			studentsReport = service.inputStudentsScoreData(filePath);// 讀取並計算每一筆學生成績的總分與平均

			if (studentsReport != null && studentsReport.getStudentsScoreData().size() > 0) {

				SortUtil.sortStudentsScoreByAverage(studentsReport);// 將學生成績進行排序

				service.outputStudentsScoreData(targetPath, studentsReport);// 輸出經過計算後的學生成績單

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
