package com.flash.questionnaire.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flash.questionnaire.Models.Quest;
import com.flash.questionnaire.Models.UsersData;
import com.flash.questionnaire.Utils.Constants;

import java.util.ArrayList;

public class DBDataHelper {
	
	private SQLiteDatabase database;
	private Context context;

	public DBDataHelper(Context context) {
		this.context = context;
		openDB();
	}
	
	public void openDB() {
	 	DBOpenHelper dbOpenHelper = new DBOpenHelper(context, Constants.DATABASE_NAME);
	 	database = dbOpenHelper.openDataBase();
	}

	public ArrayList<UsersData> getUsers(){
		ArrayList<UsersData> users = new ArrayList<UsersData>();
		String query = "SELECT * FROM " + Constants.TABLE_NAME_USERS;
		Cursor cursor = database.rawQuery(query, null);
		if(cursor.moveToFirst()){
			do{
				UsersData usersData = new UsersData();
				usersData.setSex(cursor.getString(1));
				usersData.setFio(cursor.getString(2));
				usersData.setRef(cursor.getString(3));
				usersData.setRev(cursor.getString(4));
				usersData.setMail(cursor.getString(5));
				usersData.setTel(cursor.getString(6));
				users.add(usersData);
			} while(cursor.moveToNext());
		}
		return users;
	}

	public boolean getSizes() {
		int quest = 0;
		int issue = 0;
		String query_quests = "SELECT COUNT(*) FROM " + Constants.TABLE_NAME_QUESTS;
		String query_issue = "SELECT COUNT(*) FROM " + Constants.TABLE_NAME_ISSUE;
		Cursor cursor_quests = database.rawQuery(query_quests, null);
		Cursor cursor_issue = database.rawQuery(query_issue, null);
		cursor_quests.moveToFirst();
		cursor_issue.moveToFirst();
		quest = cursor_quests.getInt(0);
		issue = cursor_quests.getInt(0);
		cursor_quests.close();
		cursor_issue.close();
		if(quest!= 0 && issue!=0){
			return true;
		} else{
			return false;
		}
	}
	public ArrayList<String> getQuests() {
		ArrayList<String> list = new ArrayList<String>();
		String query = "SELECT name FROM quests";
		Cursor cursor = database.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return list;
	}

	public String getQuestions(int idQuest, int row) {
		String question = null;
		String query = "SELECT name FROM issue WHERE id_quest=" + idQuest;
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToPosition(row - 1);
		question = cursor.getString(0);
		cursor.close();
		return question;
	}

	public int getQuestId(String questName) {
		int questId = 0;
		String query = "SELECT id FROM quests WHERE name='" + questName + "'";
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		questId = cursor.getInt(0);
		cursor.close();
		return questId;
	}

	public void addQuests(Quest quest){
		ContentValues values = new ContentValues();
		values.put("id", quest.getId());
		values.put("name", quest.getName());
		database.insert(Constants.TABLE_NAME_QUESTS, null, values);
	}

	public void addIssue(String idQuest, String issueName){
		ContentValues values = new ContentValues();
		values.put("id_quest", idQuest);
		values.put("name", issueName);
		database.insert(Constants.TABLE_NAME_ISSUE, null, values);
	}

	public void addUserAnswers(String sex, String fio,
							   String ref, String rev,
							   String mail, String tel){
		ContentValues values = new ContentValues();
		values.put("sex", sex);
		values.put("fio", fio);
		values.put("ref", ref);
		values.put("rev", rev);
		values.put("mail", mail);
		values.put("tel", tel);
		database.insert(Constants.TABLE_NAME_USERS, null, values);
	}

	public void clearTableIssue(){
		database.execSQL("DELETE FROM " + Constants.TABLE_NAME_ISSUE);
	}

	public void clearRecordUser(String tel){
		database.delete("users", "tel = " + tel, null);
	}

	public void clearTableQuests(){
		database.execSQL("DELETE FROM " + Constants.TABLE_NAME_QUESTS);
	}

	public String getPassword() {
		String pass = null;
		String query = "SELECT pass FROM admin WHERE id = 1";
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		pass = cursor.getString(0);
		cursor.close();
		return pass;
	}
}
