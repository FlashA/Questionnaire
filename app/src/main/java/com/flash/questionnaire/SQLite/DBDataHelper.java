package com.flash.questionnaire.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBDataHelper {
	
	private static final String DB_NAME = "quest.sqlite";
	
	private SQLiteDatabase database;
	private Context context;

	public static final String TABLE_NAME_ISSUE = "issue";
	public static final String TABLE_NAME_QUESTS = "quests";
	public static final String TABLE_NAME_USERS = "users";
	
	public DBDataHelper(Context context) {
		this.context = context;
		openDB();
	}
	
	public void openDB() {
	 	DBOpenHelper dbOpenHelper = new DBOpenHelper(context, DB_NAME);
	 	database = dbOpenHelper.openDataBase();
	}

	public boolean getSizes() {
		int quest = 0;
		int issue = 0;
		String query_quests = "SELECT COUNT(*) FROM quests";
		String query_issue = "SELECT COUNT(*) FROM issue";
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
		cursor.moveToPosition(row-1);
		question = cursor.getString(0);
		cursor.close();
		return question;
	}

	public void addQuests(String id, String nameQuest){
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("name", nameQuest);
		database.insert(TABLE_NAME_QUESTS, null, values);
	}

	public void addIssue(String idQuest, String issueName){
		ContentValues values = new ContentValues();
		values.put("id_quest", idQuest);
		values.put("name", issueName);
		database.insert(TABLE_NAME_ISSUE, null, values);
	}

	public void addUserAnswers(String sex, String fio,
							   String prev_quest, String ref,
							   String rev, String mail, String tel){
		ContentValues values = new ContentValues();
		values.put("sex", sex);
		values.put("fio", fio);
		values.put("prev_quest", prev_quest);
		values.put("ref", ref);
		values.put("rev", rev);
		values.put("mail", mail);
		values.put("tel", tel);
		database.insert(TABLE_NAME_USERS, null, values);
	}

	public void clearTableIssue(){
		database.execSQL("DELETE FROM " + TABLE_NAME_ISSUE);
	}

	public void clearRecordUser(String mail){
		database.delete("users", "mail = " + mail, null);
	}

	public void clearTableQuests(){
		database.execSQL("DELETE FROM " + TABLE_NAME_QUESTS);
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
