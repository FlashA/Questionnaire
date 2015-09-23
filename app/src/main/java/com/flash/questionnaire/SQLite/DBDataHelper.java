package com.flash.questionnaire.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBDataHelper {
	
	private static final String DB_NAME = "quests.sqlite";
	
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

	public String getQuestion(int id) {
		String question = null;
	    String query = "SELECT desc_quest FROM questions WHERE id_quest=" + id;
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		question = cursor.getString(0);
		cursor.close();
		return question;
	}

	public int getSize() {
		int question = 0;
		String query = "SELECT COUNT(*) FROM questions";
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		question = cursor.getInt(0);
		cursor.close();
		return question;
	}

	public int getSizeTempTable() {
		int question = 0;
		String query = "SELECT COUNT(*) FROM temp";
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		question = cursor.getInt(0);
		cursor.close();
		return question;
	}

    public int getRightAnswer(int id) {
        int rightAnswer = 0;
        String query = "SELECT answer FROM answers WHERE id_desc=" + id;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        rightAnswer = cursor.getInt(0);
        cursor.close();
        return rightAnswer;
    }

	public int getUserAnswer(int id_desc) {
		int userAnswer = 0;
		String query = "SELECT id_answer FROM temp WHERE id_desc=" + id_desc;
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		userAnswer = cursor.getInt(0);
		cursor.close();
		return userAnswer;
	}

	public boolean getUserAnswerBoolean(int id_desc) {
		String query = "SELECT id_answer FROM temp WHERE id_desc=" + id_desc;
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		if (cursor.getCount()==0) {
			cursor.close();
			return false;
		} else {
			cursor.close();
			return true;
		}
	}


	public void addQuests(String nameQuest){
		ContentValues values = new ContentValues();
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
		values.put("sex", fio);
		values.put("sex", prev_quest);
		values.put("sex", ref);
		values.put("sex", rev);
		values.put("sex", mail);
		values.put("sex", tel);
		database.insert(TABLE_NAME_USERS, null, values);
	}

	public void clearTableIssue(){
		database.execSQL("DELETE FROM " + TABLE_NAME_ISSUE);
	}

	public void clearTableUsers(){
		database.execSQL("DELETE FROM " + TABLE_NAME_USERS);
	}

	public void clearTableQuests(){
		database.execSQL("DELETE FROM " + TABLE_NAME_QUESTS);
	}
}
